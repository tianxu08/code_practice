package sortbynum;

import java.util.*;


public class Task327_CountofRangeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {-2, 5, -1}; 
		int lower = -2, upper = 2;
		Task327_CountofRangeSum sln = new Task327_CountofRangeSum();
		int rev = sln.countRangeSum(array, lower, upper);
		System.out.println("rev = " + rev);
	}

	public class SegmentTreeNode {
		SegmentTreeNode left;
		SegmentTreeNode right;
		int count;
		long start;
		long end;

		public SegmentTreeNode(long _s, long _e) {
			this.start = _s;
			this.end = _e;
		}
		public String toString() {
			return "[  " + start + "  :  " + end + "   ] =>  " + count;
			
		}
	}

	private SegmentTreeNode buildSegmentTree(Long[] valArr, int low,
			int high) {
		if (low > high)
			return null;
		SegmentTreeNode stn = new SegmentTreeNode(valArr[low], valArr[high]);
		if (low == high)
			return stn;
		int mid = (low + high) / 2;
		stn.left = buildSegmentTree(valArr, low, mid);
		stn.right = buildSegmentTree(valArr, mid + 1, high);
		return stn;
	}

	private void updateSegmentTree(SegmentTreeNode stn, Long val) {
		if (stn == null)
			return;
		if (val >= stn.start&& val <= stn.end) {
			stn.count++;
			updateSegmentTree(stn.left, val);
			updateSegmentTree(stn.right, val);
		}
	}

	private  int getCount(SegmentTreeNode stn, long start, long end) {
		if (stn == null)
			return 0;
		if (start > stn.end || end < stn.start)
			return 0;
		if (start <= stn.start && end >= stn.end)
			return stn.count;
		return getCount(stn.left, start, end) + getCount(stn.right, start, end);
	}

	public int countRangeSum(int[] nums, int lower, int upper) {

		if (nums == null || nums.length == 0)
			return 0;
		int ans = 0;
		Set<Long> valSet = new HashSet<Long>();
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += (long) nums[i];
			System.out.println("sum = " + sum);
			valSet.add(sum);
		}

		for(Long l: valSet) {
			System.out.println(l);
		}
		Long[] valArr = valSet.toArray(new Long[0]);
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(valArr));
		
		Arrays.sort(valArr);
		System.out.println(Arrays.toString(valArr));
	
		SegmentTreeNode root = buildSegmentTree(valArr, 0, valArr.length - 1);
		levelOrderPrint(root);
		System.out.println("sum = " + sum);
		System.out.println("lower = " + lower );
		System.out.println(" upper = " + upper);
		for (int i = nums.length - 1; i >= 0; i--) {
			System.out.println("----sum =  " + sum);
			updateSegmentTree(root, sum);
			sum -= (long) nums[i];
			ans += getCount(root, (long) lower + sum, (long) upper + sum);
			
			levelOrderPrint(root);
		}
		return ans;
	}

	
	public void levelOrderPrint(SegmentTreeNode root) {
		System.out.println("-------------------------");
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
		
		System.out.println("-------------------------");
	}
	
}
