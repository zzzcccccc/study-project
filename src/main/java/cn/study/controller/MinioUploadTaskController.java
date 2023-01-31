package cn.study.controller;


import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.InitTaskDto;
import cn.study.entity.SysUploadTask;
import cn.study.service.SysUploadTaskService;
import cn.study.vo.TaskInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * 分片上传-分片任务记录(SysUploadTask)表控制层
 *
 * @since 2022-08-22 17:47:31
 */
@RestController
@RequestMapping("/v1/minio/tasks")
@Api(value = "文件管理", tags = "文件管理")
public class MinioUploadTaskController {
    /**
     * 服务对象
     */
    @Resource
    private SysUploadTaskService sysUploadTaskService;


    /**
     * 获取上传进度
     * @param identifier 文件md5
     * @return
     */
    @ApiOperation(value = "获取上传进度", notes = "获取上传进度")
    @GetMapping("/{identifier}")
    public RES taskInfo (@PathVariable("identifier") String identifier) {
        TaskInfoVo taskInfoVo =  sysUploadTaskService.getTaskInfo(identifier);
        return  RES.ok(CommonConstants.SUCCESS,"成功",taskInfoVo);
    }

    /**
     * 创建一个上传任务
     * @return
     */
    @PostMapping("/initTask")
    @ApiOperation(value = "创建一个上传任务", notes = "创建一个上传任务")
    public RES initTask (@Valid @RequestBody InitTaskDto param) {
        return RES.ok(CommonConstants.SUCCESS,"",sysUploadTaskService.initTask(param));
    }

    /**
     * 获取每个分片的预签名上传地址
     * @param identifier
     * @param partNumber
     * @return
     */
    @ApiOperation(value = "获取每个分片的预签名上传地址", notes = "获取每个分片的预签名上传地址")
    @GetMapping("/{identifier}/{partNumber}")
    public RES preSignUploadUrl (@PathVariable("identifier") String identifier, @PathVariable("partNumber") Integer partNumber) {
        SysUploadTask task = sysUploadTaskService.getByIdentifier(identifier);
        if (task == null) {
            return RES.no(CommonConstants.FAIL,"分片任务不存在");
        }
        Map<String, String> params = new HashMap<>();
        params.put("partNumber", partNumber.toString());
        params.put("uploadId", task.getUploadId());

        String s = sysUploadTaskService.genPreSignUploadUrl(task.getBucketName(), task.getObjectKey(), params);
        return RES.ok(CommonConstants.SUCCESS,"", s);
    }

    /**
     * 合并分片
     * @param identifier
     * @return
     */
    @ApiOperation(value = "合并分片", notes = "合并分片")
    @PostMapping("/merge/{identifier}")
    public RES merge (@PathVariable("identifier") String identifier) {
        sysUploadTaskService.merge(identifier);
        return RES.ok(CommonConstants.SUCCESS,"",null);
    }

}
