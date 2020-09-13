package lai_online;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.spec.PSource;


public class Class10_Recursion2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
//		tes3();
		
	}
	
	/*
	 * task1
	 * N queens
	 * 
	 * Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.
	 * 
	 * Assumptions
	 * N > 0
	 * Return
	 * A list of ways of putting the N Queens
	 * Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
	 * Example
	 * N = 4, there are two ways of putting 4 queens:
	 * [1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
	 * the Queen on the second row is at y index 3, 
	 * the Queen on the third row is at y index 0 
	 * and the Queen on the fourth row is at y index 2.
	 * 
	 * [2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
	 * the Queen on the second row is at y index 0, 
	 * the Queen on the third row is at y index 3 
	 * and the Queen on the fourth row is at y index 1.
	 * 
	 */
	
	
	
	public static List<List<Integer>> task1_NQueens(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(0, n, cur, result);
		return result;
	}
	
	public static void helper(int index, int n, List<Integer> cur, List<List<Integer>> result) {
		if (index == n) {
			result.add(new ArrayList<Integer>(cur));
			return ;
		}
		for(int i = 0; i < n; i ++) {
			cur.add(i);
			if (isValid(cur)) {
				helper(index + 1, n, cur, result);
			}
			cur.remove(cur.size() - 1); 
		}
	}
	
	// check whether add the last element, the cur List is still valid
	public static boolean isValid(List<Integer> cur) {
		int size = cur.size();
		for(int i = 0; i < size - 1; i ++) {
			int rowIndex = i;
			int colIndex = cur.get(i);
			int targetRowIndex = size - 1;
			int targetColIndex = cur.get(targetRowIndex);
			// not in the same row, same column, same diag
			// since i != size - 1, so cannot be the same row
			// just check same column, same diag and counter-diag
			if (colIndex == targetColIndex || 
					rowIndex + colIndex == targetColIndex + targetRowIndex || 
					rowIndex - colIndex == targetRowIndex - targetColIndex) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * task2 
	 * String Abbreviation Matching
	 * 
	 * 
	 * Word “book” can be abbreviated to 4, b3, b2k, etc. 
	 * Given a string and an abbreviation, return if the string matches the abbreviation.
	 * Assumptions:
	 * The original string only contains alphabetic characters.
	 * Examples:
	 * pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
	 */
	public static void test2() {
		String input = "book";
		String pattern = "b2k";
		
		System.out.println(match(input, pattern));
	}
	
	public static boolean match(String input, String pattern) {
	    // Write your solution here.
		// base case
		if (input.length() == 0 && pattern.length() == 0) {
			return true;
		} else {
			if (input.length() == 0 || pattern.length() == 0) {
				return false;
			}
		}
		
		if (isDigit(pattern.charAt(0))) {
			int i = 0; 
			int num = 0;
			while(i < pattern.length() && isDigit(pattern.charAt(i))) {
				num = num * 10 + pattern.charAt(i) - '0';
				i ++;
			}
			
			if (num > input.length()) {
				// if num is out of bound, return false
				return false;
			} else {
				return match(input.substring(num), pattern.substring(i));
			}
		} else {
			// pattern.charAt(0) is NOT digit
			if (input.charAt(0) != pattern.charAt(0)) {
				return false;
			} else {
				return match(input.substring(1), pattern.substring(1));
			}
		}
		

	}
	
	public static boolean isDigit(char ch) {
		return ch >= '0' && ch <= '9';
	}
	
	
	/*
	 * task3
	 * All Valid Permutations Of Parentheses I
	 * Given N pairs of parentheses “()”, return a list with all the valid permutations.
	 * Assumptions
	 * N >= 0
	 * Examples
	 * N = 1, all valid permutations are ["()"]
	 * N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
	 * N = 0, all valid permutations are [""]
	 */
	public static void tes3() {
		List<String> result = validParentheses(4);
		System.out.println(result);
	}
	
	public static List<String> validParentheses(int n) {
		// write your solution here
		List<String> result = new ArrayList<String>();

		char[] cur = new char[n * 2];
		helper(cur, n, n, 0, result);

		return result;
	}

	public static void helper(char[] cur, int left, int right, int index,
			List<String> result) {
		if (left == 0 && right == 0) {
			// find a solution
			result.add(new String(cur));
			return;
		}

		if (left > 0) {
			cur[index] = '(';
			helper(cur, left - 1, right, index + 1, result);
		}

		if (right > left) {
			cur[index] = ')';
			helper(cur, left, right - 1, index + 1, result);
		}
	}
	
	
	/*
	 * task4
	 * All Valid Permutations Of Parentheses II
	 * 
	 * 
	 * Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.
	 * Assumptions
	 * l, m, n >= 0
	 * Examples
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
	 * 
	 * 
	 * like task3
	 * in addition, when we plan to add an right parentheses,  
	 * we need to use a stack to make sure the parentheses and st.peek() are same type
	 * 
	 *  
	 */
	public static void test4() {
		
	}
	
	public static List<String> validParentheses(int l, int m, int n) {
		// write your solution here
		final char[] PS = {'(',')', '[', ']', '{', '}'};
		int[] remaining = {l, l, m, m, n, n};
		
		// when we add right parentheses, we need to use the stack to make sure they are in the same type
		Deque<Character> stack = new LinkedList<Character>();
		
		StringBuilder cur = new StringBuilder();
		
		List<String> result = new ArrayList<String>();
		
		int targetLen = 2 * l + 2 * m + 2 * n;
		
		task4_helper(remaining, PS, stack, cur, targetLen, result);
		return result;
	}
	
	
	public static void task4_helper(int[] remaining, char[] PS, 
			Deque<Character> stack,StringBuilder cur, int targetLen,List<String> result) {
		if (cur.length() == targetLen) {
			// get a solution
			result.add(cur.toString());
			return ;
		}
		
		for(int i = 0; i < remaining.length; i ++) {
			if (i % 2 == 0) {
				// add left parentheses
				if (remaining[i] > 0) {
					cur.append(PS[i]);
					remaining[i] --;
					stack.offerFirst(PS[i]);
					task4_helper(remaining, PS, stack ,cur, targetLen, result);
					
					remaining[i] ++;
					cur.deleteCharAt(cur.length() - 1);
					stack.pollFirst();
				}
			} else {
				// add right parentheses
				if (!stack.isEmpty() && stack.peekFirst().equals(PS[i - 1])) {
					cur.append(PS[i]);
					remaining[i] --;
					stack.pollFirst();
					task4_helper(remaining, PS, stack, cur, targetLen, result);
					
					stack.offerFirst(PS[i - 1]); // add the previous left parentheses back
					remaining[i] ++;
					cur.deleteCharAt(cur.length() - 1);
				}
			}
		}
	}
	
	
	

}
