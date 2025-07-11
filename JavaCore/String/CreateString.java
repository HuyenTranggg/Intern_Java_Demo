package JavaCore.String;

public class CreateString {
    public static void main(String[] args) {
        // Cách 1: Dùng string literal 
        // Java sẽ đưa chuỗi này vào "String Pool"
        String s1 = "Hello, World!";
        System.out.println("s1 (literal): " + s1);

        // Cách 2: Dùng từ khóa 'new' (tạo một đối tượng mới trong heap)
        String s2 = new String("Hello, World!");
        System.out.println("s2 (new object): " + s2);

        // Cách 3: Từ một mảng char
        char[] charArray = {'J', 'a', 'v', 'a'};
        String s3 = new String(charArray);
        System.out.println("s3 (from char array): " + s3);
    }
}