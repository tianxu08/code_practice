package tag_backtracking;
import java.util.*;


import ds.TrieNode;

public class Task212_WordSearch2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { 
				{ 'o', 'a', 'a', 'n' }, 
				{ 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' }, 
				{ 'i', 'f', 'l', 'v' } 
		};
		String[] words = {"oath","pea","eat","rain"};
		Task212_WordSearch2 sln = new Task212_WordSearch2();
		List<String> result = sln.findWords(board, words);
		System.out.println(result);
	}

	public List<String> findWords(char[][] board, String[] words) {
		TrieNode root = new TrieNode();
		for(String word : words) {
			insert(root, word);
		}
		int rLen = board.length;
		int cLen = board[0].length;

		StringBuilder stb = new StringBuilder();
		boolean[][] visited = new boolean[rLen][cLen];
		Set<String> revSet = new HashSet<>();
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				helper(board, root, visited, stb, i, j, revSet);
			}
		}
		
		List<String> result = new ArrayList<>();
		result.addAll(revSet);
		return result;
	}
	
	public void helper(char[][] board, TrieNode node, boolean[][] visited, StringBuilder stb, int x, int y, Set<String> set ) {
		// check
		int rLen = board.length;
		int cLen = board[0].length;
		if (x < 0 || x >= rLen || y < 0 || y >= cLen || visited[x][y] == true) {
			return ;
		}
		char nextChar = board[x][y];
		if (node.children.containsKey(nextChar)) {
			// get the nextNode
			TrieNode nextNode = node.children.get(nextChar);
			// set the visited[x][y] is true and append nextChar to stb
			visited[x][y] = true;
			stb.append(nextChar);
			
			if (nextNode.isEnd) {
				set.add(stb.toString());
			}
			
			for(int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				helper(board, nextNode, visited, stb, nextX, nextY, set);
			}
			
			// backtracking
			visited[x][y] = false;
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	public   int[] dx = { 0, 0, -1, 1 };
	public   int[] dy = { -1, 1, 0, 0 };
	
	public void insert(TrieNode node, String word) {
		if (word.isEmpty()) {
			node.isEnd = true;
			return ;
		}
		char nextChar = word.charAt(0);
		// if the children hashMap doesn't contains nextChar
		// create a new TrieNode and put <nextChar, new TrieNode()> into hashMap
		if (!node.children.containsKey(nextChar)) {
			node.children.put(nextChar, new TrieNode());
		}
		// update the visited
		node.children.get(nextChar).visited ++;

		insert(node.children.get(nextChar), word.substring(1));
	}
	
	

}
