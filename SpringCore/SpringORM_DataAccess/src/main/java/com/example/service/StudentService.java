package com.example.service;

import com.example.entity.Student;
import java.util.List;

public interface StudentService {
    void createStudent(Student student);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}