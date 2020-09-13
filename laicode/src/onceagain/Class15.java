package onceagain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Class15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3();
	}
	
	/*
	 * task2 Largest Subarray Sum
	 * 
	 */
	public static int task2_LargestSubArraySum(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int sum = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < array.length; i ++) {
			sum += array[i];
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}
	
	public static int[] task2_LargestSubArraySum_2(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int end = -1;
		int start = -1;
		for(int i = 0;i < array.length; i ++) {
			sum += array[i];
			if (sum > max) {
				max = sum;
				end = i;
			}
		}
		
		if (sum <= 0) {
			start = end;
		} else {
			int remain = max;
			start = end;
			while(remain != 0) {
				remain -= array[start];
				start --;
			}
			start ++;
		}
		int[] result = Arrays.copyOfRange(array, start, end + 1);
		return result;
	}
	
	/*
	 * task3 Dictionary Word
	 * 
	 */
	public static boolean task3_canBreak(String word, Set<String> dict) {
		if (word == null || word.length() == 0) {
			return false;
		}
		int n = word.length();
		boolean[] canbreak = new boolean[n + 1];
		// canBreak[i] stands for whether the [0..i - 1] subString can be break
		// canBreak[i] = canBreak[j] && dict contains [j, i]
		canbreak[0] = true;
		for(int i= 1; i <= word.length(); i ++) {
			// word[0.. i - 1]
			for(int j = 0; j < i; j ++) {
				if (canbreak[j] && dict.contains(word.substring(j, i))) {
					canbreak[i] = true;
					break;
				}
			}
		}
		return canbreak[n];
	}
	
	public static void test3() {
		String word = "helloworldhihowisgoing";
		Set<String> dict = new HashSet<String>();
		dict.add("hello");
		dict.add("going");
		dict.add("world");
		dict.add("hi");
		dict.add("how");
		dict.add("is");
		dict.add("what");
		boolean rev = task3_canBreak(word, dict);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * task4 Edit Distance
	 * source:
	 * target:
	 * 
	 * M[i][j] = M[i - 1][j - 1]  if s[i - 1] == t[i -1]
	 *         = min(M[i][j - 1], M[i - 1][j], M[i - 1][j - 1]) + 1 
	 */
	public static int task4_editDistance(String one, String two) {
		int[][] M = new int[one.length() + 1][two.length() + 1];
		
		for(int i = 0; i < one.length(); i ++ )  {
			for(int j= 0; j < two.length(); j ++) {
				if (i == 0) {
					M[i][j] = j;
				} else if (j == 0) {
					M[i][j] = i;
				} else if (one.charAt(i - 1) == two.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1];
				} else {
					int curMin = Math.min(M[i - 1][j], Math.min(M[i][j - 1], M[i - 1][j - 1]));
					M[i][j] = curMin + 1;
				}
			}
		}
		return M[one.length()][two.length()];
	}
	
	
	
	

}
