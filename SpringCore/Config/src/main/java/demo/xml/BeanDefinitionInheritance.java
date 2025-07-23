package demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.DerivedTestBean;

public class BeanDefinitionInheritance {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean-definition-inheritance-config.xml");
		
		DerivedTestBean iwdc = (DerivedTestBean) context.getBean("inheritsWithDifferentClass");
		DerivedTestBean iwc = (DerivedTestBean) context.getBean("inheritsWithClass");
	}
}