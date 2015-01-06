package Main;

import java.io.Console;




public class Main {
	private static Indexer indexer;
	private static int mode = 1;

	public static void main (String[] args){
	    System.out.println("Hello World");
	    indexer = new Indexer("C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\cacm.all", "", "C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\common_words");
	    indexer.createIndex();
	    String cmd = "";
	    Console console = System.console();
	    
	    do{
	    	System.out.println(
		    		"Welcome to the intergalactical search engine, please choose the search mode :\n"
		    		+ "1 - Basic mode"
		    		+ "2 - Using inversed index"
		    		+ "3 - Using normalized ponderations"
		    		+ "4 - Using TF-IDF poderations"
		    		);
	    	cmd = console.readLine("Enter selection:");
	    } while(!searchMode(cmd));
	    
	    cmd = console.readLine("Enter request:");
	    search(cmd);
	    
	    cmd = console.readLine("***********************");
	}
	
	public static boolean searchMode(String cmd){
		int c = Integer.parseInt(cmd);
		if(c > 0 && c < 5){
			mode = c;
			return true;
		} else {
			return false;
		}
		
	}
	
	public static void search(String cmd){
		indexer.search(cmd, mode);
	}
	
}
