package demo.javaconfig.dependencyinjection.beans;

public class Transmission {
    public Transmission() {
        System.out.println(">>> Transmission bean created.");
    }
    public void shift() {
        System.out.println("Transmission shifted.");
    }
}