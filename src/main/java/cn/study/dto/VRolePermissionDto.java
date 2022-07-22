package cn.study.dto;

import java.io.Serializable;

public class VRolePermissionDto implements Serializable {

    private Integer roleId;
    private Integer[] permissionIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Integer[] permissionIds) {
        this.permissionIds = permissionIds;
    }
}
