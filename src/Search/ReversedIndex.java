package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReversedIndex {
	
	private Map<String, Map<Integer,Integer>> index;
	
	public ReversedIndex(Map<String, Map<Integer, Integer>> index) {
		this.index = index;
	}
	
	public ReversedIndex(ArrayList<BasicDocument> documents){
		toIndex(documents);
	}
	
	public ReversedIndex(BasicIndex documents){
		toIndex(documents);
	}

	private void toIndex(ArrayList<BasicDocument> documents) {
		index = new HashMap<String, Map<Integer,Integer>>();
		for(BasicDocument document : documents){
			Map<String, Integer> temp_map = document.getWords();
			Set<String> key_set = temp_map.keySet();
			for (String key : key_set){
				if(!index.containsKey(key)){
					index.put(key, new HashMap<Integer,Integer>());
				} 
				index.get(key).put(document.getId(), temp_map.get(key));
			}
			 
		}
		
	}
	
	private void toIndex(BasicIndex documents){
		index = new HashMap<String, Map<Integer,Integer>>();
		Set<Integer> temp_set = documents.getIndex().keySet();
		for(int i : temp_set){
			Map<String, Integer> temp_map = documents.search(i);
			Set<String> key_set = temp_map.keySet();
			for (String key : key_set){
				if(!index.containsKey(key)){
					index.put(key, new HashMap<Integer,Integer>());
				} 
				index.get(key).put(i, temp_map.get(key));
			}
		}
	}

	public Map<Integer,Integer> search(String word){
		System.out.println(index.get(word).toString());
		return index.get(word);
		}
	
/*	public Map<Integer,ArrayList<Integer>> cosSearch(Request req){
		Set<String> temp_set = req.getWords().keySet();
		int i = 0;
		for(String wordReq : temp_set){
			Set<Integer> key_set = search(wordReq).keySet();
			for(int docID : key_set){
				
				if(){//in result already
						
					
				} else {//add entry
					
				}
			}
				i++;
		}
		
		return null;
	}
		
		public float computeCosinus(Request req, BasicDocument doc){
			return 
		}*/
	
	public Map<String, Map<Integer, Integer>> getIndex() {
		return index;
	}

	public void setIndex(Map<String, Map<Integer, Integer>> index) {
		this.index = index;
	}

}
