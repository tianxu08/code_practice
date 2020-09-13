package sortbynum;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import ds.*;
import sortbynum.Task428_SerializeAndDeserializeNaryTree.Node;

public class Task401_450 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test404();
		// Task401_450 sln = new Task401_450();
//		test402();
//		test403();
//		test406();
		// test408();
		// test412();
		 test413();
		// test414();
		// test415();
		// test416();
		// sln.test407();
		// test434();
//		test436();
//		test439();
		// test449();
		// test444();
		// test474();
		// test441();
		// test442();
//		test445();
	}

	/*
	 * task401 Binary Watch
	 */
	
	/*
	 * task402
	 * 402. Remove K Digits
	 */
	
	public static void test402() {
		String num = "10200";
		int k = 1;
		String rev = task402_removeKdigits(num, k);
		System.out.println("rev = " + rev);
	}
	public static String task402_removeKdigits(String num, int k) {
		LinkedList<Character> stack = new LinkedList<>();
		for(int i = 0; i < num.length(); i++) {
			char curCh = num.charAt(i);
			while(k > 0 && !stack.isEmpty() && stack.peekFirst() > curCh) {
				stack.pollFirst();
				k --;
			}
			stack.offerFirst(curCh);
		}
		while(k > 0 && !stack.isEmpty()) {
			stack.pollFirst();
			k --;
		}
		StringBuilder stb = new StringBuilder();
		while (!stack.isEmpty()) {
			stb.append(stack.pollFirst());
		}
		while(stb.length() > 1 && stb.charAt(stb.length() - 1)=='0') {
			int lastIdx = stb.length() - 1;
			stb.deleteCharAt(lastIdx);
			stb.setLength(lastIdx);
		}
		
		return stb.length() == 0 ? "0" : stb.reverse().toString();
		
	}
	
	/*
	 * 403 Frog jump
	 * use a map to store from one stone, the step(s) the frog can take from that stone. 
	 * e.g
	 * stones = [0, 1, 3, 5, 6, 8, 12, 17]
	 * {0 : 1}
	 * {1 : 1, 2}
	 * {3 : 1, 2, 3}
	 * {5 : 1, 2, 3}
	 * {6 : 1, 2, 3, 4}
	 * {8 : 1, 2, 3, 4}
	 * {12: 3, 4, 5}
	 * 
	 * For each step s, if any stone can reach from s, we update the stone's value with s - 1, s and s + 1. 
	 * make sure that s - 1 > 0
	 * 
	 * For each step, if stone + step == stone[n - 1], the last stone, it's done. 
	 */
	public static void test403() {
		int[] stones = {0,1,3,5,6,8,12,17};
		boolean rev = task403_frogJump(stones);
		System.out.println("rev = " + rev);
		System.out.println("------------");
		rev = task403_frog_jump2(stones);
		System.out.println("rev2 = " + rev);
	}
	
	public static boolean task403_frogJump(int[] stones) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for(int stone : stones) {
			map.put(stone, new HashSet<Integer>());
		}
		map.get(0).add(1);  // {0 : 1}
		
		// for each stone
		for (int i = 0; i < stones.length - 1; i ++) {
			int curStone = stones[i];
			Set<Integer> curStoneSteps = map.get(curStone);
			for(int step : curStoneSteps) {
				int canReach = curStone + step;
				if (canReach == stones[stones.length - 1]) {
					return true;
				}
				
				// if can reach is a stone.  
				if (map.containsKey(canReach)) {
					int nextStone = canReach;
					Set<Integer> nextStoneSteps = map.get(nextStone);
					// add step - 1, step, step + 1 to the nextStoneSteps
					if (step - 1 > 0) {
						nextStoneSteps.add(step - 1);
					}
					nextStoneSteps.add(step);
					nextStoneSteps.add(step + 1);
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * 
	 * @param stones
	 * @return
	 */
	public static boolean task403_frog_jump2(int[] stones) {
		if (stones == null || stones.length == 0) {
			return false;
		}
		int n = stones.length;
		if (n == 1) {
			return true;
		}
		if (stones[1] != 1) {
			return false;
		}
		if (n == 2) {
			return true;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		int last_stone = stones[stones.length - 1];
		for (int i = 0; i < stones.length; i++) {
			if (i > 3 && stones[i] > stones[i - 1] * 2) {
				return false;
			}
			set.add(stones[i]);
		}
		return task403_dfs(set, last_stone, 1, 1);
	}
	
	
	public static boolean task403_dfs(HashSet<Integer> set, int last_stone, int pos, int step) {
		if (pos + step - 1 == last_stone || 
		    pos + step == last_stone     || 
		    pos + step + 1 == last_stone) {
			return true;
		}
		if (set.contains(pos + step + 1)) {
			// jump step + 1 to reach new position: pos + step + 1
			if (task403_dfs(set, last_stone, pos + step + 1, step + 1)) {
				return true;
			}
		}
		if (set.contains(pos + step)) {
			// jump step to reach new position: pos + step
			if (task403_dfs(set, last_stone, pos + step, step)) {
				return true;
			}
		}
		if (step > 1 && set.contains(pos + step - 1)) {
			// jump step - 1 to reach new position: pos + step - 1 
			if (task403_dfs(set, last_stone, pos + step - 1, step - 1)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * task404 
	 * Find the sum of all left leaves in a given binary tree. Example:
	 * 3 / \ 9 20 / \ 15 7 There are two left leaves in the binary tree, with
	 * values 9 and 15 respectively. Return 24.
	 */
	public static void test404() {
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		// n3.left = n4;
		// n3.right = n5;
		int sum = sumOfLeftLeaves(n1);
		System.out.println("sum = " + sum);
	}

	public static int sumOfLeftLeaves(TreeNode root) {
		return getLeftSum(root);
	}

	public static int getLeftSum(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int fromLeft = getLeftSum(node.left);
		int fromRight = getLeftSum(node.right);
		int curV = 0;
		if (node.left != null && node.left.left == null
				&& node.left.right == null) {
			curV = node.left.val;
		}

		return fromLeft + fromRight + curV;
	}

	/*
	 * task405
	 */

	// No need to worry about negative integers
	public String task405_toHex(int num) {
		if (num == 0)
			return "0";
		String[] map = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f" };
		StringBuilder stb = new StringBuilder();
		while (num != 0) {
			stb.append(map[(num & 15)]);
			num = (num >>> 4);
		}
		return stb.toString();
	}

	/*
	 * task406 Queue Reconstruction by Height
	 */
	public static void test406() {
		int[][] people = { 
				{ 7, 0 }, 
				{ 4, 4 }, 
				{ 7, 1 }, 
				{ 5, 0 }, 
				{ 6, 1 },
				{ 5, 2 } };
		int[][] result = task406_reconstructQueue(people);
		Debug.printMatrix(result);
	}
	
	public static int[][] task406_reconstructQueue(int[][] people) {
        // sort the people array by height in descending order
		// if the height is same, sort them in the ascending order of k
		Comparator<int[]> comp = new Comparator<int[]>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[0] == o2[0]) {
					return o1[1] < o2[1] ? -1 : 1;
				} 
				return o1[0] > o2[0] ? -1 : 1;
			}
		};
		
		Arrays.sort(people, comp);
		
		// do insert sort
		List<int[]> list = new LinkedList<int[]>();
		for(int i = 0; i < people.length; i++) {
			int[] curP = people[i];
			int curPPos = people[i][1];
			list.add(curPPos, curP);
		}
		// get the result
		int[][] result = new int[people.length][2];
		for(int i = 0; i < list.size(); i++) {
			result[i][0] = list.get(i)[0];
			result[i][1] = list.get(i)[1];
		}
		
		return result;
    } 
	

	/*
	 * task407 Given an m x n matrix of positive integers representing the
	 * height of each unit cell in a 2D elevation map, compute the volume of
	 * water it is able to trap after raining. Note: Both m and n are less than
	 * 110. The height of each unit cell is greater than 0 and is less than
	 * 20,000. Example: Given the following 3x6 height map: [ [1,4,3,1,3,2],
	 * [3,2,1,3,2,4], [2,3,3,2,3,1] ]
	 * 
	 * 
	 * 
	 * Return 4.
	 */
	
	

	public void test407() {
		int[][] matrix = { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 },
				{ 2, 3, 3, 2, 3, 1 } };

		int maxWater = task407_maxTrappedWater2D(matrix);
		System.out.println("maxWater = " + maxWater);

	}

	public int task407_maxTrappedWater2D(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		// add the 4 border into the min_heap
		PriorityQueue<Element> minHeap = new PriorityQueue<Element>(
				rLen * cLen, new Comparator<Element>() {

					@Override
					public int compare(Element o1, Element o2) {
						// TODO Auto-generated method stub
						if (o1.level == o2.level) {
							return 0;
						}
						return o1.level < o2.level ? -1 : 1;
					}

				});
		boolean[][] visited = new boolean[rLen][cLen];
		int[][] level = new int[rLen][cLen]; // the final water level above each
												// position

		// add the 4 boarder into the priority queue
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				if (i == 0 || i == rLen - 1 || j == 0 || j == cLen - 1) {
					if (!visited[i][j]) {
						Element elem = new Element(i, j, matrix[i][j],
								matrix[i][j]);
						level[i][j] = matrix[i][j];
						visited[i][j] = true;
						minHeap.add(elem);
					}
				} else {
					level[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		int sum = 0;
		while (!minHeap.isEmpty()) {
			Element cur = minHeap.poll();
			sum += level[cur.x][cur.y] - cur.height;

			// generate its neighbor
			for (int k = 0; k < 4; k++) {
				int nextX = cur.x + dx[k];
				int nextY = cur.y + dy[k];
				if (nextX >= 0 && nextX < rLen && nextY >= 0 && nextY < cLen
						&& !visited[nextX][nextY]) {
					int nextLevel = Math.max(matrix[nextX][nextY],
							Math.min(level[nextX][nextY], cur.level));
					// the minimum between the current level of [nextX][nextY]
					// and its parent's level

					if (level[nextX][nextY] != nextLevel) {
						// the level has been changed
						Element elem = new Element(nextX, nextY,
								matrix[nextX][nextY], nextLevel);
						// add into minHeap
						minHeap.offer(elem);
						// update level[nextX][nextY]
						level[nextX][nextY] = nextLevel;
						// update visited[nextX][nextY]
						visited[nextX][nextY] = true;
					}
				}
			}
		}
		Debug.printMatrix(matrix);
		System.out.println("----------------");
		Debug.printMatrix(level);

		return sum;
	}

	public int[] dx = { -1, 1, 0, 0 };
	public int[] dy = { 0, 0, -1, 1 };

	public class Element {
		int x;
		int y;
		int height;
		int level;

		public Element(int _x, int _y, int _h, int _l) {
			this.x = _x;
			this.y = _y;
			this.height = _h;
			this.level = _l;
		}
	}

	/*
	 * task408
	 */
	public static void test408() {
		String word = "a";
		String abbr = "01";
		boolean rev = task408_validWordAbbreviation(word, abbr);
		System.out.println("rev = " + rev);
	}

	public static boolean task408_validWordAbbreviation(String word, String abbr) {
		if (word == null && abbr == null) {
			return true;
		}
		if (word.length() == 0 && abbr.length() == 0) {
			return true;
		}
		if (word == null || abbr == null || word.length() == 0
				|| abbr.length() == 0) {
			return false;
		}
		int i = 0, j = 0;
		int wLen = word.length();
		int aLen = abbr.length();
		while (i < wLen && j < aLen) {
			if (Character.isDigit(abbr.charAt(j)) && abbr.charAt(j) != '0') {
				// cannot start with '0', e.g word = "a" abbr = "01"
				int k = j;
				while (k < aLen && Character.isDigit(abbr.charAt(k))) {
					k++;
				}
				int counter = Integer.parseInt(abbr.substring(j, k));
				j = k;
				while (counter > 0) {
					i++;
					counter--;
				}
			} else {
				if (word.charAt(i) != abbr.charAt(j)) {
					return false;
				}
				i++;
				j++;
			}
		}

		if (i != wLen || j != aLen) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * 409. Longest Palindrome
	 * 
	 * if the counter of char is even, OK
	 * we can only keep one single element. 
	 */
	public static int task409_longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> hs = new HashSet<Character>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(i));
				count++;
			} else {
				hs.add(s.charAt(i));
			}
		}
		// if there are single elements, we can only take one element
		if (!hs.isEmpty()) {
			return count * 2 + 1;
		}
		// otherwise, return count * 2
		return count * 2;
	}
	
	
	/*
	 * 410 Split Array Largest Sum
	 * 
	 */
	public static int task410_splitArray(int[] nums, int m) {
		int n = nums.length;
		int[][] dp = new int[m + 1][n + 1];
		int[] sums = new int[n + 1];
		
		// initialize the dp with INT_MAX
		for(int i = 0; i < dp.length; i ++) {
			for(int j = 0; j < dp[0].length; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 1; i <= n; i ++) {
			sums[i] = sums[i - 1] + nums[i - 1];
		}
		
		
		dp[0][0] = 0;
		
		for (int i = 1; i <= m; i ++) {
			for(int j = 1; j <= n; j ++) {
				for (int k = i - 1; k < j; k ++) {
					int val = Math.max(dp[i - 1][k], sums[j] - sums[k]);
					dp[i][j] = Math.min(dp[i][j], val);
				}
			}
		}
		return dp[m][n];
		
		
		
	}

	/*
	 * 411 Minimum Unique Word Abbreviation !!!
	 */

	/*
	 * task412
	 */
	public static void test412() {
		int n = 15;
		List<String> rev = task412_fizzBuzz(n);
		for (String s : rev) {
			System.out.println(s);
		}
	}

	public static List<String> task412_fizzBuzz(int n) {
		List<String> rev = new ArrayList<String>();
		if (n < 1) {
			return rev;
		}
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				rev.add("FizzBuzz");
			} else if (i % 3 == 0) {
				rev.add("Fizz");
			} else if (i % 5 == 0) {
				rev.add("Buzz");
			} else {
				rev.add(Integer.toString(i));
			}
		}
		return rev;
	}

	
	/*
	 * 410
	 */
	/*
	 * 413 Arithmetic Slices
	 */
	public static void test413() {
		int[] A = { 1, 2, 3, 4,5};
		int rev = task413_numberOfArithmeticSlices_dp(A);
		System.out.println("rev = " + rev);
		
		rev = task413_numberOfArithmeticSlices2(A);
		System.out.println("rev2 = " + rev);
	}

	/**
	 * @param A
	 * @return
	 * dp[i]: the number arithmetic slides in A[0..i]
	 * 如果当前值和前两个值构成arighmetic slide, dp[i] = dp[i - 1] + 1
	 * res 累加上 dp[i]
	 * init:
	 * dp[0] = dp[1] = 0;
	 * 
	 */
	public static int task413_numberOfArithmeticSlices_dp(int[] A) {
		int n = A.length;
		if (n < 3)
			return 0;
		int[] dp = new int[n];
		// dp[i] means the number of arithmetic slices ending with A[i]
		int result = 0;
		for (int i = 2; i < n; ++i) {
			// if A[i-2], A[i-1], A[i] are arithmetic, then the number of
			// arithmetic slices ending with A[i] (dp[i])
			// equals to:
			// the number of arithmetic slices ending with A[i-1] (dp[i-1], all
			// these arithmetic slices appending A[i] are also arithmetic)
			// +
			// A[i-2], A[i-1], A[i] (a brand new arithmetic slice)
			// it is how dp[i] = dp[i-1] + 1 comes
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				dp[i] = dp[i - 1] + 1;
			}
			result += dp[i]; // accumulate all valid slices
		}
		return result;
	}
	
	public static int task413_numberOfArithmeticSlices_dp2(int[] A) {
		if (A == null || A.length < 3) {
			return 0;
		}
		int res = 0, cur = 0;
        for (int i = 2; i < A.length; ++i) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur += 1;
                res += cur;
            } else {
            	// reset cur to 0
                cur = 0;
            }
        }
        return res;
	}
	
	/**
	 * 
	 * @param A
	 * @return
	 * 这道题让我们算一种算数切片，说白了就是找等差数列，限定了等差数列的长度至少为3，
	 * 那么[1,2,3,4]含有3个长度至少为3的算数切片，我们再来看[1,2,3,4,5]有多少个呢:
	 * len = 3: [1,2,3], [2,3,4], [3,4,5]
	 * len = 4: [1,2,3,4], [2,3,4,5]
	 * len = 5: [1,2,3,4,5]
	 * 那么我们可以找出递推式，长度为n的等差数列中含有长度至少为3的算数切片的个数为(n-1)(n-2)/2，
	 * 那么题目就变成了找原数组中等差数列的长度，然后带入公式去算个数即可
	 */
	public static int task413_numberOfArithmeticSlices2(int[] A) {
		int n = A.length;
		if (n < 3) {
			return 0;
		}
		int res = 0, len = 2;
		for (int i = 2; i < n; i++) {
			if (A[i - 1] - A[i - 2] == A[i] - A[i - 1]) {
				len++;
			} else {
				res = res + (len - 1) * (len - 2) / 2;
				// reset the len
				len = 2;
			}
		}
		if (len > 2) {
			res = res + (len - 1) * (len - 2) / 2;
		}
		return res;
	}

	/*
	 * 414 third max number
	 */
	public static void test414() {
		int[] nums = { 1, 2 };
		int rev = task414_thirdMax(nums);
		System.out.println("rev = " + rev);
	}

	public static int task414_thirdMax(int[] nums) {
		Integer max1 = null;
		Integer max2 = null;
		Integer max3 = null;
		for (Integer n : nums) {
			if (n.equals(max1) || n.equals(max2) || n.equals(max3))
				continue;
			if (max1 == null || n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (max2 == null || n > max2) {
				max3 = max2;
				max2 = n;
			} else if (max3 == null || n > max3) {
				max3 = n;
			}
		}
		return max3 == null ? max1 : max3;
	}

	/*
	 * 415 add two strings 1 2 3 4 5 6 7
	 */
	public static void test415() {
		String num1 = "9999999";
		String num2 = "1";
		// sum 5 7 9
		String rev = task415_addStrings(num1, num2);
		System.out.println("Rev = " + rev);
		Integer rev2 = Integer.parseInt(num1) + Integer.parseInt(num2);
		System.out.println("rev2 = " + rev2);
	}

	public static String task415_addStrings(String num1, String num2) {
		if (num1 == null || num1.length() == 0) {
			return num2;
		}
		if (num2 == null || num2.length() == 0) {
			return num1;
		}

		int i = num1.length() - 1;
		int j = num2.length() - 1;
		int carry = 0;
		StringBuilder stb = new StringBuilder();
		while (i >= 0 && j >= 0) {
			int c_num1 = num1.charAt(i) - '0';
			int c_num2 = num2.charAt(j) - '0';
			int sum = c_num1 + c_num2 + carry;
			carry = sum / 10;
			sum %= 10;
			stb.append(sum);
			i--;
			j--;
		}
		while (i >= 0) {
			int c_num1 = num1.charAt(i) - '0';
			int sum = c_num1 + carry;
			carry = sum / 10;
			sum = sum % 10;
			stb.append(sum);
			i--;
		}
		while (j >= 0) {
			int c_num2 = num2.charAt(j) - '0';
			int sum = c_num2 + carry;
			carry = sum / 10;
			sum = sum % 10;
			stb.append(sum);
			j--;
		}
		if (carry != 0) {
			stb.append(carry);
		}
		return stb.reverse().toString();
	}

	/*
	 * task416 Partition Equal Subset Sum
	 * 
	 * dp[i][j] whether the first [0.. i - 1] element can sum up to j
	 * 
	 * if we choose the jth element dp[i][j] = dp[i - 1][j - num[i]]
	 * 
	 * if we don't choose (i - 1)th element dp[i][j] = dp[i - 1][j]
	 * 
	 * dp[i][j] = dp[i - 1][j - num[i]] || dp[i - 1][j]
	 * 
	 * return dp[n][sum]
	 */
	public static void test416() {
		int[] nums = { 1, 2, 5 };
		boolean rev = task416_canPartition(nums);
		System.out.println("rev = " + rev);
		System.out.println("--------------------");
		boolean rev2 = task416_canPartition2(nums);
		System.out.println("rev2 = " + rev2);
	}

	public static boolean task416_canPartition(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 2 != 0) {
			return false;
		}
		int target = sum / 2;
		int n = nums.length;
		boolean[][] dp = new boolean[n + 1][target + 1];
		// dp[][]
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = true;
		}
		for (int j = 1; j <= target; j++) {
			dp[0][j] = false;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= target; j++) {
				if (j >= nums[i - 1]) {
					dp[i][j] = (dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][target];
	} // Time: O(n^2) Space: O(n ^ 2)

	// use 1D array
	public static boolean task416_canPartition2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int sum = 0;
		for (Integer i : nums) {
			sum += i;
		}
		if (sum % 2 != 0) {
			return false;
		}
		sum /= 2;
		int n = nums.length;
		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;

		for (int i = 0; i < n; i++) {
			// 为了避免影响后边的计算， 应该倒着来
			for (int j = sum; j >= 0; j--) {
				if (j >= nums[i]) {
					dp[j] = dp[j] || dp[j - nums[i]];
				}
			}
			System.out.println(Arrays.toString(dp));

		}

		return dp[sum];
	}

	/*
	 * task417
	 */
	int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public List<int[]> task417_pacificAtlantic(int[][] matrix) {
		List<int[]> res = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int n = matrix.length, m = matrix[0].length;
		// One visited map for each ocean
		boolean[][] pacific = new boolean[n][m];
		boolean[][] atlantic = new boolean[n][m];
		Queue<int[]> pQueue = new LinkedList<>();
		Queue<int[]> aQueue = new LinkedList<>();
		for (int i = 0; i < n; i++) { // Vertical border
			pQueue.offer(new int[] { i, 0 });
			aQueue.offer(new int[] { i, m - 1 });
			pacific[i][0] = true;
			atlantic[i][m - 1] = true;
		}
		for (int i = 0; i < m; i++) { // Horizontal border
			pQueue.offer(new int[] { 0, i });
			aQueue.offer(new int[] { n - 1, i });
			pacific[0][i] = true;
			atlantic[n - 1][i] = true;
		}
		bfs(matrix, pQueue, pacific);
		bfs(matrix, aQueue, atlantic);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (pacific[i][j] && atlantic[i][j])
					res.add(new int[] { i, j });
			}
		}
		return res;
	}

	public void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		int n = matrix.length, m = matrix[0].length;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] d : dir) {
				int x = cur[0] + d[0];
				int y = cur[1] + d[1];
				if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]
						|| matrix[x][y] < matrix[cur[0]][cur[1]]) {
					continue;
				}
				visited[x][y] = true;
				queue.offer(new int[] { x, y });
			}
		}
	}

	public List<int[]> task417_pacificAtlantic2(int[][] matrix) {
		List<int[]> res = new LinkedList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return res;
		}
		int n = matrix.length, m = matrix[0].length;
		boolean[][] pacific = new boolean[n][m];
		boolean[][] atlantic = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
			dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
		}
		for (int i = 0; i < m; i++) {
			dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
			dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, i);
		}
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (pacific[i][j] && atlantic[i][j])
					res.add(new int[] { i, j });
		return res;
	}

	public void dfs(int[][] matrix, boolean[][] visited, int height, int x,
			int y) {
		int n = matrix.length, m = matrix[0].length;
		if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]
				|| matrix[x][y] < height)
			return;
		visited[x][y] = true;
		for (int[] d : dir) {
			dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
		}
	}

	/*
	 * task418
	 */
	public static int task418_wordsTyping(String[] sentence, int rows, int cols) {
		int[] nextIndex = new int[sentence.length];
		int[] times = new int[sentence.length];
		for (int i = 0; i < sentence.length; i++) {
			int curLen = 0;
			int cur = i;
			int time = 0;
			while (curLen + sentence[cur].length() <= cols) {
				curLen += sentence[cur++].length() + 1;
				if (cur == sentence.length) {
					cur = 0;
					time++;
				}
			}
			nextIndex[i] = cur;
			times[i] = time;
		}
		int ans = 0;
		int cur = 0;
		for (int i = 0; i < rows; i++) {
			ans += times[cur];
			cur = nextIndex[cur];
		}
		return ans;
	}

	/*
	 * task419
	 */
	public int countBattleships(char[][] board) {
		int count = 0;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X')
						&& (j == 0 || board[i][j - 1] != 'X')) {
					count++;
				}
		return count;
	}

	/*
	 * 420 strong password checker
	 * A password is considered strong if below conditions are all met:
	 * It has at least 6 characters and at most 20 characters.
	 * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
	 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, 
	 * assuming other conditions are met).
	 * Write a function strongPasswordChecker(s), that takes a string s as input, 
	 * and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.
	 * Insertion, deletion or replace of any one character are all considered as one change.
	 * 
	 * http://www.cnblogs.com/grandyang/p/5988792.html
	 */
	public static int task420_strongPasswordChecker(String s) {
		if(s.length()<2) return 6-s.length();
        
        //Initialize the states, including current ending character(end), 
		//existence of lowercase letter(lower), uppercase letter(upper), digit(digit) 
		//and number of replicates for ending character(end_rep)
        char end = s.charAt(0);
        boolean upper = end>='A'&&end<='Z', lower = end>='a'&&end<='z', digit = end>='0'&&end<='9';
        
        //Also initialize the number of modification for repeated characters, 
        //total number needed for eliminate all consequnce 3 same character by replacement(change), 
        //and potential maximun operation of deleting characters(delete). 
        //Note delete[0] means maximum number of reduce 1 replacement operation by 1 deletion operation, 
        //delete[1] means maximun number of reduce 1 replacement by 2 deletion operation, delete[2] is no use here. 
        int end_rep = 1, change = 0;
        int[] delete = new int[3];
        
        for(int i = 1;i<s.length();++i){
            if(s.charAt(i)==end) ++end_rep;
            else{
                change+=end_rep/3;
                if(end_rep/3>0) ++delete[end_rep%3];
                //updating the states
                end = s.charAt(i);
                upper = upper||end>='A'&&end<='Z';
                lower = lower||end>='a'&&end<='z';
                digit = digit||end>='0'&&end<='9';
                end_rep = 1;
            }
        }
        change+=end_rep/3;
        if(end_rep/3>0) ++delete[end_rep%3];
        
        //The number of replcement needed for missing of specific character(lower/upper/digit)
        int check_req = (upper?0:1)+(lower?0:1)+(digit?0:1);
        
        if(s.length()>20){
            int del = s.length()-20;
            
            //Reduce the number of replacement operation by deletion
            if(del<=delete[0]) change-=del;
            else if(del-delete[0]<=2*delete[1]) change-=delete[0]+(del-delete[0])/2;
            else change-=delete[0]+delete[1]+(del-delete[0]-2*delete[1])/3;
            
            return del+Math.max(check_req,change);
        }
        else return Math.max(6-s.length(), Math.max(check_req, change));
	}
	

	/*
	 * 421 !!!
	 * https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit
	 * -manipulation-and-hashmap/7
	 * https://discuss.leetcode.com/topic/63207/java-o-n-solution-using-trie
	 */

	/*
	 * 422 Valid Word Square
	 */
	public static boolean validWordSquare(List<String> words) {
		if (words == null || words.size() == 0) {
			return true;
		}
		int n = words.size();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < words.get(i).length(); j++) {
				if (j >= n || words.get(j).length() <= i
						|| words.get(j).charAt(i) != words.get(i).charAt(j))
					return false;
			}
		}
		return true;
	}

	/*
	 * 423 Reconstruct Original Digits from English The idea is: for zero, it's
	 * the only word has letter 'z', for two, it's the only word has letter 'w',
	 * ...... so we only need to count the unique letter of each word, Coz the
	 * input is always valid. Code: zero: z one: o(1 - 0 - 2 - 4 ) two: w
	 * three:h (3 - 8) four: u five: f (5 - 4) six: x seven: s(7 - 6) eight: g
	 * nine: i(9 - 8 - 6 - 5)
	 */
	public static String task423_originalDigits(String s) {
		int[] count = new int[10];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'z')
				count[0]++;
			if (c == 'w')
				count[2]++;
			if (c == 'x')
				count[6]++;
			if (c == 's')
				count[7]++; // 7-6
			if (c == 'g')
				count[8]++;
			if (c == 'u')
				count[4]++;
			if (c == 'f')
				count[5]++; // 5-4
			if (c == 'h')
				count[3]++; // 3-8
			if (c == 'i')
				count[9]++; // 9-8-5-6
			if (c == 'o')
				count[1]++; // 1-0-2-4
		}
		count[7] -= count[6];
		count[5] -= count[4];
		count[3] -= count[8];
		count[9] = count[9] - count[8] - count[5] - count[6];
		count[1] = count[1] - count[0] - count[2] - count[4];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < count[i]; j++) {
				sb.append(i);
			}
		}
		return sb.toString();
	}

	/*
	 * 424. Longest Repeating Character Replacement !!!
	 */
	public static int task424_characterReplacement(String s, int k) {
		int len = s.length();
		int[] count = new int[26];
		int start = 0, maxCount = 0, maxLength = 0;
		for (int end = 0; end < len; end++) {
			maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
			while (end - start + 1 - maxCount > k) {
				count[s.charAt(start) - 'A']--;
				start++;
			}
			maxLength = Math.max(maxLength, end - start + 1);
		}
		return maxLength;
	}
	
	
	/**
	 * task429
	 * 
	 */
	public static class Node {
		int val;
		List<Node> children;
		public Node() {}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		
		Queue<Node> q = new LinkedList<>();
		
		q.offer(root);
		
		while(!q.isEmpty()) {
			int size = q.size();
			List<Integer> line = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				Node cur = q.poll();
				line.add(cur.val);
				if (cur.children != null && cur.children.size() != 0) {
					for (Node n: cur.children) {
						q.add(n);
					}
				}
				
			}
			result.add(line);
		}
		return result;
	}

	
	/**
	 * 430
	 * flatten a multilevel double linked list to one single double linked list
	 * 
	 */
	public static class Node1 {
		public int val;
	    public Node1 prev;
	    public Node1 next;
	    public Node1 child;

	    public Node1() {}

	    public Node1(int _val,Node1 _prev,Node1 _next,Node1 _child) {
	        val = _val;
	        prev = _prev;
	        next = _next;
	        child = _child;
	    }
	}
	
	public static Node1 task430_flatten(Node1 head) {
		
        return null;
    }
	public static Node1 task430_helper(Node1 node) {
		Node1 cur = node;
		Node1 tail = null;
		while(cur != null && cur.child != null) {
			cur = cur.next;
			tail = cur;
		}
		if (cur == null) {
			node.prev = tail;
			return node;
		} else {
			Node1 child = task430_helper(cur.child);
			cur.child = null;
		}
		return null;
	}
	
	/*
	 * 434 Number of Segments in a String
	 */
	public static void test434() {
		String s = "Hello, my name is John";
		int rev = task434_countSegments(s);
		System.out.println("rev = " + rev);
	}

	public static int task434_countSegments(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' '))
				res++;
		return res;

	}
	
	/*
	 * task435
	 * 435. Non-overlapping Intervals
	 * Given a collection of intervals, 
	 * find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
	 * Note:
	 * You may assume the interval's end point is always bigger than its start point.
	 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
	 * 
	 * sort the intervals. 
	 * <1> sort by end, smaller end in front
	 * <2> if ends are same, sort by start, larger start in front. 
	 * 
	 * scan the sorted intervals.
	 */
	
	public static int task435_erase_overlap_intervals(Interval[] intervals) {
		Comparator<Interval> myComp = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				if (o1.end == o2.end) {
					return o1.start > o2.start ? -1 : 1;
				}
				return o1.end < o2.end ? -1 : 1;
			}
		};
		Arrays.sort(intervals, myComp);
		
		// scan the sorted intervals.
		int end = Integer.MIN_VALUE;
		int count = 0;
		for(Interval i : intervals) {
			if (i.start >= end) {
				end = i.end;
			} else {
				count ++;
			}
		}
		return count;
	}
	
	
	/*
	 * 436 find right intervals
	 * 1 sort by start
	 * 2 For each end, find leftmost start using binary search. 
	 * 3 To get the original index, we need a map. 
	 * 
	 * 1 You may assume the interval's end point is always bigger than its start point.
	 * 2 You may assume none of these intervals have the same start point.
	 */
	
	public static void test436() {
		Interval[] intervals = {
				new Interval(3, 4),
				new Interval(2, 3),
				new Interval(1, 2)
		};
		int[] rev = task436_findRightInterval(intervals);
		System.out.println(Arrays.toString(rev));
	}
	
	 public static int[] task436_findRightInterval(Interval[] intervals) {
		 List<Integer> starts = new ArrayList<Integer>();
		 // start, index. the intervals do not have the same start point. 
		 Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		 // put the interval.start and index into the map
		 for(int i = 0; i < intervals.length; i ++) {
			 map.put(intervals[i].start, i);
			 starts.add(intervals[i].start);
		 }
		 
		 for (Entry<Integer, Integer> entry: map.entrySet()) {
			 System.out.println(entry.getKey() + " : " + entry.getValue());
		 }
		 
		Collections.sort(starts);
		
		
		int[] result = new int[intervals.length];
		// for each end, find the smallest larger start
		for (int i = 0; i < intervals.length; i ++) {
			Interval curInterval = intervals[i];
			int rightStartIdx = task436_getSmallestLargerOrEqual(starts, curInterval.end);
			
			if (rightStartIdx == -1) {
				result[i] = -1;
			} else {
				result[i] = map.get(starts.get(rightStartIdx));
			}
			
		}
		return result;
	 }
	 
	public static int task436_getSmallestLargerOrEqual(List<Integer> list, int target) {
		int left = 0, right = list.size() - 1;
		while (left + 1 < right) {
			int mid = left +(right - left)/2;
			if (list.get(mid) == target) {
				return mid;
			} else if (list.get(mid) > target) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (list.get(left) >= target) {
			return left;
		} else if (list.get(right) >= target) {
			return right;
		} else {
			return -1;
		}
	}

	/*
	 * task437 use an arraylist to store the path from root to leaf. for each
	 * leaf, we have an arraylist. this reduce to whether an subarray's sum == k
	 * 
	 * https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method
	 * 
	 */
	public static int task437_pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
		preSum.put(0, 1);
		return helper(root, 0, sum, preSum);
	}

	public static int helper(TreeNode root, int currSum, int target,
			HashMap<Integer, Integer> preSum) {
		if (root == null) {
			return 0;
		}

		currSum += root.val;
		
		int res = 0;
		if (preSum.containsKey(currSum - target)) {
			res += preSum.get(currSum - target);
		}

		if (!preSum.containsKey(currSum)) {
			preSum.put(currSum, 1);
		} else {
			preSum.put(currSum, preSum.get(currSum) + 1);
		}

		res += helper(root.left, currSum, target, preSum);
		res += helper(root.right, currSum, target, preSum);
		preSum.put(currSum, preSum.get(currSum) - 1);
		return res;
	}
	
	public static int pathSumIII(TreeNode root, int sum) {
		if (root == null)
			return 0;
		return findPath(root, sum) + pathSumIII(root.left, sum)
				+ pathSumIII(root.right, sum);
	}

	public static int findPath(TreeNode root, int sum) {
		int res = 0;
		if (root == null)
			return res;
		if (sum == root.val)
			res++;
		res += findPath(root.left, sum - root.val);
		res += findPath(root.right, sum - root.val);
		return res;
	}

	/*
	 * task438 sliding window
	 */
	public List<Integer> task438_findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<>();
		if (s == null || s.length() == 0 || p == null || p.length() == 0)
			return list;
		int[] hash = new int[256]; // character hash
		// record each character in p to hash
		for (char c : p.toCharArray()) {
			hash[c]++;
		}
		// two points, initialize count to p's length
		int left = 0, right = 0, count = p.length();
		while (right < s.length()) {
			// move right everytime, if the character exists in p's hash,
			// decrease the count
			// current hash value >= 1 means the character is existing in p
			if (hash[s.charAt(right++)]-- >= 1)
				count--;

			// when the count is down to 0, means we found the right anagram
			// then add window's left to result list
			if (count == 0)
				list.add(left);

			// if we find the window's size equals to p, then we have to move
			// left (narrow the window) to find the new match window
			// ++ to reset the hash because we kicked out the left
			// only increase the count if the character is in p
			// the count >= 0 indicate it was original in the hash, cuz it won't
			// go below 0
			if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
				count++;
		}
		return list;
	}
	
	
	/*
	 * 
	 * 439. Ternary Expression Parser
	 * Given a string representing arbitrarily nested ternary expressions, 
	 * calculate the result of the expression. 
	 * You can always assume that 
	 * the given expression is valid and only consists of digits 0-9, ?, :, T and F 
	 * (T and F represent True and False respectively).
	 * Note:
	 * The length of the given string is ≤ 10000.
	 * Each number will contain only one digit.
	 * The conditional expressions group right-to-left (as usual in most languages).
	 * The condition will always be either T or F. That is, the condition will never be a digit.
	 * The result of the expression will always evaluate to either a digit 0-9, T or F.
	 */
	
	/**
	 * 
	 * 如果有多个三元表达式嵌套的情况出现，那么我们的做法是从右边开始找到第一个问号，
	 * 然后先处理这个三元表达式，然后再一步一步向左推，这也符合程序是从右向左执行的特点。
	 * 那么我最先想到的方法是用用一个stack来记录所有问号的位置，然后根据此问号的位置，
	 * 取出当前的三元表达式，调用一个eval函数来分析得到结果，
	 * 能这样做的原因是题目中限定了三元表达式每一部分只有一个字符，而且需要分析的三元表达式是合法的，
	 * 然后我们把分析后的结果和前后两段拼接成一个新的字符串，继续处理之前一个问号，这样当所有问号处理完成后，
	 * 所剩的一个字符就是答案
	 * @param expression
	 * @return
	 * 
	 * assume the expression is valid
	 */
	
	public static void test439() {
		String expression = "T?T?F:5:3";
		String rev = task439_parseTernary(expression);
		System.out.println("rev= " +rev );
	}
    public static String task439_parseTernary(String expression) {
    	if (expression == null || expression.length() == 0) {
			return expression;
		}
        String res = expression;
        // store the index of '?'
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < expression.length(); i++) {
        	if (expression.charAt(i) == '?') {
				st.push(i);
			}
        }
        
        while(!st.isEmpty()) {
        	int index = st.pop();
        	int cur_exp_start = index - 1;
        	int cur_exp_end = index - 1 + 5;
        	String cur_exp = res.substring(cur_exp_start, cur_exp_end);
        	String cur_val = task439_eval(cur_exp);
        	res = res.substring(0, cur_exp_start) + cur_val + res.substring(cur_exp_end);
        }
        return res;
    }
    
    public static String task439_eval(String s) {
    	if (s.length() != 5) {
			return "";
		}
    	if (s.charAt(0) == 'T') {
			return s.substring(2, 3);
		} else {
			return s.substring(4);
		}
    }
    
    /**
     * 下面这种方法也是利用栈stack的思想，但是不同之处在于不是存问号的位置，而是存所有的字符，
     * 将原数组从后往前遍历，将遍历到的字符都压入栈中，我们检测如果栈首元素是问号，说明我们当前遍历到的字符是T或F，
     * 然后我们移除问号，再取出第一部分，再移除冒号，再取出第二部分，我们根据当前字符来判断是放哪一部分进栈，
     * 这样遍历完成后，所有问号都处理完了，剩下的栈顶元素即为所求
     */
    public static String task439_parseTenary2(String expression) {
    	if (expression == null || expression.length() == 0) {
			return expression;
		}
    	Stack<Character> st = new Stack<Character>();
    	for (int i = expression.length() - 1; i >= 0; i--) {
    		char cur = expression.charAt(i);
    		if (!st.isEmpty() && st.peek() == '?') {
				// the prev is '?'
    			// the cur must be 'T' or 'F'
    			// pop the '?'
    			st.pop();
    			// get the first char
    			Character firstCh = st.pop();
    			// pop the ':'
    			st.pop();
    			// get the second char
    			Character secondCh = st.pop();
    			// push the cur result into stack
    			st.push(cur == 'T' ? firstCh : secondCh);
			} else {
				// other char
				st.push(cur);
			}
    	}
    	
    	return String.valueOf(st.pop());
    }

	/*
	 * & 441
	 */
	public static void test441() {
		int n = 3;
		int rev = arrangeCoins(n);
		System.out.println("rev = " + rev);
	}

	public static int arrangeCoins(int n) {
		if (n == 1) {
			return 1;
		}
		int start = 0;
		int end = n;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (0.5 * mid * mid + 0.5 * mid <= n) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return start;
	}

	/*
	 * 442 Find All Duplicates in an Array
	 * 
	 * 1..n
	 */
	public static void test442() {
		int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
		List<Integer> result = task442_findDuplicates(nums);
		for (Integer i : result) {
			System.out.println(i);
		}
	}

	public static List<Integer> task442_findDuplicates(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer i : nums) {
			int curElem = Math.abs(i);
			int mappedIndex = curElem - 1;
			if (nums[mappedIndex] < 0) {
				result.add(curElem);
			} else {
				nums[mappedIndex] = -nums[mappedIndex];
			}
		}
		return result;
	}

	public static List<Integer> task442_findDuplicates2(int[] nums) {
		return null;
	}

	/*
	 * task444
	 */
	public static void test444() {
		int[] org = { 1, 2, 3 };
		int[][] seqs = { { 1, 2 }, { 1, 3 } };
		boolean rev = task444_sequenceReconstruction(org, seqs);
		System.out.println("rev = " + rev);
	}

	public static boolean task444_sequenceReconstruction(int[] org, int[][] seqs) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

		for (int[] seq : seqs) {
			if (seq.length == 1) {
				if (!map.containsKey(seq[0])) {
					map.put(seq[0], new HashSet<Integer>());
					indegree.put(seq[0], 0);
				}
			} else {
				for (int i = 0; i < seq.length - 1; i++) {
					if (!map.containsKey(seq[i])) {
						map.put(seq[i], new HashSet<Integer>());
						indegree.put(seq[i], 0);
					}

					if (!map.containsKey(seq[i + 1])) {
						map.put(seq[i + 1], new HashSet<Integer>());
						indegree.put(seq[i + 1], 0);
					}

					if (map.get(seq[i]).add(seq[i + 1])) {
						indegree.put(seq[i + 1], indegree.get(seq[i + 1]) + 1);
					}
				}
			}
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0)
				queue.offer(entry.getKey());
		}

		int index = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			if (size > 1) {
				// !!! key point. every time, the size must be 1
				return false;
			}

			int curr = queue.poll();

			// index out of org's bound or curr != org[index]
			if (index == org.length || curr != org[index]) {
				return false;
			}
			index++;

			for (int next : map.get(curr)) {
				indegree.put(next, indegree.get(next) - 1);
				if (indegree.get(next) == 0)
					queue.offer(next);
			}
		}
		// check whether index == org.length && index == map.size()
		return index == org.length && index == map.size();
	}

	/*
	 * 445
	 */

	public static void test445() {
		ListNode n1 = new ListNode(9);
		ListNode n2 = new ListNode(9);
		ListNode n3 = new ListNode(9);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);

		n1.next = n2;
		n2.next = n3;
		// n3.next = n4;
		// n4.next = n5;
		// n5.next = n6;

		ListNode n11 = new ListNode(1);
		ListNode n21 = new ListNode(2);
		ListNode n31 = new ListNode(3);
		n11.next = n21;
		n21.next = n31;

		ListNode l1 = n1;
		ListNode l2 = n11;
		Debug.printList(l1);
		Debug.printList(l2);

		ListNode rev = task445_addTwoListDiffLength(l1, l2);

		System.out.println("---");
		Debug.printList(rev);
	}
	
	public static ListNode task445_addTwoNumbers(ListNode l1, ListNode l2) {
		return task445_addTwoListDiffLength(l1, l2);
	}

	public static class Carry {
		int val;

		public Carry(int _v) {
			this.val = _v;
		}
	}


	public static ListNode task445_addTwoListDiffLength(ListNode l1, ListNode l2) {
		// assume that l1.length > l2.length
		int l1Len = task445_getLength(l1);
		int l2Len = task445_getLength(l2);
		if (l1Len < l2Len) {
			return task445_addTwoListDiffLength(l2, l1);
		}

		Carry carry = new Carry(0);
		int diff = l1Len - l2Len;
		if (diff == 0) {
			ListNode newTempHead = task445_addTwoListSameLength(l1, l2, carry);
			if (carry.val != 0) {
				ListNode newHead = new ListNode(carry.val);
				newHead.next = newTempHead;
				return newHead;
			} else {
				return newTempHead;
			}
		}
		ListNode ptrl1Prev = null;
		ListNode ptrl1 = l1;
		for (int i = 0; i < diff; i++) {
			ptrl1Prev = ptrl1;
			ptrl1 = ptrl1.next;
		}

		// split the first part and second part
		if (ptrl1Prev != null) {
			ptrl1Prev.next = null;
		}

		ListNode sameLenHead = task445_addTwoListSameLength(ptrl1, l2, carry);
		ListNode newTempHead = task445_addListWithCarry(l1, carry);

		ListNode newHead = null;
		if (carry.val != 0) {
			newHead = new ListNode(carry.val);
			newHead.next = newTempHead;
		} else {
			newHead = newTempHead;
		}

		// link the two part together
		ListNode tail = newHead;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = sameLenHead;

		return newHead;
	}

	public static ListNode task445_addListWithCarry(ListNode l1, Carry carry) {
		if (l1 == null) {
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0);
		result.next = task445_addListWithCarry(l1.next, carry);
		sum = l1.val + carry.val;
		carry.val = sum / 10;
		sum = sum % 10;
		result.val = sum;
		return result;
	}

	public static int task445_getLength(ListNode l1) {
		int count = 0;
		while (l1 != null) {
			count++;
			l1 = l1.next;
		}
		return count;
	}

	public static ListNode task445_addTwoListSameLength(ListNode l1,
			ListNode l2, Carry carry) {
		if (l1 == null && l2 == null) {
			return null;
		}
		int sum = 0;
		ListNode result = new ListNode(0);
		result.next = task445_addTwoListSameLength(l1.next, l2.next, carry);
		sum = l1.val + l2.val + carry.val;
		carry.val = sum / 10;
		sum = sum % 10;
		result.val = sum;

		return result;
	}

	/*
	 * 447
	 */
	public static int task447_numberOfBoomerangs(int[][] points) {
		if (points.length == 0 || points[0].length == 0)
			return 0;
		int ret = 0;
		for (int i = 0; i < points.length; i++) {
			Map<Integer, Set<int[]>> map = new HashMap<>();
			int[] p = points[i];
			for (int j = 0; j < points.length; j++) {
				if (j == i)
					continue;
				int[] q = points[j];
				int dis = getDis(p, q);
				if (!map.containsKey(dis))
					map.put(dis, new HashSet<int[]>());
				map.get(dis).add(q);
			}
			for (Integer key : map.keySet()) {
				int size = map.get(key).size();
				if (size >= 2)
					ret += (size * (size - 1));
			}
		}
		return ret;
	}

	public static int getDis(int[] p, int[] q) {
		int a = p[0] - q[0];
		int b = p[1] - q[1];
		return a * a + b * b;
	}

	/*
	 * 448. Find All Numbers Disappeared in an Array
	 */
	public static List<Integer> task448_findDisappearedNumbers(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				result.add(i + 1);
			}
		}
		return result;
	}

	/**
	 * 446
	 * 
	 */
	
	
	/*
	 * task449 serialize and deserialize BST
	 */
	// Encodes a tree to a single string.
	// do a preOrder traversal
	public static void test449() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);

		n5.left = n3;
		n3.left = n1;
		n5.right = n7;
		n7.left = n6;
		n7.right = n8;

		TreeNode root = n5;
		String serBST = task449_serialize(root);
		System.out.println(serBST);

		TreeNode nroot = task449_deserialize(serBST);
		Debug.preOrderTraversal(nroot);
		System.out.println();
		Debug.inOrderTraversal(nroot);
		System.out.println();
		Debug.postOrderTraversal(nroot);
		System.out.println("\n-------------------------");
		TreeNode root2 = task449_deserialize2(serBST);
		Debug.preOrderTraversal(root2);
		System.out.println();
		Debug.inOrderTraversal(root2);
		System.out.println();
		Debug.postOrderTraversal(root2);
	}

	public static String task449_serialize(TreeNode root) {
		StringBuilder stb = new StringBuilder();
		task449_serialize_helper(root, stb);
		return stb.toString();
	}

	public static void task449_serialize_helper(TreeNode node, StringBuilder stb) {
		if (node == null) {
			return;
		}
		stb.append(node.val);
		task449_serialize_helper(node.left, stb);
		task449_serialize_helper(node.right, stb);
	}

	// Decodes your encoded data to tree.
	public static TreeNode task449_deserialize(String data) {
		preIndex = 0;
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return task449_construct(data, data.charAt(0) - '0', min, max);
	}

	public static int preIndex = 0;

	public static TreeNode task449_construct(String data, int val, int min,
			int max) {
		if (preIndex >= data.length()) {
			return null;
		}
		TreeNode root = null;
		System.out.println("min = " + min);
		System.out.println("max = " + max);

		if (val > min && val < max) {
			root = new TreeNode(val);
			preIndex++;
			if (preIndex < data.length()) {
				root.left = task449_construct(data,
						data.charAt(preIndex) - '0', min, val);
				root.right = task449_construct(data,
						data.charAt(preIndex) - '0', val, max);
			}
		}
		return root;
	}

	/*
	 * 1. Create an empty stack. 2. Make the first value as root. Push it to the
	 * stack. 3. Keep on popping while the stack is not empty and the next value
	 * is greater than stack’s top value. Make this value as the right child of
	 * the last popped node. Push the new node to the stack. 4. If the next
	 * value is less than the stack’s top value, make this value as the left
	 * child of the stack’s top node. Push the new node to the stack. 5. Repeat
	 * steps 2 and 3 until there are items remaining in pre[].
	 */
	public static TreeNode task449_deserialize2(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(data.charAt(0) - '0');
		st.offerFirst(root);
		for (int i = 1; i < data.length(); i++) {
			int nextVal = data.charAt(i) - '0';
			TreeNode temp = null;

			while (!st.isEmpty() && nextVal > st.peekFirst().val) {
				temp = st.pollFirst();
			}

			if (temp != null) {
				// the nextVal's node is the temp's right child
				temp.right = new TreeNode(nextVal);
				// push the temp.right to the stack
				st.offerFirst(temp.right);
			} else {
				// temp is null
				// the nextVal is smaller than the st.peek().val
				temp = st.peekFirst();
				temp.left = new TreeNode(nextVal);
				st.push(temp.left);
			}
		}
		return root;
	}

	/*
	 * ( 474 474. Ones and Zeroes https://leetcode.com/problems/ones-and-zeroes/
	 */
	public static void test474() {
		String[] strs = { "10", "0001", "111001", "1", "0" };
		int m = 5, n = 3;
		int rev = task474_findMaxForm(strs, m, n);
		System.out.println("rev = " + rev);
	}

	/*
	 * Time: O(l * m * n) l: len of strs m: n:
	 */
	public static int task474_findMaxForm(String[] strs, int m, int n) {
		int[][] M = new int[m + 1][n + 1];

		for (String str : strs) {
			int[] count = task474_count(str);
			for (int i = m; i >= count[0]; i--) {
				for (int j = n; j >= count[1]; j--) {
					M[i][j] = Math.max(M[i][j],
							M[i - count[0]][j - count[1]] + 1);
				}
			}

			System.out.println("---------------------");
			Debug.printMatrix(M);
			System.out.println("=====================");

		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		Debug.printMatrix(M);

		return M[m][n];

	}

	public static int[] task474_count(String s) {
		int[] count = new int[2];
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '0') {
				count[0]++;
			} else {
				count[1]++;
			}
		}
		return count;
	}

}
