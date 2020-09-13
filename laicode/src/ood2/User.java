package ood2;


// Builder Method
public class User {
	private final String firstName; // required
	private final String lastName;  // required
	
	private int age; 		// optional
	private String phone;   // optional
	private String address; // optional
	
	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}
	
	public String getFirstName() {
		return this.firstName;
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
	
	public static class UserBuilder {
		private final String firstName;
		private final String lastName;
		private int age = 0;
		private String phone = "";
		private String address; // default value is null;
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User.UserBuilder("San", "Zhang").age(25).phone("123456789").address("Fake address").build();
		
		
		User.UserBuilder builder = new User.UserBuilder("San", "Zhang");
		builder.age(25);
		builder.phone("12345");
		builder.address(".....");
		User user2 = builder.build();
		
	}
	
	
	
	
	
}
