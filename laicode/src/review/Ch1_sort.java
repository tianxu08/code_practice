package review;

import java.util.Arrays;


public class Ch1_sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test9();
	}

	/*
	 * 1 merge sort
	 */
	public static void test1() {
		int[] a = {5,4,9,2,1};
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return ;
		}
		int[] hlp = new int[arr.length];
		mergeSort(arr, hlp, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int[] hlp, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = left + (right - left) / 2;
		mergeSort(arr, hlp, left, mid);
		mergeSort(arr, hlp, mid + 1, right);
		merge(arr, hlp, left, mid, right);
	}

	// mrege function
	public static void merge(int[] arr, int[] hlp, int l, int m, int r) {
		// copy the [l..r] to the hlp
		for(int i = l; i <= r; i++) {
			hlp[i] = arr[i];
		}
		
		// start to merge
		int lIdx = l;
		int rIdx = m + 1;
		while(lIdx <= m && rIdx <= r) {
			if (hlp[lIdx] < hlp[rIdx]) {
				arr[l++] = hlp[lIdx++];
			} else {
				arr[l++] = hlp[rIdx++];
			}
		}
		
		while(lIdx <= m) {
			arr[l++] = hlp[lIdx++];
		}
	}
	
	/*
	 * 2 quick sort
	 */
	public static void test2() {
		int[] a = null;
		a = new int[]{};
		quickSort(a);
		System.out.println(Arrays.toString(a));
		a = new int[]{1,2,3,4};
		quickSort(a);
		System.out.println(Arrays.toString(a));
		a = new int[]{5,4,6,2,7};
		quickSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void quickSort(int[] a) {
		if (a == null || a.length <= 1) {
			return ;
		}
	    quickSort(a, 0, a.length - 1);
	}
	
	public static void quickSort(int[] a, int l, int r) {
		if (l >= r) {
			return ;
		}
		int pivotIdx = partition(a, l, r);
		quickSort(a, l, pivotIdx - 1);
		quickSort(a, pivotIdx + 1, r);
		
	}
	
	public static int partition(int[] a, int l, int r) {
		int pivotIdx = r;
		int pivot = a[pivotIdx];
		int start = l, end = r - 1;
		while(start <= end) {
			if (a[start] < pivot) {
				start ++;
			} else if (a[end] >= pivot) {
				end --;
			} else {
				swap(a, start, end);
				start ++;
				end --;
			}
		}
		swap(a, start, pivotIdx);
		return start;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	
	public static int partitionRandom(int[] a, int l, int r) {
		int pivotIdx = getRanomWithin(l, r);
		int pivot = a[pivotIdx];
		swap(a, pivotIdx, r);
		int start = l, end = r - 1;
		// the same with the previous version 
		while(start <= end) {
			if (a[start] < pivot) {
				start ++;
			} else if (a[end] >= pivot) {
				end --;
			} else {
				swap(a, start, end);
				start ++;
				end --;
			}
		}
		swap(a, start, pivotIdx);
		return start;
	}
	
	public static int getRanomWithin(int left, int right) {
		return left + (int)Math.random() *(right - left + 1);
	}
	
	
	/*
	 * 3 selection sort
	 * every time, get the minimum element and put it in the corresponting position
	 */
	public static void selectionSort(int[] a) {
		if (a == null || a.length <= 1) {
			return ;
		}
		for(int i = 0; i < a.length; i ++) {
			int min = a[i];
			int minIdx = i;
			for(int j = i + 1; j < a.length; j++) {
				if (a[j] < min) {
					// update min and minIdx
					min = a[j];
					minIdx = j;
				}
			}
			// swap the min to position i
			swap(a, minIdx, i);
		}
	}
	
	
	/*
	 * 4 bubble sort
	 */ 
	
	/*
	 * 5 counting sort
	 * http://www.geeksforgeeks.org/counting-sort/
	 */
	/*
	 * input is in 0..10
	 * 
	 */
	public static void countingSort(int[] a) {
		int n = a.length;
		int[] count = new int[11];
		// get the count array
		for(int i = 0; i <= 10; i ++) {
			count[a[i]]++;
		}
		
		// change count[i] so that count[i] now contains the acutal position of this char in output array
		for(int i = 1; i <= 10; i++) {
			count[i] += count[i - 1];
		}
		int[] output = new int[n];
		// build the output array
		for(int i = 0; i < n; i ++) {
			output[count[a[i] - 1]] = a[i];
			count[a[i] -1] --;
		}
	}
	
	
	
	/*
	 * 6 bucket sort
	 * http://www.geeksforgeeks.org/bucket-sort-2/
	 */
	
	
	/*
	 * 7 Convert string “A1B2C3D4E5” to “ABCDE12345”
	 * 
	 * MergeSort
	 * only change the merge function, Char is in front of digit 
	 */
	public static String t7_convertString(String s) {
		return null;
	}
	
	public static void t7_mergeSort(char[] arr, char[] helper, int left, int right) {
		
	}
	
	public static void t7_merge(char[] arr, char[] helper, int left, int mid, int right) {
		// copy the [left, right] into helper
		for(int i = left; i<= right; i++) {
			
		}
		
	}
	
	// return true if a is less than b
	// otherwise return false
	public boolean lessThan(char a, char b) {
		return false;
	}
	
	// use this two function to helper lessThan() function 
	public boolean isLetter(char ch) {
		return false;
	}
	public boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}
	
	
	/*
	 * 8 Array Shuffling 1 (move 0 to the right end)
	 * 
	 * just move 0 to the right
	 * The relative order of the elements in the original array does not need to be maintained.
	 */
	public static void t8_arrayshuffing1(int[] a) {
		if (a == null || a.length <= 1) {
			return ;
		}
		int index = 0;
		for(int i = 0; i < a.length; i++) {
			// only copy the non-Zero element into a[index]
			if (a[i] != 0) {
				a[index] = a[i];
				index ++;
			}
		}
		
		if (index < a.length) {
			a[index] = 0;
			index ++;
		}
	} 
	
	
	
	/*
	 * Rainbow Sort
	 * red -1, 
	 * green 0,
	 * blue 1
	 * 
	 * result is like -1, 0, 0, 1, 1
	 * 
	 * [0, i)   -1
	 * [i, j)   0
	 * [j, k]  to explore
	 * (k, n - 1]  1
	 * 
	 * 
	 * int neg = zero = 0, one = n - 1
	 * 
	 * while(zero <= one)
	 * if a[zero] == -1  swap(a, neg, zero) neg ++  zero++
	 *    a[zero] == 0   j ++
	 *    a[zero] == 1   swap(a, zero, one) one --, zero remains
	 *    
	 *    
	 * 
	 */
	public static void test9() {
		int[] a = null;
		a = new int[]{};
//		t9_rainbowSort(a);
//		System.out.println(Arrays.toString(a));
		a = new int[]{-1,0,0,1,1};
		t9_rainbowSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	// more readable
	public static void t9_rainbowSort(int[] a){
		if (a == null || a.length <= 1) {
			return ;
		}
		int zero = 0, neg = 0, one = a.length - 1;
		
		while(zero <= one) {
			if (a[zero] == -1) {
				swap(a, zero, neg);
				zero ++;
				neg ++;
			} else if (a[zero] == 0) {
				zero++;
			} else {
				swap(a, zero, one);
				one --;
			}
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
