package demo.javaconfig.dependson.config;

import demo.javaconfig.dependson.beans.ApplicationRunner;
import demo.javaconfig.dependson.beans.SystemInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class DependsOnConfig {
    // Bean này PHỤ THUỘC NGẦM vào 'systemInitializer'.
    // @DependsOn("systemInitializer"): Trước khi gọi phương thức để tạo bean 'applicationRunner', bean có tên 'systemInitializer' phải được tạo trước
    @Bean
    @DependsOn("systemInitializer")
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner();
    }
    
    @Bean
    public SystemInitializer systemInitializer() {
        return new SystemInitializer();
    }
    
    // DEMO VỚI NHIỀU PHỤ THUỘC
    // Có thể liệt kê nhiều bean trong @DependsOn
    @Bean
    @DependsOn({"systemInitializer", "anotherInitializer"})
    public ApplicationRunner complexRunner() {
        System.out.println("4. ComplexRunner starting...");
        return new ApplicationRunner();
    }

    @Bean
    public SystemInitializer anotherInitializer() {
        System.out.println("3. Another Initializer running...");
        return new SystemInitializer();
    }
}