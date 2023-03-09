package cn.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

@TableName(value = "v_user")
@Data
@ApiModel(value = "用户表")
public class VUser implements Serializable {

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Long id;

    @ApiModelProperty(value = "uuid")
    private String userUuid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 1.男 2女
     */
    @ApiModelProperty(value = "性别 1男 2女")
    private Integer sex;

    @ApiModelProperty(value = "生日")
    private Date birthDay;

    /**
     * 学生年级(1-12)
     */
    @ApiModelProperty(value = "学生年级(1-12)")
    private Integer userLevel;

    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 1.启用 2禁用
     */
    @ApiModelProperty(value = "状态 1.启用 2禁用")
    private Integer status;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String imagePath;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改时间")
    private String updateTime;

    @ApiModelProperty(value = "最近一次上线时间")
    private String lastActiveTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除 0否 1是")
    private String delFlag;

    /**
     * 微信openId
     */
    @ApiModelProperty(value = "微信openId")
    private String wxOpenId;
}
