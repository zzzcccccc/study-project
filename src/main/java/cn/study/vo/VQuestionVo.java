package cn.study.vo;

import cn.study.entity.VQuestion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class VQuestionVo extends VQuestion{

    @ApiModelProperty("年级名称")
    private String gradeName;

    @ApiModelProperty("科目名称")
    private String subjectName;

}
