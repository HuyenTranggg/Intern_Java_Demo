package demo.javaconfig.beanscopes;

import java.time.LocalTime;

public class TimestampedBean {
    private final LocalTime creationTime;

    public TimestampedBean() {
        this.creationTime = LocalTime.now();
        System.out.println(">>> TimestampedBean created at: " + this.creationTime);
    }

    public LocalTime getCreationTime() {
        return creationTime;
    }
}