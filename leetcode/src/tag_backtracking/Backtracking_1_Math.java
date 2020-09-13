package tag_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Backtracking_1_Math {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		 test1_1();
//		 test1_2();
//		test3_l60();
//		test5_subset1();
//		test6_subset2();
//		 test7();
//		 test8();
//		 test9();
//		 test10();
        test11();
//		 test12();
//		 test13();
//		 test14();
    }


    /*
     * math related:
     * 1 permutations
     * 2 permutations2
     *
     * 3 permutation sequence
     *
     * 5 subset
     *
     * 6 subset2
     *
     * 7 combination
     *
     * 8 combination sum 1
     * 9 combination sum 2
     * 10 combination sum 3
     * 11 factor combinations
     * 12 Gray Code
     * 13 Count number with unique digits
     * 14 Binary Watch 15 Beautiful arrangement
     */

    /**
     * permutation without duplication
     *
     * a b c
     *
     *
     */
    public static void test1_1() {
        String str = "abc";
        List<String> result = task1_1_permutations(str);
        System.out.println(result);
    }

    public static List<String> task1_1_permutations(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        if (set.length() == 0) {
            result.add("");
            return result;
        }
        char[] input = set.toCharArray();
        task1_1_helper(input, 0, result);
        return result;
    }

    /*
     * put the elem in the index
     */
    public static void task1_1_helper(char[] set, int index, List<String> result) {
        if (index == set.length) {
            String str = new String(set);
            System.out.println("%%%%%% " + str);
            result.add(str);
            return;
        }
        // index
        for (int i = index; i < set.length; i++) {
            System.out.println("index = " + index + " >>>> => " + Arrays.toString(set));
            swap(set, index, i);
            task1_1_helper(set, index + 1, result);
            swap(set, index, i);
        }
    }

    public static void swap(char[] set, int x, int y) {
        char temp = set[x];
        set[x] = set[y];
        set[y] = temp;
    }

    public static void test1_2() {
        String set = "abc";
        List<String> result = task1_2_permutationsWithOrder(set);
        System.out.println(result);
    }

    public static List<String> task1_2_permutationsWithOrder(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        if (set.length() == 0) {
            result.add("");
            return result;
        }
        char[] arrarSet = set.toCharArray();
        Arrays.sort(arrarSet);


        // record with index has been used
        boolean[] used = new boolean[set.length()];
        StringBuilder stb = new StringBuilder();
        task1_2_helpWithOrder(arrarSet, used, stb, result);
        return result;

    }

    public static void task1_2_helpWithOrder(char[] array, boolean[] used, StringBuilder stb, List<String> list) {
        if (stb.length() == array.length) {
            list.add(stb.toString());
            return;
        }

        // when picking the next char, always according to the char
        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stb.append(array[i]);
                System.out.println(stb.toString());
                task1_2_helpWithOrder(array, used, stb, list);

                used[i] = false;
                stb.deleteCharAt(stb.length() - 1);
            }
        }
    }

    /*
     * task2 permutation2 with duplicate
     */
    public static void test1_3() {
        String str = "abb";
        List<String> result = task2_permutationsII(str);
        System.out.println(result);
    }

    public static List<String> task2_permutationsII(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        char[] input = set.toCharArray();
        task10_helperII(input, 0, result);
        System.out.println(result);
        return result;
    }

    public static void task10_helperII(char[] set, int index, List<String> result) {
        if (index == set.length) {
            String str = new String(set);
            result.add(str);
            return;
        }

        HashSet<Character> used = new HashSet<Character>();
        // 每一层放一个 hashset, 用来指示： 在某这个位置，放没放过某一个char

        for (int i = index; i < set.length; i++) {
            if (!used.contains(set[i])) {
                // 在index 上没有放过set[i]
                used.add(set[i]);
                swap(set, index, i);
                task10_helperII(set, index + 1, result);
                swap(set, index, i);
            }
        }
    }

    public static void test2_1() {
        String set = "abb";
        List<String> result = task2_permutationsWithOrder_with_Dup(set);
        System.out.println(result);
    }

    public static List<String> task2_permutationsWithOrder_with_Dup(String set) {
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }
        if (set.length() == 0) {
            result.add("");
            return result;
        }
        char[] arrarSet = set.toCharArray();
        Arrays.sort(arrarSet);

        // record with index has been used
        boolean[] used = new boolean[set.length()];
        StringBuilder stb = new StringBuilder();
        task2_1_helpWithOrder_with_Dup(arrarSet, used, stb, result);
        return result;
    }

    public static void task2_1_helpWithOrder_with_Dup(char[] array, boolean[] used, StringBuilder stb,
                                                      List<String> list) {
        if (stb.length() == array.length) {
            list.add(stb.toString());
            return;
        }

        // when picking the next char, always according to the char
        for (int i = 0; i < array.length; i++) {
            if (used[i] || (i > 0 && array[i] == array[i - 1] && used[i - 1])) {
                continue;
            }

            used[i] = true;
            stb.append(array[i]);

            task2_1_helpWithOrder_with_Dup(array, used, stb, list);

            stb.deleteCharAt(stb.length() - 1);
            used[i] = false;
        }
    }

    /*
     * 60. Permutation Sequence The set [1,2,3,...,n] contains a total of n! unique
     * permutations. By listing and labeling all of the permutations in order, we
     * get the following sequence for n = 3: "123" "132" "213" "231" "312" "321"
     * Given n and k, return the kth permutation sequence. Note: Given n will be
     * between 1 and 9 inclusive. Given k will be between 1 and n! inclusive.
     */

    public static void test3_l60() {
        int n = 4;
        int k = 14;
        String rev = t3_l60_permutation_sequence(n, k);
        System.out.println("===>>> rev: " + rev);
    }

    public static String t3_l60_permutation_sequence(int n, int k) {

        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        System.out.println(">>>>" + Arrays.toString(factorial));
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        System.out.println("!!!! nums: " + nums);
        k--;
        StringBuilder stb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            System.out.println("index = " + index);
            stb.append(nums.get(index));
            nums.remove(index);
            // update k
            k = k - index * factorial[n - i];
        }

        // System.out.println(Arrays.toString(factorial));
        return stb.toString();
    }


    /**
     * subset1
     *
     */

    public static void test5_subset1() {
        char[] arr = {'a', 'b', 'c'};
//		List<String> res = task5_subset1(arr);
//		System.out.println(res);
        System.out.println("=======================");
        List<String> res2 = task5_subset2(arr);
        System.out.println(res2);
    }

    public static List<String> task5_subset1(char[] arr) {
        List<String> result = new ArrayList<>();
        task5_helper1(arr, result, new StringBuilder(), 0);
        return result;
    }

    public static void task5_helper1(char[] arr, List<String> result, StringBuilder stb, int index) {
        if (index == arr.length) {
            result.add(stb.toString());
            return ;
        }

        // add arr[index] into stb
        stb.append(arr[index]);
        task5_helper1(arr, result, stb, index + 1);
        stb.deleteCharAt(stb.length() - 1);

        // not add arr[index] into stb
        task5_helper1(arr, result, stb, index + 1);
    }

    public static List<String> task5_subset2(char[] arr) {
        List<String> result = new ArrayList<>();
        task5_helper2(arr, result, new StringBuilder(), 0);
        return result;
    }

    public static void task5_helper2(char[] arr, List<String> result, StringBuilder stb, int index) {
        result.add(stb.toString());
        // choose what is the index in the original set to pick.
        // maintain the ascending order of the picked indices.

        for (int i = index; i < arr.length; i++ ) {
            stb.append(arr[i]);
            System.out.println(">>>>>>> " + stb.toString());
            task5_helper2(arr, result, stb, i + 1);
            stb.deleteCharAt(stb.length() - 1);
            System.out.println("< " + stb.toString());
        }
    }


    /**
     * subset2: with duplicate
     */

    public static void test6_subset2() {
        char[] arr = {'a', 'a', 'b', 'c'};
        List<String> res = task6_subset2_dup(arr);
        System.out.println(res);
    }

    public static List<String> task6_subset2_dup(char[] arr) {
        StringBuilder stb = new StringBuilder();
        List<String> result = new ArrayList<>();
        task6_helper2_dup(arr, result, stb, 0);
        return result;
    }

    public static void task6_helper2_dup(char[] arr, List<String> result, StringBuilder stb, int index) {
        result.add(stb.toString());
        for (int i = index; i < arr.length; i++) {
            if (i != index && arr[i] == arr[i - 1]) {
                continue;
            }
            stb.append(arr[i]);
            task6_helper2_dup(arr, result, stb, i + 1);
            stb.deleteCharAt(stb.length() - 1);
        }
    }


    /*
     * 7 combination Given two integers n and k, return all possible combinations of
     * k numbers out of 1 ... n. If n = 4 and k = 2, a solution is: [ [2,4], [3,4],
     * [2,3], [1,2], [1,3], [1,4] ]
     *
     */
    public static void test7() {
        int n = 4, k = 2;
        List<List<Integer>> result3 =  task7_l77_opt2(n, k);
        System.out.println(result3);
    }

    // more easier to understand
    public static List<List<Integer>> task7_l77_opt2(int n, int k) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        task7_l77_opt2_helper(n, k, 1, list, result);
        return result;
    }

    public static void task7_l77_opt2_helper(int n, int k, int start, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return ;
        }

        for (int i = start; i <=n; i++) {
            list.add(i);
            task7_l77_opt2_helper(n, k, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    /*
     *
     * 39. Combination Sum Given a set of candidate numbers (C) (without duplicates)
     * and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     *
     * The same repeated number may be chosen from Cunlimited number of times.
     *
     * one element can use multiple times
     * similar to coin problem.
     *
     * For example, given candidate set
     * [2, 3, 6, 7] and target 7,
     * A solution set is: [ [7], [2, 2, 3] ]
     */

    public static void test8() {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        List<List<Integer>> result2 = t8_l39_combinationSum(candidates, target);
        System.out.println(result2);
    }

    public static List<List<Integer>> t8_l39_combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        t8_l39_combinationSum_helper(candidates, target, 0, list, result);
        return result;
    }

    public static void t8_l39_combinationSum_helper(int[] candidates,
                                                    int target,
                                                    int index,
                                                    List<Integer> list,
                                                    List<List<Integer>> result) {
        if (target < 0) {
            return ;
        }
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return ;
        }

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            list.add(candidates[i]);
            t8_l39_combinationSum_helper(candidates, target - candidates[i], i, list, result);
            list.remove(list.size() - 1);
        }
    }

    /*
     * 40. Combination Sum II
     *
     *  Given a collection of candidate numbers (C) and a
     * target number (T), find all unique combinations in C where the candidate
     * numbers sums to T.
     *
     * Each number in C may only be used once in the combination.
     *
     * 只能用一次
     *
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, [ [1,
     * 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
     */

    public static void test9() {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        List<List<Integer>> result = t9_l40_combination2(candidates, target);


        System.out.println(result);
    }

    public static List<List<Integer>> t9_l40_combination2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        // sort the array
        Arrays.sort(candidates);
        System.out.println("==>>>" + Arrays.toString(candidates));
        t9_l40_helper(candidates, target, 0, list, result);
        return result;
    }


    public static void t9_l40_helper(
            int[] candidates,
            int target,
            int index,
            List<Integer> list,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || index >= candidates.length) {
            return;
        }

        // get an element in index and go deeper
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target < candidates[i]) {
                return;
            }
            list.add(candidates[i]);
            t9_l40_helper(candidates, target - candidates[i], i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    /*
     * 216 combination 3
     *
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each
     * combination should be a unique set of numbers. Input: k = 3, n = 7
     * Output:[[1,2,4]]
     *
     * Input: k = 3, n = 9 Output: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public static void test10() {
        int n = 9, k = 3;
        List<List<Integer>> result = t10_l216_combinationSum3(k, n);
        System.out.println(result);
    }

    public static List<List<Integer>> t10_l216_combinationSum3(int k, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        t10_l216_helper(k, target, list, result, 1);
        return result;
    }

    public static void t10_l216_helper(
            int remainingCount,
            int target,
            List<Integer> list, // temporary result
            List<List<Integer>> result,
            int start) {
        if (remainingCount == 0 && target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            list.add(i);
            t10_l216_helper(remainingCount - 1, target - i, list, result, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /*
     * 11 factor combinations Numbers can be regarded as product of its factors. For
     * example, 8 = 2 x 2 x 2; = 2 x 4.
     *
     */
    public static int CountDebug = 0;
    public static void test11() {
        int n = 16;
        List<List<Integer>> result = t11_l254_getFactors(n);
        System.out.println(result);
        System.out.println(CountDebug);
        System.out.println("==============================");
        CountDebug = 0;
        List<List<Integer>> res2 = task11_l254_getFactors_opt(n);
        System.out.println(res2);
        System.out.println(CountDebug);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        CountDebug = 0;
        List<List<Integer>> res3 = task11_getFactors_opt2(n);
        System.out.println(res3);
        System.out.println(CountDebug);
    }

    public static List<List<Integer>> t11_l254_getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        t11_l254_helper(n, list, result);
        // delete the last one, which is [[n]]
        System.out.println("===>>>" + result);
        result.remove(result.size() - 1);
        return result;
    }

    public static void t11_l254_helper(int target, List<Integer> list, List<List<Integer>> result) {
        CountDebug++;
        if (target == 1) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 2; i <= target; i++) {
            if (target % i == 0) {
                if (list.isEmpty()) {
                    list.add(i);
                    t11_l254_helper(target / i, list, result);
                    list.remove(list.size() - 1);
                } else {
                    // only choose i >= last elemnt in list
                    // the next task11_l254_getFactors_opt is more efficient
                    if (i >= list.get(list.size() - 1)) {
                        list.add(i);
                        t11_l254_helper(target / i, list, result);
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
    }


    public static List<List<Integer>> task11_l254_getFactors_opt(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        task11_l254_helper_opt(n, list, result, 2);
        return result;
    }

    // make sure that the next num picked up is not smaller than the previous, which is start
    public static void task11_l254_helper_opt(int target, List<Integer> list, List<List<Integer>> result, int start) {
        CountDebug++;
        if (target == 1) {
            // find a solution
            result.add(new ArrayList<>(list));
            return ;
        }

        for (int i = start; i <= target; i++) {
            if (target % i == 0) {
                list.add(i);
                task11_l254_helper_opt(target/i, list, result, i);
                list.remove(list.size() - 1);
            }
        }
    }


    // this is even more better
    public static List<List<Integer>> task11_getFactors_opt2(int n) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(n <= 3)  return ret;
        List<Integer> path = new ArrayList<>();
        task11_getFactors_opt2(2, n, path, ret);
        return ret;
    }


    private static void task11_getFactors_opt2(int start, int n, List<Integer> path, List<List<Integer>> result){
        CountDebug++;
        for(int i = start; i <= Math.sqrt(n); i++){
            if(n % i == 0 && n/i >= i){  // The previous factor is no bigger than the next
                path.add(i);
                path.add(n/i);
                System.out.println("!!!" + path);
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                task11_getFactors_opt2(i, n/i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }



    /*
     * 12 Gray Code
     *
     */
    public static void test12() {
        int n = 2;
        List<Integer> result = t12_l89_grayCode(n);
        System.out.println(result);
        System.out.println("-------");
        List<Integer> result2 = t12_l89_grayCode2(n);
        System.out.println(result2);
        System.out.println("~~~~~~~~");
        List<Integer> res3 = task12_grayCode3(n);
        System.out.println(res3);
    }

    /**
     *
     * @param n
     * @return this is NOT an efficient method
     */

    public static List<Integer> t12_l89_grayCode(int n) {
        return t12_helper(n);
    }

    public static List<Integer> t12_helper(int n) {
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(1);
            return list;
        } else {
            List<Integer> prev_list = t12_helper(n - 1);
            List<Integer> cur_list = new ArrayList<>(prev_list);
            for (int i = prev_list.size() - 1; i >= 0; i--) {
                int elem = prev_list.get(i);
                int n_elem = (1 << (n - 1)) + elem;
                cur_list.add(n_elem);
            }
            return cur_list;
        }
    }

    /*
     * This is more efficient
     */
    public static List<Integer> t12_l89_grayCode2(int n) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        if (n == 0) {
            return list;
        }
        for (int i = 1; i <= n; i++) {
            List<Integer> next = new ArrayList<Integer>(list);
            for (int j = list.size() - 1; j >= 0; j--) {
                int elem = list.get(j) + (int) (1 << (i - 1));
                next.add(elem);
            }
            list = next;
        }
        return list;
    }


    /*
     * G(i) = i^ (i/2)
     * https://leetcode.com/problems/gray-code/discuss/29881/An-accepted-three-line-solution-in-JAVA
     * this one is the fastest
     * */
    public static List<Integer> task12_grayCode3(int n) {
        List<Integer> result = new ArrayList<>();

        System.out.println(1<<n);
        for (int i = 0; i < 1 << n; i++)
            result.add(i ^ (i >> 1));
        System.out.println("!!!");
        System.out.println(result);
        return result;
    }


    /*
     * 13 Count Numbers with Unique Digits
     *
     * f(k) = count of numbers with unique digits with length == k e.g f(2) is 81.
     * from 10 .. 99, except [11, 22,33,..., 99]
     *
     * f(1) = 10
     * f(2) = 9 * 9 = 81
     * f(3) = 9 * 9 * 8 = 729 ...
     * f(k) = 9 * 9 * 8 * ...* (9 - k + 2)
     *
     */
    public static void test13() {
        int n = 3;
        int rev = t13_l357_countNumwithUniqueDigits(n);
        System.out.println("rev = " + rev);
    }

    public static int t13_l357_countNumwithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int curCount = 0;
        int result = 0;
        for (int k = 1; k <= n; k++) {
            if (k == 1) {
                curCount = 10;
            } else if (k == 2) {
                curCount = 9 * 9;
            } else {
                curCount = curCount * (9 - k + 2);
            }
            result += curCount;
        }
        return result;
    }

    /*
     * 14 binary watch
     */

    public static void test14() {
        int[] nums = { 8, 4, 2, 1 };
        int count = 3;
        List<Integer> res = t14_generateDigits(nums, count);
        System.out.println(res);

        for (Integer i : res) {
            System.out.print(i + "  :  ");
            System.out.println(Integer.toBinaryString(i));
        }
    }

    public static List<String> t14_l401_binaryWatch(int num) {
        int[] nums_arr1 = { 8, 4, 2, 1 };
        int[] nums_arr2 = { 32, 16, 8, 4, 2, 1 };
        List<String> reslut = new ArrayList<String>();
        for (int i = 0; i <= num; i++) {
            List<Integer> list1 = t14_generateDigits(nums_arr1, i);
            List<Integer> list2 = t14_generateDigits(nums_arr2, num - i);

            for (Integer n1 : list1) {
                if (n1 >= 12) {
                    continue;
                }
                for (Integer n2 : list2) {
                    if (n2 >= 60) {
                        continue;
                    }
                    reslut.add(n1 + ":" + (n2 < 10 ? "0" + n2 : n2));
                }
            }
        }
        return reslut;
    }


    public static List<Integer> t14_generateDigits(int[] nums, int count) {
        List<Integer> result = new ArrayList<Integer>();
        t14_generateDigit_helper(nums, count, 0, 0, result);
        return result;
    }

    private static void t14_generateDigit_helper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            t14_generateDigit_helper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }

    public static List<String> t14_l401_binaryWatch_2(int num) {
        List<String> reslut = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    reslut.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return reslut;
    }
}
