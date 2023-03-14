package cn.study.dto;

import cn.study.entity.VQuestion;
import cn.study.vo.VQuestionVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * getPageToAnswerInfo
 * 获取试卷答卷名单
 * 获取试卷答卷详情
 *
 */
@Data
public class VAnswerInfoDto {

    @ApiModelProperty("试卷id")
    private Long paperId;

    @ApiModelProperty("老师id/用户id")
    private Long userId;

    @ApiModelProperty("答卷状态 0未完成 1完成")
    private String answerState;

    @ApiModelProperty("批改状态 0未完成 1完成")
    private String correctState;

    @ApiModelProperty("总分")
    private Integer grade;

    @ApiModelProperty("批改的分数")
    private List<VQuestionVo> scoreList;

}
