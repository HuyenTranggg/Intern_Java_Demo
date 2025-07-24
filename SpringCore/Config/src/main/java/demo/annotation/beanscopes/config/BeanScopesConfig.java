package demo.annotation.beanscopes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "demo.annotation.beanscopes")
public class BeanScopesConfig {
}