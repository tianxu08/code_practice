package lai_online2;

public class Class11 {
	/*
	task1
	All Unique Characters II
	Fair
	Data Structure
	Determine if the characters of a given string are all unique.

	Assumptions

	We are using ASCII charset, the value of valid characters are from 0 to 255
	The given string is not null
	Examples

	all the characters in "abA+\8" are unique
	"abA+\a88" contains duplicate characters
	*/
	
	public boolean allUnique(String word) {
		// write your solution here
		if (word == null || word.length() == 0) {
			return true;
		}

		int[] vec = new int[8];
		for (int i = 0; i < word.length(); i++) {
			char curChar = word.charAt(i);
			int pos = (int) curChar;
			int rowIndex = pos / 32;
			int colIndex = pos % 32;
			if (((vec[rowIndex] >>> colIndex) & 1) != 0) {
				return false;
			}
			vec[rowIndex] |= 1 << (colIndex);
		}

		return true;
	}

	/*
	 * task2
	Power Of Two
	Easy
	None
	Determine if a given integer is power of 2.

	Examples

	16 is power of 2 (2 ^ 4)
	3 is not
	0 is not
	-1 is not
	 */
	
	public boolean isPowerOfTwo(int number) {
		// write your solution here

		return number > 0 && (number & (number - 1)) == 0;

	}
	
	/*
	task3
	Number Of Different Bits
	Fair
	None
	Determine the number of bits that are different for two given integers.

	Examples

	5(“0101”) and 8(“1000”) has 3 different bits
	*/
	
	public int diffBits(int a, int b) {
		// write your solution here

		int diff = a ^ b;
		int counter = 0;
		while (diff != 0) {
			if ((diff & 1) == 1) {
				counter++;
			}
			diff >>>= 1;
		}

		return counter;
	}
	
	/*
	task4
	Hexadecimal Representation
	Easy
	String
	Generate the hexadecimal representation for a given non-negative integer number as a string. The hex representation should start with "0x".

	There should not be extra zeros on the left side.

	Examples

	0's hex representation is "0x0"
	255's hex representation is "0xFF"
	*/
	public String hex(int number) {
		// write your solution here

		if (number == 0) {
			return "0x0";
		}
		boolean isNeg = false;
		if (number < 0) {
			isNeg = true;
		}
		char[] map = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		StringBuilder stb = new StringBuilder();
		while (number != 0) {
			stb.append(map[number % 16]);
			number /= 16;
		}
		stb.reverse();

		String result = null;
		if (isNeg) {
			result = "-0x" + stb.toString();
		} else {
			result = "0x" + stb.toString();
		}

		return result;
	}
	
}
