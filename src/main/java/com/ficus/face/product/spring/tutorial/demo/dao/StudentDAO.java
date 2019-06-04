package com.ficus.face.product.spring.tutorial.demo.dao;

import com.ficus.face.product.spring.tutorial.demo.persistance.Student;

/**
 * student dao interface
 */
public interface StudentDAO {

    /**
     * save student inform
     * @param student
     */
    void save(Student student);

    /**
     * remove student inform
     * @param student
     * @return
     */
    long remove(Student student);

    /**
     * find student by student id
     * @param studentId
     * @return
     */
    Student findById(String studentId);
}
