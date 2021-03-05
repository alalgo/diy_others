package org.compiler.scanner;

import java.util.HashMap;

public class Token {
	private static HashMap tokenMap = new HashMap();
	static {
		tokenMap.put(1 , "auto"); 	
		tokenMap.put(2 , "short"); 	
		tokenMap.put(3 , "int"); 	
		tokenMap.put(4 , "long"); 	
		tokenMap.put(5 , "float"); 	
		tokenMap.put(6 , "double"); 	
		tokenMap.put(7 , "char"); 	
		tokenMap.put(8 , "struct"); 	
		tokenMap.put(9 , "union"); 	
		tokenMap.put(10, "enum"); 	
		tokenMap.put(11, "typedef"); 
		tokenMap.put(12, "const"); 	
		tokenMap.put(13, "unsigned");
		tokenMap.put(14, "signed"); 	
		tokenMap.put(15, "extern"); 	
		tokenMap.put(16, "register");
		tokenMap.put(17, "static"); 	
		tokenMap.put(18, "volatile");
		tokenMap.put(19, "void"); 	
		tokenMap.put(20, "if"); 		
		tokenMap.put(21, "else"); 	
		tokenMap.put(22, "switch"); 	
		tokenMap.put(23, "case"); 	
		tokenMap.put(24, "for"); 	
		tokenMap.put(25, "do"); 		
		tokenMap.put(26, "while"); 	
		tokenMap.put(27, "goto"); 	
		tokenMap.put(28, "continue");
		tokenMap.put(29, "break"); 	
		tokenMap.put(30, "default"); 
		tokenMap.put(31, "sizeof"); 	
		tokenMap.put(32, "return"); 
		tokenMap.put(32, "return"); 
	}
}
