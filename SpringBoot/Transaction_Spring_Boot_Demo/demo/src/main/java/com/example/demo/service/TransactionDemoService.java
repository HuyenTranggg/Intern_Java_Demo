package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;

@Service
public class TransactionDemoService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Author createAuthor(String name, boolean shouldFail) {
        // Bắt đầu Transaction

        Author author = new Author();
        author.setName(name);

        authorRepository.save(author);
        System.out.println("=> Lưu thành công.");

        if (shouldFail) {
            // Cố ý gây ra lỗi để kiểm tra ROLLBACK!
            throw new RuntimeException("exception!");
        }

        System.out.println("Transaction thành công, COMMIT sẽ được thực thi.");
        return author;
    }
}