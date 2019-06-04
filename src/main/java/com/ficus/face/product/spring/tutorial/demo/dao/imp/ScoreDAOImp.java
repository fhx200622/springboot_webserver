package com.ficus.face.product.spring.tutorial.demo.dao.imp;

import com.ficus.face.product.spring.tutorial.demo.persistance.Score;
import com.ficus.face.product.spring.tutorial.demo.dao.ScoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * implementation for score dao interface
 */
@Component
public class ScoreDAOImp implements ScoreDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Score score) {
        mongoTemplate.save(score);
    }

    @Override
    public void batchInsert(List<Score> scores) {

        if (CollectionUtils.isEmpty(scores)) return;

        BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Score.class);

        for (Score score : scores) {
            Criteria criteria = Criteria.where("student_id").is(score.getStudentId())
                    .and("subject_id").is(score.getSubjectId());
            ops.upsert(new Query(criteria), new Update().set("Score", score.getScore()));
            //update or insert incase some already exist
        }
        ops.execute();
    }

    /**
     * find score by student id
     */
    @Override
    public List<Score> findByStudentId(String id) {
        Criteria criteria = Criteria.where("student_id").is(id);
        return mongoTemplate.find(new Query(criteria), Score.class);
    }

    /**
     * find score by subject id
     */
    @Override
    public List<Score> findBySubjectId(String id) {
        Criteria criteria = Criteria.where("subject_id").is(id);
        return mongoTemplate.find(new Query(criteria), Score.class);
    }
}
