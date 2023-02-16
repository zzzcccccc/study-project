package cn.study.controller;

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
    public RES getPage(Page page){
        QueryWrapper<VQuestion> wrapper = new QueryWrapper<>();
        IPage page1 = vQuestionService.page(page);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }


    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    public RES add(@RequestBody VQuestionDto vQuestionDto) {
        vQuestionService.add(vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"保存成功",null);
    }
}
