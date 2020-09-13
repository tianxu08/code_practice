package arrays;

public class p11_CountInversionsInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	/*
	 * http://www.geeksforgeeks.org/counting-inversions/
	 */
	/*
	 * Count Inversions in an array Inversion Count for an array indicates – how
	 * far (or close) the array is from being sorted. If array is already sorted
	 * then inversion count is 0. If array is sorted in reverse order that
	 * inversion count is the maximum. Formally speaking, two elements a[i] and
	 * a[j] form an inversion if a[i] > a[j] and i < j
	 * 
	 * Example: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1),
	 * (4, 3).
	 */
	
	// use two loops. Brout Force
	public static int getinvCount(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[i]) {
					count ++;
				}
			}
		}
		return count;
	} // time: O(n ^2) 
	
	public static void test() {
		int[] a = {1, 20, 6, 4, 5};
		int c = getinvCount(a);
		int c2 = getInvCount2(a);
		System.out.println("c = " + c);
		System.out.println("c2 = " + c2);
	}
	
	/*
	 * Modification of merge sort
	 */
	
	public static int getInvCount2(int[] a) {
		return mergeSort(a);
	}
	
	public static int mergeSort(int[] a) {
		int start = 0, end = a.length - 1;
		int[] temp = new int[a.length];
		int inv_count = mergeSortHelper(a, temp, start, end);
		return inv_count;
	}
	public static int mergeSortHelper(int[] a, int[] temp ,int start, int end) {
		int inv_count = 0;
		if (start < end) {
			int mid = start + (end - start)/2;
			
			inv_count += mergeSortHelper(a, temp, start, mid);
			inv_count += mergeSortHelper(a, temp, mid + 1, end);
			
			inv_count += merge(a, temp, start, mid + 1, end);
		}
		return inv_count;
	}
	
	public static int merge(int[] a,int[] temp ,int start, int mid, int end) {
		int i = start;  // mid - 1 is the leftEnd
		int j = mid;   // stands the rightStart
		int k = start;
		int inv_count = 0;
		while(i <= mid -1 && j <= end) {
			if (a[i] <= a[j]) {
				temp[k] = a[i];
				i ++;
				k ++;
			} else {
				// a[i] > a[j] 
				temp[k ++] = a[j ++];
				inv_count += (mid - i);
				// it's tricky here. since a[leftStart, leftEnd] and a[rightStart, rightEnd] are sorted
				// if a[i] > a[j], the following a[i] in the left subArray will be larger than the a[j]
				// so the inv_count += (mid - i)
			}
		}
		while (i <= mid - 1) {
			temp[k ++] = a[i ++];
		}
		
		while (j <= end) {
			temp[k++] = a[j++];
		}
		
		// copy the temp back to a
		for (int l = start; l <= end; l++) {
			a[l] = temp[l];
		}
		return inv_count;
	} // Time: O(n log n)
	
	
}
