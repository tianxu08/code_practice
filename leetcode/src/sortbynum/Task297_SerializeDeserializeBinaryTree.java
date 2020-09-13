package sortbynum;

import java.util.*;

import ds.*;

public class Task297_SerializeDeserializeBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

	public static void test() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.right = n5;

		String ser1 = serialize(n1);
		System.out.println(ser1);

		String ser2 = serialize_level(n1);
		System.out.println(ser2);

		TreeNode root1 = deserialize(ser1);
		Debug.preOrderTraversal(root1);
		System.out.println();
		Debug.inOrderTraversal(root1);
		System.out.println();
		System.out.println("----------");
		TreeNode root2 = deserialize_level(ser2);
		Debug.preOrderTraversal(root2);
		System.out.println();
		Debug.inOrderTraversal(root2);
	}

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
	 * This is Level Order Traversal
	 */
	public static String serialize_level(TreeNode root) {
		StringBuilder sb = new StringBuilder();

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node == null) {
				sb.append("null,");
			} else {
				sb.append(String.valueOf(node.val) + ",");
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}

		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public static TreeNode deserialize_level(String data) {
		if (data.isEmpty())
			return null;

		String[] vals = data.split(",");
		int[] nums = new int[vals.length]; // 节点i之前null节点的个数
		TreeNode[] nodes = new TreeNode[vals.length];

		for (int i = 0; i < vals.length; i++) {
			if (i > 0) {
				nums[i] = nums[i - 1];
			}
			if (vals[i].equals("null")) {
				nodes[i] = null;
				nums[i]++;
			} else {
				nodes[i] = new TreeNode(Integer.parseInt(vals[i]));
			}
		}

		for (int i = 0; i < vals.length; i++) {
			if (nodes[i] == null) {
				continue;
			}
			nodes[i].left = nodes[2 * (i - nums[i]) + 1];
			nodes[i].right = nodes[2 * (i - nums[i]) + 2];
		}

		return nodes[0];
	}

}
