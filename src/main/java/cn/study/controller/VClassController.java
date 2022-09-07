package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VClassDto;
import cn.study.entity.VClass;
import cn.study.service.VClassService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级管理
 */
@RestController
@RequestMapping("/class")
@CrossOrigin
public class VClassController {

    @Resource
    VClassService vClassService;


    /**
     * 列表
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAll")
    public RES getAllSubject(){
        List<VClass> allSubject = vClassService.getAll();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",allSubject);
    }
    /**
     * 分页
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getPage")
    public RES getPage(Page page, VClassDto vClassDto){
        IPage page1 = vClassService.getPage(page, vClassDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }

    /**
     * BygradeId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getClassByGradeId/{gradeId}")
    public RES getClassByGradeId(@PathVariable("gradeId") Integer gradeId){
        List<VClass> list = vClassService.list(Wrappers.<VClass>lambdaQuery()
                .eq(VClass::getGradeId, gradeId)
                .eq(VClass::getDelFlag, CommonConstants.SUCCESS));
        return RES.ok(CommonConstants.SUCCESS,"操作成功",list);
    }

    @SaCheckLogin
    @PostMapping("add")
    public RES add(@RequestBody VClass vClass) {
        String name = vClass.getName();
        Integer gradeId = vClass.getGradeId();
        Integer count = vClassService.count(Wrappers.<VClass>lambdaQuery()
                .eq(VClass::getName, name)
                .eq(VClass::getGradeId, gradeId)
                .eq(VClass::getDelFlag, CommonConstants.STATUS_NORMAL));
        if (count>0){
            return RES.no(CommonConstants.FAIL,"学科已存在");
        }
        vClassService.save(vClass);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @PostMapping("addBatch")
    public RES addBatch(@RequestBody VClassDto vClassDto) {
        String name = vClassDto.getName();
        Integer[] chooseGradeIds = vClassDto.getChooseGradeIds();
        for (int i = 0; i <chooseGradeIds.length ; i++) {
            Integer chooseGradeId = chooseGradeIds[i];
            Integer count = vClassService.count(Wrappers.<VClass>lambdaQuery()
                    .eq(VClass::getName, name)
                    .eq(VClass::getGradeId, chooseGradeId)
                    .eq(VClass::getDelFlag, CommonConstants.STATUS_NORMAL));
            if (count == 0) {
                VClass VClass = new VClass();
                VClass.setName(name);
                VClass.setGradeId(chooseGradeId);
                vClassService.save(VClass);
            }
        }
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @PutMapping("edit")
    public RES edit(@RequestBody VClass vClass) {
        String name = vClass.getName();
        Integer gradeId = vClass.getGradeId();
        Integer count = vClassService.count(Wrappers.<VClass>lambdaQuery()
                .eq(VClass::getName, name)
                .eq(VClass::getGradeId, gradeId)
                .ne(VClass::getId, vClass.getId())
                .eq(VClass::getDelFlag, CommonConstants.STATUS_NORMAL));
        if (count>0){
            return RES.no(CommonConstants.FAIL,"科目已存在");
        }
        vClassService.updateById(vClass);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @DeleteMapping("del/{id}")
    public RES del(@PathVariable(value = "id") Integer id) {
        VClass byId = vClassService.getById(id);
        byId.setDelFlag(CommonConstants.STATUS_DEL);
        vClassService.updateById(byId);
        return RES.ok(CommonConstants.SUCCESS,"删除成功",null);
    }
}
