package cn.study.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VAnwserVo {

    @ApiModelProperty("答卷状态 0未完成 1完成")
    private String answerState;

    @ApiModelProperty("批改状态 0未完成 1完成")
    private String correctState;

    @ApiModelProperty("答题id")
    private Long answerId;

    @ApiModelProperty("分数")
    private Float grade;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户真实姓名")
    private String realName;

    @ApiModelProperty("班级")
    private String className;



}
