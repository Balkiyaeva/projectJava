package com.company.tableClasses;

import com.company.ConnectDB; //importing class
import com.company.commands.Commands; //importing interface from package commands

import java.sql.*; //importing api for jdbms

public class Customers implements Commands { //class implements the interface commands
    private String customer_phone;
    private String customer_name;
    private String customer_email;
    private Connection con = null; //field for connection
    private final ConnectDB connectObj = new ConnectDB(); //creating new object of connectDB
    private Statement st; //field for statements for executing

    @Override //overriding method of interface
    public void createTables() {
    }

    @Override
    public void insertValues() { //method which is going to insert values into the table
        try {
            con = connectObj.get_connection(); //establishing connection
            st = con.createStatement(); //creating statements for executing
            String queryInsert = "Insert Into Customers(customer_phone, customer_name, customer_email) VALUES (?,?,?)"; //sql command for inserting values
            PreparedStatement stm = con.prepareStatement(queryInsert); //creating object of prepare statement to execute

            stm.setString(1, getCustomer_phone()); //precompiling and storing in prepared statement by giving the value for the first column
            stm.setString(2, getCustomer_name()); //precompiling and storing in prepared statement by giving the value for the second column
            stm.setString(3, getCustomer_email()); //precompiling and storing in prepared statement by giving the value for the third column

            stm.execute(); //executing prepared statement
            st.close(); //closing db connection
            con.close(); //closing db connection
        }catch (Exception e) { //catching exceptions
            System.out.println(e.getMessage());
        }
    }
    public Customers(String customer_phone, String customer_name, String customer_email){ //constructor for Customers class
        setCustomer_phone(customer_phone);
        setCustomer_name(customer_name);
        setCustomer_email(customer_email);
        insertValues(); //all set values is going to be inserted into the table
    }
    //getters and setters
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }


}
