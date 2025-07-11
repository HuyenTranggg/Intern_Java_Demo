package JavaCore;
import java.util.Scanner;

public class ControlStatementsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Java Control Statements Demo ===");
            System.out.println("1. Check if number is even/odd (if-else)");
            System.out.println("2. Display day of week (switch)");
            System.out.println("3. Print star pattern (nested loops)");
            System.out.println("4. Search array with break (while loop)");
            System.out.println("5. Print odd numbers with continue (for loop)");
            System.out.println("6. Search array with do-while");
            System.out.println("7. Find number in 2D array with labeled break");
            System.out.println("8. Get welcome message (return statement)");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkEvenOdd(scanner);
                    break;
                case 2:
                    displayDayOfWeek(scanner);
                    break;
                case 3:
                    printStarPattern();
                    break;
                case 4:
                    searchArray();
                    break;
                case 5:
                    printOddNumbers();
                    break;
                case 6:
                    searchArrayDoWhile();
                    break;
                case 7:
                    search2DArrayLabeledBreak();
                    break;
                case 8:
                    System.out.println("Message: " + getWelcomeMessage());
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1-9.");
            }
        }
    }

    // 1. Boolean expressions and if-else statement
    static void checkEvenOdd(Scanner scanner) {
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        boolean isPositive = number >= 0;
        boolean isEven = number % 2 == 0;

        if (isPositive && isEven) {
            System.out.println(number + " is positive and even");
        } else if (isPositive && !isEven) {
            System.out.println(number + " is positive and odd");
        } else {
            System.out.println(number + " is negative");
        }
    }

    // 2. Switch statement
    static void displayDayOfWeek(Scanner scanner) {
        System.out.print("Enter a number (1-7) for day of week: ");
        int day = scanner.nextInt();
        
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid entry");
        }
    }

    // 3. Nested loops for star pattern
    static void printStarPattern() {
        int n = 4;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 4. While loop with break
    static void searchArray() {
        String[] arr = {"A", "E", "I", "O", "U"};
        String search = "E";
        int index = 0;
        
        while (index < arr.length) {
            if (arr[index].equalsIgnoreCase(search)) {
                System.out.println("Array contains '" + search + "' at index: " + index);
                break;
            }
            index++;
        }
    }

    // 5. For loop with continue
    static void printOddNumbers() {
        System.out.println("Odd numbers from 1 to 10:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 6. Do-while loop
    static void searchArrayDoWhile() {
        String[] arr = {"A", "E", "I", "O", "U"};
        String search = "U";
        int index = 0;
        
        do {
            if (arr[index].equalsIgnoreCase(search)) {
                System.out.println("Array contains '" + search + "' at index: " + index);
                break;
            }
            index++;
        } while (index < arr.length);
    }

    // 7. Labeled break in nested loops
    static void search2DArrayLabeledBreak() {
        int[][] arr = {{1, 2}, {3, 4}, {9, 10}, {11, 12}};
        boolean found = false;
        int row = 0;
        int col = 0;

        searchint:
        for (row = 0; row < arr.length; row++) {
            for (col = 0; col < arr[row].length; col++) {
                if (arr[row][col] > 10) {
                    found = true;
                    break searchint;
                }
            }
        }
        if (found) {
            System.out.println("First int greater than 10 is found at index: [" + row + "," + col + "]");
        } else {
            System.out.println("No number greater than 10 found");
        }
    }

    // 8. Return statement
    static String getWelcomeMessage() {
        String language = "java";
        if (language.equalsIgnoreCase("java")) {
            return "Welcome to Java!";
        }
        return "Welcome to programming!";
    }
}