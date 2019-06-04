package com.ficus.face.product.spring.tutorial.demo;

import com.ficus.face.product.spring.tutorial.demo.dto.ResponseDTO;

import java.util.Map;

public class ResponceHelp {
    public static ResponseDTO createBizErrorResponse(String msg) {
        return new ResponseDTO(Constant.RTN_CODE_BIZ_ERROR, msg);
    }

    public static ResponseDTO createSuccResponse(String msg) {
        return new ResponseDTO(Constant.RTN_CODE_OK, msg);
    }

    public static ResponseDTO createSuccResponse(String msg, Map<String, Object> ext) {
        ResponseDTO response = new ResponseDTO(Constant.RTN_CODE_OK, msg);
        response.setExt(ext);

        return response;
    }
}
