package system_design;

import java.util.HashMap;
import java.util.Map;


public class Trie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie = new Trie();
		trie.insert("lintcode");
		boolean s1 = trie.search("lint");
		System.out.println("s1 = " + s1);
		boolean s2 = trie.startsWith("lint");
		System.out.println("s2 = " + s2);
		
		
	}
	
	public class TrieNode{
		// initialize your data structure here
		public Map<Character, TrieNode> children;
		public int visited;
		public boolean isEnd;
		
		public TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
			this.visited = 0;
			this.isEnd = false;
		}
	}
	
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return ;
		}
		TrieNode curRoot = root;
		for(int i = 0; i < word.length(); i ++) {
			Character next = word.charAt(i);
			if (!curRoot.children.containsKey(next)) {
				curRoot.children.put(next, new TrieNode());
			}
			curRoot = curRoot.children.get(next);
		}
		curRoot.isEnd = true;
	}

	public boolean search(String word) {
		if (word == null || word.length() == 0) {
			return true;
		}
		TrieNode curRoot = root;
		for(int i = 0; i < word.length(); i++) {
			Character next = word.charAt(i);
			if (!curRoot.children.containsKey(next)) {
				return false;
			} else {
				curRoot = curRoot.children.get(next);
			}
		}
		return curRoot.isEnd == true;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode curRoot = root;
		TrieNode revNode = startsHelper(curRoot, prefix);
		return revNode != null;
	}
	
	public TrieNode startsHelper(TrieNode node, String prefix) {
		if (prefix.length() == 0) {
			return node;
		}
		Character next = prefix.charAt(0);
		if (node.children.containsKey(next)) {
			return startsHelper(node.children.get(next), prefix.substring(1));
		} else {
			return null;
		}
	}
	
	
}
