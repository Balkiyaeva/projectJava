package com.company.tableClasses;

import com.company.ConnectDB; //importing class
import com.company.commands.Commands;

import java.sql.*;

public class Delivery implements Commands {
    private int delivery_id;
    private String state_of_delivery;
    private String city_of_delivery;
    private String address_of_delivery;
    private Connection con = null;
    private final ConnectDB connectObj = new ConnectDB();
    private Statement st;

    @Override
    public void insertValues() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryInsert = "Insert Into Delivery(delivery_id, state_of_delivery, city_of_delivery, address_of_delivery) VALUES (?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(queryInsert);

            stm.setInt(1, getDelivery_id());
            stm.setString(2, getState_of_delivery());
            stm.setString(3, getCity_of_delivery());
            stm.setString(4, getAddress_of_delivery());

            stm.execute();
            st.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createTables() {
    }
    public Delivery (int delivery_id, String state_of_delivery, String city_of_delivery, String address_of_delivery){
        setDelivery_id(delivery_id);
        setState_of_delivery(state_of_delivery);
        setCity_of_delivery(city_of_delivery);
        setAddress_of_delivery(address_of_delivery);
        insertValues(); //all set values is going to be inserted into the table
    }
    //getters and setters
    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getState_of_delivery() {
        return state_of_delivery;
    }

    public void setState_of_delivery(String state_of_delivery) {
        this.state_of_delivery = state_of_delivery;
    }

    public String getCity_of_delivery() {
        return city_of_delivery;
    }

    public void setCity_of_delivery(String city_of_delivery) {
        this.city_of_delivery = city_of_delivery;
    }

    public String getAddress_of_delivery() {
        return address_of_delivery;
    }

    public void setAddress_of_delivery(String address_of_delivery) {
        this.address_of_delivery = address_of_delivery;
    }
}
