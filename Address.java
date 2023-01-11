package coursework;

public class Address { 
	
	protected int houseNum;
	protected String postcode;
	protected String city; 
	
	public Address(int num, String postCode, String City) {
		this.houseNum = num;
		this.postcode = postCode;
		this.city = City;
		
	}
	
	public int getHouseNum() {
		return this.houseNum;
	}
	
	public String getPostcode() {
		return this.postcode;
	}
	
	public String getCity() {
		return this.city;
	}
	
} //end of class 

