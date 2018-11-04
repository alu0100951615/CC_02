package alu0100951615_turing_machine;

public class Main 
{

	public static void main(String[] args) 
	{
		TuringMachine TM1 = MachinesLibrary.EqualBinaryWords(args[0]);
		
		boolean done = TM1.Run("011", false);
		if (done==true)
		{
			System.out.println("Cadena aceptada");
		}
		else
		{
			System.out.println("Cadena rechazada");
		}
	}

}
