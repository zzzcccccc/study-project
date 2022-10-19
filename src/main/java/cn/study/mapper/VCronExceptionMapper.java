package cn.study.mapper;

import cn.study.entity.VCronException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务启动异常mapper
 */
@Mapper
public interface VCronExceptionMapper extends BaseMapper<VCronException> {

}
