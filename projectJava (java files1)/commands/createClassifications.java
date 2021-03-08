package com.company.commands;

import com.company.ConnectDB;

import java.sql.*;

public class createClassifications implements Commands{
    private Connection con = null;
    private final ConnectDB connectObj = new ConnectDB();
    private Statement st;
    @Override
    public void insertValues() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryInsert = "Insert Into Classifications VALUES (101, 'skincare'), (102, 'hair & body'), (103, 'makeup');";
            st.executeUpdate(queryInsert);
            st.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createTables() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryCreateProducts = "create table classifications (classification_id int NOT NULL, classification_name varchar (50) NOT NULL, PRIMARY KEY (classification_id));";
            st.executeUpdate(queryCreateProducts);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
