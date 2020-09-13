package mj_fb;

import ds.TreeNode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map.Entry;

public class FB_LC {

    public static void main(String[] args) {
//        test1_301();
//        task953_test();
//        task973_test();
//        task1249_test();
//        task560_test();
//        task238_test();
//        task680_test();
//        task124_test();
//        task67_test();
//        task273_test();
//        task269_test();
//        task76_test();
//        task282_test();
//        task523_test();
        task438_test();
    }

    /**
     * 301. Remove Invalid Parentheses
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
     *
     * Note: The input string may contain letters other than the parentheses ( and ).
     * Input: "()())()"
     * Output: ["()()()", "(())()"]
     *
     * Input: "(a)())()"
     * Output: ["(a)()()", "(a())()"]
     *
     */

    public static void test1_301() {
        String s = "(a)())()";
        List<String> res = task301_removeInvalidParentheses(s);
        System.out.println(res);
    }
    public static List<String> task301_removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        int rmL = 0;
        int rmR = 0;  // remove left and remove right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                rmL++;
            if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        task301_DFS(res, s, 0, rmL, rmR, 0, new StringBuilder());
        return new ArrayList<>(res);
    }

    /**
     *
     * @param res
     * @param s
     * @param index: index
     * @param rmL: unmatched left parenthesis '(', need to be removed
     * @param rmR: unmatched right parenthesis ')', need to be removed
     * @param open: the number of open parenthesis
     * @param sb
     */
    public static void task301_DFS(Set<String> res, String s, int index, int rmL, int rmR,
                                   int open, StringBuilder sb) {
        if (index == s.length() && rmL == 0 && rmR == 0 && open == 0) {
            res.add(sb.toString());
            return;
        }

        if (index == s.length() || rmL < 0 || rmR < 0 || open < 0)
            return;

        char c = s.charAt(index);
        int len = sb.length();

        if (c == '(') {
            // not use '('
            task301_DFS(res, s, index + 1, rmL - 1, rmR, open, sb);
            // use '('
            task301_DFS(res, s, index + 1, rmL, rmR, open + 1, sb.append(c));

        } else if (c == ')') {
            // not use ')'
            task301_DFS(res, s, index + 1, rmL, rmR - 1, open, sb);
            // use ')'
            task301_DFS(res, s, index + 1, rmL, rmR, open - 1, sb.append(c));

        } else {
            task301_DFS(res, s, index + 1, rmL, rmR, open, sb.append(c));
        }

        sb.setLength(len);
    }


    /**
     * 953. Verifying an Alien Dictionary
     *
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     */

    public static void task953_test() {
        String[] words = {"hello", "leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        boolean result = task953_isAlienSorted(words, order);
        System.out.println("result = " + result);

    }

    /**
     * Space - O(1) we use an array but this will always be of size 26 no matter the length of order
     * Time O(n*m) where n is the number of words and m is the length of the smallest pair of words
     */
    public static boolean task953_isAlienSorted(String[] words, String order) {

        int[] dict = new int[26];
        for(int i=0; i<order.length(); i++)
        {
            dict[order.charAt(i) - 'a'] = i;
        }

        for(int i=1; i<words.length; i++)
        {
            String prev = words[i-1];
            String cur = words[i];

            int minLen = Math.min(prev.length(), cur.length());

            for (int j=0; j<minLen; j++)
            {
                int prevIndex = dict[prev.charAt(j) - 'a'];
                int curIndex = dict[cur.charAt(j) - 'a'];

                // if the character at 'i' for the prev word is less than the character of cur at index i,
                // we know its sorted, therefore we can break out of the loop
                if (prevIndex < curIndex)
                {
                    break;
                }

                // if both letters are the same, continue, however if we are at the last letter of the
                // smallest word and the other word still has characters to examine then return false
                // since blank character is less than any other character
                if (prevIndex == curIndex)
                {
                    if (j == minLen - 1 && prev.length() != cur.length())
                    {
                        return false;
                    }
                    continue;
                }
                else
                {
                    // return false if the prev word character > current word character
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 973. K Closest Points to Origin
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
     *
     * (Here, the distance between two points on a plane is the Euclidean distance.)
     *
     * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
     *
     * Input: points = [[1,3],[-2,2]], K = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
     */

    public static void task973_test() {
        int[][] points = {
                {1, 3},
                {-2, 2},
                {5, 1}
        };
        int K = 2;

        int[][] result = task973_kClosest(points, K);

        for (int[] line: result) {
            System.out.println(Arrays.toString(line));
        }

        System.out.println("-----------------------");
        int[][] result2 = task973_kClosest2(points, K);
        for (int[] line: result2) {
            System.out.println(Arrays.toString(line));
        }

    }

    /**
     * use priority queue
     * Time:O(N log K)  N = points.length
     */
    public static int[][] task973_kClosest(int[][] points, int K) {

//        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () {
//            public int compare(int[] a, int[] b) {
//                double diff = Math.sqrt(a[0] * a[0] + a[1] * a[1]) - Math.sqrt(b[0] * b[0] + b[1] * b[1]);
//                if (diff < 0)
//                    return 1;
//                else if (diff == 0)
//                    return 0;
//                else
//                    return -1;
//            }
//        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> {
           double diff =  Math.sqrt(a[0] * a[0] + a[1] * a[1]) - Math.sqrt(b[0] * b[0] + b[1] * b[1]);
           if (diff < 0) {
               return 1;
           } else if (diff == 0) {
               return 0;
           } else {
               return -1;
           }
        });
        for (int[] point : points)
        {
            pq.offer(point);
            if (pq.size() > K)
                pq.poll();
        }

        int[][] ans = new int[K][2];
        int i = 0;
        while (!pq.isEmpty())
            ans[i++] = pq.poll();

        return ans;
    }

    /**
     * quick sort:
     * amortized: O(n)
     */

    public static int[][] task973_kClosest2(int[][] points, int K) {
        quickSelect(points,0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public static void quickSelect(int[][] points, int i, int j, int K) {
        if (i >= j) return;
        int pivotIdx = partition(points, i, j);
        if (K == pivotIdx) {
            return ;
        } else if (K > pivotIdx) {
            quickSelect(points, pivotIdx + 1, j, K);
        } else {
            quickSelect(points, i, pivotIdx - 1, K);
        }
    }

    public static int partition(int[][] points, int l, int r) {
        int pivotDist = dist(points, r);
        int i = l, j = r - 1;
        while (i <= j) {
            if (dist(points, i) < pivotDist) {
                i++;
            } else if (dist(points, j) >= pivotDist) {
                j--;
            } else {
                // swap
                swap(points, i, j);
                i++;
                j--;
            }
        }
        swap(points, i, r);
        return i;
    }

    public static int dist(int[][] points,int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public static void swap(int[][] points, int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }


    /**
     * task1249
     * Given a string s of '(' , ')' and lowercase English characters.
     *
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
     *
     * Formally, a parentheses string is valid if and only if:
     *
     *     It is the empty string, contains only lowercase characters, or
     *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
     *     It can be written as (A), where A is a valid string.
     */

    public static void task1249_test() {
        String s = "lee(t(code)";
        String result = task1249_minRemoveToMakeValid(s);
        String result2 = task1249_minRemoveToMakeValid2(s);
        System.out.println(result);
        System.out.println(result2);
    }

    /**
     * stack: store unmatched left parentheses index
     * set: store all indicesToRemove
     *
     * Time: O(n)
     * Space: O(n)
     */
    public static String task1249_minRemoveToMakeValid(String s) {
        Set<Integer> indicesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // put the un-matched parentheses from the stack into set
        while (!stack.isEmpty()) {
            indicesToRemove.add(stack.pop());
        }

        // compose the string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indicesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    /**
     * Time: O(n)
     * Space: O(1)
     */
    public static String task1249_minRemoveToMakeValid2(String s) {
        // Parse 1: Remove all invalid ")"

        StringBuilder stb = new StringBuilder();
        int leftP = 0;
        int unMatchedLeftP = 0;

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '(') {
                leftP++;
                unMatchedLeftP++;
            } else if (ch == ')') {
                if (unMatchedLeftP == 0) {
                    // this ")" is unmatched, skip
                    continue;
                }
                unMatchedLeftP--;
            }
            stb.append(ch);
        }

        // now, there might be more "("
        int matchedP = leftP - unMatchedLeftP;
        System.out.println(matchedP);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stb.length(); i++) {
            char ch = stb.charAt(i);
            if (ch == '(') {
                if (matchedP == 0) {
                    continue;
                }
                matchedP--;
            }
            // append this char to result
            result.append(ch);
        }

        return result.toString();
    }

    /**
     * 560. Subarray Sum Equals K
     *
     * Given an array of integers and an integer k,
     * you need to find the total number of continuous subarrays whose sum equals to k.
     */

    public static void task560_test() {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int k = 3;
        int result = task560_subarraySum(nums, k);
        System.out.println("result = " + result);
    }
    public static int task560_subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // key-value: <presum ending here, counter of the presum>
        int cur_sum = 0;
        int count = 0;
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            cur_sum = cur_sum + nums[i];

            if (map.containsKey(cur_sum - k)) {
                count += map.get(cur_sum - k);
            }

            if (map.containsKey(cur_sum)) {
                map.put(cur_sum, map.get(cur_sum) + 1);
            } else {
                map.put(cur_sum, 1);
            }
        }

        return count;
    }


    /**
     * 238. Product of Array Except Self
     * Given an array nums of n integers where n > 1,
     * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     *
     * Note: Please solve it without division and in O(n).
     */

    public static void task238_test() {
        int[] nums = {1, 2, 3, 4};
        int[] result = task238_productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * Time: O(n)
     * Space: O(n)
     */
    public static int[] task238_productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = nums[0];

        for(int i = 1; i < n; i ++) {
            left[i] = left[i - 1] * nums[i];
        }

        right[n - 1 ] = nums[n - 1];
        for(int j = n - 2; j >= 0; j --) {
            right[j] = right[j + 1] * nums[j];
        }

        for(int i = 0; i < n; i ++) {
            if (i == 0) {
                result[i] = right[i + 1];
            } else if (i == n - 1) {
                result[i] = left[i - 1];
            } else {
                result[i] = left[ i - 1] * right[i + 1];
            }
        }

        return result;
    }

    /**
     * 680. Valid Palindrome II
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     * Input: "abca"
     * Output: True
     * Explanation: You could delete the character 'c'.
     */

    public static void task680_test() {
        String s = "abca";
        boolean res = task680_validPalindrome(s);
        System.out.println("res = " + res);
    }

    /**
     * Time Complexity: O(N)O(N) where NN is the length of the string.
     * Each of two checks of whether some substring is a palindrome is O(N)O(N).
     */
    public static boolean task680_validPalindrome(String s) {
        if(s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                return task680_isPalindrome(s, i + 1, j) || task680_isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }
    public static boolean task680_isPalindrome(String s, int i , int j){
        while(i < j){
            if(s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }


    /**
     * 124. Binary Tree Maximum Path Sum
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     */

    public static void task124_test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n9 = new TreeNode(9);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n9;

        int result = task124_maxPathSum(n1);
        System.out.println(result);
    }

    /**
     * Time: O(n)  n is the number of treeNode.
     * basically, we did a postOrder traverse of the tree.
     */
    public static int task124_maxPathSum(TreeNode root) {
        task124_helper(root);
        return MaxSum;
    }

    public static int MaxSum = Integer.MIN_VALUE;


    private static int task124_helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = task124_helper(node.left);
        int right = task124_helper(node.right);
        int sum = node.val;
        if (left > 0) {
            sum += left;
        }
        if (right > 0) {
            sum += right;
        }
        MaxSum = Math.max(MaxSum, sum);
        return Math.max(left, right) > 0 ?
                Math.max(left, right) + node.val : node.val;
    }


    /**
     * 67. Add Binary
     * Given two binary strings, return their sum (also a binary string).
     *
     * The input strings are both non-empty and contains only characters 1 or 0.
     *
     * Example 1:
     *
     * Input: a = "11", b = "1"
     * Output: "100"
     */

    public static void task67_test() {
        String a = "1010", b = "1011";
        String res = task67_addBinary(a, b);
        System.out.println(res);
    }
    public static String task67_addBinary(String a, String b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        StringBuilder stb = new StringBuilder();
        int i = a.length() - 1; // point to a's end
        int j = b.length() - 1; // point to b's end
        int carry = 0;
        while(true) {
            if (i < 0 && j < 0 && carry == 0) {
                break;
            }

            int iVal = (i >= 0) ? a.charAt(i) - '0' : 0;
            int jVal = (j >= 0) ? b.charAt(j) - '0': 0;

            if (iVal + jVal + carry > 1) {
                // the carry will be 1
                stb.append((char)('0' + (iVal + jVal + carry) % 2));
                carry = 1;
            } else {
                stb.append((char)('0' + iVal + jVal + carry));
                carry = 0;
            }
            j --;
            i --;

        }

        return stb.reverse().toString();
    }


    /**
     * 273. Integer to English Words
     * Convert a non-negative integer to its english words representation.
     * Given input is guaranteed to be less than 231 - 1.
     *
     * Example 1:
     *
     * Input: 123
     * Output: "One Hundred Twenty Three"
     */


    public static void task273_test() {
        int num = 12340;

        String result2 = new Task273_IntegerToEnglish().numberToWords(num);
        System.out.println("result2 = " + result2);
    }
    public static class Task273_IntegerToEnglish {

        public String one(int num) {
            switch(num) {
                case 1: return "One";
                case 2: return "Two";
                case 3: return "Three";
                case 4: return "Four";
                case 5: return "Five";
                case 6: return "Six";
                case 7: return "Seven";
                case 8: return "Eight";
                case 9: return "Nine";
            }
            return "";
        }

        public String twoLessThan20(int num) {
            switch(num) {
                case 10: return "Ten";
                case 11: return "Eleven";
                case 12: return "Twelve";
                case 13: return "Thirteen";
                case 14: return "Fourteen";
                case 15: return "Fifteen";
                case 16: return "Sixteen";
                case 17: return "Seventeen";
                case 18: return "Eighteen";
                case 19: return "Nineteen";
            }
            return "";
        }

        public String ten(int num) {
            switch(num) {
                case 2: return "Twenty";
                case 3: return "Thirty";
                case 4: return "Forty";
                case 5: return "Fifty";
                case 6: return "Sixty";
                case 7: return "Seventy";
                case 8: return "Eighty";
                case 9: return "Ninety";
            }
            return "";
        }

        public String two(int num) {
            if (num == 0)
                return "";
            else if (num < 10)
                return one(num);
            else if (num < 20)
                return twoLessThan20(num);
            else {
                int tenner = num / 10;
                int rest = num - tenner * 10;
                if (rest != 0)
                    return ten(tenner) + " " + one(rest);
                else
                    return ten(tenner);
            }
        }

        public String three(int num) {
            int hundred = num / 100;
            int rest = num - hundred * 100;
            String res = "";
            if (hundred * rest != 0)
                res = one(hundred) + " Hundred " + two(rest);
            else if ((hundred == 0) && (rest != 0))
                res = two(rest);
            else if ((hundred != 0) && (rest == 0))
                res = one(hundred) + " Hundred";
            return res;
        }

        public String numberToWords(int num) {
            if (num == 0)
                return "Zero";

            int billion = num / 1000000000;
            int million = (num - billion * 1000000000) / 1000000;
            int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
            int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

            StringBuilder stb = new StringBuilder();
            if (billion != 0) {
                stb.append(three(billion) + " Billion");
            }
            if (million != 0) {
                if (stb.length() != 0) {
                    stb.append(" ");
                }

                stb.append(three(million) + " Million");

            }
            if (thousand != 0) {
                if (stb.length() != 0) {
                    stb.append(" ");
                }

                stb.append(three(thousand) + " Thousand");
            }
            if (rest != 0) {
                if (stb.length() != 0) {
                    stb.append(" ");
                }
                stb.append(three(rest));
            }
            return stb.toString();
        }
    }



    /**
     * 297. Serialize and Deserialize Binary Tree
     * Serialization is the process of converting a data structure or object into a sequence of bits
     * so that it can be stored in a file or memory buffer,
     * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
     */

    public static class Serializer {

        public String serialize(TreeNode root) {
            StringBuilder stb = new StringBuilder();
            serializeHelper(root, stb);
            stb.deleteCharAt(stb.length() - 1);
            return stb.toString();
        }

        private  void serializeHelper(TreeNode node, StringBuilder stb) {
            if (node == null) {
                stb.append("null");
                stb.append(",");
                return ;
            }
            stb.append(node.val);
            stb.append(",");
            serializeHelper(node.left, stb);
            serializeHelper(node.right, stb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            StringTokenizer stoken = new StringTokenizer(data, ",");
            return deserializeHelper(stoken);
        }
        private TreeNode deserializeHelper(StringTokenizer stoken) {
            if (!stoken.hasMoreTokens()) {
                return null;
            }
            String cur = stoken.nextToken();
            if (cur.equals("null")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(cur));
            node.left = deserializeHelper(stoken);
            node.right = deserializeHelper(stoken);
            return node;
        }
    }


    /**
     * 269. Alien Dictionary
     * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
     * You receive a list of non-empty words from the dictionary,
     * where words are sorted lexicographically by the rules of this new language.
     * Derive the order of letters in this language.
     *
     * Example 1:
     *
     * Input:
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     *
     * Output: "wertf"
     *
     * This is a topological sorting question.
     * 1. build the graph based on words.
     * 2. get the topological sorting based the graph
     */

    public static void task269_test() {
        String[] words = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        String result = new Task269_AlienOrder().alienOrder(words);
        System.out.println("result = " + result);
    }

    public static class Task269_AlienOrder {
        public  String alienOrder(String[] words) {
            if (words == null || words.length == 0)
                return "";
            if (words.length == 1)
                return words[0];

            Map<Character, Set<Character>> graph = buildGraph(words);
            System.out.println(graph);
            Map<Character, Integer> indegree = computeIndegree(graph);

            System.out.println(indegree);

            StringBuilder order = new StringBuilder();
            Queue<Character> queue = new LinkedList<>();

            // add the nodes whose indegree == 0
            for (Character c : indegree.keySet()) {
                if (indegree.get(c) == 0)
                    queue.offer(c);
            }

            // do BFS
            while (!queue.isEmpty()) {
                char c = queue.poll();
                order.append(c);
                for (Character adj : graph.get(c)) {
                    if (indegree.get(adj) - 1 == 0) {
                        queue.offer(adj);
                    }
                    else {
                        indegree.put(adj, indegree.get(adj) - 1);
                    }
                }
            }

            return order.length() == indegree.size() ? order.toString() : "";
        }

        /**
         * build the graph based on the order of words
         */
        private Map<Character, Set<Character>> buildGraph(String[] words) {
            Map<Character, Set<Character>> map = new HashMap<>();
            int N = words.length;
            for (int i = 1; i < N; i++) {
                // for two strings, we can at most get two char's order
                String word1 = words[i - 1];
                String word2 = words[i];

                int len1 = word1.length();
                int len2 = word2.length();
                int maxLen = Math.max(len1, len2);

                boolean found = false;
                for (int j = 0; j < maxLen; j++) {
                    char c1 = j < len1 ? word1.charAt(j) : ' ';
                    char c2 = j < len2 ? word2.charAt(j) : ' ';

                    // first, put the c1 into map
                    if (c1 != ' ' && !map.containsKey(c1)) {
                        map.put(c1, new HashSet<>());
                    }

                    // put the c2 into map
                    if (c2 != ' ' && !map.containsKey(c2)) {
                        map.put(c2, new HashSet<>());
                    }

                    // c1 != '' && c2 != '' & c1 != c2 && found == false
                    // !!! Note: from two strings, we can only find one sequence a -> b.  once found is true.
                    // won't execute the following code.
                    if (c1 != ' ' && c2 != ' ' && c1 != c2 && !found) {
                        map.get(c1).add(c2);
                        System.out.println(c1 + " -> " + c2);
                        found = true;
                    }
                }
            }

            return map;
        }

        private Map<Character, Integer> computeIndegree(Map<Character, Set<Character>> graph) {
            Map<Character, Integer> indegree = new HashMap<>();
            for (Character prev : graph.keySet()) {
                if (!indegree.containsKey(prev)) {
                    indegree.put(prev, 0);
                }

                for (Character succ : graph.get(prev)) {
                    if (!indegree.containsKey(succ))
                        indegree.put(succ, 1);
                    else
                        indegree.put(succ, indegree.get(succ) + 1);
                }
            }
            return indegree;
        }

    }


    /**
     *
     * Trie + Topological sort
     * Intuition:
     * 1. using Trie to build directed graph with weak cycle detection (fast ending)
     * 2. using Topological sort to get char in order with strong cycle detection
     *
     * V: number of characters E: number of edge ( relative order)
     * Time: O(V+E)
     * Space: O(V+E)
     */
    public static class AlienDictionary {

        public String alienOrder(String[] words) {
            Map<Character, Set<Character>> graph = new HashMap<>();
            Trie root = new Trie(graph);

            for(String w : words)
                if(!root.add(w,0))
                    return "";

            int n = graph.size();
            Set<Character> visited = new HashSet<>();
            Stack<Character> stack = new Stack<>();

            for(Character c : graph.keySet()){
                if(!visited.contains(c) && ! dfs(graph, c, visited, new HashSet<Character>(), stack))
                    return "";
            }

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty())
                sb.append(stack.pop());

            return sb.toString();
        }

        boolean dfs(Map<Character, Set<Character>> graph, char c, Set<Character> visited, Set<Character> visiting , Stack<Character> stack){
            visited.add(c);
            //recording current path
            visiting.add(c);

            for(char ch : graph.get(c))
                //strong cycle detection, same character woudn't appear in the same path twice
                if(visiting.contains(ch))
                    return false;
                else if(!visited.contains(ch) && !dfs(graph, ch, visited, visiting, stack))
                    return false;
            stack.push(c);
            visiting.remove(c);
            return true;
        }

        static class Trie{
            Trie[] children;
            Map<Character, Set<Character>> graph;
            List<Character> queue;

            Trie(Map<Character, Set<Character>> graph){
                children = new Trie[26];
                this.graph = graph;
                queue = new ArrayList<>();
            }

            boolean add(String s, int start){
                if(start>= s.length())
                    return true;
                char c = s.charAt(start);

                //weak cycle detection, same character wouldn't appear more than once at certain level
                if(children[c-'a'] != null && queue.get(queue.size()-1)!=c)
                    return false;

                for(Character ch : queue){
                    if(ch == c)
                        break;
                    if(!graph.containsKey(ch))
                        graph.put(ch, new HashSet<Character>());
                    graph.get(ch).add(c);
                }
                if(children[c-'a'] == null){
                    children[c-'a'] = new Trie(this.graph);
                    queue.add(c);
                }
                if(!graph.containsKey(c))
                    graph.put(c, new HashSet<Character>());
                return children[c-'a'].add(s, start+1);
            }
        }
    }


    /**
     * 282. Expression Add Operators
     *
     * Given a string that contains only digits 0-9 and a target value,
     * return all possibilities to add binary operators (not unary) +, -, or * between the digits
     * so they evaluate to the target value.
     *
     * Example 1:
     *
     * Input: num = "123", target = 6
     * Output: ["1+2+3", "1*2*3"]
     * Example 2:
     *
     * Input: num = "232", target = 8
     * Output: ["2*3+2", "2+3*2"]
     */

    public static void task282_test() {
        Task282_ExpressionAndOperators sln = new Task282_ExpressionAndOperators();
        String num = "123";
        int target = 6;
        List<String> result = sln.addOperators(num, target);
        System.out.println(result);
    }
    public static class Task282_ExpressionAndOperators {
        public List<String> addOperators(String num, int target) {
            List<String> res = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();
            dfs(res, sb, num, 0, target, 0, 0);
            return res;

        }
        public void dfs(List<String> res, StringBuilder sb, String num, int pos, int target, long prev, long multi) {
            if(pos == num.length()) {
                if(target == prev) res.add(sb.toString());
                return;
            }
            for(int i = pos; i < num.length(); i++) {
                if(num.charAt(pos) == '0' && i != pos) break;
                long curr = Long.parseLong(num.substring(pos, i + 1));
                int len = sb.length();
                if(pos == 0) {
                    dfs(res, sb.append(curr), num, i + 1, target, curr, curr);
                    sb.setLength(len);
                } else {
                    dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr);
                    sb.setLength(len);
                    dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr);
                    sb.setLength(len);
                    dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr);
                    sb.setLength(len);
                }
            }
        }
    }


    /**
     * 426. Convert Binary Search Tree to Sorted Doubly Linked List
     * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
     *
     * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
     * For a circular doubly linked list,
     * the predecessor of the first element is the last element, and the successor of the last element is the first element.
     *
     * We want to do the transformation in place. After the transformation,
     * the left pointer of the tree node should point to its predecessor,
     * and the right pointer should point to its successor.
     * You should return the pointer to the smallest element of the linked list.
     */
    public class Task426_Solution {
        // the smallest (first) and the largest (last) nodes
        TreeNode head = null;
        TreeNode tail = null;

        public TreeNode treeToDoublyList(TreeNode root) {
            if (root == null) return null;

            helper(root);
            // close DLL
            head.right = head;
            tail.left = tail;
            return head;
        }

        // do an inorder traversal.
        public void helper(TreeNode node) {
            if (node == null) {
                return;
            }

            // left
            helper(node.left);
            // node
            if (tail != null) {
                // link the previous node (last)
                // with the current one (node)
                tail.right = node;
                node.left = tail;
            }
            else {
                // keep the smallest node
                // to close DLL later on
                head = node;
            }
            tail = node;
            // right
            helper(node.right);
        }
    }


    /**
     * 523. Continuous Subarray Sum
     * Given a list of non-negative numbers and a target integer k,
     * write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k,
     * that is, sums up to n * k where n is also an integer.
     *
     * Example 1:
     *
     * Input: [23, 2, 4, 6, 7],  k = 6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     *
     */


    public static void task523_test() {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        boolean test = task523_checkSubarraySum(nums, k);
        System.out.println(test);
    }
    public static boolean task523_checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap <Integer, Integer> map = new HashMap <> ();  // preSum, index
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }


    /**
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     *
     * Example 1:
     *
     * Input: "A man, a plan, a canal: Panama"
     * Output: true
     * Example 2:
     *
     * Input: "race a car"
     * Output: false
     */
    public static class T125_ValidPalindrome {
        public boolean isPalindrome(String s) {
            return task125_isPalindrome(s);
        }

        public  boolean task125_isPalindrome(String s) {
            if (s == null ||s.length() == 0) {
                return true;
            }
            int left = 0, right = s.length() - 1;
            while(left < right) {
                char leftCh = s.charAt(left);
                char rightCh = s.charAt(right);
                if (!isAlphaumeric(leftCh)) {
                    left ++;
                } else if (!isAlphaumeric(rightCh)) {
                    right --;
                } else {
                    if (isSame(leftCh, rightCh)) {
                        left ++;
                        right --;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

        public  boolean isAlphaumeric(char ch) {

            if ((ch >= 'a' && ch<= 'z') || (ch >= 'A' && ch <= 'Z' )|| (ch >= '0' && ch <= '9')) {
                return true;
            }
            return false;
        }

        public  boolean isSame(char ch1, char ch2) {
            char ch1Up = Character.toUpperCase(ch1);
            char ch2Up = Character.toUpperCase(ch2);
            if (ch1Up == ch2Up) {
                return true;
            }
            return false;
        }
    }


    /**
     * 76. Minimum Window Substring
     * Given a string S and a string T,
     * find the minimum window in S which will contain all the characters in T in complexity O(n).
     *
     * Example:
     *
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     */
    public static void task76_test() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        String result = task76_minWindow(S, T);
        System.out.println(result);
    }


    /**
     * Time: O(|S| + |T|)
     * Space: O(1)
     */
    public static String task76_minWindow(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int[] targetHash = new int[128];
        int[] foundHash = new int[128];  // suppose it is ASCII code

        // fill in the dictHash
        for(int i = 0; i < t.length(); i ++) {
            char cur = t.charAt(i);
            targetHash[cur]++;
        }

        int appeared = 0;
        int windowStart = 0;
        int windowEnd = 0;
        int minWidth = Integer.MAX_VALUE;
        int minStart = 0;

        while(windowEnd < s.length()) {
            char cur = s.charAt(windowEnd);
            // cur is a char in t
            if (targetHash[cur] > 0) {
                if (foundHash[cur] < targetHash[cur]) {
                    appeared ++;
                }
                foundHash[cur]++;
            }

            // if appeared == T.length(), we get a window contains all Target char.
            if (appeared == t.length()) {
                char cur2 = s.charAt(windowStart);
                // shrink the window
                while(targetHash[cur2] < foundHash[cur2] || foundHash[cur2] == 0) {
                    // foundHash[cur2] > dictHash[cur2], for cur2, foundHash has more than in dictHash
                    // or the cur2 is NOT in T
                    if (targetHash[cur2] < foundHash[cur2]) {
                        foundHash[cur2] --;
                    }
                    windowStart++;
                    // udpate cur2
                    cur2 = s.charAt(windowStart);
                }

                // get the result
                if (minWidth > windowEnd - windowStart + 1) {
                    minWidth = windowEnd - windowStart + 1;
                    minStart = windowStart; // record the minStart
                }
            }
            windowEnd ++;
        }

        if (minWidth == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(minStart, minStart + minWidth);
        }
    }


    /**
     * 438. Find All Anagrams in a String
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     *
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     *
     * Example 1:
     *
     * Input:
     * s: "cbaebabacd" p: "abc"
     *
     * Output:
     * [0, 6]
     *
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     *
     * Time: O(Ns + Np)
     * Space: O(1) because pCount and sCount contain not more than 26 elements.
     */

    public static void task438_test() {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = task438_findAnagrams(s, p);
        System.out.println(result);

    }
    public static List<Integer> task438_findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList();

        Map<Character, Integer> pCount = new HashMap();
        Map<Character, Integer> sCount = new HashMap();
        // build reference hashmap using string p
        for (char ch : p.toCharArray()) {
            pCount.put(ch, pCount.getOrDefault(ch, 0) + 1);

        }
        System.out.println(pCount);

        List<Integer> output = new ArrayList();
        // sliding window on the string s
        for (int i = 0; i < sLen; ++i) {
            // add one more letter
            // on the right side of the window
            char ch = s.charAt(i);
            sCount.put(ch, sCount.getOrDefault(ch, 0) + 1);

            // remove one letter
            // from the left side of the window
            if (i >= pLen) {
                ch = s.charAt(i - pLen);
                if (sCount.get(ch) == 1) {
                    sCount.remove(ch);
                }
                else {
                    sCount.put(ch, sCount.get(ch) - 1);
                }
            }

            // compare hashmap in the sliding window
            // with the reference hashmap
            if (pCount.equals(sCount)) {
                output.add(i - pLen + 1);
            }
        }
        return output;
    }


    /**
     * 621. Task Scheduler
     *
     * Given a char array representing tasks CPU need to do. It contains capital letters A to Z
     * where different letters represent different tasks. Tasks could be done without original order.
     * Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
     *
     * However, there is a non-negative cooling interval n that means between two same tasks,
     * there must be at least n intervals that CPU are doing different tasks or just be idle.
     *
     * You need to return the least number of intervals the CPU will take to finish all the given tasks.
     *
     * Example:
     *
     * Input: tasks = ["A","A","A","B","B","B"], n = 2
     * Output: 8
     * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     */
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue < Integer > queue = new PriorityQueue < > (26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List < Integer > temp = new ArrayList < > ();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }


    /**
     * 986. Interval List Intersections
     * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
     * The intersection of two closed intervals is a set of real numbers that is either empty,
     * or can be represented as a closed interval.
     * For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     */
    public static int[][] task986_intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }


    /**
     * 415. Add Strings
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     */
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder stb = new StringBuilder();
        while(i >= 0 && j >= 0) {
            int c_num1 = num1.charAt(i) - '0';
            int c_num2 = num2.charAt(j) - '0';
            int sum = c_num1 + c_num2 + carry;
            carry = sum / 10;
            sum %= 10;
            stb.append(sum);
            i --;
            j --;
        }
        while(i >= 0) {
            int c_num1 = num1.charAt(i) - '0';
            int sum = c_num1 + carry;
            carry = sum / 10;
            sum = sum % 10;
            stb.append(sum);
            i --;
        }
        while(j >= 0) {
            int c_num2 = num2.charAt(j) - '0';
            int sum = c_num2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            stb.append(sum);
            j --;
        }
        if (carry != 0) {
            stb.append(carry);
        }
        return stb.reverse().toString();
    }


    /**
     * 211. Add and Search Word - Data structure design
     * Design a data structure that supports the following two operations:
     *
     * void addWord(word)
     * bool search(word)
     * search(word) can search a literal word or a regular expression string containing only letters a-z or .
     * . means it can represent any one letter.
     *
     * addWord("bad")
     * addWord("dad")
     * addWord("mad")
     * search("pad") -> false
     * search("bad") -> true
     * search(".ad") -> true
     * search("b..") -> true
     */
    public class WordDictionary {

        class TrieNode {
            public HashMap<Character, TrieNode> children;
            public boolean isEnd;
            public int visited;


            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
                isEnd = false;
                visited = 0;
            }
        }

        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }
        public void addWord(String word) {
            insert(root, word);
        }

        public void insert(TrieNode root, String word) {
            if (word.isEmpty()) {
                root.isEnd = true;
                return ;
            }
            char next = word.charAt(0);
            if (!root.children.containsKey(next)) {
                root.children.put(next, new TrieNode());
            }
            insert(root.children.get(next), word.substring(1));
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return searchHelper(root, word);
        }
        public boolean searchHelper(TrieNode node, String word) {
            if (word.isEmpty()) {
                if (node == null) {
                    return false;
                }
                if (node.isEnd == true) {
                    return true;
                } else {
                    return false;
                }
            }

            char next = word.charAt(0);
            if (next >= 'a' && next <= 'z') {
                if (!node.children.containsKey(next)) {
                    return false;
                } else {
                    return searchHelper(node.children.get(next), word.substring(1));
                }
            } else {
                boolean rev = false;
                for(Entry<Character, TrieNode> entry: node.children.entrySet()) {
                    rev = rev || searchHelper(entry.getValue(), word.substring(1));
                }
                return rev;
            }
        }
    }


    /**
     * 721. Accounts Merge
     * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
     *
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
     *
     * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
     *
     * Example 1:
     * Input:
     * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
     * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
     * Explanation:
     * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
     * The second John and Mary are different people as none of their email addresses are used by other accounts.
     * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
     * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
     */

    /**
     * Time: O(A log A) where A = sum(ai). ai is the length of accounts[i]
     * Space: O(A), the space used by DSU structure
     */
    static class Task721_MergeAccounts {
        class DSU {
            int[] parent;
            public DSU() {
                parent = new int[10001];
                for (int i = 0; i <= 10000; ++i)
                    parent[i] = i;
            }
            public int find(int x) {
                if (parent[x] != x) parent[x] = find(parent[x]);
                return parent[x];
            }
            public void union(int x, int y) {
                parent[find(x)] = find(y);
            }
        }

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            DSU dsu = new DSU();
            Map<String, String> emailToName = new HashMap();
            Map<String, Integer> emailToID = new HashMap();
            int id = 0;
            for (List<String> account: accounts) {
                String name = "";
                for (String email: account) {
                    if (name == "") {
                        name = email;
                        continue;
                    }
                    emailToName.put(email, name);
                    if (!emailToID.containsKey(email)) {
                        emailToID.put(email, id++);
                    }
                    dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
                }
            }

            Map<Integer, List<String>> ans = new HashMap();
            for (String email: emailToName.keySet()) {
                int index = dsu.find(emailToID.get(email));
                ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
            }
            for (List<String> component: ans.values()) {
                Collections.sort(component);
                component.add(0, emailToName.get(component.get(0)));
            }
            return new ArrayList(ans.values());
        }
    }

    /**
     * Intuition
     *
     * Draw an edge between two emails if they occur in the same account.
     * The problem comes down to finding connected components of this graph.
     *
     * Algorithm:
     * For each account, draw the edge from the first email to all other emails.
     * Additionally, we'll remember a map from emails to names on the side.
     * After finding each connected component using a depth-first search, we'll add that to our answer.
     */

    public static class Task721_Solution_DFS {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, String> emailToName = new HashMap();
            Map<String, ArrayList<String>> graph = new HashMap();
            for (List<String> account: accounts) {
                String name = "";
                for (String email: account) {
                    if (name == "") {
                        name = email;
                        continue;
                    }
                    graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                    graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                    emailToName.put(email, name);
                }
            }

            Set<String> seen = new HashSet();
            List<List<String>> ans = new ArrayList();
            for (String email: graph.keySet()) {
                if (!seen.contains(email)) {
                    seen.add(email);
                    Stack<String> stack = new Stack();
                    stack.push(email);
                    List<String> component = new ArrayList();
                    while (!stack.empty()) {
                        String node = stack.pop();
                        component.add(node);
                        for (String nei: graph.get(node)) {
                            if (!seen.contains(nei)) {
                                seen.add(nei);
                                stack.push(nei);
                            }
                        }
                    }
                    Collections.sort(component);
                    component.add(0, emailToName.get(email));
                    ans.add(component);
                }
            }
            return ans;
        }
    }


    /**
     * 173. Binary Search Tree Iterator
     *
     */

    public class BSTIterator {
        public TreeNode cur;
        public Stack<TreeNode> st;
        public BSTIterator(TreeNode root) {
            cur = root;
            st = new Stack<TreeNode>();
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return cur != null || !st.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            TreeNode node = st.pop();
            cur = node.right;
            return node.val;
        }
    }


    /**
     * 340. Longest Substring with At Most K Distinct Characters
     *
     * Given a string, find the length of the longest substring T that contains at most k distinct characters.
     *
     * Example 1:
     *
     * Input: s = "eceba", k = 2
     * Output: 3
     * Explanation: T is "ece" which its length is 3.
     * Example 2:
     *
     * Input: s = "aa", k = 1
     * Output: 2
     * Explanation: T is "aa" which its length is 2.
     */
    public static int Task340_lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        while(fast < s.length()) {
            char fastChar = s.charAt(fast);
            if (map.containsKey(fastChar)) {
                map.put(fastChar, map.get(fastChar) + 1);
            } else {
                // map don't contain fast char
                map.put(fastChar, 1);
                while(map.size() > k) {
                    // move the slow to narrow the window
                    char slowChar = s.charAt(slow);

                    map.put(slowChar, map.get(slowChar) - 1);
                    if (map.get(slowChar) == 0) {
                        map.remove(slowChar);
                    }
                    slow ++;
                }
            }
            maxLen = Math.max(maxLen, fast - slow + 1);
            fast ++;
        }
        return maxLen;
    }

    /**
     * 199. Binary Tree Right Side View
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     */

    public class Task199_rightSideView {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<Integer>();
            helper(root, result, 0);
            return result;
        }


        public void helper(TreeNode node, List<Integer> result, int level) {
            if (node == null) {
                return;
            }
            if (result.size() == level) {
                result.add(node.val);
            }
            helper(node.right, result, level + 1);
            helper(node.left, result, level + 1);

        }
    }

    /**
     * 785. Is Graph Bipartite?
     *
     */
    class Task785_isBipartite {
        public boolean isBipartite(int[][] graph) {
            return task785_isBipartite(graph);
        }


        public  boolean task785_isBipartite(int[][] graph) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < graph.length; i++) {
                if (!task785_BFS(graph, map, i)) {
                    return false;
                }
            }
            return true;
        }

        public  boolean task785_BFS(int[][] graph, Map<Integer, Integer> map, int node) {
            if (map.containsKey(node)) {
                return true;
            }

            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            map.put(node, 0); // put the flag

            while (!q.isEmpty()) {
                int cur = q.poll();
                int curSign = map.get(cur);
                int[] neis = graph[cur];
                for (Integer nei: neis) {
                    int nextSign = curSign == 0 ? 1 : 0;
                    if (!map.containsKey(nei)) {
                        map.put(nei, nextSign);
                        q.offer(nei);
                    } else {
                        if (nextSign != map.get(nei)) {
                            return false;
                        }
                    }

                }
            }
            return true;
        }
    }


    /**
     * 825. Friends Of Appropriate Ages
     *
     *
     * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
     *
     * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
     *
     * age[B] <= 0.5 * age[A] + 7
     * age[B] > age[A]
     * age[B] > 100 && age[A] < 100
     * Otherwise, A will friend request B.
     *
     * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
     *
     * How many total friend requests are made?
     *
     * Example 1:
     *
     * Input: [16,16]
     * Output: 2
     * Explanation: 2 people friend request each other.
     *
     * Simple Java O(n) similar to Count Sort.
     *
     * Time: O(n)
     * Space: O(1)
     */
    public class Task825_FriendsOfAppropriateAges {
        public int numFriendRequests(int[] ages) {
            int[] count = new int[121];

            for(int age : ages) count[age]++;

            int ans = 0;
            for(int i = 1; i <= 120; i++) {
                if (i <= 14) continue;

                // Number of occurence for age
                int occurences = count[i];

                // Accumulation of counts like count sort
                count[i] += count[i-1];

                // Count till [a] - Count till [a/2+7] - 1 -> give count for range (a/2+7, a]
                ans +=  occurences * (count[i] - count[i/2 + 7] - 1);
            }

            return ans;
        }
    }

    /**
     * 636. Exclusive Time of Functions
     */
    public int[] exclusiveTime(int n, List < String > logs) {
        Stack <Integer> stack = new Stack <> ();
        int[] res = new int[n];
        String[] s = logs.get(0).split(":");
        stack.push(Integer.parseInt(s[0]));
        int i = 1, prev = Integer.parseInt(s[2]);
        while (i < logs.size()) {
            s = logs.get(i).split(":");
            if (s[1].equals("start")) {
                if (!stack.isEmpty())
                    res[stack.peek()] += Integer.parseInt(s[2]) - prev;
                stack.push(Integer.parseInt(s[0]));
                prev = Integer.parseInt(s[2]);
            } else {
                res[stack.peek()] += Integer.parseInt(s[2]) - prev + 1;
                stack.pop();
                prev = Integer.parseInt(s[2]) + 1;
            }
            i++;
        }
        return res;
    }

    /**
     * 958. Check Completeness of a Binary Tree
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.
     * It can have between 1 and 2h nodes inclusive at the last level h.
     */

    public boolean isCompleteTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEnd = false;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                // a complete true should be 1,2,3,4,null,null i.e. always end with null
                //So the logic here isto mark aboolean as isEnd when we find a null. if we fins any
                // not nul after that, means Gap
                if(cur == null) {
                    isEnd = true;
                } else {
                    if(isEnd) return false;
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }

        return true;
    }


    /**
     * 689. Maximum Sum of 3 Non-Overlapping Subarrays
     *
     * Create cumulative sum array so that when we search for the sum of a certain subarray it is O(1);
     * Create leftVal[i] which is the max sum of subarray from nums[0] to nums[i].
     * Create rightVal[i] which is the max sum of subarray from nums[i] to nums[n - 1].
     *
     * Use a sliding window with length k to scan the middle part. The index is from i to i + k - 1.
     * The left part and right part can be easily fetched by using leftVal[i - 1] and rightVal[i + k].
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Get cumulative sum so we know sum of each subarray by O(1).
        int[] sum = new int[n];
        for(int i = 0; i < n; i++) {
            if(i == 0) sum[i] = nums[i];
            else sum[i] = sum[i - 1] + nums[i];
        }

        // leftVal[i] is the max sum of subarray from nums[0] to nums[i].
        // leftInd[i] is the starting index of the max subarray.
        int[] leftValue = new int[n];
        int[] leftIndex = new int[n];
        int tmpMax = 0;
        for(int i = 0; i < k; i++) tmpMax += nums[i];
        leftValue[k - 1] = tmpMax;
        leftIndex[k - 1] = 0;
        for(int i = k; i <= n - 1; i++) {
            leftValue[i] = leftValue[i - 1];
            leftIndex[i] = leftIndex[i - 1];
            int curr = sum[i] - sum[i - k];
            if(curr > tmpMax) {
                tmpMax = curr;
                leftValue[i] = curr;
                leftIndex[i] = i - k + 1;
            }
        }

        // rightVal[i] is the max sum of subarray from nums[i] to nums[n - 1].
        // rightInd[i] is the starting index of the max subarray.
        int[] rightValue = new int[n];
        int[] rightIndex = new int[n];
        tmpMax = 0;
        for(int i = n - 1; i >= n - k; i--) tmpMax += nums[i];
        rightValue[n - k] = tmpMax;
        rightIndex[n - k] = n - k;
        for(int i = n - k - 1; i >= 0; i--) {
            rightValue[i] = rightValue[i + 1];
            rightIndex[i] = rightIndex[i + 1];
            int curr = i > 0 ? sum[i + k - 1] - sum[i - 1] : sum[i + k - 1];
            if(curr >= tmpMax) {
                tmpMax = curr;
                rightValue[i] = curr;
                rightIndex[i] = i;
            }
        }

        // Sliding the window with length k and check the leftVal and rightVal.
        int[] res = new int[3];
        for(int i = k; i + k - 1 < n - k; i++) {
            if(i == k) {
                tmpMax = leftValue[i - 1] + (sum[i + k - 1] - sum[i - 1]) + rightValue[i + k];
                res[0] = leftIndex[i - 1];
                res[1] = i;
                res[2] = rightIndex[i + k];
            } else {
                int curr = leftValue[i - 1] + (sum[i + k - 1] - sum[i - 1]) + rightValue[i + k];
                if(curr > tmpMax) {
                    tmpMax = curr;
                    res[0] = leftIndex[i - 1];
                    res[1] = i;
                    res[2] = rightIndex[i + k];
                }
            }
        }
        return res;
    }


    /**
     *
     * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
     *
     * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
     * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
     *
     * Input: [3,6,9,12]
     * Output: 4
     * Explanation:
     * The whole array is an arithmetic sequence with steps of length = 3.
     *
     */

    public int longestArithSeqLength(int[] A) {
        if (A == null || A.length < 2) return 0;
        Map<Integer, Integer>[] dp = new HashMap[A.length];
        int res = 2;
        for (int i = 0; i < A.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int d = A[i] - A[j];
                dp[i].put(d, dp[j].getOrDefault(d, 1) + 1);   // default to be 1 other than 0
                res = Math.max(res, dp[i].get(d));
            }
        }
        return res;
    }

    static int lenghtOfLongestAP(int[] A)
    {

        if (A.length <= 2) return A.length;
        int n = A.length;

        // Create a table and initialize all
        // values as 2. The value ofL[i][j] stores
        // LLAP with set[i] and set[j] as first two
        // elements of AP. Only valid entries are
        // the entries where j>i
        int L[][] = new int[n][n];

        // Initialize the result
        int llap = 2;

        // Fill entries in last column as 2.
        // There will always be two elements in
        // AP with last number of set as second
        // element in AP
        for (int i = 0; i < n; i++)
            L[i][n - 1] = 2;

        // Consider every element as second element of AP
        for (int j = n - 2; j >= 1; j--)
        {
            // Search for i and k for j
            int i = j -1 , k = j + 1;
            while (i >= 0 && k <= n - 1)
            {
                if (A[i] + A[k] < 2 * A[j])
                    k++;

                    // Before changing i, set L[i][j] as 2
                else if (A[i] + A[k] > 2 * A[j])
                {
                    L[i][j] = 2;
                    i--;

                }

                else
                {
                    // Found i and k for j, LLAP with i and j as first two
                    // elements is equal to LLAP with j and k as first two
                    // elements plus 1. L[j][k] must have been filled
                    // before as we run the loop from right side
                    L[i][j] = L[j][k] + 1;

                    // Update overall LLAP, if needed
                    llap = Math.max(llap, L[i][j]);

                    // Change i and k to fill
                    // more L[i][j] values for current j
                    i--;
                    k++;
                }
            }

            // If the loop was stopped due
            // to k becoming more than
            // n-1, set the remaining
            // entties in column j as 2
            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }


    /**
     * 987. Vertical Order Traversal of a Binary Tree
     */

    public static class T987_VerticalOrder {
        class Location implements Comparable<Location>{
            int x, y, val;
            Location(int x, int y, int val) {
                this.x = x;
                this.y = y;
                this.val = val;
            }

            @Override
            public int compareTo(Location that) {
                if (this.x != that.x)
                    return Integer.compare(this.x, that.x);
                else if (this.y != that.y)
                    return Integer.compare(this.y, that.y);
                else
                    return Integer.compare(this.val, that.val);
            }
        }

        List<Location> locations;
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            // Each location is a node's x position, y position, and value
            locations = new ArrayList();
            dfs(root, 0, 0);
            Collections.sort(locations);

            List<List<Integer>> ans = new ArrayList();
            ans.add(new ArrayList<Integer>());

            int prev = locations.get(0).x;

            for (Location loc: locations) {
                // If the x value changed, it's part of a new report.
                if (loc.x != prev) {
                    prev = loc.x;
                    ans.add(new ArrayList<Integer>());
                }

                // We always add the node's value to the latest report.
                ans.get(ans.size() - 1).add(loc.val);
            }

            return ans;
        }

        public void dfs(TreeNode node, int x, int y) {
            if (node != null) {
                locations.add(new Location(x, y, node.val));
                dfs(node.left, x-1, y+1);
                dfs(node.right, x+1, y+1);
            }
        }
    }



}
