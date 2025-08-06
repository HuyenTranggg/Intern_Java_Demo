package com.example.errorhandling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.errorhandling.exception.ResourceNotFoundException;

import jakarta.validation.constraints.Size;

@Controller
@RequestMapping("/books")
@Validated
public class BookApiController {
    // endpoint demo custom exception
    @GetMapping("/{id}")
    @ResponseBody
    public String getBookById(@PathVariable Long id) {
        // logic tượng trưng tìm sách theo id và không tìm thấy
        // trong thực tế sẽ gọi service để lấy dữ liệu từ database
        if (id != 99) {
            throw new ResourceNotFoundException("Book not found with ID: " + id);
        }
        // (Trong thực tế sẽ trả về trang chi tiết sách)
        return "success";
    }

    // endpoint demo ConstraintViolationException
    @GetMapping("/search")
    public ResponseEntity<String> searchBooks(@RequestParam @Size(min = 3, message = "Search keyword must have at least 3 characters") String keyword) {
        // logic tìm kiếm sách
        return ResponseEntity.ok("Search results for keyword: " + keyword);
    }
}
