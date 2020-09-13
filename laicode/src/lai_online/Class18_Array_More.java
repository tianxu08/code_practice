package lai_online;

import java.util.*;
import java.util.Map.Entry;

import debug.Debug;
import ds.*;

public class Class18_Array_More {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test4();
//		test7();
//		System.out.println("--------------");
//		test9();
		test16();
		
	}

	/*
	 * task1: Array Duplications I(sorted array, duplicate elemnet only retain one)
	 * taks2: Array Duplications II(sorted array, duplicate elemnet only retain two)
	 * task3: Array Duplications III(sorted array, duplicate element not retain any)
	 * task4: Array Duplications IV (unsorted, remove adjacent duplicate elements repeatedly,)
	 * 
	 * task5: LinkedList DeduplicatesI(sorted, duplicate element only retain one)
	 * task6: LinkedList DeduplicatesII(sorted, duplicate element retain two)
	 * task7: LinkedList DeduplicatesIII(sorted, duplicate element not retain any)
	 * 
	 * task8: Largest and Smallest
	 * task9: Largest and Second Largest
	 * 
	 * 
	 * task10: Spiral Order Print
	 * task11: Rotate Matrix by 90 Degree Clockwise
	 * 
	 * task12: Zig-zag Order Print Binary Tree
	 * 
	 * task13: Lowest Common Ancestor(without parent pointer)
	 * task14: Lowest Common Ancestor(with parent pointer)
	 * task15: Lowest Common Ancestor of K nodes
	 * 
	 */
	
	
	/*
	 * task1 Array Deduplication I  
	 * <Duplicate only retain once>
	 * Given a sorted integer
	 * array, remove duplicate elements. For each group of elements with the
	 * same value keep only one of them. Do this in-place, using the left side
	 * of the original array and maintain the relative order of the elements of
	 * the array. Return the array after deduplication.
	 * 
	 * Assumptions
	 * 
	 * The array is not null Examples
	 * 
	 * {1, 2, 2, 3, 3, 3} → {1, 2, 3}
	 */
	public static int[] task1_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return array;
		}
		int start = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[start]) {
				array[++start] = array[i];
			}
		}
		return Arrays.copyOf(array, start + 1);

	}

	
	/*
	 * task2 Array Deduplication II 
	 * <Keep at most two of the duplicates>
	 * Fair Data Structure Given a sorted integer
	 * array, remove duplicate elements. For each group of elements with the
	 * same value keep at most two of them. Do this in-place, using the left
	 * side of the original array and maintain the relative order of the
	 * elements of the array. Return the array after deduplication.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
	 */
	/*
	 * 1 2 2 3 3 3 3 4 4 | s
	 */

	
	public static int[] task2_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 2) {
			return array;
		}
		int start = 0;
		
		int count = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[start]) {
				array[++start] = array[i];
				count = 1;
			} else {
				if (count < 2) {
					array[++start] = array[i];
					count++;
				}
			}
		}

		return Arrays.copyOf(array, start + 1);
	}
	
	public static void test2() {
		int[] array = {1,1,2,2,3,3,4};
//		int[] result = task2_dedup2(array);
//		System.out.println(Arrays.toString(result));
		int[] result2 = task2_dedup(array);
		System.out.println(Arrays.toString(result2));
		
	}
	
	public static int[] task2_dedup2(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int start = 2;
		for(int i = 2; i < array.length; i ++) {
			if (array[i] != array[start - 2]) {
				array[start] = array[i];
				start ++;
			}
		}
		return Arrays.copyOf(array, start);
	}

	/*
	 * task3 
	 * Array Deduplication III
	 * <Don't keep any of the duplicate letters> 
	 * Given a sorted integer
	 * array, remove duplicate elements. For each group of elements with the
	 * same value do not keep any of them. 
	 * 
	 * Do this in-place, using the left side
	 * of the original array and and maintain the relative order of the elements
	 * of the array. Return the array after deduplication.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * {1, 2, 2, 3, 3, 3} → {1}
	 */

	public int[] task3_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 1) {
			return array;
		}
		int start = 0;
		boolean flag = false;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[start]) {
				flag = true;
			} else {
				// array[i] != array[start]
				if (flag == false) {
					// the candidates doesn't have duplicate
					// we put the next candidate in next position and set flag
					// == false
					array[++start] = array[i];
				} else {
					// flag == true
					// the candidates has duplicates.
					// it can no longer put here. put another candidate, and set
					// flag == false
					array[start] = array[i];
					flag = false;
				}
			}
		}
		
		// the last element
		if (flag == false) {
			start++;
		}
		return Arrays.copyOf(array, start);

	}
	
	
	
	 /*
	  * task4
	  * Array Deduplication IV
	  * <Unsorted Array, repeatedly deduplication>
	  * 
	  * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. 
	  * For each group of elements with the same value do not keep any of them.
	  * Do this in-place, using the left side of the original array. Return the array after deduplication.
	  * Assumptions
	  * The given array is not null
	  * Examples
	  * {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
	  */
	public static void test4() {
		int[] array = {1,2,3,3,3,2,2};
		int[] rev = task4_dedup(array);
		System.out.println(Arrays.toString(rev));
	}
	
	public static int[] task4_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 1) {
			return array;
		}
		int end = -1;
		for(int i = 0; i < array.length; i ++) {
			if (end == -1 || array[end] != array[i]) {
				array[++end] = array[i];
			} else {
				// end != -1 && array[end] == array[i]
				// go forward to see whether there are more duplicates
				while(i + 1 < array.length && array[i + 1] == array[end]) {
					i ++;
				}
				// end --, since the candidate has deduplicate
				end --;
			}
		}
		return Arrays.copyOf(array, end +1);
	}
	 
	
	
	/*
	 * task5: LinkedList DeduplicatesI(sorted, duplicate element only retain
	 * one)
	 * 
	 * input: 1 -> 2 -> 2 ->3 ->3 ->4
	 * output: 1 -> 2 ->3 ->4
	 */
	public static ListNode task5_dedup_linkedlist_retain_one(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null) {
			while(fast != null && fast.value == slow.value) {
				fast = fast.next;
			}
			// here, fast.val != slow.val
			// add slow to tail
			slow.next = null;
			tail.next = slow;
			tail = tail.next;
			
			// update slow
			slow = fast;
		}
		return dummy.next;
	}
	
	
	/*
	 * task6: LinkedList DeduplicatesII(sorted, duplicate element retain two)
	 */
	
	

	/*
	 * task7: LinkedList DeduplicatesIII(sorted, duplicate element not retain
	 * any)
	 */
	public static void test7() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(3);
		ListNode n6 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		
		Debug.printLinkedList(n1);
		
		ListNode rev = task7_dedup_list_retain_none(n1);
		Debug.printLinkedList(rev);
	}
	
	
	public static ListNode task7_dedup_list_retain_none(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		boolean flag = false; // show that current candidate(slow) has
		// duplicates or not
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		ListNode tail = dummy;

		ListNode slow = head, fast = head.next;
		
		// slow is the candidate
		while(fast != null) {
			System.out.println("fastVal = " + fast.value);

			if (fast.value == slow.value) {
				flag = true;
			} else {
				// fast.val != slow.val
				if (flag == false) {
					// the candidate doesn't have duplicate
					slow.next = null;
					tail.next = slow;
					tail = tail.next;
					
					// update slow
					slow = fast;
				} else {
					// the candidate duplicate, take the next candidate
					// flag = true
					slow = fast;
					flag = false;
				}
			}
			// update fast
			fast = fast.next;			
		}
		// append the last

		if (flag == false && slow !=  null) {
			slow.next = null;
			tail.next = slow;
		}
	
		return dummy.next;	
	}
	
	
	

	
	
	/*
	 * task2 Move 0s To The End II 
	 * Given an array of
	 * integers, move all the 0s to the right end of the array.
	 * 
	 * The relative order of the elements in the original array need to be
	 * maintained.
	 * 
	 * Assumptions:
	 * 
	 * The given array is not null. Examples:
	 * 
	 * {1} --> {1} {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
	 */
	public static int[] moveZero(int[] array) {
		// Write your solution here.
		if (array == null || array.length == 0) {
			return array;
		}
		int s = 0, f = 0;
		for (; f < array.length; f++) {
			if (array[f] != 0) {
				array[s++] = array[f];
			}
		}
		for (int i = s; i < array.length; i++) {
			array[i] = 0;
		}

		return array;

	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	

	/*
	 * task8 
	 * Largest And Smallest 
	 * Fair Data Structure 
	 * Use the least number of
	 * comparisons to get the largest and smallest number in the given integer
	 * array. Return the largest number and the smallest number.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and has length of at least 1 Examples
	 * 
	 * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1.
	 */
	public static int[] task8_largestAndSmallest(int[] a) {
		// write your solution here
		if (a == null || a.length < 1)
			return null;

		int min, max;

		// if only one element
		if (a.length == 1) {
			max = a[0];
			min = a[0];
			return new int[] { max, min };
		}

		if (a[0] > a[1]) {
			max = a[0];
			min = a[1];
		} else {
			max = a[1];
			min = a[0];
		}
		for (int i = 2; i <= a.length - 2; i = i + 2) {
			if (a[i] > a[i + 1]) { // # of comparison (n-2)/2
				if (min > a[i + 1]) {
					min = a[i + 1];
				}
				if (max < a[i]) {
					max = a[i];
				}
			} else {
				// a[i] <= a[i + 1]
				if (min > a[i]) {
					min = a[i];
				}
				if (max < a[i + 1]) {
					max = a[i + 1];
				}
			}
		}
		if (a.length % 2 == 1) {
			// we need to compare with the last element
			if (min > a[a.length - 1]) {
				min = a[a.length - 1];
			}
			if (max < a[a.length - 1]) {
				max = a[a.length - 1];
			}
		}
		return new int[] { max, min };
	}
	
	
	

	 /*
	  * task9
	  * Largest And Second Largest 
	  * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. 
	  * Return the largest number and 2nd largest number.
	  * Assumptions
	  * The given array is not null and has length of at least 2
	  * Examples
	  * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
	  */
	
	public static void test9() {
		int[] array = {2,1,5,4,3};
		int[] result = task9_largestAndSecond(array);
		System.out.println(Arrays.toString(result));
	}
	public static int[] task9_largestAndSecond(int[] array) {
		// write your solution here
		// return a Pair object, the field first is the largest
		if (array == null || array.length == 0) {
			return null;
		}
		List<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < array.length; i++) {
			// pair<index, value>
			list.add(new Pair(i, array[i]));
		}
		// <Index, Smaller list of the value of Index>
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while (list.size() > 1) {
			List<Pair> nextRound = new ArrayList<Pair>();
			for (int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);

					if (p1.value <= p2.value) {
						// p2 win, add p2 to the nextRound
						nextRound.add(p2);
						// if p2.index is not in map
						if (!map.containsKey(p2.index)) {
							map.put(p2.index, new ArrayList<Integer>());
						}
						// add p2's smaller to p2's smaller list
						map.get(p2.index).add(p1.value);
					} else {
						// p1 win
						nextRound.add(p1);
						if (!map.containsKey(p1.index)) {
							map.put(p1.index, new ArrayList<Integer>());
						}
						// add p1's smaller to p1's smaller list
						map.get(p1.index).add(p2.value);
					}
				} else {
					// add the last pair into nextRound if list.size() is odd
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;

		}
		// the second Max is must be in the final winner's smaller list
		int secondMax = max(map.get(list.get(0).index));
		// System.out.println("secondMax = " + secondMax);
		return new int[] { list.get(0).value, secondMax };
	}

	
	public static int max(List<Integer> list) {
		int max = list.get(0);
		for (Integer i : list) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public static  class Pair {
		public int index;
		public int value;

		public Pair(int x, int y) {
			this.index = x;
			this.value = y;
		}
	}
	
	

	
	
	
	
	/*
	 * task11
	 * Rotate Matrix Fair Data Structure Rotate an N * N matrix clockwise
	 * 90 degrees.
	 * 
	 * Assumptions
	 * 
	 * The matrix is not null and N >= 0 Examples
	 * 
	 * { {1, 2, 3}
	 * 
	 * {8, 9, 4},
	 * 
	 * {7, 6, 5} }
	 * 
	 * after rotation is
	 * 
	 * { {7, 8, 1}
	 * 
	 * {6, 9, 2},
	 * 
	 * {5, 4, 3} }
	 */
	public static void task11_rotate(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return;
		}
		int n = matrix.length;
		int midRow = (n - 1) / 2;
		// swap by n/2
		for (int i = 0; i <= midRow; i++) {
			int sym_i = n - 1 - i;
			for (int j = 0; j < n; j++) {
				swap(matrix, i, j, sym_i, j);
			}
		}
		// swap by diag
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				swap(matrix, i, j, j, i);
			}
		}
	}

	public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
		int temp = matrix[x1][y1];
		matrix[x1][y1] = matrix[x2][y2];
		matrix[x2][y2] = temp;
	}
	
	// another way
	
	
	
	/*
	 * task12 Get Keys In Binary Tree Layer By Layer Zig-Zag Order Fair Data
	 * Structure Get the list of keys in a given binary tree layer by layer in
	 * zig-zag order.
	 * 
	 * Examples
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 3 8
	 * 
	 * / \ \
	 * 
	 * 1 4 11
	 * 
	 * the result is [5, 3, 8, 11, 4, 1]
	 * 
	 * Corner Cases
	 * 
	 * What if the binary tree is null? Return an empty list in this case. How
	 * is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#"
	 * denoting the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 * 1
	 * 
	 * / \
	 * 
	 * 2 3
	 * 
	 * /
	 * 
	 * 4
	 */

	public static List<Integer> task12_zigZag(TreeNode root) {
		// Write your solution here.
		return new ArrayList<Integer>();
	}
	
	
	
	
	
	/* 
	 * task13: Lowest Common Ancestor(without parent pointer)
	 */
	public static TreeNode task13_LCA_No_ParentPointer(TreeNode root, TreeNode n1,TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (root == n1 || root == n2) {
			return root;
		}
		
		TreeNode left = task13_LCA_No_ParentPointer(root.left, n1, n2);
		TreeNode right = task13_LCA_No_ParentPointer(root.right, n1, n2);
		
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	
	/*
	 * task14 Lowest Common Ancestor II Fair Data Structure Given two nodes in a
	 * binary tree (with parent pointer available), find their lowest common
	 * ancestor.
	 * 
	 * Assumptions
	 * 
	 * There is parent pointer for the nodes in the binary tree
	 * 
	 * The given two nodes are not guaranteed to be in the binary tree
	 * 
	 * Examples
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 9 12
	 * 
	 * / \ \
	 * 
	 * 2 3 14
	 * 
	 * The lowest common ancestor of 2 and 14 is 5
	 * 
	 * The lowest common ancestor of 2 and 9 is 9
	 * 
	 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
	 */
	/**
	 * public class TreeNodeP { public int key; public TreeNodeP left; public
	 * TreeNodeP right; public TreeNodeP parent; public TreeNodeP(int key,
	 * TreeNodeP parent) { this.key = key; this.parent = parent; } }
	 */
	public static TreeNodeP task14_lowestCommonAncestor_With_ParentPointer(TreeNodeP one,
			TreeNodeP two) {
		// write your solution here
		if (one == null || two == null) {
			return null;
		}
		TreeNodeP ptr1 = one;
		TreeNodeP ptr2 = two;
		while (ptr1 != null && ptr1.parent != null) {
			ptr1 = ptr1.parent;
		}
		while (ptr2 != null && ptr2.parent != null) {
			ptr2 = ptr2.parent;
		}

		if (ptr1 != ptr2) {
			// they are not in the same tree.
			return null;
		} else {
			// they are in the same tree.
			int l1 = length(one);
			int l2 = length(two);

			if (l1 > l2) {
				return LCA(one, two, l1 - l2);
			} else {
				return LCA(two, one, l2 - l1);
			}
		}
	}

	public static TreeNodeP LCA(TreeNodeP large, TreeNodeP small, int diff) {

		while (diff > 0) {

			large = large.parent;
			diff--;
		}

		while (large != small) {

			large = large.parent;
			small = small.parent;

		}
		return large;
	}

	public static int length(TreeNodeP node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.parent;
		}
		return length;
	}
	
	
	/*
	 * task15
	 * 
	 * Lowest Common Ancestor IV
	 * Lowest Common Ancestor of K nodes
	 * Given K nodes in a binary tree, find their lowest common ancestor.
	 * Assumptions K >= 2
	 * There is no parent pointer for the nodes in the binary tree
	 * The given two nodes are guaranteed to be in the binary tree
	 * Examples
	 *         5
	 *       /   \
	 *     9     12
	 *   /  \      \
	 *  2    3      14
	 *  The lowest common ancestor of 2, 3, 14 is 5
	 *  The lowest common ancestor of 2, 3, 9 is 9
	 */

	public static TreeNode task15_lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
		// write your solution here
		if (root == null) {
			return null;
		}
		for (TreeNode n : nodes) {
			if (root == n) {
				return root;
			}
		}
		TreeNode left = task15_lowestCommonAncestor(root.left, nodes);
		TreeNode right = task15_lowestCommonAncestor(root.right, nodes);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	
	/*
	 * task16
	 * 
	 * Sort In Specified Order Fair Data Structure 
	 * Given two integer
	 * arrays A1 and A2, sort A1 in such a way that the relative order among the
	 * elements will be same as those are in A2. For the elements that are not
	 * in A2, append them in the right end of the A1 in an ascending order.
	 * 
	 * Assumptions: A1 and A2 are both not null. There are no duplicate elements
	 * in A2. Examples: 
	 * A1 = {2, 1, 2, 5, 7, 1, 9, 3}, 
	 * A2 = {2, 1, 3}, 
	 * A1 is 
	 * sorted to {2, 2, 1, 1, 3, 5, 7, 9}
	 */
	
	/*
	 * method1
	 * use hashMap
	 * 1. traverse A1, <Key: number, Value: count of number>
	 * 2. traverse A2, put the corresponding elements into A1
	 * 3. Sort the remaining elements in hashMap and put them into A1
	 */
	public static void test16() {
		int[] A1 = {2, 1, 2, 5, 7, 1, 9, 3};
		System.out.println(Arrays.toString(A1));
		int[] A2 = {2, 1, 3};
		
		int[] rev = task16_sortSpecial_hashMap(A1, A2);
		System.out.println(Arrays.toString(rev));
	}
	
	public static int[] task16_sortSpecial_hashMap(int[] A1, int[] A2) {
		// Write your solution here.
		// edge case
		if (A1 == null || A2 == null || A1.length == 0 || A2.length == 0) {
			return A1;
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < A1.length; i ++) {
			if (!map.containsKey(A1[i])) {
				map.put(A1[i], 0);
			}
			map.put(A1[i], map.get(A1[i]) + 1);
		}
		
		int index = 0;
		for(int i = 0; i < A2.length; i ++) {
			int curKey = A2[i];
			if (map.containsKey(curKey)) {
				int curCounter = map.get(curKey);
				for(int j = 0; j < curCounter; j ++) {
					A1[index ++] = curKey;
				}
				map.remove(curKey);
			}
		}
		
		int startIndex = index;
		int endIndex = A1.length - 1;
		
		for(Entry<Integer, Integer> entry: map.entrySet()) {
			A1[index ++] = entry.getKey();
		}
		Arrays.sort(A1, startIndex, endIndex);
		
		return A1;
	}
	
	
	
	
	
	 
	
	
	 
	
	 
	
	 
		
	
	


}
