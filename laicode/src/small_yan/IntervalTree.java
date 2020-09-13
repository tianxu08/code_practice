package small_yan;


public class IntervalTree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Consider a situation where we have a set of intervals and 
	 * we need following operations to be implemented efficiently. 
	 * 1) Add an interval
	 * 2) Remove an interval
	 * 3) Given an interval x, find if x overlaps with any of the existing intervals.
	 * 
	 * Interval Tree: The idea is to augment a self-balancing Binary Search Tree (BST) like Red Black Tree, 
	 * AVL Tree, etc 
	 * to maintain set of intervals so that all operations can be done in O(Logn) time.
	 * 
	 * Every node of Interval Tree stores following information.
	 * a) i: An interval which is represented as a pair [low, high]
	 * b) max: Maximum high value in subtree rooted with this node.
	 * 
	 * The low value of an interval is used as key to maintain order in BST. 
	 * The insert and delete operations are same as insert and delete in self-balancing BST used.
	 * 
	 * The main operation is to search for an overlapping interval. 
	 * Following is algorithm for searching an overlapping interval x in an Interval tree rooted with root.
	 * Interval overlappingIntervalSearch(root, x)
	 * 1) If x overlaps with root's interval, return the root's interval.
	 * 2) If left child of root is not empty and 
	 *    the max  in left child is greater than x's low value, recur for left child
	 * 3) Else recur for right child.
	 */
	
	public static class Interval{
		int start;
		int end;
		public Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}
	
	public static class ITNode {
		Interval interval;
		int limit;
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
	
	public static ITNode insert(ITNode root, Interval interval) {
		if (root == null) {
			return new ITNode(interval, interval.end);
		}
		// if interval.start < root.interval.start, then new interval goes to left subtree
		if (interval.start < root.interval.start) {
			root.left = insert(root.left, interval);
			root.limit = Math.max(root.limit, interval.end);
		} else {
			root.right = insert(root.right, interval);
			root.limit = Math.max(root.limit, interval.end);
		}		
		return root;
	}
	
	public static boolean doOverlap(Interval interval1, Interval interval2) {
		if (interval1.start <= interval2.end && interval2.start <= interval1.end) {
			return true;
		}
		return false;
	} 
	
	public static Interval overlapSearch(ITNode root, Interval interval) {
		if (root == null) {
			return null;
		}
		if (doOverlap(interval, root.interval)) {
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
	
	
	// inorder traversal of the interval tree
	public static void inorder(ITNode root) {
		if (root == null) {
			return ;
		}
		inorder(root.left);
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		inorder(root.right);
	}
	
	public static void preorder(ITNode root) {
		if (root == null) {
			return ;
		}
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		preorder(root.left);
		preorder(root.right);
	}
	
	public static void test() {
		Interval[] ints = { new Interval(15, 20), new Interval(10, 30),
				new Interval(17, 19), new Interval(5, 20),
				new Interval(12, 15), new Interval(30, 40) };
		
		ITNode root = null;
		for(int i = 0; i < ints.length; i ++ ) {
			root = insert(root, ints[i]);
		}
		System.out.println("----");
		preorder(root);
		System.out.println("----");
		inorder(root);
		
		Interval x = new Interval(6, 7);
		System.out.println("searching for interval " + "[ "+ x.start + " " + x.end + " ]");
		Interval result = overlapSearch(root, x);
		if (result == null) {
			System.out.println("No overlap");
		} else {
			System.out.println("Overlaps with [ " + result.start + " " + result.end + " ]");
		}
	}	
	
}



