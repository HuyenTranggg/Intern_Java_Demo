package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependsOn {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("depends-on-config.xml");
	}

}
