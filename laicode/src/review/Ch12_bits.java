package review;

public class Ch12_bits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = -10;
		System.out.println(a);
//		System.out.println(a>>1);
		int b = a >> 1;
		System.out.println(Integer.toBinaryString(a));
		
		System.out.println("b = " + b);
		System.out.println(Integer.toBinaryString(b));
		
		
		System.out.println("c = " + (a>>> 2));
		System.out.println(Integer.toBinaryString(a >>> 2));
		
		System.out.println(a);
		
		int d = (a >> 2) + (2 << ~2);
		System.out.println((a >> 2) + (2 << ~2));
		System.out.println(Integer.toBinaryString(d));
		System.out.println("------");
		
	}
	
	
	
	
	
	
	
	
	
	

}
