package sortbynum;

import java.util.*;

public class Task428_SerializeAndDeserializeNaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Node> n1_children = new ArrayList<>();
//		Node n1 = new Node(1, n1_children);
	}

	public class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public String serialize(Node root) {
		StringBuilder stb = new StringBuilder();
		serializeHelper(root, stb);
		return stb.toString();
	}
	
	public static void serializeHelper(Node node, StringBuilder stb) {
		if (node == null) {
			stb.append("null");
			stb.append(",");
			return ;
		}
		
		stb.append(node.val);
		stb.append(",");
		
		for (Node n : node.children) {
			serializeHelper(n, stb);
		}
	}

	// Decodes your encoded data to tree.
	public Node deserialize(String data) {
		return null;
	}
}
