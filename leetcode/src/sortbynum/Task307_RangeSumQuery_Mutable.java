package sortbynum;

import java.util.LinkedList;

public class Task307_RangeSumQuery_Mutable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,3,5};
		Task307_RangeSumQuery_Mutable sln = new Task307_RangeSumQuery_Mutable(nums);
		int rev1 = sln.sumRange(0, 2);
		System.out.println("rev1 = " + rev1);
		sln.levelPrint(sln.root);
		sln.update(1, 2);
		
		System.out.println("------------");
		sln.levelPrint(sln.root);
		int rev2 = sln.sumRange(0, 2);
		System.out.println("rev2 = " + rev2);
	}
	
	
	private SegmentTreeNode root;
	private int size = 0;
	
	public Task307_RangeSumQuery_Mutable(int[] nums) {
		this.size = nums.length;
        this.root = build(nums);
    }

    void update(int i, int val) {
    	
        modify(root, i, val);
    }

    public int sumRange(int i, int j) {
    	SegmentTreeNode cur = root;
        return query(cur, i, j);
    }
	
	public class SegmentTreeNode{
		int start, end;
		int sum;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int _s, int _e, int _sum) {
			// TODO Auto-generated constructor stub
			this.start = _s;
			this.end = _e;
			this.sum = _sum;
		}
		
		public String toString() {
			return "[ " + start + "  " + end + "  ]"  + " sum = " + sum;
		}
	}
	
	public SegmentTreeNode build(int[] array) {
		return buildHelper(array, 0, array.length - 1);
	}
	public SegmentTreeNode buildHelper(int[] array, int start, int end) {
		if (start > end) {
			return null;
		}

		SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
		if (start == end) {
			root.sum = array[start];
			
		} else {
			int mid = start + (end - start)/2;
			root.left = buildHelper(array, start, mid);
			root.right = buildHelper(array, mid + 1, end);
			
			root.sum = root.left.sum + root.right.sum;
		}
		return root;
	}

	public void modify(SegmentTreeNode root, int index, int val) {
		if (root.start == index && index == root.end) {
			root.sum = val;
			return;
		}
		int root_mid = root.start + (root.end - root.start)/2;
		if (root.start <= index && index <= root_mid) {
			modify(root.left, index, val);
		}
		
		if (root_mid < index && index <= root.end) {
			modify(root.right, index, val);
		}
		root.sum = root.left.sum + root.right.sum;
	}
	
	public int query(SegmentTreeNode root, int start, int end) {
		if (root == null || start > root.end || end < root.start) {
			return 0;
		}
		if (root.start == start && root.end == end) {
			return root.sum;
		}
		int root_mid = root.start + (root.end - root.start)/2;
		int left_sum = 0, right_sum = 0;
		if (start < root_mid) {
			if (end > root_mid) {
				// split
				left_sum = query(root.left, start, root_mid);
			} else {
				// contains
				left_sum = query(root.left, start, end);
			}
		}
		
		if (end > root_mid) { // {root_mid + 1, end}
			if (start <= root_mid) {
				// split
				right_sum = query(root.right, start, end);
			} else {
				// contains
				right_sum = query(root.right, start, end);
			}
		}
		
		return left_sum + right_sum;
		
	}
	
	public void levelPrint(SegmentTreeNode root) {
		LinkedList<SegmentTreeNode> q = new LinkedList<Task307_RangeSumQuery_Mutable.SegmentTreeNode>();
		q.add(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				SegmentTreeNode cur = q.poll();
				
				System.out.print(cur);
				
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
