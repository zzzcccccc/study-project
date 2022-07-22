package cn.study.dto;

import java.io.Serializable;

public class VUserRoleDto implements Serializable {

    private Integer userId;
    private Integer[] roleIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

}
