package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class P3_Tree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test3_1();
//		test3_3();
//		test3_3_1();
//		test4();
//		test7();
//		test5();
//		test8();
//		test9();
//		test9_1();
//		test12();
//		test13();
//		test14();
//		test15();
		test16();
	}
	
	/*
	 * task1
	 * Sum of all the numbers that are formed from root to leaf paths
	 * http://www.geeksforgeeks.org/sum-numbers-formed-root-leaf-paths/
	 */
	
	/*
	 * task2
	 * Sum of all the numbers that are formed from root to leaf paths
	 * http://www.geeksforgeeks.org/sum-numbers-formed-root-leaf-paths/
	 * 
	 * Given a binary tree, where every node value is a digit from 1-9. 
	 * find the sum of all number which are formed from root to leaf paths. 
	 *                  6
	 *                 / \
	 *                3   5
	 *               / \   \
	 *              2  5    4
	 *                / \
	 *               7   4
	 * 6 ->3 ->2   632
	 * 6->3->5->7  6357
	 * 6->3->5->4  6354
	 * 6->5->4     654
	 * 
	 * 
	 */
	
	public static int getTreeSum(TreeNode node, int sum) {
		if (node == null) {
			return 0;
		}
		// update the value
		sum = sum * 10 + node.val;
		if (node.left ==  null && node.right == null) {
			return sum;
		}
		return getTreeSum(node.left, sum) + getTreeSum(node.right, sum) ;
	}
	
	/*
	 * task3
	 * Convert a given Binary Tree to Doubly Linked List
	 * Give a binary tree, convert it to double linked list(DLL). The left and right pointers
	 * in node are used as previous and next pointer respectively in converted DDL. 
	 * The order of nodes in DLL must be same as inorder of the given Binary Tree. 
	 * http://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
	 * 
	 * http://www.geeksforgeeks.org/convert-a-given-binary-tree-to-doubly-linked-list-set-2/
	 * 
	 * http://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/
	 * 
	 */
	/*
	 * Method1
	 * divide and conquer
	 * 1 if left subtree exists, process the left substree
	 *   1.1 recursively convert the left subtree to DLL
	 *   1.2 find inorder predecessor of root in the left subtree. 
	 *       (inorder predecessor is the rightmost noe in left subtree. )
	 *   1.3 make the inorder predecessor as previous of root and root as the next of predecessor.
	 * 2 if right subtree exists, process the right subTree
	 *   2.1 recursively convert the right subtree to DDL 
	 *   2.2 find inorder successor of the root in right subtree
	 *   2.3 make the root as previous of inorder successor and inOrder sucessor as the next of root
	 * 3 find the left most node and return it. 
	 *   the left most node is the head of converted DLL
	 */
	public static void test3() {
		int[] a ={1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		
//		TreeNode head = convertBT2DLL1(root);
		TreeNode head2 = convertBT2DLL2(root);
		
		TreeNode cur = head2;
		while (cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.right;
		}
		System.out.println();
	}
	
	public static TreeNode convertBT2DLL1(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode head = convertBT2DLL1Helper(root);
		while (head.left != null) {
			head = head.left;
		}
		return head;
	}
	
	public static TreeNode convertBT2DLL1Helper(TreeNode root) {
		 if (root == null) {
			return root;
		}
		 // 1 if left subtree exist
		 if (root.left != null) {
			 //1.1  convert the left substree. 
			 TreeNode leftSub = convertBT2DLL1Helper(root.left);
			 //1.2  find inorder prodecessor of root in the left subtree
			 while(leftSub.right != null) {
				 leftSub = leftSub.right;
			 }
			 
			 // 1.3 link the left(inOrder predecessor to root)
			 leftSub.right = root;
			 root.left = leftSub;
			 
		}
		// 2 if right subTree exist
		 if (root.right != null) {
			//2.1 convert th eleft subTree
			TreeNode rightSub = convertBT2DLL1Helper(root.right);
			//2.2 find inOrder successor of root in the right subTree
			while(rightSub.left != null) {
				rightSub = rightSub.left;
			}
			//2.3 link the rightSub(inOrder successor to root)
			rightSub.left = root;
			root.right = rightSub;
			
		}
		return root;
	}
	
	/*
	 * Method2
	 * 1 do a inOrder traversal, using a static variable to store the previous node
	 *   every time, link node.left to prev.
	 * 2 after 1, prev points to the right most node of the whole tree. 
	 *   with the help of 
	 */
	
	public static TreeNode prev = null;
	
	// test the fixPrevPtr()
	public static void test3_1() {
		int[] a ={1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		fixPrevPtr(root);
		TreeNode cur = root;
		while(cur != null) {
			System.out.println(cur.val);
			cur = cur.left;
		}
	}
	public static TreeNode convertBT2DLL2(TreeNode root) {
		if (root == null) {
			return root;
		}
		fixPrevPtr(root);
		return fixNextPtr(root);	
	}
	
	// do a inOrder Traversal. use a global variable to store the previous node. 
	public static void fixPrevPtr(TreeNode root) {
		if (root == null) {
			return ;
		}
		fixPrevPtr(root.left);
		root.left = prev;
		prev = root;
		fixPrevPtr(root.right);
	}
	
	public static TreeNode fixNextPtr(TreeNode root) {
		// after fixPrevPtr(root), executed, the prev points to the right most node in the tree
		// we can directly go to step 2;
		System.out.println("prev = " + prev.val);
		prev = null;
		
		// step 1: go to the right most node of the tree
		while(root != null && root.right != null) {	
			root = root.right;
		}
		
		// step 2: link the every node's right to its successor with the help of fixPrevPtr()
		// after the fixPrevPtr(root) executed, every nodes left pointer points its inOrder
		// predecessor. so, we can traverse via the left pointer.
		// meanwile, we use the prev to store the its previous node
		// link cur.right to prev
		while(root != null && root.left != null) {
			prev = root;
			root = root.left;
			root.right = prev;
		}
		return root;
	}
	
	
	/*
	 * method3 
	 * Basic Idea:
	 * 
	 * Do a inOrder Traversal. 
	 * use two static variables, prev and root. 
	 * The idea is same with the next method3_1. 
	 */
	
	public static TreeNode head = null;
	
	public static void convertBT2DLL3(TreeNode root) {
		
		if (root == null) {
			return ;
		}
		convertBT2DLL3(root.left);
		if (prev == null) {
			head = root;
		} else {
			root.left = prev;
			prev.right = root;
		}
		// update prev
		prev = root;
		
		convertBT2DLL3(root.right);
	}
	
	public static void test3_3() {
		int[] a ={1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		convertBT2DLL3(root);
		
		TreeNode cur = head;
		
		while(cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.right;
		}
		System.out.println();
	}
	
	
	/*
	 * method3_1 
	 * Basic Idea:
	 * create a class to store head and prev. 
	 * Do a inOrder Traversal. convertBT2DLL(root, item) 
	 *  
	 * the root left 
	 * convertBT2DLL(root.left, item)
	 * if prev == null, we reach the leftMost node of the whole tree
	 *    set head = root;
	 * else  // prev has value
	 *    root.left = prev;
	 *    prev.right = root.right;
	 * prev = root; // update the prev
	 * 
	 * convertBT2DLL(root.right, item 
	 *  
	 */
	
	public static class Item {
		public TreeNode head; 
		public TreeNode prev;
		public Item() {
			this.head = null;
			this.prev = null;
		}
	}
	public static TreeNode convertBT2DLL3Main (TreeNode root) {
		Item item = new Item();
		convertBT2DLL3Helper(root, item);
		return item.head;
	}
	
	public static void convertBT2DLL3Helper(TreeNode root, Item item) {
		if (root == null) {
			return ;
		}
		convertBT2DLL3Helper(root.left, item);
		if (item.prev == null) {
			item.head = root;
		} else {
			root.left = item.prev;
			item.prev.right = root;
		}
		item.prev = root;
		convertBT2DLL3Helper(root.right, item);
	}
	// test for convertBT2DLL3Main
	public static void test3_3_1() {
		
		int[] a ={1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		TreeNode head = convertBT2DLL3Main(root);
		TreeNode cur = head;
		while(cur != null) {
			System.out.print(cur.val + " ");
			cur = cur.right;
		}
		System.out.println();	
	}
	
	/*
	 * task3.1
	 * http://www.geeksforgeeks.org/the-great-tree-list-recursion-problem/
	 * !!!
	 */
	
	/*
	 * task4
	 * Print all nodes that don’t have sibling
	 * http://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/
	 * Given a binary tree, print all nodes that don't have sibling. 
	 * A sibling is a node that has same parent. In a binary tree, there can be 
	 * at most one sibling. 
	 * Root should be not printed as root cannot have a sibling.
	 *  
	 */
	public static void printNodeNoSibling(TreeNode node) {
		if (node == null) {
			return ;
		}
		// we have reach the leaf node
		if (node.left == null && node.right == null) {
			return ;
		}
		// check whether the node has only one child
		if (node.left!= null && node.right == null) {
			System.out.print(node.left.val + " ");
		} else if (node.left == null && node.right != null) {
			System.out.print(node.right.val + " ");
		} 
		printNodeNoSibling(node.left);
		printNodeNoSibling(node.right);
	}
	
	/*
	 * reorganize
	 * 
	 */

	public static void printNodeNoSibling2(TreeNode node) {
		if (node == null ) {
			return ;
		}
		if (node.left != null && node.right != null) {
			printNodeNoSibling2(node.left);
			printNodeNoSibling2(node.right);
		} else if (node.left != null) {
			System.out.print(node.left.val + " ");
			printNodeNoSibling2(node.left);
		} else if (node.right != null) {
			System.out.print(node.right.val + " ");
			printNodeNoSibling2(node.right);
		} else {
			// node.left == null && node.right == null
			return ;
		}
	}
	public static void test4() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		
		node1.left = node2;
		node1.right = node3;
		node2.right = node4;
		node3.left = node5;
		node5.left = node6;
		
		TreeNode root = node1;
		printNodeNoSibling(root);
		System.out.println();
		printNodeNoSibling2(root);
	}
	
	/*
	 * task5
	 * Lowest Common Ancestor in a Binary Tree
	 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
	 * Give a binary tree(Not a binary search tree) and two values n1 and n2, 
	 * write a program to find the least common ancestor. 
	 * 
	 * Let T be a rooted tree. 
	 * The lowest common ancestor between two nodes n1 and n2 is 
	 * defined as the lowest node in T that has both n1 and n2 as descendants 
	 * (where we allow a node to be a descendant of itself).
	 */
	public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return root;
		}
		if (root == n1) {
			System.out.println("root == n1");
			return root;
		}
		if (root == n2) {
			System.out.println("root == n2");
			return root;
		}
		TreeNode left = LCA(root.left, n1, n2);
		TreeNode right = LCA(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	public static void test5() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		node3.right = node7;
		
		node4.left = node8;
		node4.right = node9;
		
		TreeNode root = node1;
		
		TreeNode lca2_9 = LCA(root, node9, node2);
		TreeNode lca9_6 = LCA(root, node9, node6);
		System.out.println("lca2_9.val = " + lca2_9.val);
		System.out.println("loc9_6.val = " + lca9_6.val);
	}
	
	
	/*
	 * A**** 做题要求：
	 * A complete answer will include the following:
	 * 1. Document your assumptions
	 * 2. Explain your approach and how you intend to solve the problem
	 * 3. Provide code comments where applicable
	 * 4. Explain the big-O run time complexity of your solution. Justify your answer.
	 * 5. Identify any additional data structures you used and justify why you used them.
	 * 6. Only provide your best answer to each part of the question.
	 */
	
	/*
	 * task6
	 * Lowest Common Ancestor in a Binary Search Tree.
	 * Given values of two nodes in a Binary Search Tree, 
	 * write a c program to find the Lowest Common Ancestor (LCA).
	 * You may assume that both the values exist in the tree.
	 * http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
	 */

	// assumption: val1 < val2 
	public static TreeNode LCAInBST(TreeNode root, int val1, int val2) {
		if (root == null) {
			return null;
		}
		int cur = root.val;
		if (cur >= val1 && cur <= val2) {
			return root;
		} else if (cur < val1) {
			// in the right side
			return LCAInBST(root.right, val1, val2);
		} else {
			// cur > val2
			return LCAInBST(root.left, val1, val2);
		}
	}
	
	
	/*
	 * task7
	 * Find distance between two given keys of a Binary Tree
	 * Find the distance between two keys in a binary tree, 
	 * no parent pointers are given. 
	 * Distance between two nodes is the minimum number of edges to be 
	 * traversed to reach one node from other.
	 * 
	 * http://www.geeksforgeeks.org/find-distance-two-given-nodes/
	 * 				1
	 *          /       \
	 *         2         3
	 *       /   \      /  \
	 *      4     5    6    7
	 *                  \
	 *                   8
	 * 
	 * Dist(4,5) = 2
	 * Dist(4,6) = 4
	 * Dist(3,4) = 3
	 * Dist(2,4) = 2
	 *
	 * Idea:
	 * The distance between two nodes can be obtained in terms of lowest common ancestor.
	 * Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2 * Dist(root, lca);
	 * 
	 */
	public static class Dist {
		public int val;
		public Dist() {
			this.val = 0;
		}
		public Dist(int v) {
			this.val = v;
		}
	}
	// assume that n1 and n2 are in the tree. 
	public static TreeNode LCADist(TreeNode node, TreeNode n1, TreeNode n2, 
			Dist d1, Dist d2, Dist dist, int level) {
		
		if (node == null) {
			return null;
		}
		System.out.println("node.val = " + node.val);
		if (node == n1) {
			d1.val = level;
			return node;
		}
		if (node == n2) {
			d2.val = level;
			return node;
		}
		TreeNode left = LCADist(node.left, n1, n2, d1, d2, dist, level + 1);
		TreeNode right = LCADist(node.right, n1, n2, d1, d2, dist, level + 1);
		
		// left != null && right != null, the current is the LCA, whose distance to root is level
		if (left != null && right != null) {
			dist.val = d1.val + d2.val - 2 * level;
			return node;
		}
		return left != null ? left : right;
	}
	public static int DistanceBetweenTwoNodes(TreeNode root, TreeNode n1, TreeNode n2) {
		Dist d1 = new Dist(-1);
		Dist d2 = new Dist(-1);
		Dist dist = new Dist();
		TreeNode LCA = LCADist(root, n1, n2, d1, d2, dist, 0);
		if (d1.val != -1 && d2.val != -1) {
			return dist.val;
		}
		
		// d1.val != -1, we reached n1, n1 is the LCA of n1 and n2. n1 is ancestor  
		if (d1.val != -1) {
			return getLevel(LCA, n2, 0);
		}
		// d2.val != -1, n2 is the LCA of n1 and n2. n2 is the ancestor. 
		if (d2.val != -1) {
			return getLevel(LCA, n1, 0);
		}
		return -1;
	}
	
	public static int getLevel(TreeNode root, TreeNode node, int level) {
		
		if (root == null) {
			return -1;
		}
		if (root == node) {
			return level;
		}
		int goLeft = getLevel(root.left, node, level + 1);
		int goRight = getLevel(root.right, node, level + 1);
		if (goLeft != -1) {
			return goLeft;
		} else {
			return goRight;
		}	
	}
	
	public static void test7() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		node3.right = node7;
		
		TreeNode root = node1;
		int dis2_4 = DistanceBetweenTwoNodes(root, node2, node4);
		System.out.println("dist2_4 = " + dis2_4);
		
	}
	
	
	
	/*
	 * task8
	 * Print all nodes that are at distance k from a leaf node
	 * Given a Binary Tree and a positive integer k, 
	 * print all nodes that are distance k from a leaf node.
	 * http://www.geeksforgeeks.org/print-nodes-distance-k-leaf-node/
	 * 
	 * the idea is to traverse the tree. Keep storing all ancestors till we hit a leaf node.
	 * When we reach a leaf node, we print the ancestor at distance k. 
	 * We also need to keep track of nodes that are already visited. For that we use a boolean
	 * array visited[]. 
	 */
	
	
	public static void printKDistancdFromLeaf(TreeNode root, int k) {
		int height = getHeight(root);
		int[] path = new int[height];
		boolean[] visited = new boolean[height];
		helper(root, path, visited, 0, k);
	}
	public static void helper(TreeNode node, int[] path, boolean[] visited, int len, int k) {
		// if the node is null
		if (node == null) {
			return ;
		}
		
		path[len] = node.val;
		visited[len] = false;
		len ++;
		
		// if node is leaf. 
		if (node.left == null && node.right == null && len - k - 1 >=0 && visited[len - k - 1] == false) {
			// print out the val
			System.out.print(path[len - k - 1]);
			// mark the corresponding element in visited
			visited[len - k - 1] = true;
			return ;
		}
		
		// if node is not a leaf, go left and go right
		helper(node.left, path, visited, len, k);
		helper(node.right, path, visited, len, k);
	}
	
	public static int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	
	public static void test8() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		node3.right = node7;
		
		TreeNode root = node1;
		printKDistancdFromLeaf(root, 1);
	}
	
	
	/*
	 * task9
	 * Print all nodes at distance k from a given node
	 * http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
	 * 
	 * Given a binary tree, a target node in the binary tree, 
	 * and an integer value k, print all the nodes that are at distance k from the given target node. 
	 * No parent pointers are available.
	 * 
	 * 
	 *                  20
	 *                /     \
	 *               8       22        
	 *              / \
	 *             4  12
	 *               /  \
	 *              10  14 
	 *           input: target = pointer to node with val = 8; 
	 *                  k = 2
	 *           output: 10, 14, 22
	 *           
	 *   (1) nodes in target's subtree, with distance == k
	 *   (2) nodes and target have the same parent. in this example, like 22. their common ancestor is 20. 
	 *       distance(ancestor, target) = 1, find node in ancestor's subtree, distance is k - 1
	 *       distance(ancestor, target) = 2, 											  k - 2
	 *       etc. 
	 *       We need to keep all ancestor of target. 
	 */
	public static void test9() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		node3.right = node7;
		
		node4.left = node8;
		node4.right = node9;
		
		TreeNode root = node1;
		TreeNode target = node2;
		int k = 2;
		printKDistance(root, target, k);
	}
	
	public static int printKDistance(TreeNode root, TreeNode target, int k) {
		if (root == null) {
			return -1;
		}
		// if root is target
		if (root == target) {
			printKDistanceNodesDown(root, k);
			return 0;
		}
		
		// check whether the target is in root's left subtree
		int leftDistance = printKDistance(root.left, target, k);
		
		// if target was found in left subtree
		if (leftDistance != -1) {
			// if root is distance k from target. print root. 
			// note that leftDistance is the distance between target and root.left.
			if (leftDistance + 1 == k) {
				System.out.print(root.val + " ");
			} else {
				// now, we have the root, we need to find k - leftDistance -2 in root.right's subtree. 
				// root.left -> root    root-> root.right. so, -2. 
				printKDistanceNodesDown(root.right, k - leftDistance - 2);
			}
			// return the distance between target and root. NOT root.left. So + 1
			return leftDistance + 1;
		}
		
		int rightDistance = printKDistance(root.right, target, k);
		
		if (rightDistance != -1) {
			if (rightDistance + 1 == k) {
				System.out.print(root.val + " ");
			} else {
				printKDistanceNodesDown(root.left, k - rightDistance - 2);
			}
			return rightDistance + 1;
		}
		return -1;
	}
	
	
	public static void printKDistanceNodesDown2(TreeNode node, int k, int level) {
		if (node == null || k < level) {
			return ;
		}
		if (level == k) {
			System.out.print(node.val + " ");
			return ;
		} 
		printKDistanceNodesDown2(node.left, k, level + 1);
		printKDistanceNodesDown2(node.right, k, level + 1);
	}
	
	public static void printKDistanceNodesDown(TreeNode node, int k) {
		if (node == null || k < 0) {
			return ;
		}
		if (k == 0) {
			System.out.print(node.val + " ");
			return ;
		} 
		printKDistanceNodesDown(node.left, k- 1);
		printKDistanceNodesDown(node.right, k- 1);
	}
	
	
	
	public static void getAncestorArray(TreeNode node, TreeNode target, TreeNode[] path, int level) {
		if (node == null) {
			return ;
		}
		path[level] = node;
		if (node == target) {
			return ;
		}
		getAncestorArray(node.left, target, path, level + 1);
		getAncestorArray(node.right, target, path, level + 1);
	}
	
	
	
	
	
	/*
	 * task9.1
	 * Find the maximum path sum between two leaves of a binary tree
	 * http://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
	 */
	public static class Result{
		int val;
		public Result() {
			this.val = 0;
		}
		public Result(int v) {
			this.val = v;
		}
	}
	
	public static int maxPathSum2(TreeNode root) {
		Result result = new Result(0);
		maxPathUntilLeaf2Leaf(root, result);
		System.out.println(result.val);
		return result.val;
	}
	
	
	// result.val is the maxPath Sum from leaf to leaf
	// return max Path sum from this node to leaf. 
	public static int maxPathUntilLeaf2Leaf(TreeNode node, Result result) {
		// node is null, return Int_min, this is to distinguish whether a node has two subtree. 
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		// if the node is leaf. return node.val
		if (node.left == null && node.right == null) {
			return node.val;
		}
		
		// get the max path sum from node.left to leaf. This value might be Int_min if it don't have left
		// subtree.
		int leftSum = maxPathUntilLeaf2Leaf(node.left, result);
		// get the max path sum from node.right to leaf. 
		int rightSum = maxPathUntilLeaf2Leaf(node.right, result);
		
		System.out.println("----------------------------------------------");
		// if the node has both left subtree and right subtree
		if (leftSum != Integer.MIN_VALUE && rightSum != Integer.MIN_VALUE) {
			int curSum = leftSum + rightSum + node.val;
			if (curSum > result.val) {
				result.val = curSum;
			}
		}
		
		// for debug
//		System.out.println("node.val = " + node.val);
//		System.out.println("leftSum = " + leftSum);
//		System.out.println("rightSum = " + rightSum);
//		System.out.println("curMax = " + result.val);
		
//		System.out.println("return val = " + (Math.max(leftSum, rightSum) + node.val));		
		System.out.println("==============================================");
		
		// return max path sum from this node to leaf
		return  Math.max(leftSum, rightSum) + node.val;
	}
	
	
	
	// this function return the maximum path sum from node to leaf
	// the maximum path sum between two leaves is stored in result.val
	// these two functions doesn't work well for this problem. 
	public static int maxPathSum(TreeNode root) {
		Result result = new Result(0);
		maxPathSumUntil(root, result);
		System.out.println(result.val);
		return result.val;
		
	}
	public static int maxPathSumUntil(TreeNode node, Result result ) {
		if (node == null) {
			return 0;
		}		
		// get the maxPath Sum until node.left
		int leftSum = maxPathSumUntil(node.left, result);
		// get the maxPath Sum until node.right
		int rightSum = maxPathSumUntil(node.right, result);
		
		System.out.println("-----------------------------------------------");
		System.out.println("node.val = " + node.val);
		System.out.println("leftSum = " + leftSum + "  rightSum = " + rightSum);
		
		// !!! actually, this is the maxSum. Rather than the leaf to leaf. 
		int curSum = Math.max(leftSum + rightSum + node.val, Math.max(leftSum, rightSum));
//		int curSum = leftSum + rightSum + node.val;
		System.out.println("curSum = " + curSum);
		System.out.println("return value = " + (Math.max(leftSum, rightSum) + node.val));
		System.out.println("===============================================");
		
		// update the result val
		if (result.val < curSum) {
			result.val = curSum;
		}		
		return Math.max(leftSum, rightSum) + node.val;
	}
	
	
	public static void test9_1() {
		TreeNode node1 = new TreeNode(-15);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(-8);
		TreeNode node4 = new TreeNode(1);
		
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(6);
		
		node1.left = node2;
		node2.left = node3;
		node2.right = node4;
		
		node3.left = node5;
		node3.right = node6;
				
		TreeNode node7 = new TreeNode(6);
		TreeNode node8 = new TreeNode(3);
		TreeNode node9 = new TreeNode(9);
		
		node1.right = node7;
		node7.left = node8;
		node7.right = node9;
		
		TreeNode node10 = new TreeNode(0);
		TreeNode node11 = new TreeNode(4);
		TreeNode node12 = new TreeNode(-1);
		TreeNode node13 = new TreeNode(10);
		
		node9.right = node10;
		node10.left = node11;
		node10.right = node12;
		node12.left = node13;
		
		TreeNode root = node1;
		
//		int maxSum = maxPathSum(root);
//		System.out.println("maxSum = " + maxSum);
		int maxSum2 = maxPathSum2(root);
		System.out.println("maxSum2 = " + maxSum2);
	}
	
	/*
	 * task9.2
	 * binary tree maximum path. 
	 * Give a binary tree. find the maximum path sum
	 * The path may start and end any node in the tree. 
	 * 
	 * 可以利用“最大连续子序列和”问题的思路,见第 §13.2节。
	 * 如果说 Array 只有一个方向的话, 那么 Binary Tree 其实只是左、右两个方向而已,我们需要比较两个方向上的值。
	 * 不过,Array 可以从头到尾遍历,那么 Binary Tree 怎么办呢,
	 * 我们可以采用 Binary Tree 最常用 的 dfs 来进行遍历。先算出左右子树的结果 L 和 R,如果 L 大于 0,
	 * 那么对后续结果是有利的,我们 加上 L,如果 R 大于 0,对后续结果也是有利的,继续加上 R
	 */
	
	public static class MaxSum {
		int val;
		public MaxSum(int v) {
			this.val = v;
		}
	}
	
	public static int  maxPathSumInBinaryTree(TreeNode root) {
		MaxSum maxSum = new MaxSum(Integer.MIN_VALUE);
		maxPathSumUntilInBinaryTree(root, maxSum);
		return maxSum.val;
	}
	
	public static int maxPathSumUntilInBinaryTree(TreeNode node, MaxSum maxSum) {
		if (node == null) {
			return 0;
		}
		int left = maxPathSumUntilInBinaryTree(node.left, maxSum);
		int right = maxPathSumUntilInBinaryTree(node.right, maxSum);
		
		int curSum = node.val;
		if (left > 0) {
			// left side is useful
			curSum += left;
		}
		if (right > 0) {
			curSum += right;
		}
		// update maxSum
		if (maxSum.val < curSum) {
			maxSum.val = curSum;
		}
		
		// return to parent. 
		if (Math.max(left, right) > 0)  {
			// the max of left and right is positive, which is useful
			// we return the max(left, right) + node.val
			return Math.max(left, right) + node.val;
 		} else {
 			// the max of left and right is negative or 0
 			// in this case, we just return node.val. 
 			return node.val;
 		}
	}
	
	
	
	/*
	 * task10
	 * Check if a given Binary Tree is height balanced like a Red-Black Tree
	 * http://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
	 */
	public static boolean isBalanced(TreeNode root) {
		int balancedHeight = getBalancedHeight(root);
		return balancedHeight != -1;
	}
	
	public static int getBalancedHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = getBalancedHeight(node.left);
		int rightHeight = getBalancedHeight(node.right);
		
		if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	
	
	/*
	 * task11
	 * Interval Tree
	 * http://www.geeksforgeeks.org/interval-tree/
	 */
	
	/*
	 * task12
	 * Print a Binary Tree in Vertical Order |Set 1
	 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order/
	 * 
	 * Time: O(w*n)  w is the width of the tree. in worse case, if the tree is a complete tree, 
	 * 	     the time complexity is O(n*n)
	 * 
	 * We can optimize this with the help of hashMap or treeMap, see task13
	 */
	public static class WrapMinMax{
		int min;
		int max;
		public WrapMinMax() {
			this.min = Integer.MAX_VALUE;
			this.max = Integer.MIN_VALUE;
		}
	}
	
	public static void test12() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		printBTVerticalOrder1(root);
	}
	public static void printBTVerticalOrder1(TreeNode root) {
		WrapMinMax min_max = new WrapMinMax();
		getMinMax(root, min_max, 0);
		
		for (int i = min_max.min; i <=  min_max.max; i++) {
			printWithHorizontalDistance(root, i, 0);
			System.out.println();
		}
	} 
	
	public static void getMinMax(TreeNode node, WrapMinMax min_max, int horizontal_distance) {
		if (node == null) {
			return ;
		}
		min_max.max = Math.max(min_max.max, horizontal_distance);
		min_max.min = Math.min(min_max.min, horizontal_distance);
		getMinMax(node.left, min_max, horizontal_distance - 1);
		getMinMax(node.right, min_max, horizontal_distance + 1);	
	}
	
	public static void  printWithHorizontalDistance(TreeNode node, int cur_hd, int horizontal_hd) {
		if (node == null) {
			return ;
		}
		if (horizontal_hd == cur_hd) {
			System.out.print(node.val + " ");
		}
		printWithHorizontalDistance(node.left, cur_hd, horizontal_hd - 1);
		printWithHorizontalDistance(node.right, cur_hd, horizontal_hd + 1);
	}
	
	/*
	 * task13
	 * Print a Binary Tree in Vertical Order | Set 2 
	 * (Hashmap/treeMap based Method)
	 * http://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
	 * 
	 * Time: traverse the tree O(n). create the tree and every time access key in treeMap O(log n)
	 * So, the total time is : O(n * log n)
	 * 
	 * 
	 */
	public static void test13() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		printBTVerticalOrder2(root);
		System.out.println("--------------");
		printBTVerticalOrder2_1(root);
	}
	
	public static void printBTVerticalOrder2(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		getTreeMap(root, map, 0);
		
		// now we can pint out. Since the treeMap's key is already sorted. we can directly print out
		for (int i = map.firstKey(); i <= map.lastKey(); i++) {
			ArrayList<Integer> cur = map.get(i);
			System.out.println(cur);
		}
	}
	
	public static void getTreeMap(TreeNode node, TreeMap<Integer, ArrayList<Integer>> map, int horizontal_distance) {
		if (node == null) {
			return;
		}
	
		if (map.containsKey(horizontal_distance)) {
			map.get(horizontal_distance).add(node.val);
		} else {
			ArrayList<Integer> line = new ArrayList<Integer>();
			line.add(node.val);
			map.put(horizontal_distance, line);
		}
		getTreeMap(node.left, map, horizontal_distance - 1);
		getTreeMap(node.right, map, horizontal_distance + 1);
	}
	
	public static void printBTVerticalOrder2_1(TreeNode root) {
		if (root == null) {
			return ;
		}
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		getTreeMap2(root, map, 0);
		
		// now we can pint out. Since the treeMap's key is already sorted. we can directly print out
		ArrayList<Integer> keyArr = new ArrayList<Integer>();
		for (Integer i: map.keySet()) {
			keyArr.add(i);
		}
		Collections.sort(keyArr);
		
		for (int i = 0; i < keyArr.size(); i++) {
			int key = keyArr.get(i);
			ArrayList<Integer> cur = map.get(key);
			System.out.println(cur);
		}
	}
	
	public static void getTreeMap2(TreeNode node, HashMap<Integer, ArrayList<Integer>> map, int horizontal_distance) {
		if (node == null) {
			return;
		}
	
		if (map.containsKey(horizontal_distance)) {
			map.get(horizontal_distance).add(node.val);
		} else {
			ArrayList<Integer> line = new ArrayList<Integer>();
			line.add(node.val);
			map.put(horizontal_distance, line);
		}
		getTreeMap2(node.left, map, horizontal_distance - 1);
		getTreeMap2(node.right, map, horizontal_distance + 1);
	}
	
	
	
	/*
	 * task14
	 * Construct a tree from Inorder and PreOrder Traversals
	 * 
	 */
	public static void test14() {
		int[] preOrder = {4, 2, 1, 3, 6,5,7,8};
		int[] inOrder = {1,2,3,4,5,6,7,8};
		
		TreeNode root = buildTreeFromInAndPre(inOrder, preOrder);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
	}	
	
	public static TreeNode buildTreeFromInAndPre(int[] inOrder, int[] preOrder) {
		if (inOrder == null || preOrder == null) {
			return null;
		}
		if (inOrder.length != preOrder.length) {
			return null;
		}
		return myBuildTree(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1);
	}
	
	
	public static TreeNode myBuildTree(int[] inOrder, int inStart, int inEnd, 
										int[] preOrder, int preStart, int preEnd) {
		if (inStart > inEnd) {
			return null;
		}
		
		TreeNode root = new TreeNode(preOrder[preStart]);
		
		int position = findPosition(inOrder, inStart, inEnd, preOrder[preStart]);
		
		root.left = myBuildTree(inOrder, inStart, position - 1, preOrder, preStart + 1, preStart + position - inStart);
		root.right = myBuildTree(inOrder, position + 1, inEnd, preOrder, preEnd - (inEnd - position) + 1, preEnd);
		
		return root;
	}
	
	public static int findPosition(int[] arr, int start, int end, int key) {
		int i = start;
		for (; i <= end; i++) {
			if (arr[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	
	/*
	 * task15
	 * Construct a tree from inOrder and PostOrder Traversals 
	 */
	
	public static void test15() {
		int[] inOrder = {1, 2 ,3 ,4 ,5, 6, 7, 8}; 
		int[] postOrder ={1 ,3, 2, 5, 8, 7 ,6 ,4};
		TreeNode root = buildFromInAndPost(inOrder, postOrder);
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
	}
	
	public static TreeNode buildFromInAndPost(int[] inOrder, int[] postOrder) {
		if (inOrder == null || postOrder == null) {
			return null;
		}
		if (inOrder.length != postOrder.length) {
			return null;
		}
		int inStart = 0, inEnd = inOrder.length - 1;
		int postStart = 0, postEnd = postOrder.length - 1;
		return myBuildInAndPost(inOrder, inStart, inEnd, postOrder, postStart, postEnd);
	}
	
	public static TreeNode myBuildInAndPost(int[] inOrder, int inStart, int inEnd,
											int[] postOrder, int postStart, int postEnd) {
		if (inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postOrder[postEnd]);
		int position = findPosition(inOrder, inStart, inEnd, postOrder[postEnd]);
		
		root.left = myBuildInAndPost(inOrder, inStart, position - 1, postOrder, postStart, postStart + (position - inStart) - 1);
		
		root.right = myBuildInAndPost(inOrder, position + 1, inEnd, postOrder, postStart + position - inStart, postEnd - 1);
		return root;
	}
	
	
	
	/*
	 * task16
	 * Construct a tree from Inorder and Level order traversals
	 * http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
	 * 
	 * e.g
	 *                              1
	 *                           /    \
	 *                          2      3
	 *                        /  \    /  \
	 *                       4    5  6    7
	 *                     /  \
	 *                    8    9
	 *   level: 1 2 3 4 5 6 7 8 9
	 *   in   : 8 4 9 2 5 1 6 3 7
	 *   
	 *   from the level, we know 1 is the root.In inOrder, we find 1. 
	 *   the left part of 1 is {8,4,9,2,5,1} is in 1's left subtree 
	 *   the right part of 1 is {6,3,7} is in 1's right subtree
	 *   so 				1
	 *   				/       \
	 *          {8,4,9,2,5,1}   {6,3,7}
	 *  in the level array, since the left part is not consecutive, we need to extract the first part and second part.
	 *                        
	 */
	
	public static void test16() {
		int[] inArr = {8, 4, 9 ,2 ,5 ,1, 6, 3, 7};
		int[] levelArr = {1,2,3,4,5,6,7,8,9};
		
		ArrayList<Integer> in = new ArrayList<Integer>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		for (int i = 0; i < inArr.length; i++) {
			in.add(inArr[i]);
			level.add(levelArr[i]);
		}
		
		TreeNode root = buildFromInAndLevel(in, level);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
	}
	
	
	public static TreeNode buildFromInAndLevel(ArrayList<Integer> in, ArrayList<Integer> level) {
		if (in == null || level == null) {
			return null;
		}
		if (in.size() != level.size()) {
			return null;
		}
		return myBuildFromInAndLevel(in, level);
	}
	
	public static TreeNode myBuildFromInAndLevel(ArrayList<Integer> in, ArrayList<Integer> level) {
		if (in == null || in.size() == 0 || level == null || level.size() == 0) {
			return null;
		}
		int key = level.get(0);
		TreeNode root = new TreeNode(key);
		 
		// find position in InOrder
		int pos = 0;
		for (; pos < in.size(); pos++) {
			if (in.get(pos) == key) {
				break;
			}
		}
		
		// extract the left part and right part into two arrayList
		ArrayList<Integer> leftLevel = extract(in, 0, pos - 1, level);
		ArrayList<Integer> rightLevel = extract(in, pos + 1, in.size() - 1, level);
		
		// for debug
//		System.out.println("pos = " + pos);
//		System.out.println("in.size = " + in.size());
//		System.out.println("leftLevel " + leftLevel);
//		System.out.println("rightLevel " + rightLevel);
		
		// get the left in order and right in order
		ArrayList<Integer> leftIn = new ArrayList<Integer>();
		ArrayList<Integer> rightIn = new ArrayList<Integer>();
		int index = 0;
		for (; index < pos; index ++) {
			leftIn.add(in.get(index));
		}
		index = pos + 1;
		for (; index < in.size(); index++) {
			rightIn.add(in.get(index));
		}
		
		root.left = myBuildFromInAndLevel(leftIn, leftLevel);
		root.right = myBuildFromInAndLevel(rightIn, rightLevel);
		
		return root;
	}
	
	public static ArrayList<Integer> extract(ArrayList<Integer> in, int inStart, int inEnd, ArrayList<Integer> level) {
		if (inEnd >= in.size() || inStart > inEnd) {
			return null;
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < level.size(); i++) {
			int key = level.get(i);
			for (int j = inStart; j <= inEnd; j++) {
				if (in.get(j) == key) {
					result.add(key);
				}
			}
		}
		return result;
	}
}
