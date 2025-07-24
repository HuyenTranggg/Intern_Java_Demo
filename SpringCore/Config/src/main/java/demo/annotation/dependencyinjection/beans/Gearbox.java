package demo.annotation.dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component
public class Gearbox {
    public void engage() {
        System.out.println("Gearbox engaged.");
    }
}