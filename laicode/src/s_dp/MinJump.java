package dp;

public class MinJump {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	/*
	 * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
	 * A[i] means the maximum jump distance from that position 
	 * (you can only jump towards the end of the array). 
	 * Determine if you are able to reach the last index.
	 * Assumptions
	 * The given array is not null and has length of at least 1.
	 * Examples
	 * {1, 3, 2, 0, 3}, we are able to reach the end of array
	 * (jump to index 1 then reach the end of the array)
	 * {2, 1, 1, 0, 2}, we are not able to reach the end of array
	 */
	/*
	 * M[i] stands whether array[i] can reach the end of the array, names
	 * array[n- 1]. n = array.length
	 * base: M[n-1] true;
	 * induction rule: M[i] =
	 * 1 if i + array[i] >= n-1 M[i] = true;
	 * 2 for j > i && j <= i + array[i] if M[j] == true, M[i] = true
	 */
	public boolean canJump(int[] array) {
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
	 * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
	 * A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). 
	 * Determine the minimum number of jumps you need to reach the end of array. 
	 * If you can not reach the end of the array, return -1.
	 * Assumptions
	 * The given array is not null and has length of at least 1.
	 * Examples
	 * {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
	 * {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.
	 */
	
	// M[i] stands the minimum jump steps to reach array[n-1]
	// base case: M[n-1] = 0;
	// induction rule: M[i] = 1 + min{M[j]} is all elements that can be reached
	// by 1 jump from i
	public int minJump2(int[] array) {
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
				M[i] = 1;
			} else {
				int curMin = Integer.MAX_VALUE;
				for (int j = i + 1; j < n && j <= i + array[i]; j++) {
					curMin = Math.min(curMin, M[j]);
				}
				if (curMin != Integer.MAX_VALUE) {
					M[i] = 1 + curMin;
				} else {
					M[i] = Integer.MAX_VALUE;
				}
			}
		}
		if (M[0] == Integer.MAX_VALUE) {
			return -1;
		}
		return M[0];
	}
	
	
	/*
	 * Given an array of non-negative integers, you are initially positioned at index 0 of the array. 
	 * A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). 
	 * Determine the minimum number of jumps you need to jump out of the array.
	 * By jump out, it means you can not stay at the end of the array. Return -1 if you can not do so.
	 * Assumptions
	 * The given array is not null and has length of at least 1.
	 * Examples
	 * {1, 3, 2, 0, 2}, the minimum number of jumps needed is 3 
	 * (jump to index 1 then to the end of array, then jump out)
	 * {3, 2, 1, 1, 0}, you are not able to jump out of array, return -1 in this case.
	 */
	
	public int minJump3(int[] array) {
	    // write your solution here
	    if(array == null || array.length == 0) {
	      return -1;
	    }
	    int n = array.length;
	    int[] M = new int[n + 1];
	    M[n] = 1;
	    for(int i = n - 1; i >= 0; i--) {
	      if (array[i] == 0) {
	        M[i] = Integer.MAX_VALUE;
	      } else if(i + array[i] >= n) {
	        M[i] = 1;
	      } else {
	        int curMin = Integer.MAX_VALUE;
	        for(int j = i + 1; j < n && j <= i + array[i]; j++) {
	          curMin = Math.min(curMin, M[j]);
	        }
	        if(curMin == Integer.MAX_VALUE) {
	          M[i] = Integer.MAX_VALUE;
	        } else {
	          M[i] = 1 + curMin;
	        }
	      }
	    }
	    if(M[0] == Integer.MAX_VALUE) {
	      return -1;
	    }
	    
	    return M[0];
	  }

}
