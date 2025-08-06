package com.example.errorhandling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.example.errorhandling.dto.CreateBookRequest;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/books")
public class BookWebController {

    // get để hiển thị form tạo mới sách
    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("createBookRequest", new CreateBookRequest());
        return "book-form"; // trả về tên view để hiển thị form
    }

    // post để xử lý form tạo mới sách
    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("createBookRequest") CreateBookRequest request, BindingResult bindingResult) {
        // kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            return "book-form"; // nếu có lỗi, trả về lại form với thông báo lỗi. thymeleaf sẽ hiển thị lại nội dung đã nhập
        }
        
        // nếu không có lỗi, chuyển sang trang thành công
        return "redirect:/books/success"; 
    }

    // trang thành công sau khi tạo sách
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // trả về tên view thành công
    }

    // endpoint để demo fallback handler
    @GetMapping("/error")
    public String causeAnError() {
        throw new IllegalStateException("An unexpected internal error occurred. (Demo for fallback handler)");
    }
}
