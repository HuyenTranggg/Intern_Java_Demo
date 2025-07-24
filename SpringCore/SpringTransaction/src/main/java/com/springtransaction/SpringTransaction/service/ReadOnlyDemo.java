package com.springtransaction.SpringTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;

@Service
public class ReadOnlyDemo{
	@Autowired
	private ProductDao productDao;
	
	@Transactional(readOnly = true)
	public void demonstrateReadOnly() {
		productDao.deleteAll();
		productDao.save(new Product("Laptop"));	
		Product product = productDao.findById(1L);
		if (product != null) {
			System.out.println("Hien co san pham: " + product.getName());
		}
		// Thay doi ten san pham
		System.out.println("Thay doi ten san pham khi o readOnly:");
		product.setName("Dien thoai");
		System.out.println("Ten cua san pham hien tai: " + product.getName());
		// ket thuc method nay, thay doi nay se khong duoc cap nhat vi reanOnly = true
		
	}
}