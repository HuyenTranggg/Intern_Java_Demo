package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Author;
import com.example.demo.entity.Post;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.PostRepository;

import java.util.List;

@Service
public class N1ProblemDemoService {
    @Autowired private AuthorRepository authorRepository;
    @Autowired private PostRepository postRepository;

    @Transactional
    public void setupData() {
        postRepository.deleteAll();
        authorRepository.deleteAll();

        Author author1 = new Author();
        author1.setName("Nguyễn Văn A");
        authorRepository.save(author1);

        Post post1 = new Post(); post1.setTitle("Bài viết 1 của A"); post1.setAuthor(author1);
        Post post2 = new Post(); post2.setTitle("Bài viết 2 của A"); post2.setAuthor(author1);
        postRepository.saveAll(List.of(post1, post2));

        Author author2 = new Author();
        author2.setName("Trần Thị B");
        authorRepository.save(author2);

        Post post3 = new Post(); post3.setTitle("Bài viết 1 của B"); post3.setAuthor(author2);
        postRepository.save(post3);
    }

    // Gây ra N+1
    public List<Post> getPostsTheBadWay() {
        return postRepository.findAll();
    }

    // Giải quyết N+1
    public List<Post> getPostsTheGoodWay() {
        return postRepository.findAllWithAuthor();
    }
}