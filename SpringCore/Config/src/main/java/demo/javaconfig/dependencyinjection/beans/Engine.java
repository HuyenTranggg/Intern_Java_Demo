package demo.javaconfig.dependencyinjection.beans;

public class Engine {
    public Engine() {
        System.out.println(">>> Engine bean created.");
    }
    public void start() {
        System.out.println("Engine started.");
    }
}