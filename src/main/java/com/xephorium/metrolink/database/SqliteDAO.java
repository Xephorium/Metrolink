/*
  Christopher Cruzen                        LaunchCode CS50
  "SqliteDAO.java"                               05.12.2015

  Problem Set #6

    This class contains a concrete set of database access
  methods which give the MetrolinkDAO interface form.

*/
package com.xephorium.metrolink.database;
import com.xephorium.metrolink.database.record.*;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class SqliteDAO implements MetrolinkDAO
{
    /*--- Fields ---*/

    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    public static final String ORG_SQLITE_JDBC          = "org.sqlite.JDBC";

    /*--- Methods ---*/

    public ArrayList<Station> getStations()
    {
        try(Connection connection = getConnection();)
        {
            // Read From Database
            String query = "SELECT DISTINCT stop_name,stop_id FROM stops WHERE stop_name GLOB '*METROLINK STATION';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse Stations
            ArrayList<Station> stations = new ArrayList<Station>();
            while(resultSet.next())
            {
                Station station = new Station(resultSet.getString("stop_name"));
                station.setId(resultSet.getInt("stop_id"));
                stations.add(station);
            }

            // Return Stations
            return stations;
        }
        catch (SQLException e)
        { throw new RuntimeException("Error Retrieving Stations"); }
    }

    public ArrayList<Arrival> getStationArrivals(int stationID)
    {
        try(Connection connection = getConnection();)
        {
            // Read From Database
            String query = "SELECT DISTINCT arrival_time FROM stop_times WHERE stop_id = " + stationID + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Parse Arrivals
            ArrayList<Arrival> arrivals = new ArrayList<Arrival>();
            while(resultSet.next())
            {
                // Convert Literal to Time
                LocalTime time = convertDatabaseTime(resultSet.getString("arrival_time"));

                // Add Time
                Arrival arrival = new Arrival(time);
                arrivals.add(arrival);
            }

            // Return Arrivals
            return arrivals;
        }
        catch (SQLException e)
        { throw new RuntimeException("Error Retrieving Stations"); }
    }

    private static Connection getConnection() throws SQLException
    {
        try
        {
            Class.forName(ORG_SQLITE_JDBC);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Database Connector Class Not Found");
        }

        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    public LocalTime convertDatabaseTime(String literal)
    {
        System.out.println(literal);
        int hour   = Integer.parseInt(literal.substring(0,2));
        int minute = Integer.parseInt(literal.substring(3,5));
        int second = Integer.parseInt(literal.substring(6,8));
        return LocalTime.of(hour, minute, second);
    }
}
