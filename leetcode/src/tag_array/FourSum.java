package tag_array;

import java.util.*;

public class FourSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Determine if there exists a set of four elements in a given array that
	 * sum to the given target number. Assumptions The given array is not null
	 * and has length of at least 4 Examples A = {1, 2, 2, 3, 4}, target = 9,
	 * return true(1 + 2 + 2 + 4 = 8) A = {1, 2, 2, 3, 4}, target = 12, return
	 * false
	 */

	// O(n ^ 3)
	public boolean exist(int[] array, int target) {
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
					int curSum = array[i] + array[j] + array[start] + array[end];
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
	 * each element record a pair of numbers in the array
	 * left: the smaller index of the pair of numbers
	 * right: the larger index of the pair of numbers
	 * sum : the sum of the pair of numbers
	 */
	private static class Element implements Comparable<Element> {
		int left;
		int right;
		int sum;

		Element(int _l, int _r, int _s) {
			this.left = _l;
			this.right = _r;
			this.sum = _s;
		}

		@Override
		public int compareTo(Element o) {
			// sorted by sum
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

	/*
	 * O(n ^ 2 * log n)
	 */
	public static boolean exist2(int[] array, int target) {
		// sort the array first
		assert array != null && array.length >= 4;
		Element[] pairSum = getPairSum(array);
		Arrays.sort(pairSum);

		int left = 0, right = pairSum.length - 1;
		// pairSum are sorted by sum, then right index, then left index
		while (left < right) {
			// only return true if two pair sums' sum is target and
			// the larger pair sum's left index > smaller pair sum's large index.
			if (pairSum[left].sum + pairSum[right].sum == target && pairSum[left].right < pairSum[right].left) {
				return true;
			} else if (pairSum[left].sum + pairSum[right].sum < target) {
				left++;
			} else {
				// when two pair sums' sum > target, right--
				// when two pair sums' sum == target but larger pair sum's left
				// index <= smaller pair sum's large index, we need to do right--,
				// because the only thing we can guarantee is that right now the smaller pair sum's right index is the smallest one among all pairSums with the same sum.
				right--;
			}
		}

		return false;
	}

	public static Element[] getPairSum(int[] array) {
		int len = array.length;
		Element[] pairSum = new Element[len * (len - 1) / 2];
		int curIndex = 0;

		for (int j = 1; j < array.length; j++) {
			for (int i = 0; i < j; i++) {
				pairSum[curIndex++] = new Element(i, j, array[i] + array[j]);
			}
		}
		return pairSum;
	}

	// Method 3: HashMap O(n ^ 2)
	public boolean fourSumII(int[] array, int target) {
		assert array != null && array.length >= 4;
		HashMap<Integer, Pair> map = new HashMap<Integer, Pair>();
		// the order of traversing i, j is not arbitrary, we should guarantee
		// we can always look at the pair with the smallest right index.

		for (int j = 1; j < array.length; j++) {
			for (int i = 0; i < j; i++) {
				int pairSum = array[i] + array[j];
				if (map.containsKey(target - pairSum) && map.get(target - pairSum).right < i) {
					// no overlap
					return true;
				}

				// we only need to store the pair with smallest right index.
				if (!map.containsKey(target - pairSum)) {
					map.put(pairSum, new Pair(i, j));
				}
			}
		}
		return false;
	}

	static class Pair {
		int left;
		int right;

		Pair(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

}
