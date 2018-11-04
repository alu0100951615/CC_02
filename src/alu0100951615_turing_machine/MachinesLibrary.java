package alu0100951615_turing_machine;

public final class MachinesLibrary 
{
	private MachinesLibrary() {}
	
	public static TuringMachine EqualBinaryWords()
	{
		TuringMachine newTM = new TuringMachine();		
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
		
		newTM.addState("q0");
		newTM.addState("q1");
		newTM.addState("q2");
		newTM.addState("q3");
		newTM.addState("q4");
		newTM.setStartState("q0");
		newTM.setAcceptState("q4");
		newTM.addTransition("q0", '.', "q4", '.', "S");
		newTM.addTransition("q0", '0', "q1", 'y', "R");
		newTM.addTransition("q1", '0', "q1", '0', "R");
		newTM.addTransition("q1", 'x', "q1", 'x', "R");
		newTM.addTransition("q1", '1', "q2", 'x', "L");
		newTM.addTransition("q2", '0', "q2", '0', "L");
		newTM.addTransition("q2", 'x', "q2", 'x', "L");
		newTM.addTransition("q2", 'y', "q0", '0', "R");		//0n1m  n<=m
		newTM.addTransition("q0", 'x', "q3", 'x', "R");
		newTM.addTransition("q0", '1', "q3", '1', "R");
		newTM.addTransition("q3", 'x', "q3", 'x', "R");
		newTM.addTransition("q3", '1', "q3", '1', "R");
		newTM.addTransition("q3", '.', "q4", '.', "S");
		
		
		
		return newTM;		
	}

}
