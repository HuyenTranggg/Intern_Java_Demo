package demo;

public class DerivedTestBean extends TestBean {
    
    public DerivedTestBean() {
    }

    public void initialize() {
        System.out.println("DerivedTestBean initialized with name: " + getName() + ", age: " + getAge());
    }

    @Override
    public String toString() {
        return "DerivedTestBean [name=" + getName() + ", age=" + getAge() + "]";
    }
}
