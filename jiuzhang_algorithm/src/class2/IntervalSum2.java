package class2;


public class IntervalSum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {1,2,7,8,5};
		
		IntervalSum2 obj = new IntervalSum2(A);
		long q1 = obj.query(0, 2);
		System.out.println(q1);
		obj.modify(0, 4);
	}
	
	public class SegmentTreeNode5 {
		public int start, end;
		public long sum;
		public SegmentTreeNode5 left, right;
		public SegmentTreeNode5(int start, int end, long sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = this.right = null;
		}
	}
	
	private SegmentTreeNode5 root;
	
	public IntervalSum2(int[] A) {
		root = build(A, 0, A.length - 1);
	}
	
	public SegmentTreeNode5 build(int[] A, int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode5 root = new SegmentTreeNode5(start, end, 0);
		if (start == end) {
			root.sum = (long)A[start];
		} else {
			int mid = start + (end - start)/2;
			root.left = build(A, start, mid);
			root.right = build(A, mid + 1, end);
			root.sum = (long)(root.left.sum + root.right.sum);
		}
		return root;
	}
	
	/**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
	public long query(int start, int end) {
		return queryHelper(root, start, end);
	}
	
	public long queryHelper(SegmentTreeNode5 node, int start, int end) {
		if (node.start == start && node.end == end) {
			return node.sum;
		}
		int node_mid = node.start + (node.end - node.start)/2;
		long left_sum = 0;
		long right_sum = 0;
		if (start <= node_mid) {
			// contains
			if (end <= node_mid) {
				left_sum = queryHelper(node.left, start, end);
			} else {
				left_sum = queryHelper(node.left, start, node_mid);
			}
		}
		
		if (node_mid < end) {
			// contains
			if (node_mid < start) {
				right_sum = queryHelper(node.right, start, end);
			} else {
				right_sum = queryHelper(node.right, node_mid + 1, end);
			}
		}
		
		return left_sum + right_sum;
	}
	
	/**
     * @param index, value: modify A[index] to value.
     */
	public void modify(int index, int value){
		modifyHelper(root, index, value);
	}
	
	public void modifyHelper(SegmentTreeNode5 node, int index, int value) {
		if (node.start == index && node.end == index) {
			node.sum = (long) value;
			return ;  // don't forget this
		}
		int node_mid = node.start + (node.end - node.start)/2;
		if (node.start <= index && index <= node_mid) {
			modifyHelper(node.left, index, value);
		} 
		if (node_mid < index && index <= node.end) {
			modifyHelper(node.right, index, value);
		}
		node.sum = node.left.sum + node.right.sum;
	}
	
	
	
	

}
