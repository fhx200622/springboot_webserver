package com.ficus.face.product.spring.tutorial.demo.dao;

import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;

/**
 * subject dao interface
 */
public interface SubjectDAO {

    /**
     * save subject inform
     * @param subject
     */
    void save(Subject subject);

    /**
     * remove subject inform
     * @param subject
     * @return
     */
    long remove(Subject subject);

    /**
     * find subject by id
     * @param subjectId
     * @return
     */
    Subject findById(String subjectId);
}
