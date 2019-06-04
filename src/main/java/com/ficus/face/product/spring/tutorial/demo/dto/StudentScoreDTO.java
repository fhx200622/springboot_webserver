package com.ficus.face.product.spring.tutorial.demo.dto;

import lombok.Data;

/**
 * score of student
 */
@Data

public class StudentScoreDTO {
    private String studentName;

    private float score;

    public StudentScoreDTO(String studentName, float score) {
        this.studentName = studentName;
        this.score = score;
    }
}
