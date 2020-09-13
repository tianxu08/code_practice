package algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class DynamicProgramming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test3();
		// test5();
		// test3_2();
		// test3_3();
		test3_4();
	}

	/*
	 * set 1
	 */

	/*
	 * set 2
	 */

	/*
	 * set 3 Dynamic Programming | Set 3 (Longest Increasing Subsequence)
	 * http://
	 * www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-
	 * subsequence/
	 */

	public static class MAX {
		int val;

		public MAX(int v) {
			this.val = v;
		}
	}

	public static int LISRec(int[] a) {
		int n = a.length;
		MAX max = new MAX(Integer.MIN_VALUE);
		return LISHelper(a, n, max);
	}

	// this function return length of LIS ending with element a[n - 1]
	// base case: if n == 1, there is only one element. return 1;
	// recursion rule: LISHelper(a, n, max)
	public static int LISHelper(int[] a, int n, MAX max) {
		// base case
		if (n == 1) {
			return 1;
		}

		int maxEndingHere = 1;
		for (int i = 1; i < n; i++) {
			int preRes = LISHelper(a, i, max);
			if (a[i - 1] < a[i] && preRes + 1 > maxEndingHere) {
				maxEndingHere = preRes + 1;
			}
		}
		if (max.val < maxEndingHere) {
			max.val = maxEndingHere;
		}
		return maxEndingHere;
	}

	/*
	 * DP f[i] the the longest Increasing Subsquence ending with a[i] f[i] = if
	 * a[i] > a[j], f[i] = max (f[j] + 1) 0 <=j < i initialize: f[i] = 1
	 */

	public static int LISDP(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int[] f = new int[n];
		f[0] = 1;
		for (int i = 1; i < f.length; i++) {
			f[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && f[j] + 1 > f[i]) {
					f[i] = f[j] + 1;
				}
			}
		}
		return f[n - 1];
	}

	public static void test3() {
		int[] a = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int LIS1 = LISRec(a);
		int LIS2 = LISDP(a);
		System.out.println("lis1 = " + LIS1);
		System.out.println("lis2 = " + LIS2);
	}

	/*
	 * Variation of LIS Dynamic Programming | Set 21 (Variations of LIS)
	 * http://www
	 * .geeksforgeeks.org/dynamic-programming-set-14-variations-of-lis/
	 * 
	 * 1. Building Bridge 2. Maximum Sum Increasing Subsequence 3. The Longest
	 * Chain You are given pairs of numbers. 4. Box Stacking
	 */

	/*
	 * 1. Building Bridge Consider a 2-D map with a horizontal river passing
	 * through its center. There are n cities on the southern bank with
	 * x-coordinates a(1) … a(n) and n cities on the northern bank with
	 * x-coordinates b(1) … b(n). You want to connect as many north-south pairs
	 * of cities as possible with bridges such that no two bridges cross. When
	 * connecting cities, you can only connect city i on the northern bank to
	 * city i on the southern bank.
	 * 
	 * 8 1 4 3 5 2 6 7 <---- Cities on the other bank of river---->
	 * -------------------------------------------- <---------------
	 * River---------------> -------------------------------------------- 1 2 3
	 * 4 5 6 7 8 <------- Cities on one bank of river------->
	 */

	/*
	 * 2. Maximum Sum Increasing Subsequence: (Set 14) Given an array of n
	 * positive integers. Write a program to find the maximum sum subsequence of
	 * the given array such that the integers in the subsequence are sorted in
	 * increasing order. For example, if input is {1, 101, 2, 3, 100, 4, 5},
	 * then output should be {1, 2, 3, 100}.
	 * 
	 * 
	 * f[i] =(0 <=j < i) f[j] + a[i]
	 */

	// this function return the maxSum of increasing subsequence. It will print
	// the result subsequence
	// M[i] stands the maximum sum of increasing subsequence ending by a[i]
	// Base case: M[0] = a[0]
	// Induction Role: M[i] = a[i]
	// for all j in [0, i - 1] if a[i] > a[j] M[i] = max(M[j]) + A[j]
	// after we get the M[], we traversal it, get the maximum Sum of increasing
	// subsequence and its index.
	// we can use the index to scan back, in decreasing order, to get the
	// subsequence
	public static int maxSumIncreasingSubsequence(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int maxSum = Integer.MIN_VALUE;
		int[] M = new int[n];
		M[0] = a[0];
		for (int i = 1; i < n; i++) {
			int preMaxSum = 0;
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					preMaxSum = Math.max(preMaxSum, M[j]);
				}
			}
			M[i] = preMaxSum + a[i];

			if (M[i] > maxSum) {
				maxSum = M[i];
			}
		}

		return maxSum;
	}

	public static int maxSumIncreasingSubsequence2(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		int n = a.length;
		int maxSum = Integer.MIN_VALUE;
		int[] M = new int[n];
		M[0] = a[0];
		for (int i = 1; i < n; i++) {

			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && M[j] + a[i] > M[i]) {
					M[i] = M[j] + a[i];
				}
			}
			// update the maxSum
			if (M[i] > maxSum) {
				maxSum = M[i];
			}
		}

		return maxSum;
	}

	public static void test3_2() {
		int[] a = { 1, 4, 2, 3, 100, 4, 5 };
		int maxSum = maxSumIncreasingSubsequence2(a);
		System.out.println("maxSum = " + maxSum);
	}

	/*
	 * 3. The Longest Chain You are given pairs of numbers. (Set 20) In a pair,
	 * the first number is smaller with respect to the second number. Suppose
	 * you have two sets (a, b) and (c, d), the second set can follow the first
	 * set if b < c. So you can form a long chain in the similar fashion. Find
	 * the longest chain which can be formed. e.g input: {{5, 24}, {39, 60},
	 * {15, 28}, {27, 40}, {50, 90} }, output: length = 3 for {{5, 24}, {27,
	 * 40}, {50, 90}}
	 * 
	 * This is a variation of LIS. (1) sorting the pairs by the first element.
	 * (2) Apply LIS. the condition is a modified. M[i] stands the longest chain
	 * ending with the i'th(0 based) pair base case: M[0] = 1; induction rule:
	 * M[i] = (0 <= j < 0) max {if (pair[i].first > pair[j].last) && M[j] + 1 >
	 * M[i]} M[j] + 1
	 */
	public static class Pair {
		int fisrt;
		int second;

		public Pair(int f, int s) {
			this.fisrt = f;
			this.second = s;
		}
	}

	public static void test3_3() {
		Pair[] pair = { new Pair(5, 24), new Pair(39, 60), new Pair(15, 28),
				new Pair(27, 40), new Pair(50, 90) };
		int maxLen = longestChain(pair);
		System.out.println("maxLen = " + maxLen);
	}

	public static int longestChain(Pair[] pair) {
		if (pair == null || pair.length == 0) {
			return 0;
		}
		Comparator<Pair> mycomp = new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				return o1.fisrt - o2.fisrt;
			}

		};
		Arrays.sort(pair, mycomp);
		int maxLen = 1;
		int n = pair.length;
		int[] M = new int[n];
		M[0] = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (pair[j].second < pair[i].fisrt && M[j] + 1 > M[i]) {
					M[i] = M[j] + 1;
				}
			}
			maxLen = Math.max(maxLen, M[i]);
		}
		return maxLen;
	}

	/*
	 * 4. Box Stacking
	 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box
	 * -stacking-problem/ Dynamic Programming | Set 22 (Box Stacking Problem)
	 * 
	 * You are given a set of n types of rectangular 3-D boxes, where the i^th
	 * box has height h(i), width w(i) and depth d(i) (all real numbers). You
	 * want to create a stack of boxes which is as tall as possible, but you can
	 * only stack a box on top of another box if the dimensions of the 2-D base
	 * of the lower box are each strictly larger than those of the 2-D base of
	 * the higher box. Of course, you can rotate a box so that any side
	 * functions as its base. It is also allowable to use multiple instances of
	 * the same type of box.
	 * 
	 * 
	 * Analysis: (1) this is a variation of LIS. (2) A box can be placed on top
	 * of another box only if both width and depth of the upper placed box are
	 * smaller than width and depth of the lower box respectively. (3) We can
	 * rotate boxes. For example, if there is a box with dimensions {1x2x3}
	 * where 1 is height, 2×3 is base, then there can be three possibilities,
	 * {1x2x3}, {2x1x3} and {3x1x2}. (4) We can use multiple instances of boxes.
	 * What it means is, we can have two different rotations of a box as part of
	 * our maximum height stack.
	 * 
	 * Algorithm: (1) generate all three rotation of boxes. the new array is 3
	 * times than the original array. To make things simpler, we assume that the
	 * depth are always smaller than the width. (2) sort the rotation array in
	 * decreasing order of base area. (3) MSH(i) maximum height of stacks ending
	 * with box i (box i is at the top of stack) MSH(i) = Max{MSH(j)} +
	 * box(i).height for 0 <= j < 0 when box(j).depth > box(i).depth &&
	 * box(j).width > box(i).width if there is no such j, then MSH(i) =
	 * box(i).height
	 */
	public static class Box {
		int height;
		int width;
		int depth;

		public Box(int h, int w, int d) {
			this.height = h;
			this.width = w;
			this.depth = d;
		}
	}

	public static int maxDepth(Box[] input) {
		int n = input.length;
		Box[] rot = new Box[3 * n];
		int index = 0;
		// pub the input into the rot
		for (int i = 0; i < n; i++) {
			Box rot1 = new Box(input[i].height, Math.max(input[i].width,
					input[i].depth), Math.min(input[i].width, input[i].depth));
			rot[index] = rot1;

			index++;

			Box rot2 = new Box(input[i].width, Math.max(input[i].height,
					input[i].depth), Math.min(input[i].height, input[i].depth));
			rot[index] = rot2;
			index++;
			Box rot3 = new Box(input[i].depth, Math.max(input[i].height,
					input[i].width), Math.min(input[i].height, input[i].width));
			rot[index] = rot3;
			index++;
		}
		Comparator<Box> comp = new Comparator<Box>() {

			@Override
			public int compare(Box o1, Box o2) {
				// TODO Auto-generated method stub
				return o2.depth * o2.width - o1.depth * o1.width;
			}
		};
		Arrays.sort(rot, comp);
		for (int i = 0; i < rot.length; i++) {
			System.out.println("   height = " + rot[i].height + "  width = "
					+ rot[i].width + "  depth = " + rot[i].depth);
		}

		// now we have a 3*n Box[];
		// Use the Longest Increasing Sequence Idea.
		// h[i] stands the height of
		int[] h = new int[3 * n];
		// height is unsorted
		for (int i = 0; i < h.length; i++) {
			h[i] = rot[i].height;
		}

		// j is smaller than i, so rot[j].width > rot[i].width && rot[j].depth >
		// rot[i].depth
		for (int i = 1; i < rot.length; i++) {
			for (int j = 0; j < i; j++) {
				if (rot[j].width > rot[i].width && rot[j].depth > rot[i].depth) {
					h[i] = Math.max(h[j] + rot[i].height, h[i]);
				}
			}
		}

		for (int i = 0; i < h.length; i++) {
			System.out.print(h[i] + " ");
		}
		System.out.println();

		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < h.length; i++) {
			maxHeight = Math.max(maxHeight, h[i]);
		}
		return maxHeight;
	}

	public static void test3_4() {
		Box[] arr = { new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6),
				new Box(10, 12, 32) };
		int maxHeight = maxDepth(arr);
		System.out.println("maxHeight = " + maxHeight);
	}

	/*
	 * longest ascending SubArray
	 */

	public static int LAS(int[] a) {
		if (a == null || a.length == 0) {
			return 0;
		}
		if (a.length == 1)
			return 1;
		int maxLen = 1;
		int curLen = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i] > a[i - 1]) {
				curLen++;
			} else {
				curLen = 1;
			}
			maxLen = Math.max(maxLen, curLen);
		}
		return maxLen;
	}

	public static void test4() {
		int[] a = { 7, 2, 3, 1, 5, 8, 9 };
		int maxLen = LAS(a);
		System.out.println("maxLen = " + maxLen);
	}

	public static boolean jump(int[] A) {
		int n = A.length;
		boolean[] M = new boolean[n];
		M[n - 1] = true;
		for (int i = n - 2; i >= 0; i--) {
			if (i + A[i] >= n - 1) {
				M[i] = true;
			} else {
				for (int k = i + 1; k <= i + A[i] && k < n; k++) {
					if (M[k] == true) {
						M[i] = true;
						break;
					}
				}
			}
		}
		System.out.println("len = " + n);
		for (int i = 0; i < M.length; i++) {
			System.out.print(M[i] + " ");
		}
		System.out.println();
		return M[0];
	}

	public static void test5() {
		int[] A = { 3, 2, 1, 0, 4 };
		System.out.println(jump(A));
	}

	/*
	 * task4 Dynamic Programming | Set 4 (Longest Common Subsequence)
	 * http://www.
	 * geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
	 */

	/*
	 * task5 Dynamic Programming | Set 5 (Edit Distance)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
	 */

	/*
	 * task6 Dynamic Programming | Set 6 (Min Cost Path)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
	 */

	/*
	 * task7 Dynamic Programming | Set 7 (Coin Change)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
	 */

	/*
	 * task8 Dynamic Programming | Set 8 (Matrix Chain Multiplication)
	 * http://www
	 * .geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
	 */

	/*
	 * task9 Dynamic Programming | Set 9 (Binomial Coefficient)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-9-binomial-coefficient/
	 */

	/*
	 * task10 Dynamic Programming | Set 10 ( 0-1 Knapsack Problem)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-10-0-1-knapsack-problem/
	 */

	/*
	 * task11 Dynamic Programming | Set 11 (Egg Dropping Puzzle)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-11-egg-dropping-puzzle/
	 */

	/*
	 * task12 Dynamic Programming | Set 12 (Longest Palindromic Subsequence)
	 * http
	 * ://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic
	 * -subsequence/
	 */

	/*
	 * task13 Dynamic Programming | Set 13 (Cutting a Rod)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
	 */

	/*
	 * task14 Dynamic Programming | Set 14 (Maximum Sum Increasing Subsequence)
	 * http
	 * ://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing
	 * -subsequence/
	 */

	/*
	 * task15 Dynamic Programming | Set 15 (Longest Bitonic Subsequence)
	 * http://www
	 * .geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence
	 * /
	 */

	/*
	 * task16 Dynamic Programming | Set 16 (Floyd Warshall Algorithm)
	 * http://www.
	 * geeksforgeeks.org/dynamic-programming-set-16-floyd-warshall-algorithm/
	 */

	/*
	 * task17 Dynamic Programming | Set 17 (Palindrome Partitioning)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-17-palindrome-partitioning/
	 */

	/*
	 * task18 Dynamic Programming | Set 18 (Partition problem)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-18-partition-problem/
	 */

	/*
	 * task19 Dynamic Programming | Set 19 (Word Wrap Problem)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
	 */

	/*
	 * task20 Dynamic Programming | Set 20 (Maximum Length Chain of Pairs)
	 * http:/
	 * /www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain
	 * -of-pairs/
	 */

	/*
	 * task21 Dynamic Programming | Set 21 (Variations of LIS)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-14-variations-of-lis/
	 */

	/*
	 * task22 Dynamic Programming | Set 22 (Box Stacking Problem)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-21-box-stacking-problem/
	 */

	/*
	 * task23 Dynamic Programming | Set 23 (Bellman–Ford Algorithm)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-23-bellman-ford-algorithm/
	 */

	/*
	 * task24 Dynamic Programming | Set 24 (Optimal Binary Search Tree)
	 * http://www
	 * .geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
	 */

	/*
	 * task25 Dynamic Programming | Set 25 (Subset Sum Problem)
	 * http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
	 */

	/*
	 * task26 Dynamic Programming | Set 26 (Largest Independent Set Problem)
	 * http://www.geeksforgeeks.org/largest-independent-set-problem/
	 */

	/*
	 * task27 Dynamic Programming | Set 27 (Maximum sum rectangle in a 2D
	 * matrix)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle
	 * -in-a-2d-matrix/
	 */

	/*
	 * task28 Dynamic Programming | Set 28 (Minimum insertions to form a
	 * palindrome)
	 * http://www.geeksforgeeks.org/dynamic-programming-set-28-minimum
	 * -insertions-to-form-a-palindrome/
	 */

	/*
	 * task29 Dynamic Programming | Set 29 (Longest Common Substring)
	 * http://www.geeksforgeeks.org/longest-common-substring/
	 */

	/*
	 * task30 Dynamic Programming | Set 30 (Dice Throw)
	 * http://www.geeksforgeeks.org/dice-throw-problem/
	 */

	/*
	 * task31 Dynamic Programming | Set 31 (Optimal Strategy for a Game)
	 * http://www
	 * .geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for
	 * -a-game/
	 */

	/*
	 * task32 Dynamic Programming | Set 32 (Word Break Problem)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-32-word-break-problem/
	 */

	/*
	 * task33 Dynamic Programming | Set 33 (Find if a string is interleaved of
	 * two other strings)
	 * http://www.geeksforgeeks.org/check-whether-a-given-string
	 * -is-an-interleaving-of-two-other-given-strings-set-2/
	 */

	/*
	 * task34 Dynamic Programming | Set 34 (Assembly Line Scheduling)
	 * http://www.
	 * geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
	 */

	/*
	 * task35 Dynamic Programming | Set 35 (Longest Arithmetic Progression)
	 * http:
	 * //www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-
	 * in-a-sorted-array/
	 */

	/*
	 * task36 Dynamic Programming | Set 36 (Maximum Product Cutting)
	 * http://www.geeksforgeeks
	 * .org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
	 */

	/*
	 * task37 Largest Sum Contiguous Subarray
	 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
	 */

	/*
	 * task38 Ugly Numbers http://www.geeksforgeeks.org/ugly-numbers/
	 */

	/*
	 * task39 Maximum size square sub-matrix with all 1s
	 * http://www.geeksforgeeks
	 * .org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
	 */

	/*
	 * task40 Length of the longest substring without repeating characters
	 * http:/
	 * /www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating
	 * -characters/
	 */

	/*
	 * task41 Minimum number of jumps to reach end
	 * http://www.geeksforgeeks.org/minimum
	 * -number-of-jumps-to-reach-end-of-a-given-array/
	 */

	/*
	 * task42 Program for Fibonacci numbers
	 * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
	 */

	/*
	 * task43 Longest Palindromic Substring | Set 1
	 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
	 */

	/*
	 * task44 Travelling Salesman Problem | Set 1 (Naive and Dynamic
	 * Programming)
	 * http://www.geeksforgeeks.org/travelling-salesman-problem-set-1/
	 */

	/*
	 * task45 Count all possible paths from top left to bottom right of a mXn
	 * matrix
	 * http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right
	 * -nxm-matrix/
	 */

	/*
	 * task46 Remove minimum elements from either side such that 2*min becomes
	 * more than max
	 * http://www.geeksforgeeks.org/remove-minimum-elements-either-
	 * side-2min-max/
	 */

	/*
	 * task47 Program for nth Catalan Number
	 * http://www.geeksforgeeks.org/program-nth-catalan-number/
	 */

	/*
	 * task48
	 * 
	 * Count number of binary strings without consecutive 1’s
	 * http://www.geeksforgeeks
	 * .org/count-number-binary-strings-without-consecutive-1s/
	 */

	/*
	 * task49 Count Possible Decodings of a given Digit Sequence
	 * http://www.geeksforgeeks
	 * .org/count-possible-decodings-given-digit-sequence/
	 */

	/*
	 * task50 Count all possible walks from a source to a destination with
	 * exactly k edges
	 * http://www.geeksforgeeks.org/count-possible-paths-source-destination
	 * -exactly-k-edges/
	 */

	/*
	 * task51 Shortest path with exactly k edges in a directed and weighted
	 * graph
	 * http://www.geeksforgeeks.org/shortest-path-exactly-k-edges-directed-
	 * weighted-graph/
	 */

	/*
	 * task52 Mobile Numeric Keypad Problem
	 * http://www.geeksforgeeks.org/mobile-numeric-keypad-problem/
	 */

	/*
	 * task53 Minimum Cost Polygon Triangulation
	 * http://www.geeksforgeeks.org/minimum-cost-polygon-triangulation/
	 */

	/*
	 * task54 Longest Even Length Substring such that Sum of First and Second
	 * Half is same
	 * http://www.geeksforgeeks.org/longest-even-length-substring-sum
	 * -first-second-half/
	 */

	/*
	 * task55 Weighted Job Scheduling
	 * http://www.geeksforgeeks.org/weighted-job-scheduling/
	 */

	/*
	 * task56 Count number of ways to reach a given score in a game
	 * http://www.geeksforgeeks.org/count-number-ways-reach-given-score-game/
	 */

	/*
	 * task57 Vertex Cover Problem | Set 2 (Dynamic Programming Solution for
	 * Tree)
	 * http://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming
	 * -solution-tree/
	 */

	/*
	 * task58 Find the minimum cost to reach destination using a train
	 * http://www
	 * .geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-
	 * where-every-station-is-connected-in-one-direction/
	 */

	/*
	 * task59 How to print maximum number of A’s using given four keys
	 * http://www
	 * .geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-
	 * four-keys/
	 */

}
