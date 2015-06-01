/*
  Christopher Cruzen                        LaunchCode CS50
  "MetrolinkDAO.java"                            05.12.2015

  Problem Set #7

    This interface represents a generic set of methods with
  which the Metrolink application can pull data from a
  database. (MetrolinkDAO stands for "Metrolink Database
  Access Object".)

*/
package com.xephorium.metrolink.database;
import com.xephorium.metrolink.database.record.*;

import java.util.ArrayList;

public interface MetrolinkDAO
{
    /*--- Methods ---*/

    public ArrayList<Station> getStations();

    public ArrayList<Time> getStationArrivals(int stationID);
}
