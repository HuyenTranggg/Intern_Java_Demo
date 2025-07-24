package com.springtransaction.SpringTransaction.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	public Product(String name) {
		this.name = name;
	}
}