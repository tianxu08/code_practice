package small_yan;

import ds.Tree;
import ds.TreeNode;

public class Class17_Task4MaxTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * For each of the nodes in max tree, the node itself is the largest one within alll nodes in its
	 * subtree. No duplicate elements in the tree
	 */
	
	/*
	 * If we know the inOrder sequence, construct the tree
	 */
	public static TreeNode construct(int[] inOrder, int left, int right) {
		// corner case
		if (left > right) {
			return null;
		}
		
		// find the max from the subarray from left to right
		int rootIdx = findMax(inOrder, left, right);
		TreeNode root = new TreeNode(inOrder[rootIdx]);
		
		// split two subarrays
		root.left = construct(inOrder, left, rootIdx - 1);
		root.right = construct(inOrder, rootIdx + 1, right);
		return root;
	}
	
	public static int findMax(int[] arr, int left, int right) {
		if (left > right) {
			return -1;
		}
		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		for(int i = left; i <= right; i ++) {
			if (arr[i] > max) {
				max = arr[i];
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	public static void test() {
		int[] inOrder = {1,2,16,6,10,7};
		TreeNode root = construct(inOrder, 0, inOrder.length - 1);
		Tree.inOrder(root);
	}
	
	
	
	/*
	 * Time Complicity:
	 * O(n^2)
	 */
	
	/*
	 * suppose the inorder traversal sequence is NOT given you at once, and it is a stream. 
	 * The requirement is to at anytime, I need to know what is the max tree reconstructed by the 
	 * stream so far.
	 */

}
