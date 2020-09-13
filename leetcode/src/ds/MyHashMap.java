package ds;
import java.util.*;


public class MyHashMap<V, K> {

	public static class Entry<K, V> {
		// the key is not supposed to be changed once the Entry
		// for the key is constructed.
		final K key;
		V value;
		Entry<K, V> next;

		Entry(K key, V value, Entry<K, V> next) {
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
	
	private Entry<K, V>[] table; // tables
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
		this.table = (Entry<K, V>[]) new Entry[capacity];
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

	
	/**
	 * check whether the given value exists
	 * O(n) we have to travel the whole array and each of the entries
	 * @param value
	 * @return true if the value exist
	 *         false: if the value NOT exists
	 */
	public boolean containsValue(V value) {
		if (isEmpty()) {
			return false;
		}
		for (Entry<K, V> head : table) {
			// traverse each linked list
			while (head != null) {
				if (equalsValue(value, head.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * compare two values
	 * @param v1
	 * @param v2
	 * @return
	 * 
	 */
	private boolean equalsValue(V v1, V v2) {
		// handle the case v1 == null || v2 == null
		return v1 == v2 || v1 != null && v1.equals(v2);
	}

	/**
	 * Compare two keys
	 * @param k1
	 * @param k2
	 * @return
	 */
	private boolean equalsKey(K k1, K k2) {
		// handle the case v1 == null || v2 == null
		return k1 == k2 || k1 != null && k1.equals(k2);
	}

	/**
	 * get the hash value based on the key
	 * @param key
	 * @return
	 */
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

	
	/**
	 * given a key, return the Entry contains the key, 
	 * or null if the key is not in the hashMap
	 * @param key
	 * @return
	 */
	private Entry<K, V> getEntry(K key) {
		if (isEmpty()) {
			return null;
		}
		// get index
		int index = getIndex(key);
		
		// traverse the Linked list
		Entry<K, V> head = table[index];
		while (head != null) {
			if (equalsKey(key, head.getKey())) {
				return head;
			}
			head = head.next;
		}
		return null;
	}

	/**
	 * get the index based on the key
	 * @param key
	 * @return
	 */
	private int getIndex(K key) {
		return hash(key) % table.length;
	}

	/**
	 * check wehther the key exists
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key) {
		return getEntry(key) != null;
	}

	public V get(K key) {
		Entry<K, V> Entry = getEntry(key);
		return Entry == null ? null : Entry.getValue();
	}

	public V put(K key, V value) {
		Entry<K, V> Entry = getEntry(key);
		// 1
		if (Entry != null) {
			return Entry.setValue(value);
		}
		// 2 key not exist in the hashMap
		int index = getIndex(key);
		Entry<K, V> newHead = new Entry<K, V>(key, value, null);
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
		Entry<K, V> head = table[index];
		//
		return head.getValue();
	}
	
	private boolean needRehashing() {
		return (size + 0.0)/table.length >= loadFactor;
	}
	
	private void rehashing() {
		// create a new array with double size
		// maintian newly created array
		Entry<K, V>[] temp = table;
		table = (Entry<K, V>[]) new Entry[temp.length * 2];
		// traverse all the Entrys in the current array, and move them to new array using put
		for(int i = 0; i < temp.length; i ++) {
			Entry<K, V> Entry = temp[i];
			while(Entry != null) {
				Entry<K, V> cur  = Entry;
				// update Entry
				Entry = Entry.next;
				
				// get the index 
				int index = getIndex(cur.getKey());

				// insert before the head
				cur.next = table[index];
				table[index] = cur;
			}
		}
	}
}
