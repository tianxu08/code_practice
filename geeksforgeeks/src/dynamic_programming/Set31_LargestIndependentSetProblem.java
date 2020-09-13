package dynamic_programming;


public class Set31_LargestIndependentSetProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
	}
	/*
	 * http://www.geeksforgeeks.org/largest-independent-set-problem/
	 */

	/*
	 * Given a Binary Tree, find size of the Largest Independent Set(LIS) in it.
	 * A subset of all tree nodes is an independent set if there is no edge
	 * between any two nodes of the subset. For example, consider the following
	 * binary tree. The largest independent set(LIS) is {10, 40, 60, 70, 80} and
	 * size of the LIS is 5.
	 * 
	 *        
	 */
	
	/*
	 * LLIS(X) = Max{	
	 * 					1 + sum of LLIS for all grand children of X, 
	 * 					sum of LLIS for all children ox X
	 * 				}
	 */
	
	public static class Node1 {
		public int val;
		public Node1 left;
		public Node1 right;
		public Node1(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
		}
	}
	
	public static int LLISRec(Node1 node) {
		if (node == null) {
			return 0;
		}
		//get the size of LLIS excluding the current node
		int size_exclude = LLISRec(node.left) + LLISRec(node.right);
		
		
		int size_include = 1;
		if (node.left != null) {
			size_include += LLISRec(node.left.left) + LLISRec(node.left.right);
		}
		if (node.right != null) {
			size_include += LLISRec(node.right.left) + LLISRec(node.right.right);
		}
		
		return Math.max(size_include, size_exclude);
	}
	
	public static void test1() {
		Node1 n1 = new Node1(10);
		Node1 n2 = new Node1(20);
		Node1 n3 = new Node1(30);
		Node1 n4 = new Node1(40);
		Node1 n5 = new Node1(50);
		Node1 n6 = new Node1(60);
		Node1 n7 = new Node1(70);
		Node1 n8 = new Node1(80);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n5.left = n7;
		n5.right = n8;
		n3.right = n6;
		
		int llis = LLISRec(n1);
		System.out.println("llis = " + llis);
		
	}
	
	public static class Node2 {
		public int val;
		public int llis;
		public Node2 left;
		public Node2 right;
		public Node2(int v) {
			this.val = v;
			this.llis = 0;
			this.left = null;
			this.right = null;
		}
	}
	
	public static int LLISMemo(Node2 node) {
		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			node.llis = 1;
			return node.llis;
		}
		if (node.llis != 0) {
			return node.llis;
		}
		
		int size_exclude = LLISMemo(node.left) + LLISMemo(node.right);
		
		int size_include = 1;
		if (node.left != null) {
			size_include += LLISMemo(node.left.left) + LLISMemo(node.left.right);
		}
		if (node.right != null) {
			size_include += LLISMemo(node.right.left) + LLISMemo(node.right.right);
		}
		node.llis = Math.max(size_include, size_exclude);
		return node.llis;
	}
	
	public static void test2() {
		Node2 n1 = new Node2(10);
		Node2 n2 = new Node2(20);
		Node2 n3 = new Node2(30);
		Node2 n4 = new Node2(40);
		Node2 n5 = new Node2(50);
		Node2 n6 = new Node2(60);
		Node2 n7 = new Node2(70);
		Node2 n8 = new Node2(80);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n5.left = n7;
		n5.right = n8;
		n3.right = n6;
		
		int llis2 = LLISMemo(n1);
		System.out.println("llis2 = " + llis2);
	}

}
