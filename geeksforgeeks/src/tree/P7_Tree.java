package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class P7_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test6();
//		test3_1();
//		test3_0();
//		test3_3();
//		test7();
//		test8();
//		test9();
		test10();
	}
	/*
	 * task1
	 * AVL Tree
	 */
	
	/*
	 * task2
	 * Vertical Sum in a given Binary Tree
	 * http://www.geeksforgeeks.org/vertical-sum-in-a-given-binary-tree/
	 */
	
	
	
	
	/*
	 * task3
	 * Merge Two Balanced Binary Search Trees
	 * http://www.geeksforgeeks.org/merge-two-balanced-binary-search-trees/
	 * You are given two balanced binary search trees e.g., AVL or Red Black Tree. 
	 * Write a function that merges the two given balanced BSTs into a balanced binary search tree. 
	 * Let there be m elements in first tree and n elements in the other tree. 
	 * Your merge function should take O(m+n) time.
	 * 
	 * method1:
	 * Insert elements of first tree to second
	 * Take all elements of first BST one by one, and insert them into the second BST. 
	 * Inserting an element to a self balanced BST takes log n time, where n is the size of BST. 
	 * So time complexity is log(n) + log (n + 1) + log(n + 2) + ... + log(n + m - 1)
	 * The time is between m * log (n) to m * log(n + m - 1)
	 * 
	 * method2:
	 * (1) inOrder traverse BST1 to temp array arr1[]. 
	 * (2) inOrder traverse BST2 to temp array arr2[].
	 * (3) merge arr1[] and arr2[]  to arr[]
	 * (4) construct a balanced binary search tree from an sorted array. This is easy. 
	 * 
	 * method3:
	 * (1) convert BST1 to DDL1;  refer to P3_Tree
	 * http://www.geeksforgeeks.org/the-great-tree-list-recursion-problem/
	 * (2) convert BST2 to DDL2;
	 * (3) merge DDL1 and DDL2 to DDL
	 * Merge two sorted linked lists
	 * http://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
	 * (4) construct a balanced binary search tree from an sorted DDL. 
	 * http://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/
	 * 
	 * 
	 */
	
	public static TreeNode mergeTwoBST(TreeNode root1, TreeNode root2) {
		if (root1 == null) {
			return root2;
		}
		if (root2 == null) {
			return root1;
		}
		
		// convert BST to double linked list
		TreeNode head1 = BST2DLL(root1);
		TreeNode head2 = BST2DLL(root2);
		
		// merge two double linked list
		TreeNode head = merge2DLL(head1, head2);
		
		// convert DLL to Balanced BST 
		TreeNode root = DLL2BalancedBST(head);
		
		return root;
		
	}
	
	
	public static class WrapItem{
		TreeNode head;
		TreeNode prev; 
		public WrapItem() {
			this.head = null;
			this.prev = null;
		}
	}
	
	public static TreeNode BST2DLL(TreeNode root) {
		if (root == null) {
			return null;
		}
		WrapItem item = new WrapItem();
		BST2DLLHelper(root, item);
		return item.head;
	}
	
	public static void  BST2DLLHelper(TreeNode node, WrapItem item ) {
		if (node == null) {
			return ;
		}
		BST2DLLHelper(node.left, item);
		if (item.prev == null) {
			item.head = node;
			item.prev = node;
		} else {
			node.left = item.prev;
			item.prev.right = node;
			item.prev = node;  // !!! very important. Remember to update item.prev !!!
			
		}
		BST2DLLHelper(node.right, item);
	}
	public static void test3_3() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		TreeNode head = BST2DLL(root);
		
		printDLL(head);
	}
	
	
	public static TreeNode merge2DLL(TreeNode head1, TreeNode head2) {
		TreeNode head = null;
		TreeNode prev = null;
		TreeNode cur1 = head1, cur2 = head2;
		while(cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				if (head == null) {
					head = cur1;
					prev = cur1;
				} else {
					prev.right = cur1;
					cur1.left = prev;
					// update prev
					prev = prev.right;   // or prev = prev.right
				}
				// !!! don't forget to update cur1
				cur1 = cur1.right;
			} else {
				// cur1.val >= cur2.val
				if (head == null) {
					head = cur2;
					prev = cur2;
				} else {
					prev.right = cur2;
					cur2.left = prev;
					
					prev = prev.right;
				}
				cur2 = cur2.right;
			}
		}
		if (cur1 != null) {
			if (head == null) {
				head = cur1;
			} else {
				prev.right = cur1;
				cur1.left = prev;
			}
		}
		
		if (cur2 != null) {
			if (head == null) {
				head = cur2;
			} else {
				prev.right = cur2;
				cur2.left = prev;
			}
		}
		return head;
	}
	
	public static void test3_0() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		
		
		node1.right = node3;
		node3.left = node1;
		
		node3.right = node5;
		node5.left = node3;
		
		node5.right = node7;
		node7.left = node5;
		
		node7.right = node9;
		node9.left = node7;
		
		node2.right = node4;
		node4.left = node2;
		
		node4.right = node6;
		node6.left = node4;
		
		node6.right = node8;
		node8.left = node6;
		
		node8.right = node10;
		node10.left = node8;
		
		TreeNode head1 = node1;
		TreeNode head2 = node2;
		printDLL(head1);
		printDLL(head2);
		
		TreeNode head = merge2DLL(head1, head2);
		printDLL(head);
		
		
		
	}
	
	public static void printDLL(TreeNode head) {
		System.out.println("------------------");
		TreeNode cur = head;
		TreeNode tail = head;
		while(cur != null) {
			System.out.print(cur.val + " ");
			tail = cur;
			cur = cur.right;
		}
		
		System.out.println();
		
		while(tail != null) {
			System.out.print(tail.val + " ");
			tail = tail.left;
		}
		System.out.println();
		System.out.println("------------------");
	}
	
	
	
	/*
	 * task 3.1
	 * In-place conversion of Sorted DLL to Balanced BST
	 * http://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/
	 */
	
	
	public static void test3_1() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		
		TreeNode head = node1;
		node1.right = node2;
		node2.left = node1;
		
		node2.right = node3;
		node3.left = node2;
		
		node3.right = node4;
		node4.left = node3;
		
		node4.right = node5;
		node5.left = node4;
		
		node5.right = node6;
		node6.left = node5;
		
		node6.right = node7;
		node7.left = node6;
		
		TreeNode first = head;
		while (first != null) {
			System.out.print(first.val + " ");
			first = first.right;
		}
		System.out.println();
		
		TreeNode last = node7;
		while(last != null) {
			System.out.print(last.val + " ");
			last = last.left;
		}
		System.out.println();
		
		
		System.out.println("the DDL has already created");
		
		TreeNode root = DLL2BalancedBST(head);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
	}
	public static class WrapDDLNode{
		TreeNode node;
		public WrapDDLNode() {
			this.node = null;
		}
	}
	public static TreeNode DLL2BalancedBST(TreeNode head) {
		WrapDDLNode cur = new WrapDDLNode();
		cur.node = head;
		
		int size = 0;
		TreeNode runner = head;
		while(runner != null) {
			size ++;
			runner = runner.right;
		}
		
		return DLL2BalancedBSTHelper(cur, 0, size - 1);
	}
	
	public static TreeNode DLL2BalancedBSTHelper(WrapDDLNode cur, int start, int end) {
		if (start > end) {
			return null;
		}
		
		int mid = start + (end - start)/2;
		TreeNode left = DLL2BalancedBSTHelper(cur, start, mid - 1);
		
		// create the current node
		TreeNode root = cur.node;
		root.left = left;
		// cur.node go to its next node, i.e cur.node.right
		cur.node = cur.node.right;
		
		root.right = DLL2BalancedBSTHelper(cur, mid + 1, end);
		
		return root;
		
	}
	
	
	public static TreeNode DLL2BalancedBST2(TreeNode head) {
		WrapDDLNode cur = new WrapDDLNode();
		cur.node = head;
		
		int size = 0;
		TreeNode runner = head;
		while(runner != null) {
			size ++;
			runner = runner.right;
		}
		
		return DLL2BalancedBSTHelper2(cur, size);
	}
	
	public static TreeNode DLL2BalancedBSTHelper2(WrapDDLNode cur, int size) {
		if (size <= 0) {
			return null;
		}
		
		
		TreeNode left = DLL2BalancedBSTHelper2(cur, size/2);
		
		// create the current node
		TreeNode root = cur.node;
		root.left = left;
		// cur.node go to its next node, i.e cur.node.right
		cur.node = cur.node.right;
		
		root.right = DLL2BalancedBSTHelper2(cur, size - size/2 - 1);  
		
		return root;
		
	}
	
	
	
	
	/*
	 * task 3.2
	 * Convert Single Linked List to Balanced BST
	 * 
	 */
	
	
	/*
	 * task4
	 * Merge two BSTs with limited extra space
	 * http://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
	 */
	
	
	/*
	 * task5
	 * Find the maximum sum leaf to root path in a Binary Tree
	 * http://www.geeksforgeeks.org/find-the-maximum-sum-path-in-a-binary-tree/
	 * 
	 * 
	 * From Google
	 */
	
	
	/*
	 * task6
	 * Binary Tree to Binary Search Tree Conversion
	 * http://www.geeksforgeeks.org/binary-tree-to-binary-search-tree-conversion/
	 * Given a Binary Tree, convert it to a Binary Search Tree. 
	 * The conversion must be done in such a way that keeps the original structure of Binary Tree.
	 * 
	 * method:
	 * (1) do inOrder traversal to BT in array inOrder[]  O(n)
	 * (2) Sort inOrder[]  O(n log n)
	 * (3) Do inOrder traversal again, copy inOrder[]'s element to BT. ==> convert to Binary search Tree
	 * 
	 */
	
	/*
	 * this method using iteration for inOrder traversal
	 */
	public static void BT2BST(TreeNode root) {
		// edge case: 
		if (root == null) {
			return ;
		}
		
		// (1) do inOrder traversal 
		Stack<TreeNode> st = new Stack<TreeNode>();
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		TreeNode cur = root;
		while(cur != null || !st.isEmpty()) {
			if (cur != null) {
				st.push(cur);
				cur = cur.left;
			} else {
				// cur == null
				cur = st.pop();
				// visit cur here
				inOrder.add(cur.val);
				cur = cur.right;
			}
		}
		// (2) sort the inOrder[]
		Collections.sort(inOrder);
		// (3) do InOrder traversal again, copy inOrder's element to corresponding node when do the traversal.
		cur = root;
		int index = 0;
		while( cur != null || !st.isEmpty()) {
			if (cur != null) {
				st.push(cur);
				cur = cur.left;
			} else {
				// cur == null
				cur = st.pop();
				// update cur's value
				cur.val = inOrder.get(index);
				index ++;
				cur = cur.right;
			}
		}
	}
	
	/*
	 * This method using recursion for inOrder traversal.
	 */
	public static void BT2BST_2(TreeNode root) {
		if (root == null) {
			return ;
		}
		ArrayList<Integer> inOrder = new ArrayList<Integer>();
		inOrder_helper1(root, inOrder);
		
		// sort the inOrder[]
		Collections.sort(inOrder);
		Index index = new Index(0);
		inOrder_helper2(root, inOrder, index);
	}
	public static void inOrder_helper1(TreeNode node, ArrayList<Integer> inOrder) {
		if (node == null) {
			return ;
		}
		inOrder_helper1(node.left, inOrder);
		inOrder.add(node.val);
		inOrder_helper1(node.right, inOrder);
	}
	public static class Index{
		public int val;
		public Index(int v) {
			this.val = v;
		}
	}
	public static void inOrder_helper2(TreeNode node, ArrayList<Integer> inOrder, Index index) {
		if (node == null) {
			return ;
		}
		inOrder_helper2(node.left, inOrder, index);
		node.val = inOrder.get(index.val);
		index.val ++;
		inOrder_helper2(node.right, inOrder, index);
	}
	
	public static void test6() {
		int[] a = {4,6,2,1,3,5};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
//		BT2BST(root);
		BT2BST_2(root);
		System.out.println("after switch");
		Tree.preOrder(root);
		Tree.inOrder(root);
	}
	
	
	
	
	
	/*
	 * task7
	 * Construct Special Binary Tree from given Inorder traversal
	 * http://www.geeksforgeeks.org/construct-binary-tree-from-inorder-traversal/
	 * Given Inorder Traversal of a Special Binary Tree 
	 * in which key of every node is greater than keys in left and right children, 
	 * construct the Binary Tree and return root.
	 * 
	 * Special Way the key is the root's value is greater than its children's value
	 */
	
	
	/*
	 * This function return the root of subarray inOrder[start...end]. 
	 * (1) get the maximum in inOrder[start...end], its index is i
	 * (2) create current root 
	 * (3) recursively create root.left by inOrder[start...i - 1]
	 * (4) recursively create root.right by inOrder[i+1...end]
	 * 
	 * Time: O(n^2)
	 */
	public static TreeNode buildTreeInOrder(int[] inOrder) {
		int start = 0, end = inOrder.length-1;
		return buildTreeHelper(inOrder, start, end);
	}
	public static TreeNode buildTreeHelper(int[] inOrder, int start, int end) {
		// base case
		if (start > end) {
			return null;
		}
		
		int i = maxIndex(inOrder, start, end);
		TreeNode root = new TreeNode(inOrder[i]);
		
		root.left = buildTreeHelper(inOrder, start, i - 1);
		root.right = buildTreeHelper(inOrder, i + 1, end);
		
		return root;
	}
	
	public static int maxIndex(int[] a, int start, int end) {
		if (start < 0 || start >= a.length || end < 0 || end >= a.length) {
			return -1;
		}
		int curIndex = start;
		for (int i = start + 1; i <= end; i++) {
			if (a[curIndex] < a[i]) {
				curIndex = i;
			}
		}
		return curIndex;	
	}
	
	public static void test7() {
		int[] a = {1,2,3};
		TreeNode root = buildTreeInOrder(a);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
	}
	
	
	
	/*
	 * task8
	 * Construct a special tree from given preorder traversal
	 * http://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
	 * 
	 * Given an array ‘pre[]’ that represents Preorder traversal of a spacial binary tree 
	 * where every node has either 0 or 2 children. 
	 * 
	 * One more array ‘preLN[]’ is given which has only two possible values ‘L’ and ‘N’. 
	 * The value ‘L’ in ‘preLN[]’ indicates that 
	 * the corresponding node in Binary Tree is a leaf node and 
	 * value ‘N’ indicates that the corresponding node is non-leaf node. 
	 * Write a function to construct the tree from the given two arrays.
	 * 
	 * 
	 * e.g
	 * input: pre[] = {10,30,20,5,15} preLN[] = {'N', 'N', 'L', 'L', 'L'};
	 * 
	 * output                10
	 *                     /    \
	 *                    30    15
	 *                   / \
	 *                  20  5 
	 *                   
	 *                   
	 * the first node in pre is always root
	 * if its left subtree is empty, then its right subtree is always empty, and it is a leaf
	 * we can simply create a node and return it
	 * 
	 * if left and right subtree are not empty, then recursively call for left subtree and 
	 * right subtrees and the link returned node to root
	 * 
	 * in more details
	 * 
	 */
	public static void test8() {
		int[] preOrder = {10, 30, 20, 5, 15};
		char[] preLN = {'N', 'N', 'L', 'L', 'L'};
		Index index = new Index(0);
		TreeNode root = buildTreePreOrderSepcial(preOrder, preLN, index);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
	}
	
	
	public static TreeNode buildTreePreOrderSepcial(int[] preOrder, char[] preLN, Index index) {
		int curIndex = index.val;
		int n = preOrder.length;
		// Base Case: All nodes are constructed
		if (index.val == n) {
			return null;
		}
		
		// create the new node
		TreeNode node = new TreeNode(preOrder[index.val]);
		index.val ++;
		
		// if this is an internal node, construct the left and right subtree and finish the link
		if (preLN[curIndex] == 'N') {
			node.left = buildTreePreOrderSepcial(preOrder, preLN, index);
			node.right = buildTreePreOrderSepcial(preOrder, preLN, index);
		}
		return node;
	}
	
	
	/*
	 * task9
	 * Check if each internal node of a BST has exactly one child
	 * http://www.geeksforgeeks.org/check-if-each-internal-node-of-a-bst-has-exactly-one-child/
	 * 
	 * Given PreOrder traversal of a BST, check if each non-leaf node has only one child. 
	 * Assume that the BST contains unique entries.
	 * 
	 * input: pre[] = {20, 10, 11, 13, 12};
	 * output: Yes
	 *     20
	 *    /
	 *    10
	 *      \
	 *       11
	 *         \ 
	 *          13
	 *            \
	 *             12
	 *             
	 * Analysis:
	 * In preOrder traversal, preOrder successor of every node appear after it. 
	 * In the above example, 20 is the first node and all preOrder successors of 20 appear after it.
	 * all 20's preOrder successor are smaller than it in the above tree
	 * all 10's preOrder successor are larger  than it in the above tree
	 * .etc
	 * So, we can say that if all internal node only have one child, all preOrder successors of every node are 
	 * either smaller or larger than the node. 
	 * The reason is simple, since the tree is BST and every node has only one child, 
	 * all descendants of a node will either be on left side or right side, 
	 * means all descendants will either be smaller or greater.
	 * 
	 * This reduces to in an array, for each element, check whether all elements after it are all smaller or larget than it. 
	 * 
	 * Method1
	 * Two loops. To check for each element in array, whether its flowing elements are all smaller or larger than it
	 * Time: O(n^2)
	 * 
	 * Method2
	 * Since all the descendants of a node must either be larger or smaller than the node. 
	 * We can do following for every node in a loop.
	 * (1) find the next preOrder successor of the node
	 * (2) find the last preOrder successor of the node (the last element in pre[])
	 * (3) if both are smaller than current node or larger than current node, continue. 
	 *     otherwise, return false; 
	 * 
	 * Method3
	 * (1) scan the last two nodes of preOrder and mark them as min and max
	 * (2) Scan every node down the preorder array. Each node must have either smaller than min or larger than max
	 *     and update the min and max accordingly
	 */
	
	public static void test9() {
		int[] a = {20, 10, 11, 13, 12};
		System.out.println(hasOnlyOneChild2(a));
		System.out.println(hasOnlyOneChild3(a));
	}
	
	// method2
	public static boolean hasOnlyOneChild2(int[] pre) {
		if (pre == null || pre.length == 0) {
			return false;
		}
		for (int i = 0; i < pre.length -1; i++) {
			int nextSuccessor = pre[i+1];
			int lastSuccessor = pre[pre.length - 1];
			int diff1 = pre[i] -nextSuccessor;
			int diff2 = pre[i] - lastSuccessor;
			if(diff1 * diff2 < 0) {
				return false;
			}
		}
		return true;
	}
	
	//method 3
	public static boolean hasOnlyOneChild3(int[] pre) {
		if (pre == null || pre.length <= 1) {
			return false;
		}
		int size = pre.length;
		if (size == 2) {
			return true;
		}
		int max = Math.max(pre[size - 1], pre[size - 2]);
		int min = Math.min(pre[size - 1], pre[size - 2]);
		
		for (int i = size - 3; i >= 0; i--) {
			if (pre[i] < min) {
				min = pre[i];
			} else if (pre[i] > max) {
				max = pre[i];
			} else {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	/*
	 * task10
	 * Check whether a given Binary Tree is Complete or not | Set 1 (Iterative Solution)
	 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
	 * Level order traversal. 
	 * 
	 * Full node: node.left != null && node.right != null
	 * 
	 * In the process of level order traversal, when we meet a node is not full, the following nodes
	 * must be leaf nodes. 
	 * 
	 * Time: O(n)  Traverse the tree once.
	 */
	
	public static void test10() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		boolean isComplete = isCompleteBT(root);
		System.out.println("isComplete = " + isComplete);
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		
		
		System.out.println(isCompleteBT(node1));
	}
	
	public static boolean isCompleteBT(TreeNode root) {
		if (root == null) {
			return true;
		}
		boolean isInCompleteFlag = false;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		
		
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			//check whether the node is complete or not
			
			// if the inCompleteFlag hasn't been set
			if (isInCompleteFlag == false) {
				if (node.left != null && node.right != null) {
					// the node is complete
					q.offer(node.left);
					q.offer(node.right);
				} else {
					// this node is incomplete. 
					isInCompleteFlag = true;
					// if left subtree exist, push into q
					if (node.left != null) {
						q.offer(node.left);
					} else {
						// node.left == null, left subtree doesn't exist
						if (node.right != null) {
							//right node still exist
							// break the rule
							return false;
						}
					}
					
				}
			} else {
				// isInCompleteFlag == true. has already set
				// all the following node is leaf node. 
				if (node.left != null || node.right != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * task11
	 * Boundary Traversal of binary tree
	 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	 * 
	 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root. 
	 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
	 * 
	 *                          20
	 *                         /  \
	 *                        8    22
	 *                       / \     \
	 *                      4   12    25
	 *                          / \
	 *                         10  14    
	 * 
	 * output: 20 8 4 10 14 25 22
	 * 
	 * break the problem in 3 parts
	 * 1. Print the left boundary in top-down manner
	 * 2. Print all leaf nodes from left to right, which can break into two parts
	 *    2.1 Print all leaf nodes of left sub-tree from left to right
	 *    2.2 Print all leaf nodes of right sub-tree from left to right
	 * 3. Print the right boundary in down-top manner
	 * 
	 * This is a composition of left view, right view, top view, down view 
	 * Refer page 2, task 1, 2, 3, etc
	 */
	
	public static void printBoundaryTraversal(TreeNode root) {
		
	}
	
	
	
	
	/*
	 * task12
	 * Two nodes of a BST are swapped, correct the BST
	 * http://www.geeksforgeeks.org/fix-two-swapped-nodes-of-bst/
	 * 
	 * Do InOrder traversal, find the swapped nodes' pointers. Then swap back. 
	 * 
	 * (1) InOrder traversal. with stack
	 * (2) Morris Inorder traversal. 
	 */
	
	/*
	 * task13
	 * Construct Full Binary Tree from given preorder and postorder traversals
	 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
	 * 
	 * 
	 */
	

}
