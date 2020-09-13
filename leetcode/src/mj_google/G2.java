package mj_google;
import java.util.*;

public class G2 {

    public static void main(String[] args) {
        G2 g2 = new G2();
//        g2.T1_test();
//        g2.T3_Test();
//        g2.T7_test();
//        g2.T12_test();
        g2.T12_test2();
    }
    /**
     * 高频 4次
     * 1.下围棋，判断棋盘上点是否被包围 follow up test case 各种形状
     *
     * 请问各种形状是指什么？是不是指棋盘的形状？如果是的话，那么是不是不同点在于不同的形状的边界情况就会不一样？
     *
     * 请问：这一题哪一篇帖子有比较详细的题目内容吗？感谢
     *
     * 请问这道题有谁做出来能po出详细的code吗？感激不尽
     *
     * V 应该说的是棋子的各种摆放。手动Unit test言之成理即可。主要是要测各种edge case
     *
     * 思路：dfs，碰到空子返回false 没被围死
     *
     */


    public void T1_test() {
        int[][] board = {
                {1, 1, 1, 1},
                {1, -1, -1, 1},
                {1, 0, 1, 1},
                {0, 0, 0, 0}
        };

        T1_Go sln = new T1_Go();
        boolean result = sln.isWrapped(board, 1, 1);
        System.out.println("result = " + result);
    }

    /**
     * Time: O(rLen * cLen)
     * Space: O(rLen * cLen)
     */
    public class T1_Go {
        // 0: empty, 1: a, -1: b
        public boolean isWrapped(int[][] board, int x, int y) {
            if (board == null || board.length == 0) {
                return false;
            }
            int rLen = board.length;
            int cLen = board[0].length;
            if (board[x][y] == 0) {
                return false;
            }
            return helper(board, x, y, new boolean[rLen][cLen], board[x][y]);
        }

        private int[][] dir = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        public boolean helper(int[][] board, int x, int y, boolean[][] visited, int player) {
            int rLen = board.length;
            int cLen = board[0].length;

            if (visited[x][y]) {
                return true;
            }

            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dir[i][0];
                int nextY = y + dir[i][1];

                if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen && !visited[nextX][nextY]) {
                    if (board[nextX][nextY] == 0) {
                        return false;
                    }

                    if (board[nextX][nextY] == player) {
                        return helper(board, nextX, nextY, visited, player);
                    }
                }
            }
            return true;

        }
    }



     /** 2。n层map，每层m个node，node和edge都有值，问第一层到最后的minimum cost
     *
     * https://www.1point3acres.bs/forum.php?
     *
     * mod=viewthread&tid=434363
     *
     * 思路：遍历+改node值？com/bdijkstra吧？
     *
     * 什么叫N层map? map是stl里面的map吗？不改node值也行吧，反正就是DFS每一条edge存在于同层的node之间？为什么可以改node的值？可能的路径，到底了就和min cost比较就完事了？
     *
     */


     /** 3。拿纸牌游戏， 纸牌上面有值，比如说 100， 1， -1， 2， 200， 1. 然后两个人轮流拿，直到拿完。
      * 但是每次只能拿从左边数起的前三个，
      * 但是如果你要拿第三个，就必须前两个都拿了，你要拿第二个，就必须第一个也拿了，大家都最优策略，问最后第一个人能拿多少分。
     *
     * 思路：dp存当前人比另一个人能多拿的数，从后往前拿，每次看三个[谁能给个解法链接]
     *
     * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/类似题目
     *
     *
     * 这个是一个蛮经典的dp问题，lintcode上也有类似的题目 https://www.lintcode.com/problem/coins-in-a-line-ii/description
     *
     *
     * 这个是LINTCODE  左数两个的解法：
     */

     public void T3_Test() {
         T3_firstWillWin_Left2 sln = new T3_firstWillWin_Left2();
         int[] input = {1, 2, 2};
         boolean firstWillWin = sln.firstWillWin_dp(input);
         System.out.println("firstWillWin = " + firstWillWin);

         System.out.println(">>>>>>>>>>>>>>>>");
         int[] input2 = {1, 2, 3, 10, 4};
         boolean firstWillWin2 = new T3_firstWillWin_Left3().firstWillWin(input2);
         System.out.println("firstWillWin2 = " + firstWillWin2);
     }
     public class T3_firstWillWin_Left2 {

         int memo[];
         public boolean firstWillWin(int[] values) {
             // write your code here
             int sum = 0;
             for (int v : values) {
                 sum += v;

             }
             memo = new int[sum + 1];
             Arrays.fill(memo, -1);
             System.out.println("memo = " + Arrays.toString(memo));
             int one = dfs(values, sum, 0);
             int two = sum - one;
             System.out.println("memo2 = " + Arrays.toString(memo));
             return one > two;
         }


         int dfs(int[] values, int sum, int pos) {
             if(pos >= values.length) {
                 return 0;
             }
             if(memo[sum] != -1) {
                 return memo[sum];
             }
             int first = values[pos];
             int op1 = sum - dfs(values, sum - first, pos + 1);

             int op2 = 0;
             if(pos + 1 < values.length) {
                 int second = values[pos + 1];
                 op2 =  sum - dfs(values, sum - first - second, pos + 2);
             }
             memo[sum] = Math.max(op1, op2);
             return memo[sum];
         }


         public boolean firstWillWin_dp(int[] values) {
             // write your code here
             if (values == null || values.length == 0) return false;

             int[] dp = new int[values.length];
             for (int i = values.length - 1; i >= 0; i--) {
                 if (i == values.length - 1) {
                     dp[i] = values[i];
                 } else if (i == values.length - 2) {
                     dp[i] = values[i] + values[i + 1];
                 } else {
                     dp[i] = Math.max(values[i] - dp[i + 1], values[i] + values[i + 1] - dp[i + 2]);
                 }
             }
             System.out.println("dp = " + Arrays.toString(dp));
             return dp[0] >= 0;
         }
     }


    public class T3_firstWillWin_Left3 {
        int memo[];
        public boolean firstWillWin(int[] values) {
            // write your code here
            int sum = 0;
            for (int v : values) {
                sum += v;

            }
            memo = new int[sum + 1];
            Arrays.fill(memo, -1);
            System.out.println("memo = " + Arrays.toString(memo));
            int one = dfs(values, sum, 0);
            int two = sum - one;
            System.out.println("memo2 = " + Arrays.toString(memo));
            return one > two;
        }


        int dfs(int[] values, int sum, int pos) {
            if(pos >= values.length) {
                return 0;
            }
            if(memo[sum] != -1) {
                return memo[sum];
            }
            int first = values[pos];
            int op1 = sum - dfs(values, sum - first, pos + 1);

            int op2 = 0;
            int op3 = 0;
            if(pos + 1 < values.length) {
                int second = values[pos + 1];
                op2 =  sum - dfs(values, sum - first - second, pos + 2);
                if (pos + 2 < values.length) {
                    int third = values[pos + 2];
                    op3 = sum - dfs(values, sum - first - second - third, pos + 3);
                }
            }

            memo[sum] = Math.max(op1, Math.max(op2, op3));
            return memo[sum];
        }
    }


    /**
     * 4。image以byte[][]储存 如果想中心镜像翻转怎么弄
     *
     * https://www.1point3acres.com/bbs/thread-409626-1-1.html
     *
     * 思路：跟reverse words一个思路，先翻转每行的byte，再翻转自身（字节翻转可用位运算能快
     *
     * 可以详细讲一下这里怎么用位运算吗
     */


    /**
     * 5。已知screen的高和宽，给你最小和最大的fontSize，要求给定一个string，将string用尽可能大的fontSize显示在screen里。已知两个API getHeight(int fontSize), getWidth(char c, int fontSize)
     *
     * ，可以得到每个character在不同fontSize下的高和宽。和面试官交流后，确认string可以拆分成几行显示在screen中
     *
     * 思路：先提出暴力解法，然后用二分法优化
     *
     * Provider：Qiaoqian Lin        Harry Yi: 给的API里面每个char的width是不同的，下述方法还需要额外计算一下
     *
     * def fitScreen(screen_width, sucreen_height, num_chars, font_sizes):
     *
     *         def ok_or_not(width, height, screen_width, screen_height, num_chars):
     *
     *                 num_chars_per_line = screen_width // width
     *
     *                 num_chars_per_column = screen_height // height
     *
     *                 num_chars_can_fit = num_chars_per_line * num_chars_per_column
     *
     *                 return num_chars_can_fit >= num_chars
     *
     *
     *         lo, hi = 0, len(font_sizes) - 1
     *
     *         while lo < hi:
     *
     *                 mid = (lo + hi + 1) // 2
     *
     *                 font_size = font_sizes[mid]
     *
     *                 width, height = API(font_size)
     *
     *                 size_ok = ok_or_not(width, height, screen_width, screen_height, num_chars)
     *
     *                 if not size_ok:
     *
     *                         hi = mid -1
     *
     *                 else:
     *
     *                         lo = mid
     *
     *         return lo
     *
     * 屏幕超大，如何speedup?
     */

    /**
     * 6。LC803 打砖块
     *
     * 思路：最好方法从后往前补。先把砖块全都打掉，然后用贴天花板的砖块dfs+mark，然后从后往前一个一个往上加，
     * 加同时若碰上周围有mark的砖块就主动dfs，dfs出来的就是这次打掉的砖块
     */
    class T6_LC803_hitBricks_Solution {
        public int[] hitBricks(int[][] grid, int[][] hits) {
            int R = grid.length, C = grid[0].length;
            int[] dr = {1, 0, -1, 0};
            int[] dc = {0, 1, 0, -1};

            int[][] A = new int[R][C];
            for (int r = 0; r < R; ++r)
                A[r] = grid[r].clone();
            for (int[] hit: hits)
                A[hit[0]][hit[1]] = 0;

            DSU dsu = new DSU(R*C + 1);
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (A[r][c] == 1) {
                        int i = r * C + c;
                        if (r == 0)
                            dsu.union(i, R*C);
                        if (r > 0 && A[r-1][c] == 1)
                            dsu.union(i, (r-1) *C + c);
                        if (c > 0 && A[r][c-1] == 1)
                            dsu.union(i, r * C + c-1);
                    }
                }
            }
            int t = hits.length;
            int[] ans = new int[t--];

            while (t >= 0) {
                int r = hits[t][0];
                int c = hits[t][1];
                int preRoof = dsu.top();
                if (grid[r][c] == 0) {
                    t--;
                } else {
                    int i = r * C + c;
                    for (int k = 0; k < 4; ++k) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (0 <= nr && nr < R && 0 <= nc && nc < C && A[nr][nc] == 1)
                            dsu.union(i, nr * C + nc);
                    }
                    if (r == 0)
                        dsu.union(i, R*C);
                    A[r][c] = 1;
                    ans[t--] = Math.max(0, dsu.top() - preRoof - 1);
                }
            }

            return ans;
        }
    }

    class DSU {
        int[] parent;
        int[] rank;
        int[] sz;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
            rank = new int[N];
            sz = new int[N];
            Arrays.fill(sz, 1);
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return;

            if (rank[xr] < rank[yr]) {
                int tmp = yr;
                yr = xr;
                xr = tmp;
            }
            if (rank[xr] == rank[yr])
                rank[xr]++;

            parent[yr] = xr;
            sz[xr] += sz[yr];
        }

        public int size(int x) {
            return sz[find(x)];
        }

        public int top() {
            return size(sz.length - 1) - 1;
        }
    }


    /**
     * 7。LC253 给一堆interval，问最多要定多少间会议室
     *
     * 思路：可以用heap常规做，也可以把开始、结束时间分别升序排序，然后2pointer往后走。复杂度一样，就是更快
     */

    public void T7_test() {
        int[][] intervals = {
                {0, 2},
                {1, 3},
                {2, 3},
                {0, 4}
        };
        int result = new T7_meetingroom().meetingRoom(intervals);
        System.out.println("result = " + result);
    }
    public class T7_meetingroom {
        public class Event{
            int time;
            boolean isStart;

            public Event(int _time, boolean _isStart) {
                this.time = _time;
                this.isStart = _isStart;
            }
        }

        public int meetingRoom(int[][] intervals) {
            int n = intervals.length;
            List<Event> events = new ArrayList<>();
            for(int[] interval : intervals) {
                Event eS = new Event(interval[0], true);
                Event eE = new Event(interval[1], false);

                events.add(eS);
                events.add(eE);
            }

            // sort by time, if time is equal, isStart=false in front of isStart=true
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    if (o1.time == o2.time) {
                        if (o1.isStart == false && o2.isStart == true) {
                            return -1;
                        } else if (o1.isStart == true && o2.isStart == false) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } else {
                        return o1.time < o2.time ? -1 : 1;
                    }
                }
            });

            int max = Integer.MIN_VALUE;
            // swipe the list
            int counter = 0;
            for (Event e: events) {
                if (e.isStart) {
                    counter ++;
                } else {
                    counter--;
                }
                max = Math.max(counter, max);
            }

            return max;
        }

    }



    /**
     * 9。给一堆intervals和一个时间点，问这个时间点是不是空闲。follow up多call优化时间
     *
     * 思路：做一遍merge intervals再来一遍binary search
     *
     * 问：这个不merge，直接所有的往treeset里扔可以吗？
     */
    public class T19_PointInIntervals {
        // use interval tree
        // refer: https://github.com/tianxu08/leetcode2/blob/master/src/tag_intervals/IntervalTree_Summary.java#L41
    }


    /**
     * 10。iterator of iterator
     *
     * LC类似题目：
     *
     * x
     *
     * 题目描述
     *
     * Implement an Iterator of Iterators which traverses through an arbitrary number of iterators.
     * IE, an iterator which iterates over three list iterators in the following way: L1 = a1, a2, a3 L2 = b1, b2, b3 L3 = c1, c2, c3 Then the iterator should process them in this order: a1, b1, c1, a2, b2, c2, a3, b3, c3
     *
     * 原题链接：https://www.1point3acres.com/bbs/thread-293238-1-1.html

     */


    public class T10_ZigzagIterator {

        private List<Integer> v1;
        private List<Integer> v2;

        int index_v1;
        int index_v2;
        boolean flag;

        public T10_ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.v1 = v1;
            this.v2 = v2;
            index_v1 = 0;
            index_v2 = 0;
            this.flag = false;
        }

        public int next() {
            if (hasNext()) {
                if (index_v1 < v1.size() && index_v2 < v2.size()) {
                    if (!flag) {
                        // flag == false, choose from v1
                        int rev = v1.get(index_v1);
                        index_v1 ++;
                        flag = true;
                        return rev;
                    } else {
                        // flag == true, choose from v2
                        int rev = v2.get(index_v2);
                        index_v2 ++;
                        flag = false;
                        return rev;
                    }
                } else {
                    if (index_v1 < v1.size()) {
                        int rev = v1.get(index_v1);
                        index_v1 ++;
                        return rev;
                    } else {
                        int rev = v2.get(index_v2);
                        index_v2 ++;
                        return rev;
                    }
                }
            } else {
                return -1;
            }
        }

        public boolean hasNext() {
            if (index_v1 < v1.size() || index_v2 < v2.size()) {
                return true;
            }
            return false;
        }

    }

    /**
     * 11。 LC68 text justification 把word list转化成等长的行对齐
     *
     * 思路：two pointer [left, right] greedy的把words往上放，中间spaces个数是right-left，注意首行和末尾行的判断。
     *
     * text justify
     *
     * int lineLength
     * String input
     *
     * Spaces are as evenly as possible within lines to get the line to be the required length,
     * so no pair of adjacent words should have more than one more space between them than another other pair in the line
     * the last line should not be spaced
     */

    public void T11_test() {
        String input = "this is some sample text, really just enough to generate a few lines in the output to show what the next justify function is supposed to do.";
        System.out.println("----------------");
        System.out.println(input);

        String res2 = new T11_Text_Justification().justtify(25, input);
        System.out.println(res2);
    }

    public class T11_Text_Justification {
        public String justtify(int lineLength, String input) {
            System.out.println("lineLength = " + lineLength);
            // suppose that there are only one space between two words
            List<String> words = Arrays.asList(input.split("\\s"));
            Iterator<String> wordIter = words.iterator();

            StringBuilder stb = new StringBuilder();
            List<String> line = new ArrayList<>();
            int curLen = 0;
            while (wordIter.hasNext()) {
                String nextWord = wordIter.next();
                int newLen = curLen + nextWord.length();

                if (newLen > lineLength) {
                    stb.append(t5_addSpaces(line, lineLength));
                    // reset line and curLen
                    line = new ArrayList<>();
                    curLen = 0;
                }
                line.add(nextWord);
                curLen += nextWord.length() + 1;
            }

            if (!line.isEmpty()) {
                stb.append(t5_addSpaces(line, curLen - 1));
            }
            return stb.toString();
        }

        /**
         * balance the line
         */
        public String t5_addSpaces(List<String> words, int lineLength) {
            StringBuilder stb = new StringBuilder();
            if (words.size() > 1) {
                int nonSpace = 0;
                for (String s: words) {
                    nonSpace += s.length();
                }

                int spaces = lineLength - nonSpace;
                int spaceLots = words.size() - 1;
                int spacePerLot = spaces / spaceLots;
                int extraSpaces = spaces % spaceLots;

                for (int i = 0; i < words.size() - 1; i++) {
                    stb.append(words.get(i));
                    for (int j = 0; j < spacePerLot; j++) {
                        stb.append(" ");
                    }
                    // append extra spaces
                    if (extraSpaces > 0) {
                        stb.append(" ");
                        extraSpaces --;
                    }
                }
            }
            // last one word
            stb.append(words.get(words.size() - 1));
            stb.append("\n");

            return stb.toString();
        }
    }



    /**
     * 12  [Bash Brace Expansion]  LC1087 给出 a{d,c,b}e 得出ade ace abe。
     * 没有nested的括号，但是可以里面的字符有括号。我是先parse再dfs。 followup是括号可以nested
     */

    public void T12_test() {
        String s = "{a,b}c{d,e}f";
        String[] result = new T12_L1087_Solution().expand(s);
        System.out.println(Arrays.toString(result));
    }
    class T12_L1087_Solution {
        public String[] expand(String S) {
            String[] array = S.split("\\{|\\}");
            System.out.println("Array===" + Arrays.toString(array));
            List<String> res = new ArrayList<>();
            List<List<String>> input = new ArrayList<>();
            for(String str : array) {
                List<String> temp = Arrays.asList(str.split(","));
                Collections.sort(temp);
                if(!temp.isEmpty())
                    input.add(temp);
            }

            System.out.println(input);
            dfs(res, input, 0, new StringBuilder());
            System.out.println("res = " + res);
            return res.toArray(new String[res.size()]);
        }

        private void dfs(List<String> res, List<List<String>> input, int idx, StringBuilder sb) {
            if (idx >= input.size()) {
                res.add(sb.toString());
                return;
            }
            List<String> temp = input.get(idx);
            for(int i = 0; i < temp.size(); i++) {
                int len = sb.length();
                sb.append(temp.get(i));
                dfs(res, input, idx + 1, sb);
                sb.setLength(len);
            }
        }
    }


    /**
     * LC 1096. Brace Expansion II
     *
     * Under a grammar given below, strings can represent a set of lowercase words.
     * Let's use R(expr) to denote the set of words the expression represents.
     * https://leetcode.com/problems/brace-expansion-ii/
     *
     */

    public void T12_test2() {
        String input = "{a,b}{c,{d,e}}";
        List<String> res = new T12_LC1096_Solution().braceExpansionII(input);
        System.out.println("Res = " + res);
    }
    class T12_LC1096_Solution {
        public List<String> braceExpansionII(String expression) {
            if (expression == null || expression.length() == 0) return new ArrayList<>();
            List<List<String>> group = new ArrayList<>();
            Set<String> res = new TreeSet<>();
            int level = 0, start = -1;
            for (int i = 0; i < expression.length(); i++) {
                char cur = expression.charAt(i);
                if (cur == '{') {
                    if (level == 0) start = i + 1; //because we use recursive so we just need to mark the first level everytime
                    level++;
                } else if (cur == '}') {
                    level--;
                    if (level == 0) {
                        group.add(braceExpansionII(expression.substring(start, i))); //find the end and do recursive in what in the brace
                    }
                } else if (cur == ',' && level  == 0) {
                    res.addAll(combine(group)); //inside the braces, combine the groups{a,b}, get a and b
                    group.clear();
                } else if (level == 0) {
                    group.add(Arrays.asList(String.valueOf(cur))); //normal letter, just add to the group eg. "{a, b}c{d, e}"
                }
            }
            System.out.println("-----------");
            System.out.println(group);
            System.out.println("-----------");
            res.addAll(combine(group));//combine all the groups, eg "{a,b}{c,{d,e}}" there should be 2
            return new ArrayList<>(res);
        }

        private List<String> combine(List<List<String>> group) {
            List<String> res = new ArrayList<>();
            res.add("");
            for (List<String> g : group) {
                List<String> temp = new ArrayList<>();
                for (String p : res) {
                    for (String s : g) {
                        temp.add(p + s);
                    }
                }
                res = temp;
            }
            return res;
        }
    }
}
