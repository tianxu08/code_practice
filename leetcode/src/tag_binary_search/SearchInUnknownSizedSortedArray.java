package tag_binary_search;

public class SearchInUnknownSizedSortedArray {
    /*
     * Search In Unknown Sized Sorted Array
     *
     * Given a integer dictionary A of unknown size,
     * where the numbers in the dictionary are sorted in ascending order,
     * determine if a given target integer T is in the dictionary.
     * Return the index of T in A, return -1 if T is not in A.
     *
     * Assumptions
     * dictionary A is not null
     * dictionary.get(i) will return null if index i is out of bounds
     * Examples
     * A = {1, 2, 5, 9, ......}, T = 5, return 2
     * A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
     *
     * the key idea is that when the pointer out of bound,return null rather than throw out an exception.
     *
     */
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] arr = {1,2,3,4,5,6,8,9};
        Dictionary dict = new Dictionary(arr);
        int target = 5;
        int res = search(dict, target);
        System.out.println("res =  " + res);
    }

    public static class Dictionary {
        int[] array;
        public Dictionary(int[] a) {
            this.array = a;
        }

        public Integer get(int idx) {
            // !!! if idx >= array.length, return null rather than throw out an exception
            if (array == null || idx >= array.length) {
                return null;
            }
            return array[idx];
        }
    }

    public static int search(Dictionary dict, int T) {
        if (dict == null || dict.array == null || dict.array.length == 0) {
            return -1;
        }
        int left = 0, right = 1;

        // find the possible right bound
        while (dict.get(right) != null && dict.get(right) < T) {
            left = right; // update left
            right = right * 2;
        }

        // do binary search
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null) {
                right = mid;
            } else if (dict.get(mid) > T) {
                right = mid;
            } else if (dict.get(mid) < T) {
                left = mid;
            } else {
                return mid;
            }
        }

        // check left and right
        if (dict.get(left) != null && dict.get(left) == T) {
            return left;
        } else if (dict.get(right) != null && dict.get(right) == T) {
            return right;
        }
        return -1;
    }

}
