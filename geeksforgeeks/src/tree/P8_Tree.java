package tree;

import java.util.Arrays;

public class P8_Tree {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test13();
//		test14();
		test8();
	}
	
	/*
	 * task1
	 * Convert a given tree to its Sum Tree
	 * http://www.geeksforgeeks.org/convert-a-given-tree-to-sum-tree/
	 * 
	 * store the old value. the node.val will get from it's left subtree and right substree. 
	 * return node.val + oldval to its parent for its parent's update
	 */
	
	public static int convertSumTree(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int oldVal = node.val;
		node.val = convertSumTree(node.left) + convertSumTree(node.right);
		return oldVal + node.val;
	}
	
	public static void test1() {
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(-2);
		TreeNode node3 = new TreeNode(6);
		TreeNode node4 = new TreeNode(8);
		TreeNode node5 = new TreeNode(-4);
		TreeNode node6 = new TreeNode(7);
		TreeNode node7 = new TreeNode(5);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		TreeNode root = node1;
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		int rev = convertSumTree(root);
		
		Tree.preOrder(root);
		Tree.inOrder(root);	
	}
	
	/*
	 * task2
	 * Populate Inorder Successor for all nodes
	 *  
	 * do a inOrder in a reverse way
	 */
	public static void printReverseInorder(TreeNode3 node) {
		if (node == null) {
			return ;
		}
		printReverseInorder(node.right);
		System.out.print(node.val + " ");
		printReverseInorder(node.left);
	}
	
	public static void test2() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.inOrder(root);
		
	}
	public static class TreeNode3 {
		public int val;
		public TreeNode3 left;
		public TreeNode3 right;
		public TreeNode3 next;
		public TreeNode3(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
			this.next = null;
		}
	}
	public static TreeNode3 next_ref = null;
	public static void populateNext(TreeNode3 node) {
		if (node == null) {
			return ;
		}
		populateNext(node.right);
		node.next = next_ref;
		next_ref = node;
		populateNext(node.left);
	}
	
	/*
	 * we can avoid using static variable by using a Object
	 */
	
	/*
	 * task3
	 * Connect nodes at same level using constant extra space
	 * http://www.geeksforgeeks.org/connect-nodes-at-same-level-with-o1-extra-space/
	 * 
	 * 1 cur, we link the next layer
	 * 2 the idea of level order traversal. 
	 */
	public static class TreeNode4 {
		public int val;
		public TreeNode4 left;
		public TreeNode4 right;
		public TreeNode4 next;
	}
	
	public static void connectNodesAtSameLevel2(TreeNode4 root) {
		if (root == null) {
			return;
		}
		TreeNode4 cur = root;
		while(cur != null) {
			TreeNode4 next = null; // point the first node of next layer
			TreeNode4 prev = null;
			for (; cur != null; cur = cur.next) {
				// first find next if next == null
				if (next == null) {
					if (cur.left != null) {
						next = cur.left;
					} else {
						next = cur.right;  // cur.right might also be null 
					}
				}
				if (cur.left != null) {
					// first, let's see cur.left
					if (prev == null) {
						prev = cur.left;
					} else {
						// link prev.next to cur
						prev.next = cur.left;
						// update prev
						prev = cur.left;
					}
				}
				if (cur.right != null) {
					if (prev == null) {
						prev = cur.right;
					} else {
						prev.next = cur.right;
						prev = cur.right;
					}
				}
			}
			// go to next layer
			cur = next;
		}
	}
	
	
	// use the idea of level order traversal
	// the tricky one is we traveral the parent layer, and link its children layer
	public static void connectNodesAtSameLevel(TreeNode4 root) {
		if (root == null) {
			return ;
		}
		
		TreeNode4 cur = root;
		while( cur != null) {
			TreeNode4 next = null;
			TreeNode4 prev = null;
			for (; cur != null;cur = cur.next) {
				// first find the first node of the next level
				if (next == null) {
					next = cur.left == null ?cur.left : cur.right;
				}
				if (cur.left != null) {
					if (prev == null) {
						prev = cur.left;
					} else {
						prev.next = cur.left;
						prev = prev.next;
					}
				}
				
				if (cur.right != null) {
					if (prev == null) {
						prev = cur.right;
					} else {
						prev.next = cur.right;
						prev = prev.next;
					}
				}
				
			}
			cur = next;
		}
	}
	
	
	
	/*
	 * task4
	 * Check if a binary tree is subtree of another binary tree | Set 1
	 * http://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
	 * 
	 */
	public static boolean isSubtreeBF(TreeNode S, TreeNode T) {
		if (S == null && T== null) {
			return true;
		}
		if (T== null) {
			return true;
		}
		if (S == null) {
			return false;
		}
		if (isSameTree(S, T)) {
			return true;
		}
		return isSubtreeBF(S.left, T) || isSubtreeBF(S.right, T);
	}
	/*
	 * Time Complexity: Time worst case complexity of above solution is O(mn) 
	 * where m and n are number of nodes in given two trees.
	 */
	
	public static boolean isSameTree(TreeNode p1, TreeNode p2) {
		if (p1 == null && p2 == null) {
			return true;
		}
		if (p1 == null || p2 == null) {
			return false;
		}
		return p1.val == p2.val && isSameTree(p1.left, p2.left) && isSameTree(p1.right, p2.right);
	}
	
	
	
	/*
	 * task5
	 * Check if a binary tree is subtree of another binary tree | Set 2
	 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
	 */
	public static class Index{
		public int val;
		public Index() {
			this.val = 0;
		}
		public Index(int v) {
			this.val = v;
		}
	}
	public static void inOrder(TreeNode node, char[] inorder, Index index ) {
		if (node == null) {
			inorder[index.val ++] = '#';
			return ;
		}
		inOrder(node.left, inorder, index);
		inorder[index.val ++] = (char)(node.val);
		inOrder(node.right, inorder, index);
	}
	
	public static void preOrder(TreeNode node, char[] preorder, Index index) {
		if (node == null) {
			preorder[index.val++] = '#'; 
			return ;
		}
		preorder[index.val ++] = (char)node.val;
		preOrder(node.left, preorder, index);
		preOrder(node.right, preorder, index);
	}
	
	public static boolean isSubtree(TreeNode source, TreeNode target) {
		// target is the smaller tree, source is the larger tree
		if (target == null && source == null) {
			return true;
		}
		if (source == null) {
			return false;
		}
		if (target == null) {
			return true;
		}
		
		char[] inOrderS = new char[100];
		char[] inOrderT = new char[100];
		
		Index indexS = new Index();
		Index indexT = new Index();
		inOrder(source, inOrderS, indexS);
		inOrder(target, inOrderT, indexT);
		
		char[] copyInOrderS = Arrays.copyOfRange(inOrderS, 0, indexS.val);
		char[] copyInOrderT = Arrays.copyOfRange(inOrderT, 0, indexT.val);
		
		if (strStr(copyInOrderS, copyInOrderT) == -1) {
			return false;
		}
		
		char[] preOrderS = new char[100];
		char[] preOrderT = new char[100];
		
		indexS.val = 0;
		indexT.val = 0;
		preOrder(source, preOrderS, indexS);
		preOrder(target, preOrderT, indexT);
		
		char[] copyPreOrderS = Arrays.copyOfRange(preOrderS, 0, indexS.val);
		char[] copyPreOrderT = Arrays.copyOfRange(preOrderT, 0, indexT.val);
		
		if (strStr(copyPreOrderS, copyPreOrderT) == -1) {
			return false;
		}
		return true;
	}
	
	// use brute force, it will use Time(n^2); use KMP Time: O(n)
	public static int strStr(char[] s, char[] t) {
		return -1;
	}
	
	/*
	 * task6
	 * Trie | (Insert and Search)
	 * http://www.geeksforgeeks.org/trie-insert-and-search/
	 * Trie | (Delete)
	 * http://www.geeksforgeeks.org/trie-delete/
	 */
	
	/*
	 * task7 
	 * Decision Trees – Fake (Counterfeit) Coin Puzzle (12 Coin Puzzle)
	 * http://www.geeksforgeeks.org/decision-trees-fake-coin-puzzle/
	 */
	
	/*
	 * task8
	 * Find the largest BST subtree in a given Binary Tree
	 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
	 * 
	 * 
	 * Given a Binary Tree, write a function that returns the size of the largest subtree 
	 * which is also a Binary Search Tree (BST). 
	 * If the complete Binary Tree is BST, then return the size of whole tree.
	 * 
	 * Method1
	 * top-> down.
	 * In order traversal of the Binary Tree. for every node, 
	 * check whether the subtree using this node as root is BST or NOT
	 * If BST, then return size of the subtree rooted with N. Else, 
	 * recur down the left and right subtree and return the maxValues of left and right 
	 * subtrees.
	 *  
	 * Method2
	 * bottom up  Tricky
	 * 
	 * 
	 */
	// method1
	
	public static void test8() {
		int[] a = {1,3,2,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		int size = getSize(root);
		System.out.println("size = "  + size);
		int maxBSTSize = largestBST(root);
		int maxBSTSize2 = largestBST2(root);
		System.out.println("maxBSTSize = " + maxBSTSize);
		System.out.println("maxBSTSize2 = " + maxBSTSize2);
	}
	
	public static int largestBST(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (isBST(root)) {
			return getSize(root);
		} else {
			return Math.max(largestBST(root.left), largestBST(root.right));
		}
	}
	
	public static int getSize(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return getSize(node.left) + getSize(node.right) + 1;
	}
	
	public static boolean isBST(TreeNode node) {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return validBST(node, min, max);
	}
	public static boolean validBST(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.val <= min || node.val >= max) {
			return false;
		}
		return validBST(node.left, min, node.val) && validBST(node.right, node.val, max);
	}
	
	public static class MinVal {
		int val;
		public MinVal(int v) {
			this.val = v;
		}
	}
	public static class MaxVal {
		int val;
		public MaxVal(int v) {
			this.val = v;
		}
	}
	
	public static class MaxSize {
		int val;
		public MaxSize(int v) {
			this.val = v;
		}
	}
	
	public static class IsBSTRef {
		boolean val;
		public  IsBSTRef(boolean v) {
			this.val = v;
		}
	}
	
	public static int largestBST2(TreeNode root) {
		MinVal minRef = new MinVal(Integer.MAX_VALUE);
		MaxVal maxRef = new MaxVal(Integer.MIN_VALUE);
		MaxSize maxSize = new MaxSize(0);
		IsBSTRef isBstRef = new IsBSTRef(false);
		largestBSTUntil(root, minRef, maxRef, maxSize, isBstRef);
		return maxSize.val;	
	}
	
	// this function return the size of current subtree is it is BST
	// otherwise, return 0
	// The minRef stores the minimum value in the right subTree
	// The maxRef stores the maximum value in the left subTree
	// maxSize store the maximum size of BST so far. 
	// isBstRef store whether the current subTree is bst or not
	public static int largestBSTUntil(TreeNode node, MinVal minRef, MaxVal maxRef,
			MaxSize maxSize, IsBSTRef isBstRef ) {
		// base case
		if (node == null) {
			isBstRef.val = true;
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		
		// A flag variable for left subtree property. 
		// i.e max(root.left.val) <  root.val
		boolean left_flag = false;
		
		// A flag variable of right subtree property
		// i.e min(root.right) > root.val
		boolean right_flag = false;
		
		// store the size of left and right subtree 
		
		/*
		 *  Following tasks are done by recursive call for left subtree
		 *  (a) Get the maximum value in left subtree (Stored in maxRef)
		 *  (b) Check whether left subtree is BST or not. (Store in isBstRef)
		 *  (c) Get the size of maximum size BST in left subtree(update maxSize)
		 */
		maxRef.val = Integer.MIN_VALUE;
		int leftSize = largestBSTUntil(node.left, minRef, maxRef, maxSize, isBstRef);
		if (isBstRef.val == true && node.val > maxRef.val) {
			left_flag = true;
		}
		
		//before update minRef, store the minRef.val into min
		min = minRef.val;
		
		minRef.val = Integer.MAX_VALUE;
		int rightSize = largestBSTUntil(node.right, minRef, maxRef, maxSize, isBstRef);
		if (isBstRef.val == true && node.val < minRef.val) {
			right_flag = true;
		}
		
		// update minRef.val and maxRef.val for the parent recursive calls
		if (min  < minRef.val) {
			minRef.val = min;
		} 
		if (node.val < minRef.val) {
			minRef.val = node.val;
		}
		if (node.val > maxRef.val) {
			maxRef.val = node.val;
		}
		
		// if both left_flag && right_flag are true.
		// which means that left and right subtree properties hold for this node
		// And left and right subtree are BST. 
		// then this tree is BST. So return the size of this tree
		if (left_flag && right_flag) {
			if (leftSize + rightSize + 1 > maxSize.val) {
				maxSize.val = leftSize + rightSize + 1;
			}
			return leftSize + rightSize + 1;
		} else {
			// this subtree is NOT BST
			isBstRef.val = false;
			return 0;
		}
	}
	
	
	
	
	
	
	
	
	/*
	 * task12
	 * Tournament Tree (Winner Tree) and Binary Heap
	 * http://www.geeksforgeeks.org/tournament-tree-and-binary-heap/
	 */
	
	
	/*
	 * task13
	 * Print BST keys in the given range
	 * http://www.geeksforgeeks.org/print-bst-keys-in-the-given-range/
	 * 
	 * Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. 
	 * Print all the keys of tree in range k1 to k2. 
	 * i.e. print all x such that k1<=x<=k2 and x is a key of given BST. 
	 * Print all the keys in increasing order.
	 */
	
	public static void test13() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		System.out.println("------------");
		int k1 = 3, k2 = 8;
		printBSTKeyInRange(root, k1, k2);
	}
	
	public static void printBSTKeyInRange(TreeNode root, int k1, int k2) {
		if (root == null) {
			return ;
		}
		// 
		if (root.val > k1) {
			// we need go left to cover more node 
			printBSTKeyInRange(root.left, k1, k2);
		}
		if (root.val >= k1 && root.val <= k2) {
			System.out.print(root.val + " ");
		}
		if (root.val < k2) {
			// we can go right, do the inorder traversal
			printBSTKeyInRange(root.right, k1, k2);
		}
	}
	
	/*
	 * task14
	 * Print Ancestors of a given node in Binary Tree
	 * http://www.geeksforgeeks.org/print-ancestors-of-a-given-node-in-binary-tree/
	 */
	
	/*
	 * If target is present in tree, then prints the ancestors and returns true, otherwise returns false.
	 */
	
	public static boolean printAncestor(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		if (root.val == target) {
			return true;
		}
		if (printAncestor(root.left, target) || printAncestor(root.right, target)) {
			// the target is present in left or right subtree of this node
			// then print out this node
			System.out.print(root.val + " ");
			return true;
		}
		return false;
	}
	public static void test14() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		printAncestor(root, 1);
	}
		
}
