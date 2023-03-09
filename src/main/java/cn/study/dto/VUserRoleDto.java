package cn.study.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VUserRoleDto implements Serializable {

    private Long userId;
    private Integer[] roleIds;



}
