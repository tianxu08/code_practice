package onceagain;

import java.util.*;

import debug.Debug;
import ds.TreeNode;

public class Class24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		// test2();
		// test3();
		// test4();
//		test5_1();
//		test7_2();
//		test7_3();
		test();
	}

	/*
	 * task1 Reverse Binary Tree Upside Down Given a binary tree where all the
	 * right nodes are leaf nodes, flip it upside down and turn it into a tree
	 * with left leaf nodes as the root. Examples 1 / \ 2 5 / \ 3 4 is reversed
	 * to 3
	 * 
	 * / \ 2 4 / \ 1 5
	 */
	// return the newRoot Of reversed Tree
	public static TreeNode task1_ReverseBTUpsideDown(TreeNode root) {
		// base case: root == null or root.left == null
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = task1_ReverseBTUpsideDown(root.left);
		root.left.right = root.right;
		root.left.left = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}

	public static void test1() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n1.left = n2;
		n1.right = n5;
		n2.left = n3;
		n2.right = n4;

		Debug.preOrderBT(n1);
		System.out.println();
		Debug.inOrderBT(n1);

		TreeNode newHead = task1_ReverseBTUpsideDown(n1);
		System.out.println();
		System.out.println("-----------");
		Debug.preOrderBT(newHead);
		System.out.println();
		Debug.inOrderBT(newHead);

	}

	/*
	 * task2 All Valid Permutations Of Parentheses II(L pairs of (), M pairs of
	 * [], N pairs of{})
	 */
	public static List<String> task2_AllValidPermutationOfParentheses(int l,
			int m, int n) {
		char[] PS = { '(', ')', '[', ']', '{', '}' };
		int[] remain = { l, l, m, m, n, n };
		int totalLen = 2 * l + 2 * m + 2 * n;
		LinkedList<Character> stack = new LinkedList<Character>();
		List<String> result = new ArrayList<String>();
		task2_helper(PS, remain, totalLen, new StringBuilder(), stack, result);
		return result;
	}

	public static void task2_helper(char[] PS, int[] remain, int totalLen,
			StringBuilder stb, LinkedList<Character> stack, List<String> result) {
		if (stb.length() == totalLen) {
			result.add(stb.toString());
			return;
		}
		for (int i = 0; i < remain.length; i++) {
			if (i % 2 == 0) {
				// left parentheses
				if (remain[i] > 0) { // !!! remember to check remain[i] > 0 !!!
					stack.offerFirst(PS[i]);
					stb.append(PS[i]);
					remain[i]--;
					task2_helper(PS, remain, totalLen, stb, stack, result);

					// backtraking
					stack.pollFirst();
					stb.deleteCharAt(stb.length() - 1);
					remain[i]++;
				}
			} else {
				// i % 2 == 1
				// right parentheses
				if (!stack.isEmpty()) {
					Character preLeftP = stack.peekFirst();
					if (preLeftP == PS[i - 1]) {
						// matches
						stack.pollFirst(); // remove the matched parenthese's
											// index
						stb.append(PS[i]);
						remain[i]--;

						task2_helper(PS, remain, totalLen, stb, stack, result);

						// backtracking
						stack.offerFirst(PS[i - 1]);
						remain[i]++;
						stb.deleteCharAt(stb.length() - 1);
					}
				}

			}
		}
	}

	public static void test2() {
		int l = 1, m = 1, n = 1;
		List<String> result = task2_AllValidPermutationOfParentheses(l, m, n);
		System.out.println(result);
	}

	/*
	 * task3 N Queens
	 */
	public static void test3() {
		int n = 4;
		List<List<Integer>> result = task3_NQueens(n);
		System.out.println(result);
	}

	public static List<List<Integer>> task3_NQueens(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		task3_helper(n, 0, cur, result);
		return result;
	}

	public static void task3_helper(int n, int index, List<Integer> cur,
			List<List<Integer>> result) {
		if (index == n) {
			// we get a valid solution
			result.add(new ArrayList<Integer>(cur));
			return;
		}

		for (int i = 0; i < n; i++) {
			cur.add(i);
			if (task3_isValid(cur)) {
				task3_helper(n, index + 1, cur, result);
			}
			cur.remove(cur.size() - 1);
		}
	}

	// check whether the List is valid or NOT after add the latest element
	public static boolean task3_isValid(List<Integer> cur) {
		int size = cur.size();
		if (size <= 1) {
			return true;
		}
		int lastRow = size - 1;
		int lastCol = cur.get(size - 1);
		for (int rowI = 0; rowI <= size - 2; rowI++) {
			int colI = cur.get(rowI);
			// cannot in one row
			// cannot in one column
			// cannot in the diagonal
			// cannot in counter diagonal
			if (rowI == lastRow || colI == lastCol
					|| (rowI + colI == lastRow + lastCol)
					|| (rowI - colI == lastRow - lastCol)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * task4 All Subsequences Of Sorted String
	 */
	public static List<String> task4_subset2(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null || set.length() == 0) {
			return result;
		}
		char[] array = set.toCharArray();
		StringBuilder stb = new StringBuilder();
		task4_helper(array, stb, 0, result);
		return result;
	}

	public static void task4_helper(char[] set, StringBuilder stb, int index,
			List<String> result) {
		result.add(stb.toString());
		for (int i = index; i < set.length; i++) {
			if (i == index || set[i] != set[i - 1]) {
				stb.append(set[i]);
				task4_helper(set, stb, i + 1, result);
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}

	public static void test4() {
		String set = "abb";
		List<String> result = task4_subset2(set);
		System.out.println(result);
	}

	/*
	 * task5 Two Sum if there are two elements in a given array, the sum of
	 * which is the given target number
	 */
	// sort and two pointers
	// sort N* log N
	public static boolean task5_existSum(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}
		Arrays.sort(array);
		int i = 0, j = array.length - 1;
		while (i < j) {
			int curSum = array[i] + array[j];
			if (curSum == target) {
				return true;
			} else if (curSum > target) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

	public static boolean task5_exist2Sum2(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}
		Set<Integer> mySet = new HashSet<Integer>();
		mySet.add(array[0]);
		for (int i = 1; i < array.length; i++) {
			int curTarget = target - array[i];
			if (mySet.contains(curTarget)) {
				return true;
			} else {
				mySet.add(array[i]);
			}
		}

		return false;
	}

	/*
	 * task5.1 Two Sum all pairs Find all pairs of elements in a given array
	 * that sum to the given target number. Return all the pairs of indices.
	 * Assumptions The given array is not null and has length of at least 2.
	 * Examples A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]] A = {1, 2,
	 * 2, 4}, target = 6, return [[1, 3], [2, 3]]
	 * 
	 * there is duplicates
	 * 
	 * Use HashMap Map<Integer, ArrayList<Integer>> map to store element, and
	 * its indices.
	 */
	public static void test5_1() {
		int[] array = { 1, 2, 2, 4 };
		int target = 6;
		List<List<Integer>> result = task5_1_allPairs_Index(array, target);
		System.out.println(result);
	}

	public static List<List<Integer>> task5_1_allPairs_Index(int[] array,
			int target) {
		// put the element, list of indices of this elements into map
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < array.length; i++) {
			int elem = array[i];
			if (!map.containsKey(elem)) {
				map.put(elem, new ArrayList<Integer>());
			}
			map.get(elem).add(i);
		}

		// traverse the map
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			int curKey = entry.getKey();
			int targetKey = target - curKey;

			// curKey == targetKey
			if (curKey == targetKey) {
				ArrayList<Integer> curList = entry.getValue();
				if (curList.size() >= 2) {
					for (int i = 0; i < curList.size(); i++) {
						for (int j = i + 1; j < curList.size(); j++) {
							List<Integer> curRes = new ArrayList<Integer>();
							curRes.add(curList.get(i));
							curRes.add(curList.get(j));
							result.add(curRes);
						}
					}
				}

			} else {
				// curKey != targetKey
				if (map.containsKey(targetKey)) {
					ArrayList<Integer> curList = map.get(curKey);
					ArrayList<Integer> targetList = map.get(targetKey);
					if (curList == null || targetList == null) {
						continue;
					}
					for (Integer i : curList) {
						for (Integer j : targetList) {
							List<Integer> curRes = new ArrayList<Integer>();
							curRes.add(Math.min(i, j));
							curRes.add(Math.max(i, j));
							result.add(curRes);
						}
					}
				}
			}
			map.put(curKey, null);
		}

		return result;
	}

	/*
	 * task5.2 Find all pairs of elements in a given array that sum to the pair
	 * the given target number. Return all the distinct pairs of values.
	 * Assumptions The given array is not null and has length of at least 2 The
	 * order of the values in the pair does not matter Examples A = {2, 1, 3, 2,
	 * 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
	 */
	public static List<List<Integer>> task5_2_allPairsValues(int[] array,
			int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		// element, counter
		// two cases
		// for elements array[i] x
		// if x * 2 == target, we need make sure that the there is one and only
		// one x in the map
		// in this way, x's counter is 1 at this time
		// if x + y == target, we need to make sure that y is in the map and x
		// is its first time.
		// in this way, x's counter is null in map
		for (int i = 0; i < array.length; i++) {
			int curKey = array[i];
			int targetKey = target - curKey;
			Integer counter = map.get(curKey);
			if (curKey == targetKey && counter != null && counter == 1) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(curKey);
				list.add(curKey);
				result.add(list);
			} else if (map.containsKey(targetKey) && counter == null) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(curKey);
				list.add(targetKey);
				result.add(list);
			}
			if (counter == null) {
				map.put(curKey, 1);
			} else {
				map.put(curKey, counter + 1);
			}
		}

		return result;

	}

	/*
	 * task6 Three Sum Determine if there exists three elements in a given array
	 * that sum to the given target number. Return all the triple of values that
	 * sums to target. Assumptions The given array is not null and has length of
	 * at least 3 No duplicate triples should be returned, order of the values
	 * in the tuple does not matter Examples A = {1, 2, 2, 3, 2, 4}, target = 8,
	 * return [[1, 3, 4], [2, 2, 4]]
	 * 
	 * 
	 * 
	 */
	/*
	 * Time: O(n*n)
	 */
	public static List<List<Integer>> task6_allTriples(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		// sort the array
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			if (i != 0 && array[i] == array[i - 1]) {
				continue;
			}
			int j = i + 1;
			int k = array.length - 1;
			int tempTarget = target - array[i];
			while (j < k) {
				int curSum = array[j] + array[k];
				if (curSum == tempTarget) {
					ArrayList<Integer> line = new ArrayList<Integer>();
					line.add(array[i]);
					line.add(array[j]);
					line.add(array[k]);
					result.add(line);

					j++;
					k--;
					while (j < k && array[j - 1] == array[j]) {
						j++;
					}
					while (j < k && array[k - 1] == array[k]) {
						k--;
					}
				} else if (curSum < tempTarget) {
					while (j + 1 < k && array[j + 1] == array[j]) {
						j++;
					}
					j++;
				} else {
					while (k - 1 > j && array[k - 1] == array[k]) {
						k--;
					}
					k--;
				}
			}
		}
		return result;
	}

	/*
	 * task7 Four Sum
	 */
	
	// Time: O(n^3)
	public static boolean task7_1_fourSum(int[] array, int target) {
		Arrays.sort(array); // O(N * log N)
		for(int i = 0; i < array.length - 3; i ++ ) {
			for(int j = i + 1; j < array.length - 2; j ++) {
				int left = j + 1;
				int right = array.length - 1;
				int curTarget = target - array[i] - array[j];
				while(left < right) {
					int sum = array[left] + array[right];
					if (sum == curTarget) {
						return true;
					} else if (sum < curTarget) {
						left ++;
					} else {
						right --;
					}
				}
			}
		}
		return false;
	}
	
	
	public static class Element implements Comparable<Element> {
		int left;
		int right;
		int sum;
		
		public Element(int left, int right, int sum) {
			// TODO Auto-generated constructor stub
			this.left = left;
			this.right = right;
			this.sum = sum;
		}
		
		@Override
		public int compareTo(Element o) {
			// TODO Auto-generated method stub
			if (this.sum != o.sum) {
				return this.sum < o.sum ? -1 : 1;
			} else if (this.right != o.right) {
				return this.right < o.right ? -1 : 1;
			} else if (this.left != o.left) {
				return this.left < o.left ? -1 : 1;
			}
			return 0;
		}
	}
	
	// Time: O(n*n* log n)
	public static boolean task7_2_fourSum(int[] array, int target) {
		Element[] pairSum = task7_2_getPairSum(array);
		Arrays.sort(pairSum);
		int left = 0, right = pairSum.length - 1;
		// pairSum is sorted by sum, then right index, then left index
		while(left < right) {
			// only return true if two pair sums' sum is target
			// and the larger pair sum's left index > the smaller pair sum's right index
			if (pairSum[left].sum + pairSum[right].sum == target && 
					pairSum[left].right < pairSum[right].left) {
				return true;
			} else if (pairSum[left].sum + pairSum[right].sum < target) {
				left ++;
			} else {
				 // when two pair sums' sum > target, right--
		        // when two pair sums' sum == target but 
				// larger pair sum's left index <= smaller pair sum's large index, 
				// we need to do right--,
		        // because the only thing we can guarantee is that
		        // right now the smaller pair sum's right index is the smallest one
		        // among all pairSums with the same sum.
				right --;
			}
		}
		return false;
	}

	private static Element[] task7_2_getPairSum(int[] array) {
		Element[] pairSum = new Element[array.length * (array.length - 1) / 2];
		int curIndex = 0;
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				pairSum[curIndex++] = new Element(j, i, array[i] + array[j]);
			}
		}
		
		return pairSum;
	}
	
	public static List<List<Integer>> task7_2_fourSum_AllResult(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Element[] pairSum = task7_2_getPairSum(array);
		Arrays.sort(pairSum);
		int left = 0, right = pairSum.length - 1;
		// pairSum is sorted by sum, then right index, then left index
		while(left < right) {
			// only return true if two pair sums' sum is target
			// and the larger pair sum's left index > the smaller pair sum's right index
			if (pairSum[left].sum + pairSum[right].sum == target && 
					pairSum[left].right < pairSum[right].left) {
				List<Integer> oneRes = new ArrayList<Integer>();
				oneRes.add(array[pairSum[left].left]);
				oneRes.add(array[pairSum[left].right]);
				oneRes.add(array[pairSum[right].left]);
				oneRes.add(array[pairSum[right].right]);
				result.add(oneRes);
				System.out.println("hello");
			} else if (pairSum[left].sum + pairSum[right].sum < target) {
				left ++;
			} else {
				// when two pair sums' sum > target, right--
		        // when two pair sums' sum == target but 
				// larger pair sum's left index <= smaller pair sum's large index, 
				// we need to do right--,
		        // because the only thing we can guarantee is that
		        // right now the smaller pair sum's right index is the smallest one
		        // among all pairSums with the same sum.
				right --;
			}
		}
		return result;
	}

	
	public static void test7_2() {
		int[] array = {1,0,-1,0,-2,2};
		int target = 0;
		List<List<Integer>> result = task7_2_fourSum_AllResult(array, target);
		System.out.println(result);
	}
	
	
	public static class Pair{
		int left;
		int right;
		Pair(int l, int r) {
			this.left = l;
			this.right = r;
		}
	}
	
	// Time: O(n*n)
	public static HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
	public static boolean task7_3_fourSum(int[] array, int target) {
		
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < i; j++) {
				int pairSum = array[j] + array[i];
				// the order of traversing i, j is not arbitrary, we should guarantee
			    // we can always look at the pair with the smallest right index.

				if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < j) {
					System.out.println("pairSum = " + pairSum);
					System.out.println("j = " + j);
					return true;
				}
				
				// we only store the pair with smallest right index
				if (!map.containsKey(pairSum)) {
					map.put(pairSum, new Pair(j, i));
				}
			}
		}
		
		
		return false;
	}
	
	public static void test7_3() {
		int[] array = {1, 2, 2, 3, 4};
		int target = 9;
		boolean rev = task7_3_fourSum(array, target);
		System.out.println("rev = " + rev);
		for(Map.Entry<Integer, Pair> entry: map.entrySet()) {
			System.out.println("[ " + entry.getKey() + "  | " + 
							entry.getValue().left + " " + entry.getValue().right + "]");
		}
	}
	
	
	/*
	 * Time: O(n*n * log n)
	 */
	public static void test() {
		int[] nums = {0,0,0,0,0,0};
		int target = 0;
		List<List<Integer>> result = fourSum(nums, target);
		System.out.println(result);
	}
	
	public static List<List<Integer>> fourSum(int[] num, int target) {
		if (num == null || num.length < 4) {
			return new ArrayList<List<Integer>>();
		}
		Map<Integer, List<List<Integer>>> dict = new HashMap<Integer, List<List<Integer>>>();
		for (int i = 0; i < num.length - 1; i++) {
			for (int j = i + 1; j < num.length; j++) {
				int curSum = num[i] + num[j];
				List<Integer> pair = new ArrayList<Integer>(2);
				pair.add(i);
				pair.add(j);
				if (!dict.containsKey(curSum)) {
					dict.put(curSum, new ArrayList<List<Integer>>());
				}
				dict.get(curSum).add(pair);
			}
		}
		// Use HashSet to prevent duplicate result.
		HashSet<List<Integer>> set = new HashSet<List<Integer>>();
		for (Integer curSum : dict.keySet()) {
			List<List<Integer>> sumPair = dict.get(curSum);
			if (dict.containsKey(target - curSum)) {
				if (target - curSum == curSum && sumPair.size() == 1)
					continue;
				
				List<List<Integer>> pairs = dict.get(target - curSum);
				for (List<Integer> pair1 : sumPair) {
					for (List<Integer> pair2 : pairs) {
						// Make sure it is not the same pair.
						if (pair1 == pair2)
							continue;
						// Make sure there is no same element in two pairs.
						if (pair1.contains(pair2.get(0))
								|| pair1.contains(pair2.get(1)))
							continue;
						List<Integer> tmpResult = new ArrayList<Integer>(4);
						tmpResult.add(num[pair1.get(0)]);
						tmpResult.add(num[pair1.get(1)]);
						tmpResult.add(num[pair2.get(0)]);
						tmpResult.add(num[pair2.get(1)]);
						// Sort the list and add it into the set.
						Collections.sort(tmpResult);
						set.add(tmpResult);
					}
				}
			}
		}
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.addAll(set);
		return result;
	}
	
	

}
