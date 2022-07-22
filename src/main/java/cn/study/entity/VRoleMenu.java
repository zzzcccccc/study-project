package cn.study.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName(value = "v_role_menu")
public class VRoleMenu implements Serializable {

    private Integer roleId;
    private Integer menuId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
