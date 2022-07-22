package cn.study.service;

import cn.study.entity.VPermission;
import cn.study.entity.VRole;

import java.util.List;

public interface VRoleService {

    List<VRole> getAllRole();

    VRole getRoleById(Integer id);

    Integer editRole(VRole vRole);

    String delRole(Integer id);

    String addRole(VRole vRole);
}
