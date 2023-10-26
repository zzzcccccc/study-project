package cn.study.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VUserDto;
import cn.study.entity.VUser;
import cn.study.entity.VUserRole;
import cn.study.entity.VUserSubjectClass;
import cn.study.mapper.UserMapper;
import cn.study.mapper.VUserRoleMapper;
import cn.study.mapper.VUserSubjectClassMapper;
import cn.study.service.UserService;
import cn.study.utils.SHA256;
import cn.study.vo.VUserVo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, VUser> implements UserService {

    @Resource
    private StpInterfaceImpl stpInterfaceImpl;
    @Resource
    private VUserRoleMapper vUserRoleMapper;
    @Resource
    private VUserSubjectClassMapper vUserSubjectClassMapper;

    @Override
    public Integer doLogin(String userName, String password) {
        try {
            String encrypt = SHA256.encrypt(password);
            VUser vUser = this.baseMapper.selectOne(Wrappers.<VUser>lambdaQuery()
                    .eq(VUser::getUserName, userName)
                    .eq(VUser::getDelFlag, CommonConstants.STATUS_NORMAL)
            );
            if (vUser != null) {
                String userName1 = vUser.getUserName();
                String password1 = vUser.getPassword();
                if (userName1.equals(userName) && password1.equals(encrypt)) {
                    Long id = vUser.getId();
                    boolean login = StpUtil.isLogin();
                    if (login) {
                        System.out.println("1111");
                        String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
                        StpUtil.kickout(id);                    // 将指定账号踢下线
                        StpUtil.kickoutByTokenValue(tokenValueByLoginId);      // 将指定 Token 踢下线
                    }
                    StpUtil.login(id);
                    //获取用户角色\权限
                    List<String> roleList = stpInterfaceImpl.getRoleList(id, null);
                    List<String> permissionList = stpInterfaceImpl.getPermissionList(id, null);
                    return 0;
                } else {
                    return 1;
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 2;
    }

    @Override
    public IPage getUserPage(Page page, VUserDto vUserDto) {
        IPage iPage = this.baseMapper.getPage(page, vUserDto);
        List<VUserVo> records = iPage.getRecords();
        for (VUserVo vo : records) {
            if (vo.getClassIds() != null) {
                String classIds = vo.getClassIds().toString();
                vo.setClassIds(JSON.parse(classIds));
            } else {
                Integer[] arr = new Integer[]{};
                vo.setClassIds(arr);
            }
        }
        return iPage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer addUser(VUserDto vUserDto){
        Integer[] roleIds = vUserDto.getRoleIds();
        try {
            String password = SHA256.encrypt(vUserDto.getPassword());
            vUserDto.setPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        VUser vUser = new VUser();
        BeanUtils.copyProperties(vUserDto, vUser);
        int insert = this.baseMapper.insert(vUser);
        if (insert > 0) {
            Long userId = vUser.getId();
            for (int i = 0; i < roleIds.length; i++) {
                VUserRole vUserRole = new VUserRole();
                vUserRole.setUserId(userId);
                vUserRole.setRoleId(roleIds[i]);
                vUserRoleMapper.insert(vUserRole);
            }

            VUserSubjectClass vUserSubjectClass = new VUserSubjectClass();
            vUserSubjectClass.setUserId(userId);
            vUserSubjectClass.setSubjectId(vUserDto.getSubjectId());
            vUserSubjectClass.setGradeId(vUserDto.getGradeId());
            vUserSubjectClass.setClassIds(Arrays.toString(vUserDto.getClassIds()));
            vUserSubjectClassMapper.insert(vUserSubjectClass);
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
    public VUserVo getInfoById(Integer userId) {
        VUserVo vUserVo = this.baseMapper.getInfoById(userId);
        return vUserVo;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer editUser(VUserDto vUserDto) {
        Long userId = vUserDto.getId();
        int delete = vUserRoleMapper.delete(Wrappers.<VUserRole>lambdaQuery()
                .eq(VUserRole::getUserId, userId));
        if (delete >= 0) {
            Integer[] roleIds = vUserDto.getRoleIds();
            for (int i = 0; i < roleIds.length; i++) {
                VUserRole vUserRole = new VUserRole();
                vUserRole.setUserId(userId);
                vUserRole.setRoleId(roleIds[i]);
                vUserRoleMapper.insert(vUserRole);
            }
        }
        int flag = vUserSubjectClassMapper.delete(Wrappers.<VUserSubjectClass>lambdaQuery()
                .eq(VUserSubjectClass::getUserId, userId));
        if (flag >= 0) {
            VUserSubjectClass vUserSubjectClass = new VUserSubjectClass();
            vUserSubjectClass.setUserId(userId);
            vUserSubjectClass.setSubjectId(vUserDto.getSubjectId());
            vUserSubjectClass.setGradeId(vUserDto.getGradeId());
            vUserSubjectClass.setClassIds(Arrays.toString(vUserDto.getClassIds()));
            vUserSubjectClassMapper.insert(vUserSubjectClass);
        }
        VUser vUser = new VUser();
        BeanUtils.copyProperties(vUserDto, vUser);

        return this.baseMapper.updateById(vUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer del(Integer userId) {
        VUser vUser = this.baseMapper.selectById(userId);
        vUser.setDelFlag(CommonConstants.STATUS_DEL);
        int i = this.baseMapper.updateById(vUser);
        if (i > 0) {
            vUserSubjectClassMapper.delete(Wrappers.<VUserSubjectClass>lambdaQuery()
                    .eq(VUserSubjectClass::getUserId, userId));

            int delete = vUserRoleMapper.delete(Wrappers.<VUserRole>lambdaQuery()
                    .eq(VUserRole::getUserId, userId));
            if (delete >= 0) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public VUser getInfoByUsername(String username) {
        return this.baseMapper.selectOne(Wrappers.<VUser>lambdaQuery()
                .eq(VUser::getUserName, username)
                .eq(VUser::getDelFlag, CommonConstants.SUCCESS));
    }

    @Override
    public Integer editPassword(String password, Integer userId) {
        VUser vUser = this.baseMapper.selectById(userId);
        try {
            password = SHA256.encrypt(password);
            vUser.setPassword(password);
            return this.baseMapper.updateById(vUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
