package cn.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VItems  implements Serializable {


    @ApiModelProperty("题目")
    private String prefix;


    @TableField("`content`")
    @ApiModelProperty("解析")
    private String content;
}
