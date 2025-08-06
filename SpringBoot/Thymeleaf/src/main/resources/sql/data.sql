USE thymeleaf_security_db;

INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_USER');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_MANAGER');

-- Mật khẩu "admin123" được mã hóa bằng BCrypt
INSERT IGNORE INTO users (id, username, password, active, role_id) VALUES (1, 'admin', '$2a$10$EUBdEVnWRaa7rvTfEDos2.nxaZ7SizkVwOHeCapLZhKojiKrhYMmy', 1, 1); -- Gán role_id = 1 (ADMIN)
-- Mật khẩu "user123"
INSERT IGNORE INTO users (id, username, password, active, role_id) VALUES (2, 'user', '$2a$10$uTKxDUINy2zt735Zt0JXIeDOzywfgvKD9m1PWVAUuCIJ9WVRtJfUS', 1, 2); -- Gán role_id = 2 (USER)
-- Mật khẩu "manager123"
INSERT IGNORE INTO users (id, username, password, active, role_id) VALUES (3, 'manager', '$2a$10$tKswRARISBDKXtuikHC.qOMJ/i7weN0nP.mman3BZv9AwmgrD890S', 1, 3); -- Gán role_id = 3 (MANAGER)

INSERT IGNORE INTO products (id, name, owner_id) VALUES (1, 'Laptop Dell XPS', 1); -- Sản phẩm của admin
INSERT IGNORE INTO products (id, name, owner_id) VALUES (2, 'Bàn phím cơ', 2); -- Sản phẩm của user
INSERT IGNORE INTO products (id, name, owner_id) VALUES (3, 'Màn hình LG UltraWide', 2); -- Sản phẩm của user