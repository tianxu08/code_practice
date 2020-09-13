package sortbynum;
import java.util.*;
import ds.*;


public class Task425_WordSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task425_WordSquare sln = new Task425_WordSquare();
		String[] words = {"area","lead","wall","lady","ball"};
		
		
		List<List<String>> result = sln.wordSquares(words);
		System.out.println("print out the result: ");
		for(List<String> list: result) {
			for(String s : list) {
				System.out.println(s);
			} 
			System.out.println();
		}
	}

	public static List<List<String>> wordSquares(String[] words) {
		TrieNode root = new TrieNode();
		// add all words into the trie
		for(String s: words) {
			insert(root, s);
		}
		
		System.out.println("---------------------");
		int len = words[0].length();
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>();
		
		helper(words, result, list, root, len);
		return result;
	}
	
	public static void helper(String[] words, 
			List<List<String>> result, List<String> temp, TrieNode root, int len) {
		System.out.println("temp.size "  + temp.size()) ;
		if (temp.size() == len) {
			// we get a practical result
			result.add(new ArrayList<String>(temp));
			
			return ;
		}
		
		// the index of the char to explore
		int index = temp.size();
		
		// get the prefix
		StringBuilder prefix_stb = new StringBuilder();
		for(String s : temp) {
			prefix_stb.append(s.charAt(index));
		}
		System.out.println("index = " + index);
		System.out.println("cur str is : " + prefix_stb.toString());
		
		String prefix = prefix_stb.toString();
		
		List<String> startedWithPrefix = task2_all_str_with_prefix(root, prefix);
		
		System.out.println("----------");
		System.out.println(startedWithPrefix);
		System.out.println("----------");
		for(String next: startedWithPrefix) {
			temp.add(next);
//			System.out.println("AAA temp.size() = " + temp.size());
//			System.out.println("AAA temp content : ");
//			for(String ss : temp) {
//				System.out.println(ss);
//			}
//			System.out.println("BBB temp content");
			helper(words, result, temp, root, len);
			temp.remove(temp.size() - 1);
		}	
	}
	
	public static class TrieNode{
		HashMap<Character, TrieNode> children;
		int visited;
		boolean isEnd;
		public TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
			this.visited = 0;
			this.isEnd = false;
		}
	}
	
	public static void insert(TrieNode node, String str) {
		if (str.isEmpty()) {
			node.isEnd = true;
			return ;
		}
		char next = str.charAt(0);
		if (!node.children.containsKey(next)) {
			node.children.put(next, new TrieNode());
		}
		node.children.get(next).visited ++;
		insert(node.children.get(next), str.substring(1));
	}
	
	public static TrieNode search(TrieNode node, String str) {
		if (node == null) {
			return null;
		}
		TrieNode curNode = node;
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!curNode.children.containsKey(ch)) {
				return null;
			}
			curNode = curNode.children.get(ch);
		}
		return curNode;
	}
	
	public static void levelOrderTraversal(TrieNode root) {
		Queue<TrieNode> q = new LinkedList<TrieNode>();
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
	
	
	public static List<String> task2_all_str_with_prefix(TrieNode root, String prefix) {
		if (root == null) {
			return new ArrayList<String>();
		}
		
		TrieNode nodeOfPrefix = search(root, prefix);
		List<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		task2_helper(nodeOfPrefix, prefix, stb, result);
		
		return result;
	} 
	
	public static void task2_helper(TrieNode node, String prefix, StringBuilder stb, List<String> result) {
		if (node == null) {
			return ;
		}
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
	

}
