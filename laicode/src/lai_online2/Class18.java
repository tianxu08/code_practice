package lai_online2;
import ds_lai_online2.*;
import java.util.*;

public class Class18 {
	/*
	 * task1
	
	Array Deduplication I
	Easy
	Data Structure
	Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

	Assumptions

	The array is not null
	Examples

	{1, 2, 2, 3, 3, 3} → {1, 2, 3}
	*/
	public int[] task1_dedup(int[] array) {
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
	 * task2
	Move 0s To The End II
	Easy
	Data Structure
	Given an array of integers, move all the 0s to the right end of the array.

	The relative order of the elements in the original array need to be maintained.

	Assumptions:

	The given array is not null.
	Examples:

	{1} --> {1}
	{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
	
	*/
	public int[] moveZero(int[] array) {
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

	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/*
	 * task3
	Get Keys In Binary Tree Layer By Layer Zig-Zag Order
	Fair
	Data Structure
	Get the list of keys in a given binary tree layer by layer in zig-zag order.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	 1     4        11

	the result is [5, 3, 8, 11, 4, 1]

	Corner Cases

	What if the binary tree is null? Return an empty list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	    
	*/
	public static List<Integer> task3_zigZag(TreeNode root) {
		// Write your solution here.
		return new ArrayList<Integer>();
	}
	
	/*
	 * task4
	Array Deduplication II
	Fair
	Data Structure
	Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

	Assumptions

	The given array is not null
	Examples

	{1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
	*/
	public int[] task4_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 2) {
			return array;
		}
		int start = 0;
		// for(int i = 2; i < array.length; i ++) {
		// if (array[start - 2] != array[i]) {
		// array[start] = array[i];
		// start ++;
		// }
		// }
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
	
	/*
	 * task5
	Array Deduplication III
	Fair
	Data Structure
	Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

	Assumptions

	The given array is not null
	Examples

	{1, 2, 2, 3, 3, 3} → {1}
	*/
	public int[] task5_dedup(int[] array) {
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
		if (flag == false) {
			start++;
		}
		return Arrays.copyOf(array, start);

	}
	
	/*
	 * task6
	Largest And Smallest
	Fair
	Data Structure
	Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.

	Assumptions

	The given array is not null and has length of at least 1
	Examples

	{2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1.
	*/
	public int[] task6_largestAndSmallest(int[] a) {
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
	task7
	Rotate Matrix
	Fair
	Data Structure
	Rotate an N * N matrix clockwise 90 degrees.

	Assumptions

	The matrix is not null and N >= 0
	Examples

	{ {1,  2,  3}

	  {8,  9,  4},

	  {7,  6,  5} }

	after rotation is

	{ {7,  8,  1}

	  {6,  9,  2},

	  {5,  4,  3} }
	*/
	
	public void task7_rotate(int[][] matrix) {
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

	public void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
		int temp = matrix[x1][y1];
		matrix[x1][y1] = matrix[x2][y2];
		matrix[x2][y2] = temp;
	}
	
	/*
	 * task8
	Lowest Common Ancestor II
	Fair
	Data Structure
	Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

	Assumptions

	There is parent pointer for the nodes in the binary tree

	The given two nodes are not guaranteed to be in the binary tree

	Examples

	        5

	      /   \

	     9     12

	   /  \      \

	  2    3      14

	The lowest common ancestor of 2 and 14 is 5

	The lowest common ancestor of 2 and 9 is 9

	The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
	*/
	
	public TreeNodeP task8_lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
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

	public TreeNodeP LCA(TreeNodeP large, TreeNodeP small, int diff) {

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

	public int length(TreeNodeP node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.parent;
		}
		return length;
	}
	
	/*
	 * task9
	Sort In Specified Order
	Fair
	Data Structure
	Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.

	For the elements that are not in A2, append them in the right end of the A1 in an ascending order.

	Assumptions:

	A1 and A2 are both not null.
	There are no duplicate elements in A2.
	Examples:

	A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
	*/
	public int[] task9_sortSpecial(int[] A1, int[] A2) {
		    // Write your solution here.
		    return A1;
    }
	
	/*
	 * task10	
	Array Deduplication IV
	Hard
	Data Structure
	Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

	Do this in-place, using the left side of the original array. Return the array after deduplication.

	Assumptions

	The given array is not null
	Examples

	{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
	*/
	public int[] task10_dedup(int[] array) {
		// write your solution here
		if (array == null || array.length <= 1) {
			return array;
		}
		LinkedList<Integer> st = new LinkedList<Integer>();
		st.offer(array[0]);
		int i = 1;
		while (i < array.length) {
			if (!st.isEmpty() && array[i] == st.peek()) {
				while (i < array.length && array[i] == st.peek()) {
					i++;
				}
				// pop out st.peek();
				st.poll();
			} else {
				st.push(array[i]);
				i++;
			}
		}
		// System.out.println(st.size());
		int[] result = new int[st.size()];
		for (int j = st.size() - 1; j >= 0; j--) {
			result[j] = st.poll();
		}
		// System.out.println(result.length);
		return result;
	}
	
	
	/*
	task11
	Largest And Second Largest
	Hard
	Data Structure
	Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. Return the largest number and 2nd largest number.

	Assumptions

	The given array is not null and has length of at least 2
	Examples

	{2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
	*/
	
	public int[] task11_largestAndSecond(int[] array) {
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
		//
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while (list.size() > 1) {
			List<Pair> nextRound = new ArrayList<Pair>();
			for (int i = 0; i < list.size(); i += 2) {
				if (i + 1 < list.size()) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(i + 1);

					if (p1.second <= p2.second) {
						// p2 win, add p2 to the nextRound
						nextRound.add(p2);
						// if this
						if (!map.containsKey(p2.first)) {
							map.put(p2.first, new ArrayList<Integer>());
						}
						// add p2's smaller to p2's smaller list
						map.get(p2.first).add(p1.second);
					} else {
						// p1 win
						nextRound.add(p1);
						if (!map.containsKey(p1.first)) {
							map.put(p1.first, new ArrayList<Integer>());
						}
						// add p1's smaller to p1's smaller list
						map.get(p1.first).add(p2.second);
					}
				} else {
					// add the last pair into nextRound if list.size() is odd
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;

		}
		int secondMax = max(map.get(list.get(0).first));
		// System.out.println("secondMax = " + secondMax);
		return new int[] { list.get(0).second, secondMax };
	}

	public int max(List<Integer> list) {
		int max = list.get(0);
		for (Integer i : list) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public class Pair {
		public int first;
		public int second;

		public Pair(int x, int y) {
			this.first = x;
			this.second = y;
		}
	}
	/*
	task12
	Lowest Common Ancestor IV
	Hard
	Recursion
	Given K nodes in a binary tree, find their lowest common ancestor.

	Assumptions

	K >= 2

	There is no parent pointer for the nodes in the binary tree

	The given two nodes are guaranteed to be in the binary tree

	Examples

	        5

	      /   \

	     9     12

	   /  \      \

	  2    3      14

	The lowest common ancestor of 2, 3, 14 is 5

	The lowest common ancestor of 2, 3, 9 is 9
	*/
	public TreeNode task12_lowestCommonAncestor(TreeNode root,
			List<TreeNode> nodes) {
		// write your solution here
		if (root == null) {
			return null;
		}
		for (TreeNode n : nodes) {
			if (root == n) {
				return root;
			}
		}
		TreeNode left = task12_lowestCommonAncestor(root.left, nodes);
		TreeNode right = task12_lowestCommonAncestor(root.right, nodes);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
}
