package class3;
import java.util.*;

public class Parentheses {

	/*
	 *  task20 Valid Parentheses
	 * http://www.lintcode.com/en/problem/valid-parentheses/#
	 * https://leetcode.com/problems/valid-parentheses/
	 */
	public boolean task20_isValid(String s) {
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

	private boolean is_valid(char c1, char c2) {
		return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}')
				|| (c1 == '[' && c2 == ']');
	}
	
	/*
	 * Generate Parentheses
	 * http://www.lintcode.com/en/problem/generate-parentheses/
	 */
	public List<String> validParentheses(int k) {
		List<String> result = new ArrayList<String>();
		char[] cur = new char[k * 2];
		helper(cur, k, k, 0, result);
		return result;
	}

	private void helper(char[] cur, int left, int right, int index,
			List<String> result) {
		if (left == 0 && right == 0) {
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
	 * follow up
	 * more kind of parentheses
	 * 
	 */
	
	private static final char[] PS = new char[] { '(', ')', '[', ']', '{', '}' };

	public List<String> validParentheses_mode_kind_of_parentheses(int l, int m, int n) {
		// l, m, n >= 0
		int[] remain = new int[] { l, l, m, m, n, n };
		int targetLen = 2 * l + 2 * m + 2 * n;
		StringBuilder cur = new StringBuilder();
		Deque<Character> stack = new LinkedList<Character>();
		List<String> result = new ArrayList<String>();
		helper(cur, stack, remain, targetLen, result);
		return result;
	}

	private void helper(StringBuilder cur, Deque<Character> stack,
			int[] remain, int targetLen, List<String> result) {
		if (cur.length() == targetLen) {
			result.add(cur.toString());
			return;
		}
		for (int i = 0; i < remain.length; i++) {
			if (i % 2 == 0) {
				if (remain[i] > 0) {
					cur.append(PS[i]);
					stack.offerFirst(PS[i]);
					remain[i]--;
					helper(cur, stack, remain, targetLen, result);
					cur.deleteCharAt(cur.length() - 1);
					stack.pollFirst();
					remain[i]++;
				}
			} else {
				if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
					cur.append(PS[i]);
					stack.pollFirst();
					remain[i]--;
					helper(cur, stack, remain, targetLen, result);
					cur.deleteCharAt(cur.length() - 1);
					stack.offerFirst(PS[i - 1]);
					remain[i]++;
				}
			}
		}
	}
	
	
	
	
	
	
	/*
	 * 241 Different Ways to Add Parentheses
	 * this is hard. 
	 * https://leetcode.com/problems/different-ways-to-add-parentheses/
	 * also see leetcode 241
	 */
	
	public static List<Integer> task241_diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<Integer>();
		if (input == null || input.length() == 0) {
			return result;
		}

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != '+' && c != '-' && c != '*') {
				continue;
			}

			List<Integer> part1Result = task241_diffWaysToCompute(input.substring(0, i));
			List<Integer> part2Result = task241_diffWaysToCompute(input.substring(i + 1, input.length()));

			for (Integer m : part1Result) {
				for (Integer n : part2Result) {
					if (c == '+') {
						result.add(m + n);
					} else if (c == '-') {
						result.add(m - n);
					} else if (c == '*') {
						result.add(m * n);
					}
				}
			}
		}

		if (result.size() == 0) {
			result.add(Integer.parseInt(input));
		}

		return result;
	}
	
	
	/*
	 * 301 Remove Invalid Parentheses
	 * Remove the minimum number of invalid parentheses in order to make the input string valid. 
	 * Return all possible results.
	 * Note: The input string may contain letters other than the parentheses ( and ).
	 * Examples:
	 * "()())()" -> ["()()()", "(())()"]
	 * "(a)())()" -> ["(a)()()", "(a())()"]
	 * ")(" -> [""]
	 * 
	 * https://leetcode.com/discuss/72208/easiest-9ms-java-solution
	 * 
	 * Here I share my DFS or backtracking solution. 
	 * Limit max removal rmL and rmR for backtracking boundary. 
	 * Otherwise it will exhaust all possible valid substrings, not shortest ones.
	 * Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
	 * If it's '(', either use it, or remove it.
	 * If it's '(', either use it, or remove it.
	 * Otherwise just append it.
	 * Lastly set StringBuilder to the last decision point.
	 * In each step, make sure:
	 * i does not exceed s.length().
	 * Max removal rmL rmR and num of open parens are non negative.
	 * De-duplicate by adding to a HashSet.
	 * 
	 */
	public List<String> task301_removeInvalidParentheses(String s) {
	    Set<String> res = new HashSet<String>();
	    int rmL = 0, rmR = 0;
	    for(int i = 0; i < s.length(); i++) {
	        if(s.charAt(i) == '(') rmL++;
	        if(s.charAt(i) == ')') {
	            if(rmL != 0) rmL--;
	            else rmR++;
	        }
	    }
	    DFS(res, s, 0, rmL, rmR, 0, new StringBuilder());
	    return new ArrayList<String>(res);  
	}

	public void DFS(Set<String> res, String s, int i, int rmL, int rmR, int open, StringBuilder sb) {
	    if(i == s.length() && rmL == 0 && rmR == 0 && open == 0) {
	        res.add(sb.toString());
	        return;
	    }
	    if(i == s.length() || rmL < 0 || rmR < 0 || open < 0) return;

	    char c = s.charAt(i);
	    int len = sb.length();

	    if(c == '(') {
	        DFS(res, s, i + 1, rmL - 1, rmR, open, sb);
	        DFS(res, s, i + 1, rmL, rmR, open + 1, sb.append(c)); 

	    } else if(c == ')') {
	        DFS(res, s, i + 1, rmL, rmR - 1, open, sb);
	        DFS(res, s, i + 1, rmL, rmR, open - 1, sb.append(c));

	    } else {
	        DFS(res, s, i + 1, rmL, rmR, open, sb.append(c)); 
	    }

	    sb.setLength(len);
	}
	
	/*
	 * 32 Longest Valid Parentheses
	 * Given a string containing just the characters '(' and ')', 
	 * find the length of the longest valid (well-formed) parentheses substring.
	 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
	 * Another example is ")()())", where the longest valid parentheses substring is "()()", 
	 * which has length = 4.
	 * 
	 */
	public int task32_longestValidParentheses(String s) {
	       if (s == null || s.length() <= 1) {
				return 0;
			}
			
			int depth = 0;
			int maxLen = 0;
			int start = -1;
			
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					depth ++;
				} else {
					depth --;
					if (depth < 0) {
						start = i;
						depth = 0;
					} else if (depth == 0) {
						maxLen = Math.max(maxLen, i - start);
					}
				}
			}
			
			depth = 0;
			int end = s.length();
			for (int i = s.length() - 1; i >= 0; i--) {
				if (s.charAt(i) == ')') {
					depth ++;
				} else {
					depth --;
					if (depth < 0) {
						end = i;
						depth = 0;
					} else if (depth == 0) {
						maxLen = Math.max(maxLen, end - i);
					}
				}
			}
			return maxLen;
	    }
	
}
