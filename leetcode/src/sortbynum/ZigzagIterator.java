package sortbynum;

import java.util.*;

public class ZigzagIterator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> v1 = new ArrayList<Integer>();
		List<Integer> v2 = new ArrayList<Integer>();
		
		int[] array1 = {1,2};
		int[] array2 = {3,4,5,6};
		for(Integer i: array1){
			v1.add(i);
		}
		for(Integer i: array2) {
			v2.add(i);
		}
		
		ZigzagIterator it = new ZigzagIterator(v1, v2);
		while(it.hasNext()) {
			System.out.print(it.next() + "  ");
		}
		System.out.println();
		
		
	}

	private List<Integer> v1;
	private List<Integer> v2;
	
	int index_v1;
	int index_v2;
	boolean flag;
	
	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		this.v1 = v1;
		this.v2 = v2;
		index_v1 = 0;
		index_v2 = 0;
		this.flag = false;
	}

	public int next() {
		if (hasNext()) {
			if (index_v1 < v1.size() && index_v2 < v2.size()) {
				if (!flag) {
					// flag == false, choose from v1
					int rev = v1.get(index_v1);
					index_v1 ++;
					flag = true;
					return rev;
				} else {
					// flag == true, choose from v2
					int rev = v2.get(index_v2);
					index_v2 ++;
					flag = false;
					return rev;
				}
			} else {
				if (index_v1 < v1.size()) {
					int rev = v1.get(index_v1);
					index_v1 ++;
					return rev;
				} else {
					int rev = v2.get(index_v2);
					index_v2 ++;
					return rev;
				}
			}
		} else {
			throw new NoSuchElementException("No such element");
		}
	}

	public boolean hasNext() {
		if (index_v1 < v1.size() || index_v2 < v2.size()) {
			return true;
		}
		return false;
	}

}
