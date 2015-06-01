/*
  Christopher Cruzen                        LaunchCode CS50
  "Arrival.java"                                 05.12.2015

  Problem Set #7

    This class represents a single Metrolink train arrival
  along the train's transit line.

*/
package com.xephorium.metrolink.database.record;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="stop_times")
public class Arrival
{
    /*--- Fields ---*/

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="arrival_time")
    private String time;
    @Column(name="stop_id")
    private int stop_id;

    /*--- Constructor(s) ---*/

    public Arrival() {}

    public Arrival(String t)
    {
        time = t;
    }

    public Arrival(String t, int id)
    {
        time    = t;
        stop_id = id;
    }


    /*--- Methods ---*/

    public void setTime(String t)
    {
        time = t;
    }

    public void setStopID(int id)
    {
        stop_id = id;
    }

    public String getTime()
    {
        return time;
    }

    public int getStopID()
    {
        return stop_id;
    }

}
