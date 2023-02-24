package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VSubjectDto;
import cn.study.entity.VGrade;
import cn.study.entity.VLink;
import cn.study.entity.VSubject;
import cn.study.mapper.StudyMapper;
import cn.study.service.VSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学科管理（年级、科目）
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin
public class VSubjectController {

    @Resource
    VSubjectService vSubjectService;

    /**
     * 查所有年级
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAllGrade")
    public RES getAllGrade(){
        List<VGrade> allGrade = vSubjectService.getAllGrade();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",allGrade);
    }

    /**
     * 学科列表
     * 查询去重
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAllSubject")
    public RES getAllSubject(){
        List<VSubject> allSubject = vSubjectService.getAllSubject();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",allSubject);
    }

    /**
     * 学科列表BygradeId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getSubjectByGradeId/{gradeId}")
    public RES getSubjectByGradeId(@PathVariable("gradeId") Integer gradeId){
        List<VSubject> list = vSubjectService.list(Wrappers.<VSubject>lambdaQuery()
                .eq(VSubject::getGradeId, gradeId)
                .eq(VSubject::getDelFlag, CommonConstants.SUCCESS));
        return RES.ok(CommonConstants.SUCCESS,"操作成功",list);
    }
    /**
     * 学科列表--分页
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getPageSubject")
    public RES getPageSubject(Page page, VSubjectDto subjectDto){
        IPage page1 = vSubjectService.getPageSubject(page, subjectDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }

    @SaCheckLogin
    @PostMapping("add")
    public RES add(@RequestBody VSubject vSubject) {
        String name = vSubject.getName();
        Integer gradeId = vSubject.getGradeId();
        Integer count = vSubjectService.count(Wrappers.<VSubject>lambdaQuery()
                .eq(VSubject::getName, name)
                .eq(VSubject::getGradeId, gradeId)
                .eq(VSubject::getDelFlag, CommonConstants.STATUS_NORMAL));
        if (count>0){
            return RES.no(CommonConstants.FAIL,"学科已存在");
        }
        vSubjectService.save(vSubject);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @PostMapping("addBatch")
    public RES addBatch(@RequestBody VSubjectDto vSubjectDto) {
        String name = vSubjectDto.getName();
        Integer[] chooseGradeIds = vSubjectDto.getChooseGradeIds();
        for (int i = 0; i <chooseGradeIds.length ; i++) {
            Integer chooseGradeId = chooseGradeIds[i];
            Integer count = vSubjectService.count(Wrappers.<VSubject>lambdaQuery()
                    .eq(VSubject::getName, name)
                    .eq(VSubject::getGradeId, chooseGradeId)
                    .eq(VSubject::getDelFlag, CommonConstants.STATUS_NORMAL));
            if (count == 0) {
                VSubject vSubject = new VSubject();
                vSubject.setName(name);
                vSubject.setGradeId(chooseGradeId);
                vSubjectService.save(vSubject);
            }
        }
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @PutMapping("edit")
    public RES edit(@RequestBody VSubject vSubject) {
        String name = vSubject.getName();
        Integer gradeId = vSubject.getGradeId();
        Integer count = vSubjectService.count(Wrappers.<VSubject>lambdaQuery()
                .eq(VSubject::getName, name)
                .eq(VSubject::getGradeId, gradeId)
                .ne(VSubject::getId, vSubject.getId())
                .eq(VSubject::getDelFlag, CommonConstants.STATUS_NORMAL));
        if (count>0){
            return RES.no(CommonConstants.FAIL,"科目已存在");
        }
        vSubjectService.updateById(vSubject);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @DeleteMapping("del/{id}")
    public RES del(@PathVariable(value = "id") Integer id) {
        VSubject byId = vSubjectService.getById(id);
        byId.setDelFlag(CommonConstants.STATUS_DEL);
        vSubjectService.updateById(byId);
        return RES.ok(CommonConstants.SUCCESS,"删除成功",null);
    }
}
