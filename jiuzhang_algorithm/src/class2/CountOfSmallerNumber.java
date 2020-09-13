package class2;

import java.util.HashMap;
import java.util.*;

public class CountOfSmallerNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int[] A = {1,2,7,8,5};
		int[] queries = {1,8,5};
		
		ArrayList<Integer> result = countOfSmallerNumber(A, queries);
		System.out.println(result);
	}
	
	public static ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        // write your code here
		ArrayList<Integer> result = new ArrayList<Integer>();
		SegmentTreeNode6 root = build(A);
		for(Integer i: queries) {
			result.add(query(root, i));
		}
		return result;
    }
	
	public static class SegmentTreeNode6 {
		public int start, end;
		public int count;
		public SegmentTreeNode6 left, right;
		public SegmentTreeNode6(int start, int end, int count) {
			this.start = start;
			this.end = end;
			this.count = count;
			this.left = this.right = null;
		}
	}
	
	public static int query(SegmentTreeNode6 root, int value) {
		// edge case
		if (root == null) {
			return 0;
		}
		if (root.start >= value) {
			return 0;
		} 
		if (root.end < value) {
			return root.count;
		}
		
		int root_mid = root.start + (root.end - root.start)/2;
		int left_smaller = 0;
		int right_smaller = 0;
		if (value > root_mid) {
			left_smaller = root.left.count;
			right_smaller = query(root.right, value);
		} else {
			// value <= root_mid
			left_smaller = query(root.left, value);
		}
		return left_smaller + right_smaller;
	}
	
	public static SegmentTreeNode6 build(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < array.length; i ++) {
			int cur = array[i];
			min = Math.min(min, cur);
			max = Math.max(max, cur);
			if (map.containsKey(cur)) {
				map.put(cur, map.get(cur) + 1);
			} else {
				map.put(cur, 1);
			}
		}
		
		// get the min, max and the hashMap
		
		return buildHelper(min, max, map);
	}
	
	public static SegmentTreeNode6 buildHelper(int start, int end, HashMap<Integer, Integer> map) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode6 root = new SegmentTreeNode6(start, end, 0);
		if (start == end) {
			if (map.containsKey(start)) {
				root.count = map.get(start);
			} 
		} else {
			int mid = start + (end - start)/2;
			root.left = buildHelper(start, mid, map);
			root.right = buildHelper(mid + 1, end, map);
		}
		
		root.count +=  root.left != null? root.left.count: 0 ; 
		root.count +=  root.right != null ? root.right.count : 0;
		return root;
	}
	
	
	

}
