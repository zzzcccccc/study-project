package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.entity.VRole;
import cn.study.entity.VRolePermission;
import cn.study.entity.VUserRole;
import cn.study.mapper.VRoleMapper;
import cn.study.mapper.VRolePermissionMapper;
import cn.study.mapper.VUserRoleMapper;
import cn.study.service.VRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VRoleServiceImpl extends ServiceImpl<VRoleMapper, VRole>  implements VRoleService {

    @Resource
    private VUserRoleMapper vUserRoleMapper;
    @Resource
    private VRolePermissionMapper vRolePermissionMapper;

    @Override
    public List<VRole> getAllRole() {
        return this.baseMapper.selectList(Wrappers.<VRole>lambdaQuery()
                .eq(VRole::getDelFlag, CommonConstants.SUCCESS));
    }

    @Override
    public VRole getRoleById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public Integer editRole(VRole vRole) {
        return this.baseMapper.updateById(vRole);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public String delRole(Integer id) {
        Integer count = vUserRoleMapper.selectCount(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getRoleId, id));
        if (count>0){
            return "该角色已绑定用户,无法删除";
        }
        vRolePermissionMapper.delete(Wrappers.<VRolePermission>lambdaQuery()
                .eq(VRolePermission::getRoleId, id));
        VRole vRole = this.baseMapper.selectById(id);
        vRole.setDelFlag(CommonConstants.STATUS_DEL);
        int flag = this.baseMapper.updateById(vRole);
        if(flag>0){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public String addRole(VRole vRole) {
        String role = vRole.getRole();
        Integer flag = this.baseMapper.selectCount(Wrappers.<VRole>lambdaQuery()
                .eq(VRole::getRole, role)
                .eq(VRole::getDelFlag,CommonConstants.SUCCESS));
        if (flag>0){
            return "此角色已存在";
        }
        int insert = this.baseMapper.insert(vRole);
        if (insert>=0){
            return "添加成功";
        }
        return "添加失败";
    }

}
