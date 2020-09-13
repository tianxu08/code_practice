package tree;

public class P11_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * task1 
	 * identical tree
	 * http://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
	 */
	public static boolean sameTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 == null || t2 == null) {
			return false;
		}
		return t1.val == t2.val && sameTree(t1.left, t2.left) && sameTree(t1.right, t2.right);
	}
	
	/*
	 * task1.1
	 * symmetric tree
	 * 
	 */
	public static boolean symmetricTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 == null || t2 == null) {
			return false;
		}
		return t1.val == t2.val && symmetricTree(t1.left, t2.right) && symmetricTree(t1.right, t2.left);
	}
	/*
	 * task1.2
	 * subTree problem 
	 * Check if a binary tree is subtree of another binary tree | Set 1
	 * http://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/
	 * 
	 * get the idea of sameTree(). tree1 and tree2. let the tree2 be the bigger tree. 
	 * traverse the tree2. 
	 * if node.val = root1.val , call the sameTree(node, root1)
	 * other wise, go left and go right. 
	 * 
	 * Time: O(m * n)
	 * 
	 * 
	 * Check if a binary tree is subtree of another binary tree | Set 2
	 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
	 * Linear O(m + n)
	 * 
	 * We can use preOrder and inOrder to construct a unique tree. 
	 * do preOrder and inOrder traversal for both of tree. 
	 * Store them as inOrder1[] inOrder2[] and preOrder1[]  preOrder2[]
	 * if inOrder1[] is subArray of inOrder2[]
	 *    preOrder1[] is subArray of preOrder2[]
	 * then, tree1 is subtree of tree2.  we can do this using kmp in linear time. 
	 * 
	 */
	
	
	/*
	 * task1.4
	 * flodable tree
	 */
	
	
	/*
	 * task2
	 * tree traversal
	 * http://www.geeksforgeeks.org/618/
	 * 
	 */
	
	/*
	 * task3 
	 * Calculate Size of a tree
	 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-size-of-a-tree/
	 */
	
	public static int getSize(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int sum = 1;
		sum += getSize(root.left);
		sum += getSize(root.right);
		
		return sum;
	}
	
	/*
	 * task4
	 * get Maximum depth
	 * http://www.geeksforgeeks.org/write-a-c-program-to-calculate-size-of-a-tree/
	 */
	
	public static int getMaxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int leftMaxDep = getMaxDepth(node.left);
		int rightMaxDep = getMaxDepth(node.right);
		
		return Math.max(leftMaxDep, rightMaxDep) + 1;
	}
	
	/*
	 * task5 
	 * get Min Depth
	 */
	
	public static int getMinDeptt(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftMinDep = getMinDeptt(node.left);
		int rightMinDep = getMinDeptt(node.right);
		
		return Math.min(leftMinDep, rightMinDep) + 1;
	}
}
