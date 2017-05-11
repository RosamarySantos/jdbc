package com.iesvirgendelcarmen.jdbc.ejemplo1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class Ejemplo1 {

	public static void main(String[] args) {
		 // cargar sqlite-JDBC driver
	    try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			Connection connection = DriverManager.
					getConnection("jdbc:sqlite:BD/bd2.db",config.toProperties());
			Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);  // set timeout to 30 sec.

		      statement.executeUpdate("drop table if exists person");
		      statement.executeUpdate("create table person (id integer, name text)");
		      statement.executeUpdate("insert into person values(1, 'leo')");
		      statement.executeUpdate("insert into person values(2, 'yui')");
		      ResultSet rs = statement.executeQuery("select * from person");
		      while(rs.next())
		      {
		        // read the result set
		        System.out.println("name = " + rs.getString("name"));
		        System.out.println("id = " + rs.getInt("id"));
		      }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
