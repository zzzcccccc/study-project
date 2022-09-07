package cn.study.dto;

import cn.study.entity.VClass;

public class VClassDto extends VClass {

    private Integer[] chooseGradeIds;

    public Integer[] getChooseGradeIds() {
        return chooseGradeIds;
    }

    public void setChooseGradeIds(Integer[] chooseGradeIds) {
        this.chooseGradeIds = chooseGradeIds;
    }
}
