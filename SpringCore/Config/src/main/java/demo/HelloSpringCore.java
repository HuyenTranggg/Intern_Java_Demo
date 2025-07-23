package demo;

public class HelloSpringCore {
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void sayHello() {
        System.out.println("Xin chào " + message + "! Chào mừng đến với Spring Core.");
    }
}
