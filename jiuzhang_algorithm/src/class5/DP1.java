package class5;

public class DP1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * 1. 状态 State
	 * 灵感,创造 ,存储 规模问题的结果 a) 最优解/Maximum/Minimum
	 * b) Yes/No
	 * c) Count(*) 
	 * 2. 方程 Function
	 * 状态之间的联系,怎么通过 的状态,来求得 的状态 
	 * 3. 初始化 Intialization
	 *    最极限的 状态是什么, 起点 
	 * 4. 答案 Answer
	 * 最大的那个状态是什么,终点
	 */
	
	
	/*
	 * 1 空间优化 
	 * http://www.lintcode.com/en/problem/house-robber/
	 * House Robber
	 * 
	 * 1. 状态State f[i] 表 前i个房 中,偷了第i个房 的,偷到的最 价值 
	 * 2. 方程 Function
	 * f[i] = max(f[i-2], f[i-3])+ A[i]; 
	 * 3. 初始化 Intialization
	 * f[0] = A[0];
	 * f[1] = Math.max(A[0], A[1]);
	 * f[2] = Math.max(A[0]+A[2], A[1]);
	 * 4. 答案 Answer max{f[i]}
	 * 
	 * 
	 *  一维优化的题 这类题 特点
	 *  f[i] = 由 f[i-2],f[i-3] 来决定状态
	 *  可以转化为
	 *  f[i%3] =由f[(i-2)%3]和 f[(i-3)%3] 来决定状态
	 *  观察我们需要保留的状态来确定模数
	 *  如
	 *  Climbing Stairs
	 *  Maximum Subarray
	 */
  
	/*
	 * Maximal Square
	 * http://www.lintcode.com/en/problem/maximal-square/
	 * Maximal Square
	 * 1.  状态State
	 * f[i][j] 表 以i和j作为正 形右下 可以拓展的最大边长  
	 * 2.  方程 Function
	 * if matrix[i][j] == 1
	 * f[i][j] = min(f[i - 1][j], f[i][j-1], f[i-1][j-1]) + 1;
	 * if matrix[i][j] == 0 f[i][j] = 0
	 * 3. 初始化 Intialization
	 * f[i][0] = matrix[i][0];
	 * f[0][j] = matrix[0][j];
	 * 4. 答案 Answer
	 *   max{f[i][j]}
	 */
	
	/*
	 * 1.  状态State
	 * f[i][j] 表 以i和j作为正 形右下 可以拓展的最 边  
	 * 2.  程 Function 
	 * if matrix[i][j] == 1
	 * 	f[i%2][j] = min(f[(i – 1)%2][j], f[i%2][j-1], f[(i-1)%2][j-1]) + 1;
	 * if matrix[i][j] == 0 f[i%2][j] = 0
	 * 3. 初始化 Intialization
	 * f[i][0] = matrix[i][0];
	 * f[0][j] = matrix[0][j];
	 * 4. 答案 Answer
	 * max{f[i][j]}
	 */
	
	
	/*
	 * Backpack II
	 * http://www.lintcode.com/en/problem/backpack-ii/
	 * 1.  状态State f[i][j] 表 前i个物品当中选 些物品组成容量为j的最 价值
	 * 2.  方程 Function
	 * f[i][j] = max(f[i-1][j], f[i-1][j-A[i-1]] + V[i-1]);
	 * 3. 初始化 Intialization dp[0][0]=0;
	 * 4. 答案 Answer dp[n][m]
	 * 
	 * 类似 维动态规划空间优化 这类题 特点
	 * f[i][j] = 由f[i-1]  或者 f[i][k](k<j) 来决定状态
	 * 第i 跟 i-2 之前毫 关系
	 * 状态转变为
	 * f[i%2][j] = 由f[(i-1)%2]  或者 f[i%2][k](k<j) 来决定状态
	 * 相关的题 
	 * Unique Paths 
	 * Minimum Path Sum 
	 * Edit Distance
	 */
	
	/*
	 * 2 记忆化搜索
	 * 
	 * 记忆化搜索
	 * 本质上:动态规划
	 * 动态规划就是解决了重复计算的搜索
	 * 动态规划的实现 式:
	 * 1.  循环(从 到 递推)
	 * 2.  记忆化搜索(从 到 搜索)
	 *
	 * 什么时候用记忆化搜索
	 * 1.  状态转移特别麻烦,不是顺序性。 
	 * 2.  初始化状态不是很容易找到
	 */
	
	/*
	 * Longest Increasing Subsequence
	 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence/
	 */
	// O(n log n)
	public static int longest(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int[] tbl = new int[array.length + 1];
		int result = 1;
		tbl[1] = array[0];
		for (int i = 1; i < array.length; i++) {
			int index = find(tbl, 1, result, array[i]);
			if (index == result) {
				tbl[++result] = array[i];
			} else {
				tbl[index + 1] = array[i];
			}
		}
		return result;
	}

	private static int find(int[] tbl, int left, int right, int target) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (tbl[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
	
	
	// DP: M[i] stands that the length of longest ascending subsequence ending
	// with array[i].
	// base case: M[0] = 1;
	// induction rule: 
	// M[i] = array[i] > array[j] && M[j] + 1 > M[i] ? M[i] = M[j] + 1 (j >= 0 && j < i)
	// Time:O(n^2)
	public static int longest_dp(int[] array) {
		// Write your solution here.
		if (array == null || array.length == 0) {
			return 0;
		}
		if (array.length == 1) {
			return 1;
		}
		int n = array.length;
		int[] M = new int[n];
		M[0] = 1;
		for (int i = 1; i < n; i++) {
			M[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[i] > array[j] && M[j] + 1 > M[i]) {
					M[i] = M[j] + 1;
				}
			}
		}

		// scan M[], get the max
		int max = 1;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, M[i]);
		}
		return max;

	}

	
	/*
	 * Longest Increasing continuous Subsequence 2D
	 * http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence-ii/
	 */
	
	// Time: O(n * m)
	public static int longestIncreasingContinuousSubsequenceII(int[][] A) {
		// Write your code here
		if (A.length == 0)
			return 0;
		int n = A.length;
		int m = A[0].length;
		int ans = 0;
		dp = new int[n][m];
		flag = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = search(i, j, A);
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static int[][] dp;
	public static int[][] flag;

	public static int search(int x, int y, int[][] A) {
		int n = A.length;
		int m = A[0].length;
		if (flag[x][y] != 0)
			return dp[x][y];
		
		int ans = 1;
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (A[x][y] > A[nx][ny]) {
					ans = Math.max(ans, search(nx, ny, A) + 1);
				}
			}
		}
		flag[x][y] = 1;
		dp[x][y] = ans;
		return ans;
	}
	
	
	/*
	 * 博弈类
	 * Coins in a line
	 * 
	 * There are n coins in a line. 
	 * Two players take turns to take one or two coins from right side until there are no more coins left. 
	 * The player who take the last coin wins.
	 * Could you please decide the first play will win or lose?
	 * 
	 * n = 1, return true.
	 * n = 2, return true.
	 * n = 3, return false.
	 * n = 4, return true.
	 * n = 5, return true.
	 * Challenge
	 * O(n) time and O(1) memory
	 * 
	 * 
	 * state: f[x] 现在还剩x个硬币，现在先手取硬币的人最后输赢状况
	 * f[0], not visited yet
	 * f[1], false, lose
	 * f[2], true, win
	 * function: 
	 * f[n] = (f[n-2]&& f[n-3]) || (f[n-3]&&f[n-4] )
	 * 
	 * initialize: 
	 * f[1] = true;
	 * f[2] = true;
	 * f[3] = false;
	 * f[4] = true
	 * 
	 * answer: 
	 * f[n]
	 */
	public static boolean firstWillWin(int n) {
        // write your code here
		int []dp = new int[n+1];
        return MemorySearch(n, dp);
    }
	

    public static boolean MemorySearch(int n, int []dp) { // 0 is empty, 1 is false, 2 is true
        if(dp[n] != 0) {
            if(dp[n] == 1)
                return false;
            else
                return true;
        }
        if(n <= 0) {
            dp[n] = 1;
        } else if(n == 1) {
            dp[n] = 2;
        } else if(n == 2) {
            dp[n] = 2;
        } else if(n == 3) {
            dp[n] = 1;
        } else {
            if((MemorySearch(n-2, dp) && MemorySearch(n-3, dp)) || 
                (MemorySearch(n-3, dp) && MemorySearch(n-4, dp) )) {
                dp[n] = 2;
            } else {
                dp[n] = 1;
            }
        }
        if(dp[n] == 2) 
            return true;
        return false;
    }
    
    
    /*
     * Coins in a Line II
     * 
     * There are n coins with different value in a line. The coins are sorted according to value. 
     * Two players take turns to take one or two coins from left side until there are no more coins left. 
     * The player who take the coins with the most value wins.
     * Could you please decide the first player will win or lose?
     * 
     * Given values array A = [1,2,2], return true.
     * Given A = [1,2,4], return false.
     * 
     * 先手通过某种策略， 使 他拿到的价值 大于 1/2 的总价值
     * 
     * state: f[x] 现在还剩x个硬币，现在先手取硬币的人最后最多取硬币价值
     * function:   
     * f[x] = max(min(f[x-2], f[x-3])+a[n-x] ) ， (min(f[x-3],f[x-4])+a[n-x]+a[n-x+1] )
     * intialize: 
     * 		f[0] = 0
     * 		f[1] = a[n-1]
     * 		f[2] = a[n-2] + a[n-1]
     * Answer: f[n]
     * 
     */
    
    public static boolean firstWillWin2(int[] values) {
        // write your code here
        int []dp = new int[values.length + 1];
        boolean []flag =new boolean[values.length + 1];
        int sum = 0;
        for(int now : values) 
            sum += now;
        
        return sum < 2*MemorySearch(values.length, dp, flag, values);
    }
    public static int MemorySearch(int n, int []dp, boolean []flag, int []values) { 
        if(flag[n] == true)
            return dp[n];
        flag[n] = true;
        if(n == 0)  {
            dp[n] = 0;  
        } else if(n == 1) {
            dp[n] = values[values.length-1];
        } else if(n == 2) {
            dp[n] = values[values.length-1] + values[values.length-2]; 
        } else if(n == 3){
            dp[n] = values[values.length-2] + values[values.length-3]; 
        } else {
            dp[n] = Math.max(
                Math.min(MemorySearch(n-2, dp, flag,values) , MemorySearch(n-3, dp, flag, values)) + values[values.length-n],
                Math.min(MemorySearch(n-3, dp, flag, values), MemorySearch(n-4, dp, flag, values)) + values[values.length-n] + values[values.length - n + 1]
                );
        } 
        return dp[n];
    }
    
    /*
     * Coins in a line III
     * There are n coins in a line. 
     * Two players take turns to take a coin from one of the ends of the line until there are no more coins left. 
     * The player with the larger amount of money wins.
     * Could you please decide the first player will win or lose?
     * 
     * state: 
     *      f[x][y] 现在还第x到第y的硬币，现在先手取硬币的人最后最多取硬币价值
     * function: 
     *      f[x][y] = max(min(f[x+2][y], f[x+1][y-1])+a[x] ) ,
     *          	           (min(f[x][y-2], f[x+1][y-1])+a[y]  )
     * intialize: 
     * 		f[x][x] = a[x],
     * 		f[x][x+1] = max(a[x],a[x+1]),
     * 		
     * Answer:f[0][n]
     * 
     * Time: O(n*n)
     * Space: O(n*n)
     */
	public static boolean firstWillWin(int[] values) {
		// write your code here
		int n = values.length;
		int[][] dp = new int[n + 1][n + 1];
		boolean[][] flag = new boolean[n + 1][n + 1];

		int sum = 0;
		for (int now : values)
			sum += now;

		return sum < 2 * MemorySearch(0, values.length - 1, dp, flag, values);
	}

	public static int MemorySearch(int left, int right, int[][] dp, boolean[][] flag,
			int[] values) {

		if (flag[left][right])
			return dp[left][right];
		
		flag[left][right] = true;
		if (left > right) {
			dp[left][right] = 0;
		} else if (left == right) {
			dp[left][right] = values[left];
		} else if (left + 1 == right) {
			dp[left][right] = Math.max(values[left], values[right]);
		} else {
			int pick_left = Math.min(
					MemorySearch(left + 2, right, dp, flag, values),
					MemorySearch(left + 1, right - 1, dp, flag, values))
					+ values[left];
			int pick_right = Math.min(
					MemorySearch(left, right - 2, dp, flag, values),
					MemorySearch(left + 1, right - 1, dp, flag, values))
					+ values[right];
			dp[left][right] = Math.max(pick_left, pick_right);
		}
		return dp[left][right];
	}
	
	/*
	 * http://articles.leetcode.com/2011/02/coins-in-line.html
	 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
	 */
	
    /*
     * 3.用循环引用的状态数组解决高难度的动态规划问题
     */
	/*
	 * maximum subarray
	 * 状态 State
	 * 	local[i] 表示包括第i个元素能够找到的 最大值
	 * 	global[i] 表示全局前i个元素中能够找到的最大值
	 * 2. 方程 Function
	 * 	   	  local[i] = Max(nums[i],local[i-1]+nums[i]);  
	 *        global[i] = Max(local[i],global[i-1]); 
	 * 3. 初始化 Intialization
	 *        local [0] = global [0] = nums[0];
	 * 4. 答案 Answer 
	 *        global[n-1]
	 */
	
	/*
	 * Maximum Product Subarray
	 * 1. 状态 State
	 * 	min[i] 表示前i个数包括第i个数找到的最小乘积
	 * 	max[i] 表示前i个数包括第i个数找到的最大乘积
	 * 2. 方程 Function
	 *             min[i] = Min(nums[i], Min(min[i - 1 ] * nums[i], max[i - 1] * nums[i]));
	 *             max[i] = Max(nums[i], Max(max[i - 1 ] * nums[i], min[i - 1] * nums[i]));
	 *             global[i] = Max(global[i-1], max[i])
	 * 3. 初始化 Intialization
	 *             min[0] = max[0] = nums[0];
	 * 4. 答案 Answer	global[n-1]
	 * 
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
	
	public static int maxProfix_v1(int[] prices, int k) {
		return -1;
	}
	
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
	
	
	
	

}
