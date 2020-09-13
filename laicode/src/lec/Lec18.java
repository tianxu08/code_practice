package lec;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

import debug.Debug;
import ds.*;

public class Lec18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1_1();
//		test1_2();
//		test1_3();
//		test1_4();
//		test4_2();
		test2_2();
	}
	
	/*
	 * Section 1
	 * Array deduplication.
	 * 
	 * 隔板题：
	 * 基本思想：用两个变量，一个变量记录当前指针位置 (= fast index)，一个变量记录隔板位置 (= slow index)。
	 * 性质：隔板左边是处理好的元素，当前指针右边是未处理的元素，隔板和当前指针之间的区域是无用的元素。每次只要分析当前元素性质是否要加入或者移动隔板就可以了。
	 * 123455555 xxxxxx 89
         |          i = fast →   unknown area to explore
       index = slow
       
	 */
	/* task1.1
	 * 给定一个排好序的数组，消除里面重复的元素
	 * input: 1 1 2 2 3
	 * output: 1 2 3 
	 */
	public static void test1_1() {
		int[] a = {1,1};
		int rev = deduplicate1(a);
		System.out.println("rev = " + rev);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	public static int deduplicate1(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int index = 0;
		// i is the faster. index is the slower.
		// <=index, already completed. 
		// index + 1..i, unknown
		// i+ 1.. last, not yet reached. 
		for (int i = 1; i < a.length; i++) {
			if (a[i] != a[index]) {
				++ index; // increase index, for a new position
				a[index] = a[i];
			}
		}
		// at last, the index points the last processed element. 
		// so the length is index + 1
		Arrays.copyOf(a, index + 1);
		return index + 1;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	/*
	 * task 1.2
	 * keep at most two same elements.
	 * index: 0 1 2 3 4 5
	 * input  1 1 1 2 2 3
	 * output 1 1 2 2 3
	 *   0 1 2 3 4 5
	 *   1 1 1 2 2 3
	 *       s
	 *       f
	 *         
	 *  The left side of s(not include s) is already processed. 
	 *  
	 * compare A[f] with A[s-2]
	 * if A[f] == A[s-2], f++; s stays there. 
	 * if A[f] != A[s-2], A[s] = A[f], s++;  
	 * 
	 */
	public static void test1_2() {
		int[] a = {1,2,2,3,3,3,4,5};
		int rev = deduplicate2(a);
		System.out.println("rev = " + rev);
		Debug.printArray(a);
		
		int[] b = {1,2,2,3,3,3,4,5};
		int rev2 = deduplicate2_2(b);
		System.out.println("rev2 = " + rev2);
		Debug.printArray(b);
	}
	public static int deduplicate2(int[] a) {
		if (a == null) {
			return 0;
		}
		if ( a.length <= 2) {
			return a.length;
		}
		int slow = 2;
		for (int fast = 2; fast < a.length; fast++) {
			if (a[fast] != a[slow -2]) {
				a[slow] = a[fast];
				slow ++;
			}
		}
		return slow;
	}
	
	/*
	 * [0, index] processed area
	 * [index + 1, i-1]: useless
	 * [i, n-1]  unknown area, we will explore 
	 * index = 0;
	 *  
	 * i is the fast runner;
	 * count is the count of current element
	 * a[i] != a[index]  
	 *    a[++index] = a[i]; i ++;
	 *    count = 1; 
	 * a[i] == a[index]
	 *    if (count < 2) {
	 *    	a[++index] = a[i];
	 *      count ++;
	 *    }
	 *    i ++;
	 */
	public static int deduplicate2_2(int[] a) {
		if (a == null ) {
			return 0;
		}
		if (a.length <= 2) {
			return a.length;
		}
		int index = 0;
		int i = 1;
		int count = 1;
		while(i < a.length) {
			if (a[i] != a[index]) {
				a[++index] = a[i];
				count = 1;
				i ++;
			} else {
				// a[i] == a[index]
				if (count < 2) {
					a[++index] = a[i];
					count ++;
				}
				i ++;
			}
		}
		return index + 1;
	}
	
	/*
	 * task1.3
	 * 对于重复的元素一个都不保留怎么做
	 *   input 11233
	 *   output   2
	 *   
	 *   1 1 2 3 3
	 *   [0, index): processed area
	 *   [index, i): useless
	 *   [i, n): unknown area to explore
	 *   
	 *   boolean flag = false; // indicates whether we are currently having duplicates
	 *   int index = 0; // slow index
	 */
	public static void test1_3() {
		int[] a = {1,2,3};
		Debug.printArray(a);
		int rev = deduplicate3(a);
		System.out.println("rev = " + rev);
		Debug.printArray(a);
	}
	
	public static int deduplicate3(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		if (a.length == 1) {
			return a.length;
		}
		int index = 0;
		boolean flag = false; // indicates whether the current candidate has duplicates 
		for (int i = 1; i < a.length; i++) {
			if (a[i] == a[index]) {
				flag = true;
			} else {
				//a[i] != a[index]
				if (flag == false) { 
					// flag == false, the candidate in a[index] doesn't have duplicate. 
					// its safe to index ++, and put the new candidate into a[index];
					// for the new candidate, until now, it still doesn't meet its duplicate, so flag == false. 
					index ++;
					a[index] = a[i];
				} else {
					// flag == true, the current candidate can no longer be put here. 
					// we need a new candidates.
					a[index] = a[i];
					// put the a[i] to the a[index]. and set flag == false
					flag = false;
				}
			}
		}
		
		// consider the last candidate
		// if the last element's flag is false, it doesn't have duplicate. So index ++;
		if (flag == false) {
			index ++;
		}
		return index;
	}
	
	
	/*
	 * task4
	 * unsorted array, deduplication for adjacent letters repeatedly. (taught already in previous class)
	 * 
	 * a b b a z w  -> a  a z  -> z w
	 * 
	 *  a b b a z  w→  a b b a z  →  z w
	 *             i
	 *   stack||  z w
     *
	 * stack (a, b)
	 * 
	 * Corner Case: don't forget to reverse the string from the stack. 
	 * result => reverse(w z) = zw
	 */
	public static void test1_4() {
		int[] a = {1,1,2,3,4,5,5,4,4,4,3,2,5};
		deduplicate1_4(a);
	}
	public static void deduplicate1_4(int[] a) {
		if (a == null || a.length == 0) {
			return ;
		}
		Stack<Integer> st = new Stack<Integer>();
		st.push(a[0]);
		int i = 1;
		while(i < a.length) {
			if (!st.isEmpty() && st.peek() == a[i]) {
				while(i < a.length && st.peek() == a[i]) {
					i ++;
				}
				// after the above loop finish, we meet a[i] different from st.peek(), we need to pop out top
				st.pop();  // here, we don't update i. i should stay there. 
				
			} else {
				st.push(a[i]);
				i ++;
			}
		}
		System.out.println(st);
		// print out the stack in recursive way
		Stack<Integer> output = new Stack<Integer>();
		while(!st.isEmpty()) {
			output.push(st.pop());
		}
		while(!output.isEmpty()) {
			System.out.print(output.pop() + " ");
		}
	} 
	
	
	/*
	 * Section 2
	 * Array number comparisons
	 */
	/*
	 * task2.1
	 * Use the least number of comparisons to find the largest and smallest number
	 * 
	 * Method1: sort and return A[0] and A[n-1]
	 * Method2:
	 *   max = A[0], min = A[0]
	 *   for each i in A
	 *     if A[i] > max then max = A[i]
	 *     if A[i] < min, then min = A[i]
	 *   # of comparison is : 2 * (n-1)
	 */
	
	/*
	 *  Method3: 
	 *  keep a global_min and global_max
	 *  if (a[i] > global_max) 
	 *     // update global_max
	 *  else if a[i] < global_min
	 *     // update global_min
	 *     
	 *  see analysis below.
	 */
	public static void minMaxComparison2_1_3(int[] a) {
		if (a == null || a.length == 0) {
			return;
		}
		int min , max;
		if (a.length == 1) {
			min = a[0];
			max = a[0];
			System.out.println("max = " + max + "  " + "min = " + min);
			return ;
		}
		if (a[0] > a[1]) { // # of comparison  1
			max = a[0];
			min = a[1];
		} else {
			max = a[1];
			min = a[0];
		}
		
		for (int i = 2; i < a.length; i ++) {
			if (a[i] > max) {  // bp1   # of comparison n-2
				max = a[i];
			} else if (a[i] < min) { // bp2
				min = a[i];
			}
		}
		System.out.println("max = " + max + "  " + "min = " + min);
	} 
	/*
	 * Analysis: 
	 * worse case: the array is in decreasing order. every time, after bp1 executes, bp2 will execute
	 *             Total time: 1 + 2*(n-2) = 2n - 1
	 * best case: the array is in increasing order. every time, only bp1 execute. 
	 * 			   Total comparison: 1 + n-2 = n-1
	 * Average:   1.5*n - 1 
	 */
	
	
	/*
	 * Method4
	 * Compare in pairs
	 * 
	 */
	
	public static void minMaxComparison2_1_4(int[] a) {
		if (a == null || a.length < 1)
			return;
		int min, max;
		// if only one element
		if (a.length == 1) {
			max = a[0];
			min = a[0];
			System.out.println("min: " + min + "\nmax: " + max);
			return;
		}
		if (a[0] > a[1]) {
			max = a[0];
			min = a[1];
		} else {
			max = a[1];
			min = a[0];
		}
		for (int i = 2; i <= a.length - 2; i = i + 2) {
			if (a[i] > a[i + 1]) {  // # of comparison (n-2)/2
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
		if (a.length %2 == 1) {
			// we need to compare with the last element
			if (min > a[a.length - 1]) {
				min = a[a.length - 1];
			} 
			if (max < a[a.length - 1]) {
				max = a[a.length -1];
			}
		}
	}
	/*
	 * analysis: 
	 * n is odd,  1 + 3 *(n-2)/2 + 2 = 1.5*n
	 * n is even, 1 + 3 *(n-2)/2    = 1.5*n - 2
	 */
	
	public static class Pair {
		int min;
		int max;
		public Pair() {
			this.min = Integer.MAX_VALUE;
			this.max = Integer.MIN_VALUE;
		}
	}
	
	public static Pair minMaxComparison2_1_5(int arr[], int low, int high) {
		Pair result = new Pair();
		Pair left = new Pair();
		Pair right = new Pair();

		// if there is only one element
		if (low == high) {
			result.max = arr[low];
			result.min = arr[low];
			return result;
		}

		// if there are two elements
		if (high == low + 1) {
			if (arr[low] > arr[high]) {
				result.max = arr[low];
				result.min = arr[high];
			} else {
				result.max = arr[high];
				result.min = arr[low];
			}
			return result;
		}

		// if there are more than 2 elements
		int mid = (low + high) / 2;
		left = minMaxComparison2_1_5(arr, low, mid);
		right = minMaxComparison2_1_5(arr, mid + 1, high);

		if (left.min < right.min)
			result.min = left.min;
		else
			result.min = right.min;

		if (left.max > right.max)
			result.max = left.max;
		else
			result.max = right.max;

		return result;
	}
	
	/*
	 * analysis:
	 * T(1) = 0
	 * T(2) = 1
	 * T(n) = T(floor(n/2)) + T(ceil(n/2)) + 2  ~ 2(T(n/2) + 2 =  1.5n -2 
	 */
	 
	/*
	 * task2.2
	 * How to use the least number of comparisons to find the largest and second largest number?
	 * This is a tournament tree problem. 
	 */
	/*
	 * Tournament tree is a from of min(max) heap which is a complete binary tree. 
	 * Every external node represents a player
	 * and internal node represents winner. 
	 * In a tournament tree every internal node contains winner and every leaf node contains one player. 
	 * 
	 * There will be N-1 internal nodes in a binary tree with N leaf(external) nodes. 
	 * 
	 * Second best player. 
	 * the information explored during 
	 */
	public static void test2_2() {
		int[] array = {1,2,3,4,5,6,7};
		Pair2 result = task2_2LargestAndSecondLargest(array);
		System.out.println(result.first + " " + result.second);
	}
	public static class Pair2 {
		public int first;
		public int second;
		public Pair2(int x, int y) {
			this.first = x;
			this.second = y;
		}
	}
	public static Pair2 task2_2LargestAndSecondLargest(int[] array) {
		// array is not null and array.size >=2
		if (array == null || array.length == 0) {
			return null;
		}
		List<Pair2> list = new ArrayList<Pair2>();
		
		for(int i = 0; i < array.length; i ++) {
			list.add(new Pair2(i, array[i]));
		}
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		while (list.size() > 1) {
			List<Pair2> nextRound = new ArrayList<Pair2>();
			for(int i = 0; i < list.size(); i +=2) {
				if (i + 1 < list.size()) {
					Pair2 p1 = list.get(i);
					Pair2 p2 = list.get(i + 1);
					
					if (p1.second <= p2.second) {
						nextRound.add(p2);
						if (!map.containsKey(p2.first)) {
							map.put(p2.first, new ArrayList<Integer>());
						}
						map.get(p2.first).add(p1.second);
					} else {
						nextRound.add(p1);
						if (!map.containsKey(p1.first)) {
							map.put(p1.first, new ArrayList<Integer>());
						}
						map.get(p1.first).add(p2.second);
					}
				} else {
					nextRound.add(list.get(i));
				}
			}
			list = nextRound;
		}
		return new Pair2(list.get(0).second,max(map.get(list.get(0).first)) );
	}
	
	private static int max(List<Integer> list) {
		int max = list.get(0);
		for(int num: list) {
			if (num > max) {
				max = num;
			}
		}
		return max;
	}
	
	/*
	 * Section 3
	 * 
	 */
	/*
	 * task3.1
	 * How to print 2D array in spiral order
	 */
	public static void sprialPrint(int[][] matrix, int offset, int size, int counter) {
		
	}
	
	
	/*
	 * task3.2
	 * How to rotate an N*N matrix clockwise by 90 degree
	 */

	/*
	 * Section 4
	 * BFS print binary tree
	 * 
	 */
	
	/*
	 * task 4.1
	 * classical way to print the tree level by level
	 */
		
	/*
	 * task4.2
	 * print tree in zig-zag fashion.
	 * Deque
	 *                  1
	 *                /   \
	 *              2       3
	 *            /   \   /   \
	 *           4    5  6     7
	 *         /  \ 
	 *        8    9
	 * 
	 */
	public static void test4_2() {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
	
		printTreeZigZag(root);
	}
	public static void printTreeZigZag(TreeNode root) {
		if (root == null) {
			return ;
		}
		Deque<TreeNode> dq = new LinkedList<TreeNode>();
		
		dq.offerFirst(root);
		boolean isEvenLayer = false;
		while(!dq.isEmpty()) {
			int size = dq.size();
			if (isEvenLayer == false) {
				// odd layer. we need to poll from the last
				// add left child first, then add right child. both addFirst
				for (int i = 0; i < size; i++) {
					TreeNode cur = dq.pollLast();
					System.out.print(cur.val + " ");
					if (cur.left != null) {
						dq.offerFirst(cur.left);
					}
					if (cur.right != null) {
						dq.offerFirst(cur.right);
					}
				}
				System.out.println();
				isEvenLayer = true;
			} else {
				// even layer. We need to remove from the first.  in reverse order. 
				// add right child first, then add left child. both addLast
				for (int i = 0; i < size; i++) {
					TreeNode cur = dq.pollFirst();
					System.out.print(cur.val + " ");
					if (cur.right != null) {
						dq.offerLast(cur.right);
					}
					if (cur.left != null) {
						dq.offerLast(cur.left);
					}
				}
				System.out.println();
				isEvenLayer = false;
			}
		}
	}
	
	
	
	
	/*
	 * Section 5
	 * Lowest Common Ancestor
	 * 
	 */
	
	/*
	 * task5.1
	 * Lowest Common Ancestor 
	 */
	public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
		// base case
		if (root == null || root == n1 || root == n2) {
			return root;
		}
		
		TreeNode left = LCA(root.left, n1, n2);
		TreeNode right = LCA(root.right, n1, n2);
	
		if (left != null && right != null) {
			return root;
		} 
		return left != null ? left : right;
	}
	
	
	/*
	 * task5.2
	 * Lowest common of K nodes
	 * reduce to Longest Common Ancestor of two nodes. 
	 * 
	 * Extension 
	 * ==> K tree, m nodes' common ancestor
	 */
	public static TreeNode LCAOfKnodes(TreeNode root, ArrayList<TreeNode> nodes) {
		if (nodes == null || nodes.size() < 2) {
			return null;
		}
		TreeNode curLCA = LCA(root, nodes.get(0), nodes.get(1));

		for (int i = 2; i < nodes.size(); i++) {
			TreeNode cur = nodes.get(i);
			TreeNode temp = LCA(root, curLCA, cur);
			curLCA = temp;
		}
		return curLCA;
	}
	
	
	/*
	 * task5.3
	 * if we have parent pointer for each node in tree
	 * 1. for node1, go up until node. put it's parents to a hashset. 
	 * 2. for node2, go up. check whether it appears in the hashSet
	 * 
	 * ==> if we are not allowed to use extra space
	 * 1 calculate the layer number of both p and q, counter ++ until root
	 *   assume layer(p) = 7  layer(q) = 5
	 *   p goes up for 2 steps. 
	 *   Then p, q go up together step by step until p == q
	 */
	public static TreeNodeP LCAWithParentPtr5_3_1(TreeNodeP root, TreeNodeP n1, TreeNodeP n2) {
		// assume that n1 and n2 are both in the tree
		// root.parent == null
		HashSet<TreeNodeP> set = new HashSet<TreeNodeP>();
		TreeNodeP ptrN1 = n1;
		while(ptrN1 != root.parent) {
			set.add(ptrN1);
			ptrN1 = ptrN1.parent;
		}
		TreeNodeP ptrN2 = n2;
		while(ptrN2 != root.parent) {
			if (set.contains(ptrN2)) {
				return ptrN2;
			} else {
				ptrN2 = ptrN2.parent;
			}
		}
		return root;
	}
	
	public static TreeNodeP LCAWithParentPtr5_3_2(TreeNodeP root, TreeNodeP n1, TreeNodeP n2) {
		int layer1 = 0;
		int layer2 = 0;
		TreeNodeP ptrN1 = n1;
		while(ptrN1 != root.parent) {
			layer1 ++;
			ptrN1 = ptrN1.parent;
		}
		
		TreeNodeP ptrN2 = n2;
		while(ptrN2 != root.parent) {
			layer2 ++;
			ptrN2 = ptrN2.parent;
		}
		
		ptrN1 = n1;
		ptrN2 = n2;
		int diff = layer1 - layer2;
		int absDiff = Math.abs(diff);
		if (diff > 0) {
			while(absDiff != 0) {
				ptrN1 = ptrN1.parent;
				absDiff --;
			}
		} else {
			while(absDiff != 0) {
				ptrN2 = ptrN2.parent;
				absDiff --;
			}
		}
		
		while(ptrN1 != ptrN2) {
			ptrN1 = ptrN1.parent;
			ptrN2 = ptrN2.parent;
		}
		return ptrN1;
	}
	
}
