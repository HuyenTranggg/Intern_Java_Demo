package demo.javaconfig.autowire.beans;

public class ElectricEngine implements Engine {
    @Override
    public String getType() {
        return "Electric Engine";
    }
}