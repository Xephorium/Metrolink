/*
  Christopher Cruzen                        LaunchCode CS50
  "SqliteDAO.java"                               05.12.2015

  Problem Set #6

    This class contains a concrete set of database access
  methods which give the MetrolinkDAO interface form.

*/
package com.xephorium.metrolink.database;

public class SqliteDAO implements MetrolinkDAO
{
    /*--- Methods ---*/

    public String[] getStations()
    {
        return new String[]{" ", " ", " "};
    }

    public String[] getStationArrivals(int stationID)
    {
        return new String[]{" ", " ", " "};
    }
}
