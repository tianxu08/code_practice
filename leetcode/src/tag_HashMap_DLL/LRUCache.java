package tag_HashMap_DLL;

import java.util.HashMap;
import java.util.Map;



/*
 * Design a LRU Cache
 * HashMap with Double LinkedList
 * 
 * case1: cache miss, put the new data in the front of the queue, delete the end of the queue
 * case2: cache hit, put this data hit in the front of the queue. 
 * e.g
 * each data cached is a URL
 */
public class LRUCache<K,V> {

	// the node for DLL
	// Key:Value Pair
	static class Node<K,V>{
		Node<K,V> next;
		Node<K,V> prev;
		K key;
		V value;
		// constructor
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		// update the key and value
		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}
	} 
	
	
	private int size;  // the current size
	private int limit; // the max size of the Cache
	
	// double linked list
	private Node<K, V> head;
	private Node<K, V> tail;
	
	// Hash Map
	// Key: Value ==> Key: Node<Key, Value>
	// use the K to find the value
	Map<K, Node<K, V>> map;
	
	// constructor 
	public LRUCache(int limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
		this.size = 0;
		map = new HashMap<K, Node<K,V>>();
	}
	
	
	// setKey, set a <key, value> pair, which means this is latest recent used
	// we put the node<key, value> in front of the DDL
	
	/**
	 * 
	 * set(key, value)
	 * 1.1 the key is already in the Cache, update the Node and put the Node in the head of DLL
	 * 1.2 the key is NOT in the Cache. 
	 * 1.2.1 the size < limit, directly add the Node to the head of DLL
	 * 1.2.2 the size == limit, delete the Node in the end of DLL and then add the Node to the head of DLL
	 * @param key
	 * @param value
	 */
	public void set(K key,V value) {
		Node<K, V> node = null;
		// the key is already in the map
		if (map.containsKey(key)) {
			// get the node and update the value of node
			node = map.get(key);
			node.value = value;
			// delete the node from double linked list
			remove(node);		
		} else if(size < limit){
			// the key is not in the map
			node = new Node<K, V>(key, value);
		} else {
			// size == limit
			// remove the tail first
			node = tail;
			remove(node);
			
			// update node's content
			node.update(key, value);
		}
		// put the node in front of the DDL
		offerFirst(node);
	}
	
	/**
	 * get Key, which means we used it. and we need to put it in front of the DDL
	 * @param key
	 * @return
	 * 
	 */
	public V get(K key) {
		if (!map.containsKey(key)) {
			return null;
		}
		Node<K, V> node = map.get(key);
		// remove node first
		remove(node);
		// put the node to head
		offerFirst(node);
		return node.value;
	}

	/**
	 * remove node from the double linked list and set the corresponding position in HashMap
	 * @param node: the node to be deleted
	 * @return
	 */
	private Node<K, V> remove(Node<K, V> node) {
		// delete the node.key from map
		map.remove(node.key);
		size --;
		
		// delete the node from double linked list
		if (node.prev!= null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		
		// edge case 
		// node == head 
		if (node == head) {
			head = head.next;
		}
		
		// node == tail
		if (node == tail) {
			tail = tail.prev;
		}
		
		// set node.next and node.prev to null
		node.next = null;
		node.prev = null;
		
		return node;
	}
	
	// offerFirst node in front of the double linked list
	// and set the corresponding position in HashMap
	private Node<K, V> offerFirst(Node<K, V> node) {
		// add the node into map
		map.put(node.key, node);
		size ++;
		
		// add the node in front of the DLL
		// if head == null
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			// update head
			head = node;
		}
		return node;
	}
	
}