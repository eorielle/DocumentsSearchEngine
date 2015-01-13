package Main;

import java.io.Console;
import java.util.Scanner;




public class Main {
	private static Indexer indexer;
	private static int mode = 1;

	public static void main (String[] args){
	    System.out.println("Hello World");
	    indexer = new Indexer("C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\cacm.all", "", "C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\common_words");
	    indexer.createIndex();
	    String cmd = "";
	    Console console = System.console();
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println(
	    		"********************\n"
	    		+ "Welcome to the intergalactical search engine ! \n"
	    		+ "Please choose the search mode :\n"
	    		+ "********************\n"
	    		+ "1 - Basic mode\n"
	    		+ "2 - Using inversed index\n"
	    		+ "3 - Using normalized ponderations\n"
	    		+ "4 - Using TF-IDF poderations\n\n" 
	    		+ "Enter selection:"
	    		);
	    
	    	cmd = scanner.nextLine();
	    	
	    	
	    while(!searchMode(cmd)){
	    	System.out.println(
		    		"Your selection is not valid ! \n"
		    		+ "1 - Basic mode\n"
		    		+ "2 - Using inversed index\n"
		    		+ "3 - Using normalized ponderations\n"
		    		+ "4 - Using TF-IDF poderations\n\n" 
		    		+ "Enter selection:"
		    		);
	    	cmd = scanner.nextLine();
	    }
	    
	    System.out.println("Enter request:");
	    cmd = scanner.nextLine();
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
