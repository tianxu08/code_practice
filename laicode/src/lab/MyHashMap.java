package lab;

import java.util.Arrays;

/**
 * A hashtable implementation of map.
 * 
 * supported operations: 
 * size() 
 * isEmpty() 
 * clear() 
 * put(K key, V value) 
 * get(K key)
 * containsKey(K key) 
 * containsValue(V value) // check if the desired value is in the map. 
 * remove(K key)
 */

public class MyHashMap<V, K> {

	static class Node<K, V> {
		// the key is not supposed to be changed once the Entry
		// for the key is constructed.
		final K key;
		V value;
		Node<K, V> next;

		Node(K key, V value, Node<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}

		// for pretty print purpose
		@Override
		public String toString() {
			return String.valueOf(key) + ": " + String.valueOf(value);
		}
	}

	private Node<K, V>[] table;
	private int size; // how many entries are in the table.
	private final float loadFactor;

	public MyHashMap(int capacity, float loadFactor) {
		if (capacity <= 0) {
			throw new IllegalAccessError(".....");
		}
		// check if loadFactor >0 && < 1
		if (loadFactor <= 0 || loadFactor >= 1) {
			throw new IllegalAccessError("load factor is illegal");
		}
		this.loadFactor = loadFactor;
		this.table = (Node<K, V>[]) new Node[capacity];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Arrays.fill(this.table, null);
	}

	// O(n)
	// we have to travel the whole array and each of the nodes
	//
	public boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> head : table) {
			while (head != null) {
				if (equalsValue(value, head.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	//
	private boolean equalsValue(V v1, V v2) {
		// handle the case v1 == null || v2 == null
		return v1 == v2 || v1 != null && v1.equals(v2);
	}

	private boolean equalsKey(K k1, K k2) {
		// handle the case v1 == null || v2 == null
		return k1 == k2 || k1 != null && k1.equals(k2);
	}

	// return int value is no-negative
	private int hash(K key) {
		// key == null
		if (key == null) {
			return 0;
		}
		
		// !!! this might overflow
		// int hashNum = key.hashCode();
		// return hashNum < 0 ? -hashNum : hashNum;

		// set the sign num == 0
		// hashNum = Integer.min_value, overflow
		return key.hashCode() & 0x7FFFFFFF;
	}

	
	// given a key, return all the node contains the key, or null if the key is
	// not in the hashMap
	private Node<K, V> getNode(K key) {
		if (isEmpty()) {
			return null;
		}
		// get index
		int index = getIndex(key);
		
		// traverse the Linked list
		Node<K, V> head = table[index];
		while (head != null) {
			if (equalsKey(key, head.getKey())) {
				return head;
			}
			head = head.next;
		}
		return null;
	}

	// return index
	private int getIndex(K key) {
		return hash(key) % table.length;
	}
	
	public boolean containsKey(K key) {
		return getNode(key) != null;
	}

	public V get(K key) {
		Node<K, V> node = getNode(key);
		return node == null ? null : node.getValue();
	}

	public V put(K key, V value) {
		Node<K, V> node = getNode(key);
		// 1
		if (node != null) {
			return node.setValue(value);
		}
		// 2 key not exist in the hashMap
		int index = getIndex(key);
		Node<K, V> newHead = new Node<K, V>(key, value, null);
		newHead.next = table[index];  // insert before the head
		table[index] = newHead;
		
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}

	public V remove(K key) {
		int index = getIndex(key);
		Node<K, V> head = table[index];
		//
		return head.getValue();
	}
	
	private boolean needRehashing() {
		return (size + 0.0)/table.length >= loadFactor;
	}
	
	private void rehashing() {
		// create a new array with double size
		// maintian newly created array
		Node<K, V>[] temp = table;
		table = (Node<K, V>[]) new Node[temp.length * 2];
		// traverse all the nodes in the current array, and move them to new array using put
		for(int i = 0; i < temp.length; i ++) {
			Node<K, V> node = temp[i];
			while(node != null) {
				Node<K, V> cur  = node;
				// update node
				node = node.next;
				
				// get the index 
				int index = getIndex(cur.getKey());

				// insert before the head
				cur.next = table[index];
				table[index] = cur;
			}
		}
	}


}
