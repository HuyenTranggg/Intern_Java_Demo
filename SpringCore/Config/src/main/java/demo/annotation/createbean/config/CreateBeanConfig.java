package demo.annotation.createbean.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// Các cách tạo bean với annotation
/*
 * C1: Tự động phát hiện với @ComponentScan 
 * 
 * 		Đánh dấu các class bằng @Component, @Service, @Repository, @Controller.
 * 		Khi Spring quét và thấy các annotation trên, nó sẽ tự động tạo một instance của lớp đó và quản lý nó như một bean.
 * 		@Component là annotation cơ bản nhất và chung nhất.
 * 		@Repository, @Service, @Controller đều là các dạng đặc biệt của @Component. Chúng kế thừa mọi tính năng của @Component nhưng mang thêm ý nghĩa riêng.
 * 		Tên bean: 
 * 			Mặc định là tên lớp nhưng viết thường chữ cái đầu. 
 * 			VD: lớp CarUtility sẽ có bean tên là carUtility. 
 * 			Có thể đặt tên khác bằng cách viết @Component("another_name").
 * 		Scope mặc định là singleton.
 */
@ComponentScan(basePackages = "demo.annotation.createbean") // Nếu không chỉ định package nào, Spring sẽ quét từ package chứa chính lớp có annotation @ComponentScan

// Dùng để import một class cấu hình khác vào trong context hiện tại.
// Có thể import nhiều class.
@Import(ExternalLibraryConfig.class)

public class CreateBeanConfig {
}