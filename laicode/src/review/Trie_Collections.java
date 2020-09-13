package review;

import java.util.*;
import java.util.Map.Entry;

public class Trie_Collections {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	public static class TrieNode{
		HashMap<Character, TrieNode> children;
		boolean isEnd;
		int visited;
		
		public TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
			this.isEnd = false;
			this.visited = 0;
		}
	}
	
	/*
	 * insert a string into a Trie
	 * input: root, string
	 * output: void
	 */
	public static void insert(TrieNode node, String word) {
		
		if (word.isEmpty()) {
			node.isEnd = true;
			return;
		}
		char ch = word.charAt(0);
		if (!node.children.containsKey(ch)) {
			node.children.put(ch, new TrieNode());
		}
		node.visited ++;
		insert(node.children.get(ch), word.substring(1));
	}
	
	/*
	 * search a string in Trie
	 * input: root, string
	 * output: true/false
	 */
	public static boolean exist(TrieNode root, String word) {
		TrieNode node = search(root, word);
		return node != null && node.isEnd == true;
	}
	public static TrieNode search(TrieNode node, String word) {
		if (node == null) {
			return null;
		}
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!node.children.containsKey(ch)) {
				return null;
			}
			node = node.children.get(ch);
		}
		return node;
	}
	
	/*
	 * remove a string from Trie
	 * assume: the string is in Trie
	 * 
	 */
	public static void remove(TrieNode root, String word) {
		if (root == null) {
			return ;
		}
		TrieNode curNode = root;
		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!curNode.children.containsKey(ch)) {
				return;
			}
			TrieNode nextNode = curNode.children.get(ch);
			if (nextNode.visited == 1) {
				curNode.children.remove(ch);
			} else {
				nextNode.visited --;
				curNode = nextNode;
			}
		}
	}
	
	/*
	 * t1 find all strings staring with a certain prefix
	 * 1 find the node of the prefix,
	 * 2 form that node, find all pathes ended with a node marked as isEnd == true
	 */
	public static void test1() {
		TrieNode root = new TrieNode();
		String str1 = "abcde";
		String str2 = "abc";
		String str3 = "abdef";
		String str4 = "defg";
		String str5 = "ab";
		String prefix = "ab";
		
		insert(root, str1);
		insert(root, str2);
		insert(root, str3);
		insert(root, str4);
		insert(root, str5);
		List<String> result = t1_all_str_with_prefix(root, prefix);
		System.out.println(result);
	}
	
	public static List<String> t1_all_str_with_prefix(TrieNode root, String prefix) {
		TrieNode node = search(root, prefix);
		
		StringBuilder stb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		t1_get_all_strs_hlp(node, prefix, stb, result);
		
		return result;
	}
	
	public static void t1_get_all_strs_hlp(TrieNode node, String prefix, StringBuilder stb, 
			List<String> result) {
		if (node == null) {
			return ;
		}
		if (node.isEnd) {
			String str = prefix + stb.toString();
			result.add(str);
			// don't return here
			// we need to continue
		}
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			t1_get_all_strs_hlp(entry.getValue(), prefix, stb, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	/*
	 * 1.2 查询以某个前缀字符开头的所有字符串的个数
	 * 
	 * find the node of prefix and return the node.visited
	 */
	public static int t1_2_num_strs_prefix(TrieNode root, String prefix) {
		if (root == null) {
			return 0;
		}
		TrieNode node = search(root, prefix);
		if (node == null) {
			return 0;
		}
		return node.visited;
	}
	
	
	/*
	 * 1.3 查询前缀字符串开头的所有可能的下一个字母
	 * find all keys in the children 
	 */
	public static List<Character>  t1_3_all_chars_prefix(TrieNode root, String prefix) {
		List<Character> result = new ArrayList<Character>();
		if (root == null) {
			return result;
		}
		TrieNode node = search(root, prefix);
		if (node == null) {
			return result;
		}
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			result.add(entry.getKey());
		}
		return result;
	}
	
	/*
	 * 2 given a list of strings, find a list of prefix, we can uniquely identify every string in the list
	 * 给定一个list 的字符串，找出一个list of prefix，  从而可以uniquely identify 每个字符串
	 * visited == 1 || isEnd 的所有path
	 */
	public static void test2() {
		String[] strArr = {
				"abcd", "bce", "bcd", "cdef"
		};
		List<String> input = new ArrayList<String>();
		for(String str: strArr) {
			input.add(str);
		}
		List<String> result = t2_list_prefix_unique_identify_str(input);
		System.out.println(result);
	}
	
	public static List<String> t2_list_prefix_unique_identify_str(List<String> input) {
		TrieNode root = new TrieNode();
		// insert the input into Trie
		for(String s: input) {
			insert(root, s);
		}
		
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		t2_helper(root, result, stb);
		return result;
	}
	
	public static void t2_helper(TrieNode node, List<String> result, StringBuilder stb) {
		if (node == null) {
			return ;
		}
		if (node.visited == 1 || node.isEnd) {
			result.add(stb.toString());
			return ;
		}
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			t2_helper(entry.getValue(), result, stb);
			stb.deleteCharAt(stb.length() - 1);
		}
	}
	
	/*
	 * task3
	 * 很多urls, 然后输出最长的相同的prefix, 包含这个prefix url 必须占 75%
	 * 1 know urls.size()
	 * 2 know urls
	 * 
	 * List<String> urls
	 * 
	 * we need to find all the paths ending with visited >= 75% total # of urls, and pick the longest path
	 * as the result.
	 */
	
	
	public static String t3_longest_prefix(List<String> urls) {
		int urlsNum = urls.size();
		TrieNode root = new TrieNode();
		for(String s: urls) {
			insert(root, s);
		}
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		t3_helper(root, urlsNum, result, stb);
		String rev = "";
		for(String s : result) {
			if (s.length() > rev.length()) {
				rev = s;
			}
		}
		return rev;
	}
	public static void t3_helper(TrieNode node, int urlsNum, List<String> result, StringBuilder stb) {
		if (node == null) {
			return;
		}
		if (node.visited > urlsNum * 3/4) {
			result.add(stb.toString());
		}
		
		for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
			stb.append(entry.getKey());
			t3_helper(entry.getValue(), urlsNum, result, stb);
			stb.deleteCharAt(stb.length() - 1);
		}
		
	}
	
	
	
	
	
	


}
