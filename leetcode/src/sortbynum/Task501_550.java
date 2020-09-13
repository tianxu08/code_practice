package sortbynum;

import ds.*;

import java.util.*;
import java.util.Map.Entry;



public class Task501_550 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test501();
//		test503();
//		test505();
		// test525();
//		test524();
//		 test526();
//		test529();
//		test536();
		// test539();
		// test541();
//		test543();
		test547();

		
	}
	
	
	/*
	 * 501
	 * 
	 * must do an inorder traversal
	 */

	Integer prev = null;
	int count = 1;
	int max = 0;

	public int[] task501_findMode(TreeNode root) {
		if (root == null)
			return new int[0];

		List<Integer> list = new ArrayList<Integer>();
		traverse(root, list);

		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); ++i)
			res[i] = list.get(i);
		return res;
	}

	private void traverse(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		traverse(root.left, list);
		if (prev != null) {
			if (root.val == prev)
				count++;
			else
				count = 1;
		}
		if (count > max) {
			max = count;
			list.clear();
			list.add(root.val);
		} else if (count == max) {
			list.add(root.val);
		}
		prev = root.val;
		traverse(root.right, list);
	}

	public static void test501() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(1);
		TreeNode n3 = new TreeNode(2);
		n1.left = n2;
		// n2.left = n3;
		int[] rev = task501_findMode2(n1);
		System.out.println(Arrays.toString(rev));
	}

	// this is wrong.
	public static int[] task501_findMode2(TreeNode root) {
		int maxCounter = 0;
		int counter = 1;
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode cur = root;

		while (cur != null || !st.isEmpty()) {
			if (cur != null) {
				TreeNode prev = st.isEmpty() ? null : st.peek();
				if (prev != null) {
					if (cur.val == prev.val) {
						counter++;
					} else {
						counter = 1;
					}
				}

				if (counter > maxCounter) {
					// update the maxCounter
					maxCounter = counter;
					list.clear();

					list.add(cur.val);

				} else if (counter == maxCounter) {
					list.add(cur.val);
				}
				st.push(cur);
				cur = cur.left;
			} else {
				cur = st.pop();
				cur = cur.right;
				// reset the counter to 1
				counter = 1;
			}
		}

		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	
	
	/*
	 * 503
	 * The first typical way to solve circular array problems is to extend the original array to twice length, 
	 * 2nd half has the same element as first half. Then everything become simple.
	 * Naive by simple solution, 
	 * just look for the next greater element directly. 
	 * Time complexity: O(n^2).
	 * Space: O(n)
	 */
	public static int[] tadk503_nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] result = new int[n];
		
		for (int i = 0; i < n; i++) {
			result[i] = -1;
			for (int j = i + 1; j < i + n; j++) {
				if (nums[j % n] > nums[i]) {
					result[i] = nums[j % n];
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 栈来进行优化上面的算法，我们遍历两倍的数组，然后还是坐标i对n取余，
	 * 取出数字，如果此时栈不为空，且栈顶元素小于当前数字，说明当前数字就是栈顶元素的右边第一个较大数，
	 * 那么建立二者的映射，并且去除当前栈顶元素，最后如果i小于n，则把i压入栈。
	 * 因为res的长度必须是n，超过n的部分我们只是为了给之前栈中的数字找较大值，所以不能压入栈
	 * @param nums
	 * @return
	 */
	public static void test503() {
		int[] nums = {1, 3, 2, 4, 3, 5, 2};
		int[] rev = task503_nextGreaterElements_opt(nums);
		System.out.println(Arrays.toString(rev));
	}
	
	public static int[] task503_nextGreaterElements_opt(int[] nums) {
		if (nums == null) {
			return null;
		}
		int n = nums.length;
		Stack<Integer> st = new Stack<Integer>();
		int[] res = new int[n];
		Arrays.fill(res, -1);
		for (int i = 0; i < 2 * n; i++ ){
			int num = nums[i % n];
			
			while (!st.isEmpty() && nums[st.peek()] < num) {
				// !!! set res[st.peek()]  set stack 所指的index 的元素
				res[st.peek()] = num;
				st.pop();
			}
			if (i < n) {
				st.push(i);
			}
			// for debug
			System.out.println(st);
		}
		return res;
	}
	
	/*
	 * 504
	 */
	public String convertToBase7(int num) {
		boolean isNeg = false;
		if (num < 0) {
			isNeg = true;
			num = -num;
		}
		if (num == 0) {
			return "0";
		}
		StringBuilder stb = new StringBuilder();

		while (num > 0) {
			int digit = num % 7;
			stb.append(digit);
			num /= 7;
		}
		if (isNeg) {
			stb.append("-");
		}
		return stb.reverse().toString();
	}

	
	/*
	 * 505 The Maze II
	 * Given the ball's start position, the destination and the maze, 
	 * find the shortest distance for the ball to stop at the destination.
	 * 
	 * 这道题要求最短的路径，普通的遍历dfs和bfs都是可以做的，但是求最短路径的话还是用Dijksra。
	 * 这里相当于每个点有至多4条edge相连，每条edge的weight就是到墙之前的长度。 
	 */
	public static void test505() {
		int[][] maze = {
				{0,0,1,0,0},
				{0,0,0,0,0},
				{0,0,0,1,0},
				{1,1,0,1,1},
				{0,0,0,0,0}
		};
		int[] start = {0, 4};
		int[] destination = {4,4};
		int rev = task505_shortestDistance(maze, start, destination);
		System.out.println("rev = " + rev);
	}
	
	public static int task505_shortestDistance(int[][] maze, int[] start, int[] destination) {
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
	
	private static boolean task505_isInMaze(int[][] maze, int x, int y) {
		int rLen = maze.length;
		int cLen = maze[0].length;
		return x >= 0 && x < rLen && y >= 0 && y < cLen;
	}
	
	public static class Node implements Comparable<Node>{
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
	
	/*
	 * 506
	 */
	public static String[] task506_findRelativeRanks(int[] nums) {
		String[] result = new String[nums.length];
		int max = 0;
		for (int i : nums) {
			if (i > max)
				max = i;
		}
		int[] hash = new int[max + 1];
		for (int i = 0; i < nums.length; i++) {
			hash[nums[i]] = i + 1;
		}
		int place = 1;
		for (int i = hash.length - 1; i >= 0; i--) {
			if (hash[i] != 0) {
				if (place == 1) {
					result[hash[i] - 1] = "Gold Medal";
				} else if (place == 2) {
					result[hash[i] - 1] = "Silver Medal";
				} else if (place == 3) {
					result[hash[i] - 1] = "Bronze Medal";
				} else {
					result[hash[i] - 1] = String.valueOf(place);
				}
				place++;
			}
		}
		return result;
	}
	
	
	/*
	 * 
	 * 507. Perfect Number
	 */
	public boolean task507_checkPerfectNumber(int num) {
        if (num == 1) return false;
        
        int sum = 0;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        // increase sum by add the 1 (the minimum factor)
        sum = sum + 1;
        
        return sum == num;
    }

	/*
	 * 508
	 */
	public static int[] task508_findFrequentTreeSum(TreeNode root) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		task508_helper(root, map);
		// traverse the map and get the most frequent
		int maxFrequency = 0;
		// traverse the set
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			maxFrequency = Math.max(maxFrequency, entry.getValue());
		}
		List<Integer> list = new ArrayList<Integer>();
		// traverse the map again
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxFrequency) {
				list.add(entry.getKey());
			}
		}

		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public static int task508_helper(TreeNode node, Map<Integer, Integer> map) {
		if (node == null) {
			return 0;
		}
		int leftSum = task508_helper(node.left, map);
		int rightSum = task508_helper(node.right, map);
		int curSum = leftSum + rightSum + node.val;
		if (!map.containsKey(curSum)) {
			map.put(curSum, 1);
		} else {
			map.put(curSum, map.get(curSum) + 1);
		}
		return curSum;
	}

	/*
	 * 513
	 */
	public static int task513_findBottomLeftValue(TreeNode root) {
		// do a bfs
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		int rev = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				if (i == 0) {
					rev = cur.val;
				}
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
		}
		return rev;
	}

	public static List<Integer> task513_largestValues_DFS(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		task513_helper(root, res, 0);
		return res;
	}

	private static void task513_helper(TreeNode root, List<Integer> res, int d) {
		if (root == null) {
			return;
		}
		// expand list size
		if (d == res.size()) {
			res.add(root.val);
		} else {
			// or set value
			res.set(d, Math.max(res.get(d), root.val));
		}
		task513_helper(root.left, res, d + 1);
		task513_helper(root.right, res, d + 1);
	}

	/*
	 * task515
	 */
	public static List<Integer> task515_largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		task515_helper(root, res, 0);
		return res;
	}

	public static void task515_helper(TreeNode node, List<Integer> res,
			int depth) {
		if (node == null) {
			return;
		}
		if (depth == res.size()) {
			res.add(node.val);
		} else {
			if (node.val > res.get(depth)) {
				res.set(depth, node.val);
			}
		}
		task515_helper(node.left, res, depth + 1);
		task515_helper(node.right, res, depth + 1);
	}

	/*
	 * 520
	 */
	public static boolean task520_detectCapitalUse(String word) {
		return word.equals(word.toUpperCase())
				|| word.equals(word.toLowerCase())
				|| Character.isUpperCase(word.charAt(0))
				&& word.substring(1).equals(word.substring(1).toLowerCase());
	}
	
	/*
	 * task523
	 * 
	 * 
	 * 1、处理k为0的情况；
	 * 2、用HashMap保存sum对k取余数，如果前序有余数也为sum % k的位置，那么就存在连续子数组和为k的倍数
	 */
	public static boolean task523_checkSubarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int sum = 0;
		for(int i = 0; i < nums.length; i ++) {
			sum = sum + nums[i];
			Integer prev = k == 0 ? map.get(sum) : map.get(sum % k);
			if (prev != null) {
				if (i - prev > 1) {
					return true;
				}
			} else {
				if (k == 0) {
					map.put(sum, i);
				} else {
					map.put(sum%k, i);
				}
			}
		}
		return false;
	}
	
	
	/*
	 * 524 Longest Word in Dictionary through Deleting
	 */
	public static void test524() {
		List<String> d = new ArrayList<String>();
		d.add("ale");
		d.add("apple");
		d.add("monkey");
		d.add("plea");
		
		String s = "abpcplea";
		String rev = task524_findLongestWord(s, d);
		System.out.println("rev = " + rev);
		
		String rev2 = task524_findLongestWord2(s, d);
		System.out.println("rev = " + rev2);
	}
	
	public static String task524_findLongestWord(String s, List<String> d) {
		if (s == null || s.length() == 0) {
			return "";
		}
        // sort the list
		Comparator<String> myComp = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (o1.length() == o2.length()) {
					
					return o1.compareTo(o2);
				}
				return o1.length() > o2.length() ? -1 : 1;
			}
		};
		
		Collections.sort(d, myComp);
	
		for (String word : d) {
			int i = 0; 
			// traverse the word
			for (int j = 0; j < s.length(); j++) {
				if (i < word.length() && word.charAt(i) == s.charAt(j)) {
					i++;
				}
			}
			
			// after traversal
			if (i == word.length()) {
				return word;
			}
		}
		return "";
    }
	
	public static String task524_findLongestWord2(String s, List<String> d) {
		if (s == null || s.length() == 0) {
			return "";
		}
		
		String rev = "";
		for (String word : d) {
			int i = 0; 
			for (int j = 0; j < s.length(); j++) {
				if (i < word.length() && word.charAt(i) == s.charAt(j)) {
					i++;
				}
			}
			if (i == word.length()) {
				if (word.length() > rev.length() ||(word.length() == rev.length() && word.compareTo(rev) < 0)) {
					rev = word;
				}
			}
		}
		return rev;
	}

	/*
	 * 525. Contiguous Array
	 */

	public static void test525() {
		int[] nums = { 0, 1, 0 };
		int rev = task525_findMaxLength(nums);
	}

	public static int task525_findMaxLength(int[] nums) {
		int n = nums.length;
		int[] sum = new int[n];
		for (int i = 0; i < n; i++) {
			int curElem = nums[i] == 0 ? -1 : 1;
			if (i == 0) {
				sum[i] = curElem;
			} else {
				sum[i] = sum[i - 1] + curElem;
			}
		}
		// find the longest array that

		Debug.printArray(sum);
		return -1;
	}

	/*
	 * 526. Beautiful Arrangement
	 * 
	 * similar to permutation.
	 * 
	 */
	public static void test526() {
		int N = 4;
		int rev = task526_countArrangement(N);
		System.out.println("rev = " + rev);
	}

	public static int task526_countArrangement(int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		task526_helper(arr, 0);
		return counter;
	}

	public static int counter = 0;

	public static void task526_helper(int[] arr, int index) {
		if (index == arr.length) {
			System.out.println(Arrays.toString(arr));
			counter++;
			return;
		}
		for (int i = index; i < arr.length; i++) {

			if (arr[i] % (index + 1) == 0 || (index + 1) % arr[i] == 0) {
				// put arr[i] into index's position.
				swap(arr, index, i);
				task526_helper(arr, index + 1);
				swap(arr, index, i);
			}
		}
	}

	public static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	
	/**
	 * task529
	 * Minesweeper
	 * 
	 * 'E' =>
	 * 1) mine: change the mine to X
	 * 2) square with at least one adjacent mine is revealed, chagne it to a digit (1 --> 8)
	 * 3) blank square and un-reveal all adjacent squares
	 * 
	 *   
	 *   需要检测neighbor 的neighbor 的状态
	 */
	
	public static void test529() {
		char[][] board = {
				"EEEEE".toCharArray(), 
				"EEMEE".toCharArray(),
				"EEEEE".toCharArray(),
				"EEEEE".toCharArray()
		};
		int[] click = {3, 0};
		
		char[][] rev = task529_updateBoard(board, click);
		Debug.printMatrix(rev);
		System.out.println("===============");
		char[][] rev2 = task529_dfs_updateBoard(board, click);
		Debug.printMatrix(rev2);
	}
	
	public static char[][] task529_updateBoard(char[][] board, int[] click) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || click == null || click.length != 2) {
			return board;
		}
		int rLen = board.length;
		int cLen = board[0].length;
		int rowIdx = click[0];
		int colIdx = click[1];
		
		// 1) click to mine 
		if (board[rowIdx][colIdx] == 'M') {
			board[rowIdx][colIdx] = 'X';
			return board;
		}
		
		// 2) check neighbors to find whether there are mines
		int counter = 0;
		for (int[] dir: directions) {
			int nextX = dir[0] + rowIdx;
			int nextY = dir[1] + colIdx;
			
			if (task529_inBound(board, nextX, nextY) && board[nextX][nextY] == 'M') {
				counter ++;
			}
		}
		
		if (counter > 0) {
			board[rowIdx][colIdx] = (char)('0' + counter);
			return board;
		} else {
			// counter == 0
			// 3) click to blank square
			// run BFS
			Queue<Integer> queue = new LinkedList<>();
			Set<Integer> visited = new HashSet<>();
			
			
			
			queue.offer(click[0] * cLen + click[1]);
			visited.add(click[0] * cLen + click[1]);
			
			
			System.out.println(visited);
			while(!queue.isEmpty()) {
				int cur = queue.poll();
				int curX = cur / cLen;
				int curY = cur % cLen;
				board[curX][curY] = 'B';
				
				for (int[] dir: directions) {
					int nextX = dir[0] + curX;
					int nextY = dir[1] + curY;
					if (!task529_inBound(board, nextX, nextY) || visited.contains(nextX * cLen + nextY)) {
						continue;
					}
					
					int numOfMines = 0;
					for (int[] dir2: directions) {
						int nnx = nextX + dir2[0];
						int nny = nextY + dir2[1];
						if (task529_inBound(board, nnx, nny) && board[nnx][nny] == 'M') {
							numOfMines ++;
						}
					}
					if (numOfMines == 0) {
						int nextPos = nextX * cLen + nextY;
						if (!visited.contains(nextPos)) {
							visited.add(nextPos);
							queue.offer(nextPos);
						}
						
					} else {
						board[nextX][nextY] = (char)('0' + numOfMines);
					}
				}
			
			}	
			
		}
		return board;
	}
	
	public static boolean task529_inBound(char[][] board, int x, int y) {
		return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
	}
	public static int[][] directions = {
			{-1, -1},
			{-1, 0},
			{-1, 1},
			{0, -1},
			{0, 1},
			{1, -1},
			{1, 0},
			{1, 1}
	};
	
	
	/**
	 * DFS
	 */
	
	public static char[][] task529_dfs_updateBoard(char[][] board, int[] click) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || click == null || click.length != 2) {
			return board;
		}
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		} else {
			task529_dfs(board, click[0], click[1]);
			return board;
		}
	}
	
	public static void task529_dfs(char[][] board, int i, int j) {
		if (!task529_inBound(board, i, j)) {
			return ;
		}
		if (task529_inBound(board, i, j) && board[i][j] == 'B') {
			// already visited
			return ;
		}
		if (board[i][j] == 'E') {
			// search neighbors
			int numOfMines = 0;
			for (int[] dir: directions) {
				int nextI = dir[0] + i;
				int nextJ = dir[1] + j;
				if (task529_inBound(board, nextI, nextJ) && board[nextI][nextJ] == 'M') {
					numOfMines ++;
				}
			}
			if (numOfMines == 0) {
				board[i][j] = 'B';
				
				// search its neighbors
				for (int[] dir: directions) {
					int nextI = dir[0] + i;
					int nextJ = dir[1] + j;
					task529_dfs(board, nextI, nextJ);
				}
			} else {
				board[i][j] = (char)(numOfMines + '0');
			}
			
		}
	}
	

	
	/*
	 * 530 Minimum Absolute Difference in BST do a in order traversal
	 */
	int min = Integer.MAX_VALUE;
	Integer prevVal = null;

	public int task530_getMinimumDifference(TreeNode root) {
		if (root == null)
			return min;

		task530_getMinimumDifference(root.left);

		if (prevVal != null) {
			min = Math.min(min, root.val - prevVal);
		}
		prevVal = root.val;
		task530_getMinimumDifference(root.right);
		return min;
	}

	/*
	 * 536 string to binary tree
	 */
	public static void test536() {
		String s = "4(2(3)(1))(6(5))";
		TreeNode root = str2tree(s);
		Debug.preOrderTraversal(root);
		System.out.println("----");
		Debug.inOrderTraversal(root);
	}

	public static TreeNode str2tree(String s) {
		if (s == null || s.length() == 0 || s.equals("")) {
			return null;
		}
		boolean isNeg = false;
		int idx = 0;
		int val = 0;
		while (idx < s.length() && s.charAt(idx) != '(') {
			char cur = s.charAt(idx);
			if (cur == '-') {
				isNeg = true;
			} else {
				val += val * 10 + (cur - '0');
			}
			idx++;
		}
		val = isNeg ? -val : val;

		TreeNode node = new TreeNode(val);
		if (idx < s.length() && s.charAt(idx) == '(') {
			int openP = 1;
			idx++;
			while (openP != 0) {
				if (s.charAt(idx) == '(') {
					openP++;
				} else if (s.charAt(idx) == ')') {
					openP--;
				}
				idx++;
			}
			// after this loop
			// we have the left subtree's element
			node.left = str2tree(s.substring(2, idx));

			// let try to find the right subtree's string
			idx++;
			int rightStart = idx + 1;
			if (idx < s.length() && s.charAt(idx) == '(') {
				if (s.charAt(s.length() - 1) == ')') {
					node.right = str2tree(s.substring(rightStart, s.length()));
				}

			}
		}
		return node;

	}

	public TreeNode str2tree2(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		int firstParen = s.indexOf("(");
		int val = firstParen == -1 ? Integer.parseInt(s) : Integer.parseInt(s
				.substring(0, firstParen));
		TreeNode cur = new TreeNode(val);
		if (firstParen == -1)
			return cur;
		int start = firstParen, leftParenCount = 0;
		for (int i = start; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				leftParenCount++;
			} else if (s.charAt(i) == ')') {
				leftParenCount--;
			}
			if (leftParenCount == 0 && start == firstParen) {
				cur.left = str2tree(s.substring(start + 1, i));
				// update start to i + 1
				start = i + 1;
			} else if (leftParenCount == 0) {
				cur.right = str2tree(s.substring(start + 1, i));
			}
		}
		return cur;
	}

	
	/**
	 * 538:
	 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

	 */
	
	public static TreeNode task538_convertBST(TreeNode root) {
		if (root == null) {
			return root;
		}
		task538_convertBST(root.right);
		task538_maxSum = task538_maxSum + root.val;
		root.val = task538_maxSum;
		task538_convertBST(root.left);
		return root;
	}
	
	public static int task538_maxSum = 0;
	

	/*
	 * 539 Input: ["23:59","00:00"] Output: 1 <1>first, sort the time points.
	 * according to hour, if the hour is same, compare by minutes <2>second,
	 * traverse the string and get the time difference. Also, check the
	 * difference of the first and last element.
	 */
	public static void test539() {
		List<String> t = new ArrayList<String>();
		t.add("23:59");
		t.add("00:00");
		int rev = task539_findMinDifference(t);
		System.out.println("Rev = " + rev);
	}

	/*
	 * Time: O(n * log n) using sorting
	 */

	public static int task539_findMinDifference(List<String> timePoints) {
		if (timePoints == null || timePoints.size() == 0) {
			return 0;
		}
		Comparator<String> myComp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				// extract the hours
				Integer o1_h = Integer.parseInt(o1.substring(0, 2));
				Integer o2_h = Integer.parseInt(o2.substring(0, 2));
				Integer o1_m = Integer.parseInt(o1.substring(3, o1.length()));
				Integer o2_m = Integer.parseInt(o2.substring(3, o2.length()));

				if (o1_h == o2_h) {
					return o1_m < o2_m ? -1 : 1;
				}
				return o1_h < o2_h ? -1 : 1;
			}
		};

		Collections.sort(timePoints, myComp);

		int globalMinMinutes = Integer.MAX_VALUE;

		// traverse the list
		for (int i = 0; i < timePoints.size() - 1; i++) {

			String time1 = timePoints.get(i);
			String time2 = timePoints.get(i + 1);

			Integer time1_h = Integer.parseInt(time1.substring(0, 2));
			Integer time2_h = Integer.parseInt(time2.substring(0, 2));
			Integer time1_m = Integer.parseInt(time1.substring(3,
					time1.length()));
			Integer time2_m = Integer.parseInt(time2.substring(3,
					time2.length()));

			int curDif = (time2_h - time1_h) * 60 + (time2_m - time1_m);
			if (curDif < globalMinMinutes) {
				globalMinMinutes = curDif;
			}
		}

		String time1 = timePoints.get(timePoints.size() - 1);
		String time2 = timePoints.get(0);

		Integer time1_h = Integer.parseInt(time1.substring(0, 2));
		Integer time2_h = Integer.parseInt(time2.substring(0, 2)) + 24;
		Integer time1_m = Integer.parseInt(time1.substring(3, time1.length()));
		Integer time2_m = Integer.parseInt(time2.substring(3, time2.length()));

		int curDif = (time2_h - time1_h) * 60 + (time2_m - time1_m);
		if (curDif < globalMinMinutes) {
			globalMinMinutes = curDif;
		}

		return globalMinMinutes;
	}

	/*
	 * Time: O(24*60) constant Or we can say O(n) n = 1440
	 * 
	 * There is only 24 * 60 = 1440 possible time points. Just create a boolean
	 * array, each element stands for if we see that time point or not. Then
	 * things become simple..
	 */
	public static int task539_findMinDifference_opt(List<String> timePoints) {
		boolean[] mark = new boolean[24 * 60];
		for (String time : timePoints) {
			String[] t = time.split(":");
			int h = Integer.parseInt(t[0]);
			int m = Integer.parseInt(t[1]);
			if (mark[h * 60 + m])
				return 0;
			mark[h * 60 + m] = true;
		}

		int prev = 0, min = Integer.MAX_VALUE;
		int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
		for (int i = 0; i < 24 * 60; i++) {
			if (mark[i]) {
				if (first != Integer.MAX_VALUE) {
					min = Math.min(min, i - prev);
				}
				first = Math.min(first, i);
				last = Math.max(last, i);
				prev = i;
			}
		}

		min = Math.min(min, (24 * 60 - last + first));

		return min;
	}

	/*
	 * 541
	 */
	public static void test541() {
		String s = "abcdefg";
		int k = 8;
		String rev = t541_reverseStr(s, k);
		System.out.println(rev);
	}

	public static String t541_reverseStr(String s, int k) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; i += 2 * k) {
			int j = i + 2 * k - 1;
			// both i and j is in bound
			if (j < arr.length) {
				t541_reverse(arr, i, i + k - 1);
			} else if (i + k - 1 < arr.length) {
				t541_reverse(arr, i, i + k - 1);
			} else {
				// do nothing
				t541_reverse(arr, i, arr.length - 1);
			}
		}
		return new String(arr);
	}

	// reverse the subarray between [x...y]
	public static void t541_reverse(char[] arr, int x, int y) {
		while (x < y) {
			// swap arr[x] and arr[y]
			char temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
			x++;
			y--;
		}
	}

	
	
	/*
	 * 542
	 */
	public static void test542() {
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		int[][] arr2d = {
				{0, 0, 0},
				{0, 1, 0},
				{1, 1, 1}
		};
		for(int[] row : arr2d) {
			List<Integer> list = new ArrayList<Integer>();
			for(Integer i : row) {
				list.add(i);
			}
			matrix.add(list);
		}
		System.out.println(matrix);
		
		for(List<Integer> list : matrix) {
			System.out.println(list);
		}
		System.out.println();
		
		matrix = l542_updateMatrix(matrix);
		System.out.println("------------");
		for(List<Integer> list : matrix) {
			System.out.println(list);
		}
		System.out.println();
		
		
	}
	
	public static List<List<Integer>> l542_updateMatrix(List<List<Integer>> matrix) {
		int rLen = matrix.size();
		int cLen = matrix.get(0).size();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		// put all 0 elements into queue
		for(int i = 0; i < rLen; i ++) {
			for(int j = 0;  j < cLen; j ++) {
				if (matrix.get(i).get(j) == 0) {
					queue.offer(i * cLen + j);
				} else {
					// set to MAX
					matrix.get(i).set(j, Integer.MAX_VALUE);
				}
			}
		}
		
		// do BFS
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		while (!queue.isEmpty()) {
			int curPos = queue.poll();
			int curX = curPos / cLen;
			int curY = curPos % cLen;
			
			for(int k = 0; k < 4; k ++) {
				int nextX = curX + dx[k];
				int nextY = curY + dy[k];
				
				if (nextX < 0 || nextX >= rLen || nextY < 0 || nextY >= cLen) {
					continue;
				}
				
				if (matrix.get(nextX).get(nextY) < matrix.get(curX).get(curY) + 1) {
					continue;
				}
				
				queue.offer(nextX * cLen + nextY);
				
				matrix.get(nextX).set(nextY, matrix.get(curX).get(curY) + 1);
				
			}
		}
		return matrix;
		
	}
	
	/**
	 * 543 diameter of binary tree
	 * 
	 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

	 * Example:
	 * Given a binary tree 
     *     1
     *    / \
     *   2   3
     *  / \     
     * 4   5    
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

	 * Note: The length of path between two nodes is represented by the number of edges between them.
	 */
	
	public static void test543() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		
		int rev = task543_diameterOfBinaryTree(n1);
		System.out.println("==>> rev " + rev);
	}
	
	 public static int task543_diameterOfBinaryTree(TreeNode root) {
		 task543_helper(root);
		 return diameter;
	 }
	 
	 public static int diameter = Integer.MIN_VALUE;
	 public static int task543_helper(TreeNode node) {
		 if (node == null) {
			 return 0;
		 }
		 int leftLen = task543_helper(node.left);
		 int rightLen = task543_helper(node.right);
		 
		 diameter = Math.max(leftLen + rightLen, diameter);
		 
		 return Math.max(leftLen, rightLen) + 1;
	 }
	 
	 /*
	  * taks547 friend circles
	  * 
	  * find the number of component in directed graph
	  * */
	 
	 public static void test547() {
		 int[][] M = {
				 {1, 1, 0},
				 {1, 1, 1},
				 {0, 1, 1}
		 };
		 int rev = task547_findCircle(M);
		 System.out.println("rev = " + rev);
	 }
	 
	 public static int task547_findCircle(int[][] M) {
		 // check
		 if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) {
			 return 0;
		 }
		 boolean[] visited = new boolean[M.length];
		 int counter = 0;
		 for (int i = 0; i < M.length; i++) {
			if (!visited[i]) {
				task547_dfs(M, visited, i);
				counter++;
			}
		 }
		 return counter;
	 }
	 
	 public static void task547_dfs(int[][] M, boolean[] visited, int node) {
		 if (visited[node]) return ;
		 
		 int[] neighbors = M[node];
		 visited[node] = true;
		 for (int nei = 0; nei < neighbors.length; nei ++) {
			 if (nei != node && M[node][nei] == 1) {
				 task547_dfs(M, visited, nei);
			 }
		 }
	 }
}
