package system_design;
import java.util.*;

public class InvertedIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
		// Write your code here
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for(Document doc: docs) {
			int id = doc.id;
			String[] words = doc.content.split("\\s+"); // split the string by "spaces"
			
			for(String word: words) {
				insert(map, word, id);
			}
		}
		return map;
		
	}
	public void insert(Map<String, List<Integer>> map, String temp, int id) {
		if (temp == null || temp.equals("")) {
			
		}
		if (!map.containsKey(temp)) {
			map.put(temp, new ArrayList<Integer>());
		}
		int n =  map.get(temp).size();
		if (n == 0 || map.get(temp).get(n - 1) != id) {
			map.get(temp).add(id);
		}
	}
	
	
	public class Document{
		public int id;
		public String content;
	}

}
