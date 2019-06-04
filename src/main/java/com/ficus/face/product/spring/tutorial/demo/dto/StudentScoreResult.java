package com.ficus.face.product.spring.tutorial.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * results of scores for a student
 */
@Data

public class StudentScoreResult {
    private float max;

    private float min;

    private float avg;

    private List<StudentScoreDTO> studentScores;
}
