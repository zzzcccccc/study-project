package cn.study.service.impl;

import cn.study.constant.CommonConstants;
import cn.study.dto.VMenuDto;
import cn.study.dto.VRoleMenuDto;
import cn.study.entity.VMenu;
import cn.study.entity.VRoleMenu;
import cn.study.mapper.VMenuMapper;
import cn.study.mapper.VRoleMenuMapper;
import cn.study.service.VMenuService;
import cn.study.vo.VMenuVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VMenuServiceImpl extends ServiceImpl<VMenuMapper, VMenu> implements VMenuService {

    @Resource
    private VRoleMenuMapper vRoleMenuMapper;

    /**
     *  左侧菜单栏+权限查询展示
     * @param roleIds
     * @return
     */
    @Override
    public List<VMenuVo> getAllByRole(String roleIds) {
        List<VMenuVo> menuVoList = new ArrayList<>();
        List<VMenuVo> menuOne = this.baseMapper.getMenuOne(roleIds); //一级
        for (VMenuVo menuVo: menuOne) {
            List<VMenuVo> vMenuVos  = this.baseMapper.getChildMenu( menuVo.getMenuId(), roleIds); //二级
            for (int j = 0; j < vMenuVos.size(); j++) {
                Integer parentId = vMenuVos.get(j).getMenuId();
                List<VMenuVo> childMenu = this.baseMapper.getChildMenu(parentId, roleIds);//三级
                vMenuVos.get(j).setChildren(childMenu);
            }
            menuVo.setChildren(vMenuVos);
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }


    /**
     *
     * @param flag 1菜单 2目录 0所有
     * @return
     */
    @Override
    public List<VMenuVo> getAll(Integer flag) {

        List<VMenuVo> menuVoList = new ArrayList<>();
        List<VMenu> vMenus = new ArrayList<>();
        if (flag!=0){
            vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                    .eq(VMenu::getShowFlag, flag)
                    .eq(VMenu::getLevel, 1)
                    .eq(VMenu::getDelFlag, CommonConstants.STATUS_NORMAL));
        }else{
           vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                    .eq(VMenu::getLevel, 1)
                    .eq(VMenu::getDelFlag, CommonConstants.STATUS_NORMAL));
        }
        for (int i = 0; i < vMenus.size(); i++) {
            VMenu vmenu = vMenus.get(i);
            VMenuVo vMenuVo = new VMenuVo();
            BeanUtils.copyProperties(vmenu,vMenuVo);
            Integer parentId = vmenu.getMenuId();
            List<VMenuVo> vMenusChildren = this.baseMapper.getList(parentId,flag);
            for (int j = 0; j < vMenusChildren.size(); j++) {
                VMenuVo vMenuVo1 = vMenusChildren.get(j);
                Integer parentId1 = vMenuVo1.getMenuId();
                List<VMenuVo> vMenusChildren1 = this.baseMapper.getList(parentId1,flag);
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
        if ( value==null || value.length==0 ){
            vMenu.setParentId(-1);
            vMenu.setLevel(1);
        }else{
            int parentId = value[value.length-1];
            VMenu vMenu1 = this.baseMapper.selectById(parentId);
            if (vMenu1.getLevel()==3){
                return "此登记不允许添加";
            }
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
            Integer parentId = vmenu.getMenuId();
            List<VMenuVo> vMenusChildren = this.baseMapper.getList(parentId,0);
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
        if (parentId==-1){
            vMenu.setLevel(1);
        }else{
            vMenu.setLevel(value.length+1);
        }
        vMenu.setParentId(parentId);
        this.baseMapper.updateById(vMenu);
        return "修改成功";
    }

    @Override
    public String delMenu(Integer id, Integer level) {
        VMenu vMenu = this.baseMapper.selectById(id);
        if (level==2){
            List<VMenu> vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                    .eq(VMenu::getParentId, id)
                    .eq(VMenu::getDelFlag, CommonConstants.SUCCESS));
            for (int i = 0; i <vMenus.size() ; i++) {
                VMenu menu = vMenus.get(i);
                menu.setDelFlag(CommonConstants.STATUS_DEL);
                this.baseMapper.updateById(menu);
            }
        }else if (level==1){
            List<VMenu> vMenus = this.baseMapper.selectList(Wrappers.<VMenu>lambdaQuery()
                    .eq(VMenu::getParentId, id)
                    .eq(VMenu::getDelFlag, CommonConstants.SUCCESS));
            for (int i = 0; i <vMenus.size() ; i++) {
                VMenu vMenuTwo = vMenus.get(i);

                Integer idTwo = vMenuTwo.getMenuId();
                VMenu vMenuThree = new VMenu();
                vMenuThree.setDelFlag(CommonConstants.STATUS_DEL);
                this.baseMapper.update(vMenuThree,
                        Wrappers.<VMenu>lambdaQuery().eq(VMenu::getParentId,idTwo));

                vMenuTwo.setDelFlag(CommonConstants.STATUS_DEL);
                this.baseMapper.updateById(vMenuTwo);
            }
        }
        vMenu.setDelFlag(CommonConstants.STATUS_DEL);
        this.baseMapper.updateById(vMenu);
        return "删除成功";
    }


    //---------------------------------------------角色和权限
    @Override
    public Integer[] getRoleMenuByRoleId(Integer roleId) {
        List<Integer>  menuIdsByRoleId = this.baseMapper.findMenuIdsByRoleId(roleId);
//        List<Integer> collect = vRoleMenuMapper.selectList(Wrappers.<VRoleMenu>lambdaQuery()
//                .eq(VRoleMenu::getRoleId, roleId))
//                .stream().map(VRoleMenu::getMenuId).collect(Collectors.toList());
        Integer[] array2 = menuIdsByRoleId.toArray(new Integer[menuIdsByRoleId.size()]);
        return array2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer editRoleMenu(VRoleMenuDto vRoleMenuDto) {
        Integer roleId = vRoleMenuDto.getRoleId();

        int delete = vRoleMenuMapper.delete(Wrappers.<VRoleMenu>lambdaQuery()
                .eq(VRoleMenu::getRoleId, roleId));
        if (delete>=0){
            Integer[] menuIds = vRoleMenuDto.getMenuIds();
            for (int i = 0; i < menuIds.length; i++) {
                VRoleMenu vRoleMenu = new VRoleMenu();
                vRoleMenu.setMenuId(menuIds[i]);
                vRoleMenu.setRoleId(roleId);
                vRoleMenuMapper.insert(vRoleMenu);
            }
        }
        return 0;
    }


}
