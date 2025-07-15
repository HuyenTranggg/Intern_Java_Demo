package JavaCore.Generics;

import java.util.ArrayList;
import java.util.List;

// Là việc dùng một lớp generic mà không chỉ định tham số kiểu.

public class RawType {
    public static void main(String[] args) {
        List rawList = new ArrayList(); 
        rawList.add("Hello");
        rawList.add(123); // Không có lỗi biên dịch! Rất nguy hiểm.

        // Lỗi sẽ xảy ra khi chạy chương trình
        try {
            for (Object obj : rawList) {
                String str = (String) obj; // Gây ra ClassCastException ở phần tử thứ 2
                System.out.println(str);
            }
        } catch (ClassCastException e) {
            System.err.println("Error: Cannot cast Integer to String. Raw types are dangerous!");
        }
    }
}
