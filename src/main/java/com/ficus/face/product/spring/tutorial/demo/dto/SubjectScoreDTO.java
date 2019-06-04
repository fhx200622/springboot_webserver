package com.ficus.face.product.spring.tutorial.demo.dto;

import lombok.Data;

/**
 * score of sybject
 */
@Data

public class SubjectScoreDTO {
    private String subjectName;

    private float score;

    public SubjectScoreDTO(String subjectName, float score) {
        this.subjectName = subjectName;
        this.score = score;
    }
}
