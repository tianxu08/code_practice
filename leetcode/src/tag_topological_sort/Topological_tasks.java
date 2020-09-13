package tag_topological_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

public class Topological_tasks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test207();
//		task210();
		test269();
	}
	/*
	 * task207 
	 * Course Schedule
	 * 
	 * n courses, labeled from 0 to n - 1
	 * some courses may have prerequisites, e.g, to take course 0, you have to take course 1 first, 
	 * which is expressed as a pair [0, 1]
	 * 
	 * Give the total number of courses and a list of prerequisite pairs, is it possible for you to finish 
	 * all courses ? 
	 * e.g
	 * 2 [1, 0]  first take 0, then take 1, so, it's possible
	 * 
	 */
	
	/*
	 * 
	 */
	
	public static void test207() {
		int[][] prerequisites = {{1,0},{0, 1}};
		int numCourses = 2;
		boolean rev = task207_can_finish(numCourses, prerequisites);
		System.out.println("rev = " + rev);
	}
	
	public static boolean task207_can_finish(int numCourses, int[][] prerequisites) {
		
		HashMap<Integer, Integer> in_degree = new HashMap<Integer, Integer>();
		// get all nodes which in_degree != 0 into in_degree hashMap
		for(int i = 0; i < prerequisites.length; i ++) {
			if (!in_degree.containsKey(prerequisites[i][0])) {
				in_degree.put(prerequisites[i][0], 1);
			} else {
				in_degree.put(prerequisites[i][0], in_degree.get(prerequisites[i][0]) + 1);
			}
		}
		
		// put all nodes which in_degree == 0 into Queue
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < numCourses; i ++) {
			if (!in_degree.containsKey(i)) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			Integer cur = queue.poll();
			// get all its neighbors
			ArrayList<Integer> neighbors = getNeighbors(prerequisites, cur);
			// decrease neighbors in_degree by 1
			for(Integer i : neighbors) {
				in_degree.put(i, in_degree.get(i) - 1);
				if (in_degree.get(i) == 0) {
					in_degree.remove(i);
					// add i into queue
					queue.offer(i);
				}
			}
		}
		
		if (!in_degree.isEmpty()) {
			return false; 
		} 
		return true;
	}
	
	public static ArrayList<Integer> getNeighbors(int[][] prerequisites, int node) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for(int i = 0; i < prerequisites.length; i ++) {
			if (prerequisites[i][1] == node) {
				neighbors.add(prerequisites[i][0]);
			}
		}
		return neighbors;
	}
	
	
	/*
	 * task210
	 * return the ordering of courses you should take to finish all courses.
	 * 
	 */
	
	public static void task210() {
		int numCourses = 2;
		int[][] prerequisites = { 
				{ 1, 0 } };
		int[] result = task210_findOrder(numCourses, prerequisites);
		System.out.println(Arrays.toString(result));

	}
	public static int[] task210_findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses == 0) {
			return null;
		}
		int[] result = new int[numCourses];
		HashMap<Integer, Integer> in_degree = new HashMap<Integer, Integer>();
		
		// put all nodes in_degree != 0 into hashMap
		for(int i = 0; i < prerequisites.length; i ++) {
			if (!in_degree.containsKey(prerequisites[i][0])) {
				in_degree.put(prerequisites[i][0], 1);
			} else {
				in_degree.put(prerequisites[i][0], in_degree.get(prerequisites[i][0]) + 1);
			}
		}
		
		// debug
		// print the content of hashMap
		for(Entry<Integer, Integer> entry: in_degree.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// put all nodes indegree == 0 into queue
		for(int i = 0; i < numCourses; i ++) {
			if (!in_degree.containsKey(i)) {
				queue.offer(i);
			}
		}
		
		int index = 0;
		// do BFS
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.println("cur = " + cur);
			result[index ++] = cur;
			ArrayList<Integer> neighbors = getNeighbors(prerequisites, cur);
			for(Integer nei : neighbors) {
				in_degree.put(nei, in_degree.get(nei) - 1);
				if (in_degree.get(nei) == 0) {
					// add nei into queue
					queue.offer(nei);
					// remove nei from in_degree
					in_degree.remove(nei);
				}
			}
		}
		
		if (!in_degree.isEmpty()) {
			// NOT a DAG, return null
			return new int[0];
		}
		return result;
    }
	
	/*
	 * task269
	 * 
	 * There is a new alien language which uses the latin alphabet. 
	 * However, the order among letters are unknown to you. 
	 * You receive a list of words from the dictionary, 
	 * where words are sorted lexicographically by the rules of this new language. 
	 * Derive the order of letters in this language.
	 * For example,
	 * Given the following words in dictionary,
	 * [
	 * "wrt",
	 * "wrf",
	 * "er",
	 * "ett",
	 * "rftt"
	 * ]
	 * The correct order is: "wertf".
	 */
	
	public static String task269_alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return null;
		}
		Map<Character, Set<Character>> graph = buildGraph(words);
		
		// debug
		for(Entry<Character, Set<Character>> entry: graph.entrySet()) {
			System.out.print(entry.getKey() + " :  ");
			for(Character ch: entry.getValue()) {
				System.out.print(ch + " ");
			}
			System.out.println();
		}
		
		System.out.println("----------------------------------------");
		
		
		Map<Character, Integer> in_degree = computeIndegree(graph);
		
		for(Entry<Character, Integer> entry: in_degree.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("=========================================");
		StringBuilder result = new StringBuilder();
		
		LinkedList<Character> queue = new LinkedList<Character>();
		
		for(Character ch: in_degree.keySet()) {
			if (in_degree.get(ch) == 0) {
				queue.offer(ch);
			}
		}
		System.out.println("q.size = " + queue.size());
		while(!queue.isEmpty()) {
			Character curCh = queue.poll();
			result.append(curCh);
			Set<Character> neighbors = graph.get(curCh);
			for(Character nei: neighbors) {
				in_degree.put(nei, in_degree.get(nei) - 1);
				if (in_degree.get(nei) == 0) {
					queue.offer(nei);
				} 
			}
		}
		System.out.println("result_len = " + result.length());
		System.out.println("in_degree.size  = " + in_degree.size());
		if (result.length() == in_degree.size()) {
			return result.toString();
		} else {
			return "";
		}
	}
	
	public static Map<Character, Set<Character>> buildGraph(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
		int N = words.length;
		for(int i = 1; i < N; i ++) {
			String word1 = words[i - 1];
			String word2 = words[i];
			int len1 = word1.length();
			int len2 = word2.length();
			int maxLen = Math.max(len1, len2);
			boolean found = false;
			for(int j = 0; j < maxLen; j ++) {
				Character c1 = j < len1 ? word1.charAt(j) : ' ';
				Character c2 = j < len2 ? word2.charAt(j) : ' ';
				if (c1 != ' ' && !map.containsKey(c1)) {
					map.put(c1, new HashSet<Character>());
				}
				if (c2 != ' ' && !map.containsKey(c2)) {
					map.put(c2, new HashSet<Character>());
				}
				if (c1 != ' ' && c2 != ' ' && c1 != c2 && found == false) {
					map.get(c1).add(c2);
					found = true;
				}
			}
		}
		return map;
	}
	
	public static Map<Character, Integer> computeIndegree(Map<Character, Set<Character>> graph) {
		Map<Character, Integer> in_degree = new HashMap<Character, Integer>();
		for(Character prev : graph.keySet()) {
			// if graph doesn't contains prev, put (prev, 0) into in_degree
			if (!in_degree.containsKey(prev)) {
				in_degree.put(prev, 0);
			}
			// put/update all succ's indegree
			for(Character succ: graph.get(prev)) {
				if (!in_degree.containsKey(succ)) {
					in_degree.put(succ, 1);
				} else {
					in_degree.put(succ, in_degree.get(succ) + 1);
				}
			}
		}
		return in_degree;
	}
	
	
	
	public static void test269() {
		String[] words = {
				  "wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"
		};
		String result = task269_alienOrder(words);
		System.out.println(result);
	}
	
	
	
	/*
	 * task44
	 */
	public static boolean task444_sequence_restruction(int[] org, List<List<Integer>> seqs) {
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();
		
		// create the graph from the seqs and fill in the indegree map
		for(List<Integer> list: seqs) {
			if (list.size() == 1) {
				// only have one element
				if (!graph.containsKey(list.get(0))) {
					graph.put(list.get(0), new HashSet<Integer>());
				}
			} else {
				// traverse the list
				for(int i = 0; i < list.size() - 1; i ++) {
					int curElem = list.get(i);
					int nextElem = list.get(i + 1);
					if (!graph.containsKey(curElem)) {
						graph.put(curElem, new HashSet<Integer>());
					}
					graph.get(curElem).add(nextElem);
					
					// also update the indegree map
					if (!indegree.containsKey(nextElem)) {
						indegree.put(nextElem, 0);
					}
					indegree.put(nextElem, indegree.get(nextElem) + 1);
				}
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		// add all elements with indegree == 0 into q
		// actually there is only 1 element
		for(Integer i : org) {
			if (!indegree.containsKey(i)) {
				q.offer(i);
			}
		}
		
		int index = 0;
		// do BFS
		while(!q.isEmpty()) {
			int size = q.size();
			if (size > 1) {
				return false;
			}
			int cur = q.poll();
			
			if (index == org.length || cur != org[index]) {
				return false;
			}
			// update index
			index ++;
			
			for(Integer nei: graph.get(cur)) {
				indegree.put(nei, indegree.get(nei) - 1);
				if (indegree.get(nei) == 0) {
					q.offer(nei);
				}
			}
		}
		return index == org.length && index == graph.size();
	}
	
	
	
	

}
