package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class4 {
	/*
	task1
	Pre-order Traversal Of Binary Tree
	Easy
	Data Structure
	Implement an iterative, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	Pre-order traversal is [5, 3, 1, 4, 8, 11]

	Corner Cases

	What if the given binary tree is null? Return an empty list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3
	 */
	public List<Integer> preOrder(TreeNode root) {
		// Write your solution here.
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while (cur != null || !st.isEmpty()) {
			// System.out.print(cur.val + " ");
			if (cur != null) {
				result.add(cur.key);
				// System.out.print(cur.val + " ");
				// push cur into stack
				st.offerFirst(cur);
				cur = cur.left;
			} else {
				// cur == null, get cur = st.pop()
				cur = st.pollFirst();
				cur = cur.right;
			}
		}

		return result;
	}
	
	/*
	 * task2
	Insert In Binary Search Tree
	Easy
	Data Structure
	Insert a key in a binary search tree if the binary search tree does not already contain the key. Return the root of the binary search tree.

	Assumptions

	There are no duplicate keys in the binary search tree

	If the key is already existed in the binary search tree, you do not need to do anything

	Examples

	        5

	      /    \

	    3        8

	  /   \

	 1     4

	insert 11, the tree becomes

	        5

	      /    \

	    3        8

	  /   \        \

	 1     4       11

	insert 6, the tree becomes

	        5

	      /    \

	    3        8

	  /   \     /  \

	 1     4   6    11

	 */
	public TreeNode insert(TreeNode root, int key) {
		// Write your solution here.
		return helper(root, key);
	}

	public TreeNode helper(TreeNode node, int key) {
		if (node == null) {
			return new TreeNode(key);
		}
		if (node.key == key) {
			return node;
		} else if (node.key < key) {
			// in right subtree
			node.right = helper(node.right, key);
		} else {
			node.left = helper(node.left, key);
		}
		return node;
	}
	
	/*
	 * task3
	Search In Binary Search Tree
	Easy
	Data Structure
	Find the target key K in the given binary search tree, return the node that contains the key if K is found, otherwise return null.

	Assumptions

	There are no duplicate keys in the binary search tree
	*/
	public TreeNode search(TreeNode root, int key) {
		// Write your solution here.
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			return root;
		} else if (root.key < key) {
			// may be in the right side
			return search(root.right, key);
		} else {
			return search(root.left, key);
		}
	}
	
	/*
	 * task4
	 * 
	In-order Traversal Of Binary Tree
	Fair
	Data Structure
	Implement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	In-order traversal is [1, 3, 4, 5, 8, 11]

	Corner Cases

	What if the given binary tree is null? Return an empty list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3


	 */
	
	public List<Integer> inOrder(TreeNode root) {
	    // Write your solution here.
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    if (root == null) {
				return result;
			}
			LinkedList<TreeNode> st = new LinkedList<TreeNode>();
			TreeNode cur = root;
			while(cur != null || !st.isEmpty()) {
				if (cur != null) {
					st.offerFirst(cur);
					cur = cur.left;
				} else {
					cur = st.pollFirst();
					// System.out.print(cur.val + " ");
					result.add(cur.key);
					cur = cur.right;
				}
			}
			return result;
	  }
	  
	/*
	 * task5  
	Post-order Traversal Of Binary Tree
	Hard
	Data Structure
	Implement an iterative, post-order traversal of a given binary tree, return the list of keys of each node in the tree as it is post-order traversed.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	Post-order traversal is [1, 4, 3, 11, 8, 5]

	Corner Cases

	What if the given binary tree is null? Return an empty list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	 */
	
	public List<Integer> postOrder(TreeNode root) {
		// Write your solution here.

		List<Integer> result = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}
		Stack<TreeNode> st = new Stack<TreeNode>();
		st.push(root);
		TreeNode prev = null;
		while (!st.isEmpty()) {
			TreeNode cur = st.peek();
			// if previous is null, going down

			// if previous is null → going down (left subtree has priority)
			if (prev == null || cur == prev.left || cur == prev.right) {
				if (cur.left != null) {
					st.push(cur.left);
				} else if (cur.right != null) {
					st.push(cur.right);
				} else {
					// leaf node
					// System.out.println(cur.val + " ");
					result.add(cur.key);
					st.pop();
				}
			} else if (prev == cur.left) { // from left, bottom to top
				if (cur.right != null) {
					st.push(cur.right);
				} else {
					// System.out.println(cur.val + " ");
					result.add(cur.key);
					st.pop();
				}
			} else {
				// from right bottom to top
				// System.out.println(cur.val + " ");
				result.add(cur.key);
				st.pop();
			}
			prev = cur;
		}
		return result;
	}
	
	/*
	 * task6
	Symmetric Binary Tree
	Easy
	Recursion
	Check if a given binary tree is symmetric.

	Examples

	        5

	      /    \

	    3        3

	  /   \    /   \

	1      4  4      1

	is symmetric.

	        5

	      /    \

	    3        3

	  /   \    /   \

	1      4  1      4

	is not symmetric.

	*/
	public boolean isSymmetric(TreeNode root) {
		// Write your solution here.
		if (root == null) {
			return true;
		}

		return helper(root.left, root.right);
	}

	public boolean task6_helper(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		}
		if (one == null || two == null) {
			return false;
		}
		return one.key == two.key && task6_helper(one.left, two.right)
				&& task6_helper(one.right, two.left);
	}
	
	
	/*
	 * task7
	Identical Binary Tree
	Easy
	Recursion
	Check if two given binary trees are identical. Identical means the equal valued keys are at the same position in the two binary trees.

	Examples

	 

	        5

	      /    \

	    3        8

	and

	        5

	      /    \

	    3        8

	are identical trees.

	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.


	*/
	public boolean isIdentical(TreeNode one, TreeNode two) {
		// Write your solution here.
		if (one == null && two == null) {
			return true;
		}
		if (one == null || two == null) {
			return false;
		}
		return one.key == two.key && isIdentical(one.left, two.left)
				&& isIdentical(one.right, two.right);

	}
	
	/*
	 * task8

	Get Keys In Binary Search Tree In Given Range
	Easy
	Recursion
	Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	 1     4        11

	get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

	Corner Cases

	What if there are no keys in the given range? Return an empty list in this case.
	How is the binary tree represented?

	We use the level order traversal sequence with a special symbol "#" denoting the null node.
	*/
	public List<Integer> getRange(TreeNode root, int min, int max) {
		// Write your solution here.
		// return new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		helper(result, root, min, max);
		return result;
	}

	public void helper(ArrayList<Integer> result, TreeNode node, int min,
			int max) {
		if (node == null) {
			return;
		}
		// node.key > min, there is a need to traverse left subtree
		if (node.key > min) {
			//
			helper(result, node.left, min, max);
		}
		// in the range, add to list
		if (node.key >= min && node.key <= max) {
			result.add(node.key);
		}

		// node.key < max, there is a need to traverse right subtree
		if (node.key < max) {
			helper(result, node.right, min, max);
		}
	}
	
	/*
	 * task9
	Check If Binary Tree Is Balanced
	Fair
	Recursion
	Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.

	Examples

	        5

	      /    \

	    3        8

	  /   \        \

	1      4        11

	is balanced binary tree,

	        5

	      /

	    3

	  /   \

	1      4

	is not balanced binary tree.

	Corner Cases

	What if the binary tree is null? Return true in this case.

    */
	public boolean isBalanced(TreeNode root) {
		// Write your solution here.
		if (root == null) {
			return true;
		}
		return getHeight_fast(root) != -1 ;
	}

	public int getHeight_fast(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getHeight_fast(root.left);
		int right = getHeight_fast(root.right);

		// step2: in current layer.
		// -1 means the subtree is already not balanced
		if (Math.abs(left - right) > 1 || left == -1 || right == -1) {
			return -1;
		}

		// step3: report to parent
		return Math.max(left, right) + 1;
	}
	
	/*
	 * task10
	Tweaked Identical Binary Trees
	Fair
	Recursion
	Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. A tweak is defined as a swap of the children of one node in the tree.

	Examples

	        5

	      /    \

	    3        8

	  /   \

	1      4

	and

	        5

	      /    \

	    8        3

	           /   \

	          1     4

	the two binary trees are tweaked identical
	*/
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		// Write your solution here.
		return helper(one, two);
	}

	public boolean helper(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		}
		if (one == null || two == null) {
			return false;
		}

		boolean identical = one.key == two.key && helper(one.left, two.left)
				&& helper(one.right, two.right);
		boolean symmetric = one.key == two.key && helper(one.left, two.right)
				&& helper(one.right, two.left);

		return identical || symmetric;
	}
	
	
	/*
	 * task11
	 * 
	Is Binary Search Tree Or Not
	Fair
	Recursion
	Determine if a given binary tree is binary search tree.

	Assumptions

	There are no duplicate keys in binary search tree.
	Corner Cases

	What if the binary tree is null? Return true in this case.
	*/
	public boolean isBST(TreeNode root) {
		// Write your solution here.
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;

		return task11_helper(root, min, max);

	}

	public boolean task11_helper(TreeNode node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.key <= min || node.key >= max) {
			return false;
		}
		return task11_helper(node.left, min, node.key)
				&& task11_helper(node.right, node.key, max);
	}

}
