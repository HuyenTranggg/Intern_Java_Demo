package com.example.bookmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int status;       // Ví dụ: 200, 404, 500
    private String message;   // Ví dụ: "Success", "Resource Not Found"
    private T data;           // Dữ liệu thực tế được trả về
}