package com.company.commands;

import com.company.ConnectDB;
import java.sql.*;

public class createCustomers implements Commands {
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
            String queryCreateCustomers = "Create Table Customers(customer_phone varchar(50), customer_name varchar (50), customer_email varchar (50), primary key(customer_phone));";
            st.executeUpdate(queryCreateCustomers);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
