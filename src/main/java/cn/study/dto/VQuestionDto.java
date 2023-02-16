package cn.study.dto;

import cn.hutool.json.JSONArray;
import cn.study.entity.VExamPaper;
import cn.study.entity.VQuestion;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class VQuestionDto extends VExamPaper {

    /**
     * 是否生成试卷 1是 2否
     */
    private String check;


    @ApiModelProperty("班级ids")
    private Integer[] classIds;



    /**
     * 题目详情
     */
    private List<VQuestion> questions;
}
