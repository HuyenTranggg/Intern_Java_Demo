package com.example;

import com.example.config.AppConfig;
import com.example.dao.StudentDao;
import com.example.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo Spring Context từ class cấu hình
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Lấy Bean 'studentDao' từ Spring Context
        // Spring đã tự động tạo đối tượng StudentDaoImpl và quản lý nó
        StudentDao studentDao = context.getBean(StudentDao.class);

        // --- Bắt đầu Demo ---
        System.out.println("--- DEMO THÊM MỘT SINH VIÊN MỚI ---");
        Student newStudent = new Student("Vũ Thu Hiền", "hienvuthu@example.com");
        studentDao.save(newStudent);
        System.out.println("Đã lưu: " + newStudent);

        System.out.println("\n--- DEMO TÌM KIẾM SINH VIÊN THEO ID ---");
        Student foundStudent = studentDao.findById(newStudent.getId());
        System.out.println("Tìm thấy: " + foundStudent);

        System.out.println("\n--- DEMO LẤY TẤT CẢ SINH VIÊN ---");
        studentDao.save(new Student("Trần Thị Hồng", "tranthihong@example.com"));
        List<Student> students = studentDao.findAll();
        students.forEach(System.out::println);

        // 3. Đóng Spring Context
        context.close();
    }
}