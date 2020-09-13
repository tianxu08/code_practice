package review;

import java.util.List;

import ds.TreeNode;

public class Ch11_recursion2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test3_1();
	}
	
	/* Group 1: Recursion with LinkedList
	 * 1.1 reverse linked list in pairs
	 * 
	 * 1.2 reverse linked list in triple elements
	 */
	
	/*
	 * Group 2: recursion wiht 1D and 2D array
	 * 2.1  1D array: 二分法比较常见
	 * 2.1.1 merge sort
	 * 2.1.2 quick sort
	 * 2.2  2D array
	 * 2.2.1 逐层递归(row by row): 8 queen
	 * 2.2.1 剥洋葱递归: Spiral Print 2D array
	 */
	
	/*
	 * Group 3: recursion with string
	 * 3.1 
	 * A word such as “book” can be abbreviated to 4, 1o1k, b3, b2k, etc. 
	 * Given a string and an abbreviation, return if the string matches the abbreviation.  
	 * Assume the original string only contains alphabetic characters. 
	 * For example:  “s11d” matches “sophisticated”.
	 * 
	 */
	public static void test3_1() {
		String s1 = "book";
		String[] s2Arr = {"4", "1o1k", "b3", "b4", "b2k"};
		
		for(int i = 0; i < s2Arr.length; i++) {
			boolean rev = t3_1_abbrevMatch(s1, s2Arr[i]);
//			boolean rev2 = match(s1, s2Arr[i]);
			System.out.println(s1 + " matches " + s2Arr[i] + " is " + rev);
		}
	}
	
	public static boolean t3_1_abbrevMatch(String s1, String s2) {
		if (s1.length() == 0 && s2.length() == 0) {
			return true;
		}  
		if (s1.length() == 0 || s2.length() == 0) {
			return false;
		}
		
		// case 1. s2[0] is digit
		if (Character.isDigit(s2.charAt(0))) {
			// get the digit
			int num = 0;
			int i = 0;
			while(i < s2.length() && Character.isDigit(s2.charAt(i))) {
				num = num * 10 + (s2.charAt(i) - '0');
				i ++;
			}
			
			if (num > s1.length()) {
				return false;
			} else {
				// abcde  with 3de   now, num = 3, i = 3, matches [de] with [de] 
				return t3_1_abbrevMatch(s1.substring(num), s2.substring(i));
			}
			
		} else {
			// case2: s2[0] is a char
			if (s1.charAt(0) != s2.charAt(0)) {
				return false;
			} else {
				return t3_1_abbrevMatch(s1.substring(1), s2.substring(1));
			}
		}
	}
	
	
	
	/*
	 * Group 5: recursion with tree
	 * 5.1:lowest common ancestor I 
	 * Assume: there is no parent pointer for the nodes in the binary tree
	 *         the given two nodes are guaranteed to be in the binary tree
	 *5.2: lowest common ancestor II
	 * assumption: there is parent pointer
	 *             the given two nodes are NOT guaranteed to be in the binary tree
	 *             
	 *5.3: lowest common ancestor III
	 * assumption: there is NO parent pointer in the binary tree. 
	 *             the given two nodes are NOT guaranteed to be in the binary tree
	 *5.4: lowest common ancestor of given K nodes in binary tree
	 * assumption: there is no parent pointer for the nodes in the binary tree
	 *             The given K nodes are guaranteed to be in the binary tree
	 *             
	 */
	/*
	 * 1. What do you expect from your lchild / rchild?
	 * 2. What do you want to do in the current layer?
	 * 3. What do you want to report to your parent? (same as Q1 == Q3)
	 *     it is usually the return type of the Recursion function
	 * */
	
	public TreeNode t5_1_lowestCommonAncestor(TreeNode root, TreeNode one,
			TreeNode two) {
		// write your solution here
		
		/**
		 * 1. What do you expect from your lchild / rchild?
				whether there is a OR b in its left subtree, return a or b’ pointer if Yes
				whether there is a OR b in its right subtree, return a or b’s pointer if Yes
		 */
		if (root == null) {
			return null;
		}
		if (root == one) {
			return root;
		}
		if (root == two) {
			return root;
		}
		 
		 /* 2. What do you want to do in the current layer?
				determine whether the value from lchild is NULL or not the value from rchild is NULL or not
				if both are not NULL, then we need to update the value to report to my parent
		 */
		TreeNode left = t5_1_lowestCommonAncestor(root.left, one, two);
		TreeNode right = t5_1_lowestCommonAncestor(root.right, one, two);

		if (left != null && right != null) {
			return root;
		}
		
		/*
		3. What do you want to report to your parent? (same as Q1 == Q3)
				it is usually the return type of the Recursion function
		*/
		return left != null ? left : right;
	}

	public class TreeNodeP {
		public int key;
		public TreeNodeP left;
		public TreeNodeP right;
		public TreeNodeP parent;

		public TreeNodeP(int key, TreeNodeP parent) {
			this.key = key;
			this.parent = parent;
		}
	}

	
	
	public TreeNodeP t5_2_lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		// write your solution here
		if (one == null || two == null) {
			return null;
		}
		TreeNodeP ptr1 = one;
		TreeNodeP ptr2 = two;
		while (ptr1 != null && ptr1.parent != null) {
			ptr1 = ptr1.parent;
		}
		while (ptr2 != null && ptr2.parent != null) {
			ptr2 = ptr2.parent;
		}

		if (ptr1 != ptr2) {
			// they are not in the same tree.
			return null;
		} else {
			// they are in the same tree.
			int l1 = length(one);
			int l2 = length(two);

			if (l1 > l2) {
				return LCA(one, two, l1 - l2);
			} else {
				return LCA(two, one, l2 - l1);
			}
		}
	}

	public TreeNodeP LCA(TreeNodeP large, TreeNodeP small, int diff) {

		while (diff > 0) {

			large = large.parent;
			diff--;
		}

		while (large != small) {

			large = large.parent;
			small = small.parent;

		}
		return large;
	}

	public int length(TreeNodeP node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.parent;
		}
		return length;
	}

	public TreeNode t_5_3_lowestCommonAncestor(TreeNode root, TreeNode one,
			TreeNode two) {
		// write your solution here
		if (root == null) {
			return null;
		}
		if (one == null || two == null) {
			return null;
		}
		Wrapper wrapper = new Wrapper(false, false);
		exist(root, one, two, wrapper);
		if (wrapper.firstExist == false || wrapper.secondExist == false) {
			return null;
		} else {
			// both nodes are in root.
			return LCAHelper(root, one, two);
		}

	}

	public class Wrapper {
		public boolean firstExist;
		public boolean secondExist;

		public Wrapper(boolean x, boolean y) {
			this.firstExist = x;
			this.secondExist = y;
		}
	}

	public void exist(TreeNode root, TreeNode one, TreeNode two, Wrapper wrapper) {
		if (root == null) {
			return;
		}
		if (root == one) {
			wrapper.firstExist = true;
		}
		if (root == two) {
			wrapper.secondExist = true;
		}
		exist(root.left, one, two, wrapper);
		exist(root.right, one, two, wrapper);
	}

	public TreeNode LCAHelper(TreeNode root, TreeNode one, TreeNode two) {
		// write your solution here
		if (root == null) {
			return null;
		}
		if (root == one) {
			return root;
		}
		if (root == two) {
			return root;
		}
		TreeNode left = LCAHelper(root.left, one, two);
		TreeNode right = LCAHelper(root.right, one, two);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}

	public static TreeNode t5_4_LCA_K_nodes(TreeNode root, List<TreeNode> nodes) {
		if (root == null) {
			return null;
		}
		for (TreeNode n : nodes) {
			if (root == n) {
				return root;
			}
		}

		TreeNode left = t5_4_LCA_K_nodes(root.left, nodes);
		TreeNode right = t5_4_LCA_K_nodes(root.right, nodes);
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}
	
	
	

}
