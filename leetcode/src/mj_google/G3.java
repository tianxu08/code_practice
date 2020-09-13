package mj_google;


import ds.TreeNode;
import java.awt.Point;
import java.util.*;

public class G3 {
    public static void main(String[] args) {
        G3 g3 = new G3();
//        g3.G3_1_Test();
        g3.G3_2_T9_test();
    }

    /**
     *
     * G3_1
     * 1。LC676 magic dictionary  各种变种 所求word再dict单词差一个字母
     *
     * 思路：两种解决思路。可以把words按length存起来然后每有词想search时候遍历查找相符。第二种是存的时候就开始删，记得要记录删的地方的index，最后和所求删的index是否相等
     */
    public void G3_1_Test() {
        String[] input = {"hello", "leetcode"};
        G3_1_LC676_MagicDictionary sln = new G3_1_LC676_MagicDictionary();
        sln.buildDict(input);
        boolean res = sln.search("hello");
        System.out.println("res = " + res);

        boolean res2 = sln.search("hhllo");
        System.out.println("res2 = " + res2);

        boolean res3 = sln.search("hell");

        System.out.println("res3 = " + res3);
    }

    public class G3_1_LC676_MagicDictionary {
        public class TrieNode {
            TrieNode[] children;
            Boolean isEnd;

            public TrieNode() {
                children = new TrieNode[26];
                isEnd = false;
            }
        }

        TrieNode root;
        int diff;
        /** Initialize your data structure here. */
        public G3_1_LC676_MagicDictionary() {
            root = new TrieNode();
            this.diff = 0;
        }

        public void buildDict(String[] dict) {
            for (String word : dict) {
                insert(root, word);
            }
        }

        public boolean search(String word) {
            if (word.length() == 0) {
                return false;
            }
            // reset diff
            diff = 0;

            return dfs(this.root, word, 0);
        }

        private boolean dfs(TrieNode node, String word, int pos) {
            if (diff > 1) {
                return false;
            }

            if (node.isEnd && pos == word.length() && diff == 1) {
                return true;
            }

            // make sure the pos <word.length
            if (pos == word.length()) {
                return false;
            }
            boolean downstreamOK = false;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    int temp = diff;
                    if (i != (word.charAt(pos) - 'a')) {
                        diff ++;
                    }

                    downstreamOK = dfs(node.children[i], word, pos + 1);
                    if (downstreamOK) break;

                    diff = temp;
                }
            }

            return downstreamOK;
        }

        private void insert(TrieNode node, String word) {
            if (word.isEmpty()) {
                node.isEnd = true;
                return;
            }

            char next = word.charAt(0);
            if (node.children[next - 'a'] == null) {
                node.children[next - 'a'] = new TrieNode();
            }
            insert(node.children[next - 'a'], word.substring(1));
        }



    }


     /**
     *
     * 2。LC849 选座位 跟exam room
     *
     * 思路：注意边界条件和怎么判断距离
     */

     /**
     * 3。LC418 sentence screen fitting
     *
     * 思路：见lc
     */
     public class G3_1_T3_LC418 {
         public int wordsTyping(String[] sentence, int rows, int cols) {
             int[] nextIndex = new int[sentence.length];
             int[] times = new int[sentence.length];
             for(int i=0;i<sentence.length;i++) {
                 int curLen = 0;
                 int cur = i;
                 int time = 0;
                 while(curLen + sentence[cur].length() <= cols) {
                     curLen += sentence[cur++].length()+1;
                     if(cur==sentence.length) {
                         cur = 0;
                         time ++;
                     }
                 }
                 nextIndex[i] = cur;
                 times[i] = time;
             }
             int ans = 0;
             int cur = 0;
             for(int i=0; i<rows; i++) {
                 ans += times[cur];
                 cur = nextIndex[cur];
             }
             return ans;
         }
     }
     /**
     * 5。LC844 Backspace string compare
     *
     * 思路：简单stack秒杀 注意follow up是O(1) space → two pointers*
      * */

     class G3_1_T5_LC844_Solution {
         public boolean backdspaceCompare(String S, String T) {
             int i = S.length() - 1, j = T.length() - 1;
             int skipS = 0, skipT = 0;

             while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
                 while (i >= 0) { // Find position of next possible char in build(S)
                     if (S.charAt(i) == '#') {skipS++; i--;}
                     else if (skipS > 0) {skipS--; i--;}
                     else break;
                 }
                 while (j >= 0) { // Find position of next possible char in build(T)
                     if (T.charAt(j) == '#') {skipT++; j--;}
                     else if (skipT > 0) {skipT--; j--;}
                     else break;
                 }
                 // If two actual characters are different
                 if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                     return false;
                 // If expecting to compare char vs nothing
                 if ((i >= 0) != (j >= 0))
                     return false;
                 i--; j--;
             }
             return true;
         }
     }


     /**
     * 6。 LC394 s = "3[a]2[bc]", return "aaabcbc".
     *
     * 思路：recursion call括号中间的项
     */
     public class G3_1_T6_LC394 {
         public String decodeString(String s) {
             Stack<Integer> counter_stack = new Stack<>();
             Stack<Integer> repeat_start_idx_stack = new Stack<>();
             if (s.length() == 0)
                 return "";

             int count = 0;
             StringBuilder decode = new StringBuilder();
             for (int i = 0; i < s.length(); i++) {
                 char c = s.charAt(i);
                 if (c >= '0' && c <= '9') {
                     count = count * 10 + c - '0';
                 } else if (c == '[') {
                     counter_stack.push(count);
                     repeat_start_idx_stack.push(decode.length());
                     count = 0;
                     continue;
                 } else if (c == ']') {
                     int repeat = counter_stack.pop();
                     int indx = repeat_start_idx_stack.pop();
                     StringBuilder part = new StringBuilder();
                     repeat --; // there is already once in the decode
                     while (repeat > 0) {
                         part.append(decode.toString().substring(indx));
                         repeat --;
                     }
                     decode.append(part.toString());
                 } else {

                     decode.append(c);
                 }

             }
             return decode.toString();
         }
     }
     /** 7。LC334 给一个array，arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
     *
     * 思路：从左到右过array，用两个变量存第一小和第二小的数（初始最大值），更新尽量小的数，若同时遇到第三小的数，则为true
     */
     public class G3_1_T7_LC334_Solution {
         public boolean increasingTriplet(int[] nums) {
             if (nums == null || nums.length < 3) {
                 return false;
             }
             int min = Integer.MAX_VALUE;
             int mid = Integer.MAX_VALUE;
             for(Integer i: nums) {
                 if (i <= min) {
                     min = i;
                 } else {  // i > min
                     if (mid < i) {
                         return true;
                     } else {
                         // i <= mid
                         mid = i;
                     }

                 }
             }
             return false;
         }
     }

     /** 8。LC774 加油站最短距离
      *
      *     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=507223&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
      *      *
      *      * 这个帖子里要求必须用dp，这里贴一个dp解法。
      *      *
      *      * Dp[i][j]表示前i个加油站增加j个站点最小的最大距离.
     *
     * 思路：用binary search寻找最短的距离，边search边找当前mid是否符合mid条件，注意判断边界条件和mid==target怎么走
     *
      * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
      *
      * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
      *
      * Return the smallest possible value of D.
      *
      * Example:
      *
      * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
      * Output: 0.500000
     */


    /**
     * Time: O(N log W). N is the length of stations. W = 10^14
     * is the range of possible answers 10^8 divided by the acceptable level of precision 10^-6
     *
     */
    class G3_1_T8_LC774_Solution_binary_search {

         public double minmaxGasDist(int[] stations, int K) {
             double lo = 0, hi = 1e8;
             while (hi - lo > 1e-6) {
                 double mi = (lo + hi) / 2.0;
                 if (possible(mi, stations, K))
                     hi = mi;
                 else
                     lo = mi;
             }
             return lo;
         }

         public boolean possible(double D, int[] stations, int K) {
             int used = 0;
             for (int i = 0; i < stations.length - 1; ++i)
                 used += (int) ((stations[i+1] - stations[i]) / D);
             return used <= K;
         }
     }

    /**
     * Intuition
     *
     * Let dp[n][k] be the answer for adding k more gas stations to the first n intervals of stations.
     * We can develop a recurrence expressing dp[n][k] in terms of dp[x][y] with smaller (x, y).
     *
     * Algorithm
     *
     * Say the ith interval is deltas[i] = stations[i+1] - stations[i]. We want to find dp[n+1][k] as a recursion.
     * We can put x gas stations in the n+1th interval for a best distance of deltas[n+1] / (x+1),
     * then the rest of the intervals can be solved with an answer of dp[n][k-x]. The answer is the minimum of these over all x.
     *
     * From this recursion, we can develop a dynamic programming solution.
     */

    /**
     * Time: O(N K^2), where N is the length of stations
     * Space: O(N K).
     */
    class T8_Solution_dp {
        public double minmaxGasDist(int[] stations, int K) {
            int N = stations.length;
            double[] deltas = new double[N-1];
            for (int i = 0; i < N-1; ++i)
                deltas[i] = stations[i+1] - stations[i];

            double[][] dp = new double[N-1][K+1];
            //dp[i][j] = answer for deltas[:i+1] when adding j gas stations
            for (int i = 0; i <= K; ++i)
                dp[0][i] = deltas[0] / (i+1);

            for (int p = 1; p < N-1; ++p)
                for (int k = 0; k <= K; ++k) {
                    double bns = 999999999;
                    for (int x = 0; x <= k; ++x)
                        bns = Math.min(bns, Math.max(deltas[p] / (x+1), dp[p-1][k-x]));
                    dp[p][k] = bns;
                }

            return dp[N-2][K];
        }
    }

    /**
     * Approach #2: Brute Force [Time Limit Exceeded]
     * Intuition
     *
     * As in Approach #1, let's look at deltas, the distances between adjacent gas stations.
     *
     * Let's repeatedly add a gas station to the current largest interval, so that we add K of them total.
     * This greedy approach is correct because if we left it alone, then our answer never goes down from that point on.
     *
     * Algorithm
     *
     * To find the largest current interval, we keep track of how many parts count[i] the ith (original) interval has become.
     * (For example, if we added 2 gas stations to it total, there will be 3 parts.)
     * The new largest interval on this section of road will be deltas[i] / count[i].
     */
    class T8_Solution_bf {
        public double minmaxGasDist(int[] stations, int K) {
            int N = stations.length;
            double[] deltas = new double[N-1];
            for (int i = 0; i < N-1; ++i)
                deltas[i] = stations[i+1] - stations[i];

            int[] count = new int[N-1];
            Arrays.fill(count, 1);

            for (int k = 0; k < K; ++k) {
                // Find interval with largest part
                int best = 0;
                for (int i = 0; i < N-1; ++i)
                    if (deltas[i] / count[i] > deltas[best] / count[best])
                        best = i;

                // Add gas station to best interval
                count[best]++;
            }

            double ans = 0;
            for (int i = 0; i < N-1; ++i)
                ans = Math.max(ans, deltas[i] / count[i]);

            return ans;
        }
    }





    /**
     * G3_2
     *
     * 9。LC337 二叉树House Robber
     *
     * 思路：dfs+dp存抢当前node和不抢当前node的最大值
     *
     */

    public void G3_2_T9_test() {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.right = n5;

        int result = new G3_2_T9_LC337_Solution().rob(n1);
        System.out.println("result = " + result);
    }
    public class G3_2_T9_LC337_Solution {
        public int rob(TreeNode root) {
            HashMap<TreeNode, Integer> map = new HashMap<>();
            return helper(root, map);
        }

        public int helper(TreeNode node, HashMap<TreeNode, Integer> map) {
            if (node == null) {
                return 0;
            }
            if (map.containsKey(node)) {
                return map.get(node);
            }
            int val = 0;
            if (node.left != null) {
                val += helper(node.left.left, map) + helper(node.left.right, map);
            }
            if (node.right != null) {
                val += helper(node.right.left, map) + helper(node.right.right, map);
            }
            val = Math.max(val + node.val, helper(node.left, map) + helper(node.right, map));
            map.put(node, val);
            return val;
        }
    }


    /** 10。LC340 longest substring with at most K distinct characters
     *
     * 思路：基本滑动窗口问题，遇到就是赚到
     */

     /** 11。log start log finish
     *
     * 有一个Class叫Logger，它有两个函数，一个是LogStart(int logId, int timestamp)，一个是LogFinish(int logId, int timestamp)。Log开始时LogStart会被调用，log结束时LogFinish会被调用。要求是实现这两个函数，并打印已经结束的log，打印log时要按log的开始时间排序。
     *
     * interface Logger {
     *
     *   void started(long timestamp, String requestId);
     *
     *   void finished(long timestamp, String requestId);
     *
     *   void print();
     *
     * }
     *
     *
     * started(100, "1")
     *
     * started(101, "2")
     *
     * finished(102, "2")
     *
     * started(103, "3")
     *
     * finished(104, "1")
     *
     * finished(105, "3")
     *
     * print()
     *
     *
     * Expected Output:
     *
     * $1 start at 100 end at 104
     *
     * $2 start at 101 end at 102
     *
     * $3 start at 103 end at 105
     *
     * 思路：
     *
     * 和lru差不多， hash map + double linked list维护顺序(hanxiao yan：弱弱问一下，这个log的题为啥要用double linkedlist呀，用single linkedlist不可以么)（editor：这里可以用single list，作者因为觉得和lru题目相似，直接用的dll，这里不需要操作中间节点，感觉可以用single list）(为什么不直接用linkedHashMap 或者TreeMap)
     *
     * Node {
     *
     *         String id;
     *
     *         long start;
     *
     *         long end;
     *
     * }
     *
     * Map<String , Node> map
     *
     * start 的时候加入map，设置好开始时间，end时间还没有设置
     *
     * finish的时候设置好node的end值，然后从map中移除
     *
     * 打印的话直接遍历一遍dll即可
     */

    interface Logger {
        public void started(String id, long time);
        public void finished(String id, long time);

    }
    public class MyLogger implements Logger {

        private class Node {

            String id;

            long start;

            long end;

            Node prev;

            Node next;

            public Node(String id, long start) {

                this.id = id;

                this.start = start;

                end = -1;

            }

        }

        Node head, tail;

        Map<String, Node> map;

        public MyLogger() {

            head = new Node("", -1);

            tail = new Node("", -1);

            head.next = tail;

            tail.prev = head;

            map = new HashMap<>();

        }

        @Override

        public void started(String id, long time) {

            Node curr = new Node(id, time);

            map.put(id, curr);

            add(curr);

        }

        @Override

        public void finished(String id, long time) {

            Node curr = map.get(id);

            if(curr != null && curr.end == -1) {

                curr.end = time;

                map.remove(id);

            }

        }

        public void print() {

            Node curr = head.next;

            while(curr != tail) {

                if(curr.end != -1) {

                    System.out.println(curr.id + "start at" + curr.start + "end at "+ curr.end);

                }

                curr = curr.next;

            }

        }

        private void add(Node curr) {

            if(curr == null) return ;

            curr.next = tail;

            curr.prev = tail.prev;

            tail.prev.next = curr;

            tail.prev = curr;

        }

    }


    /**
     * 12。LC426 BST撸直变成双向链表 首尾相接
     *
     * 思路：简单DFS。想清楚思路！！！开始dummy当prev留住head，最后prev是tail。其中prev可当做class变量
     */


     /**
     * 13。LC215 Kth largest element in an array
     *
     * 思路：可以先用优先队列装个怂，再用quick select
      * */

     /**
     * 14。LC312 扎气球游戏
     *
     * 思路：二维dp问题。dp[left][right]代表能在当前段内能扎出来的最高分。memorize是当dp非零则是没计算过。
      * Time: O(n^3)
      * Space: O(n^2)
      * */

     public class T14_LC312_Solution {

         public int maxCoins(int[] nums) {
             if (nums == null || nums.length == 0) return 0;

             int[][] dp = new int[nums.length][nums.length];
             for (int len = 1; len <= nums.length; len++) {
                 for (int start = 0; start <= nums.length - len; start++) {
                     int end = start + len - 1;
                     for (int i = start; i <= end; i++) {
                         int coins = nums[i] * getValue(nums, start - 1) * getValue(nums, end + 1);
                         coins += i != start ? dp[start][i - 1] : 0; // If not first one, we can add subrange on its left.
                         coins += i != end ? dp[i + 1][end] : 0; // If not last one, we can add subrange on its right
                         dp[start][end] = Math.max(dp[start][end], coins);
                     }
                 }
             }
             return dp[0][nums.length - 1];
         }

         private int getValue(int[] nums, int i) { // Deal with num[-1] and num[num.length]
             if (i < 0 || i >= nums.length) {
                 return 1;
             }
             return nums[i];
         }
     }
     /**
     * 15。LC769 LC768 问一个array在怎样trunk sorted之后只经过拼接就能得到升序array
     *
     * 思路：[0~n]的array做法为maintain一个max变量存当前max，当max==当前index则count++
     *
     * 无限制array时候做法为构造两个新的array存maxOfLeft和minOfRight。当一个数左看都比自己小，右看都比自己大的时候，则可以trunk。（这个更加generalize
      *
      *
      * Intuition and Algorithm
      *
      * Let's try to find the smallest left-most chunk. If the first k elements are [0, 1, ..., k-1], then it can be broken into a chunk, and we have a smaller instance of the same problem.
      *
      * We can check whether k+1 elements chosen from [0, 1, ..., n-1] are [0, 1, ..., k] by checking whether the maximum of that choice is k.
      * */
     class T15_LC769_Solution {
         public int maxChunksToSorted(int[] arr) {
             int ans = 0, max = 0;
             for (int i = 0; i < arr.length; ++i) {
                 max = Math.max(max, arr[i]);
                 if (max == i) ans++;
             }
             return ans;
         }
     }

    /**
     * https://leetcode.com/problems/max-chunks-to-make-sorted-ii/solution/
     *
     */
    class T15_LC768_Solution {
        public int maxChunksToSorted(int[] arr) {
            Map<Integer, Integer> count = new HashMap();
            int ans = 0, nonzero = 0;

            int[] expect = arr.clone();
            Arrays.sort(expect);

            for (int i = 0; i < arr.length; ++i) {
                int x = arr[i], y = expect[i];

                count.put(x, count.getOrDefault(x, 0) + 1);
                if (count.get(x) == 0) nonzero--;
                if (count.get(x) == 1) nonzero++;

                count.put(y, count.getOrDefault(y, 0) - 1);
                if (count.get(y) == -1) nonzero++;
                if (count.get(y) == 0) nonzero--;

                if (nonzero == 0) ans++;
            }

            return ans;
        }
    }




    /**
    * 16。LC505 the maze II 求total steps
    *
    * 思路：用BestFS+PQ+memorization做，注意撞墙别忘了往回退一步
    */
    public class T16_LC505_Solution {
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {
            return task505_shortestDistance(maze, start, destination);
        }


        public int task505_shortestDistance(int[][] maze, int[] start, int[] destination) {
            if (Arrays.equals(start, destination)) {
                return 0;
            }
            int rLen = maze.length;
            int cLen = maze[0].length;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            PriorityQueue<Node> minHeap = new PriorityQueue<Node>();
            minHeap.offer(new Node(start[0], start[1], 0));

            // map storing the information of node: distance to start point
            int[][] map = new int[rLen][cLen];
            // init the map
            for (int[] arr : map) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }

            while (!minHeap.isEmpty()) {
                Node cur = minHeap.poll();

                // find the shortest path
                if (cur.x == destination[0] && cur.y == destination[1]) {
                    return cur.distance;
                }

                for (int[] dir : directions) {
                    int nx = cur.x;
                    int ny = cur.y;

                    while (task505_isInMaze(maze, nx + dir[0], ny + dir[1]) && maze[nx + dir[0]][ny + dir[1]] != 1) {
                        nx = nx + dir[0];
                        ny = ny + dir[1];
                    }

                    int distance = cur.distance + Math.abs(nx - cur.x) + Math.abs(ny - cur.y);

                    if (map[nx][ny] > distance) {
                        minHeap.offer(new Node(nx, ny, distance));
                        map[nx][ny] = distance;
                    }
                }
            }
            return -1;
        }

        private boolean task505_isInMaze(int[][] maze, int x, int y) {
            int rLen = maze.length;
            int cLen = maze[0].length;
            return x >= 0 && x < rLen && y >= 0 && y < cLen;
        }

        public class Node implements Comparable<Node>{
            int x;
            int y;
            int distance;
            public Node (int _x, int _y, int _dist) {
                this.x = _x;
                this.y = _y;
                this.distance = _dist;
            }
            @Override
            public int compareTo(Node o) {
                // TODO Auto-generated method stub
                if (this.distance == o.distance) {
                    return 0;
                }
                return this.distance < o.distance ? -1 : 1;
            }
        }
    }

    /** 17。LC96 Unique Binary Search Trees
    *
    * 思路：递归+memorization
    **/
    public class T17_Unique_BS_Solution {
        public int numTrees(int n) {
            int[] count = new int[n+1];
            count[0] = 1;
            count[1] = 1;

            for(int i=2; i<=n; i++){
                for(int j=0; j<i; j++){
                    count[i] += count[j]* count[i-j -1];
                }
            }
            return count[n];
        }
    }

    class T17_UniqueBT2_Solution {
        public LinkedList<TreeNode> generate_trees(int start, int end) {
            LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
            if (start > end) {
                all_trees.add(null);
                return all_trees;
            }

            // pick up a root
            for (int i = start; i <= end; i++) {
                // all possible left subtrees if i is choosen to be a root
                LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

                // all possible right subtrees if i is choosen to be a root
                LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

                // connect left and right trees to the root i
                for (TreeNode l : left_trees) {
                    for (TreeNode r : right_trees) {
                        TreeNode current_tree = new TreeNode(i);
                        current_tree.left = l;
                        current_tree.right = r;
                        all_trees.add(current_tree);
                    }
                }
            }
            return all_trees;
        }

        public List<TreeNode> generateTrees(int n) {
            if (n == 0) {
                return new LinkedList<TreeNode>();
            }
            return generate_trees(1, n);
        }
    }

    /** 18。LC834 Sum Of distances in tree
    *
    * 思路：两次遍历，更新count和res。第一次post order 第二次pre order
    *
    */
    class T18_LC834_Solution {
        int[] ans, count;
        List<Set<Integer>> graph;
        int N;
        public int[] sumOfDistancesInTree(int N, int[][] edges) {
            this.N = N;
            graph = new ArrayList<>();
            ans = new int[N];
            count = new int[N];
            Arrays.fill(count, 1);

            for (int i = 0; i < N; ++i) {
                graph.add(new HashSet<>());
            }
            for (int[] edge: edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            dfs(0, -1);
            dfs2(0, -1);
            return ans;
        }

        public void dfs(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    dfs(child, node);
                    count[node] += count[child];
                    ans[node] += ans[child] + count[child];
                }
        }

        public void dfs2(int node, int parent) {
            for (int child: graph.get(node))
                if (child != parent) {
                    ans[child] = ans[node] - count[child] + N - count[child];
                    dfs2(child, node);
                }
        }
    }
    /** LC939 && LC963 给一堆坐标点，求坐标点形成的横平竖直矩形最小面积
    *
    * 思路：任取两点当对角线做矩形，存在set里。若已有set存在则因为另外一个对角线存在，更新面积
    */

    /**
     * Algorithm
     *
     * Group the points by x coordinates, so that we have columns of points.
     * Then, for every pair of points in a column (with coordinates (x,y1) and (x,y2)),
     * check for the smallest rectangle with this pair of points as the rightmost edge.
     * We can do this by keeping memory of what pairs of points we've seen before.
     */
    class LC939_Solution {
        public int minAreaRect(int[][] points) {
            Map<Integer, List<Integer>> rows = new TreeMap();
            for (int[] point: points) {
                int x = point[0], y = point[1];
                rows.computeIfAbsent(x, z-> new ArrayList()).add(y);
            }

            int ans = Integer.MAX_VALUE;
            Map<Integer, Integer> lastx = new HashMap();
            for (int x: rows.keySet()) {
                List<Integer> row = rows.get(x);
                Collections.sort(row);
                for (int i = 0; i < row.size(); ++i)
                    for (int j = i+1; j < row.size(); ++j) {
                        int y1 = row.get(i), y2 = row.get(j);
                        int code = 40001 * y1 + y2;
                        if (lastx.containsKey(code))
                            ans = Math.min(ans, (x - lastx.get(code)) * (y2-y1));
                        lastx.put(code, x);
                    }
            }

            return ans < Integer.MAX_VALUE ? ans : 0;
        }
    }



    class LC963_Solution {
        public double minAreaFreeRect(int[][] points) {
            int N = points.length;
            Point[] A = new Point[N];
            for (int i = 0; i < N; ++i)
                A[i] = new Point(points[i][0], points[i][1]);

            Map<Integer, Map<Point, List<Point>>> seen = new HashMap();
            for (int i = 0; i < N; ++i)
                for (int j = i+1; j < N; ++j) {
                    // center is twice actual to keep integer precision
                    Point center = new Point(A[i].x + A[j].x, A[i].y + A[j].y);

                    int r2 = (A[i].x - A[j].x) * (A[i].x - A[j].x);
                    r2 += (A[i].y - A[j].y) * (A[i].y - A[j].y);
                    if (!seen.containsKey(r2))
                        seen.put(r2, new HashMap<Point, List<Point>>());
                    if (!seen.get(r2).containsKey(center))
                        seen.get(r2).put(center, new ArrayList<Point>());
                    seen.get(r2).get(center).add(A[i]);
                }

            double ans = Double.MAX_VALUE;
            for (Map<Point, List<Point>> info: seen.values()) {
                for (Point center: info.keySet()) {  // center is twice actual
                    List<Point> candidates = info.get(center);
                    int clen = candidates.size();
                    for (int i = 0; i < clen; ++i)
                        for (int j = i+1; j < clen; ++j) {
                            Point P = candidates.get(i);
                            Point Q = candidates.get(j);
                            Point Q2 = new Point(center);
                            Q2.translate(-Q.x, -Q.y);
                            double area = P.distance(Q) * P.distance(Q2);
                            if (area < ans)
                                ans = area;
                        }
                }
            }

            return ans < Double.MAX_VALUE ? ans : 0;
        }
    }

    /** LC230: Kth smallest in BST，followup 若要改树怎么整 [改树是什么意思？有很多树call这个函数多次？]如果中间有人要insert node to BST的话，如何实现同样功能
    *
    * 思路：建一个TreeNodeWithCount，这样以后改的时候也是log n复杂度。改树的同时改TreeNodeWithCount
     *
     *
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/solution/
     *
     * Insert: O(h)
     * delete: O(h)
     * findKth: O(k)
    */
    public class LC230_Solution {
        public int kthSmallest(TreeNode root, int k) {
            kthSmallestHelper(root, k);
            return rev;
        }


        public int counter = 0;
        public int rev = Integer.MAX_VALUE;

        public void kthSmallestHelper(TreeNode node, int k) {
            if (node == null) {
                return ;
            }
            kthSmallestHelper(node.left, k);
            if (counter == k - 1) {
                rev = node.val;
            }
            counter ++;

            kthSmallestHelper(node.right, k);
        }
    }


    /**
    * 4. 面试官迟到 15 分钟。面试时间实际为 30 分钟。给定一个 picture (二维)。里面有一些有色的像素，保证所有像素是相连的（上下左右相连），
     *
     * * 且只有一个联通块。返回一个最小矩阵，这个矩阵能包含所有的有色 pixel。
    *
    * 这题是找上下左右的极大和极小值吗
    *
    * 是的 LC302原题，用二分查找做的，上下左右分别二分找边界
    */

    /**
     * Time complexity : O(E) = O(B) = O(mn)
     */
    public class LC302_Solution {
        public int minArea(char[][] image, int x, int y) {
            if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
                return 0;
            }
            int rowLen = image.length;
            int colLen = image[0].length;
            LinkedList<Item> queue = new LinkedList<Item>();
            queue.offerLast(new Item(x, y));
            boolean[][] visited = new boolean[rowLen][colLen];
            visited[x][y] = true;

            int left = colLen, right = -1;
            int up = rowLen, down = -1;
            while(!queue.isEmpty()) {
                Item cur = queue.pollFirst();
                left = Math.min(left, cur.y);
                right = Math.max(right, cur.y);
                up = Math.min(up, cur.x);
                down = Math.max(down, cur.x);
                // expand
                for(int i = 0; i < 4; i ++) {
                    int nextX = dx[i] + cur.x;
                    int nextY = dy[i] + cur.y;
                    if (nextX >= 0 && nextX < rowLen && nextY >= 0 && nextY < colLen &&
                            visited[nextX][nextY] == false  && image[nextX][nextY] == '1') {
                        queue.offerLast(new Item(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
            return (right - left + 1) * (down - up + 1);
        }
        public class Item{
            int x;
            int y;
            public Item(int _x, int _y) {
                this.x = _x;
                this.y = _y;
            }
        }

        public int[] dx = {0, 0 ,-1, 1};
        public int[] dy = {-1, 1, 0, 0};


    }

    /**
     * Binary search
     */
    public class LC302_Solution_BinarySearch {
        public int minArea(char[][] image, int x, int y) {
            int m = image.length, n = image[0].length;
            int left = searchColumns(image, 0, y, 0, m, true);
            int right = searchColumns(image, y + 1, n, 0, m, false);
            int top = searchRows(image, 0, x, left, right, true);
            int bottom = searchRows(image, x + 1, m, left, right, false);
            return (right - left) * (bottom - top);
        }
        private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
            while (i != j) {
                int k = top, mid = (i + j) / 2;
                while (k < bottom && image[k][mid] == '0') ++k;
                if (k < bottom == whiteToBlack) // k < bottom means the column mid has black pixel
                    j = mid; //search the boundary in the smaller half
                else
                    i = mid + 1; //search the boundary in the greater half
            }
            return i;
        }
        private int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
            while (i != j) {
                int k = left, mid = (i + j) / 2;
                while (k < right && image[mid][k] == '0') ++k;
                if (k < right == whiteToBlack) // k < right means the row mid has black pixel
                    j = mid;
                else
                    i = mid + 1;
            }
            return i;
        }
    }

    /**
    * 5. 给定一个棋盘，里面有一些棋子。你能移走这个棋子，当且仅当这个棋子的同行同列有其它棋子。要求最多能移走多少棋子。类似LC 947
    *
    * 思路：可以把所有棋子放到list里，每row，col存在的棋子再分别放到set of set of nodes里。
     * 用dfs思路第一轮删除任意一点，然后往后推第二次在上一次基础上清除任意满足要求的那个点，直到最后无点可清除时回溯看总共清除了多少。可mem
    *
    * 更新思路：用union find看能有多少组划分出来（如果同行或同列分成一组），然后最多能移走的棋子数=总棋子-组数（number of islands）
    *
    * follow up ：是应该用什么顺序拿，才能保证能拿最多 (这个follow up应该怎么解呢)尽量先把一个component里的都去掉？
    *
    * 优先拿掉不导致component数量增加的棋子。
    *
    *
    * recurrence relation: A[n][k] = min ( { max(A[k-1], sum(S[i+1], S[i+2], ..., S[n]))  for i = k-1, k, ..., n-1} )
     * 其中S表示task resource的数组，A[n][k]表示n个tasks, k days的每天最小resource值。
    *
    * 这题其实就是(lc410)
    *
    *
    *
    * 国人。 一个只有正整数的list， 其中插入+， * 或者（），求得到式子最大的值。 e.g. [1，2，1，2 ]->  (1+2)*(1+2)=9.
     * dp解， follow up， 如果有负数该怎么办， 如果想要拿到最大的式子该怎么办。
    *
    * 思路：类似burst balloon dp[i][j] = max of for (k : i ~ j  max(dp[i][k - 1] * dp[k][j], dp[i][k - 1] + dp[k][j]))
    *
    */

    /** LC739 array of temperatures, tell me how many days have to wait till next warmer weather
    *
    * 思路：用stack从后往前存，每次看天气时候pop出比栈顶温度低的日子，再peek出比当前暖和的一天index
    */
    class LC739_Solution {

        public int[] dailyTemperatures(int[] T) {
            int[] ans = new int[T.length];
            Stack<Integer> stack = new Stack();
            for (int i = T.length - 1; i >= 0; --i) {
                while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
                ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }
            return ans;
        }
    }

    /**
    * LC731 持续加intervals，问会不会出现triple booked
    *
    * 思路：按start end加到treemap里，start+1， end - 1，每次从小到大遍历treemap看是否存在count>2
     *
     * When booking a new event [start, end), count delta[start]++ and delta[end]--.
     * When processing the values of delta in sorted order of their keys,
     * the running sum active is the number of events open at that time.
     * If the sum is 3 or more, that time is (at least) triple booked.
     *
     * Time: O(N^N)  N is the number of events booked, For each new event, we traverse delta in O(N)O(N) time.
     * Space: O(N)
    */
    class LC731_MyCalendarTwo {
        TreeMap<Integer, Integer> map;

        public LC731_MyCalendarTwo() {
            map = new TreeMap();
        }

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int active = 0;
            for (int d: map.values()) {
                active += d;
                if (active >= 3) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    if (map.get(start) == 0)
                        map.remove(start);
                    return false;
                }
            }
            return true;
        }
    }


    /**
    * LC736 非常难 parse lisp expression 注意边界条件和判断条件
    *
    * 思路：分情况 let mult add讨论，用一个parse function处理运算符以后的事宜
     *
     * If the expression starts with a digit or '-', it's an integer: return it.
     *
     * If the expression starts with a letter, it's a variable. Recall it by checking the current scope in reverse order.
     *
     * Otherwise, group the tokens (variables or expressions) within this expression
     * by counting the "balance" bal of the occurrences of '(' minus the number of occurrences of ')'.
     * When the balance is zero, we have ended a token.
     * For example, (add 1 (add 2 3)) should have tokens '1' and '(add 2 3)'.
     *
     * For add and mult expressions, evaluate each token and return the addition or multiplication of them.
     *
     * For let expressions, evaluate each expression sequentially and assign it to the variable in the current scope,
     * then return the evaluation of the final expression.
    */

    class Solution {
        List<Map<String, Integer>> scope;
        public Solution() {
            scope = new ArrayList();
            scope.add(new HashMap());
        }

        public int evaluate(String expression) {
            scope.add(new HashMap());
            int ans = evaluate_inner(expression);
            scope.remove(scope.size() - 1);
            return ans;
        }

        public int evaluate_inner(String expression) {
            if (expression.charAt(0) != '(') {
                // integer
                if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-') {
                    return Integer.parseInt(expression);
                }

                for (int i = scope.size() - 1; i >= 0; --i) {
                    if (scope.get(i).containsKey(expression)) {
                        return scope.get(i).get(expression);
                    }
                }
            }

            List<String> tokens = parse(expression.substring(expression.charAt(1) == 'm' ? 6 : 5, expression.length() - 1));

            if (expression.startsWith("add", 1)) {
                return evaluate(tokens.get(0)) + evaluate(tokens.get(1));
            } else if (expression.startsWith("mult", 1)) {
                return evaluate(tokens.get(0)) * evaluate(tokens.get(1));
            } else {
                for (int j = 1; j < tokens.size(); j += 2) {
                    scope.get(scope.size() - 1).put(tokens.get(j-1), evaluate(tokens.get(j)));
                }
                return evaluate(tokens.get(tokens.size() - 1));
            }
        }

        public List<String> parse(String expression) {
            List<String> ans = new ArrayList();
            int bal = 0;
            StringBuilder buf = new StringBuilder();
            for (String token: expression.split(" ")) {
                for (char c: token.toCharArray()) {
                    if (c == '(') bal++;
                    if (c == ')') bal--;
                }
                if (buf.length() > 0) buf.append(" ");
                buf.append(token);
                if (bal == 0) {
                    ans.add(new String(buf));
                    buf = new StringBuilder();
                }
            }
            if (buf.length() > 0)
                ans.add(new String(buf));

            return ans;
        }
    }

    /**
    * LC768 乱序数组 chunk出最多组使得每组sort后整个sort好
    *
    * 思路：从右往左扫一遍最小值，从左到右扫一遍最大值。在第二遍途中若看到左最大<=右最小时++
    */


    /**
     * Google Snapshot
     *
     * 实现三个functions， get, set, take snapshots。其实就是一个长度为N的Array。Set可以设置Index i的值，每次take snapshot， version + 1，并且记录下当前version下 Array里面的值。然后get方法可以得到某一个Version下，每一个Index的Array的值。就是非常Naive的方法，在Chromebook上写完了。写完之后有一个变量名Typo被指出。口头跑了Test case。Follow up 时空复杂度，并且要节省空间。
     *
     * 举例
     *
     * 初始 100001
     *
     * take a snapshot 返回sid 1
     *
     * 改变成 100201.
     *
     * take a snapshot 返回sid 2
     *
     * 这时lookup get（3，1）（ get(index, snapshotID)）应该返回0而不是2，这是version control的原理可是关键在怎么存最省空间
     *
     *
     * 可以参考视频压缩，每隔若干帧保存一份完整的图像，期间只保存delta。重建图像的时候，从最近的full snapshot开始，然后apply 每个version的delta
     *
     *
     * 一开始三个数：        0 0 0
     *
     * //这时版本数为 0
     *
     * set(0, 10)  // 10 0 0
     *
     * set(0, 12)  // 12 0 0
     *
     * takesnapshots() //这时版本数变成了1
     *
     * set(0, 9)  // 9 0 0
     *
     * takesnapshots() //这时版本数变成了2
     *
     * set(0, 3)  // 3 0 0
     *
     *
     *
     * get(0, 0)  // 第一参数为版本数，第二参数是index。则返回12。
     *
     * get(1, 0)  // 返回9
     *
     *
     *
     * 看来面经还是有点用的.. 这个我也是碰到了原题. 然而并没有看到这个面经.. 哈哈
     *
     * 单独把每个元素处理就可以了, list<int[]> 或者treemap.
     *
     *
     * 第一轮美国白人小哥，一路提醒
     *
     * 自己设计一个SnapShot class 和一个SnapshottableMap class，snapshot要实现get(String key) function，snapshottable map要实现put(String key, String value)，get(String key), createSnapshots(), List<Snapshot> getSnapshots() ，四个function
     *
     *
     * Example：
     *
     * SnapshottableMap map = new SnapshottableMap();
     *
     * map.put("name", "John");esap.put("country", "UK");
     *
     * Snapshot s1 = map.createSnapshot();
     *
     *
     *
     * assert(s1.get("name").equals("John"));
     *
     * assert(s1.get("country").equals("UK"));
     *
     *
     *
     * map.put("name", "Marta");
     *
     * Snapshot s2 = map.createSnapshot();-baidu 1point3acres
     *
     *
     *
     * assert(s2.get("name").equals("Marta"));
     *
     * assert(s2.get("country").equals("UK"));
     *
     * assert(s1.get("name").equals("John"));
     *
     *
     *
     * implement 4 interfaces for class Snapshot, 面之前没见过，后来好像看到是高频题，不知道怎么才是最佳。
     * 如果用数组，每次snapshot增加时复制，实现容易，get O(1), set O(n)，空间复杂度不好。估计不是面试官想要的。
     */

    /*
    class Snapshot {


        int get(int index);  //get current snapshot value on index


        int get(int index, int snapshot);  // get value on index on some snapshot


        void set(int index, int value);  // set value on index to be the new value, snapshot would be increased


        int snapshot()  // return current snapshot id

    };
    */




    public class Main {

        public void test(String[] args) {

            SnapshottableMap map = new SnapshottableMap();

            Snapshot s0 = map.createSnapshot();



            map.put("name", "John");

            map.put("country", "UK");

            Snapshot s1 = map.createSnapshot();



            Assert(s1.get("name").equals("John"));

            Assert(s1.get("country").equals("UK"));



            map.put("name", "Marta");

            Snapshot s2 = map.createSnapshot();



            Assert(s2.get("name").equals("Marta"));

            Assert(s2.get("country").equals("UK"));

            Assert(s1.get("name").equals("John"));



            Assert(s0.get("name") == null);



            map.put("label", "AAA");

            map.put("label", "BBB");

            Snapshot s3 = map.createSnapshot();

            Assert(s3.get("label").equals("BBB"));



            System.out.println("--------\n" + s1.toDebugString() + "--------\n");

            // --------

            // country: UK

            // name: John

            // --------



            System.out.println("--------\n" + s2.toDebugString() + "--------\n");

            // --------

            // country: UK

            // name: Marta

            // --------



            System.out.println("--------\n" + s3.toDebugString() + "--------\n");

            // --------

            // country: UK

            // name: Marta

            // label: BBB

            // --------

        }



        private void Assert(boolean value) {

            if (!value) {

                throw new RuntimeException("");

            }

        }

    }



// Snapshot只存version number和主map的引用

    class Snapshot {

        private int snapshotVersion;

        private SnapshottableMap map;



        public Snapshot(int version, SnapshottableMap map) {

            this.snapshotVersion = version;

            this.map = map;

        }



        public String get(String key) {

            return map.get(key, snapshotVersion);

        }



        public String toDebugString() {

            return map.toDebugString(snapshotVersion);

        }

    }



// SnapshottableMap的实现

    class SnapshottableMap {

        private int lastestVersion = 0;

        private List<Snapshot> snapshots = new ArrayList<>();

        private Map<String, ValueHistory> storage = new HashMap<>();



        public void put(String key, String value) {

            ValueHistory history = storage.get(key);

            if (history == null) {

                history = new ValueHistory();

                storage.put(key, history);

            }

            history.put(value, lastestVersion);

        }



        public String get(String key) {

            return get(key, lastestVersion);

        }



        // 微量级时间的create snapshot

        public Snapshot createSnapshot() {

            Snapshot snapshot = new Snapshot(lastestVersion++, this);

            snapshots.add(snapshot);

            return snapshot;

        }



        public List<Snapshot> getSnapshots() {

            return snapshots;

        }



        public String get(String key, int version) {

            ValueHistory history = storage.get(key);

            if (history == null) {

                return null;

            }

            return history.get(version);

        }



        // 打印某一个version的所有值用于调试

        public String toDebugString(int version) {

            StringBuilder sb = new StringBuilder();

            for (String key : storage.keySet()) {

                final String value = get(key, version);

                if (value != null) {

                    sb.append(key + ": " + value + "\n");

                }

            }

            return sb.toString();

        }

    }



    class ValueHistory {

        // 代码逻辑保证slots中version号码一定是有序的

        private ArrayList<ValueSlot> slots = new ArrayList<>();



        public void put(String value, int version) {

            // 修改最后一个version或者是添加新version

            if (!slots.isEmpty()) {

                ValueSlot lastSlot = slots.get(slots.size() - 1);

                if (lastSlot.getVersion() == version) {

                    lastSlot.SetValue(value);

                    return;

                }

            }

            slots.add(new ValueSlot(version, value));

        }



        public String get(int version) {

            // 二分查找version

            int index = Collections.binarySearch(slots, version);

            if (index < 0) {

                if (index == -1) {

                    return null;

                }

                // 不存在指定version，但是有之前的version

                index = -index - 2;

            }

            return slots.get(index).getValue();

        }

    }



    class ValueSlot implements Comparable<Integer> {

        private int version;

        private String value;

        public ValueSlot(int version, String value) {

            this.version = version;

            this.value = value;

        }

        public int getVersion() {

            return version;

        }

        public void SetValue(String newValue) {

            value = newValue;

        }

        public String getValue() {

            return value;

        }

        @Override

        public int compareTo(Integer other) {

            return version - other;

        }

    }

}
