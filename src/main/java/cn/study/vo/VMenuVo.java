package cn.study.vo;

import cn.study.entity.VMenu;

import java.util.List;

public class VMenuVo extends VMenu {


    private List<VMenuVo> children;


    public List<VMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<VMenuVo> children) {
        this.children = children;
    }
}
