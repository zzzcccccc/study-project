package cn.study.vo;

import cn.study.entity.VUser;


public class VUserVo extends VUser {

    private String subjectName;

    private Integer subjectId;

    private String gradeName;

    private Integer gradeId;

    private String classIds;

    private Object classIdArray;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public Object getClassIdArray() {
        return classIdArray;
    }

    public void setClassIdArray(Object classIdArray) {
        this.classIdArray = classIdArray;
    }
}
