package cn.study.vo;

import cn.study.entity.VUser;
import lombok.Data;


@Data
public class VUserVo extends VUser {

    private String subjectName;

    private Integer subjectId;

    private String gradeName;

    private Integer gradeId;

    private Object classIds;

    private Object roleIds;
}
