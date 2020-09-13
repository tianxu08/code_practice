package sortbynum;

import ds.Debug;
import java.util.*;
import java.util.Map.Entry;

public class Task701_750 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test733();
//		test734();
		test743();
	}

	/**
	 * 733 Flood Fill
	 */

	public static void test733() {
		int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		int[][] rev = task733_floodFill(image, 1, 1, 2);

		Debug.printMatrix(rev);
	}

	public static int[][] task733_floodFill(int[][] image, int sr, int sc, int newColor) {
		task733_fill(image, sr, sc, image[sr][sc], newColor);
		return image;
	}

	public static void task733_fill(int[][] image, int sr, int sc, int originColor, int newColor) {
		int rLen = image.length;
		int cLen = image[0].length;
		if (sr < 0 || sr >= rLen || sc < 0 || sc >= cLen || originColor != image[sr][sc]) {
			return;
		}
		image[sr][sc] = newColor;
		for (int i = 0; i < 4; i++) {
			int nextX = dx[i] + sr;
			int nextY = dy[i] + sc;
			task733_fill(image, nextX, nextY, originColor, newColor);
		}
	}

	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	
	
	/**
	 * 734. Sentence Similarity
	 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

	 * For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

	 * Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

	 * However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

	 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

	 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

	 * Note:

	 * The length of words1 and words2 will not exceed 1000.
	 * The length of pairs will not exceed 2000.
	 * The length of each pairs[i] will be 2.
	 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
	 * 
	 * 
	 * @param words1
	 * @param words2
	 * @param pairs
	 * @return
	 * 
	 * 
	 */
	
	public static void test734() {
		String[] words1 = {"great","acting","skills"};
		String[] words2 = {"fine","painting","talent"};
		String[][] pairs = {
				{"great","fine"},
				{"drama","acting"},
				{"skills","talent"}
		};
		
		
		boolean rev = task734_areSentencesSimilar(words1, words2, pairs);
		System.out.println("===>>> rev: " + rev);
	}
	public static boolean task734_areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) {
			return false;
		}
		// use HashMap<String, Set<String>> to store string and its similarity
		Map<String, Set<String>> map = new HashMap<>();
		for (String[] sArr : pairs) {
			String s1 = sArr[0];
			String s2 = sArr[1];
			
			if (!map.containsKey(s1)) {
				map.put(s1, new HashSet<>());
			}
			
			if (!map.containsKey(s2)) {
				map.put(s2, new HashSet<>());
			}
			
			map.get(s1).add(s2);
			map.get(s2).add(s1);
		}
		
		for (int i = 0; i < words1.length; i++) {
			String s1 = words1[i];
			String s2 = words2[i];
		
			if (s1.equals(s2) || map.containsKey(s1) && map.get(s1).contains(s2)) {
				continue;
			} else {
				return false;
			}
				
		}
		return true;
	}
	
	/**
	 * task737
	 * Note that the similarity relation is transitive. For example, 
	 * if "great" and "good" are similar, and "fine" and "good" are similar,
	 *  then "great" and "fine" are similar.
	 * 
	 * Disjaska: 
	 * Time: O(N log N + E)
	 * Space: O (N + E)
	 */
	
	public static boolean task737_areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		if (words1.length != words2.length) {
			return false;
		}
		
		// build the graph
		Map<String, Set<String>> map = new HashMap<>();
		
		for (String[] arr: pairs) {
			String s1 = arr[0];
			String s2 = arr[1];
			
			if (!map.containsKey(s1)) {
				map.put(s1, new HashSet<>());
			}
			
			if (!map.containsKey(s2)) {
				map.put(s2, new HashSet<>());
			}
			
			map.get(s1).add(s2);
			map.get(s2).add(s1);
		}
		
		for (int i = 0; i < words1.length; i++) {
			String s1 = words1[i];
			String s2 = words2[i];
			
			if (s1.equals(s2)) {
				continue;
			}
			
			if (!map.containsKey(s1)) {
				return false;
			}
			if (!map.get(s1).contains(s2) && !task737_helper(s1, s2, map, new HashSet<>())) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean task737_helper(String source, String target, Map<String, Set<String>> map, Set<String> visited) {
		if (map.get(source).contains(target)) {
			return true;
		}
		
		visited.add(source);
		for (String next : map.get(source)) {
			if (!visited.contains(next) && task737_helper(next, target, map, visited)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * task743
	 * network delay time: 
	 * input: int[][] times, int N(num of nodes from 1~N), int K(start point)
	 * 
	 * output: how long it takes for the signal to reveive all nodes
	 */
	
	public static void test743() {
		int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
		int N = 4;
		int K = 2;
		int rev = task743_newworkDelay(times, N, K);
		System.out.println("rev = " + rev);
	}
	
	
	public static int task743_newworkDelay(int[][] times, int N, int K) {
		Map<Integer, List<int[]>> map = new HashMap<>();
		// node => [neighbor, weight]
		for (int[] edge: times) {
			int s = edge[0];
			int t = edge[1];
			int w = edge[2];
			if (!map.containsKey(s)) {
				map.put(s, new ArrayList<>());
			}
			Integer[] array = {t, w};
			map.get(s).add(new int[]{t, w});
		}
	
		boolean[] visited = new boolean[N + 1];
		int[] minCost = new int[N + 1];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		minCost[K] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(N, (a, b) -> {
			return a[1] - b[1];
		});
		minCost[K] = 0;
		
		pq.offer(new int[] {K, 0});
		
		int max = 0;
		while(!pq.isEmpty()) {
			// handle current node
			int[] cur = pq.poll();
			if (visited[cur[0]]) {
				continue;
			}
			visited[cur[0]] = true;
			N --;
			
			// get current node's cost
			max = cur[1];
			
			// expand its neighbor 
			if (!map.containsKey(cur[0])) {
				continue;
			}
			
			List<int[]> neighbors = map.get(cur[0]);
			for (int[] neighbor: neighbors) {
				int dist = neighbor[1] + cur[1];
				int neiNode = neighbor[0];
				if (!visited[neiNode] && dist < minCost[neiNode]) {
					pq.offer(new int[] {neiNode, dist});
					minCost[neiNode] = dist;
				}
			}
		}
		return N == 0 ? max : -1;
			
	}
	

}
