package dynamic_programming;

public class Set4_LongestCommonSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-
	 * subsequence/
	 */

	public static int LCS(String X, String Y) {
		return LCS_rec(X, Y, X.length(), Y.length());
	}

	public static int LCS_rec(String X, String Y, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			return LCS_rec(X, Y, m - 1, n - 1) + 1;
		} else {
			return Math.max(LCS_rec(X, Y, m - 1, n), LCS_rec(X, Y, m, n - 1));
		}
	}

	public static int LCS_DP(String X, String Y) {
		int m = X.length();
		int n = Y.length();
		int[][] f = new int[m + 1][n + 1];
		// f[i][j] = if X[i-1] == Y[j-1] f[i-1][j-1] + 1
		// else max(f[i][j-1], f[i-1][j])
		// innitiazlie:
		for (int i = 0; i <= m; i++) {
			f[i][0] = 0;
		}
		for (int j = 0; j <= n; j++) {
			f[0][j] = 0;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					f[i][j] = f[i - 1][j - 1] + 1;
				} else {
					f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				}
			}
		}
		int len = f[m][n];
		// follow up, print out the LCS
		char[] lcs = new char[len];
		int index = len - 1;
		int i = m, j = n;
		while (i > 0 && j > 0) {
			if (X.charAt(i - 1) == Y.charAt(j - 1)) {
				lcs[index] = X.charAt(i - 1);
				i--;
				j--;
				index--;
			} else if (f[i - 1][j] > f[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		System.out.println("  X=  " + X + "  Y=  " + Y + "  lcs " + String.copyValueOf(lcs));

		return f[m][n];
	}

	public static void test() {
		String X = "abcde";
		String Y = "cbabdfe";

		int lcs1 = LCS(X, Y);
		int lcs2 = LCS_DP(X, Y);
		System.out.println("lcs1 = " + lcs1);
		System.out.println("lcs2 = " + lcs2);
	}

	// follow up
	/*
	 * Print out the longest common subsequence
	 */

}
