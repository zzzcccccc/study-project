package cn.study.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VAnswerInfoDto;
import cn.study.dto.VAnswerElsDto;
import cn.study.dto.VQuestionDto;
import cn.study.service.VAnswerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhangcc
 * @description v_user_answer
 * @date 2023-03-08
 */
@RestController
@RequestMapping(value = "/paper/answer")
@Api(value = "答卷管理", tags = "答卷管理")
public class VAnswerController {

    @Resource
    private VAnswerService vAnswerService;

    /**
     * 查询
     *
     * @author zhangcc
     * @date 2023/03/08
     **/
    @GetMapping("/getList")
    public RES pageList(VAnswerInfoDto vAnswerInfoDto) {
        return RES.ok(CommonConstants.SUCCESS, "操作成功",  vAnswerService.getList(vAnswerInfoDto));
    }


    /**
     * 新增
     *
     * @author zhangcc
     * @date 2023/03/08
     **/
    @PostMapping("/insert")
    public RES insert(@RequestBody VQuestionDto vQuestionDto) {
        vAnswerService.insert(vQuestionDto);
        return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
    }
//
//    /**
//     * 刪除
//     *
//     * @author zhangcc
//     * @date 2023/03/08
//     **/
//    @DeleteMapping("/delete/{id}")
//    public RES delete(Long id) {
//        return vUserAnswerService.delete(id);
//    }


    /**
     * 批改
     *
     * @author zhangcc
     * @date 2023/03/08
     **/
    @ApiOperation(value = "批改", notes = "批改")
    @PutMapping("/correct")
    @SaCheckRole(value = {"admin","teacher"})
    public RES correct(@RequestBody VAnswerInfoDto VAnswerInfoDto) {
        int edit = vAnswerService.correct(VAnswerInfoDto);
        return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
    }


    /**
     * 更新
     *
     * @author zhangcc
     * @date 2023/03/08
     **/
    @PutMapping("/update")
    public RES update(@RequestBody VAnswerElsDto vAnswerElsDto) {
        int edit = vAnswerService.edit(vAnswerElsDto);
        return RES.ok(CommonConstants.SUCCESS, "操作成功", null);
    }


    @ApiOperation(value = "获取试卷答卷名单", notes = "获取试卷答卷名单")
    @GetMapping("/getPageToAnswerInfo")
    public RES getPageToAnswerInfo(Page page, VAnswerInfoDto VAnswerInfoDto){
        IPage page1 = vAnswerService.getPageToAnswerInfo(page, VAnswerInfoDto);
        return RES.ok(CommonConstants.SUCCESS,"操作成功",page1);
    }

}