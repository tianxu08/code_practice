package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class10 {
	/*
	 * task1
	Reverse Linked List In Pairs
	Fair
	Data Structure
	Reverse pairs of elements in a singly-linked list.

	Examples

	L = null, after reverse is null
	L = 1 -> null, after reverse is 1 -> null
	L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
	L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
	 
	*/
	public ListNode reverseInPairs(ListNode head) {
		// write your solution here
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newNode = reverseInPairs(head.next.next);
		ListNode cur = head;
		ListNode next = head.next;

		next.next = cur;
		cur.next = newNode;

		return next;
	}
	
	/*
	 * task2
	Spiral Order Traverse I
	Fair
	Data Structure
	Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

	Assumptions

	The 2D array is not null and has size of N * N where N >= 0
	Examples

	{ {1,  2,  3},

	  {4,  5,  6},

	  {7,  8,  9} }

	the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]

	*/
	public int[] task2_spiral(int[][] matrix) {
		// write your solution here
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return new int[] {};
		}
		int n = matrix.length;
		int[] result = new int[n * n];
		int leftB = 0, rightB = n - 1;
		int upperB = 0, lowerB = n - 1;
		int index = 0;
		while (leftB <= rightB && upperB <= lowerB) {
			for (int j = leftB; j <= rightB; j++) {
				result[index++] = matrix[upperB][j];
			}
			upperB++;
			for (int i = upperB; i <= lowerB; i++) {
				result[index++] = matrix[i][rightB];
			}
			rightB--;
			for (int j = rightB; j >= leftB; j--) {
				result[index++] = matrix[lowerB][j];
			}
			lowerB--;
			for (int i = lowerB; i >= upperB; i--) {
				result[index++] = matrix[i][leftB];
			}
			leftB++;
		}
		return result;
	}

	/*
	 * task3
	Spiral Order Traverse II
	Fair
	Data Structure
	Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

	Assumptions

	The 2D array is not null and has size of M * N where M, N >= 0
	Examples

	{ {1,  2,  3,  4},

	  {5,  6,  7,  8},

	  {9, 10, 11, 12} }

	the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

	 */
	public List<Integer> spiral(int[][] matrix) {
		// write your solution here
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return result;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int leftB = 0, rightB = cLen - 1;
		int upperB = 0, lowerB = rLen - 1;

		while (true) {
			for (int j = leftB; j <= rightB; j++) {
				result.add(matrix[upperB][j]);
			}
			upperB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = upperB; i <= lowerB; i++) {
				result.add(matrix[i][rightB]);
			}
			rightB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int j = rightB; j >= leftB; j--) {
				result.add(matrix[lowerB][j]);
			}
			lowerB--;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
			for (int i = lowerB; i >= upperB; i--) {
				result.add(matrix[i][leftB]);
			}
			leftB++;
			if (leftB > rightB || upperB > lowerB) {
				break;
			}
		}
		return result;
	}
	
	/*
	 * task4
	Lowest Common Ancestor I
	Fair
	Recursion
	Given two nodes in a binary tree, find their lowest common ancestor.

	Assumptions

	There is no parent pointer for the nodes in the binary tree

	The given two nodes are guaranteed to be in the binary tree

	Examples

	        5

	      /   \

	     9     12

	   /  \      \

	  2    3      14

	The lowest common ancestor of 2 and 14 is 5

	The lowest common ancestor of 2 and 9 is 9

	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one,
			TreeNode two) {
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
		TreeNode left = lowestCommonAncestor(root.left, one, two);
		TreeNode right = lowestCommonAncestor(root.right, one, two);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}
	
	/*
	 * task5
	
	N Queens
	Fair
	Recursion
	Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.

	Assumptions

	N > 0
	Return

	A list of ways of putting the N Queens
	Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
	Example

	N = 4, there are two ways of putting 4 queens:

	[1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.

	[2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.
	
	 * 
	 * 
	 */
	public List<List<Integer>> nqueens(int n) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helper(0, n, cur, result);
		return result;
	}

	public void helper(int index, int n, List<Integer> cur,
			List<List<Integer>> result) {
		if (index == n) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i = 0; i < n; i++) {
			cur.add(i);
			if (isValid(cur)) {
				helper(index + 1, n, cur, result);
			}
			cur.remove(cur.size() - 1);
		}
	}

	// check whether add the last element, the cur List is still valid
	public boolean isValid(List<Integer> cur) {
		int size = cur.size();
		for (int i = 0; i < size - 1; i++) {
			int rowIndex = i;
			int colIndex = cur.get(i);
			int targetRowIndex = size - 1;
			int targetColIndex = cur.get(targetRowIndex);
			// not in the same row, same column, same diag
			// since i != size - 1, so cannot be the same row
			// just check same column, same diag and counter-diag
			if (colIndex == targetColIndex
					|| rowIndex + colIndex == targetColIndex + targetRowIndex
					|| rowIndex - colIndex == targetRowIndex - targetColIndex) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * task6
	String Abbreviation Matching
	Fair
	Recursion
	Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

	Assumptions:

	The original string only contains alphabetic characters.
	Examples:

	pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
	*/
	
	public boolean match(String input, String pattern) {
		// Write your solution here.
		// base case
		if (input.length() == 0 && pattern.length() == 0) {
			return true;
		} else {
			if (input.length() == 0 || pattern.length() == 0) {
				return false;
			}
		}

		if (isDigit(pattern.charAt(0))) {
			int i = 0;
			int num = 0;
			while (i < pattern.length() && isDigit(pattern.charAt(i))) {
				num = num * 10 + pattern.charAt(i) - '0';
				i++;
			}
			if (num > input.length()) {
				return false;
			} else {
				return match(input.substring(num), pattern.substring(i));
			}
		} else {
			// pattern.charAt(0) is NOT digit
			if (input.charAt(0) != pattern.charAt(0)) {
				return false;
			} else {
				return match(input.substring(1), pattern.substring(1));
			}
		}

	}

	public boolean isDigit(char ch) {
		return ch >= '0' && ch <= '9';
	}

}
