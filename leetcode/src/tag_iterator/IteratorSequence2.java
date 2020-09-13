package tag_iterator;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/*
 * This time, the constructor get a List of Iterator as input. 
 * 
 */
public class IteratorSequence2 implements Iterator<Integer>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list_a = new ArrayList<>();
		List<Integer> list_b = new ArrayList<>();
		
		for(int i = 0; i < 10; i ++) {
			if (i % 2 == 0) {
				list_a.add(i);
			} else {
				list_b.add(i);
			}
		}
		
		List<Integer> list_c = new ArrayList<>();
		list_c.add(11);
		list_c.add(12);
		
		System.out.println(list_a);
		System.out.println(list_b);
		System.out.println(list_c);
		System.out.println("---------------------");
		List<Iterator<Integer>> listIter = new ArrayList<Iterator<Integer>>();
		listIter.add(list_a.iterator());
		listIter.add(list_b.iterator());
		listIter.add(list_c.iterator());
		
		IteratorSequence2 sln = new IteratorSequence2(listIter);
		while(sln.hasNext()) {
			System.out.print(sln.next() + " ");
		}
	}
	
	Iterator<Integer> inner;   // iterator
	Iterator<Iterator<Integer>> outer;  // iterator of iterator
	public IteratorSequence2(List<Iterator<Integer>> list) {
		inner = null;
		outer = list.iterator();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		while((inner == null || !inner.hasNext()) && 
				outer.hasNext()) {
			inner = outer.next();
		}
		
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
