package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.UserFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class DemoController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Trang chủ Demo Thymeleaf");
        return "index";
    }

    // === DEMO SYNTAX CƠ BẢN ===
    @GetMapping("/syntax")
    public String syntaxDemo(Model model) {
        // 1. Đẩy dữ liệu ra Model để hiển thị bằng ${...}
        model.addAttribute("welcomeMessage", "Chào mừng đến với Thymeleaf!");

        // 2. Đẩy một đối tượng ra Model
        UserFormDTO currentUser = new UserFormDTO();
        currentUser.setUsername("Nguyễn Văn A");
        currentUser.setEmail("nguyenvana@example.com");
        model.addAttribute("user", currentUser);

        // 3. Dữ liệu cho th:text vs th:utext
        model.addAttribute("safeText", "Đây là thẻ <b>in đậm</b> an toàn.");
        model.addAttribute("unsafeText", "Đây là thẻ <b>in đậm</b> không an toàn.");

        // 4. Dữ liệu cho th:attr và các shortcut
        model.addAttribute("profileUrl", "/images/avatar1.png");
        model.addAttribute("avatarUrl", "/images/avatar2.png");
        model.addAttribute("userId", 99);

        // 5. Dữ liệu cho th:classappend
        model.addAttribute("isUrgent", true);
        model.addAttribute("isImportant", false);

        return "syntax-demo";
    }

    // === DEMO LOGIC: IF/UNLESS/SWITCH/EACH ===
    @GetMapping("/logic")
    public String logicDemo(Model model) {
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("userRole", "ADMIN");
        model.addAttribute("userIsActive", false); // Demo cho th:unless
        model.addAttribute("accessLevel", "full"); // Demo cho th:switch

        // Dữ liệu cho th:each
        List<String> tasks = Arrays.asList("Học Thymeleaf", "Làm bài tập", "Uống cà phê", "Đi ngủ");
        model.addAttribute("tasks", tasks);
        return "logic-demo";
    }

    // === DEMO FRAGMENTS ===
    @GetMapping("/fragments")
    public String fragmentsDemo(Model model) {
        model.addAttribute("pageTitle", "Demo Fragments");
        return "index"; // Trang index sử dụng fragments
    }
}