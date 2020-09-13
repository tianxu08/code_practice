package sortbynum;

import java.util.Map.Entry;
import java.util.*;
import ds.Debug;
import ds.ListNode;
import ds.TreeNode;


public class Task351_400 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test351();
		test354();
//		test356();
//		test357();
//		test358();
//		test360();
//		test361();
//		test363();
//		test365();
//		test366();
//		test367();
//		test368();
//		test369();
//		test371();
//		test372();
//		test373();
//		test375();
//		test376();
//		test377();
//		test378();
//		test383();
//		test387();
//		test389();
//		test397();
//		test400();
		
	}

	/*
	 * task351 Android Unlock Patterns
	 * The optimization idea is that 
	 * 1,3,7,9 are symmetric, 
	 * 2,4,6,8 are also symmetric. 
	 * Hence we only calculate one among each group and multiply by 4.
	 */
	
	public static void test351() {
		int m = 1, n = 2;
		int rev = numberOfPatterns(m, n);
		System.out.println("rev = " + rev);
	}

	public static int numberOfPatterns(int m, int n) {
		// skip array is the number to skip between two numbers in a pair
		/*
		 * 1 2 3
		 * 4 5 6
		 * 7 8 9
		 */
		int[][] skip = new int[10][10];
		skip[1][3] = skip[3][1] = 2;
		skip[1][7] = skip[7][1] = 4;
		skip[3][9] = skip[9][3] = 6;
		skip[7][9] = skip[9][7] = 8;
		skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
		
		boolean[] visited = new boolean[10];
		int result = 0;
		// DFS each length from m to n
		for(int len = m; len <= n; len++) {
			result += DFS(visited, skip, 1 , len - 1 ) * 4;
			result += DFS(visited, skip, 2, len - 1) * 4;
			result += DFS(visited, skip, 5, len - 1);
		}
		return result;
	}
	
	public static int DFS(boolean[] visited, int[][] skip, int cur_pos, int remain) {
		if (remain < 0) {
			return 0;
		}
		if (remain == 0) {
			return 1;
		}
		visited[cur_pos] = true;
		int result = 0;
		for(int i = 1; i <= 9; i ++) {
			// if visited[i] is NOT visited and (two numbers are adjacent or skip number is already visited) 
			if (!visited[i] && (skip[cur_pos][i] == 0 || visited[skip[cur_pos][i]])) {
				result += DFS(visited, skip, i, remain - 1);
			}
		}
		// backtracking
		visited[cur_pos] = false;
		return result;
	}
	

	
	/*
	 * task352 Data Stream as Disjoint Intervals
	 */
	
	

	/*
	 * task353 Design Snake Game
	 */

	/*
	 * task354 Russian Doll Envelopes
	 * [[5,4],[6,4],[6,7],[2,3]] => 
	 *  the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
	 */
	public static void test354() {
		int[][] envelops = {
				{5,4},
				{5,5},
				{6,4},
				{6,7},
				{2,3}
		};
		int rev = task354_maxEnvelps_opt(envelops);
		System.out.println("rev = " + rev);
		
		int[] test1 = {1,3, 5, 7, 9};
		int rev2 = Arrays.binarySearch(test1, 2);
		System.out.println("rev2: " + rev2);
	}
	
	/*
	 * Time: O(n * n)
	 * 
	 * 
	 */
	
	public static int task354_maxEnvelopes(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0) {
			return 0;
		}
		
		Comparator<int[]> myComp = new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[0] == o2[0]) {
					if (o1[1] == o2[1]) {
						return 0;
					}
					return o1[1] < o2[1] ? -1 : 1;
				}
				return o1[0] < o2[0] ? -1 : 1;
			}
		};
		
		Debug.printMatrix(envelopes);
		
		Arrays.sort(envelopes, myComp);
		
		Debug.printMatrix(envelopes);
		
		// run a LIS
		int maxLen = 1;
		int n = envelopes.length;
		int[] dp = new int[n];
		dp[0] = 1;
		// dp[i] is the num of max envelops 
		for(int i = 1; i < n; i++) {
			int[] cur = envelopes[i];
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				int[] prev = envelopes[j];
				if (cur[0] > prev[0] && cur[1] > prev[1] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					maxLen = Math.max(maxLen, dp[i]);
				}
			}
		}
		
        return maxLen;
    }
	
	
	/**
	 * optimization Time: O(n * log n)
	 * @param envelopes
	 * @return
	 */
	public static int task354_maxEnvelps_opt(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0 || envelopes[0] == null
				|| envelopes[0].length != 2)
			return 0;
		
		// Sort the array. Ascend on width and descend on height if width are same.
		// Find the longest increasing subsequence based on height.
		
		Arrays.sort(envelopes, (a, b)-> { 
			if (a[0] == b[0]) {
				return b[1] - a[1];
			}
			return a[0] - b[0];
		});
		
		int tailTable[] = new int[envelopes.length];
		tailTable[0] = envelopes[0][1];
		int len = 1;
		for (int i = 1; i < envelopes.length; i++) {
			if (envelopes[i][1] < tailTable[0]) {
				tailTable[0] = envelopes[i][1];
			}  else if (envelopes[i][1] > tailTable[len - 1]) {
				tailTable[len] = envelopes[i][1];
				len ++;
			} else {
				// insert position
				int index = Arrays.binarySearch(tailTable, 0, len, envelopes[i][1]);
				if (index < 0) {
					index = -(index + 1);
				}
				tailTable[index] = envelopes[i][1];
			}
			
		}
		return len;
	}

	/*
	 * task355 Design Twitter
	 */

	/*
	 * task356 Line Reflection
	 * 
	 * Given n points on a 2D plane, 
	 * find if there is such a line parallel to y-axis that reflect the given points.
	 * Example 1:
	 * Given points = [[1,1],[-1,1]], return true.
	 * Example 2:
	 * Given points = [[1,1],[-1,-1]], return false.
	 * 问我们存不存在一条平行于y轴的直线，使得所有的点关于该直线对称。
	 * 题目中的提示给的相当充分，我们只要按照提示的步骤来做就可以解题了。
	 * 首先我们找到所有点的横坐标的最大值和最小值，那么二者的平均值就是中间直线的横坐标，
	 * 然后我们遍历每个点，如果都能找到直线对称的另一个点，则返回true，反之返回false
	 */
	public static void test356() {
		int[][] points = {
				{0,0},
				{0,0}
		};
		
		boolean rev = task356_isReflected(points);
		System.out.println("rev = " + rev);
	}
	
	/**
	 * 
	 * @param points
	 * @return
	 */
	public static boolean task356_isReflected(int[][] points) {
		// get the min and max x-values of all points
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		
		HashSet<Point> set = new HashSet<Point>();
		
		for(int i = 0; i < points.length; i ++) {
			int x = points[i][0];
			int y = points[i][1];
			minX = Math.min(minX, x);
			maxX = Math.max(maxX, x);
			set.add(new Point(x, y));
		}
		int min_plus_max = minX + maxX;
		
		for(int i = 0; i < points.length; i ++) {
			int x = points[i][0];
			int y = points[i][1];
			
			int rX = min_plus_max - x;
			int rY = y;
			Point reflect = new Point(rX, rY);
			
			if (!set.contains(reflect)) {
				return false;
			}
		}
		return true;
	}
	
	public static class Point {
		public int x;
		public int y;
		
		public Point(int _x, int _y) {
			// TODO Auto-generated constructor stub
			this.x = _x;
			this.y = _y;
		}
		
		// override hashCode
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.x * 101 + this.y;
		}
		
		// override equals
		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (! (obj instanceof Point)) {
				// compare value
				return false;
			}
			Point myPoint = (Point) obj;
			return this.x == myPoint.x && this.y == myPoint.y;
		}
	}
	
	

	/*
	 * task357 Count Numbers with Unique Digits
	 * 
	 * A direct way is to use the backtracking approach.
	 * Backtracking should contains three states which are 
	 * (the current number, number of steps to get that number 
	 * and a bitmask which represent which number is marked as visited so far in the current number). 
	 * Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
	 * This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
	 * Let f(k) = count of numbers with unique digits with length equals k.
	 * f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 
	 * because a number cannot start with 0].
	 */
	public static void test357() {
		int n = 3;
		int rev = task357_counterNumberWithUniqueDigits(n);
		System.out.println("rev = " + rev);
	}
	
	public static int task357_counterNumberWithUniqueDigits(int n) {
		if (n == 0) {
			return 1;
		}
		int[] dp = new int[n + 1];
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 9;
		for(int i = 2; i <= n; i ++) {
			int cur = 9;
			int max = 9;
			// calculate f[k] = 9 * 9 * 8 * ... (9 - k + 2).  the number of digits is k 
			for(int j = 1; j < i; j ++) {
				cur *= max;
				max --;
			}
			
			f[i] = cur;
		}
		dp[0] = f[0];
		for(int i = 1; i <= n; i ++) {
			dp[i] = dp[i - 1] + f[i];
		}
		System.out.println(Arrays.toString(f));
		System.out.println();
		System.out.println(Arrays.toString(dp));
		
		return dp[n];
		
	}
	

	/*
	 * task358 Rearrange String k Distance Apart
	 * s = "aabbcc", k = 3
	 * Result: "abcabc"
	 * The same letters are at least distance 3 from each other.
	 * 
	 */
	public static void test358() {
		String s = "aabbcc";
		int k = 3;
		String rev = task358_rearrangeString(s, k);
		System.out.println("rev = " + rev);
	}
	
	public static String task358_rearrangeString(String s, int k) {
		// <char, count>
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i ++) {
			char ch = s.charAt(i);
			if (map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
		}
		
		// use priority queue and waitqueue 
		PriorityQueue<Entry<Character, Integer>> maxHeap = 
				new PriorityQueue<Map.Entry<Character,Integer>>(s.length(), new Comparator<Map.Entry<Character, Integer>>() {
					@Override
					public int compare(Entry<Character, Integer> o1,
							Entry<Character, Integer> o2) {
						// TODO Auto-generated method stub
						if (o1.getValue() == o2.getValue()) {
							return 0;
						}
						return o1.getValue() > o2.getValue() ? -1 : 1;
					}
		});

		Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<Map.Entry<Character,Integer>>();
		maxHeap.addAll(map.entrySet());
		
		StringBuilder stb = new StringBuilder();
		// greedy algorithm
		while(!maxHeap.isEmpty()) {
			Entry<Character, Integer> curEntry = maxHeap.poll();
			stb.append(curEntry.getKey());
			curEntry.setValue(curEntry.getValue() - 1);
			waitQueue.offer(curEntry);
			
			// for the first k - 1 chars
			if (waitQueue.size() < k) {
				continue;
			}
			
			// NOT for the first k - 1 chars
			// poll from wait queue
			Entry<Character, Integer> entry = waitQueue.poll();
			if (entry.getValue() > 0) {
				maxHeap.offer(entry);
			}	
		}
		
		return stb.length() == s.length() ? stb.toString() : "";
	}


	/*
	 * task359 Logger Rate Limiter
	 * see Task359_LoggerRateLimiter
	 */

	/*
	 * task360 Sort Transformed Array
	 * 
	 * 数学题： 一元二次函数 画一个图就解决
	 * 
	 */
	public static void test360(){
		int[] nums = {-4, -2, 2, 4};
		int a = -1, b = 3, c = 5;
		int[] result = task360_sortedTransformedArray(nums, a, b, c);
		System.out.println(Arrays.toString(result));
	}
	
	
	public static int[] task360_sortedTransformedArray(int[] nums, int a, int b, int c) {
		int len = nums.length;
		int[] result = new int[nums.length];
		if (a == 0) {
			//f(x) = bx + c
			if (b >= 0) {
				for(int i = 0; i < len; i ++) {
					result[i] = b* nums[i] + c;
				}
			} else {
				// b < 0
				for(int i = 0;i < len; i ++) {
					result[len - 1 - i] = b * nums[i] + c;
				}
			}
		} else {
			// a != 0
			double axis = (double)(-b/(a *2.0));
			int nearestIdx = task360_findClosestElem(nums, axis);
			if (a > 0) {
				int index = 0;
				int elem = nums[nearestIdx];
				result[index ++] = elem * elem * a + b * elem + c;
				int i = nearestIdx - 1;
				int j = nearestIdx + 1;
				while(i >= 0 && j <= len - 1) {
					if (Math.abs(nums[i] - axis) < Math.abs(nums[j] - axis)) {
						elem = nums[i];
						result[index ++] = a * elem * elem + b * elem + c;
						i --;
					} else {
						elem = nums[j];
						result[index ++] = a * elem * elem + b * elem + c;
						j++;
					}
				}
				while(i >= 0) {
					elem = nums[i];
					result[index ++] = a * elem * elem + b * elem + c;
					i --;
				}
				while(j <= len - 1) {
					elem = nums[j];
					result[index ++] = a * elem * elem + b * elem + c;
					j++;
				}
			} else {
				// a < 0
				int index = len - 1;
				int elem = nums[nearestIdx];
				result[index --] = elem * elem * a + b * elem + c;
				
				int i = nearestIdx - 1;
				int j = nearestIdx + 1;
				while(i >= 0 && j <= len - 1) {
					if (Math.abs(nums[i] - axis) < Math.abs(nums[j] - axis)) {
						elem = nums[i];
						result[index --] = a * elem * elem + b * elem + c;
						i --;
					} else {
						elem = nums[j];
						result[index --] = a * elem * elem + b * elem + c;
						j++;
						
					}
				}
				while(i >= 0) {
					elem = nums[i];
					result[index --] = a * elem * elem + b * elem + c;
					i --;
				}
				while(j <= len - 1) {
					elem = nums[j];
					result[index --] = a * elem * elem + b * elem + c;
					j++;
				}
			}
		}
		return result;
	}
	
	public static int task360_findClosestElem(int[] nums, double axis) {
		int smallestLargerIdx = task360_SmallestLarger(nums, axis);
		int largestSmallerIdx = task360_LargestSmaller(nums, axis);
		return Math.abs(nums[smallestLargerIdx] - axis) < Math.abs(nums[largestSmallerIdx] - axis) ? 
				smallestLargerIdx : largestSmallerIdx;
	}
	
	public static int task360_SmallestLarger(int[] nums, double axis) {
		int start = 0, end = nums.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (nums[mid] - axis == 0.0 ) {
				return mid;
			} else if (nums[mid] > axis) {
				end = mid;
			} else {
				start = mid;
			}
		}
		
		if (nums[start] > axis) {
			return start;
		} else {
			return end;
		}
	}
	
	public static int task360_LargestSmaller(int[] nums, double axis) {
		int start = 0, end = nums.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (nums[mid] - axis == 0.0) {
				return mid;
			} else if (nums[mid] > axis) {
				end = mid;
			} else {
				start = mid;
			}
		}
		
		if (nums[end] < axis) {
			return end;
		} else {
			return start;
		}
	}
	
	

	/*
	 * task361 Bomb Enemy
	 * 
	 * 
	 */
	public static void test361() {
		char[][] grid = {
				"0E00".toCharArray(), 
				"E0WE".toCharArray(), 
				"0E00".toCharArray()
		};
		int rev = task361_maxKilledEnemies(grid);
		System.out.println("rev = " + rev);
	}
	
	public static int task361_maxKilledEnemies(char[][] grid) {
		
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		int[][] left2Right = new int[rLen][cLen];
		int[][] right2Left = new int[rLen][cLen];
		int[][] up2Down = new int[rLen][cLen];
		int[][] down2Up = new int[rLen][cLen];
		
		/*Left to Right*/
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (j == 0) {
					if (grid[i][j] == 'E') {
						left2Right[i][j] = 1;
					} else {
						left2Right[i][j] = 0;
					}
					
				} else {
					if (grid[i][j] == 'E' ) {
						left2Right[i][j] = left2Right[i][j - 1] + 1;
					} else if (grid[i][j] == '0') {
						left2Right[i][j] = left2Right[i][j - 1];
					} else {
						// grid[i][j] == 'W'
						left2Right[i][j] = 0;
					}
				}
			}
		}
		
		// right to left
		for(int i = 0; i < rLen; i ++) {
			for(int j = cLen - 1; j >= 0; j --) {
				if (j == cLen - 1) {
					if (grid[i][j] == 'E') {
						right2Left[i][j] = 1;
					} else {
						right2Left[i][j] = 0;
					}
				} else {
					if (grid[i][j] == 'E' ) {
						right2Left[i][j] = right2Left[i][j +1] + 1;
					} else if (grid[i][j] == '0') {
						right2Left[i][j] = right2Left[i][j + 1];
					} else {
						// grid[i][j] == 'W'
						right2Left[i][j] = 0;
					}
				}
			}
		}
		
		// up to down
		for(int j = 0; j < cLen; j ++) {
			for(int i = 0; i < rLen; i ++) {
				if (i == 0) {
					// the fisrt row
					if (grid[i][j] == 'E') {
						up2Down[i][j] = 1;
					} else {
						up2Down[i][j] = 0;
					}
				} else {
					if (grid[i][j] == 'E') {
						up2Down[i][j] = up2Down[i - 1][j] + 1;
					} else if (grid[i][j] == '0') {
						up2Down[i][j] = up2Down[i - 1][j];
					} else {
						up2Down[i][j] = 0;
					}
				}
			}
		}
		
		// down to up
		for(int j = 0; j < cLen; j ++) {
			for(int i = rLen - 1; i >= 0; i--) {
				if (i == rLen - 1) {
					if (grid[i][j] == 'E') {
						down2Up[i][j] = 1;
					} else {
						down2Up[i][j] = 0;
					}
				} else {
					if (grid[i][j] == 'E') {
						down2Up[i][j] = down2Up[i + 1][j] + 1;
					} else if (grid[i][j] == '0') {
						down2Up[i][j] = down2Up[i + 1][j];
					} else {
						down2Up[i][j] = 0;
					}
				}
			}
		}
		
		// traverse the four matrix
		int result = 0;
		for(int i = 0; i < rLen; i++) {
			for(int j = 0; j < cLen; j ++) {
				if (grid[i][j] == '0') {
					int cur = left2Right[i][j] + right2Left[i][j] + up2Down[i][j] + down2Up[i][j];
					result = cur > result ? cur : result;
				}
			}
		}
		
		return result;
		
	}
	
	

	/*
	 * task362 Design Hit Counter
	 */

	/*
	 * task363 Max Sum of Rectangle No Larger Than K
	 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
	 * Example:
	 * Given matrix = [
	 * 		[1,  0, 1],
	 * 		[0, -2, 3]
	 * ]
	 * k = 2
	 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 
	 * and 2 is the max number no larger than k (k = 2).
	 * 
	 * Time: O(n^3 * log n)
	 */
	public static void test363() {
		int[][] matrix = {
				{1,0, 1},
				{0, -2, 3}
		};
		int k = 2;
		int res = task363_maxSubmatrix(matrix, k);
		System.out.println("res = " + res);
	}
	
	/**
	 * 
	 * @param matrix
	 * @param k
	 * @return
	 * Time: O(n^3 * log n)
	 */
	public static int task363_maxSubmatrix(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int maxSum = Integer.MIN_VALUE;
		for (int leftB = 0; leftB < cLen; leftB++ ) {  // O(n)
			int[] curSumArr = new int[rLen];
			for (int rightB = leftB; rightB < cLen; rightB++) { // O(n)
				for (int i = 0; i < rLen; i++) {
					curSumArr[i] += matrix[i][rightB];
				}
				// in curSum[i] find the largest sum of sunarray smaller than k
				int curMax = task363_largestSubarraySumSmallerThanK(curSumArr, k); // O(n * log n)
				maxSum = Math.max(maxSum, curMax);
			}
		}
		return maxSum;
	}

	
	/*
	 * http://howcanfix.com/40349/largest-sum-of-contiguous-subarray-no-larger-than-k
	 * http://www.cnblogs.com/grandyang/p/5617660.html
	 * https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
	 * 
	 * Given an array of integers A and an integer k, 
	 * find a subarray that contains the largest sum, 
	 * subject to a constraint that the sum is less than k?
	 * 
	 * store the cumulative sum in cum
	 * then this problem reduces to finding i, j such that i < j and cum[j] - cum[i] is as close to 
	 * k but lower or equal than it. 
	 * 
	 * cum[j] - cum[i] <= k ==> cum[j] - k <= cum[i]  ==> in the set, find the smallest larger or equal to
	 * cum[j] - k
	 * scan from left to right. 
	 * put the cum[i] values that have encountered till now into a treeSet.
	 * when processing cum[j], retrieve the smallest larlger or equal of cum[j] - k
	 * this can be finished in O(log n) in treeSet. by call treeSet.ceiling(cum[j] - k)
	 * 
	 * So the total time is : O(n * log n)
	 *  
	 */
	public static int task363_largestSubarraySumSmallerThanK(int[] arr, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(0);
		
		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			/*
			 * Returns the least element in this set greater than or equal to the given element, 
			 * or null if there is no such element.
			 */
			Integer ceiling = set.ceiling(sum - k);
			
			if (ceiling != null) {
				max = Math.max(max, sum - ceiling);
			}
			// add the sum into treeSet
			set.add(sum);
		}
		return max;
	}
	
	

	/*
	 * task364 Nested List Weight Sum II
	 * refer to linkedIn package
	 */
	

	/*
	 * task365 Water and Jug Problem
	 * 
	 * 
	 * 
	 * The idea is, if there is c-volume water in smaller jug now, what volumns can we achieved in the next round. 
	 * There are two cases:
	 * we keep the c-volume water in v1 (smaller jug), and refill the v2(bigger jug), 
	 * then, we try to use water in v2 to fill v1, then, the remaining volume in v2 could be a new achieved volume;
	 * Or, we can do the reverse, transfer the c-volume water into v2 first, then,
	 * try to use water in v1 to fill v2, the remaining in v1 will be our new achieved volume.
	 * We need to simplify the numbers x, y, z first; say, x < y. We don’t need to consider those y > 2x; 
	 * because, you can always, dump a few “full” x into y utill there are less than 2x “free” space left. 
	 * Same case for z, that’s why we only consider whether we can achieve z%x.
	 */
	public static void test365() {
		int x = 3, y = 5, z = 4;
		boolean rev = task365_canMeasureWater(x, y, z);
		System.out.println("rev = " + rev);
		
		int GCD = task365_GCD(3, 5);
		System.out.println("==>>>>: " + GCD);
	}
	
	
	public static boolean task365_canMeasureWater(int x, int y, int z) {
        if(z > x + y) return false;
        if(x == 0 || y == 0) return z == x || z == y;
        
        int small = x < y ? x : y;
        int big = x < y ? y : x;
        boolean[] visited = new boolean[small + 1];
        
        return task365_dfs(small, big % small + small, z % small, small, visited);
    }
    
    // v1 - the volume of smaller jug, v2 - volume of bigger one; c is the curt water in smaller; visited record, the water volume we achieved before
    private static boolean task365_dfs(int v1, int v2, int target, int c, boolean[] visited) {
        int c1 = (v2 - c) % v1;
        int c2 = v1 - (v2 - c) % v1;
        if(c1 == target || c2 == target) return true;
        boolean canAchieved = false;
        if(!visited[c1]) {
            visited[c1] = true;
            canAchieved = canAchieved || task365_dfs(v1, v2, target, c1, visited);
        }
        if(c2 != c1 && !visited[c2] && !canAchieved) {
            visited[c2] = true;
            canAchieved = canAchieved || task365_dfs(v1, v2, target, c2, visited);
        }
        if(canAchieved) return true;
        return false;
    }
	
	public static boolean task365_canMeasureWater2(int x, int y, int z) {
		// limit brought by the statement that water is finallly in one or both
		// buckets
		if (x + y < z)
			return false;
		// case x or y is zero
		if (x == z || y == z || x + y == z)
			return true;

		// get GCD, then we can use the property of Bézout's identity
		return z % task365_GCD(x, y) == 0;
	}

	public static int task365_GCD(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/*
	 * task366 Find Leaves of Binary Tree
	 */
	public static void test366() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		List<List<Integer>> result = task366_findLeaves(n1);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task366_findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		task366_getHeight(root, result);
		return result;
	}
	
	public static int task366_getHeight(TreeNode node, List<List<Integer>> result) {
		if (node == null) {
			return -1;
		}
		
		int leftHeight = task366_getHeight(node.left, result);
		int rightHeight = task366_getHeight(node.right, result);
		
		
		int height = Math.max(leftHeight, rightHeight) + 1;
		// if it's the first time to reach this height
		if (height >= result.size()) {
			result.add(new ArrayList<Integer>());
		}
		// add the value to the layer
		result.get(height).add(node.val);
		return height;
	}
	

		
	
	

	/*
	 * task367 Valid Perfect Square
	 * input: positive integer
	 * output: true if it's perfect square
	 * 
	 * using binary to get sqrt(x)
	 * then sqrt(x) * sqrt(x) compare with x 
	 * if equals, true
	 * otherwise, false
	 * 
	 * follow up
	 * sqrt(double)
	 * 
	 */
	public static void test367() {
		int num = 16;
		boolean rev = task367_isPerfectSquare(num);
		System.out.println("rev = " + rev);
	}	
	public static boolean task367_isPerfectSquare(int num) {
		if (num <= 0) {
			return false;
		}
		int sqrt = task367_sqrt(num);
		System.out.println("sqrt = " + sqrt);
		return sqrt*sqrt == num;
		
	}
	
	public static int task367_sqrt(int num) {
		int start = 1, end = num;
		while(start + 1 < end) {
			int mid = start + (end - start)/2;
			if (num/mid == mid) {
				return mid;
			} else if (num/mid < mid) {
				// mid*mid > num
				end = mid;
			} else {
				start = mid;
			}
		}
		if (num/start == start) {
			return start;
		} else {
			return end;
		}
	}
	

	/*
	 * task368 Largest Divisible Subset
	 */
	public static void test368() {
		int[] nums = {4,8,10, 240};
		List<Integer> rev = task368_largestDivisibleSubset(nums);
		
	}
	
	/*
	 * Similar to LIS
	 * Time: O(n^2)
	 * Space: O(n)
	 */
	public static List<Integer> task368_largestDivisibleSubset(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		int n = nums.length;
		if (n == 1) {
			result.add(nums[0]);
			return result;
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		Arrays.sort(nums);
		Debug.printArray(dp);
		int maxIdx = 0;
		int maxLen = Integer.MIN_VALUE;
		int[] prevIdx = new int[n];
		for(int i = 1; i < n; i ++) {
			int maxPrevIdx = -1;
			int maxPrev = Integer.MIN_VALUE;
			for(int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0 && dp[j] > maxPrev) {
					maxPrevIdx = j;
					maxPrev = dp[j];
				}
			}
			
			if (maxPrevIdx != -1) {
				dp[i] = maxPrev + 1;
				prevIdx[i] = maxPrevIdx;
				
			}
			if (maxLen < dp[i]) {
				maxLen = dp[i];
				maxIdx = i;
			}
		}
		Debug.printArray(dp);
		
		System.out.println("--");
		Debug.printArray(prevIdx);
		// now Compose the result
		System.out.println("maxIdx = " + maxIdx);
		result.add(nums[maxIdx]);
		maxLen --;
		while(maxLen > 0) {
			int prevIndex = prevIdx[maxIdx];
			result.add(nums[prevIndex]);
			maxIdx = prevIndex;
			maxLen --;
		}
		System.out.println("-----------");
		for(Integer i : result) {
			System.out.print(i + " ");
		}
		System.out.println();
		Collections.reverse(result);
		System.out.println("======");
		for(Integer i : result) {
			System.out.print(i + " ");
		}
		System.out.println();
		return result;
	}
	
	
	
	

	/*
	 * task369 Plus One Linked List
	 */
	public static void test369() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		
		n1.next = n2;
		n2.next = n3;
		
		Debug.printList(n1);
		ListNode head = task369_plusOne(n1);
		System.out.println("-------------");
		Debug.printList(head);
	}
	
	public static ListNode task369_plusOne(ListNode head) {
		if (head == null) {
			return new ListNode(1);
		}
		ListNode revHead = task369_reverse(head);
		int carry = 1;
		ListNode cur = revHead;
		ListNode tail = null;
		while(cur != null) {
			int sum = cur.val + carry;
			cur.val = sum % 10;
			carry = sum / 10;
			tail = cur;
			cur = cur.next;
		}
		if (carry == 1) {
			ListNode n = new ListNode(1);
			tail.next = n;
		}
		
		return task369_reverse(revHead);
		
	}
	
	public static ListNode task369_reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = task369_reverse(next);
		head.next = null;
		next.next = head;
		return newHead;
	}
	
	

	/*
	 * task370 Range Addition
	 * !!!
	 */
	public static int[] getModifiedArray(int length, int[][] updates) {
		int[] res = new int[length];
		for (int[] update : updates) {
			int value = update[2];
			int start = update[0];
			int end = update[1];

			res[start] += value;

			if (end < length - 1)
				res[end + 1] -= value;

		}

		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += res[i];
			res[i] = sum;
		}

		return res;
    }
	

	/*
	 * task371 Sum of Two Integers
	 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
	 * Example:
	 * Given a = 1 and b = 2, return 3.
	 */
	public static void test371() {
		int a = 20, b = 30;
		int sum = task371_getSum(a, b);
		System.out.println("sum = " + sum);
		System.out.println(Integer.toBinaryString(sum));
		int minus = task371_substract(a, b);
		System.out.println("minus = " + minus);
	}
	
	public static int task371_getSum(int a, int b) {
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		while((a & b) != 0) {
			int temp1 = a^b;
			int temp2 = (a&b) << 1;
			a = temp1;
			b = temp2;
		}
		return a^b;	
	}
	
	public static int task371_substract(int a, int  b) {
		int c = -b;
		while((a&c) != 0) {
			int temp1 = a^c;
			int temp2 = (a&c) << 1;
			a = temp1;
			c = temp2;
		}
		return a^c;
	}

	/*
	 * task372 Super Pow
	 * !!! detaisl
	 */
	public static void test372() {
		int num = 10000 %1337;
		int num2 = 2048 - 1337;
		System.out.println("num =" + num);
		System.out.println("num2 = " + num2);
		
		
		
	}
	
	public static int task372_superPow(int a, int[] b) {
		int[] buf = new int[10];
        buf[0] = 1;
        a = a%1337;
        for(int i =1;i<10;i++){               
        	//since b is large, and b[i] is between 0 and 9, 
        	// which reuse several times, we build pattern for them
            buf[i] = (buf[i-1]*a)%1337;
        }
        int ans = buf[b[0]], temp;
        for(int i = 1;i<b.length;i++){
            ans = (ans*ans)%1337;      
            //calculate ans^10%1337 in 5 lines, since we may have ans^3>Integer.MAX_VALUE.
            temp = ans;
            ans = (ans*ans)%1337;
            ans = (ans*ans)%1337;
            ans = (ans*temp)%1337;
            ans = (ans*buf[b[i]])%1337;
        }
        return ans;
	}
	

	/*
	 * task373 Find K Pairs with Smallest Sums
	 */
	public static void test373() {
		int[] nums1 = {1,2};
		int[] nums2 = {3};
		int k = 3;
		List<int[]> result = task373_ksmallestPairs(nums1, nums2, k);
		
		for(int[] array : result) {
			System.out.println("[  " +  array[0] + "  " + array[1] + "  ]");
		}
	}
	
	public static List<int[]> task373_ksmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> result = new ArrayList<int[]>();
		if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
			return result;
		}
		
		int len1 = nums1.length;
		int len2 = nums2.length;
		
		PriorityQueue<Item> minHeap = new PriorityQueue<Item>(k, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				// TODO Auto-generated method stub
				if (o1.v == o2.v) {
					return 0;
				}
				return o1.v < o2.v ? -1 : 1;
			}
		});
		
		boolean[][] visited = new boolean[len1][len2];
		minHeap.offer(new Item(0, 0, nums1[0] + nums2[0]));
		visited[0][0] = true;
		
		while(k > 0 && !minHeap.isEmpty()) {
			// before heap.poll(), make sure that the heap is NOT empty
			Item cur = minHeap.poll();			
			int[] array = {nums1[cur.x], nums2[cur.y]};
			result.add(array);
			
			if (cur.x + 1 < len1 && !visited[cur.x + 1][cur.y]) {
				int nextX = cur.x + 1;
				int nextY = cur.y;
				minHeap.offer(new Item(nextX, nextY, nums1[nextX] + nums2[nextY]));
				visited[nextX][nextY] = true;
			}
			
			if (cur.y + 1 < len2 && !visited[cur.x][cur.y + 1]) {
				int nextX = cur.x ;
				int nextY = cur.y + 1;
				minHeap.offer(new Item(nextX, nextY, nums1[nextX] + nums2[nextY]));
				visited[nextX][nextY] = true;
			}
			k --;	
		}
		return result;
	}
	
	public static class Item{
		int x ;
		int y;
		int v;
		public Item(int _x, int _y, int _v) {
			this.x = _x;
			this.y = _y;
			this.v = _v;
		}
	}
	

	/*
	 * task374 Guess Number Higher or Lower
	 */
	public static int task374_guessNumber(int n) {
        int start = 1;
        int end = n;
        while(start + 1 < end) {
            int mid =start + (end - start)/2;
            if(guess(mid) == 0) {
                return mid;
            } else if(guess(mid) == -1) {
                // in the left side
                end = mid;
            } else if(guess(mid) == 1){
                start = mid;
            }
        }
        if(guess(start) == 0){
            return start;
        } else {
            return end;
        }
    }
	public static int guess(int num) {
		return -1;
	}
	

	/*
	 * task375 Guess Number Higher or Lower II
	 */
	public static void test375(){
		int n = 10;
		int rev = task375_getMoneyAmount(n);
		System.out.println("rev = " + rev);
	}
	
	public static int task375_getMoneyAmount(int n) {
		// dp[i][j] means the in i..j, the money you need have to guarantee a win. 
		int[][] dp =new int[n + 1][n + 1];
		return helper(dp, 1, n);
	}
	
	public static int helper(int[][] dp, int start, int end) {
		if (start >= end) {
			return 0;
		}
		if (dp[start][end] != 0) {
			return dp[start][end];
		}
		int res = Integer.MAX_VALUE;
		for(int i = start; i <= end; i ++) {
			int temp = i + Math.max(helper(dp, start, i - 1), helper(dp, i + 1, end));
			res = Math.min(temp, res);
		}
		dp[start][end] = res;
		return res;
	}
	

	/*
	 * task376 Wiggle Subsequence
	 * dp[i] the length of longest wiggle subsequence in [0..i]
	 * 
	 * Just explain what is happening here :
	 * For each i, we do not need to compare all the previous conditions and update the max, 
	 * because for both up and down array, it keeps increasing(+0 or +1) with either copying 
	 * from last element or plus 1 operation.
	 * And, if we reduce the space to O(1). 
	 * It would be the exactly same as the most up-voted greedy solution.
	 * 
	 */
	public static void test376() {
		int[] nums = {1,2,3,4,5,6,7};
		int rev = task376_swiggleMaxLength(nums);
		System.out.println("rev = " + rev);
	}
	
	public static int task376_swiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
			return 0;
		}
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        
        up[0] = 1;
        down[0] = 1;
        for	(int i = 1; i < len; i ++) {
        	if (nums[i] > nums[i - 1]) {
				up[i] = down[i - 1] + 1;
				down[i] = down[i - 1];
			} else if (nums[i] < nums[i - 1]) {
				down[i] = up[i - 1] + 1;
				up[i] = up[i - 1];
			} else {
				// equals
				down[i] = down[i - 1];
				up[i] = up[i - 1];
			}
        }
        
        return Math.max(down[len - 1], up[len - 1]);
    }
	
	public static int task376_wiggleMaxLength2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int len = nums.length;
		int up = 1, down = 1;
		for(int i = 1; i < len; i ++) {
			if (nums[i] > nums[i - 1]) {
				up = down + 1;
			} else if (nums[i] < nums[i - 1]) {
				down = up + 1;
			}
		}
		
		return Math.max(up, down);
	        
	}

	/*
	 * task377 Combination Sum IV
	 * 
	 */
	
	/*
	 * comb(n) = sum{comb(n - nums[i])}  0 <= i < nums.length &&  > nums[is]
	 * method1 recursive solution
	 * this will do a lot of repeating calculation. 
	 * we can use DP to optimize, see method2 and 3
	 */
	public static void test377() {
		int[] nums = {1,2,3};
		int target = 32;
		int rev = task377_combinationSum4(nums, target);
		System.out.println("rev = " + rev);
		int rev2 = task377_combinationSum4_dp1(nums, target);
		System.out.println("rev2 = " + rev2);
		int rev3 = task377_combinationSum4_dp2(nums, target);
		System.out.println("rev3 = " + rev3);
	}
	
	public static int task377_combinationSum4(int[] nums, int target) {
		return task377_helper(nums, target);
	}
	
	public static int task377_helper(int[] nums, int target) {
		if (target == 0) {
			return 1;
		}
		int rev = 0;
		for(int i = 0; i < nums.length; i ++) {
			if (target >= nums[i]) {
				rev += task377_helper(nums, target - nums[i]);
			}
		}
		return rev;
	}
	
	/*
	 * method2
	 * 这个是记忆化搜索.. 也可以看作top-down 的DP
	 */
	public static int task377_combinationSum4_dp1(int[] nums, int target) {
		int[] dp = new int[target + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		task377_helper_dp1(nums, target, dp);
		
		return dp[target];
	}
	
	public static int task377_helper_dp1(int[] nums, int target, int[] dp) {
		if (dp[target] != -1) {
			return dp[target];
		}
		int rev = 0;
		for(int i = 0; i < nums.length; i ++) {
			if (target >= nums[i]) {
				rev += task377_helper_dp1(nums, target - nums[i], dp);
			}
		}
		dp[target] = rev;
		return rev;
	}
	
	
	/*
	 * method3 
	 * DP from bottom to up
	 */
	public static int task377_combinationSum4_dp2(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for(int t = 1; t <= target; t ++) {
			// we will get dp[t] in this loop
			// dp[t] = sum {dp[t - nums[i]]}  i >= 0 && i < nums.length && t >= nums[i]
			int curcomb = 0;
			for(int i = 0;i < nums.length ; i ++) {
				if (t >= nums[i]) {
					curcomb += dp[t - nums[i]];
				}
				
			}
			dp[t] = curcomb;
		}
		return dp[target];
	}
	
	
	
	
	

	/*
	 * task378 Kth Smallest Element in a Sorted Matrix
	 */
	public static void test378() {
		int[][] matrix = { 
				{ 1, 5, 9 }, 
				{ 10, 11, 13 }, 
				{ 12, 13, 15 } 
		};
		int k = 9;
		int rev = task378_kthSmallest(matrix, k);
		System.out.println("rev = " + rev);
	}
	
	
	public static int task378_kthSmallest(int[][] matrix, int k) {
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		
		boolean[][] visited = new boolean[rLen][cLen];
		PriorityQueue<Element> pQueue = new PriorityQueue<Element>(k);
		pQueue.add(new Element(0, 0, matrix[0][0]));
		visited[0][0] = true;
		
		int result = Integer.MAX_VALUE;
		while(k > 0) {
			Element cur = pQueue.poll();
			result = cur.val;
			
			if (cur.x + 1 >= 0 && cur.x + 1 < rLen && !visited[cur.x + 1][cur.y]) {
				pQueue.add(new Element(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
				visited[cur.x + 1][cur.y] = true;
			}
			if (cur.y + 1 >= 0 && cur.y + 1 < cLen && !visited[cur.x][cur.y + 1]) {
				pQueue.add(new Element(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
			}
			k --;
		}
		return result;
	}
	
	public static class Element implements Comparable<Element>{
		int x;
		int y;
		int val;
		public Element(int _x, int _y, int _v) {
			this.x = _x;
			this.y = _y;
			this.val = _v;
		}
		@Override
		public int compareTo(Element o) {
			// TODO Auto-generated method stub
			if (this.val == o.val) {
				return 0;
			}
			return this.val < o.val ? -1 : 1;
		}
	}
	

	/*
	 * task379 Design Phone Directory
	 */

	/*
	 * task380 Insert Delete GetRandom O(1)
	 */

	/*
	 * task381 Insert Delete GetRandom O(1) - Duplicates allowed
	 */
	

	/*
	 * task382 Linked List Random Node
	 */
	

	/*
	 * task383 Ransom Note
	 */
	public static void test383() {
		String str1 = "aa";
		String str2 = "aab";
		boolean rev = task383_canConstruct(str1, str2);
		System.out.println("rev = " + rev);
	}
	public static boolean task383_canConstruct(String ransomNote, String magazine) {
		int[] count = new int[256];
		for(int i = 0; i < magazine.length(); i ++) {
			char curCh = magazine.charAt(i);
			count[curCh]++;
		}
		
		for(int i = 0; i < ransomNote.length(); i++) {
			char curCh = ransomNote.charAt(i);
			count[curCh] --;
			if (count[curCh] < 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * task384 Shuffle an Array
	 */

	/*
	 * task385 Mini Parser
	 */
	

	/*
	 * task386 Lexicographical Numbers
	 */

	/*
	 * task387 First Unique Character in a String
	 * find the first no-repeating character in it and return it's index
	 * if it doesn't exist, return -1
	 */
	public static void test387() {
		String s = "loveleetcode";
		int rev = task387_firstUnique(s);
		System.out.println("rev = " + rev);
	}
	
	public static int task387_firstUnique(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}
		int[] map = new int[26];
		for(int i = 0; i < s.length(); i ++) {
			char c = s.charAt(i);
			map[c - 'a'] ++;
		}
		for(int i = 0; i < s.length(); i ++) {
			char c = s.charAt(i);
			if (map[c - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}
	

	/*
	 * task388 Longest Absolute File Path
	 */

	/*
	 * task389 Find the Difference
	 */
	public static void test389() {
		String s = "abcd";
		String t = "abcde";
		char rev = task389_findTheDifference(s, t);
		System.out.println("rev = " + rev);
	}
	
	
	public static char task389_findTheDifference(String s, String t) {
		char rev = 0;
		int[] map = new int[26];
		for(int i = 0; i < t.length(); i ++) {
			map[t.charAt(i) - 'a'] ++;
		}
		for(int i = 0; i < s.length(); i ++) {
			map[s.charAt(i) - 'a'] --;
		}
		for(int i = 0; i < 26; i++) {
			if (map[i] == 1) {
				rev = (char)(i + 'a');
			}
		}
		return rev;
	}

	/*
	 * task390
	 */

	/*
	 * task391 Perfect Rectangle
	 */
	public static boolean task391_isRectangleCover(int[][] rectangles) {

        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y1 = Integer.MAX_VALUE;
        int y2 = Integer.MIN_VALUE;
        
        HashSet<String> set = new HashSet<String>();
        int area = 0;
        
        for (int[] rect : rectangles) {
            x1 = Math.min(rect[0], x1);
            y1 = Math.min(rect[1], y1);
            x2 = Math.max(rect[2], x2);
            y2 = Math.max(rect[3], y2);
            
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);
            
            String s1 = rect[0] + " " + rect[1];
            String s2 = rect[0] + " " + rect[3];
            String s3 = rect[2] + " " + rect[3];
            String s4 = rect[2] + " " + rect[1];
            
            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }
        
        if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4) return false;
        
        return area == (x2-x1) * (y2-y1);
    }
	

	/*
	 * task392 Is Subsequence
	 * Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.
	 */
	public static boolean task392_isSubsequence(String s, String t) {
        if (s.length() > t.length()){
            return false;
        }
        
        int sPtr = 0;
        for (int tPtr = 0; tPtr < t.length() && sPtr < s.length(); ++tPtr){
            if (s.charAt(sPtr) == t.charAt(tPtr)){
                ++sPtr;
            } 
        }
        
        return sPtr == s.length();
        
    }
	
	/**
	 * 392 follow up
	 * @param s
	 * @return
	 * Follow up:
	 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
	 * and you want to check one by one to see if T has its subsequence. 
	 * In this scenario, how would you change your code?
	 * 
	 * e.g target: abc  S = 
	 */
	
	
	
	
	/*
	 * task393 UTF-8 Validation
	 */

	/*
	 * task394 Decode String
	 * 
	 * impelment!!!
	 */
	public static String task394_decodeString(String s) {
		LinkedList<Character> st = new LinkedList<Character>();
		return null;
	}
	
	public static String task394_helper(String s, int endIdx, LinkedList<Character> st) {
		return null;
	}
	
	
	/*
	 * task395 Longest Substring with At Least K Repeating Characters
	 * More Investigation
	 * 
	 */
	public static int task395_longestSubstring(String s, int k) {
		return -1;
	}

	public int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		return helper(str, 0, s.length(), k);
	}

	private int helper(char[] str, int start, int end, int k) {
		if (end < start)
			return 0;
		if (end - start < k)
			return 0;// substring length shorter than k.
		int[] count = new int[26];
		for (int i = start; i < end; i++) {
			int idx = str[i] - 'a';
			count[idx]++;
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] == 0)
				continue;// i+'a' does not exist in the string, skip it.
			if (count[i] < k) {
				for (int j = start; j < end; j++) {
					if (str[j] == i + 'a') {
						int left = helper(str, start, j, k);
						int right = helper(str, j + 1, end, k);
						return Math.max(left, right);
					}
				}
			}
		}
		return end - start;
	}
	
	
	/*
	 * task397
	 * https://discuss.leetcode.com/topic/58334/a-couple-of-java-solutions-with-explanations
	 */
	public static void test397() {
		int n = 7;
		int rev = task397_integerReplacement(n);
		System.out.println("rev = " + rev);
	}
	public static int task397_integerReplacement(int n) {
		int c = 0;
	    while (n != 1) {
	        if ((n & 1) == 0) {
	            n >>>= 1;
	        } else if (n == 3 || ((n >>> 1) & 1) == 0) {
	            --n;
	        } else {
	            ++n;
	        }
	        ++c;
	    }
	    return c;
	}
	
	public int integerReplacement2(int n) {
	    int c = 0;
	    while (n != 1) {
	        if ((n & 1) == 0) {
	            n >>>= 1;
	        } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
	            --n;
	        } else {
	            ++n;
	        }
	        ++c;
	    }
	    return c;
	}
	
	
	
	/*
	 * task399
	 * Evaluate Division
	 * 
	 */	
	public static double[] task399_calcEquation(String[][] equations, double[] values, String[][] query) {
        Map<String, Map<String, Double>> numMap = new HashMap<String, Map<String, Double>>();
        int i = 0;
        for(String[] str : equations) {
            insertPairs(numMap, str[0], str[1], values[i]);
            insertPairs(numMap, str[1], str[0], 1.0/values[i]);
            i++;
        }

        double[] res = new double[query.length];
        i = 0;
        for(String[] q: query) {
            Double resObj = handleQuery(q[0], q[1], numMap, new HashSet<String>());
            res[i++] = (resObj != null) ? resObj : -1.0;
        }
        return res;
    }

    public static void insertPairs(Map<String, Map<String, Double>> numMap, String num, String denom, Double value) {
        Map<String, Double> denomMap = numMap.get(num);
        if(denomMap == null) {
            denomMap = new HashMap<>();
            numMap.put(num, denomMap);
        }
        denomMap.put(denom, value);
    }

    public static Double handleQuery(String num, String denom, 
    		Map<String, Map<String, Double>> numMap, Set<String> visitedSet) {
        String dupeKey = num+":"+denom;
        if(visitedSet.contains(dupeKey)) return null;
        if(!numMap.containsKey(num) || !numMap.containsKey(denom)) return null;
        if(num.equals(denom)) return 1.0;

        Map<String, Double> denomMap = numMap.get(num);
        visitedSet.add(dupeKey);
        for(String key : denomMap.keySet()) {
            Double res = handleQuery(key, denom, numMap, visitedSet);
            if(res != null) {
                return denomMap.get(key) * res;
            }
        }
        visitedSet.remove(dupeKey);
        return null;
    }
	

	/*
	 * task400
	 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
	 * Input:
	 * 11
	 * Output:
	 * 0
	 * Explanation:
	 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, 
	 * which is part of the number 10.
	 * 
	 * 1 .. 9    len = 1, count = 9 
	 * 10 .. 99  len = 2, count = 90
	 * 100 ... 999  len = 3, count = 900
	 * 1000 ... 9999 len = 4, count = 9000
	 */
	public static void test400() {
		int n = 100;
		int rev = task400_findNthDigit(n);
		System.out.println("rev = " + rev);
	}
	
	public static int task400_findNthDigit(int n) {
		int len = 1;
		long count = 9;
		int start = 1;

		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}

		System.out.println("start = " + start);
		System.out.println("n = " + n);
		start += (n - 1) / len;  // 拿到具体的那个数
		
		// n - 1, since the index is 0 based
		System.out.println("start2 = " + start);
		String s = Integer.toString(start);
		System.out.println("s = " + s);		// 拿到具体的那一个bit
		return Character.getNumericValue(s.charAt((n - 1) % len)); 
	}
}
