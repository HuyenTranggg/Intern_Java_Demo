package demo.annotation.dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component("electricEngine")
public class ElectricEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Electric Engine whirring silently...");
    }
}