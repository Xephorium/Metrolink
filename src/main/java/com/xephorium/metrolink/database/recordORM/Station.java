/*
  Christopher Cruzen                        LaunchCode CS50
  "Station.java"                                 05.12.2015

  Problem Set #7

    This class represents a single Metrolink Station along
  the train's transit line.

*/
package com.xephorium.metrolink.database.recordORM;

public class Station
{
    /*--- Fields ---*/

    String name;
    int    id;


    /*--- Constructor(s) ---*/

    public Station(String n)
    {
        name = n;
    }

    public Station(String n, int i)
    {
        name = n;
        id = i;
    }


    /*--- Methods ---*/

    public boolean equals(Station st)
    {
        return (st.getName().equalsIgnoreCase(name) && st.getId() == id);
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setId(int i)
    {
        id = i;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }
}
