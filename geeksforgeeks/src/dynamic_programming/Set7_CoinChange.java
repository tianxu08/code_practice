package dynamic_programming;

public class Set7_CoinChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
	 */

	// return the count of ways we can sum s[0.. m-1] coins to get sum n
	public static int count(int[] S, int m, int n) {
		// base case
		if (n == 0) {
			return 1;
		}
		if (n < 0) {
			return 0;
		}
		// if there are no coins and n still > 0, then no solution
		if (m <= 0 && n > 0) {
			return 0;
		}
		// the sum of solution (1)excluding s[m - 1] and (2) including s[m - 1]
		return count(S, m - 1, n) + count(S, m, n - S[m - 1]);
	}

	public static void test() {
		int[] S = { 1, 2, 3 };
		int c = count(S, S.length, 4);
		int c2 = count2(S, S.length, 4);
		System.out.println(" c = " + c);
		System.out.println(" c2 = " + c2);
		
		
		int[] S2 = {1,5,3,6};
		int m = S2.length;
		int n = 10;
		int c21 = count(S2, m, n);
		int c22 = count2(S2, m, n);
		System.out.println("c21 = " + c21);
		System.out.println("c22 = " + c22);
	}

	public static int count2(int[] S, int m, int n) {

		// f[i][j] stands the S[0..j] to compose sum i
		// f[i][j] = f[i - s[j]][j] (including s[j])
		// + f[i][j-1] (excluding s[j])
		int[][] f = new int[n + 1][m];

		// initialize
		// f[0][j] = 1
		for (int j = 0; j < f[0].length; j++) {
			f[0][j] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				// count the including s[j]
				if (i - S[j] >= 0) {
					f[i][j] += f[i-S[j]][j];
				}
				if (j > 0) {
					f[i][j] += f[i][j - 1];
				}
			}
		}
		System.out.println("-----------------------");
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
		return f[n][m-1];
	}

}
