package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Đây là truy vấn để GIẢI QUYẾT vấn đề N+1
    // "JOIN FETCH" sẽ yêu cầu Hibernate lấy thông tin Author cùng lúc với Post trong 1 query.
    @Query("SELECT p FROM Post p JOIN FETCH p.author")
    List<Post> findAllWithAuthor();
}