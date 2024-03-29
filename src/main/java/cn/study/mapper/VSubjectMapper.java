package cn.study.mapper;

import cn.study.dto.VSubjectDto;
import cn.study.entity.VSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VSubjectMapper extends BaseMapper<VSubject> {

    IPage getPageSubject(@Param("page") Page page, @Param("subjectDto") VSubjectDto subjectDto);
}
