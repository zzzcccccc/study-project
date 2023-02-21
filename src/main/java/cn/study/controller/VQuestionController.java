package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.entity.VQuestion;
import cn.study.service.VQuestionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description v_question
 * @author zhangcc
 * @date 2023-02-09
 */
@RestController
@RequestMapping(value = "/vQuestion")
@Api(value = "题库管理", tags = "题库管理")
public class VQuestionController {

    @Resource
    private VQuestionService vQuestionService;

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/getPage")
    public RES getPage(Page page,VQuestionDto vQuestionDto){
        IPage page1 = vQuestionService.getPage(page, vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }


    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    public RES add(@RequestBody VQuestionDto vQuestionDto) {
        vQuestionService.add(vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("/edit")
    public RES edit(@RequestBody VQuestion vQuestion) {
        vQuestionService.edit(vQuestion);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }

    @ApiOperation(value = "删除",notes = "删除")
    @SaCheckLogin
    @DeleteMapping("del/{id}")
    public RES del(@PathVariable("id") Long id) {
        Integer flag = vQuestionService.del(id);
        if (flag==1){
            return RES.ok(CommonConstants.SUCCESS,"删除成功",null);
        }
        return RES.no(CommonConstants.FAIL,"删除失败");
    }

}
