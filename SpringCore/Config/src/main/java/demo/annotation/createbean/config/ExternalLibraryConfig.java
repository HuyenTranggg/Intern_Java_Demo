package demo.annotation.createbean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.annotation.createbean.util.MovieFormatter;

// Đánh dấu đây là một lớp cấu hình
@Configuration 
public class ExternalLibraryConfig {
	// Các cách tạo bean với annotation
	/*
	 * C2: Dùng @Bean để khai báo 
	 * 
	 * 		Tên bean: 
	 * 			Mặc định là tên phương thức 
	 * 			Có thể đặt tên khác bằng cách viết @Bean("another_name")
	 * 		Bean Type: Kiểu trả về của phương thức
	 * 		Scope mặc định là singleton
	 */
    @Bean
    public MovieFormatter movieFormatter() {
        System.out.println("Creating a bean via @Bean annotation");
        return new MovieFormatter("[OFFICIAL]");
    }

    @Bean("anotherFormatter") // Đặt tên khác cho bean
    public MovieFormatter anotherMovieFormatter() {
    	System.out.println("Creating another bean via @Bean annotation with a custome name");
        return new MovieFormatter("---");
    }
}