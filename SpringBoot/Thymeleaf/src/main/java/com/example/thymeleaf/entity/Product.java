package com.example.thymeleaf.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "products") 
@Getter
@Setter
@NoArgsConstructor 
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY: chỉ tải thông tin User khi thực sự cần.
    @JoinColumn(name = "owner_id") 
    private User owner;

    public Product(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
}