package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CreateBean {

	public static void main(String[] args) {
		// Khởi tạo ApplicationContext
		// Cách 1: Từ classpath (src/main/resources)
		ApplicationContext context = new ClassPathXmlApplicationContext("create-bean-config.xml");

		// Cách 2: Từ file nằm ngoài classpath
		// ApplicationContext context = new FileSystemXmlApplicationContext("D:/Intern_Java_Demo/SpringCore/Config/src/main/java/demo/xml/create-bean-config.xml");
	}
}
