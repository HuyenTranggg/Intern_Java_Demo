package demo;

public class Address {
	private String city;

	public Address() {}
    public Address(String city) {
        this.city = city;
    }
    
    public void initialize() {
        System.out.println("Address initialized");
    }
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + "]";
	}
}
