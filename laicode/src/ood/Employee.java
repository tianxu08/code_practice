package ood;

public abstract class Employee {
	static double bonusRate = 0.1;
	private final String name;
	private int age;
	private String id;
	private int salary;
	private int level;
	
	public Employee(String name, int age, String id, int level) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.id = id;
		this.level = level;
	}
	
	void printInfo() {
		System.out.println("Name: " + name + ": Age: " + age + ": ID" + id);
	}
	
	void setId(String id) {
		this.id = id;
	}
	
	void setAge(int age) {
		this.age = age;
	}
	
	public int calculageSalary(double performanceScore) {
		return -1;
		// calculate the salary based on the employee's level and performance score
	}
	
}

/*
 * C++ Abstract class
 * Java: Interface and Abstract class
 * interface:  completely focus on public function. No data field
 * Abstract: we can define some data field, and implement some function implementation. 
 * 
 * 
 * public interface Employee{
 * 	 // no data field
 *   public int calculateSalary(double performanceScore) {
 *   	// no method implementation before java 8
 *   }
 * }
 * 
 * public abstract class Employee{
 * 		// we have some data field here
 *		// constructor method OK
 *		// method with implementation also OK 
 * }
 * 
 * 
 * we cannot directly initialize abstract class or interface
 * Employee e = new Employee() // Wrong
 * 
 * abstract class can have constructor, but we cannot new it 
 * when there is a lot of parameter, we can use builder to initialize. 
 */
