package sortbynum;

import java.util.LinkedList;

public class Task346_MovingAverageFromDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private LinkedList<Integer> queue;
	private double curSum;
	private int size;
	
	public Task346_MovingAverageFromDataStream(int size){
		queue = new LinkedList<Integer>();
		curSum = 0;
		
		this.size = size;
	}
	
	public double next(int val) {
		
		if (queue.size() < size) {
			curSum += val;
			queue.offerLast(val);
		} else {
			// counter == size
			curSum -= queue.pollFirst();
			queue.offerLast(val);
			curSum += val;
		}
		return curSum/queue.size();
	}

}


/*
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
