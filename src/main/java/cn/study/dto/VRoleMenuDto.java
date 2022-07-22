package cn.study.dto;

import java.io.Serializable;

public class VRoleMenuDto implements Serializable {

    private Integer roleId;
    private Integer[] menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }
}
