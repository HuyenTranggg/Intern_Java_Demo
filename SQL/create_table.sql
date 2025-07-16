-- =================================================================
--  Hệ thống Quản lý Thư viện
-- =================================================================

DROP DATABASE IF EXISTS `librarymanagement`;

CREATE DATABASE `librarymanagement` 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE `librarymanagement`;

-- =================================================================
--  Các bảng chính
-- =================================================================

-- Bảng lưu thông tin người dùng (thành viên, quản trị viên)
CREATE TABLE `users` (
    `user_id`       INT PRIMARY KEY AUTO_INCREMENT,
    `name`          VARCHAR(255) NOT NULL,
    `email`         VARCHAR(255) NOT NULL UNIQUE, -- email là duy nhất
    `password`      VARCHAR(255) NOT NULL,
    `avatar_url`	VARCHAR(255) NULL,
    `phone_number`  VARCHAR(20) NULL,
    `address`       TEXT NULL,
    `role`          VARCHAR(50) NOT NULL DEFAULT 'member', -- member, admin
    `status`        VARCHAR(50) NOT NULL DEFAULT 'active', -- active, blocked
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT `check_user_status` CHECK (`status` IN ('active', 'blocked')),
    CONSTRAINT `check_user_role` CHECK (`role` IN ('member', 'admin'))
);

-- Bảng lưu thông tin tác giả
CREATE TABLE `authors` (
    `author_id`     INT PRIMARY KEY AUTO_INCREMENT,
    `author_name`   VARCHAR(255) NOT NULL,
    `author_dob`    DATE NULL, -- Ngày sinh
    `author_dod`    DATE NULL, -- Ngày mất
    `biography`     TEXT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng lưu thông tin nhà xuất bản
CREATE TABLE `publishers` (
    `publisher_id`        INT PRIMARY KEY AUTO_INCREMENT,
    `publisher_name`      VARCHAR(255) NOT NULL,
    `publisher_address`   TEXT NULL,
    `year_of_establish`   INT NULL,
    `contact_email`       VARCHAR(255) NULL,
    `contact_phone`       VARCHAR(20) NULL,
    `website`             VARCHAR(255) NULL,
    `description`         TEXT NULL,
    `created_at`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng lưu thông tin sách
CREATE TABLE `books` ( 
    `book_id`     INT PRIMARY KEY AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `description` TEXT NULL
);

-- Bảng lưu các phiên bản của tác phẩm
CREATE TABLE `editions` (
    `edition_id`        INT PRIMARY KEY AUTO_INCREMENT,
    `book_id`           INT NOT NULL, 
    `publisher_id`      INT NOT NULL, 
    `isbn`              VARCHAR(20) NULL UNIQUE,
    `title`             VARCHAR(255) NOT NULL, -- Tiêu đề của phiên bản (có thể khác)
    `language`          VARCHAR(50) NULL,
    `publication_date`  DATE NULL,
    `page_number`       INT NULL,
    `cover_image_url`   VARCHAR(255) NULL,
    `format`            VARCHAR(50) NULL, -- 'Hardcover', 'Softcover', 'Ebook'
    
    CONSTRAINT `fk_edition_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`book_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_edition_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publishers`(`publisher_id`) ON DELETE RESTRICT,
	CONSTRAINT `check_edition_format` CHECK (`format` IN ('hardcover', 'softcover', 'ebook'))
);

-- Bảng lưu các bản sao cụ thể của mỗi đầu sách
CREATE TABLE `book_instances` (
    `book_instance_id` INT PRIMARY KEY AUTO_INCREMENT,
    `edition_id`       INT NOT NULL,
    `barcode`          VARCHAR(100) NOT NULL UNIQUE, 
    `call_number`      VARCHAR(100) NOT NULL, -- Địa chỉ của sách trên giá
    `acquired_date`    DATE NOT NULL,
    `acquired_price`   DECIMAL(10, 2) NULL,
    `status`           VARCHAR(50) NOT NULL DEFAULT 'available', -- available, borrowed, lost, damaged
    `note`             TEXT NULL, -- Tình trạng sách hiện tại
    
    CONSTRAINT `fk_instance_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`edition_id`) ON DELETE CASCADE,
    CONSTRAINT `check_book_instance_status` CHECK (`status` IN ('available', 'borrowed', 'lost', 'damaged', 'in_maintenance'))
);

-- Bảng lưu thông tin thể loại sách
CREATE TABLE `genres` (
    `genre_id`      INT PRIMARY KEY AUTO_INCREMENT,
    `genre_name`    VARCHAR(100) NOT NULL UNIQUE,
    `description`   TEXT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =================================================================
--  Các bảng quan hệ (Many-to-Many)
-- =================================================================

-- Bảng nối Sách và Tác giả
CREATE TABLE `author_book` (
    `author_id` INT NOT NULL,
    `book_id`   INT NOT NULL,
    
    PRIMARY KEY (`author_id`, `book_id`),
    CONSTRAINT `fk_ab_author` FOREIGN KEY (`author_id`) REFERENCES `authors`(`author_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_ab_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`book_id`) ON DELETE CASCADE
);

-- Bảng nối Sách và Thể loại
CREATE TABLE `book_genre` (
    `book_id`  INT NOT NULL,
    `genre_id` INT NOT NULL,
    
    PRIMARY KEY (`book_id`, `genre_id`),
    CONSTRAINT `fk_bg_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`book_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_bg_genre` FOREIGN KEY (`genre_id`) REFERENCES `genres`(`genre_id`) ON DELETE CASCADE
);

-- =================================================================
--  Các bảng chức năng (Mượn trả, Đánh giá, Bình luận...)
-- =================================================================

-- Bảng đơn mượn sách
CREATE TABLE `borrowing_receipts` (
    `borrowing_receipt_id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`              INT NOT NULL,
    `borrowed_date`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `due_date`             DATETIME NOT NULL,
    `actual_return_date`   DATETIME NULL,
    `status`               VARCHAR(50) NOT NULL DEFAULT 'active', -- active (đang mượn), returned, overdue (quá hạn)
    `updated_at`           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT `fk_receipt_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE RESTRICT,
    CONSTRAINT `check_borrowing_receipt_status` CHECK (`status` IN ('active', 'returned', 'overdue'))
);

-- Bảng chi tiết đơn mượn (mỗi đơn có thể mượn nhiều sách)
CREATE TABLE `borrowing_details` (
    `borrowing_receipt_id` INT NOT NULL,
    `book_instance_id`     INT NOT NULL,
    
    PRIMARY KEY (`borrowing_receipt_id`, `book_instance_id`),
    CONSTRAINT `fk_detail_receipt` FOREIGN KEY (`borrowing_receipt_id`) REFERENCES `borrowing_receipts`(`borrowing_receipt_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_detail_instance` FOREIGN KEY (`book_instance_id`) REFERENCES `book_instances`(`book_instance_id`) ON DELETE RESTRICT
);

-- Bảng đánh giá sách (sao)
CREATE TABLE `ratings` (
    `rating_id`  INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `edition_id` INT NOT NULL,
    `rate`       INT NOT NULL, -- Từ 1 đến 5
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE KEY `uk_user_edition_rating` (`user_id`, `edition_id`), -- Mỗi user chỉ đánh giá 1 sách một lần
    CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_rating_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`edition_id`) ON DELETE CASCADE
);

-- Bảng bình luận về sách
CREATE TABLE `comments` (
    `comment_id` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `edition_id`    INT NOT NULL,
    `content`    TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_comment_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`edition_id`) ON DELETE CASCADE
);

-- Bảng lưu danh sách sách yêu thích của người dùng
CREATE TABLE `favorite_books` (
    `user_id`    INT NOT NULL,
    `edition_id` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (`user_id`, `edition_id`),
    CONSTRAINT `fk_favorite_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_favorite_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`edition_id`) ON DELETE CASCADE
);

-- Bảng theo dõi tác giả
CREATE TABLE `author_follows` (
    `user_id`   	INT NOT NULL,
    `author_id` 	INT NOT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (`user_id`, `author_id`),
    CONSTRAINT `fk_author_follow_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_author_follow_author` FOREIGN KEY (`author_id`) REFERENCES `authors`(`author_id`) ON DELETE CASCADE
);

-- Bảng theo dõi nhà xuất bản
CREATE TABLE `publisher_follows` (
    `user_id`       INT NOT NULL,
    `publisher_id`  INT NOT NULL,
	`created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (`user_id`, `publisher_id`),
    CONSTRAINT `fk_publisher_follow_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_publisher_follow_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publishers`(`publisher_id`) ON DELETE CASCADE
);

-- =================================================================
--  Các bảng về Phí phạt
-- =================================================================

-- Bảng định nghĩa các loại phạt
CREATE TABLE `fines` (
    `fine_id`   INT PRIMARY KEY AUTO_INCREMENT,
    `fine_type` VARCHAR(255) NOT NULL, -- 'late_return', 'damaged', 'lost'
    `fee`       DECIMAL(10, 2) NOT NULL -- Mức phí cơ bản
);

-- Bảng ghi nhận các khoản phạt cụ thể cho từng đơn mượn
CREATE TABLE `receipt_fines` (
    `receipt_fine_id`      INT PRIMARY KEY AUTO_INCREMENT,
    `borrowing_receipt_id` INT NOT NULL,
    `book_instance_id`     INT NOT NULL,
    `fine_id`              INT NOT NULL,
    `amount`               DECIMAL(10, 2) NOT NULL, -- Số tiền phạt thực tế
    `fine_note`            TEXT NULL,
    
    CONSTRAINT `fk_rf_receipt` FOREIGN KEY (`borrowing_receipt_id`) REFERENCES `borrowing_receipts`(`borrowing_receipt_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_rf_instance` FOREIGN KEY (`book_instance_id`) REFERENCES `book_instances`(`book_instance_id`) ON DELETE RESTRICT,
    CONSTRAINT `fk_rf_fine` FOREIGN KEY (`fine_id`) REFERENCES `fines`(`fine_id`) ON DELETE RESTRICT
);

-- =================================================================
--  Kết thúc
-- =================================================================