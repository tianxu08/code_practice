package lai_online2;
import java.util.*;
public class Class7 {
	/*
	 * task1
	Common Numbers Of Two Sorted Arrays
	Easy
	Data Structure
	Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

	Assumptions

	In each of the two sorted arrays, there could be duplicate numbers.
	Both two arrays are not null.
	Examples

	A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
	*/
	
	public List<Integer> common(List<Integer> A, List<Integer> B) {
		// write your solution here
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (A == null || A.size() == 0 || B == null || B.size() == 0) {
			return result;
		}
		int i = 0, j = 0;

		while (i < A.size() && j < B.size()) {
			if (A.get(i) == B.get(j)) {
				result.add(A.get(i));
				i++;
				j++;
			} else if (A.get(i) > B.get(j)) {
				j++;
			} else {
				i++;
			}
		}
		return result;

	}

	/*
	 * task2 Top K Frequent Words Fair Data Structure Given a composition with
	 * different kinds of words, return a list of the top K most frequent words
	 * in the composition.
	 * 
	 * Assumptions
	 * 
	 * the composition is not null and is not guaranteed to be sorted K >= 1 and
	 * K could be larger than the number of distinct words in the composition,
	 * in this case, just return all the distinct words Return
	 * 
	 * a list of words ordered from most frequent one to least frequent one (the
	 * list could be of size K or smaller than K) Examples
	 * 
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2
	 * frequent words are [“b”, “c”] Composition = ["a", "a", "b", "b", "b",
	 * "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5
	 * frequent words are [“b”, “c”, "a", "d"]
	 */
	public String[] topKFrequent(String[] combo, int k) {
		// Write your solution here.
		final HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String str : combo) {
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}

		PriorityQueue<String> minHeap = new PriorityQueue<String>(k,
				new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						int f1 = map.get(o1);
						int f2 = map.get(o2);
						if (f1 == f2) {
							return 0;
						}
						return f1 < f2 ? -1 : 1;
					}
				});
		// traverse the map and put them into the k size minHeap
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry.getKey());
			} else if (entry.getValue() > map.get(minHeap.peek())) {
				minHeap.poll();
				minHeap.offer(entry.getKey());
			}
		}

		String[] output = null;
		if (k > minHeap.size()) {
			output = new String[minHeap.size()];
		} else {
			output = new String[k];
		}

		for (int i = minHeap.size() - 1; i >= 0; i--) {
			output[i] = minHeap.poll();
		}
		return output;
	}
	
	/*
	task3
	Missing Number I
	Fair
	None
	Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number.

	Assumptions

	The given array is not null, and N >= 1
	Examples

	A = {2, 1, 4}, the missing number is 3
	A = {1, 2, 3}, the missing number is 4
	A = {}, the missing number is 1
	*/
	public int missing(int[] array) {
		// write your solution here
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			result ^= array[i];
		}
		for (int i = 1; i <= array.length + 1; i++) {
			result ^= i;
		}
		return result;
	}
	/*
	 * task4
	Remove Adjacent Repeated Characters I
	Easy
	String
	Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.

	Assumptions

	Try to do it in place.
	Examples

	“aaaabbbc” is transferred to “abc”
	Corner Cases

	If the given string is null, we do not need to do anything.
	*/
	// [0, s] processed
	// [s + 1, f) useless
	// [f, n ) to explore

	public String deDup(String input) {
		// write your solution here
		if (input == null || input.length() <= 0) {
			return input;
		}

		char[] str = input.toCharArray();
		int s = 0, f = 1;
		while (f < str.length) {
			if (str[s] != str[f]) {
				// ++ s;
				str[++s] = str[f];
			}
			f++;
		}

		String output = new String(str, 0, s + 1);
		// System.out.println(output);
		return output;
	}
	
	/*
	 * task5
	Remove Spaces
	Easy
	String
	Given a string, remove all leading/trailing/duplicated empty spaces.

	Assumptions:

	The given string is not null.
	Examples:

	“  a” --> “a”
	“   I     love MTV ” --> “I love MTV”
	*/
	// [0, s) processed
	// [s, f) useless
	// [f, n) to explore
	public String removeSpaces(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) {
			return input;
		}
		int count = 0;
		int s = 0, f = 0;
		char[] arr = input.toCharArray();
		while (f < arr.length) {
			// skip the leading space
			while (f < arr.length && arr[f] == ' ') {
				f++;
			}
			if (f == arr.length) {
				break;
			}
			// already have word, add one space after the word
			if (count > 0) {
				arr[s++] = ' ';
			}
			while (s < arr.length && f < arr.length && arr[f] != ' ') {
				arr[s++] = arr[f++];
			}
			count++;
		}

		return new String(arr, 0, s);

	}

	/*
	 * task6
	Remove Certain Characters
	Easy
	String
	Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.

	Assumptions

	The given input string is not null.
	The characters to be removed is given by another string, it is guranteed to be not null.
	Examples

	input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
	*/
	public String remove(String input, String t) {
		// Write your solution here.
		if (input == null || input.length() == 0)
			return input;
		char[] arr = input.toCharArray();
		HashSet<Character> set = new HashSet<Character>();
		int s = 0;
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}
		for (int f = 0; f < arr.length; f++) {
			if (!set.contains(arr[f])) {
				arr[s++] = arr[f];
			}
		}
		return new String(arr, 0, s);
	}
	
	/*
	 * task7
	Determine If One String Is Another's Substring
	Fair
	String
	Determine if a small string is a substring of another large string.

	Return the index of the first occurrence of the small string in the large string.

	Return -1 if the small string is not a substring of the large string.

	Assumptions

	Both large and small are not null
	If small is empty string, return 0
	Examples

	“ab” is a substring of “bcabc”, return 2

	“bcd” is not a substring of “bcabc”, return -1

	"" is substring of "abc", return 0
	*/
	public int strstr(String large, String small) {
		// write your solution here
		if (large.length() < small.length()) {
			return -1;
		}
		// return 0 if small is empty.
		if (small.length() == 0) {
			return 0;
		}
		for (int i = 0; i <= large.length() - small.length(); i++) {
			if (equals(large, i, small)) {
				return i;
			}
		}
		return -1;

	}

	public boolean equals(String large, int start, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(i + start) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * task8
	Remove Adjacent Repeated Characters IV
	Hard
	String
	Repeatedly remove all adjacent, repeated characters in a given string from left to right.

	No adjacent characters should be identified in the final string.

	Examples

	"abbbaaccz" → "aaaccz" → "ccz" → "z"
	"aabccdc" → "bccdc" → "bdc"
	*/
	public String task8_deDup(String input) {
		// write your solution here
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		int end = 0;
		for (int i = 1; i < array.length; i++) {
			if (end == -1 || array[i] != array[end]) {
				array[++end] = array[i];
			} else {
				end--;
				while (i + 1 < array.length && array[i] == array[i + 1]) {
					i++;
				}
			}
		}
		return new String(array, 0, end + 1);
	}
	

}
