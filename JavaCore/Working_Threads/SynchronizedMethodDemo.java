package JavaCore.Working_Threads;

class SynchronizedCounter {
    private int count = 0;

    // Chi can them tu khoa "synchronized"
    // Tai mot thoi diem, chi co mot thread duoc phep thuc thi phuong thuc nay
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizedMethodDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Ket qua luon luon la 20000
        System.out.println("Gia tri cuoi cung (dung synchronized method): " + counter.getCount());
    }
}
