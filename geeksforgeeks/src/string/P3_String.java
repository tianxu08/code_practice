package string;

public class P3_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	/*
	 * task1
	 * Count words in a given string
	 * http://www.geeksforgeeks.org/count-words-in-a-given-string/
	 * 
	 * The idea is to maintain two states:
	 * IN and OUT
	 * The state OUT indicates that a seperator is seen
	 * The state IN indicates that a word character is seen. 
	 * we increment word count when previous state is OUT and next character is a word character. 
	 */
	
	public static int countWord(String s) {
		int count = 0;
		int OUT = 1;
		int IN = 0;
		int state = OUT;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (cur == ' ' || cur == '\n' || cur == '\t') {
				state = OUT;
			} else {
				if (state == OUT) {
					state = IN;
					count++;
				}
			}
		}
		return count;
	}
	public static void test1() {
		String s = " hello world   \n hi";
		int count = countWord(s);
		System.out.println(count);
	}
	
	/*
	 * task2
	 * String matching where one string contains wildcard characters
	 * http://www.geeksforgeeks.org/wildcard-character-matching/
	 */
	
	/*
	 * task3
	 * Write your own atoi()
	 * http://www.geeksforgeeks.org/write-your-own-atoi/
	 */
	
	/*
	 * task4
	 * Dynamic Programming | Set 29 (Longest Common Substring)
	 * http://www.geeksforgeeks.org/longest-common-substring/
	 */
	
	
	/*
	 * task5
	 * http://www.geeksforgeeks.org/remove-a-and-bc-from-a-given-string/
	 * Remove “b” and “ac” from a given string (Google Interview)
	 */
	
	/*
	 * task6
	 * Dynamic Programming | Set 33 (Find if a string is interleaved of two other strings)
	 * http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings-set-2/
	 */
	
	/*
	 * task7
	 * Find the first non-repeating character from a stream of characters
	 * http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
	 * 
	 * this is a interesting problem. 
	 */
	
	/*
	 * task8
	 * Recursively remove all adjacent duplicates
	 * http://www.geeksforgeeks.org/recursively-remove-adjacent-duplicates-given-string/
	 */
	
	/*
	 * task9
	 * Rearrange a string so that all same characters become d distance away
	 * http://www.geeksforgeeks.org/rearrange-a-string-so-that-all-same-characters-become-at-least-d-distance-away/
	 */
	
	/*
	 * task10
	 * Suffix Array | Set 2 (nLogn Algorithm)
	 */
	
	/*
	 * task11
	 * Suffix Array | Set 2 (nLogn Algorithm)
	 * http://www.geeksforgeeks.org/suffix-array-set-2-a-nlognlogn-algorithm/
	 */
	
	
	/*
	 * task12
	 * longest common subsequence
	 */
	/*
	 * task13
	 * Printing Longest Common Subsequence
	 * http://www.geeksforgeeks.org/printing-longest-common-subsequence/
	 */
	
	/*
	 * task14
	 * Print all possible words from phone digits
	 * http://www.geeksforgeeks.org/find-possible-words-phone-digits/
	 */
	
	/*
	 * task15
	 * Check if a given string is a rotation of a palindrome
	 * http://www.geeksforgeeks.org/check-given-string-rotation-palindrome/
	 * 
	 * 
	 * (1)simple solution, get all rotate string, check whether is palindrome
	 * (2)Let the string be s, ss, e.g  aaad   ==> aaadaaad  
	 *    ===> change this problem to find the palindrome whose length = n  
	 *    n = s.length()
	 *    similar the longest palindrome substring problem. 
	 *    Use Mancher's algorithm, we can solve it in Linear time. O(n)
	 *    So the total time is O(n)
	 */
	
	
	/*
	 * task16
	 * Count Possible Decodings of a given Digit Sequence
	 * http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
	 */
	
	
	
	

}
