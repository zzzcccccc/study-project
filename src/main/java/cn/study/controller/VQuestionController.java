package cn.study.controller;

import cn.study.service.VQuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @description v_question
 * @author zhangcc
 * @date 2023-02-09
 */
@RestController
@RequestMapping(value = "/vQuestion")
public class VQuestionController {

    @Resource
    private VQuestionService vQuestionService;
}
