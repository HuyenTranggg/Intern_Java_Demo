package demo.javaconfig.beanscopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanScopesConfig {

    // 1. BEAN VỚI SCOPE SINGLETON (Mặc định)
    // Spring sẽ chỉ gọi phương thức này MỘT LẦN duy nhất.
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public TimestampedBean singletonScopedBean() {
        return new TimestampedBean();
    }


    // 2. BEAN VỚI SCOPE PROTOTYPE
    // Spring sẽ gọi lại phương thức này MỖI KHI có yêu cầu lấy bean.
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TimestampedBean prototypeScopedBean() {
        return new TimestampedBean();
    }
}