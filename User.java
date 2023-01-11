package coursework;

public abstract class User {
	
	private int userID;
	private String username;
	private String name; 
	private Address address; 
	
	public User(int ID, String userName, String Name, Address userAddress) {
		
		this.userID = ID;
		this.username = userName;
		this.name = Name;
		this.address = userAddress; 
		
	
	} //end of constructor  
	
	public int getUserID() {
		return this.userID;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	
} //end of class 

