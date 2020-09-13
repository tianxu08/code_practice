package lai_online2;
import ds_lai_online2.*;
public class Class20 {
	/*
	 * task1
	Minimum Cuts For Palindromes
	Fair
	DP
	Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

	Assumptions

	The given string is not null
	Examples

	“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

	The minimum number of cuts needed is 3.
	*/
	
	public int task1_minCuts(String input) {
		// write your solution here
		if (input == null || input.length() <= 1) {
			return 0;
		}
		int n = input.length();
		boolean[][] f = new boolean[n][n];
		int[] c = new int[n];
		// f[i][j] input[i..j] is palindrome
		// f[i][j] = input[i] == input[j] && f[i + 1][ j - 1]
		// initialize: len = 0: f[i][j] = true; len = 1: f[i][j] = input[i]==
		// input[j]
		for (int i = 0; i < n; i++) {
			f[i][i] = true;
		}
		for (int i = 0; i < n - 1; i++) {
			f[i][i + 1] = input.charAt(i) == input.charAt(i + 1);
		}

		for (int len = 3; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1; // i + len - 1 <= n - 1 i + len <= n i <= n
										// - len
				if (input.charAt(i) == input.charAt(j)) {
					f[i][j] = f[i + 1][j - 1];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (f[0][i] == true) {
				c[i] = 0;
			} else {
				c[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {

					if (f[j + 1][i] == true && 1 + c[j] < c[i]) {
						c[i] = 1 + c[j];
					}
				}
			}
		}
		return c[n - 1];
	}
	
	/*task2
	Maximum Path Sum Binary Tree I
	Fair
	Recursion
	Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

	Examples

	  -15

	  /    \

	2      11

	     /    \

	    6     14

	The maximum path sum is 6 + 11 + 14 = 31.

	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	
	*/
	public int maxPathSum(TreeNode root) {
		// Write your solution here.
		MAX max = new MAX(Integer.MIN_VALUE);
		helper(root, max);
		return max.val;
	}

	public class MAX {
		int val;

		public MAX(int v) {
			this.val = v;
		}
	}

	public int helper(TreeNode node, MAX max) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		if (node.left == null && node.right == null) {
			return node.key;
		}
		int leftSum = helper(node.left, max);
		int rightSum = helper(node.right, max);

		if (leftSum != Integer.MIN_VALUE && rightSum != Integer.MIN_VALUE) {
			int curSum = node.key + leftSum + rightSum;
			if (max.val < curSum) { // update the maxVal
				max.val = curSum;
			}
		}
		return Math.max(leftSum, rightSum) + node.key;
	}

}
