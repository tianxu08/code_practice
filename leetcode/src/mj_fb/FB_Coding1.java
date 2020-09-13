package mj_fb;

import ds.Debug;
import ds.TreeNode;
import mj_linkedin.NestedInteger;

import java.util.*;
public class FB_Coding1 {
    public static void main(String[] args) {

    }

    /**
     * task1
	 * 给一组区间的二维数组，每一组代表一个接收方，每一个接收方有一组区间。然后给一个整数，要求找出所有区间包含这个整数的接收方
	 * 有序且不会有overlap
	 *
     * 例子就是.
	 * A想要接收1到4、7到9、12到15.
	 * B想要接收2到8、10到12
	 * C想要接收5到6
	 *
     * 如果给一个数字8，应该返回A和B
	 * 如果给个数字5，应该返回B和C
	 *
     * 其实第一题的基本思路就是binary search。有两种方法：
     *
     * 第一种遍历所有的list，对每一个list做binary search，找到了就加到结果里
	 *
     * 第二种，做一个反向的查找表，建立一个range到list of id的sorted list:
     * <start, end> -> [id1, id2, id3]
     * 然后对这个sorted list做binary search，找到了对应的range，直接返回range对应的id list
	 *
     */


    /**
     * 2给一个整数数组和一个整数K，找一个子数组的和等于K
     */

    /**
     * 组合问题的变种，给定的数组是一个无重复质数的数组，求所有无重复的乘积
     */

    /**
     * LC67
     */

    /**
     * deep copy graph
     */

    /**
     * binary addition
     */

    /**
     * find common friends
     */

    /**
     * BST <--> DDL
     */


    /**
     * LC17. Letter Combinations of a Phone Number
     */

    /**
     * LC Combination Sum系列，
     */

    /**
     * K-nearest points，
     */

    /**
     * LC10/LC44混合一下条件  10. Regular Expression Matching/ 44. Wildcard Matching
     */


    /**
     * first bad version以及parallel优化
     */

    /**
     * 合并两个sorted intervals.
     */

    /**
     * Given a task sequence tasks such as ABBABBC, and an integer k,
     * which is the cool down time between two same tasks.
     * Assume the execution for each individual task is 1 unit.
     * For example, if k = 3, then tasks BB takes a total of 5 unit time to finish,
     * because B takes 1 unit of time to execute, then wait for 3 unit,
     * and execute the second B again for another unit of time. So 1 + 3 + 1 = 5.
     *
     * Given a task sequence and the cool down time, return the total execution time.
     *
     * Follow up: Given a task sequence and the cool down time,
     * rearrange the task sequence such that the execution time is minimal.
     *
     */

    /**
     * 1 coding：给一个字符串，如果是相邻字母是一样的话就称为一个桥，把桥之间的字符替换成加号
	 * --a-d*-d-a-b 变成 --a-++++-a-b
	 * 解法： lastchar 记录上一个char, lastindex 记录 lastchar 的index.
	 * 要是和上一个相同， 之间换成+, 更新 lastchar, lastindex
	 * 要是不同， 只更新lastchar, lastindex
	 */

    /**
     * 2 给一个BST的先序遍历结果，返回中序遍历结果
     */


    /**
     * 3 序列化反序列化二叉树+LC143
     */


    /**
     * LC 498 Diagonal Traverse
     */

    /**
     * LC314 Vertical Binary tree traversal
     */

    /**
     * LC8 Given an input char array, convert it to integer
     */
    // assume every mission cost the same
    public static int missionOrder(int[] missions, int N) {
        if (missions == null || missions.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int time = 0;
        for(int i = 0;i < missions.length; i++) {
            time++;
            if (!map.containsKey(missions[i])) {
                map.put(missions[i], time);
            } else {
                if (time - map.get(missions[i]) > N) {
                    map.put(missions[i], time);
                } else {
                    // still need some time to cool down
                    time = N + map.get(missions[i]) + 1; // get the new start time
                    map.put(missions[i], time);
                }
            }
        }
        return time;
    }

    /*
     * if can change the order of mission, how to minimize the time
     * always arrange the mission with the highest frequency
     * if its mission interval is smaller than k, find the second highest mission
     * if all the mission's interval smaller than k, just add '*'
     * Time: O(n log n + n^2)  log n is the add or remove operation of treeSet
     * space: O(n)
     */
    public static String minizeTime(String input, int k) {
        if (input.length() <= 1) {
            return input;
        }
        StringBuilder stb = new StringBuilder();
        TreeSet<Mission> missionSet = new TreeSet<>();

        HashMap<Character, Integer> missionToTime = new HashMap<Character, Integer>();
        HashMap<Character, Integer> missionToFreq = new HashMap<Character, Integer>();
        StringBuilder result = new StringBuilder();

        // fill in the missionToFreq
        for(char mission: input.toCharArray()) {
            if (!missionToFreq.containsKey(mission)) {
                missionToFreq.put(mission, 1);
            } else {
                missionToFreq.put(mission, missionToFreq.get(mission) + 1);
            }
        }


        // fill in the missionSet
        for(Map.Entry<Character, Integer> entry: missionToFreq.entrySet()) {
            // key: mission name
            // value: mission freq
            missionSet.add(new Mission(entry.getKey(), entry.getValue()));
        }

        while(!missionSet.isEmpty()) {
            Mission select = null;
            // traverse the missionSet from high frequency to low frequency
            for(Mission mission: missionSet) {
                if (!missionToTime.containsKey(mission.name) ||
                        result.length() - missionToTime.get(mission.name) >= k) {
                    // if the mission(current mission) hasn't been scheduled or
                    // the time interval is larger than the cool down time, k
                    select = mission;
                    missionToTime.put(mission.name, result.length() + 1);
                    break;
                }
            }
            // if all the missions in the missionSet, the time interval is greater than cool down time,
            // k. append "*"
            if (select == null) {
                result.append("*");
            } else {
                // we have got the select mission
                result.append(select.name);
                // remove from the treeSet
                missionSet.remove(select);
                // update the frequency
                select.freq--;
                // add back to the treeSet
                if (select.freq > 0) {
                    missionSet.add(select);
                }
            }
        }
        return result.toString();
    }

    public static class Mission implements Comparable<Mission>{
        public char name;
        public int freq;
        public Mission(char _name, int _freq) {
            this.name = _name;
            this.freq = _freq;
        }

        @Override
        public int compareTo(Mission that) {
            if (this.freq == that.freq) {
                return this.name < that.name ? -1 : 1;
            }
            return this.freq > that.freq ? -1 : 1;
        }
    }

    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=167887&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
     * Given a tree, find the smallest subtree that contains all of the tree's deepest nodes.
     *                  a
     *               /  |  \
     *             b   c   d
     *           /   \
     *         e      f
     *       /       /  \
     *      h      i     j
     * depth of tree: 4
     * deepest nodes:[h,i,j]
     * least common ancestor of [h,i,j]: b
     * return: b
     *
     */

    /**
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=167887&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
     * Given a tree, find the smallest subtree that contains all of the tree's deepest nodes.
     *                  a
     *               /  |  \
     *             b   c   d
     *           /   \
     *         e      f
     *       /       /  \
     *      h      i     j
     * depth of tree: 4
     * deepest nodes:[h,i,j]
     * least common ancestor of [h,i,j]: b
     * return: b
     *
     */



    /**
     * 1 do BFS or DFS. find the deepest nodes List
     * 2 reduce to find the lowest common ancestor of the nodes in nodes list
     *
     * optimize?
     */


    /**
     * 第一题：plus one原题.
     * 第二题：一个数组内要是存在至少三个升序的数（array[x] < array[y] < array[z], x < y < z）就返回true，否则返回false。
     * lc上有类似题。
     */

    /*
     * 1 binary tree, find the LCA(Lowest Common Ancestor) of all deepest leaf
     *
     *      1
     *     / \
     *     2  3
     *       / \
     *       5 6
     *   return 3
     *
     *
     *      1
     *     /  \
     *     2  3
     *    /   / \
     *    4   5  6
     *
     *   return 1
     */
    public static void t1() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n6.left = n7;
        n6.right = n8;

        TreeNode LCA = M1_LCAAllDeepestLeaves(n1);
        System.out.println(LCA.val);
        System.out.println("--------------------");
        TreeNode LCA2 = M1_LCAAllDeepestLeaves_Iter(n1);
        if (LCA2 != null) {
            System.out.println(LCA2.val);
        } else {
            System.out.println("null");
        }

    }

    public static TreeNode M1_LCAAllDeepestLeaves(TreeNode root) {
        Element result = M1_helper(root);
        return result.node;
    }

    public static Element M1_helper(TreeNode node) {
        if (node == null) {
            return new Element(null, 0);
        }
        int depth = 0;
        Element leftE = M1_helper(node.left);
        Element rightE = M1_helper(node.right);

        depth = Math.max(leftE.depth, rightE.depth) + 1;

        if (leftE.depth == rightE.depth) {
            // the node is the LCA for the left and right
            return new Element(node, depth);
        } else if (leftE.depth > rightE.depth) {
            // the leftE's depth > rightE.depth, result will be in the leftE
            return new Element(leftE.node, depth);  // !!! here, leftE.node
        } else {
            // result will be in the rightE
            return new Element(rightE.node, depth); // !!! here, rightE.node
        }
    }

    public static class Element {
        TreeNode node;  // the LCA node of all deepest
        int depth;      // the depth of node
        public Element(TreeNode n, int d) {
            this.node = n;
            this.depth = d;
        }
    }

    /*
     * Iterative
     * use a hashmap to record parent of every node. do BFS and record the most left node and most right node of
     * each level
     * Then we have the most left and right node of the last Level
     * search into the hashMap until we find the same parent which is the LCA
     */
    public static TreeNode M1_LCAAllDeepestLeaves_Iter(TreeNode root) {
        if (root == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        map.put(root, null);  // root's parent is null
        TreeNode leftMost = null;
        TreeNode rithtMost = null;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i ++) {

                TreeNode cur = q.poll();
                if (i == 0) {
                    // record the mostLeft
                    leftMost = cur;
                }
                if (i == (size - 1)) {
                    // record the mostRight
                    rithtMost = cur;
                }

                if (cur.left != null) {
                    q.offer(cur.left);
                    map.put(cur.left, cur);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                    map.put(cur.right, cur);
                }
            }
        }

        if (leftMost == rithtMost) {
            return null;
        } else {
            // leftMost and rightMost are NOT same
            // the leftMost and rightMost have the same height.
            TreeNode leftP = map.get(leftMost);
            TreeNode rightP = map.get(rithtMost);
            while(leftP != null && rightP != null && leftP != rightP) {
                leftP = map.get(leftP);
                rightP = map.get(rightP);
            }
            return leftP;
        }
    }

    //multiple children
    public TreeNodeMulti findLca(TreeNodeMulti root) {
        return helper(root).node;
    }

    private Result helper(TreeNodeMulti root) {
        if (root == null) {
            return new Result(null, 0);
        }
        int depth = 0;
        List<Result> next = new ArrayList<>();
        for (TreeNodeMulti child : root.children) {
            next.add(helper(child));
        }
        // like the above's leftE and rightE
        Result deepest = new Result(null, 0);
        Result deepest2 = new Result(null, 0);
        for (Result result : next) {
            if (result.depth >= deepest.depth) {
                deepest2.node = deepest.node;
                deepest2.depth = deepest.depth;
                deepest.node = result.node;
                deepest.depth = result.depth;
            }
        }
        depth = 1 + deepest.depth;
        if (deepest.depth == deepest2.depth) {
            return new Result(root, depth);
        }
        // otherwise, return the (deepest.node, depth)
        return new Result(deepest.node, depth);
    }

    class Result {
        TreeNodeMulti node;
        int depth;
        public Result(TreeNodeMulti node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public class TreeNodeMulti {
        public int val;
        public List<TreeNodeMulti> children;
        public TreeNodeMulti(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

    }


    /*
     * M2:
     * 2. 就是按格式打印json，之前用过json不记得了格式细节了，店面小哥说无所谓，就打印就可以了
     * http://jsonprettyprint.com/
     * 类似这种的， 输入[1,2,3, {"id": 1, "name": "wang", "tag":[1,"home",2], "price":234}]
     * 要求打出来这个
     * [  1,  2,  3,  {    "id": 1,    "name": "wang",    "tag": [      1,      "home",      2    ],    "price": 234  }]
     */


    /*
     * M3.1
     * flatten nested list (e.g. [1,2,[3,4[5]]])
     * M3.2
     * binary tree level order traversal
     * M3.3
     * 给三个funtions: is_low(), is_mid(), is_high(). 让给一个数组排序, low的放在最前面, mid的放在中间, high的放在最后面.
     * 跟rainbow sort 是一个题，the difference is that array[i] == 0, in this task, is is_low(array[i])
     * 两个挡板，三个区域问题
     * ----------------------
     *     i     j   k
     * [0, i)  is_low
     * [i, j)  is_mid
     * [j, k]  unknown, to explore
     * (k, n - 1] is_high
     * init:
     * i = 0;
     * j = 0;
     * k = n - 1
     * condition: j <= k
     */
    //'flatten'
    public static class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> content;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.content = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                this.content.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return content.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!content.isEmpty()) {
                NestedInteger curNested = content.peek();
                if (curNested.isInteger()) {
                    return true;
                }
                content.pop();
                for (int i = curNested.getList().size() - 1; i >= 0; i--) {
                    content.push(curNested.getList().get(i));
                }
            }
            return false;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub

        }
    }

    //no Iterator
    //'Time complexity: O(n), space complexity: o(n)'
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<>();
        if (nestedList == null) {
            return result;
        }
        Stack<NestedInteger> content = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            content.push(nestedList.get(i));
        }
        while (!content.isEmpty()) {
            NestedInteger curNested = content.pop();
            if (curNested.isInteger()) {
                result.add(curNested.getInteger());
            }
            else {
                for (int i = curNested.getList().size() - 1; i >= 0; i--) {
                    content.push(curNested.getList().get(i));
                }
            }
        }
        return result;

    }
    class FlattenList {
        public void flattenPrint(ListNodeRecur head) {
            if (head == null) {
                return;
            }
            while (head != null) {
                System.out.printf("%d ", head.val);
                if (head.branch != null) {
                    flattenPrint(head.branch);
                }
                head = head.next;
            }
        }

        public ListNodeRecur flatten(ListNodeRecur head) {
            if (head == null) {
                return head;
            }
            ListNodeRecur fakeHead = new ListNodeRecur(0);
            helper(head, fakeHead);
            return head;
        }

        private ListNodeRecur helper(ListNodeRecur branch, ListNodeRecur head) {
            head.next = branch;
            ListNodeRecur prev = head;
            while (branch != null) {
                System.out.printf("%d ", branch.val);
                if (branch.branch != null) {
                    ListNodeRecur next = branch.next;
                    ListNodeRecur tail = helper(branch.branch, branch);
                    tail.next = next;
                    branch = next;
                    prev = tail;
                }
                else {
                    prev = prev.next;
                    branch = branch.next;
                }
            }
            return prev;
        }
    }

    class ListNodeRecur {
        public ListNodeRecur next;
        public ListNodeRecur branch;
        public int val;
        public ListNodeRecur(int val) {
            this.val = val;
        }
    }

    // color sort
    public static void t3_3() {
        int[] array = {0,1,1,1,0,2,1,0};
        System.out.println(Arrays.toString(array));
        M3_3_sortColor(array);
        System.out.println(Arrays.toString(array));
    }
    public static void M3_3_sortColor(int[] array) {
        if (array == null || array.length == 0) {
            return ;
        }
        int i = 0, j = 0, k = array.length - 1;
        while(j <= k && i < array.length && k >= 0) {
            if (array[j] == 0) {
                swap(array, i, j);
                System.out.println("i = " + i);
                System.out.println("j = " + j);
                i ++;
                j ++;
            } else if (array[j] == 1) {
                j ++;
            } else {
                // array[j] == 2
                swap(array, j, k);
                System.out.println("j =" + j);
                System.out.println("k = " + k);
                k --;
            }
        }
    }

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /*
     * M3.3.1
     * sort k color
     * method1:
     * every time, sort the min and max, after that, store the minIndex and maxIndex as the new range. k -= 2
     *
     * method2:
     * for the sorting color, we can always use count sort
     *
     */
    public static void t3_3_1() {
        int[] array = {9,1,4,2,5,4,2,3,8};
        System.out.println(Arrays.toString(array));
        int k = 10;
        M3_3_1_SortKColor(array, k);
        System.out.println(Arrays.toString(array));
    }
    public static void M3_3_1_SortKColor(int[] array, int k) {
        int leftB = 0, rightB = array.length - 1;
        while(k > 0) {
            // traverse the array and get the max and min
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int minIdx = leftB;
            int maxIdx = rightB;
            int idx = leftB;
            for(int i = leftB; i <= rightB; i ++) {
                min = Math.min(min, array[i]);
                max = Math.max(max, array[i]);
            }
            while(idx <= maxIdx && minIdx <= rightB && maxIdx >= leftB) {
                if (array[idx] == min) {
                    swap(array, minIdx, idx);
                    minIdx ++;
                    idx ++;
                } else if (array[idx] == max) {
                    swap(array, maxIdx, idx);
                    maxIdx --;
                } else {
                    idx ++;
                }
            }
            // after this, we get the min at the leftmost side and max at the rightmost side
            leftB = minIdx;
            rightB = maxIdx;
            k -= 2;
        }
    }

    // using counting sort
    // Time: O(n)
    // Space: O(n)
    public void countingSort(int[] colors) {
        int[] count = new int[3];
        for (int color : colors) {
            count[color]++;
        }
        int[] index = new int[3];
        int total = 0;
        for (int i = 0; i < 3; i++) {
            index[i] = total;
            total += count[i];
        }
        int[] temp = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            temp[index[colors[i]]] = colors[i];
            index[colors[i]]++;
        }
        for (int i = 0; i < colors.length; i++) {
            colors[i] = temp[i];
        }
    }


    /*
     * M4 merge 2 array in place
     * e.g
     * a = [2,4,6,8, -, -, -, -]
     * b = [1,3,5 7];
     * a has enough space to store b
     *
     */
    public static void M4_merge2ArraysInSpace(int[] a, int m, int[] b, int n) {
        int index = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0) {
            if (a[i] > b[j]) {
                a[index] = a[i];
                index --;
                i --;
            } else {
                a[index --] = a[j --];
            }
        }
        while(j >= 0) {
            a[index --] = b[j --];
        }
    }

    /*
     * M5
     * balance parentheses in a string
     * e.g
     * "(a)()" -> "(a)()"
     * "((bc)" -> "(bc)"
     * ")))a((" -> "a"
     * "(a(b)" ->"(ab)" or "a(b)"
     * Given a string with parentheses,
     * return a string with balanced parentheses by removing the fewest characters possible.
     * You cannot add anything to the string.
     *
     * Examples:
     * balance("()") -> "()"
     * balance(")(") -> ""
     * balance("(((((") -> ""
     * balance("(()()(") -> "()()"
     * balance(")(())(") -> "(())"
     * balance(")(())(") != "()()"
     * 就是删去string里面没有close的括号, 值留下close的括号
     */
    public static void t5() {
        String a = ")(())(";
        String ra = M5_remove2BalancedParenth(a);
        System.out.println(ra);

        String rev = findBalanceParentheses(a);
        System.out.println(rev);
    }

    public static String M5_remove2BalancedParenth(String input) {
        char[] arr = input.toCharArray();
        int L = 0, R = 0;
        // scan from left to right
        for(int i = 0; i < arr.length - 1; i ++) {
            if (arr[i] == '(') {
                L++;
            }
            if (arr[i] == ')') {
                R++;
            }
            // if R > L, the right P is unmatched
            // mark this ')' to invalid
            //
            if (R > L) {
                arr[i] = '*';
                R--;
            }
        }

        // reset L and R
        L = 0;
        R = 0;

        // scan from right to left
        for(int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ')') {
                R++;
            }
            if (arr[i] == '(') {
                L++;
            }
            // if L > R, the left P is unmatched.
            // mark this '(' as invalid
            if (L > R) {
                arr[i] = '*';
                L--;
            }
        }
        // traverse the arr and get the new string
        StringBuilder stb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] != '*') {
                stb.append(arr[i]);
            }
        }
        return stb.toString();
    }

    public static String findBalanceParentheses(String input) {
        System.out.println("input = " + input);
        String result = deleteCloseParenthes(input);
        System.out.println("result = " + result);
        result = deleteOpenParenthes(result);
        System.out.println("result2 = " + result);
        return result;
    }

    private static String deleteCloseParenthes(String input) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (char letter : input.toCharArray()) {
            // append current letter to result
            result.append(letter);
            if (letter == '(') {
                count++; // record the counter of '('
            } else if (letter == ')') {
                // if this is a close parenthesis ')'
                if (count > 0) { // there are unmatched '(', count --
                    count--;
                } else {
                    // count == 0, there is no unmatched '(', so this is an unmatched ')', delete it from result
                    result.deleteCharAt(result.length() - 1);
                }
            }
        }
        // after the above loop, we deleted unmatched ')', close parentesis
        return result.toString();
    }

    private static String deleteOpenParenthes(String input) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        // from right to left
        for (int i = input.length() - 1; i >= 0; i--) {
            char letter = input.charAt(i);
            result.append(letter);
            if (letter == ')') {
                count++;
            } else if (letter == '(') {
                if (count > 0) {
                    count--;
                } else {
                    result.deleteCharAt(result.length() - 1);
                }
            }
        }
        // we delete the unmatched '(', open parenthesis
        return result.reverse().toString();
    }


    /*
     * M5.2
     * check whether a string is a valid group of parenthesis
     * http://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
     * algorithm:
     * 1 declare a stack for character
     * 2 traverse the string, when meet a starting p('(' or '[' or '{'), add it into stack
     * 3 					  when meet a closing p(')', or ']', or '}') pop from the stack, and check whether
     * 						  the poped left P matches the current right P, if NOT, return false
     * 4 after the traversal, if stack is empty, return true, otherwise, return false
     *
     * LC 20
     * https://leetcode.com/problems/valid-parentheses/
     */
    public static void t5_2() {
        String input = "{[[()]]}";
        boolean rev = M5_2_valid_balanced_parenthesis(input);
        System.out.println("rev = " + rev);
    }

    public static boolean M5_2_valid_balanced_parenthesis(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        LinkedList<Character> stack = new LinkedList<Character>();
        for(int i = 0; i < input.length(); i ++) {
            char cur = input.charAt(i);
            if (cur =='(' || cur == '[' || cur == '{') {
                stack.offerLast(cur);
            } else {
                // cur is closing P
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char top = stack.pollLast();
                    if (!M5_2_match(top, cur)) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty() ? true : false;

    }

    public static boolean M5_2_match(Character c1, Character c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '[' && c2 == ']') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else {
            return false;
        }
    }
    // we can only use a counter to store unmatched '('
    public static void t5_2_1() {
        String str = "((()))()";
        boolean rev = M5_2_1_isValidParenthesis(str);
        System.out.println("rev = " + rev);
    }
    public static boolean M5_2_1_isValidParenthesis(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        int counter = 0;
        for(int i = 0; i < str.length(); i ++) {
            char cur = str.charAt(i);
            if (cur == '(') {
                counter ++;
            } else if (cur == ')') {
                if (counter > 0) {
                    counter --;
                } else {
                    // counter == 0
                    // this ')' cannot be matched, since there is no more '(' left
                    return false;
                }
            }
        }
        return counter == 0;
    }


    /*
     * M5.3
     * LC301. Remove Invalid Parentheses
     * Remove the minimum number of invalid parentheses in order to make the input string valid.
     * Return all possible results.
     * Note: The input string may contain letters other than the parentheses ( and ).
     * 删除最小数量的括号， 返回所有结果， string 可能包括 除 （ and ) 之外的其他字符
     *
     * "()())()" -> ["()()()", "(())()"]
     * "(a)())()" -> ["(a)()()", "(a())()"]
     * ")(" -> [""]
     *
     */
    public static void t5_3() {
        String a = "(a)())()";
        List<String> rev = M5_3_getAllValidP(a);
        System.out.println(rev);
    }

    public static List<String> M5_3_getAllValidP(String input) {
        int removeLeft = 0, removeRight = 0;
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                removeLeft ++;
            } else if(input.charAt(i) == ')') {
                if (removeLeft > 0) {
                    removeLeft --;
                } else {
                    removeRight ++;
                }
            }
        }
        System.out.println("remLeft = " + removeLeft);
        System.out.println("remright = " + removeRight);
        HashSet<String> set = new HashSet<String>();
        M5_3_helper(input, set, removeLeft, removeRight, 0, 0, new StringBuilder());

        return new ArrayList<String>(set);
    }
    public static void M5_3_helper(String input, HashSet<String> set,
                                   int removeLeft, int removeRight,
                                   int index, int unmatchedLeft, StringBuilder stb) {
        if (removeLeft < 0 || removeRight < 0 || unmatchedLeft < 0) {
            return ;
        }
        if (index == input.length() && removeLeft == 0 &&
                removeRight == 0 && unmatchedLeft == 0) {
            set.add(stb.toString());
            return ;
        }

        char curCh = input.charAt(index);
        int len = stb.length();
        System.out.println("len = " + len);

        if (curCh == '(') {
            // don't use it
            M5_3_helper(input, set, removeLeft - 1, removeRight, index + 1, unmatchedLeft, stb);
            // use it
            M5_3_helper(input, set, removeLeft, removeRight, index + 1, unmatchedLeft + 1, stb.append(curCh));
        } else if (curCh == ')') {
            // don't use it
            M5_3_helper(input, set, removeLeft, removeRight - 1, index + 1, unmatchedLeft, stb);
            // use it
            M5_3_helper(input, set, removeLeft, removeRight, index + 1, unmatchedLeft - 1, stb.append(curCh));

        } else {
            // other character
            M5_3_helper(input, set, removeLeft, removeRight, index + 1, unmatchedLeft, stb.append(curCh));
        }
        System.out.println(stb.toString());
        // backtracking
        // set the stb to previous state
        stb.setLength(len);
    }


    /*
     * M5.4
     * generate all valid parenthesis
     * n = 2
     * (())  ()()
     * n = 3
     * ((())), (()()), (())(), ()(()), ()()()
     */
    public static void t5_4() {
        int n = 3;
        List<String> rev = M5_4_generateAllP(n);
        System.out.println(rev);
    }

    public static List<String> M5_4_generateAllP(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        M5_4_helper(n, n, stb, result);
        return result;
    }
    public static void M5_4_helper(int leftR, int rightR, StringBuilder stb, List<String> result) {
        if (leftR == 0 && rightR == 0) {
            // get a valid solution
            result.add(stb.toString());
            return ;
        }
        if (leftR > 0) {
            // add '('
            M5_4_helper(leftR - 1, rightR, stb.append('('), result);
            // backtracking
            stb.deleteCharAt(stb.length() - 1);
        }
        if (rightR > leftR) {
            M5_4_helper(leftR, rightR - 1, stb.append(')'), result);
            stb.deleteCharAt(stb.length() - 1);
        }
    }


    /*
     * M5.5
     * generate all valid parenthesis 2
     * follow up for M5.4
     * input m: num of ()
     * 		 n: num of []
     * 		 k: num of {}
     * generate all the valid permutation of parenthesis
     * e.g
     * m = 1, n = 1, k = 1
     * ()[]{}  ([]){} ([]{}), etc
     */
    public static void t5_5() {
        int m = 1, n = 1, k = 1;
        List<String> result = M5_5_allParenthesis(m, n, k);
        System.out.println(result);
        System.out.println(result.size());
    }

    public static List<String> M5_5_allParenthesis(int m, int n, int k) {
        StringBuilder stb = new StringBuilder(); // use to store intermediate result
        List<String> result = new ArrayList<String>();
        LinkedList<Integer> stack = new LinkedList<Integer>(); // store the index of left P
        int[] input = {m, m, n, n, k, k};
        char[] chArr = {'(', ')', '[',']', '{', '}'};
        int finalLen = 2 * m + 2 * n + 2 * k;
        M5_5_helper(input, chArr, result, stb, stack, finalLen);
        return result;
    }

    public static void M5_5_helper(int[] input, char[] chArr,
                                   List<String> result, StringBuilder stb,LinkedList<Integer> stack, int finalLen) {
        System.out.println(Arrays.toString(input));
        if (stb.length() == finalLen) {
            // get a resonable solution
            result.add(stb.toString());
            return ;
        }
        for(int i = 0; i < input.length; i ++) {
            if (i % 2 == 0) {
                // left P
                if (input[i] > 0) { // make sure that there are still Left P remaining.
                    // add this P's index into stack
                    stack.offerFirst(i);
                    // append this P into stb
                    stb.append(chArr[i]);
                    input[i] --;
                    M5_5_helper(input, chArr, result, stb, stack, finalLen);
                    System.out.println("----");
                    System.out.println(Arrays.toString(input));
                    System.out.println("stb is : " + stb.toString());
                    System.out.println("stack.size = " + stack.size());

                    // backtracking
                    stack.pollFirst();
                    stb.deleteCharAt(stb.length() - 1);
                    input[i] ++;
                }
            } else {
                // i % 2 == 1
                // right P
                if (input[i] > 0) {
                    // make sure that there are still right P remaining.
                    if (!stack.isEmpty()) {
                        int lastLeftIdx = stack.peekFirst();
                        System.out.println("lastLeftIdx = " + lastLeftIdx);
                        if (lastLeftIdx == i - 1) {  // this right P matches last Left P
                            // pop the lastLeftIdx from stack
                            stack.pollFirst();
                            // add the current right P into stb
                            stb.append(chArr[i]);
                            input[i] --;
                            M5_5_helper(input, chArr, result, stb, stack, finalLen);
                            // backtracking
                            // put the lastLeftIdx back
                            stack.offerFirst(lastLeftIdx);
                            // delete the last Char in the stb
                            stb.deleteCharAt(stb.length() - 1);
                            input[i] ++;
                        }
                    }
                }
            }
        }
    }

    /*
     * M6.1
     * nearest K points to origin point(0, 0)  max heap
     *
     * M6.2
     *
     * nearest K points to origin point(0, 0)  quick select
     *
     * M6.3
     * decode way, optimize space complixity to O(1)
     */

    /*
     * M6.1
     * method 1
     * use max heap, the size of the maxHeap is k
     * for each new point, check whether the distance(new point, origin) < distance(maxHeap.peek, origin)
     * if yes, pop the head of maxHeap and insert the new point into maxHeap
     * otherwise, continue to the next point
     * the distance(point(x, y), origin) is defined as |x| + |y|
     */
    public static void t6_1() {
        Point[] points = {
                new Point(0, 0),
                new Point(10, 5),
                new Point(4, 5),
                new Point(8, 5),
                new Point(5, 10)
        };
        int k = 3;
        List<Point> result = M6_1_NearestKPoints(points, k);
        for(Point p : result) {
            System.out.println(p);
        }
        System.out.println("-----------------------------");
        List<Point> result2 = M6_2_NearestKPoints(points, k);
        for(Point p: result2) {
            System.out.println(p);
        }


    }


    /*
     * Time: O(n * log k) we need to manipulate n elements, for each element,
     * insert into heap, O(log k), total n * log k
     * Space: O(k) : the size of heap is k, so the space of heap is O(k)
     */
    public static List<Point> M6_1_NearestKPoints(Point[] points, int k) {
        if (points == null || points.length == 0) {
            return new ArrayList<Point>();
        }
        if (points.length < k) {
            return Arrays.asList(points);
        }

        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                // TODO Auto-generated method stub
                int dist1 = Math.abs(o1.x) + Math.abs(o1.y);
                int dist2 = Math.abs(o2.x) + Math.abs(o2.y);
                if (dist1 == dist2) {
                    return 0;
                }
                return dist1 > dist2 ? -1 : 1;
            }
        });

        for(int i = 0; i < points.length; i ++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(points[i]);
            } else {
                Point top = maxHeap.peek();
                int distTop = Math.abs(top.x) + Math.abs(top.y);
                int distCur = Math.abs(points[i].x) + Math.abs(points[i].y);
                if (distCur < distTop) {
                    maxHeap.poll();
                    maxHeap.offer(points[i]);
                }
            }
        }

        List<Point> result = new ArrayList<Point>();
        while(!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }
        return result;
    }

    public static class Point{
        int x;
        int y;
        public Point(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }
        @Override
        public String toString() {
            return "[ " + x + " " + y + " ]";
        }
    }

    public static int M6_3_distance(Point p) {
        return p.x * p.x + p.y * p.y;
    }

    /*
     * M5_3
     * method 2
     * use quick select to find the nearest K
     * it's similar to "find the smallest k element in an array"
     * the difference is when we compare two points, we need to compare their distance to origin
     */
    public static List<Point> M6_2_NearestKPoints(Point[] points, int k) {
        int left = 0, right = points.length - 1;
        M6_2_quickSelect(points, left, right, k - 1);
        List<Point> result = new ArrayList<Point>();
        for(int i = 0; i < k; i ++) {
            result.add(points[i]);
        }
        return result;
    }

    public static void M6_2_quickSelect(Point[] points, int left, int right, int k){
        int partitionIdx = M6_2_Partition(points, left, right);
        if (partitionIdx == k) {
            return ;
        } else if (partitionIdx < k) {
            M6_2_quickSelect(points, partitionIdx + 1, right, k);
        } else {
            // partitionIdx > k
            M6_2_quickSelect(points, left, partitionIdx - 1, k);
        }
    }

    public static int M6_2_Partition(Point[] points, int left, int right){
        // ues the point[right] as pivot
        int start = left, end = right - 1;
        Point pivot = points[right];
        while(start <= end) {

            if (M6_3_distance(points[start]) < M6_3_distance(pivot)) {
                start ++;
            } else if (M6_3_distance(points[end]) >= M6_3_distance(pivot)) {
                end --;
            } else {
                // swap the start and end
                M6_2_swapPoint(points, start, end);
                start ++;
                end --;
            }
        }
        M6_2_swapPoint(points, start, right);  // put the pivot into its position
        return start;  // now, the start is the index of pivot
    }

    public static void M6_2_swapPoint(Point[] points, int x, int y) {
        Point temp = points[x];
        points[x] = points[y];
        points[y] = temp;
    }



    /*
     * M6_3
     * decode way
     * LC91
     * 91. Decode Ways
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     *
     * For example,
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * The number of ways decoding "12" is 2.
     *
     * M1:
     * Rec
     * M2:
     * DP  time: O(n), space O(n)
     * M3:
     * DP time: O(n), space O(1)
     */

    public static int M6_3_decodeWaysRec(String s) {
        return 0;
    }

    /*
     * M6-4
     * return  all possible decode ways.
     */
    /*
     * 91 Decode Ways
     * https://leetcode.com/problems/decode-ways/
     *
     * M[i]: the # of decode ways that s[0..i - 1] has
     *
     * M[i] = M[i - 1] if s[i - 1] is a valid char
     *        M[i - 1] + M[i - 2]  is s[i - 2, i - 1] are valid two digits
     *
     * 定义数组number，number[i]意味着：字符串s[0..i-1]可以有number[i]种解码方法。
     * 回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
     * 但不同的是本题有多种限制：
     * 第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
     * 第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。
     * 1010，生成的number数组为：[1,1,1,1,1]
     * 10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]
     */
    public static void test6_3() {
        String s = "1234";
        int rev = task91_numDecodings(s);
        System.out.println("rev = " + rev);
        int rev2 = task91_decodeWayRec(s);
        System.out.println("rev2 = " + rev2);

        int rev3 = task91_DecodeWayDPOPT(s);
        System.out.println("rev3 = " + rev3);
    }
    public static int task91_numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] M = new int[s.length() + 1];
        M[0] = 1; // NOTE!!! Here.

        //s[0]
        if (task91_isValid(s.substring(0, 1))) {
            M[1] = 1;
        } else {
            M[1] = 0;
        }

        for (int i = 2; i <= s.length(); i++) {
            if (task91_isValid(s.substring(i - 1, i))) {
                // s[i-1, i]
                M[i] += M[i - 1];
            }

            if (task91_isValid(s.substring(i - 2, i))) {
                // s[i - 2, i]
                System.out.println(s.substring(i - 2, i));
                M[i] += M[i - 2];
                System.out.println("M[i] = " + M[i]);
            }
        }
        Debug.printArray(M);
        return M[s.length()];
    }
    public static int task91_decodeWayRec(String str) {
        if (str.length() == 0) {
            return 1;
        }

        if (str.length() == 1 ) {
            if (task91_isValid(str)) {
                return 1;
            } else {
                return 0;
            }
        }

        int cur = 0;
        int len = str.length();

        String lastOne = str.substring(len - 1, len);
        if (task91_isValid(lastOne)) {
            cur += task91_decodeWayRec(str.substring(0, len - 1));
        }

        String lastTwo = str.substring(len - 2, len);
        if (task91_isValid(lastTwo)) {
            cur+= task91_decodeWayRec(str.substring(0, len - 2));
        }

        return cur;
    }

    public static int task91_DecodeWayDPOPT(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return s.charAt(0) != 0 ? 1 : 0;
        }

        int secondLast = 1;
        int last = s.charAt(0) != 0 ? 1 : 0;

        for(int i = 2; i <= s.length(); i ++ ){
            int curNum = 0;
            if (task91_isValid(s.substring(i - 1, i))) {
                curNum += last;
            }
            if (task91_isValid(s.substring(i - 2, i))) {
                curNum += secondLast;
            }

            // update last and secondLast
            secondLast = last;
            last = curNum;
        }

        return last;
    }


    public static boolean task91_isValid(String s) {
        if (s.charAt(0) == '0') {
            // starting with 0
            return false;
        }
        int val = Integer.parseInt(s);
        return val >= 1 && val <= 26;
    }

    /*
     * follow up
     * 给定一个数列，比如1234，将它match到字母上，1是A，2是B等等，
     * 那么1234可以是 ABCD 但是还可以是12是L，所以1234也可以写作 LCD 或者 AWD 给定一个定长的序列（可以非常长），
     * 请给出solution输出所有可能的字母组合（consecutive的，只要连续的组合）。
     * 链接: https://instant.1point3acres.com/thread/176238
     * 来源: 一亩三分地
     */
    public static void test91_2() {
        String str = "1234";
        List<String> result = task91_decodeAllResult(str);
        System.out.println(result);
    }

    public static List<String> task91_decodeAllResult(String str) {
        char[] map = {' ','A','B','C', 'D', 'E', 'F', 'G', 'H','I','G', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        System.out.println(map.length);
        for(int i = 1; i < map.length; i ++) {
            System.out.println("[  " + i + " : " + map[i] + "  ]");
        }
        List<String> result = new ArrayList<String>();
        StringBuilder stb = new StringBuilder();
        task91_helperAllResult(str, 0, result, stb, map);
        return result;
    }

    public static void task91_helperAllResult(String str, int index,
                                              List<String> result, StringBuilder stb, char[] map) {
        if (index == str.length()) {
            result.add(stb.toString());
            return;
        }
        // decode 1 digit
        int i = (int)(str.charAt(index) - '0');

        stb.append(map[i]);
        task91_helperAllResult(str, index + 1, result, stb, map);
        stb.deleteCharAt(stb.length() - 1);

        // decode 2 digits
        if (index < str.length() - 1) {
            // get the next digit
            int j = (int)(str.charAt(index + 1) - '0');
            if (i * 10 + j <= 26) {
                stb.append(map[i * 10 + j]);
                task91_helperAllResult(str, index + 2, result, stb, map);
                stb.deleteCharAt(stb.length() - 1);
            }
        }
    }




    /*
     * M7
     * 一骑士在一个无限大的国际象棋棋盘，有障碍. 骑士行走模式:https://en.wikipedia.org/wiki/Knight_(chess)
     * basic: bfs
     * calculate the shortest path to some target.
     * basic code: bfs. more info on 1point3acres.com
     *
     * follow up:
     * how to optimize: A*, pruning.
     * edge case: how to end the program? what kinds of target is unreachable and how to avoid?.
     *
     * knight step:
     * make one step, then virtually, make two steps
     * cannot jump out of bound
     * cannot jump on obstacle
     *
     */
    /*
     * use BFS
     * start from the start point and do BFS.
     *
     */
    public static void t7() {
        int[][] matrix = {
                {0,0, 0, -1},
                {0,0, -1, },
                {},
                {}
        };
    }

    public static int M7_ShortestPath(int[][] matrix, Point start, Point destination) {
        int rLen = matrix.length;
        int cLen = matrix[0].length;

        boolean[][] visited = new boolean[rLen][cLen];
        Queue<Point> q = new LinkedList<Point>();
        q.offer(start);
        visited[start.x][start.y] = true;
        int count = 0;
        int shortestDist = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Point cur = q.poll();
                if (cur.x == destination.x && cur.y == destination.y) {
                    // get the shortest result;
                    shortestDist = Math.min(shortestDist, count);
                    break;
                }
                List<Point> nextPos = M7_nextPositions(matrix, cur.x, cur.y, visited);
                q.addAll(nextPos);
            }
            count ++;
        }
        return shortestDist;
    }

    public static List<Point> M7_nextPositions(int[][] matrix, int x, int y, boolean[][] visited) {
        List<Point> result = new ArrayList<Point>();
        int rLen = matrix.length;
        int cLen = matrix[0].length;

        for(int i = 0; i < 4; i ++) {
            int firstX = dx1[i] + x;
            int firstY = dy1[i] + y;
            if (firstX >= 0 && firstX < rLen && firstY >= 0 && firstY < cLen &&
                    matrix[firstX][firstY] != -1 && !visited[firstX][firstY] ) {
                for(int j = 0; j < 4; j ++) {
                    int secondX = firstX + dx2[j];
                    int secondY = firstY + dx2[j];
                    if (secondX >= 0 && secondY < rLen && secondY >= 0 && secondY < cLen &&
                            matrix[secondX][secondY] != -1 && !visited[secondX][secondY]) {
                        // this is a valid position
                        result.add(new Point(secondX, secondY));
                        visited[secondX][secondY] = true;
                    }
                }
            }
        }
        return result;
    }

    public static int[] dx1 = {0, 0,  1,-1};
    public static int[] dy1 = {1, -1, 0, 0};
    public static int[] dx2 = {0, 0, 2, -2};
    public static int[] dy2 = {2, -2, 0, 0};


    /*
     * M7
     * A* algorithm
     */


    /*
     * M8
     * Longest Consectuive sequence
     * Use the HashMap to record the longest length of the consecutive sequence.
     *
     */
    public static void t8() {
        int[] a = {1,4,3,5,2,9,10};
        int rev = M8_longestConsecutiveSequence(a);
        System.out.println("rev = " + rev);
        int rev2 = M8_longestConsectiveSequence2(a);
        System.out.println("rev2 = " + rev2);
    }

    public static int M8_longestConsecutiveSequence(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 1;
        for(int i = 0; i < a.length; i++) {
            int curElem = a[i];
            if (map.containsKey(curElem)) {
                continue;
            }
            int left = map.containsKey(curElem - 1) ? map.get(curElem - 1) : 0;
            int right = map.containsKey(curElem + 1) ? map.get(curElem + 1) : 0;
            int len = left + right + 1;
            map.put(curElem, len);
            map.put(curElem - left, len);
            map.put(curElem + right, len);
            result = Math.max(result, len);
        }
        return result;
    }

    public static int M8_longestConsectiveSequence2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        int result = 1;
        for(int i = 0; i < a.length; i ++) {
            map.put(a[i], false);
        }

        for(int i = 0; i < a.length; i ++) {
            int curElem = a[i];
            if (map.get(curElem) == true) {
                continue;
            }
            int leftLen = M8_getLen(map, curElem, -1);
            int rightLen = M8_getLen(map, curElem, 1);
            int curLen = leftLen + rightLen + 1;
            result = Math.max(result, curLen);
        }
        return result;
    }

    public static int M8_getLen(Map<Integer, Boolean> map, int elem, int step) {
        int len = 0;
        int curElem = elem + step;
        while(map.containsKey(curElem)) {
            len ++;
            map.put(curElem, true);
            curElem += step;
        }
        return len;
    }


    /*
     * M9
     * reconstruct BST from pre-order sequence
     * (preOrder & BST)
     */
    public static void t9() {
        int[] preOrder = {5,3,1,4,7,6, 8};
        TreeNode root = M9_preOrder2BST(preOrder);
        Debug.preOrderTraversal(root);
        System.out.println();
        Debug.inOrderTraversal(root);
        System.out.println();
        Debug.postOrderTraversal(root);
    }
    public static TreeNode M9_preOrder2BST(int[] preOrder) {
        return M9_helper(preOrder, 0, preOrder.length - 1);
    }
    public static TreeNode M9_helper(int[] preOrder, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(preOrder[start]);
        }
        TreeNode root = new TreeNode(preOrder[start]);
        int idx = start + 1;
        // look for the first element > preOrder[start], which is the root.right
        while (idx <= end && preOrder[idx] < preOrder[start]) {
            idx ++;
        }
        // link the left child
        root.left = M9_helper(preOrder, start + 1, idx -1);
        // link the right child
        root.right = M9_helper(preOrder, idx, end);
        return root;
    }

    /*
     * M9.1
     * postOrder to BST
     */
    public static void t9_1() {
        int[] postOrder = {1,4,3,6,8,7,5};
        TreeNode root = M9_1_Post2BST(postOrder);
        Debug.preOrderTraversal(root);
        System.out.println();
        Debug.inOrderTraversal(root);
        System.out.println();
        Debug.postOrderTraversal(root);
    }
    public static TreeNode M9_1_Post2BST(int[] postOrder){
        return M9_1_helper(postOrder, 0, postOrder.length - 1);
    }
    public static TreeNode M9_1_helper(int[] postOrder, int start, int end){
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(postOrder[start]);
        }
        TreeNode root = new TreeNode(postOrder[end]);
        int idx = end - 1;
        while(idx >= start && postOrder[idx] > postOrder[end]) {
            idx --;
        }
        root.left = M9_1_helper(postOrder, start, idx);
        root.right = M9_1_helper(postOrder, idx + 1, end - 1);
        return root;
    }



    /*
     * M10
     * move 0s to the end of array
     */
    public static void t10() {
        int[] arr = {0,1,2,0,1,5,0,0,0,2,7};
        int[] rev = M10_move0s(arr);
        System.out.println(Arrays.toString(rev));
    }
    public static int[] M10_move0s(int[] arr) {
        int idx = 0;
        for(int i = 0; i < arr.length; i ++) {
            if (arr[i] != 0) {
                arr[idx ++] = arr[i];
            }
        }
        return Arrays.copyOf(arr, idx);
    }


    /*
     * M11
     * consecutive sequence number equals to K
     *
     *
     */
    public static void t11() {
        int[] a = {15, 2, 4, 8, 9, 5, 10, 23};
        int k = 23;
        boolean rev = M11_consecutiveSeqEqualsK(a, k);
        System.out.println("rev = " + rev);
        boolean rev2 = M11_consecutiveSeqEqualsK2(a, k);
        System.out.println("rev2 = " + rev2);
    }
    public static boolean M11_consecutiveSeqEqualsK(int[] a, int k) {
        int n = a.length;
        int[] presum = new int[n];  // preSum[i] is the sum of a[0]..a[i]
        presum[0] = a[0];


        for(int i = 1; i < n; i++ ) {
            presum[i] = presum[i - 1] + a[i];
        }
        // reduce to two difference
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < n; i ++) {
            if (a[i] == k || presum[i] == k || set.contains(presum[i] - k)) {
                return true;
            } else {
                set.add(presum[i]);
            }
        }
        return false;
    }

    /*
     * Time: O(n)
     * Space: O(1)
     * 快慢指针
     * 有正有负 没法这么搞..
     * assume: the elements in the array is no_negative
     */
    public static boolean M11_consecutiveSeqEqualsK2(int[] a, int k) {
        int cur_sum = a[0];
        int slow = 0;
        for(int fast = 1; fast < a.length; fast++) {
            // shrink the window
            while (cur_sum > k && slow < fast ) {
                cur_sum -= a[slow];
                slow ++;
            }
            if (cur_sum == k) {
                return true;
            }
            cur_sum += a[fast];
        }
        return false;
    }


    /*
     * M12
     * LC28
     * implement strStr()
     * return the index of the first occurrence of needle in haystack, or -1 is needle is NOT part of haystack
     */
    public static int M12_strStr_BF(String text, String pattern) {
        return -1;
    }

    /*
     * M13
     * LC111
     * Minimum Depth of Binary Tree
     */
    public static int task111_minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return task111_MinDepthHelper(root);
    }

    public static int task111_MinDepthHelper(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftMin = task111_MinDepthHelper(root.left);
        int rightMin = task111_MinDepthHelper(root.right);

        return Math.min(leftMin, rightMin) + 1;
    }


    /*
     * M14
     * read4
     * read4 multiple time calls
     * https://segmentfault.com/a/1190000003794420
     *
     * task157:
     * read n chars given read4
     *
     */
    public static int task157_readOPT(char[] buf, int n) {
        for(int i = 0; i < n; i += 4) {
            char[] temp = new char[4];
            int tempLen = read4(temp);
            // copyt the temp to buf, the length is the min(tempLen, n - i)
            System.arraycopy(temp, 0, buf, i, Math.min(tempLen, n - i));
            //
            if (tempLen < 4) {

                return Math.min(i + tempLen, n);
            }
        }
        // here, the n is 4 的倍数
        return n;
    }

    /*
     * task158
     * call multiple times
     * corner case:
     * 1 first call, read4 读出的多余的字符就暂时存起来，留着第二次调用
     * 2 second call, 调用第一次存起来的字符，要是还没用完， 就留着给第三次调用使用
     * use queue
     */
    public static int task158_read2OPT(char[] buf, int n) {
        Queue<Character> remaining = new LinkedList<Character>();
        // if q is NOT empty, read the content from q
        int i = 0;
        while(i < n && !remaining.isEmpty()) {
            buf[i] = remaining.poll();
            i++;
        }
        // if we still need to read via read(char[] buf)
        for(; i < n; i = i + 4) {
            char[] temp = new char[4];
            int tempLen = read4(temp);
            // if the read chars is more than the number that we need
            if (tempLen > n - i) {
                // copy the n - i chars into buf
                System.arraycopy(temp, 0, buf, i, n - i);
                // put the remaining into queue(remaining)
                for(int j = n - i; j < tempLen; j ++) {
                    remaining.offer(temp[j]);
                }
            } else {
                // the number of read chars is less than the number that we need (n - i)
                // copy the tempLen chars
                System.arraycopy(temp, 0, buf, i, tempLen);
            }
            // if tempLen < 4, we have finished read the file.
            // return min(tempLen + i, n)
            return Math.min(i + tempLen, n);
        }
        // if goes here, return the n
        return n;
    }
    public static int read4(char[] buf) {
        return -1;
    }


    /*
     * M15 buy and sell stock.
     * LC121 best time to buy and sell stock
     * 		 at most 1 transaction(buy and sell)
     * LC122 best time to buy and sell stock2
     * 		 complete as many txn as you like (buy and sell stock multiple times)
     * LC123 best time to buy and sell stock3
     * 		 complete at most two txn
     * 		 You may not engage in multiple transactions at the same time
     *       (ie, you must sell the stock before you buy again).
     * LC188 best time to buy and sell stock4
     * 		 ( You may complete at most k transactions.)
     * LC309 best time to buy and sell stock with colldown
     * 		 (complete as many transactions as you like, )
     *       (you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).)
     *       (After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day))
     */

    /*
     * LC121, find the max difference between prices[i] and prices[j], j > i
     * maintinan a min so far
     * scan from left to right.
     * every time, update the min,  and calculate the curProfit and update the maxProfit
     */

    public static void testM15_121() {
        int[] a = {7,1,5,3,6,4};
        int rev = M15_lc121_maxprofit_1txn(a);
        System.out.println("rev = " + rev);
    }

    public static int M15_lc121_maxprofit_1txn(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i ++) {
            // update the min and max
            min = Math.min(min, prices[i]);
            int curProfit = prices[i] - min;

            maxProfit = Math.max(maxProfit, curProfit);

        }
        return maxProfit;
    }

    public static int M15_lc122_maxprofit_ntxn(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // traverse the prices, get the difference of all ascending elements
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i ++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }


    /*
     * at most two transactions
     * left[i]: the maxprofit in the previous [0..i] day
     * right[i]: the maxprofit in the day [i..n - 1]
     *
     * for each element prices[i], the left[i] and right[i] is the maxprofit
     * left[i] = max(left[i - 1], price[i] - minLeft)
     * rigth[i] = max(right[i + 1], maxright - price[i])
     */


    public static int M15_lc123_maxprofit_2txn(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] leftP = new int[len];
        int[] rightP = new int[len];

        leftP[0] = 0;
        int leftMin = prices[0];
        for(int i = 1; i < len; i++) {
            // update the leftMin
            leftMin = Math.min(leftMin, prices[i]);
            int curProfit = prices[i] - leftMin;
            leftP[i] = Math.max(leftP[i - 1], curProfit);
        }

        rightP[len - 1] = 0;
        int rightMax = prices[len - 1];
        for(int j = len - 2; j >= 0; j--) {
            // update the rightMax
            rightMax = Math.max(rightMax, prices[j]);
            int curProfit = rightMax - prices[j];
            rightP[j] = Math.max(rightP[j + 1], curProfit);
        }

        // scan from 0..n - 1
        int maxProfit = 0;
        for(int i = 0; i < len; i ++) {
            maxProfit = Math.max(maxProfit, leftP[i]+ rightP[i]);
        }
        return maxProfit;
    }

    /*
     * complete exactly k txn
     */



    /*
     * lc188
     * complete at most K txn
     *
     * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
     *
     * !!! finish later
     */

    /*
     * Best Time to Buy and Sell Stock IV
     *
     * 简单的做法
     * k transcations
     * state: f[i][j]表示前i天进行j次交易，能够获得的最大收益
     * function: f[i][j] = max{f[x][j-1] + profit(x+1, i)} {x=0->i-1}
     *                                      在x + 1 到i， 进行一次利润最大的交易
     * O(n*n*k)
     *
     * answer: f[n][k]
     * intialize: f[i][0] = 0, f[0][i] = -MAXINT (i>0)
     *
     * Optimize:
     *
     * 1. 状态 State
     *      mustSell[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
     *      globalbest[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益
     * 2. 方程 Function
     * 	   gain = prices[i] - prices[i - 1];
     *           mustsell[i][j] = Max(globalbest[(i - 1)][j - 1] + gain, mustsell[(i - 1)][j] + gain);
     *           globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i ][j]);
     * 3. 初始化 Intialization
     *           mustsell[0][i] = globalbest[0][i] = 0;
     * 4. 答案 Answer: globalbest[(n - 1)][k]
     *
     *
     * 2. 方程 Function
     * 	gain = prices[i] - prices[i - 1];
     *   mustsell[i][j] = Max(globalbest[i - 1][j - 1] + gain, mustsell[i - 1][j] + gain);
     *   globalbest[i-1][j-1] + gain = a. mustsell[i-1][j-1] + gain
     *   		                       b. mustsell[x][j-1] + gain   x< i-1
     *   对于a的情况:   相当于i-1 天sell 了，又buy了，一遍，相当于把sell 拖后到了第i天，
     *   所以可以是mustsell[i][j] 的最优解，由于题目是at most k,所以符合题意。
     *   对于b的情况： 本来其实要x遍历一遍所有的0到i-1的mustsell[x][j-1]最优解的，
     *   但是globalbest刚好帮助我们保存了之前所有的最优解所以不需要遍历，优化的时间。
     */

    public static int maxProfix_v2(int[] prices, int k) {
        if (k == 0) {
            return 0;
        }
        // if k >= prices.length/2, 相当于buy and sell stock II
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int n = prices.length;
        int[][] mustsell = new int[n + 1][n + 1]; // mustSell[i][j]
        // 表示前i天，至多进行j次交易，第i天必须sell的最大获益
        int[][] globalbest = new int[n + 1][n + 1]; // globalbest[i][j]
        // 表示前i天，至多进行j次交易，第i天可以不sell的最大获益

        mustsell[0][0] = globalbest[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            mustsell[0][i] = globalbest[0][i] = 0;
        }

        for (int i = 1; i < n; i++) {
            int gainorlose = prices[i] - prices[i - 1];
            mustsell[i][0] = 0;
            for (int j = 1; j <= k; j++) {
                mustsell[i][j] = Math.max(globalbest[i - 1][j - 1]
                        + gainorlose, mustsell[i - 1][j] + gainorlose);
                // 当前交易和上次交易连在一起， 把上次交易拖后一天
                globalbest[i][j] = Math.max(globalbest[i - 1][j],
                        mustsell[i][j]);
            }
        }
        return globalbest[n - 1][k];
    }


    /*
     * http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
     *
     * p[i][j] in ith day, there are jth transactions.
     * diff = prices[i] - prices[i - 1]
     * p[i][j] = max(p[i- 1][j], p[i - 1][j - 1] + diff);
     * p[i][j] = p[i - 1][j]				if diff < 0
     *     		 p[i - 1][j - 1] + diff		if diff > 0
     * diff is the difference of p[i - 1] and p[i].  if in i-1 day, there is txn, with the ith days txn,
     * it can be treated as one txn. so, p[i - 1][j - 1] + diff is only j - 1 txns. NOT j txns. the result will be
     * smaller
     *
     * use
     * mustsell[i][j]  in ith day, there must be a txn(sell).
     * global[i][j]
     *
     * if diff > 0 (price[i] - price[i - 1] > 0) 可以把这次交易（i -1 th day in, ith day out）和 第i - 1 th day 合并成一个交易
     * mustsell[i][j] = mustsell[i - 1][j] + diff
     * if diff < 0, 第ith 天的交易是亏的.. local[i][j] = global[i - 1][j - 1] + diff. diff < 0, 这次可以不交易
     *
     *
     * 这里我们需要两个递推公式来分别更新两个变量local和global，参见网友Code Ganker的博客，我们其实可以求至少k次交易的最大利润。
     * 我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。
     * 然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：
     * local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
     * global[i][j] = max(local[i][j], global[i - 1][j])，
     * 其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值后相比，
     * 两者之中取较大值，而全局最优比较局部最优和前一天的全局最优。
     *
     */

    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2) return 0;

        int days = prices.length;
        if (k >= days) return maxProfit2(prices);

        int[][] mustsell = new int[days][k + 1];
        int[][] global = new int[days][k + 1];

        for (int i = 1; i < days ; i++) {
            int diff = prices[i] - prices[i - 1];

            for (int j = 1; j <= k; j++) {
                mustsell[i][j] = Math.max(global[i - 1][j - 1] + Math.max(0, diff), mustsell[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], mustsell[i][j]);
            }
        }

        return global[days - 1][k];
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    /*
     * lc309
     *
     */


    /*
     * M16
     * print all path from root to leaves in BT
     */
    public static void t16() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        M16_allPaths(n1);

    }

    /*
     * DFS
     */
    public static void M16_allPaths(TreeNode root) {
        M16_helper(root, new StringBuilder());
    }
    public static void M16_helper(TreeNode root, StringBuilder stb) {
        if (root == null) {
            return ;   // we cannnot print out here, since for a leaf node, there will be two null children.
            // in this way, it will print duplicate result.
        }
        if (root.left == null && root.right == null) {
            stb.append(root.val);
            System.out.println(stb.toString());
            stb.deleteCharAt(stb.length() - 1);
            return ;
        }
        stb.append(root.val);
        M16_helper(root.left, stb);
        stb.deleteCharAt(stb.length() - 1);

        stb.append(root.val);
        M16_helper(root.right, stb);
        stb.deleteCharAt(stb.length() - 1);
    }




    /*
     * M16
     * check duplicate number in window k
     * two pointer,
     * hashset to store the values for the window
     * slow, fast,
     *
     */
    public static boolean M16_dupWndK(int[] array, int k) {
        HashSet<Integer> set = new HashSet<Integer>();
        int slow = 0, fast = 0;
        while(fast < array.length) {
            if (fast - slow >= k) {
                // shrink the window, now the fast hasn't been in the window
                // remove the array[slow] from set and upadte slow pointer
                set.remove(array[slow]);
                slow ++;
            }
            // try to add the new element in the window
            if (set.contains(array[fast])) {
                return true;
            } else {
                // not contain
                set.add(array[fast]);
                fast ++;
            }
        }
        return false;
    }

    /*
     * M16.2
     * task301 Remove Invalid Parentheses
     *
     * https://leetcode.com/problems/remove-invalid-parentheses/
     *
     * Remove the minimum number of invalid parentheses in order to make the
     * input string valid. Return all possible results. Note: The input string
     * may contain letters other than the parentheses ( and ).
     *
     * Examples: "()())()" -> ["()()()", "(())()"] "(a)())()" -> ["(a)()()",
     * "(a())()"] ")(" -> [""]
     *
     *
     * Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones.
     * Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
     * If it's '(', either use it, or remove it.
     * If it's '(', either use it, or remove it.
     * Otherwise just append it.
     * Lastly set StringBuilder to the last decision point.
     * In each step, make sure:
     * i does not exceed s.length().
     * Max removal rmL rmR and num of open parens are non negative.
     * De-duplicate by adding to a HashSet.
     *
     * https://leetcode.com/problems/remove-invalid-parentheses/#/solutions
     *
     */
    public static void test301() {
        String s = "()())()";
        List<String> result = task301_removeInvalidParentheses(s);
        System.out.println(result);
    }

    public static List<String> task301_removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<String>();
        int unmatchedL = 0, unmatchedR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                unmatchedL++;
            if (s.charAt(i) == ')') {
                if (unmatchedL != 0) {
                    unmatchedL--;
                } else {
                    unmatchedR++;
                }
            }
        }
        System.out.println("rmL = " + unmatchedL);
        System.out.println("rmR = " + unmatchedR);
        DFS(res, s, 0, unmatchedL, unmatchedR, 0, new StringBuilder());
        return new ArrayList<String>(res);
    }

    /**
     *
     * @param res
     * @param s
     * @param i: index
     * @param unmatchedL: unmatched left parenthesis '(', need to be removed
     * @param unmatchedR: unmatched right parenthesis ')', need to be removed
     * @param open: the number of open parenthesis
     * @param sb
     */
    public static void DFS(Set<String> res, String s, int index, int unmatchedL, int unmatchedR,
                           int open, StringBuilder sb) {

        if (index == s.length() && unmatchedL == 0 && unmatchedR == 0 && open == 0) {
            res.add(sb.toString());
            return;
        }
        if (index == s.length() || unmatchedL < 0 || unmatchedR < 0 || open < 0)
            return;

        char c = s.charAt(index);
        int len = sb.length();

        if (c == '(') {
            // not use '(', the matchedL will be decreased by 1
            DFS(res, s, index + 1, unmatchedL - 1, unmatchedR, open, sb);
            // use '(', the open parenteses increased by 1
            DFS(res, s, index + 1, unmatchedL, unmatchedR, open + 1, sb.append(c));

        } else if (c == ')') {
            // not use ')', the unatchedR will be decreased by 1.
            DFS(res, s, index + 1, unmatchedL, unmatchedR - 1, open, sb);
            // use ')', the open parenthesis decreased by 1
            DFS(res, s, index + 1, unmatchedL, unmatchedR, open - 1, sb.append(c));

        } else {
            // other char
            DFS(res, s, index + 1, unmatchedL, unmatchedR, open, sb.append(c));
        }

        sb.setLength(len);
    }

    /*
     * M17
     * LC278
     * First bad version
     */

    /*
     * M18
     * Valid Palindrome.
     * LC125
     */

    /*
     * M19
     * sparce vector Dot product
     * A = {a1, a2, a3}
     *
     * use 2D matrix to store the spare vector
     * e.g
     * A = {a1, ..., a300, ..., a5000}
     * B = {b100, ..., b300, ..., b1000}
     * A[][] = {
     * 	{1, a1},
     *  {300, a300},
     *  {5000, a5000}
     * }
     * B[][] = {
     *   {100, b100},
     *   {300, b300},
     *   {1000, b1000}
     * }
     */
    public static void t19() {

    }

    public static int M19_sparceVectorDotProduct(int[][] A, int[][] B) {
        // suppose that A.length < B.length
        if (A.length > B.length) {
            return M19_sparceVectorDotProduct(B, A);
        }
        int sum = 0;
        for(int i = 0; i < A.length; i ++) {
            int indexB = M19_BinarySearch(B, i);
            if (indexB != -1) {
                sum += A[i][1] * B[indexB][1];
            }
        }
        return sum;
    }

    public static int M19_BinarySearch(int[][] B, int index) {
        int start = 0, end = B.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start)/2;
            if (B[mid][0] == index) {
                return mid;
            } else if (B[mid][0] > index) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (B[start][0] == index) {
            return start;
        } else if (B[end][0] == index) {
            return end;
        } else {
            return -1;
        }
    }

    /*
     * M20
     * find longest arithmetic subsequence in an unsorted array
     * arithmetic subsequence(arithmetic progression) 等差数列
     * set[] = {1, 7, 10, 15, 27, 29}
     * output 3 the longest arithmetic subsequence is : 1, 15, 29
     *
     * T[i][j] the length of the longest arithmetic progression(LLAP) when we set a[i] and a[j]
     * as the first two elements of AP and j > i
     * T[i][n - 1] is alwyas 2
     * the table is filled from bottom right to top left.
     * to fill the table, j(the second element) is first fixed.
     * i and k are searched for a fixed j.
     * if i, j, k can form an AP, then the value of L[i][j] is set as L[j][k] + 1
     * L[j][k] must be filled before L[i][j] is filled
     *
     * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
     */
    public static void t20() {
        int[] a = {1,7, 10, 15, 27, 29};
        int rev = M20_LLAP(a);
        System.out.println("rev = " + rev);
    }

    public static int M20_LLAP(int[] a) {
        if (a == null || a.length < 2) {
            return 0;
        }
        int len = a.length;
        int[][] L = new int[len][len];
        int maxLLP = 2;
        // init L[i][n - 1] is 2
        for(int i = 0; i < len - 1; i++) {
            L[i][len - 1] = 2;
        }

        for(int j = len - 2; j >= 0; j --) {
            int i = j - 1, k = j + 1;
            while(i >= 0 && k <= len - 1) {
                if (a[i] + a[k] == 2 * a[j]) {
                    L[i][j] = L[j][k] + 1;
                    maxLLP = Math.max(maxLLP, L[i][j]);
                    i --;
                    k ++;
                } else if (a[i] + a[k] > 2 * a[j]) {
                    // we need to make i --, before change i, first set L[i][j] = 2
                    L[i][j] = 2;
                    i --;
                } else {
                    // a[i] + a[k] < 2 * a[j]
                    k ++;
                }
            }
            // if the loop is stopped due to k > n -1, set the remaining L[i][j] to 2
            while(i >= 0) {
                L[i][j] = 2;
                i --;
            }
        }
        Debug.printMatrix(L);

        return maxLLP;
    }


    /*
     * M21
     * strStr
     */
    public static void t21() {
        char[] s = "AABBCABC".toCharArray();
        char[] p = "BC".toCharArray();
        int rev = M21_strStr_naive(s, p);
        System.out.println("rev = " + rev);

        int rev2 = M21_strStr_KMP(s, p);
        System.out.println("rev2 = " + rev2);
    }

    public static int M21_strStr_naive(char[] s, char[] p) {
        if (s == null || p == null || s.length < p.length) {
            return -1;
        }
        if (s.length == 0 && p.length == 0) {
            return 0;
        }
        if (s.length == 0 || p.length == 0) {
            return 0;
        }
        // i is the start of matching str in s
        // j is the index of char in p
        int i = 0, j = 0;
        while(i <= s.length - p.length && j < p.length) {
            if (s[i + j] == p[j]) {
                if (j == p.length - 1) {
                    return i;
                }
                i ++;
                j ++;
            } else {
                i ++;
                j = 0;
            }
        }
        return -1;
    }


    public static int M21_strStr_KMP(char[] s, char[] p) {
        // sanity check
        if (s == null || p == null || s.length < p.length) {
            return -1;
        }
        if (s.length == 0 && p.length == 0) {
            return 0;
        }
        if (s.length != 0 && p.length == 0) {
            return 0;
        }
        int[] T = M21_KMP_table(p);
        int s_start = 0;  // the start position in s for the matches
        int i = 0;        // the current position in p to be matches
        while(s_start <= s.length - p.length && i < p.length) {
            if (s[s_start + i] == p[i]) {
                if (i == p.length - 1) {
                    return s_start;
                }
                i ++;
            } else {
                // s[s_start + i] != p[i]
                if (T[i] != -1) {
                    // we can backtrack
                    s_start = s_start + i - T[i];
                    i = T[i];
                } else {
                    // T[i] == -1
                    i = 0;
                    s_start = s_start + 1; // begin to the next
                }
            }
        }
        return -1;
    }

    // assume that p.length >= 1
    public static int[] M21_KMP_table(char[] p) {
        int len = p.length;
        int[] T = new int[len];
        T[0] = -1;
        if (p.length == 1) {
            return T;
        }

        T[1] = 0;
        int pos = 2;
        int nxt = 0;
        while(pos < len) {
            if (p[pos - 1] == p[nxt]) {
                T[pos] = nxt + 1;
                pos ++;
                nxt ++;
            } else {
                if (T[nxt] > 0) {
                    // we can track back
                    T[pos] = 0;
                    nxt = T[nxt];
                } else {
                    // T[nxt] == 0
                    // we must reset the nxt and T[pos] = 0 and move forward pos
                    T[pos] = 0;
                    nxt = 0;
                    pos ++;
                }
            }
        }
        return T;
    }


    /*
     * M22
     * strStr in 2D
     * intput
     * haystack[][] = {
     * 		{a,b,c,c},
     * 		{c,b,d}
     * };
     * needle := {c,c,b,d}
     * return 3
     * if needle := {b,d}
     * return 5 (是吧haystack 看成1D array 找到第一个db 以后的下标)
     */

    public static int M22_strStr_2D(List<List<Character>> s, char[] p) {

        return -1;
    }


    /*
     * M23
     * 17. 字符串匹配，最原始的那种：一个s，一个p，在s里面找p，返回第一个匹配字串的开头下标。如没有返回-1。
     * 忘了KMP怎么写的了，只能brute-force。。。。。
     * follow up：不用写代码，大概描述一下：不是找p，而是找p的permutation。比如s="aabacddccab", p="dadc"，返回3。
     * 因为acdd是dadc的permutation。
     * Strstr permutation
     *
     * task7 Find All Anagrams Of Short String In A Long String
     * Find all anagrams of String s in String l, return all the starting indices.
     */

    public static void test7() {
        String s = "abc";
        String l = "abzabcbadefacb";
        List<Integer> result = task7_allAnagrams(s, l);
        System.out.println(result);
    }

    public static List<Integer> task7_allAnagrams(String s, String l) {
        List<Integer> result = new ArrayList<Integer>();
        if (s == null || l == null || s.length() == 0 || l.length() == 0) {
            return result;
        }
        if (s.length() > l.length()) {
            return result;
        }
        // get the counter map for string s
        Map<Character, Integer> map = countMap(s);
        // print the map
        mapIterator(map);

        int match = 0;
        // traverse the long string, l
        for (int i = 0; i < l.length(); i++) {
            char temp = l.charAt(i);
            Integer count = map.get(temp);
            if (count != null) {
                map.put(temp, count - 1);
                // before decrease by 1, if the original count is 1, which means we already matches all the
                // temp. so, match ++
                if (count == 1) {
                    match++;
                }
            }

            System.out.println("1: i = " + i + "  count = " + count);
            System.out.println("1: match = " + match);

            if (i >= s.length()) {
                // put back the elements the window already passed
                temp = l.charAt(i - s.length());
                count = map.get(temp);
                if (count != null) {
                    map.put(temp, count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
            }

            System.out.println("2: i = " + i + "  count = " + count);
            System.out.println("2: match = " + match);

            // if match == map.size(), which means we match all the elements in anagrams of short string s
            if (match == map.size()) {
                // add the index into result
                result.add(i - s.length() + 1);
            }
            // for debug, print out the map
            mapIterator(map);
        }
        return result;
    }

    public static Map<Character, Integer> countMap(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch : s.toCharArray()) {
            Integer count = map.get(ch);
            if (count == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, count + 1);
            }
        }
        return map;
    }

    public static void mapIterator(Map<Character, Integer> map) {
        System.out.println("-----------------------------------");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "  ");
            System.out.println(entry.getValue());
        }
        System.out.println("***********************************");
    }



    /*
     * M24
     * 21. Valid Palindrome. leetcode 125. 问了时间空间复杂度。这题要求要in-place，
     * 然后输入是char[]，不过做法跟leetcode一样。做这题居然忘记了Character.toLowerCase()这个method。
     *
     */


    /*
     * M25
     * 22. Dot Product.
     *
   A={a1, a2, a3,...}
   B={b1, b2, b3,...}
   dot_product = a1 * b1 + a2 * b2 + a3 * b3 ＋。。。
   如果数组很稀疏，例如
   A={a1, ...., a300, ...., a5000}. more info on 1point3acres.com
   B={...., b100, ..., b300, ..., b1000, ...}
  里面有很多0，用什么数据结构表示能节省空间。我开始说用hashmap，但是hashmap不能做有顺序的iteration。然后改用list和array，两个都可以。后面做题的时用的array。. visit 1point3acres.com for more.
  题目是：
  input A=[[1, a1], [300, a300], [5000, a5000]]
          B=[[100, b100], [300, b300], [1000, b1000]].
  求 dot product. 问了时间复杂度。
  Follow up:
  如果length(B) >>> length(A)，即B非常长，怎么做能减少时间复杂度。对A里面的每个数，用binary search找B中相对应的值，这样时间复杂度是O(nlogm) (n = len(A), m =len(B)).时间不够没写代码， 就说了一下思路和复杂度。

  第三题的输入就是稀疏数组的非0的数列出来了，A=[[1, a1], [300, a300], [5000, a5000]]的意思就是A中第1个数是a1,第300个是a300,第5000个是a5000，其他都是0.
     */
    //'Time complexity: O(max(LenA, LenB))'
    public int dotProduc(int[][] A, int[][] B) {
        int indexA = 0;
        int indexB = 0;
        int product = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA][0] == B[indexB][0]) {
                product += A[indexA][1] * B[indexB][1];
                indexA++;
                indexB++;
            }
            else if (A[indexA][0] > B[indexB][0]) {
                indexB++;
            }
            else {
                indexA++;
            }
        }
        return product;
    }


    // Pair[1] - value, Pair[0] - index
    public int dotProduct(int[][] A, int[][] B) {
        int sum = 0;
        for (int[] pair : A) {
            int index = pair[0];
            int indexB = binarySearch(B, index);
            if (indexB != -1) {
                sum += pair[1] * B[indexB][1];
            }
        }
        return sum;
    }
    private int binarySearch(int[][] B, int index) {
        int low = 0;
        int high = B.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (B[mid][1] >= index) {
                high = mid;
            }
            else {
                low = mid;
            }
        }
        if (B[low][0] == index) {
            return low;
        }
        else if (B[high][0] == index) {
            return high;
        }
        return -1;
    }

    /*
     * M26
     * 26. Find longest arithmetic subsequence in an unsorted array.
     */
    // The idea is to maintain a 2d array. Called length[input.length][input.length]
    // length[i][j] means the length of the arithmetic that ends with input[i] and input[j]
    // And a hashMap to record the index of every number
    // We traverse the input from index 1 to the end.
    // Everytime we meet a number, fix this as the end of sequence
    // Then traverse back and try to make every number as second last number
    // When we fix one as second last one number, we calculate the gap as input[last] - input[secondLast]
    // look into hashmap to find is there a number in the input equals to input[secondLast] - gap.
    // And check its index whether it is smaller than secondLast.
    // If it is. Then this is the third last number. And we should already have length[thridLast][secondLast]
    // Then length[secondLast][last] = length[thridLast][secondLast] + 1
    // If it is not. We make length[secondLast][last] = 2 -- those two number are the only numbers in the arithmetic


    // 中心开花的思想
    public int findLongest(int[] input) {
        if (input.length <= 2) {
            return 2;
        }
        int maxLen = 2;
        int[][] length = new int[input.length][input.length];
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }

        for (int index = 1; index < input.length; index++) {
            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
                int gap = input[index] - input[secondLast];
                int next = input[secondLast] - gap;  // try to find the first element

                if (valueToIndex.containsKey(next)) {
                    int nextIndex = -1;
                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
                        // get the index which is in front of secondLast
                        if (valueToIndex.get(next).get(j) < secondLast) {
                            nextIndex = valueToIndex.get(next).get(j);
                            break;
                        }
                    }
                    if (nextIndex != -1) {
                        length[secondLast][index] = length[nextIndex][secondLast] + 1;
                        maxLen = Math.max(maxLen, length[secondLast][index]);
                    }
                }

                if (length[secondLast][index] == 0) {
                    length[secondLast][index] = 2;
                }
            }
        }
        return maxLen;
    }

    // if print out the sequence
    // 'Time complexity: O(n^2), space complexity: O(n^2 * m) --m is the average
    // length of all the arithmetic sequence'
    public List<Integer> printLongest(int[] input) {
        List<Integer> result = new ArrayList<>();
        if (input.length <= 2) {
            for (int num : input) {
                result.add(num);
            }
            return result;
        }
        int maxLen = 0;
        List<Integer>[][] length = new ArrayList[input.length][input.length];
        HashMap<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            if (!valueToIndex.containsKey(input[i])) {
                valueToIndex.put(input[i], new ArrayList<Integer>());
            }
            valueToIndex.get(input[i]).add(i);
        }
        for (int index = 1; index < input.length; index++) {
            for (int secondLast = index - 1; secondLast >= 0; secondLast--) {
                int gap = input[index] - input[secondLast];
                int next = input[secondLast] - gap;
                if (valueToIndex.containsKey(next)) {
                    int nextIndex = -1;
                    for (int j = valueToIndex.get(next).size() - 1; j >= 0; j--) {
                        if (valueToIndex.get(next).get(j) < secondLast) {
                            nextIndex = valueToIndex.get(next).get(j);
                            break;
                        }
                    }
                    if (nextIndex != -1) {
                        length[secondLast][index] = new ArrayList<Integer>(
                                length[nextIndex][secondLast]);
                        length[secondLast][index].add(input[index]);
                        if (maxLen <= length[secondLast][index].size()) {
                            result = length[secondLast][index];
                            maxLen = length[secondLast][index].size();
                        }
                    }
                }
                if (length[secondLast][index] == null) {
                    length[secondLast][index] = new ArrayList<>();
                    length[secondLast][index].add(input[secondLast]);
                    length[secondLast][index].add(input[index]);
                }
            }
        }
        return result;
    }


    public static int longestLengthAP(int[] a) {
        int n = a.length;
        if (n <= 2) {
            return n;
        }

        int[][] L = new int[n][n];
        // L[i][j] stands the LLAP(length of longest arithemic procession) with a[i] and a[j]
        // as the first two elemnts of AP. only valid when j > i
        int llap = 2;

        // fill entries in the last column as 2
        // they will always be 2
        for(int i = 0; i < n; i ++) {
            L[i][n - 1] = 2;
        }

        // consider every element as second element of AP
        for(int j = n - 2; j >= 1; j --) {
            // search for i and k, i is in front of j and k is in front of j
            int i = j - 1;
            int k = j + 1;
            while(i >= 0 && k <= n - 1) {
                if (a[i] + a[k] < 2 * a[j]) {
                    // we need to get a larger element
                    k ++;
                } else if (a[i] + a[k] > 2 * a[j]) {
                    // we need to get a smaller element
                    L[i][j] = 2;
                    i --;
                } else {
                    // a[i] + a[k] == 2 * a[j]
                    // Found i and k for j, LLAP with i and j as first two
                    // elements is equal to LLAP with j and k as first two
                    // elements plus 1. L[j][k] must have been filled
                    // before as we run the loop from right side
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);

                    //
                    i --;
                    k ++;
                }
            }
            // If the loop was stopped due to k becoming more than
            // n-1, set the remaining entties in column j as 2
            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }

	/*
	24.  leetcode原题，strstr，唯一区别就是参数是char array，不让用string方法做（当然也包括stringbuilder），
	返回在hackstack里第一次匹配needle的substring的第一个字符下标，后来想想这哥们写c和c++的，也就make sense了

	25. 大意就是把第一个haystack参数变成一个二维数组，
	然后找needle在haystack第一次出现的位置，同样的不能用string相关方法，
	还是返回把haystack[][]二维数组看成一维以后的第一次匹配的字符下标，
	举例：haystack := [[a,b,c,c], [c,b,d]], needle := [c,c,b,d] ,
	return 2;
	needle := [b, d] return 5 (5是把haystack看成一维的以后的找到第一个bd以后b的下标)
	http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=181597&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
	(Strstr 2d array)
	*/

    /*
     * 需要注意的是： 当某一行不够用的时候， 需要调到下一行
     */
    public int strStr(List<List<Character>> haystack, String needle) {
        int total = 0;
        for (int row = 0; row < haystack.size(); row++) {
            for (int col = 0; col < haystack.get(row).size(); col++) {
                int k = 0;
                int posRow = row;
                int posCol = col;
                while ((posRow < haystack.size() &&
                        posCol < haystack.get(posRow).size()) &&
                        k < needle.length() &&
                        haystack.get(posRow).get(posCol) == needle.charAt(k)) {
                    k++;
                    posCol++;
                    if (posCol == haystack.get(posRow).size()) {
                        posCol = 0;
                        posRow++;
                    }
                }
                // if we get a subtring haystack matching needle
                if (k == needle.length()) {
                    return total + col - 1;
                }
            }
            total += haystack.get(row).size();
        }

        return -1;
    }

    /*
     * 27. longest PATH(could be the path from leaf to leaf) in binary tree(BT longest path)
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=199567&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     */
    public int findLongest(TreeNode root) {
        Pair length = checkNode(root);
        return length.longestPath;
    }

    private Pair checkNode(TreeNode root) {
        if (root == null) {
            return new Pair(0, 0);
        }
        Pair left = checkNode(root.left);
        Pair right = checkNode(root.right);
        int across = 1 + left.depth + right.depth;
        int longestPath = Math.max(across,
                Math.max(left.longestPath, right.longestPath));
        int depth = Math.max(left.depth, right.depth) + 1;
        return new Pair(depth, longestPath);
    }

    class Pair {
        int depth;
        int longestPath;

        public Pair(int depth, int longestPath) {
            this.depth = depth;
            this.longestPath = longestPath;
        }
    }





}
