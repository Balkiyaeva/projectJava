package com.company.commands;

import com.company.ConnectDB;

import java.sql.*;

public class createProducts implements Commands{
    private Connection con = null;
    private final ConnectDB connectObj = new ConnectDB();
    private Statement st;
    @Override
    public void insertValues() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String value1 = "(11, 1, 'Product 1', 101, 53), ";
            String value2 = "(12, 1, 'Product 2', 102, 93), ";
            String value3 = "(13, 2, 'Product 3', 103, 7), ";
            String value4 = "(14, 2, 'Product 4', 101, 97), ";
            String value5 = "(15, 1, 'Product 5', 103, 4);";
            String queryInsert = "Insert Into Products VALUES" + value1 + value2 + value3 + value4 + value5;
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
            String queryCreateProducts = "Create Table Products (product_id int NOT NULL, brand_id int NOT NULL, product_name varchar (50) NOT NULL, classification_id int NOT NULL, price int NOT NULL, PRIMARY KEY (product_id), FOREIGN KEY (brand_id) references brands(brand_id), FOREIGN KEY (classification_id) references classifications(classification_id));";
            st.executeUpdate(queryCreateProducts);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
