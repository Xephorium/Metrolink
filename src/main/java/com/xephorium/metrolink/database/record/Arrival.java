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

    int    time;
    String headSign;


    /*--- Constructor(s) ---*/

    public Arrival(int t)
    {
        time = t;
    }

    public Arrival(int t, String h)
    {
        time = t;
        headSign = h;
    }


    /*--- Methods ---*/

    public void setTime(int t)
    {
        time = t;
    }

    public void setHeadSign(String h)
    {
        headSign = h;
    }

    public int getTime()
    {
        return time;
    }

    public String getHeadSign()
    {
        return headSign;
    }
}
