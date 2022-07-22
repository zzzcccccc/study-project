package cn.study.dto;

import cn.study.entity.VUser;


public class VUserDto extends VUser {

    // 1学生 2老师 3管理员 4老师、管理员
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
