package cn.study.mapper;

import cn.study.dto.VClassDto;
import cn.study.dto.VSubjectDto;
import cn.study.entity.VClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VClassMapper extends BaseMapper<VClass> {

    IPage getPage(@Param("page") Page page, @Param("vClassDto") VClassDto vClassDto);
}
