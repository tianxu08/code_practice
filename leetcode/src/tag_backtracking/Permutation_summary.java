package tag_backtracking;

import java.util.*;

public class Permutation_summary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 1 permutation without duplicate
	// 1. DFS solution with swapping
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] array = set.toCharArray();
		helper(array, 0, result);
		return result;
	}

	private void helper(char[] array, int index, List<String> result) {
		if (index == array.length) {
			result.add(new String(array));
			return;
		}
		for (int i = index; i < array.length; i++) {
			swap(array, index, i);
			helper(array, index + 1, result);
			swap(array, index, i);
		}
	}

	private void swap(char[] array, int left, int right) {
		char tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	// 2. Solution to maintain the order of all the permutations.
	public List<String> permutationsWithOrder(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] arraySet = set.toCharArray();
		Arrays.sort(arraySet);
		// record which index has been used.
		boolean[] used = new boolean[arraySet.length];
		StringBuilder cur = new StringBuilder();
		helperWithOrder(arraySet, used, cur, result);
		return result;
	}

	private void helperWithOrder(char[] array, boolean[] used,
			StringBuilder cur, List<String> result) {
		if (cur.length() == array.length) {
			result.add(cur.toString());
			return;
		}
		// when picking the next char, always according to the order.
		for (int i = 0; i < array.length; i++) {
			if (!used[i]) {
				used[i] = true;
				cur.append(array[i]);
				helperWithOrder(array, used, cur, result);
				used[i] = false;
				cur.deleteCharAt(cur.length() - 1);
			}
		}
	}

	
	/*
	 * permutation with dup
	 */
	public List<String> permutations_dup(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] array = set.toCharArray();
		helper_dup(array, 0, result);
		return result;
	}

	private void helper_dup(char[] array, int index, List<String> result) {
		if (index == array.length) {
			result.add(new String(array));
			return;
		}
		HashSet<Character> used = new HashSet<Character>();
		for (int i = index; i < array.length; i++) {
			if (!used.contains(array[i])) {
				used.add(array[i]);
				swap(array, i, index);
				helper_dup(array, index + 1, result);
				swap(array, i, index);
			}
		}
	}

	

}
