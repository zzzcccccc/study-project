package cn.study.mapper;

import cn.study.entity.VGrade;
import cn.study.entity.VSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 年级
 */
@Mapper
public interface VGradeMapper extends BaseMapper<VGrade> {
}
