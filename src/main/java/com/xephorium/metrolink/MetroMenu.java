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

    private final String RETURN = "back";

    private MetrolinkDAO databaseReader;
    private String[]     menuItems;
    private Scanner      reader;
    private int          selection;


    /*--- Constructors ---*/

    public MetroMenu()
    {
        // TEMP - TO BE REPLACED BY DI
        databaseReader = new SqliteDAO();
        // TEMP

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
            this.readInteger();
            this.act();
        }while(selection != menuItems.length);
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

    private void readInteger()
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

            if(selection < 1 || selection > menuItems.length)
                valid = false;

        }while(!valid);
    }

    private Station readUserStation()
    {
        Station input;

        // Input Sentinel
        boolean valid = true;
        do
        {
            // Prompt User & Get Input
            if(!valid)
                System.out.println("Invalid station name.");
            System.out.println("Enter the name of your current station");
            System.out.print("or 'back' to return to the Main Menu: ");
            input = new Station(reader.nextLine());

            // Get Stations List
            ArrayList<Station> stations;
            stations = databaseReader.getStations();

            // Check User Station Validity
            for(Station st : stations)
            {
                if (st.getName().equalsIgnoreCase(input.getName()))
                {
                    input.setId(st.getId());
                    valid = true;
                    break;
                }
                else
                    valid = false;
            }
            if(input.getName().equalsIgnoreCase(RETURN))
            {
                valid = true;
            }

        }while(!valid);

        return input;
    }

    private void act()
    {
        switch(selection)
        {
            // List Stations
            case 1:
                ArrayList<Station> stations = databaseReader.getStations();
                for(Station station: stations)
                    System.out.printf("   %s\n", station.getName());
                break;

            // Find Next Arrival
            case 2:
                Station current = readUserStation();
                if(current.getName().equalsIgnoreCase(RETURN))
                    break;
                ArrayList<Arrival> stationArrivals
                        = databaseReader.getStationArrivals(current.getId());
                for(Arrival arrival: stationArrivals)
                    System.out.printf("   %s\n", arrival.getTime().toString());
                // Calculate Next Arrival
                // Print Time to Arrival
                break;
        }
    }
}
