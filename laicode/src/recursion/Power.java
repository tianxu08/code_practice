package recursion;

public class Power {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(power(2, 2));
	}

	public static long power(int a ,int b) {
		if (b == 0) {
			return 1;
		} 
		long half = power(a, b/2);
		if (b %2 == 1) {
			return half * half * a;
		} else {
			return half * half;
		}
	}
	
}
