package lai_online2;
import java.util.*;
public class Class8 {
	/*
	 * task1 
	 * All Permutations II Hard Recursion Given a string with possible
	 * duplicate characters, return a list with all permutations of the
	 * characters.
	 * 
	 * Examples
	 * 
	 * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”,
	 * “cba”] Set = "aba", all permutations are ["aab", "aba", "baa"] Set = "",
	 * all permutations are [""] Set = null, all permutations are []
	 */
	public List<String> permutations(String set) {
		// write your solution here
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		task10_helperII(input, 0, result);
		System.out.println(result);
		return result;
	}

	public void task10_helperII(char[] set, int index, List<String> result) {
		if (index == set.length) {
			String str = new String(set);
			result.add(str);
			return;
		}
		HashSet<Character> used = new HashSet<Character>();
		for (int i = index; i < set.length; i++) {
			if (!used.contains(set[i])) {
				used.add(set[i]);
				swap(set, index, i);
				task10_helperII(set, index + 1, result);
				swap(set, index, i);
			}

		}
	}

	public void swap(char[] set, int x, int y) {
		char temp = set[x];
		set[x] = set[y];
		set[y] = temp;
	}
	
	/*
	task2
	ReOrder Array
	Hard
	Recursion
	Given an array of elements, reorder it as follow:

	{ N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }

	{ N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

	Try to do it in place.

	Assumptions

	The given array is not null
	Examples

	{ 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }

	{ 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }

	{ 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }
	*/
	
	public int[] reorder(int[] array) {
		// write your solution here
		if (array.length % 2 == 0) {
			reorderHelper(array, 0, array.length - 1);
		} else {
			reorderHelper(array, 0, array.length - 2);
		}
		return array;
	}

	public void reorderHelper(int[] array, int left, int right) {
		if (left + 1 >= right) {
			return;
		}
		int size = right - left + 1;
		int mid = left + size / 2;
		int leftMid = left + size / 4;
		int rightMid = left + size * 3 / 4;

		// shuffle the middle part
		reverse2(array, leftMid, mid - 1);
		reverse2(array, mid, rightMid - 1);
		reverse2(array, leftMid, rightMid - 1);

		reorderHelper(array, left, left + 2 * (leftMid - left) - 1);
		reorderHelper(array, left + 2 * (leftMid - left), right);
	}

	public void reverse2(int[] array, int left, int right) {
		while (left < right) {
			swap(array, left++, right--);
		}
	}

	public void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	/*
	 * task3
	
	
	Reverse String
	Easy
	String
	Reverse a given string.

	Assumptions

	The given string is not null.
	*/
	public String reverse(String input) {
		// Write your solution here.
		char[] arr = input.toCharArray();
		int left = 0, right = input.length() - 1;
		helper(arr, left, right);
		return new String(arr);
	}

	public void helper(char[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		swap(arr, left, right);
		helper(arr, left + 1, right - 1);
	}
	
	/*
	 * task4
	Right Shift By N Characters
	Easy
	String
	Right shift a given string by n characters.

	Assumptions

	The given string is not null.
	n >= 0.
	*/
	public String rightShift(String input, int n) {
		// Write your solution here.
		if (input == null || input.length() == 0)
			return input;
		char[] arr = input.toCharArray();
		int len = input.length();
		// len - 1 - n + 1 = len - n
		if (n >= len) {
			return rightShift(input, n % len);
		}
		reverse(arr, 0, len - n - 1);
		reverse(arr, len - n, len - 1);
		reverse(arr, 0, len - 1);
		return new String(arr);
	}

	public void reverse(char[] arr, int i, int j) {
		while (i <= j) {
			swap(arr, i++, j--);
		}
	}
	
	/*
	 * task5
	Reverse Words In A Sentence I
	Fair
	String
	Reverse the words in a sentence.

	Assumptions

	Words are separated by single space

	There are no heading or tailing white spaces

	Examples

	“I love Google” → “Google love I”

	Corner Cases

	If the given string is null, we do not need to do anything.
	*/
	public String reverseWords(String input) {
	    // write your solution here
	    if (input == null || input.length() == 0) {
	      return input;
	    }
	    char[] arr = input.toCharArray();
	    
	    int s = 0, f = 0;
	    while(f <= arr.length) {
	      if (f == arr.length || arr[f] == ' ') {
	        reverse(arr, s, f - 1);
	        f ++;
	        s = f;
	      } else {
	        f ++;
	      }
	    }
	    
	    reverse(arr, 0, arr.length - 1);
	    
	    return new String(arr);
	  }
	  
  /*
   * task6
	Longest Substring Without Repeating Characters
	Fair
	String
	Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
	*/
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int slow = 0, fast = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLen = 0;
		int start = 0, end = 0;
		while (fast < s.length()) {
			char cur = s.charAt(fast);
			if (!map.containsKey(cur)) {
				map.put(cur, 1);
			} else {
				map.put(cur, map.get(cur) + 1);
			}

			while (map.get(cur) > 1 && slow <= fast) {
				char slow_char = s.charAt(slow);
				map.put(slow_char, map.get(slow_char) - 1);
				slow++;
			}
			if (maxLen < fast - slow + 1) {
				maxLen = fast - slow + 1;
				start = slow;
				end = fast;
			}
			fast++;
		}

		// System.out.println("maxLen = " + maxLen);
		// System.out.println(s.substring(start, end + 1));
		return maxLen;
	}
	
	/*
	 * task7
	All Anagrams
	Fair
	String
	Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

	Assumptions

	s is not null or empty.
	l is not null.
	Examples

	l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
	*/
	List<Integer> allAnagrams(String s, String l) {
		// Write your solution here.
		List<Integer> result = new ArrayList<Integer>();
		if (s == null || l == null || s.length() == 0 || l.length() == 0) {
			return result;
		}
		if (s.length() > l.length()) {
			return result;
		}
		Map<Character, Integer> map = countMap(s);
		int match = 0;
		for (int i = 0; i < l.length(); i++) {
			char temp = l.charAt(i);
			Integer count = map.get(temp);
			if (count != null) {
				map.put(temp, count - 1);

				if (count == 1) {
					match++;
				}
			}

			if (i >= s.length()) {
				temp = l.charAt(i - s.length());
				count = map.get(temp);
				if (count != null) {
					map.put(temp, count + 1);
					if (count == 0) {
						match--;
					}
				}
			}

			if (match == map.size()) {
				result.add(i - s.length() + 1);
			}
		}
		return result;
	}

	public Map<Character, Integer> countMap(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			Integer count = map.get(ch);
			if (count == null) {
				map.put(ch, 1);
			} else {
				map.put(ch, count + 1);
			}
		}
		return map;
	}
	
	/*
	 * task8
	String Replace
	Hard
	String
	Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

	Assumptions

	input, S and T are not null, S is not empty string
	Examples

	input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
	input = "dodododo", S = "dod", T = "a", input becomes "aoao"
	*/ 
	public String replace(String input, String s, String t) {
		// write your solution here
		if (s.length() >= t.length()) {
			return replaceWithShorter(input, s, t);
		} else {
			return replaceWithLonger(input, s, t);
		}
	}

	public String replaceWithShorter(String input, String s, String t) {
		char[] array = input.toCharArray();
		// record the current end
		int nextStart = 0;
		for (int i = 0; i < input.length();) {
			if (i <= input.length() - s.length() && equalSubArray(input, i, s)) {
				// copy the shorter target to this place
				copyFromLeft(array, nextStart, t);
				// update i
				i += s.length();
				// update end
				nextStart += t.length();
			} else {
				array[nextStart++] = input.charAt(i++);
			}
		}
		return new String(array, 0, nextStart);
	}

	public boolean equalSubArray(String input, int index, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (input.charAt(index + i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public void copyFromLeft(char[] array, int index, String t) {
		for (int i = 0; i < t.length(); i++) {
			array[index + i] = t.charAt(i);
		}
	}

	public  String replaceWithLonger(String input, String s, String t) {
				// first find all substring's ending index,  which matches t
				ArrayList<Integer> matches = new ArrayList<Integer>();
				for(int i =0; i <= input.length() - s.length();) {
					if (equalSubArray(input, i, s)) {
						matches.add(i + s.length() - 1);
						i += s.length();
					} else {
						i ++;
					}
				}
				int newLength = input.length() + matches.size() * (t.length() - s.length());
				
				// replace from the last
				int lastIndex = matches.size() - 1;
				int end = newLength - 1;
				char[] result = new char[newLength];
				for(int i = input.length() - 1; i >= 0;) {
					if (lastIndex >= 0  && i == matches.get(lastIndex)) {
						copyFromRight(result, end, t);
						lastIndex --;
						// update i
						i -= s.length();
						// update end
						end -= t.length();
					} else {
						result[end--] = input.charAt(i --);
					}
				}
				
				return new String(result);
				
			}

	public void copyFromRight(char[] array, int index, String t) {
		for (int i = t.length() - 1; i >= 0; i--) {
			array[index--] = t.charAt(i);
		}
	}
	
	/*
	 * task9
	Decompress String II
	Hard
	String
	Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.

	Assumptions

	The string is not null

	The characters used in the original string are guaranteed to be ‘a’ - ‘z’

	There are no adjacent repeated characters with length > 9

	Examples

	“a1c0b2c4” → “abbcccc”
	*/
	public String decompress(String input) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return input;
		}
		int newLen = task11_getNewLength(input);
		char[] array = new char[newLen];

		int end = newLen - 1;
		// System.out.println("end = " + end);
		// System.out.println("input = ");
		// System.out.println(input);

		for (int i = input.length() - 1; i >= 0; i--) {
			if (isChar(input.charAt(i))) {
				int j = i + 1;
				while (j < input.length() && isNum(input.charAt(j))) {
					j++;
				}
				String temp = input.substring(i + 1, j);
				int counter = Integer.parseInt(temp);
				if (counter == 0) {
					continue;
				} else {
					// counter != 0
					while (counter > 0) {
						array[end--] = input.charAt(i);
						counter--;
					}
				}
			}
		}

		String rev = new String(array);
		// System.out.println(rev);
		return rev;
	}

	public int task11_getNewLength(String input) {
		int counter = 0;
		int s = 0, f = 1;

		while (f < input.length()) {
			if (isNum(input.charAt(f))) {
				while (f < input.length() && isNum(input.charAt(f))) {
					f++;
				}
				String temp = input.substring(s + 1, f);
				Integer cur_counter = Integer.parseInt(temp);
				counter += cur_counter;
			}
			s = f;
			f++;
		}

		return counter;
	}

	public boolean isChar(char ch) {
		return ch >= 'a' && ch <= 'z';
	}

	public boolean isNum(char ch) {
		return ch >= '0' && ch <= '9';
	}

}
