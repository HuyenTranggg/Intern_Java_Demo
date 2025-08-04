package com.example.aop_demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAspectJAutoProxy // Bật AOP
@ImportResource("classpath:application-context-mixed.xml") // Nhập bean từ file XML
@ComponentScan(basePackages = "com.example.aop_demo.aspect") // Chỉ quét package aspect để tìm LoggingAspect
public class MixedJavaConfig {
}
