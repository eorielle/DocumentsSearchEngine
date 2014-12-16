package Search;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Indexer {
	private Path input, output, breakWords;
	private BufferedReader reader;
	private ArrayList<String> words;
	private BasicIndex documents;
	private String line = "";
	private ReversedIndex index;
	
	public Indexer(String input, String output, String breakWords){
		this.input= Paths.get(input);
		System.out.println(input);
		this.output=Paths.get(output);
		this.breakWords=Paths.get(breakWords);
		this.documents = new BasicIndex();
	}
	
	public void execute(){
		loadBreakwords();
		
		//Read document and analyse it
		try {
			
			reader = Files.newBufferedReader(input);
			line = reader.readLine();
			while(line != null && line.contains(".I")){
				BasicDocument document = new BasicDocument(Integer.parseInt(line.split(".I ")[1]), analyseDocument(), words);
				documents.add(document);
			} 
			writeResult("frequencies.txt",documents.getIndex().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		//create an inversed index
		try {
			index = new ReversedIndex(documents);
			writeResult("index.txt", index.toString());
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	 //index.search("program");
	 documents.cosSearch(new Request(0, "shallow lisp", words));
	 //documents.search(2000);
		
	}
	
	public void writeResult(String name, String content) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(name,"UTF-8");
		writer.write(content);
		writer.close();
		
	}
	
	public void loadBreakwords(){
		words = new ArrayList<String>();
		
		try {
			String line;
			reader = Files.newBufferedReader(breakWords);
			while((line = reader.readLine()) != null){
				words.add(line);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public String analyseDocument() throws IOException{
		
		String document = "";
		
		line = reader.readLine();
		
		while(line != null && !line.contains(".I")){
			if(line.equals(".T") || line.equals(".W")|| line.equals(".K")){					//Line .T
				
				line=reader.readLine();					//next line
				while(!line.startsWith(".")){		//analyse line and switch to next line
					document += line;
					line=reader.readLine();
				}
			} else {
				line=reader.readLine();
			}
			
		}
		
		return document;
		
	}
	
}



