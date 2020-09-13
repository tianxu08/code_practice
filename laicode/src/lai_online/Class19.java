package lai_online;

import java.util.*;

import debug.Debug;
import ds.TreeNode;
import ds.TreeNodeP;
public class Class19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test4();
//		test7();
//		test8();
//		test9();
//		test10();
//		test11();
//		 test13() ;
	}
	
	
	/*
	 * Array Deduplication I
	 * Easy
	 * Data Structure
	 * Given a sorted integer array, remove duplicate elements. 
	 * For each group of elements with the same value keep only one of them. Do this in-place, 
	 * using the left side of the original array and maintain the relative order of the elements of the array. 
	 * Return the array after deduplication.
	 * sorted
	 * 
	 * 1 2 3 4 5 5 5 5 5 5 5 5 5  X X X X 8 9
	 *       s                    f
	 * 
	 * s = 0, f = 1
	 * for(f = 1; f < a.length; f ++) {
	 *    if(a[f] != a[s]) {
	 *       a[++s] = a[f];
	 *    }
	 * }
	 * return s + 1;
	 * 
	 * 
	 */
	public static void test1() {
		int[] a = {1,2,2,3,3,3,4,5,5,6};
		int[] rev = task1_dedup1(a);
		for(int i = 0; i < rev.length; i ++) {
			System.out.print(rev[i] + " ");
		}
		System.out.println();
	}
	
	public static int[] task1_dedup1(int[] a){
		if (a == null || a.length == 0) {
			return a;
		}
		int s = 0, f = 1;
		for(; f < a.length; f++) {
			if (a[f] != a[s]) {
				++s;
				a[s] = a[f];
			}
		}
		return Arrays.copyOf(a, s + 1);
	}
	
	
	/*
	 * Array Deduplication II
	 * Fair Data Structure
	 * Given a sorted integer array, remove duplicate elements. 
	 * For each group of elements with the same value keep at most two of them. Do this in-place, 
	 * using the left side of the original array and maintain the relative order of the elements of the array. 
	 * Return the array after deduplication.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
	 * 
	 * sorted
	 * 
	 * 1 2 2 3 3 3 
	 * 
	 * 
	 * 
	 */
	public static void test2() {
		int[] a = {1,2,2,3,3,3,4,4,4};
		int[] rev = task2_dedup2(a);
		for(int i = 0; i < rev.length; i ++) {
			System.out.print(rev[i] + " ");
		}
		System.out.println();
	}
	public static int[] task2_dedup2(int[] a) {
		if (a == null || a.length <= 2) {
			return a;
		}
		int s = 2, f = 2;
		for(f = 2; f < a.length; f ++) {
			if (a[f] != a[s - 2]) {
				a[s] = a[f];
				s ++;
			}
		}
		return Arrays.copyOf(a, s);
	}
	
	public int[] task2_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 2) {
			return array;
		}
		int start = 0;
		// for(int i = 2; i < array.length; i ++) {
		// if (array[start - 2] != array[i]) {
		// array[start] = array[i];
		// start ++;
		// }
		// }
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[start]) {
				array[++start] = array[i];
				count = 1;
			} else {
				if (count < 2) {
					array[++start] = array[i];
					count++;
				}
			}
		}
		return Arrays.copyOf(array, start + 1);
	}
	
	/*
	 * Array Deduplication III
	 * Fair Data Structure
	 * Given a sorted integer array, remove duplicate elements. 
	 * For each group of elements with the same value do not keep any of them. 
	 * Do this in-place, 
	 * using the left side of the original array and and maintain the relative order of the elements of the array. 
	 * Return the array after deduplication.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {1, 2, 2, 3, 3, 3} → {1}
	 * 
	 * 
	 */
	public static void test3() {
		int[] a = {1,2,2,3,3,3};
		int[] rev = task3_dedup3(a);
		for(int i = 0; i < rev.length; i ++) {
			System.out.print(rev[i] + " ");
		}
		System.out.println();
	}
	
	public static int[] task3_dedup3(int[] a) {
		if (a == null || a.length <= 1) {
			return a;
		}
		int s = 0;
		boolean flag = false;
		for(int f = 1; f < a.length; f ++) {
			// case1
			if (a[f] == a[s]) {
				flag = true;
			} else {
				if (flag == false) {
					// the candidates doesn't have duplicate
					// we put the next candidate in next position and set flag == false
					// case2: a[f] != a[s]
					// a[s] doesn't have duplicate
					++s;
					a[s] = a[f];
				} else {
					// flag == true
					// the candidates has duplicates.
					// it can no longer put here. put another candidate, and set
					// flag == false
					a[s] = a[f];
					flag = false;
				}
			}
		}
		return Arrays.copyOf(a, s);
	}
	
	
	/*
	 * Array Deduplication IV
	 * Hard Data Structure
	 * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, 
	 * from left to right. For each group of elements with the same value do not keep any of them.
	 * Do this in-place, using the left side of the original array. Return the array after deduplication.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
	 */
	
	public static void test4() {
		int[] array = {1,1,2,3,4,5,5,4,4,4,3,2,5};
		int[] result = task4_dedup(array);
		for(Integer i: result) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	public static int[] task4_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 1) {
			return array;
		}
		LinkedList<Integer> st = new LinkedList<Integer>();
		st.offer(array[0]);
		int i = 1;
		while (i < array.length) {
			if (!st.isEmpty() && array[i] == st.peek()) {
				while (i < array.length && array[i] == st.peek()) {
					i++;
				}
				// pop out st.peek();
				st.poll();
			} else {
				st.push(array[i]);
				i++;
			}
		}
		System.out.println("st.size() == " + st.size());
		int[] result = new int[st.size()];
		for (int j = st.size() - 1; j >= 0; j--) {
			result[j] = st.poll();
		}
		System.out.println(result.length);
		
		System.out.println("-----------------");
		for(int k = 0; k < result.length; k++) {
			System.out.print(result[k] + " ");
		}
		System.out.println();
		System.out.println("-----------------");
		return result;
	}
	
	
	
	/*
	 * Q2(Array number comparisions)
	 * task5
	 * Largest And Smallest
	 * Fair Data Structure
	 * Use the least number of comparisons to get the largest and smallest number in the given integer array.
	 * Return the largest number and the smallest number.
	 * Assumptions
	 * The given array is not null and has length of at least 1
	 * Examples
	 * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1.
	 * 
	 * 
	 * (1) sort and return A[0] and A[n-1]
	 * (2) use 1 loop to find the min and max  2*n comparition
	 *      for 0..n-1
	 *      if (min > a[i]) min = a[i]
	 *      if (max < a[i]) max = a[i] 
	 * (3) 
	 */
	
	
	public int[] task5_largestAndSmallest(int[] array) {
	    // write your solution here
	    return new int[]{0, 0};
	}
	
	/*
	 * task6
	 * Largest And Second Largest
	 * Hard Data Structure
	 * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. 
	 * Return the largest number and 2nd largest number.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * Examples
	 * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
	 */
	
	public int[] task6_largestAndSecond(int[] array) {
		// write your solution here
		// return a Pair object, the field first is the largest
		if (array == null || array.length == 0) {
			return null;
		}
		List<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < array.length; i++) {
			// pair<index, value>
			list.add(new Pair(i, array[i]));
		}
		//
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while (list.size() > 1) {
			List<Pair> nextRound = new ArrayList<Pair>();
			for (int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);

					if (p1.second <= p2.second) {
						// p2 win, add p2 to the nextRound
						nextRound.add(p2);
						// put p1 into the list of p2 beats
						// if this
						if (!map.containsKey(p2.first)) {
							map.put(p2.first, new ArrayList<Integer>());
						}
						// add p2's smaller to p2's smaller list
						map.get(p2.first).add(p1.second);
					} else {
						// p1 win
						// put p1 into nextRound
						nextRound.add(p1);
						// put p2 into the list of p1 beats
						if (!map.containsKey(p1.first)) {
							map.put(p1.first, new ArrayList<Integer>());
						}
						// add p1's smaller to p1's smaller list
						map.get(p1.first).add(p2.second);
					}
				} else {
					// add the last pair into nextRound if list.size() is odd
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;
		}
		int secondMax = max(map.get(list.get(0).first));
		// System.out.println("secondMax = " + secondMax);
		return new int[] { list.get(0).second, secondMax };
	}

	public int max(List<Integer> list) {
		int max = list.get(0);
		for (Integer i : list) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public class Pair {
		public int first;
		public int second;

		public Pair(int x, int y) {
			this.first = x;
			this.second = y;
		}
	}
		

	/*
	 * task7
	 * Spiral Order Traverse I
	 * Fair Data Structure
	 * Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. 
	 * Return the list of traversal sequence.
	 * Assumptions
	 * The 2D array is not null and has size of N * N where N >= 0
	 * Examples
	 * { 
	 * {1,  2,  3},
	 * {4,  5,  6},
	 * {7,  8,  9} 
	 * }
	 * the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
	 */
	public static void test7() {
		int[][] matrix = {
				{1} 
				};
		task7_SpiralOrderTraversal(matrix);
	}
	public static void task7_SpiralOrderTraversal(int[][] matrix) {
		int n = matrix.length;
		int leftB = 0, rightB = n-1;
		int upB = 0, downB = n-1;
		while (leftB <= rightB && upB <= downB) {
			for(int i = leftB; i <= rightB; i ++) {
				System.out.print(matrix[upB][i]);
			}
			upB++;
			for(int j = upB; j <= downB; j ++ ) {
				System.out.print(matrix[j][rightB]);
			}
			rightB --;
			for(int i = rightB; i >= leftB; i --) {
				System.out.print(matrix[i][downB]);
			}
			downB --;
			for(int j = downB; j >=upB; j --) {
				System.out.print(matrix[j][leftB]);
			}
			leftB ++;
		}
	}
	
	/*
	 * Spiral Order Traverse II
	 * Fair Data Structure
	 * Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. 
	 * Return the list of traversal sequence.
	 * Assumptions
	 * The 2D array is not null and has size of M * N where M, N >= 0
	 * Examples
	 * { 
	 *    {1,  2,  3,  4},
	 *    {5,  6,  7,  8},
	 *    {9, 10, 11, 12} 
	 * }
	 * the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
	 */
	public static void test8() {
		int[][] matrix = { 
				{ 1, 2, 3, 4 },
				{ 5, 6, 7, 8 },
				{ 9, 10, 11, 12 } 
		};
		List<Integer> result = task8_spiral(matrix);
		System.out.println(result);
	}

	public static List<Integer> task8_spiral(int[][] matrix) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return result;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int leftB = 0, rightB = cLen - 1;
		int upperB = 0, lowerB = rLen - 1;
		
		while(true) {
			for(int j = leftB; j <= rightB; j ++) {
				result.add(matrix[upperB][j]);
			}
			upperB ++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for(int i = upperB; i <= lowerB; i ++) {
				result.add(matrix[i][rightB]);
			}
			rightB --;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for(int j = rightB; j >= leftB; j --) {
				result.add(matrix[lowerB][j]);
			}
			lowerB --;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for(int i = lowerB; i >= upperB; i --) {
				result.add(matrix[i][leftB]);
			}
			leftB ++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
		}
		return result;
	}
	
	/*
	 * Spiral Order Generate I 
	 * Fair Data Structure
	 * Generate an N * N 2D array in spiral order clock-wise starting from the top left corner, 
	 * using the numbers of 1, 2, 3, …, N * N in increasing order.
	 * Assumptions
	 * N >= 0
	 * Examples
	 * N = 3, the generated matrix is
	 * { {1,  2,  3}
	 *   {8,  9,  4},
	 *   {7,  6,  5} }
	 */
	public static void test9() {
		int n = 3;
		int[][] matrix = task9_spiralGenerate(n);
		Debug.printMatrix(matrix);
	}
	
	 public static int[][] task9_spiralGenerate(int n) {
	    // write your solution here
		if (n <= 0) {
			return new int[][] {};
		}
		int rLen = n;
		int cLen = n;
		int[][] matrix = new int[n][n];
		int leftB = 0, rightB = cLen - 1;
		int upperB = 0, lowerB = rLen - 1;
		int counter = 1;
		while (true) {
			for (int j = leftB; j <= rightB; j++) {
				matrix[upperB][j] = counter++;
			}
			upperB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = upperB; i <= lowerB; i++) {
				matrix[i][rightB] = counter++;
			}
			rightB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int j = rightB; j >= leftB; j--) {
				matrix[lowerB][j] = counter++;
			}
			lowerB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = lowerB; i >= upperB; i--) {
				matrix[i][leftB] = counter++;
			}
			leftB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
		}
		return matrix;
	 }
	 
	 /*
	  * Spiral Order Generate II
	  * Fair Data Structure
	  * Generate an M * N 2D array in spiral order clock-wise starting from the top left corner, 
	  * using the numbers of 1, 2, 3, …, M * N in increasing order.
	  * Assumptions
	  * M >= 0, N >= 0
	  * Examples
	  * M = 3, N = 4, the generated matrix is
	  * { 
	  * 	{1,  2,  3,  4}
	  * 	{10, 11, 12, 5},
	  * 	{9,  8,  7,  6} 
	  * }
	  */
	public static void test10() {
		int m = 0, n = 1;
		int[][] matrix = task10_spiralGenerate(m, n);
		System.out.println(matrix.length);
		Debug.printMatrix(matrix);
	}
	 
	public static int[][] task10_spiralGenerate(int m, int n) {
		// write your solution here
		if (m <= 0) {
			// !!! only m <= 0, return a empty array
			return new int[][] {};
		}
		int rLen = m;
		int cLen = n;
		int[][] matrix = new int[m][n];
		int leftB = 0, rightB = cLen - 1;
		int upperB = 0, lowerB = rLen - 1;
		int counter = 1;
		while (true) {
			for (int j = leftB; j <= rightB; j++) {
				matrix[upperB][j] = counter++;
			}
			upperB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = upperB; i <= lowerB; i++) {
				matrix[i][rightB] = counter++;
			}
			rightB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int j = rightB; j >= leftB; j--) {
				matrix[lowerB][j] = counter++;
			}
			lowerB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = lowerB; i >= upperB; i--) {
				matrix[i][leftB] = counter++;
			}
			leftB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
		}
		return matrix;
	}
	
	/*
	 * task11
	 * Rotate Matrix
	 * Fair Data Structure
	 * Rotate an N * N matrix clockwise 90 degrees.
	 * Assumptions
	 * The matrix is not null and N >= 0
	 * Examples
	 * { 
	 * {1,  2,  3}
	 * {8,  9,  4},
	 * {7,  6,  5} 
	 * }
	 * after rotation is
	 * { 
	 * {7,  8,  1}
	 * {6,  9,  2},
	 * {5,  4,  3} }
	 * 
	 * { 
	 * {1,  2,  3}
	 * {8,  9,  4},
	 * {7,  6,  5} 
	 * }
	 * 
	 * 7 6 5
	 * 8 9 4
	 * 1 2 3
	 * 
	 */
	public static void test11() {
		int[][] matrix = {
				{1,2},
				{4,5}
		};
		Debug.printMatrix(matrix);
		System.out.println("----------");
		task11_rotate(matrix);
		Debug.printMatrix(matrix);
	}
	public static void task11_rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return ;
		}
		int n = matrix.length;
		int midRow = (n-1)/2;
		// swap by (n-1)/2
		for(int i = 0; i <= midRow; i ++) {
			int sym_i = (n-1) - i;
			for(int j = 0; j < n; j ++) {
				swap(matrix, i, j, sym_i, j);
			}
		}
		// swap by diag
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j <i; j ++) {
				swap(matrix, i, j, j, i);
			}
		}
	}
	
	public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
		int temp = matrix[x1][y1];
		matrix[x1][y1] = matrix[x2][y2];
		matrix[x2][y2] = temp;
	}
	
	
	/*
	 * Lowest Common Ancestor I
	 * Fair Recursion
	 * Given two nodes in a binary tree, find their lowest common ancestor.
	 * Assumptions
	 * There is no parent pointer for the nodes in the binary tree
	 * The given two nodes are guaranteed to be in the binary tree
	 * Examples

        	5

      	  /   \

        9     12

       /  \      \

      2    3      14

	The lowest common ancestor of 2 and 14 is 5

	The lowest common ancestor of 2 and 9 is 9
	 */
	
	public static TreeNode task12_LCA(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null) {
			return null;
		}
		if (root == one) {
			return root;
		}
		if (root == two) {
			return root;
		}
		TreeNode left = task12_LCA(root.left, one, two);
		TreeNode right = task12_LCA(root.right, one, two);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}
	
	/*
	 * Lowest Common Ancestor II
	 * Fair Data Structure
	 * Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
	 * Assumptions
	 * There is parent pointer for the nodes in the binary tree
	 * The given two nodes are not guaranteed to be in the binary tree
	 */
	public static void test13() {
		TreeNodeP n1 =new TreeNodeP(1);
		TreeNodeP n2 =new TreeNodeP(2);
		TreeNodeP n3 =new TreeNodeP(3);
		TreeNodeP n4 =new TreeNodeP(4);
		TreeNodeP n5 =new TreeNodeP(5);
		TreeNodeP n6 =new TreeNodeP(6);
		TreeNodeP n7 =new TreeNodeP(7);
		TreeNodeP n8 =new TreeNodeP(8);
		
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		n3.left = n6; 
		n3.right = n7;
		
		n4.left = n8;
		
		n2.parent = n1;
		n3.parent = n1;
		
		n4.parent = n2;
		n5.parent = n2;
		
		n6.parent = n3;
		n7.parent = n3;
		
		n8.parent = n4;
		
		TreeNodeP n9 = new TreeNodeP(9);
		TreeNodeP lca = task13_LCA(n1, n7);
		if (lca != null) {
			System.out.println("lca.value = " + lca.val);
		} else {
			System.out.println("LCA is null");
		}
		
	}
	
	public static TreeNodeP task13_LCA(TreeNodeP one, TreeNodeP two) {
		if (one == null || two == null) {
			return null;
		}
		TreeNodeP ptr1 = one;
		TreeNodeP ptr2 = two;
		while (ptr1 != null && ptr1.parent != null) {
			ptr1 = ptr1.parent;
		}
		while (ptr2 != null && ptr2.parent != null) {
			ptr2 = ptr2.parent;
		}

		if (ptr1 != ptr2) {
			// they are not in the same tree.
			return null;
		} else {
			// they are in the same tree.
			int l1 = length(one);
			int l2 = length(two);

			if (l1 > l2) {
				return LCA(one, two, l1 - l2);
			} else {
				return LCA(two, one, l2 - l1);
			}
		}
	}

	public static TreeNodeP LCA(TreeNodeP large, TreeNodeP small, int diff) {
		while (diff > 0) {

			large = large.parent;
			diff--;
		}
		while (large != small) {

			large = large.parent;
			small = small.parent;

		}
		return large;
	}

	public static int length(TreeNodeP node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.parent;
		}
		return length;
	}
	
	/*
	 * task14
	 * Lowest Common Ancestor III Hard
	 * Recursion
	 * Given two nodes in a binary tree, find their lowest common ancestor
	 *  (the given two nodes are not guaranteed to be in the binary tree).
	 * Return null If any of the nodes is not in the tree.
	 * Assumptions
	 * There is no parent pointer for the nodes in the binary tree
	 * The given two nodes are not guaranteed to be in the binary tree
	 * Examples
        	5
      	  /   \
     	 9     12
   	    /  \      \
  	   2    3      14
		The lowest common ancestor of 2 and 14 is 5

		The lowest common ancestor of 2 and 9 is 9

		The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
		
		
		Method:
		(1) check whether two nodes are in the tree
		(2) if yes, reduce to LCA
	 */
	
	public static TreeNode task14_LCA(TreeNode root, TreeNode one, TreeNode two) {
		if (root == null) {
			return null;
		}
		if (one == null || two == null) {
			return null;
		}
		Wrapper wrapper = new Wrapper(false, false);
		exist(root, one, two, wrapper);
		if (wrapper.firstExist == false || wrapper.secondExist == false) {
			return null;
		} else {
			// both nodes are in root. 
			return task12_LCA(root, one, two);
		}
	}
	public static class Wrapper{
		public boolean firstExist;
		public boolean secondExist;
		public Wrapper(boolean x, boolean y) {
			this.firstExist = x;
			this.secondExist = y;
		}
	}
	public static void exist(TreeNode root, TreeNode one, TreeNode two, Wrapper wrapper) {
		if (root == null) {
			return ;
		}
		if (root == one) {
			wrapper.firstExist = true;
		}
		if (root == two) {
			wrapper.secondExist = true;
		}
		exist(root.left, one, two, wrapper);
		exist(root.right, one, two, wrapper);
	}
	
	
	/*
	 * task15
	 * Lowest Common Ancestor IV
	 * Hard Recursion
	 * Given K nodes in a binary tree, find their lowest common ancestor.
	 * Assumptions
	 * K >= 2
	 * There is no parent pointer for the nodes in the binary tree
	 * The given two nodes are guaranteed to be in the binary tree
	 * Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

	The lowest common ancestor of 2, 3, 14 is 5

	The lowest common ancestor of 2, 3, 9 is 9
	 */
	
	public static TreeNode task15_lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
		// write your solution here
		if (root == null) {
			return null;
		}
		for(TreeNode n: nodes) {
			if (root == n) {
				return root;
			}
		}
		TreeNode left = task15_lowestCommonAncestor(root.left, nodes);
		TreeNode right = task15_lowestCommonAncestor(root.right, nodes);
		
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	

}
