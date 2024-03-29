package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.service.VPaperService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangcc
 * @date 2023-02-24 11:05
 * @desc 试卷
 */
@RestController
@Api(value = "试卷管理",tags = "试卷管理")
@RequestMapping("/paper")
public class VPaperController {

    @Autowired
    VPaperService vPaperService;


//    @ApiOperation(value = "test", notes = "test")
//    @GetMapping("/test")
//    public RES test(){
//
//        TestVo testVo = new TestVo();
//        testVo.setValue(1);
//        testVo.setLabel("test1");
//
//        TestVo testVo2 = new TestVo();
//        testVo2.setValue(2);
//        testVo2.setLabel("test2");
//
//        List<TestVo> testVoList = new ArrayList<>();
//        testVoList.add(testVo);
//        testVoList.add(testVo2);
//
//
//        return RES.ok(CommonConstants.SUCCESS,"操作成功",testVoList);
//    }



    @ApiOperation(value = "分页-管理员查看试卷", notes = "分页-管理员查看试卷")
    @GetMapping("/getPageAdmin")
    public RES getPageAdmin(Page page, VQuestionDto vQuestionDto){
        IPage page1 = vPaperService.getPageAdmin(page, vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }


    @ApiOperation(value = "分页-老师学生", notes = "分页-老师学生")
    @GetMapping("/getPage")
    public RES getPage(Page page, VQuestionDto vQuestionDto){
        IPage page1 = vPaperService.getPage(page, vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @SaCheckPermission(value = "paper:create", orRole = {"admin","teacher"})
    public RES add(@RequestBody VQuestionDto vQuestionDto){
        int add = vPaperService.add(vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @SaCheckPermission(value = "paper:add", orRole = {"admin","teacher"})
    @PostMapping("/insert")
    public RES insert(@RequestBody VQuestionDto vQuestionDto){
        int add = vPaperService.insert(vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @SaCheckPermission(value = "paper:del", orRole = {"admin","teacher"})
    @DeleteMapping("/del/{paperId}")
    public RES del(@PathVariable(value = "paperId") Long paperId){
        vPaperService.del(paperId);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",null);
    }
}
