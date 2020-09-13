package small_yan_class18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ZigzagIterator2 {

	/*
	 * this method doesn't work well. 
	 * 
	 * Please refer to leetcode5/iterator/IteratorZigZag..
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list_a = new ArrayList<Integer>();
		List<Integer> list_b = new ArrayList<Integer>();
		List<Integer> list_c = new ArrayList<Integer>();
		List<Integer> list_d = new ArrayList<Integer>();
		
		/*
		 * input: 
		 * {1,2,3,4}
		 * {5}
		 * {6}
		 * {7,8,9}
		 */
		list_a.add(1);
		list_a.add(2);
		list_a.add(3);
		list_a.add(4);
		
		list_b.add(5);
		
		list_c.add(6);
		
		list_d.add(7);
		list_d.add(8);
		list_d.add(9);
		
		System.out.println(list_a);
		System.out.println(list_b);
		System.out.println(list_c);
		System.out.println(list_d);
		
		List<Iterator<Integer>> listIter = new ArrayList<Iterator<Integer>>();
		listIter.add(list_a.iterator());
		listIter.add(list_b.iterator());
		listIter.add(list_c.iterator());
		listIter.add(list_d.iterator());
		
		ZigzagIterator2 sln = new ZigzagIterator2(listIter);
		
		while(sln.hasNext()) {
			System.out.print(sln.next() + "  ");
		}
	}
	
	Iterator<Iterator<Integer>> outer;
	Iterator<Integer> inner;
	
	List<Iterator<Integer>> list;
	boolean flag;
	
	public ZigzagIterator2(List<Iterator<Integer>> list) {
		this.list = list;
		inner = null;
		outer = list.iterator();
		flag = false;
	}
	
	public boolean hasNext() {
		if (!flag) {
			// flag == false
			succeed();
			flag = true;
		}
		if (inner == null || !inner.hasNext()) {
			// inner is null or inner.hasNext() is false
			return false;
		}
		return true;
	}
	
	public Integer next() {
		if (!flag) {
			succeed();
		}
		
		if (inner == null || !inner.hasNext()) {
			flag = true;
			throw new NoSuchElementException("no such element");
		}
		
		flag = false;
		return inner.next();
	}
	
	
	private void succeed() {
		move();
		if (inner != null && inner.hasNext()) {
			return ;
		}
		outer = list.iterator();
		move();
	}
	
	private void move() {
		while(outer.hasNext()) {
			inner = outer.next();
			if (!inner.hasNext()) {
				outer.remove(); // remove the current row. 
			} else {
				break;
			}
		}
	}
	
	
}
