package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.ServiceConsumer;
import demo.Student;

public class Autowire {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("autowire-config.xml");
		
		System.out.println("--------------");
		Student std = (Student) context.getBean("autowireByType");
		System.out.println(std.getAddress());
	}

}
