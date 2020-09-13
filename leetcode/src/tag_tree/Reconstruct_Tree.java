package tag_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ds.Debug;
import ds.TreeNode;

public class Reconstruct_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
//		test2();
//		test10();
	}
	
	public static int pre_idx;
	public static int in_idx;
	public static int post_idx;

	public static void print(TreeNode root) {
		System.out.println("==================================");
		Debug.preOrderTraversal(root);
		System.out.println();
		Debug.inOrderTraversal(root);
		System.out.println();
		Debug.postOrderTraversal(root);
		System.out.println("\n==================================");
	}

	public static void test1() {
		int[] pre = {6,7,1,2,5,3,9,10};
		int[] in = {1,7,2,6,3,5,9,10};
		int[] post = {1,2,7,3,10, 9, 5, 6};
		int[] level = {6,7,5,1,2,3,9,10};
		TreeNode r1 = t1_reconstruct_BT_pre_in(pre, in);
		print(r1);
		
		/*
		TreeNode r2 = t2_reconstruct_BT_post_in(post, in);
		
		System.out.println("-------------------------");
		Debug.preOrderTraversal(r2);
		System.out.println();
		Debug.inOrderTraversal(r2);
		System.out.println();
		Debug.postOrderTraversal(r2);
		System.out.println();
		System.out.println("-------------------------");
		
		TreeNode r3 = t3_reconstruct_BT_level_in(level, in);
		
		System.out.println("-------------------------");
		Debug.preOrderTraversal(r3);
		System.out.println();
		Debug.inOrderTraversal(r3);
		System.out.println();
		Debug.postOrderTraversal(r3);
		System.out.println();
		System.out.println("-------------------------");
		*/
		
		System.out.println("t11");
		TreeNode r11 = t11_recontruct_BT_pre_in(pre, in);
		print(r11);
		
		/*
		System.out.println("t21");
		TreeNode r21 = t21_reconstruct_BT_post_in(post, in);
		Debug.preOrderTraversal(r21);
		System.out.println();
		Debug.inOrderTraversal(r21);
		System.out.println();
		Debug.postOrderTraversal(r21);
		System.out.println();
		*/
		
		
	}
	
	/**
	 * reconstruct Binary Tree from preOrder and inOrder
	 * @param pre
	 * @param in
	 * @return
	 * Assume pre and in are NOT null and their length is same.
	 * Time: O(n)  the pre and in are traversal only once 
	 *  
	 */
	public static TreeNode t1_reconstruct_BT_pre_in(int[] pre, int[] in) {
		pre_idx = 0;
		in_idx = 0;
		System.out.println(">>> pre " + Arrays.toString(pre));
		System.out.println("!!! in  " + Arrays.toString(in));
		return t1_reconstruct_BT_pre_in_helper(pre, in, Integer.MAX_VALUE);
	}
	
	public static TreeNode t1_reconstruct_BT_pre_in_helper(int[] pre, int[] in, int target) {
		System.out.println("$$$ pre_idx: " + pre_idx);
		System.out.println(">>> in_idx: " + in_idx);
		System.out.println("### target: " + target);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^");
		// use in[in_idx] as the pivot, in_idx changes from left to right
		if (in_idx >= in.length || in[in_idx] == target) {
			System.out.println("return from the 113>>> " + in_idx);
			return null;
		}
		TreeNode root = new TreeNode(pre[pre_idx]);
		System.out.println("^^^^^^^^ root.val = " + pre[pre_idx]);
		pre_idx ++;
		root.left = t1_reconstruct_BT_pre_in_helper(pre, in, root.val);  // root.val, left side
		in_idx ++;
		root.right = t1_reconstruct_BT_pre_in_helper(pre, in, target);  // target's left side
		return root;
	}
	
	
	/**
	 * reconstruct Binary Tree from postOrder and inOrder
	 * @param post
	 * @param in
	 * @return
	 * Time: O(n)  the post and in are traversal only once 
	 */
	public static TreeNode t2_reconstruct_BT_post_in(int[] post, int[] in) {
		post_idx = post.length - 1;
		in_idx = in.length - 1;
		return t2_reconstruct_BT_post_in_helper(post, in, Integer.MIN_VALUE);
	}
	
	public static TreeNode t2_reconstruct_BT_post_in_helper(int[] post, int[] in, int target) {
		// also use in[in_idx] as pivot, in_idx changes from right to right
		if (in_idx < 0 || in[in_idx] == target) {
			return null;
		}
		TreeNode root = new TreeNode(post[post_idx]);
		post_idx --;
		root.right = t2_reconstruct_BT_post_in_helper(post, in, root.val); // first, right. root.val's right
		in_idx --;
		root.left = t2_reconstruct_BT_post_in_helper(post, in, target); // then, left, target's right
		return root;
	}
	
	
	/*
	 * reconstruct Binary Tree from level and inOrder 
	 */
	public static TreeNode t3_reconstruct_BT_level_in(int[] level, int[] in) {
		List<Integer> level_list = new ArrayList<Integer>();
		for(Integer i : level) {
			level_list.add(i);
		}
		// map for in_order  <element, index>
		Map<Integer, Integer> in_map = new HashMap<Integer, Integer>();
		for(int i = 0; i < in.length; i ++) {
			in_map.put(in[i], i);
		}
		
		return t3_reconstruct_BT_level_in_helper(level_list, in_map);
		
	}
	
	public static TreeNode t3_reconstruct_BT_level_in_helper(List<Integer> level, Map<Integer, Integer> in_map) {
		if (level.isEmpty()) {
			return null;
		}
		TreeNode root = new TreeNode(level.get(0));
		level.remove(0);
		
		List<Integer> left_level = new ArrayList<Integer>();
		List<Integer> right_level = new ArrayList<Integer>();
		// fill in the left_level and right_level
		for(Integer num : level) {
			// left_level contains the indices smaller than root.val's index in in_map
			if (in_map.get(num) < in_map.get(root.val)) {
				left_level.add(num);
			} else {
		    // right_level contains the indices larger than root.val's index in in_map
				right_level.add(num);
			}
		}
		root.left = t3_reconstruct_BT_level_in_helper(left_level, in_map);
		root.right = t3_reconstruct_BT_level_in_helper(right_level, in_map);
		return root;
	}


	/**
	 * Reconstruct Binary Tree with PreOrder and InOrder Traversal
	 * @param pre
	 * @param in
	 * @return
	 * Time: O(n)
	 * Additional Space: O(n)
	 *
	 * assumption: there is no duplicate element in the array
	 */
	// assume there is no duplicate element in pre and in
	public static TreeNode t11_recontruct_BT_pre_in(int[] pre, int[] in) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		TreeNode root = t11_reconstruct_BT_pre_in_helper(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
		return root;
	}
	
	public static TreeNode t11_reconstruct_BT_pre_in_helper(
			int[] pre, int pStart, int pEnd,
			int[] in, int iStart, int iEnd,
			HashMap<Integer, Integer> map) {
		if (pStart > pEnd || iStart > iEnd) {
			return null;
		}
		
		TreeNode node = new TreeNode(pre[pStart]);
		int inIdx = map.get(pre[pStart]);
		int num = inIdx - iStart;  // the length is calculate in inOrder[]
		
		node.left = t11_reconstruct_BT_pre_in_helper(pre, pStart + 1, pStart + num, in, iStart, inIdx - 1, map);
		node.right = t11_reconstruct_BT_pre_in_helper(pre, pStart + num + 1, pEnd, in, inIdx + 1, iEnd, map);
		return node;
	}
	
	/**
	 * Reconstruct Binary Tree with post and in Order Traversal
	 * @param post
	 * @param in
	 * @return
	 * 
	 * Time: O(n)
	 * Additional Space: O(n)  // for hashMap
	 */
	public static TreeNode t21_reconstruct_BT_post_in(int[] post, int[] in) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < in.length; i++) {
			map.put(in[i], i);
		}
		TreeNode root = t21_reconstruct_BT_post_in_helper(post, 0, post.length -1, in, 0, in.length - 1, map);
		return root;
	}
	
	public static TreeNode t21_reconstruct_BT_post_in_helper(int[] post, int postStart, int postEnd, 
			int[] in, int inStart, int inEnd, HashMap<Integer, Integer> map) {
		if (postStart > postEnd || inStart > inEnd) {
			return null;
		}
		
		TreeNode node = new TreeNode(post[postEnd]);
		int inIdx = map.get(post[postEnd]);
		int num = inEnd - inIdx;
		        
		node.right = t21_reconstruct_BT_post_in_helper(post, postEnd - num, postEnd -1 , in, inIdx + 1, inEnd, map);
		node.left = t21_reconstruct_BT_post_in_helper(post, postStart, postEnd - num - 1, in, inStart, inIdx - 1, map);
		return node;
	}
	
	

	/**
	 * Test for reconstruct binary search tree from preOrder or postOrder
	 */
	public static void test2() {
		int[] pre = {5,2,1,3,4,7,6,8,9};
		int[] post = {1,4,3,2,6,9,8,7,5};
		
		TreeNode r1 = t4_reconstruct_BST_pre(pre);
		System.out.println("-------------------------");
		Debug.preOrderTraversal(r1);
		System.out.println();
		Debug.inOrderTraversal(r1);
		System.out.println();
		Debug.postOrderTraversal(r1);
		System.out.println();
		System.out.println("-------------------------");
		
		TreeNode r2 = t5_reconstruct_BST_post(post);
		
		System.out.println("-------------------------");
		Debug.preOrderTraversal(r2);
		System.out.println();
		Debug.inOrderTraversal(r2);
		System.out.println();
		Debug.postOrderTraversal(r2);
		System.out.println();
		System.out.println("-------------------------");
	}
	
	/**
	 * reconstruct Binary Search Tree from preOrder
	 * @param pre
	 * @return
	 */
	public static TreeNode t4_reconstruct_BST_pre(int[] pre) {
		pre_idx = 0;
		return t4_reconstruct_BST_pre_helper(pre, Integer.MAX_VALUE);
	}
	
	/**
	 * 
	 * @param pre
	 * @param max: bound of 
	 * @return
	 */
	public static TreeNode t4_reconstruct_BST_pre_helper(int[] pre, int max) {
		// define a max
		if (pre_idx >= pre.length || pre[pre_idx] >= max) {
			return null;
		}
		
		TreeNode root = new TreeNode(pre[pre_idx]);
		pre_idx ++;
		root.left = t4_reconstruct_BST_pre_helper(pre, root.val); // left side, it will smaller than the root.val
		root.right = t4_reconstruct_BST_pre_helper(pre, max); // right side, it will smaller than the max
		return root;
		
	}
	
	
	/*
	 * reconstruct Binary Search Tree from postOrder
	 */
	
	public static TreeNode t5_reconstruct_BST_post(int[] post) {
		post_idx = post.length - 1;
		return t5_reconstruct_BST_post_helper(post, Integer.MIN_VALUE);
	}
	
	public static TreeNode t5_reconstruct_BST_post_helper(int[] post, int min) {
		// use a min as pivot
		if (post_idx < 0 || post[post_idx] <= min) {
			return null;
		}
		TreeNode root = new TreeNode(post[post_idx]);
		post_idx --;
		root.right = t5_reconstruct_BST_post_helper(post, root.val); // first, right, it will larger than the root.val
		root.left = t5_reconstruct_BST_post_helper(post, min); // second, left, it will larger than the min
		return root;
	}
	

	/*
	 * BST, given preorder traversal, print out the inOrder without reconstruct BST
	 */
	public static void test10() {
		int[] preorder = {5,3,1,4,7,6,8};
		int[] inorder = BST_inorder_from_preorder(preorder);
		System.out.println(Arrays.toString(inorder));
	}
	
	// assume the preorder is valid
	public static int[] BST_inorder_from_preorder(int[] preorder){
		if (preorder == null || preorder.length == 0) {
			return null;
		}
		int[] rev = new int[preorder.length];
		int index = 0;
		Deque<Integer> stack = new LinkedList<Integer>();
		for(Integer i: preorder) {
			while (!stack.isEmpty() && i > stack.peekFirst()) {
				rev[index] = stack.pollFirst();
				index ++;
			}
			stack.offerFirst(i);
		}
		System.out.println(Arrays.toString(rev));
		
		while(!stack.isEmpty()) {
			rev[index ++] = stack.pollFirst();
		}
		return rev;
	}
	

	
	/*
	 * LC255 Verify PreOrder Sequence in Binary Search Tree 
	 */
	public static void test255() {
		int[] array = {5,3,1,4,8,7,9};
		boolean rev = task255_verifyPreOrder(array);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * http://www.cnblogs.com/grandyang/p/5327635.html
	 * 根据二叉搜索树的性质，当前节点的值一定大于其左子树中任何一个节点值，
	 * 而且其右子树中的任何一个节点值都不能小于当前节点值，那么我们可以用这个性质来验证，举个例子，比如下面这棵二叉搜索树：
	 *  	 5
	 *  	/ \
	 * 	    2   6
	 *	   / \
	 *    1   3
	 * 其先序遍历的结果是{5, 2, 1, 3, 6}, 我们先设一个最小值low，然后遍历数组，如果当前值小于这个最小值low，返回false，
	 * 对于根节点，我们将其压入栈中，然后往后遍历，如果遇到的数字比栈顶元素小，说明是其左子树的点，继续压入栈中，
	 * 直到遇到的数字比栈顶元素大，那么就是右边的值了，我们需要找到是哪个节点的右子树，所以我们更新low值并删掉栈顶元素，
	 * 然后继续和下一个栈顶元素比较，如果还是大于，则继续更新low值和删掉栈顶，
	 * 直到栈为空或者当前栈顶元素大于当前值停止，压入当前值，这样如果遍历完整个数组之前都没有返回false的话，最后返回true即可，
	 */
	public static boolean task255_verifyPreOrder(int[] preorder) {
		int low = Integer.MIN_VALUE;
		LinkedList<Integer> st = new LinkedList<Integer>();
		for(Integer i : preorder) {
			if (i < low) {
				return false;
			}
			while(!st.isEmpty() && i > st.peekFirst()) {
				low = st.pollFirst();
			}
			st.offerFirst(i);
		}
		return true;
	}
	
	/*
	 * 跟之前那道验证二叉搜索树的题Validate Binary Search Tree的思路很类似，我们在递归函数中维护一个下界lower和上届upper，
	 * 那么当前遍历到的节点值必须在(lower, upper)区间之内，然后我们在给定的区间内搜第一个大于当前节点值的点，然后以此为分界，
	 * 左右两部分分别调用递归函数，注意左半部分的upper更新为当前节点值val，
	 * 表明左子树的节点值都必须小于当前节点值，而右半部分的递归的lower更新为当前节点值val，
	 * 表明右子树的节点值都必须大于当前节点值，如果左右两部分的返回结果均为真，则整体返回真，
	 */
	public static boolean task255_verifyPreOrder2(int[] preorder) {
		return task255_helper2(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static boolean task255_helper2(int[] preorder, int start, int end, int lower, int upper) {
		if (start > end) {
			return true;
		}
		int val = preorder[start], i = 0;
		if (val <= lower || val >= upper) {
			return false;
		}
		for(i = start + 1; i <= end; i++) {
			if (preorder[i] >= val) {
				break;
			}
		}
		return task255_helper2(preorder, start + 1, i - 1, lower , val) && 
			   task255_helper2(preorder, i, end, val, upper);
	}
	


}
