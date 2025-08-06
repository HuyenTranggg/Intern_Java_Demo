package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Quan hệ nhiều-một: Nhiều bài viết thuộc về một tác giả.
    // Minh hoa FetchType.EAGER.
    // Hibernate sẽ tự động tải Author ngay khi một Post được tải.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
}