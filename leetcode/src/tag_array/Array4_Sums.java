package tag_array;

import ds.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Array4_Sums {
    public static void main(String[] args) {
        test1_2();
//        test1_3();
//        test1_4();
//        test1_5();
//        test2_1();
//        test2_2();
//        test2_3();
//        test3_4();
//        test7();

//        test8();
        test9();
    }

    /**
     * Sums
     * 1.1 2Sum
     * 1.2 2Sum all pair(return indices)
     * 1.3 2Sum all pair(return all pairs)
     * 1.4 2Sum closest
     * 1.5 2Sum smaller
     * 2.1 3Sum
     * 2.2 3Sum smaller
     * 2.3 3Sum closest
     * 3   4Sum
     * 4   2Sum in 2 arrays
     * 5   3Sum in 3 arrays
     * 6   valid triangle
     * 7   2 Difference In Sorted Array
     * 8   Number Of Pairs Diff To Target
     * 9   Different Elements In Two Sorted Arrays
     * 10  Minimum Three Array Distance
     */

    /**
     * t1_1
     * 2Sum, return exist or NOT
     * time: O(n)
     * space: O(n)
     */
    public static boolean t1_1_2Sum(int[] array, int target) {
        // hasMap
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i: array) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int k = entry.getKey();
            if (k == target - k) {
                if (entry.getValue() > 1) {
                    return true;
                }
            } else {
                if (map.containsKey(target - k)) {
                    return true;
                }
            }
        }

        return false;
    }




    /**
     * t1_2 2Sum return all pair indices
     */
    public static void test1_2() {
        int[] array = {1, 2, 2, 2,4};
        int target = 4;
        List<List<Integer>> result = t1_2_2SumAllPairsIndices(array, target);
        System.out.println(result);
    }
    public static List<List<Integer>> t1_2_2SumAllPairsIndices(int[] array, int target) {
        // use map to store all indices of element
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int cur = array[i];
            if (!map.containsKey(cur)) {
                map.put(cur, new ArrayList<>());
            }
            map.get(cur).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            int elem1 = entry.getKey();
            int elem2 = target - elem1;
            if (elem1 == elem2) {
                List<Integer> list = entry.getValue();
                if (list != null && list.size() >= 2) {
                    // add from one line
                    for (int i = 0; i < list.size() - 1; i++) {
                        for (int j = i + 1; j < list.size(); j++) {
                            List<Integer> l = new ArrayList<>();
                            l.add(list.get(i));
                            l.add(list.get(j));
                            result.add(l);
                        }
                    }
                }
                map.put(elem1, null);
            } else {
                if (map.containsKey(elem2)) {
                    List<Integer> l1 = map.get(elem1);
                    List<Integer> l2 = map.get(elem2);
                    if (l1 != null && l2 != null) {
                        for (Integer i: l1) {
                            for (Integer j: l2) {
                                List<Integer> l = new ArrayList<>();
                                l.add(i);
                                l.add(j);
                                result.add(l);
                            }
                        }
                        map.put(elem1, null);
                        map.put(elem2, null);
                    }
                }
            }
        }
        return result;
    }


    /**
     * 2 sum, return all pairs, no dup
     * time: O(n * log n)
     * Space: O(1)
     */
    public static void test1_3() {
        int[] array = {2, 1, 3, 2, 3, 2, 5};
        int target = 6;
        List<List<Integer>> res = t1_3_2Sum_allPairs_no_dup(array, target);
        System.out.println(res);
    }
    public static List<List<Integer>> t1_3_2Sum_allPairs_no_dup(int[] array, int target) {
        Arrays.sort(array);
        List<List<Integer>> result = new ArrayList<>();
        int i = 0, j = array.length - 1;
        while (i < j) {
            int sum = array[i] + array[j];
            if (sum == target) {
                List<Integer> l = new ArrayList<>();
                l.add(array[i]);
                l.add(array[j]);
                result.add(l);
                i++;
                j--;
                // array[i] is the same with array[i - 1]
                while (i < j && i < array.length && array[i] == array[i - 1]) {
                    i++;
                }
                // array[j] is the same with array[j + 1]
                while (i < j && j >= 0 && array[j] == array[j + 1]) {
                    j--;
                }
            } else if (sum < target) {
                i++;

            } else {
                j --;
            }
        }
        return result;
    }



    /**
     * t1_4 2Sum closest, return the closest pair.
     */
    public static void test1_4() {
        int[] array = {1, 4, 7, 13};
        int target = 7;
        List<Integer> res = t1_4_2SumClosest(array, target);
        System.out.println("res: " + res);
    }

    public static List<Integer> t1_4_2SumClosest(int[] array, int target) {
        Arrays.sort(array);
        int[] res = new int[]{-1, -1};
        int diff = Integer.MAX_VALUE;
        int i = 0, j = array.length - 1;
        while (i < j) {
            int sum = array[i] + array[j];
            int curDiff = Math.abs(target - sum);
            if (curDiff < diff) {
                diff = curDiff;
                res[0] = array[i];
                res[1] = array[j];
            }
            if (curDiff == 0) {
                break;
            } else if (sum < target) {
                i++;

            } else {
                j --;
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(res[0]);
        result.add(res[1]);
        return result;
    }

    /**
     * 1.5
     * 2 Sum smaller just return number of pairs
     * 1> sort
     * 2> binary search, find largest smaller element of target - array[i]
     * 3> compose the result
     */
    public static void test1_5() {
        int[] array = {1, 2, 2, 4, 7};
        int target = 7;
        int rev = t1_5_2SumSmaller(array, target);
        System.out.println(">>>>> rev = " + rev);
    }
    public static int t1_5_2SumSmaller(int[] array, int target) {
        Arrays.sort(array);
        int counter = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int cur = array[i];
            int largestSmallerIndex = t1_5_largestSmallerIndex(array, i, array.length - 1, target - cur);
            if (largestSmallerIndex != -1) {
                counter += largestSmallerIndex - i;
            }
        }
        return counter;
    }

    public static int t1_5_largestSmallerIndex(int[] array, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (array[mid] == target) {
                end = mid;
            } else if (array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // check array[end] and array[start]
        if (array[end] < target) {
            return end;
        } else if (array[start] < target) {
            return start;
        } else {
            return -1;
        }
    }

    /**
     * 2.1
     * 3Sum, return all non-duplicate triples
     * order doesn't matter
     *
     * Examples
     *
     * A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
     */
    public static void test2_1() {
        int[] array = {1,1,1,1,1,1,1,1,1,1,1};
        int target = 3;
        List<List<Integer>> result = t2_1_3Sum(array, target);
        System.out.println(result);
    }
    public static List<List<Integer>> t2_1_3Sum(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (array == null || array.length < 3) {
            return result;
        }
        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {
            if (i > 0 && array[i] == array[i - 1])  // skip the duplicate array[i]
                continue;

            int diff = target - array[i];
            int j = i + 1, k = array.length - 1;
            // reduce to 2Sum
            while (i < array.length &&j < k && j < array.length && k >= 0) {
                int elem2 = array[j];
                int elem3 = array[k];
                if (elem2 + elem3 == diff) {
                    List<Integer> l = new ArrayList<>();
                    l.add(array[i]);
                    l.add(elem2);
                    l.add(elem3);
                    result.add(l);
                    j++;
                    k--;
                    while (j < k && array[j] == array[j - 1]) {
                        j++;
                    }
                    while (j < k && array[k] == array[k + 1]) {
                        k--;
                    }
                } else if (elem2 + elem3 < diff) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    /**
     * 2.2
     * 3Sum smaller, return the num of triples whose sum is smaller than target

     * nums[i] + nums[j] + nums[k] < target.
     *
     * For example,
     *
     * nums = [-2, 0, 1, 3], and target = 2.
     *
     * Return 2. Because there are two triplets which sums are less than 2:
     * [-2, 0, 1]
     * [-2, 0, 3]
     */

    public static void test2_2() {
        int[] array = {-2, 0, 1, 3};
        int target = 2;
        int rev = t2_2_3Sum_Smaller(array, target);
        System.out.println("rev = " + rev);
    }
    public static int t2_2_3Sum_Smaller(int[] array, int target) {
        if (array == null || array.length < 3) {
            return 0;
        }
        int result = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; i++) {
           int j = i + 1, k = array.length - 1;
           int diff = target - array[i];
           while (j < k) {
               int elem2 = array[j];
               int elem3 = array[k];
               if (elem2 + elem3 < diff) {
                   result += k - j;
                   j++;
               } else {
                   k--;
               }
           }
        }

        return result;
    }

    /**
     * 2.3 3Sum closest
     *
     * return the minDiff of array[i] + array[j] + array[k] and target.
     *
     * Example
     *
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     *
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2) and the difference is 1.
     *
     * Time: O(n*n)
     */
    public static void test2_3() {
        int[] array = {-1, 2, 1, -4};
        int target = 1;
        int rev = t2_3_3SumClosest(array, target);
        System.out.println("rev  = " + rev);
    }
    public static int t2_3_3SumClosest(int[] array, int target) {
        Arrays.sort(array);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < array.length - 2; i++ ) {
            if (i > 0 && array[i] == array[i - 1])
                continue;
            int j = i + 1, k = array.length - 1;
            int elem1 = array[i];
            while (j < k) {
                int elem2 = array[j];
                int elem3 = array[k];
                if (elem1 + elem2 + elem3 == target) {
                    return 0;
                } else if (elem1 + elem2 + elem3 < target) {
                    j++;
                } else if (elem1 + elem2 + elem3 > target) {
                    k--;
                }
                minDiff = Math.min(minDiff, Math.abs(target - elem1 - elem2 - elem3));
            }

        }
        return minDiff;

    }

    /**
     * task3
     * 4Sum
     * return true/false
     * whether there is a set that array[i] + array[j] + array[m] + array[n] == target
     *
     * A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
     *
     * A = {1, 2, 2, 3, 4}, target = 12, return false
     *
     * Time: O(n * n)
     * Space: O(n)
     */
    public static void test3_4() {
        int[] array = {1,2,2,3,4};
        int target = 9;
        boolean rev = task3_4Sum(array, target);
        System.out.println("rev = " + rev);
    }
    public static boolean task3_4Sum(int[] array, int target) {
        if (array == null || array.length < 4) {
            return false;
        }
        Arrays.sort(array);
        Map<Integer, Pair> map = new HashMap<>();
        for (int j = 1; j < array.length; j++) {
            for (int i = 0; i < j; i++) {
                int sum = array[i] + array[j];
                /* there is no overlap */
                if (map.containsKey(target - sum) && map.get(target - sum).right < i) {
                    return true;
                }

                if (!map.containsKey(sum)) {
                    map.put(sum, new Pair(i, j));
                }
            }
        }

        return false;
    }
    public static class Pair {
        int left;
        int right;
        public Pair(int _l, int _r) {
            this.left = _l;
            this.right = _r;
        }
    }


    /**
     * t3_1
     * get all list of 4 sums
     * A = {1,1,2, 2, 3, 4,5}, target = 9, return {1,1,2,5} {1,1,3,4} {1,2,2,4}
     * Time: O(n^3)
     */
    public static void test3_1() {
        int[] array = {1,1,2,2,3,4,5};
        int target = 9;
        List<List<Integer>> result = t3_1_fourSum_All(array, target);
        System.out.println("--------------");
        System.out.println(result);
    }

    public static List<List<Integer>> t3_1_fourSum_All(int[] array, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> resultSet = new HashSet<>();
        Map<Integer, ArrayList<Pair>> map = new HashMap<>(); // <Sum, List<(index pair)>>
        List<Integer> sumList = new ArrayList<>();

        // fill in the map and sumList
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = i + 1; j < array.length; j++) {
                int curSum = array[i] + array[j];
                if (!map.containsKey(curSum)) {
                    map.put(curSum, new ArrayList<>());
                    sumList.add(curSum);
                }
                map.get(curSum).add(new Pair(i, j));
            }
        }

		/* For Debug
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for (Entry<Integer, ArrayList<Pair>> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
		*/

        Set<List<Integer>> indexSet = new HashSet<>();
        for (int i = 0; i < sumList.size(); i++) {
            int curSum = sumList.get(i);
            int targetSum = target - curSum;
            if (!map.containsKey(targetSum)) {
                continue;
            }
            if (curSum == targetSum) {
                List<Pair> curPairList = map.get(curSum);
                t3_1_getNoOverlapPairs(curPairList, curPairList, indexSet);
                map.remove(curSum);
            } else {
                // curSum != targetSum
                if (map.containsKey(targetSum)) {
                    List<Pair> curPairList = map.get(curSum);
                    List<Pair> targetPairList = map.get(targetSum);
                    t3_1_getNoOverlapPairs(curPairList, targetPairList, indexSet);

                    map.remove(curSum);
                    map.remove(targetSum);
                }
            }
        }
        // get result set
        t3_1_getResult(indexSet, resultSet, array);
        // convert result set to result list
        result.addAll(resultSet);
        return result;
    }


    public static void t3_1_getNoOverlapPairs(List<Pair> list1, List<Pair> list2, Set<List<Integer>> indexSet) {
        for (Pair p1 : list1) {
            int p1Left = p1.left;
            int p1Right = p1.right;
            for (Pair p2 : list2) {
                int p2Left = p2.left;
                int p2Right = p2.right;

                if (p2Left != p1Left && p2Right != p1Left && p2Left != p1Right && p2Right != p1Right) {
                    List<Integer> oneRes = Arrays.asList(p1Left, p1Right, p2Left, p2Right);
                    Collections.sort(oneRes);
                    indexSet.add(oneRes);
                }
            }
        }
    }

    public static void t3_1_getResult(Set<List<Integer>> indices, Set<List<Integer>> result, int[] array) {
        for (List<Integer> indexList : indices) {
            List<Integer> elemList = new ArrayList<Integer>();
            for(Integer idx : indexList) {
                elemList.add(array[idx]);
            }
            result.add(elemList);
        }
    }

    /**
     * t3_2
     * k Sum
     * use recursion.
     * https://www.sigmainfy.com/blog/k-sum-problem-analysis-recursive-implementation-lower-bound.html
     */
    public static void test3_2() {
        int[] num = {1,1,2, 2, 3, 4,5};
        int target = 9;
        int K = 4;

        List<List<Integer>> result = t3_2_KSum(num, K, target);
        System.out.println(result);
    }

    public static List<List<Integer>> t3_2_KSum(int[] num, int K, int target) {
        Arrays.sort(num);
        return t3_2_KSum(num, K, target, 0);
    }


    public static List<List<Integer>> t3_2_KSum(int[] num, int K, int target, int startIdx) {
        List<List<Integer>> results = new ArrayList<>();

        if (K == 2) { // base case
            int i = startIdx, j = num.length - 1;
            while (i < j) {
                List<Integer> twoSumList = new ArrayList<Integer>();
                if (i > startIdx && num[i] == num[i - 1]) {
                    i++;
                    continue;
                }
                int sum = num[i] + num[j];
                if (sum == target) {
                    twoSumList.add(num[i]);
                    twoSumList.add(num[j]);
                    i++;
                    j--;
                    results.add(new ArrayList<>(twoSumList));
                }
                else if (sum > target) {
                    j--;
                }
                else {
                    i++;
                }
            }
            return results;
        }
        // K > 2
        for (int i = startIdx; i < num.length; i++) {
            if (i > startIdx && num[i] == num[i - 1]) {
                continue;
            }
            // get k-1 sum
            List<List<Integer>> K1Sum = t3_2_KSum(num, K - 1, target - num[i], i + 1);
            // add num[i] to the k-1 sum,
            // we will get k sum
            for (List<Integer> list : K1Sum) {
                list.add(num[i]);
                results.add(new ArrayList<Integer>(list));
            }
        }
        return results;
    }


    /**
     * 4
     * 2Sum in 2 arrays
     *
     * Given two arrays A and B, determine whether or not there exists a pair of elements, one drawn from each array,
     * that sums to the given target number.
     *
     * Assumptions
     *
     * The two given arrays are not null and have length of at least 1
     * Examples
     *
     * A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)
     *
     * A = {1, 3, 5}, B = {2, 8}, target = 6, return false
     */
    // use set
    // Time: O(n) Space: O(n)
    public static boolean t4_existSum(int[] a, int[] b, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer i: a) {
            set.add(i);
        }
        for (Integer j: b) {
            if (set.contains(target - j)) {
                return true;
            }
        }
        return false;
    }

    // Time: O(n * log n)
    public static boolean t4_existSum2(int[] a, int[] b, int target) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = b.length - 1;
        while(i < a.length && j >= 0) {
            int sum = a[i] + b[j];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                i ++;
            } else {
                j--;
            }
        }
        return false;
    }

    /**
     *
     * task5
     * Given three arrays, determine if a set can be made by picking one element from each array that sums to the given target number.
     *
     * Assumptions
     *
     * The three given arrays are not null and have length of at least 1
     * Examples
     *
     * A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
     */
    public static boolean t5_exist(int[] a, int[] b, int[] c, int target) {
        // using set
        Set<Integer> set = new HashSet<>();
        for (Integer i: a) {
            set.add(i);
        }
        for (Integer j: b) {
            for (Integer k: c) {
                if (set.contains(target - j - k)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * task6
     * Given an unsorted array of positive integers.
     * Find the number of triangles that can be formed with three different array elements as three sides of triangles.
     *
     * Assumptions:
     *
     * The given array is not null and has length of at least 3.
     * Exmaples:
     *
     * array = {4, 6, 3, 7}, the output should be 3.
     * There are three triangles possible {3, 4, 6}, {4, 6, 7} and {3, 6, 7}. Note that {3, 4, 7} is impossible.
     *
     * ref: https://www.geeksforgeeks.org/find-number-of-triangles-possible/
     */

    public static int t6_numOfTriangles(int[] array) {
        if (array == null || array.length < 3) {
            return 0;
        }
        Arrays.sort(array);
        int counter = 0;
        for (int i = 0; i < array.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < array.length - 1; j++) {
                // after the loop, the k point to the smallest element which elem1 + elem2 <= elem3
                // k - 1 points to the largest element which elem1 + elem2 > elem3
                // k only traverse the array once per i.
                // so the total time complexity is O(n * n)
                while (k < array.length && array[i] + array[j] > array[k]) {
                    k++;
                }
                counter += k - 1 - j;
            }
        }
        return counter;
    }

    /**
     * task7
     * Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).
     *
     * If there does not exist such pair, return a zero length array.
     *
     * Assumptions:
     *
     * The given array is not null and has length of at least 2.
     * Examples:
     *
     * A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
     * A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == 2.
     */

    public static void test7() {
        int[] array = {1,2,2,3,4};
        int target = -3;

        int[] res = task7_twoDiff(array, target);
        Debug.printArray(res);
    }
    public static int[] task7_twoDiff(int[] array, int target) {
        int i = 0, j = 1;
        int target2 = Math.abs(target);
        while (i < array.length && j < array.length) {
            if (i != j && array[j] - array[i] == target2) {
                if (target == target2) {
                    return new int[]{Math.min(i, j), Math.max(i, j)};
                } else {
                    return new int[]{Math.max(i, j), Math.min(i, j)};
                }


            } else if (array[j] - array[i] < target2) {
                j++; // increase j
            } else {
                i++;
            }
        }

        return new int[]{};
    }

    /**
     * t8
     */
    public static void test8() {
        int[] array = {3,3, 1, 2, 1};
        int target = 2;
        int rev = task8_numTwoDiff(array, target);
        System.out.println("rev = " + rev);
    }

    public static int task8_numTwoDiff(int[] array, int target) {
        Arrays.sort(array);
        Debug.printArray(array);
        int i = 0, j = 1;
        int counter = 0;
        while (i < array.length && j < array.length) {
            if (i != j && array[j] - array[i] == target) {
                counter++;
                if (j + 1 < array.length) {
                    if (i + 1 < array.length && array[j + 1] - array[j] > array[i + 1] - array[i]) {
                        i++;
                    } else {
                        j++;
                    }
                } else {
                    i++;
                }


            } else if (array[j] - array[i] < target) {
                j++; // increase j
            } else {
                i++;
            }
        }

        return counter;
    }

    /**
     * t9
     * Given two sorted arrays a and b containing only integers, return two list of elements:
     * the elements only in a but not in b, and the elements only in b but not in a.
     *
     * Do it in one pass.
     *
     * Assumptions:
     *
     * The two given arrays are not null.
     * Examples:
     *
     * a = {1, 2, 2, 3, 4, 5}
     *
     * b = {2, 2, 2, 4, 4, 6}
     *
     * The returned two lists are:
     *
     * [
     *
     *   [1, 3, 5],
     *
     *   [2, 4, 6]  // there are two 2s in a, so there is one 2 in b not in a
     *
     * ]
     */

    public static void test9() {
        int[] a = {1, 2, 2, 3, 4, 5};
        int[] b = {2, 2, 2, 4, 4, 6};
        int[][] res = task9_diff(a, b);
    }
    public static int[][] task9_diff(int[] a, int[] b) {
        // Write your solution here
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();

        for (Integer i: a) {
            aMap.put(i, aMap.getOrDefault(i, 0) + 1);
        }

        for (Integer j: b) {
            bMap.put(j, bMap.getOrDefault(j, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: aMap.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            if (bMap.containsKey(k)) {
                int vb = bMap.get(k);
                aMap.put(k, Math.max(v - vb, 0));
                bMap.put(k, Math.max(vb - v, 0));
            }
        }

        List<Integer> resA = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: aMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            while (value > 0) {
                resA.add(key);
                value--;
            }
        }

        List<Integer> resB = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: bMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            while (value > 0) {
                resB.add(key);
                value--;
            }
        }

        System.out.println(resA);
        System.out.println(resB);

        return null;
    }

}

