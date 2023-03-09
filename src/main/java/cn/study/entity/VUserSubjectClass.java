package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "v_user_subject_class")
public class VUserSubjectClass implements Serializable {

    private Long userId;
    // 学科
    private Integer subjectId;

    //年级 id
    private Integer gradeId;

    // 班级ids
    private String classIds;


}
