package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.dto.VMenuDto;
import cn.study.entity.VMenu;
import cn.study.mapper.VMenuMapper;
import cn.study.service.VMenuService;
import cn.study.vo.VMenuVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VMenuServiceImpl extends ServiceImpl<VMenuMapper, VMenu> implements VMenuService {

    @Override
    public List<VMenuVo> getAll() {
        List<VMenuVo> menuVoList = new ArrayList<>();
        List<VMenu> vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                .eq(VMenu::getLevel, 1)
                .eq(VMenu::getDelFlag, CommonConstants.STATUS_NORMAL));
        for (VMenu vmenu: vMenus ) {
            VMenuVo vMenuVo = new VMenuVo();
            BeanUtils.copyProperties(vmenu,vMenuVo);
            Integer parentId = vmenu.getId();
            List<VMenuVo> vMenusChildren = this.baseMapper.getList(parentId,null);
            for (int i = 0; i < vMenusChildren.size(); i++) {
                VMenuVo vMenuVo1 = vMenusChildren.get(i);
                Integer parentId1 = vMenuVo1.getId();
                List<VMenuVo> vMenusChildren1 = this.baseMapper.getList(parentId1,null);
                vMenuVo1.setChildren(vMenusChildren1);
            }
            vMenuVo.setChildren(vMenusChildren);
            menuVoList.add(vMenuVo);
        }
        return menuVoList;
    }

    @Override
    public String addMenu(VMenuDto vMenuDto) {
        VMenu vMenu = new VMenu();
        BeanUtils.copyProperties(vMenuDto,vMenu);
        Integer[] value = vMenuDto.getValue(); //分类ID
        if (value.length==0){
            vMenu.setParentId(-1);
            vMenu.setLevel(1);
        }else{
            int parentId = value[value.length-1];
            vMenu.setLevel(value.length+1);
            vMenu.setParentId(parentId);
        }
        this.baseMapper.insert(vMenu);
        return "添加成功";
    }

    @Override
    public List<VMenuVo> getSecondLevel() {
        List<VMenuVo> menuVoList = new ArrayList<>();
        List<VMenu> vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                .eq(VMenu::getLevel, 1)
                .eq(VMenu::getDelFlag, CommonConstants.STATUS_NORMAL));
        for (VMenu vmenu: vMenus ) {
            VMenuVo vMenuVo = new VMenuVo();
            BeanUtils.copyProperties(vmenu,vMenuVo);
            Integer parentId = vmenu.getId();
            List<VMenuVo> vMenusChildren = this.baseMapper.getList(parentId,null);
            vMenuVo.setChildren(vMenusChildren);
            menuVoList.add(vMenuVo);
        }
        return menuVoList;
    }

    @Override
    public VMenu getInfoById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public String editMenu(VMenuDto vMenuDto) {
        VMenu vMenu = new VMenu();
        BeanUtils.copyProperties(vMenuDto,vMenu);
        Integer[] value = vMenuDto.getValue(); //分类ID
        int parentId = value[value.length-1];
        vMenu.setParentId(parentId);
        vMenu.setLevel(value.length+1);
        this.baseMapper.updateById(vMenu);
        return "修改成功";
    }

    @Override
    public String delMenu(Integer id, Integer parentId) {
//        if (parentId==-1){
//            this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
//                    .eq(VMenu::getParentId,id));
//        }
//
//
//        vMenu.setDelFlag(CommonConstants.STATUS_DEL);
//        this.baseMapper.updateById(vMenu);
        return null;
    }
}
