package onceagain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import ds.TreeNode;

public class Class20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		test3();
	}

	/*
	 * task1 All permutation with duplicate
	 */
	public static List<String> task1_allPermutation(String str) {
		char[] array = str.toCharArray();
		int index = 0;
		List<String> result = new ArrayList<String>();
		task1_helper(array, index, result);
		return result;
	}

	public static void task1_helper(char[] array, int index, List<String> result) {
		if (index == array.length) {
			result.add(new String(array));
			return;
		}
		HashSet<Character> mySet = new HashSet<Character>();
		for (int i = index; i < array.length; i++) {
			if (mySet.contains(array[i])) {
				continue;
			}
			mySet.add(array[i]);
			swap(array, index, i);
			task1_helper(array, index + 1, result);
			swap(array, index, i);
		}
	}

	public static void swap(char[] array, int x, int y) {
		char temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static void test1() {
		String set = "abb";
		List<String> result = task1_allPermutation(set);
		System.out.println(result);

	}

	/*
	 * task2 Max Path Sum From One Leaf Node To Another In Binary Tree
	 */
	public static int task2_MaxPathSumOfTwoLeafNode(TreeNode root) {
		int[] max = new int[1];
		max[0] = Integer.MIN_VALUE;
		task2_helper(root, max);
		return max[0];
	}

	public static int task2_helper(TreeNode node, int[] max) {
		if (node == null) {
			return 0;
		}
		int leftResult = task2_helper(node.left, max);
		int rightResult = task2_helper(node.right, max);
		int tempSum = leftResult + rightResult + node.val;
		if (node.left == null && node.right == null && tempSum > max[0]) {
			max[0] = tempSum;
		}

		if (node.left == null) {
			return rightResult + node.val;
		} else if (node.right == null) {
			return leftResult + node.val;
		} else {
			return Math.max(leftResult, rightResult) + node.val;
		}
	}

	/*
	 * task3 Min Cuts Of Palindrome Partitions
	 */
	public static int task3_minCutPalindromePartition(String str) {
		int n = str.length();
		boolean[][] isP = new boolean[n][n];
		// isP[i][j] substring str[i..j] is Palindrome
		// isP[i][i] = true
		// isP[i][j] = isP[i + 1][j - 1] && str[i] == str[j]
		// init
		for (int i = 0; i < str.length(); i++) {
			isP[i][i] = true;
		}
		for (int i = 0; i < str.length() - 1; i++) {
			isP[i][i + 1] = str.charAt(i) == str.charAt(i + 1);
		}
		for (int len = 3; len <= str.length(); len++) {
			// i + len - 1 < str.length
			for (int i = 0; i < str.length() + 1 - len; i++) { // i<= str.length() + 1 - len
				int j = i + len - 1;
				if (str.charAt(i) == str.charAt(j)) {
					isP[i][j] = isP[i + 1][j - 1];
				}
			}
		}
		int[] M = new int[n];
		// M[i] stands the minimum cut of palindrome partitions of str[0..i]
		// M[i] = min( M[j] ) + 1 if isP[j..i] == true j >= 0 && j <= i

		for (int i = 0; i < n; i++) {
			if (isP[0][i] == true) {
				M[i] = 0;
			} else {
				M[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {

					if (isP[j + 1][i] == true && 1 + M[j] < M[i]) {
						M[i] = 1 + M[j];
					}
				}
			}
		}

		return M[n - 1];
	}

	public static void test3() {
		String str = "abbbc";
		int rev = task3_minCutPalindromePartition(str);
		System.out.println("rev = " + rev);
	}

}
