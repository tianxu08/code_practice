package mj_microsoft;

import java.util.Deque;
import java.util.LinkedList;

public class OA0610 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	public static void helper(String str, int n, StringBuilder result) {
		if (n == 0) {
			// there is n char to remove from str
			// append everything to result
			result.append(str);
			return ;
		}
		int len = str.length();
		
		if (len <= n) {
			// if there are more chars to remove than string length, append nothing to res
			return ;
		}
		// find the smallest 
		int min_idx = 0;
		for(int i = 1; i<= n; i ++) {
			if (str.charAt(i) < str.charAt(min_idx)) {
				min_idx = i;
			}
		}
		result.append(str.charAt(min_idx));
		String next_str = str.substring(min_idx + 1);
		System.out.println("-------");
		System.out.println(result);
		System.out.println("next_str = " + next_str);
		System.out.println("========");
		helper(next_str, n - min_idx, result);
	}
	
	public static String buildLowestNumber(String s, int n) {
		StringBuilder stb = new StringBuilder();
		helper(s, n, stb);
		return stb.toString();
	}
	
	public static void test() {
		String s = "4325043";
		int n = 3;
		String rev = buildLowestNumber(s, n);
		System.out.println(rev);
		
		String rev2 = buildLowestString2(s, n);
		System.out.println(rev2);
	}
	
	
	public static String buildLowestString2(String str, int n) {
		int len = str.length();
		
		StringBuilder stb = new StringBuilder();
		Deque<Character> dq = new LinkedList<Character>();
		int i = 0;
		// insert the first n+1 into dq
		for(; i <= n; i++) {
			insertInNoDecOrder(dq, str.charAt(i));
		}
		while(i < len) {
			stb.append(dq.pollFirst());
			insertInNoDecOrder(dq, str.charAt(i));
			i ++;
		}
		
		stb.append(dq.pollFirst());
		return stb.toString();
		
		
	}
	
	public static void insertInNoDecOrder(Deque<Character> dq, char ch) {
		if (dq.isEmpty()) {
			dq.offerLast(ch);
		} else {
			while(!dq.isEmpty() && ch < dq.peekLast()) {
				dq.pollLast();
			}
			dq.offerLast(ch);
		}
	}
	
	

}
