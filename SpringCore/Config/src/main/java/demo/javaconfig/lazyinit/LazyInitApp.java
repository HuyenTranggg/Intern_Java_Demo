package demo.javaconfig.lazyinit;

import demo.javaconfig.lazyinit.beans.LazyBean;
import demo.javaconfig.lazyinit.config.LazyInitConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyInitApp {
    public static void main(String[] args) {
        System.out.println("--- 1. Initializing Spring Container ---");

        // Khi dòng này thực thi, EagerBean sẽ được tạo, nhưng LazyBean thì chưa.
        ApplicationContext context = new AnnotationConfigApplicationContext(LazyInitConfig.class);

        System.out.println("--- 2. Spring Container Initialized ---");
        System.out.println("    (Notice that only EagerBean's constructor was called)");

        System.out.println("\n--- 3. Requesting the LazyBean for the first time ---");
        // Khi dòng getBean() này được gọi, Spring mới bắt đầu tạo instance của LazyBean.
        LazyBean lazyBean = context.getBean(LazyBean.class);
        lazyBean.doWork();

        System.out.println("\n--- 4. Requesting the LazyBean for the second time ---");
        // Lần này, Spring sẽ trả về instance đã được tạo ở bước 3, không tạo lại.
        LazyBean sameLazyBean = context.getBean(LazyBean.class);
        sameLazyBean.doWork();
        
        System.out.println("\nAre both lazy beans the same instance? " + (lazyBean == sameLazyBean));
    }
}