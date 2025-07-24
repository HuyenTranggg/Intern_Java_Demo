package com.springtransaction.SpringTransaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.*;
import javax.sql.DataSource;
// Danh dau day la file config
@Configuration
// Tu dong tim cac annotation va khai bao chung la bean
@ComponentScan(basePackages = "com.springtransaction.SpringTransaction")
@EnableTransactionManagement // kich hoat quan ly transaction bang @Transactional
public class AppConfig{
	// Cau hinh DataSource (ket noi voi DB)
	@Bean 
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
	
	// Cau hinh EntityManagerFactory (tao va quan ly cac entity)
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource()); // ket noi den db nao
		emf.setPackagesToScan("com.springtransaction.SpringTransaction.entity"); // tim cac lop entity
		
		// Cau hinh hibernate
		// Chi dinh JPA provider la Hibernate
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		
		// Cau hinh cac thuoc tinh cua Hibernate
		Properties  properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create"); // Tự tạo schema
		properties.setProperty("hibernate.show_sql", "true");      // Hiển thị SQL
		properties.setProperty("hibernate.format_sql", "true");
		emf.setJpaProperties(properties);
		return emf;
	}
	// Cau hinh Transaction Manager
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		// lien ket voi EntityManagerFactory o tren
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	// bean template cho Programmatic approach
	@Bean
	public TransactionTemplate transactionTemplate() {
		// truyen transaccitonManager
		return new TransactionTemplate(transactionManager());
	}
}
