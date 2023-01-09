package cn.study.dto;

import cn.study.entity.VUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class VUserDto extends VUser {

    // 1学生 2老师 3管理员 4老师、管理员
    @ApiModelProperty(value = "角色 1学生 2老师 3管理员 4老师、管理员")
    private Integer roleId;

    // 学科
    @ApiModelProperty(value = "学科id")
    private Integer subjectId;

    //年级 id
    @ApiModelProperty(value = "年级id")
    private Integer gradeId;

    // 班级ids
    @ApiModelProperty(value = "班级ids")
    private Integer[] classIds;

    @ApiModelProperty(value = "角色ids")
    private Integer[] roleIds;

    @ApiModelProperty(value = "班级id")
    private Integer classId;

    private String orderBy;

}
