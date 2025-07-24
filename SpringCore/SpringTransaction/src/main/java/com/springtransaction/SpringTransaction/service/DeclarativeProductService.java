package com.springtransaction.SpringTransaction.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class DeclarativeProductService{
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	public void createProductAndFail(Product product) {
		System.out.println("Declarative Service - " + product.getName());
		productDao.save(product);
		System.out.println("That bai");
		throw new RuntimeException("Loi khi test rollback");
	}
	
	@Transactional
	public void createProductSuccess(Product product) {
		System.out.println("Declarative Service - " + product.getName());System.out.println("");
		productDao.save(product);
		System.out.println("Thanh cong");
	}
}