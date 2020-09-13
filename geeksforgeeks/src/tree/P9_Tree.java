package tree;

import java.util.LinkedList;
import java.util.Queue;

public class P9_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test3();
//		test4();
//		test7();
//		test10();
		test5();
//		test8();
	}
	/*
	 * task1 
	 * http://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/
	 * Find k-th smallest element in BST (Order Statistics in BST)
	 * 
	 * !!!
	 * 1: using inorder traversal
	 * 2: change the treeNode structure. add subtreeSize 
	 */
	public static class TreeNode2 {
		public int val;
		public int leftCount;
		public TreeNode2 left;
		public TreeNode2 right;
		public TreeNode2(int v) {
			this.val = v;
			leftCount = 0;
			left = null;
			right = null;
		}
	}
	public static int kSamllestElement(TreeNode2 root, int k) {
		int ret = -1;
		if (root != null) {
			TreeNode2 p = root;
			while(p != null) {
				if ((p.leftCount + 1) == k) {
					ret = root.val;
					break;
				} else if (k > p.leftCount + 1) {
					// the elements in left subtree is not enough. 
					// we need to go to the right subtree
					k = k - (p.leftCount + 1);
					p = p.right;
				} else {
					// the node is on the left subtree
					p = p.left;
				}
			}
		}
		return ret;
	}
	
	public static TreeNode2 buildTree() {
		return null;
	}
	
	public static TreeNode2 insert(TreeNode2 root, TreeNode2 node) {
		TreeNode2 p = root;
		TreeNode2 curParent = root;
		
		while(p != null) {
			curParent = p;
			if (node.val < p.val) {
				// go left;
				p.leftCount ++;
				p = p.left;
			} else {
				p = p.right;
			}
		}
		if (root == null) {
			root = node;
		} else if (node.val < curParent.val) {
			curParent.left = node;
		} else {
			curParent.right = node;
		}
		return root;
	}
	
	/*
	 * task2
	 * inOrder Successor in Binary Search Tree
	 * http://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
	 */
	/*
	 * (1)node.right != null, the successor is in the right subtree.
	 * (2)node.right == null, start from the root. search. 
	 *    if data > root.data, go right; otherwise, go left
	 */
	public static TreeNode inOrderSuccessor(TreeNode root, TreeNode node) {
		if (node == null) {
			return null;
		}
		//(1)
		if (node.right != null) {
			return getMin(node.right);
		}
		
		//(2)
		TreeNode successor = root;
		TreeNode p = root;
		while(p != null) {
			if (node.val < p.val) {
				
				successor = p;
				p = p.left;
			} else if (node.val > p.val) {
				p = p.right;
			} else {
				break;
			}
		}
		return successor;
	}
	
	public static TreeNode getMin(TreeNode node) {
		TreeNode cur = node;
		while(cur.left != null){
			cur = cur.left;
		}
		return cur;
	}
	
	public static void test2() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		TreeNode node = root.left.right.right;
		TreeNode succ = inOrderSuccessor(root, node);
		System.out.println(node.val);
		System.out.println(succ.val);
	}
	
	/*
	 * task3
	 * Sorted order printing of a given array that represents a BST
	 * http://www.geeksforgeeks.org/sorted-order-printing-of-an-array-that-represents-a-bst/
	 */
	
	public static void printSorted(int[] arr, int start, int end) {
		if (start > end) {
			return ;
		}
		printSorted(arr, start * 2 + 1, end);
		System.out.print(arr[start] + " ");
		printSorted(arr, start * 2 + 2, end);
	}
	public static void printSorted(int[] arr) {
		int start = 0, end = arr.length - 1;
		printSorted(arr, start, end);
	}
	
	public static void test3() {
		int[] a = {4,2,5,1,3};
		printSorted(a);
	}
	
	/*
	 * task4
	 * Print nodes at k distance from root
	 * http://www.geeksforgeeks.org/print-nodes-at-k-distance-from-root/
	 * 
	 * traversal the tree
	 */
	
	public static void kDistanceFromRoot(TreeNode root, int k) {
		if (root == null) {
			return ;
		}
		if (k == 0) {
			System.out.print(root.val + " ");
			return ;
		} else {
			kDistanceFromRoot(root.left, k - 1);
			kDistanceFromRoot(root.right, k - 1);
		}
	}
	
	public static void test4() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		int k = 2;
		kDistanceFromRoot(root, k);
	}
	
	/*
	 * task5
	 * Foldable Binary Trees
	 * http://www.geeksforgeeks.org/foldable-binary-trees/
	 * 
	 * A tree can be folded if left and right subtrees of 
	 * the tree are structure wise mirror image of each other. 
	 * An empty tree is considered as foldable.
	 * 
	 * use helper function, isMirror(TreeNode one, TreeNode two)
	 * values does NOT matter. 
	 * 
	 * extinsion of same tree and symetric tree
	 */
	public static void test5() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		
		TreeNode root = node1;
		node1.left = node2;
		node2.right = node4;
		node1.right = node3;
		node3.left = node5;
		
		boolean isFoldable= foldableBinaryTree(root);
		System.out.println("isFoldable = " + isFoldable);
	}
	
	
	public static boolean foldableBinaryTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isMirror(root.left, root.right);
	}
	public static boolean isMirror(TreeNode one, TreeNode two) {
		if(one == null && two == null) {
			return true;
		}
		if(one == null || two == null) {
			return false;
		}
		return isMirror(one.left, two.right) && isMirror(one.right, two.left);
	}
	
	

	/*
	 * task6
	 * Total number of possible Binary Search Trees with n keys
	 * http://www.geeksforgeeks.org/g-fact-18/
	 * 
	 * DP              f[0] = 1
	 * n = 1  sln = 1  f[1] = 1                         1 as root  2 as root
	 * n = 2  sln = 2   1 as root, 2 as root  f[2] = f[0]*f[1]  + f[1][0]
	 *   2    1
	 *  /      \
	 * 1        2  
	 * n = 3  
	 *   1 as root f[3] =  f[0]*f[2] + f[1]*f[1] + f[2][0]
	 *   1
	 * 
	 * ...
	 * f[n] = sum (f[i]*f[n - i -1])  (i >= 0 && i <= n-1)
	 * 
	 * covered in leetcode. 
 	 */
	
	
	
	
	
	
	
	/*
	 * task7
	 * Maximum width of a binary tree
	 * http://www.geeksforgeeks.org/maximum-width-of-a-binary-tree/
	 * 
	 * Given a binary tree, write a function to get the maximum width of the given tree. 
	 * Width of a tree is maximum of widths of all levels.
	 * 
	 * two says: 
	 * (1)BFS
	 * (2)DFS
	 */
	
	/*
	 * breadth first search. BFS. level order traversal
	 * we only need to compare maxWidth with every layer's size.
	 */
	public static int maxWidth(TreeNode root) {
		int maxWidth = Integer.MIN_VALUE;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			// get the maxWidth 
			maxWidth = Math.max(maxWidth, size);
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
		}
		return maxWidth;
	}
	public static void test7() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		
		node1.left = node2;
		node1.right = node3;
		
		node2.left = node4;
		node2.right = node5;
		
		node3.right = node8;
		node8.left = node6;
		node8.right = node7;
		
		TreeNode root = node1;
		
		int maxWidth = maxWidth(root);
		int maxWidth2 = maxWidth2(root);
		System.out.println("maxWidth = " + maxWidth);
		System.out.println("maxWidth2 = " + maxWidth2);
	}
	
	//DFS
	// use preOrder traversal. use a array to count every level's width
	// first, we need to get the height of the tree. 
	// use a helper function (preOrder) to get the count array. 
	public static int maxWidth2(TreeNode root) {
		int height = getHeight(root);
		int[] count = new int[height];
		int level = 0;
		
		// get the count array
		getMaxWidRec(root, count, level);
		
		// get the maxWidth
		int maxWidth = Integer.MIN_VALUE;
		for (int i = 0; i < count.length; i++) {
			maxWidth = Math.max(maxWidth, count[i]);
		}
		return maxWidth;
	}
	
	
	public static void getMaxWidRec(TreeNode node, int[] count, int level) {
		if (node == null) {
			return ;
		}
		count[level] ++;
		getMaxWidRec(node.left, count, level + 1);
		getMaxWidRec(node.right, count, level + 1);
	} 
	
	public static int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}

	
	/*
	 * task8
	 * Double Tree
	 * http://www.geeksforgeeks.org/double-tree/
	 * 
	 * use the idea of post order. !!!
	 * 
	 *    1
	 *   / \
	 *  2   3
	 *  
	 *  
	 *   		1
	 *     	   / \
	 *        1   3
	 *       /   /
	 *      2   3
	 *     /
	 *    2    
	 *    
	 *  for every node, duplicate itself, link the duplicates as left child.
	 *  the original leftchild as the duplicates.left, so, we need to store the old left pointer
	 *  
	 *  Algorithm:
	 *  for every node.
	 *  TreeNode oldLeft = node.left; 
	 *  double(node.left)
	 *  double(node.right)
	 *  
	 *  // now, we are in th current layer
	 *  TreeNode dup = new TreeNode(node.val);
	 *  node.left = dup;
	 *  dup.left = oldLeft;
	 *  
	 *  return node;  // return to the parent layer.
	 *  
	 *    
	 */
	public static TreeNode doubleTree(TreeNode node) {
		if (node == null) {
			return null;
		}
		TreeNode oldLeft = node.left;
		doubleTree(node.left);
		doubleTree(node.right);
		
		TreeNode new_node = new TreeNode(node.val);
		node.left = new_node;
		new_node.left = oldLeft;
		
		return node;
	}
	
	public static void test8() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.left = node2;
		node1.right = node3;
		
		TreeNode root = node1;
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		TreeNode double_root = doubleTree(root);
		Tree.preOrder(double_root);
		Tree.inOrder(double_root);
	}
	
	
	/*
	 * task9
	 * Construct Tree from given Inorder and Preorder traversals
	 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
	 * 
	 * classic
	 */
	
	
	
	
	/*
	 * task10 
	 * Root to leaf path sum equal to a given number
	 * http://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
	 * 
	 * Given a binary tree and a number, return true if the tree has a
	 * root-to-leaf path such that adding up all the values along the path
	 * equals the given number. Return false if no such path can be found.
	 * 
	 * recursively check if left or right child has path sum equal to (num - cur.val)
	 * http://cslibrary.stanford.edu/110/BinaryTrees.html
	 */
	
	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return sum == 0;
		} else {
			boolean res = false;
			int subSum = sum - root.val;
			if (subSum == 0 && root.left == null && root.right ==null) {
				// if subSum == 0 && cur_node == leaf
				// return true
				return true;
			}
			
			// go left
			if (root.left != null) {
				res = res || hasPathSum(root.left, subSum);
			}
			
			// go right
			if (root.right != null) {
				res = res || hasPathSum(root.right, subSum);
			}
			return res;
		}
	}
	
	public static void test10() {
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(2);
		node1.left = node2;
		node1.right = node3;
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(2);
		node2.left = node4;
		node2.right = node5;
		
		node3.left = node6;
		TreeNode root = node1;
		
		int sum = 21;
		System.out.println(hasPathSum(root, sum));	
	}
}
