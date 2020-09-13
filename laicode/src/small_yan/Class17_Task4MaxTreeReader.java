package small_yan;

import java.util.LinkedList;

import ds.TreeNode;

public class Class17_Task4MaxTreeReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	TreeNode root;
	
	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
	// read the next number in the order sequence
	
	public void read(int number) {
		TreeNode cur = null;
		while(!stack.isEmpty() && stack.peek().val < number) {
			cur = stack.poll();
		}
		TreeNode newNode = new TreeNode(number);
		newNode.left = cur;
		
		if (stack.isEmpty()) {
			root = newNode;
		} else {
			stack.peek().right = newNode;
		}
		stack.push(newNode);
	}
	
	// any time, when call this method, return the tree reconstruced by 
	// the inOrder sequence read so far
	public TreeNode currentTree() {
		return root;
	}
}
