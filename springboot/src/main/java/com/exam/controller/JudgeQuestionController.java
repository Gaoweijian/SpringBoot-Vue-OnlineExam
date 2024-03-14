package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.JudgeQuestion;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @PostMapping("/judgeQuestion")
    public ApiResult add(@RequestBody JudgeQuestion judgeQuestion) {
        int res = 0;
        if (Objects.isNull(judgeQuestion.getQuestionId())) {
            res = judgeQuestionService.add(judgeQuestion);
        } else {
            res = judgeQuestionService.edit(judgeQuestion);
        }

        if (res != 0) {
            return ApiResultHandler.buildApiResult(200, "添加成功", res);
        }
        return ApiResultHandler.buildApiResult(400, "添加失败", res);
    }

    @GetMapping("/judgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }

    @GetMapping("/judgeQuestionById")
    public ApiResult judgeQuestionById(@RequestParam(value = "questionId") String questionId) {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionById(questionId);
        return ApiResultHandler.buildApiResult(200, "查询成功", res);
    }
}
