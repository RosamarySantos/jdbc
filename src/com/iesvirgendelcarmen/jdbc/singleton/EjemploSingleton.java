package com.iesvirgendelcarmen.jdbc.singleton;

public class EjemploSingleton {

	public static void main(String[] args) {
		// obtener el objeto único
		ClaseDeObjetoUnico objetoUnico;
		for (int i = 0; i < 10; i++) {
			objetoUnico = ClaseDeObjetoUnico.getInstanceClaseDeObjetoUnico();
			System.out.println(objetoUnico);
		}
		System.out.println("Contador de objetos: " 
				+ ClaseDeObjetoUnico.contadorObjetos);
		System.out.println("Contador de llamadas: " 
				+ ClaseDeObjetoUnico.contadorLlamadaMetodo);
	}

}
