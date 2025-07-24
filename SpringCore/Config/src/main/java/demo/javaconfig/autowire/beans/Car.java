package demo.javaconfig.autowire.beans;

public class Car {
    private final Engine engine;
    public Car(Engine engine) {
        this.engine = engine;
    }
    public void printEngineType() {
        System.out.println("This car is powered by: " + engine.getType());
    }
}