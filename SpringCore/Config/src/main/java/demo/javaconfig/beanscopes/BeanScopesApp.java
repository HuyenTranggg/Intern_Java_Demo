package demo.javaconfig.beanscopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopesApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Initializing Spring Container ---");
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesConfig.class);
        System.out.println("--- Spring Container Initialized ---\n");


        System.out.println("--- DEMO: SINGLETON SCOPE ---");
        System.out.println("Requesting singleton bean for the 1st time...");
        TimestampedBean singleton1 = context.getBean("singletonScopedBean", TimestampedBean.class);
        System.out.println("Requesting singleton bean for the 2nd time...");
        TimestampedBean singleton2 = context.getBean("singletonScopedBean", TimestampedBean.class);

        System.out.println("Time of singleton1: " + singleton1.getCreationTime());
        System.out.println("Time of singleton2: " + singleton2.getCreationTime());
        System.out.println("Are singleton1 and singleton2 the same object? " + (singleton1 == singleton2));
        System.out.println("----------------------------------\n");

        Thread.sleep(1000);

        System.out.println("--- DEMO: PROTOTYPE SCOPE ---");
        System.out.println("Requesting prototype bean for the 1st time...");
        TimestampedBean prototype1 = context.getBean("prototypeScopedBean", TimestampedBean.class);
        System.out.println("Requesting prototype bean for the 2nd time...");
        TimestampedBean prototype2 = context.getBean("prototypeScopedBean", TimestampedBean.class);

        System.out.println("Time of prototype1: " + prototype1.getCreationTime());
        System.out.println("Time of prototype2: " + prototype2.getCreationTime());
        System.out.println("Are prototype1 and prototype2 the same object? " + (prototype1 == prototype2));
        System.out.println("----------------------------------");
    }
}