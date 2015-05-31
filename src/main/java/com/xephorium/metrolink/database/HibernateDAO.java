/*
  Christopher Cruzen                        LaunchCode CS50
  "HibernateDAO.java"                            05.30.2015

  Problem Set #7

    This class contains a concrete set of database access
  methods which give the MetrolinkDAO interface form.

*/
package com.xephorium.metrolink.database;

import com.xephorium.metrolink.database.recordORM.*;
import com.xephorium.metrolink.database.recordORM.Time;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;

@Repository("temp")
public class HibernateDAO implements MetrolinkDAO
{
    /*--- Fields ---*/

    public static final String JDBC_SQLITE_METROLINK_DB = "jdbc:sqlite:metrolink.db";
    public static final String ORG_SQLITE_JDBC          = "org.sqlite.JDBC";

    /*--- Methods ---*/

    public ArrayList<Station> getStations()
    {
        return new ArrayList<Station>();
    }

    public ArrayList<Time> getStationArrivals(int stationID)
    {
        return new ArrayList<Time>();
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

}
