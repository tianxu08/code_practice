package mj_linkedin;


import ds.Interval;

public class MyIntervalLinkedIn4 implements IntervalLinkedIn{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyIntervalLinkedIn4 sln = new MyIntervalLinkedIn4();
		/*
		 * Interval[] ints = { new Interval(15, 20), new Interval(10, 30),
				new Interval(17, 19), new Interval(5, 20),
				new Interval(12, 15), new Interval(30, 40) };
		 */
		sln.addInterval(15, 20);
		sln.printInterval();
		sln.printTotalLength();
		
		System.out.println("-------");
		sln.addInterval(10, 30);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-------");
		sln.addInterval(17, 19);
		sln.printInterval();
		sln.printTotalLength();
		System.out.println("-------");
		sln.addInterval(5, 20);
		sln.printInterval();
		sln.printTotalLength();
		
		System.out.println("-------");
		sln.addInterval(40, 50);
		sln.printInterval();
		sln.printTotalLength();
		
		System.out.println("===========");
		sln.inorder(sln.root);
		
	}
	
	
	public ITNode root;
	private int totalLength;
	
	public MyIntervalLinkedIn4() {
		this.root = null;
		this.totalLength = 0;
	}
		
	

	/*
	 * (non-Javadoc)
	 * @see linkedin.IntervalLinkedIn#addInterval(int, int)
	 * add to the Interval Tree. Time: O(log n)  
	 */
	@Override
	public void addInterval(int from, int to) {
		// TODO Auto-generated method stub
		this.root = insert(root, new Interval(from, to));
	}

	/*
	 * (non-Javadoc)
	 * @see linkedin.IntervalLinkedIn#getTotalCoveredLength()
	 * Do a inorder traversal of the interval tree. 
	 * Time: O(n)
	 */
	@Override
	public int getTotalCoveredLength() {
		// TODO Auto-generated method stub
		this.totalLength = 0;
		Interval prev = new Interval(0, 0);
		getTotalLengthHelper(root, prev);
		
		System.out.println("prev.start = " + prev.start);
		System.out.println("prev.end = " + prev.end);
		
		this.totalLength += prev.end - prev.start;
		return this.totalLength;
		
	}
	

	/*
	 * Traverse the interval tree, get the total Length. 
	 * 
	 * Time: O(n)
	 */
	public void getTotalLengthHelper(ITNode root, Interval prev) {
		if (root == null) {
			return ;
		}
		getTotalLengthHelper(root.left, prev);
		
		// there is overlap
		if (root.interval.start <= prev.end) {
			prev.end = Math.max(root.interval.end, prev.end);
		} else {
			// no overlap
			this.totalLength += prev.end - prev.start;
			prev.start = root.interval.start;
			prev.end = root.interval.end;
		}
		getTotalLengthHelper(root.right, prev);
		
	}
	

	public class ITNode {
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
	
	
	/*
	 * Insert: 
	 * Time: O(log n)
	 * 
	 */
	public ITNode insert(ITNode root, Interval interval) {
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
	
	public boolean doOverlap(Interval interval1, Interval interval2) {
		if (interval1.start <= interval2.end && interval2.start <= interval1.end) {
			return true;
		}
		return false;
	} 
	
	public Interval overlapSearch(ITNode root, Interval interval) {
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
	
	
	
	public Interval overlap_search_single_val(ITNode root, int val) {
		if (root == null) {
			return null;
		}
		if (root.interval.start <= val  && root.interval.end >= val) {
			return root.interval;
		}
		
		if (root.left != null && root.left.limit >= val) {
			return overlap_search_single_val(root.left, val);
		} else {
			return overlap_search_single_val(root.right, val);
		}
	}
	
	
	
	
	// inorder traversal of the interval tree
	public void inorder(ITNode root) {
		if (root == null) {
			return ;
		}
		inorder(root.left);
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		inorder(root.right);
	}
	
	public void preorder(ITNode root) {
		if (root == null) {
			return ;
		}
		System.out.println("[ "  + root.interval.start + " " + root.interval.end + " ]" + " limit= " + root.limit );
		preorder(root.left);
		preorder(root.right);
	}
	
	
	public void printInterval() {
		inorder(root);
		System.out.println("---------------------------");
		preorder(root);
	}
	
	public void printTotalLength() {
		System.out.println("totalCoveredLength = " + getTotalCoveredLength());
	}
	
	

}
