package cn.study.mapper;

import cn.study.config.ExpandBaseMapper;
import cn.study.entity.VLink;
import cn.study.entity.VLink2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Study2Mapper extends ExpandBaseMapper<VLink2> {

    int inserList(List<VLink2> list);
}
