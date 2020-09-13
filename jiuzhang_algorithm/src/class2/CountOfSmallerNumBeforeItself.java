package class2;
import java.util.*;

public class CountOfSmallerNumBeforeItself {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void test() {
		int[] A = {1,2,7,8,5};
		ArrayList<Integer> result = countOfSmallerNumberII(A);
		System.out.println(result);
	}
	
	public static ArrayList<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
		int max = Integer.MIN_VALUE;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < A.length; i ++) {
			max = Math.max(max, A[i]);
		}
		
		SegmentTreeNode8 root = build(0, max);
		
		for(int i = 0; i < A.length; i ++) {
			int curSmallerNum = query(root, 0, A[i] - 1); 
			// query the range[0..A[i] - 1], this segment's count will be the number of smaller number before A[i]
			result.add(curSmallerNum);
			modify(root, A[i], 1);
		}
		return result;
    } 
	
	public static class SegmentTreeNode8{
		public SegmentTreeNode8 left, right;
		int start, end;
		int count;
		public SegmentTreeNode8(int _s, int _e, int _c) {
			this.start = _s;
			this.end = _e;
			this.count = _c;
		}
		@Override
		public String toString(){
			return "[ " + start  + " , " + end + " ]" + " count = " + count;
			
		}
	}
	
	public static SegmentTreeNode8 build(int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode8 root = new SegmentTreeNode8(start, end, 0);
		if (start == end) {
			root.count = 0;
		} else {
			int mid = start + (end - start)/2;
			root.left = build(start, mid);
			root.right = build(mid + 1, end);
		}
		return root;
	}
	
	public static int query(SegmentTreeNode8 root, int start, int end) {
		if (root == null || root.end < start || root.start > end) {
			return 0;
		}
		// root is exactly the same with [start, end]
		if (root.start == start && root.end == end) {
			return root.count;
		}
		int root_mid = root.start + (root.end - root.start) / 2;
		int left_count = 0;
		int right_count = 0;
		// left side
		if (start <= root_mid) {
			if (end > root_mid) { // split
				left_count = query(root.left, start, root_mid);
			} else { // [root.start, root_mid] contains the [start, end]
				left_count = query(root.left, start, end);
			}
		}

		// right side
		if (root_mid < end) {
			if (start <= root_mid) {// split
				right_count = query(root.right, root_mid + 1, end);
			} else { // [root_mid, root.end] contains [start, end]
				right_count = query(root.right, start, end);

			}
		}

		// doesn't overlap
		return left_count + right_count;
	}
	
	public static void modify(SegmentTreeNode8 root, int index, int count) {
		if (root.start == index && root.end == index) {
			root.count += count;
			return ;
		}
		
		int mid = root.start + (root.end - root.start)/2;
		if (root.start <= index && index <= mid) {
			modify(root.left, index, count);
		}
		
		if (mid < index && index <= root.end) {
			modify(root.right, index, count);
		}
		// update
		root.count = root.left.count + root.right.count;
	}

	
	
	public static void levelOrderPrint(SegmentTreeNode8 root) {
		if (root == null) {
			return ;
		}
		LinkedList<SegmentTreeNode8> q = new LinkedList<SegmentTreeNode8>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				SegmentTreeNode8 cur = q.poll();
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
