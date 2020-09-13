package mj_fb;

import java.util.*;
import java.util.Map.Entry;

import ds.*;

public class FB_Coding2 {

    /*
     * 28. word search 面试官一开始怕太难了 就说允许重复使用character 写出来之后 再让我写不允许重复使用的情况
     * http://www
     * .1point3acres.com/bbs/forum.php?mod=viewthread&tid=199572&extra=
     * page%3D1%26f
     * ilter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue
     * %5D%3D1%26searchoption
     * %5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D
     * %5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
     * 'Time complexity: O(n^2*len)--len is the average length of the words,
     * Space complexity: O(len)'
     */

    private static int[] X = { 0, 0, 1, -1 };
    private static int[] Y = { 1, -1, 0, 0 };

    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == word.charAt(0)) {
                    if (helper(word,
                            new boolean[board.length][board[0].length], board,
                            1, row, col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean helper(String word, boolean[][] visited, char[][] board,
                           int index, int row, int col) {
        if (index == word.length()) {
            return true;
        }
        visited[row][col] = true;
        for (int k = 0; k < 4; k++) {
            int nextX = row + X[k];
            int nextY = col + Y[k];
            if (nextX < 0 || nextX >= board.length || nextY < 0
                    || nextY >= board[0].length || visited[nextX][nextY]
                    || word.charAt(index) != board[nextX][nextY]) {
                continue;
            }
            if (helper(word, visited, board, index + 1, nextX, nextY)) {
                return true;
            }
        }
        visited[row][col] = false;
        return false;
    }

    /*
     * word search 2:
     * // Use trie tree to store the words in the dictionary
     * //So we dont have to look into the array to find the character match any words
     * // We only need to look into the trieNode root to see children[ch - 'a'] == null
     * // if not, then we can start search 'Time complexity:
     * O(n^2*len), space complexity:O(len*k) there are k words in the arrays'
     */

    private TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            insert(word);
        }
        List<String> result = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (root.children[board[row][col] - 'a'] != null) {

                    String word = String.valueOf(board[row][col]);
                    helper(board, new boolean[board.length][board[0].length], root.children[board[row][col] - 'a'], row, col, word, result);
                }
            }
        }
        return result;
    }

    private void helper(char[][] board, boolean[][] visited, TrieNode node, int row, int col, String word,
                        List<String> result) {
        visited[row][col] = true;
        if (node.isEnd && !result.contains(word)) {
            result.add(word);
        }
        for (int k = 0; k < 4; k++) {
            int nextX = row + X[k];
            int nextY = col + Y[k];
            if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length || visited[nextX][nextY]
                    || node.children[board[nextX][nextY] - 'a'] == null) {
                continue;
            }
            helper(board, visited, node.children[board[nextX][nextY] - 'a'], nextX, nextY, word + board[nextX][nextY], result);
        }
        visited[row][col] = false;
    }

    private void insert(String word) {
        TrieNode mover = root;
        for (char letter : word.toCharArray()) {
            if (mover.children[letter - 'a'] == null) {
                mover.children[letter - 'a'] = new TrieNode();
            }
            mover = mover.children[letter - 'a'];
        }
        mover.isEnd = true;
    }

    class TrieNode {
        public TrieNode[] children;
        public boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }


    /*
     * 30. product of the array   给定一个array，返回里面元素乘积的所有可能值。 例如给定array:[1,2,3,4]
     * 应该返回 [1, 2, 3, 4, 6, 8, 12, 24].
     * http://www.1point3acres.com/bbs/thread-199253-1-1.html
     * // Just Like find the subset of the array
     * 'Time complexity: O(2^n)--number of subsets,
     * space complexity: O(Len(input))'
     */
    public static void test30() {
        int[] a = {1,2,3,4};
        List<Integer> res = product(a);
        System.out.println(res);
    }
    public static List<Integer> product(int[] input) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(input);
        helper(set, 1, 0, input, true);
        return new ArrayList<Integer>(set);
    }

    private static void helper(Set<Integer> result, int curProduct, int pos, int[] input, boolean first) {
        if (!first) {
            result.add(curProduct);
        }
        first = false;
        for (int i = pos; i < input.length; i++) {
            if (i != pos && input[i] == input[i - 1]) {
                continue;
            }
            if (input[i] == 0) {
                result.add(0);
                continue;
            }
            curProduct *= input[i];
            helper(result, curProduct, i + 1, input, first);
            curProduct /= input[i];
        }
    }

    /*
     * 31. combination sum 5
     */

    /*
     * 32. 题目：
     * binary tree inorder traversal
     */

    public TreeNodeWithParent findNext(TreeNodeWithParent node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            TreeNodeWithParent result = node.right;
            while (result.left != null) {
                result = result.left;
            }
            return result;
        } else if (node.parent == null) {
            return null;
        } else if (node.parent.left == node) {
            return node.parent;
        } else {
            TreeNodeWithParent result = node.parent;
            while (result.parent != null && result.parent.right == result) {
                result = result.parent;
            }
            return result.parent;
        }
    }

    class TreeNodeWithParent {
        public TreeNodeWithParent parent;
        public TreeNodeWithParent left;
        public TreeNodeWithParent right;
        public int val;

        public TreeNodeWithParent(int val, TreeNodeWithParent parent) {
            this.val = val;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

    }


    /*
     * 34. 买股票1，删除在单链表的节点
     * 35. 有一个无限流的interval输入，要找出cover range。这个用什么数据结构比较好呢？
     * 36. 设计一个系统，大概有1b的active user，大家都会post，每个post，都有名字和时间戳。
     * 然后还有一个search的功能，输入是一个或多个keyword（words之间是OR关系），要求返回相关的post。
     * 这个问题牵扯到如何存，如果做search word。大概的结构是什么样子的，还有要估算出每个function都需要多少机器呢。估算做的不完整，
     * 我只算出来了存post需要多少个机器，然后没有时间了。三哥说架构没有问题，good direction，算出来多少机器那一步不错。
     *
     * 37. 工作的调度，是个面经题，有些变种，只要求出给定tasks的工作总时间，在小哥提示下做了优化到O(n).
     * Follow up是如何schedule这些工作，这样最后的工作总时间最少。我说了一种greedy的算法，
     * 就是相同task相隔约长约好。但是不太对，小哥说其实是一旦数量最多的task cooldown时间到了，
     * 就schedule这个task。问了我大概怎么实现，就结束了。
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
        TreeSet<Mission> missionSet = new TreeSet<Mission>(
                new Comparator<Mission>() {
                    @Override
                    public int compare(Mission o1, Mission o2) {
                        // TODO Auto-generated method stub
                        if (o1.freq == o2.freq) {
                            return o1.name < o2.name ? -1 : 1;
                        }
                        return o1.freq > o2.freq ? -1 : 1;
                    }
                });

        HashMap<Character, Integer> missionToTime = new HashMap<Character, Integer>();
        HashMap<Character, Integer> missionToFreq = new HashMap<Character, Integer>();
        StringBuilder result = new StringBuilder();

        // fill in the missionToFreq
        for (char mission : input.toCharArray()) {
            if (!missionToFreq.containsKey(mission)) {
                missionToFreq.put(mission, 1);
            } else {
                missionToFreq.put(mission, missionToFreq.get(mission) + 1);
            }
        }

        // fill in the missionSet
        for (Entry<Character, Integer> entry : missionToFreq.entrySet()) {
            // key: mission name
            // value: mission freq
            missionSet.add(new Mission(entry.getKey(), entry.getValue()));
        }

        while (!missionSet.isEmpty()) {
            Mission select = null;
            // traverse the missionSet from high frequency to low frequency
            for (Mission mission : missionSet) {
                if (!missionToTime.containsKey(mission.name)
                        || result.length() - missionToTime.get(mission.name) >= k) {
                    // if the mission(current mission) hasn't been scheduled or
                    // the time interval is larger than the cool down time, k
                    select = mission;
                    missionToTime.put(mission.name, result.length() + 1);
                    break;
                }
            }
            // if all the missions in the missionSet, the time interval is
            // greater than cool down time,
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

    public static class Mission {
        public char name;
        public int freq;

        public Mission(char _name, int _freq) {
            this.name = _name;
            this.freq = _freq;
        }
    }


    //reverse print linked List
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode mover = head;
        ListNode last = null;
        while (mover != null) {
            ListNode next = mover.next;
            mover.next =last;
            last = mover;
            mover = next;
        }
        return last;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }



    /*
     * 38. 找出数组中第M大的数。quick sort也可以解决，average run time O(N), worst case O(N^2)。
     * 如果数组特别大呢，不想sort呢。可以用一个M size的pq，这样的话就是O(N*logM).
     *
     * 用minHeap
     */

    /*
     * 39. 给一个正数n，打印出所有相加的组合
     * 例如10可以打印出
     * 1+1+1+...1
     * 1+2+1+...1
     * 9+1
     * 10
     *
     * 有些像 coin 问题
     */
    public static void test39() {
        int input = 5;
        List<List<Integer>> result = find(input);
        System.out.println(result);
    }
    public static List<List<Integer>> find(int input) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), 1, input, 0);
        return result;
    }

    private static void helper(List<List<Integer>> result, List<Integer> path,
                               int pos, int target, int sum) {
        if (target == sum) {
            System.out.println(path);

            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = pos; i <= target; i++) {
            if (sum + i > target) {
                return;
            }
            sum += i;
            path.add(i);
            // the next position is still "i"
            helper(result, path, i, target, sum);
            sum -= i;
            path.remove(path.size() - 1);
        }
    }

    /*
     * 40. 某白人，一半谈career/resume，一半问算法。
     * 题目：破解密码，提供了一个函数，isPassword(String str)，如果pass in的是正确的密码，return true。
     * 又给定了每个字母可以变形的集合（例如，字母a可以变形为a或者A或者B或者*, 字母b可以变形为B或者F或者&...，
     * 需要自己设计一个data structure来存这个mapping），
     * 设计并实现一个函数，在给定一个字符串的情况下，对字符串进行变形，最后找到正确的密码。
    // Phone number
    //Time O(3^n)
    //Space O(3^n)
    */
    public static void test40() {
        String s = "abc";
        List<String> rev = letterCombinations40(s);
        System.out.println(rev);
    }

    public static List<String> letterCombinations40(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        char[][] chs = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' },
                { 'g', 'h', 'i' }, { 'j', 'k', 'l' }, { 'm', 'n', 'o' },
                { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' },
                { 'w', 'x', 'y', 'z' } };
        StringBuilder path = new StringBuilder();
        helper40(result, path, 0, digits, chs);
        return result;
    }

    private static void helper40(List<String> result, StringBuilder path, int pos,
                                 String digits, char[][] phoneNumber) {
        if (pos == digits.length()) {
            result.add(new StringBuilder(path).toString());
            return;
        }

        int digit = Integer.parseInt(digits.substring(pos, pos + 1));
        for (int i = 0; i < phoneNumber[digit].length; i++) {
            path.append(phoneNumber[digit][i]);
            helper40(result, path, pos + 1, digits, phoneNumber);
            path.delete(path.length() - 1, path.length());
        }
    }

    /*
     * 41
     * 考了valid BST和move zeros两道原题
     */

    /*
     * 42 貌似一个ABC，考了merge k sorted linked list
     */

    /*
     * 43. 题目是给定一个list of sorted integer arrays，要求找所有的数的median
     * e.g. [1,3,6,7,9], [2,4,8], [5], return 5
     */
    public static double findMedian_43(List<double[]> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(),
                new Comparator<Number>() {
                    @Override
                    public int compare(Number num1, Number num2) {
                        if (num1.value > num2.value) {
                            return 1;
                        }
                        return -1;
                    }
                });
        int total = 0;
        for (int i = 0; i < input.size(); i++) {
            minNumber.add(new Number(input.get(i)[0], i, 0));
            total += input.get(i).length;
        }
        double median = 0;
        double previous = 0;
        int count = 0;
        while (count <= total / 2) {
            Number min = minNumber.poll();
            count++;
            previous = median;
            median = min.value;
            if (input.get(min.arrayIndex).length - 1 > min.index) {
                minNumber.add(new Number(
                        input.get(min.arrayIndex)[min.index + 1],
                        min.arrayIndex, min.index + 1));
            }
        }
        if (total % 2 == 0) {
            return (previous + median) / 2.0;
        }
        return median;
    }

    private static class Number {
        double value;
        int arrayIndex;
        int index;

        public Number(double value, int arrayIndex, int index) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }



    /*
     * 第二道题是 给个Tree 不一定是平衡的， 要求 把所有路径排序后  按字符串那样的比较大小方法 找出最小的路径  时间要求线性的。 比如
          5
       /     \
    10      3
   /   \   /
  1    7  8
路径有  5 10 1 ； 5 10 7 ； 5 3 8
排序后  1 5 10 ； 5 7 10 ； 3 5 8；
所以按字符串类型排序 为  1 5 10 < 3 5 8 < 5 7 10；
     */
    // assume there is no duplicate in the tree
    public static void test() {
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(10);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;

        List<Integer> res = find(n1);
        System.out.println(res);

    }
    public static List<Integer> find(TreeNode root) {
        Result result = helper(root);
        return result.path;
    }

    private static Result helper(TreeNode root) {
        if (root == null) {
            Result result = new Result();
            result.path.add(Integer.MAX_VALUE);
            return result;
        }
        if (root.left == null && root.right == null) {
            Result result = new Result();
            result.min = root.val;
            result.path.add(root.val);
            return result;
        }
        Result left = helper(root.left);
        Result right = helper(root.right);
        Result result = new Result();
        result.path.add(root.val);
        if (left.min > right.min) {
            // 要右边的path
            result.path.addAll(right.path);
        } else {
            // 要左边的path
            result.path.addAll(left.path);
        }
        result.min = Math.min(root.val, Math.min(left.min, right.min));
        return result;
    }

    static class Result {
        public int min = Integer.MAX_VALUE;
        public List<Integer> path = new ArrayList<>();

        public Result() {
        }
    }
}
