package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VClassDto;
import cn.study.entity.VClass;
import cn.study.entity.VCron;
import cn.study.service.VClassService;
import cn.study.service.VCronService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级管理
 */
@RestController
@RequestMapping("/class")
@CrossOrigin
public class VClassController {

    @Resource
    private VClassService vClassService;


    /**
     * 列表
     * @return
     */

    @GetMapping("/getAll")
    public RES getAllSubject(){
        List<VClass> all = vClassService.getAll();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",all);
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
        return RES.ok(CommonConstants.SUCCESS, "删除成功", null);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //第一种方式获取Class对象
        VClass stu1 = new VClass();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = VClass.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            Class stuClass3 = Class.forName("cn.study.entity.VClass");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
