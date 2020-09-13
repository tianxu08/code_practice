package lai_online;

import java.util.Arrays;

public class Class12_DP1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
	}
	/*
	 * task1 Longest Ascending SubArray
	 * task2 Max Product Of Cutting Rope
	 * task3 Array Hopper I
	 * task4 Array Hopper II, Jump Game II 
	 * task5 Array Hopper III 
	 */
	
	
	/*
	 * task1
	 * Longest Ascending SubArray
	 * Easy DP
	 * Given an unsorted array, find the length of the longest subarray in 
	 * which the numbers are in ascending order.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * 
	 * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
	 * {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
	 * 
	 */
	
	public static int task1_longest_ascending_subarray(int[] array) {
	    // write your solution here
	    if(array == null || array.length == 0) {
	      return 0;
	    }
	    if (array.length == 1) {
	      return 1;
	    }
	    int curLen = 1;
	    int maxLen = Integer.MIN_VALUE;  // global max
	    for(int i = 1; i < array.length; i++) {
	      if (array[i] > array[i - 1]) {
	        curLen ++;
	      } else {
	        curLen = 1;
	      }
	      maxLen = Math.max(curLen, maxLen);
	    }
	    return maxLen;
	    
	  }
	
	
	/*
	 * task2
	 * 
	 * Max Product Of Cutting Rope Fair DP
	 * Given a rope with positive integer-length n, how to cut the rope into m integer-length parts
	 *  
	 * with length p[0], p[1], ...,p[m-1], in order to
	 * 
	 * get the maximal product of p[0]*p[1]* ... *p[m-1]?
	 *  
	 * m is determined by you and must be greater than 0 (at least one cut must be made). 
	 * Return the max product you can have.
	 * 
	 * Assumptions
	 * n >= 2
	 * Examples
	 * n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
	 *
	 * M[n] is the max product of n
	 * 
	 * M[n] =  Max(i, M[i]) * Max(n - i, M[n - i])
	 * Init: 
	 * AT Least 1 cut, so M[0]= 0, M[1] = 0;
	 * 
	 */
	
	public static void test2() {
		int n = 12;
		int rev = task2_maxProduct(n);
		int rev2 = task2_maxProduct_Rec(n);
		System.out.println(rev);
		System.out.println(rev2);
	}
	public static int task2_maxProduct(int n) {
	    // write your solution here
	    if (n <= 1) {
			return 0;
		}
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 1; i <= n; i++) {
			int curMax = 0;
			for (int j = 1; j <= i/2; j++) {
				curMax = Math.max(curMax,
						Math.max(j, M[j]) * Math.max(i - j, M[i - j]));
			}
			M[i] = curMax;
		}
		System.out.println(Arrays.toString(M));
		return M[n];
	  }
	
	public static int task2_maxProduct_Rec(int n) {
		return task2_helper(n);
	}
	
	public static int task2_helper(int n) {
		if (n == 0 || n == 1) {
			return 0;
		}
		int curMax = 0;
		for(int i = 1; i <= n/2; i ++) {
			curMax = Math.max(curMax, Math.max(i, task2_helper(i)) * Math.max(n - i, task2_helper(n - i)));
		}
		return curMax;
	}
	
	
	/*
	 * task3
	 * 
	 * Array Hopper I Fair DP 
	 * Given an array A of non-negative integers, you are
	 * initially positioned at index 0 of the array. A[i] means the maximum jump
	 * distance from that position (you can only jump towards the end of the
	 * array). Determine if you are able to reach the last index.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and has length of at least 1. Examples
	 * 
	 * {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1
	 * then reach the end of the array)
	 * 
	 * {2, 1, 1, 0, 2}, we are not able to reach the end of array
	 */
	
	 // M[i] stands whether array[i] can reach the end of the array, names array[n- 1]. n = array.length
	  // base: M[n-1] true;
	  // induction rule: M[i] = 
	  // 						1 if i + array[i] >= n-1  M[i] = true;
	  // 						2 for j > i && j <= i + array[i] if M[j] == true, M[i] = true 
	public boolean task3_canJump(int[] array) {
		// write your solution here
		if (array == null) {
			return true;
		}
		if (array.length <= 1) {
			return true;
		}
		int n = array.length;
		boolean[] M = new boolean[n];
		M[n - 1] = true;

		for (int i = n - 2; i >= 0; i--) {
			if (i + array[i] >= n - 1) {
				M[i] = true;
			} else {
				for (int j = i + 1; j <= i + array[i]; j++) {
					if (M[j]) {
						M[i] = true;
					}
				}
			}
		}
		return M[0];
	}
	
	/*
	 * task1 Array Hopper II 
	 * get the minimum steps from the start to the end
	 * Given an array A of non-negative integers, you are initially positioned
	 * at index 0 of the array. A[i] means the maximum jump distance from index
	 * i (you can only jump towards the end of the array). 
	 * 
	 * Determine the minimum number of jumps you need to reach the end of array.
	 *  
	 * If you can not reach the end of the array, return -1. 
	 * 
	 * Assumptions The given array is not null
	 * and has length of at least 1. Examples {3, 3, 1, 0, 4}, the minimum jumps
	 * needed is 2 (jump to index 1 then to the end of array) {2, 1, 1, 0, 2},
	 * you are not able to reach the end of array, return -1 in this case.
	 */

	// M[i] stands the minimum jump steps to reach array[n-1]
	// base case: M[n-1] = 0;
	// induction rule: M[i] = 1 + min{M[j]} is all elements that can be reached by 1 jump from i
	public static int task4_minJump(int[] array) {
		// write your solution here
		if (array == null) {
			return 0;
		}
		if (array.length <= 0) {
			return 0;
		}
		int n = array.length;
		int[] M = new int[n];
		M[n - 1] = 0;
		for (int i = n - 2; i >= 0; i--) {
			if (array[i] == 0) {
				M[i] = Integer.MAX_VALUE;
			} else if (i + array[i] >= n - 1) {
				// can be finish by 1 jump
				M[i] = 1;
			} else {
				// traverse i+1 .. min(n, i + array[i])
				int curMin = Integer.MAX_VALUE;
				for (int j = i + 1; j < n && j <= i + array[i]; j++) {
					// get the current min steps
					curMin = Math.min(curMin, M[j]);
				}
				// if curMin != INT_MAX
				if (curMin != Integer.MAX_VALUE) {
					M[i] = 1 + curMin;
				} else {
					// we the min == INT_MAX
					M[i] = Integer.MAX_VALUE;
				}
			}
		}
		// check whether M[0] is INT_MAX
		if (M[0] == Integer.MAX_VALUE) {
			// cannot reach
			return -1;
		}
		return M[0];
	}

	/*
	 * task2 Array Hopper III Jump out of Bound
	 * 
	 * This can reduce to task1. M[] = new int[n + 1]
	 * 
	 * Given an array of non-negative integers, you are initially positioned at
	 * index 0 of the array. A[i] means the maximum jump distance from that
	 * position (you can only jump towards the end of the array). 
	 * 
	 * Determine the minimum number of jumps you need to jump out of the array. By jump out,
	 * it means you can not stay at the end of the array. 
	 * Return -1 if you can not do so. 
	 * 
	 * Assumptions The given array is not null and has length of at
	 * least 1. Examples {1, 3, 2, 0, 2}, the minimum number of jumps needed is
	 * 3 (jump to index 1 then to the end of array, then jump out) {3, 2, 1, 1,
	 * 0}, you are not able to jump out of array, return -1 in this case.
	 */

	// M[i] stands the minimum steps from array[i] to jump out of array
	// we can reduce this problem to jump to array[n], n = array.length;
	// base case:
	// first, we might need to scan the array from right to left.
	public int task5_minJump(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		int[] M = new int[n + 1];
		M[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (array[i] == 0) {
				M[i] = Integer.MAX_VALUE;
			} else if (i + array[i] >= n) {
				M[i] = 1;
			} else {
				int curMin = Integer.MAX_VALUE;
				for (int j = i + 1; j < n && j <= i + array[i]; j++) {
					curMin = Math.min(curMin, M[j]);
				}
				if (curMin == Integer.MAX_VALUE) {
					M[i] = Integer.MAX_VALUE;
				} else {
					M[i] = 1 + curMin;
				}
			}
		}
		if (M[0] == Integer.MAX_VALUE) {
			return -1;
		}

		return M[0];
	}
	
}
