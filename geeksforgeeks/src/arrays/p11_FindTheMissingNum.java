package arrays;

public class p11_FindTheMissingNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/find-the-missing-number/
	 */

	public static int findMissingNo(int[] a, int n) {
		int sum = n * (n + 1) / 2;
		for (int i = 0; i < a.length; i++) {
			sum -= a[i];
		}
		return sum;
	}

	/*
	 * 1) XOR all the array elements, let the result of XOR be X1. 
	 * 2) XOR all numbers from 1 to n, let XOR be X2. 
	 * 3) XOR of X1 and X2 gives the missing number.
	 */
	public static int findMissingNo2(int[] a, int n) {

		int x1 = 1;
		int x2 = a[0];

		for (int j = 2; j <= n; j++) {
			x1 ^= j;
		}
		for (int i = 1; i < a.length; i++) {
			x2 ^= a[i];
		}

		return x1 ^ x2;
	}

	public static void test() {
		int[] a = { 1, 2, 3, 4, 5, 7, 8, 9 };
		int n = 9;
		int m1 = findMissingNo(a, n);
		int m2 = findMissingNo2(a, n);
		System.out.println("m1 = " + m1);
		System.out.println("m2 = " + m2);
	}

}
