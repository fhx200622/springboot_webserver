package com.ficus.face.product.spring.tutorial.demo.dao;

import com.ficus.face.product.spring.tutorial.demo.persistance.Score;

import java.util.List;

/**
 * score dao interface
 */
public interface ScoreDAO {
    /**
     * save score
     * @param score
     */
    void save(Score score);

    /**
     * batch insert scores
     * @param scores
     */
    void batchInsert(List<Score> scores);

    /**
     * find score by student id
     * @param id
     * @return
     */
    List<Score> findByStudentId(String id);

    /**
     * find score by subject id
     * @param id
     * @return
     */
    List<Score> findBySubjectId(String id);
}
