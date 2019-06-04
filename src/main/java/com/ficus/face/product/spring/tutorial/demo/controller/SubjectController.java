package com.ficus.face.product.spring.tutorial.demo.controller;

import com.ficus.face.product.spring.tutorial.demo.dto.ResponseDTO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Subject;
import com.ficus.face.product.spring.tutorial.demo.service.SubjectService;
import com.ficus.face.product.spring.tutorial.demo.ResponceHelp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * controller of subject
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectSvc;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "add Subject")
    public ResponseDTO add(@RequestBody @Valid Subject subject) {
        subjectSvc.addSubject(subject);
        return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete Subject")
    public ResponseDTO delete(@PathVariable("id") @ApiParam(value = "Subject id") String id) {
        if (subjectSvc.delSubject(id)) {
            return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
        } else {
            return ResponceHelp.createBizErrorResponse(ControllerConstant.MSG_DELETE_FAID);
        }
    }
}
