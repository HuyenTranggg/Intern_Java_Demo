package demo.annotation.dependencyinjection;

import demo.annotation.dependencyinjection.config.DIConfig;
import demo.annotation.dependencyinjection.consumers.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DIConfig.class);

        // 3 cách inject dependency
        System.out.println("--- 1. Constructor Injection ---");
        ConstructorInjectedCar car1 = context.getBean(ConstructorInjectedCar.class);
        car1.drive();

        System.out.println("\n--- 2. Setter Injection ---");
        SetterInjectedCar car2 = context.getBean(SetterInjectedCar.class);
        car2.drive();

        System.out.println("\n--- 3. Field Injection ---");
        FieldInjectedCar car3 = context.getBean(FieldInjectedCar.class);
        car3.drive();

        // Ngoài 3 cách tiêm phụ thuộc cơ bản (Constructor, Setter, Field), Spring còn cung cấp các kỹ thuật để bổ trợ cho cơ chế DI.        
        System.out.println("\n--- 4. Collection Injection ---");
        CollectionInjectedGarage garage = context.getBean(CollectionInjectedGarage.class);
        garage.listEngines();

        System.out.println("\n--- 5. Optional Injection (for Null) ---");
        OptionalInjectedComponent optionalComp = context.getBean(OptionalInjectedComponent.class);
        optionalComp.checkAccessory();

        System.out.println("\n--- 6. Value Injection (SpEL, Properties, Empty) ---");
        ValueInjectedDriver driver = context.getBean(ValueInjectedDriver.class);
        driver.showInfo();
    }
}