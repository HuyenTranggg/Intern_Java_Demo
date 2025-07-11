package JavaCore.Working_Threads;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Code ma thread se thuc thi nam o day
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread (implements): " + i);
            try {
                Thread.sleep(500); // Tam dung 0.5 giay
            } catch (InterruptedException e) {
                System.out.println("Thread bi ngat.");
            }
        }
        System.out.println("Thread (implements) ket thuc.");
    }
}

public class DemoImplementsRunnable {
    public static void main(String[] args) {
        System.out.println("Main thread bat dau.");

        // 1. Tao mot doi tuong tu lop MyRunnable (day la tac vu - task)
        MyRunnable myTask = new MyRunnable();

        // 2. Tao mot doi tuong Thread va truyen tac vu (task) vao
        Thread t1 = new Thread(myTask);

        // 3. Bat dau thuc thi thread.
        t1.start();

        System.out.println("Main thread tiep tuc cong viec cua minh...");
        System.out.println("Main thread ket thuc.");
    }
}
