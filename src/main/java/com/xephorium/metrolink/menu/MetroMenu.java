/*
  Christopher Cruzen                       LaunchCode CS50
  "MetroMenu.java"                              05.09.2015

  Problem Set #7

    This class represents a Metrolink-specific Menu object
  that, when activated, allows the user to choose from a
  number of program options until they decide to quit.

*/
package com.xephorium.metrolink.menu;
import com.xephorium.metrolink.database.*;
import com.xephorium.metrolink.database.record.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Scanner;

@Component("myMenu")
@Scope("prototype")
public class MetroMenu implements Menu
{
    /*--- Fields ---*/

    private final String RETURN = "back";

    @Autowired
    private MetrolinkDAO databaseReader;
    private String[]     menuItems;
    private Scanner      reader;
    private int          selection;


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

    public void displayGreeting()
    {
        System.out.print("///////////////////////////\n");
        System.out.print("// Welcome to Metrolink! //\n");
        System.out.print("///////////////////////////\n");
    }

    public void runMenu()
    {
        do
        {
            this.displayMenu();
            this.readSelection();
            this.act();
        }while(selection != menuItems.length);
    }

    public void displayMenu()
    {
        System.out.println("\nMain Menu");
        for(int x = 0; x <  menuItems.length; x++)
            System.out.printf("%d. %s\n", (x + 1), menuItems[x]);
        System.out.println("");
    }

    public void readSelection()
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

    public void act()
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
                ArrayList<Time> times = databaseReader.getStationArrivals(current.getId());
                Time currentTime  = Time.getCurrent();
                Time untilArrival = times.get(0);
                for(Time t: times)
                    if(currentTime.until(t).earlierThan(untilArrival))
                        untilArrival = currentTime.until(t);
                System.out.println("Next arrival in " + untilArrival.toString() + ".");
                break;
        }
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
            System.out.print("Current Station (or 'back'): ");
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
}
