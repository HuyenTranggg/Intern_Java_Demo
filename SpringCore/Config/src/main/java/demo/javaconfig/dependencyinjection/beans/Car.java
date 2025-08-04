package demo.javaconfig.dependencyinjection.beans;

public class Car {
    private final Engine engine;
    private final Transmission transmission;

    public Car(Engine engine, Transmission transmission) {
        this.engine = engine;
        this.transmission = transmission;
        System.out.println(">>> Car bean created with dependencies.");
    }

    public void drive() {
        engine.start();
        transmission.shift();
        System.out.println("Car is driving.");
    }
}