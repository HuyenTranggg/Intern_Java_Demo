package demo;

public class TestBean {
    private String name;
    private int age;

    public TestBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void initialize() {
        System.out.println("TestBean initialized");
    }

    @Override
    public String toString() {
        return "TestBean [name=" + name + ", age=" + age + "]";
    }
}
