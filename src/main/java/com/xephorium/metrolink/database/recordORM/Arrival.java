/*
  Christopher Cruzen                        LaunchCode CS50
  "Arrival.java"                                 05.12.2015

  Problem Set #7

    This class represents a single Metrolink train arrival
  along the train's transit line.

*/
package com.xephorium.metrolink.database.recordORM;

import com.xephorium.metrolink.database.record.Time;

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
