package small_yan;

import small_yan.IntervalTree.ITNode;
import small_yan.IntervalTree.Interval;

public class IntervalTreePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public ITNode addInterval(ITNode root, Interval i) {
		if (root == null) {
			int range = getRange(i);
			int limit = i.end;
			return new ITNode(i, limit, range);
		}
		if (overlapped(root.interval, i)) {
			// merge the i with root.interval
			root.interval.start = Math.min(root.interval.start, i.start);
			root.interval.end = Math.max(root.interval.end, i.end);
			root.range = root.interval.end - root.interval.start + 1;
			root.limit = Math.max(root.limit, root.interval.end);
			return root;
		}
		return null;
	}
	
	public int queryRange(ITNode root) {
		return -1;
	}
	
	public boolean overlapped(Interval i1, Interval i2) {
		if (i1.start <= i2.end && i2.start <= i1.end) {
			return true;
		}
		return false;
	}
	
	public int getRange(Interval i) {
		return i.end - i.start + 1;
	}

}
