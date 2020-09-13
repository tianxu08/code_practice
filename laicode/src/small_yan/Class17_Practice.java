package small_yan;

import java.util.ArrayList;

import ds.TreeNode;

public class Class17_Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2_2();
//		test5();
//		test5_2_1();
		test5_3();
	}
	
	/*
	 * task2.1
	 * largest subArray sum. Given an integer array, what is the largest sum of any of the subarrays?
	 */
	public static void test1() {
		int[] a = {1,2,-1,2,3,-1};
		int res = task2_1_sum(a);
		System.out.println(res);
	}
	
	public static int task2_1_sum(int[] a) {
		int lastMax = 0;
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i ++) {
			lastMax = Math.max(lastMax + a[i], a[i]);
			result = Math.max(result, lastMax);
		}
		return result;
	}
	
	/*
	 * task2.2
	 *  I​f we can make the array into a circle, what is the largest sum of subarray?
	 *  {1,2,-4,2,3,-1}  largest is 2 + 3 + -1 + 1 + 2 = 7
	 */
	public static void test2_2() {
		int[] a = {1,2,-4,2,3,-1};
		int res = task2_2_sum(a);
		System.out.println(res);
	}
	public static int task2_2_sum(int[] a) {
		int lastMax = 0;
		int lastMin = 0;
		int sum = 0;
		// maintian the resultMax and resultMin
		// after that, compare resultMax and sum - resultMin
		int resultMax = Integer.MIN_VALUE;
		int resultMin = Integer.MAX_VALUE;
		for(int i = 0; i < a.length; i ++) {
			lastMax = Math.max(lastMax + a[i], a[i]);
			lastMin = Math.min(lastMin + a[i], a[i]);
			sum += a[i];
			resultMax = Math.max(resultMax, lastMax);
			resultMin = Math.min(resultMin, lastMin);
		}
		return resultMax > sum - resultMin ? resultMax : sum - resultMin;
	}
	
	/*
	 * task2.3
	 * refer to practice Lec22 task2.3
	 * Maximum Path Sum Binary Tree III
	 * Given a binary tree in which each node contains an integer number. 
	 * Find the maximum possible path sum
	 * (both the starting and ending node should be on the same path from root to one of the leaf nodes, 
	 * and the path is allowed to contain only one node).
	 * 
	 * Examples
	 *   -5
	 *  /    \
	 *2      11	
	 *     /    \
	 *
	 *    6     14
	 * The maximum path sum is 11 + 14 = 25
	 * 
	 * 
	 * traverse the tree. when we met the leaf node, we calculate the max sum from root to this leaf node. 
	 * 
	 */
	public static int maxPathSum = Integer.MIN_VALUE;
	public static int task2_3_maxPathSum(TreeNode root) {
		return -1;
	}
	
	public static void helper() {
		
	}
	
	
	
	
	/*
	 * task2.4
	 * 
	 */
	
	/*
	 * task5
	 * Longest increasing subarray, return the length
	 * 
	 */
	public static void test5() {
		int[] array = {0, 1,5,3,2,8,9,7};
		int len = task5_LISubarray(array);
		System.out.println("len = " + len);
	}
	public static int task5_LISubarray(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int len = array.length;
		
		int longest = Integer.MIN_VALUE;
		int curLen = 1;
		for(int i = 1; i < len; i ++) {
			if (array[i] > array[i - 1]) {
				curLen ++;
				longest = Math.max(longest, curLen);
			} else {
				curLen = 1;
			}
		}
		return longest;
	}
	
	/*
	 * task5.2
	 * the edge are directed and there is no edge directed to parent. 
	 * 1 find all the paths from root to leaf node
	 * 2 find the longest increasing SubArray from all the paths
	 */
	public static void test5_2_1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		
		n2.left = n4;
		n2.right = n5;
		
		ArrayList<ArrayList<Integer>> result = task5_2_1_allPaths(n1);
		System.out.println(result);
		
		System.out.println("---------------------------------------");
		int rev2 = task5_2_2LISInPath(n1);
		System.out.println("rev2 = " + rev2);
		
	}
	
	public static ArrayList<ArrayList<Integer>> task5_2_1_allPaths(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		task5_2_1_helper(root, path, result);
		return result;
	}
	public static void task5_2_1_helper(TreeNode node, ArrayList<Integer> path, 
			ArrayList<ArrayList<Integer>> result) {
		if (node == null) {
			return ;
		}
		if (node.left == null && node.right == null) {
			path.add(node.val);
			result.add(new ArrayList<Integer>(path));
			path.remove(path.size() - 1);
			return ;
		}
		path.add(node.val);
		task5_2_1_helper(node.left, path, result);
		task5_2_1_helper(node.right, path, result);
		path.remove(path.size() - 1);
	}
	
	
	
	public static int task5_2_2LISInPath(TreeNode root) {
		int max = 0;
		int prev = Integer.MIN_VALUE;
		return task5_2_2_findPath(root, max, prev);
	}
	
	public static int task5_2_2_findPath(TreeNode node, int max, int prevVal) {
		int curLongest = 0;
		if (node == null) {
			return curLongest;
		}
		if (node.val > prevVal) {
			curLongest = max + 1;
		} else {
			curLongest = 1;
		}
		int leftLongest = task5_2_2_findPath(node.left, curLongest, node.val);
		int rigthLongest = task5_2_2_findPath(node.right, curLongest, node.val);
		return Math.max(curLongest, Math.max(leftLongest, rigthLongest));
	}
	
	/*
	 * task5.3
	 * Longest increasing path in matrix(from one cell can to to neighbors cells, 4 directions)
	 * 
	 */
	public static void test5_3() {
		int[][] matrix = {
				{2,3,4,1},
				{2,4,1,2},
				{1,3,3,4},
				{4,2,1,1}
		};
		int rev = task5_3_longestPathInMatrix(matrix);
		System.out.println("rev = " + rev);
	}
	
	
	public static int task5_3_longestPathInMatrix(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] longest = new int[rLen][cLen];
		for(int i = 0; i < rLen; i++) {
			for(int j = 0; j < cLen; j++) {
				task5_3_helper(matrix, i, j, longest);
			}
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++ ){
				max = Math.max(max, longest[i][j]);
			}
		}
		return max;
	}
	
	// int[][] longest: longest[i][j] longest path ending (i, j)
	public static int task5_3_helper(int[][] matrix, int indexX, int indexY, int[][] longest) {
		// already calculated
		if (longest[indexX][indexY] != 0) {
			return longest[indexX][indexY];
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		int curLongest = 1;
		int curVal = matrix[indexX][indexY];
		for(int i = 0; i < 4; i ++) {
			int nextX = indexX + dx[i];
			int nextY = indexY + dy[i];
			if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen) {
				if (curVal < matrix[nextX][nextY]) {
					curLongest = Math.max(curLongest, task5_3_helper(matrix, nextX, nextY, longest));
				}
			}
		}
		longest[indexX][indexY] = curLongest;
		return longest[indexX][indexY];
	}
	public static int[] dx = {0, 0, -1 , 1};
	public static int[] dy = {-1, 1, 0, 0};
	
	
	/*
	 * task6
	 * for each of the nodes in the max tree, the node itself is the largest one within all the nodes in his subtree. 
	 * no duplicate elements in the tree.
	 * 1). if we know the inorder traversal sequence, can we reconstruct the tree?
	 * 2). if so, given the inorder traversal, reconstruct the tree. what is the time complexity?
	 * O(n ^ 2).
	 * 3). suppose the inorder traversal sequence is not given you at once, 
	 * and it is a stream. The requirement is to at anytime, 
	 * I need to know what is the max tree reconstructed by the stream so far.
	 * 4).​What is the time complexity of constructing the tree in this way? O(n ^ 2) ?
	 * O(n) → read() amortized O(1)
	 */
	
	
	
	
	
	/*
	 * task7
	 * 
	 */
	

}
