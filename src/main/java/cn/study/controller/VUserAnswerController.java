package cn.study.controller;

import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.VQuestionDto;
import cn.study.service.VAnswerService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhangcc
 * @description v_user_answer
 * @date 2023-03-08
 */
@RestController
@RequestMapping(value = "/vUserAnswer")
@Api(value = "答卷管理", tags = "答卷管理")
public class VUserAnswerController {

    @Resource
    private VAnswerService vAnswerService;

    /**
     * 查询 分页查询
     *
     * @author zhangcc
     * @date 2023/03/08
     **/
//    @RequestMapping("/pageList")
//    public RES pageList(Page page, VUserAnswer vUserAnswer) {
//        return vUserAnswerService.pageList(page, vUserAnswer);
//    }
//

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
//
//    /**
//     * 更新
//     *
//     * @author zhangcc
//     * @date 2023/03/08
//     **/
//    @PutMapping("/update")
//    public RES update(@RequestBody  VUserAnswer vUserAnswer) {
//        return vUserAnswerService.update(vUserAnswer);
//    }


}