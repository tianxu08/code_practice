package lai_online2;
import java.util.*;
import ds_lai_online2.*;
public class Class24 {

	/*
	 * task1
	2 Sum
	Easy
	Data Structure
	Determine if there exist two elements in a given array, the sum of which is the given target number.

	Assumptions

	The given array is not null and has length of at least 2
	​Examples

	A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)

	A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)

	A = {2, 4, 1}, target = 4, return false

   */
	public boolean task1_existSum(int[] array, int target) {
		// write your solution here
		if (array == null || array.length == 0) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(array[0]);

		for (int i = 1; i < array.length; i++) {
			if (set.contains(target - array[i])) {
				return true;
			} else {
				set.add(array[i]);
			}
		}
		return false;
	}
	
	
	/*
	 * task2
	Common Elements In Three Sorted Array
	Fair
	Data Structure
	Find all common elements in 3 sorted arrays.

	Assumptions

	The 3 given sorted arrays are not null
	There could be duplicate elements in each of the arrays
	Examples

	A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
	*/
	public List<Integer> common(int[] array1, int[] array2, int[] array3) {
		// write your solution here
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (i < array1.length && j < array2.length && k < array3.length) {
			if (array1[i] == array2[j] && array2[j] == array3[k]) {
				result.add(array1[i]);
				i++;
				j++;
				k++;
			} else if (array1[i] <= array2[j] && array1[i] <= array3[k]) {
				// array1[i] is the smallest.
				i++;
			} else if (array2[j] <= array1[i] && array2[j] <= array3[k]) {
				// array2[j] is the smallest.
				j++;
			} else {
				k++;
			}
		}
		return result;
	}
	
	
	/*
	 * task3
	Reverse Binary Tree
	Fair
	Data Structure
	Given a binary tree where all the right nodes are leaf nodes, flip it upside down and turn it into a tree with left leaf nodes as the root.

	Examples

	        1

	      /    \

	    2        5

	  /   \

	3      4

	is reversed to

	        3

	      /    \

	    2        4

	  /   \

	1      5

	How is the binary tree represented?

	We use the pre order traversal sequence with a special symbol "#" denoting the null node.

	For Example:

	The sequence [1, 2, #, 3, 4, #, #, #] represents the following binary tree:

	    1

	  /   \

	 2     3

	      /

	    4
	 
	*/
	public TreeNode reverse(TreeNode root) {
		// Write your solution here.
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = reverse(root.left);
		root.left.right = root.right;
		root.left.left = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
	
	/*
	 * task3
	2 Sum All Pair I
	Fair
	Data Structure
	Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.

	Assumptions

	The given array is not null and has length of at least 2.

	Examples

	A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]

	A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
	*/
	public List<List<Integer>> allPairs(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> arrayNoDup = new ArrayList<Integer>();
		// put every element into map
		for (int i = 0; i < array.length; i++) {
			ArrayList<Integer> curElemIndexList = null;
			int curElem = array[i];
			if (!map.containsKey(curElem)) {
				curElemIndexList = new ArrayList<Integer>();
				map.put(curElem, curElemIndexList);
			} else {
				curElemIndexList = map.get(curElem);
			}
			curElemIndexList.add(i);
		}
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			arrayNoDup.add((Integer) entry.getKey());
		}
		Collections.sort(arrayNoDup);

		for (int it = 0; it < arrayNoDup.size(); it++) {
			int curKey = arrayNoDup.get(it);
			int targetKey = target - curKey;

			if (map.containsKey(targetKey)) {
				if (curKey != targetKey) {
					for (Integer i : map.get(curKey)) {
						for (Integer j : map.get(targetKey)) {
							ArrayList<Integer> curResult = new ArrayList<Integer>();
							curResult.add(Math.min(i, j));
							curResult.add(Math.max(i, j));
							result.add(curResult);
						}
					}

					map.remove(curKey);
				} else {
					// curKey == targetKey
					ArrayList<Integer> curList = map.get(curKey);
					System.out.println(curList);
					if (curList.size() != 1) {
						for (int i = 0; i < curList.size(); i++) {
							for (int j = i + 1; j < curList.size(); j++) {
								ArrayList<Integer> curResult = new ArrayList<Integer>();
								curResult.add(Math.min(curList.get(i),
										curList.get(j)));
								curResult.add(Math.max(curList.get(i),
										curList.get(j)));
								result.add(curResult);
							}
						}
					}

					map.remove(curKey);
				}
			}
		}
		return result;
	}
	
	/*
	 * task4
	2 Sum All Pair II
	Fair
	Data Structure
	Find all pairs of elements in a given array that sum to the pair the given target number. Return all the distinct pairs of values.

	Assumptions

	The given array is not null and has length of at least 2
	The order of the values in the pair does not matter
	Examples

	A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]

	 */
	public List<List<Integer>> task4_allPairs(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		Arrays.sort(array);
		int i = 0, j = array.length - 1;
		while (i < j) {
			// while(i + 1 < j && array[i+1] == array[i]) {
			// i ++;
			// }
			int curSum = array[i] + array[j];
			if (curSum == target) {
				List<Integer> element = new ArrayList<Integer>();
				element.add(array[i]);
				element.add(array[j]);
				result.add(element);
				while (i + 1 < j && array[i + 1] == array[i]) {
					i++;
				}
				while (i < j - 1 && array[j - 1] == array[j]) {
					j--;
				}
				i++;
				j--;
			} else if (curSum < target) {
				while (i + 1 < j && array[i + 1] == array[i]) {
					i++;
				}
				i++;
			} else {
				while (i < j - 1 && array[j - 1] == array[j]) {
					j--;
				}
				j--;
			}
		}
		return result;
	}
	
	/*
	 * task5
	3 Sum
	Fair
	Data Structure
	Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

	Assumptions

	The given array is not null and has length of at least 3
	No duplicate triples should be returned, order of the values in the tuple does not matter
	Examples

	A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
	*/
	public List<List<Integer>> task5_allTriples(int[] array, int target) {
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
	 * task6
	4 Sum
	Fair
	Data Structure
	Determine if there exists a set of four elements in a given array that sum to the given target number.

	Assumptions

	The given array is not null and has length of at least 4
	Examples

	A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)

	A = {1, 2, 2, 3, 4}, target = 12, return false
	*/
	public boolean task6_exist(int[] array, int target) {
		// write your solution here
		if (array == null || array.length == 0) {
			return false;
		}
		Arrays.sort(array);
		for (int i = 0; i < array.length - 3; i++) {
			for (int j = i + 1; j < array.length - 2; j++) {
				int start = j + 1;
				int end = array.length - 1;
				while (start < end) {
					int curSum = array[i] + array[j] + array[start]
							+ array[end];
					if (curSum == target) {
						return true;
					} else if (curSum < target) {
						start++;
					} else {
						end--;
					}
				}
			}
		}
		return false;
	}
	
	
	/*
	 * task7
	
	All Subsets II
	Hard
	Recursion
	Given a set of characters represented by a String, return a list containing all subsets of the characters.

	Assumptions

	There could be duplicate characters in the original set.
	​Examples

	Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
	Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
	Set = "", all the subsets are [""]
	Set = null, all the subsets are []
	*/

	public List<String> task7_subSets(String set) {
		// write your solution here
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		Arrays.sort(input);
		StringBuilder stb = new StringBuilder();
		task7_helper(input, 0, stb, result);
		return result;
	}

	public void task7_helper(char[] set, int index, StringBuilder stb,
			List<String> result) {
		result.add(stb.toString());
		for (int i = index; i < set.length; ++i) {
			if (i == index || set[i] != set[i - 1]) {
				stb.append(set[i]);
				task7_helper(set, i + 1, stb, result);
				stb.deleteCharAt(stb.length() - 1);
			}
		}
	}
	
	/*
	 * task8
	
	All Valid Permutations Of Parentheses II
	Hard
	Recursion
	Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.

	Assumptions

	l, m, n >= 0
	Examples

	l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
	*/
	public List<String> task8_validParentheses(int l, int m, int n) {
		// write your solution here
		final char[] PS = { '(', ')', '[', ']', '{', '}' };
		int[] remaining = { l, l, m, m, n, n };

		// when we add right parentheses, we need to use the stack to make sure
		// they are in the same type
		Deque<Character> stack = new LinkedList<Character>();

		StringBuilder cur = new StringBuilder();

		List<String> result = new ArrayList<String>();

		int targetLen = 2 * l + 2 * m + 2 * n;

		task8_helper(remaining, PS, stack, cur, targetLen, result);
		return result;
	}

	public void task8_helper(int[] remaining, char[] PS,
			Deque<Character> stack, StringBuilder cur, int targetLen,
			List<String> result) {
		if (cur.length() == targetLen) {
			// get a solution
			result.add(cur.toString());
			return;
		}

		for (int i = 0; i < remaining.length; i++) {
			if (i % 2 == 0) {
				// add left parentheses
				if (remaining[i] > 0) {
					cur.append(PS[i]);
					remaining[i]--;
					stack.offerFirst(PS[i]);
					task8_helper(remaining, PS, stack, cur, targetLen, result);

					remaining[i]++;
					cur.deleteCharAt(cur.length() - 1);
					stack.pollFirst();
				}
			} else {
				// add right parentheses
				if (!stack.isEmpty() && stack.peekFirst().equals(PS[i - 1])) {

					cur.append(PS[i]);
					remaining[i]--;
					stack.pollFirst();
					task8_helper(remaining, PS, stack, cur, targetLen, result);

					stack.offerFirst(PS[i - 1]);
					remaining[i]++;
					cur.deleteCharAt(cur.length() - 1);
				}
			}
		}
	}
	
	

}
