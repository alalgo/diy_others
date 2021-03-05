package org.compiler.reengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class State {

	private int stateValue;
	private Set<State> epsilonTransition;
	private Map<Character,State> transition;
	private  State(int stateValue) {
		this.stateValue = stateValue;
		this.epsilonTransition = new HashSet<State>();
		this.transition = new HashMap<Character,State>();
	}
	
	public int getStateValue() {
		return stateValue;
	}

	public void setStateValue(int stateValue) {
		this.stateValue = stateValue;
	}

	public static State getBeginState() {
		return new State(-1);
	}

	public static State getCommonState() {
		return new State(0);
	}
	
	public static State getEndState() {
		return new State(1);
	}	
	
	public  boolean isBeginState() {
		return this.stateValue == 1;
	}
	
	public  boolean isEndState() {
		return this.stateValue == -1;
	}
	
	public  boolean isCommonState() {
		return this.stateValue == 0;
	}	
	
	public void addTransition(Character token,State state) {
		transition.put(token, state);
	}
	
	public void addEpsilonTransition(State state) {
		epsilonTransition.add(state);
	}		
	
	public List<State> getNextSymbolStates(char symbol) {
		List<State> nextStates = new ArrayList<State>();
		findNextSymbolStates(symbol,nextStates);
		return nextStates;
	}
	private void findNextSymbolStates(char symbol,List<State> nextStates) {
		if(this.epsilonTransition.size() > 0) {
			for(State state:this.epsilonTransition) {
				state.findNextSymbolStates(symbol, nextStates);
			}			
		}else {
			if(this.transition.get(symbol) != null) {
				nextStates.add(this.transition.get(symbol));
			}			
		}
	}

}
