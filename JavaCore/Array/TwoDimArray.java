package JavaCore.Array;

public class TwoDimArray {
    public static void main(String[] args) {
        // Mảng 2 chiều là một mảng của các mảng
        int[][] matrix = {
            {1, 2, 3}, // Hàng 0
            {4, 5, 6}, // Hàng 1
            {7, 8, 9}  // Hàng 2
        };

        System.out.println("Element at row 1, col 2: " + matrix[1][2]); // In ra 6

        // Duyệt mảng 2 chiều
        System.out.println("Matrix content:");
        for (int i = 0; i < matrix.length; i++) { // Lặp qua các hàng
            for (int j = 0; j < matrix[i].length; j++) { // Lặp qua các cột trong hàng i
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println(); // Xuống dòng sau mỗi hàng
        }
    }
}
