package cn.study.entity;

import cn.hutool.json.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * @description v_exam_paper
 * @author zhangcc
 * @date 2023-02-13
 */
@Data
@ApiModel("v_exam_paper")
public class VExamPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    /**
     * 试卷题目
     */
    @ApiModelProperty("试卷题目")
    private String headline;

    /**
     * 试卷类型 1计时 2不计时
     */
    @ApiModelProperty("试卷类型 1计时 2不计时")
    private String paperType;

    /**
     * 截止时间
     */
    @ApiModelProperty("截止时间")
    private String deadline;

    /**
     * 学科id
     */
    @ApiModelProperty("学科id")
    private Integer subjectId;

    /**
     * 年级id
     */
    @ApiModelProperty("年级id")
    private Integer gradeId;

    /**
     * 班级ids
     */
    @ApiModelProperty("班级ids")
    private String classIdArray;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private Float totalPoints;


    @ApiModelProperty("create_time")
    private String createTime;


    @ApiModelProperty("update_time")
    private String updateTime;


    @ApiModelProperty("del_flag")
    private String delFlag;

}