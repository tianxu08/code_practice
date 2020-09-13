package arrays;

public class p11_reverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void reverseIter(int[] a) {
		if (a == null || a.length == 0) {
			return ;
		}
		int i = 0, j = a.length - 1;
		while (i < j) {
			swap(a, i, j);
			i ++;
			j --;
		}
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void reverseRec(int[] a) {
		reverse(a, 0, a.length - 1);
	}
	
	public static void reverse(int[] a, int start, int end) {
		if (start > end) {
			return ;
		}
		swap(a, start, end);
		reverse(a, start + 1, end - 1);
	}

}
