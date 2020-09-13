package tag_iterator;

import java.util.Iterator;


/**
 * 
 * @author xutian
 * LC 284
 *
 *  Given an Iterator class interface with methods: next() and hasNext(), 
 *  design and implement a PeekingIterator that support the peek() operation -- 
 *  it essentially peek() at the element that will be returned by the next call to next().
 *  
 *  Here is an example. 
 *  Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *  
 *  Call next() gets you 1, the first element in the list.
 *  
 *  Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *  
 *  You call next() the final time and it returns 3, the last element. 
 *  
 *  Calling hasNext() after that should return false.
 *  
 *  with a help of iterator, and make some control 
 */

public class IteratorPeek implements Iterator<Integer> {
    private Iterator<Integer> iterator;
	private boolean hasPeeked;
	private Integer peekedElement;
	public IteratorPeek(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
		hasPeeked = false;
		peekedElement = null;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (!hasPeeked) {
			peekedElement = iterator.next();
			hasPeeked = true;
		}
		return peekedElement;
	}

	@Override
	public boolean hasNext() {
	    return hasPeeked || iterator.hasNext();
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (!hasPeeked) {
	    	// the iterator.next() hasn't been peeked. just return 
			return iterator.next();
		}
	    
	    // hasPeeked is true. we just return the peekElement
		Integer result = peekedElement;
		
		// set the hasPeeked = false and peekElement = null
		hasPeeked = false;
		peekedElement = null;
		return result;
	}

	

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}