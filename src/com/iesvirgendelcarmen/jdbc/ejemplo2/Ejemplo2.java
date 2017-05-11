package com.iesvirgendelcarmen.jdbc.ejemplo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class Ejemplo2 {

	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		try {
			Connection connection = DriverManager.
					getConnection("jdbc:sqlite:BD/bd1.db",config.toProperties());
			//insertamos un alumno, por ejemplo
			String sql = "INSERT INTO alumno (nombre, apellidos) "
					+ "VALUES('luisa', 'pérez lópez')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			//aprovechamos el objeto statement para una consulta
			sql = "SELECT * FROM modulo";
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()){
				System.out.println("MÓDULO: " + resulset.getString("nombre"));
				System.out.println("Nº HORAS: " + resulset.getInt("numeroHoras"));
			}
			//aprovechamos el objeto statement para un delete
			sql = "DELETE FROM alumno where nombre = 'luisa'";
			statement.executeUpdate(sql);
			//usamos PreparedStatement para mayor seguridad
			sql = "UPDATE curso SET aula = ? WHERE id = ?";
			PreparedStatement preparedStatement = 
					connection.prepareStatement(sql);
			preparedStatement.setString(1, "Aula polivalente");
			preparedStatement.setInt(2, 1);
			preparedStatement.executeUpdate();
			//muy mal diseño del cerrado de conexión
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
