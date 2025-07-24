package demo.javaconfig.autowire.beans;

public class V8Engine implements Engine {
    @Override
    public String getType() {
        return "V8 Engine";
    }
}