package small_sun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;

public class Class1_09202014 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test11();
//		test12();
//		test6();
//		test7();
//		test9();
//		test4();
//		test5();
		test5_1();
	}
	
	/*
	 * Q2
	 * Give an int array A[], define distance = A[i] + A[j] + (j - i) j >= i
	 * find the max distance in A[]
	 * 				0   1  2  3  4
	 * index a[5] = {1 ,2, 3, 4, 5}
	 * 
	 * distance = (A[i] - i) + (A[j] + j)
	 * i_max_so_far to store the max value of (A[i] - i)
	 * j is used to go from the right side
	 * 
	 * similar to buying and selling stock
	 * 
	 */
	public static void test2() {
		int[] array = {1,2,3,4,5};
		int rev = task2_max_distance(array);
		System.out.println("rev = " + rev);
	}
	
	public static int task2_max_distance(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int maxDistance = Integer.MIN_VALUE;
		int i_max_so_far = Integer.MIN_VALUE;
		
		int end = -1;
		for(int i = 0; i < array.length; i ++) {
			if (i_max_so_far < array[i] - i) {
				i_max_so_far = array[i] - i;
			}
//			maxDistance = Math.max(maxDistance, i_max_so_far + array[i] + i);
			if (maxDistance < i_max_so_far + array[i] + i) {
				maxDistance = i_max_so_far + array[i] + i;
				end = i;
				
			}
		}
		
		System.out.println("maxDistance = " + maxDistance);
		System.out.println("end = " + end);
		return maxDistance;
	}
	
	
	/*
	 * task3
	 * skyline problem
	 * !!! do later
	 */
	
	/*
	 * task4
	 * given an array ['this', "is", "a", "happy", "fox"], return the smallest indices difference between
	 * two give words.
	 * 
	 * 
	 * prev points the previous given word. 
	 * cur points the current word which is one of the given two words
	 * if array[prev] == array[cur], prev = cur;
	 * if array[prev] != array[cur], get the difference, prev = cur
	 */
	
	public static void test4() {
		String[] input = {"this", "is", "a", "happy", "fox", "this", "this", "happy"};
		String s1 = "this";
		String s2 = "good";
		int rev = task4_smallestIndices(input, s1, s2);
		System.out.println("rev = " + rev);
	}
	public static int task4_smallestIndices(String[] input, String s1, String s2) {
		// sanity check
		if (input == null || input.length == 0) {
			return -1;
		}
		int prev = -1;
		int cur = 0;
		Set<String> set = new HashSet<String>();
		set.add(s1);
		set.add(s2);
		int minDiff = Integer.MAX_VALUE;
		for(; cur < input.length; cur ++) {
			String curStr = input[cur];
			if (set.contains(curStr)) {
				if (prev == -1) {
					prev = cur;
				} else if (input[prev].equals(input[cur])) {
					prev = cur;
				} else {
					minDiff = Math.min(minDiff, cur - prev);
					prev = cur;
				}
			}
		}
		if (minDiff == Integer.MAX_VALUE) {
			return -1;
		}
		
		return minDiff;
		
	}
	
	/*
	 * task5
	 * print out all valid parentheses
	 * 3{}, 2[], 1()
	 * 
	 * similar to print all valid parentheses left = 2, right = 2
	 * with the help of stack
	 */
	public static void test5() {
		ArrayList<String> result = task5_all_valid_parentheses(3, 2, 1);
		System.out.println(result);
	}
	
	public static ArrayList<String> task5_all_valid_parentheses(int a, int b, int c) {
		
		char[] PS = {'{', '}', '[', ']', '(', ')'};
		int[] remaining = {a,a,b,b,c,c};
		int totalLen =a*2 + b*2 + c*2;
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder stb = new StringBuilder();
		LinkedList<Character> st = new LinkedList<Character>(); // store left parentheses
		task5_helper(PS, remaining, totalLen, stb, result, st);
		return result;
		
	}
	
	public static void task5_helper(char[] PS, int[] remaining, int totalLen, 
			StringBuilder stb, ArrayList<String> result, LinkedList<Character> st) {
		// base case
		if (stb.length() == totalLen) {
			// find a result
			result.add(stb.toString());
			return ;
		}
		
		for(int i = 0; i < remaining.length; i ++) {
			if (i % 2 == 0) {
				// add left parentheses
				if (remaining[i] > 0) {
					stb.append(PS[i]);
					st.offerFirst(PS[i]);
					remaining[i] --;
					task5_helper(PS, remaining, totalLen, stb, result, st);
					// trackback
					stb.deleteCharAt(stb.length() - 1);
					st.pollFirst();
					remaining[i] ++;
				}
			} else {
				// add right parentheses
				if (remaining[i] > 0) {
					if (!st.isEmpty() && st.peekFirst().equals(PS[i - 1])) {
						// matches
						// pop the last element in stack
						st.pollFirst();
						remaining[i] --;
						stb.append(PS[i]);
						task5_helper(PS, remaining, totalLen, stb, result, st);
						// trackback
						stb.deleteCharAt(stb.length() - 1);
						remaining[i] ++;
						st.offerFirst(PS[i -1]);
					}
				}
			}
		}
	}
	
	public static void test5_1() {
		int n = 3;
		ArrayList<String> result = task5_1_valid_parentheses(n);
		System.out.println(result);
	}
	public static ArrayList<String> task5_1_valid_parentheses(int n) {
		ArrayList<String> result = new ArrayList<String>();
		
		char[] cur = new char[n*2];
		task5_1_helper(n, n, cur, 0, result);
		return result;
	}
	
	public static void task5_1_helper(int leftR, int rightR, char[] cur,int index, ArrayList<String> result) {
		if (leftR == 0 && rightR == 0) {
			result.add(new String(cur));
			return ;
		}
		if (leftR > 0) {
			cur[index] = '(';
			task5_1_helper(leftR - 1, rightR, cur,index + 1, result);
		}
		
		if (rightR > leftR) { // rightRemaining  > leftRemaining
			cur[index] = ')';
			task5_1_helper(leftR, rightR - 1, cur, index + 1, result);
		}
	}
	
	
	
	/*
	 * task6
	 * array sorting
	 * a1 < a2 > a3 < a4 > a5 < a6
	 * 
	 * <1> sorting in descending order, then swap each pair
	 * <2> just make sure that a[even] > a[even - 1] && a[even] < a[even + 1], so we can make sure that 
	 * the array is sorted in waved way
	 */
	public static void test6() {
		int[] array = {1,2,3};
		task6_waved_sorting(array);
		System.out.println(Arrays.toString(array));
	}
	public static void task6_waved_sorting(int[] array) {
		if (array == null || array.length <= 1) {
			return ;
		}
		for(int i = 0 ; i < array.length; i += 2) {
			if (i > 0 && array[i] > array[i - 1]) {
				swap(array, i, i - 1);
			}
			if (i < array.length - 1 && array[i] > array[i + 1]) {
				swap(array, i, i + 1);
			}
		} 
	}
	
	public static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	
	/*
	 * task7
	 * given an array of integers, how to divide the whole array into two parts, with their sums equals
	 * to each other
	 * 
	 * this can reduce to all set, and get the sum_so_far. 
	 * if sum_so_far * 2 = total_sum, we get one solution. 
	 */
	public static void test7() {
		int[] array = {1,2,3,4};
		ArrayList<Integer> result = task7_divideArray(array);
		System.out.println(result);
	}
	
	
	public static ArrayList<Integer> task7_divideArray(int[] array) {
		int total_sum = 0;
		for(int i = 0; i < array.length; i ++) {
			total_sum += array[i];
		}
		int sum_so_far = 0;
		ArrayList<Integer> cur = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		task6_helper(array, 0, cur, total_sum, sum_so_far, result);
		
		return result;
	}
	public static boolean found = false;
	public static void task6_helper(int[] array, int index, ArrayList<Integer> cur, 
			int total_sum,int sum_so_far, ArrayList<Integer> result) {
		if (found) {
			return;
		} else {
			if (index == array.length) {
				if (sum_so_far * 2 == total_sum) {
					// copy the content of cur to result
					for(Integer i: cur) {
						result.add(i);
					}
					found = true;
				}
				return ;
			}
			// don't choose
			task6_helper(array, index + 1, cur, total_sum, sum_so_far, result);
			// choose
			cur.add(array[index]);
			sum_so_far += array[index];
			task6_helper(array, index + 1, cur, total_sum, sum_so_far, result);
			sum_so_far -= cur.get(cur.size() - 1);
			cur.remove(cur.size() - 1);
		}
	}
	
	
	
	/*
	 * task8
	 * 2D matrix, search, Yong Matrix
	 */
	
	/*
	 * task9
	 * reverse linked list using recursion
	 * 
	 * 1  ->  2  ->  3  ->  4  ->  5
	 * cur   next
	 */
	public static void test9() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		Debug.printLinkedList(n1);
		
		ListNode rev = task9_reverse(n1);
		Debug.printLinkedList(rev);
		
	}
	
	public static ListNode task9_reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode next = head.next;
		ListNode newHead = task9_reverse(next);
		next.next = head;
		head.next = null;
		
		return newHead;
	}
	
	/*
	 * task10
	 * binary tree, all the right nodes are leaf nodex, flip the upside down and turn it into a tree with
	 * left leaf nodes
	 * 
	 * 
	 * 		   1
	 *   	  / \
	 *  	 2   3
	 *      / \
	 *     4   5         
	 *     
	 *     
	 *  root.left.right = root;
	 *  root.left.left = root.right
	 *  root.left = null;
	 *  root.right = null;
	 *  
	 *  base case:
	 *  root == null || root.left == null
	 *  return root;
	 *  
	 *  newRoot = reverse(root.left);
	 *  root.left.right = root;
	 *  root.left.left = root.right;
	 *  
	 *  root.left = null;
	 *  root.right = null;
	 *  
	 */
	public static TreeNode task10_convert(TreeNode node) {
		if (node == null || node.left == null) {
			return node;
		}
		
		TreeNode newRoot = task10_convert(node.left);
		
		node.left.right = node;
		node.left.left = node.right;
		
		node.left = null;
		node.right = null;
		
		return newRoot;
	}
	
	/*
	 * task11
	 * Nested integer weighted sum. 一个list, 元素可能是list,也可能是Integer,
	 * 但是每个元素 都包装在NestedInteger类里面了,求weighted sum. 例子是{2{4{6}}}. 应该返回2×1 + 4×2 + 6×3.
	 * 
	 */
	public static void test11() {
		String input = "(2(4(6)))";
		int rev = task11_getSum(input);
		System.out.println("rev = " + rev);
		
	}
	
	public static int task11_getSum(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		return helper(input, 1, 0);
	}
	
	
	// Notes:Character.isDigit(char)  check whether a char is digit  
	public static int helper(String input, int level, int index) {
		if (input.charAt(index) == ')') {
			return 0;
		}
		if (input.charAt(index) == '(') {
			index ++;
		}
		
		boolean isNeg = false;
		if (input.charAt(index) == '-') {
			isNeg = true;
			index ++;
		}
		
		int val = 0;
		for(; index < input.length(); index ++) {
			if (!Character.isDigit(input.charAt(index))) {
				break;
			}
			val = val* 10 + input.charAt(index) - '0';
		}
		val = val * level;
		if (isNeg) {
			val = -val;
		}
		// go to the next position
		return val + helper(input, level + 1, index);
	}
	
	
	/*
	 * task12
	 * Given a string, return the longest substring that contains at most two(or k) type of characters
	 * 
	 * let do at most k type of characters
	 * fast, slow
	 * input: abbbbbbcbaa, k = 2
	 * output: bbbbbbcb   len = 8
	 * 
	 * map<char, counter>
	 * 
	 * loop: fast
	 *     if (map.contain(cur)) {
	 *     		map.put(cur, map.get(cur) + 1)
	 *     } else {
	 *     		map.put(cur, 1);
	 *     		while (map.size > k) {
	 *     			//move forward the the slow, shrink the window
	 *     			char slowChar = input.charAt(slow);
	 *     			int slowCounter = map.get();
	 *     			map.put(slowChar, slowCounter - 1);
	 *              if (map.get(slowChar) == 0) {
	 *              	map.remove(slowChar);
	 *              }
	 *              slow++;
	 *          }
	 *     }
	 *     // update the maxLen
	 *     maxLen = max(maxLen, fast - slow + 1);
	 *     fast ++;
	 */
	
	public static void test12() {
		String input = "abbbbbbcbaa";
		int k = 2;
		int maxLen = task12_longestSubstringAtMostK(input, k);
		System.out.println("maxLen = " + maxLen);
	}
	
	public static int task12_longestSubstringAtMostK(String input, int k) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		int maxLength = Integer.MIN_VALUE;
		int finalStart = -1;
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// key: character, value: counter of this char
		
		int slow = 0, fast = 0;
		while(fast < input.length()) {
			char curCh = input.charAt(fast);
			if (map.containsKey(curCh)) {
				map.put(curCh, map.get(curCh) + 1);
			} else {
				// first, put it into the map
				map.put(curCh, 1);
				
				// if map.size > k, shrink the slow
				while (map.size() > k) {
					char curSlow = input.charAt(slow);
					map.put(curSlow, map.get(curSlow) - 1);
					if (map.get(curSlow) == 0) {
						map.remove(curSlow);
					}
					slow ++;  // !!! remember to update slow
				}
			}
			
			if (maxLength < fast - slow + 1) {
				maxLength = fast - slow + 1;
				finalStart = slow;
			}
			fast ++;
		}
		
		String subStr = input.substring(finalStart, finalStart + maxLength);
		
		System.out.println(subStr);
		
		return maxLength;
	}
	
	
}
