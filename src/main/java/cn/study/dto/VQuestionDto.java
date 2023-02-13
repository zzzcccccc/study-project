package cn.study.dto;

import cn.study.entity.VExamPaper;
import cn.study.entity.VQuestion;
import lombok.Data;

import java.util.List;

@Data
public class VQuestionDto extends VExamPaper {


    /**
     * 题目详情
     */
    private List<VQuestion> questions;
}
