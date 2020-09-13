package class4;
import ds.*;
import java.util.Arrays;

public class TP3_twoArray_twoPtrs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 *  The Smallest Difference
	 *  http://www.lintcode.com/en/problem/the-smallest-difference/
	 *  For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0
	 */
	
	public static int smallestDifference(int[] A, int[] B) {
        // write your code here
		// sort the arrays first. 
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int i = 0, j = 0;
		int min = Integer.MAX_VALUE;
		while(i < A.length && j < B.length) {
			min = Math.min(min, Math.abs(A[i] - B[j]));
			if (A[i] < B[j]) {
				i ++;
			} else {
				j ++;
			}
		}
		
		return min;
    }
	
	/*
	 * http://www.lintcode.com/en/problem/merge-two-sorted-lists/
	 * 
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write your code here
        if (l1 == null)
            return l2;
        if (l2 == null) 
            return l1;
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode l1Ptr = l1, l2Ptr = l2;
        while (l1Ptr != null && l2Ptr!= null) {
            if (l1Ptr.value < l2Ptr.value) {
                tail.next = l1Ptr;
                tail = tail.next;
                l1Ptr = l1Ptr.next;
            } else {
                tail.next = l2Ptr;
                tail = tail.next;
                l2Ptr = l2Ptr.next;
            }
        }
        if (l1Ptr != null) {
            tail.next = l1Ptr;
        }
        if (l2Ptr != null) {
            tail.next = l2Ptr;
        }
        return dummy.next;
    }
	
	/*
	 * 两个指针
	 * a. 对撞型 (2 sum 类 和 partition 类)
	 * b. 前向型 (窗口类, 快慢类)
	 * c. 两个数组,两个指针 (并行)
	 */
}
