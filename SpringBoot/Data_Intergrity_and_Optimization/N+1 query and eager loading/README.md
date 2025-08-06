# Demo: Transaction và N+1 Query trong Spring Boot

Dự án này là một ứng dụng console đơn giản nhằm minh họa hai khái niệm quan trọng trong Spring Data JPA: Quản lý Transaction và Vấn đề N+1 Query.

## Mô hình Cơ sở dữ liệu

Demo sử dụng hai thực thể chính với mối quan hệ một-nhiều:

- Author: Lưu thông tin tác giả.
- Post: Lưu thông tin bài viết, mỗi bài viết phải thuộc về một tác giả.

### Sơ đồ quan hệ:

```
+------------+        +------------+
|   Author   | 1    * |    Post    |
+------------+        +------------+
| id (PK)    |--------| id (PK)    |
| name       |        | title      |
+------------+        | author_id(FK)|
                      +------------+
```

Mối quan hệ này được định nghĩa với FetchType.EAGER trên Entity Post để cố tình gây ra vấn đề N+1 trong phần demo.

## Hướng dẫn chạy

1. Tạo Database: Tạo một database MySQL với tên spring_demo.

```sql
CREATE DATABASE spring_demo;
```

2. Cấu hình: Cập nhật file src/main/resources/application.properties với thông tin username/password MySQL.

3. Thực thi: Chạy ứng dụng bằng lệnh Maven. Toàn bộ kết quả sẽ được in ra console.

```bash
mvn spring-boot:run
```

## Nội dung Demo

Khi chạy, ứng dụng sẽ tự động thực thi 2 phần demo sau:

### 1. Quản lý Transaction (@Transactional)

- Mục tiêu: Chứng minh tính toàn vẹn dữ liệu (all-or-nothing).

- Cách hoạt động:
  - Trường hợp thành công: Một hàm không có lỗi sẽ thực thi và blệnh COMMIT hiển thị trong log SQL. Dữ liệu được lưu thành công.
  - Trường hợp thất bại: Một hàm cố tình gây lỗi sẽ bị hủy bỏ. Lệnh ROLLBACK sẽ được hiển thị trong log, đảm bảo dữ liệu không nhất quán không được lưu vào database.

### 2. Vấn đề N+1 Query & Eager Loading

- Mục tiêu: Chỉ ra sự khác biệt về hiệu năng giữa cách truy vấn kém hiệu quả và cách tối ưu.

- Cách hoạt động:
  - Vấn đề: Khi gọi findAll() trên các Post, 1 câu query để lấy tất cả Post, theo sau bởi N câu query con để lấy thông tin Author cho từng Post. Đây là vấn đề 1 + N query.
  - Giải pháp: Bằng cách sử dụng JOIN FETCH trong một query tùy chỉnh, ứng dụng chỉ thực thi DUY NHẤT 1 câu query để lấy tất cả dữ liệu cần thiết, giải quyết vấn đề hiệu năng.
