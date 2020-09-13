package lai_online;
import ds.*;

import java.util.*;


public class Class20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test6();
		test3();
	}
	
	/*
	 * task1: Minimum Cuts For Palindromes
	 * task2: Binary Tree Path Sum To Target
	 * task3: Maximum Path Sum Binary Tree I 
	 * task4: Maximum Path Sum Binary Tree II 
	 * task5: Maximum Path Sum Binary Tree III
	 * task6: Binary Tree Diameter 
	 * 
	 */
	
	
	/*
	 * task1
	 * Minimum Cuts For Palindromes Fair DP
	 * Given a string, a partitioning of the string is a palindrome partitioning 
	 * if every substring of the partition is a palindrome. 
	 * Determine the fewest cuts needed for a palindrome partitioning of a given string.
	 * Assumptions
	 * The given string is not null
	 * Examples
	 * “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
	 * The minimum number of cuts needed is 3.
	 */
	
	
	/*
	 * task2 
	 * Binary Tree Path Sum To Target Fair Data Structure 
	 * Given a binary tree in which each node contains an integer number. Determine if there
	 * exists a path from any node to any node (the two nodes can be the same
	 * node and they can only be on the path from root to one of the leaf
	 * nodes), the sum of the numbers on the path is the given target number.
	 * Examples
	 * 
	 *   5
	 * /   \
	 * 2    11
	 *     /   \
	 *    6     14
	 * 
	 * If target = 17, There exists a path 11 + 6, the sum of the path is
	 * target,
	 * 
	 * If target = 10, There does not exist any paths sum of which is target.
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 *    1
	 *   / \
	 *  2   3
	 *     /
	 *    4
	 *    
	 * !!! the two nodes should be on the path from root to a leaf node
	 * So, we can use an array list to store the intermediate result when we do DFS.
	 * For example, 5 11 6
	 * every time, we calculate from the backward, when we get the sum, return true
	 * 
	 */
	
	public static class Result {
		public int val;
		public Result(int v) {
			this.val = v;
		}
	}
	
	public static boolean task2_exist(TreeNode root, int target) {
		if (root ==null) {
			return false;
		}
		Result result = new Result(0);
		ArrayList<Integer> path = new ArrayList<Integer>();
		task2_helper(root, target, path, result);
		return result.val == 1;
	}
	
	public static void task2_helper(TreeNode node, int target, ArrayList<Integer> path,Result result) {
		if (node == null) {
			return ;
		}
		int sum = 0;
		path.add(node.val);
		for(int i = path.size() - 1; i >=0; i --) {
			sum += path.get(i);
			if (sum == target) {
				result.val = 1;
				return ;
			}
		}
		task2_helper(node.left, target, path, result);
		task2_helper(node.right, target, path, result);
		path.remove(path.size() - 1);
	}
	

	
	/*
	 * task3 
	 * Maximum Path Sum Binary Tree I 
	 * Fair Recursion 
	 * Given a binary tree in which each node contains an integer number. Find the maximum possible
	 * sum from one leaf node to another leaf node. If there is no such path
	 * available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
	 * 
	 * Examples
	 * 
	 *     -15 
	 *     / \
	 *    2   11
	 *       /  \
	 *      6    14
	 * 
	 * The maximum path sum is 6 + 11 + 14 = 31.
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 *      1
	 * 
	 *     / \
	 * 
	 *    2    3
	 * 
	 *        /
	 * 
	 *      4
	 */
	public static void test3() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		
		n1.left = n2;
		n2.left = n3;
		n2.right = n4;
		
		n4.right = n5;
		
		n5.right = n6;
		
		int maxSum = task3_maxPathSum(n1);
		System.out.println("maxSum = " + maxSum);
	}
	
	public static int task3_maxPathSum(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		Max maxSum = new Max(Integer.MIN_VALUE);
		task3_helper(root, maxSum);
		return maxSum.val;
	}
	
	public static int task3_helper(TreeNode node, Max maxSum) {
		if (node == null) {
			return 0;
		}
		
		int leftSum = task3_helper(node.left, maxSum);
		int rightSum = task3_helper(node.right, maxSum);
		
		int tempSum = leftSum + rightSum + node.val;
		if (node.left != null && node.right != null && tempSum > maxSum.val) {
			maxSum.val = tempSum;
		}
		if (node.left == null) {
			return rightSum + node.val;
		} else if (node.right == null) {
			return leftSum + node.val;
		}
		return Math.max(leftSum, rightSum) + node.val;
	}
	
	

	
	/*
	 * task4
	 * Maximum Path Sum Binary Tree II 
	 * Hard Recursion 
	 * Given a binary tree in
	 * which each node contains an integer number. Find the maximum possible sum
	 * from any node to any node (the start node and the end node can be the
	 * same).
	 * 
	 * Assumptions
	 * 
	 * ​The root of the given binary tree is not null Examples
	 * 
	 *       -1
	 *      /   \
	 *     2     11
	 *          /  \
	 *         6   -14
	 * 
	 * one example of paths could be -14 -> 11 -> -1 -> 2
	 * 
	 * another example could be the node 11 itself
	 * 
	 * The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 *      1
	 *    /   \
	 *    2   3
	 *       /
	 *      4
	 */
	public static void test4() {
		
	}
	
	public static int task4_maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		Result result = new Result(0);
		task4_helper(root, path, result);
		return result.val;
	}
	
	public static void task4_helper(TreeNode node, ArrayList<Integer> path, Result result) {
		if (node == null) {
			return ;
		}
		path.add(node.val);
		if (node.left == null && node.right == null) {
			int maxSum = getLargestSum(path);
			if (maxSum > result.val) {
				result.val = maxSum;
			}
		}
		task4_helper(node.left, path, result);
		task4_helper(node.right, path, result);
		
		path.remove(path.size() - 1);
		
	}
	
	public static int getLargestSum(ArrayList<Integer> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for(int i = 0; i < list.size(); i ++) {
			if (sum < 0) {
				sum = 0;
			}
			sum += list.get(i);
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	/*
	 * task5
	 * Maximum Path Sum Binary Tree III 
	 * Fair Recursion
	 * Given a binary tree in
	 * which each node contains an integer number. Find the maximum possible
	 * path sum(both the starting and ending node should be on the same path
	 * from root to one of the leaf nodes, and the path is allowed to contain
	 * only one node).
	 * 
	 * Assumptions
	 * 
	 * The root of given binary tree is not null Examples
	 * 
	 * 		-5
	 * 	   /  \
	 *    2    11
	 *        /  \
	 *       6    14
	 * 
	 * The maximum path sum is 11 + 14 = 25
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 */
	public static void test5() {
		
	}
	
	/*
	 * task6
	 * 
	 * Binary Tree Diameter Fair Recursion 
	 * Given a binary tree in which each node contains an integer number. The diameter is defined as the longest
	 * distance from one leaf node to another leaf node. The distance is the
	 * number of nodes on the path.
	 * 
	 * If there does not exist any such paths, return 0.
	 * 
	 * Examples
	 * 
	 *   	 5
	 *     /   \
	 *    2    11
	 *        /  \
	 *       6    14
	 * 
	 * The diameter of this tree is 4 (2 → 5 → 11 → 14)
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 */
	public static void test6() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		
		n1.left = n2;
		n2.left = n3;
		n2.right = n4;
		
		n4.right = n5;
		
		n5.right = n6;
		
		int diameter = task6_diameter(n1);
		System.out.println("diameter" + diameter);
	}
	
	public static int task6_diameter(TreeNode root) {
		if (root ==null) {
			return 0;
		}
		Max max = new Max(0);
		task6_helper(root, max);
		return max.val;
	}
	
	public static class Max{
		public int val;
		public Max(int v) {
			this.val = v;
		}
	}
	
	public static int task6_helper(TreeNode node, Max max) {
		if (node == null) {
			return 0;
		}
		int leftHeight = task6_helper(node.left, max);
		int rightHeight = task6_helper(node.right, max);
		
		int tempDia = leftHeight + rightHeight + 1;
		if (node.left != null && node.right != null && max.val < tempDia) {
			max.val = tempDia;
		}
		if (node.left == null) {
			return rightHeight + 1;
		} else if (node.right == null) {
			return leftHeight + 1;
		}
		return Math.max(leftHeight, rightHeight) + 1;	
	}
	
	
	
	/*
	 * task7: All Permutations (with duplicates)
	 */
	/*
	 * task8: Min Cuts of Palindrome Partitions
	 */
	/*
	 * task9: Valid Blocks
	 */
	/*
	 * task10: Longest Valid Parentheses
	 */
	
	
	
	
	
	

}
