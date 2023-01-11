package coursework;

public class Keyboard extends Product {
	
	private String keyboardType;
	private String layout;

	public Keyboard(int prodID, String type, String prodBrand, String prodColour, String prodConnectivity, int inStock,
			double OGPrice, double RTPrice, String whichProd, String Layout) {
		super(prodID, prodBrand, prodColour, prodConnectivity, inStock, OGPrice, RTPrice, whichProd);
		
		this.keyboardType = type;
		this.layout = Layout;
		
	} //end of constructor 
	
	public String getKeyboardType() {
		return this.keyboardType;
	}
	
	public String getKeyboardLayout() {
		return this.layout;
	}

	
} //end of class 

