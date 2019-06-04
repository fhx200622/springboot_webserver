package com.ficus.face.product.spring.tutorial.demo.persistance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

/**
 * definition for subject
 */
@Data
@Document(collection = "Subject")
@ApiModel(value = "Subject", description = "Subject object")

public class Subject {
    @NotEmpty(message = "Subject id is empty")
    @Id
    @ApiModelProperty(value = "Subject id", required = true, example = "388")
    private String id;

    @NotEmpty(message = "Subject name is empty")
    @ApiModelProperty(value = "Subject name", required = true, example = "CS")
    private String name;
}
