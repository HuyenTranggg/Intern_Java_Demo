package com.example.dao;

import com.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Đánh dấu đây là một Spring Bean ở tầng Repository/DAO
public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Override
    public Student findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Student", Student.class).getResultList();
    }

    @Override
    public Student findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("FROM Student WHERE email = :emailParam", Student.class);
        query.setParameter("emailParam", email);
        return query.uniqueResultOptional().orElse(null);
    }
}