package cn.study.entity;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
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
    /**
     * id
     */
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
    /**
     * teacher_id
     */
    private Long paperId;

    /**
     * teacher_id
     */
    private Long teacherId;

    /**
     * answer_array
     */
    private String answerArray;

    private String answerState;

    private String correctState;

    private String grade;


    private String createTime;


    private String updateTime;

    private String delFlag;

}