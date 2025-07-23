package demo;

public class BookFactory {
	public Book createInstance() {
		System.out.println("Create bean via an instance factory method");
        return new Book();
    }
}
