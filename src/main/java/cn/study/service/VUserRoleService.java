package cn.study.service;

import cn.study.dto.VUserRoleDto;
import cn.study.entity.VMenu;
import cn.study.entity.VUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VUserRoleService extends IService<VUserRole> {

    Integer editUsrRole(VUserRoleDto vUserRoleDto);

    List<VUserRole> getUseRoleByUserId(Integer userId);

}
