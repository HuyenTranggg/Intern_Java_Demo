package JavaCore.Working_Threads;

class Screen {
    // Bien instance duy nhat
    private static Screen theScreen;

    // private constructor de khong the tao instance tu ben ngoai
    private Screen() {
        System.out.println("Mot doi tuong Screen da duoc tao!");
    }

    // Phuong thuc static synchronized de lay instance
    // Khoa duoc ap dung tren doi tuong Class (Screen.class)
    public static synchronized Screen getScreen() {
        if (theScreen == null) {
            theScreen = new Screen();
        }
        return theScreen;
    }
}

public class SynchronizedStaticDemo {
    public static void main(String[] args) {
        // Tao 2 thread cung goi getScreen()
        // Du goi dong thoi, constructor cua Screen chi duoc goi 1 lan duy nhat
        Thread thread1 = new Thread(Screen::getScreen);
        Thread thread2 = new Thread(Screen::getScreen);

        thread1.start();
        thread2.start();
    }
}
