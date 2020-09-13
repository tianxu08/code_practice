package string;

public class WildCardMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	/*
	 * f[i][j]: the first i chars in s match the first j in p
	 * 
	 * f[i][j] = f[i - 1][j- 1]                 if s[i - 1] == p[j - 1] || p[j - 1] == '?'
	 *           f[i][j - 1] || f[i - 1][j - 1] || f[i - 2][j -1 ] ||..||f[0][j - 1]  if p[j] = '*'
	 *           
	 * p[j] = '*'
	 * f[i - 1][j] = 
	 */
	
	public static void test() {
		String p = "aa";
		String s = "aa";
		System.out.println(isMatch2(s, p));
	}
	
	
	/*
	 * f[i][j]  s[0, i - 1]  p[]
	 */
	public static boolean isMatch(String s, String p) {
		if (s == null && p == null) {
			return true;
		} 
		if (s == null || p == null) {
			return false;
		}
		
		boolean[][] f = new boolean[s.length() + 1][p.length() + 1];
		// initialize:
		f[0][0] = true;
		
		// f[i][0] = false
		for(int i = 1; i <= s.length(); i ++) {
			f[i][0] = false;
		}
		// f[0][j]
		boolean flag = false;
		for(int j = 1; j <= p.length();j ++) {
			if (j == 1 && p.charAt(j - 1) == '*' ) {
				flag = true;
				f[0][j] = true;
			} else if (flag && p.charAt(j - 1) == '*') {
				f[0][j] = true;
			} else {
				flag = false;
				f[0][j] = false;
			}
		}
		// i 1 -> p.length(),  j 1 -> s.length()
		for(int i = 1; i <= s.length(); i ++) {
			for(int j = 1; j <= p.length(); j ++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					f[i][j] = f[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					f[i][j] = f[i - 1][j] || f[i][j -1];
				} else {
					f[i][j] = false;
				}
			}
		}
		return f[s.length()][p.length()];
	}
	
	
	// 1d dp
	/*
	 * f[i][j] = f[i-1][j-1]  if s[i - 1] ==p[j - 1] || p[j - 1] ='?'
	 *           f[i][j-1] || f[i-1][j]   if p[j - 1] == '*'
	 *           
	 * f[i]  f[i - 1] is f[i - 1][j]
	 *       the previous f[i] is f[i][j - 1]
	 *       we, also need to keep previous f[i - 1] <= f[i - 1][j - 1]
	 *       
	 * init: f[0] = f[0][j] 
	 */
	
	public static boolean isMatch2(String s, String p) {
		if (s == null && p == null) {
			return true;
		} 
		if (s == null || p == null) {
			return false;
		}
		
		boolean[] f = new boolean[p.length() + 1];
		boolean flag = false;
		
		f[0] = true;
		for(int j = 1; j <= p.length(); j ++) {
			if ( j == 1 && p.charAt(j - 1) == '*') {
				f[j] = true;
				flag = true;
			} else if (flag && p.charAt(j - 1) == '*') {
				f[j] = true;
				flag = true;
			} else {
				flag = false;
				f[j] = false;
			}
		}
		
		for(int i = 1; i <= s.length(); i ++) {
			boolean diag = f[0];
			f[0] = false;
			for(int j = 1; j <= p.length(); j ++) {
				boolean temp = f[j];
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					f[j] = diag;
				} else if (p.charAt(j - 1) == '*') {
					f[j] = f[j - 1] || f[j];
				
				} else {
					f[j] = false;
				} 
				diag = temp;
			}
		}
		return f[p.length()];
	}
	
	
	
	
	
	/*
	 * 
	 */
	
	public static boolean isMatch3(String s, String p) {
		if (s == null && p == null) {
			return true;
		}
		int indexP = 0;
		int indexS = 0;
		
		int preS = 0;
		int preP = 0;
		
		boolean back = false;
		
		while(indexS < s.length()) {
			if (indexP < p.length() && (s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) =='?')) {
				indexP ++;
				indexS ++;
			} else if (indexP < p.length() && p.charAt(indexP) == '*') {
				while(indexP < p.length() && p.charAt(indexP) == '*') {
					indexP ++;
				}
				
				// if all the remaining is *, return true;
				if (indexP == p.length()) {
					return true;
				}
				
				back = true;
				preS = indexS;
				preP = indexP;
			} else if (back) {
				++ preS;
				indexP = preP;
				indexS = preS;
			} else {
				return false;
			}
		}
		
		while(indexP < p.length() && p.charAt(indexP) == '*') {
			indexP ++;
		}
		if (indexP == p.length() && indexS == s.length()) {
			return true;
		}
		
		return false;
	}
	

}
