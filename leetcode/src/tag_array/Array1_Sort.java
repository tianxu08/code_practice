package tag_array;

import java.util.*;
import java.util.Map.Entry;

import ds.*;

public class Array1_Sort {
    public static void main(String[] args) {
//        test();
//        t3_test();
        test4();

    }

    /**
     * sort related
     * 1. quick sort
     * 2. merge sort
     * 3. sort in special order
     * 4. count sort
     */

    public static void test() {
        int[] arr = {5,2,9,1,4,3};
        Debug.printArray(arr);
        //  t1_quickSort(arr);
        t2_mergeSort(arr);
        Debug.printArray(arr);
    }

    // quick sort
    public static void t1_quickSort(int[] arr) {
        t1_quickSort(arr, 0, arr.length - 1);
    }

    public static void t1_quickSort(int[] arr, int x, int y) {
        if (x >= y) {
            return ;
        }
        int pivotIdx = t1_partition(arr, x, y);
        t1_quickSort(arr, x, pivotIdx - 1);
        t1_quickSort(arr, pivotIdx + 1, y);
    }
    public static int t1_partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l, j = r - 1;
        while(i <= j) {  // !!!! here, i <= j
            if (arr[i] < pivot) {
                i++;
            } else if (arr[j] >= pivot) {
                j--;
            } else {
                // swap
                Utilities.swap(arr, i, j);
                i++;
                j--;
            }
        }
        Utilities.swap(arr, i, r);
        return i;
    }

    public static void t2_mergeSort(int[] arr) {
        int n = arr.length;
        int[] helper = new int[n];
        t2_mergesortHelper(arr, helper, 0, n - 1);
    }

    public static void t2_mergesortHelper(int[] arr, int[] helper, int l, int r) {
        if (l >= r) {
            return ;
        }
        int m = l + (r - l)/2;
        t2_mergesortHelper(arr, helper, l, m);
        t2_mergesortHelper(arr, helper, m + 1, r);
        t2_merger(arr, helper, l, m, r);
    }

    public static void t2_merger(int[] arr, int[] helper, int l, int m, int r) {
        if (l >= r){
            return ;
        }
        // copy the element from arr to helper
        for (int i = l; i <= r; i++) {
            helper[i] = arr[i];
        }
        // merge in arr
        int i = l, j = m + 1;
        int idx = l;
        while(i <= m && j <= r) {
            if (helper[i] < helper[j]) {
                arr[idx] = helper[i];
                i++;
            } else {
                arr[idx] = helper[j];
                j++;
            }
            idx++;
        }

        while(i <= m) {
            arr[idx] = helper[i];
            idx++;
            i++;
        }
    }

    /**
     * t3
     * Given two integer arrays A1 and A2,
     * sort A1 in such a way that the relative order among the elements will be same as those are in A2.
     * For the elements that are not in A2, append them in the right end of the A1 in an ascending order.
     * Assumptions:
     * A1 and A2 are both not null.
     * There are no duplicate elements in A2.
     * Examples:
     * A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3},
     * A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9}
     */

    public static void t3_test() {
        int[] A1 = {2, 1, 2, 5, 7, 1, 9, 3};
        int[] A2 = {2, 1, 3};
        int[] result = t3_sortSpecial(A1, A2);
        Debug.printArray(result);
    }
    public static int[] t3_sortSpecial(int[] A1, int[] A2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // traverse A1 and put element into map
        for (Integer i: A1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] result = new int[A1.length];
        int index = 0;
        for (Integer i: A2) {
            if (map.containsKey(i)) {
                int count = map.get(i);
                while(count > 0) {
                    result[index ++] = i;
                    count --;
                }
                map.remove(i);
            }
        }
        int remainingIdx = index;
        // traverse the map
        for(Entry<Integer, Integer> entry: map.entrySet()) {
            result[index ++] = entry.getKey();
        }
        // sorr the remaining
        t1_quickSort(result, remainingIdx, result.length - 1);

        return result;
    }


    /**
     * For simplicity, consider the data in the range 0 to 9.
     Input data: 1, 4, 1, 2, 7, 5, 2
     1) Take a count array to store the count of each unique object.
     Index:     0  1  2  3  4  5  6  7  8  9
     Count:     0  2  2  0   1  1  0  1  0  0

     2) Modify the count array such that each element at each index
     stores the sum of previous counts.
     Index:     0  1  2  3  4  5  6  7  8  9
     Count:     0  2  4  4  5  6  6  7  7  7

     The modified count array indicates the position of each object in
     the output sequence.

     3) Output each object from the input sequence followed by
     decreasing its count by 1.
     Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2 - 1 = 1.
     Put data 1 at index 1 in output.
     Decrease count by 1 to place
     next data 1 at an index 1 - 1 = 0
     smaller than this index.
     */

    public static void test4(){
        int[] array = {1,4,1,2,7,5,2};
        t4_count_sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void t4_count_sort(int[] array) {
        int n = array.length;
        int[] count = new int[10];
        // store count
        for(int i = 0; i < n; i ++) {
            count[array[i]]++;
        }
        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for(int i = 1; i <10; i ++) {
            count[i] = count[i] + count[i - 1];
        }
        // build the output
        int[] output = new int[n];
        for(int i = 0; i < array.length; i ++) {
            // index = count[array[i] - 1]
            output[count[array[i]] - 1] = array[i];
            // decrease the count[]
            count[array[i]] --;
        }
        // copy the output back to array
        for(int i = 0; i < array.length; i ++) {
            array[i] = output[i];
        }
    }


}
