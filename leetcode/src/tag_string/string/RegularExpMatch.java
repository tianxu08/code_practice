package string;

public class RegularExpMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
		
	
	/* 
	 * dp[i][j]   s[0, i - 1] matches p[j - 1]
	 * 
	 * p[j - 1] != '.' && p[j - 1] != '*' 
	 * 		dp[i][j] = dp[i - 1][j - 1] && s[i - 1] == p[j - 1]
	 * p[j - 1] == '.' dp[i][j] = dp[i - 1][j - 1]
	 * 
	 * p[j - 1] == '*'  * can match 0, 1 or more p[j - 2]
	 * (1) matches 0 p[j - 2], p[0..j - 1] = p[0.. j - 3]
	 * dp[i][j] = dp[i][j - 2]
	 * (2) matches 1 p[j - 2], p[0..j - 1] = p[0..j - 2]
	 * dp[i][j] = dp[i][j - 2]
	 * ...
	 * (3) matches more p[j - 2], p[0..j - 1] = p[0..j -2, ]
	 * 
	 * dp[i][j] = dp[i - 1][j] && (p[j - 2] == '.' || s[i - 2] == p[j - 2])
	 */
	
	public static boolean isMatch(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		
		for(int i = 0; i <= s.length(); i ++) {
			for(int j = 1; j <= p.length(); j ++) {
				if (p.charAt(j - 1) != '.' && p.charAt(j - 1) != '*') {
					if (i > 0 && s.charAt(i - 1) == p.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} 
				} else if (p.charAt(j - 1) == '.') {
					if (j > 0) {
						dp[i][j] = dp[i - 1][j - 1];
					}
				} else if (j > 1) {
					// * cannot be the first element
					if(dp[i][j-1] || dp[i][j-2])  // match 0 or 1 preceding element
                        dp[i][j] = true;
                    else if(i>0 && (p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.') && dp[i-1][j]) // match multiple preceding elements
                        dp[i][j] = true;
				}
			}
		}
		return dp[s.length()][p.length()];
	}
	

}
