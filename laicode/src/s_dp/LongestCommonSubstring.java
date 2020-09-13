package dp;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * Longest Common Substring Fair DP
	 * Find the longest common substring of two given strings.
	 * Assumptions
	 * The two given strings are not null
	 * Examples
	 * S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd”
	 */
	
	/*
	 * M[i][j] the length of longest common substring of the first i chars in string one
	 * and first j chars in string two
	 * 
	 * Base case: M[0][0] = 0;
	 *            M[i][0] = 0; M[0][j] = 0;
	 * Induction rule:
	 * 			  if s[i-1] == t[j - 1], M[i][j] = M[i-1][j-1] + 1  
	 * 					if maxLen < M[i][j], maxLen = M[i][j], lEnd = i - 1
	 * 			  else M[i][j] = 0;  
	 */
	public static String longestCommon(String s, String t) {
		// write your solution here
		if (s == null || t == null) {
			return null;
		}
		int sLen = s.length();
		int tLen = t.length();
		int[][] M = new int[sLen + 1][tLen + 1];
		
		int maxLen = 0;
		int maxEndInS = -1;
		M[0][0] = 0;
		for(int i = 1; i <= sLen; i ++) {
			M[i][0] = 0;
		}
		for(int j = 1; j <= tLen; j ++) {
			M[0][j] = 0;
		}
		
		for(int i = 1; i <= sLen; i ++) {
			for(int j = 1; j < tLen; j ++) {
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					M[i][j] = M[i-1][j - 1] + 1;
					if (maxLen < M[i][j]) {
						maxLen = M[i][j];
						maxEndInS = i - 1;
					}
				} else {
					M[i][j] = 0;
				}
			}
		}
		int maxStartInS = maxEndInS - maxLen + 1;
		return s.substring(maxStartInS, maxStartInS + maxLen);
	}
	
	public static void test() {
		String s = "abcde";
		String t = "cdf";
		String rev = longestCommon(s, t);
		System.out.println("rev = " + rev);
	}

}
