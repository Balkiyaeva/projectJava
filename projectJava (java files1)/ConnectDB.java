package com.company;

import java.sql.*;

public class ConnectDB {
	
	
	public Connection get_connection() {
		Connection connection = null; //field for connection
		String host = "localhost"; //field for host
		String port = "5432"; //field for port
		String db_name = "postgres"; //field for database's name
		String username = "postgres"; //field for username's name
		String password = "25462"; //field for password

		   try { //using exception handling for connection to db
			  Class.forName("org.postgresql.Driver");
			  connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name, username, password); //establish the connection
			   
			  if(connection!=null) { //if connection is established successfully
				  System.out.print(" ");
			  } else {
				  System.out.println("Database failed to open \n");
			  }
			   	   
		   }catch(Exception e) { //catching an exception
			   System.out.println(e);
		   }
		return connection;
	}

}
