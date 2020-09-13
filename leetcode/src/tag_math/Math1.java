package tag_math;

import java.util.ArrayList;
import java.util.List;

public class Math1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	/**
	 * get all prime factors of a give integer
	 */
	public static void test1() {
		int n = 30;
		List<Integer> list = t1_prime_factors(n);
		System.out.println(list);
	}
	
	// let's start with 2, 3, 5... if we already finish the 2, there will no possible to deal with 4, etc. 
	// this can we get all prim factors. 
	public static List<Integer> t1_prime_factors(int n) {
		List<Integer> list = new ArrayList<>();
		while(n % 2 == 0) {
			list.add(2);
			n = n / 2;
		}
		
		for (int i = 3; i <= Math.sqrt(n); i++) {
			while(n % i == 0) { // !!!!!
				list.add(i);
				n = n / i;
			}
		}
		
		if (n > 2) {
			list.add(n);
		}
		return list;
	}
	
}
