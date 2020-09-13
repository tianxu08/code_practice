package class1;


import java.util.*;

public class IntervalMinimumNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Given an integer array (index from 0 to n-1, where n is the size of this array), 
	 * and an query list. Each query has two integers [start, end]. 
	 * For each query, calculate the minimum number between index start and end in the given array, 
	 * return the result list.
	 * 
	 * Example
	 * For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]
	 */

	public ArrayList<Integer> intervalMinNumber(int[] A,
			ArrayList<Interval> queries) {
		// write your code here
		SegmentTreeNode3 root = build(A);
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(Interval interval: queries) {
			int start = interval.start;
			int end = interval.end;
			
			result.add(query(root, start, end));
		}
		return result;
		
	}
	
	public static int query(SegmentTreeNode3 root, int start, int end) {
		if (root.start == start && root.end == end) {
			return root.min;
		}
		
		int root_mid = (root.end + root.start)/2;
		int left_min = Integer.MAX_VALUE;
		int right_min = Integer.MAX_VALUE;
		if (start <= root_mid) {
			if (end <= root_mid) {
				// contains
				left_min = query(root.left, start, end);
			} else {
				// split
				left_min = query(root.left, start, root_mid);
			}
		}
		
		if (end > root_mid) {
			if (start > root_mid) {
				// contains
				right_min = query(root.right, start, end);
			} else {
				right_min = query(root.right, root_mid + 1, end);
			}
		}
		
		return Math.min(left_min, right_min);
	}
	
	public static SegmentTreeNode3 build(int[] A) {
		SegmentTreeNode3 root = build3Helper(A, 0, A.length - 1);
		return root;
	}
	
	public static SegmentTreeNode3 build3Helper(int[] A, int start, int end) {
		if (start > end) {
			return null;
		}
		
		SegmentTreeNode3 root = new SegmentTreeNode3(start, end, Integer.MAX_VALUE);
		if (start == end) {
			root.min = A[start];
		} else {
			int mid = (start + end)/2;
			root.left = build3Helper(A, start, mid);
			root.right = build3Helper(A, mid + 1, end);
			
			root.min = Math.min(root.left.min, root.right.min);
		}
		return root;
	}
	
	public static class SegmentTreeNode3 {
		public int start, end;
		public int min;
		SegmentTreeNode3 left, right;
		public SegmentTreeNode3(int start, int end, int min) {
			this.start = start;
			this.end = end;
			this.min = min;
			this.left = this.right = null;
		}
	}
	
	public static class Interval{
		public int start;
		public int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
