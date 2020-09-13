package small_yan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;




public class Class14_Trie_Suffix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test();
//		test3();
//		test5();
//		test6();
		test8();
	}
	public static class TrieNode{
		Map<Character, TrieNode> children;
		boolean isEnd;
		// other information
		int visited;
		public TrieNode() {
			children = new HashMap<Character, TrieNode>();
			isEnd = false;
			visited = 0;
		}
	}
	
	// insert a string into the Trie
	public static void insert(TrieNode root, String str) {
		if (str.isEmpty()) {
			root.isEnd = true;
			return;
		}
		char next = str.charAt(0);
		if (!root.children.containsKey(next)) {
			root.children.put(next, new TrieNode());
		}
		insert(root.children.get(next), str.substring(1));
	}
	
	// insert a string into the Trie
	public static void insert2(TrieNode root, String str) {
		if (str.isEmpty()) {
			root.isEnd = true;
			return ;
		}
		char next = str.charAt(0);
		if (!root.children.containsKey(next)) {
			root.children.put(next, new TrieNode());
		} 
		root.children.get(next).visited ++;
		insert2(root.children.get(next), str.substring(1));
	}
	
	public static void insert3(TrieNode root, String str) {
		TrieNode node = root;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!node.children.containsKey(ch)) {
				node.children.put(ch, new TrieNode());
			}
			node.children.get(ch).visited ++;
			node = node.children.get(ch);
		}
		node.isEnd = true;
	}
	
	// search the str in Trie. return 
	public static TrieNode search(TrieNode root, String str) {
		TrieNode curNode = root;
		for(int i = 0; i < str.length(); i ++) {
			char cur = str.charAt(i);
			if (!curNode.children.containsKey(cur)) {
				return null;
			}
			curNode = curNode.children.get(cur);
		}
		return curNode;
	}
	
	
	public static void remove(TrieNode root, String str) {
		TrieNode curNode = root;
		for(int i = 0; i < str.length(); i ++) {
			char cur = str.charAt(i);
			if (!curNode.children.containsKey(cur)) {
				return ;
			}
			TrieNode next = curNode.children.get(cur);
			if (next.visited == 1) {
				curNode.children.remove(cur);
			} else {
				next.visited --;
				curNode = next;
			}
		}
	}
	
	public static void levelOrderTraversal(TrieNode root) {
		Queue<TrieNode> q = new LinkedList<Class14_Trie_Suffix.TrieNode>();
		q.add(root);
		int level = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			System.out.println("layer: " + level);
			for(int i = 0; i < size;i ++) {
				TrieNode cur = q.poll();
				System.out.println("isEnd = " + cur.isEnd);
				System.out.println("visited = " + cur.visited);
				System.out.println("children are: ");
				for(Map.Entry<Character, TrieNode> entry: cur.children.entrySet()) {
					System.out.print(entry.getKey() + "  ");
					q.offer((TrieNode) entry.getValue());
				}
			}
			System.out.println("\n ======next layer========");
			level ++;
		}
	}
	
	public static void test() {
		String s1 = "abe";
		String s2 = "ac";
		String s3 = "abd";
		String s4 = "ab";
		TrieNode root = new TrieNode();
		insert2(root, s1);
		insert2(root, s2);
		insert2(root, s3);
		insert2(root, s4);
		
//		String s5 = "ab";
//		levelOrderTraversal(root);
//		TrieNode node = search(root, s5);
//		System.out.println(node.isEnd);
//		System.out.println(node.visited);
//		for(Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
//			System.out.println(entry.getKey() + " ");
//		}
//		remove(root, s3);

		System.out.println("---------------------");
//		levelOrderTraversal(root);
		String prefix = "ab";
		System.out.println("task1: all str with prefix: ");
		ArrayList<String> result = task2_all_str_with_prefix(root, prefix);
		System.out.println(result);
//		System.out.println(task2_1_all_str_with_prefix(root, prefix));
		
//		task2_2_next_char_of_prefix(root, prefix);

	}
	
	
	/*
	 * Prefix related questions: 
	 * task2
	 * find all string starting with a certain prefix
	 * 查询以某字符串开头的所有字符串
	 * 1 find the node of the prefix. find path in Trie
	 * 2 from the node, find all paths ended with a node marked is isEnd==true (DFS)
	 */
	public static ArrayList<String> task2_all_str_with_prefix(TrieNode root, String prefix) {
		if (root == null) {
			return null;
		}
		if (prefix == null || prefix.length() == 0) {
			return null;
		}
		TrieNode nodeOfPrefix = search(root, prefix);
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		task2_helper(nodeOfPrefix, prefix, stb, result);
		
		return result;
	} 
	
	public static void task2_helper(TrieNode node, String prefix, StringBuilder stb, ArrayList<String> result) {
		if (node.isEnd) {
			// we find the solution
			String sln = prefix + stb.toString();
			result.add(sln);
			// don't add return here
		}
		for(Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			task2_helper(entry.getValue(), prefix, stb, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	/*
	 * task2.1 
	 * 查询以某个前缀字符串开头的所有的字符串个数
	 * we need the visited number in TrieNode
	 * when we find the node, return the node.visited.
	 */
	public static int task2_1_all_str_with_prefix(TrieNode root, String prefix) {
		if (root == null) {
			return 0;
		}
		if (prefix == null || prefix.length() == 0) {
			return 0;
		}
		TrieNode node = search(root, prefix);
		if (node == null) {
			return 0;
		}
		return node.visited;
	}
	
	/*
	 * task2.2
	 * 查询前缀字符串开头的所有可能的下一个字母
	 * 1 find all keys in the children map
	 */
	public static void task2_2_next_char_of_prefix(TrieNode root, String prefix) {
		if (root == null) {
			return ;
		}
		if (prefix == null || prefix.length() == 0) {
			return ;
		}
		TrieNode node = search(root, prefix);
		if (node == null) {
			return ;
		}
		for(Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
			System.out.println(entry.getKey());
		}
	} 
	
	
	/*
	 * task3
	 * given a list of strings, find a list of prefix, we can uniquely identify every strings in the list. 
	 * 给你一个list 的字符串，找出一个list of prefix，  从而可以uniquely identify 每个字符串
	 * if (visited == 1 || isEnd) //  所有的path 
	 */
	
	public static void test3() {
		String[] strArray = {
				"abcd", "bce", "bcd", "cdef"
		};
		List<String> input = new ArrayList<String>();
		for(String str: strArray) {
			input.add(str);
		}
		
		List<String> result = task3_list_prefix_unique_identify_every_str(input);
		System.out.println(result);
		
	}
	
	public static List<String> task3_list_prefix_unique_identify_every_str(List<String> input) {
		TrieNode root = new TrieNode();
		for(String str: input) {
			insert2(root, str);
		}
		
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		task3_helper(root, stb, result);
		return result;
	}
	
	
	public static void task3_helper(TrieNode node, StringBuilder stb, List<String> result) {
		if (node.isEnd || node.visited == 1) {
			// we find a prefix that can uniquely identify a node
			String prefix = stb.toString();
			result.add(prefix);
			return ;
		}
		
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			task3_helper(entry.getValue(), stb, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	
	/*
	 * task4
	 * 很多urls, 然后输出最长的相同的prefix, 包含这个prefix url 必须占 75%
	 * 1 know urls.size()
	 * 2 know urls
	 * 
	 * List<String> urls
	 * 
	 * we need to find all the paths ending with visited >= 75% total # of urls, and pick the longest path
	 * as the result.
	 */
	public static String task4_longestURL(List<String> urls) {
		int allURLnum = urls.size();
		List<String> result = new ArrayList<String>();
		TrieNode root = new TrieNode();
		for(String s: urls) {
			insert2(root, s);
		}
		StringBuilder stb = new StringBuilder();
		task4_helper(result, root, allURLnum, stb);
		
		int maxLen = 0;
		String rev = "";
		for(String s : result) {
			if (maxLen < s.length()) {
				rev = s;
			}
		}
		return rev;
	}
	
	public static void task4_helper(List<String> result, TrieNode node, int allURLnum, StringBuilder stb) {
		if (node.visited > allURLnum * 3/4) {
			String prefix = stb.toString();
			result.add(prefix);
			return ;
		}
		
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			task4_helper(result, entry.getValue(), allURLnum, stb);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	
	
	/*
	 * task5
	 * 给一个dictionary, 一个target string, 找出edit distance with the target string,
	 * 找出所有edit distance < = 1 的单词
	 *  
	 * check if the edit distance <= 1
	 *  
	 * method1
	 * naive
	 * editDistance(String s1, String s2) O(m*n)
	 * 
	 * for each of the strings in the dictionary, calculate the edit distance with the target string, check
	 * if the edit distance <= 1
	 * target string length: m
	 * average string length: n
	 * dictionary size: k
	 * O(m*n*k)
	 * 
	 * Method2 !!!
	 * oneEidtDistancd(String s1, String s2) O(m)
	 * O(m*k)
	 * string with same prefix, prefix has been traversed multiple times
	 */
	
	public static void test5() {
		String[] strarr = {
				"abce",
				"abef",
				"adgk",
				"abd",
				"abcd"
		};
		String target = "abc";
		ArrayList<String> dict = new ArrayList<String>();
		for (int i = 0; i < strarr.length; i++) {
			dict.add(strarr[i]);
		}
		ArrayList<String> result = task5_editDistance(dict, target);
		System.out.println(result);
	}
	
	public static ArrayList<String> task5_editDistance(ArrayList<String> dict, String target) {
		TrieNode root = new TrieNode();
		for(String str: dict) {
			insert2(root, str);
		}
		ArrayList<String> result = new ArrayList<String>();
		find(target, "", root, target.length(), result);
		return result;
	}
	
	public static void find(String target, String path, TrieNode root, int distance, ArrayList<String> result) {
		System.out.println("1: path = " + path);
		System.out.println("2: distance = " + distance);
		if (target.isEmpty()) {
			if (distance == 0) {
				if (root.isEnd) {
					// only when target is empty and distance == 0 and root is end node
					// the path can be added to result
					System.out.println("path = " + path);
					result.add(path);
				}
			} else {
				// distance == 1
				// and the target is empty, we can do inserting one character to path
				// and going to the next level with distance == 0
				for(Entry<Character, TrieNode> entry: root.children.entrySet()) {
					find(target, path + entry.getKey(), entry.getValue(), 0, result);
				}
			}
			return ;
		}
		// target is not empty, get the first character
		char cur = target.charAt(0);
		// if there exists a child node mapped by the first character, we can go there without changing the 
		// distance
		if (root.children.containsKey(cur)) {
			find(target.substring(1), path + cur, root.children.get(cur), distance , result);
		}
		
		if (distance == 1) {
			// remove first character from target
			find(target.substring(1), path, root, 0, result);
			for(Entry<Character, TrieNode> entry: root.children.entrySet()) {
				// replace first Character from target
				find(target.substring(1), path + entry.getKey(), entry.getValue(), 0, result);
				// insert one character in target
				find(target, path + entry.getKey(), entry.getValue(), 0, result);
			}
		}
	}
	
	
	/*
	 * task6
	 * !!!
	 * Given a dictionary and a regular expression, 
	 * find all the words in the dictionary matches the regular expression.
	 * 
	 * “?” ­ single character
	 * “*” ­ zero or more of any characters
	 */
	
	 
	
	
	
	/*
	 * task7
	 * Boggle Game
	 * Given a matrix of characters, you can move from one cell to neighbor cell(up, down, left, right), 
	 * each cell can be used only once. can you find all paths of any word in the dictionary?
	 * dictionary = {“AB”, “ABEE”, “ABCD”, “ADF”}
	 * 
	 * 
	 * A B ​C D 
	 * D ​E E​ G 
	 * F I ​H I
	 * J K L M
	 * Can we search for all the words at once instead of search for each of them one by one? Record the current visiting Trie node when doing the DFS.
	 */
	 
	 	public static void test6() {
		char[][] matrix = {
				"ABCD".toCharArray(),
				"DEEG".toCharArray(),
				"FIHI".toCharArray(),
				"JKLM".toCharArray()
		};
		String[] dict = {
				"AB","ABEE", "ABCD", "ADF", "EEHL", "GEHLKIEBC"
		};
		
		ArrayList<String> result = BoggleGame2(matrix, dict);
		ArrayList<String> result2 = wordSearchII(matrix, dict);
		System.out.println(result);
		System.out.println(result2);
	}
	
	public static ArrayList<String> BoggleGame2(char[][] matrix, String[] dict) {
		// create the Trie
		TrieNode root = new TrieNode();
		for(String s: dict) {
			insert2(root, s);
		}
		
//		levelOrderTraversal(root);
//		System.out.println("------------------------------------");
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		
		
		boolean[][] visited = new boolean[rLen][cLen];
	
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				BoggleGameHelper(matrix, visited, new StringBuilder(), root, i, j, result);
			}
		}
		
		System.out.println(result);
		return result;
	}
	
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1,1, 0,  0};
	
	public static void BoggleGameHelper(char[][] matrix, boolean[][] visited, StringBuilder stb, TrieNode node, 
			int rIndex, int cIndex, ArrayList<String> result) {
		//!!!! Not check here. will lead to wrong answer
//		System.out.println("stb.tostring = " + stb.toString());
//		if (node.isEnd == true) {
//			// we find a reasonable solution. 
//			String solution = stb.toString();
////			System.out.println("solution = " + solution);
//			if (!result.contains(solution)) {
//				result.add(solution);
//			}
//			return ;
//		}
		// check if rIndex or cIndex out of bound, or rIndex and cIndex has been visited or node in Trie is null
		if (rIndex < 0 || rIndex >= matrix.length || cIndex < 0 || cIndex >= matrix[0].length ||visited[rIndex][cIndex] || node == null) {
			return ;
		}
		
		// visited this node
		char nextChar = matrix[rIndex][cIndex];
		if (node.children.containsKey(nextChar)) {
			// we can go next
			TrieNode nextNode = node.children.get(nextChar);
			visited[rIndex][cIndex] = true; // set the visited[rowIndex][cIndex] = true;
			// append the nextChar to stb
			stb.append(nextChar);
			
			// we need to check whether the nextNode.isEnd == true, if Yes, here we find a solution, and add it into the result
			if (node.children.get(nextChar).isEnd) {
				if (!result.contains(stb.toString())) {
					result.add(stb.toString());
				}
			}
			// for debug
//			System.out.println("stb.tostring2 = " + stb.toString());
			
			for(int i = 0; i < 4; i ++) {
				int next_x = rIndex + dx[i];
				int next_y = cIndex + dy[i];
				BoggleGameHelper(matrix, visited, stb, nextNode, next_x, next_y, result);
			}
			
			visited[rIndex][cIndex] = false;
			stb.deleteCharAt(stb.length() - 1);
		}
		
	}
	
	
	
	public static ArrayList<String> wordSearchII(char[][] board,
			String[]  words) {
		// write your code here
		ArrayList<String> result = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return result;
		}

		TrieNode trie = new TrieNode();
		for (String s : words) {
			insert2(trie, s);
		}

		int rowLen = board.length;
		int colLen = board[0].length;
		boolean[][] visited = new boolean[rowLen][colLen];

		HashSet<String> set = new HashSet<String>();

		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				helper(board, visited, trie, i, j, new StringBuilder(), set);
			}
		}

		for (String s : set) {
			result.add(s);
		}
		return result;
	}

	public static void helper(char[][] board, boolean[][] visited,
			TrieNode trie, int rowIndex, int colIndex, StringBuilder stb,
			HashSet<String> set) {
		// !!! NOT check here
//		if (trie.isEnd) {
//			set.add(stb.toString());
//			return ;
//		}
		
		// out of bound
		if (trie == null || 
			rowIndex < 0 || rowIndex >= board.length || 
			colIndex < 0 || colIndex >= board[0].length) {
			return;
		}
		// 
		if (trie.children.get(board[rowIndex][colIndex]) == null || visited[rowIndex][colIndex]) {
			return;
		}

		TrieNode next = trie.children.get(board[rowIndex][colIndex]);
		
		stb.append(board[rowIndex][colIndex]);
		if (next.isEnd == true) {
			set.add(stb.toString());
		}
		
		visited[rowIndex][colIndex] = true;
		
		for(int i = 0; i < 4; i ++) {
			int next_x = rowIndex + dx[i];
			int next_y = colIndex + dy[i];
			helper(board, visited, next, next_x, next_y, stb, set);
		}
		
		visited[rowIndex][colIndex] = false;
		stb.deleteCharAt(stb.length() - 1);
	}
	
	
	
	
	/*
	 * task8
	 * 4. a set of integer binary representations, S = 
	 * S = “10000” “100” “1101”
	 * T = “1011”
	 * given another integer binary representation T, 
	 * what is the largest value that can be made by T XOR any of S?
	 * 01011 ^ 10000 = 11011
	 * 01011 ^ 10100 = 11111
	 * 01011 ^ 11101 = 10110
	 * 
	 * 1. 把所有的string补成长度相同 
	 * 2. S → Trie
	 * 3. search path, from root (Do DFS)
	 * 
	 */
	
	public static void test8() {
		String[] dict = {
				"10000",
				"100",
				"1101"
		};
		String T = "1011";
		System.out.println(task8_largest_value(dict, T));
		System.out.println(Integer.toBinaryString(globalMax));
		
		
	}
	
	public static int task8_largest_value(String[] dict, String T) {
		//int maxLen = Integer.MIN_VALUE;
		int maxLen = T.length(); // change maxLength to T.length();
		// after the following loop, maxLen will return the maxLength for dict's strings and T
		for(int i = 0; i < dict.length; i ++) {
			maxLen = Math.max(maxLen, dict[i].length());
		}
		
		// for strings in dict
		ArrayList<String> dict2 = new ArrayList<String>();
		for(int i = 0; i < dict.length; i ++) {
			int diff = maxLen - dict[i].length();
			StringBuilder sb = new StringBuilder();
			for(int k = 0; k < diff; k ++) {
				sb.append('0');
			}
			sb.append(dict[i]);
			dict2.add(sb.toString());
		}
		System.out.println(dict2);
		
		// for T
		int diff = maxLen - T.length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < diff;i ++) {
			sb.append('0');
		}
		sb.append(T);
		String newT = sb.toString();
		
		// create Trie
		TrieNode root = new TrieNode();
		// insert the str in dict2 into Trie
		for(String str: dict2) {
			insert2(root, str);
		}
//		levelOrderTraversal(root);
	
		// traverse the Trie
		task8_helper(root, newT, new StringBuilder());
		System.out.println(globalMax);
		return globalMax;
	}
	public static int globalMax = Integer.MIN_VALUE;
	public static void task8_helper(TrieNode root, String T, StringBuilder stb) {
		if (root.isEnd) {
			// calculate the XOR of T and STB
			String S = stb.toString();
			// convert the binary string to decimal  
			Integer SVal = Integer.parseInt(S,2);
			Integer TVal = Integer.parseInt(T,2);
			
			Integer curVal = SVal ^ TVal;
			
			globalMax = Math.max(globalMax, curVal);
			return ;
		}
		
		// do dfs
		for(Map.Entry<Character, TrieNode> entry: root.children.entrySet()) {
			stb.append(entry.getKey());
			task8_helper(entry.getValue(), T, stb);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	
	
	/*	
	 * Google Search 的时候， auto complete 怎么弄？ 
	 * "amazon"
	 * 1 "a" - top 10 frequently visited pages with 'a' as prefix
	 * 2 "am"- top 10 frequently visited pages with "am" as prefix
	 * 3 "ama" - top 10 frequently visited page with "ama" as prefix
	 * 
	 * This is : Prefix matching
	 * 1. we can store the top 10 frequently visited pages with the current node as prefix
	 * 2. how can we caululate top 10? 
	 * 
	 *      	             root
	 *  	                 | 'a'
	 *                  	 A
	 *           /'a'  /'b'    \'c'   \'d'
	 *           A     B       C       D
	 *   B: store top 10 with prefix 'ab'
	 *   C: store top 10 with prefix 'ac'
	 *   ...
	 *   Z: store top 10 with prefix 'az'
	 *   
	 *  top 10 with prefix "a" = merge all top 10 of its children ---> merge k sorted list. 
	 *           
	 *
	 */
	
	
	// Suffix Tree
	// it is still a trie, but all the strings are the given string's suffix substrings.
	// construct a trie, the dictionary is all the suffix substrings. 
	// banana's substrings
	// banana
	// anana
	// nana
	// ana
	// na
	// a
	// ""
	// related problem
	// Anything related to "substring" of string, we can consider use suffix tree
	// why, 
	// because in the suffix tree, actually, any prefix is one of the substrings of the original string. 
	// we still want to solve problem with "prefix" related.
	// all the paths from root == all the substrings of the given string
	// e.g
	
	
	// 1 longest substring with repeated in a string, example banana, return ana (ana repeated twice)
	// this is the same problem as "in the trie, what is the longest prefix that shared by multiple strings"
	// use visited as other information.
	
	/*
	 * class TrieNode{
	 * 		Map<Character, TireNode> children;
	 * 		boolean isEnd;
	 * 		// other information
	 * 		int visited;
	 * }
	 */
	// 2 longest common substring with two different strings. DP
	// S, T
	// create suffix tree for S
	// for each suffix of T, search suffix in suffix Tree of S
	// S = "banana"
	// T = "nancy"
	// return "nan"
	
	/*
	 * Largest distance between two identical substring with length >= 2
	 * 
	 * e,g
	 * "b a n a n a n" 
	 *  0 1 2 3 4  5 6
	 *  
	 *  distancd is 5 - 1 = 4
	 *  
	 *  class TrieNode {
	 *  	Map<Character, TrieNode> children;
	 *  	boolean isEnd;
	 *  	// other information
	 *  	ArrayList<Integer> startIndices;
	 *  }
	 *  an -> [1]
	 *  an -> [1,3]
	 *  an -> [1,3,5]
	 */
	
	
}
