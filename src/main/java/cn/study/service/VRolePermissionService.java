package cn.study.service;


import cn.study.dto.VRolePermissionDto;

public interface VRolePermissionService {


    Integer[] getUsePermissionByRoleId(Integer roleId);

    /**
     * 修改角色下的权限
     * @param vRolePermissionDto
     * @return
     */
    Integer editRolePermission(VRolePermissionDto vRolePermissionDto);
}
