package dynamic_programming;

public class Set36_MaximumProductCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
	 */
	
	public static int maxProductCutRec(int n) {
		if (n == 0 || n == 1) {
			return 0;
		}
		int max = 0;
		for (int i = 1; i < n; i++) {
			max = Math.max(max, Math.max(i * (n - i), maxProductCutRec(n - i) * i));
		}
		return max;
	}
	
	public static void test() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("i = " + i + " maxProduct = " + maxProductCutRec(i));
			System.out.println("i = " + i + " maxProductDP = " + maxProductCutDP(i));
			System.out.println("i = " + i + " maxProductTricky = " + maxProductCutTricky(i));
			
		}
	}
	
	public static int maxProductCutDP(int n) {
		if (n == 0 || n == 1) {
			return 0;
		}
		int[] f = new int[n+1];
		f[0] = 0;
		f[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			int max = 0;
			for (int j = 1; j < i; j++) {
				//max = maxOf3(max, j * (i-j), f[i-j] * j);
				max = Math.max(max, Math.max(j * (i - j), f[i - j] * j));
			}
			f[i] = max;
		}
		return f[n];
	}
	
	public static int maxOf3(int x, int y, int z) {
		int max = Math.max(x, y);
		return Math.max(max, z);
	}
	
	public static int maxProductCutTricky(int n) {
		if (n == 0 || n == 1) {
			return 0;
		}
		
		if (n == 2 || n == 3) {
			return n-1;
		}
		int res = 1;
		while (n > 4) {
			n -= 3;
			res *= 3;
		}
		return n * res;
	}

}
