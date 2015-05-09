/*
  Christopher Cruzen                         LaunchCode CS50
  "Metrolink.java"                                05.06.2015

  Problem Set #6

    Create a command line application that employs SQLite to
  read data from a simple database. The application should
  list all metrolink stations, ask the user which station
  they're currently at, and display the amount of time until
  that station's next train will arrive.
    Be sure the project uses maven, Spring, and is unit
  testable.

*/
package com.xephorium.metrolink;

public class Metrolink
{
    /*--- Fields ---*/

    /*--- Constructors ---*/

    /*--- Methods ---*/

    public static void main(String[] args)
    {
        MetroMenu myMenu = new MetroMenu();

        myMenu.displayGreeting();
        myMenu.runMenu();

    }
}
