package dynamic_programming;

public class Set13_CuttingRod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
	 */
	//input:      0 1 2 3 4 5  6  7   8 
	// price[] = {0,1,5,8,9,10,17,17, 20}
	
	// DP:
	// cutMax(n) = max(cut(n), price[i] + cut(n - i))  0 <=i <= n 
	
	public static int cutRodRec(int[] prices, int n) {
		if (n <= 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for (int k = 1; k <= n; k++) {
			max = Math.max(max, prices[k] + cutRodRec(prices, n - k));
		}
		return max;
	}
	
	public static void test() {
		int[] prices = {0,1,5,8,9,10,17,17,20};
		int n = prices.length - 1;
		int cut = cutRodRec(prices, n);
		int cut2 = cutRodDP(prices, n);
		System.out.println("cut = " + cut);
		System.out.println("cut2 = " + cut2);
	}
	
	
	
	public static int cutRodDP(int[] prices, int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		
		for (int i = 1; i <= n; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = 0; j <= i; j++) {
				max = Math.max(max, prices[j] + f[i - j]);
			}
			f[i] = max;
		}
		return f[n];
	}
	
	

}
