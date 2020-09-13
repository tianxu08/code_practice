package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P10_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test4();
//		test3();
		test13();
//		test8();
	}
	
	/*
	 * task1
	 * 
	 * http://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
	 * How to determine if a binary tree is height-balanced?
	 * 
	 */
	
	public static boolean heightBalanced(TreeNode root) {
		return getHeight(root) != -1;
	}
	public static int balancedHeight(TreeNode node) {
		if(node == null) {
			return 0;
		}
		int leftHeight = balancedHeight(node.left);
		if (leftHeight == -1) {
			return -1;
		}
		int rightHeight = balancedHeight(node.right);
		if (rightHeight == -1) {
			return -1;
		}
		
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/*
	 * task2
	 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
	 * Diameter of a Binary Tree
	 * 
	 * diameter is a longest path from one leaf node to another leaf node
	 * 
	 * Algorithm;
	 * 1 get the diameter of left subtree
	 * 2 get the diameter of right subtree
	 * 3 get the max diameter of this tree
	 */
	
	public static int diameter(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = getHeight(root.left);
		int rightHeight= getHeight(root.right);
		
		int leftDiameter = diameter(root.left);
		int rightDiameter = diameter(root.right);
		
		return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
	}
	
	public static int getHeight(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return Math.max(getHeight(node.left) , getHeight(node.right)) + 1;
	}
	
	public static void test2() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		int diameter = diameter(root);
		System.out.println(diameter);
		int diameter2 = diameterOPT(root);
		System.out.println(diameter2);
	}
	
	// optimize
	public static class Height{
		public int val;
		public Height() {
			this.val = 0;
		}
		public Height(int v) {
			this.val = v;
		}
	}
	public static int diameterOPT(TreeNode root) {
		Height height = new Height();
		return diameterOPT(root, height);
	}
	public static int diameterOPT(TreeNode root, Height height) {
		if (root == null) {
			height.val = 0;
			return 0;  //diameter is 0
		}
		Height leftHeight = new Height();
		Height rightHeight = new Height();
		
		int leftDiameter = diameterOPT(root.left, leftHeight);
		int rightDiameter = diameterOPT(root.right, rightHeight);
		
		height.val = Math.max(leftHeight.val, rightHeight.val) + 1;
		
		return Math.max(leftHeight.val + rightHeight.val + 1, Math.max(leftDiameter, rightDiameter));
	}
	
	/*
	 * task3
	 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
	 * Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
	 * for the children sum property, see the task4
	 * You can only increment data values in any node. 
	 * You cannot change structure of tree and cannot devrement value of any node
	 * 
	 * Algorithm:
	 * Traverse the tree in post order to convert it. 
	 * first change left and right child to hold the children sum property then change the parent node
	 * 
	 * diff = node's children sum - node's data
	 * if diff == 0 then nothing needs to change
	 * if diff > 0  children sum is larger than node's data. update the node's data + diff
	 * if diff < 0  children sum is smaller than node's data update one child's data.  
	 * 				we choose to update the node left data
	 * 				if the left subtree is null, then we update the right subtree's data
	 *    
	 */
	
	public static void convertTree(TreeNode node) {
		if (node == null || (node.left == null && node.right == null)) {
			return ;
		} else {
			// do post order traversal
			convertTree(node.left);
			convertTree(node.right);
			
			// get the children Sum
			int childrenSum = (node.left != null ? node.left.val : 0) + 
								(node.right != null ? node.right.val : 0);
			// get the diff of childrenSum and node.val 
			int diff = childrenSum - node.val;
			if (diff == 0) {
			   // diff == 0 this node is OK. 
				return ;
			} else if (diff > 0) {
				// diff > 0 childrenSum > node.val, we increase the node.val
				node.val = node.val + diff;
			} else {
				// diff < 0 childrenSum, we update the its children recursively. 
				increment(node, -diff);
			}
		}
	}
	
	// first we first update node.left. if the left==null.
	public static void increment(TreeNode node, int dif) {
		if (node.left != null) {
			node.left.val += dif;
			increment(node.left, dif);
		} else if (node.right != null) {
			node.right.val += dif;
			increment(node.right, dif);
		}
	}
	
	public static void test3() {
		TreeNode node1 = new TreeNode(50);
		TreeNode node2 = new TreeNode(7);
		TreeNode node3 = new TreeNode(2);
		node1.left = node2;
		node1.right = node3;
		
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		node2.left = node4;
		node2.right = node5;
		
		TreeNode node6 = new TreeNode(1);
		TreeNode node7 = new TreeNode(30);
		
		node3.left = node6;
		node3.right = node7;
		
		TreeNode root = node1;
		Tree.preOrder(root);
		Tree.inOrder(root);
		convertTree(root);
		Tree.preOrder(root);
		Tree.inOrder(root);
	}
	
	
	
	/*
	 * task4 
	 * Check for Children Sum Property in a Binary Tree.
	 * http://www.geeksforgeeks.org/check-for-children-sum-property-in-a-binary-tree/
	 * 
	 * Given a binary tree, write a function that returns true if the tree
	 * satisfies below property.
	 * 
	 * For every node, data value must be equal to sum of data values in left
	 * and right children. Consider data value as 0 for NULL children.
	 */
	
	public static boolean checkChildrenSumProperty(TreeNode root) {
		
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		int chilerenSum = (root.left != null? root.left.val: 0 ) + 
						  (root.right != null ? root.right.val: 0);
		if (root.val != chilerenSum) {
			return false;
		}
		return (root.val == chilerenSum && checkChildrenSumProperty(root.left) && checkChildrenSumProperty(root.right));
	}
	
	public static void test4() {
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(8);
		TreeNode node3 = new TreeNode(2);
		node1.left = node2;
		node1.right = node3;
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(5);
		node2.left = node4;
		node2.right = node5;
		TreeNode node6 = new TreeNode(2);
		node3.left = node6;
		
		boolean check = checkChildrenSumProperty(node1);
		System.out.println(check);
	}
	
	
	/*
	 * task5
	 * Level order traversal in spiral form
	 * http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
	 * Add a dfs method
	 */
	
	
	/*
	 * taskk6
	 * A program to check if a binary tree is BST or not
	 * use min max method
	 */
	public static boolean isBST(TreeNode root) {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return isBSTHelper(root, min, max);
	}
	
	public static boolean isBSTHelper(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		
		if (node.val < min || node.val > max) {
			return false;
		}
		
		// go left, update the max to node.val
		// go right, update the min to node.val
		return isBSTHelper(node.left, min, node.val) && isBSTHelper(node.right, node.val, max);
	}
	
	/*
	 * task7 
	 * count leaf node in binary tree
	 * http://www.geeksforgeeks.org/write-a-c-program-to-get-count-of-leaf-nodes-in-a-binary-tree/
	 * 
	 */
	
	public static int countLeaf(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int sum = 0;
		sum += countLeaf(root.left);
		sum += countLeaf(root.right);
		return sum;
	}
	
	/*
	 * task8 
	 * Level Order Tree Traversal
	 * http://www.geeksforgeeks.org/level-order-tree-traversal/
	 */
	public static void levelOrderTraversal(TreeNode root) {
		if (root == null) {
			return ;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				System.out.print(cur.val + " ");
				if (cur.left != null) {
					q.add(cur.left);
				}
				if (cur.right != null) {
					q.add(cur.right);
				}
			}
			System.out.println();
		}
	}
	public static void test8() {
		int[] a = {1,2,3,4,5,6,7};
		TreeNode root = Tree.createTree(a);
		levelOrderTraversal(root);
	}
	
	/*
	 * task9 
	 * Find the node with minimum value in a Binary Search Tree
	 * http://www.geeksforgeeks.org/find-the-minimum-element-in-a-binary-search-tree/
	 * easy
	 * find the left most node
	 */
	
	
	/*
	 * task10
	 * The Great Tree-List Recursion Problem.
	 * http://www.geeksforgeeks.org/the-great-tree-list-recursion-problem/
	 * 
	 * !!!
	 */
	
	
	/*
	 * 求二叉树中两个节点的最低公共祖先节点： 
	 * (1) LAC 求解最小公共祖先, 使用list来存储path. 算法复杂度是：O(n),
	 * 这里遍历2次，加一次对最高为H的list的遍历. 并且有O(h)的空间复杂度。 
	 * (2) LCARec 递归算法 . 算法复杂度是：O(n) 
	 * (3) LCABstRec 递归求解BST树. 算法复杂度: O(logN).
	 */
	/*
	 * task11
	 * Lowest Common Ancestor in a Binary Search Tree.
	 * http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
	 * 
	 * we make an assumption that n1 < n2
	 */
	public static TreeNode LCABST(TreeNode root, int n1, int n2) {
		if (root == null) {
			return null;
		}
		if (n1 <= root.val && root.val <= n2) {
			return root;
		} else if (n2 < root.val) {
			
			return LCABST(root.left, n1, n2);
		} else {
			return LCABST(root.right, n1, n2);
		}
	}
	
	/*
	 * task12
	 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
	 * Lowest Common Ancestor in a Binary Tree | Set 1
	 */
	/*
	 * solution 1
	 *  store the root to n1 and root to n2 path
	 *  (1) find path from root to n1 and store to ArrayList<TreeNode> path1
	 *  (2) find path from root to n2 and store to ArrayList<TreeNode> path2
	 *  (3) traverse both  until the values in arrays are same. 
	 */
	
	public static TreeNode LCABS(TreeNode root, int n1, int n2) {
		ArrayList<TreeNode> path1 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> path2 = new ArrayList<TreeNode>();
		
		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
			return null;
		}
		int i = 0;
		for (; i < path1.size() && i < path2.size(); i++) {
			if (path1.get(i) != path2.get(i)) {
				// continue until the path has different. 
				break;
			}
		}
		// then return the previous node
		return path1.get(i - 1);
	}
	
	public static boolean findPath(TreeNode root, int n, ArrayList<TreeNode> path) {
		if (root == null) {
			return false;
		}
		path.add(root);
		if (root.val == n) {
			return true;
		}
		if ((root.left != null && findPath(root.left, n, path)) || 
			(root.right != null && findPath(root.right, n, path))) {
			return true;
		}
		path.remove(path.size() - 1);
		return false;	
	}
	
	public static boolean findPath2(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {
		if (root == null || node == null) {
			return false;
		}
		path.add(root);
		if (root == node) {
			// we already found it
			return true;
		} else {
			// root != node
			if (findPath2(root.left, node, path) || findPath2(root.right, node, path)) {
				// we find in root.left or in root.right
				return true;
			}
			path.remove(path.size() - 1);
			return false;
		}
	}
	
	
	/*
	 * solution 2 
	 *  求二叉树中两个节点的最低公共祖先节点：
     * Recursion Version:
     * LACRec 
     * 1. If found in the left tree, return the Ancestor.
     * 2. If found in the right tree, return the Ancestor.
     * 3. If Didn't find any of the node, return null.
     * 4. If found both in the left and the right tree, return the root.
	 */
	public static TreeNode LCAOPT(TreeNode root, int n1, int n2) {
		if (root == null) {
			return null;
		}
		if (root.val == n1 || root.val == n2) {
			return root;
		}
		TreeNode left = LCAOPT(root.left, n1, n2);
		TreeNode right = LCAOPT(root.right, n1, n2);
		
		if (left != null && right != null) {
			return root;
		}
		if (left != null) {
			return left;
		}
		if (right != null) {
			return right;
		}
		return null;
	}
	
	public static TreeNode LCARec(TreeNode root, TreeNode node1, TreeNode node2) {
		if (root == null || node1 == null || node2 == null) {
			return null;
		}
		// if any of the node == root, return root;
		if (node1 == root || node2 == root) {
			return root;
		}
		// if no node equals root, just recursively find it in Left and Right tree
		TreeNode left = LCARec(root.left, node1, node2);
		TreeNode right = LCARec(root.right, node1, node2);
		
		if (left == null) {
			// we didn't find in left side
			return right;
		}
		if (right ==null) {
			// we didn't find the right side
			return left;
		}
		// otherwise, left is not null, right is not null, so in this case, only root is the 
		// lowest common ancestor
		return root;
	}
	
	
	/*
	 * task13
	 * Given a binary tree, print out all of its root-to-leaf paths one per line.
	 * http://www.geeksforgeeks.org/given-a-binary-tree-print-out-all-of-its-root-to-leaf-paths-one-per-line/ 
	 */
	public static void allRoot2LeafPath(TreeNode root) {
		int len = getHeight(root);
		int[] path = new int[len];
		helper(root, path, 0);
	}
	
	public static void test13() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		allRoot2LeafPath(root);
		
		ArrayList<ArrayList<Integer>> result = allRoot2LeafPath2(root);
		System.out.println(result);
	}
	
	public static void helper(TreeNode node, int[] path, int len) {
		if (node == null) {
			return ;
		}
	    path[len] = node.val;
	    len ++;
	    
	    if (node.left == null && node.right == null) {
			printArray(path, len);
		} else {
			helper(node.left, path, len);
			helper(node.right, path, len);
		}
	}
	
	public static void printArray(int[] path, int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}
	
	public static ArrayList<ArrayList<Integer>> allRoot2LeafPath2(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		helper2(root, result, path);
		return result;
	}
	public static void helper2(TreeNode node, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path) {
		if (node == null) {
			return ;
		}
		path.add(node.val);
		if (node.left == null && node.right == null) {
			result.add(new ArrayList<Integer>(path));
		} else {
			helper2(node.left, result, path);
			helper2(node.right, result, path);
		}
		// don't forget to remove the last element.  
		path.remove(path.size() - 1);
	}
	
	
	
	/*
	 * task14
	 * Write an Efficient C Function to Convert a Binary Tree into its Mirror Tree
	 * http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
	 * 
	 * use a post order traversal. 
	 * mirror(node)
	 * if node == null
	 *    return;
	 * else 
	 *    mirror(node.left)
	 *    mirror(node.right)
	 *    
	 *    // switch the left subtree and right subtree
	 *    TreeNode temp = node.left;
	 *    node.left = node.right;
	 *    node.right = temp;
	 */
	
	public static void mirror(TreeNode node) {
		if (node == null) {
			return ;
		} else {
			mirror(node.left);
			mirror(node.right);
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}
	}	
}
