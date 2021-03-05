package org.compiler.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Scanner {
	private char[] sourceCode;
	public Scanner(String filePath) throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(new File(filePath)));
		String lineStr;
		int lineNum = 1;
		while((lineStr = fr.readLine()) != null) {
			
			lineNum++;
		}
	}
	
	public void match() {
		for(int i =0; ; i++) {}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner("src/org/compiler/testfile/index.c");
		System.out.println(scanner.sourceCode);
	}
}
