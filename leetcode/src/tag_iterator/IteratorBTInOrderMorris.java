package tag_iterator;

import ds.TreeNode;

public class IteratorBTInOrderMorris {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 *     1
	 *     /\
	 *    2  3
	 *   /\
	 *  4  5
	 *  
	 *  inorder: 4 2 5 1 3
	 */
	
	
	private TreeNode current;
	
	public boolean hasNext() {
		return current != null;
	}
	
	public TreeNode next() {
		TreeNode reNode = null;
		while(true) {
			if (current.left == null) {
				reNode = current;
				current = current.right;
				break;
			} else {
				TreeNode predecessor = current.left;
				while(predecessor.right != null && predecessor.right != current) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else {
					reNode = current;
					predecessor.right = null;
					current = current.right;
					break;
				}
			}
		}
		return reNode;
	}
}
