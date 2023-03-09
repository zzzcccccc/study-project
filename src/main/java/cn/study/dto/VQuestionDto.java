package cn.study.dto;

import cn.study.entity.VPaper;
import cn.study.entity.VQuestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class VQuestionDto extends VPaper {

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


    /**
     * 学科name
     */
    @ApiModelProperty("学科name")
    private String subjectName;

    @ApiModelProperty("quesIds")
    private String quesIds;

    @ApiModelProperty("userId")
    private Long userId;



}
