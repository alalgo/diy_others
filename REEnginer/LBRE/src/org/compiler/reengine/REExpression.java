package org.compiler.reengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class REExpression {
	private String pattern;
	private String cOPattern;
	private String postfixPattern;
	private NFA nfa;
	//private static List<Character> operators = Arrays.asList('*','|','(',')','·');
	private static Map<Character,Integer> operators = new HashMap<Character,Integer>();
	static {
		operators.put('(', 0);
		operators.put(')', 0);
		operators.put('*', 1);
		operators.put('|', 2);
		operators.put('·', 3);
	}

	public REExpression(String pattern) {
		if(!isValidPattern()) {
			throw new IllegalArgumentException("invalid regular expression.");
		}
		this.pattern = pattern;
		this.cOPattern = addConcatenateOperator();
		this.postfixPattern = infixToPostfix();
		this.nfa = postfixToNFA();
	}
	
	private boolean isValidPattern() {
		//to-do
		
		return true;
	}
	
	/**
	 * 判断字符是否为操作符
	 * @param c
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	private boolean isOperator(char c) {
		if(operators.containsKey(c))
			return true;
		return false;
	}
	
	/**
	 * 比较两操作符的级别高低，after 和befor 同级或小于时返回true，否则返回false（调度场算法将中缀表达式转后缀表达式用到）
	 * @param befor
	 * @param after
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	private boolean compareOperator(char befor,char after) {
		if(operators.get(after) >= operators.get(befor)) 
			return true;
		return false;
	}
	
	/**
	 * 增加'·'连接符
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	private String addConcatenateOperator() {
		StringBuilder resultStr = new StringBuilder();
		for(int i = 0; i < pattern.length()-1; i++) {
			char beforChar = pattern.charAt(i);
			char afterChar = pattern.charAt(i+1);
			resultStr.append(beforChar);
			if(
				(beforChar == '*' || beforChar == ')' || !isOperator(beforChar))
				&&
				((afterChar == '(' || !isOperator(afterChar)))
			){
				resultStr.append("·");
			}
		}
		resultStr.append(pattern.charAt(pattern.length()-1));
		return resultStr.toString();
	}
	
	/**
	 * 中缀表达式转后缀表达式（调度场算法）
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	private String infixToPostfix() {
		Stack<Character> operatorStack = new Stack<Character>();
		Queue<Character> queue = new LinkedList<Character>();
		
		for(int i = 0; i < cOPattern.length(); i++) {
			char c = cOPattern.charAt(i);
			if('(' == c) {
				operatorStack.push(c);
			}else if(')' == c) {
				for(int j = operatorStack.size() -1; j>=0; j--) {
					if(operatorStack.get(j) == '(') {
						operatorStack.remove(j);
						break;
					}
				}
				queue.add(operatorStack.pop());				
			}else if(isOperator(c)) {
				if(!operatorStack.isEmpty() && operatorStack.peek() != '(' && compareOperator(operatorStack.peek(),c)){
					queue.add(operatorStack.pop());
				}
				operatorStack.push(c);				
			}else {
				queue.add(c);
			}
		}
		while(!operatorStack.isEmpty()) {
			queue.add(operatorStack.pop());
		}
		StringBuilder strs = new StringBuilder();
		while(!queue.isEmpty()) {
			strs.append(queue.poll());
		}
		return strs.toString();
	}
	
	/**
	 * 后缀表达式转NFA
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	private NFA postfixToNFA() {
		Stack<NFA> stack = new Stack<NFA>();
		for(int i = 0; i < postfixPattern.length(); i++) {
			char c = postfixPattern.charAt(i);
			switch (c) {
				case '·': {
						NFA nfa1 = stack.pop();
						NFA nfa2 = stack.pop();
						State a1 = nfa1.getBeginState();
						State a2 = nfa1.getBeginState();						
						stack.push(NFA.concatenate(nfa2, nfa1));
					}
					break;
				case '|': {
						NFA nfa1 = stack.pop();
						NFA nfa2 = stack.pop();	
						State a1 = nfa1.getBeginState();
						State a2 = nfa2.getBeginState();						
						stack.push(NFA.union(nfa1, nfa2));
					}
					break;
				case '*': {
							stack.push(NFA.kleeneClosure(stack.pop()));
						}
					break;					
				default: {
							stack.push(NFA.createBasicNFA(c));
						}
					break;
			}
		}
		return stack.pop();
	}
	
	@Deprecated
	private DFA NFAToDFA(NFA nfa) {
		DFA dfa = new DFA();
		return dfa;
	}
	private String text;
	private int textLength;

	/**
	 * 根据NFA 判断录入字符串是否和设定的正则表达式匹配
	 * @param text
	 * @return 
	 * @date: 2019年8月23日
	 * @author: security
	 */
	public boolean match(String text) {
		this.text = text;
		this.textLength = text.length();
		return backTrackingSearch();
	}
	public boolean backTrackingSearch() {
		List<State> states = new ArrayList<State>();
		states.add(nfa.getBeginState());
		for(int i = 0; i < textLength; i++) {
			char c = this.text.charAt(i);
			List<State> statesTemp = new ArrayList<State>();
			if(states == null || states.size() == 0) {
				return false;
			}
			for(State state:states) {
				if(state.getNextSymbolStates(c) != null) {
					statesTemp.addAll(state.getNextSymbolStates(c));			
				}
			}
			states = statesTemp;
		}
		if(states.contains(nfa.getEndState())) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		REExpression re = new REExpression("(a|e)*a");
		System.out.println(re.cOPattern); //(a|b)·*·a·b
		System.out.println(re.postfixPattern);//ab|*.
		System.out.println(re.match("eeeeea"));
	}
}
