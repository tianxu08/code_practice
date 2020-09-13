package small_yan;

import java.util.*;

import debug.Debug;
public class Class9_DP2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2_4();
		t4();
	}
	
	/*
	 * task1
	 * Given an array of characters. 
	 * Find words in the array. a character cannot be part of 2 words. 
	 * Maximize the number of characters used.
	 * 
	 * [​d o g | ​a b | ​c a t | ​​m i c e]​ {dog, cat, mice} ­ dictionary 
	 * Answer = 3 + 3 + 4 = 10
	 * 
	 * method1 
	 * state(i, j) substring(i..j) maximum number of charactered used
	 * 1) state(i,j) is substring(i..j) is in the dictionary : j - i + 1
 	 * 2) state(i, j)  substring(i..j) is not in the dictionary
 	 *      state(i,j)  = max(state(i, k ) + state(k + 1, j))  i <= k < j
 	 * 
 	 * base case: state(i, i) if substring[i..i] is in the dictionary, 1, otherwise, 0
 	 * return state(0, n - 1)
 	 *   
	 */
	public static void test1() {
		String input = "dogabcatmice";
		char[] array = input.toCharArray();
		HashSet<String> dict = new HashSet<String>();
		dict.add("dog");
		dict.add("cat");
		dict.add("mice");
//		dict.add("d");
		int rev = task1_max_num_used_1(array, dict);
		System.out.println("rev = " + rev);
		int rev2 = task1_max_num_used_2(array, dict);
		System.out.println("rev2 = " + rev2);
	}
	
	public static  int task1_max_num_used_1(char[] array, HashSet<String> dict) {
		if (array == null || array.length == 0 || dict == null || dict.size() == 0) {
			return 0;
		}
		int n = array.length;
		int[][] state = new int[n][n];
		// base case
		// len == 1
		for(int i = 0; i < n; i ++) {
			String curString = new String(Arrays.copyOfRange(array, i, i + 1));
			if (dict.contains(curString)) {
				state[i][i] = 1;
			}
		}
		
		for(int len = 2; len <= array.length; len ++) {
			for(int i = 0; i <= n - len ;i ++) {
				int j = i + len - 1;   // i + len - 1 <= n - 1  so i <= n - len  
				String cur = new String(Arrays.copyOfRange(array, i, j + 1));
				if (dict.contains(cur)) {
					state[i][j] = j - i + 1;
				} else {
					int temp = 0;
					for(int k = i; k <j; k ++) {
						temp = Math.max(temp, state[i][k] + state[k + 1][j]);
					}
					state[i][j] = temp;
				}
			}
		}
		Debug.printMatrix(state);
		return state[0][n - 1];
	} // Time: O(n^2)
	
	
	/*
	 * method2
	 * state[i]  substring[0..i], max # of chars used
	 * state[i] == if substring[0..i] in the dictionary, i + 1
	 *            else
	 *              max (state[k] +  (i - k)if subarray[k + 1.. i] in the dictionary) 0 < k < i
	 * base state[0] substring[0..0] in the dictionary: 1, otherwise, 0;   
	 * return state[n - 1]
	 */
	
	public static int task1_max_num_used_2(char[] array, HashSet<String> dict) {
		if (array == null || array.length == 0 || dict == null
				|| dict.size() == 0) {
			return 0;
		}
		int n = array.length;
		int[] state = new int[n];
		// base case
		String cur = new String(Arrays.copyOfRange(array, 0, 1));
		state[0] = dict.contains(cur) ? 1: 0;
		
		for(int i = 1; i < n; i ++) {
			String curStr = new String(Arrays.copyOfRange(array, 0, i + 1));
			if (dict.contains(curStr)) {
				state[i] = i + 1;
			} else {
				for(int k = 0; k < i; k ++) {
					int temp = state[k];
					String secondPart = new String(Arrays.copyOfRange(array, k + 1, i + 1));
					if (dict.contains(secondPart)) {
						temp += (i - k);
					}
					state[i] = Math.max(state[i], temp);
				}
			}
		}
		Debug.printArray(state);
		return state[n - 1];
	}
	
	/*
	 * task2 
	 * Jump Game
	 * Array of no-negative integers. in 1,2,3 
	 * A[i] means that the max jump distance from that position(you can only jump towards the end of the array)
	 * n = a.length;
	 * task2.1
	 * Jump game1
	 *  
	 * can we jump and reach the end of the array
	 * state[i] means whether in position i, can we reach the end of the array. 
	 * base: state[n - 1] = true;
	 * induction rule: 
	 *       base[i] == true if 
	 *       1) A[i] >= n- 1
	 *       2) for j > i && j <= i + A[i]   
	 *          if state[j] = true ==>  state[i] = true
	 * 
	 * task2.2
	 * Jump game2
	 * what is the minimum number of jumps to reach the end of array
	 * state[i]: the minimum jump steps to reach array[n - 1]
	 * base case: state[n - 1] = 0;
	 * induction rule:
	 *            state[i] = 1 + min{state[j]} is all elements that can be reach by 1 jump from i
	 * 
	 * 
	 * 
	 * task2.3
	 * Jump game3
	 * what is the minimum # of jumps to jump out of the array
	 * we append another element at the end will make the problem same as Jump Game II.
	 * 
	 * task2.4
	 * at each position, we can jump to left or to right, give a position, can we reach the end of the array? 
	 *  
	 * 
	 */
	/*
	 * task2.4
	 * state[i][k]: from index i, jump k steps can reach the end of array
	 * base: state[n-1][0] == true
	 * induction rule:
	 *       state[i][k] = for all the j in [i - array[i], i + array[i]]
	 *       if state(j, k - 1) == true 
	 *          then state(i, k) = true
	 *          
	 * result: 
	 * state(i, x) == true for x in [1..n]
	 * 
	 * Time: O(n^3)
	 */
	public static void test2_4() {
		int[] a = {4, 0, 1, 0, 0};
		int result = task2_4_min_jump(a);
		System.out.println("result = " + result);
	}
	
	public static int task2_4_min_jump(int[] array) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		boolean[][] state = new boolean[n][n];
		state[n - 1][0] = true;
//		Debug.printMatrix(state);
		for(int k = 1; k < n; k ++) {
			for(int i = 0; i < n; i ++) {
				int leftBound = i - array[i] >= 0 ? i - array[i] : 0 ;
				int rightBound = i + array[i] <= n - 1? i + array[i] : n - 1;
				for(int j = leftBound; j <= rightBound; j ++) {
					if (state[j][k - 1] == true) {
						state[i][k] = true;
					}
				}
				
			}
			if (state[0][k] == true) {
				return k;
			}
		}		
		return -1;
	}
	
	public static int task2_4_min_jump_lai(int[] array, int index) {
		if (array == null || array.length == 0) {
			return -1;
		}
		
		int n = array.length;
		if (index >= n && index < 0) {
			return -1;
		}
		if (index == n - 1) {
			return 0;
		}
		boolean[][] state = new boolean[n][n];
		state[n - 1][0] = true;
		for(int k = 1; k < n; k ++) {
			for(int i = 0; i < n; i ++) {
				int leftBound = i - array[i] >= 0 ? i - array[i] : 0 ;
				int rightBound = i + array[i] <= n - 1? i + array[i] : n - 1;
				for(int j = leftBound; j <= rightBound; j ++) {
					if (state[j][k - 1] == true) {
						state[i][k] = true;
					}
				}
				
			}
			if (state[index][k] == true) {
				return k;
			}
		}

		return -1;
	}
	
	/*
	 * task3
	 * 直线上有一个机器人从原点开始移动,每次移动距离相同(1), 每次可以向左移,也可以向 右移(概率相同都是0.5),移动n步,再回到原点的概率是多少。
	 * 
	 * prob(n, x) = probability of moving n steps, reaching x
	 * prob(n, x) = prob(n - 1, x - 1)* 0.5 + prob(n-1, x + 1)* 0.5
	 *                from x - 1                 from x + 1 
	 * result:
	 * prob(n, 0)
	 */
		
	
	
	
	/*
	 * task4
	 * Given an n x n matrix A(i,j) of integers, 
	 * find maximum value A(c,d) ­- A(a,b) over all choices of indexes such that both c > a and d > b.
	 * 
	 * max(a, b) maximum value in submatrix of ((a + 1), (b + 1)) as top left corner
	 * 
	 * max(a, b) = max of (max(a + 1), b) max(a, b + 1), max(a + 1, b + 1)
	 * 
	 * from right to left, 
	 * from low to up
	 * 
	 * matrix
	 * M[i][j] someting ending or start with (i, j)
	 * 
	 * http://www.geeksforgeeks.org/find-a-specific-pair-in-matrix/
	 * this is easier
	 * M[i][j] the maximum element in the submatrix [i][j] to (n - 1, m - 1)
	 * 
	 */
	public static void t4() {
		int[][] matrix = 
			{ 
				{ 1, 2, -1, -4, -20 },
				{ -8, -3, 4, 2, 1 },
				{ 3, 8, 6, 1, 3 },
				{ -4, -1, 1, 7, -6 },
				{ 0, -4, 10, -5, 1 }
			};
		int maxDiff = task4_maxDiff(matrix);
		System.out.println("maxDif = " + maxDiff);
		
	}
		
	
	public static int task4_maxDiff(int[][] matrix) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		
		int maxDifVal = Integer.MIN_VALUE;
		
		M[rLen - 1][cLen - 1] = matrix[rLen - 1][cLen - 1];
		// init the last row
		for(int j = cLen - 2; j >= 0; j --) {
			M[rLen - 1][j] = Math.max(matrix[rLen - 1][j], M[rLen - 1][j + 1]);
		}
		
		// init the last column
		for(int i = rLen - 2; i >= 0; i--) {
			M[i][cLen - 1] = Math.max(matrix[i][cLen - 1], M[i + 1][cLen - 1]);
		}
		
		// for all others element
		for(int i = rLen - 2; i >= 0; i--) {
			for(int j = cLen - 2; j >= 0; j--) {
				if (M[i + 1][j + 1] - matrix[i][j] > maxDifVal) {
					maxDifVal =  M[i + 1][j + 1] - matrix[i][j] ;
				}
				// set the M[i][j]
				M[i][j] = Math.max(Math.max(M[i][j + 1], M[i + 1][j]), Math.max(M[i + 1][j + 1], matrix[i][j]));
			}
		}
		Debug.printMatrix(M);
		
		return maxDifVal;
	}
	
	
	
	/*
	 * task5
	 * Given a boolean expression consisting of the symbols 0,1, &, |, ^and a desired boolean result value result, 
	 * implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result.
	 */
	
	
	
	
	
	
	
	

}
