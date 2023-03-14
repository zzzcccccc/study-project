package cn.study.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 答卷dto -> 题目详情
 */
@Data
public class VAnswerQuesDto implements Serializable {

    @ApiModelProperty("id")
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
    private Float score;

    @ApiModelProperty("学科id")
    private Integer subjectId;


    @ApiModelProperty("年级id")
    private Integer gradeId;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("用户答案 （多选题是数组[ a ， b ]）")
    private String userAnswer;

}
