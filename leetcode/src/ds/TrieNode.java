package ds;

import java.util.HashMap;

public class TrieNode {
	public HashMap<Character, TrieNode> children;
	public boolean isEnd;
	public int visited;
	
	
	public TrieNode() {
		children = new HashMap<>();
		isEnd = false;
		visited = 0;
	}
}


