package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.Book;

public class ImportingConfigurationFiles {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("importing-configuration-files-config.xml");
		
		Book b = (Book) context.getBean("book");
		System.out.println(b);
	}

}
