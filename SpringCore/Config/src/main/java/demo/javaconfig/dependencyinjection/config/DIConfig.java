package demo.javaconfig.dependencyinjection.config;

import demo.javaconfig.dependencyinjection.beans.Car;
import demo.javaconfig.dependencyinjection.beans.Engine;
import demo.javaconfig.dependencyinjection.beans.Transmission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }

    @Bean
    public Transmission transmission() {
        return new Transmission();
    }

    // --- CÁC CÁCH TIÊM PHỤ THUỘC (WIRING) ---

    // C1: TIÊM QUA THAM SỐ PHƯƠNG THỨC (Parameter Injection)
    // Spring thấy phương thức này cần một Engine và một Transmission. Nó sẽ tự động tìm
    // các bean tương ứng đã được định nghĩa trong context (từ 2 phương thức ở trên)
    // và truyền chúng vào làm tham số.
    @Bean
    public Car carFromParameterInjection(Engine engine, Transmission transmission) {
        System.out.println("   (Wiring via Parameter Injection)");
        return new Car(engine, transmission);
    }


    // C2: GỌI TRỰC TIẾP CÁC PHƯƠNG THỨC @Bean KHÁC (Direct Method Invocation)
    // Nhìn có vẻ như mỗi lần gọi carFromMethodInvocation(), nó sẽ gọi lại engine() và transmission(),
    // tạo ra các đối tượng mới. Nhưng KHÔNG!
    // Vì DIConfig được đánh dấu là @Configuration, Spring tạo một proxy CGLIB cho nó.
    // Proxy này sẽ chặn các cuộc gọi đến engine() và transmission(), đảm bảo rằng nó luôn
    // trả về các bean singleton đã tồn tại trong context, thay vì thực thi lại phương thức.
    @Bean
    public Car carFromMethodInvocation() {
        System.out.println("   (Wiring via Direct Method Invocation)");
        return new Car(engine(), transmission()); // Gọi trực tiếp các phương thức @Bean khác
    }
}