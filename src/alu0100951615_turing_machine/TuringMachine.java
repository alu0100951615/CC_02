package alu0100951615_turing_machine; 

import java.util.*;



public class TuringMachine 
{
	private Set<String> StateSpace;
	private Set<Transicion> TransitionSpace;
	private String StartState;
	private ArrayList<String> AcceptStates = new ArrayList<String>();
	private String RejectState;	
	private String Tape;
	private String CurrentState;
	private int CurrentSymbol;
	
	public TuringMachine()
	{
		StateSpace = new HashSet<String>();
		TransitionSpace = new HashSet<Transicion>();
		StartState = new String("");
		RejectState = new String("");
		Tape = new String("");
		CurrentState = new String("");
		CurrentSymbol = 0;
		
	}
	
	public boolean Run(String input, boolean silentmode)
	{
		CurrentState = StartState;
		Tape = "..................";
		Tape = Tape.concat(input);
				
		
		if(input.equals("."))
			CurrentSymbol = 0;
		else {
		
		while(Tape.charAt(CurrentSymbol) == '.') {
			CurrentSymbol++;
		}
		}
		
		while(!CurrentState.equals(RejectState))
		{
			boolean foundTransition = false;
			Transicion CurrentTransition = null;
			
			if (silentmode == false)
			{
				if (CurrentSymbol>0)
				{
					System.out.println(Tape.substring(0, CurrentSymbol) + " " + CurrentState + " " + Tape.substring(CurrentSymbol) + ".................." );
				}
				else
				{
					System.out.println(" " + CurrentState + " " + Tape.substring(CurrentSymbol) + "........");
				}
			}
			
			
			
			

			Iterator<Transicion> TransitionsIterator = TransitionSpace.iterator();
			while ( TransitionsIterator.hasNext() && foundTransition == false)
		    {
				Transicion nextTransition = TransitionsIterator.next();
				if (nextTransition.readState.equals(CurrentState) && nextTransition.readSymbol == Tape.charAt(CurrentSymbol))
				{
					foundTransition = true;
					CurrentTransition = nextTransition;
				}						
		    }	
			
			if (foundTransition == false)
			{
				System.err.println ( "\n"+ "There is no valid transition for this phase! (state=" + CurrentState + ", symbol=" + Tape.charAt(CurrentSymbol) + ")");
				for(int i = 0; i < AcceptStates.size(); i++) {
				if (CurrentState.equals(AcceptStates.get(i)))
				{
					//System.out.println(Tape.substring(0, CurrentSymbol) + " " + CurrentState + " " + Tape.substring(CurrentSymbol) + ".................." );
					return true;
					
				}
				else
				{
					//System.out.println(Tape.substring(0, CurrentSymbol) + " " + CurrentState + " " + Tape.substring(CurrentSymbol) + ".................." );
					return false;
				}
			  }
			}
			else
			{
				CurrentState = CurrentTransition.writeState;
				char[] tempTape = Tape.toCharArray(); 				
				tempTape[CurrentSymbol] = CurrentTransition.writeSymbol;
				Tape =  new String(tempTape);
				if(CurrentTransition.moveDirection.equals("R"))
				{
					CurrentSymbol++;
				}
				else if(CurrentTransition.moveDirection.equals("L"))
				{
					CurrentSymbol--;
				}
				
				if (CurrentSymbol < 0)
					CurrentSymbol = 0;
				
				while (Tape.length() <= CurrentSymbol)
				{
					Tape = Tape.concat(".");
				}
				
				
			}			
		
			
		}	
		return false;
	}
	
	public boolean addState(String newState)
	{
		if (StateSpace.contains(newState))
		{
			return false;
		}
		else
		{
			StateSpace.add(newState);
			return true;
		}
	}
	
	public boolean setStartState(String newStartState)
	{
		if (StateSpace.contains(newStartState))
		{
			StartState = newStartState;
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	public boolean setAcceptState(String newAcceptState)
	{
		if (StateSpace.contains(newAcceptState) && !RejectState.equals(newAcceptState))
		{
			AcceptStates.add(newAcceptState);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean setRejectState(String newRejectState)
	{
		if (StateSpace.contains(newRejectState))
		{
			RejectState = newRejectState;
			return true;
		}
		else
		{
			return false;
		}		
	}

	public boolean addTransition(String rState, char rSymbol, String wState, char wSymbol, String mDirection)
	{
		if(!StateSpace.contains(rState) || !StateSpace.contains(wState))
		{
			return false;
		}
			
		boolean conflict = false;
		Iterator<Transicion> TransitionsIterator = TransitionSpace.iterator();
		while ( TransitionsIterator.hasNext() && conflict == false)
	    {
			Transicion nextTransition = TransitionsIterator.next();
			if (nextTransition.isConflicting(rState, rSymbol))
			{
				conflict = true;
			}
					
	    }
		if (conflict == true)
		{
			return false;
		}
		else
		{
			Transicion newTransition = new Transicion();
			newTransition.readState = rState;
			newTransition.readSymbol = rSymbol;
			newTransition.writeState = wState;
			newTransition.writeSymbol = wSymbol;
			newTransition.moveDirection = mDirection;
			TransitionSpace.add(newTransition);
			return true;
		}
	}
}
