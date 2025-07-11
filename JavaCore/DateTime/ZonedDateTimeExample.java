package JavaCore.DateTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        // Một ngày giờ cục bộ
        LocalDateTime ldt = LocalDateTime.of(2023, 10, 27, 10, 0);
        System.out.println("Local DateTime: " + ldt);

        // Gắn múi giờ vào để có ZonedDateTime
        ZoneId vietnamZone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime vietnamTime = ZonedDateTime.of(ldt, vietnamZone);
        
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime newYorkTime = ZonedDateTime.of(ldt, newYorkZone);

        // Định dạng để thấy sự khác biệt giữa 2 múi giờ khác nhau
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        
        System.out.println("\nVietnam Time: " + formatter.format(vietnamTime));
        System.out.println("New York Time: " + formatter.format(newYorkTime));

        // So sánh 2 thời điểm
        // Mặc dù cùng là "10:00", nhưng chúng không phải là cùng một thời điểm
        boolean areTheySameInstant = vietnamTime.toInstant().equals(newYorkTime.toInstant());
        System.out.println("\nAre they the same instant in time? " + areTheySameInstant); // false

        // Chuyển đổi thời gian từ múi giờ này sang múi giờ khác
        ZonedDateTime vietnamTimeInNewYork = vietnamTime.withZoneSameInstant(newYorkZone);
        System.out.println("10:00 AM in Vietnam is equivalent to: " + formatter.format(vietnamTimeInNewYork) + " in New York");
    }
}