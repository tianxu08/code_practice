package sortbynum;

import ds.*;
import mj_linkedin.MyIntervalLinkedIn4.ITNode;

import java.awt.print.Printable;
import java.util.*;
import java.util.Map.Entry;


public class Task201_250 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test201();
		// test202();
		// test204();
//		test205();
//		 test210();
//		 test215();
//		test218();
//		test224();
//		test228();
		 test238();
		// test239();
		// test228();
		// test216();
		// test220();
//		 test243();
		// test246();
		// test247();
		// test247_2();
		// test248();
//		test248_2();
		// test249();
	}

	/*
	 * 201 Bitwise And of numbers range
	 */
	public static void test201() {
		int m = 5, n = 7;
		int rev = task201_rangeBitwiseAnd(m, n);
		System.out.println("rev = " + rev);
		int rev2 = task201_rangeBitwiseAndOPT(m, n);
		System.out.println("rev2 = " + rev2);
	}

	public static int task201_rangeBitwiseAnd(int m, int n) {
		int sum = Integer.MAX_VALUE;
		for (int i = m; i <= n; i++) {
			System.out.println(Integer.toBinaryString(i));
			sum &= i;

		}
		return sum;
	}

	// this doesn't work well
	public static int task201_rangeBitwiseAndOPT(int m, int n) {
		int mask = Integer.MAX_VALUE;
		while ((m & mask) != (n & mask)) {
			mask <<= 2;
		}
		return mask;
	}

	// http://www.cnblogs.com/grandyang/p/4431646.html
	// http://www.programcreek.com/2014/04/leetcode-bitwise-and-of-numbers-range-java/
	public static int rangeBitwiseAnd(int m, int n) {
		while (n > m) {
			n = n & n - 1;
		}
		return m & n;
	}

	/*
	 * 202 Happy Number A happy number is a number defined by the following
	 * process: Starting with any positive integer, replace the number by the
	 * sum of the squares of its digits, and repeat the process until the number
	 * equals 1 (where it will stay), or it loops endlessly in a cycle which
	 * does not include 1. Those numbers for which this process ends in 1 are
	 * happy numbers.
	 * 
	 * 照着题意来就好
	 */
	public static void test202() {
		int n = 2;
		boolean rev = task202_isHappy(n);
		System.out.println("rev = " + rev);
	}

	public static boolean task202_isHappy(int n) {
		HashSet<Integer> set = new HashSet<Integer>();
		while (!set.contains(n)) {
			set.add(n);
			ArrayList<Integer> digits = getDigits(n);
			n = getSum(digits);

			if (n == 1) {
				return true;
			}
		}
		return false;
	}

	public static int getSum(ArrayList<Integer> digits) {
		int sum = 0;
		for (int i = 0; i < digits.size(); i++) {
			sum += digits.get(i) * digits.get(i);
		}
		return sum;
	}

	public static ArrayList<Integer> getDigits(int n) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while (n > 0) {
			digits.add(n % 10);
			n /= 10;
		}
		return digits;
	}

	/*
	 * 203 Remove LInked List Elements
	 */
	public static ListNode task203_removeElements(ListNode head, int val) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;
		ListNode cur = dummy;
		while (cur != null && cur.next != null) {
			if (cur.next.val == val) {
				ListNode nnext = cur.next.next;
				cur.next.next = null;
				cur.next = nnext;
			} else {
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	/*
	 * 204 Count Primes
	 * 
	 * Count the number of prime numbers less than a non-negative number, n.
	 * 
	 * (1) isPrime() O(n) for 2.. n -1, call isPrime() n times. O(n)
	 * 
	 * total: O(n^2)
	 * 
	 * (2)
	 */
	public static void test204() {
		int n = 10;
		int rev1 = task204_countPrimes(n);
		int rev2 = task204_countPrimes_method1(n);
		System.out.println("rev1 = " + rev1);
		System.out.println("rev2 = " + rev2);
	}

	/*
	 * method1 use a isPrime() function to determine whether a number is prime,
	 * we need to check if it is NOT divisible by any number less than n. The
	 * runtime of isPrime() is O(n) and hence the total prime numbers up to n
	 * would be O(n^2).
	 */
	public static int task204_countPrimes_method1(int n) {
		int count = 0;
		for (int i = n - 1; i >= 2; i--) {
			if (isPrime_better(i)) {
				count++;
			}
		}
		return count;
	}

	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		for (int i = num - 1; i >= 2; i--) {
			if (num % i == 0) {
				return false;
			}
		}
		System.out.println(num);
		return true;
	}

	public static boolean isPrime_better(int num) {
		if (num <= 1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		for (int i = num / 2; i >= 2; i--) {
			if (num % i == 0) {
				return false;
			}
		}
		System.out.println(num);
		return true;
	}

	public static boolean isPrime_better2(int num) {
		if (num <= 1) {
			return false;
		}
		for (int i = 2; i * i <= num; i++) {
			if (num % 2 == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Sieve of Eratosthenes 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	 * 
	 * for 2, it's prime. marked 2*2, 2*2 + 2, 2*2 + 4, etc, they are NOT prime
	 * for 3, it's prime. marked 3*3, 3*3 + 3= 3*4, 3*5, etc, they are NOT
	 * prime. for 4, already marked as NON-Prime. continue for 5, etc
	 * 
	 * After the loop, we will get the prime number smaller than n Time: O(n *
	 * log logn)
	 */
	public static int task204_countPrimes(int n) {
		if (n <= 2) {
			return 0;
		}
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}
		int count = 0;

		// loop ends condition is i * i < n
		for (int i = 2; i * i < n; i++) {
			if (isPrime[i] == false) { // if we already know i is NOT prime,
										// skip
				continue;
			}
			for (int j = i * i; j < n; j += i) {
				isPrime[j] = false;
			}
		}

		for (int i = 2; i < n; i++) {
			if (isPrime[i]) {
				count++;
			}
		}
		return count;

	}

	/*
	 * 205 Isomorphic Strings
	 * 
	 * the key is that char in s mapped to char in t 1-1
	 */
	public static void test205() {
		String s = "ab";
		String t = "cd";
		boolean test = task205_isIsomorphic(s, t);
		System.out.println("-==>>> test: " + test);
	}

	public static boolean task205_isIsomorphic(String s, String t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			Character s_ch = s.charAt(i);
			Character t_ch = t.charAt(i);

			if (!map.containsKey(s_ch)) {
				if (map.containsValue(t_ch)) {
					// map donesn't contains s_ch but map.value contains t_ch
					return false;
				} else {
					map.put(s_ch, t_ch);
				}
			} else {
				// check whether t_ch equals map.get(s_ch)
				if (!t_ch.equals(map.get(s_ch))) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 206 Reverse LInked LIst
	 */
	public static ListNode task206_reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = task206_reverseList(next);
		head.next = null;
		next.next = head;
		return newHead;
	}

	/*
	 * 207 Course Schedule topological sort see 210
	 */

	/*
	 * 208 implement Trie (Prefix Tree) see Trie in ds
	 */

	/*
	 * 209 Minimum Size SubArray Sum
	 */
	public static int task209_minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int curSum = 0;
		int minLen = Integer.MAX_VALUE;
		int slow = 0, fast = 0;
		while (slow < nums.length) {
			while (fast < nums.length && curSum < s) {
				curSum += nums[fast];
				fast++;
			}

			if (curSum > s) {
				minLen = Math.min(minLen, fast - slow);
			}
			curSum -= nums[slow];
			slow++;
		}

		if (minLen == Integer.MAX_VALUE) {
			return 0;
		}

		return minLen;
	}

	/*
	 * 210 Course Schedule II
	 * 
	 * Topological sorting
	 * 
	 */
	public static void test210() {
		int numCourses = 3;
		int[][] prerequisites = { { 0, 1 }, { 0, 2 }, { 1, 2 } };

		int[] result = findOrder(numCourses, prerequisites);
		System.out.println(Arrays.toString(result));
	}

	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] result = new int[numCourses];
		int index = 0;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// put all neighbors into map
		for (int i = 0; i < prerequisites.length; i++) {
			int indegree = 0;
			if (map.containsKey(prerequisites[i][0])) {
				indegree = map.get(prerequisites[i][0]);
			}
			map.put(prerequisites[i][0], indegree + 1);
		}

		// put all indegree == 0 node into queue
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (!map.containsKey(i)) {
				q.offer(i);
				result[index++] = i;
			}
		}
		// do BFS
		while (!q.isEmpty()) {
			Integer cur = q.poll();
			ArrayList<Integer> neighbors = getNeighbors(cur, prerequisites);

			for (Integer nei : neighbors) {
				if (map.containsKey(nei)) {
					// in-degree --
					map.put(nei, map.get(nei) - 1);
					if (map.get(nei) == 0) {
						q.offer(nei);
						result[index++] = nei;
						map.remove(nei);
					}
				}
			}
		}

		// check if the map.size == 0
		if (map.size() != 0) {
			// there are circles in the graph.
			return new int[0];
		}

		return result;
	}

	public static ArrayList<Integer> getNeighbors(Integer cur,
			int[][] prerequisites) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < prerequisites.length; i++) {
			if (prerequisites[i][1] == cur) {
				neighbors.add(prerequisites[i][0]);
			}
		}
		return neighbors;
	}

	// add topological using DFS
	// for large test, it will timeout
	public static int[] task210_findOrder2(int numCourses, int[][] prerequisites) {
		HashSet<Integer> visiting = new HashSet<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < numCourses; i++) {
			task210_visit(i, prerequisites, list, visiting, visited);
		}
		if (NOTDAG) {
			return new int[0];
		}

		int[] result = new int[numCourses];
		int index = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			result[index] = list.get(i);
			index++;
		}
		return result;
	}

	public static boolean NOTDAG = false;

	public static void task210_visit(int n, int[][] prerequisites,
			ArrayList<Integer> list, HashSet<Integer> visiting,
			HashSet<Integer> visited) {
		if (visited.contains(n)) {
			return;
		}
		if (visiting.contains(n)) {
			NOTDAG = true;
			return;
		}
		visiting.add(n);

		ArrayList<Integer> neighbors = getNeighbors(n, prerequisites);
		for (Integer nei : neighbors) {
			task210_visit(nei, prerequisites, list, visiting, visited);
		}
		visited.add(n);
		visiting.remove(n);
		list.add(n);
	}

	/*
	 * 211 Add and Search Word -- data structure design
	 * 
	 * see Word Dictionary in the same directory
	 */
	public static void test211() {

	}

	/*
	 * 212 Word Search II see Word Dictionary in the same directory
	 */
	public static void test212() {

	}

	/*
	 * 213 House Robber 2
	 * 
	 * 因为第一个element 和最后一个element不能同时出现. 则分两次call House Robber I. 
	 * case 1:不包括最后一个element. 
	 * case 2: 不包括第一个element.
	 */
	public static int task213_rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int n = nums.length;

		if (nums.length == 1) {
			return nums[0];
		}

		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		if (nums.length == 3) {
			return Math.max(Math.max(nums[0], nums[1]), nums[2]);
		}

		// including nums[0], not include num[n - 1]
		int[] M1 = new int[nums.length];
		M1[0] = nums[0];
		M1[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length - 1; i++) {
			M1[i] = Math.max(M1[i - 1], M1[i - 2] + nums[i]);
		}

		// including num[n - 1], not include nums[0]
		int[] M2 = new int[nums.length];
		M2[1] = nums[1];
		M2[2] = Math.max(nums[1], nums[2]);
		for (int i = 3; i < nums.length; i++) {
			M2[i] = Math.max(M2[i - 1], M2[i - 2] + nums[i]);
		}

		return Math.max(M1[n - 2], M2[n - 1]);
	}
	
	

	/*
	 * 214 Shortest Palindrome
	 * KMP 算法
	 */
	public static String task214_shortestPalindrome(String s) {
		StringBuilder stb = new StringBuilder(s);
		String r = stb.reverse().toString();
		String t = s + "#" + r;
		int[] p = new int[t.length()];
		for (int i = 1; i < t.length(); i++) {
			int j = p[i - 1];
			while (j > 0 && t.charAt(i) != t.charAt(j)) {
				j = p[j - 1];
			}
			if (t.charAt(i) == t.charAt(j)) {
				p[i] = j + 1;
			} else {
				p[i] = j;
			}

		}
		return r.substring(0, s.length() - p[t.length() - 1]) + s;
	}

	/*
	 * 215 kth larlgest elemnt in an array
	 */
	public static void test215() {
		int[] a = { 3, 2, 1, 5, 6, 4 };
		int k = 2;
		int rev = task215_findKthLargest(a, k);
		System.out.println(rev);
	}

	public static int task215_findKthLargest(int[] nums, int k) {
		// nums.length - k is the index(position) of the kth largest element
		task215_quickSelect(nums, 0, nums.length - 1, nums.length - k);
		return nums[nums.length - k];
	}

	public static void task215_quickSelect(int[] nums, int start, int end,
			int targetIdx) {
		int pivotIndex = task215_partition(nums, start, end);
		if (pivotIndex == targetIdx) {
			return;
		} else if (targetIdx > pivotIndex) {
			task215_quickSelect(nums, pivotIndex + 1, end, targetIdx);
		} else {
			// target < pivotIndex
			task215_quickSelect(nums, start, pivotIndex - 1, targetIdx);
		}
	}

	// return a random that in [start, end]
	public static int task215_getRandom(int start, int end) {
		Random random = new Random();
        return random.nextInt((end - start) + 1) + start;
	}
	public static int task215_partition(int[] nums, int start, int end) {
		int pivotIdx = task215_getRandom(start, end);
		// swap the pivot with the end
		task215_swap(nums, pivotIdx, end);
		int pivot = nums[end];
		int left = start, right = end - 1;
		while (left <= right) {   // !!! left <= right
			if (nums[left] < pivot) {
				left++;
			} else if (nums[right] >= pivot) {
				right--;
			} else {
				task215_swap(nums, left, right);
				left++;
				right--;
			}
		}
		
		task215_swap(nums, left, end);
		// after swap, the left is the pivot's position
		return left;
	}

	public static void task215_swap(int[] nums, int x, int y) {
		int temp = nums[x];
		nums[x] = nums[y];
		nums[y] = temp;
	}

	/*
	 * 216 Combanition Sum 3
	 * 
	 * k = 3, n = 7
	 * 
	 * 1 2 4
	 * 
	 * k = 3, n = 9
	 * 
	 * 1 2 6, 1 3 5, 2 3 4
	 */
	public static void test216() {
		int k = 3, n = 9;
		List<List<Integer>> result = task216_combinationSum3(k, n);
		System.out.println(result);
	}

	public static List<List<Integer>> task216_combinationSum3(int k, int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> line = new ArrayList<Integer>();
		task216_helper(k, n, line, result, 1, 0);

		return result;
	}

	public static void task216_helper(int k, int n, List<Integer> buf,
			List<List<Integer>> result, int pos, int sum) {
		if (k == 0) {
			if (sum == n) {
				result.add(new ArrayList<Integer>(buf));
			}
			return;
		}
		for (int i = pos; i <= 9; i++) {
			if (sum + i > n) {
				break;
			}
			buf.add(i);
			sum += i;

			task216_helper(k - 1, n, buf, result, i + 1, sum);

			sum -= i;
			buf.remove(buf.size() - 1);
		}

	}

	/*
	 * 217 Contains Duplicates
	 */
	public static boolean task217_containsDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		HashSet<Integer> set = new HashSet<Integer>();
		set.add(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return true;
			} else {
				set.add(nums[i]);
			}
		}
		return false;
	}

	/*
	 * 218 The skyline problem refer to jiuzhangAdvancedAlgorithm and
	 * lai.small_yan.interval
	 * 
	 * 
	 * (1) 自建一个名为Height的数据结构，保存一个building的index和height。
	 * 约定，当height为负数时表示这个高度为height的building起始于index；height为正时表示这个高度为height的building终止于index。
	 * (2) 对building数组进行处理，每一行[ Li, Ri, Hi ]，根据Height的定义，转换为两个Height的对象，
	 * 即，Height(Li, -Hi) 和 Height(Ri, Hi)。 将这两个对象存入heights这个List中。
	 * (3) 写个Comparator对heights进行升序排序，首先按照index的大小排序，若index相等，则按height大小排序，
	 * 以保证一栋建筑物的起始节点一定在终止节点之前。
	 * (4) 将heights转换为结果。使用PriorityQueue对高度值进行暂存。
	 * 遍历heights，遇到高度为负值的对象时，表示建筑物的起始节点，此时应将这个高度加入PriorityQueue。
	 * 遇到高度为正值的对象时，表示建筑物的终止节点，此时应将这个高度从PriorityQueue中除去。
	 * 且在遍历的过程中检查，当前的PriorityQueue的peek()是否与上一个iteration的peek()值（prev）相同，
	 * 若否，则应在结果中加入[当前对象的index, 当前PriorityQueue的peek()]，并更新prev的值。
	 */
	public static void test218() {
		int[][] buildings = {
				{2,9,10},
				{3,7,15},
				{5,12,12},
				{15,20,20},
				{19,24,8}
		};
		List<int[]> result = task218_getSkyline(buildings);
		
	}
	
	public static List<int[]> task218_getSkyline(int[][] buildings) {
		List<int[]> result = new ArrayList<int[]>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return result;
		}

		List<Height> heights = new ArrayList<Height>();
		for (int[] building : buildings) {
			heights.add(new Height(building[0], -building[2]));
			heights.add(new Height(building[1], building[2]));
		}
		
		// 首先按照index的大小排序，若index相等，则按height大小排序，以保证一栋建筑物的起始节点一定在终止节点之前
		Collections.sort(heights, new Comparator<Height>() {
			@Override
			public int compare(Height h1, Height h2) {
				if (h1.index == h2.index) {
					return h1.height - h2.height;
				}
				return h1.index - h2.index;
			}
		});
		
		for(Height h : heights) {
			System.out.println(h);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(1000,
				Collections.reverseOrder());
		pq.offer(0);
		int prev = 0;
		System.out.println("=======================");
		for (Height h : heights) {
			if (h.height < 0) {  // start point
				pq.offer(-h.height);
			} else {
				pq.remove(h.height);
			}
			int cur = pq.peek();
			System.out.println("-------------------");
			if (cur != prev) {
				result.add(new int[] { h.index, cur });
				System.out.println("[ " + h.index + "  " + cur + " ]");
				prev = cur;
			}	
		}

		return result;
	}

	static class Height {
		int index;
		int height;

		Height(int index, int height) {
			this.index = index;
			this.height = height;
		}
		@Override
		public String toString() {
			return "[  " + index + "  "  + height + " ]";
		}
	}

	/*
	 * 219 Contains Duplicate 2 Given an array of integers and an integer k,
	 * find out whether there are two distinct indices i and j in the array such
	 * that nums[i] = nums[j] and the difference between i and j is at most k.
	 */
	public static boolean task219_containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], i);
			} else {
				// map.containsKey(nums[i])
				int preIndex = map.get(nums[i]);
				if (i - preIndex <= k) {
					return true;
				} else {
					map.put(nums[i], i);
				}
			}
		}
		return false;
	}

	/*
	 * 220 Contains Duplicate 3 Given an array of integers, find out whether
	 * there are two distinct indices i and j in the array such that the
	 * difference between nums[i] and nums[j] is at most t and the difference
	 * between i and j is at most k.
	 */
	public static void test220() {
		int[] nums = { 1, 2 };
		int k = 0, t = 1;
		boolean rev = task220_containsNearbyAlmostDuplicate(nums, k, t);
		System.out.println("rev = " + rev);

	}

	public static boolean task220_containsNearbyAlmostDuplicate(int[] nums,
			int k, int t) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			Integer floor = set.floor(nums[i] + t); // greatest <= nums[i] + t
			Integer ceil = set.ceiling(nums[i] - t); // smallest >= nums[i] - t

			if ((floor != null && nums[i] <= floor)
					|| (ceil != null && nums[i] >= ceil)) {
				return true;
			}
			set.add(nums[i]);

			if (i >= k) {
				set.remove(nums[i - k]);
			}

		}

		return false;
	}

	/*
	 * 221 Maximal Square Given a 2D binary matrix filled with 0's and 1's, find
	 * the largest square containing all 1's and return its area. For example,
	 * given the following matrix: 1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
	 * return 4
	 * 
	 * 
	 * M[i][j] the max Length of Squares that ending with [i,j] M[i][j] =
	 * min(M[i - 1][j - 1], M[i - 1][j], M[i][j - 1]) + 1 IF matrix[i][j] == 1
	 * 
	 * initialize: first column, first row
	 * 
	 * !!! return Area: maxLen * maxLen
	 */
	public static int task221_maximalSquare(char[][] matrix) {
		// sanity check
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] M = new int[rLen][cLen];
		int maxLen = Integer.MIN_VALUE;
		// init
		for (int i = 0; i < rLen; i++) {
			if (matrix[i][0] == '1') {
				M[i][0] = 1;
			} else {
				M[i][0] = 0;
			}
			maxLen = Math.max(maxLen, M[i][0]);
		}

		for (int j = 0; j < cLen; j++) {
			if (matrix[0][j] == '1') {
				M[0][j] = 1;
			} else {
				M[0][j] = 0;
			}
			maxLen = Math.max(maxLen, M[0][j]);
		}

		//
		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == '1') {
					M[i][j] = Math.min(Math.min(M[i - 1][j - 1], M[i][j - 1]),
							M[i - 1][j]) + 1;
				}
				maxLen = Math.max(maxLen, M[i][j]);
			}
		}
		return maxLen * maxLen;
	}

	/*
	 * 222 Count Complete TreeNode
	 * 
	 * 如果从某节点一直向左的高度 = 一直向右的高度, 那么以该节点为root的子树一定是complete binary tree. 而
	 * complete binary tree的节点数,可以用公式算出 2^h - 1. 如果高度不相等, 则递归调用 return
	 * countNode(left) + countNode(right) + 1. 复杂度为O(h^2)
	 */
	public static int task222_countNodes(TreeNode root) {
		return countNodes(root);
	}

	public static int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getLeftHeight(root) + 1;
		int rightHeight = getRightHeight(root) + 1;

		if (leftHeight == rightHeight) {
			return (2 << (leftHeight - 1)) - 1;
		} else {
			return countNodes(root.left) + countNodes(root.right) + 1;
		}
	}

	public static int getLeftHeight(TreeNode root) {
		int count = 0;
		while (root.left != null) {
			root = root.left;
			count++;
		}
		return count;
	}

	public static int getRightHeight(TreeNode root) {
		int count = 0;
		while (root.right != null) {
			root = root.right;
			count++;
		}
		return count;
	}

	/*
	 * 223 Rectangle Area
	 * 
	 * This problem can be converted as a overlap internal problem. On the
	 * x-axis, there are (A,C) and (E,G); on the y-axis, there are (F,H) and
	 * (B,D). If they do not have overlap, the total area is the sum of 2
	 * rectangle areas. If they have overlap, the total area should minus the
	 * overlap area.
	 */
	public int task223_computeArea(int A, int B, int C, int D, int E, int F,
			int G, int H) {
		if (C < E || G < A) // no overlap
			return (G - E) * (H - F) + (C - A) * (D - B);

		if (D < F || H < B) // no overlap
			return (G - E) * (H - F) + (C - A) * (D - B);

		int right = Math.min(C, G);
		int left = Math.max(A, E);
		int top = Math.min(H, D);
		int bottom = Math.max(F, B);
		// this is the overlapped rectangle

		return (G - E) * (H - F) + (C - A) * (D - B) - (right - left)
				* (top - bottom);
	}

	/*
	 * 224 Basic Calculator https://leetcode.com/problems/basic-calculator/ also
	 * see 227
	 *           " ( 1 + ( 4 + 5 + 2 ) - 3 ) + ( 6 + 8 ) "
	 * stack:     [0, 1]
	 * stack:     [0, 1, 1, 1]
	 * stack:     [0, 1, 1, 1] result = 11
	 * 
	 * 
	 */
	
	public static void test224() {
		String s = "(1+(4+5+2)-3)+(6+8)";
//		int rev = task224_calculate(s);
//		System.out.println("==>>> rev: " + rev);
		int rev2 = task224_calculate2(s);
		System.out.println("<><><>: " + rev2);
	}
	public static int task224_calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int len = s.length(), sign = 1, result = 0;
		
		// puth the sign and value into stack
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < len; i++) {
			// if curCh is digit, get all the number
			if (Character.isDigit(s.charAt(i))) {
				// get the full number
				int num = s.charAt(i) - '0';
				while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				// get the result
				result += num * sign;
				
			} else if (s.charAt(i) == '+')
				sign = 1;
			else if (s.charAt(i) == '-')
				sign = -1;
			else if (s.charAt(i) == '(') {
				
				// first, push the result
				stack.push(result);
				// then, push the sign
				stack.push(sign);
				
				// reset the result and sign
				result = 0;
				sign = 1;
			} else if (s.charAt(i) == ')') {
				
				result = result * stack.pop();
				result += stack.pop();
			}

			System.out.println(stack  + ":: result: " + result );
		}
		return result;
	}
	
	public static int task224_calculate2(String s) {
		System.out.println(s);
		LinkedList<Integer> numSt = new LinkedList<>();
		LinkedList<Character> opSt = new LinkedList<>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			if (ch == '(') {
				opSt.offerLast(ch);
			} else if (Character.isDigit(ch)) {
				numSt.offerLast(ch - '0');
			} else if (ch == '+' || ch =='-') {
				while (opSt.peekLast() == '+' || opSt.peekLast() == '-') {
					char op = opSt.pollLast();
					if (numSt.size() >= 2) {
						int num2 = numSt.pollLast();
						int num1 = numSt.pollLast();
						int res = 0;
						if (op == '+') {
							res = num1 + num2;
						} else {
							res = num1 - num2;
						}
						numSt.offerLast(res);
					}
				}
				opSt.offerLast(ch);
			} else if (ch == ')') {
				char op = opSt.peekLast();
				while(op != '(') {
					if (numSt.size() >= 2) {
						int num2 = numSt.poll();
						int num1 = numSt.poll();
						int res = 0;
						if (op == '+') {
							res = num1 + num2;
						} else if (op == '-') {
							res = num1 - num2;
						} else if (op == '*') {
							res = num1 * num2;
						} else {
							res = num1 / num2;
						}					
						numSt.offerLast(res);
						op = opSt.pollLast();
					}
				}
				opSt.pollLast();
				
			}
//			System.out.println("-------------");
//			System.out.println(numSt);
//			System.out.println(opSt);
//			System.out.println("-------------");
		}
		
		int result = numSt.peekLast();
		
		System.out.println(result);
		return result;
	}
	

	/*
	 * 225 Implement Stack using Queue
	 */

	/*
	 * 226 Invert Binary Tree
	 */
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode new_left = invertTree(root.left);
		TreeNode new_right = invertTree(root.right);

		root.left = new_right;
		root.right = new_left;

		return root;
	}

	/**
	 * 227 Basic Calculator 2
	 * @param s
	 * @return
	 * sign:     + - * / 
	 * operator: 0 1 2 3
	 * 
	 * there is NO parentheses like () 
	 */
	public static int task227_calculate(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			// is Digit
			if (Character.isDigit(s.charAt(i))) {
				// get the num
				int num = s.charAt(i) - '0';
				while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				
				if (!stack.empty() && (stack.peek() == 2 || stack.peek() == 3)) {
					int sign = stack.pop();
					int firstNumber = stack.pop();
					if (sign == 2)
						stack.push(firstNumber * num);
					else if (sign == 3)
						stack.push(firstNumber / num);
				} else {
					stack.push(num);
				}
			} else if (s.charAt(i) == '+')
				stack.push(0);
			else if (s.charAt(i) == '-')
				stack.push(1);
			else if (s.charAt(i) == '*')
				stack.push(2);
			else if (s.charAt(i) == '/')
				stack.push(3);
		}

		int result = 0;
		while (!stack.isEmpty()) {
			if (stack.size() > 1) {
				// there is operator
				int num = stack.pop();
				int op = stack.pop();
				if (op == 0)
					result += num;
				else if (op == 1)
					result -= num;
			} else {
				result += stack.pop();
			}
		}
		return result;
	}

	/*
	 * 228 Summary Ranges
	 * 
	 * [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 * 
	 * 2 pointers
	 * fast slow
	 * 
	 */

	public static void test228() {
		int[] nums = { 0, 1, 2, 4, 5, 7 };
		List<String> result = task228_summaryRanges(nums);
		System.out.println(result);
		
		int[] nums2 = {0, 1, 2,2,2, 4, 5,5, 7};
		List<String> result2 = task228_summaryRanges2_dup(nums2);
		System.out.println(result2);
	}

	public static List<String> task228_summaryRanges(int[] nums) {
		List<String> result = new ArrayList<String>();
		if (nums == null || nums.length == 0) {
			return result;
		}

		if (nums.length == 1) {
			result.add(Integer.toString(nums[0]));
			return result;
		}
		int n = nums.length;
		int slow = 0, fast = 0;
		while (fast < n) { // don't forget f == n - 1
			if (fast < n - 1 && nums[fast] + 1 == nums[fast + 1]) {
				fast++;
			} else {
				if (fast == slow) {
					// only one number
					String curRange = Integer.toString(nums[fast]);
					result.add(curRange);
				} else {
					// ther is a range
					String curRange = Integer.toString(nums[slow]) + "->"
							+ Integer.toString(nums[fast]);
					result.add(curRange);
				}
				// update fast
				fast++;
				// update slow
				slow = fast;
			}
		}
		return result;
	}
	
	/*
	 * follow up 
	 * there is duplicate
	 */
	public static List<String> task228_summaryRanges2_dup(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new ArrayList<String>();
		}
		int slow = 0, fast = 0;
		List<String> result = new ArrayList<String>();
		while(fast < nums.length) {
			if (fast < nums.length - 1 && (nums[fast] == nums[fast + 1] || nums[fast] + 1 == nums[fast + 1])) {
				fast ++;
			} else {
				if (fast == slow) {
					// only one element
					String cur_range = Integer.toString(nums[fast]);
					result.add(cur_range);
				} else if (nums[slow] == nums[fast]) {
					String cur_range = Integer.toString(nums[fast]);
					result.add(cur_range);
				} else {
					String cur_range = Integer.toString(nums[slow]) + "->" + Integer.toString(nums[fast]);
					result.add(cur_range);
				}
				fast ++;
				slow = fast;
			}
		}
		return result;
	}
	
	/*
	 * follow up
	 * not sorted
	 */
	public static List<String> task228_summaryRange3_notSorted(int[] nums) {
		
		return null;
	}

	/*
	 * 229 Majority Element 2
	 */
	public static List<Integer> majorityElement(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0)
			return result;

		if (nums.length == 1) {
			result.add(nums[0]);
			return result;
		}

		int cand1 = Integer.MIN_VALUE;
		int cand2 = Integer.MIN_VALUE;
		int count1 = 0;
		int count2 = 0;

		for (int i = 2; i < nums.length; i++) {
			int cur = nums[i];
			if (cur == cand1) {
				count1++;
			} else if (cur == cand2) {
				count2++;
			} else {

				if (count1 == 0) {
					cand1 = cur;
					count1 = 1;
				} else if (count2 == 0) {
					cand2 = cur;
					count2 = 1;
				} else {
					count1--;
					count2--;
				}
			}
		}

		count1 = 0;
		count2 = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == cand1) {
				count1++;
			}
			if (nums[i] == cand2) {
				count2++;
			}
		}

		if (count1 > nums.length / 2) {
			result.add(cand1);
		}
		if (count2 > nums.length / 2) {
			result.add(cand2);
		}

		return result;
	}

	/*
	 * 230 Kth Smallest Element in BST
	 * 
	 * doing a in order traversal
	 */
	public static int counter = 0;
	public static int rev = Integer.MAX_VALUE;

	public static int kthSmallest(TreeNode root, int k) {
		kthSmallestHelper(root, k);
		return rev;
	}

	public static void kthSmallestHelper(TreeNode node, int k) {
		if (node == null) {
			return;
		}
		kthSmallestHelper(node.left, k);
		if (counter == k - 1) {
			rev = node.val;
		}
		counter++;

		kthSmallestHelper(node.right, k);
	}

	/*
	 * 231 Power of Two
	 */
	public static boolean task231_isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;

	}

	/*
	 * 232 Implement Queue using Stacks
	 */

	/*
	 * 233 Number of Digit One
	 * 
	 * https://leetcode.com/discuss/58868/easy-understand-java-solution-with-
	 * detailed-explaination
	 */

	/*
	 * 234 Palindrome Linked List
	 */
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode mid = findMid(head);
		ListNode second = mid.next;
		mid.next = null;
		ListNode reverse_sec = reverse(second);
		ListNode firstPtr = head;
		ListNode secondPtr = reverse_sec;
		while (firstPtr != null && secondPtr != null) {
			if (firstPtr.val != secondPtr.val) {
				return false;
			}
			firstPtr = firstPtr.next;
			secondPtr = secondPtr.next;
		}
		return true;
	}

	public static ListNode findMid(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode newHead = reverse(next);
		head.next = null;
		next.next = head;
		return newHead;
	}

	/*
	 * 235 Lowest Common Ancestor of Binary search Tree
	 * suppose that n1.val < n2.val
	 */
	public static TreeNode task235_LCABST(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (n1.val > n2.val) {
			return task235_LCABST(root, n2, n1);
		}
		if (root.val == n1.val || root.val == n2.val) {
			return root.val == n1.val ? n1 : n2;
		}
		// root's val is smaller than smaller val, go right
		if (root.val < n1.val) {
			return task235_LCABST(root.right, n1, n2);
		} else if (root.val > n2.val) {
			// go left
			return task235_LCABST(root.left, n1, n2);
		} else {
			// n1.val < root.val < n2.val
			return root;
		}
	}
	
	/*
	 * 236 Lowest Common Ancestor of a Binary Tree
	 */
	public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (root == n1 || root == n2) {
			return root == n1 ? n1 : n2;
		}

		TreeNode left = LCA(root.left, n1, n2);
		TreeNode right = LCA(root.right, n1, n2);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}

	/*
	 * 237 Delete Node in Linked List
	 */
	public static void task237_deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	/*
	 * 238 Product of Array Except Itself
	 */
	public static void test238() {
		int[] nums = { 9, 1, 3, 4, 5};
		int[] result = productExceptSelf(nums);
		System.out.println(Arrays.toString(nums));
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		System.out.println(Arrays.toString(result));
		System.out.println("<<<<<<<<<<<<<<<<<<<");
		int[] res2 = task238_productExceptSelf_opt(nums);
		System.out.println(Arrays.toString(res2));
	}

	public static int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		if (nums == null || nums.length == 0) {
			return result;
		}
		int n = nums.length;
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];

		left[0] = nums[0];
		right[n - 1] = nums[n - 1];
		for (int i = 1; i < n; i++) {
			left[i] = left[i - 1] * nums[i];
		}

		for (int j = n - 2; j >= 0; j--) {
			right[j] = right[j + 1] * nums[j];
		}


		for (int i = 0; i < n; i++) {
			if (i == 0) {
				result[i] = right[i + 1];
			} else if (i == n - 1) {
				result[i] = left[i - 1];
			} else {
				result[i] = left[i - 1] * right[i + 1];
			}
		}

		return result;
	}
	
	public static int[] task238_productExceptSelf_opt(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		res[0] = 1;
		int left = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}

		System.out.println(Arrays.toString(res));
		System.out.println("!!!!!!!!!!!!!!!!!!");
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] = res[i] * right;
			right = right * nums[i];
		}
		return res;
	}

	/*
	 * 239 Sliding Window Maximum
	 * 
	 * deque<Integer>
	 * 
	 * max -> min of current window maintain a decreasing deque. the
	 * nums[deque.peekFirst()] is the max for current sliding window
	 * 
	 * if (deque.peekFirst() <= i - k) deque.pollFirst() if nums[i] >=
	 * nums[deque.peekLast()] we have a larger number for the next window, so,
	 * deque.pollLast()
	 * 
	 * also refer to jiuzhangAlgorithm two pointers
	 */

	public static void test239() {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int[] result = task239_maxSlidingWindow(nums, k);

	}

	public static int[] task239_maxSlidingWindow(int[] nums, int k) {
		if (nums == null) {
			return null;
		}
		int n = nums.length;
		int[] result = new int[n - k + 1];
		int index = 0;
		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}

			deque.offerLast(i);

			System.out.println("------ print out the deque -------");
			System.out.println(deque);
			System.out.println("------finish print out the deque---");
		}

		System.out.println("----!!!-- print out the deque -------");
		System.out.println(deque);
		System.out.println("------finish print out the deque---");

		for (int i = k; i < n; i++) {
			System.out.println("------ print out the deque -------");
			System.out.println(deque);
			System.out.println("------finish print out the deque---");
			// get the current window max
			result[index] = nums[deque.peekFirst()];
			index++;

			// for remover element if the deque.peekFirst is smaller than or
			// equal to i - k, out of window
			while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}

			// remove useless element's index
			while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
				deque.pollLast();
			}

			deque.offerLast(i);
		}
		result[index++] = nums[deque.peekFirst()];

		System.out.println(Arrays.toString(result));
		return result;
	}

	/*
	 * 240 Search a 2D matrix II
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return false;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;

		int i = 0, j = cLen - 1;
		while (i < rLen && j >= 0) {
			if (matrix[i][j] > target) {
				// go to smaller side
				j--;
			} else if (matrix[i][j] < target) {
				// go to larder side
				i++;
			} else {
				// matrix[i][j] == target
				return true;
			}
		}

		return false;
	}

	/*
	 * 241 Different Ways to Add Parentheses this is hard.
	 */

	public static List<Integer> task241_diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<Integer>();
		if (input == null || input.length() == 0) {
			return result;
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != '+' && c != '-' && c != '*') {
				continue;
			}

			List<Integer> part1Result = task241_diffWaysToCompute(input
					.substring(0, i));
			List<Integer> part2Result = task241_diffWaysToCompute(input
					.substring(i + 1, input.length()));

			for (Integer m : part1Result) {
				for (Integer n : part2Result) {
					if (c == '+') {
						result.add(m + n);
					} else if (c == '-') {
						result.add(m - n);
					} else if (c == '*') {
						result.add(m * n);
					}
				}
			}
		}

		if (result.size() == 0) {
			result.add(Integer.parseInt(input));
		}

		return result;
	}

	/*
	 * 242 Valid Anagram
	 * 
	 * use hashSet or array as hashSet
	 */
	public static boolean task242_isAnagram(String s, String t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}

		int[] hash = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			hash[ch - 'a']++;
		}

		for (int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			hash[ch - 'a']--;
			if (hash[ch - 'a'] < 0) {
				return false;
			}
		}

		return true;
	}

	/*
	 * 243 Shortest Word Distance
	 */
	public static void test243() {
		String[] words = { "a", "c", "b", "a" };
		String word1 = "a";
		String word2 = "b";

		int rev = task243_shortestDistance(words, word1, word2);
		System.out.println("rev = " + rev);

	}

	public static int task243_shortestDistance(String[] words, String word1,
			String word2) {
		int prevIndex = -1;
		int minDist = Integer.MAX_VALUE;
		// traverse the words
		for (int i = 0; i < words.length; i++) {
			String curStr = words[i];
			if (curStr.equals(word1) || curStr.equals(word2)) {
				if (prevIndex == -1) {
					prevIndex = i;
				} else {
					String prevStr = words[prevIndex];
					if (!curStr.equals(prevStr)) {
						int curDif = i - prevIndex;
						if (minDist > curDif) {
							minDist = curDif;
						}
					}
					// update prevIndex
					prevIndex = i;

				}
			}
		}
		return minDist;
	}
	
	public static int task243_shortestDistance2(String[] words, String word1, String word2) {
	    int i1 = -1, i2 = -1;
	    int minDistance = words.length;
	    int currentDistance;
	    for (int i = 0; i < words.length; i++) {
	        if (words[i].equals(word1)) {
	            i1 = i;
	        } else if (words[i].equals(word2)) {
	            i2 = i;
	        }

	        if (i1 != -1 && i2 != -1) {
	            minDistance = Math.min(minDistance, Math.abs(i1 - i2));
	        }
	    }
	    return minDistance;
	}

	/*
	 * follow up another way is to use hashMap<Word, ArrayList<Integer>> word,
	 * Index List
	 */

	/*
	 * 244 Shortest Word Distance II See WordDistance in the same directory
	 */
	
	

	/*
	 * 245 Shortest Word Distance III
	 * 
	 * check word1 == word2 ?
	 * 
	 * word1 == word2
	 * 
	 * word1 != word2, the same with task 243
	 */
	public static int shortestWordDistance3(String[] words, String word1,
			String word2) {
		if (word1.equals(word2)) {
			return task245_shortestDistanceSameWord(words, word1);
		} else {
			return task243_shortestDistance(words, word1, word2);
		}
	}

	public static int task245_shortestDistanceSameWord(String[] words,
			String word) {
		int prev = -1;
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			String curStr = words[i];
			if (curStr.equals(word)) {
				if (prev == -1) {
					prev = i;
				} else {
					int curDif = i - prev;
					minDist = Math.min(minDist, curDif);
					prev = i;
				}
			}
		}

		return minDist;
	}

	/*
	 * 246 Strobogrammatic Number A strobogrammatic number is a number that
	 * looks the same when rotated 180 degrees (looked at upside down). Write a
	 * function to determine if a number is strobogrammatic. The number is
	 * represented as a string. For example, the numbers "69", "88", and "818"
	 * are all strobogrammatic. 1 2 3 4 5 6 7 8 9 0
	 * 
	 * 1 8 0 self set1 6 9 set2
	 */
	public static void test246() {
		String num = "81218";
		boolean rev = task246_isStrobogrammatic(num);
		System.out.println("rev = " + rev);
	}

	public static boolean task246_isStrobogrammatic(String num) {
		if (num == null || num.length() == 0) {
			return false;
		}
		int i = 0, j = num.length() - 1;
		HashSet<Integer> set1 = new HashSet<Integer>();

		set1.add(1);
		set1.add(8);
		set1.add(0);

		while (i <= j) {
			int num_i = num.charAt(i) - '0';
			int num_j = num.charAt(j) - '0';
			if (set1.contains(num_i)) {
				if (num_i == num_j) {
					i++;
					j--;
				} else {
					return false;
				}
			} else if ((num_i == 6 && num_j == 9) || (num_i == 9 && num_j == 6)) {
				i++;
				j--;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean task246_isStrobogrammatic2(String num) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('0', '0');
		map.put('1', '1');
		map.put('8', '8');
		map.put('6', '9');
		map.put('9', '6');

		for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {
			if (map.get(num.charAt(i)) != (Character) num.charAt(j))
				return false;
		}
		return true;
	}

	/*
	 * 247 Strobogrammatic Number II A strobogrammatic number is a number that
	 * looks the same when rotated 180 degrees (looked at upside down). Find all
	 * strobogrammatic numbers that are of length = n. For example, Given n = 2,
	 * return ["11","69","88","96"].
	 */
	public static void test247() {
		int n = 4;
		List<String> result = task247_findStrobogrammatic(n);
		System.out.println(result);
	}

	public static List<String> task247_findStrobogrammatic(int n) {
		char[] keys = { '0', '1', '8', '6', '9' };
		char[] values = { '0', '1', '8', '9', '6' };
		List<String> res = new ArrayList<String>();
		char[] buffer = new char[n];
		task247_helper(keys, values, res, buffer, 0, n);
		return res;
	}

	public static void task247_helper(char[] keys, char[] values,
			List<String> result, char[] buffer, int index, int n) {
		if (index > n / 2 || (index == n / 2 && n % 2 == 0)) {
			// we got a result
			result.add(new String(buffer));
			return;
		}
		for (int i = 0; i < keys.length; i++) {
			if ((index == 0 && keys[i] == '0' && n > 1)
					|| (index == n - index - 1 && (keys[i] == '6' || keys[i] == '9'))) {
				// skip
				// 第一个不能是0， 除非是 n == 1 e.g, 生成长度为1的字符串
				// 如果 index 和 n - index - 1 相同的时候， 此时不能 放 6 或者 9
				continue;
			}
			buffer[index] = keys[i];
			buffer[n - index - 1] = values[i];
			task247_helper(keys, values, result, buffer, index + 1, n);
		}
	}

	/*
	 * task247 A better way A strobogrammatic number is a number that looks the
	 * same when rotated 180 degrees (looked at upside down). Find all
	 * strobogrammatic numbers that are of length = n. For example, Given n = 2,
	 * return ["11","69","88","96"]. Try to use recursion and notice that it
	 * should recurse with n - 2 instead of n - 1.
	 */

	public static void test247_2() {
		int n = 2;
		List<String> result = task247_findStrobogrammatic2(n);
		System.out.println(result);
	}

	public static List<String> task247_findStrobogrammatic2(int n) {
		char[] arr = new char[n];
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		task247_fillMap(map);
		List<String> result = new ArrayList<String>();
		task247_helper2(arr, 0, n - 1, result, map);
		return result;
	}

	public static void task247_helper2(char[] arr, int start, int end,
			List<String> result, HashMap<Character, Character> map) {
		if (start > end) {
			// termination
			if (arr.length == 1 || (arr.length > 1 && arr[0] != '0')) {
				result.add(new String(arr));
			}
			return;
		}

		for (Character c : map.keySet()) {
			arr[start] = c;
			arr[end] = map.get(c);

			if (start < end || (start == end && map.get(c) == c)) {
				task247_helper2(arr, start + 1, end - 1, result, map);
			}
		}
	}

	public static void task247_fillMap(HashMap<Character, Character> map) {
		map.put('0', '0');
		map.put('1', '1');
		map.put('8', '8');
		map.put('6', '9');
		map.put('9', '6');
	}

	/*
	 * 248 Strobogrammatic Number III
	 */

	public static void test248() {
		String low = "0";
		String high = "0";

		int rev = task248_strobogrammaticInRange(low, high);
		System.out.println("rev = " + rev);
	}

	public static int task248_strobogrammaticInRange(String low, String high) {
		int low_len = low.length();
		int high_len = high.length();
		HashMap<Character, Character> map = new HashMap<Character, Character>();

		task247_fillMap(map);

		for (int n = low_len; n <= high_len; n++) {
			char[] arr = new char[n];
			task248_helper(arr, 0, n - 1, low, high, map);
		}

		return task248_counter;
	}

	public static int task248_counter = 0;

	public static void task248_helper(char[] arr, int start, int end,
			String low, String high, HashMap<Character, Character> map) {
		if (start > end) {
			String s = new String(arr);
			if ((s.length() == 1 || s.charAt(0) != '0') && smaller(low, s)
					&& smaller(s, high)) {
				task248_counter++;
			}
			return;
		}

		for (Character c : map.keySet()) {
			arr[start] = c;
			arr[end] = map.get(c);

			if (start < end || (start == end && map.get(c) == c)) {
				task248_helper(arr, start + 1, end - 1, low, high, map);
			}
		}
	}

	public static boolean smaller(String s1, String s2) {
		if (s1.length() == s2.length()) {
			if (s1.compareTo(s2) <= 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	/*
	 * 248
	 */
	public static void test248_2() {
		String low = "0";
		String high = "0";
		int rev = task248_findStrobogrammatic2(low, high);
		System.out.println("rev = " + rev);
	}

	public static int task248_findStrobogrammatic2(String low, String high) {
		char[] keys = { '0', '1', '8', '6', '9' };
		char[] values = { '0', '1', '8', '9', '6' };
		int low_len = low.length();
		int hight_len = high.length();
		for (int n = low_len; n <= hight_len; n++) {
			char[] buffer = new char[n];
			task248_helper2(keys, values, buffer, 0, n, low, high);
		}
		return task248_counter2;
	}

	public static int task248_counter2 = 0;

	public static void task248_helper2(char[] keys, char[] values,
			char[] buffer, int index, int n, String low, String high) {
		if (index > n / 2 || (index == n / 2 && n % 2 == 0)) {
			// we got a result
			String s = new String(buffer);
			if (smaller(low, s) && smaller(s, high)) {
				task248_counter2++;

			}
			return;
		}
		for (int i = 0; i < keys.length; i++) {
			if ((index == 0 && keys[i] == '0' && n > 1)
					|| (index == n - index - 1 && (keys[i] == '6' || keys[i] == '9'))) {
				// skip
				// 第一个不能是0， 除非是 n == 1 e.g, 生成长度为1的字符串
				// 如果 index 和 n - index - 1 相同的时候， 此时不能 放 6 或者 9
				continue;
			}
			buffer[index] = keys[i];
			buffer[n - index - 1] = values[i];
			task248_helper2(keys, values, buffer, index + 1, n, low, high);
		}
	}

	/*
	 * 249 Group Shifted Strings
	 * 
	 * if they are in the same group, for example, abc, def, the different
	 * between each char to corresponding char in the other string are same.
	 * 
	 * d - a = 3 d - b = 3 f - c = 3
	 * 
	 * for every string, we can get the start string by (ch - 'a' - dist +
	 * 26)%26 + 'a' to get the original char, and get the original string.
	 */
	public static void test249() {
		String[] strings = { "ab", "ba" };
		List<List<String>> result = task249_groupStrings(strings);
		System.out.println(result);
	}

	public static List<List<String>> task249_groupStrings(String[] strings) {
		if (strings == null) {
			throw new IllegalArgumentException("strings is null");
		}
		List<List<String>> result = new ArrayList<List<String>>();
		if (strings.length == 0) {
			return result;
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strings) {
			String shifted_str = shiftString(str);
			if (map.containsKey(shifted_str)) {
				map.get(shifted_str).add(str);
			} else {
				List<String> item = new ArrayList<String>();
				item.add(str);
				map.put(shifted_str, item);
				result.add(item);
			}
		}
		for (List<String> list : result) {
			System.out.println(list);
			Collections.sort(list);
		}
		return result;
	}

	public static String shiftString(String str) {
		StringBuilder buffer = new StringBuilder();
		char[] char_array = str.toCharArray();
		int dist = str.charAt(0) - 'a';
		for (char c : char_array) {
			buffer.append((c - 'a' - dist + 26) % 26 + 'a');
		}
		return buffer.toString();
	}

	/*
	 * 250 Count Univalue Subtrees
	 * 
	 * Given a binary tree, count the number of uni-value subtrees. A Uni-value
	 * subtree means all nodes of the subtree have the same value. 5 / \ 1 5 / \
	 * \ 5 5 5
	 * 
	 * return 4
	 */

	public static int countUnivalSubtrees(TreeNode root) {
		helper(root);
		return task250_counter;
	}

	public static int task250_counter = 0;

	public static boolean helper(TreeNode node) {
		if (node == null) {
			return true;
		}
		if (node.left == null && node.right == null) {
			task250_counter++;
			return true;
		}

		boolean left = helper(node.left);
		boolean right = helper(node.right);

		if (left && right && (node.left == null || node.left.val == node.val)
				&& (node.right == null || node.right.val == node.val)) {
			task250_counter++;
			return true;
		}

		return false;
	}

}
