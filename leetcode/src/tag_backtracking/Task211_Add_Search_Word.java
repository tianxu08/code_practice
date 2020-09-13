package tag_backtracking;

import java.util.Map.Entry;

import ds.TrieNode;

public class Task211_Add_Search_Word {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task211_Add_Search_Word sln = new Task211_Add_Search_Word();


	}

	TrieNode root;
	
	public Task211_Add_Search_Word() {
		root = new TrieNode();
	}
	
	// Adds a word into the data structure.
    public void addWord(String word) {
        insert(root, word);
    }
    
	private void insert(TrieNode node, String word) {
		if (word.isEmpty()) {
			node.isEnd = true;
			return ;
		}
		char ch = word.charAt(0);
		if (!node.children.containsKey(ch)) {
			node.children.put(ch, new TrieNode());
		}
		node.children.get(ch).visited ++;
		insert(node.children.get(ch), word.substring(1));
	}

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return searchHelper(root, word);
    }
    
    private boolean searchHelper(TrieNode node, String word) {
    	if (word.isEmpty()) {
    		if (node == null) {
				return false;
			}
			if (node.isEnd) {
				return true;
			} else {
				return false;
			}
		}
    	
    	char ch = word.charAt(0);
    	// ch is between 'a' to 'z'
    	if (ch >= 'a' && ch <= 'z') {
			if (!node.children.containsKey(ch)) {
				return false;
			} else {
				return searchHelper(node.children.get(ch), word.substring(1));
			}
		} else {
			// ch is '.'
			boolean rev = false;
			for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
				rev = rev || searchHelper(entry.getValue(), word.substring(1));
			}
			return rev;
		}
    }

}
