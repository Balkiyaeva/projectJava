package com.company.tableClasses;

import com.company.ConnectDB;
import com.company.commands.Commands;

import java.sql.*;

public class Orders implements Commands {
    private int order_id;
    private String customer_phone;
    private String date_of_order;
    private int product_id;
    private int quantity;
    private int delivery_id;
    private Connection con = null;
    private final ConnectDB connectObj = new ConnectDB();
    private Statement st;

    @Override
    public void createTables() {
    }

    @Override
    public void insertValues() {
        try {
            con = connectObj.get_connection();
            st = con.createStatement();
            String queryInsert = "Insert Into Orders(order_id, customer_phone, date_of_order, product_id, quantity, delivery_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(queryInsert);

            stm.setInt(1, getOrder_id());
            stm.setString(2, getCustomer_phone());
            stm.setString(3, getDate_of_order());
            stm.setInt(4, getProduct_id());
            stm.setInt(5, getQuantity());
            stm.setInt(6, getDelivery_id());

            stm.execute();
            st.close();
            con.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Orders(int order_id, String customer_phone, String date_of_order, int product_id, int quantity, int delivery_id){
        setOrder_id(order_id);
        setCustomer_phone(customer_phone);
        setDate_of_order(date_of_order);
        setProduct_id(product_id);
        setQuantity(quantity);
        setDelivery_id(delivery_id);
        insertValues();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(String date_of_order) {
        this.date_of_order = date_of_order;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }
}
