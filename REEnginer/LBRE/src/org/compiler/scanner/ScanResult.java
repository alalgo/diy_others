package org.compiler.scanner;

public class ScanResult {
	private int line;
	private String symbol;
	private int  id;
	public ScanResult(int line, String symbol,int id) {
		super();
		this.line = line;
		this.symbol = symbol;
		this.id = id;
	}
	
}
