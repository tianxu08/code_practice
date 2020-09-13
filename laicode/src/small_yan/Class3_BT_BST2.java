package small_yan;

import java.util.ArrayList;
import java.util.List;

import ds.Tree;
import ds.TreeNode;

public class Class3_BT_BST2  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test3();
//		test4();
//		test5_1();
		test6_6();
	}
	
	/*
	 * task1
	 * find parent node of a give node in binary tree
	 * 
	 * Time: O(n) 
	 */
	public static TreeNode findParent(TreeNode root, TreeNode target) {
		// 1 root == null
		if (root == null) {
			return root;
		}
		
		// 2 root is parent of target, return root
		if (root.left == target || root.right == target) {
			return root;
		}
		
		TreeNode left = findParent(root.left, target);
		TreeNode right = findParent(root.right, target);
		if (left != null) {
			return left;
		}
		if (right != null) {
			return right;
		}
		return null;
		
	}
	
	/*
	 * task1.1
	 * find parent node of a given node in binary search tree
	 */
	
	public static TreeNode findParentBST(TreeNode root, TreeNode target) {
		if (root == null) {
			return null;
		}
		if (root.left == target ||root.right == target) {
			return root;
		}
		
		if (root.val < target.val) {
			// go right
			return findParentBST(root.right, target);
		} else {
			return findParentBST(root.left, target);
		}
	}
	
	/*
	 * task2
	 * create mirror of a given binary tree
	 */
	public static TreeNode createMirror(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode newRoot = new TreeNode(root.val);
		newRoot.left = createMirror(root.right);
		newRoot.right = createMirror(root.left);
		return newRoot;
	}
	
	/*
	 * task3
	 * sum of all paths from root to leaf nodes in binary tree
	 *        7
	 *      /    \
	 *     4      9
	 *    / \    /
	 *   1   5  8
	 *      /
	 *     3
	 *     
	 *  741 + 7453 + 798 = 8992
	 *  when do DFS, 
	 *  1. what's the base case? 
	 *  2. what's the transition from one state to another? what information we will need?
	 *  3. what is the termination condition? 
	 *  
	 *  val = previous_value*10 + node.val
	 *  
	 */
	
	public static void test3() {
		TreeNode n1 = new TreeNode(7);
		TreeNode n2 = new TreeNode(4);
		TreeNode n3 = new TreeNode(9);
		n1.left = n2;
		n1.right = n3;
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(5);
		n2.left = n4;
		n2.right = n5;
		TreeNode n6 = new TreeNode(3);
		n5.left = n6;
		TreeNode n7 = new TreeNode(8);
		n3.left = n7;
		
		int sum = allPathSum(n1);
		System.out.println("sum = " + sum);
	}
	
	public static int allPathSum(TreeNode root) {
		if (root == null) {
			return -1;
		}
		return sumHelper(root, 0);
		
	}
	
	public static int sumHelper(TreeNode node, int curSum) {
		curSum = curSum * 10 + node.val;
		if (node.left == null && node.right == null) {
			// this is a leaf node
			return curSum;
		}
		int result = 0;
		if (node.left != null) {
			result += sumHelper(node.left, curSum);
		}
		if (node.right != null) {
			result += sumHelper(node.right, curSum);
		}
		return result;
	}
	
	
	/*
	 * task4
	 * level order of binary tree
	 * DFS
	 * 1. for each node, we need to know the node's depth(level)
	 * 2. for each level, the node on the left is always traversed before the node on the right
	 */
	public static void test4() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		List<List<Integer>> result = task4_levelOrder(n1);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task4_levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		task4_doLevelOrder(root, result, 0);
		return result;
	}
	
	public static void task4_doLevelOrder(TreeNode node, List<List<Integer>> result, int level) {
		if (result.size() == level) {
			result.add(new ArrayList<Integer>());
		}
		result.get(level).add(node.val);
		if (node.left != null) {
			 task4_doLevelOrder(node.left, result, level + 1);
		}
		if (node.right != null) {
			task4_doLevelOrder(node.right, result, level + 1);
		}
	}
	
	/*
	 * task4.1
	 * traversal kth layer of binary tree
	 */
	
	
	/*
	 * task5 
	 * binary search tree, closest number to target(sorted array, closest number to target)
	 */
	
	public static int closest = Integer.MAX_VALUE;
	public static void task5_closest2target(TreeNode root, int target) {
		if (root == null) {
			return ;
		}
		if (Math.abs(root.val - target) < Math.abs(closest - target)) {
			closest = root.val;
		}
		if (root.val < target) {
			task5_closest2target(root.right, target);
		} 
		if (root.val > target) {
			 task5_closest2target(root.left, target);
		}
	}
	
	/*
	 * 5.1 binary search tree, the smallest number larger than target
	 * 
	 */
	public static void test5_1() {
		TreeNode n1 = new TreeNode(4);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(9);
		n1.left = n2;
		n1.right = n3;
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(3);
		n2.left = n4;
		n2.right = n5;
		TreeNode n6 = new TreeNode(7);
		TreeNode n7 = new TreeNode(12);
		n3.left = n6;
		n3.right = n7;
		
		TreeNode rev = task5_1_smallestLargerThanTarget(n1, 5);
		System.out.println(rev.val);
		
		TreeNode rev2 = task5_2_largestSmallerThanTraget(n1, 20);
		System.out.println(rev2.val);
		
		
	}
	
	// this is also like to find the inOrder successor of target
	// the only difference is that the target might NOT be in the binary search tree
	public static TreeNode task5_1_smallestLargerThanTarget(TreeNode root,
			int target) {
		if (root == null) {
			return null;
		}
		if (root.val <= target) { // go root.right to find the result
			return task5_1_smallestLargerThanTarget(root.right, target);
		} else {
			// target < root.val, root might be the result, we also need to check in the root.left subtree
			TreeNode left = task5_1_smallestLargerThanTarget(root.left, target);
			if (left == null) {
				return root;
			}
			return left;
		}
	}
	
	
	/*
	 * 5.2 binary search tree, the largest number smaller than target
	 */
	
	public static TreeNode task5_2_largestSmallerThanTraget(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (root.val >= target) {
			// the result should in the left subtree
			return task5_2_largestSmallerThanTraget(root.left, target);
		} else {
			// root.val < target
			// the root might be the result. we check root.right first. if right == null, 
			// we return the root.
			TreeNode right = task5_2_largestSmallerThanTraget(root.right, target);
			if (right == null) {
				return root;
			}
			return right;
		}
	}
	
	/*
	 * 6 lowest common ancestor
	 *   refer to lai_code
	 */
	/*
	 * 6.1 node1 and node2 are both in the binary tree
	 * 6.2 node1 and node2 are not guaranted in the binary tree
	 *     first traversal the binary tree. make sure the node1 and node2 are in bt, then reduce to 6.1
	 * 6.3 6.1 and 6.2, with parent node,
	 *     Make sure that the two nodes are in BT
	 * 	   (1) use hashset to store one node's parent's until to root. 
	 *        Go up, if node's parent exist in the hashSet, node.parent is the LCA
	 *     (2) get height of node1 and node2. for example, node1.height > node2.height, 
	 *         left node1 go up diffs steps. 
	 *         Then left node1 and node2 go up together. 
	 *         the intersect node is the LCA
	 * 6.4 Given K nodes in a binary tree, find their lowest common ancestor.
	 *     the similiar with 6.1
	 * 
	 * 6.5 binary tree two nodes' distance, distance = # of edges between the nodes.
	 *     get LCA,
	 *         if lca != node1 && lca != node2 ==> distance(node1, node2) = distance(node1, lca) + distance(node2, lca);
	 *         else                            ==> distance(node1, node2)
	 *         
	 *         to get distance(node1, lca), getHeight(LCA);
	 * 6.6 binary search tree, two nodes' distance 
	 *     node1, node2, guarentee that both nodes are in bst, assume node1.val < node2.val
	 *     if cur.val >= max.val, go left
	 *     else if cur.val <= min.val  go right
	 *     else // cur.val> min.val && cur.val < max.val
	 *     // the cur is the LCA. 
	 *     distance (node1, node2) = distance(node1, lca) + distancd(node2, lca)
	 *     
	 */
	
	public static void test6_6() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		
		n4.left = n2;
		n4.right = n6;
		
		n2.left = n1;
		n2.right = n3;
		
		n6.left = n5;
		n6.right = n7;
		
		TreeNode root = n4;
		Tree.preOrder(root);
		Tree.inOrder(root);
		
		int dist = task6_6_getDistance(n2, n3, 0);
		System.out.println(dist);
		
		int distance = task6_6_distance2nodes(root, n1, n1);
		System.out.println("distance = " + distance);
	}
	public static int task6_6_distance2nodes(TreeNode root, TreeNode min, TreeNode max) {
		if (root == null) {
			return 0;
		}
		if (root.val < min.val) {
			return task6_6_distance2nodes(root.left, min, max);
		} else if (root.val > max.val) {
			return task6_6_distance2nodes(root.right, min, max);
		} else {
			// root is the lca
			if (root == min) {
				return task6_6_getDistance(min, max, 0);
			} else if (root == max) {
				return task6_6_getDistance(max, min, 0);
			} else {
				return task6_6_getDistance(root, min, 0) + task6_6_getDistance(root, max, 0);
			}
		}
	}
	
	public static int task6_6_getDistance(TreeNode root, TreeNode node, int curDist) {
		if (root == null) {
			return 0;
		}	
		if (root.val < node.val) {
			// go right
			return task6_6_getDistance(root.right, node, curDist + 1);
		} else if (root.val > node.val) {
			return task6_6_getDistance(root.left, node, curDist + 1);
		} else {
			return curDist;
		}
	}
	
	
	
	
	
	
	

}

