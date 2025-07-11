package JavaCore.LambdaExpression;

// Lambda expression là cách viết ngắn gọn của một Functional Interface (interface chỉ có một phương thức trừu tượng)
// Cú pháp: (parameters) -> { body }

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaSyntax {
    public static void main(String[] args) {
        // 1. With type declaration
        MathOperation addition = (int a, int b) -> a + b; // Có nghĩa là: một hàm nhận vào 2 số a và b, trả về a + b

        // 2. Without type declaration
        MathOperation subtraction = (a, b) -> a - b;

        // 3. With return statement and curly braces (Multiple statements)
        MathOperation multiplication = (a, b) -> {
            System.out.println("Multiplying " + a + " and " + b);
            return a * b;
        };

        // 4. Without return and curly braces (Single statement)
        MathOperation division = (a, b) -> a / b;

        System.out.println("10 + 5 = " + addition.operate(10, 5));
        System.out.println("10 - 5 = " + subtraction.operate(10, 5));
        System.out.println("10 * 5 = " + multiplication.operate(10, 5));
        System.out.println("10 / 5 = " + division.operate(10, 5));
    }
}
