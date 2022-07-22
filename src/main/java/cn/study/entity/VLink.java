package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "v_link")
public class VLink implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String title;
    private String link;
    private String date;
    private String author;
    private String tag;

}
