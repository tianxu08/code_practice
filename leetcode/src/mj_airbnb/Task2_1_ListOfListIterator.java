package mj_airbnb;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Task2_1_ListOfListIterator implements Iterator<Integer>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private Iterator<List<Integer>> rowIter;
	private Iterator<Integer> colIter;
	
	public Task2_1_ListOfListIterator(List<List<Integer>> vec2d) {
		rowIter = vec2d.iterator();
		colIter = Collections.emptyIterator();
	}
	
	@Override
	public Integer next() {
		return colIter.next();
	}
	
	@Override
	public boolean hasNext() {
		while((colIter == null || !colIter.hasNext()) && rowIter.hasNext()) {
			colIter = rowIter.next().iterator();
		}
		return colIter != null && colIter.hasNext();
	}
	
	@Override
	public void remove() {
		while(colIter == null && rowIter.hasNext()) {
			colIter = rowIter.next().iterator();
		}
		if (colIter != null) {
			colIter.remove();
		}
	}
}
