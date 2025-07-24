package com.example.aop_demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component("loggingAspect")
public class LoggingAspect {
  // Pointcut để chọn tất cả các phương thức trong OperationService
  @Pointcut("execution(* com.example.aop_demo.service.OperationService.*(..))")
  public void operationServiceMethods() {}

  // Pointcut để chọn các phương thức có tên bắt đầu bằng "m"
  @Pointcut("execution(* com.example.aop_demo.service.OperationService.m*(..))")
  public void operationServiceMethodsStartWithM() {}

  // Advice: Chạy trước khi join point thực thi
  @Before("operationServiceMethodsStartWithM()")
  public void beforeAdvice(JoinPoint joinPoint) {
    System.out.println("@Before: Log truoc khi thuc thi phuong thuc: " + joinPoint.getSignature().getName());
  }

  // Advice: Chạy sau khi join point thực hiện thành công
  @AfterReturning(pointcut = "operationServiceMethodsStartWithM()", returning = "result")
  public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
    System.out.println("@AfterReturning: Phuong thuc thuc thi thanh cong: " + joinPoint.getSignature().getName());
    System.out.println("@AfterReturning: Ket qua tra ve: " + result);
  }

  // Advice: Chạy sau khi join point thực hiện, bất kể thành công hay thất bại
  @After("operationServiceMethods()")
  public void afterAdvice(JoinPoint joinPoint) {
    System.out.println("@After: Phuong thuc da thuc thi: " + joinPoint.getSignature().getName());
  }

  // Advice: Chạy khi join point ném ra ngoại lệ
  @AfterThrowing(pointcut = "operationServiceMethods()", throwing = "ex")
  public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
    System.out.println("@AfterThrowing: Phuong thuc " + joinPoint.getSignature().getName() + " da xay ra loi: " + ex.getMessage());
  }

  // Advice bao quanh join point
  @Around("operationServiceMethods()")
  public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("@Around: Truoc khi thuc thi phuong thuc: " + proceedingJoinPoint.getSignature().getName());
    Object result;
    try {
      result = proceedingJoinPoint.proceed(); // Thực thi phương thức
      System.out.println("@Around: Phuong thuc thuc thi thanh cong: " + proceedingJoinPoint.getSignature().getName());
    } catch (Throwable ex) {
      System.out.println("@Around: Phuong thuc " + proceedingJoinPoint.getSignature().getName() + " da xay ra loi: " + ex.getMessage());
      throw ex; // Ném lại ngoại lệ
    }
    long endTime = System.currentTimeMillis();
    System.out.println("@Around: Thoi gian thuc thi cua phuong thuc: " + proceedingJoinPoint.getSignature().getName() + " la " + (endTime - System.currentTimeMillis()) + " ms");
    return result;
  }
}