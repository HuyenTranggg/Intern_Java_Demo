package demo.javaconfig.createbean.beans;

public class SimpleBean {
    public SimpleBean() {
        System.out.println(">>> SimpleBean has been created.");
    }
    
    public String getMessage() {
        return "This is a simple bean.";
    }
}