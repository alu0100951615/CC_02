package alu0100951615_turing_machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Daniel Rodríguez Suárez
 * Main donde se piden las cadenas al usuario y se muestra el resultado
 *
 */
public class Main {

	public static void main(String[] args) {

		boolean hecho;
		boolean valida;
		String cadena;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		if (args.length == 0) {
			System.out.println("No hay fichero");
			return;
		}
		MaquinaTuring TM1 = ReaderFile.Lectura(args[0]);

		try {
			do {
				valida = true;
				System.out.println("Introduce la cadena: ");
				cadena = br.readLine();
				for (int i = 0; i < cadena.length(); i++)
					if (!ReaderFile.alfabeto.pertenece(cadena.charAt(i)) && !cadena.equals("."))
						valida = false;
			} while (!valida);
			if (TM1.Run(cadena, false))
				System.out.println("Cadena aceptada");
			else
				System.out.println("Cadena Rechazada");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
