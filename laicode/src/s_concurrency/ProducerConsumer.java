package concurrency;

import java.util.*;

public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q q = new Q();
		Thread producder = new Thread(new Producer(q));
		Thread consumer = new Thread(new Consumer(q));
		producder.start();
		consumer.start();
	}	
}

class Producer implements Runnable{
	Q q;
	public Producer(Q q) {
		super();
		this.q = q;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i ++) {
			q.put(i);
		}
	}
}

class Consumer implements Runnable{
	Q q;
	public Consumer(Q q) {
		super();
		this.q = q;
	}
	@Override
	public void run() {
		for(int i = 0; i < 100; i ++) {
			System.out.println(q.take());
		}
	}
}

class Q{
	int limit = 10;
	Queue<Integer> queue = new LinkedList<Integer>();
	public synchronized void put(int i) {
		while(queue.size() == limit) {
			// wait
			try {
				/*
				 * causes the current to wait until another thread invokes the notify() method or 
				 * the notifyAll() method for this object 
				 */
				this.wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
				return ;
			}
		}
		if (queue.size() == 0) {
			this.notifyAll();
		}
		queue.offer(i);
	}
	
	public synchronized int take() {
		while (queue.size() == 0) {
			// wait
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if (queue.size() == limit) {
			this.notifyAll();
		}
		return queue.poll();
	}
}
