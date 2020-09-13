package lai_online2;
import java.util.*;
public class Class26_LRU_Cache <K, V>{
	// limit is the max capacity of the cache

	static class Node<K, V> {
		Node<K, V> next;
		Node<K, V> prev;
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

	private int size; // the current size
	private int limit; // the max size of the Cache

	// double linked list
	private Node<K, V> head;
	private Node<K, V> tail;

	// Hash Map
	Map<K, Node<K, V>> map;

	// constructor
	public Class26_LRU_Cache(int limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
		this.size = 0;
		map = new HashMap<K, Node<K, V>>();

	}

	// setKey
	public void set(K key, V value) {
		// the key is already in the map
		Node<K, V> node = null;
		if (map.containsKey(key)) {
			node = map.get(key);
			node.value = value;
			// delete the node from double linked list
			remove(node);

		} else if (size < limit) {
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

		append(node);
	}

	public V get(K key) {
		if (!map.containsKey(key)) {
			return null;
		}
		Node<K, V> node = map.get(key);
		// remove node first
		remove(node);
		// append the node to head
		append(node);
		return node.value;
	}

	// remove node from the double linked list
	// and set the corresponding position in HashMap
	public Node<K, V> remove(Node<K, V> node) {
		map.remove(node.key);
		size--;

		// delete the node from double linked list
		if (node.prev != null) {
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

	// append node in front of the double linked list
	// and set the corresponding position in HashMap
	public Node<K, V> append(Node<K, V> node) {
		map.put(node.key, node);
		size++;

		// if head == null
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}
}
