package demo.annotation.beanscopes.beans;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {
    private final String creationTime = LocalDateTime.now().toString();

    public PrototypeBean() {
        System.out.println(">>> PrototypeBean has been created at " + creationTime);
    }

    public String getCreationTime() {
        return creationTime;
    }
}