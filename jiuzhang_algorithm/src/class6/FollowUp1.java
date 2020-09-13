package class6;
import java.util.*;
import ds.*;
public class FollowUp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 1 Find Peak Element
	 * here is an integer array which has the following features:
	 * The numbers in adjacent positions are different.
	 * A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
	 * We define a position P is a peek if:
	 * A[P] > A[P-1] && A[P] > A[P+1]
	 * Find a peak element in this array. Return the index of the peak.
	 * 
	 */
	public int findPeak(int[] A) {
        // write your code here
        int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界 
        while(start + 1 <  end) {
            int mid = (start + end) / 2;
            if(A[mid] < A[mid - 1]) {
                end = mid;
            } else if(A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(A[start] < A[end]) {
            return end;
        } else { 
            return start;
        }
    }
	
	
	/*
	 * 2 Find Peak Element II
	 * 
	 * There is an integer matrix which has the following features:
	 * The numbers in adjacent positions are different.
	 * The matrix has n rows and m columns.
	 * For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
	 * For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
	 * 
	 * We define a position P is a peek if:
	 * A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
	 * Find a peak element in this matrix. Return the index of the peak.
	 * 
	 * 
	 * [
	 *  [1 ,2 ,3 ,6 ,5],
	 *  [16,41,23,22,6],
	 *  [15,17,24,21,7],
	 *  [14,18,19,20,10],
	 *  [13,14,11,10,9]
	 *  ]
	 *  
	 *  return index of 41 (which is [1,1]) or index of 24 (which is [2,2])
	 *  Note
	 *  The matrix may contains multiple peeks, find any of them.
	 *  Challenge
	 *  Solve it in O(n+m) time.
	 *  If you come up with an algorithm that you thought it is O(n log m) or O(m log n), 
	 *  can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?
	 */
	
	public List<Integer> findPeakII(int[][] A) {
        // this is the nlog(n) method
        int low = 1, high = A.length-2;
        List<Integer> ans = new ArrayList<Integer>();
        while(low <= high) {
            int mid = (low + high) / 2;
            int col = find(mid, A);
            if(A[mid][col] < A[mid - 1][col]) {
                high = mid - 1;
            } else if(A[mid][col] < A[mid + 1][col]) {
                low = mid + 1;
            } else {
                ans.add(mid);
                ans.add(col);
                break;
            }
        }
        return ans;
    }
    int find(int row, int [][]A) {
        int col = 0;
        for(int i = 0; i < A[row].length; i++) {
            if(A[row][i] > A[row][col]) {
                col = i;
            }
        }
        return col;
    } 
    
    
    /*
     * Quick Sort 思想
     * 
     * quick sort, 
     * quick select
     * Nuts & Bolts Problem
     * 
     * 
     */
    
    public class NBComparator {
    	  public int cmp(String a, String b) {
    		 return -1;  
    	  }
    }
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;

        qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare, 
                       int l, int u) {
        if (l >= u) return;
        // find the partition index for nuts with bolts[l]
        int part_inx = partition(nuts, bolts[l], compare, l, u);
        // partition bolts with nuts[part_inx]
        partition(bolts, nuts[part_inx], compare, l, u);
        // qsort recursively
        qsort(nuts, bolts, compare, l, part_inx - 1);
        qsort(nuts, bolts, compare, part_inx + 1, u);
    }

    private int partition(String[] str, String pivot, NBComparator compare, 
                          int l, int u) {
          //
        int m = l;
        for (int i = l + 1; i <= u; i++) {
            if (compare.cmp(str[i], pivot) == -1 || 
                compare.cmp(pivot, str[i]) == 1) {
                //
                m++;
                swap(str, i, m);
            } else if (compare.cmp(str[i], pivot) == 0 || 
                       compare.cmp(pivot, str[i]) == 0) {
                // swap nuts[l]/bolts[l] with pivot
                swap(str, i, l);
                i--;
            }
        }
        // move pivot to proper index
        swap(str, m, l);

        return m;
    }

    private void swap(String[] str, int l, int r) {
        String temp = str[l];
        str[l] = str[r];
        str[r] = temp;
    }
    
    
    /*
     * Median Follow up
     * 
     */
    
    /*
     * find the median of a given array
     * http://www.lintcode.com/en/problem/median/
     */
    public int median(int[] nums) {
        // write your code here
    	return -1;
    }
    /*
     * data stream median
     * http://www.lintcode.com/en/problem/data-stream-median/#
     */
    public int medianII(int[] nums) {
    	return -1;
    }
    
    /*
     * slide window median
     * http://www.lintcode.com/en/problem/sliding-window-median/
     * 
     * see class 3, SlidingWindowMedian
     */
    
    /*
     * Meidan of two sorted arrays
     * http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/#
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (KthElement(A, 0, B, 0, len/2) + KthElement(A, 0, B, 0, len/2 + 1))/2.0;
        } else {
            return KthElement(A, 0, B, 0, len/2 + 1);
        }
    }
    
    public static int KthElement(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }
        int A_key = A_start + k/2 - 1 < A.length ? A[A_start + k/2 - 1]: Integer.MAX_VALUE;
        int B_key = B_start + k/2 - 1 < B.length ? B[B_start + k/2 - 1]: Integer.MAX_VALUE;
        
        if(A_key < B_key) {
            // the first k/2 half must be smaller than the kth
            return KthElement(A, A_start + k/2, B, B_start, k - k/2);
        } else {
            return KthElement(A, A_start, B, B_start + k/2, k - k/2);
        }
    }
    
    /*
     * 第k大 median问题第二种 follow up
     */
    /*
     *  Kth Largest Element
     *  
     */
    public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        // write your code here
        // 1,2,3,4,5  4 2nd largest, 4th smallest, index 3 = len - k
        int index = getKth(numbers.size() - k, numbers, 0, numbers.size() - 1);
        return numbers.get(index);
    }
    
    //this k starts from 0
    public int getKth(int k, ArrayList<Integer> numbers, int start, int end) {
        int pivot = numbers.get(end);
        int left = start, right = end;
        while(true) {
            while(left < right && numbers.get(left) < pivot){
                left ++;
            }
            while (left < right && numbers.get(right) >= pivot) {
                right --;
            }
            
            if(left == right)
                break;
            swap(numbers, left, right);
        }
        // now the left points the first element >= pivot
        swap(numbers, left, end);
        if (k == left) {
            return k;
        } else if (k < left) {
            // in left side
            return getKth(k, numbers, start, left - 1);
        } else {
            return getKth(k, numbers, left + 1, end);
        }
    }
    
    public void swap(ArrayList<Integer> nums, int x, int y) {
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    } 
    
    /*
     * 2.  一个行列都递增的矩阵递增的第k  用priority queue 
     * 3.  两个数组他们乘积(和)的第k     同2
     * 4.  n个数组的第k               priority queue, merge
     * 5.  n个数组多台机器第k (k 较 小)
     * 6.  n个数组多台机器第k (k 较 大)
     */
    
    /*
     * 循环连续子序列
     * 连续子序列最大和
     * { -2, 11, -4, 13, -5, -2 }
     * 连续循环子序列最大和
     * { -2, 11, -4, 13, -5, -2 }
     * 
     * 
     * Gas Station
     * http://www.lintcode.com/en/problem/gas-station/
     */
    
    /*
     * 扫描线
     * Number of Airplanes in the Sky
     * http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/
     * 
     * 
     */
    public static class Point{
        int time;
        int flag;

        Point(int t, int s){
          this.time = t;
          this.flag = s;
        }
        public static Comparator<Point> PointComparator  = new Comparator<Point>(){
          public int compare(Point p1, Point p2){
            if(p1.time == p2.time) return p1.flag - p2.flag;
            else return p1.time - p2.time;
          }
        };
    }
    
    public static int countOfAirplanes(List<Interval> airplanes) { 
		List<Point> list = new ArrayList<Point>(airplanes.size() * 2);
		for (Interval i : airplanes) {
			list.add(new Point(i.start, 1));
			list.add(new Point(i.end, 0));
		}

		Collections.sort(list, Point.PointComparator);
		int count = 0, ans = 0;
		for (Point p : list) {
			if (p.flag == 1)
				count++;
			else
				count--;
			ans = Math.max(ans, count);
		}

		return ans;
	}
    
    /*
     * Building Outline
     * http://www.lintcode.com/en/problem/building-outline/
     */

}
