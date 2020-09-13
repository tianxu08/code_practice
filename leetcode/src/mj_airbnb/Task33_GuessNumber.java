package mj_airbnb;

import java.util.*;

public class Task33_GuessNumber {

	private static String target = "4361";

	public static void main(String args[]) {
		System.out.println(client());
	}

	public static int check(String guess) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (target.charAt(i) == guess.charAt(i)) {
				count++;
			}
		}
		return count;
	}

	private static String client() {
		char[] result = new char[4];
		Arrays.fill(result, '0');
		String base = "1111";
		System.out.println("Server call: " + base);
		int baseResult = check(base);
		System.out.println("=>>> baseResult: " + baseResult);
		if (baseResult == 4) {
			return base;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 6; j++) {
				String newS = replace(base, i, (char) (j + '0'));
				System.out.println("new Server call: " + newS);
				int newResult = check(newS);
				System.out.println("newRes= " + newResult + " : " + "baseRes= " + baseResult);
				if (newResult != baseResult) {
					result[i] = baseResult > newResult ? '1' : (char) (j + '0');
					break;
				}
			}
			if (result[i] == '0') {
				result[i] = '6';
			}
			System.out.println("!!! result: " + Arrays.toString(result) + "==> i = " + i);
		}
		System.out.println("!!! result: " + Arrays.toString(result));
		return new String(result);
	}

	private static String replace(String s, int index, char c) {
		char[] arr = s.toCharArray();
		arr[index] = c;
		return new String(arr);
	}
}
