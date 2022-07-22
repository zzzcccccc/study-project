package cn.study.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.study.entity.VPermission;
import cn.study.entity.VRole;
import cn.study.entity.VRolePermission;
import cn.study.entity.VUserRole;
import cn.study.mapper.VPermissionMapper;
import cn.study.mapper.VRoleMapper;
import cn.study.mapper.VRolePermissionMapper;
import cn.study.mapper.VUserRoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StpInterfaceImpl implements StpInterface {

    @Resource
    VUserRoleMapper vUserRoleMapper;
    @Resource
    VRoleMapper vRoleMapper;
    @Resource
    VRolePermissionMapper vRolePermissionMapper;
    @Resource
    VPermissionMapper vPermissionMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<VUserRole> vUserRoles = vUserRoleMapper.selectList(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getUserId, loginId));
        List<Integer> collect = new ArrayList<>();
        for (int i = 0; i < vUserRoles.size(); i++) {
            Integer roleId = vUserRoles.get(i).getRoleId();
            collect = vRolePermissionMapper.selectList(Wrappers.<VRolePermission>lambdaQuery()
                    .select(VRolePermission::getPermissionId)
                    .eq(VRolePermission::getRoleId, roleId))
                    .stream().map(VRolePermission::getPermissionId).collect(Collectors.toList());
        }
        List<String> all = vPermissionMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                .in(VPermission::getId, collect))
                .stream().map(VPermission::getPermission).collect(Collectors.toList());
        return all;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<Integer> collect = vUserRoleMapper.selectList(Wrappers.<VUserRole>lambdaQuery()
                .select(VUserRole::getRoleId)
                .eq(VUserRole::getUserId, loginId))
                .stream().map(VUserRole::getRoleId).collect(Collectors.toList());
        List<String> vRoles = vPermissionMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                .in(VPermission::getId, collect))
                .stream().map(VPermission::getPermission).collect(Collectors.toList());
        return vRoles;
    }
}
