package cn.study.vo;

import cn.study.entity.VPaper;
import cn.study.entity.VQuestion;
import lombok.Data;

import java.util.List;

@Data
public class VPaperVo extends VPaper {

//    @ApiModelProperty("年级名称")
//    private String gradeName;
//
//    @ApiModelProperty("科目名称")
//    private String subjectName;

    /**
     * 题目详情
     */
    private List<VQuestionVo> questions;

    private String realName;


}
