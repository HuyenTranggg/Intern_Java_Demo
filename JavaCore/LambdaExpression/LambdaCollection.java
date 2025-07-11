package JavaCore.LambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaCollection {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "Anna", "Ben"));

        // 1. Foreach loop
        System.out.println("Printing with forEach:");
        names.forEach(name -> System.out.println("- " + name));

        // 2. Comparator: Sắp xếp danh sách
        names.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println("\nSorted by length: " + names);

        // 3. Predicate và Filter: Lọc 
        // Predicate<T> là functional interface dùng để kiểm tra một điều kiện logic với một đối tượng kiểu T.
        // Nó nhận một đầu vào và trả về true/false
        Predicate<String> startsWithA = (name) -> name.startsWith("A");
        
        System.out.println("\nNames starting with 'A':");
        names.stream()
             .filter(startsWithA) // Hoặc .filter(name -> name.startsWith("A"))
             .forEach(name -> System.out.println("- " + name));
    }
}
