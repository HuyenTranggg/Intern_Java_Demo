package demo.javaconfig.createbean;

import demo.javaconfig.createbean.beans.DependentBean;
import demo.javaconfig.createbean.beans.Product;
import demo.javaconfig.createbean.beans.SimpleBean;
import demo.javaconfig.createbean.config.CreateBeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CreateBeanApp {
    public static void main(String[] args) {
        System.out.println("--- Initializing Spring Container ---\n");
        ApplicationContext context = new AnnotationConfigApplicationContext(CreateBeanConfig.class);
        System.out.println("\n--- Spring Container Initialized ---\n");

        System.out.println("--- 1: Getting the default named bean ---");
        SimpleBean bean1 = context.getBean("simpleBean", SimpleBean.class);
        System.out.println("Message: " + bean1.getMessage());

        System.out.println("\n--- 2: Getting the custom named bean ---");
        SimpleBean bean2 = context.getBean("myCustomBean", SimpleBean.class);
        System.out.println("Message: " + bean2.getMessage());

        System.out.println("\n--- 3: Getting bean with aliases ---");
        SimpleBean main = context.getBean("mainBean", SimpleBean.class);
        SimpleBean alias1 = context.getBean("alias1", SimpleBean.class);
        SimpleBean alias2 = context.getBean("alias2", SimpleBean.class);
        System.out.println("Are main and alias1 the same object? " + (main == alias1));
        System.out.println("Are alias1 and alias2 the same object? " + (alias1 == alias2));

        System.out.println("\n--- 4: Getting bean with dependency ---");
        DependentBean dependentBean = context.getBean(DependentBean.class);
        dependentBean.showDependencyMessage();

        System.out.println("\n--- 5: Getting bean from instance factory ---");
        Product product1 = context.getBean("productFromInstanceFactory", Product.class);
        System.out.println("Product origin: " + product1.getOrigin());

        System.out.println("\n--- 6: Getting bean from static factory ---");
        Product product2 = context.getBean("productFromStaticFactory", Product.class);
        System.out.println("Product origin: " + product2.getOrigin());
    }
}