package demo.annotation.dependencyinjection.beans;

import org.springframework.stereotype.Component;

@Component("v8Engine")
public class V8Engine implements Engine {
    @Override
    public void start() {
        System.out.println("V8 Engine roaring to life!");
    }
}