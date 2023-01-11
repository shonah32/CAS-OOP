package coursework;

public class Mouse extends Product {
	
	private String mouseType;
	private int buttons; 

	public Mouse(int prodID, String type, String prodBrand, String prodColour, String prodConnectivity, int inStock, double OGPrice,
			double RTPrice, String whichProd, int Buttons) {
		super(prodID, prodBrand, prodColour, prodConnectivity, inStock, OGPrice, RTPrice, whichProd);
		
		this.mouseType = type;
		this.buttons = Buttons; 
		
	} //end of constructor 
	
	public String getMouseType() {
		return this.mouseType;
	}
	
	public int getButtonNums() {
		return this.buttons; 
	}

} //end of class 

