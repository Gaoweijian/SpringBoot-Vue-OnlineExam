package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class FillQuestionController {

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @PostMapping("/fillQuestion")
    public ApiResult add(@RequestBody FillQuestion fillQuestion) {
        int res = 0;
        if(Objects.isNull(fillQuestion.getQuestionId())){
            res = fillQuestionService.add(fillQuestion);
        }else {
            res = fillQuestionService.edit(fillQuestion);
        }
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @GetMapping("/fillQuestionId")
    public ApiResult findOnlyQuestionId() {
        FillQuestion res = fillQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @GetMapping("/fillQuestionById")
    public ApiResult fillQuestionById(@RequestParam(value = "questionId") String questionId) {
        FillQuestion res = fillQuestionService.findFillQuestionId(questionId);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
