package demo.annotation.dependencyinjection.consumers;

import demo.annotation.dependencyinjection.beans.Engine;
import demo.annotation.dependencyinjection.beans.Gearbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectedCar {
    private Engine engine;
    private Gearbox gearbox;

    // Các kiểu Dependency Injection (DI)
    /*
     * C2: Setter Injection 
     *	- Mô tả: Phụ thuộc được tiêm vào thông qua các phương thức setter.
     *  - Cơ chế: Spring sẽ khởi tạo đối tượng bằng constructor không tham số trước, sau đó gọi các
     *    phương thức setter được đánh dấu @Autowired để tiêm dependency.
     */
    @Autowired
    // Khi có nhiều hơn 1 bean cùng kiểu, cần chỉ rõ bean muốn inject bằng tên bean qua @Qualifier (VD: @Qualifier("v8Engine"))
    // Nếu có nhiều hơn 1 bean thỏa mãn -> lỗi
    @Qualifier("electricEngine")
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Autowired
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void drive() {
        System.out.print("SetterInjectedCar: ");
        engine.start();
        gearbox.engage();
    }
}