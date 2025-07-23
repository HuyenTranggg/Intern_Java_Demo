package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.Book;

public class LazyInitialized {

	public static void main(String[] args) {
		System.out.println("---Đây là các bean thông thường---");
		ApplicationContext context = new ClassPathXmlApplicationContext("lazy-initialized-config.xml");
		System.out.println();
		
		System.out.println("---Đây là các bean khởi tạo theo kiểu lazy init---");
		Book lz = (Book) context.getBean("lazy");
	}

}
