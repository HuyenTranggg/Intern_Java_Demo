import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DemoHandleException {

    public static void main(String[] args) {
        System.out.println("BAT DAU DEMO EXCEPTION HANDLING");

        // Demo 1: Unchecked Exception
        demoUncheckedException();

        // Demo 2: Các phương thức của một Exception (getMessage, toString, printStackTrace)
        demoExceptionMethods();

        // Demo 3: Xử lý Checked Exception bằng try-catch
        demoCheckedExceptionWithTryCatch();

        // Demo 4: Xử lý Checked Exception bằng cách throw
        try {
            demoCheckedExceptionWithThrows();
        } catch (FileNotFoundException e) {
            System.out.println("\n--- Demo 4: Checked Exception (voi throws) ---");
            System.out.println("Loi da duoc xu ly o ham main: " + e.getMessage());
        }

        // Demo 5: Khối finally
        demoFinally();

        // Demo 6: Nhiều khối catch
        demoMultipleCatchBlocks();
        
        // Demo 7: Nested try (try lồng nhau)
        demoNestedTry();

        // Demo 8: Từ khóa "throw" để chủ động ném ra một ngoại lệ
        demoThrowKeyword();
        
        // Demo 9: User-defined Exception
        demoUserDefinedException();

        // Demo 10: Sự khác biệt giữa Error và Exception (StackOverflowError)
        // Nó sẽ làm chương trình bị crash.
        // demoError();

        System.out.println("\nKET THUC CHUONG TRINH DEMO.");
    }

    // Demo 1: Unchecked Exception (ví dụ: ArithmeticException)
    public static void demoUncheckedException() {
        System.out.println("\n--- Demo 1: Unchecked Exception ---");
        try {
            System.out.println("Thuc hien phep chia 10 / 0...");
            int result = 10 / 0; // Dòng này sẽ gây ra ArithmeticException
            System.out.println("Ket qua: " + result); // Dòng này không bao giờ được thực thi
        } catch (ArithmeticException e) {
            System.out.println("Da bat duoc ngoai le! Khong the chia cho 0.");
        }
    }

    // Demo 2: Sử dụng các phương thức getMessage(), toString() và printStackTrace()

    public static void demoExceptionMethods() {
        System.out.println("\n--- Demo 2: Cac phuong thuc cua Exception ---");
        try {
            int[] arr = new int[5];
            arr[10] = 50; // Gây ra ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("e.getMessage(): " + e.getMessage());
            System.out.println("e.toString(): " + e.toString());
            System.out.println("e.printStackTrace(): (in ra luong loi tieu chuan)");
            e.printStackTrace(); // In ra chi tiết lỗi và dấu vết ngăn xếp (call stack)
        }
    }

    // Demo 3: Xử lý một Checked Exception (FileNotFoundException) bằng try-catch
    public static void demoCheckedExceptionWithTryCatch() {
        System.out.println("\n--- Demo 3: Checked Exception (voi try-catch) ---");
        File file = new File("non_existent_file.txt");
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Doc file thanh cong."); // Sẽ không được thực thi
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Da bat duoc ngoai le! File khong ton tai.");
        }
    }

    //Demo 4: Khai báo một Checked Exception bằng "throws" để phương thức gọi nó xử lý
    public static void demoCheckedExceptionWithThrows() throws FileNotFoundException {
        // Phương thức này không tự xử lý lỗi mà "ném" nó cho hàm main xử lý
        File file = new File("another_non_existent_file.txt");
        Scanner scanner = new Scanner(file);
        // code dưới đây sẽ không chạy
        scanner.close();
    }
    
    // Demo 5: Sử dụng khối "finally"

    public static void demoFinally() {
        System.out.println("\n--- Demo 5: Khoi finally ---");
        // Trường hợp 1: không có exception
        System.out.println("Truong hop 1: Khong co exception");
        try {
            System.out.println("Ben trong khoi try.");
        } catch (Exception e) {
            System.out.println("Ben trong khoi catch.");
        } finally {
            System.out.println("Khoi finally luon duoc thuc thi.");
        }
        
        // Trường hợp 2: có exception
        System.out.println("\nTruong hop 2: Co exception");
        try {
            System.out.println("Ben trong khoi try, chuan bi gay loi...");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Ben trong khoi catch, da bat duoc loi.");
        } finally {
            System.out.println("Khoi finally van duoc thuc thi du co loi.");
        }
    }

    //Demo 6: Sử dụng nhiều khối catch để bắt các loại ngoại lệ khác nhau

    public static void demoMultipleCatchBlocks() {
        System.out.println("\n--- Demo 6: Nhieu khoi catch ---");
        try {
            int a[] = new int[5];
            // Thay đổi dòng dưới đây để xem các khối catch khác nhau hoạt động
            // a[5] = 30; // Gây ra ArrayIndexOutOfBoundsException
            a[4] = 30 / 0; // Gây ra ArithmeticException
            System.out.println("Du lieu: " + a[4]);
        } catch (ArithmeticException e) {
            System.out.println("Da bat duoc ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Da bat duoc ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Da bat duoc mot Exception chung khac.");
        }
    }
    
    // Demo 7: Nested try (try lồng nhau)

    public static void demoNestedTry() {
        System.out.println("\n--- Demo 7: Nested try ---");
        try { // try-block ngoài
            // try-block con 1
            try {
                System.out.println("Ben trong try-block con 1");
                int b = 30 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Da bat ArithmeticException trong block con 1");
            }

            // try-block con 2
            try {
                System.out.println("Ben trong try-block con 2");
                int a[] = new int[5];
                a[5] = 4;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Da bat ArrayIndexOutOfBoundsException trong block con 2");
            }

            System.out.println("Mot cau lenh khac");

        } catch (Exception e) {
            System.out.println("Duoc xu ly boi khoi catch ngoai cung.");
        }
    }

    // Demo 8: Dùng "throw" để ném một ngoại lệ một cách chủ động
    public static void demoThrowKeyword() {
        System.out.println("\n--- Demo 8: Tu khoa 'throw' ---");
        try {
            validateAge(15);
        } catch (ArithmeticException e) {
            System.out.println("Da bat duoc ngoai le: " + e.getMessage());
        }
    }
    
    // Phương thức phụ cho demo 8
    public static void validateAge(int age) {
        if (age < 18) {
            // Chủ động ném ra một ngoại lệ nếu tuổi không hợp lệ
            throw new ArithmeticException("Tuoi khong hop le, phai tu 18 tro len.");
        } else {
            System.out.println("Tuoi hop le.");
        }
    }

    // Demo 9: Sử dụng một lớp Exception tự định nghĩa

    public static void demoUserDefinedException() {
        System.out.println("\n--- Demo 9: User-defined Exception ---");
        try {
            checkProductCode("PROD-123"); // Hợp lệ
            checkProductCode("INVALID");  // Không hợp lệ, sẽ ném exception
        } catch (InvalidProductCodeException e) {
            System.out.println("Loi tuy chinh da xay ra: " + e.getMessage());
        }
    }

    // Phương thức phụ cho demo 9
    public static void checkProductCode(String code) throws InvalidProductCodeException {
        if (!code.startsWith("PROD-")) {
            throw new InvalidProductCodeException("Ma san pham '" + code + "' khong hop le. Phai bat dau bang 'PROD-'.");
        } else {
            System.out.println("Ma san pham '" + code + "' hop le.");
        }
    }

    // Demo 10: Minh họa một Error (StackOverflowError)
    public static void demoError() {
        System.out.println("\n--- Demo 10: Error (StackOverflowError) ---");
        // Lời gọi đệ quy vô hạn sẽ gây ra lỗi tràn bộ nhớ ngăn xếp
        demoError();
    }
}

/**
 * Lớp Exception tùy chỉnh cho Demo 9.
 * Kế thừa từ Exception để nó trở thành một Checked Exception.
 */
class InvalidProductCodeException extends Exception {
    public InvalidProductCodeException(String message) {
        super(message); // Gọi constructor của lớp cha (Exception)
    }
}