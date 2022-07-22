package cn.study.controller;

import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VRolePermissionDto;
import cn.study.dto.VUserRoleDto;
import cn.study.entity.VPermission;
import cn.study.entity.VRole;
import cn.study.entity.VUserRole;
import cn.study.service.VPermissionService;
import cn.study.service.VRolePermissionService;
import cn.study.service.VRoleService;
import cn.study.service.VUserRoleService;
import cn.study.vo.VPermissionVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色和权限管理
 */
@RestController
@RequestMapping("/system")
public class VRoleController {

    @Resource
    VRoleService vRoleService;
    @Resource
    VUserRoleService vUserRoleService;

    @GetMapping("/getAllRole")
    public RES getAllRole() {
        List<VRole> allRole = vRoleService.getAllRole();
        return RES.ok(CommonConstants.SUCCESS, "查询成功", allRole);
    }

    @GetMapping("/getRoleById/{id}")
    public RES getRoleById(@PathVariable(value = "id") Integer id) {
        VRole vRole = vRoleService.getRoleById(id);
        return RES.ok(CommonConstants.SUCCESS, "查询成功", vRole);
    }

    @PutMapping("/editRole")
    public RES editRole(@RequestBody VRole vRole) {
        Integer flag = vRoleService.editRole(vRole);
        if (flag > 0) {
            return RES.ok(CommonConstants.SUCCESS, "修改成功", null);
        }
        return RES.no(CommonConstants.FAIL, "修改失败");
    }

    @GetMapping("/getUseRoleByUserId/{userId}")
    public RES getUseRoleByUserId(@PathVariable(value = "userId") Integer userId) {
        List<Integer> collect = vUserRoleService.getUseRoleByUserId(userId)
                .stream().map(VUserRole::getRoleId).collect(Collectors.toList());
        //使用toArray(T[] a)方法
        Integer[] array2 = collect.toArray(new Integer[collect.size()]);

        return RES.ok(CommonConstants.SUCCESS, "查询成功", array2);
    }

    @PutMapping("/editUsrRole")
    public RES editUsrRole(@RequestBody VUserRoleDto vUserRoleDto) {
        Integer[] roleIds = vUserRoleDto.getRoleIds();
        if (roleIds != null && roleIds.length > 0) {
            Integer flag = vUserRoleService.editUsrRole(vUserRoleDto);
            if (flag == 0) {
                return RES.ok(CommonConstants.SUCCESS, "修改成功", null);
            }
        } else {
            return RES.ok(CommonConstants.SUCCESS, "修改成功", null);
        }
        return RES.no(CommonConstants.FAIL, "修改失败");
    }

    @DeleteMapping("/delRole/{id}")
    public RES delRole(@PathVariable(value = "id") Integer id) {

        String flag = vRoleService.delRole(id);
        if ("删除成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }

    @PostMapping("/addRole")
    public RES addRole(@RequestBody VRole vRole) {

        String flag = vRoleService.addRole(vRole);
        if ("添加成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }

    //------------权限管理接口------------------------------

    @Resource
    VPermissionService vPermissionService;
    @Resource
    VRolePermissionService vRolePermissionService;

    @GetMapping("/getPermissionById/{id}")
    public RES getPermissionById(@PathVariable(value = "id") Integer id) {
        VPermission permissionById = vPermissionService.getPermissionById(id);
        return RES.ok(CommonConstants.SUCCESS, "查询成功", permissionById);
    }

    @GetMapping("/getAllPermission")
    public RES getAllPermission() {
        List<VPermissionVo> allPermission = vPermissionService.getAllPermission();
        return RES.ok(CommonConstants.SUCCESS, "查询成功", allPermission);
    }

    /**
     * 获取权限一级分类
     * @return
     */
    @GetMapping("/getPermissionOne")
    public RES getPermissionOne() {
        List<VPermission> allPermission = vPermissionService.getPermissionOne();
        return RES.ok(CommonConstants.SUCCESS, "查询成功", allPermission);
    }

    @GetMapping("/getUsePermissionByRoleId/{roleId}")
    public RES getUsePermissionByRoleId(@PathVariable(value = "roleId") Integer roleId) {
        Integer[] usePermissionByRoleId = vRolePermissionService.getUsePermissionByRoleId(roleId);
        return RES.ok(CommonConstants.SUCCESS, "查询成功", usePermissionByRoleId);
    }


    @PutMapping("/editRolePermission")
    public RES editRolePermission(@RequestBody VRolePermissionDto vRolePermissionDto) {
        Integer[] permissionIds = vRolePermissionDto.getPermissionIds();
        if (permissionIds != null && permissionIds.length > 0) {
            Integer flag = vRolePermissionService.editRolePermission(vRolePermissionDto);
            if (flag == 0) {
                return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
            }
        } else {
            return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
        }
        return RES.no(CommonConstants.FAIL, "操作失败");
    }

    @PostMapping("/addPermission")
    public RES addPermission(@RequestBody VPermission vPermission) {

        String flag = vPermissionService.addPermission(vPermission);
        if ("添加成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }

    @PostMapping("/editPermission")
    public RES editPermission(@RequestBody VPermission vPermission) {

        String flag = vPermissionService.editPermission(vPermission);
        if ("修改成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }

    @DeleteMapping("/delPermission/{id}/{parentId}")
    public RES delPermission(@PathVariable(value = "id") Integer id,
                             @PathVariable(value = "parentId") Integer parentId) {

        String flag = vPermissionService.delPermission(id,parentId);
        if ("删除成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }
}
