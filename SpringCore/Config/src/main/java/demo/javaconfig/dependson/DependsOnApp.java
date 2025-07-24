package demo.javaconfig.dependson;

import demo.javaconfig.dependson.beans.ApplicationRunner;
import demo.javaconfig.dependson.config.DependsOnConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependsOnApp {
    public static void main(String[] args) {
        System.out.println("--- Initializing Spring Container ---");
        // Spring sẽ đọc config và tự động khởi tạo các bean singleton.
        // Thứ tự khởi tạo sẽ được quyết định bởi @DependsOn.
        ApplicationContext context = new AnnotationConfigApplicationContext(DependsOnConfig.class);
        System.out.println("--- Spring Container Initialized ---\n");
    }
}