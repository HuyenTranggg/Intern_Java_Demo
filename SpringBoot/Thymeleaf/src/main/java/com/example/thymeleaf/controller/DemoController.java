package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.UserFormDTO;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // === DEMO FORM VÀ VALIDATION ===
    // Hiển thị form lần đầu
    @GetMapping("/form")
    public String showForm(Model model) {
        // Tạo một đối tượng UserFormDTO rỗng để binding với form
        // th:object sẽ dùng đối tượng này
        model.addAttribute("userFormData", new UserFormDTO());
        return "form-demo";
    }

    // Xử lý dữ liệu từ form 
    @PostMapping("/form-submit")
    public String processForm(@Valid @ModelAttribute("userFormData") UserFormDTO userForm,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        // @Valid: Kích hoạt validation trên đối tượng userForm
        // @ModelAttribute: Lấy dữ liệu từ form và gán vào userForm
        // BindingResult: Chứa kết quả của quá trình validation, PHẢI đứng ngay sau đối tượng @Valid

        // Kiểm tra nếu có lỗi validation
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi, quay trở lại trang form để hiển thị lỗi
            // Thymeleaf sẽ tự động giữ lại các giá trị người dùng đã nhập nhờ th:field
            // và hiển thị lỗi nhờ th:errors và #fields
            return "form-demo";
        }

        // Nếu không có lỗi, xử lý dữ liệu 
        redirectAttributes.addFlashAttribute("submittedUser", userForm);
        
        // Chuyển hướng về một view khác để hiển thị kết quả và tránh việc submit lại form khi người dùng refresh trang         
        return "redirect:/success"; 
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "submitted-form";
    }

    // === DEMO FRAGMENTS ===
    @GetMapping("/fragments")
    public String fragmentsDemo(Model model) {
        model.addAttribute("pageTitle", "Demo Fragments");
        return "index"; // Trang index sử dụng fragments
    }
}