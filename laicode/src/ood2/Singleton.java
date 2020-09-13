package ood2;

public class Singleton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 从头到尾只能创建一个object
	// 别的程序不能通过new 来创造object
	private static final Singleton Instance = new Singleton();
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		return Instance;
	}
	
	

}
