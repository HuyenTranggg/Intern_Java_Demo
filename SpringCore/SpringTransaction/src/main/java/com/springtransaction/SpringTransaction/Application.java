package com.springtransaction.SpringTransaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.TransactionTimedOutException;

import com.springtransaction.SpringTransaction.config.AppConfig;
import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;
import com.springtransaction.SpringTransaction.service.DeclarativeProductService;
import com.springtransaction.SpringTransaction.service.InnerService;
import com.springtransaction.SpringTransaction.service.OuterService;
import com.springtransaction.SpringTransaction.service.ProgrammaticProductService;
import com.springtransaction.SpringTransaction.service.ReadOnlyDemo;
import com.springtransaction.SpringTransaction.service.RollBackForAndNoRollBackFor;
import com.springtransaction.SpringTransaction.service.TimeOutDemo;

public class Application{
	public static void main(String[] args) {
		// Khoi tao Spring Container, nap cac bean tu appconfig
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// lay bean
//		DeclarativeProductService declarativeService = context.getBean(DeclarativeProductService.class);
//		ProgrammaticProductService programmaticService = context.getBean(ProgrammaticProductService.class);
		ProductDao productDao = context.getBean(ProductDao.class);
//		
//		// khoi tao
//		System.out.println(productDao.count());
//		
//		// Declarative - Fail case
//		try {
//			declarativeService.createProductAndFail(new Product("Fail"));
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(productDao.count());
//		
//		//Declarative - success case
//		declarativeService.createProductSuccess(new Product("Laptop"));
//		System.out.println(productDao.count());
//		
//		// Programmatic - fail case
//		try {
//			programmaticService.createProductAndFail(new Product("fail"));
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(productDao.count());
//		
//		// Programmatic -success case
//		programmaticService.createProductSusscess(new Product("Laptop"));
//		System.out.println(productDao.count());
		
		
		// Demo Propagation
//		OuterService outerService = context.getBean(OuterService.class);
//		InnerService innerService = context.getBean(InnerService.class);
//		
//		// REQUIRED
//		try {
//			outerService.testRequired_InnerFails();
//		} catch(Exception e) {}
//		System.out.println(productDao.count());
		

//		//Demo readOnly 
//		ReadOnlyDemo readOnlyDemo = context.getBean(ReadOnlyDemo.class);
//		
//	
//		readOnlyDemo.demonstrateReadOnly();
//		
//		Product afterReadOnly = productDao.findById(1L);
//		System.out.println("Ten san pham: " + afterReadOnly.getName());
		
		
		// Demo TimeOut
//		TimeOutDemo timeout = context.getBean(TimeOutDemo.class);
//		try {
//			timeout.demonstrateTimeout();
//		}catch(TransactionTimedOutException e) {
//			System.out.println(e.getClass().getSimpleName());
//		}
//		long countAfterTimeout = productDao.count();
//		System.out.println("So luong trong DB sau Timeout: " + countAfterTimeout);
		
		
		//Demo rollBackFor and noRollBackFor
		RollBackForAndNoRollBackFor demo = context.getBean(RollBackForAndNoRollBackFor.class);
		try {
			demo.throwCheckedException_Default();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Sau khi demo so luong san pham trong DB la: " + productDao.count());
		// Giai phong du lieu va dong Spring container
		context.close();
	}
}