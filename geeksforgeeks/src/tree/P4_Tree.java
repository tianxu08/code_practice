package tree;

public class P4_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1_1();
		test10();
	}
	
	/*
	 * task1
	 * Print ancestors of a given binary tree node without recursion
	 * http://www.geeksforgeeks.org/print-ancestors-of-a-given-binary-tree-node-without-recursion/
	 */
	
	
	
	/*
	 * task1.1
	 * print ancestors of a given binary tree node with recursion
	 * http://www.geeksforgeeks.org/print-ancestors-of-a-given-node-in-binary-tree/
	 */
	public static void test1_1() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		int key = 9;
		printAncestor(root, key);
	}
	public static boolean printAncestor(TreeNode root, int key) {
		if (root == null) {
			return false;
		}
		if (root.val == key) {
			return true;
		}
		boolean inLeft = printAncestor(root.left, key);
		boolean inRight = printAncestor(root.right, key);
		
		if (inLeft || inRight) {
			// the current node is an ancestor of given key
			System.out.print(root.val + " ");
			return true;
		} 
		
		return false;
	}
	
	
	
	/*
	 * task2
	 * Difference between sums of odd level and even level nodes of a Binary Tree
	 * http://www.geeksforgeeks.org/difference-between-sums-of-odd-and-even-levels/
	 */
	
	/*
	 * task3
	 * Print Postorder traversal from given Inorder and Preorder traversals
	 * http://www.geeksforgeeks.org/print-postorder-from-given-inorder-and-preorder-traversals/
	 */
	
	/*
	 * task4
	 * Find depth of the deepest odd level leaf node
	 * http://www.geeksforgeeks.org/find-depth-of-the-deepest-odd-level-node/
	 */
	
	/*
	 * task5
	 * Check if all leaves are at same level
	 * http://www.geeksforgeeks.org/check-leaves-level/
	 */
	
	/*
	 * task6
	 * Print Left View of a Binary Tree
	 * http://www.geeksforgeeks.org/print-left-view-binary-tree/
	 * 
	 * Refer to P2
	 */
	
	/*
	 * task7
	 * Add all greater values to every node in a given BST
	 * http://www.geeksforgeeks.org/add-greater-values-every-node-given-bst/
	 * 
	 * Similar with P2_task5
	 * the only difference is 
	 * in this task, node.val = node.val + sum(greater element's value)
	 * In P2_task, node.val = sum(greater elemtn's value)
	 * 
	 */
	
	
	
	
	
	/*
	 * task8
	 * Remove all nodes which don’t lie in any path with sum>= k
	 * http://www.geeksforgeeks.org/remove-all-nodes-which-lie-on-a-path-having-sum-less-than-k/
	 * 
	 */
	
	/*
	 * task9
	 * Extract Leaves of a Binary Tree in a Doubly Linked List
	 * http://www.geeksforgeeks.org/connect-leaves-doubly-linked-list/
	 */
	
	/*
	 * task10
	 * Deepest left leaf node in a binary tree
	 * http://www.geeksforgeeks.org/deepest-left-leaf-node-in-a-binary-tree/
	 * 
	 * Recursion:
	 * MaxLevel to store the max level so far
	 * DeepestLeaf to store the deepest leaf so far
	 * isLeft: indicate whether this node is left child of its parent. 
	 */
	public static class MaxLevel{
		int val;
		public MaxLevel(int v) {
			this.val = v;
		}
	}
	public static class DeepestLeaf {
		TreeNode val;
		public DeepestLeaf(TreeNode n) {
			this.val = n;
		}
	}
	public static TreeNode deepLeftLeaf(TreeNode root) {
		if (root == null) {
			return null;
		}
		MaxLevel maxLevel = new MaxLevel(-1);
		DeepestLeaf dLeaf = new DeepestLeaf(null);
		boolean isLeft = false;
		int level = 0;
		deepestLeaf(root, level, maxLevel, isLeft, dLeaf);
		
		return dLeaf.val;
	}
	
	
	/*
	 * Recursion:
	 * MaxLevel to store the max level so far
	 * DeepestLeaf to store the deepest leaf so far
	 * isLeft: indicate whether this node is left child of its parent. 
	 */
	public static void deepestLeaf(TreeNode node, int level, MaxLevel maxLevel, boolean isLeft, DeepestLeaf dLeaf) {
		// base case
		if (node == null) {
			return ;
		}
		if (isLeft && node.left == null && node.right == null && level > maxLevel.val ) {
			dLeaf.val = node;
			maxLevel.val =level;
			return ;
		}
		deepestLeaf(node.left, level, maxLevel, true, dLeaf);
		deepestLeaf(node.right, level, maxLevel, false, dLeaf);
	}
	
	public static void test10() {
		int[] a = {1,2,3,4,5,6,7,8,9};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		Tree.postOrder(root);
		
		TreeNode deepLeftLeaf = deepLeftLeaf(root);
		System.out.println("deepLeftLeaf.val = " + deepLeftLeaf.val);
		
	}
	
	/*
	 * task11
	 * Find next right node of a given key
	 * http://www.geeksforgeeks.org/find-next-right-node-of-a-given-key/
	 * 
	 * Do a level Order traversal.
	 * 
	 */
	
	/*
	 * task12
	 * Splay Tree | Set 1 (Search)
	 * http://www.geeksforgeeks.org/splay-tree-set-1-insert/
	 * 
	 * http://www.geeksforgeeks.org/splay-tree-set-2-insert-delete/
	 * 
	 */

}
