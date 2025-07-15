package JavaCore.Collection_Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Collection<E> (Interface):
// Là một interface gốc cho hầu hết các cấu trúc dữ liệu như List, Set, Map,... 
// Định nghĩa các hành vi chung (add, remove, size...)

// Collections (Class):
// Là một lớp tiện ích (utility class) chứa các phương thức tĩnh (static methods) để thao tác trên các collection
// Ví dụ như sắp xếp, tìm kiếm, đảo ngược...

public class CollectionVsCollections {
    public static void main(String[] args) {
        // 'List' là một dạng của interface 'Collection'
        List<String> names = new ArrayList<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");

        System.out.println("Original list (from Collection interface): " + names);

        // Dùng lớp tiện ích 'Collections' để thao tác trên collection
        Collections.sort(names); // Sắp xếp list
        System.out.println("Sorted list (using Collections class): " + names);
    }
}
