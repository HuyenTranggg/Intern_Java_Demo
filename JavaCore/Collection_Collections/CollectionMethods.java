package JavaCore.Collection_Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// Collection<E> (Interface)
// Là một interface gốc cho hầu hết các cấu trúc dữ liệu như List, Set, Map,... 
// Định nghĩa các hành vi chung (add, remove, size...)

public class CollectionMethods {
    public static void main(String[] args) {
        Collection<String> collection1 = new ArrayList<>();

        // 1. boolean add(Object element)
        collection1.add("Apple");
        collection1.add("Banana");
        System.out.println("After add: " + collection1); // [Apple, Banana]

        // 2. boolean addAll(Collection c)
        Collection<String> collection2 = new ArrayList<>(Arrays.asList("Cherry", "Date"));
        collection1.addAll(collection2);
        System.out.println("After addAll: " + collection1); // [Apple, Banana, Cherry, Date]

        // 3. boolean contains(Object element)
        System.out.println("Contains 'Banana'? " + collection1.contains("Banana")); // true

        // 4. boolean containsAll(Collection c)
        System.out.println("Contains all of collection2? " + collection1.containsAll(collection2)); // true

        // 5. boolean remove(Object element)
        collection1.remove("Date");
        System.out.println("After remove 'Date': " + collection1); // [Apple, Banana, Cherry]

        // 6. boolean retainAll(Collection c) - Giữ lại những phần tử có trong collection c
        Collection<String> retainList = new ArrayList<>(Arrays.asList("Apple", "Grape"));
        // originalCollection hiện là [Apple, Banana, Cherry], retainList là [Apple, Grape]
        // originalCollection sẽ chỉ giữ lại "Apple"
        Collection<String> originalCollection = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
        originalCollection.retainAll(retainList);
        System.out.println("After retainAll with [Apple, Grape]: " + originalCollection); // [Apple]

        // 7. boolean removeAll(Collection c) - Xóa những phần tử có trong collection c
        Collection<String> fullList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collection<String> toRemove = new ArrayList<>(Arrays.asList("B", "D"));
        fullList.removeAll(toRemove);
        System.out.println("After removeAll: " + fullList); // [A, C]

        // 8. int size()
        System.out.println("Size: " + fullList.size()); // 2

        // 9. void clear()
        fullList.clear();
        System.out.println("After clear: " + fullList); // []
        System.out.println("Is empty? " + fullList.isEmpty()); // true
    }
}
