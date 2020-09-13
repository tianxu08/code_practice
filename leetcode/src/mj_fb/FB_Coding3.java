package mj_fb;

import java.util.*;
import ds.*;
public class FB_Coding3 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		test1();
//		test2();
//		test2_2();
//		test13();
//		test14();
        test15();
    }

    public static void test1() {
        String s = "(a(b)";
        String rev = findBalancedParentheses(s);
        System.out.println(rev);
    }
    public static String findBalancedParentheses(String input) {
        // left_list storing the index of un-matched left parentheses
        // right_list store the index of un-matched right parentheses
        Deque<Integer> left_list = new LinkedList<>();
        Deque<Integer> right_list = new LinkedList<>();
        for(int i = 0; i < input.length(); i ++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                left_list.offerLast(i);
            } else if (ch == ')') {
                if (!left_list.isEmpty()) {
                    left_list.pollLast();
                } else {
                    // remember, here still offerLast
                    right_list.offerLast(i);
                }
            } else {
                // do nothing
            }
        }
        System.out.println(left_list);
        System.out.println("----");
        System.out.println(right_list);

        // now, the left_list and right_list contain the index of unpaired parentheses
        StringBuilder stb  = new StringBuilder();
        for(int i = 0; i < input.length(); i ++) {
            if (!left_list.isEmpty() && i == left_list.peekFirst()) {
                left_list.pollFirst();
                continue;
            } else if (!right_list.isEmpty() && i == right_list.peekFirst()) {
                right_list.pollFirst();
                continue;
            } else {
                stb.append(input.charAt(i));
            }
        }
        return stb.toString();
    }

    public static String findBalancedParentheses2(String input) {
        String delete_right = removeRightParentheses(input);
        String rev = removeLeftParentheses(delete_right);
        return rev;
    }

    /*
     * only delete the Right unmatched parentheses
     * */
    public static String removeRightParentheses(String input) {
        int count = 0;
        StringBuilder stb = new StringBuilder();
        for(int i = 0; i < input.length(); i ++) {
            char ch = input.charAt(i);
            stb.append(ch);
            if (ch == '(') {
                count ++;
            } else if (ch == ')') {
                if (count > 0) {
                    count --;
                } else {
                    // this ) is NOT matched, so delete it
                    stb.deleteCharAt(stb.length() - 1);
                }
            }
        }
        return stb.toString();
    }

    public static String removeLeftParentheses(String input) {
        int count = 0;
        StringBuilder stb = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            char ch = input.charAt(i);
            stb.append(ch);
            if (ch == ')') {
                count++;
            } else if (ch == '(') {
                if (count > 0) {
                    count--;
                } else {
                    // this ( is NOT matched, so delete is
                    stb.deleteCharAt(stb.length() - 1);
                }
            }
        }
        return stb.reverse().toString();
    }


    /**
     * L91
     * decode ways
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     *
     * M[i] the number of decode ways
     *
     */


    public static int task91_numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] M = new int[s.length() + 1];
        M[0] = 1;

        if (task91_isValid(s.substring(0, 1))) {
            M[1] = 1;
        } else {
            M[1] = 0;
        }

        for (int i = 2; i <= s.length(); i++) {
            if (task91_isValid(s.substring(i - 1, i))) {
                M[i] += M[i - 1];
            }

            if (task91_isValid(s.substring(i - 2, i))) {
                M[i] += M[i - 2];
            }
        }
        System.out.println(Arrays.toString(M));
        return M[s.length()];
    }

    public static boolean task91_isValid(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        int val = Integer.parseInt(s);
        return val >= 1 && val <= 26;
    }

    public static void test2() {
        String s = "123";
        int rev = task91_numDecodings_opt(s);
        int rev2 = task91_numDecodings(s);
        System.out.println("rev = " + rev);
        System.out.println("rev2 = " + rev2);
    }

    /*
     * Time: O(n)
     */
    public static int task91_numDecodings_opt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int pprev = 1;  // M[0] == 1

        int prev = task91_isValid(s.substring(0, 1)) ? 1 : 0;

        for (int i = 2; i <= s.length(); i++) {
            int cur = 0;

            if (task91_isValid(s.substring(i - 1, i))) {
                cur += prev;
            }

            if (task91_isValid(s.substring(i - 2, i))) {
                cur = cur + pprev;
            }

            // update
            pprev = prev;
            prev = cur;
        }

        System.out.println("prev = " + prev);
        System.out.println("pprev = " + pprev);

        return prev;
    }


    /*
     * follow up
     * print all result after decoding
     */
    public static void test2_2() {
        String s = "123";
        List<String> rev = t91_decode_all(s);
        System.out.println(rev);
    }

    public static List<String> t91_decode_all(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder stb = new StringBuilder();
        t91_all_helper(s, 0, stb, result);
        return result;
    }

    public static void t91_all_helper(String s, int idx, StringBuilder stb,
                                      List<String> result) {
        if (idx == s.length()) {
            // get a solution, here
            result.add(stb.toString());
            return ;
        }

        // get char s[idx]
        String one_digit = s.substring(idx, idx + 1);
        int one_num = Integer.parseInt(one_digit);
        if (one_num == 0) {
            return ;
        }

        // only choose one character
        stb.append((char)(one_num + 'A' - 1));
        t91_all_helper(s, idx + 1, stb, result);
        // backtracking
        stb.deleteCharAt(stb.length() - 1);

        // choose two characters
        if (idx < s.length() - 1) {
            String two_digits = s.substring(idx, idx + 2);
            int two_num = Integer.parseInt(two_digits);
            if (two_num >= 10 && two_num <= 26)  {
                stb.append((char)(two_num + 'A' - 1));
                t91_all_helper(s, idx + 2, stb, result);
                stb.deleteCharAt(stb.length() - 1);
            }
        }
    }

    /*
     * nearest Kth point
     * find the nearest Kth point to the origin point
     *
     * quick sort, partition.
     *
     */
    public static Point nearest_kth_point_quick_select(Point[] points, int k) {
        quick_select(points, 0, points.length - 1, k);
        return points[k];
    }

    public static void quick_select(Point[] points, int left, int right, int k) {
        int pivotIdx = partition(points, left, right);
        if (pivotIdx == k) {
            return ;
        } else if (pivotIdx < k) {
            quick_select(points, pivotIdx + 1, right, k);
        } else {
            quick_select(points, left, pivotIdx - 1, k);
        }

    }

    public static int partition(Point[] points, int left, int right) {
        int pivotIdx = right;
        int start = left, end = right - 1;

        while(start <= end) {
            if (getDistance(points[start]) < getDistance(points[pivotIdx])) {
                start ++;
            } else if (getDistance(points[end]) >= getDistance(points[pivotIdx])) {
                end --;
            } else {
                swap(points, start, end);
                start ++;
                end --;
            }
        }
        // swap the start with pivotIdx
        swap(points, start, pivotIdx);
        return start;
    }

    public static void swap(Point[] points, int x, int y) {
        Point temp = points[x];
        points[x] = points[y];
        points[y] = temp;
    }

    public static int getDistance(Point p) {
        int x = p.x;
        int y = p.y;

        return x*x + y * y;
    }
    public static class Point{
        int x;
        int y;
        public Point(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }
    }

    /*
     * use maxHeap. heapSize == k
     * each time, meet a new point, check if its distance is smaller than heap.peek()
     * if yes. pop out the peak, push the new point into maxHeap
     * after traversal, the maxHeap will contains the k closest points
     */

    /*
     * knight
     * there are obstacles
     * calculate the shortest path to some target
     *
     * How to optimize: A*, pruning
     *
     */
    public static int minStep(Position start, Position end, Set<Position> blocks) {
        Set<Position> visited = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();
        int step = 0;
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            step ++;
            int size = queue.size();
            for(int i = 0; i < size; i ++) {
                Position curPos = queue.poll();
                for(int k = 0; k < 4; k ++) {
                    int nextX = curPos.x + dx[k];
                    int nextY = curPos.y + dy[k];
                    Position nextPos = new Position(nextX, nextY);
                    if (nextPos.equals(end)) {
                        return step;
                    }
                    if (!visited.contains(nextPos) && !blocks.contains(nextPos)) {
                        queue.offer(nextPos);
                        visited.add(nextPos);
                    }
                }
            }
        }
        return -1;
    }

    public static int knightMinPath(Position start, Position end, Set<Position> blocks) {
        int[][] nextStep = {
                {-1, -2},
                {1, -2},
                {-2, -1},
                {2, -1},
                {-2, 1},
                {2, 1},
                {-1, 2},
                {1,2}
        };

        Queue<Position> queue = new LinkedList<>();
        HashSet<Position> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while(!queue.isEmpty()) {
            step ++;
            int size = queue.size();
            for(int i = 0; i < size; i ++) {
                Position curPos = queue.poll();
                for(int k = 0; k < 8; k ++) {
                    int nextX = curPos.x + nextStep[k][0];
                    int nextY = curPos.y + nextStep[k][1];
                    Position nextPos = new Position(nextX, nextY);
                    boolean canReach1 = true;
                    boolean canReach2 = true;
                    if (Math.abs(nextY) > Math.abs(nextX) ) {

                    }

                }
            }

        }
        return -1;
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};


    public static class Position{
        int x;
        int y;
        public Position(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new int[]{x, y});
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other == this) {
                return true;
            }
            if (!(other instanceof Position)) {
                return false;
            }
            Position point = (Position) other;
            return point.x == x && point.y == y;
        }
    }


    /*
     * Longest Consecutive sequence
     *
     * HashMap<Integer, Boolean>
     */

    public static void test13() {
        int[] nums = {100, 4, 200, 1, 3, 2};

        int rev = longestConsecutive(nums);
        System.out.println("rev = " + rev);

        int rev2 = l128_longestConsecutive(nums);
        System.out.println("rev2 = " + rev2);
    }

    public static int longestConsecutive(int[] nums) {
        // put all num into hashMap
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(Integer i : nums) {
            map.put(i, false);
        }
        int totalLen = Integer.MIN_VALUE;
        for(Integer num : nums) {
            int curLen = 1;
            curLen += getConsecutiveLen(map, num - 1, -1);
            curLen += getConsecutiveLen(map, num + 1, 1);
            if (curLen > totalLen) {
                totalLen = curLen;
            }
        }

        return totalLen;
    }

    public static int getConsecutiveLen(HashMap<Integer, Boolean> map, int num, int step) {
        int len = 0;
        while(map.containsKey(num) && map.get(num) == false) {
            len ++;
            map.put(num, true);
            num = num + step;
        }
        return len;
    }

    /*
     * l128
     */
    // Use a hashMap to record the length of a consecutice sequence
    // When meet a new number, lookup into hashMap for the adjcent number
    // if exits, then we merge this number into consecutive sequence
    // We only need to update the length at the end and start of the sequence
    // since, every time, when we expand,
    // we only expand from the start and end of the sequence
    public static int l128_longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 1;
        for(Integer num: nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int leftLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
            int rightLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
            int len = leftLen + rightLen + 1;
            map.put(num, len);
            map.put(num - leftLen, len);
            map.put(num + rightLen, len);

            // for debug
            printMap(map);
            result = Math.max(result, len);
        }
        return result;
    }

    public static void printMap(HashMap<Integer, Integer> map) {
        System.out.println("-----------------------------");
        for(java.util.Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("-----------------------------");

    }


    /*
     * reconstruct BST from pre-Order sequence
     */
    public static TreeNode constructFromPreOrder(int[] preorder) {
        return null;
    }

    public static TreeNode constructFromPreOrderHelper(int[] preorder, int start, int end) {
        if (start == end) {
            return new TreeNode(preorder[start]);
        }
        TreeNode root = new TreeNode(preorder[start]);
        // find the start of the right branch
        start ++;
        int rightIdx = start;
        while(rightIdx <= end && preorder[rightIdx] < root.val) {
            rightIdx ++;
        }

        TreeNode left = null;
        TreeNode right = null;

        if (rightIdx > start) {

        }
        return null;
    }


    public static void test14() {
        int[] preOrder = {5,3,1,2,4,7,6,9};
        TreeNode root = reConstructBSTPreOrder(preOrder);
        inOrder(root);
    }

    /**
     * Reconstruct BST from preOrder
     * @param preOrder
     * @return root of the BSTs
     *
     * Time: O(n).
     * n is the length of the preOrder.
     * every element we will visit once
     */
    public static TreeNode reConstructBSTPreOrder(int[] preOrder) {
        index = 0;
        return reConstructBSTPreOrderHelper(preOrder, Integer.MAX_VALUE);
    }

    /**
     * Helper function for reConstructBSTPreOrder.
     * @param preOrder
     * @param max: the maxBound of the subtree. if out of the maxBound, go to the parent
     *        recursion.
     * @return
     */
    public static TreeNode reConstructBSTPreOrderHelper(int[] preOrder, int max) {
        if (index >= preOrder.length || preOrder[index] >= max) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[index]);
        index ++;
        root.left = reConstructBSTPreOrderHelper(preOrder, root.val);
        root.right = reConstructBSTPreOrderHelper(preOrder, max);
        return root;
    }


    /**
     * assume: post order is NOT null, no duplicates
     * @param post
     * @return
     */
    public static TreeNode ReconstructBSTPostOrder(int[] post) {
        index = post.length - 1;

        return ReconstructBSTPosOrderHelper(post, Integer.MIN_VALUE);
    }
    /**
     *
     * @param postOrder
     * @param min: the minimum bound of subtree.
     * @return
     */
    public static TreeNode ReconstructBSTPosOrderHelper(int[] postOrder, int min) {
        if (index < 0 || postOrder[index] <= min) {
            return null;
        }
        TreeNode root = new TreeNode(postOrder[index]);
        index --;
        root.right = ReconstructBSTPosOrderHelper(postOrder, root.val);
        root.left = ReconstructBSTPosOrderHelper(postOrder, min);
        return root;
    }

    private static int index;


    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public static void preOrder(TreeNode node) {
        if (node == null) {
            return ;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void postOrder(TreeNode node) {
        if (node == null) {
            return ;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }



    /**
     * construct Binary Tree from preOrder and inOrder.
     * @param preOrder
     * @param inOrder
     * @return: the root of Binary Tree
     * Assume: pre and in is NULL and with the same length
     */
    public static TreeNode reConstruct_pre_in(int[] pre, int[] in) {
        preIdx = 0;
        inIdx = 0;
        return reConstruct_pre_in_helper(pre, in, Integer.MAX_VALUE);
    }



    public static TreeNode reConstruct_pre_in_helper(int[] pre, int[] in, int target) {
        if (inIdx >= in.length || in[inIdx] == target) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preIdx]);
        preIdx ++;
        root.left = reConstruct_pre_in_helper(pre, in, root.val);
        inIdx ++;
        root.right = reConstruct_pre_in_helper(pre, in, target);
        return root;
    }
    public static int preIdx;
    public static int inIdx;
    public static int postIdx;

    public static TreeNode reConstruct_post_in(int[] post, int[] in) {
        postIdx = post.length - 1;
        inIdx = in.length - 1;
        return reConstruct_post_in_helper(post, in, Integer.MIN_VALUE);
    }

    public static TreeNode reConstruct_post_in_helper(int[] post, int[] in, int target) {
        if (inIdx < 0 || in[inIdx] == target) {
            return null;
        }
        TreeNode root = new TreeNode(post[postIdx]);
        postIdx --;
        root.right = reConstruct_post_in_helper(post, in, root.val);
        inIdx --;
        root.left = reConstruct_post_in_helper(post, in, target);
        return root;
    }

    public static void test15() {
        int[] pre = {5,2,1,4,7,8,9};
        int[] in = {1,2,4,5,8,7,9};
        int[] post = {1,4,2,8,9,7,5};

        TreeNode r1 = reConstruct_pre_in(pre, in);
        System.out.println("-----------------");
        preOrder(r1);
        System.out.println();
        inOrder(r1);
        System.out.println();
        postOrder(r1);
        System.out.println();
        System.out.println("-----------------");

        TreeNode r2 = reConstruct_post_in(post, in);

        System.out.println("==================");
        preOrder(r2);
        System.out.println();
        inOrder(r2);
        System.out.println();
        postOrder(r2);
        System.out.println();
        System.out.println("==================");
    }

    /*
     *
     */




    /*
     * read 4k
     * read one time
     *
     * follow up
     * read many times
     */

}
