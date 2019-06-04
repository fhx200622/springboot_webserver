package com.ficus.face.product.spring.tutorial.demo.controller;

import com.ficus.face.product.spring.tutorial.demo.dto.ResponseDTO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Student;
import com.ficus.face.product.spring.tutorial.demo.service.StudentService;
import com.ficus.face.product.spring.tutorial.demo.ResponceHelp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * controller of student
 */
@RestController
@RequestMapping("/student")
@Api("Student operation api")
@Validated

public class StudentController {

    @Autowired
    StudentService studentSvc;

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "add Student")
    public ResponseDTO add(@RequestBody @Valid Student student, BindingResult bindingResult) {
        studentSvc.addStudent(student);
        return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete Student")
    public ResponseDTO delete(@PathVariable(value = "id", required = true) @NotEmpty @ApiParam(value = "Student id") String id) {
        if (studentSvc.delStudent(id)) {
            return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
        } else {
            return ResponceHelp.createBizErrorResponse(ControllerConstant.MSG_DELETE_FAID);
        }
    }
}
