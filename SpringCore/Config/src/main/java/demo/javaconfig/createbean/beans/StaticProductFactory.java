package demo.javaconfig.createbean.beans;

public class StaticProductFactory {
    public static Product createProduct() {
        System.out.println(">>> StaticProductFactory is creating a product...");
        return new Product("Made by Static Factory");
    }
}