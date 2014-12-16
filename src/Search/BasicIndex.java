package Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		//System.out.println(index.get(i));
		return index.get(i);
	}
	
	public Map<Integer, Double> cosSearch(Request req){
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		Set<String> temp_set = req.getWords().keySet();
		
		for(int i : index.keySet()){
			int size = index.get(i).size();
			HashMap<String, Integer[]> temp_r= new HashMap<String, Integer[]>();
			
			int temp = 0;
			for(String word : index.get(i).keySet()){
				
				if(req.getWords().containsKey(word)){
					temp_r.put(word, new Integer[]{index.get(i).get(word),req.getWords().get(word)});
					
				} else {
					temp_r.put(word, new Integer[]{index.get(i).get(word),0});
				}
				temp ++;
			}
			
			double f = computeCos(temp_r);
			if( f != 0){
				result.put(i,f);
			}
		}
		System.out.println(print(result));
		return result;
			
	}
	
	private double computeCos(HashMap<String, Integer[]> map) {
		int n = 0;
		double scal = 0;
		double norm1 = 0;
		double norm2 = 0;
		
		for(String word : map.keySet()){
			scal = scal + map.get(word)[0] *  map.get(word)[1];
			norm1 = norm1 +  map.get(word)[1] *  map.get(word)[1];
			norm2 = norm2 +  map.get(word)[0] *  map.get(word)[0];
			n++;
		}
		
		double temp = (Math.sqrt(norm1) * Math.sqrt(norm2));
		
		if(temp >0){
			return scal/(temp);
		}
		else {
			return 0.0d;
		}
		
	}

	public Map<Integer, Map<String, Integer>> getIndex() {
		return index;
	}

	public void setIndex(Map<Integer, Map<String, Integer>> index) {
		this.index = index;
	}
	
	public String print(HashMap m){
		String result = "";
		for(Object o : m.keySet()){
			result += o.toString() + " : " + m.get(o) + "\n";
		} 
		return result;
	}

}
