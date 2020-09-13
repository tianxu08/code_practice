package lab;


public class Lec9_Bits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	public static void printBinary(int value, int shift) {
		System.out.println(value + " ");
		StringBuilder stb = new StringBuilder();
		
		while (shift != 0) {
			stb.append(value & 1);
			value >>>= 1;
			shift --;
		}
		System.out.println(stb.reverse());
		System.out.println();
	}
	
	public static void test() {
		int a = 1;
		printBinary(a, 32);
		int b = ~a;
		printBinary(b, 32);
		int c = a^ b;
		printBinary(c, 32);
		
		
		
	}
	
	/*
	 * signed right shift and unsigned right shift
	 * >>                      >>>
	 * >> The leftMost position after singed shift depends on sign extension (the most significant bit)
	 * 
	 * 1000 1111 >> 2  
	 * 1110 0011
	 * 
	 * 0011 1111 >> 2
	 * 0000 1111
	 * 
	 * >>> unsigned right shift
	 * shift ZERO to the leftmost position
	 */
	
	/*
	 * generate bit mask
	 * (1)
	 * 1 << k
	 * set the kth bit to 1
	 * 
	 * (2)
	 * ~(1 << k)
	 * set the kth to 0
	 * 
	 * (3)
	 * (1 << (k - j + 1) - 1) << j
	 * bit mask with jth to kth bit as 1
	 * 
	 * (4)
	 * ~((1 << (k - j + 1) - 1) << j)
	 * bit mask with jth to kth bit as 0
	 */
	
	/*
	 * most frequently used
	 * (1) check if kth bit is 0/1
	 * (a >>> k) & 1 == 0 ? 
	 * 
	 * (2) set kth bit to 1
	 * a |= (1 << k) 
	 * 
	 * (3) set kth bit to 0
	 * a &= ~(1 << k)
	 * 
	 * (4) XOR  
	 * a^a = 0 ;  a ^ (~a) = 1; 0 ^ a = a; 1 ^ a = ~a
	 * a ^ a ^ b = (a ^ a) ^ b = 0 ^ b = b
	 * 
	 * (5)
	 * a & (a - 1)  set the rightmost 1 to 0, reduce the number of 1s in the bit representation.
	 *        
	 */
	
	/*
	 * Bit Vector
	 * use each of the bits to denote "true(1)" or "false(0)"
	 * if using integer(32 bits), we can use it as a boolean array with size 32
	 * to denote boolean array with K size, we can use (K - 1)/32 + 1 integers. 
	 * 
	 * size 255 bit vector need 8 ints
	 * size 256 bit vector need 8 ints
	 * size 257 bit vector need 9 ints
	 * 
	 * int[] bitVec = new int[8];
	 * x : x/32 -- index of the int
	 *     x%32 -- which bit of the int
	 */
	
	

}
