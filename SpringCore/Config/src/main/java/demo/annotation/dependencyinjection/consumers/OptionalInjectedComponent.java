package demo.annotation.dependencyinjection.consumers;

import demo.annotation.dependencyinjection.beans.Spoiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OptionalInjectedComponent {
	// *** Đây không phải 1 loại Dependency Injection ***
	
    // Spoiler không phải bean.
    // required = false: Nếu không tìm thấy bean hợp lệ thì bỏ qua, không báo lỗi.
    // Kết quả: spoiler field sẽ là null.
    @Autowired(required = false)
    private Spoiler spoiler;

    public void checkAccessory() {
        if (spoiler == null) {
            System.out.println("Optional Injection: Spoiler is not installed (bean is null).");
        } else {
            System.out.println("Optional Injection: Spoiler is installed.");
        }
    }
}