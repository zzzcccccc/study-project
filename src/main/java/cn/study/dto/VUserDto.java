package cn.study.dto;

import cn.study.entity.VUser;


public class VUserDto extends VUser {

    // 1学生 2老师 3管理员 4老师、管理员
    private Integer roleId;

    // 学科
    private Integer subjectId;

    //年级 id
    private Integer gradeId;

    // 班级ids
    private Integer[] classIds;

    private String orderBy;

    private Integer[] roleIds;

    private Integer classId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer[] getClassIds() {
        return classIds;
    }

    public void setClassIds(Integer[] classIds) {
        this.classIds = classIds;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
