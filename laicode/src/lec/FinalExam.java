package lec;

import java.util.*;
public class FinalExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * 
	 */
	public class PermutationsII {
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
			HashSet<Character> used = new HashSet<Character>();
			for (int i = index; i < array.length; i++) {
				if (!used.contains(array[i])) {
					used.add(array[i]);
					swap(array, i, index);
					helper(array, index + 1, result);
					swap(array, i, index);
				}
			}
		}

		private void swap(char[] array, int left, int right) {
			char tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
		}
	}

}
