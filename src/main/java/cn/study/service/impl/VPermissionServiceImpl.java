package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.entity.VPermission;
import cn.study.entity.VRole;
import cn.study.entity.VRolePermission;
import cn.study.entity.VUserRole;
import cn.study.mapper.VPermissionMapper;
import cn.study.mapper.VRolePermissionMapper;
import cn.study.service.VPermissionService;
import cn.study.vo.VPermissionVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VPermissionServiceImpl extends ServiceImpl<VPermissionMapper, VPermission>  implements VPermissionService {

    @Resource
    private VRolePermissionMapper vRolePermissionMapper;


    @Override
    public List<VPermissionVo> getAllPermission() {
        List<VPermissionVo> permissionVos = new ArrayList<>();
        List<VPermission> vPermissions = this.baseMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                .eq(VPermission::getLevel, 1)
                .eq(VPermission::getDelFlag, CommonConstants.STATUS_NORMAL));
        for (VPermission vPermission: vPermissions ) {
            VPermissionVo vPermissionVo = new VPermissionVo();
            BeanUtils.copyProperties(vPermission,vPermissionVo);
            Integer parentId = vPermission.getId();
            List<VPermission> vMenusChildren = this.baseMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                    .eq(VPermission::getParentId, parentId)
                    .eq(VPermission::getDelFlag, CommonConstants.STATUS_NORMAL));
            vPermissionVo.setChildren(vMenusChildren);
            permissionVos.add(vPermissionVo);
        }
        return permissionVos;
    }

    @Override
    public VPermission getPermissionById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public String addPermission(VPermission vPermission) {
        String permission = vPermission.getPermission();
        Integer flag = this.baseMapper.selectCount(Wrappers.<VPermission>lambdaQuery()
                .eq(VPermission::getPermission, permission)
                .eq(VPermission::getDelFlag,CommonConstants.SUCCESS));
        if (flag>0){
            return "此角色已存在";
        }
        Integer parentId = vPermission.getParentId();
        if (parentId == null || parentId == -1){
            vPermission.setParentId(-1);
            vPermission.setLevel(1);
        }else{
            vPermission.setLevel(2);
        }
        int insert = this.baseMapper.insert(vPermission);
        if (insert>=0){
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
    public List<VPermission> getPermissionOne() {
        List<VPermission> vPermissions = this.baseMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                .eq(VPermission::getLevel, 1)
                .eq(VPermission::getDelFlag, CommonConstants.STATUS_NORMAL));
        return vPermissions;
    }

    @Override
    public String editPermission(VPermission vPermission) {
        String permission = vPermission.getPermission();
        Integer flag = this.baseMapper.selectCount(Wrappers.<VPermission>lambdaQuery()
                .eq(VPermission::getPermission, permission)
                .ne(VPermission::getId, vPermission.getId())
                .eq(VPermission::getDelFlag,CommonConstants.SUCCESS));
        if (flag>0){
            return "此角色已存在";
        }
        Integer id = vPermission.getId();
        Integer parentId = vPermission.getParentId();
        if (parentId == null || parentId == -1){
            Integer count = this.baseMapper.selectCount(Wrappers.<VPermission>lambdaQuery()
                    .eq(VPermission::getParentId, id)
                    .eq(VPermission::getDelFlag, CommonConstants.STATUS_NORMAL));
            if (count>0){
                return "父类下有子类不可修改";
            }
            vPermission.setParentId(-1);
            vPermission.setLevel(1);
        }else{
            vPermission.setLevel(2);
        }
        int i = this.baseMapper.updateById(vPermission);
        if (i>=0){
            return "修改成功";
        }
        return "修改失败";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public String delPermission(Integer id,Integer parentId) {
        VPermission vPermission = this.baseMapper.selectById(id);
        String names = "";
        if (parentId==-1){
            List<VPermission> vPermissions = this.baseMapper.selectList(Wrappers.<VPermission>lambdaQuery()
                    .eq(VPermission::getParentId, id)
                    .eq(VPermission::getDelFlag, CommonConstants.STATUS_NORMAL));
            for (int i = 0; i <vPermissions.size() ; i++) {
                Integer personId = vPermissions.get(i).getId();
                String permissionName = vPermissions.get(i).getPermissionName();
                Integer count = vRolePermissionMapper.selectCount(Wrappers.<VRolePermission>lambdaQuery()
                        .eq(VRolePermission::getPermissionId, personId));
                if (count>0){
                    names += permissionName+",";
                }
            }
            names = names.substring(0,names.length()-1);
            return "<"+names+">权限已绑定角色,无法删除";
        }else{
            Integer count = vRolePermissionMapper.selectCount(Wrappers.<VRolePermission>lambdaQuery()
                    .eq(VRolePermission::getPermissionId, id));
            if (count>0){
                return "该权限已绑定角色,无法删除";
            }
        }
        vRolePermissionMapper.delete(Wrappers.<VRolePermission>lambdaQuery()
                .eq(VRolePermission::getPermissionId, id));
        vPermission.setDelFlag(CommonConstants.STATUS_DEL);
        int flag = this.baseMapper.updateById(vPermission);
        if(flag>0){
            return "删除成功";
        }
        return "删除失败";
    }
}
