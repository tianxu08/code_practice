package tag_backtracking;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * task89
	 * gray code
	 * 
	 * binary numeral system where two successive values differ in only one bit
	 * n = 2 return 0 1 3 2
	 * 00 - 0
	 * 01 - 1
	 * 11 - 3
	 * 10 - 2
	 * 
	 * For a given n, a gray code sequence is not uniquely defined.
	 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
	 * 
	 * 
	 * n = 1
	 * 0
	 * 1
	 * 
	 * n = 2
	 * 00
	 * 01
	 * add 1 from the end to start of the prev
	 * 11
	 * 10
	 * 
	 */
	public List<Integer> grayCode(int n) {
		if (n < 0) {
			return new ArrayList<Integer>();
		}
		
		if (n == 0) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			return list;
		}
		// n > 0
		// get the grayCode(n - 1) 
		// e.g 
		List<Integer> prev = grayCode(n  - 1);
		List<Integer> cur = new ArrayList<Integer>(prev);
		int addBit = 1 << n - 1;
		
		// from end to start
		for(int i = prev.size() - 1; i >= 0; i --) {
			cur.add(addBit + prev.get(i));
		}
		return cur;
	} 
}
