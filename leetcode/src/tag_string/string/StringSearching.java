package string;

import java.util.Arrays;

public class StringSearching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	/*
	 * KMP
	 */
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
				// check to see if the last character of string i - pattern[i -
				// 1]
				// "expands" the current "candidate"
				// best partial match - the prefix under index j
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

}
