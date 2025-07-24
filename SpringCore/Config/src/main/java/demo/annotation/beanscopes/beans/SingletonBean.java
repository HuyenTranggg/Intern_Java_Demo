package demo.annotation.beanscopes.beans;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

// @Scope("singleton") là mặc định
@Component
public class SingletonBean {
    private final String creationTime = LocalDateTime.now().toString();

    public SingletonBean() {
        System.out.println(">>> SingletonBean has been created at " + creationTime);
    }

    public String getCreationTime() {
        return creationTime;
    }
}