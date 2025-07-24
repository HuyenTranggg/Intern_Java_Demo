package demo.javaconfig.lazyinit.config;

import demo.javaconfig.lazyinit.beans.EagerBean;
import demo.javaconfig.lazyinit.beans.LazyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
// Có thể cấu hình @Lazy ở cấp độ class để áp dụng cho TẤT CẢ các bean bên trong.
public class LazyInitConfig {

    // 1. BEAN KHỞI TẠO NGAY (Mặc định)
    // Bean này sẽ được tạo ngay khi container khởi động.
    @Bean
    public EagerBean eagerBean() {
        return new EagerBean();
    }

    // 2. BEAN KHÔNG KHỞI TẠO NGAY
    // Bean này sẽ KHÔNG được tạo khi container khởi động.
    @Bean
    @Lazy
    public LazyBean lazyBean() {
        return new LazyBean();
    }
}