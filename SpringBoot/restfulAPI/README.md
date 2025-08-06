# Book Management RESTful API - Spring Boot

Một ứng dụng RESTful API đơn giản để quản lý Sách (Book), được xây dựng bằng Spring Boot. Đây là một project demo tập trung vào việc xây dựng backend theo kiến trúc 3 lớp, tuân thủ các chuẩn của REST.

## Mục tiêu kỹ thuật

Dự án này được tạo ra để demo và thực hành các kỹ năng backend cốt lõi sau:

1.  **Xây dựng RESTful API (CRUD Operations)**
    *   Thiết kế các endpoint theo chuẩn REST để thực hiện các thao tác Tạo, Đọc, Cập nhật, Xóa (CRUD) đối với tài nguyên Sách.
    *   Sử dụng các HTTP Method (`GET`, `POST`, `PUT`, `DELETE`) một cách chính xác.

2.  **Kiến trúc 3 lớp (3-Layer Architecture)**
    *   Tổ chức code một cách rõ ràng và dễ bảo trì theo mô hình:
        *   **Controller**: Tầng tiếp nhận request và trả về response.
        *   **Service**: Tầng chứa logic nghiệp vụ chính.
        *   **Repository**: Tầng giao tiếp với cơ sở dữ liệu.

3.  **Data Transfer Object (DTO) Pattern**
    *   Sử dụng DTO để tách biệt lớp API với lớp Entity, giúp API linh hoạt, bảo mật và dễ dàng validation hơn.
    *   Triển khai validation dữ liệu đầu vào ngay tại lớp DTO (`@Valid`, `@NotBlank`, `@Min`...).

4.  **Xử lý Exception tập trung (Global Exception Handling)**
    *   **Áp dụng `@ControllerAdvice` để tạo một trình xử lý lỗi toàn cục, giúp Controller luôn sạch sẽ và logic xử lý lỗi được tái sử dụng.**
    *   Tạo các exception tùy chỉnh (`ResourceNotFoundException`) để xử lý các trường hợp nghiệp vụ cụ thể.

5.  **Cấu trúc Response thống nhất (API Response Wrapper)**
    *   **Tất cả các response trả về từ API đều tuân theo một cấu trúc JSON chung, giúp phía client dễ dàng xử lý cả trường hợp thành công và thất bại.**

6.  **Logging hiệu quả**
    *   Tích hợp logging (sử dụng Lombok `@Slf4j`) vào các lớp Service và Controller để theo dõi luồng hoạt động của ứng dụng.

## API Endpoints

Ứng dụng cung cấp các endpoint sau dưới đường dẫn gốc `/api/v1/books`:

| Method | Endpoint             | Mô tả                      | Body Request |
| :----- | :------------------- | :------------------------- | :----------- |
| `GET`    | `/`                  | Lấy danh sách tất cả sách  | (Không có)   |
| `GET`    | `/{id}`              | Lấy thông tin một sách theo ID | (Không có)   |
| `POST`   | `/`                  | Tạo một sách mới           | `BookDTO`    |
| `PUT`    | `/{id}`              | Cập nhật thông tin một sách    | `BookDTO`    |
| `DELETE` | `/{id}`              | Xóa một sách theo ID       | (Không có)   |


## Sử dụng đúng HTTP Status Codes

Ứng dụng trả về các mã trạng thái HTTP theo chuẩn REST để client có thể xử lý kết quả một cách chính xác:

| Mã        | Ý nghĩa            | Khi nào dùng?                               |
| :-------- | :----------------- | :------------------------------------------- |
| `200 OK`    | Thành công         | `GET`, `PUT` thành công.                     |
| `201 Created` | Tạo mới thành công   | `POST` tạo sách thành công.                  |
| `204 No Content` | Xóa thành công, không có nội dung trả về | `DELETE` thành công.                         |
| `400 Bad Request`| Dữ liệu đầu vào không hợp lệ | Dữ liệu trong `BookDTO` không qua được validation. |
| `404 Not Found` | Không tìm thấy      | Truy vấn sách với `id` không tồn tại.         |


## Công nghệ sử dụng

*   **Framework**: Spring Boot 3
*   **Language**: Java 17
*   **API**: Spring Web (RESTful)
*   **Database**: Spring Data JPA, Hibernate, MySQL
*   **Utilities**: Lombok (giảm code boilerplate), Spring Boot Validation (xác thực dữ liệu)
*   **Build Tool**: Maven

## Cách chạy dự án

1.  **Clone repository:**
    ```bash
    git clone <your-repository-url>
    cd book-management
    ```

2.  **Cấu hình Database:**
    *   Mở MySQL và tạo một database mới: `CREATE DATABASE book_db;`
    *   Mở file `src/main/resources/application.yml`.
    *   Thay đổi `username` và `password` cho phù hợp với cấu hình MySQL của bạn. Đảm bảo cổng được đặt là `8081`.

3.  **Build dự án bằng Maven:**
    ```bash
    ./mvnw clean install
    ```

4.  **Chạy ứng dụng:**
    ```bash
    java -jar target/book-management-0.0.1-SNAPSHOT.jar
    ```
    Hoặc chạy trực tiếp từ IDE của bạn bằng cách chạy file `BookManagementApplication.java`.

5.  **Kiểm tra:**
    *   Ứng dụng sẽ chạy tại `http://localhost:8081`.
    *   Sử dụng một công cụ như **Postman** để gửi request tới các API endpoints đã liệt kê ở trên.