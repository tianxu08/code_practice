package system_design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TrieSerialization {
	
	public class TrieNode{
		public NavigableMap<Character, TrieNode> children;
		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

	/**
	 * This method will be invoked first, you should design your own algorithm
	 * to serialize a trie which denote by a root node to a string which can be
	 * easily deserialized by your own "deserialize" method later.
	 */
	public String serialize(TrieNode root) {
		// Write your code here
		if (root == null) {
			return "";
		}
		StringBuilder stb = new StringBuilder();
		stb.append("<");
		Iterator iter = root.children.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<Character, TrieNode> entry = (Map.Entry<Character, TrieNode>)iter.next();
			Character key = entry.getKey();
			TrieNode child = entry.getValue();
			stb.append(key);
			stb.append(serialize(child));
		}
		stb.append(">");
		return stb.toString();
	}

	/**
	 * This method will be invoked second, the argument data is what exactly you
	 * serialized at method "serialize", that means the data is not given by
	 * system, it's given by your own serialize method. So the format of data is
	 * designed by yourself, and deserialize it here as you serialize it in
	 * "serialize" method.
	 */
	public TrieNode deserialize(String data) {
		// Write your code here
		  // Write your code here
        if (data == null || data.length() == 0)
            return null;

        TrieNode root = new TrieNode();
        TrieNode current = root;
        LinkedList<TrieNode> path = new LinkedList<TrieNode>();
        for (Character c : data.toCharArray()) {
            switch (c) {
            case '<':
                path.offerFirst(current);
                break;
            case '>':
                path.pollFirst();
                break;
            default:
                current = new TrieNode();
                path.peek().children.put(c, current);
            }
        }
        return root;
	}
}
