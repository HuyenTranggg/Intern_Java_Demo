package com.example.aop_demo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.aop_demo.config.AppConfig;
import com.example.aop_demo.service.OperationService;

public class JavaConfigMain {
  public static void main(String[] args) {
    // Tạo ApplicationContext từ AppConfig
    System.out.println("=== Demo AOP voi Java Config ===");
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    // Lấy bean OperationService từ context
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
