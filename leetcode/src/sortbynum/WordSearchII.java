package sortbynum;
import java.util.*;

import ds.TrieNode;
public class WordSearchII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		char[][] board = {
				{'o','a','a','n'},
				{'e','t','a','e'},
				{'i','h','k','r'},
				{'i','f','l','v'}
		};
		
		String[] words = {"oath","pea","eat","rain"};
		
		List<String> result = findWords(board, words);
		System.out.println(result);
		
	}
	
	public static void insert(TrieNode root, String word) {
		if (word.isEmpty()) {
			root.isEnd = true;
			return ;
		}
		char next = word.charAt(0);
		if (!root.children.containsKey(next)) {
			root.children.put(next, new TrieNode());
		}
		root.children.get(next).visited ++;
		insert(root.children.get(next), word.substring(1));
	}
	

	public static List<String> findWords(char[][] board, String[] words) {
		List<String> result = new ArrayList<String>();
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return result;
		}
		
		int rLen = board.length;
		int cLen = board[0].length;
		TrieNode root = new TrieNode();
	    for(String word: words) {
	    	insert(root, word);
	    }
	    
	    boolean[][] visited = new boolean[rLen][cLen];
	    Set<String> set = new HashSet<String>();
	    StringBuilder stb = new StringBuilder();
	    for(int i = 0; i < rLen; i ++) {
	    	for(int j = 0; j < cLen; j ++) {
	    		findWordsHelper(board, visited, stb, root, i, j, set);
	    	}
	    }
	    
	    result.addAll(set);
	    
		return result;
		
	}
		
	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };
	public static void findWordsHelper(char[][] board, boolean[][] visited, 
			StringBuilder stb, TrieNode node, int x, int y, Set<String> set) {
		int rLen = board.length;
		int cLen = board[0].length;
		if (x < 0 || x >= rLen || y < 0 || y >= cLen || visited[x][y]) {
			return ;
		}
		
		char nextChar = board[x][y];
		if (node.children.containsKey(nextChar)) {
			// we can go next
			TrieNode nextNode = node.children.get(nextChar);
			visited[x][y] = true;
			stb.append(nextChar);
			if (nextNode.isEnd) {
				if (!set.contains(stb.toString())) {
					set.add(stb.toString());
				}
			}
			
			for(int i = 0; i < 4; i ++) {
				int nextX = dx[i] + x;
				int nextY = dy[i] + y;
				findWordsHelper(board, visited, stb, nextNode, nextX, nextY, set);
			}
			
			visited[x][y] = false;
			stb.deleteCharAt(stb.length() - 1);
		}
	}
}
