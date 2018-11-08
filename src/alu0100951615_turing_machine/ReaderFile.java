package alu0100951615_turing_machine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Daniel Rodríguez Suárez
 * Clase que sirve para leer del fichero e inicializar la máquina de Turing
 *
 */
public class ReaderFile {

	 static  Alfabeto alfabeto;
	 
	private ReaderFile() {
	}

	/**
	 * @param fichero Se le pasa el nombre del fichero
	 * @return la máquina de turing que computa la cadena
	 */
	public static MaquinaTuring Lectura(String fichero) {
		 String estadoInicial;
		 Alfabeto alfabetoTuring;
		 String blanco;
		 String finales;
		 String transicion;
		 boolean compfich;
		 boolean compfich2;

		MaquinaTuring newTM = new MaquinaTuring();

		try {
			Scanner in = new Scanner(new File(fichero));
			String[] estados;
			do {
				estados = in.nextLine().split(" ");
			} while (estados[0].charAt(0) == '#');	//se descartan los comentarios iniciales

			for (String st : estados) {	
				newTM.nuevoEstado(st);	//se añaden los estados
			}
			alfabeto = new Alfabeto(in.nextLine().split(" "), "Cadena");  //se añade el alfabeto a la clase por si hubiera que hacer comprobaciones
			alfabetoTuring = new Alfabeto(in.nextLine().split(" "), "Turing"); //lo mismo con el de turing
			estadoInicial = in.nextLine();  // se configura el estado inicial
			newTM.setEstadoInicial(estadoInicial);
			blanco = in.nextLine(); //simbolo blanco
			finales = in.nextLine(); //estados finales

			String[] Aceptados = finales.split(" ");
			for (int i = 0; i < Aceptados.length; i++) {
				newTM.setEstadoAceptacion(Aceptados[i]);
			}
			while (in.hasNextLine()) {
				String[] transiciones = in.nextLine().split(" "); //se añaden las transiciones
				for (int i = 0; i < estados.length; i++) {
					if (transiciones[0].equals(estados[i])) {
						newTM.addTransition(estados[i], transiciones[1].charAt(0), transiciones[2],
								transiciones[3].charAt(0), transiciones[4]);
					}
				}
			}
			in.close();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(-1);
		}

		return newTM;
	}

}
