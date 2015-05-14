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
import java.util.ArrayList;

public class SqliteDAO implements MetrolinkDAO
{
    /*--- Methods ---*/

    public ArrayList<Station> getStations()
    {


        return new ArrayList<Station>();
    }

    public ArrayList<Arrival> getStationArrivals(int stationID)
    {


        return new ArrayList<Arrival>();
    }
}
