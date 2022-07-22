package cn.study.controller;

import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VMenuDto;
import cn.study.entity.VMenu;
import cn.study.service.VMenuService;
import cn.study.vo.VMenuVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class VMenuController {

    @Resource
    VMenuService vMenuService;

    /**
     * 页面左侧导航栏查询、主列表
     * @return
     */
    @GetMapping("/getAll")
    public RES getAll() {
        List<VMenuVo> all = vMenuService.getAll();
        return RES.ok(CommonConstants.SUCCESS,"查询成功",all);
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
        return RES.ok(CommonConstants.SUCCESS,flag,null);
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

    @DeleteMapping("/delMenu/{id}/{parentId}")
    public RES delMenu(@PathVariable(value = "id") Integer id,
                       @PathVariable(value = "parentId") Integer parentId) {

        String flag = vMenuService.delMenu(id,parentId);
        if ("删除成功".equals(flag)) {
            return RES.ok(CommonConstants.SUCCESS, flag, null);
        }
        return RES.no(CommonConstants.FAIL, flag);
    }
}
