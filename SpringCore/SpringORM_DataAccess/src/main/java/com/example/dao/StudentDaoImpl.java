package com.example.dao;

import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Đánh dấu đây là một Spring Bean ở tầng Repository/DAO
public class StudentDaoImpl implements StudentDao {

    @Autowired // Spring sẽ tự động inject (tiêm) SessionFactory đã được cấu hình
    private SessionFactory sessionFactory;

    @Override
    @Transactional // Tất cả các thao tác trong method này sẽ được bọc trong một transaction
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Override
    @Transactional(readOnly = true) // Giao dịch chỉ đọc
    public Student findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Student", Student.class).getResultList();
    }
}