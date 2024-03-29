package cn.study.service;

import cn.study.dto.VMenuDto;
import cn.study.dto.VRoleMenuDto;
import cn.study.entity.VMenu;
import cn.study.entity.VUser;
import cn.study.vo.VMenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VMenuService extends IService<VMenu> {

    List<VMenuVo> getAll(Integer flag);

    String addMenu(VMenuDto vMenuDto);

    List<VMenuVo> getSecondLevel();

    VMenu getInfoById(Integer id);

    String editMenu(VMenuDto vMenuDto);

    String delMenu(Integer id, Integer level);

    Integer[] getRoleMenuByRoleId(Integer roleId);

    Integer editRoleMenu(VRoleMenuDto vRoleMenuDto);

    List<VMenuVo> getAllByRole(String roleIds);
}
