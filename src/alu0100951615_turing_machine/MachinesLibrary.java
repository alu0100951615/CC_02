package alu0100951615_turing_machine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MachinesLibrary 
{	
	static String estadoInicial;	
	static Alfabeto alfabeto;	
    static Alfabeto alfabetoTuring;	
    static String blanco;
    static String finales;
    static String transicion;
    static boolean compfich;
    static boolean compfich2;
	private MachinesLibrary() {}
	
	public static TuringMachine EqualBinaryWords(String fichero)
	{		

		TuringMachine newTM = new TuringMachine();		
		
		try {
			Scanner in = new Scanner(new File(fichero));
			String[] estados;
			do {
				estados = in.nextLine().split(" ");
			}while(estados[0].charAt(0) == '#');
			
			for(String st : estados) {
				newTM.addState(st);
			}
			alfabeto = new Alfabeto(in.nextLine().split(" "), "Cadena");
			alfabetoTuring = new Alfabeto(in.nextLine().split(" "),"Turing");
			estadoInicial = in.nextLine(); newTM.setStartState(estadoInicial);
			blanco = in.nextLine();
			finales = in.nextLine();
			
			String[] Aceptados = finales.split(" ");
			for(int i = 0; i < Aceptados.length; i++) {
				newTM.setAcceptState(Aceptados[i]);
		}								
			while(in.hasNextLine()) {								
				String[] transiciones = in.nextLine().split(" ");
				for(int i = 0; i < estados.length; i++) {
					alfabeto.pertenece(transiciones[1]);			//Para comprobar si el alfabeto es el mismo que el de la máquina
					alfabeto.pertenece(transiciones[3]);
					if (transiciones[0].equals(estados[i])) {
						newTM.addTransition(estados[i], transiciones[1].charAt(0), transiciones[2],transiciones[3].charAt(0), transiciones[4]);
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
