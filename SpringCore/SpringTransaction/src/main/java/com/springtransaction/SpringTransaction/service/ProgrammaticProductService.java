package com.springtransaction.SpringTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class ProgrammaticProductService{
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public void createProductAndFail(Product product) {
		transactionTemplate.execute(status->{
			try {
				System.out.println("Programmatic Service - " + product.getName());
				productDao.save(product);
				System.out.println("That bai");
				throw new RuntimeException("Loi khi test rollback");
			} catch(Exception e) {
				System.out.println("Ngoai le xay ra, rollback.");
				status.setRollbackOnly(); //yeu cau rollback
				throw e; // nem exception
			}
		});
	}
	
	public void createProductSusscess(Product product) {
		transactionTemplate.execute(status -> {
			System.out.println("Programmatic Service - " + product.getName());System.out.println("");
			productDao.save(product);
			System.out.println("Thanh cong");
			return null;
		});
	}
}