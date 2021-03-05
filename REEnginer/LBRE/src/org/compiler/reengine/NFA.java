package org.compiler.reengine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NFA {

	private  State beginState ;
	private  State endState ;
	public State getBeginState() {
		return beginState;
	}

	public State getEndState() {
		return endState;
	}
	
	private  NFA(State beginState,State endState) {
		this.beginState = beginState;
		this.endState = endState;
	}


	public static NFA createBasicNFA(Character c) {
		State begin = State.getBeginState();
		State end = State.getEndState();
		begin.addTransition(c, end);
		
		return new NFA(begin,end);
	}
	/**
	 *  '|'
	 * @param leftNFA
	 * @param rightNFA
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	public static NFA union(NFA leftNFA,NFA rightNFA) {
		State begin = State.getBeginState();
		State end = State.getEndState();	
		
		leftNFA.getBeginState().setStateValue(0);
		leftNFA.getEndState().setStateValue(0);
		State s1 = leftNFA.getBeginState();
		begin.addEpsilonTransition(s1);		
		leftNFA.getEndState().addEpsilonTransition(end);
		
		rightNFA.getBeginState().setStateValue(0);
		rightNFA.getEndState().setStateValue(0);
		State s2 = rightNFA.getBeginState();
		begin.addEpsilonTransition(s2);
		rightNFA.getEndState().addEpsilonTransition(end);
		
		return new NFA(begin,end);
	}
	
	/**
	 *  '·'
	 * @param leftNFA
	 * @param rightNFA
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	public static NFA concatenate(NFA leftNFA,NFA rightNFA) {
		State begin = leftNFA.getBeginState();
		leftNFA.getEndState().setStateValue(0);
		rightNFA.getBeginState().setStateValue(0);
		leftNFA.getEndState().addEpsilonTransition(rightNFA.getBeginState());
		return new NFA(begin,rightNFA.getEndState());
	}
	
	/**
	 *  '*'
	 * @param nfa
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	public static NFA kleeneClosure(NFA nfa) {
		State begin = State.getBeginState();
		State end = State.getEndState();
		
		nfa.getBeginState().setStateValue(0);
		nfa.getEndState().setStateValue(0);
		
		begin.addEpsilonTransition(nfa.getBeginState());
		begin.addEpsilonTransition(end);
		nfa.getEndState().addEpsilonTransition(nfa.getBeginState());
		nfa.getEndState().addEpsilonTransition(end);
		
		return new NFA(begin,end);
	}
	
}
