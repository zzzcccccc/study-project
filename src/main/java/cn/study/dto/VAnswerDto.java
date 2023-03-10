package cn.study.dto;

import cn.study.entity.VQuestion;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VAnswerDto extends VQuestion {

    @ApiModelProperty("id")
    private Long id;

    /**
     * 题目
     */
    @ApiModelProperty("题目")
    private String title;

    /**
     * 解析
     */
    @TableField("`analyze`")
    @ApiModelProperty("解析")
    private String analyze;

    /**
     * 正确答案 （多选题是数组[ a ， b ]）
     */
    @ApiModelProperty("正确答案 （多选题是数组[ a ， b ]）")
    private String answer;

    /**
     * 难度
     */
    @ApiModelProperty("难度")
    private Integer difficult;

    /**
     * 单多判题 选项 [{}，{}]
     */
    @ApiModelProperty("单多判题 选项 [{}，{}]")
    private String content;
    /**
     * 知识点
     */
    @ApiModelProperty("知识点")
    private String konwlegeIdList;

    /**
     * 问题类型1单选 2多选 3填空 4简答 5判断
     */
    @ApiModelProperty("问题类型1单选 2多选 3填空 4简答 5判断")
    private Integer quesTypeId;

    /**
     * 分数
     */
    @ApiModelProperty("分数")
    private Float score;

    /**
     * 科目id
     */
    @ApiModelProperty("学科id")
    private Integer subjectId;

    /**
     * 年级id
     */
    @ApiModelProperty("年级id")
    private Integer gradeId;

    /**
     * create_time
     */
    @ApiModelProperty("create_time")
    private String createTime;


    @ApiModelProperty("用户答案 （多选题是数组[ a ， b ]）")
    private String userAnswer;


}
