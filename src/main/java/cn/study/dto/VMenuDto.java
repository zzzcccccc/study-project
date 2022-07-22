package cn.study.dto;

import cn.study.entity.VMenu;

public class VMenuDto extends VMenu {

    private Integer[] value;

    public Integer[] getValue() {
        return value;
    }

    public void setValue(Integer[] value) {
        this.value = value;
    }
}
