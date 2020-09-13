package mj_google;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class G1_1 {
    public static void main(String[] args) {

    }

    /**
     * 6. LC489 位置地形扫地机器人 高频 7次
     * LC489 位置地形扫地机器人 高频 7次
     * <p>
     * Given a robot cleaner in a room modeled as a grid.
     * <p>
     * Each cell in the grid can be empty or blocked.
     * <p>
     * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
     * <p>
     * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
     * <p>
     * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
     * <p>
     * interface Robot {
     * // returns true if next cell is open and robot moves into the cell.
     * // returns false if next cell is obstacle and robot stays on the current cell.
     * boolean move();
     * <p>
     * // Robot will stay on the same cell after calling turnLeft/turnRight.
     * // Each turn will be 90 degrees.
     * void turnLeft();
     * void turnRight();
     * <p>
     * // Clean the current cell.
     * void clean();
     * }
     * <p>
     * Input:
     * room = [
     * [1,1,1,1,1,0,1,1],
     * [1,1,1,1,1,0,1,1],
     * [1,0,1,1,1,1,1,1],
     * [0,0,0,1,0,0,0,0],
     * [1,1,1,1,1,1,1,1]
     * ],
     * row = 1,
     * col = 3
     * <p>
     * Explanation:
     * All grids in the room are marked by either 0 or 1.
     * 0 means the cell is blocked, while 1 means the cell is accessible.
     * The robot initially starts at the position of row=1, col=3.
     * From the top left corner, its position is one row below and three columns right.
     * <p>
     * The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
     * The robot's initial position will always be in an accessible cell.
     * The initial direction of the robot will be facing up.
     * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
     * Assume all four edges of the grid are all surrounded by wall.
     */


    public static class Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move() {
            return true;
        }

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft() {
            return;
        }

        public void turnRight() {
            return;
        }

        // Clean the current cell.
        public void clean() {
            return;
        }
    }

    public void cleanRoom(Robot robot) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int x = 0;
        int y = 0;
        dfs(map, robot, 0, 0, 0);
    }

    private void dfs(Map<Integer, Set<Integer>> map, Robot robot, int x, int y, int d) {
        Set<Integer> set = map.get(x);
        if (set == null) {
            set = new HashSet<>();
            map.put(x, set);
        }
        if (!set.add(y)) {
            reverse(robot);
            return;
        }
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            if (newD == 0 && robot.move()) {
                dfs(map, robot, x, y - 1, newD);
            } else if (newD == 1 && robot.move()) {
                dfs(map, robot, x + 1, y, newD);
            } else if (newD == 2 && robot.move()) {
                dfs(map, robot, x, y + 1, newD);
            } else if (newD == 3 && robot.move()) {
                dfs(map, robot, x - 1, y, newD);
            }
            robot.turnRight();
        }
        reverse(robot);
    }

    private void reverse(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    class LC489_Robert {

        // 0: up, 1: right, 2: down , 3: left

        public void cleanRoom(Robot robot) {

            dfs(new HashSet<>(), 0, 0, 0, robot);
        }

        private void dfs(Set<String> visited, int i, int j, int currDir, Robot r) {
            if(visited.contains(i + "," + j)) return;
            visited.add(i + "," + j);
            r.clean();
            for(int k = 0 ; k < 4; k++) {
                if(r.move()) {
                    int x = i, y = j;
                    switch(currDir) {
                        case 0: x -= 1; // up
                            break;
                        case 1: y += 1; // right
                            break;
                        case 2: x += 1; // down
                            break;
                        case 3: y -= 1; // left
                            break;
                    }
                    dfs(visited, x, y, currDir, r);
                    r.turnRight();
                    r.turnRight();
                    r.move();
                    r.turnRight();
                    r.turnRight();
                }
                r.turnRight();
                currDir += 1;
                currDir %= 4;
            }
        }
    }

    class LC489_Robert_Solution {
        int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
        public void cleanRoom(Robot robot) {
            Set<String> set = new HashSet<String>();
            cleanNow(robot, set, 0, 0, 0);
        }

        private void cleanNow(Robot robot, Set<String> visited, int currDir, int x, int y) {
            String path = x + "->" + y;
            if(visited.contains(path)) return;
            visited.add(path);
            robot.clean();
            for(int i=0;i<4;i++) {
                if(robot.move()) {
                    int nx = x+dirs[currDir][0];
                    int ny = y+dirs[currDir][1];
                    cleanNow(robot, visited, currDir, nx, ny);
                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnLeft();
                    robot.turnLeft();
                }
                robot.turnRight();
                currDir = (currDir+1)%4;
            }
        }
    }

    /**
     * 7. LC855 考试找位子，尽量分散坐，人会 离开 高频 6次
     * LC849. Maximize Distance to Closest Person 离最近的人的最大距离
     */

    public static void top7_test() {
        int[] seats = {1, 0, 0, 0};
        int res1 = top7_lc849_max_distance_to_closest(seats);
        int res2 = top7_lc849_max_distance_to_closest2(seats);
        System.out.println("res1 = " + res1);
        System.out.println("res2 = " + res2);
    }

    // Time: O(n) Space: O(n)
    public static int top7_lc849_max_distance_to_closest(int[] seats) {
        int n = seats.length;
        int result = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                list.add(i);
            }
        }

        for (int i = 0; i < list.size(); i++){
            if (i == 0) {
                // the left most position
                result = Math.max(result, list.get(0));
            } else {
                // the median position
                result = Math.max(result, (list.get(i) - list.get(i - 1))/2);
            }
        }

        // the right most position
        if (!list.isEmpty()) {
            result = Math.max(result, n - 1 - list.get(list.size() - 1));
        }
        return result;
    }


    // Time: O(n) Space O(1)
    // two pointers
    public static int top7_lc849_max_distance_to_closest2(int[] seats) {
        int n = seats.length;
        int start = 0, result = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] != 1) {
                continue;
            }

            // seat[i] == 1
            // [start, i - 1] is a continous subarray with all )s
            if (start == 0) { // left most position
                result = Math.max(result, i - start);
            } else {
                result = Math.max(result, (i - start + 1) / 2);
            }
            start = i + 1;
        }

        result = Math.max(result, n - start);
        return result;
    }


    public void T7_test() {
        T855_ExamRoom sln = new T855_ExamRoom(10);
        sln.seat();
        sln.seat();
        sln.seat();
        sln.seat();
    }

    class T855_ExamRoom {
        private int n;
        private TreeSet<Interval> availableGaps;
        private Map<Integer, Interval> boundaries;
        public T855_ExamRoom(int N) {
            this.n = N;
            this.availableGaps = new TreeSet<>((i, j) -> {
                if (i.length != j.length) return j.length - i.length;
                return i.start - j.start;
            });
            // sorted by interval length, if length is same, then sorted by start

            this.boundaries = new HashMap<>();
            addInterval(0, N-1);
        }

        public int seat() {
            Interval top = availableGaps.pollFirst();
            boundaries.remove(top.start);
            boundaries.remove(top.end);

            // calculate result
            int resultIdx = 0;
            if (top.start == 0) {
                resultIdx = 0;
            } else if (top.end == n - 1) {
                resultIdx = n - 1;
            } else {
                resultIdx = top.start + top.length;
            }
            System.out.println("result = " + resultIdx);
            // add intervals
            if (resultIdx > top.start) {
                addInterval(top.start, resultIdx - 1);
            }
            if (resultIdx < top.end) {
                addInterval(resultIdx + 1, top.end);
            }

            return resultIdx;
        }

        public void leave(int p) {
            Interval prev = boundaries.get(p - 1);
            Interval next = boundaries.get(p + 1);
            if (prev != null) {
                removeInterval(prev);
            }
            if (next != null) {
                removeInterval(next);
            }
            // add new Interval
            addInterval(prev == null ? p : prev.start, next == null? p: next.end);
        }

        // insert interval into availableGaps and boundaries
        private void addInterval(int start, int end) {

            Interval temp = new Interval(start, end);
            availableGaps.add(temp);
            boundaries.put(start, temp);
            boundaries.put(end, temp);
            System.out.println("---------------------------------");
            System.out.println("availableGaps = " + availableGaps);
            System.out.println("boundaries = " + boundaries);
            System.out.println("=================================");
        }

        private void removeInterval(Interval temp) {
            availableGaps.remove(temp);
            boundaries.remove(temp.start);
            boundaries.remove(temp.end);
        }

        public class Interval {
            int start;
            int end;
            int length;
            public Interval(int _s, int _e) {
                this.start = _s;
                this.end = _e;
                if (start == 0 || end == n - 1) {
                    this.length = end - start;
                } else {
                    this.length = (end - start) /2;
                }
            }

            public String toString() {
                return "[" + start + " : " + end + " => " + length + "]";
            }
        }
    }


    /**
     * 8. Key有过期时间的hashmap 高频 6次
     * 8. Key有过期时间的hashmap 高频 6次
     * Create a map with expiring entries:
     * Example
     * 12:00:00 - put(10, 25, 5000)
     * 12:00:04 - get(10) -> 25
     * 12:00:06 - get(10) -> null
     * <p>
     * <p>
     * 思路：两个hash map，一个记录key，value pair，一个记录key的过期时间，get的时候检查key是否过期，如果过期了，删除key返回null
     * <p>
     * Put方法有三个参数，除了key，value还有个duration
     */

    public void top8_test() {
        MapWithDuration map = new MapWithDuration();
        map.put(10, 25, 1000);
        System.out.println("a: " + map.get(10));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("b: " + map.get(10));
    }

    public class MapWithDuration {
        Map<Integer, Integer> kv;
        Map<Integer, Long> ke; // key and duration time

        public MapWithDuration() {
            kv = new HashMap<>();
            ke = new HashMap<>();
        }

        public void put(Integer key, Integer value, Integer duration) {
            Long currentTimeInMs = System.currentTimeMillis();
            kv.put(key, value);
            ke.put(key, currentTimeInMs + duration);
        }

        public Integer get(Integer key) {
            if (kv.containsKey(key) && ke.containsKey(key)) {
                if (System.currentTimeMillis() < ke.get(key)) {
                    return kv.get(key);
                } else {
                    // expired, delete it
                    kv.remove(key);
                    ke.remove(key);
                }
            }
            return null;
        }
    }

    /**
     * Follow up: 采用更主动的策略删除过期的Key
     * 思路；创建后台线程定期清理过期的Key。
     * <p>
     * 用两个map，一个装<key, value>一个装<key, expiredTime>
     * <p>
     * 在get中采用lazy deletion，get的时候检查key是否过期，如果过期的话两个map中都删除key，返回null。put的时候每次都更新key的expiredTime。
     * <p>
     * 后台线程每过一段时间遍历所有key，调用get方法删除过期key。此处为了避免多线程冲突，Map用ConcurrentHashMap实现。
     * <p>
     * we can also use read and write lock
     */

    public static class T8_MyMap<K, V> {
        Map<K, V> map;
        Map<K, Long> time;
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        private Thread clearThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (K key : map.keySet()) {
                        get(key);
                    }
                }
            }
        });

        public T8_MyMap() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }

        public T8_MyMap(int capacity) {
            this(capacity, DEFAULT_LOAD_FACTOR);
        }

        public T8_MyMap(int capacity, float loadFactor) {
            map = new ConcurrentHashMap<>(capacity, loadFactor);
            time = new ConcurrentHashMap<>(capacity, loadFactor);
            clearThread.start();
        }

        public synchronized V get(K key) {
            long now = System.currentTimeMillis();
            Long expired = time.get(key);
            if (expired == null) {
                return null;
            }
            if (Double.compare(now, expired) > 0) {
                map.remove(key);
                time.remove(key);
                return null;
            } else {
                return map.get(key);
            }
        }

        public V put(K key, V value, long duration) {
            long now = System.currentTimeMillis();
            long expired = now + duration;
            time.put(key, expired);
            return map.put(key, value);
        }
    }

    /**
     *
     * 9. 多个不重复的长方形内随机取点【类似LC497?】 高频 6次
     * onsite最后一轮的面试题。其他轮都没啥发的必要就不发了。
     * 题目是这样的 给你一个list的长方形。。每个长方形面积不一样，但是你要取个点，这个点可以是任何长方形里的。
     * 但是要你每次取点的概率都是一样的。不会因为长方形大小而不同。
     *
     * 长方形的输入形式：左下坐标和长宽
     *
     * （主要考察加权抽样和merge squares）
     * 思路：把重复的长方形分成不重复的LC 类似题目：小块，然后用prefix sum进行二分查找
     *
     * 请问follow up是啥思路？用什么思路处理重叠的矩阵？以及是否需要考虑三重或者更多重的重叠区域
     *
     *
     * LC850 Rectangle Area II （如果有重复部分，思路和此题打碎矩形去掉重复部分的思路一样）
     *
     * LC497 Random Point in Non-overlapping Rectangles （没有重复部分的原题）
     *
     * LC528 Random Pick with Weight （类似题目）
     *
     *
     * 对于有多个矩形的情况，我们可以考虑先选出一个矩形，再在该矩形内选点
     *
     * 要求每个点选取的概率相同，那么选取一个矩形的概率就和该矩形的面积成正比
     *
     * 所以我们可以把所有矩形的面积的和sum算出来，然后在rand一个数%sum，再判断落在哪个矩形里
     *
     * 比如说有面积为3, 2, 1, 4的矩形，总和是10，randM = rand() % 10 ，如果randM==0,1,2就选面积为3的矩形，randM == 3,4就选面积为2的矩形，其他的同理
     *
     * 选出了矩形，然后在该矩形内选点即可
     */


    /**
     * 528. Random Pick with Weight
     * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
     *
     * Note:
     *
     * 1 <= w.length <= 10000
     * 1 <= w[i] <= 10^5
     * pickIndex will be called at most 10000 times.
     * Example 1:
     *
     * Input:
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * Output: [null,0]
     * Example 2:
     *
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * Output: [null,0,1,1,1,0]
     */

    public void T9_test_lc528() {
        int[] array = {1, 8, 10};

        T9_LC528_randomPickWithWeight sln = new T9_LC528_randomPickWithWeight(array);
        System.out.println(sln.pickIndex());
    }
    class T9_LC528_randomPickWithWeight {

        List<Integer> psum = new ArrayList<>();
        int tot = 0;
        Random rand = new Random();

        public T9_LC528_randomPickWithWeight(int[] w) {
            for (int x : w) {
                tot += x;
                psum.add(tot);
            }
        }

        public int pickIndex() {
            int targ = rand.nextInt(tot);
            return smallestNumLarger(psum, targ);
        }

        private int smallestNumLarger(List<Integer> list, int target) {
            int l = 0, r = list.size() - 1;
            while (l + 1 < r) {
                int m = l + (r - l) / 2;
                if (list.get(m) <= target) {
                    l = m;
                } else {
                    r = m;
                }
            }

            if (list.get(l) > target) {
                return l;
            } else {
                return r;
            }
        }
    }

    public void T9_test_lc497() {
        int[][] rects = {
                {1, 1, 2, 4},
                {3, 2, 5, 4},
                {2, 5, 5, 6}
        };
        T9_LC497 sln = new T9_LC497(rects);
        int[] r1 = sln.pick();
        System.out.println(Arrays.toString(r1));
        int[] r2 = sln.pick();
        System.out.println(Arrays.toString(r2));
    }

    /**
     * 497. Random Point in Non-overlapping Rectangles
     * Given a list of non-overlapping axis-aligned rectangles rects,
     * write a function pick which randomly and uniformily picks
     * an integer point in the space covered by the rectangles.
     *
     * Time Complexity: O(N)O(N) preprocessing. O(\log(N))O(log(N)) pick.
     * Space Complexity: O(N)O(N)
     */
    class T9_LC497 {

        int[][] rects;
        List<Integer> psum = new ArrayList<>();
        int totalPoints = 0;
        Random rand = new Random();


        public T9_LC497(int[][] rects) {
            this.rects = rects;
            for (int[] x : rects){
                totalPoints += (x[2] - x[0] + 1) * (x[3] - x[1] + 1);
                psum.add(totalPoints);
            }

            System.out.println("presum = " + psum);
        }

        public int[] pick() {
            /**
             * nextInt(int bound)
             * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive),
             * drawn from this random number generator's sequence.
             */
            int targ = rand.nextInt(totalPoints);

            int lo = 0;
            int hi = rects.length - 1;
            // binary search find the lowest num >= target
            int idx = -1;
            while (lo + 1 < hi) {
                int mid = lo + (hi - lo) / 2;
                if (targ >= psum.get(mid)) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }

            if (psum.get(lo) > targ) {
                idx = lo;
            } else {
                idx = hi;
            }

            int[] x = rects[idx];
            int width = x[2] - x[0] + 1;
            int height = x[3] - x[1] + 1;
            int base = psum.get(idx) - width * height;
//            System.out.println("==============");
//            System.out.println("idx = " + idx);
//            System.out.println("x = " + Arrays.toString(x));
//            System.out.println("targ = " + targ);
//            System.out.println("base = " + base);
//            System.out.println("width = " + width);
//            System.out.println("height = " + height);
            return new int[]{
                    x[0] + (targ - base) % width,
                    x[1] + (targ - base) / width
            };
        }
    }

    /**
     * LC223 Rectangle Area
     * https://leetcode.com/problems/rectangle-area/
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (C < E || G < A) // no overlap
            return (G - E) * (H - F) + (C - A) * (D - B);

        if (D < F || H < B) // no overlap
            return (G - E) * (H - F) + (C - A) * (D - B);

        int right = Math.min(C, G);
        int left = Math.max(A, E);
        int top = Math.min(H, D);
        int bottom = Math.max(F, B);

        return (G - E) * (H - F) + (C - A) * (D - B) - (right - left)
                * (top - bottom);
    }


    /**
     * Swipe line:
     * Time: O(N^2 log N)  N is the number of rectangles
     * Space: O(N)
     */


    public void T9_test_LC850() {
        T9_LC850_RectangleArea sln = new T9_LC850_RectangleArea();
        int[][] rectangles = {
                {0, 0, 2, 2},
                {1, 0, 3, 1},
                {0, 4, 1, 5},
                {2, 4, 3, 5}
        };
        int res = sln.rectangleArea(rectangles);
        System.out.println("res = " + res);
    }


    /**
     * This solution is borrowed from the leetcode official solution number 3: line sweep. But the styling of that solution is pretty bad,
     * so I rewrote it, added a sub class called Interval,
     * and used TreeMap for addition & removal, which should reduce both operations complexity to O(logn).
     *
     * order every line from the rectangle by y index. mark start of rectangle line (bottom) as OPEN,
     * mark end of rectangle line (top) as CLOSE.
     * sweep line from bottom to top, each time y coordinate changed,
     * means all intervals on current y is sweeped, merge the length back together, multiply by the y coordinate diff.
     */
    class T9_LC850_RectangleArea {

        private class Interval {
            public int start;
            public int end;
            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
            public String toString() {
                return "[" + start + "," + end + "]";
            }
        }

        public int rectangleArea(int[][] rectangles) {
            int OPEN = 0, CLOSE = 1;
            int[][] events = new int[rectangles.length * 2][4];

            int t = 0;
            /**
             open of rectangle: add to active set
             close of rectangle: remove from active set
             */
            for (int[] rec: rectangles) {
                // y, open_or_close, start, end
                events[t++] = new int[]{ rec[1], OPEN, rec[0], rec[2] };
                events[t++] = new int[]{ rec[3], CLOSE, rec[0], rec[2] };
            }

            /**
             sort by current y index
             */
            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

            // tree map: sort by interval.start.
            // if start is same, sort by end. ascending order
            TreeMap<Interval, Integer> active = new TreeMap<>((a, b) -> {
                if (a.start != b.start) return a.start - b.start;
                return a.end - b.end;
            });

            // first y coordinate at the bottom
            int currentY = events[0][0];
            long ans = 0;
            for (int[] event : events) {
                int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

                // Calculate sum of intervals in active set, that's the active intervals in prev line
                if (y > currentY) {
                    ans += calculateInterval(active) * (y - currentY);
                    // update currentY
                    currentY = y;
                }

                /**
                 add or remove new interval to current active
                 */
                if (typ == OPEN) {
                    addInterval(active, x1, x2);
                } else {
                    removeInterval(active, x1, x2);
                }
            }
            ans %= 1_000_000_007;
            return (int) ans;
        }

        /**
         using tree map, should be able to insert in log(N) time
         */
        private void addInterval(TreeMap<Interval, Integer> map, int x1, int x2) {
            Interval interval = new Interval(x1, x2);
            map.put(interval, map.getOrDefault(interval, 0) + 1);
        }

        /**
         using tree map, should be able to remove in log(N) time
         */
        private void removeInterval(TreeMap<Interval, Integer> map, int x1, int x2) {
            Interval interval = new Interval(x1, x2);
            map.put(interval, map.getOrDefault(interval, 0) - 1);
            if (map.get(interval) == 0) map.remove(interval);
        }

        private long calculateInterval(TreeMap<Interval, Integer> map) {
            long width = 0;
            int cur = -1;
            // traverse the all active intervals, then, get the width of the active intervals
            for (Interval interval : map.keySet()) {
                // get cur
                cur = Math.max(cur, interval.start);
                width += Math.max(interval.end - cur, 0);
                // update cur
                cur = Math.max(cur, interval.end);
            }
            return width;
        }
    }


    /**
     * Approach #4: Segment Tree
     * Intuition and Algorithm
     *
     * As in Approach #3, we want to support add(x1, x2), remove(x1, x2), and query().
     * While outside the scope of a typical interview, this is the perfect setting for using a segment tree.
     * For completeness, we include the following implementation.
     *
     * Time: O(N log N), N is the number of rectangles
     * Space: O(N)
     */
    class T850_Solution2 {

        public int rectangleArea(int[][] rectangles) {
            int OPEN = 1, CLOSE = -1;
            int[][] events = new int[rectangles.length * 2][];
            Set<Integer> Xvals = new HashSet();
            int t = 0;
            for (int[] rec: rectangles) {
                events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
                events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
                Xvals.add(rec[0]);
                Xvals.add(rec[2]);
            }

            Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

            Integer[] X = Xvals.toArray(new Integer[0]);
            Arrays.sort(X);
            Map<Integer, Integer> Xi = new HashMap();
            for (int i = 0; i < X.length; ++i)
                Xi.put(X[i], i);

            Node active = new Node(0, X.length - 1, X);
            long ans = 0;
            long cur_x_sum = 0;
            int cur_y = events[0][0];

            for (int[] event: events) {
                int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
                ans += cur_x_sum * (y - cur_y);
                cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
                cur_y = y;

            }

            ans %= 1_000_000_007;
            return (int) ans;
        }


        class Node {
            int start, end;
            Integer[] X;
            Node left, right;
            int count;
            long total;

            public Node(int start, int end, Integer[] X) {
                this.start = start;
                this.end = end;
                this.X = X;
                left = null;
                right = null;
                count = 0;
                total = 0;
            }

            public int getRangeMid() {
                return start + (end - start) / 2;
            }

            public Node getLeft() {
                if (left == null) left = new Node(start, getRangeMid(), X);
                return left;
            }

            public Node getRight() {
                if (right == null) right = new Node(getRangeMid(), end, X);
                return right;
            }

            public long update(int i, int j, int val) {
                if (i >= j) return 0;
                if (start == i && end == j) {
                    count += val;
                } else {
                    getLeft().update(i, Math.min(getRangeMid(), j), val);
                    getRight().update(Math.max(getRangeMid(), i), j, val);
                }

                if (count > 0) total = X[end] - X[start];
                else total = getLeft().total + getRight().total;

                return total;
            }
        }
    }




    /**
     * 10. LC853 car fleet问题 高频 6次
     * 多辆车在单行路上开，给起始地点和车速，不能超车只能位置重合跟着。问最后有几次的重合车次撞线。
     *
     *
     * 思路：
     *
     * TreeMap
     *
     * 用treeMap<position, time>去存，从接近target的地方往后遍历，local variable存最大撞线时间。若currTime > maxTime，则车次++
     *
     *
     * Sort
     *
     * 计算每个位置的车到destination的时间，然后根据位置把车排序，从后往前scan排序后的car数组，如果cars[i]的到达时间比cars[i-1]要晚，
     * 说明可以合并cars[i-1]和cars[i]，同时更新car[i-1]的到达时间
     */

    public void T10_test() {
        int target = 12;
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};

        T10_LC853_CarFleet sln = new T10_LC853_CarFleet();
        int result = sln.carFleet(target, position, speed);
        System.out.println("result = " + result);
    }

    /**
     * Time: O(n * log n). used sort
     * Space: O(n)
     */
    class T10_LC853_CarFleet {

        public int carFleet(int target, int[] position, int[] speed) {
            int len = position.length;
            Car[] cars = new Car[len];
            for(int i = 0 ; i < len ; i++) {
                cars[i] = new Car(position[i], speed[i], target);
            }

            // sort by position, ascending order
            Arrays.sort(cars, (a, b) -> a.pos - b.pos);

            //  System.out.println(Arrays.toString(cars));
            int carFleet = len;
            for(int i = len - 1 ; i > 0 ; i--) {
                // 后一个车所用的时间 >= 前一个车所用的时间，那么这两辆车可以 归为 一个fleet.
                if(Double.compare(cars[i].time, cars[i - 1].time) >= 0) {
                    cars[i - 1].time = cars[i].time;
                    carFleet--;
                }
            }
            return carFleet;
        }

        private class Car {
            int pos;
            int speed;
            double time;
            public Car(int pos, int speed, int target) {
                this.pos = pos;
                this.speed = speed;
                time = (target - pos + 0.0) / speed;
            }

            public String toString() {
                return "[" + pos + " : " + speed + " : " + time + "]";
            }
        }
    }

}
