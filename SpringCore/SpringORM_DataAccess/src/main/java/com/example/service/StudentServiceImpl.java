package com.example.service;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public void createStudent(Student student) {
        if (studentDao.findByEmail(student.getEmail()) != null) {
            throw new IllegalStateException("Email " + student.getEmail() + " đã tồn tại.");
        }
        studentDao.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(int id) {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }
}