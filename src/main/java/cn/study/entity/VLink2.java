package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "v_link2")
@ApiModel(value = "测试")
public class VLink2 implements Serializable {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.INPUT)//指定自增策略
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "link")
    private String link;
    private String date;
    private String author;
    private String tag;

}
