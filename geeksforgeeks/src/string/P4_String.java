package string;

public class P4_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3();
	}
	/*
	 * task1
	 * Given a sequence of words, print all anagrams together | Set 1
	 * http://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
	 * 
	 * Algorithm: 
	 * create a hashmap <String, ArrayList<String>>  key is the sorted string.
	 * value is the ArrayList<String> of anagrams of key. strings in List are original string
	 */
	
	/*
	 * task2
	 * Given a sequence of words, print all anagrams together | Set 2
	 * http://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-2/
	 * using trie.  After create Trie, let's finish this
	 */
	
	/*
	 * task3
	 * Longest Palindromic Substring | Set 1
	 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
	 * 
	 * dp:
	 * f[i][j]  whether s[i..j] is palindrome or not 
	 * f[i][j] = (s[i] == s[j]) && f[i+1][j-1]
	 * 
	 */
	
	public static String longestPalindromicSubstring(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		int sLen = s.length();
		boolean[][] f = new boolean[sLen][sLen];
		//f[i][j]  whether s[i..j] is palindrome or not
		// f[i][j] = (s[i] == s[j]) && f[i+1][j-1]
		//initialize
		int maxLen = 1;
		int maxStart = 0;
		for (int i = 0; i < sLen; i++) {
			f[i][i] = true;
		}
		for (int i = 0; i < sLen - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1) ) {
				f[i][i+1] = true;
				maxStart = i;
				maxLen = 2;
			}
		}
		
		for (int len = 2; len <= sLen ; len++) {
			for (int i = 0; i <= sLen - len; i++) {
				int j = i + len - 1;  // i + len - 1 <= sLen - 1
				if ((s.charAt(i) == s.charAt(j)) && f[i+1][j-1]) {
					f[i][j] = true;
					if (maxLen < len) {
						maxLen = len;
						maxStart = i;
					}
				}
			}
		}
		return s.substring(maxStart, maxStart + maxLen);
	}// Time: O(n^2) space O(n^2)
	
	public static void test3() {
		String s = "forgeeksskeegfor";
		String longestPalind = longestPalindromicSubstring(s);
		System.out.println(longestPalind);
	}
	
	// An optimization
	/*
	 * http://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
	 * Longest Palindromic Substring | Set 2
	 */
	
	
	
	/*
	 * task4
	 * Manacher’s Algorithm – Linear Time Longest Palindromic Substring 
	 */
	
	/*
	 * task5
	 * 
	 */
	
	/*
	 * task6 
	 * Check whether two strings are anagram of each other Write a
	 * function to check whether two given strings are anagram of each other or
	 * not. An anagram of a string is another string that contains same
	 * characters, only the order of characters can be different. For example,
	 * “abcd” and “dabc” are anagram of each other.
	 * (1)using sort
	 * (2)using count[]
	 */
	
	/*
	 * task7 
	 * http://www.geeksforgeeks.org/print-all-interleavings-of-given-two-strings/
	 * Print all interleavings of given two strings 
	 * Given two strings str1 and str2, write a function that prints all interleavings 
	 * of the given two strings. 
	 * You may assume that all characters in both strings are different
	 */
	
	/*
	 * task8 
	 * http://www.geeksforgeeks.org/check-whether-a-given-string-is-an-interleaving-of-two-other-given-strings/
	 * Check whether a given string is an interleaving of two other given strings
	 */
	
	/*
	 * task9
	 * Lexicographic rank of a string
	 * http://www.geeksforgeeks.org/lexicographic-rank-of-a-string/
	 */
	
	/*
	 * task10
	 * Print all permutations in sorted (lexicographic) order
	 * http://www.geeksforgeeks.org/lexicographic-permutations-of-string/
	 */
	
	
	
	
	
	
	 

}
