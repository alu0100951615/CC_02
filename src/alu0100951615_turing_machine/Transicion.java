package alu0100951615_turing_machine;

/**
 * @author Daniel Rodríguez Suárez
 * Clase que sirve para almacenar las transiciones de la máquina de turing con sus getters y setters.
 * Almacena el estado actual de la transicion
 * El simbolo a Leer de La cinta
 * El Estado siguiente al que transita
 * El simbolo que va a escribir en la cadena
 * Hacia donde se mueve el cabezal de la cinta (R,L,S)
 *
 */
class Transicion {
	String EstadoActual;
	char LeerCadena;
	String EstadoSig;
	char WriteCad;
	String Move;

	public String getEstadoActual() {
		return EstadoActual;
	}

	public void setEstadoActual(String estadoActual) {
		EstadoActual = estadoActual;
	}

	public char getLeerCadena() {
		return LeerCadena;
	}

	public void setLeerCadena(char leerCadena) {
		LeerCadena = leerCadena;
	}

	public String getEstadoSig() {
		return EstadoSig;
	}

	public void setEstadoSig(String estadoSig) {
		EstadoSig = estadoSig;
	}

	public char getWriteCad() {
		return WriteCad;
	}

	public void setWriteCad(char writeCad) {
		WriteCad = writeCad;
	}

	public String getMove() {
		return Move;
	}

	public void setMove(String move) {
		Move = move;
	}

	boolean isConflicting(String stado, char simbolo) {
		if (stado.equals(EstadoActual) && simbolo == LeerCadena) {
			return true;
		} else {
			return false;
		}
	}
}