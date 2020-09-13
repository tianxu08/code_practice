package ood;


/*
 * Builder
 */
public class User {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User.UserBuilder("San", "Zhang").age(20).phone("12345").address("Fake Address").build();
		
		// easier to understand
		User.UserBuilder builder = new User.UserBuilder("li", "Si");
		builder.age(20);
		builder.phone("12345");
		builder.address("Fake Address");
		User user2 = builder.build();
	}
	
	
	private final String firstName;
	private final String lastName;
	private int age;
	private String phone;
	private String address;
	
	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	
	public static class UserBuilder{
		private final String firstName;
		private final String lastName;
		private int age = 0;
		private String phone = "";
		private String address = "";
		public UserBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		// all the following methods are used to set values for optional fields
		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}
		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}
		public User build() {
			return new User(this);
		}
	}
}
