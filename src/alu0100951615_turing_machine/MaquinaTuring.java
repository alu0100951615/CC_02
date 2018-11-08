package alu0100951615_turing_machine;

import java.util.*;

/**
 * @author Daniel Rodríguez Suárez
 * 	Clase principal del programa que se encarga de computar la máquina de turing
 *
 */
public class MaquinaTuring {
	private Set<String> Estados; //Set donde se guardan los Estados
	private Set<Transicion> Transiciones;	//Set donde se guardan las transiciones
	private String EstadoInicial;		    //Estado inicial de la máquina
	private ArrayList<String> EstadosAceptados = new ArrayList<String>();	//ArrayList de Estados de Aceptación
	private String EstadoMuerte; //Estado de muerte por si se hubiera definido en la máquina
	private String Cinta;		//String donde se guarda la cadena introducida
	private String EstadoActual;	//Estado actual donde se mueve la máquina
	private int SimboloActual;	     //Símbolo actual donde se encuentra la máquina en el String de la cinta

	/**
	 * Constructor por defecto, vacío
	 */
	public MaquinaTuring() {
		Estados = new HashSet<String>();
		Transiciones = new HashSet<Transicion>();
		EstadoInicial = new String("");
		EstadoMuerte = new String("");
		Cinta = new String("");
		EstadoActual = new String("");
		SimboloActual = 0;

	}

	/**
	 * @param input Cadena de entrada a analizar por la Maquina de Turing
	 * @param traza Booleano para ver si se muestra la traza o no
	 * @return
	 */
	public boolean Run(String input, boolean traza) {
		EstadoActual = EstadoInicial;
		Cinta = "..................";	//simbolos vacios a la izquierda
		Cinta = Cinta.concat(input);

		if (input.equals("."))
			SimboloActual = 0;
		else {

			while (Cinta.charAt(SimboloActual) == '.') {
				SimboloActual++;							//Para que llegue a donde está la cadena a analizar
			}
		}

		while (!EstadoActual.equals(EstadoMuerte)) {	//simplemente para que no acabe el bucle while
			boolean TransEncontrada = false;
			Transicion TransicionActual = null;

			if (traza == false) {	//impresión de la traza o no.
				if (SimboloActual > 0) {
					System.out.println(Cinta.substring(0, SimboloActual) +  "|^|" + Cinta.substring(SimboloActual) + ".................." + " Estado Actual " + EstadoActual);
				} else {
					System.out.println(" " + EstadoActual + " " + Cinta.substring(SimboloActual) + "........");
				}
			}

			Iterator<Transicion> TransitionsIterator = Transiciones.iterator();
			while (TransitionsIterator.hasNext() && TransEncontrada == false) { //Se busca la próxima transición a hacer
				Transicion nextTransition = TransitionsIterator.next();
				if (nextTransition.EstadoActual.equals(EstadoActual)	//Si el símbolo a leer y el estado coinciden se cambia la transición actual a la próxima
						&& nextTransition.LeerCadena == Cinta.charAt(SimboloActual)) {
					TransEncontrada = true;
					TransicionActual = nextTransition;
				}
			}

			if (TransEncontrada == false) {	//Si no hay más transiciones se finaliza el programa y se evalúa y se encontró la cadena o no
				System.out.println("\n" + "Última transición hecha:: Estado: " + EstadoActual+ " Simbolo " + Cinta.charAt(SimboloActual) + "\n");
				for (int i = 0; i < EstadosAceptados.size(); i++) {
					if (EstadoActual.equals(EstadosAceptados.get(i)))
						return true;
				}
				return false;
			} else {	//Si hay más transiciones
				EstadoActual = TransicionActual.EstadoSig;	//El estado actual es el siguiente
				char[] cintaTemp = Cinta.toCharArray();		//El simbolo de la cinta se sobreescribe
				cintaTemp[SimboloActual] = TransicionActual.WriteCad;
				Cinta = new String(cintaTemp);
				if (TransicionActual.Move.equals("R")) {	//Se incrementa o decrementa el contador del String segun nos movamos a derecha o izquierda
					SimboloActual++;
				} else if (TransicionActual.Move.equals("L")) {
					SimboloActual--;
				}

				if (SimboloActual < 0)	//Para que no se salga de rango.
					SimboloActual = 0;

				while (Cinta.length() <= SimboloActual) { //Va insertando . a la derecha
					Cinta = Cinta.concat(".");
				}

			}

		}
		return false;
	}

	/**
	 * @param estado Añadir estado
	 * @return true/false segun esté o no el estado
	 */
	public boolean nuevoEstado(String estado) {
		if (Estados.contains(estado)) {
			return false;
		} else {
			Estados.add(estado);
			return true;
		}
	}

	/**
	 * @param nuevoEstadoInicial
	 * @return true/false segun esté o no el estado
	 */
	public boolean setEstadoInicial(String estadoInicial) {
		if (Estados.contains(estadoInicial)) {
			EstadoInicial = estadoInicial;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param nuevoEstadoAceptación
	 * @return true/false según no sea de muerte o no
	 */
	public boolean setEstadoAceptacion(String estadoAceptacion) {
		if (!EstadoMuerte.equals(estadoAceptacion)) {
			EstadosAceptados.add(estadoAceptacion);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param nuevo EstadoMuerte
	 * @return true/false si el estado muerte esté ya incluido
	 */
	public boolean setEstadoMuerte(String estadoMuerte) {
		if (Estados.contains(estadoMuerte)) {
			EstadoMuerte = estadoMuerte;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo para añadir las transiciones a la máquina
	 * @param qActual Estado actual en el que estamos
	 * @param rSimbolo Simbolo a leer de la cinta
	 * @param qSig   Estado siguiente al que transitamos
	 * @param wSimbolo Simbolo a escribir en la cinta
	 * @param move Movimiento del cabezal
	 * @return
	 */
	public boolean addTransition(String qActual, char rSimbolo, String qSig, char wSimbolo, String move) {
		if (!Estados.contains(qActual) || !Estados.contains(qSig)) {	//error en en la introduccion de los estados
			System.err.println("Error, no hay estado en la transicion");
			return false;
		}

		boolean infinito = false;
		Iterator<Transicion> TransitionsIterator = Transiciones.iterator();
		while (TransitionsIterator.hasNext() && infinito == false) {		//Se comprueba que la próxima transición no sea la misma.
			Transicion nextTransition = TransitionsIterator.next();
			if (nextTransition.isConflicting(qActual, rSimbolo)) {
				System.err.println("Error, en la proxima transicion se lee lo mismo y se transita al mismo estado, comprueba el archivo");
				infinito = true;
			}

		}
		if (infinito == true) {
			return false;
		} else {
			Transicion newTransition = new Transicion();
			newTransition.setEstadoActual(qActual);
			newTransition.setLeerCadena(rSimbolo);
			newTransition.setEstadoSig(qSig);
			newTransition.setWriteCad(wSimbolo);
			newTransition.setMove(move);
			Transiciones.add(newTransition);
			return true;
		}
	}
}
