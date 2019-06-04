package com.ficus.face.product.spring.tutorial.demo.service;

import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;

/**
 *services interface provide for subject
 */
public interface SubjectService {

    /**
     * add subject inform
     * @param subject
     */
    void addSubject(Subject subject);

    /**
     * delete subject inform
     * @param id
     * @return
     */
    boolean delSubject(String id);
}
