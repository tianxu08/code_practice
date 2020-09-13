package mj_amazon;

import java.util.HashMap;
import java.util.Map;

public class OA0605 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public String[] largestItemAsso(String[][] itemAssociation) {
		
		return null;
	}
	
	
	
	
	public static class Node {
		Node parent;
		int rank;

		public Node(int _r) {
			this.parent = this;
			this.rank = _r;
		}
	}

	public static int LongestConsecutiveSequence(int[] array) {
		Map<Integer, Node> myMap = new HashMap<Integer, Node>();
		int result = 1;
		for (int i = 0; i < array.length; i++) {
			if (!myMap.containsKey(array[i])) {
				myMap.put(array[i], new Node(1));
			}
		}

		for (Integer x : array) {
			if (myMap.containsKey(x + 1)) {
				union(myMap.get(x), myMap.get(x + 1));
			}
			if (myMap.containsKey(x - 1)) {
				union(myMap.get(x), myMap.get(x - 1));
			}

			Node temp = find(myMap.get(x));
			if (temp.rank > result) {
				result = temp.rank;
			}
		}
		return result;
	}

	// union with size
	private static void union(Node x, Node y) {
		Node xRoot = find(x);
		Node yRoot = find(y);
		if (xRoot == yRoot)
			return;
		if (xRoot.rank > yRoot.rank) {
			yRoot.parent = xRoot;  // link yRoot to xRoot
			xRoot.rank += yRoot.rank;
		} else {
			xRoot.parent = yRoot;  // link xRoot to yRoot
			yRoot.rank += xRoot.rank;
		}
	}

	// find with path compression
	private static Node find(Node x) {
		if (x.parent != x)
			x.parent = find(x.parent);
		return x.parent;
	}
	
	
	public static class Item{
		String parent;
		int rank;
		public Item(int _r) {
			this.rank = _r;
		}
	}
	
	
	
	
	
	

}
