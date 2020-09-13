package small_yan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class WordSearch2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		char[][] board = { 
				{ 'd', 'o', 'a', 'f' }, 
				{ 'a', 'g', 'a', 'i' },
				{ 'd', 'c', 'a', 'n' } 
				};
		String[] str = { "dog", "dad", "dgdg", "can" };
		ArrayList<String> words = new ArrayList<String>();
		for (String s : str) {
			words.add(s);
		}
		ArrayList<String> result = wordSearchII(board, words);
		System.out.println(result);
	}

	public static class TrieNode {
		boolean isEnd;
		TrieNode[] children;

		public TrieNode() {
			this.isEnd = false;
			children = new TrieNode[26];
		}

		// add a string to the trie
		public void add(String s, int index) {
			if (index == s.length()) {
				isEnd = true;
				return;
			}
			if (children[s.charAt(index) - 'a'] == null) {
				children[s.charAt(index) - 'a'] = new TrieNode();
			}
			children[s.charAt(index) - 'a'].add(s, index + 1);
		}

		public void print() {
			System.out.println("===========start print=============");
			Queue<TrieNode> q = new LinkedList<TrieNode>();
			// first get the first layer
			for (int i = 0; i < 26; i++) {
				if (this.children[i] != null) {
					char thisc = (char) ('a' + i);
					q.add(this.children[i]);
					System.out.print(thisc + " ");
				}
			}
			System.out.println();
			System.out.println("----------");
			while (!q.isEmpty()) {
				int size = q.size();

				for (int k = 0; k < size; k++) {
					// visited this node
					TrieNode curnode = q.poll();
					System.out.print("isENd = " + curnode.isEnd + " ");
					for (int i = 0; i < 26; i++) {
						if (curnode.children[i] != null) {
							char ch = (char) ('a' + i);
							q.add(curnode.children[i]);
							System.out.print(ch + " ");
						}
					}
					System.out.println();
				}
				System.out.println();
				System.out.println("-------------");
			}
			System.out.println("===========end print=============");
		}
	}
	
	

	public static ArrayList<String> wordSearchII(char[][] board,
			ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		if (words == null || words.size() == 0) {
			return result;
		}

		TrieNode trie = new TrieNode();
		// add all words into trie
		for (String s : words) {
			trie.add(s, 0);
		}
		trie.print();

		int rLen = board.length;
		int cLen = board[0].length;

		boolean[][] visited = new boolean[rLen][cLen];

		HashSet<String> set = new HashSet<String>();

		String temp = "";
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				helper(board, visited, trie, i, j, temp, set);
			}
		}

		for (String s : set) {
			result.add(s);
		}
		return result;

	}

	public static void helper(char[][] board, boolean[][] visited,
			TrieNode trie, int rowIndex, int colIndex, String temp,
			HashSet<String> set) {
		if (trie.isEnd == true) {
			set.add(temp);
			return ;
		}
		if (trie == null || rowIndex < 0 || rowIndex >= board.length
				|| colIndex < 0 || colIndex >= board[0].length) {
			return;
		}
		if (trie.children[board[rowIndex][colIndex] - 'a'] == null
				|| visited[rowIndex][colIndex]) {
			return;
		}

		TrieNode next = trie.children[board[rowIndex][colIndex] - 'a'];
//		System.out.println("~~~~~~~~~~~");
//		next.print();

		String newTemp = temp + board[rowIndex][colIndex];
		
		visited[rowIndex][colIndex] = true;
		// up
		helper(board, visited, next, rowIndex - 1, colIndex, newTemp, set);
		// down
		helper(board, visited, next, rowIndex + 1, colIndex, newTemp, set);
		// left
		helper(board, visited, next, rowIndex, colIndex - 1, newTemp, set);
		// right
		helper(board, visited, next, rowIndex, colIndex + 1, newTemp, set);

		visited[rowIndex][colIndex] = false;

	}
}
