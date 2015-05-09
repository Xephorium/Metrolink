/*
  Christopher Cruzen                         LaunchCode CS50
  "MetroMenu.java"                                05.09.2015

  Problem Set #6

*/
package com.xephorium.metrolink;
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

    }
}
