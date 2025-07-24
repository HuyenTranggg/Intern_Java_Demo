package demo.javaconfig.lazyinit.beans;

public class LazyBean {
    public LazyBean() {
        System.out.println(">>> LazyBean: I am created LAZILY only when first needed!");
    }

    public void doWork() {
        System.out.println("   LazyBean is doing its work.");
    }
}