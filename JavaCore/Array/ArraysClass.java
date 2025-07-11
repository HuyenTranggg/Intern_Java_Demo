package JavaCore.Array;

import java.util.Arrays;

public class ArraysClass {
    public static void main(String[] args) {
        int[] arr1 = {3, 1, 4, 1, 5, 9};
        int[] arr2 = {1, 1, 3, 4, 5, 9};
        int[] arr3 = {3, 1, 4, 1, 5, 9};

        // 1. Sắp xếp mảng
        Arrays.sort(arr1);
        System.out.println("Sorted arr1: " + Arrays.toString(arr1));

        // 2. So sánh mảng (so sánh nội dung)
        System.out.println("Arrays.equals(arr1, arr2): " + Arrays.equals(arr1, arr2)); // true 
        System.out.println("Arrays.equals(arr1, arr3): " + Arrays.equals(arr1, arr3)); // false 
        
        // 3. So sánh mảng 2 chiều
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        // Arrays.deepEquals() dùng để so sánh nội dung của 2 mảng đa chiều.
        System.out.println("Arrays.deepEquals(matrix1, matrix2): " + Arrays.deepEquals(matrix1, matrix2)); // true

        // 4. Tìm kiếm (mảng phải được sắp xếp trước)
        int index = Arrays.binarySearch(arr1, 4);
        System.out.println("Index of 4 in sorted arr1: " + index);

        // 5. Điền giá trị vào mảng
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 100);
        System.out.println("Filled array: " + Arrays.toString(filledArray));
    }
}
