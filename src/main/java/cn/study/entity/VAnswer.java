package cn.study.entity;

import cn.study.dto.VAnswerQuesDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * @description v_answer
 * @author zhangcc
 * @date 2023-03-08
 * 答卷
 */
@Data
public class VAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * user_id
     */
    private Long userId;
    private String realName;

    /**
     * class_id
     */
    private Integer classId;

    private Long paperId;

    private Long teacherId;

    @ApiModelProperty("作答详情")
    private String answerArray;

    private String answerState;

    private String correctState;

    private Integer grade;

    private String createTime;


    private String updateTime;

    private String delFlag;

}