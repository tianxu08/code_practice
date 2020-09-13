package dp;

import java.util.*;

public class WorldBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		String input = "abcde";
		String[] dict = {
				"abc","ab","cd","de","def"	
		};
		boolean canBreak = canBreak(input, dict);
		System.out.println("canBreak = " + canBreak);
	}
	
	/*
    M[i] stands for the first i characters ending with input.charAt(i - 1) is in dict
    base case: M[0] = true
    induction rule: M[i] = if there exist one j, j>= 0 && j < i, M[j] && input.sub(i - j, i) in the dict
                           is True
  */
	public static boolean canBreak(String input, String[] dict) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return true;
		}
		Set<String> set = new HashSet<String>();
		for (String s : dict) {
			set.add(s);
		}
		System.out.println(set);
		int n = input.length();
		boolean[] M = new boolean[n + 1];
		M[0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				String second = input.substring(j, i);
				
				if (M[j] == true && set.contains(second)) {
					M[i] = true;
					break;
				}
			}
		}
		for (int i = 0; i < M.length; i++) {
			System.out.print(M[i] + " ");
		}
		System.out.println();
		return M[n];
	}

}
