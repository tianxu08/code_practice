package sortbynum;

import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import ds.Debug;
import ds.ListNode;
import ds.MyHashMap.Entry;
import mj_linkedin.MyIntervalLinkedIn4.ITNode;

public class Task1_50 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test0();
//		test4();
		// test7_reverse_int();
//		test9();
//		test10();
//		test10_3();
		 test12();
//		 test17();
//		 test17_1();
//		test17_2();
		// test24();
		// test25();
		// test28();
//		test28_2();
		// test43();
//		test13();
//		test30();
	}
	
	public static void test0() {
		int a = 40;
		for (int i = 1; i < 60; i++) {
			int b = (int) (a);
			
			System.out.print(b + " --- ");
			if (i % 12 == 0) {
				System.out.println();
			}
			b = (int) (a * 1.3);
			a = b;
		}
	}

	/*
	 * task1_ Add Digits 3 + 8 = 11 1 + 1 = 2
	 * 
	 * 189 1 + 8 + 9 = 18 1 + 8 = 9
	 * 
	 * 
	 * how to do without loop/recursion on O(1) ???
	 */
	public static int task1_addDigit(int num) {
		return task1_helper(num);
	}

	public static int task1_helper(int num) {
		if (num < 10) {
			return num;
		}
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return task1_helper(sum);
	}

	/*
	 * taks2 two sum
	 */
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}

		int index1 = -1;
		int index2 = -1;

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				index1 = i + 1;
				index2 = map.get(target - nums[i]) + 1;
			}
		}

		int minIndex = Math.min(index1, index2);
		int maxIndex = Math.max(index1, index2);

		int[] rev = { minIndex, maxIndex };

		return rev;

	}
	
	/*
	 * 4 median of two sorted arrays
	 */
	public static void test4() {
		int[] a = {1,3};
		int[] b = {2,4,5};
		
		double rev = median(a, b);
		System.out.println("rev = " + rev);
	}
	
	public static double median(int[] a, int[] b) {
		int aLen = a.length, bLen = b.length;
		if ((aLen + bLen) % 2 == 1) {
			return kth(a, b, (aLen + bLen)/2 + 1);
		} else {
			return (double) (kth(a, b, (aLen + bLen)/2) + kth(a, b, (aLen + bLen)/2 + 1))/2.0;
		}
	}
	public static int kth(int[] a, int[] b, int k) {
		// a, b is not null, k <= a.length + b.length, k >=1, a, b not all empty
		return kth(a, 0, b, 0, k);
	}
	
	private static int kth(int[] a, int aleft, int[] b, int bleft, int k) {
		
		if (aleft >= a.length) {
			return b[bleft + k - 1];
		}
		if (bleft >= b.length) {
			return a[aleft + k - 1];
		}
		if (k == 1) {
			return Math.min(a[aleft], b[bleft]);
		}
		int amid = aleft + k / 2 - 1;
		int bmid = bleft + k / 2 - 1;
		int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
		int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
		if (aval <= bval) {
			return kth(a, amid + 1, b, bleft, k - k / 2);
		} else {
			return kth(a, aleft, b, bmid + 1, k - k / 2);
		}
	}
	

	/*
	 * task7 reverse integer
	 */
	public static void test7() {
		int x = 1534236469;

		System.out.println(test7_reverse(x));
		System.out.println(Integer.MAX_VALUE);
	}

	// 123 -> 321
	// -123 -> -321
	// !!! overflow.

	// 12345 -> 54321
	/*
	 * For the purpose of this problem, assume that your function returns 0 when
	 * the reversed integer overflows.
	 * 
	 * 注意越界的问题.
	 */
	public static int test7_reverse(int x) {
		boolean isNeg = false;
		if (x < 0) {
			isNeg = true;
			x = -x;
		}
		int result = 0;
		int max_diff = Integer.MAX_VALUE / 10; // !!!
		while (x > 0) {
			if (result > max_diff) {
				return 0;
			}
			result = result * 10 + x % 10;
			x /= 10;
		}
		return isNeg ? -result : result;
	}

	/*
	 * task3 Longest Substring Without Repeating Characters two pointers +
	 * hashMap
	 */
	public static int task3_lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int max = 0; // the global max length of substring without repeating
						// chars
		HashSet<Character> set = new HashSet<Character>();

		int slow = 0, fast = 0;
		while (fast < s.length()) {
			if (set.contains(s.charAt(fast))) {
				// already have duplicate, move slow, and delete s.charAt(slow)
				// from set, until met the s.charAt(fast)
				while (slow < fast && set.contains(s.charAt(fast))) {
					set.remove(s.charAt(slow));
					slow++;
				}
			}
			if (max < fast - slow + 1) {
				max = fast - slow + 1;
			}
			set.add(s.charAt(fast));
			fast++;
		}

		return max;
	}

	/*
	 * task8 String to Integer (atoi)
	 */
	public static void test8() {

	}

	public static int task8_atoi(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		str = str.trim();
		int start = 0, sign = 1, length = str.length();
		if (str.charAt(start) == '+') {
			sign = 1;
			start++;
		} else if (str.charAt(start) == '-') {
			sign = -1;
			start++;
		}
		long sum = 0;
		for (int i = start; i < length; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return (int) sum * sign;
			}
			sum = sum * 10 + (str.charAt(i) - '0');
			// !!! pay attention to overflow
			if (sign > 0 && sum > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			if (sign < 0 && sign * sum < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		return (int) sum * sign;
	}

	/*
	 * summary: String.trim(); It returns a copy of this string with leading and
	 * trailing white space removed, or this string if it has no leading or
	 * trailing white space.
	 */

	/*
	 * task9 Palindrome Number
	 * 
	 */
	
	public static void test9(){
		int x = 1213121;
		boolean rev = task9_isPalindrome(x);
		System.out.println("rev= " + rev);
	}
	public static boolean task9_isPalindrome(int x) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (x < 0) {
			return false;
		}

		int div = 1;
		// get how many digit
		while (x / div >= 10) {
			div *= 10;
		}
		System.out.println("div = " + div);
		while (x > 0) {
			int right = x % 10;
			int left = x / div;
			if (left != right) {
				return false;
			}
			// update x
			x = (x % div) / 10;
			// div = div/100, since we remove the first and last digits
			div /= 100;
		}
		return true;
	}

	/*
	 * task10 
	 * regular expression matching '.' and '*' boolean isMatch(String s, String p) 
	 * 
	 * base case: if p.length() == 0 return s.length() == 0 
	 * special case: p.length() == 1 
	 *  (1) if s.length() < 1 false 
	 *  (2) else if p[0] != s[0] && p[0] != '.' false 
	 *  (3) else return isMatch(s.substring(1), p.substring(1))
	 * 
	 * case1: 
	 * when the second char in p is NOT '*'. 
	 * p[1] != '*' 
	 * (1) if s.length() < 1, false 
	 * (2) else if p[0] != s[0] && p[0] != '.', false 
	 * (3) else return isMatch(s.substring(1), p.substring(1))
	 * 
	 * case2: when the second char in p is '*', which is p[1] == '*' 
	 * case2.1: a char and '*' can stand for 0 element, return isMatch(s, p.substring(2)); 
	 * case2.2: a char and '*' can stand for 1 or more element, so try every substring
	 * int i = 0; 
	 * while( i < s.length() && (s.charAt(i) == p.charAt(0) || p[0]== '.') 
	 * { 
	 * 		if (isMatch(s.substring(i + 1, p.substring(2)))) 
	 * 		{ 
	 * 			return true;
	 * 		}
	 * 	    i ++; 
	 * }
	 * 
	 * return false;
	 */

	public static boolean task10_isMatch(String s, String p) {
		// base case
		if (p.length() == 0) {
			return s.length() == 0;
		}

		// special case, p.length() == 1
		if (p.length() == 1) {
			if (s.length() < 1) {
				return false;
			} else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
				return false;
			} else {
				return task10_isMatch(s.substring(1), p.substring(1));
			}
		}

		// case1: p[1] is NOT '*'
		if (p.charAt(1) != '*') {
			if (s.length() < 1) {
				return false;
			} else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
				return false;
			} else {
				return task10_isMatch(s.substring(1), p.substring(1));
			}
		} else {
			// case2: p[1] is '*'
			// case2.1: a char and '*' in p match 0 char in s
			if (task10_isMatch(s, p.substring(2))) {
				return true;
			}
			
			// case2.2: a char and '*' in p match 1 or more chars in s
			// try every situation.
			int i = 0;
			while (i < s.length() && 
				   (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if (task10_isMatch(s.substring(i + 1), p.substring(2))) {
					return true;
				}
				i++;
			}
			return false;
		}
	}

	
	public static void test10() {
		String s = "aaa";
		String p = "aa*";
		boolean rev = task10_isMatch_1(s, p);
		System.out.println("rev = " + rev);
		
		boolean rev2 = task10_isMatch_dp2(s, p);
		System.out.println("Rev2 = " + rev2);
	}
	
	public static boolean task10_isMatch_1(String s, String p) {
		return helper(s, p, 0, 0);
	}

	private static boolean helper(String s, String p, int i, int j) {
		if (j == p.length())
			return i == s.length();
		
		if(j == p.length() - 1) {
			if (i == s.length()) {
				return false;
			} else if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') {
				return false;
			} else {
				return helper(s, p,  i + 1, j + 1);
			}
		}
		
		if (p.charAt(j + 1) != '*') {
			if (i == s.length()) {
				return false;
			} else if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') {
				return false;
			} else {
				return helper(s, p,  i + 1, j + 1);
			}
		} else {
			// '*' matches 1 or more p[j]
			while (i < s.length()
					&& (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j))) {
				if (helper(s, p, i, j + 2))
					return true;
				i++;
			}
			// '*' matches 0 p[j]
			return helper(s, p, i, j + 2);
		}	
	}
	
	/**
     * f[i][j]: if s[0..i-1] matches p[0..j-1]
     * if p[j - 1] != '*'
     *      f[i][j] = f[i - 1][j - 1] && s[i - 1] == p[j - 1]
     * if p[j - 1] == '*', denote p[j - 2] with x
     *      f[i][j] is true iff any of the following is true
     *      1) "x*" repeats 0 time and matches empty: f[i][j - 2]
     *      2) "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
     * '.' matches any single character
     */
	
	public static void test10_3() {
		String s = "aabd";
		String p = ".*.d";
		boolean rev = task10_isMatch_dp2(s, p);
		System.out.println("rev = " + rev);
	}
	
	/**
	 * 
	 * @param s
	 * @param p
	 * @return
	 * Time: O(n^2)
	 * 
	 * dp[i][j]   whether s[0..i - 1] and p[0..j - 1] matches
	 * 
	 * 
	 * dp[i][j] = dp[i - 1][j - 1] if p[j - 1] != '*' && (s[i - 1] == p[j - 1] || p[j - 1] == '.')
	 * 
	 * dp[i][j] = dp[i][j - 2] if p[j - 1] == '*' and the '*' matches 0 char
	 * 
	 * dp[i][j] = dp[i - 1][j] && (s[i - 1] == p[j -2] || p[j - 2] == '.') the '*' matches at least 1 times
	 * 
	 */
	public static boolean task10_isMatch_dp2(String s, String p) {
		
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		// dp[i][0] is always false
		for(int i = 1; i <= s.length(); i++) {
			dp[i][0] = false;
		}
		// p[0.., j - 3, j - 2, j - 1] matches empty iff p[j - 1] is '*' and p[0..j - 3] is empty
		for(int j = 1; j <= p.length(); j++) {
			dp[0][j] =  p.charAt(j - 1) == '*' && (j > 1 && dp[0][j - 2]); 
		}
		
//		Debug.printMatrix(dp);
		
		for(int i = 1; i <= s.length(); i++) {
			for(int j = 1; j <= p.length(); j ++) {
				
				if (p.charAt(j - 1) != '*') {
					// the s[i - 1] == p[j - 1] or p[j - 1] is '.'
					dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
				} else {
					// p[j - 1] == '*'
					boolean result = false;
					// case 1
					// x* repeats 0  time and matches empty: f[i][j - 2]
					result |= j > 1 && dp[i][j - 2];
					// case 2
					// "x*" repeats >= 1 times and matches "x*x": s[i - 1] == x && f[i - 1][j]
					result |= (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j];
					dp[i][j] = result;
				}
			}
		}
		
//		System.out.println("---------");
//		Debug.printMatrix(dp);
		return dp[s.length()][p.length()];
	}
	

	/*
	 * task11 container with most water
	 */
	public static void test11() {

	}

	public static int task11_maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int max = 0;
		while (left < right) {
			int curMin = Math.min(height[left], height[right]);

			max = Math.max(max, (right - left) * curMin);

			if (curMin == height[left]) {
				left++;
			} else {
				right--;
			}

		}

		return max;
	}

	
	/*
	 * task12 Integer to Roman
	 * 
	 * summary: background information. 
	 * 1000 M 500 D 100 C 50 L 10 X 5 V 1 I
	 * 
	 * 60 LX 40 XL 
	 * L - X = 50 - 10 = 40 比较方便单独放出来。
	 * 
	 * 所以HashMap 
	 * int[] radix = {   1000, 900,  500, 400,  100,  90,  50,   40, 10,   9,    5,   4,    1}; 
	 * String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL","X", "IX", "V", "IV", "I" };
	 */
	public static void test12() {
		int num = 60;
		String romanStr = task12_intToRoman(num);
		System.out.println(romanStr);
	}

	public static String task12_intToRoman(int num) {
		int[] radix = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbol = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X","IX", "V", "IV", "I" };

		StringBuilder rev = new StringBuilder();
		for (int i = 0; i < symbol.length && num > 0; i++) {
			// get the count of radix[i]
			int count = num / radix[i];
			num %= radix[i];
			// append count of symbol[i]s
			for (; count > 0; count--) {
				rev.append(symbol[i]);
			}
		}
		return rev.toString();
	}

	/*
	 * task13 Roman to Integer
	 * 
	 * MD  1500
	 * 
	 * IV 4 5 - 1
	 */
	public static void test13() {
		String s = "DCXXI";
		int rev = task13_romanToInt(s);
		System.out.println("rev = " + rev) ;
	}
	
	public static int task13_romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('M', 1000);
		map.put('D', 500);
		map.put('C', 100);
		map.put('L', 50);
		map.put('X', 10);
		map.put('V', 5);
		map.put('I', 1);

		char[] c = s.toCharArray();
		int result = 0;
		for (int i = 0; i < c.length - 1; i++) {
			// since we need to query c[i + 1], the i is [0..c.length - 2]
			// if map.get(c[i]) >= map.get(c[i + 1]) add
			// otherwise, minus
			result += map.get(c[i]) * (map.get(c[i]) >= map.get(c[i + 1]) ? 1 : -1);
		}
		result += map.get(c[c.length - 1]);

		return result;
	}

	/*
	 * task14 Longest Common Prefix
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int index = 0;
		while (index < strs[0].length()) {
			char c = strs[0].charAt(index);
			// scan the remaining strings in strs, to make sure that every
			// string in charAt(index) is same with c
			for (int i = 1; i < strs.length; i++) {
				if (index >= strs[i].length() || strs[i].charAt(index) != c) {
					// index out of bound or strs[i].charAt(index) != c
					return strs[0].substring(0, index);
				}
			}
			index++;
		}

		return strs[0].substring(0, index);
	}

	/*
	 * task15 3 Sum
	 */

	/*
	 * task16 3 Sum Closest
	 */

	/*
	 * task17 Letter Combinations of a Phone Number
	 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
	 */
	public static void test17() {
		String digits = "222";
		List<String> result = task17_letterCombinations(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		// build the map
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		task17_helper(digits, stb, 0, map, result);
		return result;

	}

	public static void task17_helper(String digits, StringBuilder stb,
			int index, HashMap<Integer, String> map, List<String> result) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);
		for (int i = 0; i < curStr.length(); i++) {
			stb.append(curStr.charAt(i));
			task17_helper(digits, stb, index + 1, map, result);
			stb.deleteCharAt(stb.length() - 1);
		}
	}

	/*
	 * follow up iterative
	 */
	public static void test17_2() {
		String str = "23";
		List<String> rev1 = letterCombinations(str);
		System.out.println(rev1);
		System.out.println("----------------");
		List<String> rev2 = task17_letterCombinations(str);
		System.out.println(rev2);
	}

	public static List<String> letterCombinations(String digits) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");
		LinkedList<String> list = new LinkedList<String>();
		list.add("");
		for (int i = 0; i < digits.length(); i++) {
			int num = digits.charAt(i) - '0';
			int size = list.size();
			for (int k = 0; k < size; k++) {
				String tmp = list.pop();
				for (int j = 0; j < map.get(num).length(); j++)
					list.add(tmp + map.get(num).charAt(j));
			}
		}
		List<String> rec = new LinkedList<String>();
		rec.addAll(list);
		return rec;
	}

	// follow up
	// additional requirement: no duplicate set of characters for the same
	// number.
	// HashMap<Integer, HashSet<Character>> visited 用这个来去重复
	public static void test17_1() {
		String digits = "222";
		List<String> result = task17_letterCombinations2(digits);
		System.out.println(result);
	}

	public static List<String> task17_letterCombinations2(String digits) {
		List<String> result = new ArrayList<String>();
		if (digits == null || digits.length() == 0) {
			return result;
		}

		StringBuilder stb = new StringBuilder();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		map.put(0, " ");

		HashMap<Integer, HashSet<Character>> visited = new HashMap<Integer, HashSet<Character>>();

		task17_helper2(digits, stb, 0, map, result, visited);
		return result;

	}
	/**
	 * 
	 * @param digits
	 * @param stb
	 * @param index
	 * @param map
	 * @param result
	 * @param visited: Map<>
	 */
	public static void task17_helper2(String digits, StringBuilder stb,
			int index, HashMap<Integer, String> map, List<String> result,
			HashMap<Integer, HashSet<Character>> visited) {
		if (index == digits.length()) {
			// get a reasonable result
			String oneresult = stb.toString();
			result.add(oneresult);
			return;
		}
		Integer curVal = (int) digits.charAt(index) - '0';
		String curStr = map.get(curVal);

		for (int i = 0; i < curStr.length(); i++) {
			char curCh = curStr.charAt(i);

			if (visited.containsKey(curVal)
					&& visited.get(curVal).contains(curCh)) {
				continue;
			}
			stb.append(curCh);
			if (!visited.containsKey(curVal)) {
				visited.put(curVal, new HashSet<Character>());
			}
			visited.get(curVal).add(curCh);
			task17_helper2(digits, stb, index + 1, map, result, visited);

			stb.deleteCharAt(stb.length() - 1);
			visited.get(curVal).remove(curCh);
		}
	}

	/*
	 * task18 4 Sum
	 */

	/*
	 * task19 remove Nth Node From End of list
	 */

	/*
	 * task20 Valid Parentheses use stack
	 */
	public static boolean task20_isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (Character c : s.toCharArray()) {
			if ("({[".contains(String.valueOf(c))) {
				stack.push(c);
			} else {
				if (!stack.isEmpty() && is_valid(stack.peek(), c)) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	private static boolean is_valid(char c1, char c2) {
		return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
				|| (c1 == '[' && c2 == ']');
	}

	/*
	 * task21 Merge Two Sorted List
	 */

	/*
	 * task22 Generate Parentheses
	 */

	/*
	 * task23 Merge K sorted List
	 */
	

	/*
	 * task24 Swap Node in Pairs Reverse LinkedList in 2 Group
	 * 
	 * dummy -> 1 -> 2 -> 3 -> 4 prev cur next nnext prev.next = next dummy ->2
	 */
	public static void test24() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;

		printList(n1);

		ListNode nHead = task24_ReverseIn2Group(n1);
		printList(nHead);

	}

	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static ListNode task24_ReverseIn2Group(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode cur = head;
		while (cur != null && cur.next != null) {
			ListNode nnext = cur.next.next;
			ListNode next = cur.next;
			// !!! cut cur.next Remember
			next.next = cur;
			prev.next = next;
			cur.next = nnext;
			prev = cur;
			cur = cur.next;
		}

		return dummy.next;
	}

	public static ListNode task24_ReverseIn2Group_2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode next = head.next;
		head.next = task24_ReverseIn2Group_2(head.next.next);
		next.next = head;
		return next;
	}

	/*
	 * task25 Reverse Node in k-Group
	 */
	public static void test25() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		ListNode n8 = new ListNode(8);
		ListNode n9 = new ListNode(9);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;

		printList(n1);
		int k = 3;
		// ListNode nHead1 = task25_reverseKGroup(n1, k);
		ListNode nHead2 = task25_reverseKGroup3_Rec(n1, k);
		printList(nHead2);
	}

	public static ListNode task25_reverseKGroup(ListNode head, int k) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (head == null || head.next == null || k < 2) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		for (ListNode prev = dummy, cur = head; cur != null; cur = prev.next) {
			for (int i = 1; i < k && cur != null; i++) {
				// !! i starts from 1
				// either i == k or cur == null can stop the loop
				cur = cur.next;
			}
			// for list length < k
			if (cur == null) {
				break;
			}
			prev = reverse(prev, prev.next, cur);
		}
		return dummy.next;
	}

	public static ListNode reverse(ListNode prev, ListNode start, ListNode end) {
		ListNode endNext = end.next;
		ListNode p = prev, cur = start;
		while (cur != endNext) {
			ListNode curNext = cur.next;
			cur.next = p;
			p = cur;
			cur = curNext;
		}
		prev.next = end;
		prev = start;
		prev.next = endNext;
		return prev;
	}

	public static ListNode task25_reverseKGroup3_Rec(ListNode head, int k) {
		return task25_reverseKGroup3_RecHelper(head, k, null);
	}

	public static ListNode task25_reverseKGroup3_RecHelper(ListNode head,
			int k, ListNode prev) {
		if (head == null) {
			return head;
		}
		ListNode cur = head;
		for (int i = 1; i < k && cur != null; i++) {
			cur = cur.next;
		}
		if (cur == null) {
			return head;
		}
		ListNode endNext = cur.next;
		ListNode subReturn = task25_reverseKGroup3_RecHelper(endNext, k, head);

		ListNode start = head;
		ListNode end = cur;
		// reuse the cur
		cur = start;

		while (cur != null && cur != endNext) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		start.next = subReturn;
		return end;
	}

	// jiuzhang
	public ListNode task25_reverseKGroup2(ListNode head, int k) {
		if (head == null || k <= 1) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		head = dummy;
		while (head.next != null) {
			head = reverseNextK(head, k);
		}

		return dummy.next;
	}

	// reverse head->n1->..->nk->next..
	// to head->nk->..->n1->next..
	// return n1
	private ListNode reverseNextK(ListNode head, int k) {
		// check there is enought nodes to reverse
		ListNode next = head; // next is not null
		for (int i = 0; i < k; i++) {
			if (next.next == null) {
				return next;
			}
			next = next.next;
		}

		// reverse
		ListNode n1 = head.next;
		ListNode prev = head, curt = n1;
		for (int i = 0; i < k; i++) {
			ListNode temp = curt.next;
			curt.next = prev;
			prev = curt;
			curt = temp;
		}

		n1.next = curt;
		head.next = prev;
		return n1;
	}

	/*
	 * task26 Remove Duplicate from Sorted Array
	 */

	/*
	 * task27 Remove element
	 */

	/*
	 * task28 strStr() finish KMP
	 * https://www.topcoder.com/community/data-science
	 * /data-science-tutorials/introduction-to-string-searching-algorithms/
	 */
	public static void test28() {
		char[] pattern = "ABABAC".toCharArray();
		char[] text = "ABABABABABACABAB".toCharArray();
		int rev = KMP(text, pattern);
		System.out.println("rev = " + rev);
	}

	public static int[] build_failure_function(char[] pattern) {
		int m = pattern.length;
		int[] F = new int[m + 1];
		F[0] = 0;
		F[1] = 0; // always true

		for (int i = 2; i <= m; i++) {
			// j is the index of the largest next partitial match
			// (the largest suffix/prefix) of the string under index i -1
			int j = F[i - 1];
			for (;;) {
				// check to see if the last character of string i - pattern[i -1]
				// "expands" the current "candidate"
				// best partitial match - the prefix under index j
				if (pattern[j] == pattern[i - 1]) {
					F[i] = j + 1;
					break;
				} else if (j == 0) {
					// if we cannot "expand" even the empty string
					F[i] = 0;
					break;
				} else {
					// else go to the next best "candidate" partial match
					j = F[j];
				}

			}
		}
		System.out.println(Arrays.toString(F));
		return F;
	}

	public static int KMP(char[] text, char[] pattern) {
		int m = pattern.length;
		int n = text.length;
		int[] F = build_failure_function(pattern);
		int i = 0; // the initial state of the automation is the empty string
		int j = 0; // the first character of the text
		for (;;) {
			if (j == n) {
				// we reach the end of text
				break;
			}

			// if the current character of the text "expands" the current match
			if (text[j] == pattern[i]) {
				i++; // change the state of the automaton
				j++; // get the next character from the text
				if (i == m) {
					// match found
					return i;
				}
			} else if (i > 0) {
				i = F[i];
				// if the current state is NOT 0( we haven't reached the empty
				// string yet)
				// we try to "expand" the next best(largest) match
			} else {
				// if we reach the empty string and failed to "expand" even it
				// we go to the next character from the text, the state of the
				// automation remains 0
				j++;
			}
		}
		return -1;
	}
	
	
	/*
	 * This function build the kmp table. 
	 * T[i] is the length of the longest prefix which is also the suffix in substring p[0..i - 1]
	 *      the next position in p to match when we meet a mismatch. 
	 * T[0] = -1   ''
	 * T[1] = 0     A
	 * 
	 */
	
	
	public static int[] task28_KMPtable(String p) {
		if (p == null || p.length() == 0) {
			return null;
		}
		int len = p.length();
		int[] T = new int[len];
		if (len == 1) {
			T[0] = -1;
			return T;
		}
		T[0] = -1;
		T[1] = 0;
		int pos = 2;  // the position we are calculate for T[pos]
		int next = 0;  // the next position of char of the current substring candidate of p
		
		while(pos < len) {
			if (p.charAt(pos - 1) == p.charAt(next)) {
				T[pos] = next + 1;
				next ++;
				pos ++;
			} else {
				if (next > 0) {
					T[pos] = 0;
					next = T[next];
				} else {
					// next == 0
					T[pos] = 0;
					pos ++;
				}
			}
		}
		return T;
	}
	public static void test28_2() {
		String s = "ababababaca";
		String p = "ababac";
		int rev = task28_KMP2(s, p);
		System.out.println(rev);
	}
	
	public static int task28_KMP2(String s, String p) {
		if (s == null || p == null || s.length() < p.length()) {
			return -1;
		}
		if (s.length() == 0 && p.length() == 0) {
			return 0;
		}
		if (s.length() == 0 || p.length() == 0) {
			return 0;
		}
		
		int[] T = task28_KMPtable(p);
		for(int i = 0; i < s.length(); i++) {
			System.out.println(i + "  : " + s.charAt(i));
		}
		System.out.println(Arrays.toString(T));
		int s_start = 0;  // the start position in s to match p
		int i = 0;        // the current index in p
		while(s_start + i < s.length() && i < p.length()) {
			// cur_pos in s is s_start + i 
			if (s.charAt(s_start + i) == p.charAt(i)) {
				// if i is in the last position in p
				if (i == p.length() - 1) {
					return s_start;
				}
				// go to next position
				i ++;
			} else {
				// NOT match
				if (T[i] != -1) {
					// we can backtrack
					s_start = s_start + i - T[i];  // the new start in s after backtrack
					i = T[i];  // backtrack to new position
				} else{
					// T[i] is -1
					i = 0;
					s_start = s_start + 1;
				}
			}
		}
		return -1;
	}

	/*
	 * task29 !!!! Divide two integers
	 */
	public static int task29_divide(int dividend, int divisor) {
		if (divisor == 0) {
			return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}

		if (dividend == 0) {
			return 0;
		}

		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}

		boolean isNegative = (dividend < 0 && divisor > 0)
				|| (dividend > 0 && divisor < 0);

		long a = Math.abs((long) dividend);
		long b = Math.abs((long) divisor);
		int result = 0;
		while (a >= b) {
			int shift = 0;
			while (a >= (b << shift)) {
				shift++;
			}
			a -= b << (shift - 1);
			result += 1 << (shift - 1);
		}
		return isNegative ? -result : result;

	}
	
	

	/*
	 * task30
	 * 
	 * Substring with Contenation of All words Slide window
	 * input: s
	 * words: a list of words , with same length
	 * 
	 */	
	public static void test30() {
		String s = "wordgoodgoodgoodbestword";
		String[] words = {"word","good", "best", "good"};
		
		System.out.println(Arrays.toString(words));
		
		List<Integer> result = task30_findSubstring3(s, words);
		System.out.println("===>>>> ");
		System.out.println(result);
		
		System.out.println("~~~~~~~~~");
		
//		List<Integer> res2 = task30_findSubstring2(s, words);
//		System.out.println(res2);
		
	}
	public static List<Integer> task30_findSubstring3(String s, String[] words) {
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return new ArrayList<>();
		}
		int wNum = words.length;
		int wLen = words[0].length();
		
		Map<String, Integer> map1 = new HashMap<>();
		
		List<Integer> result = new ArrayList<>();
		// fill in map1 
		for (String str : words) {
			map1.put(str, !map1.containsKey(str) ? 1 : map1.get(str) + 1);
		}
			
		System.out.println("lastIdx = " + (s.length() - wLen * wNum));
		for (int i = 0; i <= s.length() - wLen * wNum; i++) {
			Map<String, Integer> map2  = new HashMap<>();
			int n = 0;
			for (; n < wNum; n++){
				int k = i + n * wLen;
				String curW = s.substring(k, k + wLen);
				
				if (!map1.containsKey(curW)) {
					break;
				}
				
				map2.put(curW, map2.containsKey(curW) ? map2.get(curW) + 1 : 1);
				
				if (map2.get(curW) > map1.get(curW)) {
					break;
				}	
			}
	
			if (n == wNum) {
				// found
				result.add(i);
			}
		}
		
		return result;
	}

	/*
	 * task31 Next Permutation
	 */

	/*
	 * task32 Longest Valid Parentheses use stack. implement
	 */

	/*
	 * 33 Search In Rotated Sorted Array easy
	 */

	/*
	 * task34 Search for a range follow up question for binary search
	 */

	/*
	 * 35 Search Insert Position follow up question for binary search
	 */

	/*
	 * task36 Valid Sudoku
	 * 
	 * use hashSet.
	 */
	public boolean isValidSudoku(char[][] board) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		HashSet<Character> myHash = new HashSet<Character>();
		int rowLen = board.length;
		int colLen = board[0].length;
		// for every row
		for (int i = 0; i < rowLen; i++) {
			myHash.clear();
			for (int j = 0; j < colLen; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				if (checkValid(board[i][j])) {
					if (myHash.contains(board[i][j])) {
						return false;
					} else {
						myHash.add(board[i][j]);
					}
				} else {
					return false;
				}
			}

		}
		myHash.clear();
		// for every column
		for (int j = 0; j < colLen; j++) {
			myHash.clear();
			for (int i = 0; i < rowLen; i++) {
				if (board[i][j] == '.') {
					continue;
				}
				if (checkValid(board[i][j])) {
					if (myHash.contains(board[i][j])) {
						return false;
					} else {
						myHash.add(board[i][j]);
					}
				} else {
					return false;
				}
			}

		}
		myHash.clear();

		// check every 3*3 box
		for (int i = 0; i < rowLen; i += 3) {
			for (int j = 0; j < colLen; j += 3) {
				myHash.clear();
				for (int si = i; si < i + 3; si++) {
					for (int sj = j; sj < j + 3; sj++) {
						if (board[si][sj] == '.') {
							continue;
						}
						if (checkValid(board[si][sj])) {
							if (myHash.contains(board[si][sj])) {
								return false;
							} else {
								myHash.add(board[si][sj]);
							}
						} else {
							return false;
						}
					}
				}

			}
		}
		return true;
	}

	public boolean checkValid(char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		}
		return false;
	}

	/*
	 * 37 Sudoku Solver backtracking
	 */
	public void task37_solveSudoku(char[][] board) {
		solveSudoku2(board);
	}

	public boolean solveSudoku2(char[][] board) {
		int rowLen = board.length;
		int colLen = board[0].length;

		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				if (board[i][j] == '.') {
					for (int k = 1; k <= 9; k++) {
						board[i][j] = (char) ('0' + k);
						if (check(board, i, j) && solveSudoku2(board)) {
							return true;
						}
						board[i][j] = '.';
					}

					return false;
				}
			}
		}
		return true;
	}

	public boolean check(char[][] board, int rowIndex, int colIndex) {
		int rLen = board.length;
		int lLen = board[0].length;
		// check column
		for (int i = 0; i < rLen; i++) {
			if (i != rowIndex
					&& board[i][colIndex] == board[rowIndex][colIndex]) {
				return false;
			}
		}

		// check row
		for (int j = 0; j < lLen; j++) {
			if (j != colIndex
					&& board[rowIndex][j] == board[rowIndex][colIndex]) {
				return false;
			}
		}

		// check 3*3
		int startRow = 3 * (rowIndex / 3);
		int startCol = 3 * (colIndex / 3);
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (i != rowIndex && j != colIndex
						&& board[i][j] == board[rowIndex][colIndex]) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 38 Count and Say !!! forget, implement
	 */

	/*
	 * 39 Combination Sum !!! Implement
	 */
	public static List<List<Integer>> combinationSum(int[] candidates,
			int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		dfs(candidates, target, 0, result, cur);
		return result;
	}

	public static void dfs(int[] num, int gap, int start,
			List<List<Integer>> result, List<Integer> cur) {
		if (gap == 0) {
			// we got a reasonable solution
			ArrayList<Integer> copy = new ArrayList<Integer>(cur);
			result.add(copy);
			return;
		}
		for (int i = start; i < num.length; i++) {
			if (gap < num[i]) {
				return;
			}
			cur.add(num[i]);
			dfs(num, gap - num[i], i, result, cur);
			cur.remove(cur.size() - 1);
		}
	}

	/*
	 * 40 Combination Sum2 !!! Implement
	 */

	/*
	 * 41 First Missing Positive easy
	 */

	/*
	 * 42 Trapping Rain Water
	 */

	/*
	 * 43 Multiply String
	 */
	public static void test43() {
		String num1 = "123";
		String num2 = "45";
		String rev = task43_multiply(num1, num2);

		System.out.println(rev);
		int rev2 = 123 * 45;
		System.out.println(rev2);

		String rev3 = task43_multiply2(num1, num2);
		System.out.println(rev3);
	}

	public static String task43_multiply(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		int[] result = new int[len1 + len2];

		for (int i = len1 - 1; i >= 0; i--) {
			for (int j = len2 - 1; j >= 0; j--) {
				int multiply = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int pos1 = i + j, pos2 = i + j + 1;
				int sum = multiply + result[pos2];

				result[pos1] = result[pos1] + sum / 10;
				result[pos2] = sum % 10;
			}
		}

		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			if (!(stb.length() == 0 && result[i] == 0)) {
				stb.append(result[i]);
			}
		}
		return stb.length() == 0 ? "0" : stb.toString();
	}

	public static String task43_multiply2(String s1, String s2) {
		String r1 = new StringBuilder(s1).reverse().toString();
		String r2 = new StringBuilder(s2).reverse().toString();

		int[] digit = new int[r1.length() + r2.length()];

		for (int i = 0; i < r1.length(); i++) {
			for (int j = 0; j < r2.length(); j++) {
				digit[i + j] += (r1.charAt(i) - '0') * (r2.charAt(j) - '0');
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digit.length; i++) {
			int value = digit[i] % 10;
			int carry = digit[i] / 10;

			if (i + 1 < digit.length) {
				digit[i + 1] += carry;
			}
			sb.append(value);
		}

		// the first digit might be 0. like 012, in this case, we need to delete
		// the first 0
		while (sb.length() > 1 && sb.charAt(sb.length() - 1) == '0') {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.reverse().toString();
	}

	/*
	 * 44 Wildcard Matching
	 */ 
	public static boolean task44_isMatch(String s, String p) {
		if (s == null && p == null) {
			return true;
		}
		int indexP = 0;
		int indexS = 0;

		int preS = 0;
		int preP = 0;

		boolean back = false;

		while (indexS < s.length()) {
			if (indexP < p.length() && 
				(s.charAt(indexS) == p.charAt(indexP) || p.charAt(indexP) == '?')) {
				indexP++;
				indexS++;
			} else if (indexP < p.length() && p.charAt(indexP) == '*') {
				while (indexP < p.length() && p.charAt(indexP) == '*') {
					indexP++;
				}

				// if all the remaining is *, return true;
				if (indexP == p.length()) {
					return true;
				}
				
				back = true;
				preS = indexS;
				preP = indexP;
			} else if (back) {
				++preS;
				indexP = preP;
				indexS = preS;
			} else {
				return false;
			}
		}

		while (indexP < p.length() && p.charAt(indexP) == '*') {
			indexP++;
		}
		if (indexP == p.length() && indexS == s.length()) {
			return true;
		}

		return false;

	}
	
	public static boolean task44_isMatch2(String s, String p) {
		int sLen = s.length();
		int pLen = p.length();
		
		boolean[][] dp = new boolean[sLen + 1][pLen + 1];
		// dp[i][j] whether s[0..i -1] matches p[0..j- 1]
		dp[0][0] = true;
		
		
		for (int j = 1; j <= pLen; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 1];
			}
		}
		
		for (int i = 1; i <= sLen; i++) {
			for (int j = 1; j<= pLen; j++) {
				if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				} else {
					if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
						dp[i][j] = dp[i - 1][j - 1];
					}
				}
			}
		}
		
		return dp[sLen][pLen];
		
	}
	

	/*
	 * 45 Jump Game2
	 */

	/*
	 * 46 Permutations
	 */

	/*
	 * 47 Permutations2
	 */

	/*
	 * 48 Rotate Image easy
	 */

	/*
	 * 49 Group Anagrams Use hashMap<String, List<String>>
	 */

	/*
	 * 50 Pow(x, n) divide and conqure
	 */
	public static double task50_pow(double x, int n) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		// base case
		if (n < 0) {
			return 1.0 / powHelper(x, -n);
		} else {
			return powHelper(x, n);
		}
	}

	public static double powHelper(double x, int n) {
		if (n == 0) {
			return 1.0;
		}
		double half = powHelper(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}

}
