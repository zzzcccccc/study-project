package cn.study.service.impl;

import cn.study.dto.VUserRoleDto;
import cn.study.entity.VUserRole;
import cn.study.mapper.VUserRoleMapper;
import cn.study.service.VUserRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VUserRoleServiceImpl extends ServiceImpl<VUserRoleMapper, VUserRole>  implements VUserRoleService {

    @Override
    public List<VUserRole> getUseRoleByUserId(Integer userId) {
        List<VUserRole> vUserRoles = this.baseMapper.selectList(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getUserId, userId));
        return vUserRoles;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer editUsrRole(VUserRoleDto vUserRoleDto) {
        Integer userId = vUserRoleDto.getUserId();
        int delete = this.baseMapper.delete(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getUserId, userId));
        if (delete>=0){
            Integer[] roleIds = vUserRoleDto.getRoleIds();
            for (int i = 0; i < roleIds.length; i++) {
                VUserRole vUserRole = new VUserRole();
                vUserRole.setUserId(userId);
                vUserRole.setRoleId(roleIds[i]);
                this.baseMapper.insert(vUserRole);
            }
        }
        return 0;
    }
}
