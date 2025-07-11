package JavaCore.Generics;

// PECS Principle: Producer Extends, Consumer Super.
// Producer (? extends T): Nếu chỉ lấy/đọc (produce) dữ liệu từ collection, dùng extends.
// Consumer (? super T): Nếu chỉ thêm/ghi (consume) dữ liệu vào collection, dùng super.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wildcards {
    // Wildcard Argument
    // Dấu '?' có nghĩa là "một kiểu không xác định"
    public static void printList(List<?> list) { 
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
    
    // Upper-Bound Wildcard (Producer Extends)
    // <? extends T>: Cho phép truyền vào bất kỳ kiểu nào là CON của T (bao gồm chính T)
    // Phương thức này chỉ đọc từ list để tính tổng, nên nó là producer.
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        // list.add(123); // LỖI BIÊN DỊCH! Không thể ghi vì là một producer.
        return sum;
    }

    // Lower-Bound Wildcard (Consumer Super)
    // <? super T>: Cho phép truyền vào bất kỳ kiểu nào là CHA của T (bao gồm chính T)
    // Phương thức này chỉ thêm số nguyên vào list, nên nó là consumer.
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<String> stringList = Arrays.asList("A", "B", "C");

        System.out.print("Integer List: ");
        printList(intList);
        System.out.print("String List: ");
        printList(stringList);

        System.out.println("\nSum of Integers: " + sumOfList(intList));
        System.out.println("Sum of Doubles: " + sumOfList(doubleList));
        // sumOfList(stringList); // LỖI BIÊN DỊCH! String không phải là Number.
        
        List<Number> numList = new ArrayList<>();
        addIntegers(numList);
        System.out.println("\nNumbers after adding integers: " + numList);
    }
}
