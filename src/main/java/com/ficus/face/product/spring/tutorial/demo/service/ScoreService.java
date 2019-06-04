package com.ficus.face.product.spring.tutorial.demo.service;

import com.ficus.face.product.spring.tutorial.demo.dto.StudentScoreResult;
import com.ficus.face.product.spring.tutorial.demo.dto.SubjectScoreDTO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Score;

import java.util.List;

/**
 *services interface provide for scores
 */
public interface ScoreService {

    /**
     * add score
     * @param
     * @return
     */
    boolean addScore(Score score);

    /**
     * batch add scores
     * @param scores
     */
    void batchAddScores(List<Score> scores);

    /**
     * get score by student id
     * @param id
     * @return
     */
    List<SubjectScoreDTO> getScoreByStudentId(String id);

    /**
     * get score by subject id
     * @param id
     * @return
     */
    StudentScoreResult getScoreBySubjectId(String id);
}
