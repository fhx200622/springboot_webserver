package com.ficus.face.product.spring.tutorial.demo.dao.imp;

import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;
import com.ficus.face.product.spring.tutorial.demo.dao.SubjectDAO;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * implementation for subject dao interface
 */
@Component

public class SubjectDAOImp implements SubjectDAO {
    private final static Logger logger = LoggerFactory.getLogger(SubjectDAOImp.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Subject subject) {
        mongoTemplate.save(subject);
    }

    @Override
    public long remove(Subject subject) {
        DeleteResult result = mongoTemplate.remove(subject);
        //logger the subject id to be deleted
        logger.debug("delete count: {}", result.getDeletedCount());
        return result.getDeletedCount();
    }

    /**
     * find subject by id
     */
    @Override
    public Subject findById(String subjectId) {
        Query q = new Query(Criteria.where("id").is(subjectId));
        return mongoTemplate.findOne(q, Subject.class);
    }
}
