package class5;
import java.util.*;
public class DP2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	/*
	 * 4 区间动态规划
	 */
	
	/*
	 * Copy Books
	 * 
	 * Given an array A of integer with size of n( means n books and number of pages of each book) 
	 * and k people to copy the book. 
	 * You must distribute the continuous id books to one people to copy. 
	 * (You can give book A[1],A[2] to one people,
	 *  but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) 
	 *  Each person have can copy one page per minute. 
	 *  Return the number of smallest minutes need to copy all the books.
	 *  
	 *  Given array A = [3,2,4], k = 2.
	 *  Return 5( First person spends 5 minutes to copy book 1 and book 2 
	 *  and second person spends 4 minutes to copy book 3. )
	 *  
	 *  state:
	 *  dp[i][nk] 前i 本书 用nk 个人写的最小花费
	 *  2
	 *  dp[i][nk] = max{dp[j][nk - 1], w[j + 1][i]}
	 *  3: init
	 *  dp[i][1] = w[1][i]
	 *  4: answer:
	 *  dp[n][k]
	 *  
	 *  http://www.lintcode.com/en/problem/copy-books/
	 */
	
	private static int[][] init(int[] A) {
		int n = A.length;
		int[][] w = new int[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				for (int k = i; k <= j; ++k) {
					w[i][j] += A[k - 1];
				}
			}
		}
		return w;
	}

	public static int copyBooks(int[] pages, int k) {
		// write your code here
		int n = pages.length;
		int[][] w = init(pages);
		int[][] dp = new int[n + 2][k + 2];
		int[][] s = new int[n + 2][k + 2];

		int ans = Integer.MIN_VALUE;
		if (n <= k) {
			for (int i = 0; i < n; i++)
				ans = Math.max(ans, pages[i]);
			return ans;
		}

		for (int i = 0; i <= n; ++i) {
			dp[i][1] = w[1][i];

		}

		for (int nk = 2; nk <= k; nk++) {

			for (int i = nk; i <= n; i++) {
				dp[i][nk] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					if (dp[i][nk] == Integer.MAX_VALUE
							|| dp[i][nk] > Math.max(dp[j][nk - 1], w[j + 1][i]))
						dp[i][nk] = Math.max(dp[j][nk - 1], w[j + 1][i]);
				}
			}
		}
		return dp[n][k];
	}
	
	
	/*
	 * Copy Books 2
	 */
	public static int copyBooksII(int n, int[] times) {
		int k = times.length;
		int[][] f = new int[2][n + 1];
		for (int j = 0; j <= n; ++j) {
			f[0][j] = j * times[0];
		}
		for (int i = 1; i < k; ++i) {
			for (int j = 1; j <= n; ++j) {
				int a = i % 2;
				int b = 1 - a;

				f[a][j] = Integer.MAX_VALUE;
				for (int l = 0; l <= j; ++l) {
					if (f[b][j - l] > times[i] * l) {
						f[a][j] = Math.min(f[a][j], f[b][j - l]);
					} else {
						f[a][j] = Math.min(f[a][j], times[i] * l);
						break;
					}
				}

			}
		}
		return f[(k - 1) % 2][n];
	}

	public static int copyBooksII2D(int n, int[] times) {
		int k = times.length;
		int[][] f = new int[k][n + 1];
		for (int j = 0; j <= n; ++j) {
			f[0][j] = j * times[0];
		}
		for (int i = 1; i < k; ++i) {
			for (int j = 1; j <= n; ++j) {
				f[i][j] = Integer.MAX_VALUE;
				for (int l = 0; l <= j; ++l) {
					f[i][j] = Math.min(f[i][j],
							Math.max(f[i - 1][j - l], times[i] * l));
					if (f[i - 1][j - l] <= times[i] * l) {
						break;
					}
				}

			}
		}
		return f[k - 1][n];
	}

	
	
	/*
	 * Post office problem
	 *
	 * On one line there are n houses. Give you an array of integer means the the position of each house. 
	 * Now you need to pick k position to build k post office, 
	 * so that the sum distance of each house to the nearest post office is the smallest. 
	 * Return the least possible sum of all distances between each village and its nearest post office.
	 * 
	 * Given array a = [1,2,3,4,5], k = 2.
	 * return 3.
	 * Challenge
	 * Could you solve this problem in O(n^2) time ?
	 */
	/*
	 * State:
	 * 1
	 * dp[i][nk] 表示前i个房子用 nk个邮局的最小花费
	 * 2
	 * dp[i][nk] = max {dp[j][nk - 1] + dis[j + 1][i]}
	 * 3 init
	 * dp[i][1] = dis[1][i]
	 * 4 ans
	 * dp[n][k]
	 */
	
	
    
    public static int postOffice(int[] A, int k) {
        // Write your code here
        int n = A.length;
        Arrays.sort(A);

        int [][]dis = init2(A);
        int [][]dp = new int[n + 1][k + 1];
        if(n == 0 || k >= A.length)
            return 0;
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i <= n;++i)  {
            dp[i][1] = dis[1][i];

        }
        for(int nk = 2; nk <= k; nk++) {
            
            for(int i = nk; i <= n; i++) {
                dp[i][nk] = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++) {  
                    if(dp[i][nk] == Integer.MAX_VALUE || dp[i][nk] > dp[j][nk-1] + dis[j+1][i])  
                        dp[i][nk] = dp[j][nk-1] + dis[j+1][i];   
                }  
            }
        }
        return dp[n][k];
    }
    private static int[][] init2(int []A)  
    {  
        int n = A.length;
        int [][]dis = new int [n+1][n+1];
        for(int i = 1; i <= n; i++) {  
            for(int j = i+1 ;j <= n;++j)  
            {  
                int mid = (i+j) /2;  
                for(int k = i;k <= j;++k)  
                    dis[i][j] += Math.abs(A[k - 1] - A[mid - 1]);  
            } 
        }
        return dis; 
    } 
    
    
    /*
     * 1.  空间优化 重点: 滚动数组
     * 2.  记忆化搜索 重点: 博弈类问题  （画搜索树）
     * 3.  循环引 的状态数组 重点: global 和 local 最优
     * 4.  区间动态规划 重点: 区间的划分
     *           把区间分成k 段
     */
	
	
}
