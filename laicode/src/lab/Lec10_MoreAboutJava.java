package lab;

import java.util.*;


public class Lec10_MoreAboutJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
		test3();
	}
	
	/*
	 * Content:
	 * 1 Generics
	 * 2 Java Concepts
	 */

	
	
	/*
	 * Generics
	 * 
	 * provide a way to re-use the same code with different input types
	 * similar to formal parameters used in method declarations, the difference is that 
	 * the inputs to formal parameters are values, while the inputs to type parameters are types
	 * 
	 * Why do we need Generics ?
	 * (1) Elimination of casts, elimination of errors.
	 *   
	 */
	public static void test1() {
		// before generic is available
		List list = new ArrayList();
		list.add("hello");
		list.add("world");
		// cast is needed here because it is a list of Objects
		String s = (String)list.get(0);
		System.out.println(s);
	
		// after generic is available, after java5
		List<String> list2 = new ArrayList<String>();
		list2.add("hello");
		list2.add("world");
		String s2 = list2.get(0);
		// we don't need to do cast because we know it is a list of String
		System.out.println("------------------------------------");
		// for each and iterator
		for(Object o: list) {
			String cur = (String)o;
			System.out.println(cur);
		}
		
		for(String cur2: list2) {
			System.out.println(cur2);
		}
		
		for(Iterator<String> iter = list2.iterator(); iter.hasNext();) {
			String cur3 = iter.next();
			System.out.println(cur3);
		}
		System.out.println("------------------------------------");
		
		// Strong type check at compile time
		/*
		 * java object is determined at run-time. If not using generics, the checking will be delayed at run-time
		 * 
		 * A java compiler applies strong type checking to generic code and 
		 * issues errors if the code violates type safety.
		 * 
		 * compile time error is always better than run time error
		 */
		
		/*
		 * Re-use the same code for generic types
		 */
	
	}
	
	public static void test2() {
		/*
		 * Inheritance and SubTypes
		 */
		String s = "abc";
		Object o = s;
		Box<String> box = new Box<String>("abc");
		// box.set(1);  compile error, integer is NOT string
		
		
		// this is legal, because box if a box of Objects
		Box<Object> box2 = new Box<Object>("abc");
		box2.set(1);
		box2.set(new Box<String>("abc"));
		
		
		// Legal ? Is a box of String also a box of Object ? 
		Box<String> strBox = new Box<String>("abc");
		// Box<Object> objBox = strBox; // compile error here
		String str = strBox.get();
		
		// next a few lines
		// objBox.set(1);
		// oops, what should we do here? String str = strBox.get();
		
		/*
		 * Give two concrete type A and B (for example, Object and String), 
		 * MyClass<A> has no relationship to MyClass<B>, regardless of whether or not A and B are related. 
		 * The common parent of MyClass<A> and MyClass<B> is Object
		 * 
		 * inheritance 对于type 是没有直接关系的
		 * 
		 * 
		 * So long as the type argument is not varied, the subtyping relationship is preserved.
		 * 
		 * Collection<String>
		 *        |
		 *    List<String>
		 *        |
		 *  ArrayList<String>
		 *  
		 *  type argument 必须是相同， 完全一样
		 *  
		 */
	}
	
	/*
	 * another example
	 */
	public static void test3() {
		int[] input = {1,2,3,4};
		List<List<Integer>> result = subsets(input);
		System.out.println(result);
	}
	public static List<List<Integer>> subsets(int[] input) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		List<Integer> temp = new ArrayList<Integer>();
		dfs(result, temp, input, 0);
		return result;
	}
	
	public static void dfs(List<List<Integer>> result, List<Integer> temp, int[] input, int index) {
		if (index == input.length) {
			result.add(new ArrayList<Integer>(temp));
			return ;
		}
		dfs(result, temp, input, index + 1);
		temp.add(input[index]);
		dfs(result, temp, input, index + 1);
		temp.remove(temp.size() - 1);
	}
	
	/*
	 * E - element
	 * T - type
	 * K - key
	 * V - value
	 */
	
	
	
	/*
	 * Java Concept
	 */
	/*
	 * Difference between java & C++
	 * (1) Platform compatible, write once, run everywhere on JVM
	 *     a) What is JVM ? Java Virtural Machine
	 *     b) JRE vs JDK
	 *        - JRE is the JVM program, Java application need to run on JRE
	 *        - JDK contains the tools for developing Java programs running on JRE, for
	 *          example, it provides the compiler 'javac'
	 * JVM - The Java Virtual Machine (JVM) is an abstract computing machine. 
	 *       The JVM is a program that looks like a machine to the programs written to 
	 *       execute in it. 
	 * 
	 * Compile/Interpret language: 
	 * 1) Java source code -> JVM readable byte code (.java -> .class)
	 * 2) JVM interpret the Java bytecode to pre-compiled routine on machine code. 
	 * or
	 * 3) Just-in-time(JIT) compilation to compile to bytecode to native instructions.
	 * 
	 * 7) Pointers vs References
	 *     a) no pointer arithmetics
	 *  Difference between pointer in C++ and references in Java
	 *      -- references in Java are strong typed
	 *      -- there is no pointer arithmetic on references
	 *  pointers in C++ are much more powerful but also more dangerous. 
	 *  
	 * 8) No operator overloading. "+" "-"
	 * 9) Garbage Collection. 
	 *    a) 
	 * 10) Classes/Objects are always allocated on the Heap
	 * 11) Single inheritance, multiple inheritance only be done by implementing muliple interface. 
	 * 
	 * Java enforce single inheritance to eliminate this kind of problems 
	 * and to make the class hierarchy much cleaner and easier to maintain.
	 * Allowing multiple implementation of different interfaces to 
	 * provide similar support of “multiple inheritance”.
	 * 
	 * 12) ......  
	 */
	
	/*
	 * Garbage Collection
	 * 
	 * More efficiently manage dynamic memory allocation. 
	 * 
	 * 
	 * How do we solve the problem ? How does the GC work in Java? 
	 * Key points:
	 * (1) What is GC component
	 * (2) Steps to finish the job
	 * (3) How to determine what objects can be collected
	 * (4) Generational mechanism, why
	 */
	
	
	/*
	 * How to find if an object can be GCed or NOT ? 
	 * - reference counter. 
	 * 
	 * - graph of dependency of reference and objects
	 * - from a set of the root references/objects, 
	 *     find all the objects reachable through the path on the dependency, 
	 *     other ones are not needed and can be GCed.
	 * There are four kinds of GC roots in Java:
	 * 1 Local variables are kept alive by the stack of a thread. 
	 * 2 Active Java threads are always considered live objects and are therefore GC roots.
	 * 3 Static variables are referenced by their glasses. 
	 * 4 JNI References
	 */
	
	
	
	
}


class Box <T> {
	T t;
	public Box(T t) {
		this.t = t;
	}
	public T get() {
		return t;
	}
	
	public void set(T t) {
		this.t = t;
	}
 }
