## Mô tả chung

Phần demo này triển khai hai nội dung chính:

1. *Thymeleaf:* Xây dựng tầng giao diện người dùng động, có thể tái sử dụng và dễ bảo trì.
2. *Spring Security:* Xây dựng một hệ thống bảo mật toàn diện, bao gồm xác thực và phân quyền chi tiết.

### 

### I. Tầng Giao diện với Thymeleaf

#### ► Mục tiêu

* *Giao diện động:* Tạo ra các trang HTML có khả năng hiển thị dữ liệu được xử lý từ phía server.
* *Tái sử dụng code:* Xây dựng các thành phần giao diện chung (Layout, Header, Footer) để tránh lặp lại code và dễ dàng thay đổi toàn cục.
* *Form tương tác:* Xây dựng các form có khả năng binding dữ liệu hai chiều và hiển thị lỗi validation một cách thân thiện với người dùng.
* *Toàn cầu hóa (i18n):* Hỗ trợ đa ngôn ngữ (Tiếng Việt/Anh) cho ứng dụng.

#### ► Thể hiện trong Code

* *Tích hợp \& Cấu trúc:*

  * Thêm dependency spring-boot-starter-thymeleaf.
  * Tổ chức các file .html trong src/main/resources/templates theo từng chức năng.

* *Tái sử dụng Fragments:*

  * Tạo file fragments/layout.html chứa các thành phần chung.
  * Sử dụng th:fragment để định nghĩa và th:insert`/th:replace` để chèn các fragment vào các trang khác.

* *Xây dựng Form \& Validation:*

  * Sử dụng th:object để liên kết form với một đối tượng DTO trong Model.
  * Sử dụng th:field để tự động tạo id, name, value, giúp giữ lại dữ liệu khi form có lỗi.
  * Sử dụng #fields.hasErrors() và th:errors để hiển thị các thông báo lỗi validation từ backend.

* *Triển khai Đa ngôn ngữ:*

  * Cấu hình LocaleResolver trong WebConfig để quản lý ngôn ngữ của phiên làm việc.
  * Tạo các file messages\_vi.properties và messages\_en.properties.
  * Sử dụng cú pháp #{...} trong các template để hiển thị nội dung theo ngôn ngữ hiện tại.



### II. Hệ thống Bảo mật với Spring Security

#### ► Mục tiêu

* *Xác thực (Authentication):* Đảm bảo chỉ những người dùng hợp lệ, đã đăng nhập mới có thể truy cập vào các tài nguyên được bảo vệ.
* *Phân quyền (Authorization) đa lớp:* Kiểm soát quyền truy cập của người dùng ở cả hai cấp độ:

  1. *Cấp độ URL:* Ngăn chặn người dùng truy cập vào các URL mà họ không có quyền.
  2. *Cấp độ Nghiệp vụ (Method Security):* Bảo vệ các phương thức xử lý logic quan trọng trong tầng Service, tạo ra một lớp bảo mật sâu và an toàn hơn.

* *Hệ thống quyền linh hoạt:* Hỗ trợ phân quyền dựa trên cả *Vai trò (Role)* chung và *Quyền hạn (Permission)* cụ thể, chi tiết.



#### ► Thể hiện trong Code

* *Thiết kế Database \& Entities:*

  * Xây dựng schema database với các bảng users, roles, permissions, products.
  * Tạo các lớp Entity tương ứng.

* *Cấu hình Xác thực:*

  * Triển khai JpaUserDetailsService, giúp Spring Security đọc thông tin người dùng từ database thông qua UserService.
  * Định nghĩa PasswordEncoder (BCrypt) như một Bean để mã hóa mật khẩu một cách an toàn.

* *Cấu hình Phân quyền:*

  * *Bảo mật tầng URL:* Trong SecurityConfig, cấu hình SecurityFilterChain sử dụng authorizeHttpRequests để định nghĩa các quy tắc truy cập. Ví dụ: .requestMatchers("/admin/\*\*").hasAuthority("ROLE\_ADMIN").
  * *Bảo mật tầng Phương thức:*

    * Kích hoạt bằng @EnableMethodSecurity trong SecurityConfig.
    * Áp dụng các annotation @PreAuthorize, @PostAuthorize, @Secured, @RolesAllowed trực tiếp lên các phương thức trong ProductServiceImpl và UserServiceImpl để kiểm soát quyền truy cập dựa trên logic nghiệp vụ.

* *Tích hợp với Thymeleaf:*

  * Thêm dependency thymeleaf-extras-springsecurity6.
  * Sử dụng các thuộc tính của sec:\* (ví dụ: sec:authorize, sec:authentication) trong các file HTML để hiển thị/ẩn các thành phần giao diện dựa trên trạng thái đăng nhập và quyền của người dùng.

### 

### Cách kiểm thử

1. *Cài đặt Database (MySQL):*

   * Cập nhật username và password trong application.properties.
   * Ứng dụng sẽ tự động chạy các file schema.sql và data.sql để tạo bảng và chèn dữ liệu mẫu khi khởi động.

2. *Chạy ứng dụng:* Khởi động file ThymeleafApplication.java.
3. *Kiểm tra luồng Xác thực \& Phân quyền URL:*

   * Truy cập http://localhost:8080.
   * Truy cập http://localhost:8080/admin/dashboard để kiểm tra phân quyền.
   * Bạn có thể đăng nhập với các tài khoản khác nhau để kiểm tra (Tài khoản đã được ghi ngay dưới link truy cập).
   * Ở trang dashboard, click vào các link để kiểm thử các quy tắc Method Security đã được định nghĩa. Quan sát kết quả (thành công hoặc lỗi Access Denied) được hiển thị trên dashboard.
   * Lặp lại với tài khoản còn lại để thấy sự khác biệt trong phân quyền.