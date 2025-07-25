-- Tạo một database mới tên là 'spring_demo'
CREATE DATABASE IF NOT EXISTS spring_demo;

-- Sử dụng database vừa tạo
USE spring_demo;

-- Tạo bảng để lưu thông tin sinh viên
CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);