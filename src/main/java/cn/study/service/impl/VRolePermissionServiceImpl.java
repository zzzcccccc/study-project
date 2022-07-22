package cn.study.service.impl;

import cn.study.dto.VRolePermissionDto;
import cn.study.entity.VRolePermission;
import cn.study.mapper.VRolePermissionMapper;
import cn.study.service.VRolePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VRolePermissionServiceImpl extends ServiceImpl<VRolePermissionMapper, VRolePermission>  implements VRolePermissionService {

    @Override
    public Integer[] getUsePermissionByRoleId(Integer roleId) {
        List<Integer> collect = this.baseMapper.selectList(Wrappers.<VRolePermission>lambdaQuery()
                .eq(VRolePermission::getRoleId, roleId))
                .stream().map(VRolePermission::getPermissionId).collect(Collectors.toList());
        Integer[] array2 = collect.toArray(new Integer[collect.size()]);
        return array2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer editRolePermission(VRolePermissionDto vRolePermissionDto) {
        Integer roleId = vRolePermissionDto.getRoleId();
        int delete = this.baseMapper.delete(Wrappers.<VRolePermission>lambdaQuery()
                .eq(VRolePermission::getRoleId, roleId));
        if (delete>=0){
            Integer[] permissionIds = vRolePermissionDto.getPermissionIds();
            for (int i = 0; i < permissionIds.length; i++) {
                VRolePermission vRolePermission = new VRolePermission();
                vRolePermission.setPermissionId(permissionIds[i]);
                vRolePermission.setRoleId(roleId);
                this.baseMapper.insert(vRolePermission);
            }
        }
        return 0;
    }
}
