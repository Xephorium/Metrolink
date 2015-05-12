/*
  Christopher Cruzen                        LaunchCode CS50
  "MetrolinkDAO.java"                            05.12.2015

  Problem Set #6

    This interface represents a generic set of methods with
  which the Metrolink application can pull data from a
  database. (MetrolinkDAO stands for "Metrolink Database
  Access Object".)

*/
package com.xephorium.metrolink.database;

public interface MetrolinkDAO
{
    /*--- Methods ---*/

    public String[] getStations();

    public String[] getStationArrivals(int stationID);
}
