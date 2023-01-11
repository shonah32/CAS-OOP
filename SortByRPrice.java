package coursework;
import java.util.Comparator;

class sortByRPrice implements Comparator<Product> {
	@Override 
    public int compare(Product prod1, Product prod2)
    {
    	if(prod1.getRetailPrice() < prod2.getRetailPrice()) {
    		return -1;
    	}
    	if(prod1.getRetailPrice() > prod2.getRetailPrice()) {
    		return 1; 
    	}
    	else {
    		return 0; 
    	}
        
    }
    
} //end of class

