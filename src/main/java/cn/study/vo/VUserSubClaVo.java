package cn.study.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class VUserSubClaVo implements Serializable {

    private Long userId;

    private String realName;

    private String classIds;

}
