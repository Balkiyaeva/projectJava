package com.company.commands;

import com.company.ConnectDB;

import java.sql.*;

public class createDelivery implements Commands{
    @Override
    public void insertValues() {
    }

    @Override
    public void createTables() {
        try {
            Connection con = null;
            ConnectDB connectObj = new ConnectDB();
            Statement st;
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryCreateDelivery = "create table delivery (delivery_id int NOT NULL, state_of_delivery varchar (50) NOT NULL, city_of_delivery varchar (50) NOT NULL, address_of_delivery varchar (50) NOT NULL,PRIMARY KEY (delivery_id));";
            st.executeUpdate(queryCreateDelivery);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
