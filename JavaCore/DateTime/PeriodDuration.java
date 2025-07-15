package JavaCore.DateTime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class PeriodDuration {
    public static void main(String[] args) {
        // --- Period ---
        LocalDate startDate = LocalDate.of(2023, 1, 15);
        LocalDate endDate = LocalDate.of(2024, 3, 20);

        Period period = Period.between(startDate, endDate);
        System.out.println("Period between dates: ");
        System.out.printf("%d years, %d months, and %d days\n",
                period.getYears(), period.getMonths(), period.getDays());

        LocalDate futureDate = startDate.plus(Period.ofMonths(6));
        System.out.println("Date after 6 months: " + futureDate);

        // --- Duration ---
        LocalTime startTime = LocalTime.of(9, 0, 0);
        LocalTime endTime = LocalTime.of(10, 30, 15);

        Duration duration = Duration.between(startTime, endTime);
        System.out.println("\nDuration between times: ");
        System.out.println("Total seconds: " + duration.getSeconds()); 
        
        // Chuyển đổi sang giờ, phút, giây
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        System.out.printf("%d hours, %d minutes, %d seconds\n", hours, minutes, seconds);
    }
}
