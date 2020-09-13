package dp;

public class MinCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		String input = "ababbbabbababa";
		int mincuts = minCuts(input);
		System.out.println("mincuts = " + mincuts);
	}

	public static int minCuts(String input) {
		// write your solution here
		if (input == null || input.length() <= 1) {
			return 0;
		}
		int n = input.length();
		boolean[][] f = new boolean[n][n];
		int[] c = new int[n];
		// f[i][j] input[i..j] is palindrome
		// f[i][j] = input[i] == input[j] && f[i + 1][ j - 1]
		// initialize: len = 0: f[i][j] = true; len = 1: f[i][j] = input[i]==
		// input[j]
		for (int i = 0; i < n; i++) {
			f[i][i] = true;
		}
		// for(int i = 0; i < n-1; i++) {
		// f[i][i+1] = (input.charAt(i) == input.charAt(i + 1));
		// }

		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1; // i + len - 1 <= n - 1 i + len <= n i <= n
										// - len
				if (len == 2) {
					f[i][j] = (input.charAt(i) == input.charAt(j));
				} else {
					f[i][j] = (input.charAt(i) == input.charAt(j))
							&& f[i + 1][j - 1];
				}
			}
		}
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < n; i++) {
			if (f[0][i] == true) {
				c[i] = 0;
			} else {
				c[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					if (f[j + 1][i] == true && 1 + c[j] < c[i]) {
						c[i] = 1 + c[j];
					}
				}
			}
		}
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + " ");
		}
		return c[n - 1];
	}

}
