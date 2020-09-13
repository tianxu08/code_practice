package small_yan;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Class6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test6_2();
		test6_4();
	}
	
	/*
	 * task1
	 * In an int array A, find the minimum distance of two values a and b, distance = |a’s index ­- b’s index|.
	 * 
	 * Example:
	 * A = {​1,​2, ​3,​4, ​3,​​1,​5, 6, ​1}​
	 * a = 1, b = 3
	 * the distance between 1 and 3 = 1(pick the 3 at index 4 and the 1 at index 5).
	 * 
	 * 
	 */
	
	public static void test1() {
		int[] array = {1,2,3,4,3,1,5,6,1};
		int a = 1, b = 3;
		System.out.println(task1_min_distance(array, a, b));
	}
	
	public static int task1_min_distance(int[] array, int a , int b) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int aIndex = -1, bIndex = -1;
		int minDistance = Integer.MAX_VALUE;
		for(int i = 0; i < array.length; i ++) {
			if (array[i] == a) {
				aIndex = i;
				if (bIndex != -1) {
					minDistance = Math.min(minDistance, Math.abs(bIndex - aIndex));
				}
			}
			if (array[i] == b) {
				bIndex = 9;
				if (aIndex != -1) {
					minDistance = Math.min(minDistance, Math.abs(bIndex - aIndex));
				}
			}
		}
		return minDistance;
	}
	
	
	/*
	 * task2
	 * Merge Sorted Array A & B(A has enough space at the end)
	 */
	
	/*
	 * task3
	 * Sorted array A, int k,Output: All pairs of indices (i, j) such that
	 * 1). A[j] ­- A[i] = k
	 * 2). A[j] + A[i] = k
	 * 
	 * unsorted array: HashMap<Integer, Integer>
	 * 
	 * sorted array:
	 * i, j = 0,
	 * if a[j] ­- a[i] > k: i++ else j++
	 */
	
	/*
	 * task4
	 * Difference between two sorted arrays, (what if not sorted)?
	 * A = [1, 2, 2, 7, 9] 
	 *      i
	 *      
	 * B = [2, 2, 5, 6, 7]
	 *      j
	 *      
	 * if A[i] < B[j] append A[i] to result, i ++;
	 * else if A[i] == B[j] while(A[i] == B[j]) i ++, j ++;
	 * else // A[i] > B[j], append B[j] to result, j ++;
	 * 
	 * if (i == A.length)  append B's remaining to result
	 * if (j == B.length) append A's remaining to result
	 * 
	 * 
	 * follow up
	 * what if unsorted? 
	 * 
	 * (1) use a hashMap<Integer, Integer>  // <value, counter>. 
	 *     insert the smaller array into the hashMap.
	 * (2) sort the two array. 
	 *     do same with the above method
	 */
	
	/*
	 * task5
	 * From 2 arrays, pick one element from each of them, sum = k.
	 * A = {1, 3, 6, 10} 
	 * B = {2, 7, 8, 12} 
	 * k = 10
	 * 
	 * i = 0;
	 * j = B.length - 1;
	 * 
	 * while(i < A.length && j >= 0) 
	 *    if A[i] + B[j] > k
	 *       i ++;
	 *    else if A[i] + B[j] < k
	 *       j --
	 *    else // A[i] == B[j]
	 *       we get it.  
	 */
	
	
	/*
	 * task6
	 * Young's Matrix
	 * 
	 * The matrix sorted by each row, and sorted by each column:
	 *    3   5  8  12  15  19
	 *    8  10 13  17  20  21
	 *    9  11 14  18  25  30
	 *    13 15 18  22  26  32
	 *    15 16 19  28  30  36
	 *    20 21 22  30  32  40  
	 * 
	 * 1). if you start looking at the matrix from the top left corner, it is a heap.
	 * 2). if you start looking at the matrix from the top right corner, it is a binary search tree. 
	 * 3). it is natural to think of a divide & conquer solution for problems on Young’s Matrix.
	 * 
	 * A = {1,3,6}
	 * B = {2,7,8}
	 * M = 3*3 Young's matrix
	 * M[i][j] = A[i] + B[j]
	 * 
	 *   0  1  2
	 * 0 3  8  9
	 * 1 5 10  11
	 * 2 8 13  14
	 * 
	 * typical interview questions:
	 * (1) smallest/largest k elements
	 * (2) kth smallest/largest element
	 * (3) search for a value in Young's Matrix
	 * (4) two(sorted), what's the kth smallest sum if picking one element from each of the array. 
	 *  
	 */
	public static class Item6{
		public int x;
		public int y;
		public Item6(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	
	public static void test6_2() {
		final int[][] matrix = { 
				   {1,  3,   5,  7},
		  		   {2,  4,   8,   9},
		           {3,  5, 11, 15},
		           {6,  8, 13, 18} };
		int k = 3;
		int kth = task6_2_kthSmallestYoungMatrix(matrix, k);
		System.out.println("kth = " + kth);
	}
	public static int task6_2_kthSmallestYoungMatrix(final int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		boolean[][] visited = new boolean[rLen][cLen];
		Comparator<Item6> myComp  = new Comparator<Class6.Item6>() {
			
			@Override
			public int compare(Item6 o1, Item6 o2) {
				// TODO Auto-generated method stub
				return matrix[o1.x][o1.y] - matrix[o2.x][o2.y];
			}
		};
		PriorityQueue<Item6> minHeap = new PriorityQueue<Item6>(2*k, myComp);
		minHeap.add(new Item6(0, 0));
		visited[0][0] = true;
		
		while(k > 1) {
			Item6 cur = minHeap.poll();
			if (cur.x + 1 < rLen && !visited[cur.x + 1][cur.y]) {
				minHeap.offer(new Item6(cur.x + 1, cur.y));
				visited[cur.x + 1][cur.y]= true;
			}
			if (cur.y + 1 < cLen && !visited[cur.x][cur.y + 1]) {
				minHeap.offer(new Item6(cur.x, cur.y + 1));
				visited[cur.x][cur.y + 1] = true;
			}
			k --;
		}
		return matrix[minHeap.peek().x][minHeap.peek().y];
	}
	
	public static class Item61 {
		public int x;
		public int y;
		public int val;
		public Item61(int _x, int _y, int _val) {
			this.x = _x;
			this.y = _y;
			this.val = _val;
		}
	}
	
	
	public static void  test6_4() {
		int[] a = {1,3,6};
		int[] b = {2,7,8};
		int k = 2;
		int kth = task6_4_kthSmallest2arrays(a, b, k);
		System.out.println("kth = " + kth);
	}
	
	public static int task6_4_kthSmallest2arrays(int[] a, int[] b, int k) {
		int aLen = a.length;
		int bLen = b.length;
		boolean[][] visited = new boolean[aLen][bLen];
		
		Comparator<Item61> mycomp = new Comparator<Class6.Item61>() {

			@Override
			public int compare(Item61 o1, Item61 o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		PriorityQueue<Item61> minHeap = new PriorityQueue<Class6.Item61>(2*k, mycomp);
		minHeap.add(new Item61(0, 0, a[0] + b[0]));
		
		visited[0][0] = true;
		
		while (k > 1) {
			Item61 cur = minHeap.poll();
			if (cur.x + 1 < aLen && !visited[cur.x + 1][cur.y]) {
				minHeap.offer(new Item61(cur.x + 1, cur.y, a[cur.x + 1] + b[cur.y]));
				visited[cur.x + 1][cur.y] = true;
			}
			if (cur.y + 1 < bLen && !visited[cur.x][cur.y + 1]) {
				minHeap.offer(new Item61(cur.x, cur.y + 1, a[cur.x] + b[cur.y + 1]));
				visited[cur.x][cur.y + 1] = true;
			}
			k --;
		}
		return minHeap.peek().val;
	}
	
	/*
	 * task7
	 * From 3 arrays A, B, C, pick one element from each of them, sum = k.
	 * from 2 arrays --> 3 arrays
	 * for each element in C, find if there exist two elements, one from A and the other from B, their sum == k
	 * 
	 * C[i]  k - C[i]
	 */
	
	
	/*
	 * task8
	 * 3 arrays A, B, C, from each of the arrays pick one element, x from A, y from B, z from C, 
	 * what is the minimum |x­-y|+ |y­-z| + |z­-x|.
	 * 
	 * Item(value, type).  type: 1 => A, 2=>B, 3=>C
	 * merge
	 * find the consecutive element. if all the three elements are in different, calculate the  |x­-y|+ |y­-z| + |z­-x|.
	 * 
	 */
	public static class Item8 {
		public int val;
		public int type;
		public Item8(int _val, int _type) {
			this.val = _val;
			this.type = _type;
		}
		public int hashCode() {
			return this.val;
		};
	}
	public static int task8_min_distancd(int[] A, int[] B, int[] C) {
		if (A == null || A.length == 0 || B == null || B.length == 0 || C == null || C.length == 0) {
			return Integer.MAX_VALUE;
		}
		int ALen = A.length;
		int BLen = B.length;
		int CLen = C.length;
		Item8[] array = new Item8[ALen + BLen + CLen];
		int index = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < ALen; i ++) {
			array[index ++] = new Item8(A[i], 1);
		}
		for (int i = 0; i < BLen; i++) {
			array[index ++] = new Item8(B[i], 2);
		}
		for(int i = 0; i < CLen; i ++) {
			array[index ++] = new Item8(C[i], 3);
		}
		
		Comparator<Item8> myComp = new Comparator<Class6.Item8>() {

			@Override
			public int compare(Item8 o1, Item8 o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		
		Arrays.sort(array, myComp);  // n (log n)
		
		HashSet<Item8> myset = new HashSet<Class6.Item8>();
		
 		for(int i = 0; i < array.length; i ++) {
			Item8 cur = array[i];
			if (myset.contains(cur)) {
				
			}
		}
		return min;
	}
	
	
	/*
	 * task8
	 * 1 unsorted array, no duplicate values
	 * find 
	 * 1) number of all pairs
	 * 2) all pairs 
	 * that the sum of the two number is greater /smaller than a give number k
	 * 
	 *  (1) sort the array
	 *  (2) for each a[i], find the maximum j for i is x, then we knokw for i + 1, the maximum j is <=x
	 *  
	 *   
	 *  
	 */
	
	public static int task8_numberOfAllPairs(int[] array) {
		return -1;
	}
	
	
	
	
	

}
