package com.company.commands;

import com.company.ConnectDB;

import java.sql.Connection;
import java.sql.Statement;

public class createBrands implements Commands{
    private Connection con = null;
    private final ConnectDB connectObj = new ConnectDB();
    private Statement st;

    @Override
    public void insertValues() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryInsert = "Insert Into Brands(brand_id, brand_name) VALUES (1, 'Avery'), (2, 'Bailey');";
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
            String queryCreateBrands = "create table brands (brand_id int NOT NULL, brand_name varchar (50) NOT NULL, PRIMARY KEY (brand_id));";
            st.executeUpdate(queryCreateBrands);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
