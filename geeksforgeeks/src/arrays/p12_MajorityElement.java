package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class p12_MajorityElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test1_3();
	}

	/*
	 * http://www.geeksforgeeks.org/majority-element/
	 * 
	 * Majority Element: A majority element in an array A[] of size n is an
	 * element that appears more than n/2 times (and hence there is at most one
	 * such element).
	 */

	// basic method1
	public static int majority1_1(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		if (a.length == 1) {
			return a.length;
		}
		// sort the array
		Arrays.sort(a);
		int count = 1;
		// 22334444
		int prev = 0;
		int cur = 1;
		while (cur < a.length) {
			if (a[cur] == a[prev]) {
				count++;
				// for debug
				// System.out.println(count);
				if (count >= a.length / 2) {
					return a[cur];
				}
				cur++;
			} else {
				count = 1;
				prev = cur;
				cur++;
			}
		}
		return -1;
	} // time: Sort(n log n) Space O(1)

	public static void test1() {
		int[] a = { 1, 1, 2, 2, 3, 3, 4, 4, 4, 4 };
		int maj = majority1_1(a);
		int maj2 = majority1_2(a);
		System.out.println("maj = " + maj);
		System.out.println("maj2 = " + maj2);
	}

	// basic method 2
	// brute force
	public static int majority1_2(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int count = 1;
		for (int i = 0; i < a.length - 1; i++) {
			int curElem = a[i];
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] == curElem) {
					count++;
					if (count >= a.length / 2) {
						return curElem;
					}
				}
			}
			count = 1;
		}
		return -1;
	} // Time O(n ^2) Space O(1)

	// BST
	public static class BSTNode {
		public int val;
		public int count;
		public BSTNode left;
		public BSTNode right;
	}

	public static int majority1_3(int[] a) {
		return -1;

		// for every insert, if the element is already there, we increase the
		// count;
		// if count >= n/2, return the element;
		// if we insert all the element, there is no element's count >= n/2,
		// return -1;
		// for self balanced bst, Time: O(n log n). Space O(n)
	}

	// Moore's Voting Algorithm
	/*
	 * Two steps (1) find a candidate (2) check if the candidate's count >= n/2
	 */

	public static int majority1_4(int[] a) {
		int candidate = getCandidates(a);
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == candidate) {
				count++;
			}
			if (count >= a.length / 2) {
				return candidate;
			}
		}
		return -1;
	}

	public static void test1_3() {
		int[] a = { 1, 1, 2, 2, 3, 3, 4, 4, 4, 4 };
		int maj = majority1_4(a);
		System.out.println("maj = " + maj);
	}

	public static int getCandidates(int[] a) {
		int candIndex = 0;
		int count = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i] == a[candIndex]) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				candIndex = i;
				count = 1;
			}
		}
		return a[candIndex];
	}

	/*
	 * follow up Majority Number 2 Given an array of integers, the majority
	 * number is the number that occurs more than 1/3 of the size of the array.
	 * Find it Note There is only one majority number in the array
	 */
	public static int majority2(int[] a) {
		int cand1 = Integer.MAX_VALUE;
		int cand2 = Integer.MAX_VALUE;
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[count1] == cand1) {
				count1++;
			} else if (a[count2] == cand2) {
				count2++;
			} else if (count1 == 0) {
				cand1 = i;
				count1 = 1;
			} else if (count2 == 0) {
				cand2 = i;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}

		// check the cand1 and cand2 again
		count1 = 0;
		count2 = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == cand1) {
				count1++;
			}
			if (a[i] == cand2) {
				count2++;
			}
		}
		if (count1 > cand2) {
			return cand1;
		} else {
			return cand2;
		}
	}

	/*
	 * Majority Number III using a hashmap
	 * 
	 * Majority Number III My Submissions Given an array of integers and a
	 * number k, the majority number is the number that occurs more than 1/k of
	 * the size of the array. Find it. Note There is only one majority number in
	 * the array. Example For [3,1,2,3,2,3,3,4,4,4] and k = 3, return 3
	 * Challenge O(n) time and O(k) extra space ??? How to finish it in O(k)
	 * extra space ???
	 */

	public static int majority3(int[] a, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			int cur = a[i];
			if (map.containsKey(cur)) {
				// the map contains cur
				map.put(cur, map.get(cur) + 1);
			} else {
				// the map doesn't contain cur
				if (map.size() == k) {
					Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet()
							.iterator();
					while (iter.hasNext()) {
						Map.Entry<Integer, Integer> entry = iter.next();
						if (entry.getValue() - 1 == 0) {
							iter.remove();
						} else {
							map.put(entry.getKey(), entry.getValue() - 1);
						}
					}
				} else {
					map.put(cur, 1);
				}
			}
		}
		int max = 0;
		int result = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				result = entry.getKey();
			}
		}
		return result;
	}

	public static int majority3Lint(ArrayList<Integer> nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.size(); i++) {
			int cur = nums.get(i);
			if (map.containsKey(cur)) {
				map.put(cur, map.get(cur) + 1);
			} else {
				if (map.size() == k) {
					Iterator<Entry<Integer, Integer>> iter= map.entrySet().iterator();
					
					
					while (iter.hasNext()) {
						Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iter
								.next();
						if (entry.getValue() - 1 == 0) {
							iter.remove();
						} else {
							map.put(entry.getKey(), entry.getValue() - 1);
						}
					}
				} else {
					map.put(cur, 1);
				}
			}
		}
		int max = 0;
		int result = 0;
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				result = entry.getKey();
			}
		}
		return result;
	}
	
	
}
