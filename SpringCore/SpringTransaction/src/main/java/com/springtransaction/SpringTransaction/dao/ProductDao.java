// Giao tiep voi db qua cac cau lenh sql
package com.springtransaction.SpringTransaction.dao;
import com.springtransaction.SpringTransaction.entity.*;
import org.springframework.stereotype.*;
import jakarta.persistence.*;
import java.util.*;

@Repository // String bean cho tang data access
public class ProductDao{
	@PersistenceContext
	private EntityManager entityManager;
	public void save(Product product) {
		entityManager.persist(product);
	}
	public List<Product> findAll(){
		return entityManager.createQuery("FROM Product", Product.class).getResultList();
	}
	public long count() {
		return entityManager.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
	}
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return entityManager
				.createQuery("SELECT p FROM Product p WHERE p.id = :id",Product.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
	public void deleteAll() {
		// TODO Auto-generated method stub
		entityManager.createQuery("DELETE FROM Product").executeUpdate();
	}
}