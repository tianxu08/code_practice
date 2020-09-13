package tag_dp;

import ds.Debug;

public class DecodeWays_91 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 91 Decode Ways
	 * https://leetcode.com/problems/decode-ways/
	 * 
	 * M[i]: the # of decode ways that s[0..i - 1] has
	 * 
	 * M[i] = M[i - 1] if s[i - 1] is a valid char
	 *        M[i - 1] + M[i - 2]  is s[i - 2, i - 1] are valid two digits
	 *        
	 * 定义数组number，number[i]意味着：字符串s[0..i-1]可以有number[i]种解码方法。
	 * 回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
	 * 但不同的是本题有多种限制：
	 * 第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
	 * 第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。
	 * 1010，生成的number数组为：[1,1,1,1,1]
	 * 10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]
	 */
	public static void test91() {
		String s = "1";
		int rev = task91_numDecodings(s);
		System.out.println("rev = " + rev);
	}
	public static int task91_numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] M = new int[s.length() + 1];
		M[0] = 1; // NOTE!!! Here.

		if (task91_isValid(s.substring(0, 1))) {
			M[1] = 1;
		} else {
			M[1] = 0;
		}
		
		for (int i = 2; i <= s.length(); i++) {
			if (task91_isValid(s.substring(i - 1, i))) {
				// s[i-1, i]
				M[i] += M[i - 1];
			}

			if (task91_isValid(s.substring(i - 2, i))) {
				// s[i - 2, i] 
				M[i] += M[i - 2];
			}
		}

		Debug.printArray(M);
		
		return M[s.length()];
	}

	public static boolean task91_isValid(String s) {
		if (s.charAt(0) == '0') {
			// starting with 0
			return false;
		}
		int val = Integer.parseInt(s);
		return val >= 1 && val <= 26;
	}

}
