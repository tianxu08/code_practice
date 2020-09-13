package mj_linkedin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public class HeapElement {
		public int val;
		public int index;
		public int pos;

		public HeapElement(int v, int i, int pos) {
			this.val = v;
			this.index = i;
			this.pos = pos;
		}
	}

	public int[] merge(int[][] arrayOfArrays) {
		// write your solution here
		if (arrayOfArrays == null || arrayOfArrays.length == 0) {
			return null;
		}
		int rowLen = arrayOfArrays.length;
		ArrayList<Integer> rev = new ArrayList<Integer>();
		// create a minHeap
		Comparator<HeapElement> myComp = new Comparator<HeapElement>() {

			@Override
			public int compare(HeapElement o1, HeapElement o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		// create a minHeap
		PriorityQueue<HeapElement> q = new PriorityQueue<HeapElement>(rowLen,
				myComp);

		// add the the first element of all rows into the priority queue
		for (int i = 0; i < rowLen; i++) {
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				HeapElement element = new HeapElement(arrayOfArrays[i][0], i, 0);
				q.offer(element);
			}
		}

		while (!q.isEmpty()) {
			HeapElement cur = q.poll();
			rev.add(cur.val);

			if (arrayOfArrays[cur.index] != null
					&& cur.pos < arrayOfArrays[cur.index].length - 1) {
				HeapElement elem = new HeapElement(
						arrayOfArrays[cur.index][cur.pos + 1], cur.index,
						cur.pos + 1);
				q.add(elem);
			}
		}

		int[] result = new int[rev.size()];
		for (int i = 0; i < rev.size(); i++) {
			result[i] = rev.get(i);
		}
		return result;
	}

}
