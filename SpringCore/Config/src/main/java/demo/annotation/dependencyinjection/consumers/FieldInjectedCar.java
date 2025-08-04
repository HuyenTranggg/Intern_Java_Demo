package demo.annotation.dependencyinjection.consumers;

import demo.annotation.dependencyinjection.beans.Engine;
import demo.annotation.dependencyinjection.beans.Gearbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FieldInjectedCar {
	// Các kiểu Dependency Injection (DI)
    /*
     * C3: Field Injection 
	 *  - Mô tả: Phụ thuộc được tiêm trực tiếp vào các trường (field) của class.
	 *  - Cơ chế: Spring sử dụng Reflection để gán giá trị cho trường, kể cả khi nó là `private`.
     */
    @Autowired
    // Khi có nhiều hơn 1 bean cùng kiểu, cần chỉ rõ bean muốn inject bằng tên bean qua @Qualifier (VD: @Qualifier("v8Engine"))
    // Nếu có nhiều hơn 1 bean thỏa mãn -> lỗi
    @Qualifier("v8Engine")
    private Engine engine;

    @Autowired
    private Gearbox gearbox;

    public void drive() {
        System.out.print("FieldInjectedCar: ");
        engine.start();
        gearbox.engage();
    }
}