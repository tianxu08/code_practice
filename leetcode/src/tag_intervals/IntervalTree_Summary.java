package tag_intervals;

import java.util.List;

import ds.Interval;

public class IntervalTree_Summary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/**
	 * Interval tree node
	 */
	public static class ITNode {
		Interval interval;
		int limit;  // the maximum of subtree
		ITNode left;
		ITNode right;
		int range;
		public ITNode(Interval interval, int limit) {
			this.interval = interval;
			this.limit = limit;
		}
		
		public ITNode(Interval interval, int limit, int range) {
			this.interval = interval;
			this.limit = limit;
			this.range = range;
		}
	}
	
	
	public static List<Interval> t1_merge_intervals(List<Interval> list) {
		
		return null;
	}

	public static void test1() {
		Interval i1 = new Interval(15, 16);
		Interval i2 =new Interval(13, 15);
		Interval i3 = new Interval(17, 19);
		Interval i4 = new Interval(5,10);
		Interval i5 = new Interval(14,20);
		Interval i6 = new Interval(70, 80);
		
		ITNode root = null;
		root = insert(root, i1);
		root = insert(root, i2);
		root = insert(root, i3);
		root = insert(root, i4);
		root = insert(root, i5);
		root = insert(root, i6);
		
		preOrderTraversal(root);
		System.out.println("=============");
		inOrderTraversal(root);
		
		boolean exist = overlap_interval(root, new Interval(17,18));
		System.out.println("exist  =  " + exist);
		
		boolean exist2 = overlap_val_exist(root, 25);
		System.out.println("exist2 = " + exist2);
	}
	
	// insert an interval into interval tree
	public static ITNode insert(ITNode root, Interval interval) {
		if (root == null) {
			return new ITNode(interval, interval.end);
		}
		// if interval.start < root.interval.start, then new interval goes to left subtree
		if (interval.start < root.interval.start) {
			// insert into left subtree.
			root.left = insert(root.left, interval);
			// update the limit of root
			root.limit = Math.max(root.limit, interval.end);
		} else {
			// insert into right subtree
			root.right = insert(root.right, interval);
			// update the limit of root
			root.limit = Math.max(root.limit, interval.end);
		}		
		return root;
	}
	
	
	/**
	 * check whether a value is covered by the interval in the interval tree
	 * @param root
	 * @param val
	 * @return
	 *
	 * Time:
	 * Build IntervalTree: O(N * log H)
	 *
	 *
	 */
	public static boolean overlap_val_exist(ITNode root, int val) {
		Interval rev_iterval = overlap_search_single_val(root, val);
		if (rev_iterval != null) {
			return true;
		}
		return false;
	}
	
	public static Interval overlap_search_single_val(ITNode root, int val) {
		if (root == null) {
			return null;
		}
		if (root.interval.start <= val  && root.interval.end >= val) {
			return root.interval;
		}
		
		if (root.left != null && root.left.limit >= val) {
			// left subtree is NOT null, and root.left.limit >= val
			// go to left subtree
			return overlap_search_single_val(root.left, val);
		} else {
			// go to right subtree
			return overlap_search_single_val(root.right, val);
		}
	}
	

	public static boolean isOverlapped(Interval interval1, Interval interval2) {
		if (interval1.end < interval2.start || interval2.end < interval1.start) {
			return false;
		}
		return true;
	} 
	
	/**
	 * Check whether an interval has overlap with intervals in the interval tree
	 * @param root
	 * @param interval
	 * @return
	 */
	public static boolean overlap_interval(ITNode root, Interval interval) {
		Interval rev_iterval = overlapSearch(root, interval);
		if (rev_iterval != null) {
			return true;
		}
		return false;
	}
	
	public static Interval overlapSearch(ITNode root, Interval interval) {
		if (root == null) {
			return null;
		}
		
		if (isOverlapped(interval, root.interval)) {
			return root.interval;
		}
		
		// if left child of root is present and max of left child is greater than or equal to give interval
		// the interval may overlap with an interval is left subtree
		if (root.left != null && root.left.limit >= interval.start) {
			return overlapSearch(root.left, interval);
		} else {
			return overlapSearch(root.right, interval);
		}	
	}
	
	
	public static void preOrderTraversal(ITNode root) {
		if (root == null) {
			return ;
		}
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	
	public static void inOrderTraversal(ITNode root) {
		if (root == null) {
			return ;
		}
		
		inOrderTraversal(root.left);
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		inOrderTraversal(root.right);
	}
	
	
	
	
	
	
	
	

}
