package demo.javaconfig.createbean.beans;

public class DependentBean {
    private final SimpleBean dependency;

    public DependentBean(SimpleBean dependency) {
        this.dependency = dependency;
        System.out.println(">>> DependentBean has been created with a dependency.");
    }

    public void showDependencyMessage() {
        System.out.println("My dependency says: '" + dependency.getMessage() + "'");
    }
}