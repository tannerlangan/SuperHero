package com.SuperHeroList;
import java.util.*;
import java.io.*;
import java.lang.*;


/*Most recent code finished:
    Implemented menu and 
 */


public class Main {


    public static void main(String arg[]) {

        Scanner in = new Scanner(System.in);
        int choice;
        Menu menu = new Menu();
        SuperHero superhero = new SuperHero();
        do {
            do {
                menu.main();
                choice = in.nextInt();
            } while (choice < 0 || choice > 6);

            if (choice == 2) {
                System.out.println("Please Enter Superheroes name");
                String name = in.next();
                System.out.println("Please enter their superpower");
                String power = in.next();
                System.out.println("Please enter their height in cm");
                double height = in.nextDouble();
                System.out.println("Please enter how many civilians they have saved");
                int civSaved = in.nextInt();
                superhero.newHero(name, power, height, civSaved);
            }
        }while(choice != 0);

         in.close();

    }
}