package basicSearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import reversedSearch.ReversedIndex;

public class BasicIndex {
	
	private Map<Integer, Map<String, Double[]>> index;
	private int docNum = 0;
	private ReversedIndex rIndex;
	
	public BasicIndex(){
		index = new HashMap<Integer, Map<String, Double[]>>();
	}
	
	public BasicIndex(Map<Integer, Map<String, Double[]>> index) {
		this.index = index;
		
		//compute ponderations
		for (int id : index.keySet()){
			index.replace(id, computePond(index.get(id))) ;
			//index.put(id, computePond(index.get(id)));
		}
	}
	
	public void add(BasicDocument document){
		HashMap<String, Double[]> result = new HashMap<String, Double[]>();
		for(String word : document.getWords().keySet()){
			result.put(word, new Double[]{(double) document.getWords().get(word),0d,0d});
		}
		index.put(document.getId(), computePond(result));
	}

	public Map<String, Double[]> search(int i){
		//System.out.println(index.get(i));
		return index.get(i);
	}
	
	public Map<Integer, Double> cosSearch(Request req, int mode){
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();
		Set<String> temp_set = req.getWords().keySet();
		
		for(int i : index.keySet()){
			int size = index.get(i).size();
			HashMap<String, Double[]> temp_r= new HashMap<String, Double[]>();
			
			int temp = 0;
			for(String word : index.get(i).keySet()){
				
				if(req.getWords().containsKey(word)){
					temp_r.put(word, new Double[]{index.get(i).get(word)[mode],(double) req.getWords().get(word)});
					
				} else {
					temp_r.put(word, new Double[]{index.get(i).get(word)[mode],0d});
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
	
	private double computeCos(HashMap<String, Double[]> map) {
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

	public Map<Integer, Map<String, Double[]>> getIndex() {
		return index;
	}

	public void setIndex(Map<Integer, Map<String, Double[]>> index) {
		this.index = index;
	}
	
	public String print(HashMap m){
		String result = "";
		for(Object o : m.keySet()){
			result += o.toString() + " : " + m.get(o) + "\n";
		} 
		return result;
	}
	
	//compute ponderations for a document
	private Map<String, Double[]> computePond(Map<String, Double[]> map) {
		Map<String, Double[]> result = new HashMap<String, Double[]>();
		double maxFreq = 0;
		double maxTI = 0;
		for(String word : map.keySet()){
			//store normalized ponderations and Tf-IDF coefficients in the index
			double nCoeff = computeNormalCoef(word, map);
			double TICoeff = computeTICoef(word, map);
			if(nCoeff < maxFreq){
				maxFreq = nCoeff;
			}
			if(TICoeff < maxTI){
				maxTI = TICoeff;
			}
			result.put(word, new Double[]{map.get(word)[0], nCoeff, TICoeff});
		}
		
		//normalize ponderations
		for(Double[] v : result.values()){
			v[1] = v[1] / maxFreq;
			v[2] = v[2] / maxTI;
		}
		return result;
	}

	//compute normalized ponderation for a word in one document
	private double computeNormalCoef(String word, Map<String, Double[]> map) {
		return map.get(word)[0];
	}

	//compute TF-IDF ponderation for a documents
	private double computeTICoef(String word, Map<String, Double[]> map) {
		return Math.log(1 + map.get(word)[0]) * Math.log(docNum/rIndex.getIndex().get(word).size());
	}

	public void normalSearch(Request request) {
		// TODO Auto-generated method stub
		
	}

	public void TISearch(Request request) {
		// TODO Auto-generated method stub
		
	}

}
