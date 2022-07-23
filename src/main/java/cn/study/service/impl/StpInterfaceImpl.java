package cn.study.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.study.entity.VMenu;
import cn.study.entity.VRole;
import cn.study.entity.VRoleMenu;
import cn.study.entity.VUserRole;
import cn.study.mapper.*;
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
    VRoleMenuMapper vRoleMenuMapper;
    @Resource
    VMenuMapper vMenuMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<VUserRole> vUserRoles = vUserRoleMapper.selectList(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getUserId, loginId));
        List<Integer> collect = new ArrayList<>();
        for (int i = 0; i < vUserRoles.size(); i++) {
            Integer roleId = vUserRoles.get(i).getRoleId();
            collect = vRoleMenuMapper.selectList(Wrappers.<VRoleMenu>lambdaQuery()
                    .select(VRoleMenu::getMenuId)
                    .eq(VRoleMenu::getRoleId, roleId))
                    .stream().map(VRoleMenu::getMenuId).collect(Collectors.toList());
        }
        List<String> all = vMenuMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                .in(VMenu::getMenuId, collect))
                .stream().map(VMenu::getPermission).collect(Collectors.toList());
        return all;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<Integer> collect = vUserRoleMapper.selectList(Wrappers.<VUserRole>lambdaQuery()
                .select(VUserRole::getRoleId)
                .eq(VUserRole::getUserId, loginId))
                .stream().map(VUserRole::getRoleId).collect(Collectors.toList());
        List<String> vRoles = vRoleMapper.selectList(Wrappers.<VRole>lambdaQuery()
                .in(VRole::getId, collect))
                .stream().map(VRole::getRole).collect(Collectors.toList());
        return vRoles;
    }
}
