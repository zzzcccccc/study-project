package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "v_user_role")
@Data
public class VUserRole implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Long id;
    private Long userId;
    private Integer roleId;

}
