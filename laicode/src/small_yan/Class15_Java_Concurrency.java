package small_yan;

public class Class15_Java_Concurrency {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
//		Thread newThread = new HelloThread();
//		newThread.start();
//		
//		System.out.println("hello 2");
		
		Thread newThread = new Thread(new HelloRunnable());
		newThread.start();
		newThread.interrupt();
		
		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					for(int i = 0; i < 100; i ++) {
						System.out.println("i = " + i);
						if (this.isInterrupted()) {
							throw new InterruptedException("XXX");
						}
						sleep(100);
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
					return ;
				}
			}
		};
		
		th.start();
		
		th.interrupt();
	}
	
	
	/*
	 * ways to creating thread and make them running
	 * (1) extends Thread
	 */
	public static class HelloThread extends Thread{
		@Override
		public void run() {
			System.out.println("Hello.");
		}
	}
	
	/*
	 * (2) implements Runnable
	 */
	
	public static class HelloRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
//			for(int i = 0; i < 100000; i ++) {
//				System.out.println(i);
//				if (Thread.interrupted()) {
//					return ;
//				}
//			}
			
			for(int i = 0; i < 100000; i ++) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO: handle exception
					return ;
				}
				System.out.println("Hello");
			}
		}
		
	}
	
	

}
