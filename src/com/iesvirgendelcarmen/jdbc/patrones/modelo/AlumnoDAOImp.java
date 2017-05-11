package com.iesvirgendelcarmen.jdbc.patrones.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.iesvirgendelcarmen.jdbc.patrones.Conexion;

public class AlumnoDAOImp implements IAlumnoDAO {
	private static final Connection CONEXION = Conexion.getInstanceConexion("bd1.db");
	@Override
	public void insertarAlumnos(List<Alumno> listaAlumnos) {
		// INSERT INTO alumno (nombre, apellidos) 
		//VALUES ('pedro' , 'espinosa garcía')
		try {
			CONEXION.setAutoCommit(false);
		
		String sentenciaSQL = " INSERT INTO alumno (nombre, apellidos)"
				+ " VALUES (?, ?)";
		PreparedStatement preparedStatemen = CONEXION.prepareStatement(sentenciaSQL);
			for (Alumno alumno : listaAlumnos) {
				preparedStatemen.setString(1, alumno.getNombre());
				preparedStatemen.setString(2, alumno.getApellidos());
				preparedStatemen.executeUpdate();
			}
			CONEXION.commit();
		} catch (SQLException e) {
			System.out.println("Error al introducir datos");
			try {
				CONEXION.rollback();
			} catch (SQLException e1) {
				System.out.println("Error en la transacción");
			}
		}
	}

	@Override
	public void borrarAlumno(String apellidos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean comprobarAlumno(String apellidos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Alumno> obtenerAlumnosAprobados() {
		// TODO Auto-generated method stub
		return null;
	}

}
