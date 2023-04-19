package cn.study.controller;


import cn.hutool.core.util.StrUtil;
import cn.study.config.RES;
import cn.study.constant.CommonConstants;
import cn.study.dto.InitTaskDto;
import cn.study.entity.SysUploadTask;
import cn.study.service.SysUploadTaskService;
import cn.study.vo.TaskInfoVo;
import com.amazonaws.services.s3.AmazonS3;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 分片上传-分片任务记录(SysUploadTask)表控制层
 *
 * @since 2022-08-22 17:47:31
 */
@Slf4j
@RestController
@RequestMapping("/v1/minio/tasks")
@Api(value = "文件管理", tags = "文件管理")
public class MinioUploadTaskController {
    /**
     * 服务对象
     */
    @Resource
    private SysUploadTaskService sysUploadTaskService;
    @Resource
    private AmazonS3 amazonS3;
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

    /**
     * 查看---->可直接http://127.0.0.1:9000/bucketName/2023-02-07%2Ff0919f53-9b52-4ad4-b290-67dcf6bead81.jpg
     * @return
     */
    @ApiOperation(value = "查看", notes = "查看")
    @GetMapping("/viewFile")
    public void viewFile(HttpServletResponse response,
                        @RequestParam("bucketName") String bucketName,
                        @RequestParam("fileName") String fileName) {
        
        sysUploadTaskService.download(response,bucketName,fileName);
    }

//    @ApiOperation(value = "视频边下载边看", notes = "视频边下载边看")
//    @GetMapping("/getFileForRange")
//    public void getFileForRange(@RequestParam("fileId") String fileId,
//                                OutputStream out, HttpServletRequest request, HttpServletResponse response) {
//        String filepath = String.format("%s/%s","/aaaa/minio",fileId);
//        FileInputStream inputFileStream = null;
//        BufferedOutputStream outputStream = null;
//        try {
//            File file = new File(filepath);
//            if (!file.exists()) {
//                // 禁止多线程同时下载视频文件
//                try {
//                    InputStream stream   =
//                            amazonS3.getObject("atest", "2023-02-07/d05e8597-eea1-42e0-8534-f8d7850e4932.mp4").getObjectContent();
//                    cn.hutool.core.io.FileUtil.writeFromStream(stream, file);
////                    if(redisUtils.tryLock(CacheKey.VIDEO_CACHE_KEY + fileId)){
////                        //mino获取文件并写在服务端
////                        InputStream stream = fileBiz.get(fileId);
////                        cn.hutool.core.io.FileUtil.writeFromStream(stream, file);
////                    }
//                }catch (Exception e){
//                    log.error("下载文件失败:{}",e);
//                }finally {
//                    //redisUtils.release(CacheKey.VIDEO_CACHE_KEY + fileId);
//                }
//            }
//
//            long point = 0;
//            long fileLength = file.length();
//            long position_end = fileLength;
//
//            inputFileStream = new FileInputStream(file);
//            outputStream = new BufferedOutputStream(response.getOutputStream());
//            response.reset();
//
//            //设置支持断点
//            response.setHeader("Accept-Ranges", "bytes");
//
//            //获取断点位置
//            String Range = request.getHeader("Range");
//            if (Range != null) {
//                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//                String RangeFromTo = Range.replaceAll("bytes=", "");
//                log.info("threadName: " + Thread.currentThread().getId() + " Range : " + RangeFromTo);
//                String[] Ranges = RangeFromTo.split("-");
//                point = Long.parseLong(Ranges[0]);
//
//                if (2 == Ranges.length && !Ranges[1].equals("")) {
//                    position_end = Long.parseLong(Ranges[1]);
//                }
//            }
//
//            //写明要下载的文件的大小
//            response.setHeader("Content-Length", Long.toString(position_end - point));
//            log.info("threadName: " + Thread.currentThread().getId() + "point : " + point + " Content-Length : " + (position_end - point));
//
//            //设置response的编码方式
//            response.setContentType("application/x-msdownload");
//
//            //设置断点续传回应头
//            String contentRange = new StringBuffer("bytes ")
//                    .append(point)
//                    .append("-")
//                    .append(fileLength - 1)
//                    .append("/")
//                    .append(fileLength)
//                    .toString();
//
//            if (point != 0) {
//                // 断点续传的回应头：告诉改块插入的位置和文件的总大小
//                // 格式：Content-Range: bytes [文件块的开始字节]-[文件块的结束字节 - 1]/[文件的总大小]
//                contentRange = new StringBuffer("bytes ")
//                        .append(point)
//                        .append("-")
//                        .append(position_end - 1)
//                        .append("/")
//                        .append(fileLength)
//                        .toString();
//                // 移动文件指针位置，断点处
//                inputFileStream.skip(point);
//            }
//            response.setHeader("Accept-Ranges", "bytes");
//            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//            response.setContentType("video/mp4");
//            // ios 默认不为添加
//            response.setHeader("Content-Type", "video/mp4");
//            response.setHeader("Content-Disposition", "inline;filename=" + filepath);
//            response.setHeader("Content-Length", String.valueOf(position_end - point + 1));
//            response.setHeader("Content-Range", "bytes " + position_end + "-" + point + "/" + file.length());
//            response.setHeader("Content-Range", contentRange);
//
//            long bytesWritten = 0;
//            byte[] bytes = new byte[1024 * 4];
//            int byteCount;
//
//            long NeedWriten = position_end - point + 1;
//
//            while (NeedWriten >= bytesWritten && (byteCount = inputFileStream.read(bytes)) != -1) {
//                if (NeedWriten >= bytesWritten) {
//                    long tTempWriten = (bytesWritten + byteCount) > NeedWriten ? (NeedWriten - bytesWritten) : byteCount;
//                    outputStream.write(bytes, 0, (int) tTempWriten);
//                    bytesWritten += tTempWriten;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            log.error("读取文件失败：" + filepath + " FileNotFoundException happen : " + e + " URL : " + request.getRequestURL().toString());
//        } catch (IOException e) {
//            log.error("读取文件失败：" + filepath + " IOException hapepn : " + e + " URL : " + request.getRequestURL().toString());
//        } finally {
//            try {
//                if (null != inputFileStream) {
//                    inputFileStream.close();
//                }
//                if (null != outputStream) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                log.error("下载文件失败：" + filepath + " EofException happen : " + e);
//            }
//        }
//    }
}
