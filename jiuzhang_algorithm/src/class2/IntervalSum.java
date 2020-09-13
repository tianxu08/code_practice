package class2;
import java.util.*;

public class IntervalSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Given an integer array (index from 0 to n-1, where n is the size of this array), 
	 * and an query list. Each query has two integers [start, end]. 
	 * For each query, calculate the sum number between index start and end in the given array, return the result list.
	 * 
	 * Example
	 * For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]
	 */
	public static ArrayList<Long> intervalSum(int[] A, ArrayList<Interval> queries) {
		// write your code here
		SegmentTreeNode4 root = build(A);
		ArrayList<Long> result = new ArrayList<Long>();
		for(Interval interval: queries) {
			int start = interval.start;
			int end = interval.end;
			int sum = query(root, start, end);
			result.add(Long.valueOf(sum));
		}
		return result;
		
	}
	
	public static SegmentTreeNode4 build(int[] A) {
		return buildHelper(A, 0, A.length - 1);
	}
	
	public static SegmentTreeNode4 buildHelper(int[] A, int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode4 root = new SegmentTreeNode4(start, end, 0);
		if (start == end) {
			root.sum = A[start];
		} else {
			int mid = root.start + (root.end - root.start)/2;
			root.left = buildHelper(A, start, mid);
			root.right = buildHelper(A, mid + 1, end);
			root.sum = root.left.sum + root.right.sum;
		}
		return root;
	}
	
	public static int query(SegmentTreeNode4 root, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (root.start == start && root.end == end) {
			return root.sum;
		}
		int root_mid = root.start + (root.end - root.start)/2;
		int left_sum = 0;
		int right_sum = 0;
		if (start <= root_mid) {
			if (end <= root_mid) {
				// contains
				left_sum = query(root.left, start, end);
			} else {
				left_sum = query(root.left, start, root_mid);
			}
		}
		
		if (root_mid < end) {
			if (start > root_mid) {
				right_sum = query(root.right, start, end);
			} else {
				right_sum = query(root.right, root_mid + 1, end);
			}
		}
		
		return left_sum + right_sum;
	}
	
	public static class SegmentTreeNode4{
		public int start, end, sum;
		SegmentTreeNode4 left, right;
		public SegmentTreeNode4(int start, int end, int sum) {
			// TODO Auto-generated constructor stub
			this.start = start; 
			this.end = end;
			this.sum = sum;
			this.left  = this.right = null;
		}
	}

}
