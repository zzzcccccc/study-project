package cn.study.vo;

import cn.study.entity.VPaper;
import cn.study.entity.VQuestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  我的试卷，管理员试卷列表
 */
@Data
public class VPaperVo extends VPaper {

    @ApiModelProperty("答卷状态 0未完成 1完成")
    private String answerState;

    @ApiModelProperty("批改状态 0未完成 1完成")
    private String correctState;

    @ApiModelProperty("试卷总分")
    private String grade;

    //----我的试卷
    @ApiModelProperty("题目详情")
    private List<VQuestionVo> questions;

    @ApiModelProperty("老师真实姓名")
    private String realName;

    @ApiModelProperty("答卷详情id")
    private Long answerId;


}
