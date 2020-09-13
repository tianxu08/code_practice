package sortbynum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ds.TreeNode;
import mj_linkedin.MyIntervalLinkedIn4.ITNode;
import sortbynum.Task251_.Pos;


public class Task851_900 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test857();
//		test854();
		test864();
//		test_874();
	}

	/**
	 * task857 Minimum Cost to Hire K Workers
	 * 
	 * https://www.gjxhlan.me/2018/06/24/leetcode-contest-90-solution/
	 * 
	 * 
	 */
	public static  void test857() {
		int[] quality = {10, 20, 5};
		int[] wage = {70, 50, 30};
		int K = 2;
		
		double rev = task857_mincostToHireWorkers(quality, wage, K);
		System.out.println("rev = " + rev);
		
	}
	
	public static double task857_mincostToHireWorkers(int[] quality, int[] wage, int K) {
		Worker[] w = new Worker[quality.length];
		for (int i = 0; i < quality.length; i++) {
			w[i] = new Worker((double)wage[i] / quality[i], quality[i]);
		}
		
		Arrays.sort(w, (a, b) -> Double.compare(a.ratio, b.ratio));
		
		
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
		
		int minSum = 0;
		double min = Integer.MAX_VALUE;
		
		for (int i = 0; i < w.length; i++) {
			if (queue.size() < K - 1) {
				minSum += w[i].quality;
				queue.offer(w[i].quality);
			} else {
				min = Math.min(min, w[i].ratio * (minSum + w[i].quality));
				if (!queue.isEmpty() && queue.peek() > w[i].quality) {
					minSum = minSum - queue.poll() + w[i].quality;
					queue.offer(w[i].quality);
				}
			}
		}
		return  min;
	}
	
	
	
	public static class Worker {
		int quality;
		double ratio;
		
		public Worker(double ratio, int quality) {
			// TODO Auto-generated constructor stub
			this.quality = quality;
			this.ratio = ratio;
		}
	}
	
	
	/**
	 * task854
	 * K-similar strings
	 * 
	 * BFS:
	 * A-> nC2 following states
	 */
	
	
	public static void test854() {
		String A = "aabc";
		String B = "abca";
		
		int rev = task854_kSimilarity(A, B);
		System.out.println("rev = " + rev);
		
		int rev2 = task854_kSimilarity2(A, B);
		System.out.println("rev2 = " + rev2);
		
	}
	
	/*
	 * this method is too slow. 
	 */
	public static int task854_kSimilarity(String A, String B) {
		Queue<String> q = new LinkedList<>();
		q.offer(A);
		Set<String> visited = new HashSet<>();
		int k = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				String cur = q.poll();
				if (cur.equals(B)) {
					return k;
				}
				List<String> neighbors = generateNextLayer(cur, visited);
				for (String nei: neighbors) {
					q.offer(nei);
				}
			}
			k++;
		}
		return -1;
		
	}
	
	public static List<String> generateNextLayer(String A, Set<String> generated) {
		List<String> nextLayerStrings = new ArrayList<>();
		char[] arr = A.toCharArray();
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				swap(arr, i, j);
				String str = new String(arr);
				nextLayerStrings.add(str);
				// swap back
				swap(arr, i, j);
				if (!generated.contains(str)) {
					generated.add(str);
				}
			}
		}
		
		
		return nextLayerStrings;
	}
	
	public static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static int task854_kSimilarity2(String A, String B) {
		
		Queue<String> q = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();
		q.offer(A);
		map.put(A, 0);
		
		while (!q.isEmpty()) {
			String cur = q.poll();
			if (cur.equals(B)) return map.get(cur);
			List<String> neighbors = neighbors(cur, B);
			
			for (String nei: neighbors) {
				if (!map.containsKey(nei)) {
					q.offer(nei);
					map.put(nei, map.get(cur) + 1);
				}
				
			}
		}
		return -1;
	}
	
	public static List<String> neighbors(String str, String target) {
		List<String> res = new ArrayList<>();
		int i = 0;
		for (; i < str.length(); i++) {
			if (str.charAt(i) != target.charAt(i))
				break;
		}
		
		char[] strArr = str.toCharArray();
		for (int j = i + 1; j < strArr.length; j++) {
			if (strArr[j] == target.charAt(i)) {
				swap(strArr, i, j);
				res.add(new String(strArr));
				swap(strArr, i, j);
			}
		}
		return res;
	}
	
	
	/**
	 * task863
	 * 
	 */
	public static List<Integer> task863_distanceK(TreeNode root, TreeNode target, int K) {
		
		return null;
	}
	
	
	/**
	 * task864
	 * shortest path to get all keys
	 * 
	 * using bits to stand the keys
	 * using string to represent visited states
	 * 
	 * state: (keys, x, y)
	 */
	public static void test864() {
		String[] grid = {
			"@.c.#",
			"###.#",
			"b.C.B"
		};
//		int rev = task864_shortestPathAllKeys(grid);
//		System.out.println("rev = " + rev);
		
		String[] grid2 = {
				"@...a",".###A","b.BCc"
		};
		
		int rev2 = task864_shortestPathAllKeys(grid2);
		System.out.println("rev2 = " + rev2);
	}
	
	public static int task864_shortestPathAllKeys(String[] grid) {
		int rLen = grid.length;
		int cLen = grid[0].length();
		int x = -1, y = -1;
		int goal = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length(); j++) {
				char cur = grid[i].charAt(j); 
				if (cur == '@') {
					x = i;
					y = j;
				}
				if (cur >= 'a' && cur <= 'f') {
					int digit = (int) (cur - 'a');
					goal = goal | (1 << digit);
				}
			}
		}
		
		System.out.println("@@@@@ goal: " + goal);
		Queue<State> q = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		
		q.offer(new State(0, x, y));
		visited.add(0 + ":" + x + ":" + y);
		
		int step = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				System.out.println("!!!! visited: " + visited);
				System.out.println("===>>>> queue: " + q);
				State cur = q.poll();
				int curX = cur.x;
				int curY = cur.y;
				int curK = cur.keys;
				if (curK == goal) {
					return step;
				}
				for (int[] dir : directions) {
					int nextX = curX + dir[0];
					int nextY = curY + dir[1];
					if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen) {
						
						char ch = grid[nextX].charAt(nextY);
						int keys = curK;
						
						if (ch == '#') {
							continue;
						}
						
						if (ch >= 'a' && ch <= 'f') {
							// update new keys
							int pos = (int)(ch - 'a');
							int mask = 1 << pos;
							keys = keys | mask;	
						} 
						
						if (ch >= 'A' && ch <= 'F' && ((keys >> (ch - 'A') ) & 1) == 0) {
							// do NOT have the key to unlock the lock
							continue;
						}
						
						String hashVal = curK + ":" + nextX + ":" + nextY;
						if (!visited.contains(hashVal)) {
							visited.add(hashVal);
							q.offer(new State(keys, nextX, nextY));
						}
					}
				}
			}
			step ++;
		}
		
		return -1;
		
		
	}
	public static class State{
		int keys;
		int x;
		int y;
		public State(int _k, int _x, int _y) {
			this.keys = _k;
			this.x = _x;
			this.y = _y;
		}
	
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + x + "," + y + "]" + "->" + keys;
		}
	}
	
	public static int[][] directions = {
			{-1, 0},
			{1, 0},
			{0, -1}, 
			{0, 1}
	};
	
	/**
	 * task874
	 * shortest path visiting all nodes
	 * 
	 * state: cur_node, visited_nodes.
	 * we can use a integer to store all visited nodes. 
	 * if a node is visited, its corresponding bit will be set to 1, otherwise, set to 0
	 * 
	 * goal: (1<<N) - 1
	 */
	
	public static void test_874() {
		int[][] graph = {{1,2,3},{0},{0},{0}};
		int rev = task874_shortestPath(graph);
		System.out.println("rev = " + rev);
	}
	public static int task874_shortestPath(int[][] graph) {
		int N = graph.length;
		int goal = (1 << N) - 1;
		boolean[][] visited = new boolean[N][goal + 1];

		Queue<int[]> q = new LinkedList<>();
		
		// push 0-N into queue
		for (int i = 0; i < N; i++) {
			int state = 1 << i;
			q.add(new int[] {i, state});
			visited[i][state] = true;
		}
		
		int steps = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				int node = cur[0];
				int state = cur[1];
				if (state == goal) {
					return steps;
				}
				
				int[] neighbors = graph[node];
				for (Integer nei: neighbors) {
					int neiState = state | (1 << nei);
					
					if (!visited[nei][neiState]) {
						visited[nei][neiState] = true;
						q.add(new int[] {nei, neiState});
					}
				}
			}
			steps ++;
		}
		
		return -1;
	}
	
	
	
	

	  
	

}
