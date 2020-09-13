package dp;

public class MaxProductCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static int maxProduct(int n) {
		// write your solution here
		if (n <= 1) {
			return 0;
		}
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 1; i <= n; i++) {
			int curMax = 0;
			for (int j = 1; j <= i/2; j++) {
				curMax = Math.max(curMax,
						Math.max(j, M[j]) * Math.max(i - j, M[i - j]));
			}
			M[i] = curMax;
		}
		for (int i = 0; i < M.length; i++) {
			System.out.print(M[i] + " ");
		}

		return M[n];
	}
	public static void test() {
		int n = 12;
		int maxProduct = maxProduct(n);
		System.out.println("maxProduct = " + maxProduct);
	}

}
