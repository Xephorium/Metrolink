/*
  Christopher Cruzen                       LaunchCode CS50
  "Menu.java"                                   05.21.2015

  Problem Set #6

    This class represents a generic Menu object that, when
  activated, allows the user to choose from a number of
  program options until they decide to quit.

*/
package com.xephorium.metrolink.menu;

public interface Menu
{
    /*--- Methods ---*/

    public void displayGreeting();

    public void runMenu();

    public void displayMenu();

    public void readSelection();

    public void act();
}