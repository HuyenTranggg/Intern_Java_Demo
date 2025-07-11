package JavaCore.String;

public class StringComparison {
    public static void main(String[] args) {
        String str1 = "Java";
        String str2 = "java";
        String str3 = "Java";

        // 1. Dùng equals() - So sánh nội dung, phân biệt hoa/thường
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // false
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true

        // 2. Dùng equalsIgnoreCase() - So sánh nội dung, không phân biệt hoa/thường
        System.out.println("str1.equalsIgnoreCase(str2): " + str1.equalsIgnoreCase(str2)); // true

        // 3. Dùng compareTo() - So sánh theo thứ tự từ điển, trả về số nguyên
        // < 0 nếu str1 đứng trước str2
        // = 0 nếu chúng bằng nhau
        // > 0 nếu str1 đứng sau str2
        System.out.println("str1.compareTo(str2): " + str1.compareTo(str2)); // < 0 vì 'J' < 'j'
        System.out.println("str1.compareTo(str3): " + str1.compareTo(str3)); // 0

        // 4. Dùng toán tử '==' - CHỈ so sánh tham chiếu (địa chỉ bộ nhớ), không so sánh nội dung
        String str4 = new String("Java");
        System.out.println("str1 == str3: " + (str1 == str3)); // true (vì cùng trỏ đến đối tượng trong String Pool)
        System.out.println("str1 == str4: " + (str1 == str4)); // false (vì str4 là đối tượng mới trong heap memory, khác với String Pool)
    }
}