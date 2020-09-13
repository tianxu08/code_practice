package lintcode;

import java.util.HashMap;

public class Task301_400 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/*
	 * task399
	 * http://www.lintcode.com/en/problem/nuts-bolts-problem/
	 * Nuts & Bolts Problem
	 * Given a set of n nuts of different sizes and n bolts of different sizes.
	 *  There is a one-one mapping between nuts and bolts. 
	 *  Comparison of a nut to another nut or a bolt to another bolt is not allowed. 
	 *  It means nut can only be compared with bolt and 
	 *  bolt can only be compared with nut to see which one is bigger/smaller.
	 *  We will give you a compare function to compare nut with bolt.
	 *  
	 *  Example
	 *  Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
	 *  Your code should find the matching bolts and nuts.
	 *  one of the possible return:
	 *  nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
	 *  we will tell you the match compare function. If we give you another compare function.
	 *  the possible return is the following:
	 *  nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
	 *  So you must use the compare function that we give to do the sorting.
	 *  The order of the nuts or bolts does not matter. You just need to find the matching
	 */
	public static void sortNutsAndBolts(String[] nuts, String[] bolts){
		NBComparator compare = new NBComparator(nuts);
		sortNutsAndBolts(nuts, bolts, compare);
	}
	
	
	public  static void sortNutsAndBolts(String[] nuts, String[] bolts,
			NBComparator compare) {
		if (nuts == null || bolts == null)
			return;
		if (nuts.length != bolts.length)
			return;
		
		qsort(nuts, bolts, compare, 0, nuts.length - 1);
	}

	private static void qsort(String[] nuts, String[] bolts, NBComparator compare,
			int l, int u) {
		if (l >= u)
			return;
		// find the partition index for nuts with bolts[l]
		int part_inx = partition(nuts, bolts[l], compare, l, u);
		// partition bolts with nuts[part_inx]
		partition(bolts, nuts[part_inx], compare, l, u);
		// qsort recursively
		qsort(nuts, bolts, compare, l, part_inx - 1);
		qsort(nuts, bolts, compare, part_inx + 1, u);
	}

	private static int partition(String[] str, String pivot, NBComparator compare,
			int l, int u) {
		//
		int m = l;
		for (int i = l + 1; i <= u; i++) {
			if (compare.cmp(str[i], pivot) == -1
					|| compare.cmp(pivot, str[i]) == 1) {
				//
				m++;
				swap(str, i, m);
			} else if (compare.cmp(str[i], pivot) == 0
					|| compare.cmp(pivot, str[i]) == 0) {
				// swap nuts[l]/bolts[l] with pivot
				swap(str, i, l);
				i--;
			}
		}
		// move pivot to proper index
		swap(str, m, l);

		return m;
	}

	private static void swap(String[] str, int l, int r) {
		String temp = str[l];
		str[l] = str[r];
		str[r] = temp;
	}
	
	public static class NBComparator{
		HashMap<String, Integer> map;
		public NBComparator(String[] str) {
			map = new HashMap<String, Integer>();
			for(int i = 0; i < str.length; i ++) {
				String curStr = str[i];
				map.put(curStr, i);
			}
		}
		//
		public int cmp(String str, String pivotStr) {
			if (!map.containsKey(str)) {
				return 1;
			}
			int strIdx = map.get(str);
			int pivotIdx = map.get(pivotStr);
			if (strIdx == pivotIdx) {
				return 0;
			}
			return strIdx < pivotIdx ? -1 : 1; 
		}
	}

}
