package demo.annotation.dependencyinjection.consumers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueInjectedDriver {
	// *** Đây là kỹ thuật, không phải 1 loại Dependency Injection ***

    // 1. Tiêm giá trị từ file properties.
    @Value("${driver.name}")
    private String name;

    // 2. Dùng Spring Expression Language (SpEL) để nối chuỗi từ properties.
    @Value("#{'License: ' + '${driver.license.id}'}")
    private String licenseInfo;

    // 3. Dùng SpEL để tính toán.
    @Value("#{T(java.time.Year).now().getValue() - 2000}")
    private int yearsOfExperience;

    // 4. Tiêm một giá trị rỗng.
    @Value("")
    private String emptyNote;

    public void showInfo() {
        System.out.println("Value Injection Driver Info:");
        System.out.println("- Name: " + name);
        System.out.println("- " + licenseInfo);
        System.out.println("- Experience: " + yearsOfExperience + " years");
        System.out.println("- Note: '" + emptyNote + "' (injected an empty string)");
    }
}