package lai_online;

public class Class11 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * 
	 * All Unique Characters II
	 * Determine if the characters of a given string are all unique.
	 * Assumptions
	 * We are using ASCII charset, the value of valid characters are from 0 to 255
	 * The given string is not null
	 * Examples
	 * all the characters in "abA+\8" are unique
	 * "abA+\a88" contains duplicate characters
	 * 
	 * In Java, every integer has 32 bits, i.e, 4 byte
	 * so, we just need 8 integer for the hashMap
	 */
	public static boolean task3_allUnique(String word) {
		if (word == null || word.length() == 0) {
			return true;
		}
		
		int[] vec = new int[8];
		for(int i = 0; i < word.length(); i ++) {
			char curChar = word.charAt(i);
			int pos = (int)curChar;
			int rowIndex = pos / 32;
			int colIndex = pos % 32;
			if (((vec[rowIndex] >>> colIndex) & 1 )!= 0) {
				return false;
			}
			vec[rowIndex] |= 1 << (colIndex);
		}
		
		return true;
	}
}
