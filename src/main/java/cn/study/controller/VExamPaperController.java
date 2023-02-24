package cn.study.controller;

import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.mapper.VExamPaperMapper;
import cn.study.service.VExamPaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangcc
 * @date 2023-02-24 11:05
 * @desc 试卷
 */
@RestController
@Api(value = "试卷管理",tags = "试卷管理")
@RequestMapping("/paper")
public class VExamPaperController {

    @Autowired
    VExamPaperService vExamPaperService;


    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/getPage")
    public RES getPage(Page page, VQuestionDto vQuestionDto){
        IPage page1 = vExamPaperService.getPage(page, vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }

}
