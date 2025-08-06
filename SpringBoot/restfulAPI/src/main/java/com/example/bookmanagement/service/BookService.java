package com.example.bookmanagement.service;

import com.example.bookmanagement.dto.BookDTO;
import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    // == Phương thức chuyển đổi (Mapping) ==
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublicationYear(book.getPublicationYear());
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        // Không set ID vì ID sẽ được tự động sinh khi tạo mới
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        return book;
    }

    // == Các phương thức xử lý nghiệp vụ ==

    public List<BookDTO> getAllBooks() {
        log.info("SERVICE: Lấy tất cả sách");
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO) // Chuyển đổi từng Book Entity sang BookDTO
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookById(Long id) {
        log.info("SERVICE: Tìm sách với ID: {}", id);
        return bookRepository.findById(id)
                .map(this::convertToDTO); // Nếu tìm thấy, chuyển đổi sang DTO
    }

    public BookDTO createBook(BookDTO bookDTO) {
        log.info("SERVICE: Tạo sách mới từ DTO: {}", bookDTO.getTitle());
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        log.info("SERVICE: Cập nhật sách ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách với ID: " + id));

        // Cập nhật thông tin từ DTO vào Entity đã có
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());

        Book updatedBook = bookRepository.save(book);
        return convertToDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        log.warn("SERVICE: Xóa sách ID: {}", id);
        // Kiểm tra sự tồn tại và ném ra lỗi nếu không tìm thấy
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy sách để xóa với ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}