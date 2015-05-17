/*
  Christopher Cruzen                        LaunchCode CS50
  "Arrival.java"                                 05.12.2015

  Problem Set #6

    This class represents a single Metrolink train arrival
  along the train's transit line.

*/
package com.xephorium.metrolink.database.record;
import java.time.*;

public class Arrival
{
    /*--- Fields ---*/

    LocalTime time;


    /*--- Constructor(s) ---*/

    public Arrival(LocalTime t)
    {
        time = t;
    }


    /*--- Methods ---*/

    public void setTime(LocalTime t)
    {
        time = t;
    }

    public LocalTime getTime()
    {
        return time;
    }

}
