package coursework;

public abstract class Product {
	
	private int productID;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantityInStock;
	private double originalPrice;
	private double retailPrice; 
	private String whichProduct; 
	
	public Product(int prodID, String prodBrand, String prodColour, String prodConnectivity, int inStock, double OGPrice, double RTPrice, String whichProd) {
		
		this.productID = prodID;
		this.brand = prodBrand;
		this.colour = prodColour;
		this.connectivity = prodConnectivity;
		this.quantityInStock = inStock;
		this.originalPrice = OGPrice;
		this.retailPrice = RTPrice; 
		this.whichProduct = whichProd;
		
	} //end of constructor 
	
	public int getProductID() {
		return this.productID;
	}

	public String getProductBrand() {
		return this.brand;
	}
	
	public String getProductColour() {
		return this.colour;
	}
	
	public String getProductConnectivity() {
		return this.connectivity;
	}
	
	public int getQuantityInStock() {
		return this.quantityInStock; 
	}
	
	public void decrementQuantityInStock(int number) {
		this.quantityInStock = (this.quantityInStock-number); //decrements the quantity in stock by a certain number (passed through the parameters) 
	}
	
	public double getOriginalPrice() {
		return this.originalPrice;
	}
	
	public double getRetailPrice() {
		return this.retailPrice;
	}
	
	public String getWhichProduct() {
		return this.whichProduct;
	}
	
	
} //end of class 

