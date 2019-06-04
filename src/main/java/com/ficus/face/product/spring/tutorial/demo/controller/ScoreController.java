package com.ficus.face.product.spring.tutorial.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ficus.face.product.spring.tutorial.demo.dto.ResponseDTO;
import com.ficus.face.product.spring.tutorial.demo.dto.StudentScoreResult;
import com.ficus.face.product.spring.tutorial.demo.dto.SubjectScoreDTO;
import com.ficus.face.product.spring.tutorial.demo.persistance.Score;
import com.ficus.face.product.spring.tutorial.demo.service.ScoreService;
import com.ficus.face.product.spring.tutorial.demo.ResponceHelp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * controller of score
 */
@RestController
@RequestMapping("/score")
@Validated
public class ScoreController {
    private final static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    ScoreService scoSvc;

    @PostMapping
    @ApiOperation(value = "add Score")
    public ResponseDTO add(@RequestBody @Valid Score score, BindingResult bindingResult) {
        if (scoSvc.addScore(score)) {
            return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
        } else {
            return ResponceHelp.createBizErrorResponse(ControllerConstant.MSG_STUID_OR_SUBID_NOT_EXIST);
        }
    }

    /**
     * batch add scores
     */
    @RequestMapping(value = "/bulk", method = RequestMethod.POST)
    @ApiOperation(value = "batch add scores")
    public ResponseDTO bulk(@RequestBody String params) {

        logger.debug(params);

        //parse json
        JSONObject jsonObject = JSON.parseObject(params);
        if (jsonObject.containsKey(ControllerConstant.JSON_KEY_INSERT)) {
            JSONArray scoreJsonArray = jsonObject.getJSONArray(ControllerConstant.JSON_KEY_INSERT);

            List<Score> scores = new ArrayList<>();

            //set score and corresponding student id, subject id
            for (Iterator iterator = scoreJsonArray.iterator(); iterator.hasNext(); ) {
                JSONObject scoreObj = (JSONObject) iterator.next();
                if (scoreObj.containsKey(ControllerConstant.JSON_KEY_STUDENT_ID)
                        && scoreObj.containsKey(ControllerConstant.JSON_KEY_SUBJECT_ID)
                        && scoreObj.containsKey(ControllerConstant.JSON_KEY_SCORE)) {
                    Score score = new Score();
                    score.setStudentId(scoreObj.getString(ControllerConstant.JSON_KEY_STUDENT_ID));
                    score.setSubjectId(scoreObj.getString(ControllerConstant.JSON_KEY_SUBJECT_ID));
                    score.setScore(scoreObj.getFloat(ControllerConstant.JSON_KEY_SCORE));
                    scores.add(score);
                }
            }

            //add scores
            scoSvc.batchAddScores(scores);
            return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK);
        }

        return ResponceHelp.createBizErrorResponse(ControllerConstant.MSG_BULK_LOAD_FE);

    }

    @RequestMapping(value = "/getSocreByStudentId", method = RequestMethod.GET)
    @ApiOperation(value = "get all scores of a Student by Student id")
    public ResponseDTO getScoreByStudentId(@RequestParam(required = true) @NotEmpty @ApiParam(value = "Student id") String id) {

        List<SubjectScoreDTO> scores = scoSvc.getScoreByStudentId(id);

        Map<String, Object> ext = new HashMap<>();
        ext.put(ControllerConstant.RESPONSE_DATA, scores);

        return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK, ext);
    }

    @RequestMapping(value = "/getScoreBySubjectId", method = RequestMethod.GET)
    @ApiOperation(value = "get the result of a Subject by Subject id")
    public ResponseDTO getScoreBySubjectId(@RequestParam(required = true) @NotEmpty @ApiParam(value = "Subject id") String id) {

        StudentScoreResult result = scoSvc.getScoreBySubjectId(id);

        Map<String, Object> summary = new HashMap<>();
        summary.put(ControllerConstant.RESPONSE_MAX, result.getMax());
        summary.put(ControllerConstant.RESPONSE_MIN, result.getMin());
        summary.put(ControllerConstant.RESPONSE_AVG, result.getAvg());
        summary.put(ControllerConstant.RESPONSE_DATA, result.getStudentScores());

        return ResponceHelp.createSuccResponse(ControllerConstant.MSG_OK, summary);
    }
}
