package cn.study.dto;

import cn.study.entity.VPaper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 答卷 dto
 */
@Data
public class VAnswerElsDto implements Serializable {


    @ApiModelProperty("答卷id")
    private Long answerId;

    @ApiModelProperty("试卷id")
    private Long id;

    @ApiModelProperty("试卷题目")
    private String headline;

    @ApiModelProperty("截止时间")
    private String deadline;

    @ApiModelProperty("创建者用户id")
    private Long userId;

    @ApiModelProperty("创建者用户name")
    private String realName;

    @ApiModelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty("年级id")
    private Integer gradeId;

    @ApiModelProperty("班级ids")
    private String classIdArray;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("题目详情list")
    List<VAnswerQuesDto> questions;


}
