package mj_google;

import ds.Debug;
import ds.TreeNode;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class G1_2 {
    public static void main(String[] args) {
        G1_2 g1_2 = new G1_2();
//        g1_2.T11_test();
//        g1_2.T12_test();
//        g1_2.T13_test();
//        g1_2.T14_test();
//        g1_2.T15_test();
//        g1_2.T17_test();
//        g1_2.T18_test();
//        g1_2.T18_test2();
        g1_2.T19_test();
//        test18_2();
    }

    /**
     *
     * 11. LC857 雇工人 高频 6次
     * 857. Minimum Cost to Hire K Workers
     *
     * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
     *
     * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers,
     * we must pay them according to the following rules:
     *
     * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
     * Every worker in the paid group must be paid at least their minimum wage expectation.
     * Return the least amount of money needed to form a paid group satisfying the above conditions.
     *
     * Example 1:
     *
     * Input: quality = [10,20,5], wage = [70,50,30], K = 2
     * Output: 105.00000
     * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
     * Example 2:
     *
     * Input: quality = [3,1,10,10,1],
     *           wage = [4,8,2,2,7],
     *           K = 3
     * Output: 30.66667
     * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
     * 思路:
     *
     * 根据描述任何一个合法的pay group里面任意两个worker之间 quality1/quality2 = wage1/wage2，
     * 转化一下 wage1/quality1 = wage2 / quality2，
     * 即所有的工人他们的paid wage和他们的quality的比值都应该是一样的，
     * 根据这个性质，我们 一个合法的pay group只能取最大的wage/quality当作所有工人的pay ratio。
     *
     *
     * 假如我们不取最大的，选择的ratio为w1/q1，而且存在一个工人的wage i/quality i > w1/q1，
     * 转换一下很容易得到 wage i > quaility i * w1/q1，即表示该工人的最低工资大于了他实际得到的工资，和题意不符合。
     *
     *
     * 所以有了以上性质后，直接根据ratio排序，然后又需要每个group的pay最小，
     * 每个group的总工资计算方法（q1+q2+q3+...+qk）* ratio，所以就是需要quality的sum最小，
     * 那每次我们加入了一个新的ratio就把quality最大worker踢出group，这样每次group的pay可以保证是新ratio下的最小pay。
     * 遍历数组，记录所有ratio中出现的pay最小值即可。
     *
     *
     * 先来看第一个，说是每个员工的薪水要和其能力值成恒定比例，意思是说假如两个员工A和B，
     * 若A的能力值是B的2倍，那么A的薪水就要是B的两倍，要雇佣的K个员工所有人的薪水和能力都是要成比例的，
     * 而这个比例一定是个恒定值，只要能够算出这个最低的薪水能力比例值，乘以K个员工的总能力值，
     * 就可以得到最少的总花费。第二个需要满足的条件是每个员工的薪水不能低于其期望值，则每个员工都有一个自己固定的薪水能力比例值，
     * 而需要求的那个最低的薪水能力比例值不能小于任何一个员工自己的比例值。当员工能力值越低，期望薪水越高的时候，其薪水能力比例值就越大，
     * 所以可以根据薪水能力比例值从大到小来排列员工。可以将员工的薪水能力比例值和其能力值组成 pair 对儿放到一个数组中，
     * 然后对这个数组进行排序，则默认就是对薪水能力比例值进行从小到大的排列。接下来的操作就跟之前那道 Top K Frequent Words 非常像了，
     * 这里用一个最大堆，还要用一个变量 qsum 来累加员工的能力值，先将薪水能力比例最低的员工的能力值加到 qsum 中，
     * 同时加入到最大堆中，若堆中员工总数大于K了，则将堆顶能力值最大的员工移除，因为能力值越大意味着需要付的薪水越多。
     * 若堆中员工总数等于K了，则用当前员工的薪水能力比例乘以总的能力值数得到一个总花费，用来更新结果 res。
     * 为啥这样是正确的呢？因为当前员工的薪水能力比例值是大于堆中其他所有员工的，那么乘以恒定的总能力值，
     * 得出的总薪水数一定大于等于使用其他员工的薪水能力比例值，则每个员工可得到的薪水一定是大于等于其期望值的，
     * 这样就同时满足了两个条件，所以是符合题意的，最终更新完得到的总花费一定是最低的
     *
     */

    public void T11_test() {
        int[] quality = {3,1,10,10,1};
        int[] wage = {4,8,2,2,7};
        int K = 3;
        T11_Solution sln = new T11_Solution();
        double result = sln.mincostToHireWorkers(quality, wage, K);
        System.out.println("result = " + result);

    }
    class T11_Solution {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            int len = quality.length;
            Worker[] workers = new Worker[len];
            for(int i = 0 ; i < len ; i++) {
                workers[i] = new Worker(quality[i], wage[i]);
            }

            // sort by ratio in ascending order
            Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));

            System.out.println(Arrays.toString(workers));
            // maxHeap
            Queue<Worker> pq = new PriorityQueue<>((a, b) -> b.quality - a.quality);

            int sum = 0;
            double min = Double.MAX_VALUE;
            for(int i = 0 ; i < len; i++) {
                if(pq.size() >= K) {
                    Worker tmp = pq.poll();
                    sum -= tmp.quality;
                }
                pq.offer(workers[i]);
                sum += workers[i].quality;
                if(pq.size() == K) {
                    min = Math.min(min, sum * workers[i].ratio);
                }
            }
            return min;
        }

        private class Worker {
            int quality;
            int wage;
            double ratio;
            public Worker(int quality, int wage) {
                this.quality = quality;
                this.wage = wage;
                ratio = (wage + 0.0) / quality;
            }
            public String toString() {
                return "[" + quality + " : " + wage + " : " + ratio+"]";
            }
        }
    }

    // this works better, which has better performance.
    class T11_Solution2 {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            Worker[] w = new Worker[quality.length];
            for (int i = 0; i < quality.length; i++) {
                w[i] = new Worker((double)wage[i] / quality[i], quality[i]);
            }

            Arrays.sort(w, (a, b) -> Double.compare(a.ratio, b.ratio));
            // maxHeap
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
            int minSum = 0;
            double min = Integer.MAX_VALUE;
            for (int i = 0; i < w.length; i++) {
                if (maxHeap.size() < K - 1) {
                    minSum += w[i].quality;
                    maxHeap.offer(w[i].quality);
                } else {
                    min = Math.min(min, w[i].ratio * (minSum + w[i].quality));
                    if (!maxHeap.isEmpty() && maxHeap.peek() > w[i].quality) {
                        minSum = minSum - maxHeap.poll() + w[i].quality;
                        maxHeap.offer(w[i].quality);
                    }
                }
            }
            return  min;
        }

        public class Worker {
            int quality;
            double ratio;
            public Worker(double ratio, int quality) {
                this.quality = quality;
                this.ratio = ratio;
            }
        }
    }

    /**
     * 12. LC750 Corner Rectangle个数 高频 5次
     * 思路：任选两行for for loop，若相对应列（第三个for）都有点，就count++，然后对这两行的count任取两个组合，加到result上
     */

    public void T12_test() {
        int[][] grid = {
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 1}
        };
        int result = new T12_LC750_Corner_Rectangle().countCornerRectangles(grid);
        System.out.println("result = " + result);

        T12_LC750_Corner_Rectangle sln = new T12_LC750_Corner_Rectangle();
        int res1 = sln.countCornerRectangles_1(grid);
        System.out.println("res1 = " + res1);

        int res2 = sln.countCornerRectangles(grid);
        System.out.println("res2 = " + res2);
    }



    class T12_LC750_Corner_Rectangle {

        public int countCornerRectangles_bf(int[][] grid) {
            int rL = grid.length;
            int cL = grid[0].length;
            int res = 0;
            for (int i = 0; i < rL; i++) {
                for (int j = 0; j < cL; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }

                    for (int h = 1; h < rL - 1; h++) {
                        if (grid[i + h][j] == 0) {
                            for (int w = 1; w < cL - j; w++) {
                                if (grid[i][j + w] == 1 && grid[i + h][j + w] == 1) {
                                    res++;
                                }
                            }
                        }
                    }
                }
            }
            return res;
        }

        /**
         * 我们来看一种优化了时间复杂度的方法，这种方法的原理是两行同时遍历，如果两行中相同列位置的值都为1，
         * 则计数器cnt自增1，那么最后就相当于有了(cnt - 1)个相邻的格子，
         * 问题就转化为了求cnt-1个相邻的格子能组成多少个矩形，就变成了初中数学问题了，共有cnt*(cnt-1)/2个，参见代码如下：
         */
        public int countCornerRectangles_1(int[][] grid) {
            int rL = grid.length;
            int cL = grid[0].length;
            int res = 0;

            for (int i = 0; i < rL; ++i) {
                for (int j = i + 1; j < rL; ++j) {
                    int cnt = 0;

                    for (int k = 0; k < cL; ++k) {
                        if (grid[i][k] == 1 && grid[j][k] == 1) {
                            cnt ++;
                        }
                    }

                    res += cnt * (cnt - 1) / 2;
                }
            }
            return res;
        }


        public int countCornerRectangles(int[][] grid) {
            //init array of column pairs, as per problem statement cols less than or equals to 200
            int cL = grid[0].length;
            int[][] colPairs = new int[cL][cL];
            int cols = grid[0].length;
            int res = 0;
            //for every row
            for (int[] row : grid) {
                //start loop on columns need to check every pair of columns in the row
                for (int c1 = 0; c1 < cols - 1; c1++) {
                    if (row[c1] == 1) {
                        for (int c2 = c1+1; c2 < cols; c2++) {
                            //if both row[c1] and row[c2] are 1 this is potentially a rectangle
                            if (row[c2] == 1) {
                                //get the previous value before incrementing number of rectangles will be minus 1 number of
                                //rows cause we need two rows to form first rectangle
                                res += colPairs[c1][c2];
                                colPairs[c1][c2]++;
                            }
                        }
                    }
                }
            }

            System.out.println("======");
            Debug.printMatrix(colPairs);
            return res;
        }
    }



    /**
     * 13. LC815 Bus Route 高频 5次
     * 每个公交车有多个站，给一堆公交车，问A到B点最少换乘次数
     *
     * 思路：BFS + mem。map<stop, List<bus>>，然后用Set<bus>存visited bus（不能用stop查重，会很慢）。
     * 每次层序遍历poll出的站都是在这个step中所有能走到的站
     */

    public void T13_test() {
        T13_LC815_Solution sln = new T13_LC815_Solution();
        int[][] routes = {
                {1, 2, 7},
                {3, 6, 7}
        };
        int S = 1, T = 6;
        int result = sln.numBusesToDestination(routes, S, T);
        System.out.println("result = " + result);
    }

    /**
     * https://leetcode.com/problems/bus-routes/solution/
     *
     */
    class T13_LC815_Solution {
        public int numBusesToDestination(int[][] routes, int S, int T) {
            Set<Integer> visited = new HashSet<>(); // whether this bus has been taken
            Queue<Integer> q = new LinkedList<>(); // station queue
            HashMap<Integer, List<Integer>> map = new HashMap<>(); // <station, list<bus>>
            // station, buses
            int numOfBuses = 0;
            if (S == T)
                return 0;

            // get the <station, list<bus>> map
            for (int i = 0; i < routes.length; i++) {
                for (int j = 0; j < routes[i].length; j++) {
                    List<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
                    buses.add(i);
                    map.put(routes[i][j], buses);
                }
            }

            if (!map.containsKey(T)) {
                // there is no bus to reach this station
                return -1;
            }

            q.offer(S);
            while (!q.isEmpty()) {
                int len = q.size();
                numOfBuses++;
                for (int i = 0; i < len; i++) {
                    int cur = q.poll();
                    List<Integer> buses = map.get(cur);
                    for (int bus : buses) {
                        if (visited.contains(bus)) {
                            continue;
                        }
                        visited.add(bus);
                        // get the stations that this bus can readh
                        for (int j = 0; j < routes[bus].length; j++) {
                            if (routes[bus][j] == T) {
                                return numOfBuses;
                            }
                            q.offer(routes[bus][j]);
                        }
                    }
                }
            }
            return -1;
        }
    }



    /**
     * 14. LC659 Split Array into Consecutive Subsequences 高频 6次
     * 判断一个升序含重复元素的array是否能分成多个三个数字以上构成的顺子
     *
     * 思路：
     *
     * 用freq map先过一遍存频率，再建一个map存我们能用到的tail number。再过第二遍的时候，若freq==0 continue；
     * 若能接上前面的顺子，就接；不能则新开一个顺子（记住新开时候直接要把连着的两个数字剔除，因为要保证长度为三）；
     * 都不行则为false。记住最后别忘了更新当前频率
     *
     *
     * 对于每一个element，我们有两种选择
     *
     * 把它加入之前构造好的顺子中
     * 用它新开一个顺子
     * 此处用贪心策略，如果1能满足总是先满足1，因为新开顺子可能失败，即使新开顺子成功，
     * 当1能满足的时候，将新开顺子加入之前的顺子也能成功，所以能够选择策略1的时候没必要冒风险选择策略2
     *
     *
     * 目标是用策略1或者2消耗掉所有的元素
     *
     *
     * 如果两个策略都无法选择，直接返回false
     *
     *
     * 用另一个map记录已经构造好的顺子中现在需要哪些尾巴，来实现将当前元素加入构造好的顺子中
     */

    public void T14_test() {
        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
        boolean result = new T14_LC659_Solution().isPossible(nums);
        System.out.println(result);
    }

    class T14_LC659_Solution {
        public boolean isPossible(int[] nums) {

            Map<Integer,Integer> freq=new HashMap<>();  //to keep track of the frequency
            Map<Integer,Integer> hypo=new HashMap<>(); //to create a hypothetical map to keep track of the next int

            for(int i=0;i<nums.length;i++)
            {
                freq.put(nums[i],freq.getOrDefault(nums[i],0)+1);
            }

            for(int i=0;i<nums.length;i++)
            {

                int a=nums[i];

                if(freq.get(a)==0)
                    continue;
                if(hypo.getOrDefault(a,0)>0)
                {
                    hypo.put(a,hypo.get(a)-1);
                    freq.put(a,freq.get(a)-1);
                    hypo.put(a+1,hypo.getOrDefault(a+1,0)+1);
                }
                else if
                (freq.getOrDefault(a,0)>0&&freq.getOrDefault(a+1,0)>0&&freq.getOrDefault(a+2,0)>0)
                {
                    freq.put(a,freq.get(a)-1);
                    freq.put(a+1,freq.get(a+1)-1);
                    freq.put(a+2,freq.get(a+2)-1);
                    hypo.put(a+3,hypo.getOrDefault(a+3,0)+1);
                }
                else
                    return false;
            }

            return true;
        }
    }

    /**
     * 15. 王位继承 高频 10+次
     * void birth(String parent, String name) 父亲名字和孩子名字，生个娃
     *
     * void death(String name) 此人要死
     *
     * List<String> getOrder() 返回当前的继承顺序，string array/list
     *
     * 讨论得知，每个人的名字是唯一的，继承顺序符合如下规律:
     *
     * 假设王有大皇子二皇子三皇子，大皇子有长子次子三子，那么继承顺序是王->大皇子->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子
     *
     * 死掉的人不能出现在继承顺序里，
     * 但是如果上面例子中大皇子死了，只需把大皇子移除，原始继承顺序保持不变：王->大皇子长子->大皇子次子->大皇子三子->二皇子->三皇子
     *
     * 三个function会被反复调用，实现function细节。
     *
     * 思路：看起来不难的设计题，DFS只查最左枝
     */


    public void T15_test() {
        T15_Solution sln = new T15_Solution();
        sln.birth("king", "1");
        sln.birth("king", "2");
        sln.birth("king", "3");
        sln.birth("king", "4");
        sln.birth("1", "1_1");
        sln.birth("1", "1_2");
        sln.birth("1", "1_3");

        sln.birth("1_1", "1_1_1");
        sln.birth("1_1", "1_1_2");

        sln.birth("2", "2_1");
        sln.birth("2", "2_2");

        sln.birth("4", "4_1");

        List<String> order = sln.getOrder();
        System.out.println("order = " + order);

        sln.death("1");

        System.out.println("order2 = " + sln.getOrder());


    }
    public class T15_Solution {

        Map<String, List<String>> tree = new HashMap<>(); // <parent, children>
        Set<String> dead = new HashSet<>();
        String root = "king";

        public T15_Solution(){
            tree.put("king", new ArrayList<>());
        }

        public void birth(String parent, String name) {

            if(!tree.containsKey(parent)) {
                // throw exception
            } else {
                tree.get(parent).add(name);
                tree.put(name, new ArrayList<>());
            }
        }

        public void death(String name) {
            dead.add(name);
        }


        public List<String> getOrder(){
            List<String> res = new ArrayList<>();
            dfs(root, res);
            return res;
        }

        // pre-order traversal on n-nary tree
        private void dfs(String curr, List<String> result) {
            if(!dead.contains(curr)) {
                result.add(curr);
            }

            for(String child : tree.get(curr)) {
                dfs(child, result);
            }
        }
    }


    /**
     * 16. LC951 Tree Isomorphism Problem 树的同构问题 高频 5次
     * https://www.geeksforgeeks.org/tree-isomorphism-problem/
     *
     * 951. Flip Equivalent Binary Trees
     */

    /**
     * Time: O(min(N1, N2)) N1, N2 are the lengths of root1 and root2
     * Space:O(min (H1, H2)), H1 and H2 are the height of trees of root1 and root2
     */
    public boolean T16_LC951_flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null) return root2 == null;
        if(root2 == null) return root1 == null;
        if(root1.val != root2.val) return false;
        return (T16_LC951_flipEquiv(root1.left, root2.left) && T16_LC951_flipEquiv(root1.right, root2.right)) ||
                (T16_LC951_flipEquiv(root1.left,root2.right) && T16_LC951_flipEquiv(root1.right, root2.left));
    }


    /**
     * Time: O(N1 + N2)
     * Space: O(N1 + N2)
     */
    public boolean T16_LC951_flipEquiv2(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
    }



    /**
     * 17. N叉树，要求删一些node，返回list of roots 高频 8次 (LeetCode 1110)
     * More Detail : 给一个tree有红的node有蓝的node，把红的去掉后剩下一堆零零散散的tree，
     * 返回这些tree的node，只要node，不要children，也就是说把这个node的children设置成null然后加到list里。
     *
     * 参数是这个树的root。找到所有的红点然后delete掉，去掉这些红点之后就会把一个tree变成散落的几个tree，
     * 然后返回这几个tree的root。直接一个recursive判断一下，如果这个node是红点的话就ignore 掉再去判断这个node的children，
     * 如果这个node是蓝点的话，要看这个蓝点的parent是不是个红点，是的话，这个蓝点就是散落的tree中其中一个tree的root。
     *
     * 思路：DFS
     *
     * 想问一下这题返回的顺序重要吗？
     */
    public void T17_test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        Debug.preOrderTraversal(n1);
        System.out.println();

        T17_LC1110_Solution sln = new T17_LC1110_Solution();
        List<TreeNode> result = sln.delNodes(n1, new int[]{3, 5});
        for (TreeNode n: result) {
            System.out.println("n = " + n.val);
            Debug.preOrderTraversal(n);
            System.out.println();
        }


    }

    /**
     * Time: O(n)
     *
     */
    public class T17_LC1110_Solution {

        List<TreeNode> res = new ArrayList<>();

        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            Set<Integer> deleteSet = new HashSet<>();
            for (int i : to_delete) {
                deleteSet.add(i);
            }
            if (!deleteSet.contains(root.val)) {
                res.add(root);
            }
            dfs(root, deleteSet);
            return res;
        }


        public void dfs(TreeNode root, Set<Integer> delete) {
            if (root == null) {
                return;
            }

            if (delete.contains(root.val)) {
                if (root.left != null && !delete.contains(root.left.val)) {
                    res.add(root.left);
                }

                if (root.right != null && !delete.contains(root.right.val)) {
                    res.add(root.right);
                }
            }

            dfs(root.left, delete);
            dfs(root.right, delete);


            if (root.left != null && delete.contains(root.left.val)) {
                root.left = null;
            }

            if (root.right != null && delete.contains(root.right.val)) {
                root.right = null;
            }
        }
    }

    /**
     * Time: O(N) N is the number of nodes of the tree
     */
    public class T17_LC1110_Solution2 {
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (root == null) return new ArrayList<>();

            Set<TreeNode> resSet = new HashSet<>();
            resSet.add(root);
            if (to_delete.length == 0) return new ArrayList<>(resSet);

            Set<Integer> toDelete = new HashSet<>();
            for (int val : to_delete) toDelete.add(val);

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (toDelete.contains(node.val)) {
                    resSet.remove(node);
                    if (node.left != null) {
                        resSet.add(node.left);
                    }
                    if (node.right != null) {
                        resSet.add(node.right);
                    }
                }

                if (node.left != null) {
                    q.offer(node.left);
                    if (toDelete.contains(node.left.val)) {
                        node.left = null;
                    }
                }
                if (node.right != null) {
                    q.offer(node.right);
                    if (toDelete.contains(node.right.val)) {
                        node.right = null;
                    }
                }
            }
            return new ArrayList<>(resSet);
        }
    }




    /**
     * 18. 可乐饮料机 高频 5次
     * 有一系列按钮，每个按钮按下去会得到一定体积范围的可乐。先给定一个目标体积范围，问不限制按按钮次数，能否确定一定能得到目标范围内的可乐？
     *
     * 举例：有三个按钮，按下去得到的范围是[100, 120], [200, 240] , [400, 410],
     *
     * 假设目标是[100, 110], 那答案是不能。因为按下一，可能得到120体积的可乐，不在目标范围里。
     *
     * 假设目标是[90, 120]，那答案是可以。因为按下一，一定可以得到此范围内的可乐。
     *
     * 假设目标是[300, 360], 那答案是可以，因为按下一再按二，一定可以得到此范围内
     *
     * 假设目标是[310, 360], 那答案是不能，因为按下一再按二，有可能得到300，永远没可能确定得到这个范围内的可乐。
     *
     * 假设目标是[1, 9999999999]，那答案是可以。随便按一个都确定满足此范围。
     *
     * 思路：dfs+memorization从0开始暴力解  一开始[0, 0] 通过bfs、dfs往上加直到出界
     *
     */

    public void T18_test2() {
        int[][] ranges = {
                {100, 120},
                {200, 240},
                {400, 410}
        };
        CokeMachine sln = new CokeMachine(ranges);
        int targetLow = 90;
        int targetHigh = 120;

        boolean result = sln.buttonMakeRange(targetLow, targetHigh);
        System.out.println(">>>>>>> result = " + result);

    }

    public class CokeMachine {
        List<Soda> list;

        public CokeMachine(int[][] ranges) {
            list = new ArrayList<>();
            for (int[] range : ranges) {
                Soda soda = new Soda(range[0], range[1]);
                list.add(soda);
            }
        }
        public boolean buttonMakeRange(int targetLow, int targetHigh) {
            Map<String, Boolean> memo = new HashMap<>();
            return dfs(this.list, targetLow, targetHigh, targetLow, targetHigh, memo);
        }

        public class Soda {
            int lower;
            int upper;

            public Soda(int _l, int _u) {
                this.lower = _l;
                this.upper = _u;
            }
        }
        public boolean dfs(List<Soda> sodas,
                                  int volumeLower, int volumeUpper,
                                  int targetLower, int targetUpper,
                           Map<String, Boolean> memo) {

            Boolean val = memo.get(volumeLower + "-" + volumeUpper);
            if (val != null) {
                return val;
            }
            if (volumeLower >= targetLower && volumeUpper <= targetUpper) {
                return true;
            }

            if (volumeUpper > targetUpper) {
                return false;
            }
            // if (volumeUpper - volumeLower > targetUpper - targetLower) return false;

            for (Soda soda : sodas) {
                if (dfs(sodas, volumeLower + soda.lower, volumeUpper + soda.upper, targetLower, targetUpper, memo)) {
                    //false的子问题会重复计算
                    memo.put(volumeLower + "-" + volumeUpper, true);
                    return true;
                }
            }

            memo.put(volumeLower + "-" + volumeUpper, false);
            return false;
        }
    }

    public static void test18_2() {
        List<List<Integer>> bottons = new ArrayList<>();
        Integer[] b1 = {100, 120};
        Integer[] b2 = {200, 240};
        Integer[] b3 = {400, 410};

        List<Integer> l1 = Arrays.asList(b1);
        List<Integer> l2 = Arrays.asList(b2);
        List<Integer> l3 = Arrays.asList(b3);

        bottons.add(l1);
        bottons.add(l2);
        bottons.add(l3);

        List<Integer> target = new ArrayList<>();
        target.add(90);
        target.add(120);



        boolean res = coke(bottons, target);
        System.out.println(res);

    }

    public static boolean coke(List<List<Integer>> buttons, List<Integer> target) {
        int targetLeft = target.get(0);
        int targetRight = target.get(1);
        boolean[][] dp = new boolean[targetLeft + 1][targetRight + 1];

        System.out.println("m = " + targetLeft + " n = " + targetRight);

        //Init
        for (int i = 0; i <= targetLeft; ++i) {
            for (int j = 0; j <= targetRight; ++j) {
                for (List<Integer> button : buttons) {
                    if (button.get(0) >=i && button.get(1) <= j) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        Debug.printMatrix(dp);

        for (int i = 0; i <= targetLeft; ++i) {
            for (int j = 0; j <= targetRight; ++j) {
                for (List<Integer> button : buttons) {
                    int preLeft = i - button.get(0);
                    int preRight = j - button.get(1);
                    if (preLeft >= 0 && preRight >= 0 && dp[preLeft][preRight]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }

        return dp[targetLeft][targetRight];
    }

    public void T18_test() {
        CocaChicken sln = new CocaChicken();
        sln.test();
    }
    public class CocaChicken {
        // 测试
        public void test(){

            int[][] ranges = new int[][] {{100, 120}, {200, 240}, {400, 410}};
            System.out.println(ChichenButtonMakeRange(ranges, 100, 110));
            System.out.println(ChichenButtonMakeRange(ranges, 90, 120));

            System.out.println(ChichenButtonMakeRange(ranges, 300, 360));
            System.out.println(ChichenButtonMakeRange(ranges, 310, 360));

        }

        public boolean ChichenButtonMakeRange(int[][] ranges, int targetLow, int targetHigh) {
            // dp[i] ： 通过按键组合，得到一个range以i为左边界，且dp[i]为可能值最小的右边界
            int rangeLen = ranges.length;
            int[] dp = new int[targetLow];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            //排序range，左边界大的排在前面，不是必须，但是后续可帮助减少遍历次数
            // sort in descending order
            Arrays.sort(ranges, new Comparator<int[]>(){
                public int compare(int[] range1, int[] range2) {
                    return Integer.compare(range2[0], range1[0]);
                }
            });
            System.out.println("targetLow = " + targetLow + " targetHigh = " + targetHigh);

            System.out.println("------------");
            for (int[] range: ranges) {
                System.out.println(Arrays.toString(range));
            }

            for(int i = 0; i < targetLow; i++) {
                System.out.println("i = " + i);
                // 得到当前dp[i]的值
                for(int j = rangeLen - 1; j >= 0; j--) {
                    int[] range = ranges[j];
                    System.out.println("range[j]" + Arrays.toString(range));
                    if(i - range[0] < 0) break; // 因为ranges内， range[0]经过上述排序递减
                    if(dp[i - range[0]] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i - range[0]] + range[1]);
                    }
                }

                // 如果dp[i]不为Integer.MAX_VALUE, 说明通过按键组合，能得到一个range以i为左边界，dp[i]为可能值最小的右边界

                // 如果 range[0] + i >= targetLow, 说明再次按键得到的范围，左边界在[targetLow, targetHigh]内，此时判断右边界是否也在[targetLow, targetHigh]内

                if(dp[i] != Integer.MAX_VALUE && i + ranges[0][0] >= targetLow) {
                    for(int[] range : ranges) {
                        // 因为ranges内， range[0]经过上述排序递减
                        if(range[0] + i < targetLow) break;
                        if(range[1] + dp[i] <= targetHigh) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }


    /**
     * 19. 生成随机迷宫，高频5次
     * 左上到右下，怎么设计可玩性
     *
     * maze generation. 输入是int[][] board, int[] start, int[] dest,返回一个int[][] maze. 这题题意比较复杂。简单来说就是让你随机生成一个迷宫，
     *
     * 条件是：
     *
     *（1）你肯定要生成一些墙，这些墙宽度为1，意思就是board[0][0] - board[0][3]可以是墙，s宽度为1， 长度为4。 但是不能生成board[0][0] - board[1][3]这样的厚墙（2*4)
     *
     * (2) 要求这个迷宫有且仅有一条路可以从start到达destination， 另外对于那些不是墙的blank cell，也要有可以从start到达它的路径。 也就是说不能有一些孤岛是不能到达的
     *
     * (3)  后来大哥给我简化了一点，如果输入board里面
     *
     * 已经有一些墙， 用1表示，但是这个迷宫并不是具有通路的，然后让你根据以上条件，生成迷宫。
     *
     * 思路：直接DFS每次走两步，避免生成没有墙的空地。
     *
     * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell.
     * For each pair of cells on the corridor, there must exist one and only one path between them.
     * (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.).
     * The wall is denoted by 1 in the matrix and corridor is denoted by 0.
     *
     * Assumptions
     *
     * N = 2K + 1 and K >= 0
     * the top left corner must be corridor
     * there should be as many corridor cells as possible
     * for each pair of cells on the corridor, there must exist one and only one path between them
     */


    /**
     * Time: O(n * n). we need to try to flip all positions the in board
     */
    public void T19_test() {
        int n = 5;
        T19_Maze sln = new T19_Maze();
        int[][] maze = sln.maze(n);
        Debug.printMatrix(maze);
    }
    public class T19_Maze {

        public int[][] maze(int n) {
            // Assumptions: n = 2 * k + 1, where k > = 0.
            int[][] maze = new int[n][n];
            // initialize the matrix as only (0,0) is corridor,
            // other cells are all walls at the beginning.
            // later we are trying to break the walls to form corridors.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        maze[i][j] = 0;
                    } else {
                        maze[i][j] = 1;
                    }
                }
            }

            generate(maze, 0, 0);
            return maze;
        }


        private void generate(int[][] maze, int x, int y) {
            // get a random shuffle of all the possible directions,
            // and follow the shuffled order to do DFS & backtrack.
            Dir[] dirs = Dir.values();

            shuffle(dirs);

            for (Dir dir: dirs) {
                // advance by two steps.
                int nextX = dir.moveX(x, 2);
                int nextY = dir.moveY(y, 2);

                if (isValidWall(maze, nextX, nextY)) {
                    // only if the cell is a wall(meaning we have not visited before),
                    // we break the walls through to make it corridors.
                    maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
                    maze[nextX][nextY] = 0;
                    generate(maze, nextX, nextY);
                }
            }
        }

        // Get a random order of the directions.
        private void shuffle(Dir[] dirs) {
            for (int i = 0; i < dirs.length; i++) {
                int index = (int)(Math.random() * (dirs.length - i));
                System.out.println("index = " + index);
                // swap
                Dir tmp = dirs[i];
                dirs[i] = dirs[i + index];
                dirs[i + index] = tmp;
            }
        }

        // check if the position (x,y) is within the maze and it is a wall.
        private boolean isValidWall(int[][] maze, int x, int y) {
            return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length &&
                    maze[x][y] == 1;
        }
    }

    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), EAST(0, -1), WEST(0, 1);
        int deltaX;
        int deltaY;

        Dir(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // move certain times of deltax.
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }

        // move certain times of deltaY.
        public int moveY(int y, int times) {
            return y + times * deltaY;
        }
    }
}
