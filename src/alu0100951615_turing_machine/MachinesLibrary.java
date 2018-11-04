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
		
		
		
		
		
				
//		newTM.addState("q0");
//		newTM.addState("q1");
//		newTM.addState("q2");
//		newTM.setStartState("q0");
//		newTM.setAcceptState("q2");
//		newTM.addTransition("q0", '0', "q1", '0', "R");
//		newTM.addTransition("q0", '1', "q0", '1', "R");  //clase
//		newTM.addTransition("q1", '0', "q0", '0', "R");
//		newTM.addTransition("q1", '1', "q1", '1', "R");
//		newTM.addTransition("q1", '.', "q2", '.', "R");
		
//		newTM.addState("q0");
//		newTM.addState("q1");
//		newTM.addState("q2");
//		newTM.addState("q3");
//		newTM.addState("q4");
//		newTM.setStartState("q0");
//		newTM.setAcceptState("q4");
//		newTM.addTransition("q0", '.', "q4", '.', "S");
//		newTM.addTransition("q0", '0', "q1", 'y', "R");
//		newTM.addTransition("q1", '0', "q1", '0', "R");
//		newTM.addTransition("q1", 'x', "q1", 'x', "R");
//		newTM.addTransition("q1", '1', "q2", 'x', "L");
//		newTM.addTransition("q2", '0', "q2", '0', "L");
//		newTM.addTransition("q2", 'x', "q2", 'x', "L");
//		newTM.addTransition("q2", 'y', "q0", '0', "R");		//0n1m  n<=m
//		newTM.addTransition("q0", 'x', "q3", 'x', "R");
//		newTM.addTransition("q0", '1', "q3", '1', "R");
//		newTM.addTransition("q3", 'x', "q3", 'x', "R");
//		newTM.addTransition("q3", '1', "q3", '1', "R");
//		newTM.addTransition("q3", '.', "q4", '.', "S");
		
		
//		return newTM;		

	}

}
