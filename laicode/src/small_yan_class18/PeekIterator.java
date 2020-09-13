package small_yan_class18;

import java.util.Iterator;

public class PeekIterator implements Iterator<Integer> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private Iterator<Integer> iterator;
	private boolean hasPeeked;
	private Integer peekedElement;

	public PeekIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		hasPeeked = false;
		peekedElement = null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return hasPeeked || iterator.hasNext();
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (!hasPeeked) {
			return iterator.next();
		}

		Integer result = peekedElement;
		hasPeeked = false;
		peekedElement = null;
		return result;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	
	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (!hasPeeked) {
			peekedElement = iterator.next();
			hasPeeked = true;
		}
		return peekedElement;
	}

}
