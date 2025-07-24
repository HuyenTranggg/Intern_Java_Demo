package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.Author;
import demo.Book;
import demo.Class;
import demo.Student;

public class DependencyInjection {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("di-config.xml");
		System.out.println();
		
		// Dependency Injection
		// 1. Constructor-based DI
		System.out.print("Constructor-based DI: ");
		Author author = (Author) context.getBean("author");
		System.out.println(author);
		
		// 2. Setter-based DI
		System.out.print("Setter-based DI: ");
		Book book = (Book) context.getBean("book");
		System.out.println(book);
		
		System.out.println();
		
		// Inner bean
		System.out.println("---Inner bean---");
		Book fav_book = (Book) context.getBean("fav-book");
		System.out.println(fav_book);
		Student student = (Student) context.getBean("student");
		System.out.println(student);
		
		// Collection
		System.out.println("---Collection---");
		// List
		System.out.print("List: ");
		Class c = (Class) context.getBean("class");
		System.out.println(c);
		// Set
		System.out.print("Set: ");
		Book b = (Book) context.getBean("book-with-coauthors");
		System.out.println(b);
		// Map
		System.out.print("Map: ");
		Class cwfb = (Class) context.getBean("class-with-fav-books");
		System.out.println(cwfb);
		// Properties
		System.out.print("Properties: ");
		Author awi = (Author) context.getBean("author-with-info");
		System.out.println(awi);
	}

}
