package tag_HashMap_DLL;

import java.util.*;

public class LFUCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LFUCache sln = new LFUCache(2);
		sln.put(1, 1);
		sln.put(2, 2);
		Integer rev = sln.get(1);
		System.out.println(rev);
		sln.put(3, 3);
		rev = sln.get(2);
		System.out.println(rev);
	}

	/*
	 * DLL of CacheElement is maintained by the counter.
	 * Each CacheElement has a set, which contains the number of usage of those keys
	 * like:  
	 * CacheElement1 -> CacheElement2 -> CacheElement3 -> CacheElement4
	 * counter = 0         counter = 1   counter = 2      counter = 3
	 */
	private CacheElement head = null;
	private int capacity = 0;
	
	// key value hashMap
	private HashMap<Integer, Integer> valueHash = null;
	
	// hashMap that maps the CacheElemet by key
	private HashMap<Integer, CacheElement> cache = null;

	public LFUCache(int _capacity) {
		capacity = _capacity;
		valueHash = new HashMap<Integer, Integer>();
		cache = new HashMap<Integer, CacheElement>();
	}

	public int get(int key) {
		if (valueHash.containsKey(key)) {
			refreshUsage(key);
			return valueHash.get(key);
		}
		return -1;
	}

	// put a <key, value> into Cache
	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
		
		if (valueHash.containsKey(key)) {
			// update valueHash if key already exists in valueHash
			valueHash.put(key, value);
		} else {
			// otherwise
			if (valueHash.size() < capacity) {
				valueHash.put(key, value);
			} else {
				// remove the LFU cacheElement
				removeOne();
				valueHash.put(key, value);
			}
			addToHead(key);
		}
		refreshUsage(key);
	}

	/**
	 * 
	 * @param key
	 * add the key to the front of the DLL
	 */
	private void addToHead(int key) {
		if (head == null) {
			head = new CacheElement(0);
			head.keysSet.add(key);
		} else if (head.count > 0) {
			CacheElement element = new CacheElement(0);
			element.keysSet.add(key);
			element.next = head;
			head.prev = element;
			head = element;
		} else {
			// head.count == 0
			head.keysSet.add(key);
		}
		// update the cache map
		cache.put(key, head);
	}

	
	/**
	 * 
	 * @param key
	 * The DLL of CacheElement is sorted by the counter. 
	 */
	private void refreshUsage(int key) {	
		// get the element for the key with the help of cache
		CacheElement element = cache.get(key);
		// it will be modified, so, delete the key in its current set
		element.keysSet.remove(key);

		// element is the last element in the DLL
		// create a new element and linked to the end of the DLL
		if (element.next == null) {
			element.next = new CacheElement(element.count + 1); // counter is: element.count + 1
			element.next.prev = element;
			element.next.keysSet.add(key);
		} else if (element.next.count == element.count + 1) {
			// element.next's count == count + 1
			// the next element exists
			// directly add cur key into element.next.keysSet
			element.next.keysSet.add(key);
		} else {
			// the element.next.count != count + 1
			// we need to create a new element and insert into cur elem and elem.next
			CacheElement tmp = new CacheElement(element.count + 1);
			tmp.keysSet.add(key);
			// insert tmp between element and element.next
			tmp.prev = element;
			tmp.next = element.next;
			element.next.prev = tmp;
			element.next = tmp;
		}
		// update corresponding cache Map
		cache.put(key, element.next);
		
		// if the element's KeySet is empty()
		// remove from the cacheElement list
		if (element.keysSet.size() == 0) {
			remove(element);
		}
	}

	/**
	 * remove one element from the head of the keysSet in DLL 
	 * (the LFU cacheElement)
	 * 
	 */
	private void removeOne() {
		// if head of CacheElement DLL is null
		if (head == null) {
			return;
		}
		
		int old = 0;
		// get the old key
		for (int n : head.keysSet) {
			old = n;
			break;
		}
		
		
		// remove the old key from head.keys
		head.keysSet.remove(old);
		
		if (head.keysSet.size() == 0) {
			remove(head);
		}
		cache.remove(old);
		
		valueHash.remove(old);
	}

	/**
	 * remove the cacheElement from DLL
	 * @param element
	 * helper function
	 */
	private void remove(CacheElement element) {
		// handle prev pointer
		if (element.prev == null) {
			head = element.next;
		} else {
			element.prev.next = element.next;
		}
		// handle next pointer
		if (element.next != null) {
			element.next.prev = element.prev;
		}
	}

	// CacheElement
	class CacheElement {
		public int count = 0;  // the count of visited for all of its keys
		public LinkedHashSet<Integer> keysSet = null;
		public CacheElement prev = null;
		public CacheElement next = null;

		public CacheElement(int count) {
			this.count = count;
			keysSet = new LinkedHashSet<Integer>();
			prev = next = null;
		}
	}

}
