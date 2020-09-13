package onceagain;

public class Class14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}

	/*
	 * task1
	 * Longest Ascending Subarray
	 */
	public static int task1_longestAscendingSubarray(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int result = 1;
		int counter = 1;
		for(int i = 1; i < array.length; i ++) {
			if (array[i] > array[i - 1]) {
				counter ++;
				result = Math.max(result, counter);
			} else {
				counter = 1;
			}
		}
		return result;
	}
	public static void test1() {
		int[] array = {1,2,3,4,2,1,0};
		int rev = task1_longestAscendingSubarray(array);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * task2
	 * Max Product of Cutting Rope
	 */
	public static int task2_MaxProductCutRope_Rec(int n) {
		// base case
		if (n == 0 || n == 1 ) {
			return 0;
		}
		int curMax = 0;
		for(int i = 1; i <= n/2; i ++) {
			int left = i;
			int right = n - i;
			int leftMax = Math.max(left, task2_MaxProductCutRope_Rec(left));
			int rightMax = Math.max(right, task2_MaxProductCutRope_Rec(right));
			curMax = Math.max(curMax, leftMax * rightMax);
		}
		return curMax;
	}
	
	public static void test2() {
		int n = 10;
		int rev = task2_MaxProductCutRope_Rec(n);
		System.out.println("rev = " + rev);
		int rev2 = task2_MaxProductCutRope_DP(n);
		System.out.println("rev2 = " + rev2);
	}
	
	public static int task2_MaxProductCutRope_DP(int n) {
		int[] M = new int[n + 1];
		// init
		M[0] = 0;
		M[1] = 0;
		
		for(int i = 2; i <= n; i ++) {
			int curMax = 0;
			for(int j = 1; j <= i/2; j ++) {
				int leftM = Math.max(j, M[j]);
				int rightM = Math.max(i - j, M[i - j]);
				curMax = Math.max(curMax, leftM * rightM );
			}
			M[i] = Math.max(M[i], curMax);
		}
		
		return M[n];
	}
	
	
	/*
	 * task3
	 * Jump Game
	 * Given an array of non-negative integers,
	 * you are initially positioned at the first index of the array.
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * Determine if you are able to reach the last index.
	 * 
	 */
	public static boolean task3_JumpGame(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		boolean[] M = new boolean[array.length];
		int n = array.length;
		for(int i = n - 2; i >= 0; i --) {
			if (array[i] + i >= n - 1) {
				M[i] = true;
			} else {
				for(int j = 0; j <= array[i]; j ++) {
					if (M[i + j] == true) {
						M[i] = true;
						break;
					}
				}
			}
			
		}
		return M[n - 1];
	}
	
	public static void test3() {
		
	}
	
	/*
	 * task4
	 * Jume Game 2
	 * Given the same setup as the Jump problem,
	 * return the minimum number of jumps needed to reach the end.
	 * If the end of array can not be reached, return the length of the array.
	 */
	public static int task4_JumpGame2(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int n = array.length;
		int[] M = new int[n];
		for(int i = 0; i < n; i ++) {
			M[i] = Integer.MAX_VALUE;
		}
		M[n - 1] = 0;
		for(int i = n - 2; i >= 0; i --) {
			if (array[i] + i >= n - 1) {
				M[i] = 1;
			} else {
				for(int j = 0; j < array[i]; j ++) {
					if (M[j] != Integer.MAX_VALUE) {
						M[i] = Math.min(M[i], M[j] + 1);
					}
				}
			}
		}
		
		if (M[0] == Integer.MAX_VALUE) {
			return n;
		} 
		return M[0];
	}
	
	


}
