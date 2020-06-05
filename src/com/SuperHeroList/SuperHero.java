package com.SuperHeroList;

import java.util.ArrayList;

public class SuperHero {

    private String name, superpower;
    private double height;
    private int civiliansSaved;

    public SuperHero(String name, String superpower, double heightInCM, int civiliansSaved) {
        this.name = name;
        this.superpower = superpower;
        this.height = heightInCM;
        this.civiliansSaved = civiliansSaved;
    }

    public SuperHero() {
    }



    public String toString() {
        return "superhero{Name: '" + name + "',Superpower: '" + superpower + "',Height: " + height + ",Civilians Saved: " + civiliansSaved +"}";
    }

    public void removeHero(ArrayList<SuperHero> heroList, int indexToBeRemoved, String superHero) {

        heroList.remove(indexToBeRemoved);
        System.out.println(superHero + " has been removed from the Superhero List");
    }

    public void listHeroes(ArrayList<SuperHero> heroList) {

        for (int i = 0; i < heroList.size(); i++) {
            System.out.print("ID: " + (i + 1) + " ");
            System.out.println("Name: " + heroList.get(i).getName() + ", Superpower: " + heroList.get(i).getSuperpower() + ", Height: " + heroList.get(i).getHeight() + ", Civlians Saved: " + heroList.get(i).getCiviliansSaved());

        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCiviliansSaved() {
        return civiliansSaved;
    }

    public void setCiviliansSaved(int civiliansSaved) {
        this.civiliansSaved = civiliansSaved;
    }



}
