package tag_iterator;
import java.util.*;
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
 * public class HoppingIterator<T> implements Iterator<T> {
 * 		public HoppingIterator(Iterator<T> iterator, int numHops) {...}
 * 		public boolean hasNext() {...}
 * 		public T next() {...}
 * }
 */

/*
 * use a iterator, what we need is to take some control of that iterator. 
 * (1) use nextItem to store the next element to print. 
 * (2) also, we need to distinguish whether it's the first element. 
 * 
 * hasNext() {
 * 		if nextItem != null return true;
 * 		else {
 * 			// nextItem == null
 * 			if (first == true) { 
 * 				if (iterator.hasNext){
 * 					nextItem = iterator.next();
 *  				first = true;
 *  				return true;
 * 				}
 * 				
 * 			} else {
 * 				// NOT the first element. 
 * 				// let the iterator go hopNums steps
 * 				for(hop = 0; hop < hopNums && iterator.hasNext(); hop ++) {
 * 					iterator.next();
 * 				}
 * 				
 * 				if (iterator.hasNext()) {
 * 					nextItem = iterator.next();
 * 					return true;
 * 				}	
 *     		}
 *      }
 *      return false;
 * }
 * 
 * next() {
 * 
 * }
 *
 */

/**
 *
 * @param <T>
 *
 * !!!! distinguish the first element
 */
public class IteratorHopping <T> implements Iterator<T> {
	private final Iterator<T> iterator;
	private T nextItem; // to stroe the nextElement
	private final int numHops;  // once initialized
	private boolean isFirst; // check whether is the first elemen

	public IteratorHopping(Iterator<T> iterator, int numHops) {
		if (numHops < 0) {
			throw new IllegalArgumentException(String.format(
					"numHops needs to be >= 0. You passed: %d", numHops));
		}
		this.numHops = numHops;
		this.iterator = iterator;
		nextItem = null;
		isFirst = true;
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}
		// if it is NOT the first time query, move forward numHop steps
		if (!isFirst) {
			for (int hop = 0; hop < numHops && iterator.hasNext(); hop++) {
				iterator.next();
			}
		}

		// if iterator.hasNext() == true
		if (iterator.hasNext()) {
			nextItem = iterator.next(); // store the next element into nextItem
			isFirst = false;  // set the first to false
		}
		return nextItem != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		T toReturn = nextItem;
		// reset nextItem
		nextItem = null;
		return toReturn;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		
		IteratorHopping<Integer> hi = new IteratorHopping<>(
				list.iterator(), 2);
		
		while(hi.hasNext()) {
			System.out.println(hi.next());
		}
	}
}
