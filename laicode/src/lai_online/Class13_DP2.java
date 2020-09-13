package lai_online;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Class13_DP2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test5();
	}

	/*
	 *
	 * task3 Dictionary Word I 
	 * task4 Edit Distance 
	 * task5 Largest Square Of 1s
	 * task6 Largest Rectangle of 1s
	 */

	

	/*
	 * task3 Dictionary Word I
	 * 
	 * Given a word and a dictionary, determine if it can be composed by
	 * concatenating words from the given dictionary.
	 * 
	 * Assumptions The given word is not null and is not empty The given
	 * dictionary is not null and is not empty and all the words in the
	 * dictionary are not null or empty 
	 * Examples Dictionary: 
	 * {“bob”, “cat”, “rob”} 
	 * Word: “robob” return false 
	 * Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
	 */

	/*
	 * M[i] stands for the first i characters ending with input.charAt(i - 1) is
	 * in dict 
	 * base case: M[0] = true 
	 * induction rule: M[i] = true 
	 * if there exist one j, j>= 0 && j < i, M[j] && input.sub(i - j, i) in the dict is True
	 */

	public boolean task3_canBreak(String input, String[] dict) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return true;
		}
		Set<String> set = new HashSet<String>();
		for (String s : dict) {
			set.add(s);
		}

		int n = input.length();
		boolean[] M = new boolean[n + 1]; // !!!
		M[0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				String second = input.substring(j, i);
				if (M[j] == true && set.contains(second)) {
					M[i] = true;
					break;
				}
			}
		}

		return M[n];
	}


	
	/*
	 * task4 Edit Distance
	 * 
	 * Given two strings of alphanumeric characters, determine the minimum
	 * number of 
	 * Replace, 
	 * Delete, and 
	 * Insert operations needed to transform one
	 * string into the other.
	 * 
	 * Assumptions Both strings are not null Examples string one: “sigh”, string
	 * two : “asith” the edit distance between one and two is 2 (one insert “a”
	 * at front then replace “g” with “t”).
	 */

	/*
	 * M[i][j] stands that the minimum distance between the first i characters
	 * in one, which is one[0, i - 1] and the first j characters in two, which
	 * is two[0, j - 1] 
	 * base case: M[0][0] = 0 M[i][0] = i; M[0][j] = j;
	 * induction rule: 
	 * M[i][j] = one.charAt(i - 1) == two.charAt(j - 1) M[i-1][j - 1] 
	 *         else min(M[i-1][j], M[i][j - 1], M[i-1][j-1]) + 1 
	 * !!! string.length()
	 */
	public static int task4_editDistance(String one, String two) {
		// write your solution here
		if (one == null && two == null) {
			return 0;
		}
		if (one == null) {
			return two.length();
		}
		if (two == null) {
			return one.length();
		}
		int m = one.length();
		int n = two.length();
		int[][] M = new int[m + 1][n + 1];

		// initaizlie
		M[0][0] = 0;
		for (int i = 1; i <= m; i++) {
			M[i][0] = i;
		}

		for (int j = 1; j <= n; j++) {
			M[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (one.charAt(i - 1) == two.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1];
				} else {
					M[i][j] = Math.min(Math.min(M[i - 1][j], M[i][j - 1]),
							M[i - 1][j - 1]) + 1;
				}
			}
		}

		return M[m][n];
	}
	
	public static int editDistance(String word1, String word2) {
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}
		int nothing = Integer.MAX_VALUE;
		if (word1.charAt(0) == word2.charAt(0)) {
			nothing = editDistance(word1.substring(1), word2.substring(1));
		}
		int replace = 1 + editDistance(word1.substring(1), word2.substring(1));
		int delete = 1 + editDistance(word1.substring(1), word2);
		int insert = 1 + editDistance(word1, word2.substring(1));
		
		return Math.min(Math.min(nothing, replace), Math.min(delete, insert));
	}
	

	/*
	 * task5 棋盘类
	 * 
	 * Largest Square Of 1s Hard DP
	 * 
	 * Determine the largest square of 1s in a binary matrix (a binary matrix
	 * only contains 0 and 1), return the length of the largest square.
	 * 
	 * Assumptions The given matrix is not null and guaranteed to be of size N *
	 * N, N >= 0 Examples { 
	 * {0, 0, 0, 0}, 
	 * {1, 1, 1, 1}, 
	 * {0, 1, 1, 1}, 
	 * {1, 0, 1, 1} 
	 * } 
	 * the largest square of 1s has length of 2
	 */

	public static void test5() {
		int[][] matrix = { 
				{ 0, 0, 0, 0 }, 
				{ 1, 1, 1, 1 }, 
				{ 0, 1, 1, 1 },
				{ 1, 0, 1, 1 } };
		int rev = task5_largest(matrix);
		System.out.println("rev = " + rev);
	}

	/*
	 * M[i][j] the max Length of Squares that ending with [i,j] 
	 * M[i][j] = min(M[i - 1][j - 1], M[i - 1][j], M[i][j - 1]) + 1 IF matrix[i][j] == 1
	 * 
	 * initialize: 
	 * first column, first row
	 */
	public static int task5_largest(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		int maxLen = 0;
		int maxX = -1;
		int maxY = -1;

		// initialize
		// copy the first row and first column form matrix to M
		for (int i = 0; i < rLen; i++) {
			M[i][0] = matrix[i][0];
			if (maxLen < M[i][0]) {
				maxLen = M[i][0];
				maxX = i;
				maxY = 0;
			}
		}
		for (int j = 0; j < cLen; j++) {
			M[0][j] = matrix[0][j];
			if (maxLen < M[0][j]) {
				maxLen = M[0][j];
				maxX = 0;
				maxY = j;
			}
		}

		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == 1) {
					M[i][j] = Math.min(Math.min(M[i - 1][j], M[i - 1][j - 1]),
							M[i][j - 1]) + 1;
					// update the maxLen
					if (maxLen < M[i][j]) {
						maxLen = M[i][j];
						maxX = i;
						maxY = j;
					}
				} else {
					M[i][j] = 0;
				}
			}
		}
		return maxLen;
	}
	
	
	/*
	 * task6 Largest Rectangle of 1s
	 * 
	 * follow up question of largest rectangle in histogram. 
	 */
	public static int task6_maximumRectangle(char[][] matrix) {
		// check
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] sumMatrix = new int[rLen][cLen];
		
		for (int j = 0; j < cLen; j++) {
			for (int i = 0; i < rLen; i++) {
				if (i == 0 || matrix[i][j] == '0') {
					sumMatrix[i][j] = matrix[i][j] - '0';
				} else {
					if (matrix[i - 1][j] == '0') {
						sumMatrix[i][j] = matrix[i][j] - '0';
					} else {
						sumMatrix[i][j] = sumMatrix[i - 1][j]
								+ (matrix[i][j] - '0');
					}
				}
			}
		}

		int maxArea = 0;
		for (int i = 0; i < rLen; i++) {
			int curArea = task6_largestRectangleArea(sumMatrix[i]);
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}
	
	public static int task6_largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		// increasing stack
		int i = 0;
		int maxArea = 0;
		while(i <= height.length) {
			int curHeight = i < height.length ? height[i] : -1;
			while(!stack.isEmpty() &&  curHeight < height[stack.peekFirst()]) {
				// calculate the poped element's largest area. left side is smaller than it. right side is 
				// the rightmost side it can reach, which is i
				int h = height[stack.pollFirst()];
				int w = stack.isEmpty() ? (i - (-1) + 1) : i - stack.peekFirst() - 1;
				int area = h * w;
				maxArea = Math.max(maxArea, area);
			}
			stack.offerFirst(i);
			i ++;
		}
		return maxArea;
	}
	

}
