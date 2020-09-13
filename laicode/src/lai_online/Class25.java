package lai_online;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import debug.Debug;



public class Class25 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test6();
//		test7();
		test8();
	}
	
	
	/*
	 * list:
	 * 
	 * task1: Kth Smallest With Only 2, 3 As Factors
	 * task2: Kth Smallest With Only 3, 5, 7 As Factors
	 * task3: Kth Closest Point to <0,0,0>
	 * task4: Place To Put The Chair I
	 * task5: Place To Put The Chair II
	 * task6: Max Water Trapped I 
	 * task7: Max Water Trapped II 
	 * task8: 一个字典有给一系列strings,要求找两个string,使得它们没有共同字符,并且长度乘 积 最大. 
	 * 		  (Assumption: alllettersinthewordisfrom‘a‐z’in ASCII)
	 * task9: Longest Common Substring
	 * task10: Longest Common Subsequence
	 * 
	 */
	/*
	 * task1
	 * Kth Smallest With Only 2, 3 As Factors
	 * Fair Data Structure
	 * Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
	 * Assumptions
	 * K >= 1
	 * Examples
	 * the smallest is 1
	 * the 2nd smallest is 2
	 * the 3rd smallest is 3
	 * the 5th smallest is 2 * 3 = 6
	 * the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
	 */
	public static int task1_kthSmallest2_3_factors(int k) {
		if (k < 1) {
			return 0;
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		minHeap.add(1);
		visited.add(1);
		while (k > 0) {
			int current = minHeap.poll();
			if (!visited.contains(current*2)) {
				visited.add(current * 2);
				minHeap.add(current *2);
			} 
			if (!visited.contains(current * 3)) {
				visited.add(current * 3);
				minHeap.add(current * 3);
			}
			k --;
		}
		return minHeap.peek();
	}
	
	/*
	 * task2
	 * Kth Smallest With Only 3, 5, 7 As Factors
	 * Fair
	 * Data Structure
	 * Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.
	 * Assumptions
	 * K >= 1
	 * Examples
	 * the smallest is 3 * 5 * 7 = 105
	 * the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
	 * the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
	 * the 5th smallest is 3 ^ 3 * 5 * 7 = 945
	 * 
	 * initial state: 3*5*7
	 * generate rule: <i, j, k>
	 *                <i + 1, j, k>
	 *                <i, j + 1, k>
	 *                <i, j , k + 1>
	 */
	public static int task2_kthSmallest3_5_7_Factors(int k) {
		if (k < 1) {
			return -1;
		}
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		
		visited.add(3*5*7);
		minHeap.add(3*5*7);
		
		while(k > 0) {
			int cur = minHeap.poll();
			if (!visited.contains(cur*3)) {
				minHeap.add(cur * 3);
				visited.add(cur * 3);
			}
			if (!visited.contains(cur * 5)) {
				minHeap.add(cur* 5);
				visited.add(cur*5);
			}
			if (!visited.contains(cur*7)) {
				minHeap.add(cur*7);
				visited.add(cur*7);
			}
			k --;
		}
		return minHeap.peek();	
	}
	
	
	/*
	 * task3
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
	public static int[] task3_closest(final int[] a, final int[] b, final int[] c, int k) {
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
	 * task4
	 * Place To Put The Chair I Hard Graph
	 * Given a gym with k pieces of equipment and some obstacles.  
	 * We bought a chair and wanted to put this chair into the gym such that  
	 * the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. 
	 * The gym is represented by a char matrix, 
	 * ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 
	 * ' ' denotes a cell without any equipment or obstacle. 
	 * You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. 
	 * The cost of moving from one cell to its neighbor is 1. 
	 * You can not put the chair on a cell with equipment or obstacle.
	 * Assumptions
	 * There is at least one equipment in the gym
	 * The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
	 * If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
	 * Examples
	 * { { 'E', 'O', ' ' },
	 * {  ' ', 'E',  ' ' },
	 * {  ' ',  ' ',  ' ' } }
	 * we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipments is 1 + 1 = 2, 
	 * which is minimal.
	 */
	
	
	
	
	/*
	 * task5 
	 * Place To Put The Chair II 
	 * Fair Data Structure Given a gym with k
	 * pieces of equipment without any obstacles. Let’s say we bought a chair
	 * and wanted to put this chair into the gym such that the sum of the
	 * shortest path cost from the chair to the k pieces of equipment is
	 * minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with
	 * equipment, ' ' denotes a cell without equipment. The cost of moving from
	 * one cell to its neighbor(left, right, up, down) is 1. You can put chair
	 * on any cell in the gym.
	 * 
	 * Assumptions
	 * 
	 * There is at least one equipment in the gym The given gym is represented
	 * by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed
	 * to be not null ​Examples
	 * 
	 * { 
	 * { 'E', ' ', ' ' },
	 * 
	 * { ' ', 'E', ' ' },
	 * 
	 * { ' ', ' ', 'E' } 
	 * }
	 * 
	 * we should put the chair at (1, 1), so that the sum of cost from the chair
	 * to the two equipments is 2 + 0 + 2 = 4, which is minimal.
	 */
	
	/*
	 * task6 
	 * Max Water Trapped I
	 * Fair Data Structure
	 * Given a non-negative
	 * integer array representing the heights of a list of adjacent bars.
	 * Suppose each bar has a width of 1. Find the largest amount of water that
	 * can be trapped in the histogram.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * { 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at
	 * index 1, 1 unit of water can be trapped and index 3, 1 unit of water can
	 * be trapped)
	 */
	public static void test6() {
		int[] array = {2, 1, 3, 2, 4};
		int maxWater1 = task6_maxWaterI(array);
		System.out.println("maxWater = " + maxWater1);
		System.out.println(" --------------------- ");
		int maxWater2 = task6_maxWater1(array);
		System.out.println("maxWater2 = " + maxWater2);
	}
	
	public static int task6_maxWater1(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int[] leftMax = new int[n];  // leftMax[i] is the max height on the leftside of i, including i
		int[] rightMax = new int[n]; // rightMax[i] ...
		// fill in leftMax
		for(int i = 0; i < n; i ++) {
			if (i == 0) {
				leftMax[i] = a[i];
			} else {
				leftMax[i] = Math.max(leftMax[i - 1], a[i]);
			}
		}
		// fill in rigthMax
		for(int j = n - 1; j >= 0; j --) {
			if (j == n - 1) {
				rightMax[j] = a[j];
			} else {
				rightMax[j] = Math.max(rightMax[j + 1], a[j]);
			}
		}
		
		// traverse the array and get maxWater
		int maxWater = 0;
		for(int i = 0; i < n; i ++) {
			maxWater += (Math.min(leftMax[i], rightMax[i]) - a[i]);
		}
		return maxWater;
		
	}
	
	
	/**
	 * 
	 * @param array
	 * @return
	 * leftMax is the max height in the leftSide
	 * rightMax is the max height in the rightSide
	 * 
	 * if leftMax < rightMax
	 * 		maxWater = leftMax - A[i]
	 * 		i ++
	 * else  // leftMax > rightMax
	 * 		maxwater = rightMax - A[j]
	 * 		j --;
	 * 
	 * prove: 
	 * case1
	 * if leftMax < rightMax, it's OK to move to i + 1
	 * case 1.1
	 * 	  if A[i + 1] < leftMax  leftMax is still valid. water above A[i + 1] is leftMax - A[i + 1]
	 * case 1.2 
	 * 	  if A[i + 1] > leftMax  leftMax is NOT valid, we need to update it to A[i + 1]
	 * 	  water above A[i + 1] is leftMax - A[i + 1] = A[i + 1] - A[i + 1] = 0
	 * Therefore, it's OK to move to i + 1 if leftMax < rightMax
	 */
	public static int task6_maxWaterI(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int leftMax = 0, rightMax = 0;
		int i = 0, j = array.length - 1;
		int maxWater = 0;
		while(i <= j) {
			// update leftMax if needed
			leftMax = Math.max(array[i], leftMax);
			// update rightMax if needed
			rightMax = Math.max(array[j], rightMax);
			if (leftMax < rightMax) {
				maxWater += leftMax - array[i];
				i ++;
			} else {
				maxWater += rightMax - array[j];
				j --;
			}
		}
		return maxWater;
	}
	
	
	
	/*
	 * task7 
	 * Max Water Trapped II Hard Data Structure Given a non-negative
	 * integer 2D array representing the heights of bars in a matrix. Suppose
	 * each bar has length and width of 1. Find the largest amount of water that
	 * can be trapped in the matrix. The water can flow into a neighboring bar
	 * if the neighboring bar's height is smaller than the water's height. Each
	 * bar has 4 neighboring bars to the left, right, up and down side.
	 * 
	 * Assumptions
	 * 
	 * The given matrix is not null and has size of M * N, where M > 0 and N > 0
	 * Examples
	 * 
	 * { 
	 * { 2, 3, 4, 2 },
	 * 
	 * { 3, 1, 2, 3 },
	 * 
	 * { 4, 3, 5, 4 } 
	 * }
	 * 
	 * the amount of water can be trapped is 3,
	 * 
	 * at position (1, 1) there is 2 units of water trapped,
	 * 
	 * at position (1, 2) there is 1 unit of water trapped.
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
	
	public static void test7() {
//		int[][] matrix = { 
//				{ 2, 3, 4, 2 },
//				{ 3, 1, 2, 3 },
//				{ 4, 3, 5, 4 }
//		};
		int[][]matrix = {
				{5, 4,  12, 5,  8},
				{7, 4,  3,  7,  12},
				{5, 1,  1,  2,  6},
				{5, 4,  2,  5,  3},
				{9, 10, 15, 20,11}
		}; 
		int sum = task7_maxTrapped(matrix);
		System.out.println("sum = " + sum);
	}
	
	public static int task7_maxTrapped(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new  boolean[rLen][cLen];
		int[][] level = new int[rLen][cLen];
		
		// initialize the level matrix
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					// the borders by matrix[i][j]
					level[i][j] = matrix[i][j];
				} else {
					// others, Integer.max
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
		
		PriorityQueue<Coordinate> minHeap = new PriorityQueue<Coordinate>(rLen * cLen , myComp);
		
		// visit the border of the matrix
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (i == 0 || j == 0 || i == rLen - 1 || j == cLen - 1) {
					Coordinate point = new Coordinate(i, j, matrix[i][j], level[i][j]);
					// put the point into priority queue
					minHeap.add(point);
					visited[i][j] = true;
				}
			}
		}
		int sum = 0;
		
		while(!minHeap.isEmpty()) {
			Coordinate cur = minHeap.poll();
			sum += cur.level - cur.height;
			System.out.print  ("expand: ");
			System.out.println("x = " + cur.x + " y = " + cur.y + " height= " + cur.height + " level = " + cur.level);
			List<Coordinate> neighbors = task7_getNeighbor(matrix, cur, visited, level);
			for(Coordinate neighbor : neighbors) {
				
				if (visited[neighbor.x][neighbor.y] == false) {
					// neighbor's level. 
				
					int newLevel = Math.max(neighbor.height, 
							        Math.min(level[neighbor.x][neighbor.y], level[cur.x][cur.y]));
					if (level[neighbor.x][neighbor.y] != newLevel) {
						// update in neighbor's. level
						neighbor.level = newLevel;
						level[neighbor.x][neighbor.y] = newLevel;
						minHeap.add(neighbor);
						visited[neighbor.x][neighbor.y] = true;
					}
				}
			}
			System.out.println("---print level matrix ----");
			Debug.printMatrix(level);
			System.out.println("---finish print ----------");
		}
		Debug.printMatrix(level);
		return sum;
	}
	
	/*
	 * Case1: if green cell’s height is lower than 5, then its water level== 5.
	 * Case2:  else, green cell’s water level = max(green’s cell physical height,  5)
	 * For every point Pi <xi yi zi> on the border, set the water level to <zi>
	 * For every point Pj not on the border set the water level to infinity
	 * Insert all Pi into MIN_HEAP (= set of active points)
	 * 
	 * 
	 * While MIN_HEAP is not empty:
	 * P = MIN_HEAP.pop()  // Select the active point P with minimum level
	 * SUM +=  Level(P) - Height(P);
	 * For every point Q adjacent to P:
	 * Level(Q) = max(Height(Q), min(Level(Q), Level(P)))
	 * If Level(Q) was changed:
	 * Add Q to the MIN_HEAP if it is not there yet.
	 * BFS2
	 */
	
	public static List<Coordinate> task7_getNeighbor(int[][] matrix, Coordinate coordinate, boolean[][] visited, int[][] level) {
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
	 * task8 
	 * Largest Container 
	 * Fair Data Structure Given an array of non-negative integers, 
	 * each of them representing the height of a board
	 * perpendicular to the horizontal line, the distance between any two
	 * adjacent boards is 1. 
	 * Consider selecting two boards such that together
	 * with the horizontal line they form a container. Find the volume of the
	 * largest such container.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and has size of at least 2 Examples
	 * 
	 * { 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards
	 * of height 2, the volume of the container is 2 * 4 = 8.
	 * 
	 *       --
	 *  --         --
	 *     --   --    --
	 */
	
	
	public static void test8() {
		int[] a = {2, 1, 3, 1, 2, 1};
		int largestContainer = task8_largetContainer(a);
		System.out.println("largestContainer = " + largestContainer);
	}
	
	public static int task8_largetContainer(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int i = 0, j = n - 1;
		
		int maxContainer = 0;
		while(i < j) {
			int curContainer = Math.min(a[i], a[j]) * (j - i);
			
			maxContainer = Math.max(maxContainer, curContainer);
			if (a[i] < a[j]) {
				i ++;
			} else {
				j --;
			}
		}
		return maxContainer;
	}
	
	
	
	
	/*
	 * task9 
	 * ReOrder Array Hard Recursion 
	 * Given an array of elements, reorder it
	 * as follow:
	 * 
	 * { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
	 * 
	 * { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }
	 * 
	 * Try to do it in place.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
	 * 
	 * { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
	 * 
	 * { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
	 */
	
	
	
	/*
	 * task10 
	 * Largest Product Of Length Hard String 
	 * Given a dictionary
	 * containing many words, find the largest product of two words’ lengths,
	 * such that the two words do not share any common characters.
	 * 
	 * Assumptions
	 * 
	 * The words only contains characters of 'a' to 'z' The dictionary is not
	 * null and does not contains null string, and has at least two strings If
	 * there is no such pair of words, just return 0 Examples
	 * 
	 * dictionary = [“abcde”, “abcd”, “ade”, “xy”], the largest product is 5 * 2
	 * = 10 (by choosing “abcde” and “xy”)
	 */
	
	
	
	
	

}
