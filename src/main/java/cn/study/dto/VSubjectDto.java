package cn.study.dto;

import cn.study.entity.VSubject;

public class VSubjectDto extends VSubject {

    private Integer[] chooseGradeIds;

    public Integer[] getChooseGradeIds() {
        return chooseGradeIds;
    }

    public void setChooseGradeIds(Integer[] chooseGradeIds) {
        this.chooseGradeIds = chooseGradeIds;
    }
}
