package cn.study.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * @description v_question
 * @author zhangcc
 * @date 2023-02-09
 */
@Data
@ApiModel("v_question")
@TableName(value = "v_question")
public class VQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 题目
     */
    @ApiModelProperty("题目")
    private String titile;

    /**
     * 解析
     */
    @ApiModelProperty("解析")
    private String analyze;

    /**
     * 正确答案 （多选题是数组[ a ， b ]）
     */
    @ApiModelProperty("正确答案 （多选题是数组[ a ， b ]）")
    private String correct;

    /**
     * 难度
     */
    @ApiModelProperty("难度")
    private Integer difficult;

    /**
     * 单多判题 选项 [{}，{}]
     */
    @ApiModelProperty("单多判题 选项 [{}，{}]")
    private String items;

    /**
     * 知识点
     */
    @ApiModelProperty("知识点")
    private String konwlegeIdList;

    /**
     * 问题类型1单选 2多选 3判断 4填空 5简答
     */
    @ApiModelProperty("问题类型1单选 2多选 3判断 4填空 5简答")
    private String questionType;

    /**
     * 分数
     */
    @ApiModelProperty("分数")
    private Float score;

    /**
     * 科目id
     */
    @ApiModelProperty("科目id")
    private Integer subjectId;

    /**
     * 班级ids
     */
    @ApiModelProperty("班级ids")
    private Integer classId;

    /**
     * 试卷类型 1计时 2不计时
     */
    @ApiModelProperty("试卷类型 1计时 2不计时")
    private String paperType;

    /**
     * create_time
     */
    @ApiModelProperty("create_time")
    private LocalDateTime createTime;

    /**
     * update_time
     */
    @ApiModelProperty("update_time")
    private LocalDateTime updateTime;

    /**
     * del_flag
     */
    @ApiModelProperty("del_flag")
    private String delFlag;

}