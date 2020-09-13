package lab;

public class Lab_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//others - http://docs.oracle.com/javase/7/docs/api/java/lang/String.html
		
		System.out.println("abc".compareTo("abc")); // 0
		System.out.println("abc".compareTo("abd")); // -1
		System.out.println("ab".compareTo("abcd")); // -2
		
		// StringBuilder, StringBuffer
		// append()
		// insert()
		// delete()
		// deleteCharAt(int index)
		// reverse
		// replace(int start, int end, String str)
		
		// Difference with StringBuilder(used more frequently) and StringBuffer
		// (1) Do the same thing. 
		// (2.1) StringBuffer thread-safe   // like hashtable  synchronized
		// (2,2) StringBuilder not thread-safe  // like hashmap
		
		// StringBuilder is very similar with ArrayList(re-sizable array) -amortized O(m)
		
		String s1 = "abc";
		String s2 = "def";
		System.out.println(s1.concat(s2));
		// Q&A
		// (1)StringBuilder size: 1000 default. When Start JVM
		// (2) s1.concat(s2) 
		// again, this will create a new String object rather than change the value of s1 or s2.

	}
	
	
	
	
	

}
