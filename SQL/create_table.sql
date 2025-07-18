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
    `id`       		INT PRIMARY KEY AUTO_INCREMENT,
    `name`          VARCHAR(50) NOT NULL,
    `email`         VARCHAR(255) NOT NULL UNIQUE, -- email là duy nhất
    `password`      VARCHAR(255) NOT NULL,
    `dob`			DATE NOT NULL,
    `avatar_url`	VARCHAR(255) NULL,
    `phone`  		VARCHAR(20) NULL,
    `address`       TEXT NULL,
    `role`          INT NOT NULL, -- 'member', 'admin'
    `status`        INT NOT NULL, -- 'active', 'inactive'
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng lưu thông tin tác giả
CREATE TABLE `authors` (
    `id`     		INT PRIMARY KEY AUTO_INCREMENT,
    `name`   		VARCHAR(50) NOT NULL,
    `dob`    		DATE NULL, -- Ngày sinh
    `dod`    		DATE NULL, -- Ngày mất
    `biography`     TEXT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng lưu thông tin nhà xuất bản
CREATE TABLE `publishers` (
    `id`        		  INT PRIMARY KEY AUTO_INCREMENT,
    `name`      		  VARCHAR(50) NOT NULL,
    `address`   		  TEXT NULL,
    `established_year`    YEAR NULL,
    `email`       		  VARCHAR(255) NULL,
    `phone`      		  VARCHAR(20) NULL,
    `website`             VARCHAR(255) NULL,
    `description`         TEXT NULL,
    `created_at`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng lưu thông tin sách
CREATE TABLE `books` ( 
    `id`     	  INT PRIMARY KEY AUTO_INCREMENT,
    `title`       VARCHAR(255) NOT NULL,
    `description` TEXT NULL
);

-- Bảng lưu các phiên bản của tác phẩm
CREATE TABLE `editions` (
    `id`        		INT PRIMARY KEY AUTO_INCREMENT,
    `book_id`           INT NOT NULL, 
    `publisher_id`      INT NOT NULL, 
    `isbn`              VARCHAR(20) NULL UNIQUE, -- Mã số định danh cho từng phiên bản sách
    `title`             VARCHAR(255) NOT NULL, -- Tiêu đề của phiên bản (có thể khác)
    `language`          VARCHAR(50) NULL,
    `publication_date`  DATE NULL,
    `page_number`       INT NULL,
    `cover_image_url`   VARCHAR(255) NULL,
    `format`            INT NULL, -- 'hardcover', 'softcover', 'ebook'
    `initial_quantity`	INT NOT NULL,
    `available_quantity`INT NOT NULL,
    
    CONSTRAINT `fk_edition_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_edition_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publishers`(`id`) ON DELETE RESTRICT
);

-- Bảng lưu các bản sao cụ thể của mỗi đầu sách
CREATE TABLE `book_instances` (
    `id` 			   INT PRIMARY KEY AUTO_INCREMENT,
    `edition_id`       INT NOT NULL,
    `barcode`          VARCHAR(100) NOT NULL UNIQUE, 
    `call_number`      VARCHAR(100) NOT NULL, -- Địa chỉ của sách trên giá
    `acquired_date`    DATE NOT NULL,
    `acquired_price`   DECIMAL(10, 2) NULL,
    `status`           INT NOT NULL, -- 'available', 'borrowed', 'reserved': đã đặt trước, 'lost', 'damaged': hỏng không dùng được nữa, 'repairing', 'archived': sách không lưu hành nữa
    `note`             TEXT NULL, -- Tình trạng sách hiện tại
    
    CONSTRAINT `fk_instance_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`id`) ON DELETE CASCADE
);

-- Bảng lưu thông tin thể loại sách
CREATE TABLE `genres` (
    `id`      		INT PRIMARY KEY AUTO_INCREMENT,
    `name`    		VARCHAR(100) NOT NULL UNIQUE,
    `description`   TEXT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =================================================================
--  Các bảng quan hệ (Many-to-Many)
-- =================================================================

-- Bảng nối Sách và Tác giả
CREATE TABLE `author_book` (
	`id`		INT PRIMARY KEY AUTO_INCREMENT,
    `author_id` INT NOT NULL,
    `book_id`   INT NOT NULL,
    
    CONSTRAINT `fk_ab_author` FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_ab_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE
);

-- Bảng nối Sách và Thể loại
CREATE TABLE `book_genre` (
	`id`	   INT PRIMARY KEY AUTO_INCREMENT,
    `book_id`  INT NOT NULL,
    `genre_id` INT NOT NULL,
    
    CONSTRAINT `fk_bg_book` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_bg_genre` FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`) ON DELETE CASCADE
);

-- =================================================================
--  Các bảng chức năng (Mượn trả, Đánh giá, Bình luận...)
-- =================================================================

-- Bảng đơn mượn sách
CREATE TABLE `borrowing_receipts` (
    `id` 				   INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`              INT NOT NULL,
    `borrowed_date`        DATETIME NOT NULL,
    `due_date`             DATETIME NOT NULL,
    `status`               INT NOT NULL, -- 'pending', 'approved', 'borrowed', 'rejected', 'returned', 'overdue', 'lost_reported', 'cancel'
	`rejected_reason`	   TEXT NULL,
    `created_at`    	   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT `fk_receipt_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE RESTRICT
);

-- Bảng chi tiết đơn mượn (mỗi đơn có thể mượn nhiều sách)
CREATE TABLE `borrowing_details` (
	`id`				   INT PRIMARY KEY AUTO_INCREMENT,
    `borrowing_receipt_id` INT NOT NULL,
    `book_instance_id`     INT NOT NULL,
    `refund_date`		   DATETIME NULL,
    
    CONSTRAINT `fk_detail_receipt` FOREIGN KEY (`borrowing_receipt_id`) REFERENCES `borrowing_receipts`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_detail_instance` FOREIGN KEY (`book_instance_id`) REFERENCES `book_instances`(`id`) ON DELETE RESTRICT
);

-- Bảng đánh giá sách (sao)
CREATE TABLE `ratings` (
    `id`  		 INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `edition_id` INT NOT NULL,
    `rate`       INT NOT NULL, -- Từ 1 đến 5
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    UNIQUE KEY `uk_user_edition_rating` (`user_id`, `edition_id`), -- Mỗi user chỉ đánh giá 1 sách một lần
    CONSTRAINT `fk_rating_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_rating_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`id`) ON DELETE CASCADE
);

-- Bảng bình luận về sách
CREATE TABLE `comments` (
    `id` 		 INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`    INT NOT NULL,
    `edition_id` INT NOT NULL,
    `content`    TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_comment_edition` FOREIGN KEY (`edition_id`) REFERENCES `editions`(`id`) ON DELETE CASCADE
);

-- Bảng lưu danh sách sách theo dõi của người dùng (edition/author/publisher)
CREATE TABLE `follows` (
    `id`      	   INT PRIMARY KEY AUTO_INCREMENT,
    `user_id`      INT NOT NULL,
    `target_id`    INT NOT NULL,
    `target_type`  INT NOT NULL, -- 'edition', 'author', 'publisher'
    `created_at`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT `fk_follows_user` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

-- =================================================================
--  Các bảng về Phí phạt
-- =================================================================

-- Bảng định nghĩa các loại phạt
CREATE TABLE `fines` (
    `id`   INT PRIMARY KEY AUTO_INCREMENT,
    `type` INT NOT NULL, -- 'late_return', 'damaged', 'lost'
    `fee`  DECIMAL(10, 2) NOT NULL -- Mức phí cơ bản
);

-- Bảng ghi nhận các khoản phạt cụ thể cho từng đơn mượn
CREATE TABLE `receipt_fines` (
    `id`      			   INT PRIMARY KEY AUTO_INCREMENT,
    `borrowing_receipt_id` INT NOT NULL,
    `book_instance_id`     INT NOT NULL,
    `fine_id`              INT NOT NULL,
    `amount`               DECIMAL(10, 2) NOT NULL, -- Số tiền phạt thực tế
    `fine_note`            TEXT NULL, -- Ghi chú tình trạng sách khi bị phạt
    
    CONSTRAINT `fk_rf_receipt` FOREIGN KEY (`borrowing_receipt_id`) REFERENCES `borrowing_receipts`(`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_rf_instance` FOREIGN KEY (`book_instance_id`) REFERENCES `book_instances`(`id`) ON DELETE RESTRICT,
    CONSTRAINT `fk_rf_fine` FOREIGN KEY (`fine_id`) REFERENCES `fines`(`id`) ON DELETE RESTRICT
);

-- =================================================================
--  Kết thúc
-- =================================================================