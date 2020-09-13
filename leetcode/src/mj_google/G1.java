package mj_google;

import ds.Debug;
import ds.TreeNode;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class G1 {

    public static void main(String[] args) {
//        test_top3_f2();
//        t3_test_f3();
//        top5_test();
//        top7_test();
//        top8_test();
//        T2Task684RedundantConnection t2t684 = new T2Task684RedundantConnection();
//        t2t684.test();
//        T2_Test();
//        t2_f_maxPathSum();
//        t2_max_money();
        G1 g1 = new G1();
//        g1.T1_test1();
//        g1.T1_test2();
//        g1.T2_LC684_test();
//        g1.T2_LC685_test();;
//        g1.T2_delete_from_BST();

//        T3_test();
//        top5_test();
//        g1.T7_test();
//        g1.T9_test_lc497();
//        g1.T9_test_LC850();
//        g1.T9_test_lc528();


    }

    /**
     * TOP 1
     *
     * [LC1057/
     * LC近的自行车，一旦某辆自行车被占，其他人只能找别的自行车。
     *
     * OPOBOOP
     * OOOOOOO
     * OOOOOOO
     * OOOOOOO
     * BOOBOOB
     * 红色的人找到第一行的自行车，距离最近。
     * 蓝色的人离第一行自行车最近，但自行车已经被红色人占有，所以他只能找离他第二近的，右下角的自行车。
     * 问：把人和自行车配对，输出vector<pair<int, int>>每个人对应的自行车. {i, j} 是人i对应自行车j
     * Cindy Cheung: 感覺跟這題有點像 https://www.careercup.com/question?id=5687196447670272
     */

    public void T1_test1() {
        T1_LC1057_CampusBikes sln = new T1_LC1057_CampusBikes();

        int[][] workers = {{0,0},{1,1},{2,0}};
        int[][] bikes = {{1,0},{2,2},{2,1}};
        int[] result = sln.assignBikes(workers, bikes);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 只考虑局部最优，确保每一个worker 拿到他能看到的最近的自行车， 而不是全局最优
     */
    class T1_LC1057_CampusBikes {
        /**
         * Time: O( N log N). N = num_worker * num_bike
         * Space: O(N)
         */
        class Tuple implements Comparable<Tuple> {
            int distance;
            int workderId;
            int bikeId;

            public Tuple(int distance, int workderId, int bikeId) {
                this.distance = distance;
                this.workderId = workderId;
                this.bikeId = bikeId;
            }

            public int compareTo(Tuple that) {
                if (this.distance == that.distance) {
                    return this.bikeId - that.bikeId;
                }
                return this.distance - that.distance;
            }
        }

        public int[] assignBikes(int[][] workers, int[][] bikes) {
            List<Tuple> list = new ArrayList<>(workers.length * bikes.length);

            for (int i = 0; i < workers.length; i++) {
                // worker index => i
                // bike index => j
                for (int j = 0; j < bikes.length; j++) {
                    list.add(new Tuple(calDistance(workers[i], bikes[j]), i, j));
                }
            }

            // sort the list by distance, if distance is same, sort by bikeId
            Collections.sort(list, new Comparator<Tuple>() {
                @Override
                public int compare(Tuple t1, Tuple t2) {
                    return t1.distance == t2.distance ? t1.bikeId - t2.bikeId : t1.distance - t2.distance;
                }
            });

            Set<Integer> bikeSet = new HashSet<>(); // only 1 hashset is needed..

            int[] res = new int[workers.length];
            Arrays.fill(res, -1);

            System.out.println("=> " + Arrays.toString(res));
            for (int i = 0; i < list.size(); i++) {
                int workerId = list.get(i).workderId;
                int bikeId = list.get(i).bikeId;
                // if haven't filled yet
                if (res[workerId] == -1) {
                    if (!bikeSet.contains(bikeId)) {
                        res[workerId] = bikeId;
                        bikeSet.add(bikeId);
                    }
                }
            }
            return res;
        }

        private int calDistance(int[] worker, int[] bike) {
            return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        }
    }


    public void T1_test2() {
//                char[][] graph = new char[][] {
//                        {'O', 'P', 'O', 'B', 'P', 'O', 'P'},
//                        {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
//                        {'B', 'O', 'O', 'B', 'O', 'O', 'B'}
//                };
        char[][] graph = new char[][]{
                {'P', 'O', 'O', 'B', 'P', 'O', 'B'}
        };
        T1_KMAlgorithm kma = new T1_KMAlgorithm();
        System.out.println(kma.match(graph));
    }
    public class T1_KMAlgorithm {
        char[][] graph;

        List<int[]> bikes;

        List<int[]> persons;

        int[][] G;

        int[] personExpect;

        int[] bikeExpect;

        boolean[] visitedPerson;

        boolean[] visitedBike;

        int[] match;

        int[] slack;

        //return : person[i, j] + bike[i, j]
        public List<List<Integer>> match(char[][] graph) {

            this.graph = graph;

            init();

            KM();

            List<List<Integer>> res = new ArrayList<>();

            for (int i = 0; i < match.length; i++) {

                if (match[i] == -1) continue;

                int[] person = persons.get(match[i]);

                int[] bike = bikes.get(i);

                res.add(Arrays.asList(person[0], person[1], bike[0], bike[1]));

            }

            return res;


        }

        private void init() {

            int rows = graph.length;

            int cols = graph[0].length;

            persons = new ArrayList<>();

            bikes = new ArrayList<>();

            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < cols; j++) {

                    if (graph[i][j] == 'P') {

                        persons.add(new int[]{i, j});

                    }

                    if (graph[i][j] == 'B') {

                        bikes.add(new int[]{i, j});

                    }

                }

            }

            // build cost graph

            G = new int[persons.size()][bikes.size()];

            for (int i = 0; i < G.length; i++) {

                for (int j = 0; j < G[0].length; j++) {

                    int[] person = persons.get(i);

                    int[] bike = bikes.get(j);

                    int dis = Math.abs(person[0] - bike[0]) + Math.abs(person[1] - bike[1]);

                    G[i][j] = -dis;   // 求最小距离，所以这里权值用负数表示

                }

            }

            System.out.println("........");
            Debug.printMatrix(G);

            personExpect = new int[persons.size()];

            bikeExpect = new int[bikes.size()];

            visitedPerson = new boolean[persons.size()];

            visitedBike = new boolean[bikes.size()];

            match = new int[bikes.size()];

            Arrays.fill(match, -1);

            slack = new int[bikes.size()];

            Arrays.fill(slack, Integer.MAX_VALUE);


            // init person expectation array

            for (int i = 0; i < persons.size(); i++) {

                personExpect[i] = G[i][0];

                for (int j = 0; j < bikes.size(); j++) {

                    personExpect[i] = Math.max(personExpect[i], G[i][j]);

                }

            }

        }

        boolean find(int person) {

            visitedPerson[person] = true;

            for (int bike = 0; bike < bikes.size(); bike++) {

                if (visitedBike[bike]) continue;

                int gap = personExpect[person] + bikeExpect[bike] - G[person][bike];

                if (gap == 0) {

                    visitedBike[bike] = true;

                    if (match[bike] == -1 || find(match[bike])) {

                        match[bike] = person;

                        return true;

                    }

                } else {

                    slack[bike] = Math.min(slack[bike], gap);

                }

            }

            return false;

        }

        void KM() {

            for (int i = 0; i < persons.size(); i++) {

                Arrays.fill(slack, Integer.MAX_VALUE);

                // assign bike for each person

                while (true) {

                    Arrays.fill(visitedPerson, false);

                    Arrays.fill(visitedBike, false);

                    if (find(i)) break;

                    // if can not assign one bike to the person, decrease the expectation

                    int tmp = Integer.MAX_VALUE;

                    for (int bike = 0; bike < bikes.size(); bike++) {

                        if (!visitedBike[bike]) tmp = Math.min(tmp, slack[bike]);

                    }

                    for (int person = 0; person < persons.size(); person++) {

                        if (visitedPerson[person]) {

                            personExpect[person] -= tmp;

                        }

                    }

                    for (int bike = 0; bike < bikes.size(); bike++) {

                        if (visitedBike[bike]) {

                            bikeExpect[bike] += tmp;

                        } else {

                            slack[bike] -= tmp;

                        }

                    }

                }

            }

        }

    }


    /**
     *
     * TOP 2
     * LC684，BT删除多余边, undirected
     * input:
     * Input: [[1,2], [1,3], [2,3]]
     * Output: [2,3]
     * Explanation: The given undirected graph will be like this:
     *   1
     *  / \
     * 2 - 3
     *
     * 思路：
     * 1。 dfs: 对于每一条边， 检测图是否能替代这条边， 如果是， 那么可以删除这条边
     * 2。 union-find: 对于每一条边上的点， 找到它的parent, 如果parent 相同， 那么这条边可以删除
     *
     *  684是无向图，用union find，遍历edge，每次把parent赋给左节点，若发现母节点相同则为多余
     *  Time: O(n)
     *
     *
     * 685 三种错误情况，multiple parents，cycle， both 在both的情况下仔细讨论删除第一个指向multiple parent的node
     * 685 有很多隐含条件：
     * 比如 cycle 最多只可能有一个，
     * multiple parents 最多两个，
     * 没有multiple parents  的话就一定有cycle,
     *
     * 这样就多了很多限制，分情况讨论的时候就会简单许多。
     *
     * 3 invalid situations
     * case1: 2 parents no circle
     *
     * case2: 2 parents with circle
     *
     * case3: 1 parent with circle
     *
     * Input: [[1,2], [1,3], [2,3]]
     * Output: [2,3]
     * Explanation: The given directed graph will be like this:
     *   1
     *  / \
     * v   v
     * 2-->3
     *
     * 2 main steps
     * 1> check whether there exists a node with 2 parents, if yes, store the two edges.
     *
     * 2> if no edge yielded from step 1, apply union-find and find the edge creating cycle (same as 684);
     * ELSE, apply union-find to ALL edges EXCEPT edges from step 1,
     * then check:
     * if edge 1 from step 1 creates a cycle, return edge 1;
     * else return edge 2.
     *
     * Follow up1: 给一棵二叉搜索树，有一条多余边，删除它
     *
     * 例子：
     *
     *      7
     *
     *    /  \
     *
     *   5    9
     *
     * /  \   /
     *
     * 3    8
     *
     * 对于多余边5-8，9-8此处的删除需要有选择，跟之前的题目找到多余边立马不分选择删除有区别
     *
     * LC 98: Validate Binary Search Tree
     *
     * 上面BST删除多余的edge的思路，和BST insert以及delete node的思路很类似，可以放到一起总结复习
     *
     * Follow Up 2: 如果是n-ary树
     *
     */

    /**
     * Time: O(N) N is the number of nodes in the graph
     * Space: O(N)
     */
    public void T2_LC684_test() {
        int[][] edges = {
                {1, 2},
                {2, 3},
                {1, 3},
                {2, 4}
        };
        T2_LC684_684RedundantConnection_undirected_graph lc684 = new T2_LC684_684RedundantConnection_undirected_graph();
        int[] result = lc684.findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }

    class T2_LC684_684RedundantConnection_undirected_graph {
        public int[] findRedundantConnection(int[][] edges) {
            int[] result = new int[2];
            if (edges == null || edges.length == 0 || edges[0].length != 2) {
                return result;
            }
            int len = edges.length;
            UnionFind uf = new UnionFind(len + 1);
            for (int[] edge: edges) {
                int from = edge[0];
                int to = edge[1];
                if (!uf.union(from, to)) {
                    if (from > to) {
                        return new int[] {to , from};
                    } else {
                        return new int[] {from, to};
                    }
                }
            }
            return result;
        }
    }


    public void T2_LC685_test() {
        T2_LC685_RedundantConnection_DirectedGraph sln = new T2_LC685_RedundantConnection_DirectedGraph();
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3}
        };
        int[] result = sln.findRedundantDirectedConnection(edges);
        System.out.println(Arrays.toString(result));

    }
    class T2_LC685_RedundantConnection_DirectedGraph {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int n = edges.length;

            // when a node has two incoming edges, then one of those edges is redudant
            int[] indegrees = new int[n + 1];
            int found = -1;
            for (int[] edge: edges) {
                indegrees[edge[1]]++;
                if (indegrees[edge[1]] == 2) {
                    found = edge[1];
                    break;
                }
            }

            System.out.println("found = " + found);
            // if no node with multiple parent, then look for cycle
            if (found == -1) {
                return detectCycle(edges, new int[2]);
            }

            // when there are multiple parent and have cycle
            // try detectCycle with one edge skipped
            for (int i = n - 1; i >= 0; i--) {
                if (edges[i][1] == found) {
                    int[] redudant = detectCycle(edges, edges[i]);
                    // no dycle found if I skip the current edge, then we found the redudant is the edge
                    if (redudant == null){
                        return edges[i];
                    }
                }
            }
            return new int[0];
        }

        private int[] detectCycle(int[][] edges, int[] skip) {
            UnionFind uf = new UnionFind(edges.length + 1);
            for (int[] edge: edges) {
                if (edge[0] == skip[0] && edge[1] == skip[1]) {
                    continue;
                }
                if (!uf.union(edge[0], edge[1])) {
                    return edge;
                }
            }
            return null;
        }

    }

    class UnionFind {
        Node[] nodes;

        public UnionFind(int n) {
            this.nodes = create(n);
        }

        private Node[] create(int n) {
            Node[] newNodes = new Node[n];
            for (int i = 0; i < n; i++) {
                newNodes[i] = new Node(i, 0);
            }
            return newNodes;
        }

        public int find(int i) {
            System.out.println(nodes[i]);
            if (nodes[i].parent != i) {
                nodes[i].parent = find(nodes[i].parent);
            }
            return nodes[i].parent;
        }

        private boolean union(int x, int y) {
            int xroot = find(x);
            int yroot = find(y);

            if (xroot == yroot) {
                return false;
            }
            if (nodes[xroot].rank > nodes[yroot].rank) {
                nodes[yroot].parent = xroot;
            } else if (nodes[yroot].rank < nodes[xroot].rank) {
                nodes[xroot].parent = yroot;
            } else {
                // promote xroot
                nodes[xroot].rank++;
                nodes[yroot].parent = xroot;
            }
            return true;
        }

        class Node {
            int rank;
            int parent;
            public Node(int _p, int _r) {
                parent = _p;
                rank = _r;
            }
            public String toString() {
                return parent + " : " + rank;
            }
        }

    }


    public void T2_delete_from_BST() {
        TreeNode n7 = new TreeNode(7);
        TreeNode n5 = new TreeNode(5);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n8 = new TreeNode(8);

        n7.left = n5;
        n7.right = n9;

        n5.left = n3;
        n5.right = n8;
        n9.left = n8;

        Debug.preOrderTraversal(n7);

        T2_DeleteEdge_BST sln =new T2_DeleteEdge_BST();
        sln.deleteEdge_BST(n7);

        System.out.println();
        Debug.preOrderTraversal(n7);
    }

    public class T2_DeleteEdge_BST {
        public void deleteEdge_BST(TreeNode root) {
            if(root == null) return;
            deleteEdge_BST_helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        // do a pre order
        private TreeNode deleteEdge_BST_helper(TreeNode root, int left, int right) {
            if(root == null) {
                return null;
            }
            // root val is not in (left, right)
            if(root.val <= left || root.val >= right) {
                System.out.println("left: " + left + " right: " + right + " val: " + root.val);
                return null;
            }
            root.left = deleteEdge_BST_helper(root.left, left, root.val);
            root.right = deleteEdge_BST_helper(root.right, root.val, right);

            return root;
        }
    }

    public class T2_delete_edge_from_nary {
        class NTreeNode {
            int val;
            List<NTreeNode> children;
            NTreeNode(int v) {
                this.val = v;
                this.children = null;
            }
        }

        public void deleteEdge(NTreeNode root) {
            // traverse the graph

            // get the
        }


    }






    /**
     * TOP 3
     * 3. 机器人左上到右上(高频 9次)
     * Rules：
     * 1. 从左上角走到右上角
     * 2. 机器人只能走右上，右和右下

     * LC类似题目: LC 62 Unique Paths 和 LC 63 Unique Paths II

     * followup1: 优化空间复杂度至 O(n)

     * followup2: 给定矩形里的三个点，判断是否存在遍历这三个点的路径
     * 假设 点a[i, j]， 那么在前一列可以到达a[i, j] 的点则是： a[i -1, j - 1], a[i, j - 1], a[i + 1, j - 1].
     * 前一个点的可达范围则是：
     * len = j - (j - 1)
     * upper(i) = i - len
     * lower(i) = i + len
     *
     * 对于(xi, yi)，得到前一个点在该列的可达范围
     *
     * len = yi - y(i-1)
     *
     * upper = x(i-1) - len
     *
     * lower = x(i-1) + len
     */

    public static void T3_test() {
//        test3_1();
//        test_top3_f2();
//        t3_test_f3();
//        t3_test_f4();
//        t3_test_f5();
//        t2_f_maxPathSum();
        t2_max_money();
    }


    public static void test3_1() {
        int rows = 3;
        int cols = 3;
        int res = t3_1_uniquePath(rows, cols);
        System.out.println("res = " + res);
    }
    public static int t3_1_uniquePath(int rows, int cols) {
        int[] dp = new int[rows];
        int[] temp = new int[rows];
        dp[0] = 1;
        for (int j = 1; j < cols; j++)  {
            for (int i = 0; i < rows; i++) {
                int val1 = i - 1 >= 0 ? dp[i - 1] : 0;
                int val2 = dp[i];
                int val3 = i + 1 < rows ? dp[i + 1] : 0;
                temp[i] = val1 + val2 + val3;
            }
            System.arraycopy(temp, 0, dp, 0, temp.length);
        }
        System.out.println("!!!! dp = " + Arrays.toString(dp));
        return dp[0];
    }

    public static void test_top3_f2() {
        int[][] points = {
                {1, 2},
                {2, 8},
                {4, 6}
        };

        boolean res = top3_f2(points);
        System.out.println(">>>> res: " + res);
    }

    public static boolean top3_f2(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 0});

        for (int[] point : points) {
            list.add(point);
        }

        Collections.sort(list, (a, b) -> {
            return a[1] - b[1];
        }); // sorted by column

        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            int[] prev = list.get(i - 1);

            System.out.println("-------------");
            System.out.println(prev[0] + " -> " + prev[1]);
            System.out.println(cur[0] + " -> " + cur[1]);

            if (cur[1] == prev[1]) {
                // same column
                if (cur[0] != prev[0]) {
                    return false;
                } else {
                    continue;
                }
            }

            int colDistance = cur[1] - prev[1];
            int top = prev[0] - colDistance;
            int down = prev[0] + colDistance;
            //
            if (cur[0] >= top && cur[0] <= down) {
                continue;
            } else {
                return false;
            }

        }

        return true;

    }

    /**
     * followup3: 给定矩形里的三个点，找到遍历这三个点的所有路径数量
     *
     * 1：还是按照follow up 1的思路用滚动数组dp，但是如果当前列有需要到达的点时，
     * 只对该点进行dp，其他格子全部置零，表示我们只care这一列上经过目标点的路径
     *
     * 2：如果一列上有多个需要达到的点，直接返回0；
     */

    public static void t3_test_f3() {
        int rows = 5;
        int cols = 5;
        int[][] points = {
                {1, 1},
                {1, 3}
        };

        int res = top3_f3(rows, cols, points);
        System.out.println("res = " + res);
    }
    public static int top3_f3(int rowLen, int colLen, int[][] points) {
        int[] dp = new int[rowLen];
        int[] temp = new int[rowLen];

        Map<Integer, Integer> map = new HashMap<>();  // <col, row>

        for (int[] point : points) {
            if (map.containsKey(point[1])) {
                // have two points on the same column
                return 0;
            } else {
                map.put(point[1], point[0]);
            }
        }

        System.out.println(map);

        int res = 0;
        dp[0] = 1;
        for (int j = 1; j < colLen; j++) {
            for (int i = 0; i < rowLen; i++) {
                int val1 = i - 1 >= 0 ? dp[i - 1] : 0;
                int val2 = dp[i];
                int val3 = i + 1 < rowLen ? dp[i + 1] : 0;
                temp[i] = val1 + val2 + val3;
            }

            System.arraycopy(temp, 0, dp, 0, temp.length);

            // 但是如果当前列有需要到达的点时，
            // 只对该点进行dp，其他格子全部置零，表示我们只care这一列上经过目标点的路径
            if (map.containsKey(j)) {
                int row = map.get(j);
                for (int i = 0; i < rowLen; i++) {
                    if (i != row) {
                        dp[i] = 0;
                    } else {
                        res = dp[i];
                    }
                }
            }
        }

        return res;

    }

    /** followup4: 给定一个下界
     * (x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)
     *
     * 思路：
     *
     * 1：先dp一遍，得到所有到右上的路径数量
     *
     * 2：然后在 0<=x<=H, 0<=y<=cols 这个小矩形中再DP一遍得到不经过下界的所有路径数量
     *
     * 3：两个结果相减得到最终路径数量
     *
     * Code
     *
     * 重用follow up 1的代码
     *
     * public int uniquePaths(int rows, int cols, int H) {
     *
     *         return uniquePaths(rows, cols) - uniquePaths(H, cols);
     *
     * }
     */

    public static void t3_test_f4() {
        int rows = 10;
        int cols = 10;
        int H = 3;
        int rev = t3_f4_unique_path(rows, cols, H);
        System.out.println("rev = " + rev);
    }
    public static int t3_f4_unique_path(int rows, int cols, int H) {
        return t3_1_uniquePath(rows, cols) - t3_1_uniquePath(H, cols);
    }


    /**
     * followup5: 起点和终点改成从左上到左下，每一步只能 ↓↘↙，求所有可能的路径数量
     */

    public static void t3_test_f5() {
        int rows = 3;
        int cols = 3;
        int res = top3_f5_uniquePaths(rows, cols);
        System.out.println("res = " + res);
    }
    public static int top3_f5_uniquePaths(int rows, int cols) {
        int[] dp = new int[cols];
        int[] temp = new int[cols];
        dp[0] = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val1 = j - 1 >= 0 ? dp[j - 1] : 0;
                int val2 = dp[j];
                int val3 = j + 1 < cols ? dp[j + 1] : 0;
                // update temp[i]
                temp[j] = val1 + val2 + val3;
            }

            System.arraycopy(temp, 0, dp, 0, temp.length);
        }
        return dp[0];
    }

    /** 补充一个该题目变种：
     * Given a N*N matrix with random amount of money in each cell,
     * you start from top-left, and can only move from left to right,
     * or top to bottom one step at a time until you hit the bottom right cell.
     * Find the path with max amount of money on its way.
     * Sample data:
     * start
     * |
     * v
     * 5, 15,20,  ...
     * 10, 15,  5,   ...
     * 30,  5,  5,    ...
     * …
     *                 ^end here.
     *
     * 思路：LC 64 最小路径和，思路差不多，只是求和变成求相加后的最大值: LC 64. Minimum Path Sum -> Maximum
     *
     * 从上边来，
     * 从右边来,
     */

    public static void t2_f_maxPathSum() {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        int min = LC64_minPathSum(grid);
        System.out.println("min = " + min);

        int max = T2_maxPathSum(grid);
        System.out.println("max = " + max);
    }

    public static int LC64_minPathSum(int[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int[] minArr = new int[cL];

        for(int i=0; i<minArr.length; i++){
            minArr[i] = Integer.MAX_VALUE;
        }
        minArr[0] = 0;

        for(int i = 0 ; i < rL; i++) {
            minArr[0] += grid[i][0];
            for(int j = 1; j < cL; j++ ){
                minArr[j] = Math.min(minArr[j], minArr[j-1]) + grid[i][j];
            }
        }
        return minArr[cL - 1];
    }

    /**
     * Time: O(n * n)
     */
    public static int T2_maxPathSum(int[][] grid) {
        int rL = grid.length;
        int cL = grid[0].length;
        int[] maxArr = new int[cL];
        for(int i=0; i < maxArr.length; i++){
            maxArr[i] = Integer.MIN_VALUE;
        }
        maxArr[0] = 0;

        for(int i = 0 ; i <rL ; i++){
            maxArr[0] += grid[i][0];
            for(int j = 1; j < cL; j++ ){
                maxArr[j] = Math.max(maxArr[j], maxArr[j-1]) + grid[i][j];
            }
        }
        return maxArr[cL - 1];
    }
     /**
     * Follow up 1：要求重建从end 到 start的路径
     * 思路：用另一个额外数组记录每一步选择的parent，dp结束后，从end依次访问它的parent重建路径
     *
     *
     * 空间复杂度不算返回路径时需要的空间
     *
     * 思路：直接修改原数组，而且带上符号，负号表示从当前cell的左边过来，正号表示从当前cell的上边过来，
     * dp结束后从end 依次访问它的parent重建路径
     *
     *
     * 数组全是零(或者左上角一块是零)的话就没有办法通过正负号判断来的方向了吧，这样在重构path的时候可能会index out of bound，
      * 觉得还是check左边和右边哪个更大就是从那边来的更好，然后注意第一行和第一列的特殊情况，这样不会出问题
     *
     * （这个方法是面试官给的hint提示的，原数组应该都是正整数。如果全是0，用正负号表示也可以特殊处理一下第一行和第一列的情况即可，
      * 即遇到i为0时候总是往左走，j为0的时候总是往上走。
     */


     public static void t2_max_money() {
         int[][] moneys = {
                 {1,3,1},
                 {1,5,1},
                 {4,2,1}
         };
         List<List<Integer>> result = maxMoney(moneys);
         System.out.println(result);
     }

    /**
     * Time: O(n * n)
     * Space: O(1)
     *
     * 负号(-)表示从当前cell的左边过来，
     * 正号(+)表示从当前cell的上边过来，dp结束后从end 依次访问它的parent重建路径
     */
    public static List<List<Integer>> maxMoney(int[][] moneys) {
         // assume: moneys is not null, width and length are equal
         int n = moneys.length;
         if (n == 0) {
             return new ArrayList<>();
         }

         // the first row
         // from left side, the first row
         for (int j = 1; j < n; j++) {
             moneys[0][j] = -(Math.abs(moneys[0][j-1]) + moneys[0][j]);
         }
         Debug.printMatrix(moneys);
         System.out.println("----------");
         // the first column
         // from top side, the first column
         for (int i = 1 ; i < n ; i++) {
             moneys[i][0] = moneys[i-1][0] + moneys[i][0];
         }
         Debug.printMatrix(moneys);

         // from the 2nd row and 2nd column
         for(int i = 1; i < n ; i++) {
             for(int j = 1; j < n ; j++) {
                 int top = Math.abs(moneys[i-1][j]) + moneys[i][j];
                 int left = Math.abs(moneys[i][j-1]) + moneys[i][j];
                 if(top >= left) {
                     moneys[i][j] = top;
                 }
                 else {
                     moneys[i][j] = -left;
                 }
             }
         }

         System.out.println("Max path sum = " + Math.abs(moneys[n - 1][n - 1]));
         Debug.printMatrix(moneys);
         List<List<Integer>> path = new ArrayList<>();
         int curri = n-1;
         int currj = n-1;
         while (curri > 0 || currj > 0) {
             path.add(Arrays.asList(curri, currj));
             if(moneys[curri][currj] < 0) { // from left, go left
                 currj -= 1;
             } else { // from top, go top
                 curri -=1;
             }
         }
         path.add(Arrays.asList(0, 0));
         Collections.reverse(path);
         return path;
     }



    /**
     * 12 次
     * TOP 4
     * guess word
     * LC 题目：LC843 猜词，一个未知target，猜一个词会返回正确猜对的字母数
     * <p>
     * <p>
     * Onsite: 限制条件比LC 843多一些，
     * <p>
     * 长度是５，都是uppercase, 没有重复字母。先实现guess function, 自己设计数据结构，返回有几个字母match
     * （不需要位置也一样，存在于secret word里就算）和是否猜中。
     * 这个就是基础的字符串比较，主要是注意对word进行validation。然后就是问怎么猜了。就大概说了LC 843里的方法，preprocessing dictionary,
     * 然后根据猜的结果缩小范围。代码没时间写完。
     */

    public static class Master {
        public int guess(String word) {
            return -1;
        }
    }


    class T4_GuessWord {
        public void findSecretWord(String[] wordlist, Master master) {

            List<String> list = new ArrayList<>();

            for(String str: wordlist) list.add(str);

            for(int i = 0 ; i < 10 ; i++) {

                Map<String, Integer> zeroMatch = new HashMap<>();

                for(String s1: list) {

                    zeroMatch.putIfAbsent(s1, 0);

                    for(String s2: list) {

                        if(match(s1, s2) == 0) {

                            zeroMatch.put(s1, zeroMatch.get(s1) + 1);

                        }

                    }

                }

                Pair pair = new Pair("", 101);  // list size is 100

                for(String key : zeroMatch.keySet()) {

                    if(zeroMatch.get(key) < pair.freq) {

                        pair = new Pair(key, zeroMatch.get(key));

                    }

                }

                int matchNum = master.guess(pair.key);

                if(matchNum == 6) return;

                List<String> tmp = new ArrayList<>();

                for(String s: list) {

                    if(match(s, pair.key) == matchNum) {

                        tmp.add(s);

                    }

                }

                list = tmp;

            }

        }

        private  class Pair {

            String key;

            int freq;

            public Pair(String key, int freq) {

                this.key = key;

                this.freq = freq;

            }

        }

        private int match(String s1, String s2) {

            int res = 0;

            for(int i = 0 ; i < s1.length() ; i++) {

                if(s1.charAt(i) == s2.charAt(i)) res++;

            }

            return res;

        }

    }

    class T4_GuessWord2 {
        public void findSecretWord(String[] wordlist, Master master) {
            Random r = new Random();
            int index = r.nextInt(wordlist.length);
            String word = wordlist[index];
            List<String> curList = new ArrayList<>();
            for(String w: wordlist) {
                curList.add(w);
            }

            while(true) {
                // get the match number
                int matchCount = master.guess(word);
                if(matchCount == 6) {
                    return;
                }

                List<String> nextList = new ArrayList<>();
                for(String w: curList) {
                    if(matchCount == match(w, word)) {
                        nextList.add(w);
                    }
                }
                // generate a new index
                index = r.nextInt(nextList.size());
                // generate a new word and update "word"
                word = nextList.get(index);
                // switch curList with nextList
                curList = nextList;
            }

        }

        int match(String w1, String w2) {
            int count = 0;
            for(int i = 0; i < w1.length(); ++i) {
                if(w1.charAt(i) == w2.charAt(i)) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 5. LC890 word pattern match 高频8次
     * LC890 word pattern match 高频8次
     * 给一个word list和一个pattern，返回list中所有和pattern相match的单词
     * <p>
     * 此处的match为能在pattern和word之间找到一个双射函数，和LC Isomorphic String中的双射函数一样
     * <p>
     * 思路：
     * <p>
     * 1. 用两个map，用putIfAbsent存互相的对应关系，然后再查一遍对应
     * 2. 单map把string转换成pattern array，用map.put(char, map.size())存不存在的char
     * <p>
     * time: O(n * k). n is the number of words. K is the lentgh of each word
     */

    public static void top5_test() {
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pattern = "abb";
        List<String> result = top5_findAndReplacePattern(words, pattern);
        System.out.println("result: " + result);
    }

    public static List<String> top5_findAndReplacePattern(String[] words, String pattern) {

        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (top5_match(word, pattern)) {
                result.add(word);
            }
        }

        return result;
    }

    // two maps

    /**
     *
     * @param s
     * @param p
     * @return
     * check whether s matches p
     * using two maps.
     * for each char in s, map char in p
     */
    public static boolean top5_match(String s, String p) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = p.charAt(i);
            if (!map1.containsKey(c1)) {
                map1.put(c1, c2);
            }

            if (!map2.containsKey(c2)) {
                map2.put(c2, c1);
            }

            if (map1.get(c1) != c2 || map2.get(c2) != c1) {
                return false;
            }
        }
        return true;
    }

    public static boolean match_one_map(String word, String pattern) {
        Map<Character, Character> M = new HashMap();
        for (int i = 0; i < word.length(); ++i) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!M.containsKey(w)) {
                M.put(w, p);
            }
            if (M.get(w) != p) {
                return false;
            }
        }

        boolean[] seen = new boolean[26];
        for (char p : M.values()) {
            if (seen[p - 'a']) return false;
            seen[p - 'a'] = true;
        }
        return true;
    }



}
