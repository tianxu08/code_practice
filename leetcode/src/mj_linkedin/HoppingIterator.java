package mj_linkedin;

import java.util.*;
public class HoppingIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		int numHops = 2;
		HoppingIterator sln = new HoppingIterator(lista.iterator(), numHops);
		while(sln.hasNext()) {
			System.out.println(sln.next());
		}
	}
	
	/**
	 * Implement an iterator that hops specified number of times and then returns the next
	 * element after the hop. Note: the iterator always returns the first element as
	 * it is, and starts hopping only after the first element.
	 *
	 * Examples:
	 *
	 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
	 * iterator will return [1, 3, 5] in order when the hop value is 1.
	 *
	 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
	 * iterator will return [1, 4] in order when the hop value is 2.
	 *
	 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
	 * iterator will return [1, 5] in order when the hop value is 3.
	 * 
	 * Methods expected to be implemented:
	 *  
	 * public class HoppingIterator implements Iterator {
	 * 		public HoppingIterator(Iterator iterator, int numHops) {…}
	 * 		public boolean hasNext() {…}
	 * 		public T next() {…}
	 * }
	 */
	 
	private final int numHops;
	private Integer nextItem;  // store the nextItem
	private boolean first;
	private Iterator<Integer> iterator;
	public HoppingIterator(Iterator<Integer> iterator, int numHops) {
		this.numHops = numHops;
		this.nextItem = null;
		this.first = true;
		this.iterator =iterator;
	}
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}
		if (!first) { // first == false
			for(int hop = 0; hop < numHops && iterator.hasNext(); hop ++) {
				iterator.next();
			}
		}
		if (iterator.hasNext()) {
			nextItem = iterator.next();
			first = false;
		}
		return nextItem != null;
	}
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Integer toReturn = nextItem;
		nextItem = null;
		return toReturn;
	}
		  

}
