package JavaCore.LambdaExpression;

// Cách 1: Kế thừa lớp Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running (by extending Thread class)...");
    }
}

// Cách 2: Implement interface Runnable (khuyến khích)
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread running (by implementing Runnable)...");
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        // Chạy cách 1
        MyThread thread1 = new MyThread();
        thread1.start();

        // Chạy cách 2
        MyRunnable myRunnable = new MyRunnable();
        Thread thread2 = new Thread(myRunnable);
        thread2.start();

        // Chạy bằng Lambda (vì Runnable là functional interface)
        Thread thread3 = new Thread(() -> System.out.println("Thread running (with Lambda)..."));
        thread3.start();
    }
}
