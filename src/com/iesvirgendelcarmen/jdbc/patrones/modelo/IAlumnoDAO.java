package com.iesvirgendelcarmen.jdbc.patrones.modelo;

import java.util.List;

public interface IAlumnoDAO {
	
	void insertarAlumnos(List<Alumno> listaAlumnos);
	void borrarAlumno(String apellidos);
	boolean comprobarAlumno(String apellidos);
	List<Alumno> obtenerAlumnosAprobados();
	
}
