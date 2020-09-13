package lai_online;
import java.util.*;

public class Class16_1_Median_Tracker {

	public PriorityQueue<Integer> maxHeap;
	public PriorityQueue<Integer> minHeap;

	public Class16_1_Median_Tracker() {
		// add new fields and complete the constructor if necessary.
		maxHeap = new PriorityQueue<Integer>();
		minHeap = new PriorityQueue<Integer>(11, Collections.reverseOrder());
	}

	public void read(int value) {
		// write your implementation here.
		if (maxHeap.isEmpty() || maxHeap.peek() <= value) {
			maxHeap.offer(value);
		} else {
			minHeap.offer(value);
		}
		// maxHeap.size == minHeap.size()
		// or
		// maxHeap.size = minHeap.size() + 1
		// adjust the minHeap and maxHeap.
		if (maxHeap.size() > minHeap.size() + 1) {
			// move maxHeap.peek() to minHeap
			minHeap.offer(maxHeap.poll());
		} else if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public Double median() {
		// write your implementation here.
		int size = getSize();
		if (size == 0) {
			return null;
		} else if (size % 2 == 1) {
			return (double) maxHeap.peek();
		} else {
			return (double) (maxHeap.peek() + minHeap.peek()) / 2.0;
		}
	}

	public int getSize() {
		return maxHeap.size() + minHeap.size();
	}
}
