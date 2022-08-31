package cn.study.service;

import cn.study.dto.VUserRoleDto;
import cn.study.entity.VUserRole;

import java.util.List;

public interface VUserRoleService {

    Integer editUsrRole(VUserRoleDto vUserRoleDto);

    List<VUserRole> getUseRoleByUserId(Integer userId);

}
