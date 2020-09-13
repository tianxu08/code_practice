package small_yan;

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
public class Class1_LRUCache<K,V> {

	static class Node<K,V>{
		Node<K,V> next;
		Node<K,V> prev;
		K key;
		V value;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
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
	Map<K, Node<K, V>> map;
	
	// constructor 
	public Class1_LRUCache(int limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
		this.size = 0;
		map = new HashMap<K, Node<K,V>>();
	}
		
	
	// setKey, set a <key, value> pair, which means this is latest recent used
	// we put the node<key, value> in front of the DDL
	public void set(K key,V value) {
		// the key is already in the map
		Node<K, V> node = null;
		if (map.containsKey(key)) {
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
	
	// get Key, which means we used it. and we need to put it in front of the DDL 
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
	
	
	
	// remove node from the double linked list
	// and set the corresponding position in HashMap
	public Node<K, V> remove(Node<K, V> node) {
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
		
		node.next = null;
		node.prev = null;
		return node;
	}
	
	
	// offerFirst node in front of the double linked list
	// and set the corresponding position in HashMap
	public Node<K, V> offerFirst(Node<K, V> node) {
		map.put(node.key, node);
		size ++;
		
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
