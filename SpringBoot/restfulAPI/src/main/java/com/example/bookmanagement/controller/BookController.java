package com.example.bookmanagement.controller;

import com.example.bookmanagement.dto.BookDTO;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import com.example.bookmanagement.response.ApiResponse;
import com.example.bookmanagement.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooks() {
        log.info("CONTROLLER: Request GET /api/v1/books");
        List<BookDTO> books = bookService.getAllBooks();
        ApiResponse<List<BookDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách sách thành công", books);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> getBookById(@PathVariable Long id) {
        log.info("CONTROLLER: Request GET /api/v1/books/{}", id);
        BookDTO bookDTO = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));
        ApiResponse<BookDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Tìm thấy sách thành công", bookDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
        log.info("CONTROLLER: Request POST /api/v1/books");
        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        ApiResponse<BookDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo sách mới thành công", createdBookDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        log.info("CONTROLLER: Request PUT /api/v1/books/{}", id);
        BookDTO updatedBookDTO = bookService.updateBook(id, bookDTO);
        ApiResponse<BookDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật sách thành công", updatedBookDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBook(@PathVariable Long id) {
        log.info("CONTROLLER: Request DELETE /api/v1/books/{}", id);
        bookService.deleteBook(id);
        ApiResponse<Object> response = new ApiResponse<>(HttpStatus.OK.value(), "Xóa sách thành công", null);
        return ResponseEntity.ok(response);
    }
}