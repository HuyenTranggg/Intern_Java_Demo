package JavaCore.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class CreateDateTime {
    public static void main(String[] args) {
        // 1. Lấy ngày giờ hiện tại
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Today: " + today);
        System.out.println("Current Time: " + now);
        System.out.println("Current DateTime: " + currentDateTime);

        // 2. Tạo ngày giờ cụ thể bằng phương thức tĩnh 'of'
        LocalDate specificDate = LocalDate.of(2025, Month.JANUARY, 1); // Hoặc LocalDate.of(2025, 1, 1)
        System.out.println("Specific Date: " + specificDate);

        LocalTime specificTime = LocalTime.of(10, 30, 55);
        System.out.println("Specific Time: " + specificTime);

        LocalDateTime specificDateTime = LocalDateTime.of(specificDate, specificTime);
        System.out.println("Specific DateTime: " + specificDateTime);

        // 3. Chuyển đổi từ chuỗi (Parse from String)
        LocalDate parsedDate = LocalDate.parse("2023-10-27");
        System.out.println("Parsed Date: " + parsedDate);
    }
}
