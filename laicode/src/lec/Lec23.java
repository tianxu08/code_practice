package lec;

import java.util.*;

import debug.Debug;
import ds.ListNode;
import ds.TreeNode;

public class Lec23 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Q1_1test();
//		Q1_3test();
//		Q1_2test();
//		Q1_2_1test();
//		Q3_4test();
//		Q3_6test();
//		Q2_1test();
//		Q2_7test();
//		Q2_2_1test();
//		Q2_2_2test();
//		Q2_4test();
		Q2_5test();
	}
	
	
	/*
	 * Q1 Reverse linked list question
	 * Q1.1 Reverse a linked list (iterative and recursive way)
	 * 
	 * 
	 */
	public static ListNode Q1_1ReverseIterative(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode prev = null;
		ListNode cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		
		return prev;
	}
	
	
	/*     head.next
	 * 1 -> 2 -> 3 ->4 ->5 ->6 
	 * head
	 * 
	 */
	public static ListNode Q1_1ReverseRec(ListNode head) {
		// base case: 
		if (head == null || head.next == null) {
			return head;
		}
	
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode newNode = Q1_1ReverseRec(next);
		
		next.next = cur;
		cur.next = null;
		
		return newNode;
	}

	
	public static void Q1_1test() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		
		Debug.printLinkedList(n1);
		
//		ListNode newHead = Q1_1ReverseRec(n1);
		ListNode newHead = Q1_1ReverseIterative(n1);
		Debug.printLinkedList(newHead);	
	}
	
	/*
	 * Follow up
	 * Q1.2 Reverse a linked list (pair by pair)
	 * Reverse pairs of elements in a singly-linked list.
	 * 
	 * Examples
	 * L = null, after reverse is null
	 * L = 1 -> null, after reverse is 1 -> null
	 * L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
	 * L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
	 * 
	 * 1 -> 2 -> 3 ->4 ->5 
	 * cur next nnext
	 * 1 <- 2 --> ....   
	 * cur next
	 * 
	 */
	
	
	public static ListNode Q1_2ReverseByPair(ListNode head) {
		if ( head ==null || head.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode nnext = next.next;
		
		cur.next = Q1_2ReverseByPair(nnext);
		next.next = cur;
		
		return next;
	}
	
	public static ListNode Q1_2reverseListPairByPair(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = Q1_2reverseListPairByPair(head.next.next);
		ListNode cur = head;
		ListNode next = head.next;
		
		next.next = cur;
		cur.next = newHead;
		
		return next;
	}
	public static void Q1_2test() {
		ListNode head = Debug.createLinkedList(4);
		Debug.printLinkedList(head);
		
//		ListNode newHead = Q1_2reverseListPairByPair(head);
		ListNode newHead = Q1_2ReverseByPair(head);
		Debug.printLinkedList(newHead);
	}
	
	/* 
	 * Follow up
	 * Q1.2.1 Reverse a linked list every three elements
	 * e.g
	 * 1->2->3->4->5->6, after reverse 3->2->1->6->5->4
	 * 1->null           after reverse 1->null
	 * 1->2-> null       after reverse 1->2->null
	 * 1->2->3->4->5     after 3->2->1->4->5
	 * 
	 *             nnnext
	 * 1 -> 2 -> 3 ->4 ->5 ->6 ->7
	 * cur next nnext 
	 */
	public static ListNode Q1_2_1ReverseIn3Elements(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode cur = head;
		ListNode next = cur.next;
		ListNode nnext = next.next;
		ListNode nnnext = nnext.next;
		
		cur.next = Q1_2_1ReverseIn3Elements(nnnext);
		next.next = cur;
		nnext.next = next;
		return nnext;
	}
	
	public static ListNode Q1_2_1ReverseIn3(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode newNode = Q1_2_1ReverseIn3(head.next.next.next);
		ListNode cur = head;
		ListNode next1 = head.next;
		ListNode next2 = head.next.next;
		
		next1.next = cur;
		next2.next = next1;
		
		cur.next = newNode;
		
		return next2;
	}
	
	public static void Q1_2_1test() {
		ListNode head = Debug.createLinkedList(12);
		Debug.printLinkedList(head);
		
//		ListNode newHead = Q1_2_1ReverseIn3(head);
		ListNode newHead = Q1_2_1ReverseIn3Elements(head);
		System.out.println();
		Debug.printLinkedList(newHead);
	}

	
	/*
	 * Q1.3 Reverse a binary tree upside down
	 * Given a binary tree where all the right nodes are leaf nodes, 
	 * flip it upside down and turn it into a tree with left leaf nodes
	 * 
	 * the nNode.left = root;
	 *     nNode.right = root.right;
	 * 
	 *              1
	 *             / \
	 *            2   3
	 *           / \
	 *          4   5
	 *         / \
	 *        6   7
	 *        
	 *              1          1
	 *             / 
	 *            2 - 3
	 *           / 
	 *          4 - 5
	 *         / 
	 *        6 - 7     
	 *        
	 *        6 
	 *       / \
	 *      4   7
	 *     / \
	 *    2   5
	 *   / \
	 *  1   3
	 *        
	 */
	
	public static TreeNode convert2(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode nNode = convert2(root.left);
		root.left.left = root;
		root.left.right = root.right;
		root.left = null;
		root.right = null;
		return nNode;
	}
	public static void Q1_3test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n5;
		
		n2.left = n3;
		n2.right = n4;
		
		Debug.preOrderBT(n1);
		System.out.println();
		TreeNode newRoot = Q1_3Convert(n1);
		
		Debug.preOrderBT(newRoot);
	}
	
	public static TreeNode Q1_3Convert(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = Q1_3Convert(root.left);
		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
	
	
	
	public static TreeNode Q1_3ConvertLaiCode(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = Q1_3ConvertLaiCode(root.left);
		root.left.left = root;
		root.left.right = root.right;
		
		root.left = null;
		root.right = null;
		return newRoot;
	}
	
	
	
	/*
	 * Q2 DFS
	 * Q2.1 Print all subsets of a set
	 * Q2.1 ​Given an array of integers, how to divide the whole array into two parts, 
	 *      with their sums equal to each other.
	 */
	public static void Q2_1test() {
		String input = "abc";
		Q2_1PrintAllSubset(input);
	}
	public static void Q2_1PrintAllSubset(String input) {
		if (input == null || input.length() == 0) {
			return ;
		}
//		StringBuilder sln = new StringBuilder();
		ArrayList<Character> sln = new ArrayList<Character>();
		Q2_1Helper(input, 0, sln);
	}
	public static void Q2_1Helper(String input, int curLevel, ArrayList<Character> sln) {
		if (curLevel == input.length()) {
			// print the result
			for(int i = 0; i < sln.size(); i ++) {
				System.out.print(sln.get(i));
			}
			System.out.println();
			return;
		}
		// we need this character
		sln.add(input.charAt(curLevel));
		Q2_1Helper(input, curLevel + 1, sln);
		sln.remove(sln.size() - 1);
		
		Q2_1Helper(input, curLevel + 1, sln);
	}
	
	/*
	 * Q2.2 print all permutations of a string (with/without duplicated letters)
	 * 
	 * a b c
	 *                     
	 *                     /           |         \
	 *   level0           a(bc)      b(ac)     c(ba)
	 *                  /    \       /   \      /   \
	 *   level1        b(c) c(b)    a(c) c(a)  b(a)  a(b)
	 *                  |     |
	 *   level2         c     b  
	 *    
	 */
	public static void Q2_2_1test() {
		String str = "abc";
		Q2_2_1PrintAllPermutationsNoDuplicates(str);
	}
	public static void Q2_2_1PrintAllPermutationsNoDuplicates(String str) {
		if (str == null || str.length() == 0) {
			return ;
		}
		char[] input = str.toCharArray();
		Q2_2_1Helper(input, 0);
	}
	public static void Q2_2_1Helper(char[] input, int level) {
		if (level == input.length) {
			// print the input 
			String output = new String(input);
			System.out.println(output);
			return;
		}
		for(int i = level; i < input.length; i ++) {
			swap(input, i, level);
			Q2_2_1Helper(input, level + 1);
			// swap back
			swap(input, i, level);
		}
	}
	
	public static void swap(char[] input, int x, int y) {
		char temp = input[x];
		input[x] = input[y];
		input[y] = temp;
	}
	
	/*
	 * input: abb
	 * for every level, we use a hash set to remove duplicates. 
	 *                   a(bb)   b(ab)   // b(ab)   b already exists in the hash set.  
	 *                    /  \    /   \
	 *                   b(b)    a(b)  b(a)
	 */
	
	public static void Q2_2_2test() {
		String input = "abb";
		Q2_2_2PrintAllPermutationsWithDuplicates(input);
	}
	
	public static void Q2_2_2PrintAllPermutationsWithDuplicates(String str) {
		if (str == null || str.length() == 0) {
			return ;
		}
		char[] input = str.toCharArray();
		Q2_2_2Helper(input, 0);
	}
	
	public static void Q2_2_2Helper(char[] input, int level) {
		if (level == input.length) {
			System.out.println(new String(input));
		} 
		HashSet<Character> used = new HashSet<Character>();
		for(int i = level; i < input.length; i ++) {
			if(!used.contains(input[i])) {
				used.add(input[i]);
				swap(input, i, level);
				Q2_2_2Helper(input, level + 1);
				swap(input, i, level);
			}
		}
	}
	
	/*
	 * Q2.3 print all valid combination of coins that can form a certain amount of money. 
	 */
	
	
	/*
	 * Q2.4 print all valid permutations of ()()()
	 * Total Level: 2 * n
	 *  
	 */
	
	public static void Q2_4test() {
		int n = 2;
		Q2_4AllValidParenthesis(n);
	}
	public static void Q2_4AllValidParenthesis(int n) {
		if (n == 0) {
			return ;
		}
		int left = 0, right = 0;
		ArrayList<Character> line = new ArrayList<Character>();
		Q2_4Helper(n, left, right, line);
	}
	public static void Q2_4Helper(int n, int left, int right, ArrayList<Character> line) {
		if (left == n && right == n) {
			// print out the result
			for(int i = 0; i < line.size(); i ++) {
				System.out.print(line.get(i));
			}
			System.out.println();
			return;
		}
		// put '('
		if (left < n) {
			line.add('(');
			Q2_4Helper(n, left + 1, right, line);
			line.remove(line.size() - 1);
		}
		// put ')' only right < left, can we put ')' inside
		if (right < left) {
			line.add(')');
			Q2_4Helper(n, left, right + 1, line);
			line.remove(line.size() - 1);
		}
	}
	
	
	
	/*
	 * Q2.5 print all permutations of 3{} 2[] 1()
	 * char[] leftP[] = "([{"
	 * char[] rightP[] = ")]}"
	 * Restrictions:
	 * (1) Add a left parenthesis has no restriction but has to be smaller than the total number of left 
	 *     parenthesis of that type. 
	 * (2) Adding a right parenthesis has two restrictions
	 *     (a)Add a right parenthesis has two restriction added so far < the number of the left parenthesis 
	 *        of the same type added so far. 
	 *     (b)this right parenthesis must be the SAME TYPE of the most recent left parenthesis added and 
	 *        not matched yet.
	 *        
	 *  !!! Finish later
	 *  
	 *  input: {3,2,1}
	 */
	
	public static void Q2_5test() {
		int[] input = {1,1,1};
		Q2_5AllValidParenthesis2(input);
	}
	public static char[] leftP = {'(','[','{'};
	public static char[] rightP = {')', ']', '}'};
	public static char[] paren = {'(','[','{',')', ']', '}'};
	public static void Q2_5AllValidParenthesis2(int[] input) {
		LinkedList<Character> st = new LinkedList<Character>();
		int len = input.length;
		int newLen = len * 2;
		int[] input2 = new int[newLen];
		int[] added = new int[newLen];
	}
	
	public static void Q2_5Helper(int[] input, int[] added, LinkedList<Character> st, ArrayList<Character> line) {
		if (Q2_5Check(input, added)) {
			// print out the stack
		}
		
		for(int i = 0; i < input.length; i ++) {
			if(i < 3) {
				// add left parenthesis
				if (added[i] < input[i]) {
					// add the parenthesis to the stack
					line.add(paren[i]);
					st.push(paren[i]);
					// do dfs
					added[i] += 1;
					Q2_5Helper(input, added, st, line);
					st.pop();
					line.remove(line.size() - 1);
				}
			} else {
				// add right parenthesis
				if (added[i] < added[i%3] && !st.isEmpty() && paren[i] == st.peek()) {
					
				}
			}
		}
		
		

	}
	
	
	public static boolean Q2_5Check(int[] input, int[] added) {
		for(int i = 0; i < input.length; i ++) {
			if (input[i] != added[i]) {
				return false;
			}
		}
		return true;
	}
	public static void printStack(LinkedList<Character> st) {
		LinkedList<Character> output = new LinkedList<Character>();
		while(!st.isEmpty()) {
			output.push(st.pop());
		}
		while(!output.isEmpty()) {
			System.out.print(output.pop() + " ");
		}
		System.out.println();
	}
	
	
	/*
	 * Q2.6 Eight queen
	 */
	
	
	
	/*
	 * Q2.7 All subsequence of a string.
	 * Subset2 problem   
	 */
	public static void Q2_7test() {
		String input = "abbc";
		Q2_7AllSubsequenceOfaString(input);
		System.out.println();
	}
	public static void Q2_7AllSubsequenceOfaString(String input) {
		ArrayList<Character> sln = new ArrayList<Character>();
		Q2_7Helpoer(input, 0, sln);
	}
	public static void Q2_7Helpoer(String input, int curLevel, ArrayList<Character> sln) {
		if (curLevel == input.length()) {
			// print out the result
			for(int i = 0; i < sln.size(); i ++) {
				System.out.print(sln.get(i));
			}
			System.out.println();
			return;
		}
		// add current node into sln
		sln.add(input.charAt(curLevel));
		Q2_7Helpoer(input, curLevel + 1, sln);
		sln.remove(sln.size() - 1);
		
		while(curLevel < input.length() - 1 && input.charAt(curLevel + 1) == input.charAt(curLevel)) {
			curLevel ++;
		}
		Q2_7Helpoer(input, curLevel + 1, sln);
	}
	
	
	/*
	 * Q3 2,3,4­SUM questions
	 * 
	 * Q3.1 2 Sum
	 */
	
	// method1 
	// i = 0, j = array.length - 1;
	// a[i] + a[j] > target, j --;
	// a[i] + a[j] < target, i ++;
	
	// method 2 
	// use hash table
	public static boolean Q3_1TwoSumHash(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(array[0]);
		
		for(int i = 1; i < array.length; i ++) {
			if (set.contains(target - array[i])) {
				return true;
			} else {
				set.add(array[i]);
			}
		}
		return false;
	}
	
	
	/*
	 * Q3.2 3 Sum
	 * 
	 */
	
	/*
	 * Q3.3
	 * 4 Sum 
	 * Best first search
	 * if we don't sort. 
	 * A1  1 3 5 7
	 * A2  1 3 5 7
	 * A1[i] + A2[j] --> A1[i + 1] + A2[j]
	 *                   A1[i] + A2[j + 1]    Yang's matrix. ==> refer to Yan's handsout.
	 *                    
	 */
	
	
	/*
	 * Q3.4
	 * 2 Sum All PairI
	 * Find all pairs of elements in a given array that sum to the given target number. 
	 * Return all the pairs of indices.
	 * 
	 * Item(val, index). sort the item via val. then reduce to 2Sum
	 */
	public static class Item {
		public int val;
		public int index;
		public Item(int v, int i) {
			this.val = v;
			this.index = i;
		}
		@Override
		public String toString() {
			return this.val + " " + this.index;
		}
	}
	
	//!!!!!!!!
	public static List<List<Integer>> allPairs(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		ArrayList<Item> temp = new ArrayList<Item>();
		for (int i = 0; i < array.length; i ++) {
			Item item = new Item(array[i], i);
			temp.add(item);
		}
		Comparator<Item> mycomp = new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		
		Collections.sort(temp, mycomp);
	
		int i = 0, j = temp.size() - 1;
		while(i < j) {
			int cur = temp.get(i).val + temp.get(j).val;
			if (cur == target) {
				ArrayList<Integer> newPair = new ArrayList<Integer>();
				if (temp.get(i).index < temp.get(j).index) {
					newPair.add(temp.get(i).index);
					newPair.add(temp.get(j).index);
				} else {
					newPair.add(temp.get(j).index);
					newPair.add(temp.get(i).index);
				}
				result.add(newPair);
				i ++;
				j --;
			} else if (cur < target) {
				i ++;
			} else {
				j --;
			}
		}
		return result;	
	}
	
	public static void Q3_4test() {
		int[] array = {1, 3, 2, 4};
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int target = 5;
		result = allPairs(array, target);
		System.out.println(result);
	}
	 
	/*
	 * Q3.5
	 * 2 Sum All Pair II
	 * Find all pairs of elements in a given array 
	 * that sum to the pair the given target number. Return all the distinct pairs of values.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * The order of the values in the pair does not matter
	 * Examples
	 * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
	 */
	
	
	/* Q3.6
	 * 2 Sum closest 
	 */
	public static void Q3_6test() {
		int[] a = {3, 4, 0, -1, 2,0,5};
		List<Integer> result = Q3_6closest(a, 1);
		System.out.println(result);
	}
	
	public static List<Integer> Q3_6closest(int[] array, int target) {
		// write your solution here
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (array == null || array.length == 0) {
			return result;
		}
		Arrays.sort(array);
		int minDiff = Integer.MAX_VALUE;
		int i = 0, j = array.length - 1;
		int rev1 = 0, rev2 = 0;
		while (i < j) {
			int curSum = array[i] + array[j];
			if (Math.abs(target - curSum) < minDiff) {
				minDiff = Math.abs(target - curSum);
				rev1 = array[i];
				rev2 = array[j];
			}

			if (curSum < target) {
				i++;
			} else {
				j--;
			}
		}
		result.add(rev1);
		result.add(rev2);
		return result;
	}
	
	
	

}
