package lai_online2;
import java.util.*;
import ds.*;
public class Class100 {
	/*
	 * task31
	 
	Search In Shifted Sorted Array II
	Hard
	Data Structure
	Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

	For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

	Assumptions

	There could be duplicate elements in the array.
	Examples

	A = {3, 4, 5, 1, 2}, T = 4, return 1
	A = {3, 3, 3, 1, 3}, T = 1, return 3
	A = {3, 1, 3, 3, 3}, T = 1, return 1
	​Corner Cases

	What if A is null or A is of zero length? We should return -1 in this case.
	*/
	public int search(int[] array, int target) {
		// Write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}
		int n = array.length;
		int start = 0, end = n - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (array[mid] == target) {
				return mid;
			}
			if (array[mid] > array[start]) {
				// the left side is sorted
				if (target >= array[start] && target <= array[mid]) {
					// in the left side
					end = mid;
				} else {
					start = mid;
				}
			} else if (array[mid] < array[start]) {
				// right side is sorted
				if (target >= array[mid] && target <= array[end]) {
					start = mid;
				} else {
					end = mid;
				}
			} else {
				start++;
			}
		}
		if (array[start] == target) {
			return start;
		} else if (array[end] == target) {
			return end;
		} else {
			return -1;
		}
	}
	
	/*
	 * task32
	 
	Selection Sort Linked List
	Hard
	Data Structure
	Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The selectoin sort algorithm should be used to solve this problem.

	Examples

	null, is sorted to null
	1 -> null, is sorted to 1 -> null
	1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
	4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to 2 -> 3 -> 4 -> 5 -> 6
	
	*/
	
	public ListNode task32_selectionSort(ListNode head) {
		// Write your solution here.
		return head;
	}
	
	/*
	 * task33
	 *
	Quick Sort Linked List
	Hard
	Data Structure
	Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The quick sort algorithm should be used to solve this problem.

	Examples

	null, is sorted to null
	1 -> null, is sorted to 1 -> null
	1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
	4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
	*/
	 public ListNode task33_quickSort(ListNode head) {
		    // write your solution here
		    return head;
	}
	 
	 /*
	  * task34
	  
	 ReOrder Linked List
	 Hard
	 Data Structure
	 Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null

	 Examples

	 L = null, is reordered to null
	 L = 1 -> null, is reordered to 1 -> null
	 L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
	 L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
	 */
	public ListNode task34_reorder(ListNode head) {
		// write your solution here

		ListNode mid = findMid(head);

		ListNode second = mid.next;
		mid.next = null;

		ListNode newSecond = reverse(second);

		ListNode firstRunner = head;

		ListNode secondRunner = newSecond;

		while (secondRunner != null) {
			ListNode nextS = secondRunner.next;

			// insert the secondRunner after firstRunner
			secondRunner.next = firstRunner.next;
			firstRunner.next = secondRunner;

			firstRunner = firstRunner.next.next;
			secondRunner = nextS;
		}

		return head;
	}

	public ListNode findMid(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode fast = head.next;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode next = head.next;
		ListNode newHead = reverse(next);

		next.next = head;
		head.next = null;

		return newHead;

	}
	/*
	 * task35
	Max Water Trapped II
	Hard
	Data Structure
	Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. Each bar has 4 neighboring bars to the left, right, up and down side.

	Assumptions

	The given matrix is not null and has size of M * N, where M > 0 and N > 0
	Examples

	{ { 2, 3, 4, 2 },

	  { 3, 1, 2, 3 },

	  { 4, 3, 5, 4 } }

	the amount of water can be trapped is 3, 

	at position (1, 1) there is 2 units of water trapped,

	at position (1, 2) there is 1 unit of water trapped.
	*/
	
	/*
	 * task36
	Majority Number III
	Hard
	Data Structure
	Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

	Assumptions

	The given array is not null or empty
	K >= 2
	Examples

	A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
	A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
	A = {2, 1}, K = 2, return []
	*/
	public List<Integer> task36_majority(int[] array, int k) {
		// write your solution here
		List<Integer> result = new ArrayList<Integer>();
		if (array == null || array.length == 0) {
			return result;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < array.length; i++) {
			int cur = array[i];

			if (map.containsKey(cur)) {
				int count = map.get(cur);
				map.put(cur, count + 1);
			} else {
				// map dones't contains cur, i.e array[i]
				map.put(cur, 1);
				if (map.size() >= k) {
					ArrayList<Integer> keyToDelete = new ArrayList<Integer>();
					for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
						int key = entry.getKey();
						int value = entry.getValue();
						if (value - 1 == 0) {
							keyToDelete.add(key);
						}
						map.put(key, value - 1);
					}
					for (Integer key : keyToDelete) {
						map.remove(key);
					}
				}

			}

		}

		// for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
		// System.out.println(entry.getKey() + " : " + entry.getValue());
		// }

		// after traversal, we have the candidiate, set all candicates' value 0
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			map.put(key, 0);
		}

		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				int value = map.get(array[i]);
				map.put(array[i], value + 1);
			}
		}

		// traverse the map2
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int value = entry.getValue();
			if (value > array.length / k) {
				result.add(entry.getKey());
			}
		}

		return result;
	}
	/*
	 * task37
	Array Hopper IV
	Hard
	Graph
	Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array. A[i] means the maximum jump distance from that position (you can either jump left or jump right). Determine the minimum jumps you need to reach the end of the array. Return -1 if you can not reach the end of the array.

	Assumptions

	The given array is not null and has length of at least 1.
	Examples

	{1, 3, 1, 2, 2}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the end of array)

	{3, 3, 1, 0, 0}, if the initial position is 2, the minimum jumps needed is 2 (jump to index 1 then to the end of array)

	{4, 0, 1, 0, 0}, if the initial position is 2, you are not able to reach the end of array, return -1 in this case.
	*/
	
	public int task37_minJump(int[] array, int index) {
		// write your solution here
		if (array == null || array.length == 0) {
			return -1;
		}

		int n = array.length;
		if (index >= n && index < 0) {
			return -1;
		}
		if (index == n - 1) {
			return 0;
		}
		boolean[][] state = new boolean[n][n];
		state[n - 1][0] = true;
		for (int k = 1; k < n; k++) {
			for (int i = 0; i < n; i++) {
				int leftBound = i - array[i] >= 0 ? i - array[i] : 0;
				int rightBound = i + array[i] <= n - 1 ? i + array[i] : n - 1;
				for (int j = leftBound; j <= rightBound; j++) {
					if (state[j][k - 1] == true) {
						state[i][k] = true;
					}
				}

			}
			if (state[index][k] == true) {
				return k;
			}
		}

		return -1;
	}
	
	/*
	 * task38
	Missing Number II
	Easy
	None
	Given an integer array of size N - 1 sorted by ascending order, containing all the numbers from 1 to N except one, find the missing number.

	Assumptions

	The given array is not null, and N >= 1
	Examples

	A = {1, 2, 4}, the missing number is 3
	A = {1, 2, 3}, the missing number is 4
	A = {}, the missing number is 1
	*/
	public int task38_missing(int[] array) {
		// write your solution here
		if (array == null || array.length == 0) {
			return 1;
		}

		int left = 0, right = array.length - 1;
		if (array[right] == right + 1) {
			return right + 2;
		}

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] > mid + 1) {
				right = mid;
			} else {
				left = mid;
			}
		}

		// System.out.println("left = " + left);
		// System.out.println("right = " + right);
		if (array[left] > left + 1) {
			return left + 1;
		} else {
			return right + 1;
		}
	}
	
	/*
	 * task39
	
	Binary Tree Diameter
	Fair
	Recursion
	Given a binary tree in which each node contains an integer number. The diameter is defined as the longest distance from one leaf node to another leaf node. The distance is the number of nodes on the path.

	If there does not exist any such paths, return 0.

	Examples

	    5

	  /    \

	2      11

	     /    \

	    6     14

	The diameter of this tree is 4 (2 → 5 → 11 → 14)

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
	
	public int task39_diameter(TreeNode root) {
		// Write your solution here.
		if (root == null) {
			return 0;
		}
		Max max = new Max(0);
		helper(root, max);

		return max.val;
	}

	public class Max {
		public int val;

		public Max(int v) {
			this.val = v;
		}
	}

	public int helper(TreeNode node, Max max) {
		if (node == null) {
			return 0;
		}
		int leftHeight = helper(node.left, max);
		int rightHeight = helper(node.right, max);

		int tempDia = leftHeight + rightHeight + 1;
		if (node.left != null && node.right != null && max.val < tempDia) {
			max.val = tempDia;
		}
		if (node.left == null) {
			return rightHeight + 1;
		} else if (node.right == null) {
			return leftHeight + 1;
		}
		return Math.max(leftHeight, rightHeight) + 1;

	}
	
	/*
	 * task40
	Reconstruct Binary Tree With Postorder And Inorder
	Fair
	Recursion
	Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

	Assumptions

	The given sequences are not null and they have the same length
	There are no duplicate keys in the binary tree
	Examples

	postorder traversal = {1, 4, 3, 11, 8, 5}

	inorder traversal = {1, 3, 4, 5, 8, 11}

	the corresponding binary tree is

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	How is the binary tree represented?

	We use the pre order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	 */
	public TreeNode task40_reconstruct(int[] in, int[] post) {
		// Write your solution here.
		return null;
	}
	
	/*
	 * task41
	Lowest Common Ancestor III
	Hard
	Recursion
	Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).

	Return null If any of the nodes is not in the tree.

	Assumptions

	There is no parent pointer for the nodes in the binary tree

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
	
	public TreeNode task41_lowestCommonAncestor(TreeNode root, TreeNode one,
			TreeNode two) {
		// write your solution here
		if (root == null) {
			return null;
		}
		if (one == null || two == null) {
			return null;
		}
		Wrapper wrapper = new Wrapper(false, false);
		exist(root, one, two, wrapper);
		if (wrapper.firstExist == false || wrapper.secondExist == false) {
			return null;
		} else {
			// both nodes are in root.
			return LCAHelper(root, one, two);
		}

	}

	public class Wrapper {
		public boolean firstExist;
		public boolean secondExist;

		public Wrapper(boolean x, boolean y) {
			this.firstExist = x;
			this.secondExist = y;
		}
	}

	public void exist(TreeNode root, TreeNode one, TreeNode two, Wrapper wrapper) {
		if (root == null) {
			return;
		}
		if (root == one) {
			wrapper.firstExist = true;
		}
		if (root == two) {
			wrapper.secondExist = true;
		}
		exist(root.left, one, two, wrapper);
		exist(root.right, one, two, wrapper);
	}

	public TreeNode LCAHelper(TreeNode root, TreeNode one, TreeNode two) {
		// write your solution here
		if (root == null) {
			return null;
		}
		if (root == one) {
			return root;
		}
		if (root == two) {
			return root;
		}
		TreeNode left = LCAHelper(root.left, one, two);
		TreeNode right = LCAHelper(root.right, one, two);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}
	
	/*
	 * task42
	 
	Encode Space
	Easy
	String
	In URL encoding, whenever we see an space " ", we need to replace it with "20%". Provide a method that performs this encoding for a given string.

	Examples

	"google/q?flo wer market" → "google/q?flo20%wer20%market"
	Corner Cases

	If the given string is null, we do not need to do anything.
	*/
	public String task42_encode(String input) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return input;
		}

		// get new length
		int counter = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				counter++;
			}
		}

		int newLen = input.length() + 2 * counter;

		char[] array = new char[newLen];

		// from right hand to left hand. replace ' ' with "20%"
		int end = newLen - 1;
		String target = "20%";
		for (int i = input.length() - 1; i >= 0; i--) {
			char curChar = input.charAt(i);
			if (curChar == ' ') {
				for (int j = target.length() - 1; j >= 0; j--) {
					array[end--] = target.charAt(j);
				}
			} else {
				array[end--] = curChar;
			}
		}

		return new String(array);
	}
	
	/*
	 * task43
	
	Remove Adjacent Repeated Characters II
	Fair
	String
	Remove adjacent, repeated characters in a given string, leaving only two characters for each group of such characters. The characters in the string are sorted in ascending order.

	Assumptions

	Try to do it in place.
	Examples

	“aaaabbbc” is transferred to “aabbc”
	Corner Cases

	If the given string is null, we do not need to do anything.
	*/
	public String task43_deDup(String input) {
		// write your solution here
		if (input == null || input.length() <= 2) {
			return input;
		}
		char[] strArr = input.toCharArray();
		int s = 2, f = 2;
		while (f < strArr.length) {
			if (strArr[f] == strArr[s - 2]) {
				f++;
			} else {
				strArr[s++] = strArr[f++];
			}
		}

		return new String(strArr, 0, s);
	}
	
	/*
	 * task44
	 * 
	
	Remove Adjacent Repeated Characters III
	Fair
	String
	Remove adjacent, repeated characters in a given string, leaving no character for each group of such characters. The characters in the string are sorted in ascending order.

	Assumptions

	Try to do it in place.
	Examples

	“aaaabbbc” is transferred to “c”
	Corner Cases

	If the given string is null, we do not need to do anything.
	*/
	
	public String task44_deDup(String input) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return input;
		}
		boolean flag = false;
		char[] strArr = input.toCharArray();
		int s = 0, f = 1;
		while (f < strArr.length) {
			if (strArr[f] == strArr[s]) {
				flag = true;
				f++;
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
					s++;
					strArr[s] = strArr[f];

				}
				f++;
			}
		}
		// check the last element
		if (!flag) {
			s++;
		}

		return new String(strArr, 0, s);
	}
	
	/*
	 * task45
	
	Decompress String I
	Fair
	String
	Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.

	Assumptions

	The string is not null

	The characters used in the original string are guaranteed to be ‘a’ - ‘z’

	There are no adjacent repeated characters with length > 9

	Examples

	“acb2c4” → “acbbcccc”
	*/
	public String task45_decompress(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		int newLen = getNewLength(input);
		char[] array = new char[newLen];

		int end = newLen - 1;
		for (int i = input.length() - 1; i >= 0; i--) {
			if (isChar(input.charAt(i))) {
				if (i == input.length() - 1) {
					// the last element
					array[end--] = input.charAt(i);
				} else {
					int j = i + 1;
					while (j < input.length() && isNum(input.charAt(j))) {
						j++;
					}
					String counter_str = input.substring(i + 1, j);
					if (counter_str.length() > 0) {
						int counter = Integer.parseInt(counter_str);
						while (counter > 0) {
							array[end--] = input.charAt(i);
							counter--;
						}
					} else {
						array[end--] = input.charAt(i);
					}

				}
			}
		}

		String rev = new String(array);
		return rev;
	}

	public int getNewLength(String input) {
		int counter = 0;
		int s = 0, f = 1;

		// int curCounter = 1;
		while (f < input.length()) {
			if (isChar(input.charAt(f))) {
				//
				counter++;
			} else {

				while (f < input.length() && isNum(input.charAt(f))) {
					f++;
				}
				String counter_str = input.substring(s + 1, f);
				System.out.println("counter_str = " + counter_str);
				counter += Integer.parseInt(counter_str);
			}
			s = f;
			f++;
		}

		// check the last char is number or char, if char, ++, if number, do
		// nothing
		if (isChar(input.charAt(input.length() - 1))) {
			counter++;
		}
		System.out.println("s = " + s);
		System.out.println("f = " + f);
		return counter;
	}

	public boolean isChar(char ch) {
		return ch >= 'a' && ch <= 'z';
	}

	public boolean isNum(char ch) {
		return ch >= '0' && ch <= '9';
	}
	
	/*
	 * task46
	Compress String
	Hard
	String
	Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.

	Assumptions

	The string is not null

	The characters used in the original string are guaranteed to be ‘a’ - ‘z’

	There are no adjacent repeated characters with length > 9

	Examples

	“abbcccdeee” → “ab2c3de3”
	*/
	
	public String task46_compress(String input) {
		// write your solution here
		if (input == null || input.length() == 0) {
			return input;
		}
		int end = 0;
		int counter = 1; // the counter
		int candidateIndex = 0;
		char[] array = input.toCharArray();
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[candidateIndex]) {
				counter++;
				System.out.println("counter = " + counter);
			} else {

				// array[i] != array[candidatesIndex]
				// now, i points the next candidate's index
				// put the candidates into array[end]
				array[end++] = array[candidateIndex];

				if (counter > 1) {
					// put the counter into the candidates
					String temp = Integer.toString(counter);

					for (int j = 0; j < temp.length(); j++) {
						array[end++] = temp.charAt(j);
					}

				}
				candidateIndex = i;
				counter = 1;

			}
		}

		// the last type of char in original string.
		array[end++] = array[candidateIndex];
		if (counter > 1) {
			String temp = Integer.toString(counter);
			for (int j = 0; j < temp.length(); j++) {
				array[end++] = temp.charAt(j);
			}
		}
		return new String(array, 0, end);
	}

}
