package cn.study.service;

import cn.study.dto.VMenuDto;
import cn.study.entity.VMenu;
import cn.study.vo.VMenuVo;

import java.util.List;

public interface VMenuService {

    List<VMenuVo> getAll();

    String addMenu(VMenuDto vMenuDto);

    List<VMenuVo> getSecondLevel();

    VMenu getInfoById(Integer id);

    String editMenu(VMenuDto vMenuDto);

    String delMenu(Integer id, Integer parentId);
}
