package cn.study.vo;

import cn.study.entity.VPermission;

import java.util.List;

public class VPermissionVo extends VPermission {

    private List<VPermission> children;

    public List<VPermission> getChildren() {
        return children;
    }

    public void setChildren(List<VPermission> children) {
        this.children = children;
    }
}
