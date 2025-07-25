package com.example;

import com.example.config.AppConfig;
import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo Spring Context từ class cấu hình
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy Bean từ tầng Service
        StudentService studentService = context.getBean(StudentService.class);

        // --- Bắt đầu Demo ---
        System.out.println("--- DEMO TẠO SINH VIÊN ---");
        try {
            studentService.createStudent(new Student("Lê Hải Y", "y.lehai@email.com"));
            studentService.createStudent(new Student("Ngô Văn X", "x.ngovan@email.com"));
            System.out.println("Tạo 2 sinh viên thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi tạo sinh viên: " + e.getMessage());
        }

        System.out.println("\n--- DEMO THỬ TẠO SINH VIÊN TRÙNG EMAIL ---");
        try {
            studentService.createStudent(new Student("Lê Hải Y Fake", "y.lehai@email.com"));
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        System.out.println("\n--- DEMO LẤY TẤT CẢ SINH VIÊN ---");
        List<Student> students = studentService.getAllStudents();
        students.forEach(System.out::println);

        System.out.println("\n--- DEMO TÌM KIẾM SINH VIÊN THEO ID ---");
        Student student = studentService.getStudentById(2);
        System.out.println("Tìm thấy sinh viên ID 2: " + student);

        context.close();
    }
}