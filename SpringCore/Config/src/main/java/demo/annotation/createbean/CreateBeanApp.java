package demo.annotation.createbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.annotation.createbean.config.CreateBeanConfig;

public class CreateBeanApp {
    public static void main(String[] args) {
    	System.out.println("--- Initializing Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(CreateBeanConfig.class);
        System.out.println("--- Spring Container Initialized ---");

        System.out.println("\n--- Listing all beans created by Spring ---");
        for (String beanName : context.getBeanDefinitionNames()) {
            if (!beanName.startsWith("org.springframework")) {
                System.out.println("Bean Name: " + beanName);
            }
        }
    }
}
