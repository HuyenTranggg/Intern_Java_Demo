package demo.javaconfig.createbean.config;

import demo.javaconfig.createbean.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CreateBeanConfig {

    // C1: TẠO BEAN ĐƠN GIẢN
    // Tên bean mặc định sẽ là tên phương thức: "simpleBean"
    @Bean
    @Primary
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }

    // C2: TẠO BEAN VỚI TÊN TÙY CHỈNH
    // Bean này sẽ có tên là "myCustomBean", không phải "anotherSimpleBean"
    @Bean("myCustomBean")
    public SimpleBean anotherSimpleBean() {
        return new SimpleBean();
    }

    // C3: TẠO BEAN VỚI NHIỀU TÊN (ALIASES)
    // Tên chính là "mainBean", các tên phụ là "alias1", "alias2"
    @Bean(name = {"mainBean", "alias1", "alias2"})
    public SimpleBean beanWithAliases() {
        return new SimpleBean();
    }

    // C4: TẠO BEAN CÓ PHỤ THUỘC
    // Spring thấy tham số `SimpleBean bean`, nó sẽ tự tìm một bean kiểu SimpleBean
    // (chính là bean được tạo từ phương thức `simpleBean()` ở trên) và tiêm vào đây.
    @Bean
    public DependentBean dependentBean(SimpleBean bean) {
        return new DependentBean(bean);
    }

    // C5: TẠO BEAN TỪ MỘT "INSTANCE FACTORY METHOD"
    // Bước 1: Tạo factory bean trước
    @Bean
    public ProductFactory productFactory() {
        return new ProductFactory();
    }
    // Bước 2: Dùng factory bean để tạo bean
    @Bean
    public Product productFromInstanceFactory(ProductFactory factory) {
        return factory.createProduct();
    }

    // C6: TẠO BEAN TỪ MỘT "STATIC FACTORY METHOD"
    // Không cần tạo factory bean, chỉ cần gọi thẳng phương thức static.
    @Bean
    public Product productFromStaticFactory() {
        return StaticProductFactory.createProduct();
    }
}