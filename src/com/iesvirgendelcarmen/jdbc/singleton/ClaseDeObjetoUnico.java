package com.iesvirgendelcarmen.jdbc.singleton;

public class ClaseDeObjetoUnico {
	//objeto ÚNICO que se puede crear
	private static ClaseDeObjetoUnico objetoUnico = null;
	//desde fuera no se pueden crear objetos
	//para esto el constructor tiene accesibilidad privada
	static int contadorObjetos = 0; //accesibilidad package
	static int contadorLlamadaMetodo = 0; //accesibilidad package
	private ClaseDeObjetoUnico(){
		contadorObjetos++;		
	}
	
	//creamos un método de accesibilidad publica para que desde fuera
	//podamos obtener ese objeto único, ademas de controlar que solo
	//exista un único objeto
	
	public static ClaseDeObjetoUnico getInstanceClaseDeObjetoUnico(){
		contadorLlamadaMetodo++;
		if (objetoUnico == null)
			objetoUnico = new ClaseDeObjetoUnico();
		return objetoUnico;
	}
}
