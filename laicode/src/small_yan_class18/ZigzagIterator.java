package small_yan_class18;

import java.util.Iterator;
import java.util.List;

public class ZigzagIterator implements Iterator<Integer> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int row;
	int col;
	boolean flag;
	List<List<Integer>> list;
	
	public ZigzagIterator(List<List<Integer>> list) {
		// TODO Auto-generated constructor stub
		this.row = -1;
		this.col = 0;
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		return false;
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
