package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.HelloSpringCore;

public class BeanScopes {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean-scopes-config.xml");
		
		// Bean Scopes
		// 1. Singleton (Mặc định)
		HelloSpringCore objA = (HelloSpringCore) context.getBean("helloSpringCore");
		objA.setMessage("Lan");
		objA.sayHello();
		
		HelloSpringCore objB = (HelloSpringCore) context.getBean("helloSpringCore");
		objB.sayHello();
		
		System.out.println();

		// 2. Prototype
		HelloSpringCore objC = (HelloSpringCore) context.getBean("hello_prototype");
		objC.setMessage("Hà");
		objC.sayHello();
		
		HelloSpringCore objD = (HelloSpringCore) context.getBean("hello_prototype");
		objD.sayHello();
	}

}
