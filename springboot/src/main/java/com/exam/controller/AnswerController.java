package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.serviceimpl.AnswerServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @GetMapping("/answers")
    public ApiResult findAllQuestion(
            @RequestParam("currentPage") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(required = false, value = "type", defaultValue = "") String type,
            @RequestParam(required = false, value = "subject", defaultValue = "") String subject,
            @RequestParam(required = false, value = "question", defaultValue = "") String question
    ) {
        AnswerVO params = new AnswerVO();
        params.setQuestion(question);
        params.setType(type);
        params.setSubject(subject);
        Page<AnswerVO> answerVOPage = new Page<>(page, size);
        IPage<AnswerVO> answerVOIPage = answerService.findAll(answerVOPage, params);
        return ApiResultHandler.buildApiResult(200, "查询所有题库", answerVOIPage);
    }
}
