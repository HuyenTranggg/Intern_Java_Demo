package com.example.aop_demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.example.aop_demo")
@EnableAspectJAutoProxy // bật AOP
public class AppConfig {
}
