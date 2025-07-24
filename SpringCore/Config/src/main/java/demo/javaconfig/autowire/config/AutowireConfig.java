package demo.javaconfig.autowire.config;

import demo.javaconfig.autowire.beans.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AutowireConfig {
	// Tạo bean v8Engine
    @Bean
    @Primary 
    public Engine v8Engine() {
        return new V8Engine();
    }

    // Tạo bean electricEngine
    @Bean
    public Engine electricEngine() {
        return new ElectricEngine();
    }
    
	// --- AUTOWIRING BY TYPE ---
    @Bean
    public Car defaultCar(Engine defaultEngine) {
        // Nếu không có @Primary, phương thức này sẽ bị LỖI 
    	// Do Spring tìm thấy 2 bean kiểu Engine (v8Engine và electricEngine)
        // và không biết phải chọn cái nào.
    	System.out.println(">>> Wiring defaultCar with @Primary bean.");
        return new Car(defaultEngine);
    }

    
    // @Qualifier
    // Ngoài @Primary, đây cũng là 1 cách giải quyết tình trạng có nhiều bean cùng thỏa mãn
    
    // Bước 1: Gán "nhãn" (Qualifier) cho các bean
    @Bean
    @Qualifier("performance_engine") // Gán nhãn "performance_engine"
    public Engine performanceEngine() {
        return new V8Engine();
    }

    @Bean
    @Qualifier("city_engine") // Gán nhãn "city_engine"
    public Engine cityEngine() {
        return new ElectricEngine();
    }

    // Bước 2: Yêu cầu bean có "nhãn" cụ thể
    @Bean
    public Car luxuryCar(@Qualifier("performance_engine") Engine someEngine) {
        // Spring sẽ tìm bean có nhãn "performance_engine" và tiêm vào tham số 'someEngine'.
        // Tên của tham số ('someEngine') bây giờ không còn quan trọng nữa.
        System.out.println(">>> Wiring luxuryCar by matching @Qualifier('performance_engine').");
        return new Car(someEngine);
    }

    @Bean
    public Car commuterCar(@Qualifier("city_engine") Engine anyEngineName) {
        // Tương tự, tiêm bean có nhãn "city_engine".
        System.out.println(">>> Wiring commuterCar by matching @Qualifier('city_engine').");
        return new Car(anyEngineName);
    }
}