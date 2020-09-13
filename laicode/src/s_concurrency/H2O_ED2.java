package concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class H2O_ED2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		H2O_ED2 h2o = new H2O_ED2();
		for(int i = 0; i < 10; i ++) {
			Thread t1 = new Thread(h2o.new OTherad());
			Thread t2 = new Thread(h2o.new HThread());
			t1.start();
			t2.start();
		}
	}
	
	Lock lock = new ReentrantLock();
	int hCount = 0;
	int oCount = 0;
	Condition hCondition = lock.newCondition();
	Condition oCondition = lock.newCondition();
	
	public void H() {
		lock.lock();
		while(hCount >= 2) {
			try {
				hCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hCount ++;  // how many h thread are running now
		if (hCount == 2 && oCount == 1) {
			System.out.println("H = " + hCount + "  O = " + oCount);
			hCount = 0;
			oCount = 0;
			System.out.println("Gen H2O <- H");
			oCondition.signalAll();
		}
		lock.unlock();
	}
	public void O() {
		lock.lock();
		while (oCount >= 1) {
			try {
				oCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		oCount ++;
		if (hCount == 2 && oCount == 1) {
			System.out.println("H = " + hCount + "  O = " + oCount);
			hCount = 0;
			oCount = 0;
			System.out.println("Gen H2O <- O");
			hCondition.signalAll();
		}
		lock.unlock();
	}
	
	class OTherad implements Runnable{
	
		public void run() {
			O();
		}
	}
	
	class HThread implements Runnable{
		
		public void run() {
			H();
		}
	}

}
