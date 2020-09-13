package mj_linkedin;

import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

	public List<NestedInteger> nestedList;
	private int outIndex;
	private int inIndex;
	public NestedIterator(List<NestedInteger> nestedList) {
		// TODO Auto-generated constructor stub
		this.nestedList = nestedList;
		outIndex = 0;
		inIndex = 0;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return outIndex < nestedList.size();
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
	

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
