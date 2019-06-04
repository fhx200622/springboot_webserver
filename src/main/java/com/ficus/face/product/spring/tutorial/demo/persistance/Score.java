package com.ficus.face.product.spring.tutorial.demo.persistance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;

/**
 * definition for score
 */
@Data
@Document(collection = "Score")
@ApiModel(value = "Score", description = "Score object")

public class Score {

    @Field("student_id")
    @NotEmpty(message = "studentId is empty")
    @ApiModelProperty(value = "Student id", required = true, example = "0027")
    private String studentId;

    @Field("subject_id")
    @NotEmpty(message = "subjectId is empty")
    @ApiModelProperty(value = "Subject id", required = true, example = "388")
    private String subjectId;

    @NotEmpty(message = "Score is empty")
    @ApiModelProperty(value = "Score", required = true, example = "91")
    private float score;
}
