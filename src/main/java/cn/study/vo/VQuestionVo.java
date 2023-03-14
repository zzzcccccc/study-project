package cn.study.vo;

import cn.study.entity.VQuestion;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class VQuestionVo {

    @ApiModelProperty("年级名称")
    private String gradeName;

    @ApiModelProperty("科目名称")
    private String subjectName;

    @ApiModelProperty("题目id")
    private Long id;

    @ApiModelProperty("题目")
    private String title;

    @TableField("`analyze`")
    @ApiModelProperty("解析")
    private String analyze;

    @ApiModelProperty("正确答案 （多选题是数组[ a ， b ]）")
    private String answer;

    @ApiModelProperty("难度")
    private Integer difficult;

    @ApiModelProperty("单多判题 选项 [{}，{}]")
    private String content;

    @ApiModelProperty("问题类型1单选 2多选 3填空 4简答 5判断")
    private Integer quesTypeId;

    @ApiModelProperty("分数")
    private Integer score;

    @ApiModelProperty("学科id")
    private Integer subjectId;

    @ApiModelProperty("年级id")
    private Integer gradeId;

    @ApiModelProperty("create_time")
    private String createTime;

    @ApiModelProperty("用户答案")
    private String userAnswer;

    @ApiModelProperty("批改分数")
    private Integer userScore;

    @ApiModelProperty("题型名称")
    private String quesTypeName;

}
