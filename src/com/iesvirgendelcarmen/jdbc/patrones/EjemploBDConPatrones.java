package com.iesvirgendelcarmen.jdbc.patrones;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.iesvirgendelcarmen.jdbc.patrones.modelo.Alumno;
import com.iesvirgendelcarmen.jdbc.patrones.modelo.AlumnoDAOImp;
import com.iesvirgendelcarmen.jdbc.patrones.modelo.IAlumnoDAO;

public class EjemploBDConPatrones {
	private static final IAlumnoDAO ALUMNO_DAO = new AlumnoDAOImp();
	public static void main(String[] args) {
		
		/*//obtenemos el manejador de la conexion
		Connection conexion = Conexion.getInstanceConexion("bd1.db");
		//comprobar que manipulamos la BD
		String sentenciaSQL = "SELECT * FROM alumno";
		try {
			Statement statement = conexion.createStatement();
			ResultSet resulset = statement.executeQuery(sentenciaSQL);
			while(resulset.next())
				System.out.println(resulset.getString("apellidos"));
			Conexion.cerrarConexion();
		} catch (SQLException e) {
			System.out.println("Error en la ejecución SQL");
		}*/
		//1º obtener la lista de alumnos
		List<Alumno> listaAlumnos = obtenerListaAlumnos("ficheros/alumnos.txt");
		//System.out.println(listaAlumnos);
		//2º insertar los datos de los alumnos
	//	insertarDatosBD(listaAlumnos);
		//borrar una alumno por apellidos
	//	borrarAlumnoBD();
		// 3º Comprobar la existencia de un alumno
		existeAlumnoBD();
	}
	
	public static List<Alumno> obtenerListaAlumnos(String nombreFichero){
		List<Alumno> listaAlumnos = new ArrayList<>();
		try (Scanner in = new Scanner(new File(nombreFichero));){
			while(in.hasNextLine()){
				String[] alumnos = in.nextLine().split(",");
				if (alumnos[0].equals(""))
					alumnos[0] = null;
				Alumno alumno = new Alumno(alumnos[1].trim(),
						alumnos[0]);
				listaAlumnos.add(alumno);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error en la lectura del fichero");
		}
		
		
		return listaAlumnos;
	}
	
	public static void insertarDatosBD(List<Alumno> listaAlumnos){
		ALUMNO_DAO.insertarAlumnos(listaAlumnos);
	}
	
	public static void borrarAlumnoBD(){
		Scanner in = new Scanner(System.in);
		System.out.println("Introduce los apellidos:");
		String apellidos = in.nextLine();
		in.close();
		ALUMNO_DAO.borrarAlumno(apellidos);
	}
	
	public static void existeAlumnoBD(){
		String inputApellidos = JOptionPane.showInputDialog(
				"Introduce los apellidos del alumno");
		//System.out.println(inputValue);
		boolean exito = ALUMNO_DAO.comprobarAlumno(inputApellidos);
		if (!exito)
			JOptionPane.showMessageDialog(null,"No existe el alumno "
		+ inputApellidos, "INFORMACIÓN",  JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null,  "Existe el alumno "
					+ inputApellidos,"INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
	}

}
