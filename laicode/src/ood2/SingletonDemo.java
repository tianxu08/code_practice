package ood2;

public class SingletonDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static volatile SingletonDemo instance = null;
	private SingletonDemo() {
		
	}
	
	/*
	 * Lazy Initialization
	 */
	public static SingletonDemo getInstance() {
		synchronized (SingletonDemo.class) {
			if (instance == null) {
				instance = new SingletonDemo();
			}
		}
		return instance;
	}

}
