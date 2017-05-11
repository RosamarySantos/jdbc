package com.iesvirgendelcarmen.jdbc.patrones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

/* clase que usa el patrón singlenton para devolver un
 * único objeto de tipo Connection.
 * Ese objeto Connection puede manipular la BD
 */
public class Conexion {
	private static Connection conexion = null;
	//constructor privado para evitar que desde fuera se puedan
	//crear nuevos objetos
	private Conexion (String nombreBD){
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		try {
			conexion = DriverManager.
					getConnection("jdbc:sqlite:BD/" + nombreBD,
							config.toProperties());
		} catch (SQLException e) {
			System.out.println("Error al acceder a la BD");
		}
	}
	
	//método que crea y controla el objeto conexion
	public static Connection getInstanceConexion(String nombreBaseDatos){
		//comprabamos si existe la conexión
		if (conexion == null)
			new Conexion(nombreBaseDatos);
		return conexion;
	}
	
	public static void cerrarConexion(){
		if (conexion != null)
			try {
				conexion.close();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la BD");
			}
	}
	
	
	
	
	
	
	
	
}
