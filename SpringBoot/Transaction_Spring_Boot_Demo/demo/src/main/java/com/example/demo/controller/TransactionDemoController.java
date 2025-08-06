package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TransactionDemoService;

import java.util.Map;

@RestController
public class TransactionDemoController {

    @Autowired
    private TransactionDemoService transactionDemoService;

    @PostMapping("/demo/transaction")
    public ResponseEntity<?> demoTransaction(
        @RequestParam String authorName,
        @RequestParam(defaultValue = "false") boolean fail
    ) {
        try {
            transactionDemoService.createAuthor(authorName, fail);
            return ResponseEntity.ok(Map.of("message", "Tác giả '" + authorName + "' đã được tạo thành công!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "message", "Giao dịch thất bại! Tác giả '" + authorName + "' đã được ROLLBACK và không được lưu."
            ));
        }
    }
}