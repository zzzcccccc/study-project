package cn.study.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.study.constant.CommonConstants;
import cn.study.dto.VUserDto;
import cn.study.entity.VRole;
import cn.study.entity.VUser;
import cn.study.entity.VUserRole;
import cn.study.mapper.UserMapper;
import cn.study.mapper.VRoleMapper;
import cn.study.mapper.VUserRoleMapper;
import cn.study.service.UserService;
import cn.study.vo.VUserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, VUser> implements UserService {

    @Resource
    private StpInterfaceImpl stpInterfaceImpl;
    @Resource
    private VUserRoleMapper vUserRoleMapper;

    @Override
    public Integer doLogin(String userName, String password) {
        VUser vUser = this.baseMapper.selectOne(Wrappers.<VUser>lambdaQuery()
                .eq(VUser::getUserName, userName)
                .eq(VUser::getDelFlag, CommonConstants.STATUS_NORMAL)
        );
        if (vUser!=null){
            String userName1 = vUser.getUserName();
            String password1 = vUser.getPassword();
            if(userName1.equals(userName) && password1.equals(password)) {
                Integer id = vUser.getId();
                StpUtil.login(id);
                List<String> roleList = stpInterfaceImpl.getRoleList(id, null);
                //获取用户权限、角色
                List<String> permissionList = stpInterfaceImpl.getPermissionList(id, null);
                return 0;
            }else{
                return 1;
            }

        }
        return 2;
    }

    @Override
    public IPage getUserPage(Page page, VUserDto vUserDto) {
        IPage iPage = this.baseMapper.getPage(page, vUserDto);
        return iPage;
    }

    @Override
    public Integer addUser(VUserDto vUserDto) {
        Integer roleId = vUserDto.getRoleId();
        VUser vUser = new VUser();
        BeanUtils.copyProperties(vUserDto,vUser);
        int insert = this.baseMapper.insert(vUser);
        if (insert>0) {
            Integer userId = vUser.getId();
            VUserRole vUserRole = new VUserRole();
            vUserRole.setUserId(userId);
            vUserRole.setRoleId(roleId);
            vUserRoleMapper.insert(vUserRole);
        }
        return insert;
    }

    @Override
    public VUser getOneByUserName(String userName) {
        VUser vUser = this.baseMapper.selectOne(Wrappers.<VUser>lambdaQuery()
                .eq(VUser::getUserName, userName)
                .eq(VUser::getDelFlag, CommonConstants.SUCCESS));
        return vUser;
    }

    @Override
    public VUser getInfoById(Integer userId) {
        VUser vUser = this.baseMapper.selectById(userId);
        return vUser;
    }

    @Override
    public Integer editUser(VUser vUser) {
        return this.baseMapper.updateById(vUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer del(Integer userId) {
        VUser vUser = this.baseMapper.selectById(userId);
        vUser.setDelFlag(CommonConstants.STATUS_DEL);
        int i = this.baseMapper.updateById(vUser);
        if (i>0){
            int delete = vUserRoleMapper.delete(Wrappers.<VUserRole>lambdaQuery()
                    .eq(VUserRole::getUserId, userId));
            if (delete>=0){
                return 0;
            }
        }
        return 1;
    }


}
