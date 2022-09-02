package cn.study.service;

import cn.study.entity.VMenu;
import cn.study.entity.VRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VRoleService extends IService<VRole> {

    List<VRole> getAllRole();

    VRole getRoleById(Integer id);

    Integer editRole(VRole vRole);

    String delRole(Integer id);

    String addRole(VRole vRole);
}
