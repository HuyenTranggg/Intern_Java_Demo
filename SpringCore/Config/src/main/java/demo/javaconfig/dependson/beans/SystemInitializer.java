package demo.javaconfig.dependson.beans;

public class SystemInitializer {
    public SystemInitializer() {
        System.out.println("1. SystemInitializer: Performing critical system setup...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   SystemInitializer: Setup complete. System is ready.");
    }
}