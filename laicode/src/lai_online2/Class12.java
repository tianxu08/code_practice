package lai_online2;

public class Class12 {
	/*
	 * task1
	Longest Ascending SubArray
	Easy
	DP
	Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

	Assumptions

	The given array is not null
	Examples

	{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.

	{1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.
	*/
	public int task1_longest(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 0;
		}
		if (array.length == 1) {
			return 1;
		}
		int curLen = 1;
		int maxLen = Integer.MIN_VALUE; // global max
		for (int i = 1; i < array.length; i++) {
			if (array[i] > array[i - 1]) {
				curLen++;
			} else {
				curLen = 1;
			}
			maxLen = Math.max(curLen, maxLen);
		}
		return maxLen;

	}

	/*
	 * task2
	Max Product Of Cutting Rope
	Fair
	DP
	Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.

	Assumptions

	n >= 2
	Examples

	n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
	* 
	*/
	// DP: M[i] stands the max product of rope cutting at least once.
	// base case: M[1] = 0; if len = 1, we cannot cut it. so the M[1] = 0
	// induction rule: M[i] = max { max(M[j], j) * max(M[i - j], i - j)} (j >= 1
	// && j < i)
	public int task2_maxProduct(int n) {
		// write your solution here
		if (n <= 1) {
			return 0;
		}
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 1; i <= n; i++) {
			int curMax = 0;
			for (int j = 1; j <= i / 2; j++) {
				curMax = Math.max(curMax,
						Math.max(j, M[j]) * Math.max(i - j, M[i - j]));
			}
			M[i] = curMax;
		}

		return M[n];

	}
	
	/*
	 * task3
	Array Hopper I
	Fair
	DP
	Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). Determine if you are able to reach the last index.

	Assumptions

	The given array is not null and has length of at least 1.
	Examples

	{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

	{2, 1, 1, 0, 2}, we are not able to reach the end of array
	*/
	 // M[i] stands whether array[i] can reach the end of the array, names array[n- 1]. n = array.length
	  // base: M[n-1] true;
	  // induction rule: M[i] = 
	  // 1 if i + array[i] >= n-1  M[i] = true;
	  // 2 for j > i && j <= i + array[i] if M[j] == true, M[i] = true 
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
}
