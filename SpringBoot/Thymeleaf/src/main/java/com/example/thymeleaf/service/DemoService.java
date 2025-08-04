package com.example.thymeleaf.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String getDynamicDataFromService() {
        return "Đây là dữ liệu được gọi từ DemoService Bean!";
    }
}