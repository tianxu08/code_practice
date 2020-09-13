package lec;

import java.util.ArrayList;
import java.util.List;

import debug.Debug;
import ds.TreeNode;

public class Lec22 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Q5_2test();
		Q3_2test();
	}
	
	/*
	 * Recursion III
	 */
	
	/*
	 * 第一类问题： 
	 * Q1 Use recursion to return values in a bottom­up way in binary tree
	 * Q1.1 
	 * Determine whether a binary tree is a balance binary tree ( O(n log n) solution ). 
	 */
	public static boolean Q1_1isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return false;
	}
	
	public static boolean Q1_1Helper(TreeNode root) {
		return false;
	}
	
	
	
	/*
	 * Q1.2
	 * Determine whether a binary tree is a balanced binary tree​(O(n) solution).
	 */
	public static boolean Q1_2isBalanced_fast(TreeNode root) {
		if (root == null) {
			return true;
		}
		return Q1_2getHeight_fast(root) != -1;
	}
	
	public static int Q1_2getHeight_fast(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = Q1_2getHeight_fast(root.left);
		int right = Q1_2getHeight_fast(root.right);
		
		// step2: in current layer. 
		// -1 means the subtree is already not balanced
		if (Math.abs(left - right) > 1 || left == -1 || right == -1) {
			return -1;
		}
		
		// step3: report to parent
		return Math.max(left, right) + 1;
	}
	
	/*
	 * Q1.3
	 * Given a binary tree in which each node element contains a number. 
	 * Find the maximum possible sum f​rom one leaf node to another leaf node.​
	 * 
	 * From Leaf To Leaf
	 * !!! we only update when this node has both left child and right child.
	 */
	
	public static class Result {
		public int val;
		public Result (int v) {
			this.val = v;
		}
	}
	public static int Q1_3MaxPathLeaf2Leaf(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Result result = new Result(Integer.MIN_VALUE);
		Q1_3maxPathLeaf2Leaf2Helper(root, result);
		return result.val;
	}
	
	public static int Q1_3maxPathLeaf2Leaf2Helper(TreeNode node, Result result) {
		if (node == null) {
			return 0;
		}
		int leftPathSum = Q1_3maxPathLeaf2Leaf2Helper(node.left, result);
		int rightPathSum = Q1_3maxPathLeaf2Leaf2Helper(node.right, result);
		
		if (node.left != null && node.right != null && result.val < leftPathSum + rightPathSum + node.val) {
			// update result.val
			result.val = leftPathSum + rightPathSum + node.val;
		}
		if (node.left == null) {
			return rightPathSum + node.val;
		} else if (node.left != null) {
			return leftPathSum + node.val;
		}
		return Math.max(leftPathSum, rightPathSum) + node.val;
	}
	
	/*
	 * Q1.4
	 * M​aximum Path Sum Binary Tree II
	 * Laicode.com Class 20 
	 * 
	 * Given a binary tree in which each node element contains a number. 
	 * Get Maximum sum of the path cost from a​ny node to any node​(not necessarily the leaf to leaf)
	 */
	public static int Q1_4MaxPath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Result result = new Result(Integer.MIN_VALUE);
		Q1_4maxPath(root, result);
		return result.val;
	}
	
	public static int Q1_4maxPath (TreeNode root, Result result) {
		if (root == null) {
			return 0;
		}
		
		int leftPathSum = Q1_4maxPath(root.left, result);
		int rightPathSum = Q1_4maxPath(root.right, result);
		
		// cur
		if (leftPathSum < 0) {
			leftPathSum = 0;
		}
		if (rightPathSum < 0) {
			rightPathSum = 0;
		}
		if (result.val < root.val + leftPathSum + rightPathSum) {
			result.val = root.val + leftPathSum + rightPathSum;
		}
		return  Math.max(leftPathSum, rightPathSum) + root.val;
	} 
	
	
	/*
	 * 	Q2. Tree + Recursion 
	 * 第二类问题: (Path Problem in binary tree) Carry a ​直上直下​path prefix (非人字形) while traversing the tree:
	 * Discussion:
	 * Note that: Tree 相关问题,路径种类可以分为两大类
	 * Class 1:​人字形path, 这类题一般需要从下往上传integer value (E.g.,Q​5.1.5 above)​ 
	 * 
	 * Class 2​:从root 往下 (​直上直下​)path
	 * a. complete path from leaf to root
	 * b. section of complete path from leaf to root
	 */
	
	/*
	 * Q2.1 
	 * Find the maximum path cost​ f​or all paths from leaf to root i​n a Binary Tree
	 */
	public static int Q2_1MaxPath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Result result = new Result(Integer.MIN_VALUE);
		int prefix = 0;
		helper(root, prefix, result);
		return result.val;
	}
	
	public static void helper(TreeNode node, int prefixSum, Result result) {
		if (node == null) {
			return ;
		}
		int curSum = prefixSum + node.val;
		
		// if this is a leaf node, update the result
		if (node.left == null && node.right == null) {
			if (result.val < curSum) {
				result.val = curSum;
			}
		}
		helper(node.left, curSum, result);
		helper(node.right, curSum, result);
	}
	
	/*
	 * Q2.2
	 * (laicode.com Class 20) B​inary Tree Path Sum To Target
	 * Given a binary tree in which each node contains an integer number. 
	 * Determine if there exists a pa​th from any node to any node​
	 * (​t​he two nodes can be the same node and they can only be on the path from root to one of the leaf nodes),
	 * ​t​he sum of the numbers on the path is the given target number.
	 */
	public static boolean Q2_2ExistPathSumWithTarget(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Result result = new Result(0);
		ArrayList<Integer> path = new ArrayList<Integer>();
		Q2_2Helper(root, target, path, result);
		return result.val == 1;
	}
	
	// from each current node, we can trace back by using the prefix of the current path. 
	public static void Q2_2Helper(TreeNode node, int target, ArrayList<Integer> path, Result result) {
		if (node == null) {
			return ;
		}
		int sum = 0;
		path.add(node.val);

		for(int i = path.size() - 1; i >= 0; i --) {
			sum += path.get(i);
			if (target == sum) {
				result.val = 1;
				return ;
			}
		}
		Q2_2Helper(node.left, target, path, result);
		Q2_2Helper(node.right, target, path, result);
		path.remove(path.size() - 1);
	}
	

	/*
	 * Q2.3
	 * Q2.3​(laicode.com Class 20) 
	 * Maximum Path Sum Binary Tree III
	 * Given a binary tree in which each node contains an integer number. 
	 * Find the maximum possible sum from any node to any node 
	 * (the two nodes can be the same node and 
	 * they can only be on the path from root to one of the leaf nodes).
	 * 
	 */
	public static int Q2_3MaxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Result result = new Result(Integer.MIN_VALUE);
		ArrayList<Integer> path = new ArrayList<Integer>();
		Q2_3Helper(root, path, result);
		return result.val;
	}
	
	public static void Q2_3Helper(TreeNode node, ArrayList<Integer> path, Result result) {
		if (node == null) {
			return ;
		}
		path.add(node.val);
		if (node.left == null && node.right == null) {
			// this is a leaf node. 
			int curMax = maxSubArraySum(path);
			if (result.val < curMax) {
				result.val = curMax;
			}
		}
		
		Q2_3Helper(node.left, path, result);
		Q2_3Helper(node.right, path, result);
		path.remove(path.size() - 1);	
	}
	
	public static int maxSubArraySum(ArrayList<Integer> a) {
		if (a == null || a.size() == 0) {
			return 0;
		}
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for(int i = 0; i < a.size(); i ++) {
			if (sum < 0) {
				sum = 0;
			}
			sum += a.get(i);
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}
	
	/*
	 * Q2.4
	 * Binary Tree Diameter
	 * Given a binary tree in which each node contains an integer number. 
	 * The diameter is defined as the longest distance from one leaf node to another leaf node. 
	 * The distance is the number of nodes on the path.
	 * If there does not exist any such paths, return 0.
	 * 
	 * !!! Also, the leaf node to another leaf node
	 */
	public static int Q2_4diameter(TreeNode root) {
		if (root ==null) {
			return 0;
		}
		Result max = new Result(0);
		Q2_4helper(root, max);
		return max.val;
	}
	
	public static int Q2_4helper(TreeNode node, Result max) {
		if (node == null) {
			return 0;
		}
		int leftLength = Q2_4helper(node.left, max);
		int rightLength = Q2_4helper(node.right, max);
		
		if (node.left != null && node.right != null && max.val < leftLength + rightLength + 1) {
			 max.val = leftLength + rightLength + 1;
		}
		if (node.left == null) {
			return rightLength + 1;
		} else if (node.right == null) {
			return leftLength + 1;
		}
		return Math.max(leftLength, rightLength) + 1;
	}
	
	
	/*
	 * Q3. Common elements problems
	 * Q3.1 F​ind common elements in two arrays
	 * Assumption:
	 * 1, sorted vs unsorted
	 * 2, size of two arrays  2 vs 2 billion
	 * 
	 * 
	 * M1: use a hash table
	 * hash all elements from array1 into the table, and iterate over all elements in array2 to check
	 * against hash_table. 
	 * 
	 * Time: O(m + n)
	 * Space: O(m)
	 * 
	 * M2: do not a hash_table
	 * 1 1 3 5 7 9
	 * 
	 * 1 1 3 4 5 6
	 * 
	 */
	
	
	
	/*
	 * Q3.2 F​ind common elements in 3 s​orted​arrays ​preferred O(1) space
	 * 
	 * 
	 */
	public static List<Integer> Q3_2CommonElement3SortedArrays(int[] array1, int[] array2, int[] array3) {
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(i < array1.length && j < array2.length && k < array3.length) {
			if (array1[i] == array2[j] && array2[j] == array3[k]) {
				result.add(array1[i]);
				i ++;
				j ++;
				k ++;
			} else if (array1[i] <= array2[j] && array1[i] <= array3[k]) {
				// array1[i] is the smallest.
				// !!! here, we need to take 2 2 3 into consideration. 
				// in this case, we move i and j together. 
				i ++;
			} else if (array2[j] <= array1[i] && array2[j] <= array3[k]) {
				// array2[j] is the smallest.
				j ++;
			} else {
				k ++;
			}
		}
		return result;
	}
	
	public static void Q3_2test() {
		int[] a = {1,2,3,3};
		int[] b = {2,3,4,4,5};
		int[] c = {1,1,3,3};
		
		List<Integer> result = Q3_2CommonElement3SortedArrays(a, b, c);
		System.out.println(result);
		
	}
	
	/*
	 * Q4 String replacement problems
	 * Q4.1​ Replace all substrings s1 in a string s with s2
	 * (with possible minimum memory allocation);
	 * 
	 * !!! here, I need more time. 
	 */
	
	
	
	
	/*
	 * Q4.2: ​(string) Given a string such as “a4b1c4d0e0f0g0” → “aaaabcccc” ​(no additional string buffer)
	 */
	
	

	/*
	 * Q5 Longest common substring/sequence between two strings
	 * Q5.1
	 * Longest common s​ubString (solution 中字母必须连续)
	 */
	/*
	 * input: String a, String b
	 * M[i][j] represent length of the longest common substring of 
	 *         the first i chars a[0..i-1] in a and the first j chars b[0..j-1]
	 * M[i][j] = 
	 * 			if a[i-1] == b[j-1]   M[i-1][j-1] + 1
	 *          else                  0 
	 */
	
	
	/*
	 * Q5.2
	 * Longest common subsequence 
	 * 
	 * M[i][j] represent length of the longest common subsequence of 
	 *         the first i chars a[0..i-1] in a and the first j chars b[0..j-1]
	 * M[0][0] = 0;
	 * M[i][j] = 
	 * 			if a[i-1] == b[j-1]     M[i-1][j-1] + 1
	 *          else                   max{ M[i-1][j], M[i][j-1] }
	 */
	public static void Q5_2test() {
		String s = "abcde";
		String t = "cbabdfe";
		int rev = Q5_2longestCommonSubsequence(s, t);
		System.out.println("rev = " + rev);
	}
	
	public static int Q5_2longestCommonSubsequence(String s, String t) {
		// write your solution here
		if (s == null || t == null || s.length() == 0 || t.length() == 0) {
			return 0;
		}
		int sLen = s.length();
		int tLen = t.length();
		int[][] M = new int[sLen + 1][tLen + 1];

		// initialize
		for (int i = 0; i <= sLen; i++) {
			M[i][0] = 0;
		}
		for (int j = 0; j <= tLen; j++) {
			M[0][j] = 0;
		}
		
		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j <= tLen; j++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1] + 1;
				} else {
					M[i][j] = Math.max(M[i - 1][j], M[i][j - 1]);
				}
			}
		}
		Debug.printMatrix(M);

		return M[sLen][tLen];
	}
	
	
	


}
