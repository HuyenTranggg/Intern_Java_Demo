package com.example.thymeleaf.controller;

import com.example.thymeleaf.entity.Product;
import com.example.thymeleaf.entity.User;
import com.example.thymeleaf.service.ProductService;
import com.example.thymeleaf.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final UserService userService;

    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        model.addAttribute("authorities", authentication.getAuthorities());
        return "admin-dashboard";
    }

    @GetMapping("/save-product")
    public String testSaveProduct(RedirectAttributes redirectAttributes) {
        try {
            User adminUser = userService.findByUsername("admin")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user admin để gán làm chủ sở hữu"));
            String result = productService.saveProduct(new Product("Sản phẩm mới của Admin", adminUser));
            redirectAttributes.addFlashAttribute("message", "Kết quả từ saveProduct: " + result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi saveProduct: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/profile/{username}")
    public String testGetUserProfile(@PathVariable String username, RedirectAttributes redirectAttributes) {
        try {
            String result = userService.getUserProfile(username);
            redirectAttributes.addFlashAttribute("message", "Kết quả từ getUserProfile: " + result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi getUserProfile: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/product/{id}")
    public String testGetProductById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getProductById(id);
            if (product != null) {
                redirectAttributes.addFlashAttribute("message", "Kết quả từ getProductById: Tìm thấy sản phẩm '" + product.getName() + "' thuộc về user '" + product.getOwner().getUsername() + "'");
            } else {
                redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi getProductById: Không tìm thấy sản phẩm với ID = " + id);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi getProductById: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin-task")
    public String testAdminTask(RedirectAttributes redirectAttributes) {
        try {
            String result = productService.performAdminTask();
            redirectAttributes.addFlashAttribute("message", "Kết quả từ performAdminTask: " + result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi performAdminTask: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/manager-task")
    public String testManagerTask(RedirectAttributes redirectAttributes) {
        try {
            String result = productService.performManagerTask();
            redirectAttributes.addFlashAttribute("message", "Kết quả từ performManagerTask: " + result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi gọi performManagerTask: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }
}