package small_yan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import ds.Tree;
import ds.TreeNode;
import ds.TreeNodeP;

public class Class2_BT_BST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test4();
//		test5();
//		test5_1();
	}
	
	
	
	/*
	 * Binary Tree
	 * (1) traverse(recursive, iterative, morris) preorder, inoder, postorder
	 * (2) BFS- level
	 * (3) DFS
	 * (4) divide & conquer
	 *     root
	 *     root.left
	 *     root.right
	 * (5) serialization/deserialization
	 */
	
	/*
	 * Binary Search Tree
	 * (1) insert
	 * (2) delete
	 * (3) search	
	 */
	
	/*
	 * 1 Basic concept
	 * 1.1 balanced
	 * 1.2 complete
	 * 1.3 depth
	 * 1.4 height
	 * 1.5 binary search tree: search
	 * 
	 * 2 basic operation
	 * 2.1 search 
	 * 2.2 insert
	 * 2.3 delete
	 * 
	 * 3 traversal(pre/in/post order)
	 * 3.1 recursive
	 * 3.2 iterative
	 * 3.3 iterator (!!! morris)
	 * 
	 * 4 DFS/BFS
	 * 5 Divide and conquer
	 */
	
	// In-Order traversal
	// this function return the first node in inOrder traversal,No parent pointer
	public static TreeNode firstNode(TreeNode root) {
		if (root == null) {
			return null;
		}
		while(root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	// with parent pointer
	public static TreeNodeP firstNodeP(TreeNodeP root) {
		if (root == null) {
			return null;
		}
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	// return next node in InOrder Traversal
	// if there is parent pointer
	public static TreeNodeP nextNodeP(TreeNodeP current) {
		if (current == null) {
			return null;
		}
		// if current.right != null, the inorder successor is the first node in current.right
		if (current.right != null) {
			return  firstNodeP(current.right);
		}
		
		// if current.right == null, we need to go up
		// the first time that cur is NOT cur.parent.right, we break the loop
		while(current.parent != null && current == current.parent.right) {
			current = current.parent; // going up
		}
		return current.parent;
	}
	
	/*
	 * BST
	 * search from root. 
	 * (1) if target.right != null, use firstNode()
	 * (2) if target.right == null, we need to search from root. 
	 *     every time, if target.val < root.val, successor = root;  root go left
	 *                 if target.val > root.val, root.go right
	 *                 if target.val == root.val, we find target node, break the while loop. 
	 */ 
	public static TreeNode inOrderSuccessor(TreeNode node, TreeNode target) {
		if (target.right != null) {
			return firstNode(target.right);
		}
		TreeNode successor = null;
		while(node != null) {
			if(target.val < node.val) {
				// root might be the inorder successor
				successor = node;
				// go left
				node = node.left;
			} else if (target.val > node.val) {
				// go right 
				node = node.right;
			} else {
				// target.val == root.val
				// we find the target
				break;
			}
		}
		return successor;
	}
	
	// 2 Binary Search Tree, second largest node in this tree
	// root.right == null, ==> return findLargest(root.left)
	// we find largest node's parent    largestParent.right = largestNode
	// if largestParent.right.left == null, return largestParent
	// else 								return findLargest(largestParent.right.left);
	
	public static TreeNodeP secondLargest(TreeNodeP root) {
		if (root == null) {
			return null;
		}
		if (root.right == null) {
			return findLargest(root.left);
		}
		TreeNodeP largestParent = largestParent(root);
		if (largestParent.right.left == null) {
			return largestParent;
		}
		return findLargest(largestParent.right.left);
	}
	
	public static TreeNodeP findLargest(TreeNodeP root) {
		if (root == null) {
			return null;
		}
		while (root.right != null) {
			root = root.right;
		}
		return root;
	}
	
	public static TreeNodeP largestParent(TreeNodeP root) {
		if (root == null || root.right == null) {
			return null;
		}
		while(root.right.right != null) {
			root = root.right;
		}
		return root;
	}
	
	//3 diameter
	// Binary Tree, diameter, from one node to another, longest path. 
	// length of path = # of nodes on the path.
	public static int longest = 0;
	public static int diameter(TreeNode root) {
		if (root == null) {
			return 0;
		}
		currentLongest(root);
		return longest;
	}
	
	public static int currentLongest(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = currentLongest(node.left);
		int right = currentLongest(node.right);
		
		// update longest
		if (left + right + 1 > longest) {
			longest = left + right + 1;
		}
		return Math.max(left, right) + 1;
	}
	
	//4 Binary Search, delete range outside [min, max]
	public static TreeNode deleteRande(TreeNode root, int min, int max) {
		if (root == null) {
			return null;
		}		
		// first, fix the left and right subtree of the root
		root.left = deleteRande(root.left, min, max);
		root.right = deleteRande(root.right, min, max);
	
		// Fix the root. 
		// 1 There are 2 cases:
		// a. root.val < min, root is not in the range. delete it and then return root.right
		if (root.val < min) {
			//delete root.
			return root.right;
		}
		// b. root.val > max, root is not in the range. delete it and then return root.left
		if (root.val > max) {
			//detete root
			return root.left;
		}	
		// 2. root in the range
		return root;	
	}
	
	public static void test4() {
		int[] a = {1,2,3,4,5,6,7,8};
		TreeNode root =Tree.createTree(a);
		Tree.inOrder(root);
		int min = 3;
		int max = 6;
		TreeNode newroot = deleteRande(root, min, max);
		Tree.inOrder(newroot);
	}
	
	
	/*
	 * 5 Binary Tree/Binary Search Tree serialization/deserialization
	 * 5.1 binary search tree, pre/post order, reconstruct.
	 * 5.2 binary tree, inorder + pre/post
	 * 5.3 binary tree, layer by layer reconstruct
	 * 5.4 binary tree, pre/post order.
	 *  
	 * Notes: 
	 * 5.4 we can use preOrder, level order traversal to do the serialization and deserialization.
	 *    for the preOrder, serialize and deserialize, we can use the strStr() to determine whether 
	 *    a tree is subtree of another tree. 
	 * 5.2: refer lai code   
	 */
	
	
	
	// 5.4
	public static void test5() {
		int[] a = {1,2,3,4,5,6};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		System.out.println("-----serialize: level ---------");
		String serialze_level = task5_serialize_level(root);
		System.out.println(serialze_level);
		
		TreeNode newRoot = task5_deserialize_level(serialze_level);
		
		Tree.preOrder(newRoot);
		Tree.inOrder(newRoot);
		
		TreeNode newRoot2 = task5_deserialize_level_2(serialze_level);
		
		Tree.preOrder(newRoot2);
		Tree.inOrder(newRoot2);
	}
	
	public static String task5_serialize_level(TreeNode root) {
		// level order traversal
		// use " " to separate
		StringBuilder str = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i ++) {
				TreeNode node = q.poll();
				if (node != null) {
					str.append(node.val + " ");
					q.add(node.left);
					q.add(node.right);
				} else {
					str.append("# ");
				}
			}
		}
		return str.toString();
	}
	
	public static TreeNode task5_deserialize_level(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		String[] strArr = str.split(" ");
		int i = 0;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(Integer.parseInt(strArr[0]));
		i ++;
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (strArr[i].equals("#")) {
				i ++;
			} else {
				TreeNode node = new TreeNode(Integer.parseInt(strArr[i]));
				i ++;
				cur.left = node;
				q.add(node);
			}
			
			if (strArr[i].equals("#")) {
				i ++;
			} else {
				TreeNode node = new TreeNode(Integer.parseInt(strArr[i]));
				i ++;
				cur.right = node;
				q.add(node);
			}
		}
		return root;
	}
	public static TreeNode task5_deserialize_level_2(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		StringTokenizer stoken = new StringTokenizer(str, " ");

		// debug
		/*
		while(stoken.hasMoreTokens()) {
			System.out.println(stoken.nextToken());
		}
		*/
		TreeNode root = new TreeNode(Integer.parseInt(stoken.nextToken()));
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode cur = q.poll();
			String leftStr = stoken.nextToken();
			if (!leftStr.equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(leftStr));
				cur.left = node;
				q.add(node);
			}
			
			String rightStr = stoken.nextToken();
			if (!rightStr.equals("#")) {
				TreeNode node = new TreeNode(Integer.parseInt(rightStr));
				cur.right = node;
				q.add(node);
			}
		}
 		return root;
	}
	/* preOrder Traversal
	 * we passed in StringBuilder into the serialize_helper. 
	 * Also, we use " " (space) as separator. 
	 * e.g   1
	 *      / \
	 *     2   3
	 *          \
	 *           4
	 * should be 1 2 # # # 3 4 # #
	 * if the node number is n, we need n + 1 "#".
	 */
	
	public static void test5_1() {
		int[] a = {1,2,3,4,5,6};
		TreeNode root = Tree.createTree(a);
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		System.out.println("--------------serialize: preOrder------------");
		String serializeString = task5_serialize_perOrder(root);
		System.out.println(serializeString);
	
		TreeNode newRoot = task5_deserialize_pre(serializeString);
		Tree.preOrder(newRoot);
		Tree.inOrder(newRoot);
		
		System.out.println("----------------------------------------------");
		String input = "5 4 # # 6 # # ";
		TreeNode newRoot2 = task5_deserialize_pre(input);
		Tree.preOrder(newRoot2);
		Tree.inOrder(newRoot2);
	}
	
	public static String task5_serialize_perOrder(TreeNode root) {
		StringBuilder stb = new StringBuilder();
		task5_serialize_pre_helper(root, stb);
		return stb.toString();
	}
	
	public static void task5_serialize_pre_helper(TreeNode node, StringBuilder stb) {
		if (node == null) {
			stb.append("# ");
			return ;
		}
		stb.append(node.val + " ");
		task5_serialize_pre_helper(node.left, stb);
		task5_serialize_pre_helper(node.right, stb);
	}
	
	public static TreeNode task5_deserialize_pre(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		StringTokenizer stoken = new StringTokenizer(str, " ");
		return task5_deserialize_pre_helper(stoken);
	}
	
	public static TreeNode task5_deserialize_pre_helper(StringTokenizer stoken) {
		if (!stoken.hasMoreTokens()) {
			return null;
		}
		String val = stoken.nextToken();
		if (val.equals("#")) {
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(val));
		node.left = task5_deserialize_pre_helper(stoken);
		node.right = task5_deserialize_pre_helper(stoken);
		return node;
	}
	
	
	
	// part6
	// heap
	/*
	 * public API:
	 * heapify() 
	 * offer()
	 * peek()
	 * poll()
	 * update()
	 * delete(int index)
	 * 
	 * private:
	 * shiftUp()
	 * shiftDown()
	 */


}



