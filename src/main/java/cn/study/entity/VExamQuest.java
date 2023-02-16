package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class VExamQuest implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Long id;

    private Long examId;

    private Long questId;
}
