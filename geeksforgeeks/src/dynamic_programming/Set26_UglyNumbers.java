package dynamic_programming;

public class Set26_UglyNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	/*
	 * http://www.geeksforgeeks.org/ugly-numbers/
	 */
	
	public static void test1() {
		int n = 100;
		int ugly10_1 = uglyNumber(n);
		System.out.println();
		System.out.println(ugly10_1);
		int ugly10_2 = uglyNumDP(n);
		System.out.println("ugly10_2 = " + ugly10_2);
	}
	public static int uglyNumber(int n) {
		int count = 1;
		int i = 1;
		while( count < n) {
			i ++;
			if (isUgly(i)) {
				count ++;
				System.out.print(i + " ");
			}
		}
		
		return i;
	}
	
	public static boolean isUgly(int n) {
		n = divide(n, 2);
		n = divide(n, 3);
		n = divide(n, 5);
		return n == 1;
	}
	
	public static int divide(int a, int b) {
		while(a%b == 0) {
			a /=b;
		}
		return a;
	}
	public static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	public static int uglyNumDP(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int i2 = 0,i3 = 0, i5 = 0;
		int next2 = 2, next3 = 3, next5 = 5;
		for (int i = 1; i < n; i++) {
			int next = min(next2, next3,next5);
			ugly[i] = next;
			if (next == next2) {
				i2 = i2 + 1;
				next2 = ugly[i2] * 2;
			}
			if (next == next3) {
				i3 = i3 + 1;
				next3 = ugly[i3] * 3;
			}
			if (next == next5) {
				i5 = i5 + 1;
				next5 = ugly[i5] * 5;
			}
			
		}
		for(int i= 0; i<n; i++) {
			System.out.print(ugly[i] + " ");
		}
		System.out.println();
		return ugly[n-1];
	}

}
