package JavaCore.Working_Threads;

class Counter {
    private int count = 0;

    // Phuong thuc nay khong duoc dong bo
    public void increment() {
        count++; // Thao tac nay khong an toan (read-modify-write)
    }

    public int getCount() {
        return count;
    }
}

public class NonSynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Tao 2 thread cung tang bien dem
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

        // Doi 2 thread hoan thanh
        t1.join();
        t2.join();

        // Ket qua mong muon: 20000
        // Ket qua thuc te: se la mot so < 20000 do Race Condition
        System.out.println("Gia tri cuoi cung (khong dong bo): " + counter.getCount());
    }
}