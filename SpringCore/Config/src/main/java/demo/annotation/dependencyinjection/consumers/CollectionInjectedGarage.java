package demo.annotation.dependencyinjection.consumers;

import demo.annotation.dependencyinjection.beans.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CollectionInjectedGarage {
    private final List<Engine> allAvailableEngines;

    // *** Đây không phải 1 loại Dependency Injection ***
    
    // Spring sẽ tự động tìm tất cả các bean implement interface Engine và gom vào một List.
    @Autowired
    public CollectionInjectedGarage(List<Engine> allAvailableEngines) {
        this.allAvailableEngines = allAvailableEngines;
    }

    public void listEngines() {
        System.out.println("Garage has the following engines:");
        allAvailableEngines.forEach(engine -> System.out.println("- " + engine.getClass().getSimpleName()));
    }
}