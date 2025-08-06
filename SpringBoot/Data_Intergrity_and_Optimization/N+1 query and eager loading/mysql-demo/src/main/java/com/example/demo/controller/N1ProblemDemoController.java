package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.service.N1ProblemDemoService;

import java.util.List;
import java.util.Map;

@RestController
public class N1ProblemDemoController {

    @Autowired
    private N1ProblemDemoService n1ProblemDemoService;

    @PostMapping("/demo/n1/setup")
    public Map<String, String> setupData() {
        n1ProblemDemoService.setupData();
        return Map.of("message", "Đã tạo 2 tác giả và 3 bài viết.");
    }

    @GetMapping("/demo/n1/problem")
    public List<Post> demoN1Problem() {
        System.out.println("\n--- DEMO N+1 PROBLEM: Bắt đầu lấy danh sách Post ---");
        List<Post> posts = n1ProblemDemoService.getPostsTheBadWay();
        System.out.println("--- DEMO N+1 PROBLEM: Đã lấy xong. Chú ý log SQL! ---");
        return posts;
    }

    @GetMapping("/demo/n1/solution")
    public List<Post> demoN1Solution() {
        System.out.println("\n--- DEMO N+1 SOLUTION: Bắt đầu lấy danh sách Post ---");
        List<Post> posts = n1ProblemDemoService.getPostsTheGoodWay();
        System.out.println("--- DEMO N+1 SOLUTION: Đã lấy xong. Chú ý log SQL! ---");
        return posts;
    }
}