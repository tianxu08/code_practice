package string;

import java.util.ArrayList;
import java.util.HashMap;

public class P5_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
		test7();
	}

	/*
	 * task1
	 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/ 
	 * Length of the longest substring without repeating characters

	 * Given a string, find the length of the longest substring without
	 * repeating characters. For example, the longest substrings without
	 * repeating characters for “ABDEFGABEF” are “BDEFGA” and “DEFGAB”, with
	 * length 6. For “BBBB” the longest substring is “B”, with length 1. For
	 * “GEEKSFORGEEKS”, there are two longest substrings shown in the below
	 * diagrams, with length 7.
	 */
	
	/*
	 * use a hashtable to store the <char, integer> as char==>index
	 * everytime, when we meet duplicates, get the length, update max_len if necessary
	 * then, clear the hashtable, start scan from the next char
	 */
	public static int lengthOfLongestSubstringWithoutRepeating(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int  curLen = 0, maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (!map.containsKey(cur)) {
				map.put(cur, i);
				curLen ++;
				maxLen = Math.max(curLen, maxLen);
			} else {
				// there is duplicates. 
				maxLen = Math.max(maxLen, curLen);
				curLen = 0; // we need to restart the scan from the map.get(cur) + 1
				int dupIndex = map.get(cur);
				i = dupIndex;   // later, it will i ++;
				map.clear();
			}
		}
		return maxLen;
	}
	public static void test1() {
		String s = "bbbb";
		int maxLen = lengthOfLongestSubstringWithoutRepeating(s);
		int maxLen2 = lenOfLSNODup(s);
		System.out.println("maxLen = " + maxLen);
		System.out.println("maxLen2 = " + maxLen2);
	}
	
	public static int lenOfLSNODup(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int start = 0, curLen = 0, maxLen = 0;
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (map.containsKey(cur) && map.get(cur) >= start) {
				start = map.get(cur) + 1;  // update the last repeated char
				curLen = i - start + 1;    // calculate the current length
			} else {
				curLen ++;                 // calculate the current length
			}
			map.put(cur, i);
			maxLen = Math.max(curLen, maxLen);
		}
		return maxLen;
	}
	
	/*
	 * task2 
	 * Searching for Patterns | Set 1 (Naive Pattern Searching)
	 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function
	 * search(char pat[], char txt[]) 
	 * that prints all occurrences of pat[] in txt[]. You may assume that n > m.
	 * strStr() 
	 * Returns the index of the first occurrence of needle in haystack, 
	 * or -1 if needle is not part of haystack.
	 */
	
	public static int strStrNaive(String text, String pattern) {
		if (text == null) {
			return -1;
		}
		if (text.length() < pattern.length()) {
			return -1;
		}
		int txtLen = text.length();
		int patLen = pattern.length();
		
		for (int i = 0; i <= txtLen - patLen; i++) {
			int j = 0;
			for (;  j < patLen; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					// !!! here, text.charAt(i + j) compare with pattern.charAt(j)
					break;
				}
			}
//			System.out.println("j = " + j) ;
			if (j == patLen) {
				return i;
			}
		}
		return -1;
	} // Time: worse case O((n - m) * m)  n is length of text, m is length of pattern
	public static int strStrNaive2(String text, String pattern) {
		int tLen = text.length();
		int pLen = pattern.length();
		int i = 0, j = 0;
		while(i < tLen && j < pLen) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i ++;
				j ++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == pLen) {
			return i - j;
		} else {
			return -1;
		}
	}
	
	public static void test2() {
		String text = "ABABDABACDABABCABAB";
		String pattern = "ABABCABAB";
		int index = strStrNaive(text, pattern);
		System.out.println("index = " + index);
		
		int index2 = strStrKMP(text, pattern);
		System.out.println("index2 = " + index2);
		
		int index3 = strStrNaive2(text, pattern);
		System.out.println("index3 = " + index3);
	}
	
	/*
	 * task3
	 * strStr()
	 * KMP
	 */
	public static void test3() {
		String pat = "ABABCABAB";
		int[] next = getNext(pat);
		
	}
	public static int strStrKMP(String text, String pattern) {
		int i = 0, j = 0;
		int tLen = text.length();
		int pLen = pattern.length();
		int[] next = getNext(pattern);
		while(i < tLen && j < pLen) {
			if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
				//①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++  
				i ++;
				j ++;
			} else {
				//②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]      
	            //next[j]即为j所对应的next值  
				j = next[j];
			}
		}
		if (j == pLen) {
			return i - j;
		} else {
			return -1;
		}
	}
	
	public static int[] getNext(String pat) {
		int pLen = pat.length();
		int[] next = new int[pLen];
		int k = -1;
		int j = 0;
		next[0] = -1;
		while (j < pLen - 1) {
			if (k == -1 || pat.charAt(j) == pat.charAt(k)) {
				k ++;
				j ++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
		return next;
	}
	
	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/*
	 * task4 Find the smallest window in a string containing all characters of
	 * another string 
	 * Given two strings string1 and string2, find the smallest
	 * substring in string1 containing all characters of string2 efficiently.
	 * 
	 * For Example:
	 * 
	 * Input string1: “this is a test string” 
	 * Input string2: “tist” 
	 * Output string: “t stri”
	 * 
	 * For example,
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * Minimum window is "BANC".
	 */
	
	public static String minWindow(String source, String target) {
		//
		if (target == null || source.length() == 0) {
			return "";
		}
		if (source.length() < target.length()) {
			return "";
		}
		
		int[] target_count = new int[256];
		int[] window_count = new int[256];
		
		// fill in the target_count
		for (int i = 0; i < target.length(); i++) {
			target_count[target.charAt(i)] ++;
		}
		
		// scan the source 
		int wndEnd = 0;  // the end of current window
		int wndStart = 0; // the start of current window
		int minStart = -1; // the start of the min window
		int minLen = Integer.MAX_VALUE; // the length of minimum window.
		int appeared = 0;   // use to record 
		for (; wndEnd < source.length(); wndEnd ++) {
			char wndEndChar = source.charAt(wndEnd);
//			System.out.println("wndEndChar = "+ wndEndChar);
			if (target_count[wndEndChar] > 0) {
				// the target contain wndEndChar
				window_count[wndEndChar] ++;  // update the window_count
				
				if (window_count[wndEndChar] <= target_count[wndEndChar]) {
					// wndEncChar is meaningful
					appeared ++;
				}	
			}
			
			if (appeared == target.length()) {
				// the window contains all the chars of target. we can trim the wndStart
				while (target_count[source.charAt(wndStart)] == 0 || 
						window_count[source.charAt(wndStart)] > target_count[source.charAt(wndStart)]) {
					// we can throw the wndStartChar from the window	
					// update the window_count
					window_count[source.charAt(wndStart)] --;
					// update the wndStart 
					wndStart ++;
				}
				// after trim, we can update the minLen
				if (minLen > wndEnd - wndStart + 1) {
					minLen = wndEnd - wndStart + 1;
					minStart = wndStart;
				}
			}
			
		}
		
		if (minLen == Integer.MAX_VALUE) {
			return "";
		} else {
			return source.substring(minStart, minStart + minLen);
		}
	}
	
	public static void test4() {
		String source = "ADOBECODEBANC";
		String target = "ABC";
		String minWindow = minWindow(source, target);
		System.out.println("minWindow " + minWindow);
		
		String s = "this is a test string";
		String t = "tist";
		String minWnd2 = minWindow(s, t);
		System.out.println("minWnd2 = " + minWnd2);
	}
	
	/*
	 * task5
	 * Reverse words in a given string
	 * input "the sky is blue"
	 * output "blue is sky the"
	 * there might also extra space in the input. like "   the sky is     blue    " 
	 * the output should also be "blue is the sky"
	 */
	
	public static String reverseStringByWord(String input) {
		StringBuilder reverse = new StringBuilder();
		for(int i = input.length() - 1; i >=0; i--) {
			while(i >= 0 && input.charAt(i) == ' ') {
				i --;
			}
			if (i == -1) {
				//we has out of the left bound we break it. otherwise, the next code will
				// execute, which append a " "(space) to reverse
				break;
			}
			// append an " "(space) if the reverse is not empty
			if (reverse.length() != 0) {
				reverse.append(" ");
			}
			
			// now get the word in a reverse order
			StringBuilder temp = new StringBuilder();
			while (i >= 0 && input.charAt(i) != ' ') {
				temp.append(input.charAt(i));
				i --;
			}
			
			reverse.append(reverseWord(temp.toString()));
		}
		return reverse.toString();
	}
	public static String reverseWord(String s) {
		StringBuilder str = new StringBuilder();
		for(int i = s.length() - 1; i >=0; i--) {
			str.append(s.charAt(i));
		}
		return str.toString();
	}
	
	public static void test5() {
		String input = "  i   like this program very much    ";
		String reverse = reverseStringByWord(input);
		String reverse1 = reverseStringByWord2(input);
		System.out.println(reverse);
		System.out.println(reverse.length());
		System.out.println(reverse1);
		System.out.println(reverse1.length());
	}
	
	public static String reverseStringByWord2(String input) {
		StringBuilder reverse1 = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			while(i < input.length() && input.charAt(i) == ' ') {
				i ++;
			} // trim the space
			
			if (i == input.length()) {
				break;
			}
			if (reverse1.length() != 0) {
				reverse1.append(" ");
			}
			
			StringBuilder temp = new StringBuilder();
			while(i < input.length() && input.charAt(i) != ' ') {
				temp.append(input.charAt(i));
				i ++;
			}
			
			reverse1.append(reverseWord(temp.toString()));
		}
		return reverseWord(reverse1.toString());
	}
	
	/*
	 * task6 
	 * Print list items containing all characters of a given word There is
	 * a list of items. Given a specific word, e.g., “sun”, print out all the
	 * items in list which contain all the characters of “sun”
	 * 
	 * For example if the given word is “sun” and the items are “sunday”,
	 * “geeksforgeeks”, “utensils”, “”just” and “sss”, then the program should
	 * print “sunday” and “utensils”.
	 */
	
	public static void print6(ArrayList<String> list, String word) {
		int[] map = new int[256];
		for (int i = 0; i < word.length(); i++) {
			map[word.charAt(i)] ++;
		}
		
		for (int i = 0; i < list.size(); i++) {
			String cur = list.get(i);
			int count = 0;
			for (int j = 0; j < cur.length(); j++) {
				if (map[cur.charAt(j)] > 0) {
					count ++;
					// unset in the map
					map[cur.charAt(j)] --;
				}
				
				if (count == word.length()) {
					System.out.println(cur);
					break;
				}
			}
			// reconstruct the map for the next string in list
			for (int k = 0; k < word.length(); k++) {
				map[word.charAt(k)] ++;
			}
		}
		
	}
	
	public static void test6() {
		String[] s = { "sunday", "geeksforgeeks", "utensils", "just", "sss"};
		ArrayList<String> input = new ArrayList<String>();
		for (String a: s) {
			input.add(a);
		}
		String word = "sun";
		print6(input, word);
	}
	
	/*
	 * task7
	 * Given a string, find its first non-repeating character
	 * http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
	 * 
	 * also see P3_String task7
	 * Find the first non-repeating character from a stream of characters
	 * http://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
	 */
	
	public static int firstNoRepeatingChar1(String s) {
		int[] map = new int[256];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)] ++;
		}
		
		// scan the string
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (map[cur] == 1) {
				return i;
			}
		}
		// there is no no-repeating chars
		return -1;
	}
	
	public static void test7() {
		String s = "aaaaa";
		int index = firstNoRepeatingChar1(s);
		System.out.println("index = " + index);
//		System.out.println(s.charAt(index));
		
		int index2 = firstNoRepeatingChar2(s);
		System.out.println("index2 = " + index2);
//		System.out.println(s.charAt(index2));
	}
	
	public static class Item{
		public int count;
		public int index;
		public Item () {
			count = 0;
			index = -1;
		}
	}
	public static int firstNoRepeatingChar2(String s) {
		Item[] map = new Item[256];
		for (int i = 0; i < map.length; i++) {
			map[i] = new Item();
		}
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			map[cur].count ++;
			map[cur].index = i;
		}
		int firstIndex = Integer.MAX_VALUE;
		for (int i = 0; i < map.length; i++) {
			if (map[i].count == 1) {
				// if map[i].count == 1, it only appear once. we want the first index
				if (firstIndex > map[i].index) {
					// update the first index
					firstIndex = map[i].index;
				}
			}
		}
		return firstIndex;
	}
	/*
	 * task8
	 * A Program to check if strings are rotations of each other or not
	 * Given a string s1 and a string s2, write a snippet to say 
	 * whether s2 is a rotation of s1 using only one call to strstr routine?
	 * (eg given s1 = ABCD and s2 = CDAB, return true, given s1 = ABCD, and s2 = ACBD , 
	 * return false)
	 * Algorithm: areRotations(str1, str2)
    1. Create a temp string and store concatenation of str1 to
       str1 in temp.
                          temp = str1.str1
    2. If str2 is a substring of temp then str1 and str2 are 
       rotations of each other.
    Example:                 
                     str1 = "ABACD"
                     str2 = "CDABA"

     temp = str1.str1 = "ABACDABACD"
     Since str2 is a substring of temp, str1 and str2 are 
     rotations of each other.
     
     
     !!! this is a great idea
	 */
	
	
	
	/*
	 * Summary:
	 * count[]  map<char, count>
	 * use appearLen and count[] together to 
	 *     check whether s(the larger string) contains all chars in t(smaller string)
	 *     used in minimum window(task4) and print string(task5) 
	 * strStr()  naive, kmp
	 * 
	 */
	
	
	
	
	
}
