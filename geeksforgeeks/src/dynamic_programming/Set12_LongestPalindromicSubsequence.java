package dynamic_programming;

public class Set12_LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	/*
	 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic
	 * -subsequence/
	 */

	public static int longestPaliSub(String s, int i, int j) {
		if (i > j) {
			return 0;
		}
		// i == j
		if (i == j) {
			return 1;
		}
		// i + 1 == j
		if (i + 1 == j && s.charAt(i) == s.charAt(j)) {
			return 2;
		}

		//
		if (s.charAt(i) == s.charAt(j)) {
			return longestPaliSub(s, i + 1, j - 1) + 2;
		}

		return Math.max(longestPaliSub(s, i + 1, j),
				longestPaliSub(s, i, j - 1));
	}

	public static int longestPaliSub(String s) {
		int i = 0, j = s.length() - 1;
		return longestPaliSub(s, i, j);
	}

	public static void test() {
		String s = "abbacbbc";
		int rev = longestPaliSub(s);
		int rev2 = longestPaliSubDP(s);
		System.out.println("rev = " + rev);
		System.out.println("rev2 = " + rev2);
	}
	
	public static int longestPaliSubDP(String s) {
		// 
		int len = s.length();
		int[][] f = new int[len][len];
		//f[i][j] stands the maximum palindrome of  s[i..j].
		for (int i = 0; i < len; i++) {
			f[i][i] = 1;
		}
		for (int cl = 2; cl <= len; cl++) {
			for (int i = 0; i <= len - cl ; i++) {
				int j = i + cl - 1;   // j <= len - 1   i + l - 1 <= len - 1  i <= len - l
				if (cl == 2 && s.charAt(i) == s.charAt(j)) {
					f[i][j] = 2;
				} else if (s.charAt(i) == s.charAt(j)) {
					f[i][j] = f[i+ 1][j - 1] + 2;
				} else {
					f[i][j] = Math.max(f[i][j-1], f[i + 1][j]);
				}
			}
		}
		return f[0][len - 1];
		       
	}
}
