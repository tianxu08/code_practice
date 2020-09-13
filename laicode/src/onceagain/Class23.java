package onceagain;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.SliderUI;

import ds.TreeNode;

public class Class23 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}

	/*
	 * task1 Determine if Binary Tree Is Balanced skipped
	 */

	/*
	 * task2 Max Path Sum Binary Tree II(path from any node to any node)
	 */
	public static int task2_maxPathSumII(TreeNode root) {
		Result rev = task2_helper(root);
		return rev.dou_side;
	}

	public static Result task2_helper(TreeNode node) {
		if (node == null) {
			return new Result(0, Integer.MIN_VALUE);
		}
		Result leftResult = task2_helper(node.left);
		Result rightResult = task2_helper(node.right);

		int sin_side = Math.max(node.val,
				Math.max(leftResult.sin_side, rightResult.sin_side) + node.val);
		int dou_side = Math.max(sin_side, leftResult.sin_side
				+ rightResult.sin_side + node.val);

		Result cur = new Result(sin_side, Math.max(dou_side,
				Math.max(leftResult.dou_side, rightResult.dou_side)));
		return cur;
	}

	public static class Result {
		int sin_side;
		int dou_side;

		public Result(int _s, int _d) {
			this.sin_side = _s;
			this.dou_side = _d;
		}
	}

	public static void test2() {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(1);
		n1.left = n2;
		n2.left = n3;
		n2.right = n4;

		TreeNode n5 = new TreeNode(10);
		TreeNode n6 = new TreeNode(-25);
		TreeNode n7 = new TreeNode(3);
		TreeNode n8 = new TreeNode(4);

		n1.right = n5;
		n5.right = n6;
		n6.left = n7;
		n6.right = n8;

		int rev = task2_maxPathSumII(n1);
		System.out.println("rev = " + rev);
	}

	// another method
	public static class Result1 {
		public int val;

		public Result1(int v) {
			this.val = v;
		}
	}

	public static int maxPathSumAny2Any(TreeNode root) {
		// Write your solution here.

		if (root == null) {
			return 0;
		}
		Result1 result = new Result1(Integer.MIN_VALUE);
		task2_maxPath(root, result);
		return result.val;
	}

	public static int task2_maxPath(TreeNode root, Result1 result) {
		if (root == null) {
			return 0;
		}

		int left = task2_maxPath(root.left, result);
		int right = task2_maxPath(root.right, result);

		// cur
		if (left < 0) {
			left = 0;
		}
		if (right < 0) {
			right = 0;
		}
		if (result.val < root.val + left + right) {
			result.val = root.val + left + right;
		}
		return Math.max(left, right) + root.val;
	}

	/*
	 * task3 Max Path Sum Binary Tree(path from leaf to root)
	 */
	public static int task3_MaxPathSumLeaf2Root(TreeNode root) {
		return task3_helper(root, 0);
	}

	// return the maximum path of sum from leaf to root
	// sum stores the sum of current path from node to root
	public static int task3_helper(TreeNode node, int sum) {
		sum += node.val;
		if (node.left == null && node.right == null) {
			return sum;
		} else if (node.left == null) {
			return task3_helper(node.right, sum);
		} else if (node.right == null) {
			return task3_helper(node.left, sum);
		} else {
			return Math.max(task3_helper(node.left, sum),
					task3_helper(node.right, sum));
		}
	}

	public static void test3() {
		return ;
	}

	/*
	 * task4 Binary Tree Path Sum To Target (the two nodes can be the same node
	 * and they can only be on the path from root to one of the leaf nodes)
	 */
	public static boolean task4_BTPathSum2Target(TreeNode root, int target) {
		Result4 result = new Result4(0);
		List<TreeNode> list = new ArrayList<TreeNode>();
		task4_helper(root, list , target, result);
		return result.val == 1;
	}
	public static class Result4{
		int val;
		public Result4(int _v) {
			this.val = _v;
		}
	}
	
	public static void task4_helper(TreeNode node, List<TreeNode> list, int target, Result4 result) {
		if (node == null) {
			return ;
		}
		list.add(node);
		int sum = 0;
		for(int i = list.size() - 1; i >= 0; i --) {
			sum += list.get(i).val;
			if (sum == target) {
				result.val = 1;
				return ;
			}
		}	
		task4_helper(node.left, list, target, result);
		task4_helper(node.right, list, target, result);
		list.remove(list.size() - 1); 
	}
	
	
	
	/*
	 * task5 Max Path Sum Binary Tree III (the two nodes can be the same node
	 * and they can only be on the path from root to one of the leaf nodes)
	 */
	public static int  task5_MaxPathSumIII(TreeNode root) {
		Result5 result = new Result5(Integer.MIN_VALUE);
		task5_helper(root, result);
		return result.val;
	}
	
	// return the 
	public static class Result5{
		int val;
		public Result5(int _v) {
			this.val = _v;
		}
	}
	// return the max Path Sum use "node" as root
	public static int task5_helper(TreeNode node, Result5 result) {
		if (node == null) {
			return 0;
		}
		
		int leftS = task5_helper(node.left, result);
		int rightS = task5_helper(node.right, result);
		
		int single_side = Math.max(node.val, Math.max(node.val + leftS, node.val + rightS));
		result.val = Math.max(result.val, single_side);
		return single_side;
		
		
	}

	/*
	 * task6 Common Elements In Three Sorted Arrays
	 */

	/*
	 * task7 Longest Common Substring
	 * int[][] M
	 * 
	 * M[i][j] the longest Common Substring of s1[0..i - 1] and s2[0..j - 1]
	 * M[i][j] = M[i - 1][j - 1] + 1 if s1[i - 1] == s2[j - 1]
	 *         = 0 					 if != 
	 */
	public static String task6_longestCommonSubstring(String s1, String s2) {
		int s1Len = s1.length();
		int s2Len = s2.length();
		
		int[][] M = new int[s1Len][s2Len];
		int end = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < s1Len; i ++) {
			for(int j = 0; j < s2Len; j ++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					if (i == 0 || j == 0) {
						M[i][j] = 1;
					} else {
						M[i][j] = M[i - 1][j - 1] + 1;
					}
					
					if (M[i][j] > max) {
						max = M[i][j];
						end = i; 
					}
				}
			}
		}
		return s1.substring(end - max + 1, end + 1);
	}
	

	/*
	 * task8 Longest Common Subsequence
	 * M[i][j] the longest subsequence of s1[0..i - 1] and s2[0..j - 1]
	 * M[i][j] = if s1[i - 1] == s2[j - 1] M[i - 1][j - 1] + 1
	 *         = else   max(M[i - 1][j], M[i][j - 1]);
	 *           
	 */
	public static int task8_longestSubsequence(String s1, String s2) {
		int s1Len = s1.length();
		int s2Len = s2.length();
		int[][] M = new int[s1Len + 1][s2Len + 1];
		for(int i = 1; i <= s1.length(); i ++) {
			for(int j = 1; j <= s2.length(); j ++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1] + 1;
				} else {
					M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
				}
			}
		}
		return M[s1Len][s2Len];
	}
	
}
