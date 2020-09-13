package class1;

import java.util.LinkedList;

public class SegmentTree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
		test2();
	}
	
	public static void test1() {
		int start = 0, end = 10;
		SegmentTreeNode root = build(start, end);
		levelOrderPrint(root);
	}
	
	// build the segment tree. 
	public static SegmentTreeNode build(int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		if (start != end) {
			int mid = start + (end - start)/2;
			root.left = build(start, mid);
			root.right = build(mid + 1, end);
		}
		return root;
	}
	
	public static void test2() {
		int[] array = {2,4,1, 8, 3};
		int start = 0, end = array.length - 1;
		SegmentTreeNode root = build2(array, start, end);
		levelOrderPrint(root);
		
		modify(root, 2, 9);
		
		System.out.println("-----------------------");
		levelOrderPrint(root);
		
	}
	// build the segment with max
	public static SegmentTreeNode build2(int[] array, int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		if (start == end) {
			root.max = array[start]; 
		} else {
			// start != end
			int mid = start + (end - start)/2;
			root.left = build2(array, start, mid);
			root.right = build2(array, mid + 1, end);
			
			root.max = Math.max(root.left.max, root.right.max);
		}
		return root;
		
	}
	

	public static void modify(SegmentTreeNode root, int index, int value) {
		// find the 
		if (root.start == index && root.end == index) {
			root.max = value;
			return ;
		}
		
		// query
		int root_mid = root.start + (root.end - root.start)/2;
		// index in the left side, [start, root_mid]
		if (root.start <= index && index <= root_mid) {
			modify(root.left, index, value);
		}
		
		// index in the right side, [mid + 1, end]
		if (root_mid < index && index <= root.end) {
			modify(root.right, index, value);
		}
		
		// update root.max
		root.max = Math.max(root.left.max, root.right.max);
		
		
	}	
	
	public int query(SegmentTreeNode root, int start, int end) {
		// root is exactly the same with [start, end]
		if (root.start == start && root.end == end) {
			return root.max;
		}
		int root_mid = root.start + (root.end - root.start)/2;
		int left_max = Integer.MIN_VALUE;
		int right_max = Integer.MIN_VALUE;
		// left side
		if (start <= root_mid) {
			if (end > root_mid) { // split
				left_max = query(root.left, start, root_mid);
			} else { // [root.start, root_mid] contains the [start, end]
				left_max = query(root.left, start, end);
			}
		}
		
		// right side
		if (root_mid < end) {
			if (start <= root_mid) {// split
				right_max = query(root.right, root_mid + 1, end);
			} else { // [root_mid, root.end] contains [start, end]
				right_max = query(root.right, start, end);
				
			}
		}
		
		// doesn't overlap
		return Math.max(left_max, right_max);
		
	}
	
	public static void levelOrderPrint(SegmentTreeNode root) {
		if (root == null) {
			return ;
		}
		LinkedList<SegmentTreeNode> q = new LinkedList<SegmentTreeNode>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				SegmentTreeNode cur = q.poll();
				// print cur's content
				System.out.print(cur.toString() + " ");
				
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
			System.out.println();
		}
	}
	
	
}
