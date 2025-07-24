package com.springtransaction.SpringTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class TimeOutDemo{
	@Autowired
	private ProductDao productDao;
	// Transaction nay co thoi gian chay toi da 3s, sau 3s se bi rollback
	@Transactional(timeout = 3)
	public void demonstrateTimeout() {
		System.out.println("So luong trong DB truoc Timeout: " + productDao.count());
		productDao.save(new Product("Laptop"));
		// sleep = 5s
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e){
			
		}
		
		System.out.println("Tiep tuc"); // se khong in duoc dong nay do bi rollback
	}
}