package sortbynum;

import java.util.*;

public class Task432_AllOne {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task432_AllOne sln = new Task432_AllOne();
		sln.inc("A");
		sln.inc("B");
		sln.inc("C");
		sln.inc("A");
		sln.inc("A");
		sln.inc("A");
		sln.inc("C");
		sln.inc("D");
		sln.print();

	}

	public void print() {
		Bucket cur = head;
		while (cur != tail) {
			System.out.println(cur);
			cur = cur.next;
		}
	}

	/*
	 * Use a HashMap 
	 */
	// maintain a DLL of Bucket, maintain in Descreding order
	private Bucket head;
	private Bucket tail;
	// Bucket(10) <-> Bucket(8) <--> Bucket(5) <--> Bucket(1)
	
	// key: value --> count, corresponding bucket
	private Map<Integer, Bucket> countToBucketMap;

	// key: value --> string, its count
	private Map<String, Integer> keyToCountMap;  // key-> value
	
	private class Bucket{
		int count;
		Set<String> keySet;
		Bucket prev;
		Bucket next;
		
		public Bucket(int cnt) {
			this.count = cnt;
			this.keySet = new HashSet<String>();
			this.prev = null;
			this.next = null;
		}

		public String toString() {
			return keySet + " : " + count;
		}
	}
	
	


	/** Initialize your data structure here. */
	public Task432_AllOne() {
		head = new Bucket(Integer.MIN_VALUE);
		tail = new Bucket(Integer.MAX_VALUE);
		head.next = tail;
		tail.prev = head;
		countToBucketMap = new HashMap<Integer, Bucket>();
		keyToCountMap = new HashMap<String, Integer>();
	}

	/**
	 * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
	 * 
	 * 1 check in keyCountMap. 
	 * 1.1  if exists, get the count and the Bucket. 
	 * 		delete the key in the keySet of the Bucket
	 *      if needed, create a new Bucket, whose count will be (count + 1), 
	 *      insert the key into the newBucket
	 * 1.2  if NOT exist, we will add (key, 1) into 
	 * 		create the bucket if needed.
	 *      
	 * 
	 */
	public void inc(String key) {
		if (keyToCountMap.containsKey(key)) {
			changeKey(key, 1);
		} else {
			keyToCountMap.put(key, 1);
			if (head.next.count != 1) {
				addBucketAfter(new Bucket(1), head);
			}
			head.next.keySet.add(key);
			countToBucketMap.put(1, head.next);
		}
	}

	/**
	 * Decrements an existing key by 1. If Key's value is 1, remove it from the
	 * data structure.
	 */
	public void dec(String key) {
		if (keyToCountMap.containsKey(key)) {
			int count = keyToCountMap.get(key);
			if (count == 1) {
				keyToCountMap.remove(key);
				removeKeyFromBucket(countToBucketMap.get(count), key);
			} else {
				changeKey(key, -1);
			}
		}
	}
	

	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		return tail.prev == head ? "" : (String) tail.prev.keySet.iterator().next();
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		return head.next == tail ? "" : (String)head.next.keySet.iterator().next();
	}
	
	
	// change key
	private void changeKey(String key, int offset) {
		int count = keyToCountMap.get(key);

		int newCount = count + offset;
		
		// update in keyCount
		keyToCountMap.put(key, newCount);
		
		// update the value in bucket
		Bucket bucket = countToBucketMap.get(count);
		Bucket newBucket = null;
		
		// find whether the key(count + offset) exists or NOT in the countBucket Map
		if (countToBucketMap.containsKey(newCount)) {
			newBucket = countToBucketMap.get(newCount);
		} else {
			newBucket = new Bucket(newCount);
			countToBucketMap.put(newCount, newBucket);
			// insert the bucket in front of (offset == -1) OR after the bucket(offset == 1)
			addBucketAfter(newBucket, offset == 1 ? bucket : bucket.prev);
		}
		
		newBucket.keySet.add(key);
		removeKeyFromBucket(bucket, key);
	}
	
	
	private void removeKeyFromBucket(Bucket bucket, String key) {
		bucket.keySet.remove(key);
		// the keyset is empty
		if (bucket.keySet.size() == 0) {
			removeBucketFromList(bucket);
			countToBucketMap.remove(bucket.count);
		}
	}
	
	private void removeBucketFromList(Bucket bucket) {
		bucket.prev.next = bucket.next;
		bucket.next.prev = bucket.prev;
		
		bucket.next = null;
		bucket.prev = null;
	}
	
	
	
	// add newBucket after preBucket
	private void addBucketAfter(Bucket newBucket, Bucket preBucket) {
		newBucket.next = preBucket.next;
		newBucket.prev = preBucket;
		
		preBucket.next.prev = newBucket;
		preBucket.next = newBucket;
	}
	
	
	

}
