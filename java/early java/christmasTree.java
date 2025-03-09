String multiply(char a, int n) {                                                
	if (n != 0) {                                                                   
		return multiply(a, n-1) + a;                                                    
	} else {                                                                        
		return "";                                                                      
	}
}                                                                              
                                                                             
String restOfTheTree(char a, int size, int row) {
	if (size == row) {                            
		return "";
	}
	return multiply(' ', (size-row)/2) + multiply(a, row) + "\n" + restOfTheTree(a, size, row+1);
}
 
String green(String a) {
	return "\033[32m" + a + "\033[0m";
}
                  
void makeTree(int size) {                                                     
	System.out.println(green(restOfTheTree('*', size, 1)));                                             
}