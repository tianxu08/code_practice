package class1;

import java.util.HashMap;
import java.util.LinkedList;

public class Query2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static class SegmentTreeNode2{
		public int count;
		public int start, end;
		public SegmentTreeNode2 left, right;
		public SegmentTreeNode2(int start, int end, int count) {
			this.start = start;
			this.end = end;
			this.count = count;
			this.left = null;
			this.right = null;
		}
		
		public String tostrString() {
			return "[ " + start + " , " + end + " ]"  + "count = " + count ;
		}
	}
	
	public static void test() {
		int[] array = {0,2,3};
		SegmentTreeNode2 root = build(array);
		levelOrder(root);
		
//		System.out.println(query(root, 1,1));
		System.out.println(query(root, 1, 2));
	}
	
	public static SegmentTreeNode2 build(int[] array) {
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
		return buildHelper(min, max, map);
	}
	
	public static SegmentTreeNode2 buildHelper(int start, int end, HashMap<Integer, Integer> map) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode2 root = new SegmentTreeNode2(start, end, 0);
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
	
	
	public static int query(SegmentTreeNode2 root, int start, int end) {
		if (start > end) {
			return 0;
		}
		if (root == null) {
			return 0;
		}
		// contains or equal
		if (start <= root.start &&  end >= root.end) {
			return root.count;
		}
		
		int root_mid = root.start + (root.end - root.start)/2;
		int left_count = 0;
		int right_count = 0;
		
		// left side
		if (start <= root_mid) {
			if (end <=root_mid) {
				// query [] is in left side
				left_count =  query(root.left, start, end);
			} else {
				// split
				left_count = query(root.left, start, root_mid);
			}
		}
		
		// right side
		if (root_mid < end) {
			if (start > root_mid) {
				// query [] is in right side
				right_count = query(root.right, start, end);
			} else {
				// split
				right_count = query(root.right, root_mid + 1, end);
			}
		}
		
		return left_count + right_count;
	}
	
	public static void levelOrder(SegmentTreeNode2 root) {
		if (root == null) {
			return ;
		}
		LinkedList<SegmentTreeNode2> q = new LinkedList<SegmentTreeNode2>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				SegmentTreeNode2 cur = q.poll();
				// print cur's content
				System.out.print("start = " + cur.start + " end = " + cur.end + "count = " + cur.count + "  \\ ");
				
				
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
