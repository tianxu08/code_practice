package s_array;


public class KClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	public static void test() {
		int[] a = {1, 4, 6, 8};
		int target = 2;
		int k = 0;
		int[] result = kClosest(a, target, k);
//		System.out.println( esult);
//		Debug.printArray(result);
	}
	
	
	
	public static int[] kClosest(int[] array, int target, int k) {
	    // Write your solution here
	    if (array == null || array.length < k) {
	      return null;
	    }
	    int[] result = new int[k];
	    int n = array.length;
	    // find the last element which is smaller than the target
	    int start = 0, end = array.length - 1;
	    while(start + 1 < end) {
	      int mid = start + (end - start)/2;
	      if (array[mid] == target) {
	        end = mid;
	      } else if (array[mid] > target) {
	        end = mid;
	      } else {
	        start = mid;
	      }
	    }
	    // if the target isn't in array
	    // after above while, start should point the last element which is smaller than target
	    // end points the first larger element than target. 
	    // if the target is in the array
	    // start points the last smaller element than target. end points the target
	    
	    // edge case a[start] > target, all element is larget than target
	    // a[end] < target, all target is smaller than target
	    if (array[start] > target) {
	      for(int i = 0; i < k; i ++) {
	        result[i] = array[i];
	      }
	    }
	    if (array[end] < target) {
	      for(int i = n - 1; n - i > k; i--) {
	        result[i] = array[i];
	      }
	    }
	    int index = 0;
	    while(index < k) {
	      int candidate = -1;
	      if (start >= 0 && end <= n - 1) {
	        int diff_start = Math.abs(array[start] - target);
	        int diff_end = Math.abs(array[end] - target);
	        if (diff_start < diff_end) {
	          candidate = array[start];
	          start --;
	        } else {
	          candidate = array[end];
	          end ++;
	        }
	      } else if (start < 0) {
	        candidate = array[end];
	        end ++;
	      } else {
	        candidate = array[start];
	        start --;
	      }
	      result[index] = candidate;
	      index ++;
	    }
	    
	    return result;
	  }

}
