package alu0100951615_turing_machine;

class Transicion
{
	String readState;
	char readSymbol;
	String writeState;
	char writeSymbol;
	String moveDirection;
	
	boolean isConflicting(String state, char symbol)
	{
		if (state.equals(readState) && symbol == readSymbol)
		{
			return true;
		}
		else
		{
			return false;			
		}
	}		
}