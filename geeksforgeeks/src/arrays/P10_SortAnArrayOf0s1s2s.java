package arrays;

public class P10_SortAnArrayOf0s1s2s {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	/*
	 * http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
	 */
	
	/*
	 * This is three color problem
	 * http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Sort/Flag/
	 * 
	 * a[0..low - 1]  0s
	 * a[low.. mid - ] 1s
	 * a[mid.. high] unknown
	 * a[high + 1.. n -1] 2
	 */
	
	public static void sort012(int[] a) {
		int low = 0, mid = 0;
		int high = a.length - 1;
		while (mid <= high) {
			int midElem = a[mid];
			if (midElem == 0) {
				swap(a, low, mid);
				low ++;
				mid ++;
			} else if (midElem == 1) {
				mid ++;
			} else if (midElem == 2) {
				swap(a, mid, high);
				high --;
			}
		}
	} 
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void test() {
		int[] a = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
		sort012(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	
	

}
