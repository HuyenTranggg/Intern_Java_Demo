package demo;

public class Student {
	private String name;
    private Address address;

    public Student() {}
    public Student(String name) {
        this.name = name;
    }
    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    
    public void initialize() {
        System.out.println("Student initialized");
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		System.out.println("Setter Injected: " + address.getClass().getSimpleName());
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + "]";
	}
}
