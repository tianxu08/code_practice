package sortbynum;

import ds.*;
import ds.MyHashMap.Entry;

import java.util.*;

import javax.sound.midi.Track;

public class Task101_150 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test103();
		// test105();
		// System.out.println("---------");
		// test106();
		// test113();
		// test114();
		// test115();
		// test118();
		// test119();
		// test120();
		// test123();
		test126();
		// test128();
		// test130();
		// test135();
		// test138();
//		test140();
//		 test149();
//		test150();
	}

	/*
	 * 101 Symmetric Tree easy
	 */

	/*
	 * 102 Binary Level Order Tree Traversal BFS DFS
	 */
	public static List<List<Integer>> task102_levelOrderTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			List<Integer> curLevel = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = q.poll();
				curLevel.add(cur.val);
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
			}
			result.add(curLevel);
		}
		return result;
	}

	/*
	 * 103 Binary Tree Zigzag Traversal BFS, use deque
	 * 
	 * 3 / \ 9 20 / \15 7
	 */
	public static void test103() {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		TreeNode root = Debug.createTree(a);
		Debug.preOrderTraversal(root);
		System.out.println();

		List<List<Integer>> result = task103_zigzagLevelOrder(root);
		System.out.println(result);
	}

	public static List<List<Integer>> task103_zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		// use a flag to control direction
		boolean flag = false;
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		deque.offerLast(root);
		while (!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> curLayer = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {

				if (!flag) {
					// get node from the front
					TreeNode curNode = deque.pollFirst();
					curLayer.add(curNode.val);
					// first curNode.left and then curNode.right
					if (curNode.left != null) {
						deque.offerLast(curNode.left);

					}
					if (curNode.right != null) {
						deque.offerLast(curNode.right);
					}

				} else {
					// get node from the last
					TreeNode curNode = deque.pollLast();
					curLayer.add(curNode.val);
					// first, curNode.rigth and then curNode.left
					if (curNode.right != null) {
						deque.offerFirst(curNode.right);
					}
					if (curNode.left != null) {
						deque.offerFirst(curNode.left);
					}
				}
			}

			flag = !flag; // flip flag
			result.add(curLayer);
		}
		return result;

	}

	/*
	 * 104 Maximum Depth of binary tree
	 * 
	 * dfs
	 */

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);

		return Math.max(leftDepth, rightDepth) + 1;
	}

	/*
	 * 105 Construct binary from PreOrder and InOrder !!! refer laicode
	 * efficient
	 */
	public static void test105() {
		int[] pre = { 4, 2, 1, 3, 6, 5, 7 };
		int[] in = { 1, 2, 3, 4, 5, 6, 7 };

		TreeNode root = reconstructPreIn(pre, in);
		Debug.preOrderTraversal(root);
		System.out.println();
		Debug.inOrderTraversal(root);
		System.out.println();
		Debug.postOrderTraversal(root);
		System.out.println();
		System.out.println("counter = " + counter);
	}

	public static int pi = 0;
	public static int ii = 0;
	public static int counter = 0;

	public static TreeNode reconstructPreIn(int[] pre, int[] in) {
		pi = 0;
		ii = 0;
		return reconstructPreInHelper(pre, in, Integer.MAX_VALUE);
	}

	public static TreeNode reconstructPreInHelper(int[] pre, int[] in,
			int target) {
		counter++;
		if (ii >= in.length || in[ii] == target) {
			return null;
		}
		TreeNode root = new TreeNode(pre[pi]);
		pi++;
		root.left = reconstructPreInHelper(pre, in, root.val);
		ii++;
		root.right = reconstructPreInHelper(pre, in, target);
		return root;
	}

	/*
	 * 106 Construct binary tree from InOrder and PostOrder same with 105
	 */
	public static void test106() {
		int[] post = { 1, 3, 2 };
		int[] in = { 1, 2, 3 };
		TreeNode root = reconstructPostIn(post, in);
		Debug.preOrderTraversal(root);
		System.out.println();
		Debug.inOrderTraversal(root);
		System.out.println();
		Debug.postOrderTraversal(root);
		System.out.println();
	}

	public static TreeNode reconstructPostIn(int[] post, int[] in) {
		pi = post.length - 1;
		ii = in.length - 1;
		return reconstructPostInHelper(post, in, Integer.MAX_VALUE);
	}

	public static TreeNode reconstructPostInHelper(int[] post, int[] in,
			int target) {
		// this is to traverse the inOrder array.
		if (ii < 0 || in[ii] == target) {
			return null;
		}
		TreeNode root = new TreeNode(post[pi]);
		pi--;
		root.right = reconstructPostInHelper(post, in, root.val);
		ii--;
		root.left = reconstructPostInHelper(post, in, target);
		return root;
	}

	/*
	 * 107 Binary Tree Level Traversal 2
	 * 
	 * easy, only reverse the final result
	 */

	/*
	 * 108 Convert Sorted Array to BST
	 * 
	 * easy.
	 */

	/*
	 * 109 Convert Sorted List to BST
	 * 
	 * 
	 * build from bottom to up.
	 */
	public static void test109() {

	}

	public static ListNode current = null;

	public static int getLength(ListNode head) {
		int size = 0;
		while (head != null) {
			size++;
			head = head.next;
		}
		return size;
	}

	public static TreeNode sortedList2BST(ListNode head) {
		int size = getLength(head);
		current = head;
		return sortedList2BSTHelper(size);
	}

	public static TreeNode sortedList2BSTHelper(int size) {
		if (size <= 0) {
			return null;
		}
		TreeNode left = sortedList2BSTHelper(size / 2);

		TreeNode root = new TreeNode(current.val);

		current = current.next;

		TreeNode right = sortedList2BSTHelper(size - 1 - size / 2);

		root.left = left;
		root.right = right;

		return root;

	}

	/*
	 * 110 Balanced BT
	 * 
	 * recursion and BT
	 */
	public static boolean task110_isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return task110_getBalancedHeight(root) != -1;
	}

	public static int task110_getBalancedHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = task110_getBalancedHeight(root.left);
		int rightHeight = task110_getBalancedHeight(root.right);

		if (leftHeight == -1 || rightHeight == -1
				|| Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		return Math.max(leftHeight, rightHeight) + 1;

	}

	/*
	 * 111 Minimum depth of BT
	 * 
	 * The minimum depth is the number of nodes along the shortest path from the
	 * root node down to the nearest leaf node.
	 */
	public static void test111() {

	}

	public static int task111_minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return task111_MinDepthHelper(root);
	}

	public static int task111_MinDepthHelper(TreeNode root) {
		if (root == null) {
			return Integer.MAX_VALUE;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int leftMin = task111_MinDepthHelper(root.left);
		int rightMin = task111_MinDepthHelper(root.right);

		return Math.min(leftMin, rightMin) + 1;
	}

	/*
	 * 112 Path Sum
	 */
	public static boolean task112_hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return sum == root.val;
		}
		return task112_hasPathSum(root.left, sum - root.val)
				|| task112_hasPathSum(root.right, sum - root.val);

	}

	/*
	 * 113 Path Sum2
	 */
	public static void test113() {
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(4);
		TreeNode n3 = new TreeNode(8);
		n1.left = n2;
		n1.right = n3;

		TreeNode n4 = new TreeNode(11);
		TreeNode n5 = new TreeNode(13);
		TreeNode n6 = new TreeNode(4);

		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(2);
		TreeNode n9 = new TreeNode(5);
		TreeNode n10 = new TreeNode(1);

		n2.left = n4;
		n3.left = n5;
		n3.right = n6;

		n4.left = n7;
		n4.right = n8;

		n6.left = n9;
		n6.right = n10;

		System.out.println(task102_levelOrderTraversal(n1));
		int sum = 22;
		List<List<Integer>> result = task113_pathSum(n1, sum);
		System.out.println("------------------");
		System.out.println(result);

	}

	public static List<List<Integer>> task113_pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		task113_helper(result, root, sum, path);

		return result;
	}

	public static void task113_helper(List<List<Integer>> result,
			TreeNode node, int sum, ArrayList<Integer> path) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			if (node.val == sum) {
				path.add(node.val);
				ArrayList<Integer> copy = new ArrayList<Integer>(path);
				result.add(copy);
				path.remove(path.size() - 1);
				return;
			}
		}
		path.add(node.val);
		task113_helper(result, node.left, sum - node.val, path);
		task113_helper(result, node.right, sum - node.val, path);

		path.remove(path.size() - 1);
	}

	/*
	 * 114 Flatten BT to Linked List !!!
	 */
	public static void test114() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);

		n1.left = n2;
		n1.right = n5;
		n2.left = n3;
		n2.right = n4;
		n5.right = n6;
		TreeNode root = n1;
		System.out.println(task102_levelOrderTraversal(n1));

		// task114_flatten(n1);
		flatten(n1);
		while (root != null) {
			System.out.println(root.val);
			root = root.right;
		}

	}

	public static void task114_flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		task114_helper(root);
	}

	public static TreeNode task114_helper(TreeNode node) {
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return node;
		}
		TreeNode left = task114_helper(node.left);
		TreeNode right = task114_helper(node.right);

		if (left == null) {
			node.right = right;
			return node;
		} else {
			node.right = left;
			while (left.right != null) {
				left = left.right;
			}
			left.right = right;

			return node;
		}

	}

	public static TreeNode lastNode = null;

	/*
	 * This method is better, do a preOrder traversal of the tree. Maintain the
	 * lastNode, which is the last visited node. every time, lastNode.left =
	 * null, lastNode.right = curNode. !!! Only use the right pointer
	 * 
	 * every time, update lastNode Another thing, use a value to store
	 * curNode.right.
	 */
	public static void flatten(TreeNode node) {
		if (node == null) {
			return;
		}

		/*
		 * use the lastNode.left = null; lastNode.right point to the current
		 * node.
		 */
		if (lastNode != null) {
			lastNode.left = null;
			lastNode.right = node;
		}

		lastNode = node;
		// store right
		TreeNode right = node.right;
		flatten(node.left);
		flatten(right);
	}

	/*
	 * 115 Distinct Subsequence DP 。关键是如何得到递推关系，可以这样想，设母串的长度为 j， 子串的长度为
	 * i，我们要求的就是长度为 i 的字串在长度为 j 的母串中出现的次数， 设为 t[i][j]，若母串的最后一个字符与子串的最后一个字符不同，
	 * 则长度为 i 的子串在长度为 j 的母串中出现的次数就是母串的前 j - 1 个字符中子串出现的次数，即 t[i][j] = t[i][j -
	 * 1]， 若母串的最后一个字符与子串的最后一个字符相同，那么除了前 j - 1 个字符出现字串的次数外， 还要加上子串的前 i - 1
	 * 个字符在母串的前 j - 1 个字符中出现的次数，即 t[i][j] = t[i][j - 1] + t[i - 1][j - 1]。
	 * 
	 * dp[i][j] the number of distinct of s[0..i - 1], t[0..j - 1] t[0..j - 1] 在
	 * s[0..i - 1] 出现的次数
	 * 
	 * init: dp[i][0] = 1; if target == "". dp[0][j] = 0;
	 * 
	 * dp[i][j] = if s[i - 1] != t[j - 1], dp[i][j] = dp[i - 1][j] if s[i - 1]
	 * == t[j - 1], dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]
	 */
	public static void test115() {
		String s = "rabbbit";
		String t = "rabbit";
		int rev = task115_numDistinct_1D(s, t);
		int rev2 = task115_numDistinct(s, t);

	}

	public static int task115_numDistinct(String s, String t) {
		if (s == null || s.length() == 0 || t.length() == 0) {
			return 0;
		}
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		for (int i = 0; i <= s.length(); i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				if (s.charAt(i - 1) != t.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
				}
			}
		}
		System.out.println("----------------");
		Debug.printMatrix(dp);
		return dp[s.length()][t.length()];
	}

	// s: source(longer string) t: target(shorter string)
	public static int task115_numDistinct_1D(String s, String t) {
		if (s == null || s.length() == 0 || t.length() == 0) {
			return 0;
		}

		int[] M = new int[t.length() + 1];

		M[0] = 1;

		for (int i = 1; i <= s.length(); i++) {
			int upperLeft = M[0]; // record the upperLeft
			for (int j = 1; j <= t.length(); j++) {
				int temp = M[j]; // get the M[j] to temp, temp will update
									// upLeft for next time usage
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					M[j] += upperLeft;
				}
				upperLeft = temp;
			}
			Debug.printArray(M);
		}

		System.out.println("------------------------");

		Debug.printArray(M);
		return M[t.length()];
	}

	/*
	 * 116 Populating Next right pointers in each node
	 */

	/*
	 * 117 Populating Next right pointers in each node 2 level order traversal
	 * Key: 在上一层 链接 下一层的Node.
	 */
	public static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		public TreeLinkNode(int x) {
			// TODO Auto-generated constructor stub
			this.val = x;
		}
	}

	public static void task117_connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}

		TreeLinkNode curPtr = root;
		while (curPtr != null) {
			TreeLinkNode nextLayer = null;
			TreeLinkNode prevNode = null; // previous node of the next layer
											// node

			for (; curPtr != null; curPtr = curPtr.next) {
				// first get the nextLayer if the nextLayer == null
				if (nextLayer == null) {
					nextLayer = curPtr.left != null ? curPtr.left
							: curPtr.right;
				}
				if (curPtr.left != null) {
					if (prevNode == null) {
						prevNode = curPtr.left;
					} else {
						prevNode.next = curPtr.left;
						prevNode = prevNode.next;
					}
				}
				if (curPtr.right != null) {
					if (prevNode == null) {
						prevNode = curPtr.right;
					} else {
						prevNode.next = curPtr.right;
						prevNode = prevNode.next;
					}
				}
			}
			curPtr = nextLayer;
		}

	}

	/*
	 * 118 pascal's Trangle
	 */
	public static void test118() {
		int n = 4;
		List<List<Integer>> result = task118_generate(n);
		System.out.println(result);
	}

	public static List<List<Integer>> task118_generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (numRows <= 0) {
			return result;
		}
		List<Integer> prev = null;
		List<Integer> cur = new ArrayList<Integer>();

		cur.add(1);
		result.add(cur);
		for (int i = 2; i <= numRows; i++) {
			prev = cur;
			cur = new ArrayList<Integer>();
			for (int j = 0; j < i; j++) {
				if (j == 0) {
					cur.add(prev.get(0));
				} else if (j == i - 1) {
					cur.add(prev.get(j - 1));
				} else {
					cur.add(prev.get(j - 1) + prev.get(j));
				}
			}
			result.add(cur);

		}
		return result;
	}

	/*
	 * 119 pascal's Triangle 2
	 */
	public static void test119() {
		int n = 2;
		List<Integer> result = task119_getRow(n);
		System.out.println(result);
	}

	public static List<Integer> task119_getRow(int rowIndex) {
		List<Integer> result = new ArrayList<Integer>();
		if (rowIndex < 0) {
			return result;
		}
		List<Integer> prev = null;
		List<Integer> cur = new ArrayList<Integer>();

		cur.add(1);
		for (int i = 1; i <= rowIndex + 1; i++) {
			prev = cur;
			cur = new ArrayList<Integer>();
			for (int j = 0; j < i; j++) {
				if (j == 0) {
					cur.add(prev.get(0));
				} else if (j == i - 1) {
					cur.add(prev.get(j - 1));
				} else {
					cur.add(prev.get(j - 1) + prev.get(j));
				}
			}

		}
		return cur;
	}

	/*
	 * 120 triangle dp[i][j] minimum sum ending in triangle[i][j] [2], [3,4],
	 * [6,5,7], [4,1,8,3]
	 */
	public static void test120() {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(6);
		list3.add(5);
		list3.add(7);

		List<Integer> list4 = new ArrayList<Integer>();
		list4.add(4);
		list4.add(1);
		list4.add(8);
		list4.add(3);

		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		triangle.add(list1);
		triangle.add(list2);
		triangle.add(list3);
		triangle.add(list4);

		int rev = task120_minimumTotal_1D(triangle);
	}

	public static int task120_minimumTotal(List<List<Integer>> triangle) {
		int rowL = triangle.size();
		int colL = triangle.get(rowL - 1).size();
		int[][] min = new int[rowL][colL];
		min[0][0] = triangle.get(0).get(0);
		for (int i = 1; i < rowL; i++) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				if (j == 0) {
					// can only get from its above
					min[i][j] = min[i - 1][j] + triangle.get(i).get(j);
				} else if (j == triangle.get(i).size() - 1) {
					min[i][j] = min[i - 1][j - 1] + triangle.get(i).get(j);
				} else {
					// general case
					min[i][j] = Math.min(min[i - 1][j], min[i - 1][j - 1])
							+ triangle.get(i).get(j);
				}
			}
		}
		// traversal the last layer.
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < min[rowL - 1].length; i++) {
			if (min[rowL - 1][i] < minSum) {
				minSum = min[rowL - 1][i];
			}
		}
		return minSum;
	}

	public static int task120_minimumTotal_1D(List<List<Integer>> triangle) {
		int rowL = triangle.size();
		int colL = triangle.get(rowL - 1).size();

		int[] M = new int[colL];

		M[0] = triangle.get(0).get(0);
		for (int i = 1; i < rowL; i++) {
			int leftUp = M[0];
			for (int j = 0; j < triangle.get(i).size(); j++) {
				int temp = M[j];
				if (j == 0) {
					// can only get from its above
					M[j] = M[j] + triangle.get(i).get(j);
				} else if (j == triangle.get(i).size() - 1) {

					M[j] = leftUp + triangle.get(i).get(j);
				} else {
					// general case
					M[j] = Math.min(M[j], leftUp) + triangle.get(i).get(j);
				}
				leftUp = temp;
			}
		}
		// traversal the last layer.

		int minSumRev = Integer.MAX_VALUE;
		for (int i = 0; i < M.length; i++) {
			minSumRev = Math.min(minSumRev, M[i]);
		}
		// debug
		// System.out.println("minSumRev = " + minSumRev);
		return minSumRev;
	}

	/*
	 * 121 Best Time to buy and sell stock find the lowest and highest
	 */
	public static int task121_maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int lowest = Integer.MAX_VALUE;
		int curProfit = 0;
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			lowest = Math.min(lowest, prices[i]);
			curProfit = prices[i] - lowest;

			maxProfit = Math.max(maxProfit, curProfit);
		}
		return maxProfit;
	}

	/*
	 * 122 Best Time to buy and sell stock 2
	 */
	public static int task122_maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}
		return maxProfit;
	}

	/*
	 * 123 Best Time to buy and sell stock 3 Design an algorithm to find the
	 * maximum profit. You may complete at most two transactions.
	 * 
	 * left[i] if selling stock in ith day, the max profit 
	 * right[i] if selling stock in ith day, the max profit
	 */
	public static void test123() {
		int[] prices = { 1, 2 };
		int rev = task123_maxProfit(prices);
		System.out.println("rev = " + rev);

	}

	public static int task123_maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int[] left = new int[prices.length];
		int[] right = new int[prices.length];

		left[0] = 0;
		int minPrice = prices[0];
		for (int i = 1; i < prices.length; i++) {
			minPrice = Math.min(minPrice, prices[i]);
			left[i] = Math.max(left[i - 1], prices[i] - minPrice);
		}

		right[prices.length - 1] = 0;
		int maxPrice = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			maxPrice = Math.max(maxPrice, prices[i]);
			right[i] = Math.max(right[i + 1], maxPrice - prices[i]);
		}

		int profit = 0;
		for (int i = 0; i < prices.length; i++) {
			profit = Math.max(profit, left[i] + right[i]);
		}

		Debug.printArray(left);
		Debug.printArray(right);
		return profit;

	}

	/*
	 * 124 Binary Tree Maximum Path Sum
	 */
	public static int MaxSum = Integer.MIN_VALUE;

	public static int task124_maxSum(TreeNode root) {
		task124_helper(root);
		return MaxSum;
	}

	public static int task124_helper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = task124_helper(node.left);
		int right = task124_helper(node.right);
		int sum = node.val;
		if (left > 0) {
			sum += left;
		}
		if (right > 0) {
			sum += right;
		}
		MaxSum = Math.max(MaxSum, sum);
		return Math.max(left, right) > 0 ? Math.max(left, right) + node.val : node.val;
	}

	/*
	 * 125 Valid Palindrome
	 */
	public static boolean task125_isPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		int left = 0, right = s.length() - 1;
		while (left < right) {
			char leftCh = s.charAt(left);
			char rightCh = s.charAt(right);
			if (!isAlphaumeric(leftCh)) {
				left++;
			} else if (!isAlphaumeric(rightCh)) {
				right--;
			} else {
				if (isSame(leftCh, rightCh)) {
					left++;
					right--;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isAlphaumeric(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9')) {
			return true;
		}

		// Character.isLetter(c) Character.isDigit(c)
		return false;
	}

	public static boolean isSame(char ch1, char ch2) {
		char ch1Up = Character.toUpperCase(ch1);
		char ch2Up = Character.toUpperCase(ch2);
		if (ch1Up == ch2Up) {
			return true;
		}
		return false;
	}

	/*
	 * 126 Word Ladder 2
	 * 
	 * return the shortest paths. 
	 */
	public static void test126() {
		String[] strArr = {
				"hot", "dot", "dog", "lot", "log", "cog"
		};
		List<String> wordList = new ArrayList<String>();
		for (String s: strArr) {
			wordList.add(s);
		}
		String beginWord = "hit";
		String endWord = "cog";
		List<List<String>> result2 = task126_findLadders3(beginWord, endWord, wordList);
		System.out.println(result2);
		
	}
		
	public static List<List<String>> task126_findLadders3(String start, String end,
			List<String> dictWordList) {
		List<List<String>> ladders = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, Integer> distance = new HashMap<String, Integer>();

		Set<String> dict = new HashSet<String>();
		dict.addAll(dictWordList);
		dict.add(start);
		dict.add(end);

		task126_bfs(map, distance, start, end, dict);
		
		System.out.println("-------------------------------------");
		// print out map
		for (java.util.Map.Entry<String, List<String>> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("-------------------------------------");
		// print out distance
		for (Map.Entry<String, Integer> entry: distance.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("-------------------------------------");
		
		List<String> path = new ArrayList<String>();
		task126_dfs(ladders, path, end, start, distance, map);

		return ladders;
	}

	
	/**
	 * 
	 * @param map
	 * @param distance
	 * @param start
	 * @param end
	 * @param dict
	 */
	public static void task126_bfs(Map<String, List<String>> map, Map<String, Integer> distance,
			String start, String end, Set<String> dict) {
		Queue<String> q = new LinkedList<String>();
		q.offer(start);
		distance.put(start, 0);
		for (String s : dict) {
			map.put(s, new ArrayList<String>());
		}

		while (!q.isEmpty()) {
			String cur = q.poll();
			List<String> nextList = task126_expand(cur, dict);
			for (String next : nextList) {
				map.get(next).add(cur);
				if (!distance.containsKey(next)) {
					// the next's distance hasn't been updated
					// then update it
					distance.put(next, distance.get(cur) + 1);
					// add the next into queue
					q.offer(next);
				}
			}
		}
	}
	
	public static List<String> task126_expand(String crt, Set<String> dict) {
		List<String> expansion = new ArrayList<String>();
		
		for (int i = 0; i < crt.length(); i++) {
			for (char ch = 'a'; ch <= 'z'; ch++) {
				if (ch != crt.charAt(i)) {
					String expanded = crt.substring(0, i) + ch
							+ crt.substring(i + 1);
					if (dict.contains(expanded)) {
						expansion.add(expanded);
					}
				}
			}
		}

		return expansion;
	}
	
	public static void task126_dfs(List<List<String>> ladders, List<String> path, String cur,
			String start, Map<String, Integer> distance,
			Map<String, List<String>> map) {
		path.add(cur);
		if (cur.equals(start)) {
			Collections.reverse(path);
			ladders.add(new ArrayList<String>(path));
			Collections.reverse(path);
		} else {
			for (String next : map.get(cur)) {
				if (distance.containsKey(next)
						&& distance.get(cur) == distance.get(next) + 1) {
					task126_dfs(ladders, path, next, start, distance, map);
				}
			}
		}
		path.remove(path.size() - 1);
	}



	/*
	 * 127 word ladder
	 * 
	 * BFS
	 */
	public static int task127_ladderLength(String beginWord, String endWord,
			Set<String> wordList) {
		if (wordList == null || wordList.size() == 0) {
			return 0;
		}
		HashSet<String> visited = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		q.offer(beginWord);
		visited.add(beginWord);

		int length = 1;
		while (!q.isEmpty()) {
			length++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				String cur = q.poll();

				for (String nextWord : task127_getNextWords(cur, wordList)) {
					if (visited.contains(nextWord)) {
						continue;
					}
					if (nextWord.equals(endWord)) {
						return length;
					}
					visited.add(nextWord);
					q.offer(nextWord);
				}
			}
		}
		return 0;
	}

	// return a list of string which only has one different char with word and
	// also in dict.
	public static ArrayList<String> task127_getNextWords(String word,
			Set<String> dict) {
		ArrayList<String> nextWords = new ArrayList<String>();
		for (char ch = 'a'; ch <= 'z'; ch++) {
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == ch) {
					continue;
				}

				String nextWord = task127_replace(word, i, ch);
				if (dict.contains(nextWord)) {
					nextWords.add(nextWord);
				}
			}
		}
		return nextWords;
	}

	// replace character of a string at given index to a give characters.
	// return the new string.
	public static String task127_replace(String s, int index, char c) {
		char[] chars = s.toCharArray();
		chars[index] = c;
		return new String(chars);
	}

	/*
	 * 128 Longest Consecutive Sequence unsorted array, find the length of the
	 * longest consecutive element sequence
	 * 
	 * input: [100,4, 200, 1,3,2] output: length == 4 [1,2,3,4]
	 * 
	 * use hashMap
	 */
	public static void test128() {
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		int rev = task128_longestConsecutive(nums);
		System.out.println("rev = " + rev);
	}

	public static int task128_longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int longestLen = 1;
		int curLen = 1;
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], false);
		}

		for (int i = 0; i < nums.length; i++) {
			int curElem = nums[i];
			curLen = 1;
			curLen += task128_getLength(curElem + 1, map, 1);
			curLen += task128_getLength(curElem - 1, map, -1);

			longestLen = Math.max(longestLen, curLen);
		}
		return longestLen;

	}

	public static int task128_getLength(int input, Map<Integer, Boolean> map,
			int step) {
		int count = 0;
		while (map.containsKey(input) && map.get(input) == false) {
			map.put(input, true);
			count++;
			input += step;

		}
		return count;
	}

	/*
	 * 129 Sum Root to Leaf Numbers 
	 * 	 1 
	 * 	/ \ 
	 * 2   3
	 * 
	 * 1->2 12 1->3 13 
	 * 
	 * 12 + 13 = 25
	 */
	public static int task129_sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return task129_helper(root, 0);

	}

	public static int task129_helper(TreeNode node, int prev) {
		if (node == null) {
			return 0;
		}

		int sum = node.val + prev * 10;
		if (node.left == null && node.right == null) {
			return sum;
		}

		return task129_helper(node.left, sum)
				+ task129_helper(node.right, sum);
	}

	/*
	 * 130 Surrounded Regions
	 * 
	 * Can also solved by Union Find !!! to do later.
	 */

	public static void test130() {
		char[][] board = { "XXXX".toCharArray(), "XOOX".toCharArray(),
				"XXOX".toCharArray(), "XOXX".toCharArray() };

		task130_solveSurroundedRegions(board);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void task130_solveSurroundedRegions(char[][] board) {
		if (board == null || board.length == 0 || board[0] == null
				|| board[0].length == 0) {
			return;
		}
		int m = board.length;
		int n = board[0].length;

		Queue<Cell> queue = new LinkedList<Cell>();

		boolean[][] visited = new boolean[m][n];
		
		// add all 'O' on bound into queue
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O') {
				Cell cell1 = new Cell(i, 0);
				queue.offer(cell1);
			}
			if (board[i][n - 1] == 'O') {
				Cell cell2 = new Cell(i, n - 1);
				queue.offer(cell2);
			}
		}

		for (int j = 1; j < n - 1; j++) {
			if (board[0][j] == 'O') {
				Cell cell1 = new Cell(0, j);
				queue.offer(cell1);
			}

			if (board[m - 1][j] == 'O') {
				Cell cell2 = new Cell(m - 1, j);
				queue.offer(cell2);
			}
		}

		// Search all 'O' and marked them as 'T'
		while (!queue.isEmpty()) {
			Cell cur = queue.poll();
			board[cur.x][cur.y] = 'T';
			visited[cur.x][cur.y] = true;

			// for all neighbors
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx >= 0 && nx < m && ny >= 0 && ny < n
						&& board[nx][ny] == 'O' && !visited[nx][ny]) {
					Cell cell = new Cell(nx, ny);
					queue.offer(cell);
				}
			}
		}

		// traverse the board, convert all 'O' to 'X'
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'T') {
					board[i][j] = 'O';
				}
			}
		}

	}

	public static int[] dx = { 0, 0, -1, 1 };
	public static int[] dy = { -1, 1, 0, 0 };

	public static class Cell {
		int x;
		int y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	

	/*
	 * 131 Palindrome Partitioning
	 */
	public static List<List<String>> task131_partition(String s) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		List<String> curResult = new ArrayList<String>();
		task131_helper(s, result, curResult, 0);

		return result;
	}

	public static void task131_helper(String s, List<List<String>> result,
			List<String> curResult, int index) {
		if (index == s.length()) {
			List<String> copy = new ArrayList<String>(curResult);
			result.add(copy);
			return;
		}

		for (int i = index; i < s.length(); i++) {
			// substring[index, i]
			String prefix = s.substring(index, i + 1);
			if (!task131_isPalindrome(prefix)) {
				continue;
			}
			curResult.add(prefix);
			task131_helper(s, result, curResult, i + 1); // next index is i + 1
			curResult.remove(curResult.size() - 1);
		}
	}

	public static boolean task131_isPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return true;
		}
		int i = 0, j = s.length() - 1;
		while (i <= j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	/*
	 * 132 Palindrome Partitioning2
	 * 
	 * return the minimum cuts needed for a palindrome partition of s
	 * 
	 * dp[i][j] s[i..j] is palindrome.
	 * 
	 * dp[i][j] = if s[i] == s[j] dp[i - 1][j - 1] else s[i] != s[j] false
	 * 
	 * init dp[i][i] = true; dp[i][i + 1] = s[i] == s[i + 1]
	 */
	public static int task132_minCut(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		int n = s.length();
		boolean[][] dp = new boolean[n][n];

		// init
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
			if (i < n - 1) {
				if (s.charAt(i) == s.charAt(i + 1)) {
					dp[i][i + 1] = true;
				}
			}
		}

		for (int len = 3; len <= n; len++) {
			for (int i = 0; i < n + 1 - len; i++) {
				int j = i + len - 1; // i + len - 1 < n i < n + 1 - len
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1];
				}
			}
		}

		// after get the dp[][] matrix, we can find the minimum number cut.
		// cut[i] is the minimum cut for s[0..i]
		// cut[i] == 0 if dp[0][i] = true;
		// else
		// for all j in 0..i - 1
		// dp[ j + 1][i] == true; && cut[j] + 1 < cut[i]
		// cut[i] = 1 + cut[j]

		int[] cut = new int[n];
		for (int i = 0; i < n; i++) {
			if (dp[0][i]) {
				cut[i] = 0; // we don't need cut, since s[0..i] is palindrome
			} else {
				cut[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					// s[j + 1..i] is palindrome and 1 + cut[j] < cut[i]
					if (dp[j + 1][i] && 1 + cut[j] < cut[i]) {
						cut[i] = 1 + cut[j];
					}
				}
			}
		}

		return cut[n - 1];

	}

	/*
	 * 133 Clone Graph use hashMap<node, copynode>, then use the hashMap to
	 * construct corresponding connections
	 */
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		q.offer(node);

		while (!q.isEmpty()) {
			UndirectedGraphNode cur = q.poll();
			UndirectedGraphNode copy_cur = new UndirectedGraphNode(cur.label);
			map.put(cur, copy_cur);
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				q.offer(neighbor);
			}
		}

		// now the q is empty
		q.offer(node);
		while (!q.isEmpty()) {
			UndirectedGraphNode cur = q.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				UndirectedGraphNode copy_neighbor = map.get(neighbor);
				UndirectedGraphNode copy_cur = map.get(cur);
				copy_cur.neighbors.add(copy_neighbor);

				q.offer(neighbor);
			}
		}

		return map.get(node);

	}

	public static class UndirectedGraphNode {
		public int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}

	/*
	 * 134 Gas Station
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int total = 0;
		int sum = 0;
		int index = -1;

		for (int i = 0; i < gas.length; i++) {
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if (sum < 0) {
				index = i;
				sum = 0;
			}
		}

		if (total < 0) {
			return -1;
		} else {
			return index + 1;
		}
	}

	/*
	 * 135 Candy
	 */
	public static void test135() {
		int[] ratings = { 1, 2, 5, 3, 2, 1 };
		int total = task135_candy(ratings);
		System.out.println("total = " + total);
	}

	public static int task135_candy(int[] ratings) {
		if (ratings == null || ratings.length == 0) {
			return 0;
		}

		int total = ratings.length; // every child assign one
		int n = ratings.length;
		int[] left = new int[n];
		int[] right = new int[n];

		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				left[i] = left[i - 1] + 1;
			}
			if (ratings[n - i - 1] > ratings[n - i]) {
				right[n - i - 1] = right[n - i] + 1;
			}
		}

		for (int i = 0; i < n; i++) {
			total += Math.max(left[i], right[i]);
		}

		Debug.printArray(left);
		Debug.printArray(right);
		return total;
	}

	/*
	 * 136 Single Number
	 */

	/*
	 * 137 Single Number 2 考虑全部用二进制表示，如果我们把 第 ith 个位置上所有数字的和对3取余，那么只会有两个结果 0 或 1
	 * (根据题意，3个0或3个1相加余数都为0). 因此取余的结果就是那个 “Single Number”.
	 * 
	 * http://www.acmerblog.com/leetcode-single-number-ii-5394.html 这个帖子讲的很好
	 */
	public static int task137_singleNumber(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int result = 0;
		int[] bits = new int[32];
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < A.length; j++) {
				bits[i] += A[j] >> i & 1;
				bits[i] %= 3;
			}
			result |= (bits[i] << i);
		}
		return result;
	}

	public static int task137_singleNumber2(int[] A) {
		int ones = 0, twos = 0, threes = 0;
		for (int i = 0; i < A.length; i++) {
			twos |= ones & A[i];
			ones ^= A[i];

			threes = ones & twos;
			ones &= ~threes;
			twos &= ~threes;
		}
		return ones;
	}

	/*
	 * 138 Copy List With Random Pointer
	 */

	public static void test138() {
		RandomListNode l1 = new RandomListNode(-1);
		RandomListNode l11 = new RandomListNode(1);
		l1.next = l11;
		RandomListNode l2 = copyRandomList(l1);

		while (l1 != null) {
			System.out.print(l1.label + " ");
			l1 = l1.next;
		}
		System.out.println();
		while (l2 != null) {
			System.out.print(l2.label + " ");
			l2 = l2.next;
		}
		System.out.println();
	}

	public static RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return head;
		}
		RandomListNode cur = head;

		// create the copy node
		while (cur != null) {
			RandomListNode next = cur.next;
			RandomListNode copy_cur = new RandomListNode(cur.label);

			cur.next = copy_cur;
			copy_cur.next = next;

			cur = next;
		}

		// link the random pointer.
		cur = head;
		while (cur != null && cur.next != null) {
			if (cur.random != null) { // make sure that cur.random exists.
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}

		// split the original list and copy list.
		cur = head;
		RandomListNode copy_head = null;
		RandomListNode copy_tail = null;
		while (cur != null && cur.next != null) {
			RandomListNode next = cur.next.next;
			if (copy_head == null) {
				copy_head = cur.next;
				copy_tail = copy_head;
			} else {
				copy_tail.next = cur.next;
				copy_tail = copy_tail.next;
			}
			cur.next.next = null;
			// linke cur.next to next
			cur.next = next;
			cur = next;

		}

		return copy_head;

	}

	public static class RandomListNode {
		int label;
		RandomListNode next, random;

		public RandomListNode(int x) {
			// TODO Auto-generated constructor stub
			this.label = x;
		}
	}

	/*
	 * 139 Word Break To do
	 */
	public boolean task139_wordBreak(String s, Set<String> dict) {
		return task139_wordBreakHelper(s, dict, 0);
	} // Time: O(n*n)

	public boolean task139_wordBreakHelper(String s, Set<String> dict, int start) {
		if (start == s.length())
			return true;

		for (String a : dict) {
			int len = a.length();
			int end = start + len;

			// end index should be <= string length
			if (end > s.length())
				continue;

			if (s.substring(start, start + len).equals(a))
				if (task139_wordBreakHelper(s, dict, start + len))
					return true;
		}

		return false;
	}

	public boolean task139_wordBreak_dp(String s, Set<String> dict) {
		boolean[] t = new boolean[s.length() + 1];
		t[0] = true; // set first to be true, why?
		// Because we need initial state

		for (int i = 0; i < s.length(); i++) {
			// should continue from match position
			if (!t[i])
				continue;

			for (String a : dict) {
				int len = a.length();
				int end = i + len;
				if (end > s.length())
					continue;

				if (t[end])
					continue;

				if (s.substring(i, end).equals(a)) {
					t[end] = true;
				}
			}
		}

		return t[s.length()];
	}// Time: O(string len * dict.size)

	/*
	 * 140 Word break2 To do
	 * 
	 * use trie + backtracking
	 */
	
	
	public static void test140() {
		String s = "catsanddog";
		String[] arr = {"cat", "cats", "and", "sand", "dog"};
		Set<String> dict = new HashSet<>();
		
		for(String ss: arr) {
			dict.add(ss);
		}
		
		List<String> res = task140_wordBreak(s, dict);
		System.out.println("===>>> res: " + res);
	}
	public static List<String> task140_wordBreak(String s, Set<String> dict) {
		// Note: The Solution object is instantiated only once and is reused by
		// each test case.
		Map<String, ArrayList<String>> memo = new HashMap<String, ArrayList<String>>();
		
		
		List<String> res = wordBreakIIHelper(s, dict, memo);
		for (Map.Entry<String, ArrayList<String>> entry: memo.entrySet()) {
			System.out.println(entry.getKey() + " ==>>> " + entry.getValue());
		}
		
		return res;
		
	}

	public static List<String> wordBreakIIHelper(
			String s,
			Set<String> dict, 
			Map<String, ArrayList<String>> memo) {
		
		if (memo.containsKey(s)) {
			return memo.get(s); // return the value with the key is mapping to.
								// return null if the map contains no mapping
								// for the key
		}// base case
		
		int len = s.length();
		ArrayList<String> result = new ArrayList<String>();
		if (len <= 0) {
			return result;
		}
		for (int curLen = 1; curLen <= len; curLen++) {
			String secondPart = s.substring(len - curLen);
			// s.substring(beginningIndex) from the beginningIndex to the end of string
			if (dict.contains(secondPart)) { // if the subString is in the dict
				if (curLen == len) {
					result.add(secondPart);
				} else {
					String firstPart = s.substring(0, len - curLen);
					List<String> preArr = wordBreakIIHelper(firstPart, dict, memo);
					
					for (String item : preArr) {
						item = item + " " + secondPart;
						result.add(item);
					}
				}
			}
		}
		
		memo.put(s, result);
		return result;
	}

	
	
	
	/*
	 * 141 Linked List Cycle fast slow pointers
	 */

	/*
	 * 142 Linked List cycle 2 fast slow pointers
	 */

	/*
	 * 143 Reorder List
	 * 
	 * find the middle. Revrse the second part merge
	 */

	/*
	 * 144 BT preorder traversal One stack
	 */

	/*
	 * 145 BT postOrder traversal Try to use one stack
	 */

	/*
	 * 146 LRU Cache
	 */

	/*
	 * 147 Insertion Sort List
	 */
	public static ListNode task147_insertionSortList(ListNode head) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			pre = dummy;
			while (pre.next != null && pre.next.val <= cur.val) {
				pre = pre.next;
			}
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		return dummy.next;
	}

	/*
	 * 148 Sort List
	 */

	/*
	 * 149 Max Points on a Line
	 * http://www.cnblogs.com/grandyang/p/4579693.html
	 * 
	 */
	public static void test149() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(1, 0);
		Point[] points = { p1, p2 };

		int rev = task149_maxPoints(points);
		System.out.println("rev = " + rev);
	}

	public static class Point {
		int x;
		int y;

		public Point() {
			// TODO Auto-generated constructor stub
			x = 0;
			y = 0;
		}

		public Point(int a, int b) {
			this.x = a;
			this.y = b;
		}
	}

	public static int task149_maxPoints(Point[] points) {
		// sanity check
		if (points == null || points.length == 0) {
			return 0;
		}
		int result = 0;

		// we use each pair points to form a line
		for (int i = 0; i < points.length; i++) {
			// any line can be represented by a point and a slope
			// we take one point as seed and try to find all possible slopes
			Point seed = points[i];
			int same = 1; // record the same points with same <x, y>
			int sameX = 0; // record the points with same x, for the special
							// case of infinite slope
			int most = 0; // record the max number of points on the same line
							// crossing the seed point
			// a map with all possible slopes
			HashMap<Double, Integer> cnt = new HashMap<Double, Integer>();

			for (int j = 0; j < points.length; j++) {
				if (j == i) {
					continue;
				}
				Point temp = points[j];
				if (temp.x == seed.x && temp.y == seed.y) {
					same++;
				} else if (temp.x == seed.x) {
					sameX++;
				} else {
					double slope = (temp.y - seed.y + 0.0) / (temp.x - seed.x); // +
																				// 0.0
					if (!cnt.containsKey(slope)) {
						cnt.put(slope, 1);
					} else {
						cnt.put(slope, cnt.get(slope) + 1);
					}
					// update most
					most = Math.max(most, cnt.get(slope));
				}
			}
			most = Math.max(most, sameX) + same;
			result = Math.max(result, most);
		}
		return result;
	}

	public static int task149_maxPoint2(Point[] points) {
		int res = 0, n = points.length;
		for (int i = 0; i < n; ++i) {
			int duplicate = 1;
			for (int j = i + 1; j < n; ++j) {
				int cnt = 0;
				long x1 = points[i].x, y1 = points[i].y;
				long x2 = points[j].x, y2 = points[j].y;
				if (x1 == x2 && y1 == y2) {
					++duplicate;
					continue;
				}
				for (int k = 0; k < n; ++k) {
					int x3 = points[k].x, y3 = points[k].y;
					if (x1 * y2 + x2 * y3 + 
						x3 * y1 - x3 * y2 - 
						x2 * y1 - x1 * y3 == 0) {
						++cnt;
					}
				}
				res = Math.max(res, cnt);
			}
			res = Math.max(res, duplicate);
		}
		return res;
	}
	
	
	/**
	 * 而用double表示的双精度小数在有的系统里不一定准确，dividend/divisor
	 * 为了更加精确无误的计算共线，我们应当避免除法，从而避免无线不循环小数的出现，
	 * 那么怎么办呢，我们把除数和被除数都保存下来，不做除法，但是我们要让这两数分别除以它们的最大公约数（greatest common divisor=> gcd），
	 * 这样例如8和4，4和2，2和1，
	 * 这三组商相同的数就都会存到一个映射里面，同样也能实现我们的目标，而求GCD的函数如果用递归来写那么一行就搞定了
	 * @param points
	 * @return
	 */
	public static int task149_maxPoint3(Point[] points) {
		int res = 0;
        for (int i = 0; i < points.length; ++i) {
        	// use Map as key, this map has two int
            Map<Map<Integer, Integer>, Integer> m = new HashMap<>();
            int duplicate = 1;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicate++; 
                    continue;
                }
                int dx = points[j].x - points[i].x;
                int dy = points[j].y - points[i].y;
                int d = task149_gcd(dx, dy);
                Map<Integer, Integer> t = new HashMap<>();
                t.put(dx / d, dy / d);
                m.put(t, m.getOrDefault(t, 0) + 1);
            }
            res = Math.max(res, duplicate);
            for (Map.Entry<Map<Integer, Integer>, Integer> e : m.entrySet()) {
                res = Math.max(res, e.getValue() + duplicate);
            }
        }
        return res;
	}
	
	public static int task149_gcd(int a, int b) {
        return (b == 0) ? a : task149_gcd(b, a % b);
    }
	/*
	 * 150 Evaluate Reverse Polish Notation stack
	 */
	public static void test150() {
		String[] tokens = { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" };
		int rev = task150_evalRPN(tokens);
		System.out.println("rev = " + rev);
	}

	public static int task150_evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0) {
			return 0;
		}

		Set<String> set = new HashSet<>();
		// put + - */ into set
		set.add("+");
		set.add("-");
		set.add("*");
		set.add("/");
		
		LinkedList<Integer> st = new LinkedList<Integer>();
		
		// traverse the string
		for (String str: tokens) {
			if (!set.contains(str)) {
				// not operator, push into the stack
				st.offerLast(Integer.parseInt(str));
			} else {
				// operator
				Integer num1 = st.pollLast();
				Integer num2 = st.pollLast();
				// make sure that num1 and num2 are not null;
				
				String token = str;
				
				switch(token) {
					case "+": 
						st.offerLast(num1 + num2);
						break;
					case "-": 
						st.offerLast(num2 - num1);
						break;
					case "*": 
						st.offerLast(num1 * num2);
						break;
					case "/": 
						st.offerLast(num2 / num1);
						break;
					default: 
						break;
				}
			}
		}
		
		return st.pollLast();
	
	}

}
