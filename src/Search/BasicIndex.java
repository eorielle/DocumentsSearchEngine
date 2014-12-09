package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicIndex {
	
	private Map<Integer, Map<String, Integer>> index;
	
	public BasicIndex(){
		index = new HashMap<Integer, Map<String, Integer>>();
	}
	
	public BasicIndex(Map<Integer, Map<String, Integer>> index) {
		this.index = index;
	}
	
	public BasicIndex(ArrayList<BasicDocument> list){
		
	}
	
	public void add(BasicDocument document){
		index.put(document.getId(), document.getWords());
	}

	public Map<String, Integer> search(int i){
		System.out.println(index.get(i));
		return index.get(i);
	}

	public Map<Integer, Map<String, Integer>> getIndex() {
		return index;
	}

	public void setIndex(Map<Integer, Map<String, Integer>> index) {
		this.index = index;
	}

}
