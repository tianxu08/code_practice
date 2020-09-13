package tag_HashMap_DLL;

import java.util.HashMap;
import java.util.LinkedHashSet;


public class LFUCache2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LFUCache2 sln = new LFUCache2(1);
		
		sln.put(2, 1);
		System.out.println(sln.get(1));
		sln.put(3, 2);
		System.out.println(sln.get(2));
		
		
	}
	
	private class CacheElement{
		int count;
		LinkedHashSet<Integer> keys;
		CacheElement prev;
		CacheElement next;
		
		public CacheElement(int _c) {
			this.count = _c;
			keys = new LinkedHashSet<Integer>();
			prev = null;
			next = null;
		}
	}
	
	private int capacity;
	private HashMap<Integer, Integer> valueHash;
	private HashMap<Integer, CacheElement> cacheHash;
	private CacheElement head;
	
	public LFUCache2(int _c) {
		this.capacity = _c;
		this.valueHash = new HashMap<Integer, Integer>();
		this.cacheHash = new HashMap<Integer, CacheElement>();
		this.head = null;
	}
	
	public int get(int key) {
		if (valueHash.containsKey(key)) {
			updateUsage(key); // the usage of key increased by 1
			return valueHash.get(key);
		}
		return -1;
	}
	
	
	public void put(int key, int value) {
		if (capacity == 0) {
			return ;
		}
		if (valueHash.containsKey(key)) {
			valueHash.put(key, value);
		} else {
			if (valueHash.size() < capacity) {
				// still have space
				valueHash.put(key, value);
			} else {
				// already full, we need to remove the oldest key
				removeLFUElement();
				valueHash.put(key, value);
			}
			// add to the cache Element list and update the cacheHash
			addToHead(key);
		}
		
		updateUsage(key);
		
	}
	
	
	private void  addToHead(int key) {
		if (head == null) {
			head = new CacheElement(0);
			head.keys.add(key);
		} else if (head.count > 0) {
			CacheElement newElem = new CacheElement(0);
			newElem.keys.add(key);
			
			newElem.next = head;
			head.prev = newElem;
			
			head = newElem;
		} else {
			// head.cout == 0
			head.keys.add(key);
		}
		// update cacheHash
		cacheHash.put(key, head);
	}
	
	
	private void updateUsage(int key) {
		// get the element which contains key
		CacheElement curElem = cacheHash.get(key);
		// delete the key from curElem.keys
		curElem.keys.remove(key);
		
		// case1 curElem is the last Element in the DLL
		if (curElem.next == null) {
			// create a new CacheElement
			CacheElement newElem = new CacheElement(curElem.count + 1);
			// add the "key" in newElem.keys
			newElem.keys.add(key);
			// link the newElem after curElem
			curElem.next = newElem;
			newElem.prev = curElem;
			
			// update in cacheHash, now, the key points to newElem
			cacheHash.put(key, newElem);
		} else if (curElem.next.count == curElem.count + 1) {
			// the next element's count exactly equals count + 1
			curElem.next.keys.add(key);
			
			// update in cacheHash
			cacheHash.put(key, curElem.next);
		} else {
			// the next element's count greater than count + 1
			// we need insert the new Element between curElem and curElem.next
			CacheElement newElem = new CacheElement(curElem.count + 1);
			newElem.keys.add(key);
			// remember the cur's next
			CacheElement curNext = curElem.next;
			
			// insert between cur and curnext
			curElem.next = newElem;
			newElem.prev = curElem;
			
			newElem.next = curNext;
			curNext.prev = newElem;
			
			// update in cacheHash
			cacheHash.put(key, newElem);
		}
		
		// check whether currentElem is empty, if YES, remove it from the list
		if (curElem.keys.size() == 0) {
			removeCacheElemet(curElem);
		}
	}
	
	private void removeLFUElement() {
		if (head == null) {
			return ;
		}
		
		int oldKey = 0;
		for (Integer n : head.keys) {
			oldKey = n;
			break;
		}
		// remove the key from head.keys
		head.keys.remove(oldKey);
		
		if (head.keys.size() == 0) {
			removeCacheElemet(head);
		}
		
		// update the valueHash
		valueHash.remove(oldKey);
		// update the cacehHash
		cacheHash.remove(oldKey);
	}
	
	
	
	private void removeCacheElemet(CacheElement elem) {
		
		if (elem.prev == null) {
			head = elem.next;
		} else {
			elem.prev.next = elem.next;
		}
		// handle next pointer
		if (elem.next != null) {
			elem.next.prev = elem.prev;
		}
		
		elem.prev = null;
		elem.next = null;
	}
	

}
