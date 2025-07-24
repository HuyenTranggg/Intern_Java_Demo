package com.springtransaction.SpringTransaction.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class RollBackForAndNoRollBackFor{
	@Autowired
	public ProductDao productDao;
	
	// 1. Nem ra Checked Exception, mac dinh khong rollback
	@Transactional
	public void throwCheckedException_Default() throws IOException{
		System.out.println("Truoc khi Demo so luong san pham la: " + productDao.count());
		productDao.save(new Product("Laptop"));
		throw new IOException("Checked Exception & ko RollBack");
	}
	
	// 2. Nem ra Checked Exception, chi dinh rollback
	@Transactional(rollbackFor = IOException.class)
	public void throwCheckedException_RollBack() throws IOException {
		System.out.println("Truoc khi Demo so luong san pham la: " + productDao.count());
		productDao.save(new Product("Laptop"));
		throw new IOException("Checked Exception & RollBack");
	}
	
	// 3. Nem ra Unchecked Exception, mac dinh rollback
	@Transactional
	public void throwUncheckedException_Default() {
		System.out.println("Truoc khi Demo so luong san pham la: " + productDao.count());
		productDao.save(new Product("Laptop"));
		throw new IllegalStateException("Unchecked Exception & RollBack");
	}
	
	// 4. Nem ra Unchecked Exception & no rollback
	@Transactional(noRollbackFor = IllegalStateException.class)
	public void throwUncheckedException_NoRollBack() {
		System.out.println("Truoc khi Demo so luong san pham la: " + productDao.count());
		productDao.save(new Product("Laptop"));
		throw new IllegalStateException("Unchecked Exception & RollBack");
	}
}