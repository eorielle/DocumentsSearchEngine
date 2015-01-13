package reversedSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import basicSearch.BasicDocument;
import basicSearch.BasicIndex;

public class ReversedIndex {
	
	private Map<String, Map<Integer,Double>> index;
	
	public ReversedIndex(Map<String, Map<Integer, Double>> index) {
		this.index = index;
	}
	
	public ReversedIndex(ArrayList<BasicDocument> documents){
		toIndex(documents);
	}
	
	public ReversedIndex(BasicIndex documents){
		toIndex(documents);
	}

	private void toIndex(ArrayList<BasicDocument> documents) {
		index = new HashMap<String, Map<Integer,Double>>();
		for(BasicDocument document : documents){
			Map<String, Integer> temp_map = document.getWords();
			Set<String> key_set = temp_map.keySet();
			for (String key : key_set){
				if(!index.containsKey(key)){
					index.put(key, new HashMap<Integer,Double>());
				} 
				index.get(key).put(document.getId(), (double) temp_map.get(key));
			}
			 
		}
		
	}
	
	private void toIndex(BasicIndex documents){
		index = new HashMap<String, Map<Integer,Double>>();
		Set<Integer> temp_set = documents.getIndex().keySet();
		for(int i : temp_set){
			Map<String, Double[]> temp_map = documents.search(i);
			Set<String> key_set = temp_map.keySet();
			for (String key : key_set){
				if(!index.containsKey(key)){
					index.put(key, new HashMap<Integer,Double>());
				} 
				index.get(key).put(i, temp_map.get(key)[0]);
			}
		}
	}

	public Map<Integer, Double> search(String word){
		System.out.println(index.get(word).toString());
		return index.get(word);
		}
	
	
	public Map<String, Map<Integer, Double>> getIndex() {
		return index;
	}

	public void setIndex(Map<String, Map<Integer, Double>> index) {
		this.index = index;
	}

}
