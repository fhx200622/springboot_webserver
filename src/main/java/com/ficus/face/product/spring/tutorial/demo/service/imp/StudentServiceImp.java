package com.ficus.face.product.spring.tutorial.demo.service.imp;

import com.ficus.face.product.spring.tutorial.demo.dao.StudentDAO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Student;
import com.ficus.face.product.spring.tutorial.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation for services interface provide for student
 */
@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentDAO studentDao;

    //add student inform
    @Override
    public void addStudent(Student student) {
        studentDao.save(student);
    }

    //delete student inform
    @Override
    public boolean delStudent(String id) {
        Student s1 = new Student(id);

        if (studentDao.remove(s1) > 0) {
            return true;
        }
        return false;
    }
}
