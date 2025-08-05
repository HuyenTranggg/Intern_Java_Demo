package com.example.thymeleaf.service.impl;

import com.example.thymeleaf.service.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional(readOnly = true)
public class DemoServiceImpl implements DemoService {
    @Override
    public String getDynamicDataFromService() {
        return "Đây là dữ liệu được gọi từ DemoServiceImpl Bean!";
    }
}