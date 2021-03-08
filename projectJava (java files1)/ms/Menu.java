package com.company.ms;

import com.company.ConnectDB;
import com.company.tableClasses.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner; //variable scanner referencing to Scanner
    private ArrayList<Customers> customers = new ArrayList<>(); //arraylist for customers class
    private ArrayList<Orders> orders = new ArrayList<>(); //arraylist for orders class
    private ArrayList<Delivery> deliveries = new ArrayList<>(); //arraylist for delivery class
    private ArrayList<Integer> prod_ids = new ArrayList<>(); //arraylist for holding products' ids
    private Connection con = null;
    private ConnectDB connectObj = new ConnectDB();
    private Statement st;
    private ResultSet rs;


    public Menu(){
        scanner = new Scanner(System.in);
    } //for easy accessing to scanner by creating object

    public void start(){ //method for start which does limited options for unauthorised users
        while (true) {
            System.out.println("\nWelcome!");
            System.out.println("Select option: \n1. See Products \n2. See Brands \n3. Purchase Products \n4. Search \n55. Stop the application and delete all tables\n");
            try {
                System.out.print("Enter option (1-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    seeProducts(); //method that provides access to all products
                } else if (option == 2) {
                    seeBrands(); //method that provides access to all brands
                } else if (option == 3){
                    authorisation(); //method for authorisation, without authorisation user cant buy products
                } else if (option == 4){
                    search(); //method for searching for specified classification of products
                } else if (option == 55){
                    deleteAll(); //method for deleting all tables (when it is needed to stop the application and easy run it without errors)
                }
            } catch (InputMismatchException mismatchException) { //if user's input wasnt integer datatype
                System.out.println("Input must be integer");
                scanner.nextInt(); //will take another input
            }
            catch (Exception e) { //cathcing exceptions
                System.out.println(e.getMessage());
            }
            System.out.println("____________________");
        }
    }

    public void seeProducts(){ //method that provides access to all products
        try {
            ResultSet rs2;
            con = connectObj.get_connection();
            st = con.createStatement();
            String querySelect1 = "Select * From Products"; //selecting all from products table
            rs = st.executeQuery(querySelect1); //executing statement
            System.out.println("Our Products:");
            while (rs.next()) { // Processing the result
                System.out.println("Product_id: " + rs.getInt(1)); //outputting first column
                prod_ids.add(rs.getInt(1)); //adding to arraylist products' ids by accessing the resultset
                System.out.println("Brand_id: " + rs.getInt(2)); //outputting second column
                System.out.println("Product_name: " + rs.getString(3)); //outputting third column
                rs2 = st.executeQuery("Select classification_name From Classifications, Products where Classifications.classification_id = " + rs.getInt(4) + ";"); //executing statement for specified classification_id
                String clName = ""; //string variable for holding the classification_name
                while(rs2.next()) //while there are rows
                    clName = rs2.getString(1); //holding the result
                System.out.println("Classification: " + clName); //outputting classification_name
                System.out.println("Price, $: " + rs.getInt(5)); //outputting 5th column
                System.out.println();
            }
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void seeBrands(){ //method that provides access to all brands
        try {
            con = connectObj.get_connection(); //establish connection
            st = con.createStatement(); //initialising statement
            String querySelect2 = "Select * From Brands"; //selecting all from table
            rs = st.executeQuery(querySelect2); //executing statement
            System.out.println("Brands:");
            while (rs.next()) { // Processing the result
                System.out.println("Brand_id: " + rs.getInt(1)); //outputting first column
                System.out.println("Brand_name: " + rs.getString(2));
                System.out.println();
            }
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void search(){ //method for searching for specified classification of products
        System.out.println("You are going to search Products by Classification");
        System.out.println("There are Skincare, Hair & Body, and Makeup");
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            System.out.println("Please, write your Search:");
            String search = scanner.next().toLowerCase(); //holding input values in lower case
            String querySelect = "Select products.product_id, products.product_name, products.brand_id, products.price, classifications.classification_name From Products inner join Classifications on products.classification_id = classifications.classification_id where classifications.classification_name like " + "'%" + search + "%';";
            rs = st.executeQuery(querySelect); //selecting specified columns where classification_name like the input
            System.out.println("You've searched:" + search);
            while (rs.next()) { // Processing the result
                System.out.println("Product_id: " + rs.getInt(1));
                System.out.println("Product_name: " + rs.getString(2));
                System.out.println("Brand_id: " + rs.getInt(3));
                System.out.println("Price, $: " + rs.getInt(4));
                System.out.println("Classification: " + rs.getString(5));
                System.out.println();
            };

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void aMenu(String phone){ //menu for authorised users, has more options than regular one
        while (true) {
            System.out.println("\nWelcome!");
            System.out.println("Select option: \n1. See Products \n2. See Brands \n3. Purchase Products \n4. Search \n5. See Orders \n0. Exit \n");
            try {
                System.out.print("Enter option (1-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    seeProducts(); //method that provides access to all products
                } else if (option == 2) {
                    seeBrands(); //method that provides access to all brands
                } else if (option == 3){
                    purchaseProducts(phone); //method for purchasing products
                } else if (option == 4){
                    search(); //method for searching for specified classification of products
                } else if (option == 5){
                    seeOrders(phone); //method for accessing user's orders
                }else {
                    start(); //exiting from authorised menu to regular menu
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("Input must be integer");
                scanner.nextInt();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________");
        }
    }

    public void purchaseProducts(String phone){ //method for purchasing products
        ArrayList<Integer> orderedproducts = new ArrayList<>(); //arraylist for holding ordered products' ids
        ArrayList<Integer> quantities = new ArrayList<>(); //for holding their quantitites
        String dateoforder = null; //variable for holding the date values
        seeProducts(); //calling the method to see products' ids and prices
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            int response; //for holding the input
            do{
                System.out.println("Do you want to purchase something?(1 - Yes/ 2 - No)");
                response = scanner.nextInt();
                if (response != 1){
                    break;
                }
                else{
                    try {
                        System.out.println("Which product do you want to buy?(write the id of product):");
                        int prod_id = scanner.nextInt();
                        for (int i = 0; i < prod_ids.size(); i++) { //loop for checking desired products' id
                            if (prod_id == prod_ids.get(i)) { //comparing input with existing products' ids
                                orderedproducts.add(prod_id); //adding value to the arraylist
                                System.out.println("Write the quantity of the product:");
                                int quant = scanner.nextInt(); //writing its quantity
                                quantities.add(quant); //adding value to the arraylist
                                LocalDate date = LocalDate.now(); //to get the local date
                                int year = date.getYear(); //holding year values
                                int month = date.getMonthValue(); //holding month values
                                int dayOfMonth = date.getDayOfMonth(); //holding day values
                                dateoforder = String.valueOf(dayOfMonth) + "." + String.valueOf(month) + "." + String.valueOf(year); //adding values to one string variable
                                break; //it is not necessary for running loop after desired product id is found
                            }
                        }
                        System.out.println("Done");
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } while (response == 1); //loop will run at least one time
            if (orderedproducts.size() > 0){ //if there are ordered products
                int retDel = delivery(); //implementing the method and holding its value
                rs = st.executeQuery("SELECT max(order_id) as max_id from Orders;"); //finding the max order_id to make it like serial
                int ord_id = -1; //is going to hold the next order's id
                while (rs.next()) //if there is a row
                    ord_id = rs.getInt(1) + 1; //holding the value
                for (int i = 0; i < orderedproducts.size(); i++) {
                    orders.add(new Orders(ord_id, phone, dateoforder, orderedproducts.get(i), quantities.get(i), retDel)); //creating objects for orders class
                }
                System.out.println("You have successfully placed an order!");
            }
            orderedproducts.clear(); //deleting all values for future ordered products
            quantities.clear(); //deleting
            st.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int delivery(){ //method for inputting values of address to make a delivery
        String state, city, address; //variables to hold the inputs
        int del_id = -1; //is going to hold the next delivery's id
        System.out.println("we are going to deliver Ordered Products for you");
        System.out.println("Please, write your State/Country:");
        state = scanner.next();
        System.out.println("Please, write your City:");
        city = scanner.next();
        System.out.println("Please, write your address:");
        address = scanner.next();
        try {
            rs = st.executeQuery("SELECT max(delivery_id) as max_id from Delivery"); //finding the max delivery_id to make it like serial
            while (rs.next()) //if there is a row
                del_id = (rs.getInt(1)) + 1; //holding the value
            deliveries.add(new Delivery(del_id, state, city, address)); //creating objects for delivery class
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return del_id; //returning value of delivery_id
    }

    public void authorisation(){ //method for authorisation
        while (true) {
            System.out.println("Please, authorise to Purchase Products");
            System.out.println("Select option: \n1. Sign Up \n2. Sign In");
            try {
                System.out.print("Enter option (1-2): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    customerSignUp();
                } else if (option == 2) {
                    customerSignIn();
                } else {
                    break;
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("Input must be integer");
                scanner.nextInt();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________");
        }
    }

    public void customerSignIn(){ //method for sign in
        String phone;
        System.out.println("You are going to Sign In");
        System.out.println("Please, write your phone number:");
        phone = scanner.next();
        for (Customers customer : customers) {
            if (phone.equals(customer.getCustomer_phone())) {
                System.out.println("You can shop now!");
                aMenu(phone);
            } else {
                System.out.println("Sorry, You should Sign Up");
                customerSignUp();
            }
        }
    }

    public void customerSignUp(){ //method for sign up
        int id;
        String name, phone, email; //variables for holding the inputs
        System.out.println("You are going to Sign Up");
        System.out.println("Please, write your name:");
        name = scanner.next();
        System.out.println("Please, write your phone number:");
        phone = scanner.next();
        System.out.println("Please, write your email address:");
        email = scanner.next();
        customers.add(new Customers(phone, name, email)); //creating new object of customers
        System.out.println("You can shop now!");
        aMenu(phone); //implementing authorised menu
    }

    public void seeOrders(String phone){ //method for accessing user's orders
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String querySelect = "Select * From Orders inner join Customers on Orders.customer_phone = Customers.customer_phone where Customers.customer_phone =" + "'" + phone+ "';";
            rs = st.executeQuery(querySelect); //selecting all from orders where phone number equals to the customer's phone
            while (rs.next()) { // Processing the result

                System.out.println("Order_id: " + rs.getInt(1));
                System.out.println("Customer_phone: " + rs.getString(2));
                System.out.println("Date_of_order: " + rs.getString(3));
                System.out.println("Product_id: " + rs.getInt(4));
                System.out.println("Quantity: " + rs.getInt(5));
                System.out.println("Delivery_id:" + rs.getInt(6));
                System.out.println();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll(){ //method for deleting all tables
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            st.executeUpdate("drop table Orders");
            st.executeUpdate("drop table Products");
            st.executeUpdate("drop table Delivery");
            st.executeUpdate("drop table Customers");
            st.executeUpdate("drop table Classifications");
            st.executeUpdate("drop table Brands");
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
