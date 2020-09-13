package onceagain;

import java.util.*;

public class Class7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test5();
//		test6();
//		test8();
//		test7();
		test9();
		
	}
	
	/*
	 * task1 Top K frequent Words
	 * 1 use a hashMap to store <String, Counter>
	 * 2 use minHeap to get the top k frequent words, the minHeap is based on the counter of each string. 
	 *    => reduce to get the largest k number from a unsorted string
	 */
	public static String[] task1_topKFrequent(List<String> combo, int k) {
		// check
		if (combo == null || combo.size() == 0) {
			 return null;
		}
		final Map<String, Integer> map = new HashMap<String, Integer>();
		for(String s: combo) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		
		PriorityQueue<String> minHeap = new PriorityQueue<String>(k, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				Integer f1 = map.get(o1);
				Integer f2 = map.get(o2);
				return 0;
			}
		});
		
		// traverse the map
		for(java.util.Map.Entry<String, Integer> entry: map.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry.getKey());
			} else {
				if (entry.getValue() > map.get(minHeap.peek())) {
					minHeap.poll();
					minHeap.offer(entry.getKey());
				}
			}
		}
		
		List<String> topK = new ArrayList<String>();
		while(!minHeap.isEmpty()) {
			topK.add(minHeap.poll());
		}
		
		Collections.reverse(topK);
		return topK.toArray(new String[0]);
		
		
		
	}
	
	
	/*
	 * task2 missing number1
	 * 
	 * 
	 */
	
	/*
	 * task3 Common numbers of two sorted Arrays
	 */
	
	/*
	 * task4 Remove All Leading/Tailing/Duplicate space Characters
	 */
	public static String task4_removeSpaces(String str) {
		char[] strArr = str.toCharArray();
		
		int slow = 0, fast = 0;
		for(; fast < strArr.length; fast ++) {
			if (strArr[fast] == ' ' && (fast == 0 || strArr[fast - 1] == ' ')) {
				continue;
			}
			strArr[slow ++] = strArr[fast];
		}
		if (fast > 0 && strArr[fast - 1] == ' ') {
			return new String(strArr, 0, fast - 1);
		}
		return new String(strArr, 0, fast);
	}
	
	/*
	 * task5 remove 'u' and 'n' from Word
	 */
	public static String task5_remove(String str) {
		if (str == null) {
			return str;
		}
		char[] arr = str.toCharArray();
		int end = 0;
		for(int i = 0; i < str.length(); i ++) {
			if (arr[i] == 'u' || arr[i] == 'n') {
				continue;
			} 
			arr[end ++] = arr[i];
		}
		return new String(arr, 0, end);
	}
	
	public static void test5() {
		String str = "universityninenice";
		String rev = task5_remove(str);
		System.out.println(rev);
	}
	
	/*
	 * task6 Remove Adjacent Repeated Characters I
	 * try to convert the string to char array,
	 * and do it in place.
	 * For the adjacent repeated characters, only retain one of them.
	 * abbc => abc
	 * aaabbcc => abc
     */
	public static String task6_onlyKeepOne(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char[] array = str.toCharArray();
		int s = 1, f = 1;
		for(; f < array.length; f ++) {
			if (array[f] == array[s - 1]) {
				continue;
			}
			array[s ++] = array[f];
		}
		return new String(array, 0, s);
	}
	
	public static void test6() {
		String str = "abccc    ";
		String rev = task6_onlyKeepOne(str);
		System.out.println(rev);
		System.out.println(rev.length());
	}
	
	/*
	 * task7 Remove Adjacent Repeated Characters II
	 * remove all the duplicates 
	 * e.g 
	 * aabbc => c
	 * abccdddde
	 */
	public static String task7_removeAllDuplicates(String str) {
		if (str == null || str.length() <= 1) {
			return str;
		}
		char[] array = str.toCharArray();
		int s = 0, f = 1;
		boolean flag = false; // use to mark whether current candidate has flag
		while(f < array.length) {
			if (array[f] == array[s]) {
				// there is duplicate, mark flag = true
				flag = true;
			} else {
				// array[f] != array[s]
				if (flag == false) {
					s ++;
					array[s] = array[f];
				} else {
					// flag == true
					array[s] = array[f];
					flag = false;
				}
			}
			f ++;
		}
		if (flag == true) {
			return new String(array, 0, s);
		} else {
			return new String(array, 0, s + 1);
		}
	}
	
	public static void test7() {
		String str = "abbccd";
		String rev = task7_removeAllDuplicates(str);
		System.out.println(rev);
	}
	
	
	/*
	 * task8 Remove Adjacent Repeated Characters III
	 * keep at most two
	 * e.g
	 * aabbbbc => aabbc
	 * 
	 * s, f
	 * [0, s) finished
	 * [s, f) unknown
	 * [f, n - 1] to explore
	 * array[f] compares with array[s - 2]
	 */
	
	public static String task8_removeKeepAtMost2(String str) {
		if (str == null || str.length() <= 2) {
			return str;
		}
		char[] array = str.toCharArray();
		int s = 2, f = 3;
		for(; f < array.length; f ++) {
			if (array[f] == array[s - 2]) {
				continue;
			} 
			array[s ++] = array[f];
		}
		return new String(array, 0, s);
	}
	
	public static void test8() {
		String str = "abbbccccccddefg";
		String rev = task8_removeKeepAtMost2(str);
		System.out.println(rev);
	}
	
	/*
	 * task9 Remove Adjacent Repeated Characters Iv
	 *
     *  try to convert the string to char array,
     *  and do it in place.
     *  Remove all adjacent repeated characters repeatedly.
     *  “abbbaac” → “aaac” → “c”
     */
	public static String task9_removeAdjRepeated(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char[] array = str.toCharArray();
		int s = 0, f = 1;
		while(f < array.length) {
			if (s == -1) {
				array[++s] = array[f];
			} else if (array[s] != array[f]) {
				array[++s] = array[f];
			} else {
				// s != -1 && array[s] == array[f]
				s --;
				while(f + 1 < array.length && array[f] == array[f + 1]) {
					f++;
				}
			}
			f++;
		}
		return new String(array, 0, s + 1);
	}
	
 	public static void test9() {
 		String str = "abbbabccd";
 		String rev = task9_removeAdjRepeated(str);
 		System.out.println(rev);
 	}

	
	/*
	 * task10
	 * determine if one string is another's substring
	 */
	/*
	 * Brute force
	 * Time: O(m * n)
	 */
	public static boolean task10_strStr1(String text, String pattern) {
		if (text == null || text.length() == 0) {
			return false;
		}
		if (pattern == null || pattern.length() == 0) {
			return true;
		}
		for(int i = 0; i < text.length() - pattern.length(); i ++) {
			int j = 0; 
			for(; j < pattern.length(); j ++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if (j == pattern.length()) {
				return true;
			}
		}
		return false;
	}
	
	public static void test10() {
		String text = "hello world, how is going";
		String pattern = "how";
		boolean rev = task10_strStr1(text, pattern);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * task10.1  KMP
	 * 
	 */
	

}
