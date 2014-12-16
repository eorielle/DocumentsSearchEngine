package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicDocument {
	private int id;
	private Map<String, Integer> words;
	
	public BasicDocument(int i, String s, ArrayList<String> breakWords){
		id = i;
		words = tokenisation(s, breakWords);
	}
	
		public Map<String,Integer> tokenisation(String s, ArrayList<String> breakWords){
			s=s.toLowerCase();
			
			ArrayList<String> w = new ArrayList<String>();
			int last_word = -1;
			
			for(int i=0; i<s.length(); i++){
				if(!Character.isLetterOrDigit(s.charAt(i)) || (i == s.length()-1 && last_word != i-1)){
					if(last_word != i-1 && !breakWords.contains(s.substring(last_word+1, i))){
						if( i==s.length()-1 ){
							w.add(s.substring(last_word+1, i+1));
						} else {
							w.add(s.substring(last_word+1, i));
						}
					}
					last_word = i;
				}
			}
		return wordCount(s, w);
	}
	
	public Map<String,Integer> wordCount(String s, ArrayList<String> wList){
		Map<String,Integer> w = new HashMap<String,Integer>();
		
		for(String word : wList){
			//System.out.println("searching : "+word);
			if(w.containsKey(word)){
				w.replace(word, w.get(word), w.get(word)+1);
			} else {
				w.put(word, 1);
			}
		}
		//for(String t : w.keySet()){
		//	System.out.println(t + ":" + w.get(t));
		//}
		return w;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Integer> getWords() {
		return words;
	}

	public void setWords(Map<String, Integer> words) {
		this.words = words;
	}

}
