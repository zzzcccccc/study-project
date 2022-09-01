package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.entity.VGrade;
import cn.study.entity.VLink;
import cn.study.entity.VSubject;
import cn.study.mapper.StudyMapper;
import cn.study.service.VSubjectService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @SaCheckLogin
    @SaCheckPermission("user-add")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

}
