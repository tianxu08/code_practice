package tag_tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import ds.Debug;
import ds.TreeNode;

public class Seralize_Deserlize_Tree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}
	
	public static void test1() {
		
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.right = n5;
		
		String s1 = serialize(n1);
		System.out.println(s1);
		TreeNode r1 = deserialize(s1);
		Debug.preOrderTraversal(r1);
		System.out.println();
		Debug.inOrderTraversal(r1);
		
		System.out.println("\n--------------");
		String s2 = serialize_level(n1);
		System.out.println(s2);
		TreeNode r2 = deserialize_level(s2);
		Debug.preOrderTraversal(r2);
		System.out.println();
		Debug.inOrderTraversal(r2);
	}

	/*
	 * Seralize and Deseralize Binary Tree
	 */

	// Encodes a tree to a single string.
	public static String serialize(TreeNode root) {
		StringBuilder stb = new StringBuilder();
		buildString(root, stb);
		stb.deleteCharAt(stb.length() - 1);
		return stb.toString();
	}

	private static void buildString(TreeNode node, StringBuilder stb) {
		if (node == null) {
			stb.append("null");
			stb.append(",");
			return;
		}
		stb.append(node.val);
		stb.append(",");
		buildString(node.left, stb);
		buildString(node.right, stb);
	}

	public static TreeNode deserialize(String data) {
		Deque<String> values = new LinkedList<String>();
		values.addAll(Arrays.asList(data.split(",")));
		return buildTree(values);
	}

	public static TreeNode buildTree(Deque<String> values) {
		String val = values.removeFirst();
		if (val.equals("null")) {
			return null;
		} else {
			TreeNode node = new TreeNode(Integer.valueOf(val));
			node.left = buildTree(values);
			node.right = buildTree(values);
			return node;
		}
	}

	/*
	 * Use Level Order Traversal
	 */
	public static String serialize_level(TreeNode root) {
		if (root == null) {
			return "";
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		StringBuilder stb = new StringBuilder();
		q.offer(root);
		while(!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				stb.append("null");
				stb.append(",");
			} else {
				stb.append(node.val);
				stb.append(",");
				q.offer(node.left);
				q.offer(node.right);
			}
		}
		return stb.toString();
	}
	
	
	public static TreeNode deserialize_level(String data) {
		if (data == null || data.equals("")) {
			return null;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		String[] values = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		q.offer(root);
		for(int i = 1; i < values.length;) {
			// get the parent node
			TreeNode parent = q.poll();
			// check left subtree
			if (!values[i].equals("null")) {
				TreeNode left = new TreeNode(Integer.parseInt(values[i]));
				parent.left = left;
				q.offer(left);
			}
			i++;
			
			// check right subtree
			if (!values[i].equals("null")) {
				TreeNode right = new TreeNode(Integer.parseInt(values[i]));
				parent.right = right;
				q.offer(right);
			}
			i++;
		}
		return root;
	}
	
	
	/*
	 * Seralize and deserialize Binary Search Tree
	 */

}
