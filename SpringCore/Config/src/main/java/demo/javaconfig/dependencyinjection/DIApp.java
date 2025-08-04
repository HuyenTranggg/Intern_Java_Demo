package demo.javaconfig.dependencyinjection;

import demo.javaconfig.dependencyinjection.beans.Car;
import demo.javaconfig.dependencyinjection.config.DIConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DIApp {
    public static void main(String[] args) {
        System.out.println("--- Initializing Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);
        System.out.println("--- Spring Container Initialized ---\n");

        System.out.println("--- Getting car wired by Parameter Injection ---");
        Car car1 = context.getBean("carFromParameterInjection", Car.class);
        car1.drive();

        System.out.println("\n--- Getting car wired by Direct Method Invocation ---");
        Car car2 = context.getBean("carFromMethodInvocation", Car.class);
        car2.drive();
        
        System.out.println("\n--- Checking if both cars are the same ---");
        // 2 bean khác nhau, nhưng cùng dùng chung Engine và Transmission singleton
        System.out.println("Are car1 and car2 the same? " + (car1 == car2)); 
    }
}