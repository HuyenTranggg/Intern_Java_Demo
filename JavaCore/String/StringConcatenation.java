package JavaCore.String;

public class StringConcatenation {
    public static void main(String[] args) {
        String firstName = "John";
        String lastName = "Doe";

        // Cách 1: Dùng toán tử '+' 
        String fullName1 = firstName + " " + lastName;
        System.out.println("Using '+': " + fullName1);

        // Cách 2: Dùng phương thức concat()
        String fullName2 = firstName.concat(" ").concat(lastName);
        System.out.println("Using concat(): " + fullName2);

        // Cách 3: Dùng String.join()
        String[] parts = {"Java", "is", "powerful"};
        String sentence = String.join(" ", parts); // Nối các phần tử bằng dấu cách
        System.out.println("Using String.join(): " + sentence);
        
        // Cách 4: Dùng StringBuilder (hiệu quả khi nối nhiều chuỗi trong vòng lặp do không tạo ra object mới mỗi lần thay đổi)
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        String fullName4 = sb.toString();
        System.out.println("Using StringBuilder: " + fullName4);
    }
}
