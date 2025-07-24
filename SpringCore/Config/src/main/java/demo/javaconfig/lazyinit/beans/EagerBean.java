package demo.javaconfig.lazyinit.beans;

public class EagerBean {
    public EagerBean() {
        System.out.println(">>> EagerBean: I am created EAGERLY during application startup!");
    }

    public void doWork() {
        System.out.println("   EagerBean is doing its work.");
    }
}