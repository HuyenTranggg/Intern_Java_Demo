package JavaCore.Working_Threads;

public class LifecycleAndPriorityDemo {

    public static void main(String[] args) {
        System.out.println("Bat dau chuong trinh trong Main Thread.");

        // Tao 3 thread voi cac do uu tien khac nhau
        Thread threadThap = new MyTask("Do uu tien THAP");
        threadThap.setPriority(Thread.MIN_PRIORITY); // Gia tri la 1

        Thread threadTrungBinh = new MyTask("Do uu tien TRUNG BINH");
        threadTrungBinh.setPriority(Thread.NORM_PRIORITY); // Gia tri la 5

        Thread threadCao = new MyTask("Do uu tien CAO");
        threadCao.setPriority(Thread.MAX_PRIORITY); // Gia tri la 10

        // Khoi chay cac thread
        // Khi goi start(), thread chuyen tu trang thai NEW sang RUNNABLE
        System.out.println("\nKhoi chay cac thread...");
        threadCao.start();
        threadTrungBinh.start();
        threadThap.start();

        System.out.println("Main Thread da khoi chay xong cac thread con va ket thuc.");
        // Luu y: Thu tu thuc thi khong hoan toan duoc dam bao,
        // nhung he dieu hanh se "co gang" uu tien cho thread co priority cao hon.
    }
}

// Mot tac vu don gian de thread thuc thi
class MyTask extends Thread {
    public MyTask(String name) {
        super(name); // Dat ten cho thread thong qua constructor cua lop cha
    }

    @Override
    public void run() {
        // Khi vao ham run(), thread dang o trang thai RUNNING
        System.out.println("Thread '" + getName() + "' dang chay voi do uu tien " + getPriority());

        try {
            // Dung sleep() de gia lap cong viec ton thoi gian
            // Khi sleep(), thread chuyen sang trang thai BLOCKED/TIMED_WAITING
            System.out.println("Thread '" + getName() + "' di ngu trong 1 giay.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sau khi thuc thi xong ham run(), thread se chuyen sang trang thai TERMINATED
        System.out.println(">>> Thread '" + getName() + "' da hoan thanh.");
    }
}