package tag_array;

import ds.Debug;
import ds.Utilities;

import java.util.Arrays;

public class Array3_2Pointers {
    public static void main(String[] args) {
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }


    /**
     * two pointers
     * 1 move 0s to the end(non-zero num order need to be maintained)
     * 2 rainbow sort (3, 4, k colors)
     * 3 sorted array deduplicate(keep 1, 2, 0 element for duplicates)
     * 4 unsorted array deduplicate (unsorted, keeo 0 elem)
     * 5 array dedup 5 (unsorted, keep 2 element)
     * 6 Rearrange positive and negative numbers in O(n) time and O(1) extra space
     */


    /**
     * t1:
     * Given an array of integers, move all the 0s to the right end of the array.
     *
     * The relative order of the elements in the original array need to be maintained.
     *
     * Assumptions:
     *
     * The given array is not null.
     * Examples:
     *
     * {1} --> {1}
     * {1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
     * sf
     * {1, 3, 0, 0, 1}
     *
     */
    public static int[] t1_move0s(int[] array) {
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[index] = array[i];
                index ++;
            }
        }
        while(index < array.length) {
            array[index] = 0;
            index++;
        }
        return array;
    }

    /**
     *
     * @param
     * @return
     * -1 | 0 | ... | 1
     *    i   k     j
     *
     * init i = 0, j = n - 1, k = 0
     */

    public static void test2() {
        int[] array = {1, 0, 1, -1, 0};
        int[] res = t2_rainbowSort_3Colors(array);
        Debug.printArray(array);

        int[] array2 = {1,3,2,0,0,2};
        int[] res2 = t2_rainbowSort_4Colors(array2);
        Debug.printArray(res2);

        int[] array3 = {1,3,4,2,5,2,1};
        int k = 6;
        int[] res3 = t2_rainbowSort_kColors(array3, k);
        Debug.printArray(res3);
    }
    public static int[] t2_rainbowSort_3Colors(int[] array) {
        int i = 0, k = 0, j = array.length - 1;
        while (k <= j) {
            if (array[k] == -1) {
                Utilities.swap(array, i, k);
                i++;
                k++;
            } else if (array[k] == 0) {
                k++;
            } else {
                // array[k] == 1
                Utilities.swap(array, k, j);
                j --;
            }
        }
        return array;
    }

    public static int[] t2_rainbowSort_4Colors(int[] array) {
        int i = 0, j = 0, k = 0, m = array.length - 1;
        while (k <= m) {
            if (array[k] == 0) {
                Utilities.swap(array, k, j);
                Utilities.swap(array, j, i);
                i++;
                j++;
                k++;
            } else if (array[k] == 1) {
                Utilities.swap(array, j, k);
                j++;
                k++;
            } else if (array[k] == 2) {
                k++;
            } else {
                Utilities.swap(array, k, m);
                m--;
            }
        }
        return array;
    }

    public static int[] t2_rainbowSort_kColors(int[] array, int k) {
        int[] pos = new int[k];
        pos[k - 1] = array.length - 1;
        while(pos[k - 2] <= pos[k - 1]) {
            int val = array[pos[k - 2]];
            if (val < k - 2) {
                int counter = k - 2 - val;
                for (int i = 0; i < counter; i++) {
                    Utilities.swap(array, pos[k - 2 - i], pos[k - 2 - i - 1]);
                }
                for (int i = 0; i <= counter; i++) {
                    pos[k - 2 - i]++;
                }
            } else if (val == k - 2) {
                pos[k - 2] ++;
            } else {
                // val > k - 2
                Utilities.swap(array, pos[k - 2], pos[k - 1]);
                pos[k - 1] --;
            }
        }
        return array;
    }

    /**
     * t3 sorted array, dedupe
     * t3.1 keep 1 element
     * t3.2 keep 2 element
     * t3.3 keep 0 element
     */

    public static  void test3() {
        int[] a = {1, 2, 3};
//        int[] rev = t3_dedup_1(a);

//        Debug.printArray(rev);
//        int[] rev2 = t3_dedup_2(a);
//        Debug.printArray(rev2);
        int[] rev3 = t3_dedup_3(a);
        Debug.printArray(rev3);

    }

    // keep 1 element
    public static int[] t3_dedup_1(int[] array) {
        int s = 0, f = 1;
        while (f < array.length) {
            if (array[f] == array[s]) {
                f++;
            } else {
                array[++s] = array[f];
            }
        }

        return Arrays.copyOfRange(array, 0, s + 1); // java, subarray
    }

    // keep 2 elements
    public static int[] t3_dedup_2(int[] array) {
        if (array == null || array.length <= 2) {
            return array;
        }
        int s = 0, f = 1;
        int counter = 1;
        while (f < array.length) {
            if (array[f] == array[s]) {
                counter++;
                if (counter <= 2) {
                    array[++s] = array[f];
                }
                f++;
            } else {
                array[++s] = array[f];
                f++;
                // reset counter
                counter = 1;
            }
        }

        return Arrays.copyOfRange(array, 0, s + 1);
    }

    // remove all duplicate
    public static int[] t3_dedup_3(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int s = 0, f = 1;
        boolean hasDup = false;
        while (f < array.length) {
            if (array[s] == array[f]) {
                hasDup = true;

            } else if (array[s] != array[f]) {
                if (hasDup) {
                    // the candidate has dup
                    // put a new candidate
                    array[s] = array[f];
                    // reset the flag
                    hasDup = false;
                } else {
                    // the candidate doesn't have dup
                    ++s;
                    // put a new candidate
                    array[s] = array[f];
                }
            }
            f++;
        }

        return hasDup ? Arrays.copyOfRange(array, 0, s) : Arrays.copyOfRange(array, 0, s + 1);
    }


    /**
     * t4:
     * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right.
     * For each group of elements with the same value do not keep any of them.
     *
     * Do this in-place, using the left side of the original array. Return the array after deduplication.
     *
     * Assumptions
     *
     * The given array is not null
     * Examples
     *
     * {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
     */

    public static  void test4() {
        int[] array = {1, 2, 3, 3, 3, 2, 2};
        int[] res = t4_dedup(array);
        Debug.printArray(res);
    }
    public static int[] t4_dedup(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int end = -1;
        int i = 0;
        while (i < array.length) {
            if (end == -1 || array[i] != array[end]) {
                // put array[i] as the candidate
                array[++end] = array[i];
            } else {
                // array[i] == array[end]
                while (i + 1 < array.length && array[i + 1] == array[end]) {
                    i++;
                }
                // there is dup, end --
                end --;
            }
            i++;
        }
        return Arrays.copyOfRange(array, 0, end + 1);
    }



    /**
     * t5:
     * Given an integer array(not guaranteed to be sorted), remove adjacent repeated elements.
     * For each group of elements with the same value keep at most two of them. Do this in-place,
     * using the left side of the original array and maintain the relative order of the elements of the array.
     * Return the final array.
     *
     * Assumptions
     *
     * The given array is not null
     * Examples
     *
     * {1, 2, 2, 3, 3, 3} --> {1, 2, 2, 3, 3}
     *
     * {2, 1, 2, 2, 2, 3} --> {2, 1, 2, 2, 3}
     *
     */

    public static void test5() {
        int[] array = {2, 1, 2, 2, 2, 3};
        int[] res = t5_dedup(array);
        Debug.printArray(res);
    }
    public static int[] t5_dedup(int[] array) {
        if (array == null || array.length <= 2) {
            return array;
        }
        int s = 0, f = 1;
        int counter = 1;
        while (f < array.length) {
            if (array[f] == array[s]) {
                counter++;
                if (counter <= 2) {
                    array[++s] = array[f];
                }
                f++;
            } else {
                array[++s] = array[f];
                f++;
                // reset counter
                counter = 1;
            }
        }
        return Arrays.copyOfRange(array, 0, s + 1);
    }

    /**
     * t6
     * Given an array with both positive and negative numbers in random order.
     * Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.
     *
     * If there are more positive/negative numbers, put them at the end of the array.
     * The ordering of positive/negative numbers does not matter.
     *
     * Assumptions:
     *
     * The given array is not null.
     * There is no 0 in the array.
     * Examples:
     *
     * {1, 2, 3, 4, 5, -1, -1, -1} --> {1, -1, 2, -1, 3, -1, 4, 5}  (The ordering of positive/negative numbers do not matter)
     *
     */

    public static void test6() {
        int[] array = {-1,-1,-1,-1,1,-1,1,-1,-1};
        t6_shuffle_positive_negative(array);
        Debug.printArray(array);
    }
    public static int[] t6_shuffle_positive_negative(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // partition, put negative before positive
        int i = 0, j = array.length - 1;
        while(i <= j) {
            if (array[i] < 0) {
                i++;
            } else if (array[j] > 0) {
                j--;
            } else {
                Utilities.swap(array, i, j);
                i++;
                j--;
            }
        }
        // after partition, i is positive index.
        int pos = i, neg = 0;
        // do shuffle
        // negative += 2, positive += 1
        while (neg < array.length && pos < array.length && neg < pos && array[neg] < 0) {
            Utilities.swap(array, neg, pos);
            neg += 2;
            pos += 1;
        }
        return array;
    }
}
