package tree;

import java.util.*;

public class P2_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test3();
//		test5();
		test6();
	}
	
	
	/*
	 * task1
	 * Print Right View of a Binary Tree
	 * http://www.geeksforgeeks.org/print-right-view-binary-tree-2/
	 * 
	 * 1 level order traversal. 
	 *   for every layer, just print the rightmost element
	 * 2 Recursion: 
	 *   keep track of level of a node by passing a parameter to all recursive calls. Keep track of maximum level also. 
	 *   And traverse the tree right subtree before left subtree. 
	 *   When we see a node whose level > maxLevel so far, we print the node because this is the last node in its level.
	 */
	public static class MaxLevel {
		int val;
		public MaxLevel(int v) {
			this.val = v;
		}
	}
	
	public static void printRightView(TreeNode root) {
		if (root == null) {
			return ;
		}
		MaxLevel maxLevel = new MaxLevel(-1);
		printRightViewHelper(root, 0, maxLevel);
	}
	public static void printRightViewHelper(TreeNode node, int level, MaxLevel maxLevel) {
		if (node == null) {
			return ;
		}
		if (level > maxLevel.val) {
			// print out the node.val if current level < maxLevel
			System.out.print(node.val + " ");
			// update maxLevel
			maxLevel.val = level;
		}
		printRightViewHelper(node.right, level + 1, maxLevel);
		printRightViewHelper(node.left, level + 1, maxLevel);
	}
	
	public static void test1() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		printRightView(root);
		System.out.println();
		printLeftView(root);
	}
		
	
	/*
	 * task2
	 * Print Left View of a Binary Tree
	 * http://www.geeksforgeeks.org/print-left-view-binary-tree/
	 */
	public static void printLeftView(TreeNode root) {
		if (root == null) {
			return ;
		}
		MaxLevel maxLevel = new MaxLevel(-1);
		printLeftViewHelper(root, 0, maxLevel);
	}
	public static void printLeftViewHelper(TreeNode node, int level, MaxLevel maxLevel) {
		if (node == null) {
			return ;
		}
		if (level > maxLevel.val) {
			System.out.print(node.val + " ");
			maxLevel.val = level;
		}
		printLeftViewHelper(node.left, level + 1, maxLevel);
		printLeftViewHelper(node.right, level + 1, maxLevel);
	}
	
	
	/*
	 * task3
	 * Print Nodes in Top View of Binary Tree
	 * http://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
	 * 
	 * The idea is similar to vertical order. 
	 * Like Vertical Order, we need to nodes of same horizontal distance together. We do a level order traversal 
	 * so that the topmost node at a horizontal node is visited before any other node of the same horizontal distance
	 * below it. 
	 * Hashing is used to check if a node at a given horizontal is sceen. 
	 * 
	 */
	public static class QItem {
		TreeNode node;
		int hd;
		public QItem(TreeNode n, int h) {
			this.node = n;
			this.hd = h;
		}
	}
	
	public static void printTopView(TreeNode root) {
		if (root == null) {
			return ;
		}
		Set<Integer> set = new HashSet<Integer>();
		Queue<QItem> q = new LinkedList<QItem>();
		q.add(new QItem(root, 0));
		
		while(!q.isEmpty()) {
			QItem cur = q.poll();
			int cur_hd = cur.hd;
			TreeNode cur_node = cur.node;
			if (!set.contains(cur_hd)) {
				// the set doesn't contain the current hd
				set.add(cur_hd);
				// print the cur out
				System.out.print(cur_node.val + " ");
			} 
			if (cur_node.left != null) {
				QItem leftItem = new QItem(cur_node.left, cur_hd - 1);
				q.add(leftItem);
			} 
			if (cur_node.right != null) {
				QItem rightItem = new QItem(cur_node.right, cur_hd + 1);
				q.add(rightItem);
			}
		}
	}
	
	public static void test3() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		printTopView(root);
		System.out.println();
		printBottomView(root);
	}
	
	
	
	
	
	/*
	 * task3.1
	 * Bottom View of a Binary Tree
	 * http://www.geeksforgeeks.org/bottom-view-binary-tree/
	 */
	public static void printBottomView(TreeNode root) {
		if (root == null) {
			return ;
		}
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		Queue<QItem> q = new LinkedList<QItem>();
		q.add(new QItem(root, 0));
		
		while(!q.isEmpty()) {
			QItem cur_item = q.poll();
			TreeNode cur_node = cur_item.node;
			int cur_hd = cur_item.hd;
			
			map.put(cur_hd, cur_node.val);
			
			if (cur_node.left != null) {
				QItem leftItem = new QItem(cur_node.left, cur_hd - 1);
				q.offer(leftItem);
			} 
			if (cur_node.right != null) {
				QItem rightItem = new QItem(cur_node.right, cur_hd + 1);
				q.offer(rightItem);
			}
		}
		
		
		
		// print out the bottom view
		for (int i = map.firstKey(); i <= map.lastKey(); i++) {
			System.out.print(map.get(i) + " ");
		}
		System.out.println();
	}
	
	
	
	/*
	 * task4
	 * Boundary Traversal of binary tree
	 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	 * 
	 * We break the problem in 3 parts:
	 * 1. Print the left boundary in top-down manner.
	 * 2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
	 * …..2.1 Print all leaf nodes of left sub-tree from left to right.
	 * …..2.2 Print all leaf nodes of right subtree from left to right.
	 * 3. Print the right boundary in bottom-up manner.
	 */
	
	public static void printLeaves(TreeNode root) {
		if (root != null) {
			return;
		} else {
			printLeaves(root.left);
			if (root.left == null && root.right == null) {
				System.out.print(root.val + " ");
			}
			printLeaves(root.right);
		}
	}
	
	public static void printBoundaryLeft(TreeNode root) {
		if (root != null) {
			if (root.left != null) {
				System.out.print(root.val + " ");
				printBoundaryLeft(root.left);
			} else if (root.right != null) {
				System.out.print(root.val + " ");
				printBoundaryLeft(root.right);
			}
		}
	}
	
	public static void printBoundaryRight(TreeNode root) {
		if (root != null) {
			if (root.right != null) {
				printBoundaryRight(root.right);
				System.out.print(root.val + " ");
			} else if (root.left != null) {
				printBoundaryRight(root.left);
				System.out.print(root.val + " ");
			}
		}
	}
	
	public static void printBoundary(TreeNode root) {
		if (root == null) {
			return ;
		}
		System.out.print(root.val + " ");
		printBoundaryLeft(root.left);
		printLeaves(root.left);
		printLeaves(root.right);
		printBoundaryRight(root.right);
	}
	
	
	
	
	/*
	 * task5
	 * Transform a BST to greater sum tree
	 * http://www.geeksforgeeks.org/transform-bst-sum-tree/
	 * 
	 * method1
	 * This method doesn't require the tree to be a BST. Following are steps
	 * 1. Traverse node by node. (inOrder, preOrder, etc)
	 * 2. For each node, find all the nodes greater than that of the current node, sum the values. 
	 *    Store all the sums. 
	 * 3. Replace each node value with their corresponding sum by traversing in the same order as in step1
	 * 
	 * Time: O(n^2)
	 * 
	 * method2
	 * Since its a BST, we can find an O(n) solution. 
	 * The idea is to traverse BST in reverse inorder. 
	 * Reverse inOrder traversal of a BST gives us keys in decreasing order. 
	 * Before visiting a node, we visit all greater nodes of that nodes of that node. while traversing we keep 
	 * track of sum of keys which is the sum of all the keys greater than the key of current node 
	 * 
	 * Time: O(n)
	 */
	
	public static void test5() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		BST2GreaterSumTree(root);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
	}
	public static class Sum{
		int val;
		public Sum(int v) {
			this.val = v;
		}
	}
	public static void BST2GreaterSumTree(TreeNode root) {
		if (root == null) {
			return ;
		}
		Sum sum = new Sum(0);
		BST2GreaterSumTree(root, sum);
	}
	
	public static void BST2GreaterSumTree(TreeNode root, Sum sum) {
		if (root == null) {
			return ;
		}
		BST2GreaterSumTree(root.right, sum);
		int curVal = root.val;
		root.val = sum.val;
		// update sum.val
		sum.val += curVal;
		BST2GreaterSumTree(root.left, sum);
	}
	
	
	
	/*
	 * task6
	 * Reverse alternate levels of a perfect binary tree
	 * http://www.geeksforgeeks.org/reverse-alternate-levels-binary-tree/
	 * 
	 * A perfect binary tree is a binary tree in which all leaves have the same depth or same level
	 * 
	 * method1
	 * (1) Access nodes level by level.
	 * (2) if the current level is odd, then store nodes of this level in an array
	 * (3) Reverse the array and store elements back in three
	 * 
	 * method2
	 * do 2 inOrder traversals. 
	 * (1) Traverse the give tree in inOrder and store all odd level nodes in an auxiliary array
	 * (2) Reverse the array. 
	 * (3) Traverse the tree again inOrder fashion. While traversing the tree, one by one take elements 
	 *     from array and store elements from array to every odd level traversed node. 
	 * 
	 * Time: O(n), we only do two inOrder traversal of the tree. 
	 * 
	 */
	public static class Index {
		int val;
		public Index(int v) {
			this.val = v;
		}
	}
	public static void storeAlternate(TreeNode node, ArrayList<Integer> arr, int level) {
		if (node == null) {
			return ;
		}
		storeAlternate(node.left, arr, level + 1);
		if (level %2 == 1) {
			arr.add(node.val);
		}
		storeAlternate(node.right, arr, level + 1);
	}
	
	public static void modifyTree(TreeNode node, ArrayList<Integer> arr, Index index, int level) {
		if (node == null) {
			return ;
		}
		modifyTree(node.left, arr, index, level + 1);
		if (level %2 == 1) {
			node.val = arr.get(index.val);
			index.val ++;
		}
		modifyTree(node.right, arr, index, level + 1);
	}
	
	public static void reverse(ArrayList<Integer> arr) {
		int end = arr.size() - 1;
		int start = 0;
		while(start < end) {
			int temp = arr.get(start);
			arr.set(start, arr.get(end));
			arr.set(end, temp);
			start ++;
			end --;
		}
	}
	
	public static void reverseAlternate(TreeNode root) {
		if (root == null) {
			return ;
		}
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		storeAlternate(root, arr, 0);
		
		reverse(arr);
		Index index = new Index(0);
		modifyTree(root, arr, index, 0);
		
	}
	
	public static void test6() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
		reverseAlternate(root);
		
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
	}
	
	
	
	
	
	/*
	 * task7
	 * Find the maximum path sum between two leaves of a binary tree
	 * http://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
	 * Also put this in P3_Tree. after task 
	 */
	
	
	/*
	 * task8
	 * InOrder predecessor and successor for a given key in BST
	 * http://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
	 */
	
	
	
	/*
	 * task9
	 * Check if a binary tree is subtree of another binary tree | Set 1
	 * http://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
	 * 
	 * Check if a binary tree is subtree of another binary tree | Set 2
	 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
	 * 
	 * refer to
	 */
	
	/*
	 * task10
	 * Check if two nodes are cousins in a Binary Tree
	 * http://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
	 */
	
	/*
	 * task11
	 * Clone a Binary Tree with Random Pointers
	 * http://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
	 * 
	 * Method1: hashing
	 * Method2: temporary modify the binary tree (interesting)
	 */
	
	/*
	 * task12
	 * Minimum no. of iterations to pass information to all nodes in the tree
	 * http://www.geeksforgeeks.org/minimum-iterations-pass-information-nodes-tree/
	 */
	
	
	/*
	 * task13
	 * Find Height of Binary Tree represented by Parent array
	 * http://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/
	 */
	
	
	/*
	 * task14
	 * Print nodes between two given level numbers of a binary tree
	 * http://www.geeksforgeeks.org/given-binary-tree-print-nodes-two-given-level-numbers/
	 */
	
	
	/*
	 * task15
	 * Serialize and Deserialize a Binary Tree
	 * http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
	 */
	
	
	/*
	 * task6
	 * Serialize and Deserialize an N-ary Tree
	 * http://www.geeksforgeeks.org/serialize-deserialize-n-ary-tree/
	 */

}
