package sortbynum;
import ds.*;

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

import ds.TreeNode;

public class Task251_ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test253();
//		test254();
//		test255();
		test264();
//		test261();
//		test259();
//		test260();
//		test264();
//		test266();
//		test267();
//		test269();
//		test286();
//		test273();
//		test287();
//		test280();
//		test290();
//		test293();
//		test294();
//		test_294_1();
//		test296();
		
		
	}
	/*
	 * 251 Flatten 2D Vector 
	 * 
	 * see Vector2D
	 */
	
	/*
	 * 252 Meeting Rooms 
	 */
	public static boolean canAttendMeetings(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return true;
		}
		Arrays.sort(intervals, intervalComp);
		int end_so_far = -1;
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
				new Interval(1,13)
		};
		
		int minMtRm = minMeetingRooms(intervals);
		System.out.println("minMeetingRoom = " + minMtRm);
	}
	
	public static int minMeetingRooms(Interval[] intervals) {
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
	
	
	
	/*
	 * 254 Factor Combinations 
	 */
	public static void test254() {
		int n = 32;
		List<List<Integer>> result =  task254_getFactors(n);
		System.out.println(result);
 	}
	
	public static List<List<Integer>> task254_getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        helper(result, list, n, 2);
        return result;
    }
	
	public static void helper(List<List<Integer>> result, 
			List<Integer> list, int n, int pos) {
		if (n == 1) {
			if (list.size() > 1) {
				List<Integer> copy = new ArrayList<Integer>(list);
				result.add(copy);
			}
			return ;
		}
		
		for(int i = pos; i <= n; i ++) {
			if (n % i == 0) {
				if (list.size() > 0 && i < list.get(list.size() - 1)) {
					continue;
				}
				list.add(i);
				helper(result, list, n/i, pos);
				list.remove(list.size() - 1);
			}
			
		}
	}
	
	
	
	/*
	 * 255 Verify Preorder Sequence in Binary Search Tree 
	 */
	public static void test255() {
		int[] array = {5,3,1,4,8,7,9};
		boolean rev = task255_verifyPreorder(array);
		System.out.println("rev = " + rev);
	}
	
	public static boolean task255_verifyPreorder(int[] preorder) {
		return task255_helper(preorder, 0, preorder.length - 1);
	}
	
	public static boolean task255_helper(int[] preorder, int start, int end) {
		if (start >=  end) {
			return true;
		}
		
		int index = -1;
		int curRoot = preorder[start];

		// get the rightChild of curRoot 
		for(int i = start + 1; i <= end; i ++) {
			if (preorder[i] > curRoot) {
				index = i;
				break;
			}
		}
		
		if (index != -1) {
			// traverse the second part, if there
			// are preorder[i] < curRoot, return false;
			for(int i = index; i <= end; i ++) {
				if (preorder[i] < curRoot) {
					return false;
				}
			}
		}
		
		if (index == -1) {
			// only left side
			return task255_helper(preorder, start + 1, end);
		} else {
			// left side and right side
			return task255_helper(preorder, start + 1, index - 1)
					&& task255_helper(preorder, index, end);
		}
	}
	
	
	/*
	 * 256 Paint House 
	 */
	
	
	
	/*
	 * 257 Binary Tree Paths
	 */
	public static List<String> binaryTreePaths(TreeNode root) {
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
	 * 
	 */
	public static void test264() {
		int n = 1407;
		int rev = task264_uglyNumber_main(n);
		System.out.println(rev);
		System.out.println("----------------");
		int rev2 = task264_uglyNumber_main2(n);
		System.out.println(rev2);
		
		
	}
	public static int task264_uglyNumber_main(int n) {
		long result = task264_uglyNumber(n);
		return (int)result;
	}
	
	public static long task264_uglyNumber(int n) {
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		HashSet<Long> set = new HashSet<Long>();
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
	 * 265 Paint House II 
	 */
	
	
	

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
	 * <1> use hashMap to count the occurence of each char
	 * <2> get the first half of char_arrayl
	 * <3> get all permutations of the first half array. 
	 * <4> link the second part
	 * 
	 * !!! pay attentions that the odd Occurence
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
			if (!map.containsKey(curCh)) {
				map.put(curCh, 1);
			} else {
				map.put(curCh, map.get(curCh) + 1);
			}
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
	 * 
	 * XOR all numbers in nums and 1..n
	 * 
	 * the result is the missing number. 
	 * 
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
	 * t -> f
	 * w -> e
	 * r -> t
	 * e -> r
	 */
	public static void test269() {
		String[] words = {"wnlb"};
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
        
        for (Character c : indegree.keySet()) {  
            if (indegree.get(c) == 0)  
                queue.offer(c);  
        }
        
        while (!queue.isEmpty()) {  
            char c = queue.poll();  
            order.append(c);
            for (Character adj : graph.get(c)) {  
                if (indegree.get(adj) - 1 == 0) { 
                    queue.offer(adj); 
                }
                else {  
                    indegree.put(adj, indegree.get(adj) - 1);
                }
            }  
        }
        
        return order.length() == indegree.size() ? order.toString() : "";  
    }  
	
    public static  Map<Character, Set<Character>> buildGraph(String[] words) {  
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();  
        int N = words.length;  
        for (int i = 1; i < N; i++) {  
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
                if (c1 != ' ' && c2 != ' ' && c1 != c2 && !found) {  
                    map.get(c1).add(c2);  
                    found = true;  
                }  
            }  
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
	
	/*
	 * 272 Closest Binary Search Tree Value II
	 * find k closest values near target
	 * 
	 *  !!!
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
				StringBuilder curStb = new StringBuilder();
				for(int i = 0; i < num3Digits.size(); i ++) {
					curStb.append(num3Digits.get(i));
					curStb.append(" ");
				}
				stb.insert(0, curStb.toString());
			}
			index ++;
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
		if (num > 9 && num < 20) {
			list.add(teen[num%10]);
		} else {
			if (num > 19) {
				list.add(tens[num/10]);
			}
			num %= 10;
			if (num != 0) {
				list.add(one[num]);
			}
		}
		for(String str: list) {
			System.out.println(str);
		}
		
		return list;
		
	}
	
	
	
	

	/*
	 * 274 H-Index
	 */
	
	/*
	 * 275 H-Index II
	 */
	
	/*
	 * 276 Paint Fence 
	 */
	
	 
	
	/*
	 * 277 Find the Celebrity 
	 */
	
	/*
	 * 278 First Bad Version
	 */
	
	/*
	 * 279 Perfect Squares
	 * 
	 */
	
	/*
	 * method1 dp
	 * This problem can be solved by dynamic programming. 
	 * If we call dp is the array of least numbers of perfect square numbers for each integer 
	 * from 1 to n, we have the following relation:
	 * dp[n] = 1 + min (dp[n-i*i] for i from 1 to square root of n)
	 * 
	 * dp[x + y * y] = min(dp[x + y * y], dp[x] + 1)
	 * 
	 * Time: O(n * sqrt(n))
	 */
	public static int task279_numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for(int i = 1; i * i <= n; i ++) {
        	dp[i * i] = 1;
        }
        
        for(int i = 1; i <=n; i ++) {
        	for(int j = 1; i + j * j <= n; j ++) {
        		dp[i + j*j] = Math.min(dp[i + j * j], dp[i] + 1);
        	}
        }
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
		while((n & 3 ) == 0) { // n % 4 == 0
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
	 */
	
	/*
	 * 282 Expression Add Operators
	 */
	
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
	 * 
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
	  */
	 public static int INF = Integer.MAX_VALUE;
	 public static void test286() {
		int[][] rooms = { 
				{ INF, -1, 0, INF }, 
				{ INF, INF, INF, -1 },
				{ INF, -1, INF, -1 }, 
				{ 0, -1, INF, INF } 
				};
		task286_wallsAndGates(rooms);
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
				if (rooms[i][j] == -1) {
					result[i][j] = -1;
				} else {
					result[i][j] = INF;
				}
				
			}
		}
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (rooms[i][j] == 0) {
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
			if (nei_x >= 0 && nei_x < rLen && nei_y >= 0 && nei_y < cLen && !visited[nei_x][nei_y] && rooms[nei_x][nei_y] != -1 ) {
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
	
	
	// this method is good
	public static void task286_wallsAndGates2(int[][] rooms) {
		if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
			return ;
		}
		int rLen = rooms.length;
		int cLen = rooms[0].length;
		LinkedList<Pos> q = new LinkedList<Pos>();
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (rooms[i][j] == 0) {
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
				if (next_x < 0 || next_x >= rLen || next_y < 0 || next_y >= cLen ||
						rooms[next_x][next_y] < rooms[x][y] + 1) {
					continue;
				}
				
				rooms[next_x][next_y] = rooms[x][y] + 1;
				q.offer(new Pos(next_x, next_y));
			}
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
	
	
	/*
	 * 289
	 * Game of Life
	 */
	
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
		
        String[] strArr = str.split(" ");
        if (strArr.length != pattern.length()) {
			return false;
		}
        HashMap<Character, String> map = new HashMap<Character, String>();
        for(int i = 0; i < pattern.length(); i ++) {
        	char curCh = pattern.charAt(i);
        	if (!map.containsKey(curCh)) {
        		// if strArr[i] is already mapped
        		if (map.containsValue(strArr[i])) {
					return false;
				}
        		
				map.put(curCh, strArr[i]);
			} else {
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
	 * need more time to understand
	 */

	public static boolean wordPatternMatch(String pattern, String str) {
		HashMap<Character, String> mapP2S = new HashMap<Character, String>();
		HashMap<String, Character> mapS2P = new HashMap<String, Character>();
		return dfs(pattern, str, 0, 0, mapP2S, mapS2P);
	}
	
	public static boolean dfs(String pattern, String str ,int indexP, int indexS,
			HashMap<Character, String> mapP2S, HashMap<String, Character> mapS2P) {
		int patLen = pattern.length();
		int strLen = str.length();
		if (indexP == patLen && indexS == strLen) {
			return true;
		}
		if (indexP == patLen || indexS == strLen) {
			return false;
		}
		
		char pCh = pattern.charAt(indexP);
		for(int i = indexS + 1; i <= strLen; i ++) {
			String item = str.substring(indexS, i);
			
			boolean findP = true;
			if (mapP2S.containsKey(pCh) && !mapP2S.get(pCh).equals(item)) {
				continue;
			} else {
				if (!mapP2S.containsKey(pCh)) {
					mapP2S.put(pCh, item);
					findP = false;
				}
			}
			
			boolean findStr = true;
			if (mapS2P.containsKey(item) && mapS2P.get(item) != pCh) {
				if (!findP) {
					mapP2S.remove(pCh);
					continue;
				} 
			} else {
				if (!mapS2P.containsKey(item)) {
					mapS2P.put(item, pCh);
					findStr = false;
				}
			}
			
			if (dfs(pattern, str, indexP + 1, i, mapP2S, mapS2P)) {
				return true;
			} else {
				if (!findP) {
					mapP2S.remove(pCh);
				}
				if (!findStr) {
					mapS2P.remove(item);
				}
			}
		}
		
		return false;
	}
	
	
	/*
	 * https://leetcode.com/discuss/63252/share-my-java-backtracking-solution
	 */
	public static void test290_2() {
		
	}
	
	public static boolean task290_wordPatternMatch2(String pattern, String str) {
		Map<Character, String> map = new HashMap<Character, String>();
		Set<String> set = new HashSet<String>();
		return task290_helper2(str, 0, pattern, 0, map, set);
	}
	
	public static boolean task290_helper2(String str,int indexS,  String pattern, int indexP, Map<Character, String> map, 
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
				return false;
			}
			
			// if it can match, continue to match the rest
			return task290_helper2(str, indexS + s_mapped.length(), pattern, indexP + 1, map, set);
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
			if (task290_helper2(str, k + 1, pattern, indexP + 1, map, set)) {
				return true;
			}
			
			// backtracking
			map.remove(curP);
			set.remove(s_to_map);
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
	
	
	
	/*
	 * 295
	 */
	
	/*
	 * 296
	 * 
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
//				visited[cur.x][cur.y] = true;
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
	 * this is wrong. 
	 */
	public static int task296_bestMeetingPoint(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		int rLen = grid.length;
		int cLen = grid[0].length;
		
		
		
		int[][] counter = new int[rLen][cLen];
		LinkedList<Pos> q = new LinkedList<Pos>();
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				if (grid[i][j] == 1) {
					q.offer(new Pos(i, j));
				} else {
					grid[i][j] = Integer.MAX_VALUE;
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
				System.out.println("-----------------");
				System.out.println("next_x= " + next_x);
				System.out.println("next_y= " + next_y);
				System.out.println("-----------------");
				if (next_x < 0 || next_x >= rLen || next_y < 0 || next_y >= cLen ||
						grid[next_x][next_y] < grid[x][y] + 1) {
					continue;
				}
				
				grid[next_x][next_y] = grid[x][y] + 1;
				q.offer(new Pos(next_x, next_y));
			}
		}
		
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++ ){
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		
		int minDist = Integer.MAX_VALUE;
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0; j < cLen; j ++) {
				minDist = Math.min(minDist, grid[i][j]);
			}
		}
		return minDist;
		
	}
	
	

	
}
