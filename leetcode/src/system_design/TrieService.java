package system_design;
import java.util.*;

public class TrieService {

	public class TrieNode{
		NavigableMap<Character, TrieNode> children;
		public List<Integer> top10;
		public boolean isEnd;
		public int visited;
		
		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
			top10 = new ArrayList<Integer>();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private TrieNode root = null;

	public TrieService() {
		root = new TrieNode();
	}

	public TrieNode getRoot() {
		// Return root of trie root, and
		// lintcode will print the tree struct.
		return root;
	}

	// @param word a string
	// @param frequency an integer
	public void insert(String word, int frequency) {
		// Write your cod here
		if (word == null || word.length() == 0) {
			return ;
		}
		TrieNode curRoot = root;
		for(int i = 0; i < word.length(); i ++) {
			Character next = word.charAt(i);
			if (!curRoot.children.containsKey(next)) {
				curRoot.children.put(next, new TrieNode());
				curRoot.children.get(next).top10 = new ArrayList<Integer>();
			}
			
			List<Integer> curtop10 = curRoot.children.get(next).top10;
			if (curtop10.size() > 10) {
				// the last element is the minimum
				int minIdx = curtop10.size() - 1;
				if (curtop10.get(minIdx) < frequency) {
					curtop10.set(minIdx, frequency);
				}
			} else {
				curtop10.add(frequency);
			}
			// sort the collection
			Collections.sort(curtop10, Collections.reverseOrder());
			
			curRoot = curRoot.children.get(next);
		}
	}
	
	
	


}
