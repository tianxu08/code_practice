package tag_HashMap_DLL;

import java.util.HashMap;

public class FirstNonRepeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstNonRepeating sln = new FirstNonRepeating();
		char[] arr = "abcda".toCharArray();
		for(char ch: arr) {
			sln.read(ch);
			char firstNonRepeat = sln.firstNoRepeating();
			System.out.println(firstNonRepeat);
		}
	}
	
	/*
	 * Given an unlimited stream of characters, find the first non-repeating character from stream
	 * You need to tell the first non-repeating character in O(1) time at any moment. 
	 * 
	 * 			0 1 2 3 4
	 * 			a b c d a
	 * output   a a a a b
	 * 
	 * 
	 * HashMap with Double Linked List
	 * 
	 * when read a character, 
	 * case1: it's a new character, put it into the tail of DDL and put an new element in the HashMap
	 * case2: the second time appearance, delete it from the DDL, 
	 *        in HashMap, set the value of this character to null
	 * case3: the third or more time appearance, in HashMap, 
	 * 		  this character exists, but its value is null, do nothing
	 */
	
	/*
	 * When we are scanning a new element 'X', what should we do ? 
	 * case1: X has not been seen yet. (1st time see 'X')
	 *      1.1 insert X into the tail(right end) of the DDL
	 *      1.2 insert X into the hashMap
	 * case2: X has been seen before
	 * 		2.1 X has been just seen for the 2nd time 
	 * 			(1) delete X from the DDL
	 * 			(2) set the value of 'X' in the hash_table to NULL 
	 * 				(which means the letter 'X' has been seen for 2nd or more times)
	 * 		2.2 X has been seen for the 3rd or more times(X exists in hashMap, but its value is NULL) 
	 * 			we do nothing. 
	 */
	
	
	// defind the Node of Double Linked List
	private static class Node{
		char key;
		Node prev;
		Node next;
		public Node(char k) {
			// TODO Auto-generated constructor stub
			this.key = k;
		}
	}
	
	HashMap<Character, Node> map;
	private Node head;
	private Node tail;
	
	// constructor
	public FirstNonRepeating() {
		map = new HashMap<Character, Node>();		
	}
	
	/*
	 * read a char from stream
	 * if not exist in map, add it to the tail of DLL
	 * 
	 */
	public void read(char ch) {
		if (map.containsKey(ch)) {
			if (map.get(ch) == null) {
				// ch exists before, but already has duplicates. do nothing
			} else {
				// ch exists before, but this is the second time it appears
				remove(ch);
			}
		} else {
			addNode(ch);
		}
	}
	
	/**
	 * 
	 * return the head of the DLL
	 * @return: the first non repeating char
	 */
	public Character firstNoRepeating() {
		if (head == null) {
			return null;
		}
		return head.key;
	}
	
	/*
	 * addNode(char ch)
	 * add Node(ch) to the tail of DDL and update map
	 */
	private void addNode(char ch) {
		Node node = new Node(ch);
		if (tail == null) {
			tail = node;
			head = node;
		} else {
			tail.next = node;
			tail = tail.next;
		}
		// add the <key, value> <-- (ch, node) into map
		map.put(ch, node);
	}
	
	/**
	 * 
	 * @param ch
	 * @return
	 * remove the char node from the DLL and set <key: value>  value to null
	 */
	private Node remove(char ch) {
		Node node = null;
		if (map.containsKey(ch)) {
			// map contains ch
			
			// get the node
			node = map.get(ch);

			// remove node from DLL
			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.prev;
			}

			// edge case
			if (node == head) {
				head = node.next;
			}

			if (node == tail) {
				tail = node.prev;
			}
			node.prev = null;
			node.next = null;
			
			// set the <key, value>'s value to null, indicates that the node has been deleted.
			map.put(ch, null);
		}
		return node;
	}
}
