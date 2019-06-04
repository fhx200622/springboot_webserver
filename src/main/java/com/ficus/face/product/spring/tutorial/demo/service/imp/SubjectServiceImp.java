package com.ficus.face.product.spring.tutorial.demo.service.imp;

import com.ficus.face.product.spring.tutorial.demo.dao.SubjectDAO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;
import com.ficus.face.product.spring.tutorial.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation for services interface provide for subject
 */
@Service
public class SubjectServiceImp implements SubjectService {
    @Autowired
    SubjectDAO subjectDao;

    //add subject inform
    @Override
    public void addSubject(Subject subject) {
        subjectDao.save(subject);
    }

    //delete subject inform
    @Override
    public boolean delSubject(String id) {
        Subject s1 = new Subject();
        s1.setId(id);
        if (subjectDao.remove(s1) > 0) {
            return true;
        }
        return false;
    }
}
