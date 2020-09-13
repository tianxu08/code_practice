package sortbynum;

import java.util.*;
import java.util.Map.Entry;

import ds.*;

public class Task301_350 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test301();
		// test302();
//		test311();
		// test313();
		// test315();
		 test320();
		// test321();
		// test322();
//		 test324();
//		test324_1();
		// test327();
		// test326();
		// test330();
		// test331();
//		test332();
//		test333();
//		test336();
//		test337();
//		test340();
//		test347();
//		test350();
	}

	
	/*
	 * task301 Remove Invalid Parentheses
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
	 * 302. Smallest Rectangle Enclosing Black Pixels
	 * https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
	 * http://qa.geeksforgeeks.org/5013/return-the-smallest-rectangle
	 * 
	 * An image is represented by a binary matrix with 0 as a white pixel and 1
	 * as a black pixel.
	 * 
	 * The black pixels are connected, i.e., there is only one black region.
	 * Pixels are connected horizontally and vertically. Given the location (x,
	 * y) of one of the black pixels, return the area of the smallest
	 * (axis-aligned) rectangle that encloses all black pixels.
	 * 
	 * Black pixels connected.
	 * 
	 * 
	 * For example, given the following image:
	 * 
	 * [ "0010", "0110", "0100" ] and x = 0, y = 2, Return 6.
	 */
	public static void test302() {
		char[][] image = { "0010".toCharArray(), "0110".toCharArray(),
				"0100".toCharArray() };
		int rev = task302_minArea(image, 0, 2);
		System.out.println("rev = " + rev);

		int rev2 = task302_minArea2(image, 0, 2);
		System.out.println("rev2 = " + rev2);
	}

	/*
	 * use binary search. Time: O(m * log n + n * log m)
	 */
	public static int task302_minArea(char[][] image, int x, int y) {
		// sanity check
		if (image == null || image.length == 0 || image[0] == null
				|| image[0].length == 0) {
			return 0;
		}
		int rowLen = image.length;
		int colLen = image[0].length;
		int left = searchCol(image, 0, y, true);
		int right = searchCol(image, y, colLen - 1, false);
		int up = searchRow(image, 0, x, true);
		int down = searchRow(image, x, rowLen - 1, false);

		return (right - left + 1) * (down - up + 1);
	}

	public static int searchCol(char[][] image, int start, int end,
			boolean explore_left) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			boolean midColHasBlack = checkCol(image, mid);
			if (explore_left) {
				if (midColHasBlack == true) {
					end = mid;
				} else {
					start = mid;
				}
			} else {
				if (midColHasBlack == true) {
					// explore right side
					start = mid;
				} else {
					end = mid;
				}
			}
		}

		if (explore_left) {
			if (checkCol(image, start)) {
				return start;
			} else {
				return end;
			}
		} else {
			if (checkCol(image, end)) {
				return end;
			} else {
				return start;
			}
		}
	}

	public static int searchRow(char[][] image, int start, int end,
			boolean explore_up) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			boolean rev = checkRow(image, mid);
			if (explore_up) {
				if (rev) {
					// row_mid has black pixel
					// explore up
					end = mid;
				} else {
					start = mid;
				}
			} else {
				if (rev) {
					start = mid;
				} else {
					end = mid;
				}
			}
		}
		if (explore_up) {
			if (checkRow(image, start)) {
				return start;
			} else {
				return end;
			}
		} else {
			if (checkRow(image, end)) {
				return end;
			} else {
				return start;
			}
		}
	}

	public static boolean checkCol(char[][] board, int colNum) {
		int rowLen = board.length;
		for (int k = 0; k < rowLen; k++) {
			if (board[k][colNum] == '1') {
				return true;
			}
		}
		return false;
	}

	public static boolean checkRow(char[][] board, int rowNum) {
		int colLen = board[0].length;
		for (int k = 0; k < colLen; k++) {
			if (board[rowNum][k] == '1') {
				return true;
			}
		}
		return false;
	}

	/*
	 * Do BFS
	 */
	public static int task302_minArea2(char[][] image, int x, int y) {
		if (image == null || image.length == 0 || image[0] == null
				|| image[0].length == 0) {
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
		while (!queue.isEmpty()) {
			Item cur = queue.pollFirst();
			left = Math.min(left, cur.y);
			right = Math.max(right, cur.y);
			up = Math.min(up, cur.x);
			down = Math.max(down, cur.x);
			// expand
			for (int i = 0; i < 4; i++) {
				int nextX = dx[i] + cur.x;
				int nextY = dy[i] + cur.y;
				if (nextX >= 0 && nextX < rowLen && nextY >= 0
						&& nextY < colLen && visited[nextX][nextY] == false
						&& image[nextX][nextY] == '1') {
					queue.offerLast(new Item(nextX, nextY));
					visited[nextX][nextY] = true;
				}
			}
		}
		return (right - left + 1) * (down - up + 1);

	}

	public static class Item {
		int x;
		int y;

		public Item(int _x, int _y) {
			this.x = _x;
			this.y = _y;
		}
	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	/*
	 * 303
	 * 
	 * Range Sum Query - Immutable Given an integer array nums, find the sum of
	 * the elements between indices i and j (i ≤ j), inclusive. Given nums =
	 * [-2, 0, 3, -5, 2, -1] sumRange(0, 2) -> 1 sumRange(2, 5) -> -1
	 * sumRange(0, 5) -> -3 You may assume that the array does not change. There
	 * are many calls to sumRange function.
	 * 
	 * see task303_rangeSumQuery
	 * 
	 * 303 Range Sum Query - immutable 304 Range Sum Query 2D - immutable
	 * 
	 * 307 Range Sum Query - mutable 308 Range Sum Query 2D - mutable
	 */
	public static void test303() {

	}

	/*
	 * task304 See task304 in the same directory
	 */
	public static void test304() {

	}

	/*
	 * task305 Number of Islands II Union Find see task305 in the same directory
	 */
	public static void test305() {

	}

	/*
	 * task306 Additive Number 
	 * 
	 */
	public boolean task306_isAdditiveNumber(String num) {
		int L = num.length();

		// choose the first number A
		for (int i = 1; i <= (L - 1) / 2; i++) {
			// A cannot start with a 0 if its length is more than 1
			if (num.charAt(0) == '0' && i >= 2)
				break; // previous code: continue;

			// choose the second number B
			for (int j = i + 1; L - j >= j - i && L - j >= i; j++) {
				// B cannot start with a 0 if its length is more than 1
				if (num.charAt(i) == '0' && j - i >= 2)
					break; // previous: continue;

				long num1 = Long.parseLong(num.substring(0, i)); // A
				long num2 = Long.parseLong(num.substring(i, j)); // B
				String substr = num.substring(j); // remaining string

				if (task306_isAdditive(substr, num1, num2))
					return true; // return true if passes isAdditive test
				// else continue; // continue for loop if does not pass
				// isAdditive test
			}
		}
		return false; // does not pass isAdditive test, thus is not additive
	}

	// Recursively checks if a string is additive
	public boolean task306_isAdditive(String str, long num1, long num2) {
		if (str.equals(""))
			return true; // reaches the end of string means a yes

		long sum = num1 + num2;
		String s = ((Long) sum).toString();
		if (!str.startsWith(s))
			return false; // if string does not start with sum of num1 and num2,
							// returns false

		return task306_isAdditive(str.substring(s.length()), num2, sum); 
		// recursively checks the remaining string
	}

	/*
	 * task307 task308 see task307 task308 in same directory
	 */
	public static void test307() {

	}

	public static void test308() {

	}

	/*
	 * task309 Best Time to Buy and Sell Stock with Cooldown
	 * https://leetcode.com
	 * /problems/best-time-to-buy-and-sell-stock-with-cooldown/ to Do
	 */
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int len = prices.length;
		int[] sell = new int[len];
		int[] coolDown = new int[len];
		sell[0] = 0;
		coolDown[0] = 0;
		for (int i = 1; i < len; i++) {
			sell[i] = Math.max(sell[i - 1] + prices[i] - prices[i - 1],
					coolDown[i - 1]);
			coolDown[i] = Math.max(sell[i - 1], coolDown[i - 1]);
		}
		return Math.max(sell[len - 1], coolDown[len - 1]);
	}

	/*
	 * task310 Minimum Height Trees
	 * https://leetcode.com/problems/minimum-height-trees/ To Do
	 * 
	 * Basically my code starts from the leaf nodes. For leaf nodes, their
	 * degree = 1, which means each of them is only connected to one node. In
	 * our loop, each time we delete the leaf nodes from our graph(just by
	 * putting their degrees to 0), and meanwhile we add the new leaf nodes
	 * after deleting them (just add their connected nodes with degree as 2) to
	 * the queue. So basically in the end, the nodes in the queue would be
	 * connected to no other nodes but each other. They should be the answer.
	 */
	public List<Integer> task310_findMinHeightTrees(int n, int[][] edges) {
		List<List<Integer>> myGraph = new ArrayList<List<Integer>>();
		List<Integer> res = new ArrayList<Integer>();
		if (n == 1) {
			res.add(0);
			return res;
		}
		int[] degree = new int[n];
		for (int i = 0; i < n; i++) {
			myGraph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < edges.length; i++) {
			myGraph.get(edges[i][0]).add(edges[i][1]);
			myGraph.get(edges[i][1]).add(edges[i][0]);
			degree[edges[i][0]]++;
			degree[edges[i][1]]++;
		}
		Queue<Integer> myQueue = new ArrayDeque<Integer>();

		for (int i = 0; i < n; i++)
			if (degree[i] == 0)
				return res;
			else if (degree[i] == 1) {
				myQueue.offer(i);
			}

		while (!myQueue.isEmpty()) {
			res = new ArrayList<Integer>();
			int count = myQueue.size();

			for (int i = 0; i < count; i++) {
				int curr = myQueue.poll();
				res.add(curr);
				degree[curr]--;
				for (int k = 0; k < myGraph.get(curr).size(); k++) {
					int next = myGraph.get(curr).get(k);
					if (degree[next] == 0)
						continue;
					if (degree[next] == 2) {
						myQueue.offer(next);
					}
					degree[next]--;
				}
			}
		}
		return res;
	}

	/*
	 * 311. Sparse Matrix Multiplication HashTable
	 * 
	 * http://www.cnblogs.com/jcliBlogger/p/5015959.html
	 */
	public static void test311() {
		int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
		int[][] B = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		int[][] result = task311_multiply_opt(A, B);
		Debug.printMatrix(result);
	}

	public static int[][] task311_multiply(int[][] A, int[][] B) {
		List<Item> listA = new ArrayList<Item>();
		List<Item> listB = new ArrayList<Item>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] != 0) {
					listA.add(new Item(i, j));
				}
			}
		}

		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				if (B[i][j] != 0) {
					listB.add(new Item(i, j));
				}
			}
		}

		int[][] result = new int[A.length][B[0].length];
		for (Item itemA : listA) {
			for (Item itemB : listB) {
				if (itemA.y == itemB.x) {
					result[itemA.x][itemB.y] += A[itemA.x][itemA.y]
							* B[itemB.x][itemB.y];
				}
			}
		}
		return result;
	}

	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];

		for (int i = 0; i < m; i++) {
			for (int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0)
							C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
	}

	/*
	 * optimize solution sln 1 use hashMap
	 * 
	 * The sparse matrix can be 
	 * 
	 * a sparse matrix can be represented as a sequence of rows, 
	 * each of which is a sequence of (column-number, value) pairs of the nonzero values in the row. 
	 * The matrixA =	
	 * 2.0	-1.0	0	0
	 * -1.0	2.0	-1.0	0
	 * 0	-1.0	2.0	-1.0
	 * 0	0	-1.0	2.0
	 * is represented in this way as
	 * A = [
	 * 		[(0, 2.0), (1, -1.0)],
	 * 		[(0, -1.0), (1, 2.0), (2, -1.0)],
	 * 		[(1, -1.0), (2, 2.0), (3, -1.0)],
	 * 		[(2, -1.0), (3, 2.0)]
	 *     ]
	 * 
	 */

	public static int[][] task311_multiply_opt(int[][] A, int[][] B) {
		if (A == null || B == null || A.length == 0 || B.length == 0
				|| A[0].length == 0 || B[0].length == 0) {
			return null;
		}
		int m = A.length;
		int n = A[0].length;
		int l = B[0].length;

		int[][] C = new int[m][l];

		Map<Integer, Map<Integer, Integer>> denseA = new HashMap<Integer, Map<Integer, Integer>>();
		// get dense A
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] != 0) {
					if (!denseA.containsKey(i)) {
						denseA.put(i, new HashMap<Integer, Integer>());
					}
					denseA.get(i).put(j, A[i][j]);
				}
			}
		}

		Map<Integer, Map<Integer, Integer>> denseB = new HashMap<Integer, Map<Integer, Integer>>();
		// get dense B
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < l; k++) {
				if (B[j][k] != 0) {
					if (!denseB.containsKey(j)) {
						denseB.put(j, new HashMap<Integer, Integer>());
					}
					denseB.get(j).put(k, B[j][k]);
				}
			}
		}

		// get the result C
		for (Integer i : denseA.keySet()) {
			for (Integer j : denseA.get(i).keySet()) {
				if (!denseB.containsKey(j)) {
					continue;
				}
				// denseB containsKey(j) == true
				for (Integer k : denseB.get(j).keySet()) {
					C[i][k] += denseA.get(i).get(j) * denseB.get(j).get(k);
				}
			}
		}
		return C;
	}

	/*
	 * sln2 http://www.cs.cmu.edu/~scandal/cacm/node9.html 
	 * idea from a CMU lecture
	 */
	public static int[][] taks311_multiply_sln2(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] result = new int[m][nB];

		List[] indexA = new List[m];
		for (int i = 0; i < m; i++) {
			List<Integer> numsA = new ArrayList<Integer>();
			for (int j = 0; j < n; j++) {
				if (A[i][j] != 0) {
					numsA.add(j);
					numsA.add(A[i][j]);
				}
			}
			indexA[i] = numsA;
		}

		for (int i = 0; i < m; i++) {
			List<Integer> numsA = indexA[i];
			for (int p = 0; p < numsA.size() - 1; p += 2) {
				int colA = numsA.get(p);
				int valA = numsA.get(p + 1);
				for (int j = 0; j < nB; j++) {
					int valB = B[colA][j];
					result[i][j] += valA * valB;
				}
			}
		}
		return result;
	}

	/*
	 * 312 Burst Balloons 
	 * https://leetcode.com/problems/burst-balloons/
	 * 
	 * dp[i][j] means the maximal coins for range [i...j]. In this case, our
	 * final answer is dp[0][nums.length - 1].
	 * 
	 * 
	 * https://leetcode.com/discuss/72215/java-dp-solution-with-detailed-explanation-o-n-3
	 */
	public static int task312_maxCoins(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[][] dp = new int[nums.length][nums.length];
		for (int len = 1; len <= nums.length; len++) {
			for (int start = 0; start <= nums.length - len; start++) {
				int end = start + len - 1; // start + len - 1 <= nums.length - 1
											// => start <= nums.length - len
				for (int k = start; k <= end; k++) {
					int coins = getCoins(nums, k - 1) * getCoins(nums, k)
							* getCoins(nums, k + 1);
					coins += k != start ? dp[start][k - 1] : 0;
					// If not first one, we can add subrange on its left.
					coins += k != end ? dp[k + 1][end] : 0;  
					// If not last one, we can add subrange on its right
					dp[start][end] = Math.max(dp[start][end], coins);
				}
			}
		}
		return dp[0][nums.length - 1];
	}

	public static int getCoins(int[] nums, int index) {
		if (index < 0 || index >= nums.length) {
			return 1;
		} else {
			return nums[index];
		}
	}

	/*
	 * 313 Super Ugly Number
	 */
	public static void test313() {
		int[] primes = { 2, 3, 5, 7 };
		int n = 25;
		int rev = task313_nthSuperUglyNumber(n, primes);
		System.out.println("rev = " + rev);
		int rev2 = task313_nthSuperUglyNumber2(n, primes);
		System.out.println("rev2 = " + rev2);
		int rev3 = task313_nthSuperUglyNumber3(n, primes);
		System.out.println("rev3 = " + rev3);
	}

	public static int task313_nthSuperUglyNumber(int n, int[] primes) {
		long result = task313_uglyNumber(n, primes);
		return (int) result;
	}

	/*
	 * Time: O(n * k * log(n*k))
	 */
	public static long task313_uglyNumber(int n, int[] primes) {
		PriorityQueue<Long> queue = new PriorityQueue<Long>();
		HashSet<Long> set = new HashSet<Long>();
		queue.add((long) 1);
		while (n > 1) {
			Long cur = queue.poll();
			for (int i = 0; i < primes.length; i++) {
				int factor = primes[i];
				if (!set.contains(cur * factor)) {
					set.add(cur * factor);
					queue.offer(cur * factor);
				}
			}
			n--;
		}
		return queue.peek();
	}

	/*
	 * Time: O(n * k) k is the length of primes.length() == k
	 */
	public static int task313_nthSuperUglyNumber2(int n, int[] primes) {
		int[] index = new int[primes.length];
		int[] next = new int[primes.length];
		int[] ugly = new int[n];
		Arrays.fill(next, 1);
		System.out.println(Arrays.toString(next));
		for (int i = 0; i < n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j : next)
				min = Math.min(min, j);
			ugly[i] = min;
			for (int j = 0; j < primes.length; j++) {
				if (min == next[j]) {
					next[j] = primes[j] * (ugly[index[j]++]);
				}
			}
			System.out.println("--------------------");
			System.out.println(Arrays.toString(next));
			System.out.println(Arrays.toString(index));
			System.out.println("--------------------");
		}
		System.out.println(Arrays.toString(ugly));
		return ugly[n - 1];
	}

	/*
	 * Time: O((log (k)) * n )
	 */
	public static int task313_nthSuperUglyNumber3(int n, int[] primes) {
		int[] ugly = new int[n];
		PriorityQueue<Num> pq = new PriorityQueue<Num>();
		for (int i = 0; i < primes.length; i++) {
			pq.offer(new Num(primes[i], 1, primes[i]));
		}
		ugly[0] = 1;
		for (int i = 1; i < n; i++) {
			ugly[i] = pq.peek().val; // the current minimum
			while (pq.peek().val == ugly[i]) {
				// generate the next element
				Num next = pq.poll();
				pq.offer(new Num(next.prime * ugly[next.index], next.index + 1,
						next.prime));
			}
		}
		return ugly[n - 1];
	}

	private static class Num implements Comparable<Num> {
		int val;
		int index;
		int prime;

		public Num(int _val, int _index, int _prime) {
			this.val = _val;
			this.index = _index;
			this.prime = _prime;
		}

		@Override
		public int compareTo(Num o) {
			// TODO Auto-generated method stub
			if (this.val == o.val) {
				return 0;
			}
			return this.val < o.val ? -1 : 1;
		}
	}

	/*
	 * 314. Binary Tree Vertical Order Traversal
	 */

	public static List<List<Integer>> task314_verticalOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		task314_getTreeMap(root, map, 0);

		// traverse the treeMap and get result
		for (int i = map.firstKey(); i <= map.lastKey(); i++) {
			List<Integer> list = map.get(i);
			result.add(list);
		}
		return result;
	}

	public static void task314_getTreeMap(TreeNode node,
			TreeMap<Integer, ArrayList<Integer>> map, int horizonDistance) {
		if (node == null) {
			return;
		}
		
		if (!map.containsKey(horizonDistance)) {
			map.put(horizonDistance, new ArrayList<Integer>());
		}
		map.get(horizonDistance).add(node.val);
		
		task314_getTreeMap(node.left, map, horizonDistance - 1);
		task314_getTreeMap(node.right, map, horizonDistance + 1);
	}

	public static List<List<Integer>> task314_verticalOrder2(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) {
			return res;
		}
		
		Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
		Queue<Integer> qCol = new LinkedList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		qCol.offer(0);
		
		// BFS
		while (!queue.isEmpty()) {
			TreeNode curr = queue.poll();
			int col = qCol.poll();
			if (!map.containsKey(col)) {
				map.put(col, new ArrayList<Integer>(Arrays.asList(curr.val)));
			} else {
				map.get(col).add(curr.val);
			}
			if (curr.left != null) {
				queue.offer(curr.left);
				qCol.offer(col - 1);
			}
			if (curr.right != null) {
				queue.offer(curr.right);
				qCol.offer(col + 1);
			}
		}
		
		for (int n : map.keySet()) {
			res.add(map.get(n));
		}
		return res;
	}

	/*
	 * task315 You are given an integer array nums and you have to return a new
	 * counts array. The counts array has the property where counts[i] is the
	 * number of smaller elements to the right of nums[i].
	 * 
	 * Segment Tree
	 * 
	 * See task315 In the same file
	 */
	public static void test315() {

	}

	/*
	 * 316. Remove Duplicate Letters Unsorted
	 * https://leetcode.com/discuss/75738/
	 * java-solution-using-stack-with-comments
	 */

	public String removeDuplicateLetters(String s) {
		int[] res = new int[26]; // will contain number of occurrences of
									// character (i+'a')
		boolean[] visited = new boolean[26]; // will contain if character ('a' +
												// i) is present in current
												// result Stack
		char[] ch = s.toCharArray();
		for (char c : ch) { // count number of occurences of character
			res[c - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		; // answer stack
		int index;
		for (char c : ch) {
			index = c - 'a';
			res[index]--; // decrement number of characters remaining in the
							// string to be analysed
			if (visited[index]) // if character is already present in stack,
								// dont bother
				continue;
			// if current character is smaller than last character in stack
			// which occurs later in the string again
			// it can be removed and added later e.g stack = bc remaining string
			// abc then a can pop b and then c
			while ((sb.length() > 0) && c < sb.charAt(sb.length() - 1)
					&& res[sb.charAt(sb.length() - 1) - 'a'] != 0) {
				visited[sb.charAt(sb.length() - 1) - 'a'] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append(c); // add current character and mark it as visited
			visited[index] = true;
		}

		return sb.toString();

	}

	/*
	 * 317. Shortest Distance from All Buildings
	 * https://leetcode.com/problems/shortest-distance-from-all-buildings/ see
	 * task317_in the same file
	 */
	public static void test317() {
		
	}

	/*
	 * 318. Maximum Product of Word Lengths
	 * https://leetcode.com/problems/maximum-product-of-word-lengths/
	 * 
	 * Bit Vector the strings only contains 'a'-'z' we can use a 32bit Integer
	 * to mark the occurrence of char in a string. 0 bit stands for 'a', 1st bit
	 * stands for 'b', 25th bit stands for 'z' for every string, we have a
	 * integer. and put them into a int[].
	 * 
	 * for two strings, str_i, str_j, we can compare check[str_i]& check[str_j]
	 * == 0, they don't have any common chars otherwise, they have common chars.
	 * 
	 * then do 2 loop, to compare every pair of strings.
	 */
	public static int task318_maxProduct(String[] words) {
		// sanity check
		if (words == null || words.length == 0) {
			return 0;
		}
		int len = words.length;
		int[] checker = new int[len];
		for (int i = 0; i < len; i++) {
			int num = 0;
			String cur = words[i];
			for (int j = 0; j < cur.length(); j++) {
				num |= 1 << (cur.charAt(j) - 'a');
			}
			checker[i] = num;
		}
		int max = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if ((checker[i] & checker[j]) == 0) {
					max = Math.max(max, words[i].length() * words[j].length());
				}
			}
		}
		return max;

	}

	/*
	 * 320. Generalized Abbreviation
	 * https://leetcode.com/problems/generalized-abbreviation/
	 */
	public static void test320() {
		String word = "word";
		List<String> result = generateAbbreviations(word);
		System.out.println(result);
	}

	public static List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
		helper(word, result, 0, "", 0);
		return result;
	}

	public static void helper(String word, List<String> result, int pos,
			String cur, int count) {
		if (pos == word.length()) {
			if (count > 0) {
				cur += count;
			}
			result.add(cur);
			return;
		} else {
			// treat the current char as a counter
			helper(word, result, pos + 1, cur, count + 1);
			// treat the current char as char
			helper(word, result, pos + 1,
					cur + (count > 0 ? count : "") + word.charAt(pos), 0);
		}

	}

	/*
	 * 321. Create Maximum Number
	 * https://leetcode.com/problems/create-maximum-number/ Greedy
	 */
	public static void test321() {

		int k = 21;
		int[] nums1 = { 1, 6, 5, 4, 7, 3, 9, 5, 3, 7, 8, 4, 1, 1, 4 };
		int[] nums2 = { 4, 3, 1, 3, 5, 9 };

		int[] maxNumArray = task321_maxNumber(nums1, nums2, k);
		System.out.println(Arrays.toString(maxNumArray));

		int[] maxNumArray2 = maxNumber(nums1, nums2, k);
		System.out.println(Arrays.toString(maxNumArray2));
	}

	public static int[] task321_maxNumber(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] maxNumArray = new int[k];

		for (int la = 0; la <= k && la <= len1; la++) {
			int lb = k - la;

			if (lb > len2) { // we cannot compose a
				continue;
			}
			int[] array1 = task321_createArray(nums1, la);
			int[] array2 = task321_createArray(nums2, lb);

			int[] curNumArray = task321_merge(array1, array2);

			System.out.println("----------------------------------");
			System.out.println("array1 = " + Arrays.toString(array1));
			System.out.println("array2 = " + Arrays.toString(array2));
			System.out.println("merged = " + Arrays.toString(curNumArray));
			System.out.println("----------------------------------");

			if (isGreater(maxNumArray, 0, curNumArray, 0)) {
				continue;
			}
			maxNumArray = curNumArray;
		}
		return maxNumArray;

	}

	public static int[] task321_createArray(int[] num, int k) {
		int[] array = new int[k];
		int max, max_index;
		int start = 0;
		for (int i = 0; i < k; i++) {
			max = num[start];
			max_index = start;
			for (int j = start; j <= num.length - k + i; j++) {
				if (max < num[j]) {
					max = num[j];
					max_index = j;
				}
			}
			array[i] = max;
			start = max_index + 1; // the next start
		}
		return array;
	}

	public static int[] task321_merge(int[] array1, int[] array2) {
		int len1 = array1.length;
		int len2 = array2.length;
		int index = 0;
		int[] result = new int[len1 + len2];
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (array1[i] > array2[j]) {
				result[index++] = array1[i++];
			} else if (array2[j] > array1[i]) {
				result[index++] = array2[j++];
			} else {

				if (isGreater(array1, i, array2, j)) {
					result[index++] = array1[i++];
				} else {
					result[index++] = array2[j++];
				}
			}
		}
		while (i < len1) {
			result[index++] = array1[i++];
		}
		while (j < len2) {
			result[index++] = array2[j++];
		}

		return result;
	}

	public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] ans = new int[k];

		for (int lena = 0; lena <= k && lena <= nums1.length; lena++) {
			int lenb = k - lena;
			if (lenb > nums2.length)
				continue;
			int neo[] = merge(createArray(nums1, lena),
					createArray(nums2, lenb));

			if (isGreater(ans, 0, neo, 0))
				continue;
			ans = neo;
		}

		return ans;
	}

	public static int[] createArray(int[] num, int k) {
		int neo[] = new int[k];
		int start = 0, max, max_index;
		for (int i = 0; i < k; i++) {
			max = num[start];
			max_index = start;
			for (int j = start; j <= num.length - k + i; j++)
				if (num[j] > max) {
					max = num[j];
					max_index = j;
				}
			start = max_index + 1;
			neo[i] = max;
		}
		return neo;
	}

	public static int[] merge(int[] a, int[] b) {
		int la = a.length;
		int lb = b.length;
		int k = la + lb;

		int[] neo = new int[k];
		int tot = 0;
		int i = 0, j = 0;
		for (; i < la && j < lb;) {
			if (a[i] > b[j])
				neo[tot++] = a[i++];
			else if (a[i] < b[j])
				neo[tot++] = b[j++];
			else {
				if (isGreater(a, i, b, j))
					neo[tot++] = a[i++];
				else
					neo[tot++] = b[j++];
			}
		}

		while (i < a.length)
			neo[tot++] = a[i++];
		while (j < b.length)
			neo[tot++] = b[j++];

		return neo;
	}

	public static boolean isGreater(int[] a, int startA, int[] b, int startB) {
		for (; startA < a.length && startB < b.length; startA++, startB++) {
			if (a[startA] > b[startB])
				return true;
			if (a[startA] < b[startB])
				return false;
		}
		return startA != a.length;
	}

	/*
	 * 322 Coin Change DP 
	 * M[i] stands the minimum number of coins that compose amount = i;
	 * 
	 * M[i] = M[j] + 1 ( j <= i && M[j] is valid)
	 */
	public static void test322() {
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		int rev = task322_coinChange(coins, amount);
		System.out.println("rev = " + rev);
	}

	public static int task322_coinChange(int[] coins, int amount) {
		// sanity check
		if (coins == null || coins.length == 0 || amount < 0) {
			return -1;
		}
		int[] M = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			M[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i && M[i - coins[j]] != Integer.MAX_VALUE) {
					M[i] = Math.min(M[i], M[i - coins[j]] + 1);
				}
			}
		}

		if (M[amount] != Integer.MAX_VALUE) {
			return M[amount];
		} else {
			return -1;
		}
	}

	/*
	 * 323. Number of Connected Components in an Undirected Graph
	 * https://leetcode
	 * .com/problems/number-of-connected-components-in-an-undirected-graph/
	 * 
	 * See task323 in the same directory
	 */
	public static void test323() {

	}

	/*
	 * 324. Wiggle Sort II https://leetcode.com/problems/wiggle-sort-ii/ 
	 * Given
	 * an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2]
	 * < nums[3]....
	 * 
	 * More strickly then task280 This is similar that let the result as {3, -1,
	 * 2, -5, 1, -9}
	 * 
	 * first, find the median using the idea of quickSort.
	 */
	
	public static void test324() {
		int[] array = { 1, 5, 1, 1, 6, 4 };
		task324_wiggleSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void task324_wiggleSort(int[] array) {
		// sanity check
		if (array == null || array.length <= 1) {
			return;
		}
		if (array.length == 2) {
			if (array[0] > array[1]) {
				swap(array, 0, 1);
			}
		}

		int len = array.length;
		quickSelect(array, 0, len - 1, (len - 1) / 2);

		System.out.println(Arrays.toString(array));
		System.out.println("--------------------");
		
		int ptr1 = (len - 1) / 2, ptr2 = len - 1;
		
		System.out.println("ptr1 = " + ptr1);
		System.out.println("ptr2 = " + ptr2);
		
		int[] temp = new int[len];
		int median = array[(len - 1) / 2];
		int median_index = (len - 1) / 2;
		int start = 0, end = len - 1;

		/*
		 * swap median together 
		 */
		for(int i = median_index + 1, j = array.length - 1; i < j;) {
			if (array[j] == median) {
				swap(array, i, j);
				i ++;
				j --;
			} else {
				j --;
			}
		}
		
		System.out.println("median = " + median);
		System.out.println("median_index = " + median_index);
		
		// re-organize the array. [ smaller, median, larger ]
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= median) {
				temp[start++] = array[i];
			} else if (array[i] > median) {
				temp[end--] = array[i];
			}
		}
		
		
		while (start <= median_index)
			temp[start++] = median;
		while (end > median_index)
			temp[end--] = median;
		
		System.out.println(Arrays.toString(temp));
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		System.out.println("start = " + start);
		System.out.println("end = " + end);

		System.out.println(Arrays.toString(temp));

		int i = 0;
		for (; i < len; i++) {
			if (i % 2 == 0) {
				array[i] = temp[ptr1--];
			} else {
				array[i] = temp[ptr2--];
			}
		}
	}
	
	
	public static void test324_1() {
		int[] array = {1,5,4,3,1,4,4,8,9,4,4};
		System.out.println(Arrays.toString(array));
		int len = array.length;
		quickSelect(array, 0, len - 1, (len - 1)/2);
		System.out.println(Arrays.toString(array));
		int medianIdx = (len - 1)/2;
		int median = array[medianIdx];
		System.out.println("median = " + median);
		System.out.println("medianIdx = " + medianIdx);
	}
	
	/*
	 * 找第k大的数
	 */
	public static void quickSelect(int[] array, int start, int end, int k) {
		int pivotIndex = partition(array, start, end);
		if (pivotIndex == k) {
			return;
		} else if (pivotIndex > k) { // k is in 0..pivotIndex - 1
			quickSelect(array, start, pivotIndex - 1, k);
		} else { // k is in pivotIndex + 1, end
			quickSelect(array, pivotIndex + 1, end, k);
		}
	}

	public static int partition(int[] array, int left, int right) {
		int pivot = array[right]; // choose the last element as pivot
		int start = left, end = right - 1;
		while (start <= end) {
			if (array[start] < pivot) {
				start++;
			} else if (array[end] >= pivot) {
				end--;
			} else {
				swap(array, start, end);
				start++;
				end--;
			}
		}
		// swap the pivot back to its position.
		swap(array, start, right);
		// left is the pivot index
		return start;
	}

	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	/*
	 * 325. Maximum Size SubArray Sum Equals k HashTable
	 * Time: O(n)
	 * Use a HashMap to keep track of the sum from index 0 to index i, use it as
	 * the key, and use the current index as the value build the hashmap: 
	 * scan
	 * from left to write, if the current sum does not exist in the hashmap, put it in. 
	 * 
	 * If the current sum does exist in HashMap, do not replace or add to
	 * the older value, simply do not update. Because this value might be the
	 * left index of our subArray in later comparison. We are looking for the
	 * longest subArray so we want the left index to be the smaller the better.
	 * Every time we read a number in the array,
	 * 
	 * we check to see if map.containsKey(num-k), if yes, try to update the
	 * maxLen.
	 */
	public static int task325_maxSubArrayLen(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0;
		int maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
			if (map.containsKey(sum - k)) {
				int index = map.get(sum - k);
				maxLen = Math.max(maxLen, i - index);
			}
		}
		return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
	}

	/*
	 * 326. Power of Three https://leetcode.com/problems/power-of-three/ 换底公式
	 */
	public static void test326() {
		for (int i = 3; i < 1000; i *= 3) {
			System.out.println(Integer.toBinaryString(i));
		}
	}

	public static boolean task326_isPowerOfThree(int n) {
		return (Math.log10(n) / Math.log10(3)) % 1 == 0;
	}

	/*
	 * 327. Count of Range Sum 
	 * https://leetcode.com/problems/count-of-range-sum/
	 * 
	 * Given an integer array nums, return the number of range sums that lie in
	 * [lower, upper] inclusive. Range sum S(i, j) is defined as the sum of the
	 * elements in nums between indices i and j (i ≤ j), inclusive.
	 * 
	 * Given nums = [-2, 5, -1], lower = -2, upper = 2, Return 3. 
	 * The three ranges are : [0, 0], [2, 2], [0, 2] and 
	 * their respective sums are: -2, -1, 2.
	 */
	public static void test327() {
		int[] nums = { 0, -3, -3, 1, 1, 2 };
		int rev1 = task327_countRangeSum(nums, 3, 5);
		System.out.println(" rev1 = " + rev1);
	}

	public static int task327_countRangeSum(int[] nums, int lower, int upper) {
		SegmentNode root = build(nums);
		traverse(root, lower, upper);
		levelOrderPrint(root);
		return counter;
	}

	public static class SegmentNode {
		int start;
		int end;
		int sum;
		SegmentNode left, right;

		public SegmentNode(int _s, int _e, int _sum) {
			this.start = _s;
			this.end = _e;
			this.sum = _sum;
		}

		@Override
		public String toString() {
			return "[ " + start + " , " + end + " ]" + " sum= " + sum;

		}
	}

	public static SegmentNode build(int[] array) {
		return buildHelper(array, 0, array.length - 1);
	}

	public static SegmentNode buildHelper(int[] array, int left, int right) {
		if (left > right) {
			return null;
		}
		SegmentNode root = new SegmentNode(left, right, 0);
		if (left == right) {
			root.sum = array[left];
		} else {
			int mid = left + (right - left) / 2;
			root.left = buildHelper(array, left, mid);
			root.right = buildHelper(array, mid + 1, right);

			root.sum = root.left.sum + root.right.sum;
		}
		return root;
	}

	public static int counter = 0;

	public static void traverse(SegmentNode root, int lower, int upper) {
		if (root == null) {
			return;
		}
		if (root.sum >= lower && root.sum <= upper) {
			counter++;
		}
		traverse(root.left, lower, upper);
		traverse(root.right, lower, upper);
	}

	public static void levelOrderPrint(SegmentNode root) {
		if (root == null) {
			return;
		}
		LinkedList<SegmentNode> q = new LinkedList<SegmentNode>();
		q.offer(root);

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				SegmentNode cur = q.poll();
				// print cur's content
				System.out.print(cur.toString() + " ");

				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
			System.out.println();
		}
	}

	/*
	 * 328 Odd Even Linked List Given 1->2->3->4->5->NULL, return
	 * 1->3->5->2->4->NULL.
	 */
	public static ListNode task328_oddEvenList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		ListNode dummy_odd = new ListNode(-1);
		ListNode tail_odd = dummy_odd;
		ListNode dummy_even = new ListNode(-1);
		ListNode tail_even = dummy_even;

		ListNode cur = head;
		int count = 1;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = null;
			if (count % 2 == 1) {
				tail_odd.next = cur;
				tail_odd = tail_odd.next;
			} else {
				tail_even.next = cur;
				tail_even = tail_even.next;
			}
			count++;
			cur = next;
		}

		tail_odd.next = dummy_even.next;
		return dummy_odd.next;

	}

	/*
	 * 329 Longest Increasing Path in a Matrix refer to jiuzhangAdvanceAlgorithm
	 * dp
	 */

	/*
	 * 330 Patching Array
	 */

	/*
	 * Iterating the nums[], while keep adding them up, and we are getting a
	 * running sum starting from 0. At any position i, if nums[i] > sum+1, then
	 * we are sure we have to patch a (sum+1) because all nums before index i
	 * can't make sum+1 even adding all of them up, and all nums after index i
	 * are all simply too large. Since the sum is growing from 0, we also can be
	 * sure that any number equal or smaller than the current sum is covered. In
	 * the worst case, the code will go thru all the numbers in the array before
	 * the sum goes doubling itself towards n. Therefore, the time is
	 * O(k+log(n)) where k being the size of the array and n being the target n
	 * to sum up. Thanks @StefanPochmann for pointing it out. Here is the
	 * accepted code,
	 */
	public static void test330() {
		int[] nums = {};
		int n = 6;
		int rev = task330_minPatches(nums, n);
		System.out.println("rev = " + rev);
	}

	public static int task330_minPatches(int[] nums, int n) {
		int count = 0;
		long sum = 0;
		if (nums == null || nums.length == 0) {
			while (sum < n) {
				sum = sum + sum + 1;
				count++;
			}
			return count;
		}

		for (int i = 0; i < nums.length; i++) {
			if (sum >= n) {
				break;
			}
			while (sum + 1 < nums[i] && sum < n) {
				// patch count ++
				count++;
				// patch sum + 1
				sum += sum + 1;
			}
			sum += nums[i];
		}

		while (sum < n) {
			count++;
			sum += sum + 1;
		}
		return count;
	}

	/*
	 * task331 Verify Preorder Serialization of a Binary Tree
	 */
	 public static boolean task331_isValidSerialization(String preorder) {
	        if (preorder == null || preorder.length() == 0) {
	            return false;
	        }
	        String[] input = preorder.split(",");
	        LinkedList<String> st = new LinkedList<String>();
	        
	        if (input.length == 1 && input[0].equals("#")) {
	            return true;
	        }
	        st.offerFirst(input[0]);
	        for (int i = 1; i < input.length; i++) {
	            String cur = input[i];
	            if(!cur.equals("#")) {
	                st.offerFirst(cur);
	            } else {
	                // cur == "#"
	               
	            	// make sure that the firstPop is "#" and the secondPop is NOT "#"
	                while(st.size() >= 2 && st.peekFirst().equals("#")) {
	                    
	                	st.pollFirst();
	                    if(st.peekFirst().equals("#")) {
	                        return false;
	                    }
	                    st.pollFirst();
	                }
	                st.offerFirst("#");
	            }
	        }
	        
	        return st.size() == 1 && st.peekFirst().equals("#");
	    }

	public static void preOrder(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			System.out.println(cur.val);
			if (cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			}
		}
	}

	public static void preOrder1_1(TreeNode root) {
		List<String> preOrder = new ArrayList<String>();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			preOrder.add(Integer.toString(cur.val));

			if (cur.right != null) {
				stack.offerFirst(cur.right);
			} else {
				preOrder.add("#");
			}
			if (cur.left != null) {
				stack.offerFirst(cur.left);
			} else {
				preOrder.add("#");
			}
		}
		System.out.println(preOrder);
	}

	/*
	 * 1 / \ 2 3 / \ / \ 4 5 # # / \ /\ # # # 6 / \ # #
	 */

	public static void preOrder2(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.offerFirst(cur);
				System.out.println(cur.val);
				cur = cur.left;
			} else {
				// cur == null
				cur = stack.pollFirst();
				cur = cur.right;
			}
		}
	}

	public static void test331() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		n1.left = n2;
		n1.right = n3;

		n2.left = n4;
		n2.right = n5;
		n5.right = n6;

		preOrder(n1);
		System.out.println("--------");
		preOrder2(n1);
		System.out.println("--------");
		preOrder1_1(n1);
	}
	
	
	
	/*
	 * 332 Reconstruct Itinerary
	 */
	public static void test332() {
		String[][] tickets = {
				{"MUC", "LHR"},
				{"JFK", "MUC"},
				{"SFO", "SJC"},
				{"LHR", "SFO"}
		};
		String[][] tickets2 = {
				{"JFK","SFO"},
				{"JFK","ATL"},
				{"SFO","ATL"},
				{"ATL","JFK"},
				{"ATL","SFO"}
		};
		
		List<String> rev = task332_findItinerary(tickets);
		System.out.println(rev);
	}
	

	public static List<String> task332_findItinerary(String[][] tickets) {
		List<String> path = new ArrayList<String>();
		Arrays.sort(tickets, myComp);
		List<String> result = new ArrayList<String>();
		boolean[] visited = new boolean[tickets.length];
		
		task332_itinerary(tickets, path, visited, result);
		
		return result;
	}
	
	public static Comparator<String[]> myComp = new Comparator<String[]>() {
		
		@Override
		public int compare(String[] o1, String[] o2) {
			// TODO Auto-generated method stub
			if (o1[0].equals(o2[0])) {
				return o1[1].compareTo(o2[1]);
			}
			return o1[0].compareTo(o2[0]);
		}
	};
	public static boolean found = false;
	public static void task332_itinerary(String[][] tickets, List<String> path, 
			boolean[] visited, List<String> result) {
		if (found) {
			return ;
		}
		if (path.size() == tickets.length + 1) {
			
			System.out.println(path);
			for(int i = 0; i < path.size(); i++) {
				result.add(path.get(i));
			}
			found = true;
			return ;
		} 
		if (path.isEmpty()) {
			// get the first one
			for(int i = 0; i < tickets.length; i ++) {
				if (visited[i] == false && tickets[i][0].equals("JFK")) {
					visited[i] = true;
					path.add(tickets[i][0]);
					path.add(tickets[i][1]);
					task332_itinerary(tickets, path, visited, result);
					visited[i] = false;
					path.remove(path.size() - 1);
					path.remove(path.size() - 1);
				}
			}
		} else {
			// path is NOT empty
			for(int i = 0; i < tickets.length; i ++) {
				if (visited[i] == false && tickets[i][0].equals(path.get(path.size() - 1))) {
					visited[i] = true;
					path.add(tickets[i][1]);
					task332_itinerary(tickets,  path, visited, result);
					visited[i] = false;
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	
	
	
	/*
	 * task333
	 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
	 * where largest means subtree with largest number of nodes in it.
	 * return the size of the largest number of nodes in it. 
	 */
	public static void test333() {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(5);
		TreeNode n3 = new TreeNode(15);
		n1.left = n2;
		n1.right = n3;
		
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(8);
		n2.left = n4;
		n2.right = n5;
		
		TreeNode n6 = new TreeNode(20);
		n3.right = n6;
		
		int result = task333_largestBSTSubtree(n1);
		System.out.println("result = " + result);
	}	
	
	public static int task333_largestBSTSubtree(TreeNode root) {
		if(root == null) return 0;
		Element result = task333_helper(root);
		return result.nums;
	}
	public static class Element{
		int min;
		int max;
		int nums;
		boolean isBSt;
		public Element(int _min, int _max, int _nums, boolean _isBst) {
			this.min = _min;
			this.max = _max;
			this.nums = _nums;
			this.isBSt = _isBst;
		}
	}
	
	public static Element task333_helper(TreeNode node) {
		if (node == null) {
			return null;
		}
		Element left = task333_helper(node.left);
		Element right = task333_helper(node.right);
		if (left == null && right == null) {
			// this is a leaf node
			return new Element(node.val, node.val, 1, true);
		} else if (left == null) {
			if (right.isBSt == true && node.val < right.min) {
				return new Element(node.val, right.max, right.nums + 1, true);
			} else {
				// right isn't a BST nor node.val >= right.min
				return new Element(right.min, right.max, right.nums, false);
			}
		} else if (right == null) {
			if (left.isBSt == true && node.val > left.max) {
				return new Element(left.min, node.val, left.nums + 1, true);
			} else {
				return new Element(left.min, left.max, left.nums, false);
			}
		} else {
			// left != null && right != null
			if (left.isBSt && right.isBSt && node.val > left.max && node.val < right.min) {
				return new Element(left.min, right.max, left.nums + right.nums + 1, true);
			} else {
				return new Element(left.min, left.max, Math.max(left.nums, right.nums), false);
			}
		}
	}
	
	 
	/*
	 * task334
	 * 
	 */
	
	// Time: O(n)
	// space: O(n)
	public static boolean task334_increasingTriplet_v1(int[] nums) {
		if (nums == null || nums.length < 3) {
			return false;
		}
		int n = nums.length;
		int[] smaller = new int[n];
		int[] larger = new int[n];
		
		smaller[0] = Integer.MAX_VALUE;
		int smallest = Integer.MAX_VALUE;
		for(int i = 1; i < n; i ++) {
			smallest = Math.min(smallest, nums[i - 1]);
			smaller[i] = smallest;
		}
		
		larger[n - 1] = Integer.MIN_VALUE;
		int largest = Integer.MIN_VALUE;
		for(int i = n - 2; i >= 0; i --) {
			largest = Math.max(largest, nums[i + 1]);
			larger[i] = largest;
		}
		
		for(int i = 0; i < n; i ++) {
			if (smaller[i] < nums[i] && nums[i] < larger[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean task334_increasingTriplet_v2(int[] nums) {
		if (nums == null || nums.length < 3) {
			return false;
		}
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		
		for(Integer i : nums) {
			if(i <= min1) {
				min1 = i;
			} else { // i > min1
				if (i <= min2)  // i > min1 && i <= min2
					min2 = i;
				else { // i > min1 && i > min2
					return true;
				}
			} 
		}
		return false;
	}
	
	public static boolean task334_increasingTriplet_v3(int[] nums) {
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
	
	/*
	 * 335 Self Crossing
	 * // Categorize the self-crossing scenarios, there are 3 of them: 
	 * // 1. Fourth line crosses first line and works for fifth line crosses second line and so on...
	 * // 2. Fifth line meets first line and works for the lines after
	 * // 3. Sixth line crosses first line and works for the lines after
	 */
	public static boolean task335_isSelfCrossing(int[] x) {
		int l = x.length;
		if (l <= 3)
			return false;

		for (int i = 3; i < l; i++) {
			if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3])
				return true; // Fourth line crosses first line and onward
			if (i >= 4) {
				if (x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2])
					return true; // Fifth line meets first line and onward
			}
			if (i >= 5) {
				if (x[i - 2] - x[i - 4] >= 0 && x[i] >= x[i - 2] - x[i - 4]
						&& x[i - 1] >= x[i - 3] - x[i - 5]
						&& x[i - 1] <= x[i - 3])
					return true; // Sixth line crosses first line and onward
			}
		}
		return false;
	}
	
	
	/*
	 * task336
	 * input: a list of UNIQUE words
	 * 
	 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
	 * so that the concatenation of the two words, 
	 * i.e. words[i] + words[j] is a palindrome.
	 * 
	 * time: O(n*m) m is the average length of strings in string array
	 * 
	 */
	public static void test336() {
		String[] words = {"bat", "tab", "cat"};
		String[] words2 = {"abcd", "dcba", "lls", "s", "sssll"};
		String[] words3 = {"a", "abc", "aba", ""};
		
		List<List<Integer>> result = task336_palindromePairs(words3);
		System.out.println(result);
	}
	
	
	/*
	 * there are several cases to be considered that is Palindrome(s1 + s2)
	 * 1: if s1 is a blank string, then any string that is palindrom s2 --> s1 + s2, s2 + s1 are palindrome
	 * 2: if s2 is the reverse string of s1, then s1 + s2 and s2 + s1 are palindrome
	 * 3: if s1[0..i] is palindrome && there the s1[i + 1.. n -1] is the reverse string of s2 --> s2 + s1 is palindrome
	 * 4: similar to 3, 
	 *    if s1[i + 1, n] is palindrome and s1[0..i] is the reverse string os s2 --> s1 + s2 is palindrome
	 * 
	 * To make things faster, use hashMap to store string and its index
	 */
	public static List<List<Integer>> task336_palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (words == null || words.length == 0) {
			return result;
		}
		// map: key-value: String -index
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// map<String, index in the array>
		for(int i = 0; i < words.length; i ++) {
			map.put(words[i], i);
		}
		
		// deal with case1:
		if (map.containsKey("")) {
			int blankIdx = map.get("");
			// traverse the String[] words, find the palindrome
			for (int i = 0; i < words.length; i++) {
				if (task336_isPalindrom(words[i])) {
					if (i == blankIdx) {
						continue;
					}
					result.add(Arrays.asList(blankIdx, i));
					result.add(Arrays.asList(i, blankIdx));
				}
			}
		}
		
		// case2: find all string and reverse string pairs
		for(int i = 0; i < words.length; i++) {
			String curStr = words[i];
			String curStrR = new StringBuilder(curStr).reverse().toString();
			if (map.containsKey(curStrR)) {
				int indexR = map.get(curStrR);
				// make sure that indexR != i
				if (indexR == i) {
					continue;
				}
				result.add(Arrays.asList(i, indexR));
			}
		}
		
		
		// case3  s1[0..i] is palindrome && s1[i + 1, n - 1] == reverse(s2) ---> (s2, s1)
		// case4  s1[i + 1..n - 1] is palindrome && s1[0..i] == reverse(s2) ---> (s1, s2)
		for (int i = 0; i < words.length; i ++) {
			String cur = words[i];
			for (int j = 1; j < cur.length(); j ++) {
				String curLeft = cur.substring(0, j);   // the cut index is j  !!!
				String curRight = cur.substring(j);
				
				if (task336_isPalindrom(curLeft)) {
					String curRightR = new StringBuilder(curRight).reverse().toString();
					if (map.containsKey(curRightR)) {
						int curRightRIdx = map.get(curRightR);
						if (curRightRIdx == i) {
							continue;
						}
						result.add(Arrays.asList(curRightRIdx, i));
					}
				}
				
				if (task336_isPalindrom(curRight)) {
					String curLeftR = new StringBuilder(curLeft).reverse().toString();
					if (map.containsKey(curLeftR)) {
						int curLeftRIdx = map.get(curLeftR);
						if (curLeftRIdx == i) {
							continue;
						}
						result.add(Arrays.asList(i, curLeftRIdx));
					}
				}
			}
		}	
		return result;
	}
		
	public static boolean task336_isPalindrom(String input) {
		int left = 0, right = input.length() -1;
		while(left <= right) {
			if (input.charAt(left) != input.charAt(right)) {
				return false;
			}
			left ++;
			right --;
		}
		return true;
	}
	
	/*
	 * task337
	 * House robber III
	 * get the sum of each layer
	 * and reduce to house robber I
	 * get the maximum sum not adjacent from the array
	 * 
	 * this is wrong
	 */
	
	public static void test337() {
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		
		int maxSum = task337_rob3(n1);
		System.out.println("maxSum = " + maxSum);
		
	}
	/*
	public static int task337_rob3(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		helper(root, list, 0);
		System.out.println(list);
		if (list.size() <= 0) {
			return 0;
		}
		if (list.size() == 1) {
			return root.val;
		}
		if (list.size() == 2) {
			return Math.max(list.get(0), list.get(1));
		}
		// get the maxSum from array, no adjacent elements can be selected
		int len = list.size();
		// M[i] is the max Sum in array[0..i]
		// M[i] = Math.max(M[i - 2] + array[i], M[i - 1])
		int[] M = new int[len];
		M[0] = list.get(0);
		M[1] = Math.max(list.get(0), list.get(1));
		
		for(int i = 2; i < len; i ++) {
			M[i] = Math.max(M[i - 1], M[i - 2] + list.get(i));
		}
		
		System.out.println(Arrays.toString(M));
		return M[len - 1];
		
	}
	
	public static void helper(TreeNode node, ArrayList<Integer> list, int level) {
		if (node == null) {
			return ;
		}
		if (level == list.size()) {
			list.add(node.val);
		} else {
			int curSum = list.get(level);
			list.set(level, curSum + node.val);
		}
		helper(node.left, list, level + 1);
		helper(node.right, list, level + 1);
		
	}
	*/
	
	/*
	 * https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
	 */
	
	public static int task337_rob3(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int val = 0;
		if (root.left != null) {
			val += task337_rob3(root.left.left) + task337_rob3(root.left.right);
		}
		if (root.right != null) {
			val += task337_rob3(root.right.left) + task337_rob3(root.right.right);
		}
		return  Math.max(val + root.val, task337_rob3(root.left) + task337_rob3(root.right));
	}
	
	public static int task337_rob3_opt(TreeNode root) {
		HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
		return task337_rob3_optH(root, map);
	}
	public static int task337_rob3_optH(TreeNode node, HashMap<TreeNode, Integer> map) {
		if (node == null) {
			return 0;
		}
		if (map.containsKey(node)) {
			return map.get(node);
		}
		int val = 0;
		if (node.left != null) {
			val += task337_rob3_optH(node.left.left, map) + task337_rob3_optH(node.left.right, map);
		}
		if (node.right != null) {
			val += task337_rob3_optH(node.right.left, map) + task337_rob3_optH(node.right.right, map);
		}
		val = Math.max(val + node.val, task337_rob3_optH(node.left, map) + task337_rob3_optH(node.right, map));
		map.put(node, val);
		return val;
	}
	
	
	/*
	 * analyze !!!
	 */
	public static int task337_rob3_opt2(TreeNode root) {
		int[] res = robSub(root);
	    return Math.max(res[0], res[1]);
	}
	
	private static int[] robSub(TreeNode root) {
	    if (root == null) {
	    	return new int[2];
	    }
	    
	    int[] left = robSub(root.left);
	    int[] right = robSub(root.right);
	    
	    int[] res = new int[2];
	    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	    res[1] = root.val + left[0] + right[0];
	    
	    return res;
	}
	
	
	/*
	 * task338
	 * Counting Bits
	 * to Do
	 */
	
	
	/*
	 * task339
	 * Nested List Weight Sum
	 * 
	 */
	
	
	
	
	
	
	/*
	 * task340
	 * Longest substring with at most K distinct Distinct Characters
	 * e.g
	 * s = "eceba and k = 2
	 * T is "ece" with its length is 3
	 */
	public static void test340() {
		String s = "eceba";
		int k = 2;
		int rev = task340_lengthOfLongestSubstringKDistinct(s, k);
		System.out.println("rev = " + rev);
	}
	
	public static int task340_lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int slow = 0, fast = 0;
		int maxLen = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		while(fast < s.length()) {
			char fastChar = s.charAt(fast);
			if (map.containsKey(fastChar)) {
				map.put(fastChar, map.get(fastChar) + 1);
			} else {
				// map don't contain fast char, put the fastChar into map
				map.put(fastChar, 1);
				
				// move the slow pointer, when map.size > k
				while(map.size() > k) {
					// move the slow to narrow the window
					char slowChar = s.charAt(slow);
					
					map.put(slowChar, map.get(slowChar) - 1);
					if (map.get(slowChar) == 0) {
						map.remove(slowChar);
					}
					slow ++;
				}
			}
			maxLen = Math.max(maxLen, fast - slow + 1);
			fast ++;
		}
		return maxLen;
	}
	
	
	/*
	 * task341
	 * Flatten Nested List Iterator
	 * refer to task341_Flatten_Nested_Integer
	 */
	public static void test341() {
		
	}
	
	
	
	
	
	
	/*
	 * task342 Power of Four
	 * 
	 * there is only one 1 and the tailing 0s
	 * the counter of tailing 0s is even
	 * 
	 */
	public static boolean task342_isPowerOfFour(int num) {
		if (num <= 0) {
			return false;
		}
		int counter = 0;
		while((num & 1) == 0) {
			counter ++;
			num >>>= 1;
		}
		return counter%2 == 0 && num == 1;
	}
	
	/*
	 * task343
	 * a positive integer n, break it into the sum of at least two positive integers
	 * and maximize the product of those integers. 
	 * return the maximum product you can get
	 * e.g
	 * n = 2, return 1(2 = 1 + 1)
	 * n = 10, return 36 (10 = 3 + 3 + 4)
	 * 
	 * M[i]: the max product of i
	 * M[i] = max(max(j, M[j]) * max(i - j, M[i - j]))
	 * 
	 */
	public static int task343_integerBreak(int n) {
		int[] M = new int[n + 1];
		M[0] = 0;
		M[1] = 0;
		M[2] = 1;
		for(int i = 3; i <= n; i ++) {
			int curMax = Integer.MIN_VALUE;
			for(int j = 1; j <=i/2; j++) {
				curMax = Math.max(curMax, Math.max(j, M[j]) * Math.max(i - j, M[i - j]));
			}
			M[i] = curMax;
		}
		return M[n];
	}
	
	
	
	
	/*
	 * task344
	 * Reverse String
	 */
	public static String task344_reverseString(String s) {
        if (s == null || s.length() == 0) {
			return s;
		}
		char[] arr = s.toCharArray();
		int i = 0, j = arr.length - 1;
		while(i < j) {
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i ++; 
			j --;
		}
		return new String(arr);
    }
	
	
	
	
	
	
	/*
	 * task345
	 * Reverse Vowels of a String
	 */
	public static String task345_reverseVowels(String s) {
		// Write a function that takes a string as input and reverse only the vowels of a string.
		HashSet<Character> vowels = new HashSet<Character>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		
		char[] arr = s.toCharArray();
		int i = 0, j = arr.length - 1;
		while(i < j) {
			boolean i_is_vowel = vowels.contains(Character.toLowerCase(arr[i]));
			boolean j_is_vowel = vowels.contains(Character.toLowerCase(arr[j]));
			if (i_is_vowel && j_is_vowel) {
				// swap
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i ++;
				j --;
			} else if (i_is_vowel) {
				j --;
			} else {
				i ++;
			}
		}
		return new String(arr);
	}
	
	
	
	/*
	 * task346
	 * Moving Average from data stream
	 * see task346_movingAverageFromDS
	 */
	
	
	/*
	 * task347
	 * top k frequent elements
	 * given a non-empty array of arrays, return the k most frequent element
	 * [1,1,1,2,2,3] and k = 2, return [1,2]
	 */
	public static void test347() {
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		List<Integer> result = task347_topKFrequent(nums, k);
		System.out.println(result);
	}
	
	public static List<Integer> task347_topKFrequent(int[] nums, int k) {
		List<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		// key, counter
		final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// final marks the reference, not the object. 
		// map cannot reference point to a different hashMap.
		// but we can do anython to the object
		for(int i = 0; i < nums.length; i ++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
			} else {
				map.put(nums[i], map.get(nums[i]) + 1);
			}
		}
		
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if (map.get(o1) == map.get(o2)) {
					return 0;
				}
				return map.get(o1) > map.get(o2) ? -1 : 1;
			}
		});
		
		for(Entry<Integer, Integer> entry : map.entrySet()) {
			pQueue.add(entry.getKey());
		}
		for(int i = 0; i < k; i ++) {
			result.add(pQueue.poll());
		}
		
		return result;
	}
	
	
	
	
	
	/*
	 * task349
	 * Intersection of two arrays
	 */
	public static int[] task349_intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 ||
			nums2 == null || nums2.length == 0) {
			return null;
		}
		if (nums1.length > nums2.length) {
			return task349_helper(nums1, nums2);
		} else {
			return task349_helper(nums2, nums1);
		}
    }
	public static int[] task349_helper(int[] longer, int[] shorter) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < shorter.length; i ++) {
			set.add(shorter[i]);
		}
		HashSet<Integer> set2 = new HashSet<Integer>();
		for(int i = 0; i < longer.length; i ++) {
			if (set.contains(longer[i])) {
				set2.add(longer[i]);
			}
		}
		
		int[] result = new int[set2.size()];
		int idx = 0;
		for(Integer i: set2) {
			result[idx ++] = i; 
		}
		return result;
	}
	
	/*
	 * task350
	 * Intersection of two arrays II
	 */
	public static void test350(){
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		int[] result = task350_intersect(nums1, nums2);
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] task350_intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 || nums2 == null
				|| nums2.length == 0) {
			return new int[0];
		}
		// assume nums1.length > nums2.length
		if (nums1.length < nums2.length) {
			return task350_intersect(nums2, nums1);
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums2.length; i ++) {
			int elem = nums2[i];
			if (map.containsKey(elem)) {
				map.put(elem, map.get(elem) + 1);
			} else {
				map.put(elem, 1);
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < nums1.length; i ++) {
			int elem = nums1[i];
			if (map.containsKey(elem) && map.get(elem) > 0) {
				list.add(elem);
				map.put(elem, map.get(elem) - 1);
				if (map.get(elem) == 0) {
					map.remove(elem);
				}
			}
		}
		int[] result = new int[list.size()];
		int idx = 0;
		for(int i = 0; i< list.size(); i++) {
			result[idx] = list.get(i);
			idx ++;
		}
		return result;
	}
	
	
	/*
	 * Best Time to Buy and Sell Stock with Cooldown
	 * http://buttercola.blogspot.com/2016/01/leetcode-best-time-to-buy-and-sell.html
	 * 
	 * https://segmentfault.com/a/1190000004193861
	 */
	public static int lc301(int[] prices) {
		int n = prices.length;
		int[] buy = new int[n];
		int[] sell = new int[n];
		
		buy[0] = -prices[0];
		buy[1] = Math.max(-prices[0], -prices[1]);
		sell[0] = 0;
		sell[1] = Math.max(0, prices[1] - prices[0]);
		for(int i = 2; i < n; i ++) {
			buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1]);
			sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
		}
		return sell[n - 1];
		
	}
	
	public static int lc301_2(int[] prices) {
		 if (prices == null || prices.length == 0) {
	            return 0;
	        }
	        
	        // 表示当天最终未持股的情况下，当天结束后的累计最大利润, 最后一个操作是sell
	        int[] sellDp = new int[prices.length];
	        // 表示当天最终持股的情况下，当天结束后的累计最大利润, 最后一个操作是buy
	        int[] buyDp = new int[prices.length];
	        
	        // 考虑初始情况
	        buyDp[0] = -prices[0];
	        sellDp[0] = 0;
	        for (int i = 1; i < prices.length; i++) {
	            sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
	            if (i >= 2) {
	                buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
	            } else {
	                buyDp[i] = Math.max(buyDp[i - 1], -prices[i]);
	            }
	        }
	        return sellDp[prices.length - 1];
	}
	
	public int lc301_maxProfit_opt(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
    
        int currBuy = -prices[0];
        int currSell = 0;
        int prevSell = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = currSell;
            currSell = Math.max(currSell, currBuy + prices[i]);
            if (i >= 2) {
                currBuy = Math.max(currBuy, prevSell - prices[i]);
            } else {
                currBuy = Math.max(currBuy, -prices[i]);
            }
            prevSell = temp;
        }
        return currSell;
    }
	
	
	
	
	

	
	
}
