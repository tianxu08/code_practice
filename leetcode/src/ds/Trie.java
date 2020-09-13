package ds;

public class Trie {
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	// insert a word into the trie
	public void insert(String word) {
		insertHelper(root, word);
	}
	
	public void insertHelper(TrieNode node, String word) {
		if (word.isEmpty()) {
			node.isEnd = true;
			return ;
		}
		char next = word.charAt(0);
		if (!node.children.containsKey(next)) {
			node.children.put(next, new TrieNode());
		}
		insertHelper(node.children.get(next), word.substring(1));
	}
	
	// return if the word is in the trie
	public boolean search(String word) {
		TrieNode end = searchHelper(root, word);
		if (end != null && end.isEnd == true) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public TrieNode searchHelper(TrieNode node, String word) {
		TrieNode curNode = node;
		for(int i = 0; i < word.length(); i ++) {
			char curCh = word.charAt(i);
			if (!curNode.children.containsKey(curCh)) {
				return null;
			}
			curNode = curNode.children.get(curCh);
		}
		
		System.out.println("cur.isEnd = " + curNode.isEnd);
		return curNode;
	}
	
	// return if there is any word in the tire that starts with the given prefix
	public boolean startsWith(String preFix) {
		TrieNode node = searchHelper(root, preFix);
		if (node != null) {
			return true;
		} 
		return false;
	}
	
}
