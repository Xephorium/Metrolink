/*
  Christopher Cruzen                       LaunchCode CS50
  "MetroMenu.java"                              05.09.2015

  Problem Set #6

    This class represents a Metrolink-specific Menu object
  that, when activated, allows the user to choose from a
  number of program options until they decide to quit.

*/
package com.xephorium.metrolink;
import com.xephorium.metrolink.database.*;
import com.xephorium.metrolink.database.record.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MetroMenu
{
    /*--- Fields ---*/

    private String[] menuItems;
    private Scanner  reader;
    private int      selection;


    /*--- Constructors ---*/

    public MetroMenu()
    {
        reader = new Scanner(System.in);
        menuItems = new String[3];
        menuItems[0] = "List Stations";
        menuItems[1] = "Find Next Arrival";
        menuItems[2] = "Quit";
    }


    /*--- Methods ---*/

    public void runMenu()
    {
        do
        {
            this.displayMenu();
            this.readSelection();
            this.act();
        }while(selection != 3);
    }

    public void displayGreeting()
    {
        System.out.print("///////////////////////////\n");
        System.out.print("// Welcome to Metrolink! //\n");
        System.out.print("///////////////////////////\n");
    }

    private void displayMenu()
    {
        System.out.println("\nMain Menu");
        for(int x = 0; x <  menuItems.length; x++)
            System.out.printf("%d. %s\n", (x + 1), menuItems[x]);
        System.out.println("");
    }

    private void readSelection()
    {
        // Input Sentinel
        boolean valid = true;
        do
        {
            if(!valid)
                System.out.println("Invalid input. (Example: 1)");
            System.out.print("Enter the number of your selection: ");
            String input = reader.nextLine();

            try {
                selection = Integer.parseInt(input);
                valid = true;
            }
            catch(NumberFormatException e)
            { valid = false; }

            if(selection < 1 || selection > 3)
                valid = false;

        }while(!valid);
    }

    private void act()
    {
        switch(selection)
        {
            // List Stations
            case 1:
                ArrayList<Station> stations = new ArrayList<Station>();
                // Retrieve Stations
                // Print Stations
                break;

            // Find Next Arrival
            case 2:
                ArrayList<Arrival> stationArrivals = new ArrayList<Arrival>();
                // Retrieve Arrivals
                // Calculate Next Arrival
                // Print Time to Arrival
                break;
        }
    }
}
