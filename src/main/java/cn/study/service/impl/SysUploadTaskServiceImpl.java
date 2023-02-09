package cn.study.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.study.constant.MinioConstant;
import cn.study.dto.InitTaskDto;
import cn.study.entity.SysUploadTask;
import cn.study.mapper.SysUploadTaskMapper;
import cn.study.properties.MinioProperties;
import cn.study.service.SysUploadTaskService;
import cn.study.vo.TaskInfoVo;
import cn.study.vo.TaskRecordVo;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分片上传-分片任务记录(SysUploadTask)表服务实现类
 *
 * @since 2022-08-22 17:47:31
 */
@Service("sysUploadTaskService")
public class SysUploadTaskServiceImpl extends ServiceImpl<SysUploadTaskMapper, SysUploadTask> implements SysUploadTaskService {

    @Resource
    private AmazonS3 amazonS3;

    @Resource
    private MinioProperties minioProperties;

    @Resource
    private SysUploadTaskMapper sysUploadTaskMapper;

    @Override
    public SysUploadTask getByIdentifier(String identifier) {
        return sysUploadTaskMapper.selectOne(new QueryWrapper<SysUploadTask>().lambda()
                .eq(SysUploadTask::getFileIdentifier, identifier));
    }


    @Override
    public TaskInfoVo initTask(InitTaskDto param) {
        Date currentDate = new Date();
        String bucketName = minioProperties.getBucket(); //可让前端传指定Bucket
        String fileName = param.getFileName();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        String key = StrUtil.format("{}/{}.{}", DateUtil.format(currentDate, "YYYY-MM-dd"), IdUtil.randomUUID(), suffix);
        String contentType = MediaTypeFactory.getMediaType(key).orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        InitiateMultipartUploadResult initiateMultipartUploadResult = amazonS3
                .initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, key).withObjectMetadata(objectMetadata));
        String uploadId = initiateMultipartUploadResult.getUploadId();

        SysUploadTask task = new SysUploadTask();
        int chunkNum = (int) Math.ceil(param.getTotalSize() * 1.0 / param.getChunkSize());
        task.setBucketName(minioProperties.getBucket())
                .setChunkNum(chunkNum)
                .setChunkSize(param.getChunkSize())
                .setTotalSize(param.getTotalSize())
                .setFileIdentifier(param.getIdentifier())
                .setFileName(fileName)
                .setObjectKey(key)
                .setUploadId(uploadId);
        sysUploadTaskMapper.insert(task);
        return new TaskInfoVo()
                .setFinished(false)
                .setTaskRecord(TaskRecordVo.convertFromEntity(task))
                .setPath(getPath(bucketName, key));
    }

    @Override
    public String getPath(String bucket, String objectKey) {
        return StrUtil.format("{}/{}/{}", minioProperties.getEndpoint(), bucket, objectKey);
    }

    @Override
    public TaskInfoVo getTaskInfo(String identifier) {
        SysUploadTask task = getByIdentifier(identifier);
        if (task == null) {
            return null;
        }
        TaskInfoVo result = new TaskInfoVo()
                .setFinished(true)
                .setTaskRecord(TaskRecordVo.convertFromEntity(task))
                .setPath(getPath(task.getBucketName(), task.getObjectKey()));

        boolean doesObjectExist = amazonS3.doesObjectExist(task.getBucketName(), task.getObjectKey());
        if (!doesObjectExist) {
            // 未上传完，返回已上传的分片
            ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
            PartListing partListing = amazonS3.listParts(listPartsRequest);
            result.setFinished(false).getTaskRecord().setExitPartList(partListing.getParts());
        }
        return result;
    }

    @Override
    public String genPreSignUploadUrl(String bucket, String objectKey, Map<String, String> params) {
        Date currentDate = new Date();
        Date expireDate = DateUtil.offsetMillisecond(currentDate, MinioConstant.PRE_SIGN_URL_EXPIRE.intValue());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, objectKey)
                .withExpiration(expireDate).withMethod(HttpMethod.PUT);
        if (params != null) {
            params.forEach((key, val) -> request.addRequestParameter(key, val));
        }
        URL preSignedUrl = amazonS3.generatePresignedUrl(request);
        return preSignedUrl.toString();
    }

    @Override
    public void merge(String identifier) {
        SysUploadTask task = getByIdentifier(identifier);
        if (task == null) {
            throw new RuntimeException("分片任务不存");
        }

        ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
        PartListing partListing = amazonS3.listParts(listPartsRequest);
        List<PartSummary> parts = partListing.getParts();
        if (!task.getChunkNum().equals(parts.size())) {
            // 已上传分块数量与记录中的数量不对应，不能合并分块
            throw new RuntimeException("分片缺失，请重新上传");
        }
        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest()
                .withUploadId(task.getUploadId())
                .withKey(task.getObjectKey())
                .withBucketName(task.getBucketName())
                .withPartETags(parts.stream().map(partSummary -> new PartETag(partSummary.getPartNumber(), partSummary.getETag())).collect(Collectors.toList()));
        CompleteMultipartUploadResult result = amazonS3.completeMultipartUpload(completeMultipartUploadRequest);
    }



    //--------------------------
    @SneakyThrows
    @Override
    public void download(HttpServletResponse response, String bucketName, String fileName) {
        InputStream inputStream  = amazonS3.getObject(bucketName, fileName).getObjectContent();
//        response.setContentType("image/jpeg; charset=UTF-8");
 //response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.copy(inputStream, response.getOutputStream());
    }
}
