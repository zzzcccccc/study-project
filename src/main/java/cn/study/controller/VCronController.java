package cn.study.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.entity.VCron;
import cn.study.service.VCronService;
import cn.study.task.CronUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cron")
public class VCronController {
    @Resource
    private VCronService vCronService;
    @Resource
    private CronUtils cronUtils;


    /**
     * 列表
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAll")
    public RES getAllSubject(){
        List<VCron> list = vCronService.list(Wrappers.<VCron>lambdaQuery()
                .eq(VCron::getDelFlag, CommonConstants.STATUS_NORMAL));
        return RES.ok(CommonConstants.SUCCESS,"操作成功",list);
    }
    /**
     * 分页
     * @return
     */
    @GetMapping("/getPage")
    public RES getPage(Page page, VCron vCron){
        // 简单分页查询
        QueryWrapper<VCron> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("gender", gender);
        IPage page1 = vCronService.page(page, queryWrapper);
        System.out.println("1111111111111");

        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }
    @SaCheckLogin
    @PostMapping("add")
    public RES add(@RequestBody VCron vCron) {
//        String name = vClass.getName();
//        Integer gradeId = vClass.getGradeId();
//        Integer count = vClassService.count(Wrappers.<VClass>lambdaQuery()
//                .eq(VClass::getName, name)
//                .eq(VClass::getGradeId, gradeId)
//                .eq(VClass::getDelFlag, CommonConstants.STATUS_NORMAL));
//        if (count>0){
//            return RES.no(CommonConstants.FAIL,"学科已存在");
//        }
//        vCronService.save(vCron);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

//
//    @SaCheckLogin
//    @PutMapping("edit")
//    public RES edit(@RequestBody VClass vClass) {
//        String name = vClass.getName();
//        Integer gradeId = vClass.getGradeId();
//        Integer count = vClassService.count(Wrappers.<VClass>lambdaQuery()
//                .eq(VClass::getName, name)
//                .eq(VClass::getGradeId, gradeId)
//                .ne(VClass::getId, vClass.getId())
//                .eq(VClass::getDelFlag, CommonConstants.STATUS_NORMAL));
//        if (count>0){
//            return RES.no(CommonConstants.FAIL,"科目已存在");
//        }
//        vClassService.updateById(vClass);
//        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
//    }
//
//    @SaCheckLogin
//    @DeleteMapping("del/{id}")
//    public RES del(@PathVariable(value = "id") Integer id) {
//        VClass byId = vClassService.getById(id);
//        byId.setDelFlag(CommonConstants.STATUS_DEL);
//        vClassService.updateById(byId);
//        return RES.ok(CommonConstants.SUCCESS,"删除成功",null);
//    }
}
