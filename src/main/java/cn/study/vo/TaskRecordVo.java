package cn.study.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.study.entity.SysUploadTask;
import com.amazonaws.services.s3.model.PartSummary;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class TaskRecordVo extends SysUploadTask {

    /**
     * 已上传完的分片
     */
    private List<PartSummary> exitPartList;

    public static TaskRecordVo convertFromEntity (SysUploadTask task) {
        TaskRecordVo dto = new TaskRecordVo();
        BeanUtil.copyProperties(task, dto);
        return dto;
    }
}
