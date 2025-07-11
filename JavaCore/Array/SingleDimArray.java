package JavaCore.Array;

public class SingleDimArray {
    public static void main(String[] args) {
        // Cách 1: Khai báo và khởi tạo kích thước, sau đó gán giá trị
        int[] numbers = new int[5]; // Một mảng 5 số nguyên, mặc định là 0
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;

        System.out.println("Element at index 2: " + numbers[2]);

        // Cách 2: Khai báo và khởi tạo giá trị luôn
        String[] fruits = {"Apple", "Banana", "Orange"};

        // Duyệt mảng
        System.out.println("List of fruits:");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        System.out.println("Length of numbers array: " + numbers.length);
    }
}
