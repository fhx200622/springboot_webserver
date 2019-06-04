package com.ficus.face.product.spring.tutorial.demo.service;

import com.ficus.face.product.spring.tutorial.demo.persistance.Student;


/**
 *services interface provide for student
 */
public interface StudentService {

    /**
     * add student inform
     * @param student
     */
    void addStudent(Student student);

    /**
     * delete student inform
     * @param id
     * @return
     */
    boolean delStudent(String id);
}
