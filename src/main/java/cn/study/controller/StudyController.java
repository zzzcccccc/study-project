package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.entity.VLink;
import cn.study.mapper.StudyMapper;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/link")
@CrossOrigin
@Api(value = "测试管理", tags = "测试")
public class StudyController {

    @Resource
    StudyMapper studyMapper;

    @ApiOperation(value = "list列表", notes = "list列表")
    @GetMapping("/getlist")
    public R getlist(){
        List<VLink> vLinks = studyMapper.selectList(null);
        return R.ok(vLinks);
    }

    @SaCheckLogin
    @GetMapping("/getAll")
    @ApiOperation(value = "查询所有", notes = "查询所有")
    public R getAll(){
        List<VLink> vLinks = studyMapper.selectList(null);
        return R.ok(vLinks);
    }

    @SaCheckLogin
    @SaCheckPermission("user-add")
    @PostMapping("add")
    @ApiOperation(value = "用户增加", notes = "用户增加")
    public String add() {
        return "用户增加";
    }

}
