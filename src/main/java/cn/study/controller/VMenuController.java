package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VMenuDto;
import cn.study.dto.VRoleMenuDto;
import cn.study.entity.VMenu;
import cn.study.service.VMenuService;
import cn.study.service.impl.StpInterfaceImpl;
import cn.study.vo.VMenuVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class VMenuController {

    @Resource
    VMenuService vMenuService;
    @Resource
    private StpInterfaceImpl stpInterfaceImpl;

    /**
     * 页面左侧导航栏查询+（权限查询，不同权限返回不同菜单）
     * roleIds 权限ids
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAllByRole/{roleIds}")
    public RES getAllByRole(@PathVariable(value = "roleIds") String roleIds) {
        System.out.println(roleIds);
        List<VMenuVo> all = vMenuService.getAllByRole(roleIds);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",all);
    }

    /**
     * 页面左侧导航栏查询（查询全部，三级）
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAll/{flag}")
    public RES getAll(@PathVariable(value = "flag") Integer flag) {
        List<VMenuVo> all = vMenuService.getAll(flag);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",all);
    }

    /**
     * 根据角色id获取用户绑定的权限，只返回第三级的menuId，不然tree会选中
     * @param roleId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getRoleMenuByRoleId/{roleId}")
    public RES getRoleMenuByRoleId(@PathVariable(value = "roleId") Integer roleId) {
        Integer[] ids = vMenuService.getRoleMenuByRoleId(roleId);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",ids);
    }

    @SaCheckLogin
    @SaCheckRole("admin")
    @PutMapping("/editRoleMenu")
    public RES editRoleMenu(@RequestBody VRoleMenuDto vRoleMenuDto) {
        Integer[] menuIds = vRoleMenuDto.getMenuIds();
        if (menuIds != null && menuIds.length > 0) {
            Integer flag = vMenuService.editRoleMenu(vRoleMenuDto);
            if (flag == 0) {
                return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
            }
        } else {
            return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
        }
        return RES.no(CommonConstants.FAIL, "操作失败");
    }

    /**
     * 目录查询 查询到第二级
     * @param
     * @return
     */
    @GetMapping("/getSecondLevel")
    public RES getSecondLevel() {
        List<VMenuVo> all = vMenuService.getSecondLevel();
        return RES.ok(CommonConstants.SUCCESS,"查询成功",all);
    }

    @SaCheckLogin
    @SaCheckRole("admin")
    @PostMapping("/addMenu")
    public RES addMenu(@RequestBody VMenuDto vMenuDto) {
        String flag  = vMenuService.addMenu(vMenuDto);
        if ("添加成功".equals(flag)){
            return RES.ok(CommonConstants.SUCCESS,flag,null);
        }
        return RES.no(CommonConstants.FAIL,flag);

    }

    @SaCheckLogin
    @SaCheckRole("admin")
    @PutMapping("/editMenu")
    public RES editMenu(@RequestBody VMenuDto vMenuDto) {
        String flag  = vMenuService.editMenu(vMenuDto);
        return RES.ok(CommonConstants.SUCCESS,flag,null);
    }

    /**
     * 获取当前信息
     * @param id
     * @return
     */
    @GetMapping("/getInfoById/{id}")
    public RES getInfoById(@PathVariable(value = "id") Integer id) {
        VMenu infoById = vMenuService.getInfoById(id);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",infoById);
    }

    @SaCheckLogin
    @SaCheckRole("admin")
    @DeleteMapping("/delMenu/{id}/{level}")
    public RES delMenu(@PathVariable(value = "id") Integer id,
                       @PathVariable(value = "level") Integer level) {

        String flag = vMenuService.delMenu(id,level);
        if ("删除成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }

    /**
     * 根据用户ID获取权限标识
     * @param userId
     * @return
     */
    @GetMapping("getPermissionList/{userId}")
    public RES getPermissionFlagByUserId(@PathVariable(value = "userId") Integer userId) {
        //获取用户角色\权限
        List<String> roleList = stpInterfaceImpl.getRoleList(userId, null);
        List<String> permissionList = stpInterfaceImpl.getPermissionList(userId, null);
        return RES.ok(CommonConstants.SUCCESS, "0", permissionList);
    }
}
