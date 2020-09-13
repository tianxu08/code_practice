package small_yan;

import java.util.*;

public class WordSearch3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = {
				{'d','o','a','f'},
				{'a','g','a','i'},
				{'d','c','a','n'}
		};
		String[] str = {
			"dog", "dad", "dgdg", "can"
		};
		ArrayList<String> words = new ArrayList<String>();
		for(String s: str) {
			words.add(s);
		}
		ArrayList<String> result = new WordSearch3().wordSearchIII(board, words);
		System.out.println(result);
	}
	
	class TrieNode {
		 String s;
		 boolean isString;
		 HashMap<Character, TrieNode> subtree;
		 public TrieNode() {
			// TODO Auto-generated constructor stub
			 isString = false;
			 subtree = new HashMap<Character, TrieNode>();
			 s = "";
		 }
	};


	class TrieTree{
		TrieNode root ;
		public TrieTree(TrieNode TrieNode) {
			root = TrieNode;
		}
		public void insert(String s) {
			TrieNode now = root;
			for (int i = 0; i < s.length(); i++) {
				if (!now.subtree.containsKey(s.charAt(i))) {
					now.subtree.put(s.charAt(i), new TrieNode());
				}
				// update now to previous_now.get(s.charAt(i))
				now  =  now.subtree.get(s.charAt(i));
			}
			now.s = s;
			now.isString  = true;
		}
		public boolean find(String s){
			TrieNode now = root;
			for (int i = 0; i < s.length(); i++) {
				if (!now.subtree.containsKey(s.charAt(i))) {
					return false;
				}
				now  =  now.subtree.get(s.charAt(i));
			}
			return now.isString ;
		}
	};

	
	public ArrayList<String> wordSearchIII(char[][] board, ArrayList<String> words) {
		ArrayList<String> ans = new ArrayList<String>();
		
		TrieTree tree = new TrieTree(new TrieNode());
		for(String word : words){
			tree.insert(word);
		}
		
		boolean[][] visited = new boolean[board.length][board[0].length];
		
		String res = ""; 
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				search(board,visited,i, j, tree.root, ans, res);
			}
		}
		return ans; 
    }

	public int []dx = {1, 0, -1, 0};
	public int []dy = {0, 1, 0, -1};

	public void search(char[][] board, boolean[][] visited ,int x, int y, TrieNode root, ArrayList<String> ans, String res) {
		if(root.isString == true)
		{
			if(!ans.contains(root.s)){
				ans.add(root.s);
			}
		}
		// out of bound or already visited or trie == null
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length ||visited[x][y]|| root == null)
			return ;
		
		if(root.subtree.containsKey(board[x][y])){
			for(int i = 0; i < 4; i++){
				char now = board[x][y];
				visited[x][y] = true;
				search(board,visited, x+dx[i], y+dy[i], root.subtree.get(now), ans, res);
				visited[x][y] = false;
			}
		}
	}
}
