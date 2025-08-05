DROP DATABASE IF EXISTS thymeleaf_security_db;
CREATE DATABASE thymeleaf_security_db;

USE thymeleaf_security_db;

-- Bảng 'roles' 
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Bảng 'users' để lưu thông tin người dùng
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    role_id BIGINT,
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Bảng 'products'
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id BIGINT,
    CONSTRAINT fk_products_users FOREIGN KEY (owner_id) REFERENCES users (id)
);