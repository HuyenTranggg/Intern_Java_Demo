package demo.javaconfig.autowire;

import demo.javaconfig.autowire.beans.Car;
import demo.javaconfig.autowire.config.AutowireConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowireApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutowireConfig.class);

        System.out.println("\n--- DEMO: Getting the car with the @Primary dependency ---");
        Car defaultCar = context.getBean("defaultCar", Car.class);
        defaultCar.printEngineType();

        System.out.println("\n--- DEMO: Getting cars with @Qualifier dependencies ---");
        Car luxuryCar = context.getBean("luxuryCar", Car.class);
        luxuryCar.printEngineType();
        
        Car commuterCar = context.getBean("commuterCar", Car.class);
        commuterCar.printEngineType();
    }
}