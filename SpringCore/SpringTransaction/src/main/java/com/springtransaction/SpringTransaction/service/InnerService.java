package com.springtransaction.SpringTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;


@Service
public class InnerService{
	@Autowired
	private ProductDao productDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void required_method_and_fail() {
		productDao.save(new Product("Inner-Product-Required"));
		throw new RuntimeException("Rollback Inner-REQUIRED");
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void requires_new_method() {
		productDao.save(new Product("Inner-Product-RequiredNew"));
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void supports_method_and_fail() {
		productDao.save(new Product("Inner-Product-Supports"));
		throw new RuntimeException("loi tu Inner-SUPPORTS");
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public void mandatory_method() {
		productDao.save(new Product("Inner-Product-Mandatory"));
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void not_supported_method() {
		productDao.save(new Product("Inner-Product_NotSupported"));
	}
	
	@Transactional(propagation = Propagation.NEVER)
	public void never_method() {
		// 
	}
	
	@Transactional(propagation = Propagation.NESTED)
	public void nested_method_and_fail() {
		productDao.save(new Product("Inner-Product-Nested"));
		throw new RuntimeException("Rollback Inner-NESTED");
	}
}