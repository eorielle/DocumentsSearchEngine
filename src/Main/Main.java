package Main;

import Search.Indexer;

public class Main {

	public static void main (String[] args){
	    System.out.println("Hello World");
	    Indexer indexer = new Indexer("C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\cacm.all", "", "C:\\Users\\Mew\\Cours\\Recherche d'Information Web\\cacm\\common_words");
	    indexer.execute();
	}
}
