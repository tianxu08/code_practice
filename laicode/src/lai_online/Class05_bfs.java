package lai_online;


import java.util.*;

import debug.Debug;
import ds.TreeNode;

public class Class05_bfs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2_1();
		test6();
	}
	
	/*
	 * list
	 * 1 get keys in binary tree lay by layer
	 * 1_2 Get Keys In Binary Tree Layer By Layer Zig-Zag Order
	 * 
	 * 2 K Smallest numbers in Unsorted Array
	 * 3 Bipartite
	 * 4 Kth Smallest Number In Sorted Matrix 
	 * 5 Kth Smallest With Only 2, 3 As Factors
	 * 6 Kth Smallest With Only 3, 5, 7 As Factors
	 * 7 Place To Put The Chair I
	 * 8 Place To Put The Chair II
	 * 9 Max Water Trapped II
	 * 10 Largest Product Of Length 
	 * 11 KthClosestPointTo<0,0,0>
	 */

	/*
	 * task1 
	 * get keys in binary tree layer by layer
	 * 
	 * dfs
	 */
	public List<List<Integer>> task1_1_layerByLayer(TreeNode root) {
		// Write your solution here.

		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		helper(result, root, 0);

		return result;
	}

	public void helper(ArrayList<List<Integer>> result, TreeNode node, int level) {
		// do a preorder traversal
		if (node == null) {
			return;
		}
		List<Integer> line = null;
		if (result.size() <= level) {
			line = new ArrayList<Integer>();
			result.add(line);
		} else {
			line = result.get(level);
		}
		line.add(node.val);

		helper(result, node.left, level + 1);
		helper(result, node.right, level + 1);

	}
	
	
	/*
	 * task1_2
	 * Follow up
	 * Get Keys In Binary Tree Layer By Layer Zig-Zag Order
	 * use deque
	 */

	
	/*
	 * task2
	 * K Smallest numbers in Unsorted Array
	 * Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.
	 * Assumptions
	 * A is not null
	 * K is >= 0 and smaller than or equal to size of A
	 * Return
	 * an array with size K containing the K smallest numbers in ascending order
	 * Examples
	 * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
	 */
	
	
	// method1: using priority queue. maxHeap size is k
	// time: n log k
	public static void test3() {
		int[] array = { 3, 1, 5, 2, 4 };
		int[] result = task2_1_kSmallest(array, 5);

		Debug.printArray(result);

	}
	public static int[] task2_1_kSmallest(int[] array, int k) {
		if (array == null || array.length == 0) {
			return array;
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						if (o1 > o2) {
							return -1;
						} else if (o1 < o2) {
							return 1;
						} else {
							return 0;
						}
					}

				});

		for (int i = 0; i < k; i++) {
			maxHeap.offer(array[i]);
		}

		System.out.println(maxHeap.peek());

		for (int i = k; i < array.length; i++) {
			if (array[i] >= maxHeap.peek()) {
				continue;
			} else {
				maxHeap.poll();
				maxHeap.add(array[i]);
			}
		}

		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}

	
	public static int[] kSmallest2(int[] array, int k) {
		if (array == null) {
			return null;
		}
		if (array.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						if (o1.equals(o2)) {
							return 0;
						}
						return o1 > o2 ? -1 : 1;
					}
				});

		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				// offer the first k elements into max heap.
				maxHeap.offer(array[i]);
			} else if (array[i] < maxHeap.peek()) {
				// for the other elements, only offer it into the max heap if it
				// is
				// smaller than the max value in the max heap.
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = maxHeap.poll();
		}
		return result;
	}
	
	
	// method2
	// use the idea quick sort
	
	public static void test2_1() {
		int[] array = {5,3,2,8,9,1,6};
		int[] result = task2_1_kSmallest_method2(array, 3);
		int[] result2 = kSmallest2(array, 3);
		System.out.println(Arrays.toString(result2));
	}
	public static int[] task2_1_kSmallest_method2 (int[] array, int k) {
		
		quickSelect(array, 0, array.length - 1, k - 1);
		int[] result = Arrays.copyOf(array, k);
		System.out.println(Arrays.toString(result));
		return result;
	}
	
	public static void quickSelect(int[] array, int start, int end, int target) {
		int mid = partition(array, start, end);
		if (mid == target) {
			return;
		} else if (target < mid) {
			quickSelect(array, start, mid - 1, target);
		} else {
			quickSelect(array, mid + 1, end, target);
		}
	}
	
	public static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int start = left, end = right - 1;
		while(start <= end) {
			if (array[start] < pivot) {
				start ++;
			} else if (array[end] >= pivot) {
				end --;
			} else {
				// swap array[start], array[end]
				swap(array, start, end);
				start ++;
				end --;
			}
		}
		// the start points the location which belongs pivot
		swap(array, start, right);
		return start;
	}
	public static void swap(int[] array, int start, int end) {
		int temp = array[start];
		array[start] = array[end];
		array[end] = temp;
	}
	
	
	
	
	/*
	 * Q3: Bipartite
	 * Graph
	 * Determine if an undirected graph is bipartite. 
	 * A bipartite graph is one in which the nodes can be divided into two groups 
	 * such that no nodes have direct edges to other nodes in the same group.
	 * 
	 * 
	 * Method:
	 * using hashMap<GraphNode, Integer> to store the sign of node. 
	 * and mark whether the node is visited or not.
	 * 
	 * to every node, we do a BFS. 
	 * 
	 */
	public static class GraphNode {
		public int key;
		public List<GraphNode> neighbors;
		public GraphNode (int key) {
			this.key = key;
			this.neighbors = new ArrayList<GraphNode>();
		}
	}
	
	public static boolean Q1_Bipartite(List<GraphNode> graph) {
		if (graph == null) {
			return true;
		}
		HashMap<GraphNode, Integer> map = new HashMap<GraphNode, Integer>();
		for(GraphNode node: graph) {
			if (Q3_BFS(node, map) == false) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean Q3_BFS(GraphNode node, HashMap<GraphNode, Integer> map) {
		if (map.containsKey(node)) {
			// this node has already been expanded/marked
			// do nothing with this node.
			return true;
		}
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.offer(node);
		map.put(node, 0);
		
		while(!q.isEmpty()) {
			GraphNode cur = q.poll();
			int curSign = map.get(cur);
			int neighSign = curSign == 0 ? 1 : 0;
			
			// get neighbors and assign the sign to them, or check whether the sign of neighbor is 
			// different with the curSign.
			for(GraphNode nei : cur.neighbors) {
				if (!map.containsKey(nei)) {
					// the nei hasn't been generated
					map.put(nei, neighSign);
					q.add(nei);
				} else if (map.get(nei) != neighSign) {
					// nei has been generated, but its sign is different with neighSign. 
					// this violate the property of bipaartite
					return false;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * Q4:Kth Smallest Number In Sorted Matrix 
	 * Given a matrix of size N x M. For each row the elements are sorted in ascending order, 
	 * and for each column the elements are also sorted in ascending order. 
	 * Find the Kth smallest number in it.
	 * 
	 * 
	 * AsItemptions
	 * the matrix is not null, N > 0 and M > 0
	 * K > 0 and K <= N * M
	 * Examples
	 * { 
	 * {1,  3,   5,   7},
	 * {2,  4,   8,   9},
	 * {3,  5,  11,  15},
	 * {6,  8,  13,  18} 
	 * }
	 * the 5th smallest number is 4
	 * the 8th smallest number is 6
	 * 
	 * 1. Initial state A[0][0]
	 * 2. Node expansion/Generation rule:
	 *    A[i][j] -> generate A[i + 1][j] and A[i][j + 1]
	 * 3. Termination condition:
	 * 	  when the kth element is popped out of the heap for expansion, then stop. 
	 * !!!
	 * Store all elements A[i][j] in a hashSet to avoid node duplication. 
	 * 
	 */
		
	public static class Item {
		public int x;
		public int y;
		public int value;
		public Item (int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
	
	public static void Q4_test() {
		int[][] matrix = { {1,  3,   5,  7},
				  		   {2,  4,   8,   9},
				           {3,  5, 11, 15},
				           {6,  8, 13, 18} };
		int k = 5;
		int rev = Q4_KthSmallestMatrix(matrix, k);
		System.out.println(" rev = " + rev);
	}
	public static int Q4_KthSmallestMatrix(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0 ) {
			return Integer.MIN_VALUE;
		}
		
		int len = matrix.length;
		int clen = matrix[0].length;
		// create a minHeap
		PriorityQueue<Item> minHeap = new PriorityQueue<Item>(k, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				// TODO Auto-generated method stub
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value? -1: 1;
			}
		});
		
		boolean[][] visited = new boolean[len][clen];
		// to avoid duplicate
		
		int result = Integer.MIN_VALUE;
		visited[0][0] = true;
		minHeap.offer(new Item(0, 0, matrix[0][0]));
		while(k > 0) {
			Item cur = minHeap.poll();
			result = cur.value;
			if (cur.x + 1 < len && !visited[cur.x + 1][cur.y]) {
				// go down
				minHeap.offer(new Item(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
				visited[cur.x + 1][cur.y] = true;
			}
			if (cur.y + 1 < clen && !visited[cur.x][cur.y + 1]) {
				// go right
				minHeap.offer(new Item(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
				visited[cur.x][cur.y + 1] = true;
			}
			k --;
		}
		return result;	
	}
	
	
	/*
	 * Q5:
	 * Kth Smallest With Only 2, 3 As Factors
	 * Find the Kth smallest number s 
	 * such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
	 * 
	 * Assumptions
	 * K >= 1
	 * Examples
	 * the smallest is 1
	 * the 2nd smallest is 2
	 * the 3rd smallest is 3
	 * the 5th smallest is 2 * 3 = 6
	 * the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
	 * 
	 * Initial state: 1 = 2^0 * 3^0
	 * Expand / Generate rule:
	 *         <i, j> => <i + 1, j> and <i, j + 1>
	 * Termination: the kth time to pop out from priority queue 
	 * using a hashset to avoid generating more then once.
	 * !!! the result might out of bound. transfer this to Long. Discuss with the Interviewer. 
	 */
	
	public static void Q5_test() {
		for(int i = 1; i <= 6; i ++) {
			System.out.println("i = " + Q5_KthSmallestWith2And3AsFactors(i));
		}
	}
	public static int Q5_KthSmallestWith2And3AsFactors(int k) {
		if (k < 1) {
			return -1; 
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.offer(1);
		visited.add(1);
		
		while(k > 1) { // pop out n - 1 time
			int cur = minHeap.poll();
			if (visited.add(cur * 2)) {
				minHeap.add(cur * 2);
			}
			if (visited.add(cur * 3)) {
				minHeap.add(cur * 3);
			}
			k --;
		}
		return minHeap.peek();
	}
	
	
	
	
	/*
	 * Q6
	 * Kth Smallest With Only 3, 5, 7 As Factors
	 * Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, 
	 * 		x > 0 and y > 0 and z > 0, x, y, z are all integers.
	 * Assumptions
	 * K >= 1
	 * Examples
	 * the smallest is 3 * 5 * 7 = 105
	 * the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
	 * the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
	 * the 5th smallest is 3 ^ 3 * 5 * 7 = 945
	 */
	public static void test6() {
		int k = 10;
		int rev1 = Q6_kthSmallestWith3_5_7_method2(k);
		int rev2 = Q6_kthSmallestWith3_5_7_method2(k);
		
		System.out.println("rev1 = " + rev1);
		System.out.println("rev2 = " + rev2);
	}
	
	public static long Q6_kthSmallestWith3_5_7(int k) {
		// write your solution here
		if (k < 1) {
			return -1;
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		minHeap.add(3 * 5 * 7);
		visited.add(3 * 5 * 7);

		while (k > 1) { // pop out k - 1 time
			int current = minHeap.poll();
			if (!visited.contains(current * 3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			if (!visited.contains(current * 5)) {
				visited.add(current * 5);
				minHeap.add(current * 5);
			}
			if (!visited.contains(current * 7)) {
				visited.add(current * 7);
				minHeap.add(current * 7);
			}
			k--;
		}
		return minHeap.peek();
	}
	
	// use deque
	public static int Q6_kthSmallestWith3_5_7_method2(int k) {
		int seed = 1;
		Deque<Integer> three = new LinkedList<Integer>();
		Deque<Integer> five = new LinkedList<Integer>();
		Deque<Integer> seven = new LinkedList<Integer>();
		
		three.add(seed * 3);
		five.add(seed * 5);
		seven.add(seed * 7);
		int result = seed;
		while(k > 0) {
			if (three.peekFirst() < five.peekFirst() && three.peekFirst() < seven.peekFirst()) {
				result = three.pollFirst();
				three.offerLast(result * 3);
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else if (five.peekFirst() < three.peekFirst() && five.peekFirst() < seven.peekFirst()) {
				result = five.pollFirst();
				five.offerLast(result * 5);
				seven.offerLast(result * 7);
			} else {
				result = seven.pollFirst();
				seven.offerLast(result * 7);
			}
			
			k --;
		}
		
		System.out.println(result);
		return result;
	}
	
	
	
	/*
	 * Q7: 
	 * Place To Put The Chair I
	 * Given a gym with k pieces of equipment and some obstacles.  
	 * We bought a chair and wanted to put this chair into the gym 
	 * such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. 
	 * 
	 * 
	 * The gym is represented by a char matrix, 
	 * ‘E’ denotes a cell with equipment, 
	 * ‘O’ denotes a cell with an obstacle, 
	 * ' ' denotes a cell without any equipment or obstacle.
	 *  
	 *  You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle.
	 *  The cost of moving from one cell to its neighbor is 1. 
	 *  You can not put the chair on a cell with equipment or obstacle.
	 *  Assumptions
	 *  There is at least one equipment in the gym
	 *  The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, 
	 *  it is guaranteed to be not null
	 *  If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
	 *  Examples
	 *  { 
	 *  { 'E', 'O', ' ' },
	 *  {  ' ', 'E',  ' ' },
	 *  {  ' ',  ' ',  ' ' }
	 *  }
	 *  we should put the chair at (1, 0), 
	 *  so that the sum of cost from the chair to the two equipments is 1 + 1 = 2, which is minimal.
	 *  
	 *  assumption: 
	 *  gym is N*N
	 *  1) the cost from one cell to its neighbors(up/down/left/right) is 1
	 *  2)  ‘E’ denotes a cell with equipment,  ‘O’ denotes a cell with an obstacle
	 *  3) The chair can not be put on equipment or obstacle
	 *  
	 *  int[][] cost, cost[i][j] to store the sum of distance from (i,j) to all equipment. 
	 *  Run Dijkstra's algorithm from each equipment, and update cost[i][j]. 
	 *  traverse cost[i][j], and get the minimum's cooridination.  
	 */
	
	public static void Q7_test() {
		char[][] gym = {
				{'E',  'O', ' '},
				{' ', 'E', ' '},
				{' ',  ' ', ' '}
		};
		List<Integer> result = Q5_PutChair(gym);
		System.out.println(result);
		
//		char[][] gym2 = {
//				{'O'}
//		}; 
//		List<Integer> result2 = Q5_PutChair(gym2);
//		System.out.println(result2);
	}
	
	public static class Pair {
		public int x;
		public int y;
		public Pair(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	// this function return the position that the chair should be put.
	public static List<Integer> Q5_PutChair(char[][] gym) {
		if (gym == null || gym.length == 0 || gym[0] == null || gym[0].length == 0) {
			return null;
		}
		int len = gym.length;
		int[][] costSum = new int[len][len];
		Pair result = null;
		
		// get costSum matrix
		for(int i = 0; i < len; i ++) {
			for(int j = 0; j < len; j ++) {
				if (gym[i][j] == 'E') {	
					if (!Q5_AddCost(gym, costSum, i, j)) {
						return null;
					}
					Debug.printMatrix(costSum);
				}
			}
		}
			
		// get the position to put the chair
		for(int i = 0; i < len; i ++) {
			for(int j = 0; j < len; j ++) {
				if (gym[i][j] != 'O' && gym[i][j] != 'E') {
					if (result == null) {
						result = new Pair(i, j);
					} else {
						if (costSum[i][j] < costSum[result.x][result.y]) {
							result.x = i;
							result.y = j;
						}
					}
				}
			}
		}
		
		List<Integer> rev = new ArrayList<Integer>();
		if (result == null) {
			return null;
		} else{
			rev.add(result.x);
			rev.add(result.y);
		}
		
		return rev;
	}
	// this function run Dijkstra's algorithm from one equipment and update every empty cell's cost, 
	// adding distance to this equipment.
	// assume (i,j) are equipment. 
	public static boolean Q5_AddCost(char[][] gym, int[][] costSum, int i, int j) {
		int len = gym.length;
		boolean[][] visited = new boolean[len][len];
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(j, j));
		visited[i][j] = true;
		
		int cost = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int ith = 0; ith < size; ith ++ ) {
				// expand the node
				Pair curPair = q.poll();
				costSum[curPair.x][curPair.y] += cost;
				// generate its neighbor
				List<Pair> neighbors = Q5_getNeighbors(curPair, gym, len);
				// add neighbors into the queue
				for(Pair p : neighbors) {
					if (!visited[p.x][p.y]) { // the 
						q.add(p);
						visited[p.x][p.y] = true;
					}
				}
			}
			cost ++;  // the next layer, cost ++
		}
		
		// traverse the gym again to make sure that every equipment is accessible. 
		// if there is equipment unaccessible, so, the distance from any cell to this equipment is infinite. 
		// there is no such position to put chair in, and let the sum of distance is minimum. 
		for(int x = 0; x < len; x ++) {
			for(int y = 0; y < len; y ++) {
				if (gym[x][y] == 'E' && visited[x][y] == false) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	
	private static List<Pair> Q5_getNeighbors(Pair cur, char[][] gym, int len) {
		List<Pair> neighbors = new ArrayList<Pair>();
		int rLen = gym.length;
		int cLen = gym[0].length;
		for(int i = 0; i < 4; i ++) {
			int nextX = cur.x + dx[i];
			int nextY = cur.y + dy[i];
			if (nextX >= 0 && nextX < rLen && nextY >= 0 && 
				nextY < cLen && gym[nextX][nextY] !='O') {
				neighbors.add(new Pair(nextX, nextY));
			}
		}
		return neighbors;
	}
	
	
	
	
	/*
	 * LaiCode
	 * the matrix is M*N  NOT N*N
	 */
	
	public static void Q5_test_Lai() {
		char[][] gym = { 
				{ ' ', ' ', 'E', 'O', ' ' },
				{ ' ', ' ', 'O', ' ', 'E' }, 
				{ ' ', ' ', 'E', 'E', ' ' },
				{ ' ', 'O', ' ', 'E', 'E' }, 
				{ ' ', ' ', 'O', ' ', ' ' } 
				};
		List<Integer> result = Q5_PutChair_Lai(gym);
		System.out.println(result);
	}
	
	// this function return the position that the chair should be put.
	public static List<Integer> Q5_PutChair_Lai(char[][] gym) {
		if (gym == null || gym.length == 0 || gym[0] == null || gym[0].length == 0) {
			return null;
		}
		int rlen = gym.length;
		int clen = gym[0].length;
		int[][] costSum = new int[rlen][clen];
		Pair result = null;
		
		// get costSum matrix
		for(int i = 0; i < rlen; i ++) {
			for(int j = 0; j < clen; j ++) {
				if (gym[i][j] == 'E') {
					if (!Q5_AddCost_Lai(gym, costSum, i, j)) {
						return null;
					}
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("i = " + i + " " + "j = " + j);
					Debug.printMatrix(costSum);
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
			}
		}
		
		// get the position to put the chair
		for(int i = 0; i < rlen; i ++) {
			for(int j = 0; j < clen; j ++) {
				if (gym[i][j] != 'O' && gym[i][j] != 'E') {
					if (result == null) {
						result = new Pair(i, j);
					} else {
						if (costSum[i][j] < costSum[result.x][result.y]) {
							result.x = i;
							result.y = j;
						}
					}
				}
			}
		}
		
		System.out.println("------------------------");
		Debug.printMatrix(costSum);
		System.out.println("------------------------");
		
		List<Integer> rev = new ArrayList<Integer>();
		if (result == null) {
			return null;
		} else{
			rev.add(result.x);
			rev.add(result.y);
		}
		
		return rev;
	}
	
	/*
	 * [
	 * [X,X,E,O,X], 
	 * [X,X,O,X,E], 
	 * [X,X,E,E,X], 
	 * [X,O,X,E,E], 
	 * [X,X,O,X,X]
	 * ]
	 */
	// this function run Dijkstra's algorithm from one equipment and update every empty cell's cost, 
	// adding distance to this equipment.
	// assume (i,j) are equipment. 
	public static boolean Q5_AddCost_Lai(char[][] gym, int[][] costSum, int i, int j) {
		int rlen = gym.length;
		int clen = gym[0].length;
		boolean[][] visited = new boolean[rlen][clen];
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(i, j));
		visited[i][j] = true;
		int cost = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int ith = 0; ith < size; ith ++ ) {
				// expand the node
				Pair curPair = q.poll();
				costSum[curPair.x][curPair.y] += cost;
				// generate its neighbor
				List<Pair> neighbors = Q7_getNeighbors_Lai(curPair, gym);
				// add neighbors into the queue
				for(Pair p : neighbors) {
					if (!visited[p.x][p.y]) { // the 
						q.add(p);
						visited[p.x][p.y] = true;
					}
				}
			}
			cost ++;  // the next layer, cost ++
		}
		
		// traverse the gym again to make sure that every equipment is accessible. 
		// if there is equipment unaccessible, so, the distance from any cell to this equipment is infinite. 
		// there is no such position to put chair in, and let the sum of distance is minimum. 
		for(int x = 0; x < rlen; x ++) {
			for(int y = 0; y < clen; y ++) {
				if (gym[x][y] == 'E' && !visited[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static List<Pair> Q7_getNeighbors_Lai(Pair cur, char[][] gym) {
		int rlen = gym.length;
		int clen = gym[0].length;
		List<Pair> neighbors = new ArrayList<Pair>();

		if (cur.x + 1 < rlen && gym[cur.x + 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}

		if (cur.x - 1 >= 0 && gym[cur.x - 1][cur.y] != 'O') {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}

		if (cur.y + 1 < clen && gym[cur.x][cur.y + 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}

		if (cur.y - 1 >= 0 && gym[cur.x][cur.y - 1] != 'O') {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;
	}
	
	
	/*
	 * Q8: 
	 * Place To Put The Chair II
	 * Given a gym with k pieces of equipment without any obstacles.  
	 * Let’s say we bought a chair and wanted to put this chair 
	 * into the gym such that the sum of the shortest path cost 
	 * from the chair to the k pieces of equipment is minimal. 
	 * The gym is represented by a char matrix, 
	 * ‘E’ denotes a cell with equipment, ' ' denotes a cell without equipment. 
	 * The cost of moving from one cell to its neighbor(left, right, up, down) is 1. 
	 * You can put chair on ANY cell in the gym.
	 * 
	 * Assumptions
	 * There is at least one equipment in the gym
	 * The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, 
	 * it is guaranteed to be not null
	 * 
	 * Examples
	 * { 
	 * 	{  'E', ' ', ' ' },
	 * 	{  ' ', 'C', ' ' },
	 * 	{  ' ', ' ', 'E' } 
	 * }
	 * 'C' stands for Chair
	 * 
	 * we should put the chair at (1, 1), 
	 * so that the sum of cost from the chair to the two equipments is 2 + 0 + 2 = 4, which is minimal.
	 * 
	 * (1)Run Dijkstra's for every empty cell, 
	 * calculate the shortest path from this empty cell to every equipment.
	 *    Time: n^2 log(n)
	 * (2) Run Dijkstra's for every equipment, 
	 *  
	 */
	public static List<Integer> Q8_putChairII(char[][] gym) {
		if (gym == null || gym.length == 0 || gym[0] == null || gym[0].length == 0) {
			return null;
		}
		int rlen = gym.length;
		int clen = gym[0].length;
		int[][] costSum = new int[rlen][clen];
		Pair result = null;
		
		for(int i = 0; i < rlen; i ++) {
			for(int j = 0; j < clen; j ++) {
				if (gym[i][j] == 'E') {
					Q8_AddCost(gym, costSum, i, j);
				}
			}
		}

		// traverse the costSum matrix
		for(int i = 0; i < rlen; i ++) {
			for(int j = 0; j < clen; j ++) {
				if (gym[i][j] != 'E') {
					if (result == null) {
						result = new Pair(i, j);
					} else {
						if (costSum[result.x][result.y] > costSum[i][j]) {
							result.x = i;
							result.y = j;
						}
					}
				}
			}
		}
		if (result == null) {
			return null;
		} else {
			List<Integer> rev = new ArrayList<Integer>();
			rev.add(result.x);
			rev.add(result.y);
			return rev;
		}
	}
	
	public static void Q8_AddCost(char[][] gym, int[][] costSum, int i, int j) {
		int rLen = gym.length;
		int cLen = gym[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(i, j));
		visited[i][j] = true;
		int cost = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int ith = 0; ith < size; ith ++) {
				// expand cur
				Pair cur = q.poll();
				costSum[cur.x][cur.y] += cost;
				List<Pair> neighbors = Q8_GetNeighbors(gym, cur);
				for(Pair neighbor: neighbors) {
					if (!visited[neighbor.x][neighbor.y]) {
						// not been expanded yet
						q.offer(neighbor);
						visited[neighbor.x][neighbor.y] = true;
					}
				}
			}
			cost ++;
		}
		
	}
	
	public static List<Pair> Q8_GetNeighbors(char[][] gym, Pair cur) {
		int rLen = gym.length;
		int cLen = gym[0].length;
		List<Pair> neighbors = new ArrayList<Pair>();
		if (cur.x + 1 < rLen) {
			neighbors.add(new Pair(cur.x + 1, cur.y));
		}
		if (cur.x - 1 >= 0) {
			neighbors.add(new Pair(cur.x - 1, cur.y));
		}
		if (cur.y + 1 < cLen) {
			neighbors.add(new Pair(cur.x, cur.y + 1));
		}
		if (cur.y - 1 >= 0) {
			neighbors.add(new Pair(cur.x, cur.y - 1));
		}
		return neighbors;
	}
	
	
	/*
	 * Q9 
	 * Max Water Trapped II
	 * Given a non-negative integer 2D array representing the heights of bars in a matrix. 
	 * Suppose each bar has length and width of 1. 
	 * Find the largest amount of water that can be trapped in the matrix. 
	 * The water can flow into a neighboring bar 
	 * if the neighboring bar's height is smaller than the water's height. 
	 * Each bar has 4 neighboring bars to the left, right, up and down side.
	 * Assumptions
	 * The given matrix is not null and has size of M * N, where M > 0 and N > 0
	 * Examples
	 * { 
	 * { 2, 3, 4, 2 },
	 * { 3, 1, 2, 3 },
	 * { 4, 3, 5, 4 } }
	 * the amount of water can be trapped is 3, 
	 * at position (1, 1) there is 2 units of water trapped,
	 * at position (1, 2) there is 1 unit of water trapped.
	 * 
	 */
	
	public static class Coordinate {
		public int x;
		public int y;
		public int height;
		public int level;
		public Coordinate(int _x, int _y, int _height, int _level) {
			this.x = _x;
			this.y = _y;
			this.height = _height;
			this.level = _level;
		}
	}
	
	public static void test9() {
		int[][] matrix = { 
				{ 2, 3, 4, 2 },
				{ 3, 1, 2, 3 },
				{ 4, 3, 5, 4 }
		};
		int sum = Q9_maxTrapped(matrix);
		System.out.println("sum = " + sum);
	}
	
	public static int Q9_maxTrapped(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		int[][] level = new int[rLen][cLen];

		// initialize the level matrix
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					// the edges, level == matrix[i][j]
					level[i][j] = matrix[i][j];
				} else {
					level[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		Comparator<Coordinate> myComp = new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				// TODO Auto-generated method stub
				return o1.level - o2.level;
			}
		};
		PriorityQueue<Coordinate> minHeap = new PriorityQueue<Coordinate>(rLen * cLen, myComp);

		// visit the border of the matrix
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					Coordinate point = new Coordinate(i, j, matrix[i][j],
							level[i][j]);
					// put the point into priority queue
					minHeap.add(point);
					visited[i][j] = true;
				}
			}
		}
		
		int sum = 0;
		while (!minHeap.isEmpty()) {
			Coordinate cur = minHeap.poll();
			sum += cur.level - cur.height;

			List<Coordinate> neighbors = Q7_getNeighbor(matrix, cur, visited,
					level);
			for (Coordinate neighbor : neighbors) {
				if (visited[neighbor.x][neighbor.y] == false) {
					int newLevel = Math
							.max(neighbor.height, Math.min(
									level[neighbor.x][neighbor.y],
									level[cur.x][cur.y]));

					if (level[neighbor.x][neighbor.y] != newLevel) {
						// update in neighbor's. level
						neighbor.level = newLevel;
						level[neighbor.x][neighbor.y] = newLevel;
						minHeap.add(neighbor);
						visited[neighbor.x][neighbor.y] = true;
					}
				}

			}
		}
		return sum;
	}
	
	public static List<Coordinate> Q7_getNeighbor(int[][] matrix, Coordinate coordinate, boolean[][] visited, int[][] level) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int x = coordinate.x;
		int y = coordinate.y;
		List<Coordinate> neighbor = new ArrayList<Coordinate>();
		if (x + 1 < rLen && !visited[x+1][y]) {
			neighbor.add(new Coordinate(x + 1, y, matrix[x + 1][y], level[x+ 1][y]));
		}
		if (x - 1 >= 0 && !visited[x-1][y]) {
			neighbor.add(new Coordinate(x - 1, y, matrix[x - 1][y], level[x-1][y]));
		}
		
		if (y - 1 >= 0 && !visited[x][y-1]) {
			neighbor.add(new Coordinate(x, y - 1, matrix[x][y-1], level[x][y-1]));
		}
		if (y + 1 < cLen && !visited[x][y + 1]) {
			neighbor.add(new Coordinate(x, y + 1, matrix[x][y + 1], level[x][y + 1]));
		}
		return neighbor;
	}
	
	
	
	
	
	/*
	 * Q10
	 * Largest Product Of Length 
	 * Hard String 
	 * Given a dictionary containing many words, 
	 * find the largest product of two words’ lengths, such that the
	 * two words do not share any common characters.
	 * 
	 * Assumptions
	 * 
	 * The words only contains characters of 'a' to 'z' The dictionary is not
	 * null and does not contains null string, and has at least two strings If
	 * there is no such pair of words, just return 0 Examples
	 * 
	 * dictionary = [“abcde”, “abcd”, “ade”, “xy”], 
	 * the largest product is 5 * 2 = 10 (by choosing “abcde” and “xy”)
	 * 
	 * (1) initial state: <w1, w2>
	 * (2) Expansion/Generation rule
	 *      a) expand <wi, wj>
	 *      b) generate <wi + 1, wj> or <wi, wj + 1>
	 *      	i. the definition of a valid state
	 *      	   1 i != j
	 *      	   2 wi and wj does not share same char
	 * (3) Termination condition
	 *     a. when the first state that is valid is popped out of the heap, then we terminate
	 *        and return that state <wi, wj>
	 */
	
	public static class Element{
		public int product;
		public Pair pair;
		public Element(int p, int x, int y) {
			// TODO Auto-generated constructor stub
			this.product = p;
			this.pair = new Pair(x, y);
		}
	}
	
	public static void test10() {
		String[] dict = {"abcde", "abcd", "ade", "xy"};
		int result = Q10_largestProduct(dict);
		System.out.println("result = " + result);
	}
	public static int Q10_largestProduct(String[] dict) {
		// write your solution here.
		if (dict == null || dict.length < 2) {
			return 0;
		}
		Comparator<String> myComp = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o2.length() - o1.length();
			}
		};
		
		Comparator<Element> myComp2 = new Comparator<Element>() {
			
			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				return o2.product - o1.product;
			}
		}; 
		
		Arrays.sort(dict, myComp);
		for(int i = 0; i < dict.length; i ++) {
			System.out.println(dict[i]);
		}
		
		PriorityQueue<Element> heap = new PriorityQueue<Element>(2*dict.length, myComp2);
		int x = 0;
		int y = 1;
		int curProduct = dict[x].length() * dict[y].length();
		heap.add(new Element(curProduct, x, y));
		
		while (!heap.isEmpty()) {
			Element cur = heap.poll();
			x = cur.pair.x;
			y = cur.pair.y;
			String str_x = dict[x];
			String str_y = dict[y];
			
			if ( x != y && checkTwoWords2(str_x, str_y)) {
				int curMaxProduct = str_x.length() * str_y.length();
				
				return curMaxProduct;
			}
			
			// generate cur.x - 1, cur.y and cur.x, cur.y - 1
			if (x + 1 < dict.length) {
				int x_len = dict[x+1].length();
				int y_len = dict[y].length();
				heap.add(new Element(x_len*y_len, x + 1, y));
			}
			if (y + 1 < dict.length) {
				int x_len = dict[x].length();
				int y_len = dict[y + 1].length();
				heap.add(new Element(x_len* y_len, x, y + 1));
			}
		}
		return 0;
	}
	
	
	public static boolean checkTwoWords(String w1, String w2) {
		int[] map = new int[256];
		for(int i = 0; i < w1.length(); i ++) {
			map[w1.charAt(i)] ++;
		}
		
		for(int i = 0; i < w2.length(); i ++) {
			if (map[w2.charAt(i)] != 0) {
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * 0000 0000 0000 0000 0000 0000 0000 0000 
	 * 
	 */
	public static void test11() {
		String w1 = "abc";
		String w2 = "aef";
		boolean result = checkTwoWords2(w1, w2);
		System.out.println("result = " + result);
	}
	
	public static boolean checkTwoWords2(String w1, String w2) {
		int[] bitMap = new int[8];
		for(int i = 0; i < w1.length(); i ++) {
			char curCh = w1.charAt(i);
			int rowIndex = (int) curCh / 32;
			int offset = (int) curCh % 32;
//			System.out.println("offset = " + offset);
			int mask = 1 << offset;
			bitMap[rowIndex] |= mask;
		}
		
		for(int i = 0; i < w2.length(); i ++) {
			char curCh = w2.charAt(i);
			int rowIndex = (int) curCh / 32;
			int offset = (int) curCh % 32;
			int mask = 1 << offset;
			if ((bitMap[rowIndex] & mask) != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/*
	 * task11
	 * Kth Closest Point
	 * Fair Data Structure
	 * Given three arrays sorted in ascending order. 
	 * Pull one number from each array to form a coordinate <x,y,z> in a 3D space. 
	 * Find the coordinates of the points that is k-th closest to <0,0,0>.
	 * We are using Euclidean distance here.
	 * Assumptions
	 * The three given arrays are not null or empty
	 * K >= 1 and K <= a.length * b.length * c.length
	 * Return
	 * a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
	 * Examples
	 * A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
	 * The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
	 * The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
	 */
	public static int[] task11_closest(final int[] a, final int[] b, final int[] c, int k) {
		Comparator<List<Integer>> myComp = new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				// TODO Auto-generated method stub
				long dist1 = distance(o1, a, b, c);
				long dist2 = distance(o2, a, b, c);
				
				if (dist1 == dist2) {
					return 0;
				}
				return dist1 < dist2 ? -1: 1;
			}
		};
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<List<Integer>>(2*k, myComp);
		HashSet<List<Integer>> visited = new HashSet<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		visited.add(cur);
		minHeap.add(cur);
		
		while( k > 0) {
			cur = minHeap.poll();
			List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
			if (n.get(0) < a.length && !visited.contains(n)) {
				minHeap.add(n);
				visited.add(n);
			} 
			n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
			if (n.get(1) < b.length && !visited.contains(n)) {
				minHeap.add(n);
				visited.add(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
			if (n.get(2) < c.length && !visited.contains(n)) {
				minHeap.add(n);
				visited.add(n);
			}
			k --;
		}
		int[] result = new int[3];
		result[0] = a[cur.get(0)];
		result[1] = a[cur.get(1)];
		result[2] = a[cur.get(2)];
		
		return result;
	}

	public static long distance(List<Integer> point, int[] a, int[] b, int[] c) {
		long dist = 0;
		dist += a[point.get(0)] * a[point.get(0)];
		dist += b[point.get(1)] * b[point.get(1)];
		dist += c[point.get(2)] * c[point.get(2)];		
		return dist;
	}
	
	/*
	 * Q12
	 * Disjoint White Objects
	 * 
	 */
}

