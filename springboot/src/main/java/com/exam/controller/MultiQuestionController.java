package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.MultiQuestion;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class MultiQuestionController {

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @GetMapping("/multiQuestionId")
    public ApiResult findOnlyQuestion() {
        MultiQuestion res = multiQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @PostMapping("/MultiQuestion")
    public ApiResult add(@RequestBody MultiQuestion multiQuestion) {
        int res = 0 ;
        if(Objects.isNull(multiQuestion.getQuestionId())){
             res = multiQuestionService.add(multiQuestion);
        }else {
            res = multiQuestionService.edit(multiQuestion);
        }
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }


    @GetMapping("/multiQuestionById")
    public ApiResult findOnlyQuestion(@RequestParam(value = "questionId") String questionId) {
        MultiQuestion res = multiQuestionService.findQuestionById(questionId);
        res.setRightAnswers(res.getRightAnswer().split(","));
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
