package dynamic_programming;


public class Set3_LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
	 */
	
	public static int longestIncreasingSubsequenceDP(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		int len = input.length;
		int[] f = new int[len];
		//f[i] stands for the longest length of increasing subsequence ending with input[i]
		f[0] = 1;
		for (int i = 1; i < len; i++) {
			f[i] = 1;
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i]) {
					f[i] = Math.max(f[i], f[j] + 1);
				}
			}
		}
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			maxLen = Math.max(maxLen, f[i]);
		}
		return maxLen;
	}
	
	public static int longestIncreasingsubsequenceOPT(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		int len = input.length;
		int[] tailTable = new int[len];
		tailTable[0] = input[0];
		int tailTableLen = 1;
		for (int i = 1; i < len; i++) {
			int curElem = input[i];
			System.out.println("curElem = " + curElem);
			if (curElem < tailTable[0]) {
				tailTable[0] = curElem;
			} else if (curElem >= tailTable[tailTableLen-1]) {
				// allow duplicate
				tailTable[tailTableLen] = curElem;
				tailTableLen ++;
			} else {
				//between tailTable[0] and tailTable[endIndex]
				int ceilIndex = getCeilIndex(tailTable, tailTableLen - 1, curElem);
				tailTable[ceilIndex] = curElem;
			}
		}
		
		return tailTableLen;
	}
	
	//get the first element > elem (strictly >)
	public static int getCeilIndex(int[] A,  int end, int elem ) {
		int start = 0;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (A[mid] == elem) {
				start ++;
			} else if (elem < A[mid]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		//if there is only one element in A, which start = end
		if (end == 0) {
			if (A[0] > elem) {
				return 0;
			}
		}
		return start + 1;
	}
	
	public static void test() {
		int[] input = {10,1,11,2,12,3,11};
		int maxLen = longestIncreasingsubsequenceOPT(input);
		int maxLen2 = longestIncreasingSubsequenceDP(input);
		System.out.println("maxLen = " + maxLen);
		System.out.println("maxLen2 = " + maxLen2);
	}

}
