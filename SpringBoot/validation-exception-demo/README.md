# Validation and Exception Handling Demo in Spring Boot

Phần này demo **Validation** và **Exception Handling** trong module Spring Boot.

## Cấu trúc và các thành phần chính đã triển khai

- **Controller**: Logic được chia thành 2 Controller:
  - `BookWebController.java`: Xử lý các yêu cầu liên quan đến việc hiển thị và xử lý form Thymeleaf.
  - `BookApiController.java`: Cung cấp các endpoint RESTful để demo các lỗi 400, 404 và 500.

- **Global Exception Handler**:
  - Tạo lớp `GlobalExceptionHandler.java` với `@RestControllerAdvice` làm trung tâm xử lý lỗi cho toàn bộ ứng dụng.
  - Xây dựng lớp `ErrorResponse.java` để định nghĩa một cấu trúc JSON chuẩn cho các phản hồi lỗi.

- **Các loại lỗi được xử lý**:

  - **Lỗi Validation**:
    - `MethodArgumentNotValidException`: Bắt lỗi khi submit form Thymeleaf không hợp lệ.
    - `ConstraintViolationException`: Bắt lỗi khi tham số trên URL của API không hợp lệ.

  - **Lỗi Nghiệp vụ**: Tạo `ResourceNotFoundException` để trả về lỗi `404 Not Found`.

  - **Lỗi Hệ thống**: Tạo một "Fallback Handler" (`Exception.class`) để trả về lỗi `500 Internal Server Error`.

## Cách demo

1. Clone folder này về và chạy ứng dụng từ file `ValidationExceptionDemoApplication.java`.

2. **Kiểm tra**:

   - Truy cập `http://localhost:8080/books/new`  
     * Để trống các trường và nhấn submit => Trang sẽ tải lại và hiển thị các thông báo lỗi.
     * Điền đúng thông tin theo yêu cầu và submit => Chuyển sang trang thông báo thành công.

   - Truy cập `http://localhost:8080/books/search?keyword=js`  
     → Nhận về JSON lỗi `400 Bad Request`.

   - Truy cập `http://localhost:8080/books/search?keyword=java`  
     → Nhận được kết quả không lỗi.

   - Truy cập `http://localhost:8080/books/123`  
     → Nhận về JSON lỗi `404 Not Found`.

   - Truy cập `http://localhost:8080/books/99` *(giả định tìm được sách có id 99 trong logic code)*  
     → Nhận được kết quả không lỗi.

   - Truy cập `http://localhost:8080/books/error`  
     → Nhận về JSON lỗi `500 Internal Server Error`.
