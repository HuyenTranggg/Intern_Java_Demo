package com.example.aop_demo.service;

import org.springframework.stereotype.Service;

@Service("operationService")
public class OperationServiceImpl implements OperationService {

  @Override
  public String m1(String name) {
    System.out.println("Dang thuc thi method m1");
    return "Hello " + name;
  }

  @Override
  public void m2() {
    System.out.println("Dang thuc thi method m2");
  }

  @Override
  public void n1() throws IllegalArgumentException {
    System.out.println("Dang thuc thi method n1");
    throw new IllegalArgumentException("Loi de test @AfterThrowing");
  }

}
