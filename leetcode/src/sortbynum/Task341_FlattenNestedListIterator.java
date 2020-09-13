package sortbynum;

import java.util.*;

/**
 * @author xutian
 * a nested list of integers, implement an iterator to faltten it
 * each element is either an integer, or a list. whose elements may also be integers or other lists
 * 
 * e.g
 * list[[1,1], 2, [1,1]]
 * By calling next repeatedly until hasNext returns false, 
 * the order of elements returned by next should be: [1,1,2,1,1].
 * 
 * Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false, 
 * the order of elements returned by next should be: [1,4,6].
 * 
 * Method1
 * use an list to store all elements in the nestedList. 
 * this can be implemented recursively.
 * 
 * after we have the arraylist, it's easy to call the arrayList's hasNext() and next()
 * 
 */

public class Task341_FlattenNestedListIterator implements Iterator<Integer> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private List<Integer> elements ;
	private Iterator<Integer> it;
	
	public Task341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
		elements = new ArrayList<Integer>();
		addElement(nestedList);
		it = elements.iterator();
	}
	
	public void addElement(List<NestedInteger> nestedList) {
		for(NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				elements.add(ni.getInteger());
			} else {
				addElement(ni.getList());
			}
		}
	}


	@Override
	public Integer next() {
		return it.next();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
