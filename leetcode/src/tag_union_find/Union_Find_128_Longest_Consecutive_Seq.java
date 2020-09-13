package tag_union_find;

import java.util.HashMap;
import java.util.Map.Entry;

public class Union_Find_128_Longest_Consecutive_Seq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test128();
		test128_1();
	}
	
	/*
	 * task128
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * For example,
	 * Given [100, 4, 200, 1, 3, 2],
	 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * 
	 * Your algorithm should run in O(n) complexity.
	 */
	public static void test128() {
		int[] nums = {100, 4, 200, 1, 3, 2};
		int rev = task128_longestConsecutive(nums);
		System.out.println("rev = " + rev);
	}
	
	public static int task128_longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for(int i = 0; i < nums.length; i ++) {
			map.put(nums[i], false);
		}
		
		int result = Integer.MIN_VALUE;
		// traverse the array
		for(int i = 0; i < nums.length; i ++) {
			int count = 1;
			count += getLen(nums[i] - 1, map,  - 1);
			count += getLen(nums[i] + 1, map, 1);
			
			result = Math.max(result, count);
		}
		return result;
	}
	
	public static int getLen(int elem, HashMap<Integer, Boolean> map, int step) {
		int count = 0;
		while(map.containsKey(elem) && map.get(elem) == false) {
			map.put(elem, true);
			count ++;
			elem += step;
		}
		return count;
		
	}

	
	/*
	 * Union Find
	 * 
	 */
	public static void test128_1() {
		int[] nums = {100, 4, 200, 1, 3, 2};
		int result = task128_longest2(nums);
		System.out.println("result = " + result);
	}
	
	public static int task128_longest2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashMap<Integer, Node> map = new HashMap<Integer,Node>();
		int result = 1;
		for(int i = 0; i < nums.length; i ++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], new Node(1));
			}
		}
		
		
		for(int x : nums) {
			if (map.containsKey(x + 1)) {
				union(map.get(x), map.get(x + 1));
			}
			if (map.containsKey(x - 1)) {
				union(map.get(x), map.get(x - 1));
			}
			
			Node current = find(map.get(x));
			if (current.rank > result) {
				result = current.rank;
			}
		}
		
		for (Entry<Integer, Node> entry: map.entrySet()) {
			System.out.print("==>>" + entry.getKey());
			System.out.println(">>>> " + entry.getValue().parent + " : " + entry.getValue().rank);
		}
		return result;
	}
	
	
	
	public static class Node {
		Node parent;
		int rank;
		public Node(int _r) {
			this.parent = this;
			this.rank = _r;  // also mean the size of consecutive starting with this number
		}
	}
	
	public static Node find(Node n) {
		if (n.parent != n) {
			n.parent = find(n.parent);
		}
		return n.parent;
	}
	
	public static void union(Node x, Node y) {
		Node xRoot = find(x);
		Node yRoot = find(y);
		
		if (xRoot == yRoot) {
			return ;
		}
		if (xRoot.rank > yRoot.rank) {
			yRoot.parent = xRoot;
			xRoot.rank += yRoot.rank; 
		} else {
			xRoot.parent = yRoot;
			yRoot.rank += xRoot.rank;
		}
	}
}
