package ood;

public class Manager extends Employee {
	
	private String projectName;
	public Manager(String name, int age, String id, int level, String projectName) {
		super(name, age, id, level);
		this.projectName = projectName;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public int calculageSalary(double performanceScore) {
		int lev = 5;
		int salary = (int)(getBaseSalary(lev) * (1 + 0.25 * performanceScore));
		return salary;
	}
	
	private int getBaseSalary(int level) {
		return level * 1000;
	}

}
