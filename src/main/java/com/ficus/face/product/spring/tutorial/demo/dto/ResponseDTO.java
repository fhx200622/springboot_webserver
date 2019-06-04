package com.ficus.face.product.spring.tutorial.demo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Map;

/**
 * http response
 */
@Data
@ApiModel(value = "ResponseDTO", description = "response object")
public class ResponseDTO {
    private int rtn;

    private String message;

    private Map<String, Object> ext;

    public ResponseDTO(int rtn, String message) {
        this.rtn = rtn;
        this.message = message;
    }
}
