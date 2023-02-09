package cn.study.service;

import cn.study.dto.InitTaskDto;
import cn.study.entity.SysUploadTask;
import cn.study.vo.TaskInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * 分片上传-分片任务记录(SysUploadTask)表服务接口
 *
 * @since 2022-08-22 17:47:30
 */
public interface SysUploadTaskService extends IService<SysUploadTask> {

    /**
     * 根据md5标识获取分片上传任务
      * @param identifier
     * @return
     */
    SysUploadTask getByIdentifier (String identifier);

    /**
     * 初始化一个任务
     */
    TaskInfoVo initTask (InitTaskDto param);

    /**
     * 获取文件地址
     * @param bucket
     * @param objectKey
     * @return
     */
    String getPath (String bucket, String objectKey);

    /**
     * 获取上传进度
     * @param identifier
     * @return
     */
    TaskInfoVo getTaskInfo (String identifier);

    /**
     * 生成预签名上传url
     * @param bucket 桶名
     * @param objectKey 对象的key
     * @param params 额外的参数
     * @return
     */
    String genPreSignUploadUrl (String bucket, String objectKey, Map<String, String> params);

    /**
     * 合并分片
     * @param identifier
     */
    void merge (String identifier);

    void download(HttpServletResponse response, String bucketName, String fileName);
}
