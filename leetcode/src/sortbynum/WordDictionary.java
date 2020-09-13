package sortbynum;

import java.util.Map.Entry;

import ds.TrieNode;

public class WordDictionary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordDictionary wordDic = new WordDictionary();
		wordDic.addWord("word");
		wordDic.addWord("abcd");
		wordDic.addWord("bad");
		
		boolean rev = wordDic.search("..d");
		System.out.println("rev = "+ rev);
	}

	private TrieNode root; 
	
	public WordDictionary() {
		root = new TrieNode();
	}
	public void addWord(String word) {
		insert(root, word);
	}
	
	public void insert(TrieNode root, String word) {
		if (word.isEmpty()) {
			root.isEnd = true;
			return ;
		}
		char next = word.charAt(0);
		if (!root.children.containsKey(next)) {
			root.children.put(next, new TrieNode());
		}
		insert(root.children.get(next), word.substring(1));
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		return searchHelper(root, word);
	}
	public boolean searchHelper(TrieNode node, String word) {
		if (word.isEmpty()) {
			if (node == null) {
				return false;
			}
			if (node.isEnd == true) {
				return true;
			} else {
				return false;
			}
		}
		
		char next = word.charAt(0);
		if (next >= 'a' && next <= 'z') {
			// from 'a' to 'z'
			if (!node.children.containsKey(next)) {
				return false;
			} else {
				return searchHelper(node.children.get(next), word.substring(1));
			}
		} else { // next == '.'
			boolean rev = false;
			// traverse the children
			for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
				rev = rev || searchHelper(entry.getValue(), word.substring(1));
			}
			return rev;
		}
	}

}
