package concurrency;

public class H2O {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		H2O h2o = new H2O();
		for(int i = 0; i < 10; i ++) {
			Thread th1 = new Thread(h2o.new HThread(i));
			th1.start();
			Thread th2 = new Thread(h2o.new OThread(i));
			th2.start();
			
		}
	}
	
	/*
	 * H2O problem: 
	 * H threads running method H()
	 * O threads running method O()
	 * Requirement
	 * (3) 2 H and 1 O threads can form a H2O. If there are 2 H and 1 O, these 3 threads should return
	 * (4) If there are no sufficient numbers of H or O threads, all threads should be blocked and waited for
	 *     the other threads to make them proceed
	 */
	
	int cntH = 0, cntO = 0;
	
	public synchronized void O(int i) {
		if (cntH == 2 && cntO == 1) {
			cntO -= 1;
			cntH -= 2;
			System.out.println("gen H2O : O");
		}
		
		while (cntO >= 1) {
			// there are still O thread, wait for 2 more H threads
			try {
				System.out.println("O wait");
				wait();
			} catch (InterruptedException ex) {
				// TODO: handle exception
				return ;
			}
		}
		
		if (cntO == 0) {
			this.notify();
		}
		System.out.println("Add O: " + i);
		cntO ++;
	}
	
	public synchronized void H(int i) {
		if (cntH == 2 && cntO == 1) {
			cntO -= 1;
			cntH -= 2;
			System.out.println("get H2O: H");
		}
		while(cntH >= 2) {
			try {
				System.out.println("H wait");
				wait(); 
			} catch (InterruptedException e) {
				// TODO: handle exception
				return ;
			}
		}
		if (cntH == 1) {
			this.notify();
		}
		System.out.println("add h : " + i);
		cntH ++;
	}
	
	class OThread implements Runnable{
		int id;
		OThread(int i) {
			id = i;
		}
		@Override
		public void run() {
			O(id);
		}
	}
	
	class HThread implements Runnable{
		int id;
		public HThread(int i) {
			// TODO Auto-generated constructor stub
			id = i;
		}
		@Override
		public void run() {
			H(id);
		}
	}

}
