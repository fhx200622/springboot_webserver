package com.ficus.face.product.spring.tutorial.demo.persistance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

/**
 * definition for student
 */
@Data
@Document(collection = "Student")
@ApiModel(value = "Student", description = "Student object")

public class Student {
    @NotEmpty(message = "Student id is empty")
    @Id
    @ApiModelProperty(value = "Student id", required = true, example = "0027")
    private String id;

    @NotEmpty(message = "Student name is empty")
    @ApiModelProperty(value = "Student name", required = true, example = "Nicholas")
    private String name;

    /**
     * student id
     */
    public Student(@NotEmpty(message = "Student id is empty") String id) {
        this.id = id;
    }
}
