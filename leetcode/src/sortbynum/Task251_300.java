package sortbynum;
import ds.*;

import java.lang.reflect.Array;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;
import java.util.PrimitiveIterator.OfDouble;

import ds.TreeNode;

import java.util.*;
public class Task251_300 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test253();
//		test254();
//		test255();
//		test264();
//		test261();
//		test259();
//		test260();
//		test264();
//		test266();
//		test267();
//		test269();
//		test286();
//		test273();
//		test274();
//		test275();
//		test279();
//		test287();
//		test280();
//		test286();
//		test290();
//		test293();
//		test294();
//		test_294_1();
//		test296();
//		test300_2();
		test();
		
	}
	/*
	 * 251 Flatten 2D Vector 
	 * 
	 * see Vector2D
	 * see iterator.iterator2DVector
	 */
	
	/*
	 * 252 Meeting Rooms
	 *  
	 */
	public static boolean canAttendMeetings(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return true;
		}
		// sort the intervals according the start time
		Arrays.sort(intervals, intervalComp);
		int end_so_far = -1;
		// traverse the sorted array. get the current interval, 
		// if cur.start < end_so_far, then there is overlap, return false
		// otherwise, update the end_so_far
		for(int i = 0; i < intervals.length; i ++) {
			Interval curInterval = intervals[i];
			if (curInterval.start < end_so_far) {
				return false;
			}
			// update end_so_far
			end_so_far = Math.max(end_so_far, curInterval.end);
		}
		return true;
		
	}
	public static Comparator<Interval> intervalComp = new Comparator<Interval>() {

		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			if (o1.start == o2.start) {
				return 0;
			}
			return o1.start < o2.start ? -1: 1;
		}
	};
	
	
	/*
	 * 253 Meeting Rooms II 
	 */
	public static void test253() {
		Interval[] intervals = {
				new Interval(13, 15),
				new Interval(1,8),
				new Interval(7,9)
		};
		
		int minMtRm = task253_minMeetingRooms(intervals);
		System.out.println("minMeetingRoom = " + minMtRm);
		
		int minMtRm2 = task253_minMeetingRooms2(intervals);
		System.out.println("minMeetingRoom2 = " + minMtRm2);
		
	}

	/**
	 *
	 * @param intervals
	 * @return
	 *
	 */
	public static int task253_minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		ArrayList<Cell> list = new ArrayList<Cell>();
		for(Interval interval: intervals) {
			Cell cell_start = new Cell(interval.start, true);
			Cell cell_end = new Cell(interval.end, false);
			
			list.add(cell_start);
			list.add(cell_end);
		}
		
		int counter = 0;
		int result = Integer.MIN_VALUE;
		Collections.sort(list);
		
		for(int i = 0; i < list.size(); i ++) {
			System.out.print("[" + list.get(i).val + " " + list.get(i).isStart + " ]");
		}
		System.out.println();
		
		for(int i = 0; i < list.size(); i ++ ) {
			Cell curCel = list.get(i);
			if (curCel.isStart) {
				counter ++;
			} else if (!curCel.isStart ) {
				counter --;
			}	
			result = Math.max(result, counter);
		}
		return result;	
	}
	
	public static class Cell implements Comparable<Cell>{
		public int val;
		public boolean isStart;
		
		public Cell(int v, boolean isStart) {
			this.val = v;
			this.isStart = isStart;
		}
		@Override
		public int compareTo(Cell other) {
			// TODO Auto-generated method stub
			if (this.val == other.val) {
				// let the end in front of start, if start.val == end.val
				if (!this.isStart) {
					return -1;
				} else if (this.isStart) {
					return 1;
				} else {
					return 0;
				}
			}
			
			return this.val < other.val ? -1 : 1;
		}
	}
	
	
	public static int task253_minMeetingRooms2(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

		// sort the interval based on the start of each interval, in ascending order
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start == i2.start) {
					return 0;
				}
				return i1.start < i2.start ? -1 : 1;
			}
		});
	
		// the earliest end of all interval
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		int count = 1;
		minHeap.offer(intervals[0].end);
	
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < minHeap.peek()) {
				// we need increase the meeting room
				count++;
			} else {
				// intervals[i].start >= minHeap.peek() the earliest end of interval
				// pop the ending interval
				minHeap.poll();
			}
			// push the current interval's end into minHeap
			minHeap.offer(intervals[i].end);
		}

		return count;
	}
	
	/*
	 * 254 Factor Combinations 
	 */
	public static void test254() {
		int n = 12;
		List<List<Integer>> result =  task254_getFactors(n);
		System.out.println(result);
 	}
	
	public static List<List<Integer>> task254_getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(result, list, n, 2);
        return result;
    }
	
	public static void helper(List<List<Integer>> result, 
			List<Integer> list, int n, int start) {
		if (n == 1) {
			if (list.size() > 1) {
				List<Integer> copy = new ArrayList<>(list);
				result.add(copy);
			}
			return ;
		}
		
		for(int i = start; i <= n; i ++) {
			if (n % i == 0) {
				if (list.size() > 0 && i < list.get(list.size() - 1)) {
					// 保持list 升序
					continue;
				}
				list.add(i);
				helper(result, list, n/i, start);
				list.remove(list.size() - 1);
			}
		}
	}
	
	
	/*
	 * this method is faster.
	 */
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n <= 3)
			return res;
		List<Integer> item = new ArrayList<Integer>();
		getAllFactors(n, item, res);
		return res;
	}

	public void getAllFactors(int n, List<Integer> item, List<List<Integer>> res) {
		if (n <= 3) {
			return;
		}
		for (int i = 2; i * i <= n; i++) {
			if (item.size() == 0 || 
					(item.size() > 0 && item.get(item.size() - 1) <= i)) {
				if ((n % i == 0)) {
					List<Integer> list = new ArrayList<Integer>(item);
					// if n % i == 0, like 16 % 2 ==0 ,then put 2, 8 into list
					list.add(i);
					list.add(n / i);
					res.add(list);

					item.add(i);
					getAllFactors(n / i, item, res);
					item.remove(item.size() - 1);
				}
			}
		}
	}
	
	
	
	/*
	 * 255 Verify PreOrder Sequence in Binary Search Tree 
	 */
	public static void test255() {
		int[] array = {5,3,1,4,8,7,9};
		boolean rev = task255_verifyPreOrder(array);
		System.out.println("rev = " + rev);
	}
	
	
	/*
	 * http://www.cnblogs.com/grandyang/p/5327635.html
	 * 根据二叉搜索树的性质，当前节点的值一定大于其左子树中任何一个节点值，
	 * 而且其右子树中的任何一个节点值都不能小于当前节点值，那么我们可以用这个性质来验证，举个例子，比如下面这棵二叉搜索树：
	 *  	 5
	 *  	/ \
	 * 	    2   6
	 *	   / \
	 *    1   3
	 * 其先序遍历的结果是{5, 2, 1, 3, 6}, 我们先设一个最小值low，然后遍历数组，如果当前值小于这个最小值low，返回false，
	 * 对于根节点，我们将其压入栈中，然后往后遍历，如果遇到的数字比栈顶元素小，说明是其左子树的点，继续压入栈中，
	 * 直到遇到的数字比栈顶元素大，那么就是右边的值了，我们需要找到是哪个节点的右子树，所以我们更新low值并删掉栈顶元素，
	 * 然后继续和下一个栈顶元素比较，如果还是大于，则继续更新low值和删掉栈顶，
	 * 直到栈为空或者当前栈顶元素大于当前值停止，压入当前值，这样如果遍历完整个数组之前都没有返回false的话，最后返回true即可，
	 */
	public static boolean task255_verifyPreOrder(int[] preorder) {
		int low = Integer.MIN_VALUE;
		LinkedList<Integer> st = new LinkedList<Integer>();
		for(Integer i : preorder) {
			if (i < low) {
				return false;
			}
			while(!st.isEmpty() && i > st.peekFirst()) {
				low = st.pollFirst();
			}
			st.offerFirst(i);
		}
		return true;
	}
	
	
	/*
	 * 256 Paint House 
	 * 
	 * state[i][R] the mininum cost of paint the [0..i] houses and the ith house's color is R
	 * 
	 * state[i][R] = min(state[i - 1][G] + state[i - 1][B]) + cost[i][R]
	 * 
	 * 
	 * final result: min(state[n - 1][R], state[n - 1][G], state[n - 1][B])
	 * Time: O(n)
	 */
	
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
    public static int task256_minCost(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0] == null || cost[0].length == 0) {
            return 0;
        }
        int numHouse = cost.length;
		int[][] state = new int[numHouse][3];
		
// 		System.out.println(state.length);
// 		System.out.println(state[0].length);
		// init
		state[0][R] = cost[0][R];
		state[0][G] = cost[0][G];
		state[0][B] = cost[0][B];
		
		for(int i = 1; i < numHouse; i ++) {
			state[i][R] = Math.min(state[i - 1][G], state[i - 1][B]) + cost[i][R];
			state[i][G] = Math.min(state[i - 1][R], state[i - 1][B]) + cost[i][G];
			state[i][B] = Math.min(state[i - 1][R], state[i - 1][G]) + cost[i][B];
		}
		
		return Math.min(Math.min(state[numHouse - 1][R], state[numHouse - 1][G]), state[numHouse - 1][B]);
    }
	
	
	/*
	 * 257 Binary Tree Paths
	 */
	public static List<String> task257_binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<String>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		task257_helper(result, path, root);
		return result;
	}
	
	public static void task257_helper(List<String> result, ArrayList<Integer> path, TreeNode node) {
		if (node == null) {
			return ;
		}
		if (node.left == null && node.right == null) {
			path.add(node.val);
			StringBuilder stb = new StringBuilder();
			for(int i = 0; i < path.size(); i ++) {
				if (i == path.size() - 1) {
					stb.append(path.get(i));
				} else {
					stb.append(path.get(i));
					stb.append("->");
				}
			}
			path.remove(path.size() - 1);
			result.add(stb.toString());
		}
		
		path.add(node.val);
		task257_helper(result, path, node.left);
		task257_helper(result, path, node.right);
		path.remove(path.size() - 1);
	}
	
	
	
	/*
	 * 258 Add Digits
	 */
	public int task258_addDigits(int num) {
		if (num < 10) {
			return num;
		}
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}

		return task258_addDigits(sum);

	}
	
	/*
	 * 259 3Sum Smaller 
	 */
	public static void test259() {
		int[] nums = {-2, 0, 1, 3};
		int target = 2;
		int counter = task259_threeSumSmaller(nums, target);
		System.out.println("counter = " + counter);
	}
	public static int task259_threeSumSmaller(int[] nums, int target) {
		if (nums == null || nums.length <3) {
			return 0;
		}
		Arrays.sort(nums);
		int counter = 0;
		int n = nums.length;
		for(int i = 0; i < n - 2; i ++) {
			int j = i + 1;
			int k = n - 1;
			while(j < k) {
				int curSum = nums[i] + nums[j] + nums[k];
				if (curSum < target) {
					counter += (k - j);
					j ++;
				} else {
					k --;
				}
			}
		}
		
		return counter;
	}
	
	
	
	
	/*
	 * 260 Single Number III 
	 * Given an array of numbers nums, in which exactly two elements appear 
	 * only once and all the other elements appear exactly twice. 
	 * Find the two elements that appear only once.
	 * 
	 * 
	 * XOR all elements. the result is a^ b
	 * 
	 * find the last digit which == 1 to distinguish the a and b
	 * 
	 * 
	 */
	public static void test260() {
		int[] nums = {1, 2, 1, 3, 2, 5};
		int[] res = task260_singleNumber(nums);
		System.out.println(Arrays.toString(res));
	}
	public static int[] task260_singleNumber(int[] nums) {
		int[] res = new int[2];
		
		int result = nums[0];
		for(int i = 1; i < nums.length; i ++) {
			result ^= nums[i];
		}
		
		res[0] = 0;
		res[1] = 0;
		
		int mask = result & (~(result - 1));
		for(int i = 0; i < nums.length; i ++ ) {
			if ((mask & nums[i]) != 0) {
				res[0] = res[0] ^ nums[i]; 
			} else {
				res[1] = res[1] ^ nums[i];
			}
		}
		
		return res;
	}
	
	
	/*
	 * 261 Graph Valid Tree
	 * 
	 * all connected and no cycle
	 * 
	 */
	public static void test261() {
		int n = 5;
		int[][] edges = {
				{0,1},
				{0,2},
				{2,3},
				{2,4}
		};
		
		boolean rev = task261_validTree(n, edges);
		System.out.println("rev = " + rev);
		/*
		for(int i = 0;i < n; i ++) {
			
			ArrayList<Integer> neighbors = task261_getNeighbor(i, edges);
			System.out.println("  i = " + i);
			System.out.println("neighbors: ");
			System.out.println(neighbors);
		}
		*/
	}
	
	public static boolean task261_validTree(int n, int[][] edges) {
		boolean[] visited = new boolean[n];
		
		// no cycle
		if (task261_hasCycle(0, edges, visited, -1)) {
			System.out.println("has cycle");
			return false;
		}
		
		// connected
		for(int i = 0; i < n; i ++) {
			if (visited[i] == false) {
				// this node hasn't been visited
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * check whether a connected undirected has cycle
	 */
	public static boolean task261_hasCycle(int n, int[][] edges, 
			boolean[] visited, int parent) {
		
		visited[n] = true;
		
		ArrayList<Integer> neighbors = task261_getNeighbor(n, edges);
		System.out.println("---------------debug-----------");
		System.out.println("n = " + n);
		System.out.println(neighbors);
		System.out.println("-----------finish debug---------");
		for(Integer nei: neighbors) {
			if (visited[nei] == true) {
				// we need to introduce parent
				// since for 1-> 2
				// every time, after we visited 1, we expand to 2
				// since it's undirected, for 2, we also have an 
				// edge 2->1, and now, visited[1] = true;
				// in this way, if we don't introduce parent, 
				// 1->2  && 2->1 will construct a cycle.
				// actually NOT
				if (nei != parent) {
					return true;
				}
			} else {
				// visited[nei] == false
				if (task261_hasCycle(nei, edges, visited, n)) {
					return true;
				} 
			}
		}
		return false;
	}
	
	public static ArrayList<Integer> task261_getNeighbor(int k, int[][] edges) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for(int i = 0; i < edges.length; i ++) {
			if (edges[i][0] == k) {
				neighbors.add(edges[i][1]);
			}
			if (edges[i][1] == k) {
				neighbors.add(edges[i][0]);
			}
		}
		return neighbors;
	}
	
	/*
	 * 263 Ugly Number
	 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
	 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
	 * Note that 1 is typically treated as an ugly number.
	 * 
	 * 先除以 5， 再除以3， 再除以2， 都用while 
	 */
	public static boolean task263_isUgly(int num) {
		if (num < 0) {
			return false;
		}
		if (num == 0) {
			return false;
		}
		while(num%5 == 0) {
			num /= 5;
		}
		while(num%3 == 0) {
			num /= 3;
		}
		while(num%2 == 0) {
			num /= 2;
		}
		
		return num == 1;
	}
	
	
	/*
	 * 264 Ugly Number II
	 * get the nth ugly number
	 * 
	 * convert Long to Integer
	 * http://stackoverflow.com/questions/5804043/convert-long-into-integer
	 * 
	 * 2 ways
	 * 
	 * Integer i = theLong != null ? theLong.intValue() : null;
	 * 
	 * // auto-unboxing does not go from Long to int directly, so
	 * Integer i = (int) (long) theLong;
	 * 
	 * 考虑越界问题， 用Long
	 * 
	 */
	public static void test264() {
		int n = 15;
//		int rev = task264_uglyNumber_main(n);
//		System.out.println(rev);
//		System.out.println("----------------");
//		int rev2 = task264_uglyNumber_main2(n);
//		System.out.println(rev2);
		int rev3 = task264_uglyNumber3(n);
		System.out.println(rev3);
		
	}
	public static int task264_uglyNumber_main(int n) {
		long result = task264_uglyNumber(n);
		return (int)result;
	}
	
	public static long task264_uglyNumber(int n) {
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		HashSet<Long> set = new HashSet<Long>(); // de-duplicate
		queue.add((long)1);
		while(n > 1) {
			Long cur = queue.poll();
			if (!set.contains(cur * 2)) {
				queue.add(cur*2);
				set.add(cur*2);	
			}
			if (!set.contains(cur * 3)) {
				queue.add(cur * 3);
				set.add(cur*3);
			}
			if (!set.contains(cur * 5)) {
				queue.add(cur * 5);
				set.add(cur*5);
			}
			n --;
		}
		return queue.peek();
	}
	
	public static int task264_uglyNumber_main2(int n) {
		long result = task264_uglyNumber2(n);
		return (int)result;
	}
	
	/*
	 * Use 3 deques. 
	 * to avoid 2*3*5 and 3*2*5
	 * for 2, we can multiply 2, 3, 5
	 * for 3, multiply 3, 5
	 * for 5, only multiply 5 to maintain order 
	 */
	public static long task264_uglyNumber2(int n) {
		long seed = 1;
		Deque<Long> two = new LinkedList<Long>();
		Deque<Long> three = new LinkedList<Long>();
		Deque<Long> five = new LinkedList<Long>();
		
		two.add(seed * 2);
		three.add(seed * 3);
		five.add(seed * 5);
		
		long result = seed;
		while(n > 1) {
			if (two.peekFirst() < three.peekFirst() && two.peekFirst() < five.peekFirst()) {
				result = two.pollFirst();
				two.offerLast(result * 2);
				three.offerLast(result * 3);
				five.offerLast(result * 5);
			} else if (three.peekFirst() < two.peekFirst() && three.peekFirst() < five.peekFirst()) {
				result = three.pollFirst();			
				three.offerLast(result * 3);
				five.offerLast(result * 5);
			} else {
				result = five.pollFirst();
				five.offerLast(result * 5);
			}
			n --;
		}
		return result;
	}
	
	
	/*
	 * 这里让我们找到第n个丑陋数，还好题目中给了很多提示，基本上相当于告诉我们解法了，
	 * 根据提示中的信息，我们知道丑陋数序列可以拆分为下面3个子列表：
	 * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
	 * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
	 * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
	 * 仔细观察上述三个列表，我们可以发现每个子列表都是一个丑陋数乘以2,3,5，
	 * 而这些丑陋数的值就是从已经生成的序列中取出来的，我们每次都从三个列表中取出当前最小的那个加入序列，
	 */
	public static int task264_uglyNumber3(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int f2 = 2, f3 = 3, f5 = 5;
		int index2 = 0, index3 = 0, index5 = 0;
		for(int i = 1; i < n; i ++) {
			int min = Math.min(Math.min(f2, f3), f5);
			dp[i] = min;
			// find next ugly number * 2
			if (min == f2) {
				f2 = 2 * dp[++index2];
			}
			// find next ugly number * 3
			if (min == f3) {
				f3 = 3 * dp[++index3];
			}
			// find next ugly number * 5
			if (min == f5) {
				f5 = 5 * dp[++index5];
			}
			
		}
		System.out.println(Arrays.toString(dp));
		return dp[n - 1];
	}
	
	
	/*
	 * follow up
	 * 313 Super Ugly Number
	 */
	public static int task264_super_ugly_number(int n, int[] primes) {
		return -1;
	}
	
	/*
	 * 265 Paint House II
	 * refer to lai.small_yan.dp 
	 * 
	 * state[i][j]: the minimum cost of paint [0..i] houses and the ith house's color is j
	 * 
	 * state[i][j] = min (state[i - 1][k]) + cost[i][j];  k >= 0 && k < numColors && k != j
	 * 
	 * Time: O(n * m * m)  
	 * n is the number of houses, m is the number of colors 
	 */
	public static int task265_minCostII(int[][] cost) {
        if (cost == null || cost.length == 0 || cost[0] == null || cost[0].length == 0) {
			return 0;
		}
		int numHouses = cost.length;
		int numColors = cost[0].length;
		int[][] state = new int[numHouses][numColors];
		
		// init
		for(int i = 0; i < numHouses; i ++) {
			for(int j = 0; j < numColors; j ++) {
				// here, we find the minimum for each j  
				// actually we can store min1 and min2 for the i - 1. 
				// if curColor != lastColr, use min1 otherwise, sine we cannot use the same
				// color for the adjacent houses, we use min2
				if (i == 0) {
					state[i][j] = cost[i][j];
				} else {
					int prevMin = Integer.MAX_VALUE;
					for(int k = 0; k < numColors; k ++) {
						if (k != j) {
							// get the minimum in the previous state
							prevMin = Math.min(prevMin, state[i - 1][k]);
						}
					}
					
					state[i][j] = prevMin + cost[i][j];
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < numColors; i ++ ) {
			result = Math.min(result, state[numHouses - 1][i]);
		}
		
		return result;
    }
	
	
	// optimize
	// solve it in O(nk) runtime?
	/*
	 * 扩展到k种颜色。 对于第i个房子，维护前 i - 1 中， 最小的花费 min1, 第二小的花费 min2, 和 最小的花费 i -1 的color
	 * when curColor == lastColor, use min2
	 * otherwise, use min1
	 * then update min1, min2, lastColor
	 * 
	 * refer: http://www.cnblogs.com/yrbbest/p/5020937.html
	 */
	public static int task265_minCostII_opt(int[][] costs) {
		if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
			return 0;
		}
		int numHouses = costs.length;
		int numColors = costs[0].length; 
		int min1 = 0, min2 = 0, lastColor = -1;
		for(int i = 0; i < numHouses; i ++) {
			int curMin1 = Integer.MAX_VALUE;
			int curMin2 = Integer.MAX_VALUE;
			int curColor = -1;
			for(int j = 0; j < numColors; j ++) {
				int cost = (j == lastColor ? min2 : min1) + costs[i][j];
				if (cost < curMin1) {
					// update curMin and curMin2
					curMin2 = curMin1;
					curMin1 = cost;
					curColor = j;
				} else if(cost < curMin2) {
					curMin2 = cost;
				}
			}
			
			min1 = curMin1;
			min2 = curMin2;
			lastColor = curColor;
		}
		return min1;
	}
	

	/*
	 * 266 
	 * 
	 * Palindrome Permutation 
	 * 
	 * check whether a string has a permutation which is a palindrome
	 * 
	 * Use hashMap to count each char's counter. 
	 * the odd occurrence can appear at MOST once.
	 * 
	 */
	public static void test266() {
		String s = "carerac";
		boolean rev = task266_canPermutePalindrome(s);
		System.out.println("rev = " + rev);
	}
	
	// assume the s is NOT null
	public static boolean task266_canPermutePalindrome(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i ++) {
			char ch = s.charAt(i);
			if (!map.containsKey(ch)) {
				map.put(ch, 1);
			} else {
				map.put(ch, map.get(ch) + 1);
			}
		}
		// 
		int oddOccurence = 0;
		for(Entry<Character, Integer> entry: map.entrySet()) {
			if (entry.getValue() % 2 == 1) {
				oddOccurence ++;
			}
		}
		if (oddOccurence > 1) {
			return false;
		}
		return true;
	}
	
	
	/*
	 * 267 
	 * Palindrome Permutation II 
	 * 
	 * <1> use hashMap to count the occurrence of each char
	 * <2> get the first half of char_arrayl
	 * <3> get all permutations of the first half array.
	 *     -> reduce to get all permutation for a char array, which may contain duplicates 
	 * <4> link the second part
	 * 
	 * 
	 * !!! pay attentions that the odd Occurrence
	 * 
	 */
	public static void test267() {
		String s = "aaa";
		List<String> result = task267_generatePalindromes(s);
		System.out.println(result);
		
	}
	public static List<String> task267_generatePalindromes(String s) {
		int n = s.length();
		List<String> result = new ArrayList<String>();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int oddOccurence = 0;
		Character oddOccurenceChar = null; 
		for(int i = 0; i < s.length(); i ++) {
			char curCh = s.charAt(i);
			map.put(curCh, map.getOrDefault(curCh, 0) + 1);
		}
		
		for(Entry<Character, Integer> entry: map.entrySet()) {
			if (entry.getValue() % 2 == 1) {
				oddOccurence ++;
				oddOccurenceChar = entry.getKey();
			}
		}
		
		// there isn't such palindromes
		if (oddOccurence > 1) {
			return result;
		}
		
		char[] firstHalf = new char[n/2];
		int index = 0;
		
		// get the first half array. 
		for(Entry<Character, Integer> entry: map.entrySet()) {
			int counter = entry.getValue();
			for(int i = 0; i < counter/2; i ++) {
				firstHalf[index] = entry.getKey();
				index ++;
			}
		}
		
		// get the first half Permutations with duplicates 
		List<String> firstHalfList = task267_generateFirstHalf(firstHalf);
		if (oddOccurence == 1) {
			for(String str: firstHalfList) {
				String reverse = new StringBuilder(str).reverse().toString();
				StringBuilder stb = new StringBuilder();
				stb.append(str);
				stb.append(oddOccurenceChar);
				stb.append(reverse);
				
				result.add(stb.toString());
			}
		} else {
			for(String str: firstHalfList) {
				String reverse = new StringBuilder(str).reverse().toString();
				StringBuilder stb = new StringBuilder();
				stb.append(str);
				stb.append(reverse);
				
				result.add(stb.toString());
			}
		}
		return result;
	}
	
	
	// get all permutation of the char[] 
	public static List<String> task267_generateFirstHalf(char[] firstHalf) {
		List<String> result = new ArrayList<String>(); 
		task267_helper(firstHalf, result, 0);
		return result;
	}
	
	
	public static void task267_helper(char[] firstHalf, List<String> result,
			int index) {
		if (index == firstHalf.length) {
			String str = new String(firstHalf);
			result.add(str);
			return;
		}
		HashSet<Character> used = new HashSet<Character>();
		for (int i = index; i < firstHalf.length; i++) {
			if (!used.contains(firstHalf[i])) {
				used.add(firstHalf[i]);
				task267_swap(firstHalf, i, index);
				task267_helper(firstHalf, result, index + 1);
				task267_swap(firstHalf, i, index);
			}
		}
	}
	
	public static void task267_swap(char[] array, int start, int end) {
		char temp = array[start];
		array[start] = array[end];
		array[end] = temp;
	}
	
	/*
	 * 268 
	 * Missing Number
	 * XOR all numbers in nums and 1..n
	 * the result is the missing number. 
	 */
	public static int task268_missingNumber(int[] nums) {
		int n = nums.length;
		int result = 0;
		for(int i = 0; i < nums.length; i ++) {
			result ^= nums[i];
		}
		
		for(int i = 0; i <=n; i ++) {
			result ^= i;
		}
		
		return result;
	}
	
	/*
	 * 269 Alien Dictionary 
	 * 
	 * Topological sorting
	 * 
	 */

	/*
	 * (1)"wrt",
	 * (2)"wrf",
	 * (3)"er",
	 * (4)"ett",
	 * (5)"rftt"
	 * 
	 * 
	 * wrt
	 * wrf
	 * 
	 * t -> f
	 * w -> e
	 * r -> t
	 * e -> r
	 */
	public static void test269() {
		String[] words = {"ab", "adc"};
		String result = task269_alienOrder(words);
		System.out.println(result);
	}
	
	public static String task269_alienOrder(String[] words) {  
        if (words == null || words.length == 0)   
            return "";  
        if (words.length == 1)  
            return words[0]; 
        
        Map<Character, Set<Character>> graph = buildGraph(words);  
        Map<Character, Integer> indegree = computeIndegree(graph);  
        
        StringBuilder order = new StringBuilder();  
        LinkedList<Character> queue = new LinkedList<Character>();
        
        // add the nodes whose indegree == 0
        for (Character c : indegree.keySet()) {  
            if (indegree.get(c) == 0)  
                queue.offer(c);  
        }
        
        while (!queue.isEmpty()) {  
            char c = queue.poll();  
            order.append(c);
            for (Character adj : graph.get(c)) {
                if (indegree.get(adj) - 1 == 0) { 
                		// only add the indegree is 1 in to the queue
                    queue.offer(adj); 
                }
                else {  
                    indegree.put(adj, indegree.get(adj) - 1);
                }
            }  
        }
        return order.length() == indegree.size() ? order.toString() : "";  
    }  
	
	
	/**
	 * 
	 * 
	 * @param words
	 * @return map
	 * 
	 * the map contains all the nodes as keys
	 */
    public static  Map<Character, Set<Character>> buildGraph(String[] words) {  
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();  
        int N = words.length;  
        for (int i = 1; i < N; i++) {
        		// for two strings, we can at most get two char's order
            String word1 = words[i - 1];  
            String word2 = words[i];  
            int len1 = word1.length(), len2 = word2.length(), maxLen = Math.max(len1, len2);  
          
            
            boolean found = false;
           
            for (int j = 0; j < maxLen; j++) {  
                char c1 = j < len1 ? word1.charAt(j) : ' ';  
                char c2 = j < len2 ? word2.charAt(j) : ' ';  
                
                // first, put the c1 into map
                if (c1 != ' ' && !map.containsKey(c1))   
                    map.put(c1, new HashSet<Character>());
                
                // put the c2 into map
                if (c2 != ' ' && !map.containsKey(c2))  
                    map.put(c2, new HashSet<Character>());

                // c1 != '' && c2 != '' & c1 != c2 && found == false 
                // !!! Note: from two strings, we can only find one sequence a -> b.  once found is true.
                // won't execute the following code. 
                if (c1 != ' ' && c2 != ' ' && c1 != c2 && !found) {  
                    map.get(c1).add(c2);  
                    found = true;  
                }  
            }   
        } 
        
        for (Entry<Character, Set<Character>> entry: map.entrySet()) {
        		System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        
        return map;  
    }  
    
    
    
    public static Map<Character, Integer> computeIndegree(Map<Character, Set<Character>> graph) {  
        Map<Character, Integer> indegree = new HashMap<Character, Integer>();  
        	
        for (Character prev : graph.keySet()) {  
            if (!indegree.containsKey(prev)) {
            		indegree.put(prev, 0); 
            } 
                 
            for (Character succ : graph.get(prev)) {  
                if (!indegree.containsKey(succ))  
                    indegree.put(succ, 1);  
                else  
                    indegree.put(succ, indegree.get(succ) + 1);   
            }  
        } 
        
        for (Entry<Character, Integer> entry: indegree.entrySet()) {
        		System.out.println(entry.getKey() + " => " + entry.getValue());
        }
        return indegree;  
    }  
	
	
	/*
	 * 270 Closest Binary Search Tree Value 
	 */
	public static int closestVal = 0;
	public static double min = Double.MAX_VALUE;
	public static int task270_closestValue(TreeNode root, double target) {
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		task270_helper(root, target);
		return closestVal;
	}
	
	public static void task270_helper(TreeNode root, double target) {
		if (root == null) {
			return ;
		}
		
		if (Math.abs(target - (double)root.val) < min) {
			min = Math.abs((double)root.val - target);
			closestVal = root.val;
		}
		
		if ((double)root.val < target) {
			task270_helper(root.right, target);
		} else {
			task270_helper(root.left, target);
		}
	}
	
	/*
	 * 271 Encode and Decode Strings 
	 */
	public static void test271() {
		
	}
	
	/*
	 * 272 Closest Binary Search Tree Value II
	 * find k closest values near target
	 * 
	 * http://www.cnblogs.com/grandyang/p/5247398.html
	 * 
	 */
	public static List<Integer> closestKValues(TreeNode root, double target,
			int k) {
		
		PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(k, new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				// TODO Auto-generated method stub
				if (o1 == o2) {
					return 0;
				}
				return o1 > o2 ? -1 : 1;
			}
			
		});
		
		Set<Integer> set = new HashSet<Integer>();
		task272_helper(root, target, k, maxHeap, set);
		
		return new ArrayList<Integer>(set);
	}
	
	public static void task272_helper(TreeNode root, double target, int k, PriorityQueue<Double> maxHeap, 
			Set<Integer> set) {
		if (root == null) {
			return ;
		}
		
		double diff = Math.abs(target - root.val);
		if (maxHeap.size() < k) {
			maxHeap.offer(diff);
			set.add(root.val);
		} else if (diff < maxHeap.peek()) {
			double x = maxHeap.poll();
			int target_plus_x = (int)(target + x);
			int target_minus_x = (int)(target - x);
			if (!set.remove(target_plus_x)) {
				set.remove(target_minus_x);
			}
			maxHeap.offer(diff);
			set.add(root.val);
		} else {
			if (root.val > target) {
				task272_helper(root.left, target, k, maxHeap, set);
			} else {
				task272_helper(root.right, target, k, maxHeap, set);
			}
			return ;
		}
		
		task272_helper(root.left, target, k, maxHeap, set);
		task272_helper(root.right, target, k, maxHeap, set);
	}

	public static TreeNode smallestSuccessor(TreeNode root, double target) {
		if (root == null) {
			return null;
		}

		if ((double) root.val < target) {
			return smallestSuccessor(root.right, target);
		} else {
			TreeNode left = smallestSuccessor(root.left, target);
			if (left == null) {
				return root;
			}
			return left;
		}

	}

	/*
	 * 273 Integer to English Words
	 */
	public static void test273() {
		
		int num = 12345;
		
		String rev = task273_numberToWords(num);
		System.out.println("------------------");
		System.out.println(num);
		System.out.println(rev);
		System.out.println("------------------");
		
	}
	
	//2 147 483 647
	public static String[] bigs = {"","Thousand","Million","Billion"};
	public static String[] one = {
		"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	public static String[] teen = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
		"Seventeen", "Eighteen", "Nineteen" };
	public static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", 
		"Sixty", "Seventy", "Eighty", "Ninety"};
	
	public static String task273_numberToWords(int num) {
		int index = 0;
		StringBuilder stb = new StringBuilder();
		
		while(num != 0) {
			if (num % 1000 != 0) {
				ArrayList<String> num3Digits = task273_numberWords_3Digits(num%1000);
				
				num3Digits.add(bigs[index]); 
				// index == 0, add "", index == 1, add "thousand", index == 2, add "million"
				
				StringBuilder curStb = new StringBuilder();
				for(int i = 0; i < num3Digits.size(); i ++) {
					curStb.append(num3Digits.get(i));
					curStb.append(" ");
				}
				stb.insert(0, curStb.toString());
			}
			// update index
			index ++;
			// handle the next three digit
			num /= 1000;
		}
		if (stb.length() == 0) {
			return "Zero";
		}
		
		int tailIndex = stb.length() - 1;
		while(stb.charAt(tailIndex) == ' ') {
			stb.deleteCharAt(tailIndex);
			tailIndex --;
		}
		
        return stb.toString();
    }
	
	public static ArrayList<String> task273_numberWords_3Digits(int num) {
		ArrayList<String> list = new ArrayList<String>();
		if (num > 99) {
			list.add(one[num/100]);
			list.add("Hundred");
		}
		num %= 100;
		if (num > 9 && num < 20) { // teens 
			list.add(teen[num%10]);
		} else {
			if (num >= 20) {
				list.add(tens[num/10]);
			}
			num %= 10;
			if (num != 0) {
				list.add(one[num]);
			}
		}
		return list;	
	}
	
	
	
	

	/*
	 * 274 H-Index
	 * 
	 * 1 HashMap
	 * 2 Sort
	 */
	public static void test274() {
		int[] citations = {3, 0, 6, 1, 5};
		int rev = task274_hIndex(citations);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * Time: O(n)
	 * Space: O(n)
	 * http://segmentfault.com/a/1190000003794831
	 * stats[i]表示有多少文章被引用了i次，这里如果一篇文章引用大于N次，我们就将其当为N次，因为H指数不会超过文章的总数。
	 * 为了构建这个数组，我们需要先将整个文献引用数组遍历一遍，对相应的格子加一。
	 * 
	 * 统计完后，我们从N向1开始遍历这个统计数组。
	 * 如果遍历到某一个引用次数时，大于或等于该引用次数的文章数量，大于引用次数本身时，我们可以认为这是H指数。
	 * 之所以不用再向下找，因为我们要取最大的H指数。那如何求大于或等于某个引用次数的文章数量呢？
	 * 我们可以用一个变量，从高引用次的文章数累加下来。
	 * 因为我们知道，如果有x篇文章的引用大于等于3次，那引用大于等于2次的文章数量一定是x加上引用次数等于2次的文章数量。
	 */
	public static int task274_hIndex(int[] citations) {
		int n = citations.length, total = 0;
		int[] arr = new int[n + 1];
		// 统计各个引用次数对应多少篇文章
		for (int i = 0; i < n; i++) {
			if (citations[i] >= n)
				arr[n]++;
			else
				arr[citations[i]]++;
		}
		System.out.println(Arrays.toString(citations));
		System.out.println(Arrays.toString(arr));
		for (int i = n; i >= 0; i--) {
			// 引用大于等于i次的文章数量，等于引用大于等于i+1次的文章数量，加上引用等于i次的文章数量 
			total += arr[i];
			// 如果引用大于等于i次的文章数量，大于引用次数i，说明是H指数
			if (total >= i)
				return i;
		}
		return 0;
	}
	
	
	/*
	 * 275 H-Index II
	 */
	public static void test275() {
		int[] citations = {1};
		Arrays.sort(citations);
		int rev = task275_hIndex2(citations);
		System.out.println("rev = " + rev);
	}
	
	public static int task275_hIndex2(int[] citations) {
		if (citations == null || citations.length == 0) {
			return 0;
		}
		int len = citations.length;
        int start = 0, end = len - 1;
        while(start + 1 < end) {
        	int mid = start + (end - start)/2;
        	if (citations[mid] == len - mid) {
				return len - mid;
			} else if (citations[mid] < len - mid) {
				start = mid;
			} else {
				// citations[mid] > mid + 1
				end = mid;
			}
        }
        System.out.println("start = " + start);
        System.out.println("end = " + end);
        
        if (citations[start] >= len - start) {
			return len - start;
		}
        if (citations[end] >= len - end) {
			return len - end;
		}
        return 0;
    }
	
	/*
	 * 276 Paint Fence 
	 */
	public static int task276_numWays(int n, int k) {
		// 当n=0时返回0
		int dp[] = { 0, k, k * k, 0 };
		if (n <= 2) {
			return dp[n];
		}
		for (int i = 2; i < n; i++) {
			// 递推式：第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色
			dp[3] = (k - 1) * (dp[1] + dp[2]);
			dp[1] = dp[2];
			dp[2] = dp[3];
		}
		return dp[3];
	}
	
	 
	
	/*
	 * 277 Find the Celebrity 
	 * If there is celebrity, there is only one celebrity.
	 *  each pair of persons, we know one of them is not celebrity.
	 *  we can do pair comparison for n ­ 1 times, then we have a candidate. 
	 *  we still need to check if the candidate is the celebrity.
	 *  http://yanguango.com/posts/2015/09/08/leetcode-find-the-celebrity/
	 *  
	 *  there at most one celebrity
	 *  (1) find the candidate, using knows(i, j)
	 *  (2) check whether the candidate is celebrity
	 */
	public static void test277() {
		
	}
	
	public boolean knows(int A, int B) {
		return false;
	}
	
    public int task277_findCelebrity(int n) {
        if (n < 2) return -1;
      int candidate = 0;
      // find the candidate
      for (int i = 1; i < n; i++) {
          if (knows(candidate, i)) candidate = i;
      }

      for (int i = 0; i < n; i++) {
          if (i != candidate) { // i is NOT candidate
        	  // if the candidate knows i or i doesn't knows candidate, then, 
        	  // there is no celebrity in this group
              if (knows(candidate, i) || !knows(i, candidate)) {
            	  return -1;
              }
          }
      }
      return candidate;
  }
	
	/*
	 * 278 First Bad Version
	 * Do Binary Search
	 */
	public static void test278() {
		
	}
	
	/*
	 * 279 Perfect Squares
	 * 
	 */
	
	/*
	 * method1 dp
	 * This problem can be solved by dynamic programming. 
	 * If we call dp is the array of least numbers of perfect square numbers for each integer 
	 * from 1 to n, 
	 * 
	 * we have the following relation:
	 * dp[n] = 1 + min (dp[n-i*i] for i from 1 to square root of n)
	 * 
	 * dp[x + y * y] = min(dp[x + y * y], dp[x] + 1)
	 * 
	 * Time: O(n * sqrt(n))
	 */
	public static void test279() {
		int n = 15;
		int rev = task279_numSquares(n);
		System.out.println("rev = " + rev);
	}
	
	public static int task279_numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for(int i = 1; i * i <= n; i ++) {
        	dp[i * i] = 1;
        }
        
        System.out.println(Arrays.toString(dp));
        
        for(int i = 1; i <=n; i ++) {
        	for(int j = 1; i + j * j <= n; j ++) {
        		dp[i + j*j] = Math.min(dp[i + j * j], dp[i] + 1);
        	}
        }
        
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
	
	/*
	 * method2
	 * BFS
	 * http://traceformula.blogspot.com/2015/09/perfect-squares-leetcode.html
	 */
	
	
	/*
	 * method3
	 * math way
	 * http://traceformula.blogspot.com/2015/09/perfect-squares-leetcode-part-2-solve.html
	 */
	public static int task279_numSquares2(int n) {
		while(n % 4  == 0) { // n % 4 == 0
			n >>= 2;
		}
		if ((n & 7) == 7) {
			// n % 8 == 7
			return 4;
		}
		if (task279_isSquare(n)) {
			return 1;
		}
		int sqrt_n = (int) Math.sqrt(n);
		for(int i = 1; i <= sqrt_n; i ++) {
			if (task279_isSquare(n - i * i)) {
				return 2;
			}
		}
		return 3;
	}
	
	public static boolean task279_isSquare(int n) {
		int temp  = (int) Math.sqrt(n);
		return temp * temp == n;
	}
	
	
	/*
	 * 280 Wiggle Sort 
	 * nums[0] <= nums[1] >= nums[2] <= nums[3]
	 * 
	 * similar: 324 Wiggle Sort2
	 * nums[0] < nums[1] > nums[2] < nums[3]...
	 */
	public static void test280() {
		int[] nums = {1,2};
		task280_wiggleSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
	public static void task280_wiggleSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return ;
		}
		for(int i = 0; i < nums.length; i +=2) {
			if (i > 0 && nums[i] > nums[i - 1]) {
				swap(nums, i, i - 1);
			}
			if (i < nums.length && nums[i] > nums[i + 1]) {
				swap(nums, i, i + 1);
			}
		}
	} 
	
	public static void swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}
	
	/*
	 * 281 Zigzag Iterator
	 * see ZigZagIterator in same directory and iterator.IteratorZigZag 
	 */
	
	
	/*
	 * 282 Expression Add Operators
	 * refer to jiuzhangAlgorithm stack. 
	 */
	public List<String> addOperators(String num, int target) {
	    List<String> res = new ArrayList<String>();
	    StringBuilder sb = new StringBuilder();
	    dfs(res, sb, num, 0, target, 0, 0);
	    return res;

	}
	public void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) { 
	    if(pos == num.length()) {
	        if(target == prev) res.add(sb.toString());
	        return;
	    }
	    for(int i = pos; i < num.length(); i++) {
	        if(num.charAt(pos) == '0' && i != pos) break;
	        long curr = Long.parseLong(num.substring(pos, i + 1));
	        int len = sb.length();
	        if(pos == 0) {
	            dfs(res, sb.append(curr), num, i + 1, target, curr, curr); 
	            sb.setLength(len);
	        } else {
	            dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr); 
	            sb.setLength(len);
	            dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr); 
	            sb.setLength(len);
	            dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr); 
	            sb.setLength(len);
	        }
	    }
	}
	
	
	
	/*
	 * 283 Move Zeroes
	 */
	public static void task283_moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return ;
		}
		int index = 0;
		for(int i = 0; i < nums.length; i ++) {
			if (nums[i] != 0) {
				nums[index] = nums[i];
				index ++;
			}
		}
		
		for(int i = index; i < nums.length; i ++) {
			nums[i] = 0;
		}		
	}
	
	/*
	 * 284 Peeking Iterator
	 * see iterator.IteratorPeek
	 */
	
	/*
	 * 285 Inorder Successor in BST 
	 */
	 public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		 if (root == null) {
			return null;
		}
		if (root.val <= p.val) {
			return inorderSuccessor(root.right, p);
		} else {
			TreeNode left = inorderSuccessor(root.left, p);
			if (left == null) {
				return root;
			}
			return left;
		}
	 }
	 
	 /*
	  * 286 
	  * Walls and Gates
	  * You are given a m x n 2D grid initialized with these three possible values.
	  * 
	  * do BFS
	  * form each gate, run BFS 
	  * 
	  * this is like find the nearest distance to the policeman
	  * 
	  * INF  -1  0  INF
	  * INF INF INF  -1
	  * INF  -1 INF  -1
	  *   0  -1 INF INF
	  *   
	  * after 
	  * 3  -1   0   1
	  * 2   2   1  -1
	  * 1  -1   2  -1
	  * 0  -1   3   4
	  * 
	  * Time: O(m^2 * n^2)
	  * 
	  */
	 private static final int INF = Integer.MAX_VALUE;
	 private static final int WALL = -1;
	 private static final int GATE = 0;
	 public static void test286() {
		int[][] rooms = { 
				{ INF, -1, 0, INF }, 
				{ INF, INF, INF, -1 },
				{ INF, -1, INF, -1 }, 
				{ 0, -1, INF, INF } 
				};
		task286_wallsAndGates(rooms);
		System.out.println("##############");
		task286_wallsAndGates2(rooms);
	 }
	 
	
	public static void task286_wallsAndGates(int[][] rooms) {
		if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
			return ;
		}
		int rLen = rooms.length;
		int cLen = rooms[0].length;
		int[][] result =  new int[rLen][cLen];
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (rooms[i][j] == WALL) {
					result[i][j] = -1;
				} else {
					result[i][j] = INF;
				}
			}
		}
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (rooms[i][j] == GATE) {
					boolean[][] visited = new boolean[rLen][cLen];
					BFS(rooms, result, visited, i, j);
				}
			}
		}
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}	
	}
	
	public static void BFS(int[][] rooms, int[][] result, boolean[][] visited, int x, int y) {
		LinkedList<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(x, y));
		
		int distance = 0;
		while ( !queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i ++) {
				Pos curEntry = queue.poll();
				result[curEntry.x][curEntry.y] = Math.min(result[curEntry.x][curEntry.y], distance);
				visited[curEntry.x][curEntry.y] = true;
				
				ArrayList<Pos> neighbors = getNeighbors(rooms, visited, curEntry.x, curEntry.y);
				queue.addAll(neighbors);
			}
			distance ++;
		}
	}
	
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1,1, 0, 0};

	public static ArrayList<Pos> getNeighbors(int[][] rooms, boolean[][] visited, int x, int y) {
		ArrayList<Pos> neighbors = new ArrayList<Pos>();
		int rLen = rooms.length;
		int cLen = rooms[0].length;
		for(int i = 0; i < 4; i ++) {
			int nei_x = dx[i] + x;
			int nei_y = dy[i] + y;
			if (nei_x >= 0 && nei_x < rLen && nei_y >= 0 && nei_y < cLen && 
				!visited[nei_x][nei_y] && rooms[nei_x][nei_y] != WALL ) {
				Pos entry = new Pos(nei_x, nei_y);
				neighbors.add(entry);
			}
		}
		return neighbors;
	}
	
	
	 
	public static class Pos {
		public int x;
		public int y;
		public Pos(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}
	
	
	/*
	 * this method is better
	 * Time: O(m*n)
	 * Instead of searching from an empty room to the gates, how about searching the other way round? 
	 * In other words, we initiate breadth-first search (BFS) from all gates at the same time. 
	 * Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d + 1, 
	 * the distance to an empty room must be the shortest.
	 * 
	 * https://leetcode.com/course/chapters/leetcode-101/walls-and-gates/
	 * 
	 * Complexity analysis:
	 * If you are having difficulty to derive the time complexity, start simple.
	 * Let us start with the case with only one gate. 
	 * The breadth-first search takes at most m x n steps to reach all rooms, 
	 * therefore the time complexity is O(mn). But what if you are doing breadth-first search from k gates?
	 * Once we set a room's distance, we are basically marking it as visited, 
	 * which means each room is visited at most once. 
	 * Therefore, the time complexity does not depend on the number of gates and is O(mn).
	 */
	private static final int EMPTY = Integer.MAX_VALUE;
	
	public static void task286_wallsAndGates2(int[][] rooms) {
		if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
			return ;
		}
		int rLen = rooms.length;
		int cLen = rooms[0].length;
		LinkedList<Pos> q = new LinkedList<Pos>();
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (rooms[i][j] == GATE) {
					// add all gates to the queue
					q.offer(new Pos(i, j));
				}
			}
 		}
		
		while(!q.isEmpty()) {
			Pos curPos = q.poll();
			int x = curPos.x;
			int y = curPos.y;
			for(int i = 0; i < 4; i ++) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
				if (next_x >= 0 && next_x < rLen && next_y >= 0 && next_y < cLen &&
						rooms[next_x][next_y] == EMPTY) {
					rooms[next_x][next_y] = rooms[x][y] + 1;
				    // update the neighbor's distance to nearest gate
				    q.offer(new Pos(next_x, next_y));
				    // add it to the queue
				}
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			Debug.printMatrix(rooms);
			System.out.println("-------------------");
		}
	}
	
	
	
	/*
	 * 287
	 * Find the Duplicate Number
	 * Given an array nums containing n + 1 integers 
	 * where each integer is between 1 and n (inclusive), 
	 * prove that at least one duplicate number must exist. 
	 * Assume that there is only one duplicate number, find the duplicate one.
	 */
	public static void test287() {
		int[] nums = {2,4,3,1,4};
		int rev = task287_findDuplicate2(nums);
		System.out.println("rev = " + rev);
	}
	
	
	public static int task287_findDuplicate(int[] nums) {
		int slow = 0, fast = 0;
		while(fast >= 0 && fast < nums.length) {
			fast = nums[nums[fast]];
			slow = nums[slow];
			if (fast == slow) {
				break;
			}
		}
		fast = 0;
		while(fast != slow) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return fast;
	}
	
	
	
	/*
	 * http://segmentfault.com/a/1190000003817671
	 * 二分枚举答案范围，使用鸽笼原理进行检验
	 * 根据鸽笼原理，给定n + 1个范围[1, n]的整数，其中一定存在数字出现至少两次。
	 * 假设枚举的数字为 n / 2：
	 * 遍历数组，若数组中不大于n / 2的数字个数超过n / 2，则可以确定[1, n /2]范围内一定有解，
	 * 否则可以确定解落在(n / 2, n]范围内。
	 *
	 * Time: N log N
	 * 
	 * https://leetcode.com/discuss/60830/python-solution-explanation-without-changing-input-array
	 * Let count be the number of elements in the range 1 .. mid, as in your solution.
	 * If count > mid, then there are more than mid elements in the range 1 .. mid and 
	 * thus that range contains a duplicate.
	 * If count <= mid, then there are n+1-count elements in the range mid+1 .. n. 
	 * That is, at least n+1-mid elements in a range of size n-mid. Thus this range must contain a duplicate.
	 */
	public static int task287_findDuplicate2(int[] nums) {
		int left = 0, right = nums.length - 1;
		while(left < right) {
			int mid = left + (right - left)/2;
			int count = task287_getCount(nums, mid);
		
			if (count > mid) {
				// duplicate is in the left side
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
	
	public static int task287_getCount(int[] nums, int mid) {
		int count = 0;
		for(int i = 0; i < nums.length; i ++) {
			if (nums[i] <= mid) {
				count ++;
			}
		}
		return count;
	}
	
	
	/*
	 * 288
	 * Unique Word Abbreviation
	 * see ValidWordAbbr in the same file
	 */
	public static void test288(){
		
	}
	
	
	
	/*
	 * 289
	 * Game of Life
	 * https://leetcode.com/discuss/68352/easiest-java-solution-with-explanation
	 * 
	 */
	public static void test289() {
		
	}
	public void task289_gameOfLife(int[][] board) {
	    if(board == null || board.length == 0) return;
	    int m = board.length, n = board[0].length;

	    for(int i = 0; i < m; i++) {
	        for(int j = 0; j < n; j++) {
	            int lives = liveNeighbors(board, m, n, i, j);

	            // In the beginning, every 2nd bit is 0;
	            // So we only need to care about when the 2nd bit will become 1.
	            if(board[i][j] == 1 && lives >= 2 && lives <= 3) {  
	                board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
	            }
	            if(board[i][j] == 0 && lives == 3) {
	                board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
	            }
	        }
	    }

	    for(int i = 0; i < m; i++) {
	        for(int j = 0; j < n; j++) {
	            board[i][j] >>= 1;  // Get the 2nd state.
	        }
	    }
	}

	public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
	    int lives = 0;
	    for(int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
	        for(int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
	            lives += board[x][y] & 1;
	        }
	    }
	    lives -= board[i][j] & 1;
	    return lives;
	}
	
	/*
	 * 290
	 * Word Pattern
	 */
	public static void test290() {
		String pattern = "abba", str = "dog cat cat dog" ;
		boolean rev = task290_wordPattern(pattern, str);
		System.out.println(rev);
	}
	
	public static boolean task290_wordPattern(String pattern, String str) {
		// split the input
        String[] strArr = str.split(" ");
        if (strArr.length != pattern.length()) {
			return false;
		}
        // map from char in pattern to word in strArr
        HashMap<Character, String> map = new HashMap<Character, String>();
        // traverse the pattern, and the strArr. They share the same iterator index
        for(int i = 0; i < pattern.length(); i ++) {
        	char curCh = pattern.charAt(i);
        	String curStr = strArr[i];
        	// if map doesn't contain curCh
        	if (!map.containsKey(curCh)) {
        		// if strArr[i] is already mapped
        		if (map.containsValue(curStr)) {
					return false;
				}
        		// put the <curCh, curStr> into map
				map.put(curCh, curStr);
			} else {
				// the curCh already in the Map
				String mappedStr = map.get(curCh);
				if (!strArr[i].equals(mappedStr)) {
					return false;
				}
			}
        }
        return true;
    }
	
	
	/*
	 * 291
	 * Word Pattern II
	 * 
	 *
	 * https://leetcode.com/discuss/63252/share-my-java-backtracking-solution
	 */
	public static void test291() {
		
	}
	
	public static boolean task290_wordPatternMatch(String pattern, String str) {
		Map<Character, String> map = new HashMap<Character, String>();
		Set<String> set = new HashSet<String>();
		return task290_helper(str, 0, pattern, 0, map, set);
	}
	
	public static boolean task290_helper(String str,int indexS,  
			String pattern, int indexP, 
			Map<Character, String> map, 
			Set<String> set) {
		// base case
		if (indexS == str.length() && indexP == pattern.length()) {
			return true;
		}
		if (indexS == str.length() || indexP == pattern.length()) {
			return false;
		}
		
		// get current pattern character
		char curP = pattern.charAt(indexP);
		
		// if pattern exist
		if (map.containsKey(curP)) {
			String s_mapped = map.get(curP);
			
			// check if we can use it to match str[i..i + s.length()]
			if (!str.startsWith(s_mapped, indexS)) {
				// if NOT matched, return false
				return false;
			} else {
				// if it can match, continue to match the rest
				return task290_helper(str, indexS + s_mapped.length(), pattern, indexP + 1, map, set);
			}
		}
		
		// pattern character doesn't not exist in the map
		for(int k = indexS; k < str.length(); k ++) {
			String s_to_map = str.substring(indexS, k + 1);
			
			if (set.contains(s_to_map)) {
				continue;
			}
			
			// create or update it
			map.put(curP, s_to_map);
			set.add(s_to_map);
			
			// continue to match the rest
			if (task290_helper(str, k + 1, pattern, indexP + 1, map, set)) {
				return true;
			}
			
			// backtracking
			map.remove(curP);
			set.remove(s_to_map);
		}
		return false;
	}
	
	
	
	public boolean wordPatternMatch(String pattern, String str) {
	    Map<Character, String> map = new HashMap<Character, String>();
	    return helper(pattern, 0, str, 0, map);
	}

	public boolean helper(String pattern, int pPos, String str, int sPos, Map<Character, String> map) {
	    if(sPos == str.length() && pPos == pattern.length()) return true;

	    if(sPos == str.length() || pPos == pattern.length()) return false;

	    char c = pattern.charAt(pPos);

	    for(int i = sPos; i < str.length(); i++) {
	        String substr = str.substring(sPos, i+1);
	        
	        if(map.containsKey(c) && map.get(c).equals(substr) ) {
	            if(helper(pattern, pPos+1, str, i+1, map)) return true;
	        }
	        
	        if(!map.containsKey(c) && !map.containsValue(substr) ) {
	            map.put(c, substr);
	            if(helper(pattern, pPos+1, str, i+1, map)) return true;
	            map.remove(c);
	        }
	    }           
	    return false;
	}
	
	/*
	 * 292
	 * Nim Game
	 * 当n∈[1,3]时，先手必胜。
	 * 当n == 4时，无论先手第一轮如何选取，下一轮都会转化为n∈[1,3]的情形，此时先手必负。
	 * 当n∈[5,7]时，先手必胜，先手分别通过取走[1,3]颗石头，可将状态转化为n == 4时的情形，此时后手必负。
	 * 当n == 8时，无论先手第一轮如何选取，下一轮都会转化为n∈[5,7]的情形，此时先手必负。
	 * 
	 * 当n % 4 != 0时，先手必胜；否则先手必负。
	 */
	public static boolean task292_canWinNim(int n) {
		return n%4 != 0;
	}
	
	
	
	/*
	 * 293
	 * Flip Game
	 */
	public static void test293() {
		String s = "++++";
		List<String> rev = task293_generatePossibleNextMoves(s);
		System.out.println(rev);
	}
	
	
	public static List<String> task293_generatePossibleNextMoves(String s) {
		List<String> result = new ArrayList<String>();
		if ( s == null || s.length() < 2) {
			return result;
		}
		char[] array = s.toCharArray();
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == '+' && array[i + 1] == '+') {
				array[i] = '-';
				array[i + 1] = '-';
				String onesln = new String(array);
				result.add(onesln);
				// reovery array
				array[i] = '+';
				array[i + 1] = '+';
			}
		}
		return result;
	}
	
	/*
	 * 294
	 * Flip Game II
	 */
	public static void test294() {
		String s = "++++++-++++++-++++++";
		boolean rev = task294_canWin(s);
		System.out.println("rev = " + rev);
	}
	
	public static boolean task294_canWin(String s) {
        if (s == null || s.length() < 2) {
			return false;
		}
        char[] array = s.toCharArray();
        Wrapper result = new Wrapper(false);
        task294_helper(array, 1, result);
        return result.val;
    }
	
	public static class Wrapper{
		boolean val;
		public Wrapper(boolean val ) {
			this.val = val;
		}
	}
	
	public static void task294_helper(char[] array, int num, Wrapper result) {
		if (num % 2 == 0 && task294_noAdjacentPlus(array)) {
			result.val = true;
			return ;
		}
		for(int i = 0; i < array.length - 1; i ++) {
			if (array[i] == '+' && array[i + 1] =='+') {
				array[i] = '-';
				array[i] = '-';
				
				task294_helper(array, num  + 1, result);
				// recovery
				array[i] = '+';
				array[i + 1] = '+';
			}
		}

	}
	
	public static boolean task294_noAdjacentPlus(char[] array) {
		for(int i = 0; i < array.length - 1; i ++) {
			if (array[i] == '+' && array[i + 1] == '+') {
				return false;
			}
		}
		return true;
	}
	
	
	public static void test_294_1() {
		char[] s = "++++".toCharArray();
		boolean rev = task294_canWin_1(s);
		System.out.println("rev = " + rev);
		
		String input = "++++";
		boolean revopt = task294_canWin_opt(input);
		System.out.println("rev2 = " + revopt);
	}
	public static boolean task294_canWin_1(char[] s) {
		int n = s.length;

		for (int i = 0; i < n - 1; ++i) {
			if (s[i] == '+' && s[i + 1] == '+') {
				char[] ss = Arrays.copyOf(s, s.length);
				ss[i] = '-';
				ss[i + 1] = '-';
				if (!task294_canWin_1(ss))
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * https://leetcode.com/discuss/64291/share-my-java-backtracking-solution
	 * The idea of the solution is clear, but the time complexity of the backtracking method is high. During the process of searching, we could encounter duplicate computation as the following simple case.
	 * One search path:
	 * Input s = "++++++++"
	 * Player 0: "--++++++"
	 * Player 1: "----++++"
	 * Player 0: "----+--+"
	 * Player0 can win for the input string as "----++++".
	 * Another search path:
	 * Player 0: "++--++++"
	 * Player 1: "----++++"
	 * Player 0: "----+--+"
	 * (Duplicate computation happens. We have already known anyone can win for the
	 * input string as "----++++".)
	 * Use a HashMap to avoid duplicate computation
	 * Key : InputString.
	 * Value: can win or not.
	 */
	
	
	public static boolean task294_canWin_opt(String s) {
		Map<String, Boolean> map = new HashMap<>();
		task294_helper_opt(s, map);
		return map.get(s);
	}
	
	
	public static boolean task294_helper_opt(String s, Map<String, Boolean> map) {
		if (map.containsKey(s)) {
			return map.get(s);
		}
		
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.startsWith("++", i)) {
				String nextStr = s.substring(0, i) + "--" + s.substring(i + 2);
				if (!task294_helper_opt(nextStr, map)) {
					// next one cannot win. 
					map.put(s, true);
					return true;
				}
			}
		}
		
		// if all of the above cannot find the win solution
		map.put(s, false);
		return false;
	}
	
	
	
	/*
	 * 295 Median of Data Stream
	 * refer task295_MeidanDataStream
	 */
	
	/*
	 * 296
	 * Best Meeting Point
	 * https://leetcode.com/problems/best-meeting-point/
	 * https://leetcode.com/course/chapters/leetcode-101/best-meeting-point/
	 */
	public static void test296() {
		int[][] grid = {
				{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}
		};
		
		int minDistance = task296_minTotalDistance(grid);
		System.out.println("minDistance = " + minDistance);
		
	}
	
	public static int task296_minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
        int rLen = grid.length;
        int cLen = grid[0].length;
        int[][] counter = new int[rLen][cLen];
        for(int i = 0; i < rLen; i ++) {
        	for(int j = 0; j < cLen; j ++) {
        		if (grid[i][j] == 1) {
					boolean[][] visited = new boolean[rLen][cLen];
					task296_BFS(grid, counter, visited, i, j);
				}
        	}
        }
        
        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < rLen; i++) {
        	for(int j = 0; j < cLen; j ++) {
        		minDistance = Math.min(minDistance, counter[i][j]);
        	}
        }
        
        return minDistance;
    }
	
	public static void task296_BFS(int[][] grid, int[][] counter, boolean[][] visited, int x, int y) {
		
		LinkedList<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(x, y));
		visited[x][y] = true;
		int distance = 0; 
		while(!q.isEmpty()) {
			int size = q.size(); 
			for(int i = 0; i < size; i ++) {
				Pos cur = q.poll();
				counter[cur.x][cur.y] += distance;
				ArrayList<Pos> neighbors = task296_getNeighbors(grid, visited, cur.x, cur.y);
				for(Pos nei : neighbors) {
					q.add(nei);
					visited[nei.x][nei.y] = true;
				}
			}
			distance ++;
		}
	}
	
	
	public static ArrayList<Pos> task296_getNeighbors(int[][] grid, boolean[][] visited, int x, int y) {
		int rLen = grid.length;
		int cLen = grid[0].length;
		ArrayList<Pos> neighbors = new ArrayList<Pos>();
		for(int i = 0; i < 4; i ++) {
			
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			if (next_x >= 0 && next_x < rLen && next_y >= 0 && next_y < cLen && !visited[next_x][next_y]) {
				neighbors.add(new Pos(next_x, next_y));
			}
		}
		
		return neighbors;
	}
	
	
	
	/*
	 * better solution
	 * Time: O(m*n)
	 * 
	 * in 1D array, e.g, {1,0,0,0,1,1}, the point to all "1" with minimum distance is all 1's index's median. 
	 * here, {0, 4, 5}. The position is 4, there is one "1" in the left and one "1" in the right, 
	 * if we move the desired one step to left, the left side will minus 1, but the right side will plus 2. 
	 * So, the total distance will increase 1. 
	 * 
	 * So, in 1D array, the median of all 1's index will be the minimum distance position. 
	 * 
	 * to expand to 2D. 
	 * Since the Mahantan Distance = |x1 - x2| + |y1 - y2|
	 * we can get the point's row array and column array. 
	 * 
	 * And get row's median and col's median. 
	 * 
	 * The two median will be the result. 
	 * 
	 * 
	 */
	public static int task296_minTotalDistance_better(int[][] grid) {
		List<Integer> rows = collectRows(grid);
		List<Integer> cols = collectCols(grid);
		int rowMedian = rows.get(rows.size() / 2);
		int colMedian = cols.get(cols.size() / 2);
		return minDistance1D(rows, rowMedian) + minDistance1D(cols, colMedian);
	}

	private static int minDistance1D(List<Integer> points, int median) {
		int distance = 0;
		for (int point : points) {
			distance += Math.abs(point - median);
		}
		return distance;
	}

	private static List<Integer> collectRows(int[][] grid) {
		List<Integer> rows = new ArrayList<Integer>();
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				if (grid[row][col] == 1) {
					rows.add(row);
				}
			}
		}
		return rows;
	}

	private static List<Integer> collectCols(int[][] grid) {
		List<Integer> cols = new ArrayList<Integer>();
		for (int col = 0; col < grid[0].length; col++) {
			for (int row = 0; row < grid.length; row++) {
				if (grid[row][col] == 1) {
					cols.add(col);
				}
			}
		}
		return cols;
	}
	
	private static int minDistance1D_2(List<Integer> points) {
	    int distance = 0;
	    int i = 0;
	    int j = points.size() - 1;
	    while (i < j) {
	        distance += points.get(j) - points.get(i);
	        i++;
	        j--;
	    }
	    return distance;
	}
	
	/*
	 * task297
	 * Serialize and Deserialize Binary Tree
	 * see in same directory
	 * Task297_SerializeDeserializeBinaryTree
	 */
	public static void test297() {
		
	}
	
	/*
	 * 298 Binary Tree Longest Consecutive Sequence
	 */
	public int longestConsecutive(TreeNode root) {
		if (root == null)
			return 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		q.add(root);
		q2.add(1);
		int max = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode current = q.poll();
				int count = q2.poll();
				if (current.left != null) {
					q.add(current.left);
					if (current.left.val == current.val + 1) {
						q2.add(count + 1);
						max = count + 1 > max ? count + 1 : max;
					} else
						q2.add(1);
				}
				if (current.right != null) {
					q.add(current.right);
					if (current.right.val == current.val + 1) {
						q2.add(count + 1);
						max = count + 1 > max ? count + 1 : max;
					} else
						q2.add(1);
				}
			}
		}
		return max;
	}
	
	// method2
	private static int max;
	public static int longestConsecutive_method2(TreeNode root) {
	    if(root==null) return 0;
	    max = 1;
	    helper(root, 1);
	    return max;
	}
	private static void helper(TreeNode root, int maxCurrent) {
	    if(root==null) return;
	    if(root.left!=null) {
	        if(root.left.val == root.val+1) {
	            max = maxCurrent+1 > max ? maxCurrent+1 : max;
	            helper(root.left, maxCurrent+1);
	        }
	        else
	            helper(root.left, 1);
	    }
	    if(root.right!=null) {
	        if(root.right.val == root.val+1) {
	            max = maxCurrent+1 > max ? maxCurrent+1 : max;
	            helper(root.right, maxCurrent+1);
	        }
	        else
	            helper(root.right, 1);
	    }

	}
	
	
	// method3
	public int longestConsecutive_method3(TreeNode root) {
	    if(root == null){
	        return 0;
	    }

	    return Math.max(longest(root.left, 1, 1, root.val), longest(root.right, 1, 1, root.val));
	}

	public int longest(TreeNode root, int localLen, int retLen, int preVal){
	    if(root == null){
	        return retLen;
	    }

	    if(root.val == preVal + 1){
	        localLen++;
	        retLen = Math.max(localLen, retLen);
	    }else{
	        localLen = 1;
	    }

	    return Math.max(longest(root.left, localLen, retLen, root.val), longest(root.right, localLen, retLen, root.val));
	}
	
	/*
	 * 299 Bulls and Cows
	 */
	public String getHint(String secret, String guess) {
		int bulls = 0, cows = 0;
		// This is for recording the occurring time of each number in secret
		// number
		HashMap<Character, Integer> check = new HashMap<Character, Integer>();
		// This is for storing which numbers in guess number are left after
		// getting rid of bulls
		char[] guessA = guess.toCharArray();
		// Initialized the HashMap
		for (Integer i = 0; i < secret.length(); i++) {
			if (check.containsKey(secret.charAt(i)))
				check.put(secret.charAt(i), check.get(secret.charAt(i)) + 1);
			else
				check.put(secret.charAt(i), 1);
		}
		// First, every time we find a bull, reducing its occurring time once in
		// HashMap and change the bull in guess number to 'A'
		for (Integer i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == guess.charAt(i)) {
				bulls++;
				check.put(secret.charAt(i), check.get(secret.charAt(i)) - 1);
				guessA[i] = 'A';
			}
		}
		// Next, check the cows. According to array guessA, if any number in
		// guessA is contained in HashMap and occurring time is not zero, cows
		// automatically add one and reducing the occurring time once;
		for (Integer i = 0; i < guessA.length; i++) {
			if (check.containsKey(guessA[i]) && check.get(guessA[i]) != 0) {
				cows++;
				check.put(guessA[i], check.get(guessA[i]) - 1);
			}
		}
		// This way is more efficient than bulls + "A" + cows + "B" since the
		// latter way have intermediate objects that are created behind the
		// scene;
		return String.valueOf(bulls) + "A" + String.valueOf(cows) + "B";
	}
	
	/*
	 * task300 
	 * Longest Increasing subsequence
	 * Given an unsorted array of integers, find the length of longest increasing subsequence.
	 * For example,
	 * Given [10, 9, 2, 5, 3, 7, 101, 18],
	 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
	 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
	 * Your algorithm should run in O(n2) complexity.
	 * Follow up: Could you improve it to O(n log n) time complexity?
	 */
	/*
	 * M[i] Length of LIS ending in i
	 * M[0] = 1
	 * M[i] = max(M[j]) + 1 for nums[j] < nums[i]
	 * 
	 */
	public static int task300_LengthOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] M = new int[n];
		int max = 0;
		for(int i = 0; i < n; i ++) {
			if (i == 0) {
				M[0] = 1;
			} else {
				int curMax = 0;
				for(int j = 0; j < i; j ++) {
					if (nums[j] < nums[i]) {
						curMax = Math.max(curMax, M[j]);
					}				
				}
				M[i] = curMax + 1;
			}
			max = Math.max(max, M[i]);
		}
		return max;
	}
	
	/*
	 * follow up 
	 * in O(n log n)
	 * see jiuzhangAdvanceAlgorithm
	 */
	
	public static void test300_2() {
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		int rev = task300_lengthOfLIS2(nums);
		System.out.println("rev= " + rev);
	}
	public static int task300_lengthOfLIS2(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;

		for (int x : nums) {
			System.out.println("===================");
			int i = Arrays.binarySearch(dp, 0, len, x);
			System.out.println(" x = " + x + " i = " + i);
			
			if (i < 0){
				i = -(i + 1);
				// i < 0, i is the ( - insertPos - 1)
				// i = - insertPos - 1
				// insertPos = -(i + 1)
				// insert x insertPos 
			}
				
			dp[i] = x;
			System.out.println("len = " + len);
			if (i == len)
				len++;
			Debug.printArray(dp);
			System.out.println(" i = "  + i);
			System.out.println("len = " + len);
			
			System.out.println("--------------------");
		}

		return len;
	}
	
	/*
	 * index of the search key, if it is contained in the array within the specified range; 
	 * otherwise, (-(insertion point) - 1). 
	 * The insertion point is defined as the point at which the key would be inserted into the array: 
	 * the index of the first element in the range greater than the key, 
	 * or toIndex if all elements in the range are less than the specified key. 
	 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
	 */


	public static void test() {
		String s = "415+architects";
		String res = escapeSolrValue(s);
		System.out.print(res);
	}

	public static String escapeSolrValue(String s) {
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          // These characters are part of the query syntax and must be escaped
          if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
            || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
            || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
            || Character.isWhitespace(c)) {
            sb.append('\\');
          }
          sb.append(c);
        }
    	return sb.toString();
	}

}
