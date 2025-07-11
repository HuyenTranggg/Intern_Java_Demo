package JavaCore.Working_Threads;

class MyThread extends Thread {
    @Override
    public void run() {
        // Code ma thread se thuc thi nam o day
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread (extends): " + i);
            try {
                Thread.sleep(500); // Tam dung 0.5 giay
            } catch (InterruptedException e) {
                System.out.println("Thread bi ngat.");
            }
        }
        System.out.println("Thread (extends) ket thuc.");
    }
}

public class DemoExtendsThread {
    public static void main(String[] args) {
        System.out.println("Main thread bat dau.");

        // Tao mot doi tuong tu lop MyThread
        MyThread t1 = new MyThread();

        // Bat dau thuc thi thread. JVM se goi phuong thuc run() cua t1.
        t1.start();

        System.out.println("Main thread tiep tuc cong viec cua minh...");
        System.out.println("Main thread ket thuc.");
    }
}
