package com.company.commands;

import com.company.ConnectDB;

import java.sql.Connection;
import java.sql.Statement;

public class createOrders implements Commands{
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
            String queryCreateOrders = "CREATE table Orders(order_id int, customer_phone varchar(50), date_of_order varchar(50), product_id int, quantity int, delivery_id int, foreign key(customer_phone) references customers(customer_phone), foreign key(product_id) references products(product_id), foreign key(delivery_id) references Delivery(delivery_id));";
            st.executeUpdate(queryCreateOrders);
            st.close();
            con.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
