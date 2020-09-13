package sortbynum;
import java.rmi.dgc.Lease;
import java.util.*;
import java.util.Map.Entry;

import mj_linkedin.MyIntervalLinkedIn4.ITNode;

public class Task451_500 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test452();
//		test458();
//		test462();
//		test490();
		test491();
//		test496();
//		test463();
//		test459();
//		test475();
//		test461();
	}
	
	/*
	 * 451 Sort Characters By Frequency
	 */
	public static String task451_frequencySort(String s) {
		if (s == null) {
			return null;
		}
		Map<Character, Integer> map = new HashMap();
		char[] charArray = s.toCharArray();
		int max = 0;
		for (Character c : charArray) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
			max = Math.max(max, map.get(c));
		}

		List<Character>[] array = buildArray(map, max);

		return buildString(array);
	}

	private static List<Character>[] buildArray(Map<Character, Integer> map,
			int maxCount) {
		List<Character>[] array = new List[maxCount + 1];
		for (Character c : map.keySet()) {
			int count = map.get(c);
			if (array[count] == null) {
				array[count] = new ArrayList();
			}
			array[count].add(c);
		}
		return array;
	}

	private static String buildString(List<Character>[] array) {
		StringBuilder sb = new StringBuilder();
		for (int i = array.length - 1; i > 0; i--) {
			List<Character> list = array[i];
			if (list != null) {
				for (Character c : list) {
					for (int j = 0; j < i; j++) {
						sb.append(c);
					}
				}
			}
		}
		return sb.toString();
	}
	
	/*
	 * 452
	 * Minimum Number of Arrows to Burst Balloons
	 */
	public static void test452() {
		int[][] points = {
				{10, 16},
				{2, 8},
				{1, 6},
				{7, 12}
		};
		int rev = task452_findMinArrowShots(points);
		System.out.println("rev = " + rev);
	}
	public static int task452_findMinArrowShots(int[][] points) {
		if (points == null || points.length == 0) {
			return 0;
		}
		if (points.length == 1) {
			return 1;
		}
        final int[][] matrix = points;
        
        // sort the interval by end in ascending order
        // if two ends are same, sort by start in descending order
        Comparator<int[]> myComp = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[1] == o2[1]) {
					return o1[0] > o2[0] ? -1 : 1;
				}
				return o1[1] < o2[1] ? -1 : 1;
			}
		};
		Arrays.sort(matrix, myComp);
		
		int end = Integer.MIN_VALUE;
		// 
		int count = matrix.length;
		for (int i = 0; i < matrix.length; i ++) {
			if (matrix[i][0] > end) {
				// cur.start > end
				end = matrix[i][1];
			} else {
				// cur.start <= end, there is overlap, so we can decrease the number of arrows
				count --;
			}
		}
		return count;
		
    }
	

	/*
	 * 453
	 */
	
	/**
	 * 
	 * @param nums
	 * @return
	 * 
	 * sorting
	 * sort the array. 
	 * 
	 * the last one elem is the largest one. 
	 */
	public static int task453_minMoves_1(int[] nums) {
		Arrays.sort(nums);
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i] - nums[0];
		}
		return sum;
	}
	
	/**
	 * 
	 * @param nums
	 * @return
	 * increasing n - 1 elem equals decrease the largest elem by 1
	 * so the result is sum (a[i] - min)
	 * 
	 */
	public static int task453_minMoves_2(int[] nums) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
			sum += nums[i];
		}
		return sum - min * nums.length;
	}
	
	public static int task453_minMove_2_2(int[] nums) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
		}
		for (int i = 0; i < nums.length; i++) {
			sum += (nums[i] - min);
		}
		return sum;
	}
	
	/*
	 * task454
	 */
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				int sum = C[i] + D[j];
				if (!map.containsKey(sum)) {
					map.put(sum, 0);
				}
				map.put(sum, map.get(sum) + 1);
			}
		}

		int res = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if (map.containsKey(-(A[i] + B[j]))) {
					res += map.get(-(A[i] + B[j]));
				}	
			}
		}

		return res;
	}
	
	
	/*
	 * 455
	 */
	public static int task455_findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);

		int pointG = 0;
		int pointS = 0;

		while (pointG < g.length && pointS < s.length) {
			if (g[pointG] <= s[pointS]) {
				pointG++;
				pointS++;
			} else {
				pointS++;
			}
		}

		return pointG;
	}
	
	/*
	 * task456
	 * 
	 * Given a sequence of n integers a1, a2, ..., an, 
	 * a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
	 * Design an algorithm that takes a list of n numbers as input and checks 
	 * whether there is a 132 pattern in the list.
	 * Note: n will be less than 15,000.
	 * Example 1:
	 * Input: [1, 2, 3, 4]
	 * Output: False
	 * 
	 * Explanation: There is no 132 pattern in the sequence.
	 * Example 2:
	 * Input: [3, 1, 4, 2]
	 * Output: True
	 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
	 * Example 3:
	 * Input: [-1, 3, 2, 0]
	 * Output: True
	 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
	 * 
	 * 维护一个栈和一个变量third，其中third就是第三个数字，也是pattern 132中的2，
	 * 栈里面按顺序放所有大于third的数字，也是pattern 132中的3，
	 * 那么我们在遍历的时候，如果当前数字小于third，即pattern 132中的1找到了，
	 * 我们直接返回true即可，因为已经找到了，注意我们应该从后往前遍历数组。
	 * 如果当前数字大于栈顶元素，那么我们按顺序将栈顶数字取出，赋值给third，然后将该数字压入栈，
	 * 这样保证了栈里的元素仍然都是大于third的，我们想要的顺序依旧存在，进一步来说，
	 * 栈里存放的都是可以维持second > third的second值，其中的任何一个值都是大于当前的third值，
	 * 如果有更大的值进来，那就等于形成了一个更优的second > third的这样一个组合，
	 * 并且这时弹出的third值比以前的third值更大，为什么要保证third值更大，
	 * 因为这样才可以更容易的满足当前的值first比third值小这个条件
	 */
	public static boolean task456_find132pattern(int[] nums) {
		if (nums == null || nums.length < 3) {
			return false;
		}
		// store all elements that greater than the "third"
		Stack<Integer> st = new Stack<Integer>();
		int third = Integer.MIN_VALUE;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] < third) {
				return true;
			} else {
				while (!st.isEmpty() && nums[i] > st.peek()) {
					// update the third
					// we want to make the third as large as possible.
					third = st.pop();
				}
				// push the nums[i] into stack
				st.push(nums[i]);
			}
		}
		return false;
	}
	
	
	/**
	 * 457
	 * 
	 */
	
	/**
	 * There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. They all look the same. If a pig drinks that poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket contains the poison within one hour.
	 * Answer this question, and write an algorithm for the follow-up general case.
	 * Follow-up:
	 * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the "poison" bucket within p minutes? There is exact one bucket with poison.
	 * 
	 * 
	 * @param buckets
	 * @param minutesToDie
	 * @param minutesToTest
	 * @return
	 * 
	 * 
	 * With 2 pigs, poison killing in 15 minutes, and having 60 minutes, we can find the poison in up to 25 buckets in the following way. Arrange the buckets in a 5×5 square:
	 * 
	 * 1  2  3  4  5
	 * 6  7  8  9 10
	 * 11 12 13 14 15
	 * 16 17 18 19 20
	 * 21 22 23 24 25
	 * 
	 * Now use one pig to find the row (make it drink from buckets 1, 2, 3, 4, 5, wait 15 minutes, make it drink from buckets 6, 7, 8, 9, 10, wait 15 minutes, etc). Use the second pig to find the column (make it drink 1, 6, 11, 16, 21, then 2, 7, 12, 17, 22, etc).
	 * Having 60 minutes and tests taking 15 minutes means we can run four tests. If the row pig dies in the third test, the poison is in the third row. If the column pig doesn't die at all, the poison is in the fifth column (this is why we can cover five rows/columns even though we can only run four tests).
	 * 
	 * With 3 pigs, we can similarly use a 5×5×5 cube instead of a 5×5 square and again use one pig to determine the coordinate of one dimension (one pig drinks layers from top to bottom, one drinks layers from left to right, one drinks layers from front to back). So 3 pigs can solve up to 125 buckets.
	 * In general, we can solve up to (⌊minutesToTest / minutesToDie⌋ + 1)pigs buckets this way, so just find the smallest sufficient number of pigs for example like this:
	 * def poorPigs(self, buckets, minutesToDie, minutesToTest):
     *   pigs = 0
     *    while (minutesToTest / minutesToDie + 1) ** pigs < buckets:
     *    pigs += 1
     *  return pigs
     *  
     *  
     *  this solution is pretty smart
	 */
	
	public static void test458() {
		int buckets = 1000;
		int minutesToDie = 15;
		int minutesToTest = 60;
		int rev = task458_poorPigs(buckets, minutesToDie, minutesToTest);
		
	}
	public static int task458_poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int pigs = 0;
		int testRoundFactor = minutesToTest/ minutesToDie + 1;
//		System.out.println("===>>> testRoundFactor: " + testRoundFactor);
		while(Math.pow(testRoundFactor, pigs) < buckets) {
//			System.out.println("!!!! " + Math.pow(testRoundFactor, pigs));
			pigs ++;
		}
		
		return pigs;
	}
	
	
	/*
	 * 459
	 * 
	 */
	public static void test459() {
		String s = "abab";
		
		boolean rev = task459_repeatedSubstringPattern(s);
		System.out.println("rev = " + rev);
		
	}
	public static boolean task459_repeatedSubstringPattern(String s) {
		int len = s.length();
		if (len <= 1) {
			return false;
		}
		
		List<Integer> factorList = new ArrayList<Integer>();
		for(int i = 1; i <= len / 2; i ++) {
			if (len % i == 0) {
				factorList.add(i);
			}
		}
		for(Integer i : factorList) {
			System.out.println(i);
		}
		
		boolean result = false;
		for(int i = factorList.size() - 1; i >= 0; i--) {
			int subLen = factorList.get(i);
//			System.out.println("subLen = " + subLen);
			String pattern = s.substring(0, subLen);
			result |= task459_helper(s, pattern);
		}
		return result;

	}
	public static void test459_2() {
		String s = "aba";
		String p = "a";
		boolean rev = task459_helper(s, p);
		System.out.println("rev = " + rev);
	}
	public static boolean task459_helper(String s, String p) {
		int sLen = s.length();
		int pLen = p.length();
		for(int i = 0; i < sLen; i += pLen) {
			if (i + pLen <= s.length()) {
				if (!s.substring(i, i + pLen).equals(p)) {
					return false;
				}
			} else {
				return false;
			}
			
		}
		return true;
	}
	
	/*
	 * 461
	 */
	public static void test461() {
		int x = 1;
		int y = 4;
		int rev = task461_hammingDistance(x, y);
		System.out.println("rev = " + rev);
	}
	
	public static int task461_hammingDistance(int x, int y) {
        int xory = x^y;
        int count = 0;
        while(xory > 0) {
        	if ((xory & 1) == 1) {
				count ++;
			}
        	xory >>>= 1;
        }
        return count;
    }
	
	/*
	 *  462. Minimum Moves to Equal Array Elements II
	 *  Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
	 *  You may assume the array's length is at most 10,000.
	 *  
	 *  Input:
	 *  [1,2,3]
	 *  
	 *  Output:2
	 *  Explanation:
	 *  Only two moves are needed (remember each move increments or decrements one element):
	 *  
	 *  [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
	 *  
	 */
	
	/**
	 * 
	 * @param nums
	 * @return
	 * 
	 * solution: 
	 * 1: find the median of the array. 
	 * 2: median is the 
	 * 
	 */
	public static void test462() {
		int[] nums = {1};
//		int sedThElem = task462_findKthElem(nums, 1);
//		System.out.println("====>>>sedElem: " + sedThElem);
		int minSum = task462_minMove2(nums);
		System.out.println("==>>> minSum: " + minSum);
		
	}
	
	public static int task462_minMove2(int[] nums) {
		int n = nums.length;
		if (n % 2 == 0) {
			int leftMedian = task462_findKthElem(nums, n / 2 - 1);
			int rightMedian = task462_findKthElem(nums, n /2);
			int sumL = 0, sumR = 0;
			for (int i = 0; i < nums.length; i++) {
				sumL += Math.abs(nums[i] - leftMedian);
				sumR += Math.abs(nums[i] - rightMedian);
			} 
			return Math.min(sumL, sumR);
		} else {
			int median = task462_findKthElem(nums, n / 2);
			System.out.println("==>> median: " + median);
			int sum = 0;
			for (int i = 0; i < nums.length; i++) {
				sum += Math.abs(nums[i] - median);
			}
			return sum;
		}		
	}
	
	public static int task462_findKthElem(int[] nums, int k) {
		
		int n = nums.length;
		return task462_findKthElemHelper(nums, 0, n - 1, k);
	}
	
	public static int task462_findKthElemHelper(int[] nums, int start, int end, int k) {
		if (start <= end) {
			int pivotIdx = task462_partition(nums, start, end);
			if (k == pivotIdx) {
				return nums[pivotIdx];
			} else if (k < pivotIdx) {
				return task462_findKthElemHelper(nums, start, pivotIdx - 1, k);
			} else {
				return task462_findKthElemHelper(nums, pivotIdx + 1, end, k);
			}
		} else {
			return -1;
		}
		
	}
	
	public static int task462_partition(int[] nums, int start, int end) {
		int pivot = nums[end];
		int l = start, r = end - 1;
		
		while(l <= r) {
			if (nums[l] < pivot) {
				l ++;
			} else if (nums[r] >= pivot) {
				r --;
			} else {
				task462_swap(nums, l, r);
				l++;
				r--;
			}
			
		}
		// put the pivot to its position, l is the pivotIdx
		System.out.println("==>> pivotIdx: " + l);
		task462_swap(nums, l, end);
		return l;
	}
	
	public static void task462_swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	

	/*
	 * 463
	 */
	
	public static void test463() {
		int[][] grid = {
				{0,1,0,0},
				{1,1,1,0},
				{0,1,0,0},
				{1,1,0,0}
		};
		
		int[][] grid2 = {
				{0,1,0,0},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
		};

		int rev = task463_islandPerimeter(grid);
		System.out.println("rev = " + rev);
	}
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0,0, -1, 1};
	public static int task463_islandPerimeter(int[][] grid) {
		// check
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		int counter = 0;
		for(int i = 0; i < rLen; i++ ) {
			for(int j = 0; j < cLen;j ++) {
				if (grid[i][j] == 1) {
					for(int k = 0; k < 4; k ++) {
						int nextX = i + dx[k];
						int nextY = j + dy[k];
						if (nextX < 0 || nextX >= rLen || nextY < 0 || nextY >= cLen) {
							counter ++;
						} else {
							// the next element is in bound
							if (grid[nextX][nextY] == 0) {
								counter ++;
							}
						}
					}
				}
			}
		}
		return counter;
	}
	
	/*
	 * loop over the matrix and count the number of islands;
	 * if the current dot is an island, count if it has any right neighbour or down neighbour;
	 * the result is islands * 4 - neighbours * 2
	 */
	public static int task463_islandPerimeter2(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
	
	public static int islandPerimeter(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		int result = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					result += 4;
					if (i > 0 && grid[i - 1][j] == 1)
						result -= 2;
					if (j > 0 && grid[i][j - 1] == 1)
						result -= 2;
				}
			}
		}
		return result;
	}
	
	
	
	/**
	 * 464 can I win
	 */
	public static boolean task464_canIWin(int maxChoosableInteger, int desiredTotal) {
		return false;
	}
	
	
	/*
	 * 474
	 * For now, suppose you are a dominator of m 0s and n 1s respectively. 
	 * On the other hand, there is an array with strings consisting of only 0s and 1s.
	 * Now your task is to find the maximum number of strings that 
	 * you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
	 */
	
	
	
	/*
	 * 475
	 * For each house, find its position between those heaters (thus we need the heaters array to be sorted).
	 * Calculate the distances between this house and left heater and right heater, 
	 * get a MIN value of those two values. Corner cases are there is no left or right heater.
	 * Get MAX value among distances in step 2. It's the answer.
	 * Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.
	 */
	public static void test475() {
		int[] houses = {1,2,3,4};
		int[] heaters = {1,4};
		int rev = findRadius(houses, heaters);
		System.out.println("rev = " + rev);
	}
	
	public static int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		Arrays.sort(houses);
		
		int minRadius = Integer.MIN_VALUE;
		for (Integer h : houses) {
			int index = Arrays.binarySearch(heaters, h);
			if (index < 0) {
				index = -(index + 1);
			}
			// index is the insert position
			int leftDis = index - 1 >= 0 ? h - heaters[index - 1] : Integer.MAX_VALUE;
			int rightDis = index < heaters.length ? heaters[index] - h : Integer.MAX_VALUE;
			
			minRadius = Math.max(minRadius, Math.min(leftDis, rightDis));
		}
		return minRadius;
	}
	
	/*
	 * 476
	 */
	public static int task476_findComplement(int num) {
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
    }
	
	/*
	 * 477
	 * https://discuss.leetcode.com/topic/72104/java-solution-with-explanation
	 * https://discuss.leetcode.com/topic/74655/simple-example-for-the-java-o-n-time-o-1-space-solution
	 * !!!
	 */
	
	
	

	/*
	 * 485
	 */
	public static int task485_findMaxConsecutiveOnes(int[] nums) {
		int n = nums.length;
        int[] dp = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
        	if (nums[i] == 1) {
				dp[i] = 1;
				if (i > 0 && nums[i - 1] == 1) {
					dp[i] = dp[i - 1] + 1;
				}
				max = Math.max(max, dp[i]);
			}
        }
        return max;
    }
	
	
	
	/**
	 * 490. The Maze
	 * 这道题要求球不能停下来，即使碰到destination，必须是碰到wall才能停下来。
	 */
	public static void test490() {
		int[][] maze = {
				{0,0,1,0,0},
				{0,0,0,0,0},
				{0,0,0,1,0},
				{1,1,0,1,1},
				{0,0,0,0,0}
		};
		int[] start = {0, 4};
		int[] destination = {4,4};
		boolean rev = task490_hasPath(maze, start, destination);
		System.out.println("rev = " + rev);
	}
	
	public static boolean task490_hasPath(int[][] maze, int[] start, int[] destination) {
		if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
			return false;
		}
		if (Arrays.equals(start, destination)) {
			return true;
		}
		int rLen = maze.length;
		int cLen = maze[0].length;
		
		boolean[][] visited = new boolean[rLen][cLen];
		
		return task490_dfs(maze, start, destination, visited);
	}
	
	
	public static boolean task490_dfs(int[][] maze, int[] cur, int[] dest, boolean[][] visited) {
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		if (visited[cur[0]][cur[1]]) {
			return false;
		}
		if (Arrays.equals(cur, dest)) {
			return true;
		}
		
		visited[cur[0]][cur[1]] = true;
		for (int[] dir: directions) {
			int nextX = cur[0];
			int nextY = cur[1];
			// not reach the bound or not reach wall
			while(task490_notWalls(maze, nextX + dir[0], nextY + dir[1]) && maze[nextX + dir[0]][nextY + dir[1]] != 1) {
				nextX = nextX + dir[0];
				nextY = nextY + dir[1];
			}
			int[] nextPos = {nextX, nextY};
			if (task490_dfs(maze, nextPos, dest, visited)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean task490_notWalls(int[][] maze, int x, int y) {
		int rLen = maze.length;
		int cLen = maze[0].length;
		return x >= 0 && x < rLen && y >= 0 && y < cLen;
	}
	
	/**
	 * task491:
	 * 
	 * looks like the subset of a array
	 * 
	 */
	public static void test491() {
		int[] nums = {4, 6, 7, 7};
		List<List<Integer>> result = task491_findSubsequences(nums);
		System.out.println(result);
		
	}
	
	public static List<List<Integer>> task491_findSubsequences(int[] nums) {
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		task491_helper(nums, 0, list, result);
        return result;
    }
	
	private static void task491_helper(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
		if (list.size() > 1) {
			result.add(new ArrayList<>(list));
		}
		Set<Integer> used = new HashSet<>();
		for (int i = index; i < nums.length; i++) {
			if (used.contains(nums[i])) {
				continue;
			}
			if (list.size() == 0 || nums[i] >= list.get(list.size() - 1) ) {
				list.add(nums[i]);
				used.add(nums[i]);
				task491_helper(nums, i + 1, list, result);
				list.remove(list.size() - 1);
			}
		}
 	}
	
	
	/*
	 * 492
	 */
	public static void test492() {
		
	}
	public static int[] task492_constructRectangle(int area) {
		int[] rev = new int[2];
		if (area < 1) {
			return rev; 
		}
		int width = (int)Math.sqrt(area);
		
		for(; width >= 1; width --) {
			if (area % width == 0) {
				rev[0] = area / width;
				rev[1] = width;
				break;
			}
		}
		return rev;
    }
	
	
	/*
	 * task494
	 */
	public static void test494() {
		int[] nums = { 1, 1, 1, 1, 1 };
		int S = 3;
		int rev = task494_findTargetSumWays(nums, S);
		System.out.println("rev = " + rev);

		int[] nums2 = { 1, 2, 7, 9, 981 };
		int S2 = 100000000;
		int rev2 = task494_findTargetSumWays2(nums2, S2);
		System.out.println("rev2 = " + rev2);

	}

	public static int result = 0;

	public static int task494_findTargetSumWays(int[] nums, int S) {
		if (nums == null || nums.length == 0)
			return result;
		int n = nums.length;
		int[] sums = new int[n];
		sums[n - 1] = nums[n - 1];
		for (int i = n - 2; i >= 0; i--)
			sums[i] = sums[i + 1] + nums[i];
		System.out.println(Arrays.toString(sums));
		helper(nums, S, 0, 0, sums);
		return result;
	}

	public static void helper(int[] nums, int target, int pos, long eval,
			int[] sums) {
		if (pos == nums.length) {
			if (target == eval)
				result++;
			return;
		}
		if (sums[pos] < Math.abs(target - eval)) {
			return;
		}
		helper(nums, target, pos + 1, eval + nums[pos], sums);
		helper(nums, target, pos + 1, eval - nums[pos], sums);
	}

	// opt
	// reduce to find a subset, whether the sum of subset equals to Target
	// Sum(P) - Sum(N) = S
	// Sum{P} + Sum{N} + Sum{P} - Sum{N} = S + sum(P and N)
	// 2 Sum{P} = target
	// sum{P} = target/2
	public static int task494_findTargetSumWays2(int[] nums, int S) {
		int sum = 0;
		for (Integer i : nums) {
			sum += i;
		}
		if ((sum + S) % 2 != 0) {
			return 0;
		}
		// if the sum < S
		if (sum < S) {
			return 0;
		}
		int target = (sum + S) / 2;

		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = target; j >= 0; j--) {
				if (j >= nums[i]) {
					dp[j] += dp[j - nums[i]];
				}
			}
		}
		return dp[target];

	}
	
	

	/*
	 * 500 Keyboard Row
	 */
	public String[] findWords(String[] words) {
		String[] strs = { "QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM" };
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			for (char c : strs[i].toCharArray()) {
				map.put(c, i);// put <char, rowIndex> pair into the map
			}
		}
		List<String> res = new LinkedList<>();
		for (String w : words) {
			if (w.equals(""))
				continue;
			int index = map.get(w.toUpperCase().charAt(0));
			for (char c : w.toUpperCase().toCharArray()) {
				if (map.get(c) != index) {
					index = -1; // don't need a boolean flag.
					break;
				}
			}
			if (index != -1)
				res.add(w);// if index != -1, this is a valid string
		}
		return res.toArray(new String[0]);
	}
	
	/*
	 * task496
	 * 
	 * keep a decreasing order of nums in the stack. 
	 * 6 5 4 3 2 1  if there is 7, then 7 is the next greater element of all the previous 
	 * element.
	 * 
	 * also see 503
	 */
	public static void test496(){
		int[] findNums = {4,1,2};
		int[] nums = {1,3,4,2};
		int[] rev = task496_nextGreaterElement(findNums, nums);
		System.out.println(Arrays.toString(rev));	
	}
	
	
	public static int[] task496_nextGreaterElement(int[] findNums, int[] nums) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		// key: value ==> element, nextGreat Element
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(Integer cur : nums) {
			while(!stack.isEmpty() && stack.peekFirst() < cur) {
				int popElem = stack.pollFirst();
				map.put(popElem, cur);
			}
			stack.offerFirst(cur);
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		// traverse the findNum and get the result
		int len = findNums.length;
		int[] rev = new int[len];
		
		for(int i = 0; i < len; i ++) {
			if (map.containsKey(findNums[i])) {
				rev[i] = map.get(findNums[i]);
			} else {
				rev[i] = -1;
			}
		}
		return rev;
	}
	
	/*
	 * 498
	 * 
	 */
	public static int[] task498_findDiagonalOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return new int[0];
		int m = matrix.length, n = matrix[0].length;

		int[] result = new int[m * n];
		int row = 0, col = 0, d = 0;
		int[][] dirs = { { -1, 1 }, { 1, -1 } };

		for (int i = 0; i < m * n; i++) {
			result[i] = matrix[row][col];
			row += dirs[d][0];
			col += dirs[d][1];

			if (row >= m) {
				row = m - 1;
				col += 2;
				d = 1 - d;
			}
			if (col >= n) {
				col = n - 1;
				row += 2;
				d = 1 - d;
			}
			if (row < 0) {
				row = 0;
				d = 1 - d;
			}
			if (col < 0) {
				col = 0;
				d = 1 - d;
			}
		}

		return result;
    }

}
