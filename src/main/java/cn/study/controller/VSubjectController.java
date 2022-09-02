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
    @GetMapping("/getAllGrade")
    public RES getAllGrade(){
        List<VGrade> allGrade = vSubjectService.getAllGrade();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",allGrade);
    }

    /**
     * 学科列表
     * @return
     */
    @SaCheckLogin
    @GetMapping("/getAllSubject")
    public RES getAllSubject(){
        List<VSubject> allSubject = vSubjectService.getAllSubject();
        return RES.ok(CommonConstants.SUCCESS,"操作成功",allSubject);
    }
    /**
     * 学科列表--分页
     * @return
     */
    @GetMapping("/getPageSubject")
    public RES getPageSubject(Page page, VSubjectDto subjectDto){
        QueryWrapper<VSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(subjectDto.getName()), "name", subjectDto.getName());
        queryWrapper.eq(StringUtils.isNotBlank(subjectDto.getGradeName()), "grade_name", subjectDto.getGradeName());
        queryWrapper.eq("del_flag",CommonConstants.SUCCESS);
        IPage page1 = vSubjectService.page(page, queryWrapper);
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
        if (count>1){
            return RES.no(CommonConstants.FAIL,"科目已存在");
        }
        vSubjectService.save(vSubject);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @SaCheckLogin
    @PostMapping("edit")
    public RES edit(@RequestBody VSubject vSubject) {
        String name = vSubject.getName();
        Integer gradeId = vSubject.getGradeId();
        Integer count = vSubjectService.count(Wrappers.<VSubject>lambdaQuery()
                .eq(VSubject::getName, name)
                .eq(VSubject::getGradeId, gradeId)
                .ne(VSubject::getId, vSubject.getId())
                .eq(VSubject::getDelFlag, CommonConstants.STATUS_NORMAL));
        if (count>1){
            return RES.no(CommonConstants.FAIL,"科目已存在");
        }
        vSubjectService.updateById(vSubject);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

}
