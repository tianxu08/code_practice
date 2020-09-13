package sortbynum;

import java.util.Collections;

import java.util.PriorityQueue;


public class Task295_MedianDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task295_MedianDataStream sln = new Task295_MedianDataStream();
		sln.addNum(1);
		sln.addNum(10);
		sln.addNum(3);
		sln.addNum(1);
		System.out.println(sln.findMedian());
	}
	PriorityQueue<Integer> minHeap, maxHeap;

	public Task295_MedianDataStream() {
		this.minHeap = new PriorityQueue<Integer>();
		this.maxHeap = new PriorityQueue<Integer>(16,Collections.reverseOrder());
	}
	
	
	/*
	 * maxHeap.size == minHeap.size or
	 * maxHeap.size + 1 == minHeap.size
	 */
	public void addNum(int num) {
		if (maxHeap.size() < minHeap.size()) {
			// add to maxHeap first
			maxHeap.add(num);
			
			// adjust
			minHeap.add(maxHeap.poll());
			maxHeap.add(minHeap.poll());
		} else {
			// maxHeap.size() == minHeap.size()
			// add to minHeap
			minHeap.add(num);
			
			// adjust
			maxHeap.add(minHeap.poll());
			minHeap.add(maxHeap.poll());
		}
	}
	
	

	// Returns the median of current data stream
	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek())/2.0;
		} else {
			return minHeap.peek();
		}
	}
	

}
