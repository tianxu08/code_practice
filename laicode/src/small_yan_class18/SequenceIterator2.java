package small_yan_class18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/*
 * This time, the constructor get a List of Iterator as input. 
 * 
 */
public class SequenceIterator2 implements Iterator<Integer>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list_a = new ArrayList<Integer>();
		List<Integer> list_b = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i ++) {
			if (i % 2 == 0) {
				list_a.add(i);
			} else {
				list_b.add(i);
			}
		}
		
		System.out.println(list_a);
		System.out.println(list_b);
		System.out.println("---------------------");
		List<Iterator<Integer>> listIter = new ArrayList<Iterator<Integer>>();
		listIter.add(list_a.iterator());
		listIter.add(list_b.iterator());
		
		SequenceIterator2 sln = new SequenceIterator2(listIter);
		while(sln.hasNext()) {
			System.out.print(sln.next() + " ");
		}
	}
	
	Iterator<Integer> inner;   // iterator
	Iterator<Iterator<Integer>> outer;  // iterator of iterator
	public SequenceIterator2(List<Iterator<Integer>> list) {
		inner = null;
		outer = list.iterator();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		move();
		if (inner == null || ! inner.hasNext()) {
			return false;
		}
		return true;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		if (hasNext()) {
			return inner.next();
		}
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	private void move() {
		// if inner is null or inner.hasNext() is false
		// and 
		// outer.hasNext() is true
		// we reset inner as outer.next()
		while((inner == null || !inner.hasNext()) && 
				outer.hasNext()) {
			inner = outer.next();
		}
	}

}
