package com.example.thymeleaf.config;

import com.example.thymeleaf.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity // Bật các tính năng bảo mật web của Spring Security.

// @EnableMethodSecurity: Bật tính năng bảo mật ở tầng Service.
// prePostEnabled=true: Bật các annotation @PreAuthorize và @PostAuthorize.
// securedEnabled=true: Bật annotation @Secured.
// jsr250Enabled=true: Bật annotation @RolesAllowed (theo chuẩn JSR-250).
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain định nghĩa một chuỗi các bộ lọc (Filter Chain) mà mỗi request phải đi qua.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 1. Phân quyền dựa trên URL 
                // Cho phép tất cả request truy cập vào các tài nguyên tĩnh và các trang công khai.
                .requestMatchers("/css/**", "/images/**", "/", "/syntax", "/logic", "/login").permitAll()
                
                // 2. Phân quyền dựa trên vai trò (Role-Based)
                // Chỉ những người dùng có vai trò 'ADMIN' mới được truy cập các URL bắt đầu bằng /admin/
                // .hasRole("ADMIN") sẽ tự động kiểm tra quyền 'ROLE_ADMIN'.
                // .requestMatchers("/admin/**").hasRole("ADMIN")

                // 3. Phân quyền dựa trên quyền hạn (Permission-Based)
                // Chỉ những người dùng có quyền 'product:read' mới được xem danh sách sản phẩm.
                .requestMatchers(HttpMethod.GET, "/products").hasAuthority("product:read")
                // Chỉ những người có quyền 'product:write' mới được tạo sản phẩm qua phương thức POST.
                .requestMatchers(HttpMethod.POST, "/products").hasAuthority("product:write")

                // Mọi request khác chưa được định nghĩa ở trên đều yêu cầu phải xác thực (đăng nhập).
                .anyRequest().authenticated()
            )
            // Cấu hình đăng nhập bằng trang login mặc định của Spring.
            .formLogin(withDefaults())
            
            // Cấu hình đăng nhập bằng HTTP Basic
            .httpBasic(withDefaults())

            // Cấu hình cho trang lỗi khi truy cập bị từ chối (403 Forbidden)
            .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"))

            // Cấu hình UserDetailsService để Spring biết cách tìm người dùng.
            .userDetailsService(jpaUserDetailsService);
        return http.build();
    }
}