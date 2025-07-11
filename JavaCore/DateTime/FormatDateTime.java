package JavaCore.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class FormatDateTime {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        // 1. Dùng các định dạng có sẵn
        DateTimeFormatter shortFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        
        System.out.println("Short format: " + now.format(shortFormatter));
        // Ví dụ tiếng Việt
        Locale vietnameseLocale = Locale.forLanguageTag("vi-VN"); 
        System.out.println("Medium format (vi-VN): " + now.format(mediumFormatter.withLocale(vietnameseLocale)));

        // 2. Dùng mẫu tùy chỉnh (Custom Pattern)
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' hh:mm a");
        System.out.println("Custom format: " + now.format(customFormatter));
    }
}
