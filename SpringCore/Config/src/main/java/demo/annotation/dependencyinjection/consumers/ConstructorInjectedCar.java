package demo.annotation.dependencyinjection.consumers;

import demo.annotation.dependencyinjection.beans.Engine;
import demo.annotation.dependencyinjection.beans.Gearbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectedCar {
    private final Engine engine;
    private final Gearbox gearbox;

    // Các kiểu Dependency Injection (DI)
    /*
     * C1: Constructor Injection 
     *  - Mô tả: Phụ thuộc được tiêm vào thông qua constructor của class.
     *  - Cơ chế: Spring sẽ tìm một constructor phù hợp và các bean tương ứng với các tham số (parameter)
     *    của constructor đó để khởi tạo đối tượng.
     */
    // Có thể bỏ qua @Autowired nếu class chỉ có duy nhất một constructor.
    @Autowired
    // Khi có nhiều hơn 1 bean cùng kiểu, cần chỉ rõ bean muốn inject bằng tên bean qua @Qualifier (VD: @Qualifier("v8Engine"))
    // Nếu có nhiều hơn 1 bean thỏa mãn -> lỗi
    public ConstructorInjectedCar(@Qualifier("v8Engine") Engine engine, Gearbox gearbox) {
        this.engine = engine;
        this.gearbox = gearbox;
    }

    public void drive() {
        System.out.print("ConstructorInjectedCar: ");
        engine.start();
        gearbox.engage();
    }
}