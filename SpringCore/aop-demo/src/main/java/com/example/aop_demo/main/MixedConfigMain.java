package com.example.aop_demo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.aop_demo.config.MixedJavaConfig;
import com.example.aop_demo.service.OperationService;

public class MixedConfigMain {
  public static void main(String[] args) {
    System.out.println("=== Demo AOP voi Java Config ket hop XML ===");
    // Tạo ApplicationContext từ MixedJavaConfig
    ApplicationContext context = new AnnotationConfigApplicationContext(MixedJavaConfig.class);
    OperationService operationService = context.getBean("operationService", OperationService.class);
    
    // Gọi các phương thức để kiểm tra AOP
    System.out.println("-- Goi phuong thuc m1: --");
    operationService.m1("Trang");

    System.out.println("-- Goi phuong thuc m2: --");
    operationService.m2();  

    System.out.println("-- Goi phuong thuc n1 (se nem ngoai le): --");
    try {
      operationService.n1();
    } catch (IllegalArgumentException e) {
      System.out.println("Ngoai le: " + e.getMessage());
    }
  }
}
