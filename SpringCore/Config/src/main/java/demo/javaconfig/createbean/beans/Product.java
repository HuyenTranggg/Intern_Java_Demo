package demo.javaconfig.createbean.beans;

public class Product {
    private final String origin;
    
    public Product(String origin) { 
    	this.origin = origin; 
    }
    
    public String getOrigin() { 
    	return origin; 
    }
}