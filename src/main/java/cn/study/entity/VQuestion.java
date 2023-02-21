package cn.study.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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

    /**
     * update_time
     */
    @ApiModelProperty("update_time")
    private String updateTime;

    /**
     * del_flag
     */
    @ApiModelProperty("del_flag")
    private String delFlag;

}