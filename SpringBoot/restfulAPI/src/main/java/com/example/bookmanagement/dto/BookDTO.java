package com.example.bookmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // Lombok: Tự tạo getter, setter, toString...
public class BookDTO {

    private Long id; // Dùng để trả về, không yêu cầu khi tạo mới

    @NotBlank(message = "Tiêu đề không được để trống") // Validation: Không được rỗng hoặc chỉ chứa khoảng trắng
    private String title;

    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Min(value = 1000, message = "Năm xuất bản phải là một số hợp lệ") // Validation: Giá trị nhỏ nhất là 1000
    private Integer publicationYear;
}