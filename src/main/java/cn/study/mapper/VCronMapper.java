package cn.study.mapper;

import cn.study.entity.VCron;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务mapper
 */
@Mapper
public interface VCronMapper extends BaseMapper<VCron> {

}
