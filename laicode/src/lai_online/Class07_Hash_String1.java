package lai_online;

import java.util.*;
public class Class07_Hash_String1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test1();
//		test();
//		test6_1();
//		test3_2();
		test3_4();
	}
	
	
	/*
	 * task1
	 * Top K Frequent Words
	 * Given a composition with different kinds of words, 
	 * return a list of the top K most frequent words in the composition.
	 * Assumptions
	 * the composition is not null and is not guaranteed to be sorted
	 * K >= 1 and K could be larger than the number of distinct words in the composition, 
	 * in this case, just return all the distinct words
	 * Return a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
	 * Examples
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
	 * Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
	 * 
	 * 
	 * Hash + Priority Queue
	 * 1 use hashMap<String, Integer>() to count the Strings
	 * 2 traverse the map and put every string into a k sized minHeap.
	 * 3 pop all elements in minHeap
	 */
	public static void test1() {
		String[] combo = {"a","c","d","a","b","b","c","d","d","a","d"};
		String[] output = topKFrequent(combo, 1);
		for(String str: output) {
			System.out.println(str);
		}
	}
	
	public static String[] topKFrequent(String[] combo, int k) {
		// Write your solution here.
		final HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String str: combo) {
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 1);
			}
		}
		
		PriorityQueue<String> minHeap = new PriorityQueue<String>(k, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				int f1 = map.get(o1);
				int f2 = map.get(o2);
				if (f1 == f2) {
					return 0;
				}
				return f1 < f2 ? -1: 1;
			}
		});
		// traverse the map and put them into the k size minHeap
		for(Map.Entry<String, Integer> entry: map.entrySet()) {
			System.out.println("--------");
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if (minHeap.size() < k) {
				minHeap.offer(entry.getKey());
			} else if (entry.getValue() > map.get(minHeap.peek())) {
				minHeap.poll();
				minHeap.offer(entry.getKey());
			}
		}
		
		System.out.println("minHeap.size = ");
		System.out.println(minHeap.size());
		
		String[] output = null;
		if (k > minHeap.size()) {
			output = new String[minHeap.size()];
		} else {
			output = new String[k];
		}
		
		for(int i = minHeap.size() - 1; i >=0; i --) {
			output[i] = minHeap.poll();
		}
		return output;
	}
	
	
	/*
	 * task3
	 * Remove Adjacent Repeated Characters I
	 * Remove adjacent, repeated characters in a given string, 
	 * leaving only one character for each group of such characters.
	 * Assumptions
	 * Try to do it in place.
	 * Examples
	 * “aaaabbbc” is transferred to “abc”
	 * Corner Cases
	 * If the given string is null, we do not need to do anything.
	 */
	public static void test3() {
		String input = "abc";
		String output = task3_deDup(input);
		System.out.println(output);
	}

	public static String task3_deDup(String input) {
		// write your solution here
		if (input == null || input.length() <= 0) {
			return input;
		}
		char[] str = input.toCharArray();
		int s = 0, f = 1;
		while (f < str.length) {
			if (str[s] != str[f]) {
				++s;
				str[s] = str[f];
			}
			f++;
		}
		// the new length of the string will be s + 1
		String output = new String(str, 0, s + 1);
		System.out.println(output);
		return output;
	}
	
	
	/*
	 * task3.1
	 * Remove Adjacent Repeated Characters II
	 * 
	 * Remove adjacent, repeated characters in a given string,
	 * leaving only two characters for each group of such characters. 
	 * The characters in the string are sorted in ascending order.
	 * 
	 * Sorted
	 * 
	 * “aaaabbbc” is transferred to “aabbc”
	 * 
	 * two pointers
	 * [0, s) processed
	 * [s, f) useless
	 * [f, n) to explore
	 * s, f 
	 * init: s = 2, f = 2;
	 * array[f] == array[s - 2]  f ++
	 * else  array[s++] = array[f ++]
	 */
	public static String task3_1_deDup(String input) {
		if (input == null || input.length() <=2) {
			return input;
		}
		char[] strArr = input.toCharArray();
		int s = 2, f = 2;
		while(f < strArr.length) {
			if (strArr[f] == strArr[s - 2]) {
				f ++;
			} else {
				strArr[s ++] = strArr[f ++];
			}
		}
		return new String(strArr, 0, s);
	}
	
	/*
	 * task3_2
	 * Remove Adjacent Repeated Characters III
	 * Remove adjacent, repeated characters in a given string, 
	 * leaving no character for each group of such characters. 
	 * The characters in the string are sorted in ascending order.
	 * “aaaabbbc” is transferred to “c”
	 * 
	 * two pointers
	 * 
	 * s points an candidate
	 * f use to explore 
	 * 
	 * we use a flag to check whether the candidate has duplicate
	 * init:
	 * 
	 * s = 0, f = 1, flag = false, whether the candidate has duplicate
	 * 
	 * [0, s) processed
	 * [s, f) useless
	 * [f, n) to explore
	 * 
	 */
	
	public static void test3_2() {
		String input = "abbccde";
		String output = task3_2_deDup(input);
		System.out.println(output);
	}
	public static String task3_2_deDup(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		boolean flag = false;
		char[] strArr = input.toCharArray();
		int s = 0, f = 1;
		while(f < strArr.length) {
			if (strArr[f] == strArr[s]) {
				flag = true;
				f ++;
			} else {
				// strArr[f] != strArr[s]
				if (flag) {
					// flag is true
					// we need a new candidate
					strArr[s] = strArr[f];
					flag = false;
				} else {
					// flag is false
					// its safe to to move forward s, and put a new candicates
					s ++;
					strArr[s] = strArr[f];
				}
				f ++;
			}
		}
		// check the last element
		if (!flag) {
			s ++;
		}
		
		return new String(strArr, 0, s);
	}
	
	
	/*
	 * task3_4
	 * 
	 * !! unfinished
	 * Remove Adjacent Repeated Characters IV
	 * Repeatedly remove all adjacent, repeated characters in a given string from left to right.
	 * No adjacent characters should be identified in the final string.
	 * Examples
	 * "abbbaaccz" → "aaaccz" → "ccz" → "z"
	 * "aabccdc" → "bccdc" → "bdc"
	 * 
	 * data structure: stack
	 * we need to access the previous element. so, stack is a choice
	 */
	public static void test3_4() {
		String input = "abbbaac";
		String output = task3_4_deDup(input);
		System.out.println(output);
		System.out.println("------------------");
		String output2 = task3_4_deDup_2(input);
		System.out.println(output2);
	}
	
	public static String task3_4_deDup(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		LinkedList<Character> stack = new LinkedList<Character>();
		
		stack.offerFirst(input.charAt(0));
		int f = 1;
		while(f < input.length()) {
			if (stack.isEmpty()) {
				stack.offerFirst(input.charAt(f));
			} else if (stack.peekFirst() != input.charAt(f)) {
				stack.offerFirst(input.charAt(f));
			} else {
				// stack is NOT empty && stack.peekFirst() == input[f]
				stack.pollFirst();
				while(f + 1 < input.length() && input.charAt(f) == input.charAt(f + 1)) {
					f++;
				} 
			}
			f++;
		}
		// so far, the stack contains the result. 
		StringBuilder stb = new StringBuilder();
		while(!stack.isEmpty()) {
			stb.append(stack.pollFirst());
		}
		return stb.reverse().toString();
	}
	
	public static String task3_4_deDup_2(String input) {
		/*
		 * try to convert the string to char array, and do it in place. Remove
		 * all adjacent repeated characters repeatedly. “abbbaac” → “aaac” → “c”
		 */
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		int s = 0;
		int f = 1;
		while (f < input.length()) {
			if (s == -1) {
				// increase s
				// we put an candidate into s
				s++;
				array[s] = array[f];
			} else if (array[f] != array[s]) {
				// increase s, and put an candidate into s
				s++;
				array[s] = array[f];
			} else {
				// s != -1 && array[f] == array[s]
				// the candidate is no longer valid, so go back one step. 
				s--;
				// skip all the following same elements
				while (f + 1 < array.length && array[f] == array[f + 1]) {
					f++;
				}
			}
			f++;
		}
		return new String(array, 0, s + 1);
	}


	/*
	 * task4
	 * remove spaces
	 * Given a string, remove all leading/trailing/duplicated empty spaces.
	 * Assumptions:
	 * The given string is not null.
	 * Examples:
	 * “  a” --> “a”
	 * “   I     love MTV ” --> “I love MTV”
	 */

	public static String task4_removeSpaces(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) {
			return input;
		}
		// counter for words
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
			// copy the word char by char
			while (s < arr.length && f < arr.length && arr[f] != ' ') {
				arr[s++] = arr[f++];
			}
			count++;
		}
		return new String(arr, 0, s);
	}

	/*
	 * task5
	 * Remove Certain Characters
	 * Remove given characters in input string, 
	 * the relative order of other characters should be remained. 
	 * Return the new string after deletion.
	 * 
	 * Assumptions
	 * The given input string is not null.
	 * The characters to be removed is given by another string, it is guranteed to be not null.
	 * Examples
	 * input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
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
	
	
	
	/* task6
	 * 
	 * missing number I 
	 * Given an integer array of size N - 1, containing all the numbers from 1 to N except one, 
	 * find the missing number.
	 * Assumptions
	 * The given array is not null, and N >= 1
	 * Examples
	 * A = {2, 1, 4}, the missing number is 3
	 * A = {1, 2, 3}, the missing number is 4
	 * A = {}, the missing number is 1
	 * 
	 * !!! Unsorted
	 * method1: 
	 * using hashset. put array elements into hashset. 
	 * traverse 1..N, the one doesn't in hashset in the missing number.
	 * method2:
	 * using XOR. XOR all elemtns in array and 1..N. The result would be the missing number
	 * 
	 * method3:
	 * using sum. get the sum of 1..N
	 * traverse the array, substract every element from the sum, the remaining is the missing number. 
	 * 
	 * 
	 */
	public static void test() {
		int[] array = {1};
		int miss = task6_missing(array);
		System.out.println("miss = " + miss);
	}
	public static int task6_missing(int[] array) {
	    // write your solution here
	    int result = array[0];
	    for(int i = 1; i < array.length; i ++) {
	      result ^= array[i];
	    }
	    for(int i = 1; i <= array.length + 1; i ++) {
	      result ^= i;
	    }
	    return result;
	  }
	
	
	/*
	 * task6.1
	 * 
	 * if Sorted, we can use binary search.
	 * index: 0 1 2
	 * input: 1 2 4  
	 * output: 3
	 * 
	 * Time: O(log n)
	 *   
	 */
	
	public static void test6_1() {
		int[] array = {1,2,3};
		int result = task6_1_missing(array);
		System.out.println("result = " + result);
		
	}
	public static int task6_1_missing(int[] array) {
		if (array == null || array.length == 0) {
			return 1;
		}
	
		int left = 0, right = array.length - 1;
		// edge case: array[right] == right + 1, 
		if (array[right] == right + 1) {
			return right + 2;
		}
		
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (array[mid] > mid + 1) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		System.out.println("left = " + left);
		System.out.println("right = " + right);
		if (array[left] > left + 1) {
			return left + 1;
		} else {
			return right + 1;
		}
	}
	
	
	/*
	 * task7
	 * Common Numbers Of Two Sorted Arrays
	 * Find all numbers that appear in both of two sorted arrays 
	 * (the two arrays are all sorted in ascending order).
	 * 
	 * Assumptions
	 * In each of the two sorted arrays, there could be duplicate numbers.
	 * Both two arrays are not null
	 * Examples
	 * A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
	 */
	public static List<Integer> common(List<Integer> A, List<Integer> B) {
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
	 * task7.1
	 * Common Numbers Of Two Arrays I
	 * Find all numbers that appear in both of the two unsorted arrays.
	 * 
	 * No duplicates
	 * Assumptions
	 * Both arrays are not null.
	 * There are no duplicate numbers in each of the two arrays respectively.
	 * Exmaples
	 * A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
	 * A = {}, B = {3, 1, 4}, return []
	 */
	public static List<Integer> task7_1common(List<Integer> a, List<Integer> b) {
	    // write your solution here
		List<Integer> result = new ArrayList<Integer>();
		if (a == null || a.size() == 0 || b == null || b.size() == 0) {
			return result;
		}
		// suppose that a.size > b.size
		if (a.size() < b.size()) {
			return task7_1common(b, a);
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < b.size();i ++) {
			set.add(b.get(i));
		}
		
		for(int i = 0; i < a.size(); i ++) {
			if (set.contains(a.get(i))) {
				result.add(a.get(i));
			}
		}
		
		Collections.sort(result);
	    return result;
	}
	
	
	/*
	 * task7.2
	 * In any of the two arrays, there could be duplicate numbers.
	 */
	
	public static List<Integer> common2(List<Integer> A, List<Integer> B) {
	    //write your solution here
		List<Integer> result = new ArrayList<Integer>();
		if (A == null ||A.size() == 0 || B == null || B.size() == 0) {
			return result;
		}
		if (A.size() < B.size()) {
			return common2(B, A);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for(Integer i: B) {
	    	if (!map.containsKey(i)) {
				map.put(i, 1);
			} else {
				map.put(i, map.get(i) + 1);
			}
	    }
	    
	    for(Integer i : A) {
	    	if (map.containsKey(i)) {
				if (map.get(i) != 0) {
					result.add(i);
					map.put(i, map.get(i) - 1);
				}
			}
	    }
	    
	    Collections.sort(result);
		return result;
	}
	
	
	/*
	 * task7.3
	 * Common Elements In Three Sorted Array
	 * Find all common elements in 3 sorted arrays.
	 * 
	 * 
	 * Assumptions
	 * The 3 given sorted arrays are not null
	 * There could be duplicate elements in each of the arrays
	 * Examples
	 * A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
	 * 
	 * if three unsorted, we can reduce common elemnts in two unsorted array.
	 */
	
	public static List<Integer> task7_3common(int[] array1, int[] array2,
			int[] array3) {
		// write your solution here
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (i < array1.length && j < array2.length && k < array3.length) {
			if (array1[i] == array2[j] && array2[j] == array3[k]) {
				result.add(array1[i]);
				i++;
				j++;
				k++;
			} else if (array1[i] <= array2[j] && array1[i] <= array3[k]) {
				// array1[i] is the smallest.
				i++;
			} else if (array2[j] <= array1[i] && array2[j] <= array3[k]) {
				// array2[j] is the smallest.
				j++;
			} else {
				k++;
			}
		}
		return result;
	}
	
	
	
	
	
}
