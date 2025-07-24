package demo.annotation.dependencyinjection.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "demo.annotation.dependencyinjection")
@PropertySource("classpath:application.properties")
public class DIConfig {
}