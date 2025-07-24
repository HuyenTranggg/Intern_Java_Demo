package demo.javaconfig.createbean.beans;

public class ProductFactory {
    public Product createProduct() {
        System.out.println(">>> ProductFactory instance is creating a product...");
        return new Product("Made by Instance Factory");
    }
}