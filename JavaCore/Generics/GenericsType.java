package JavaCore.Generics;

// Generics cho phép tạo ra các lớp và phương thức có thể làm việc 
// với nhiều kiểu dữ liệu khác nhau mà vẫn đảm bảo an toàn tại thời điểm biên dịch.

// Một lớp Generic Box có thể chứa bất kỳ kiểu dữ liệu nào
class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

public class GenericsType {
    public static void main(String[] args) {
        // Tạo một Box chứa Integer
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        // integerBox.setContent("hello"); // LỖI BIÊN DỊCH!

        int value = integerBox.getContent();
        System.out.println("Integer Box content: " + value);
        
        // Tạo một Box chứa String
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello Generics");
        String text = stringBox.getContent();
        System.out.println("String Box content: " + text);
    }
}