package onceagain;

public class Class12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
		test4();
	}
	
	/*
	 * task1
	 * Determine if a Number is Power of 2
	 * 
	 * 0000010000000
	 */
	public static boolean task1_isPowerOfTwo(int number) {
		if (number <= 0) {
			return false;
		}
		while ((number & 1) == 0) {
			number >>>= 1;
		}
		return number == 1;
	}
	
	public static boolean task1_isPowerOfTwo2(int number) {
		if (number <= 0) {
			return false;
		}
		return (number & (number - 1)) == 0;
	}

	
	/*
	 * task2
	 * All Unique characters
	 * using bit vector
	 */
	public static boolean task2_allUnique(String str) {
		int[] vector = new int[8];
		for(int i = 0; i < str.length(); i ++) {
			char ch = str.charAt(i);
			int rowIdx = ch / 32;
			int colIdx = ch % 32;
			int mask = 1 << colIdx;
			if ((vector[rowIdx] & mask) == 0) {
				vector[rowIdx] |= mask;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static void test2() {
		String str = "abcc";
		boolean rev = task2_allUnique(str);
		System.out.println("rev = " + rev);
	}
	
	/*
	 * task3 number of Different bit
	 */
	
	/*
	 * task4 Hexadecimal Representation
	 * 
	 * 20 -> 14
	 * 41
	 */
	public static String task4_hexRepresent(int num) {
		// assume number >= 0
		String prefix = "0x";
		if (num == 0) {
			return prefix + "0";
		}
		StringBuilder stb = new StringBuilder();
		while(num != 0) {
			int rem = num % 16;
			System.out.println("Rem = " + rem);
			if (rem < 10) {
				stb.append((char)(rem + '0'));  // don't forget to force converted to char
			} else {
				stb.append((char)(rem - 10 + 'A'));
			}
			num = num/16;
		}
		stb.reverse();
		System.out.println("rev  = " + stb.toString());
		return prefix + stb.toString();
	}
	public static void test4() {
		int num = 10;
		System.out.println(task4_hexRepresent(num));
	}
}
