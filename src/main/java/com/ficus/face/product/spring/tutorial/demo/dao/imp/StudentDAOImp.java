package com.ficus.face.product.spring.tutorial.demo.dao.imp;

import com.ficus.face.product.spring.tutorial.demo.persistance.Student;
import com.ficus.face.product.spring.tutorial.demo.dao.StudentDAO;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * implementation for student dao interface
 */
@Component
public class StudentDAOImp implements StudentDAO {
    private final static Logger logger = LoggerFactory.getLogger(StudentDAOImp.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Student student) {
        mongoTemplate.save(student);
    }

    @Override
    public long remove(Student student) {
        DeleteResult result = mongoTemplate.remove(student);
        //logger the student id to be deleted
        logger.debug("delete count: {}", result.getDeletedCount());
        return result.getDeletedCount();
    }

    /**
     * find student by id
     */
    @Override
    public Student findById(String studentId) {
        Query q = new Query(Criteria.where("id").is(studentId));
        return mongoTemplate.findOne(q, Student.class);
    }
}
