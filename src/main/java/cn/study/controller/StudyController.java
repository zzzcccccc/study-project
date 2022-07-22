package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.entity.VLink;
import cn.study.mapper.StudyMapper;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/link")
@CrossOrigin
public class StudyController {

    @Resource
    StudyMapper studyMapper;

    @GetMapping("/getAll")
    public R getAll(){
        List<VLink> vLinks = studyMapper.selectList(null);
        return R.ok(vLinks);
    }

    @SaCheckPermission("user-add")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

}
