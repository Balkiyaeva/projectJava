package com.company;

import com.company.commands.*; //importing all classes from commands package
import com.company.ms.Menu;

import java.util.ArrayList; //importing a library for using the arraylist

public class Main {

    public static void main(String[] args) {
        createTables(); //calling method for creating tables
        Menu menu = new Menu(); //creating new object of Menu class
        menu.start(); //calling method for running the menu
    }
    public static void createTables() {
        ArrayList<Commands> createTables = new ArrayList<>(); //creating arraylist of objects Commands reference
        createTables.add(new createBrands()); //adding to arraylist new object of createBrands
        createTables.add(new createClassifications()); //adding to arraylist new object of createClassifications
        createTables.add(new createProducts()); //adding to arraylist new object of createProducts
        createTables.add(new createCustomers()); //adding to arraylist new object of createCustomers
        createTables.add(new createDelivery()); //adding to arraylist new object of createDelivery
        createTables.add(new createOrders()); //adding to arraylist new object of createOrders
        for (Commands c : createTables){ //for every object in createtables variable
            c.createTables(); //implementing method which creates tables
            c.insertValues(); //implementing method which inserts values of some constant tables
        }
    }
}
