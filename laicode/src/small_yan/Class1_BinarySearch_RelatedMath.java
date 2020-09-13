package small_yan;

public class Class1_BinarySearch_RelatedMath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test4();
//		test5();
	}
	
	/*
	 * task4
	 * sqrt() cubert()
	 */
	// method1
	public static void test4() {
		for(int i = 1; i < 10000; i ++) {
			System.out.println("---------------------------");
			System.out.println("i = " + i);
			int sqrt = sqrt1(i);
			System.out.println("sqrt = "+ sqrt);
			System.out.println("===========================");
		}
		
//		double i = 0.01;
//		for(int i = 1; i < 100; i ++) {
//			System.out.println("------------");
//			double k = (double) i;
//			double sqrt2 = sqrt3(k);
//			System.out.println(sqrt2);
//			System.out.println(sqrt2*sqrt2);
//			System.out.println("------------");
//		}
	}
	
	public static int sqrt1(int target) {
		if (target < 2) {
			return target;
		}
		int left = 1;
		int right = target;
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (target/mid == mid) {
				return mid;
			} else if (target/mid < mid) {
				// we want a smaller one
				right = mid;
			} else {
				left = mid;
			}
		}
		System.out.println("left = " + left);
		System.out.println("right = " + right);
		
		if (target/right > right) {
			System.out.println("!!!!! right is more closer");
			return right;
		} else {
			return left;
		}
	}
	
	// binary search 
	public static double sqrt2(double target) {
		if (target == 0 || target == 1) {
			return target;
		}
		// if target < 1 [target, 1]
		// if target > 1 [1, target]
		double left = target < 1 ? target : 1;
		double right = target < 1 ? 1: target;
		double epsilon = 0.00001;
		while(left <= right) {
			double mid = left + (right - left)/2;
			if (Math.abs(mid * mid - target)/target < epsilon) {
				return mid;
			} else if (mid * mid < target) {
				left =mid;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	/*
	 * Babylonian method
	 * 
	 * 1 start with an arbitary positive start value x, the closer to the root, the better
	 * 2 initiate y = 1
	 * 3 do following until desired approximation is archived
	 *   a) get the next appproximation for root using average of x and y
	 *   b) set y = n/x
	 */
	public static double sqrt3(double target) {
		double y = 1;
		double x = target;
		double e = 0.00001;
		while(x - y > e) {
			x = (x + y)/2;
			y = target/x;
		}
		return x;
	}
	
	
	/*
	 * task5
	 * divide two integers without using "/" 
	 * (or mod two integers without using "%")
	 * 
	 */
	public static void test5() {
		int a = 11, b = 2;
		int res = divide(a, b);
		System.out.println("res = " + res);
	}
	
	public static int divide(int a, int b) {
		// edge case
		if (b == 0) {
			return Integer.MAX_VALUE;
		}
		if (a == 0 || a < b) {
			return 0;
		}
		if (b == 1) {
			return a;
		}
		if (a == b) {
			return 1;
		}
		
		int left = 0;
		int right = 1;
		// find the right bound
		while(right * b < a) {
			right *=2;
		}
		// after the above loop, right is larger than a/b
		// do binary search
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if (mid * b == a) {
				return mid;
			} else if (mid * b > a) {
				// we want a smaller mid, go left
				right = mid;
			} else {
				left = mid;
			}
		}
		
		// first check right bound first
		if (right * b < a) {
			return right;
		}
		return left;
	}

}
