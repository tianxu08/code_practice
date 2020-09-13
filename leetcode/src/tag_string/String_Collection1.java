package tag_string;

public class String_Collection1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 10
	 * regular expression matching
	 * '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element.
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * 
	 * Some examples:
	 * isMatch("aa","a") ? false
	 * isMatch("aa","aa") ? true
	 * isMatch("aaa","aa") ? false
	 * isMatch("aa", "a*") ? true
	 * isMatch("aa", ".*") ? true
	 * isMatch("ab", ".*") ? true
	 * isMatch("aab", "c*a*b") ? true
	 */
	public static boolean t10_regular_matching(String s, String p) {
		
		return false;
	}
	
	public static boolean t10_rMatch(String s, String p) {
		if (p.length() == 0) {
			return s.length() == 0;
		}
		
		// p.length == 1
		if (p.length() == 1) {
			if (s.length() < 1) {
				return false;
			} else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
				return false;
			} else {
				return t10_rMatch(s.substring(1), p.substring(1));
			}
		}
		
		// case p[1] != '*'
		if (p.charAt(1) != '*') {
			if (s.length() < 1) {
				 return false;
			} else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
				return false;
			} else {
				return t10_rMatch(s.substring(1), p.substring(1));
			}
		} else {
			// case2: p[1] is '*'
			// case2.1: a char and '*' in p match 0 char in s
			if (t10_rMatch(s, p.substring(2))) {
				return true;
			} 
			
			// case2.2: a char and '*' in p match 1 or more chars in s
			// try every situation.
			int i = 0;
			while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if (t10_rMatch(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}
		}
		return false;
	}
	
	
	
	public static boolean t2_edit_distance_dp(String s, String p) {
		
		return false;
	}
	
	/**
	 * wild card matching
	 */
	
	/**
	 * 72 edit distance
	 * word1 
	 * word2
	 * the minimum number of steps required to convert word1 to word2.
	 * 3 types of operations:
	 * a) Insert a character
	 * b) Delete a character
	 * c) Replace a character
	 * dp[i][j] the minimum number of step required to convert from word1[0..i-1] to word2[0..j -1]
	 * assume that word1 and word2 is not null
	 */
	
	public static int t72_edit_distance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		
		int[][] dp = new int[len1 + 1][len2 + 1];
		// dp[0][0] = 0;
		// dp[i][0] = i;
		// dp[0][j] = 0;
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}            
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
		
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j<= len2; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i- 1][j], dp[i - 1][j -1])) + 1;
				}
			}
		}
		
		return dp[len1][len2];
	}
	
	public static int t2_edit_distance_opt(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		
		int upperLeft = 0;
		int[] M = new int[len2 + 1];
		
		// fill in the first row
		for (int j = 0; j <= len2; j++) {
			M[j] = j;
		}
		
		for (int i = 1; i <= len1; i++) {
			upperLeft = M[0]; // get the first element in the previous row
			M[0] = i;
			for (int j = 1; j<= len2; j++) {
				int upper = M[j];
				
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					M[j] = upperLeft;
				} else {
					M[j] = Math.min(M[j - 1], Math.min(upperLeft, upper)) + 1;
				}
				// update upperLeft
				upperLeft = upper;
			}
		}
		return M[len2];
	
	}
	
	
	
	
	

}
