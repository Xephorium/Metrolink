/*
  Christopher Cruzen                        LaunchCode CS50
  "Arrival.java"                                 05.12.2015

  Problem Set #6

    This class represents a single Metrolink train arrival
  along the train's transit line.

*/
package com.xephorium.metrolink.database.record;

public class Arrival
{
    /*--- Fields ---*/

    Time time;


    /*--- Constructor(s) ---*/

    public Arrival(Time t)
    {
        time = t;
    }


    /*--- Methods ---*/

    public void setTime(Time t)
    {
        time = t;
    }

    public Time getTime()
    {
        return time;
    }

}
