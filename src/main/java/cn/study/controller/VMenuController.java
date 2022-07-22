package cn.study.controller;

import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VMenuDto;
import cn.study.dto.VRoleMenuDto;
import cn.study.entity.VMenu;
import cn.study.service.VMenuService;
import cn.study.vo.VMenuVo;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class VMenuController {

    @Resource
    VMenuService vMenuService;

    /**
     * 页面左侧导航栏查询
     * @return
     */
    @GetMapping("/getAll/{flag}")
    public RES getAll(@PathVariable(value = "flag") Integer flag) {
        List<VMenuVo> all = vMenuService.getAll(flag);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",all);
    }

    @GetMapping("/getRoleMenuByRoleId/{roleId}")
    public RES getRoleMenuByRoleId(@PathVariable(value = "roleId") Integer roleId) {
        Integer[] ids = vMenuService.getRoleMenuByRoleId(roleId);
        return RES.ok(CommonConstants.SUCCESS,"查询成功",ids);
    }

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

    @PostMapping("/addMenu")
    public RES addMenu(@RequestBody VMenuDto vMenuDto) {
        String flag  = vMenuService.addMenu(vMenuDto);
        if ("添加成功".equals(flag)){
            return RES.ok(CommonConstants.SUCCESS,flag,null);
        }
        return RES.no(CommonConstants.FAIL,flag);

    }

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

    @DeleteMapping("/delMenu/{id}/{level}")
    public RES delMenu(@PathVariable(value = "id") Integer id,
                       @PathVariable(value = "level") Integer level) {

        String flag = vMenuService.delMenu(id,level);
        if ("删除成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }
}
